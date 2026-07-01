import { expect, type Locator } from 'playwright/test';
import { waitForNoLoading } from '../twentyfive-cdp';
import { clickAttached } from '../twentyfive-ui';
import { normalizeText, xpathLiteral } from './xpath';
import { BasePage } from './base-page';

/**
 * Estimate / Labor-grid controls for the ExtJS LOCKED (split) grid.
 *
 * Cell resolution is split by sub-grid, on purpose:
 *  - LEFT/locked columns (WBS, Grade, Location, Role) are the FIRST gridcells in the DOM, so
 *    `resourceGridCell` resolves them by a small leading positional index — safe because they lead.
 *  - RIGHT/normal columns (FTE, Effort, ...) are NOT positionally reachable (an absolute
 *    getByRole('gridcell').nth() counts across both sub-grids + hidden columns), so they MUST be
 *    resolved by the column HEADER's x-position + the data ROW's y-position (openGridCellEditorByHeader).
 * Never add an absolute positional index for a right-grid column — that was the FTE bug.
 */
export class EstimatePage extends BasePage {
  /** Resource grid, identified by its WBS + Grade column headers. */
  resourceGrid(): Locator {
    return this.activeTabPanel()
      .locator(
        "xpath=.//*[@role='grid' and .//*[@role='columnheader' and normalize-space()='WBS'] and .//*[@role='columnheader' and normalize-space()='Grade']]",
      )
      .first();
  }

  /**
   * The clickable "click here to add" node on an empty grid. The real click target is the inner
   * div[text()] inside the [data-grigaddlink] anchor, scoped to the visible grid/treegrid —
   * clicking the anchor wrapper itself does not create a row.
   */
  noRecordsAddLink(): Locator {
    return this.page
      .locator(
        "xpath=//*[@role='tabpanel' and @aria-hidden='false']//*[@aria-hidden='false' and (@role='grid' or @role='treegrid')]//*[@data-grigaddlink='true']//div[text()]",
      )
      .first();
  }

  // Leading (locked) column indexes only. These columns are the first gridcells in the DOM, so a
  // small positional index is safe. Do NOT add right-grid columns (FTE/Effort) here — they are not
  // positionally reachable; use openGridCellEditorByHeader (header-x + row-y) for those.
  private static readonly RESOURCE_COLUMN_INDEXES: Record<string, number> = { WBS: 2, Grade: 3, Location: 4, Role: 5 };

  /** Resolve a LEFT/locked resource-grid cell (WBS/Grade/Location/Role) by its leading position. */
  async resourceGridCell(columnName: string): Promise<Locator> {
    const index = EstimatePage.RESOURCE_COLUMN_INDEXES[columnName];
    if (index === undefined) {
      throw new Error(`${columnName} is not a locked resource column; right-grid columns must use openGridCellEditorByHeader.`);
    }
    const cell = this.activeTabPanel().getByRole('gridcell').nth(index);
    await expect(cell, `${columnName} gridcell should be visible`).toBeVisible({ timeout: 30_000 });
    return cell;
  }

  /**
   * The input of the currently-open ExtJS grid cell editor. This grid renders ~20 inputs, so the
   * old "last visible input on the page" heuristic grabbed the wrong field; the active editor lives
   * inside `.x-grid-cell-editor`/`.x-grid-editor` and is the only visible one of its kind.
   */
  private activeCellEditorInput(): Locator {
    return this.page.locator('.x-grid-cell-editor input:visible, .x-grid-editor input:visible').first();
  }

  /**
   * Select `value` on the currently-focused ExtJS grid-cell combo by its STORE RECORD, not the
   * boundlist UI. After Grade, the Role combo's boundlist renders with offsetParent=null (paints as
   * not-visible), so no UI option is ever clickable — but the combo's remote store is fully loaded, so
   * selecting the matching record on the component commits deterministically. Returns false until a
   * matching record exists (store still loading). Prefers an exact displayField match.
   */
  private async selectComboRecord(value: string): Promise<boolean> {
    return this.page
      .waitForFunction(
        (needle) => {
          const Ext = (window as unknown as { Ext?: any }).Ext;
          const combo = (Ext?.ComponentQuery.query('combobox') || []).filter((c: any) => c.isVisible?.()).find((c: any) => c.hasFocus);
          if (!combo) return false;
          const store = combo.getStore?.();
          if (!store || !store.getCount()) return false;
          const field = combo.displayField;
          const items = store.getRange();
          const lower = String(needle).toLowerCase();
          const rec =
            items.find((r: any) => String(r.get(field)).trim().toLowerCase() === lower) ||
            items.find((r: any) => String(r.get(field)).toLowerCase().includes(lower));
          if (!rec) return false;
          if (combo.select) combo.select(rec);
          else combo.setValue(rec);
          combo.fireEvent('select', combo, rec);
          return true;
        },
        value,
        { timeout: 15_000, polling: 300 },
      )
      .then(() => true, () => false);
  }

  /**
   * Open a resource-grid cell's inline combo editor, type to filter the remote boundlist, pick the
   * matching option with a real click, and commit. Selecting a boundlist item does NOT write the
   * value back on its own — the edit must be completed (Tab) before the cell shows it and coupled
   * fields (e.g. Role -> Location) auto-populate.
   */
  async selectResourceGridDropdown(columnName: string, value: string): Promise<void> {
    const cell = await this.resourceGridCell(columnName);
    for (let attempt = 1; attempt <= 3; attempt += 1) {
      // Proven sequence (from live probing): dblclick to open + focus the ExtJS cell editor, wait a
      // fixed settle, then type REAL keystrokes into the focused editor. This drives the
      // remote/forceSelection/minChars=3 combo so its boundlist populates. Adding Escape/clear
      // keystrokes here suppresses the boundlist — keep this minimal.
      await clickAttached(cell, `${columnName} cell`, 20_000);
      await cell.dblclick({ force: true, timeout: 8_000 }).catch(() => undefined);
      await this.page.waitForTimeout(700);
      await this.page.keyboard.type(value, { delay: 40 }); // triggers the remote store to load matches

      if (await this.selectComboRecord(value)) {
        await this.page.keyboard.press('Tab'); // commit — writes the selected value back (verified it retains)
        await waitForNoLoading(this.page, 20_000);
        return;
      }
      console.warn(`[${columnName}] no matching store record for "${value}" (attempt ${attempt}); retrying.`);
      await this.page.keyboard.press('Escape').catch(() => undefined); // cancel the uncommitted edit before retrying
      await this.page.waitForTimeout(500);
    }
    throw new Error(`Could not select "${value}" in the ${columnName} grid combo (boundlist option never appeared).`);
  }

  /**
   * Open a grid cell editor by the column HEADER's x-position and the data ROW's y-position, then
   * double-click that point. Robust for the locked/split grid: absolute `getByRole('gridcell').nth()`
   * counts across both sub-grids + hidden columns, so a right-grid column like FTE is unreachable
   * positionally — but the header pixel column always aligns with its cells.
   */
  private async openGridCellEditorByHeader(headerLabel: string, rowText: string): Promise<void> {
    const header = this.page
      .locator(`xpath=//*[@role='tabpanel' and @aria-hidden='false']//*[@role='columnheader'][starts-with(normalize-space(.), ${xpathLiteral(headerLabel)})]`)
      .first();
    const rowCell = this.page
      .locator(`xpath=//*[@role='tabpanel' and @aria-hidden='false']//*[@role='gridcell'][contains(normalize-space(.), ${xpathLiteral(rowText)})]`)
      .first();
    await expect(header, `${headerLabel} column header should be visible`).toBeVisible({ timeout: 10_000 });
    await header.scrollIntoViewIfNeeded().catch(() => undefined);
    await expect(rowCell, `row '${rowText}' should be visible`).toBeVisible({ timeout: 10_000 });
    const hb = await header.boundingBox();
    const rb = await rowCell.boundingBox();
    if (!hb || !rb) throw new Error(`Could not resolve ${headerLabel} cell coordinates for row '${rowText}'`);
    const x = hb.x + hb.width / 2;
    const y = rb.y + rb.height / 2;
    await this.page.mouse.click(x, y);
    await this.page.mouse.dblclick(x, y);
  }

  /** Set the FTE value on the planning grid row identified by rowText, then commit. */
  async setFte(rowText: string, value: string): Promise<void> {
    await expect(this.textLocator(rowText)).toBeVisible({ timeout: 20_000 });
    await this.openGridCellEditorByHeader('FTE', rowText); // FTE is in the right (normal) sub-grid

    const editorInput = this.activeCellEditorInput();
    await expect(editorInput, 'FTE cell editor input should be visible').toBeVisible({ timeout: 10_000 });
    await this.setInputText(editorInput, value);
    await editorInput.press('Tab');
    await waitForNoLoading(this.page, 30_000);
  }

  /** A visible element (scoped to the active panel) containing the given text. */
  textLocator(text: string): Locator {
    return this.activeTabPanel().locator(`xpath=.//*[contains(normalize-space(.), ${xpathLiteral(text)})]`).first();
  }

  /** Read the committed text of a resource-grid cell by column (WBS/Grade/Location/Role). */
  async resourceCellText(columnName: string): Promise<string> {
    const cell = await this.resourceGridCell(columnName);
    return normalizeText(await this.readLocatorValue(cell));
  }

  /** Read the proposal KPI header band (Total Price / Total Cost / Engagement Margin summary). */
  async kpiBandText(): Promise<string> {
    const band = this.page
      .locator("xpath=//*[contains(normalize-space(.),'Total Price') and contains(normalize-space(.),'Engagement Margin')]")
      .last();
    return normalizeText((await band.textContent().catch(() => '')) || '');
  }

  /** Ensure a labor resource row exists on the active Labor Resources grid. */
  async ensureLaborResourceRow(wbs: string, logTag = 'EstimatePage'): Promise<void> {
    await waitForNoLoading(this.page, 15_000);
    // The template copy leaves the labor grid empty ("No records found"); create the WBS row
    // by clicking the add link before touching Grade/Role/FTE.
    if (await this.noRecordsAddLink().isVisible({ timeout: 10_000 }).catch(() => false)) {
      await this.addLaborResourceRow(logTag);
    }

    const wbsCell = await this.resourceGridCell('WBS');
    const wbsText = normalizeText(await this.readLocatorValue(wbsCell));
    if (!wbsText.includes(wbs)) {
      await this.selectResourceGridDropdown('WBS', wbs);
    }
    await expect(this.textLocator(wbs)).toBeVisible({ timeout: 20_000 });
  }

  /**
   * Add a labor resource row on an empty grid by clicking the "click here to add" link.
   * The click target is the inner div[text()] of the [data-grigaddlink] anchor, and it MUST be
   * a real (non-forced) click so the ExtJS `ibe-add-row` delegated handler fires — a forced or
   * synthetic element.click() on the anchor wrapper is a no-op that never creates a row.
   */
  async addLaborResourceRow(logTag = 'EstimatePage'): Promise<void> {
    const addLink = this.noRecordsAddLink();
    await expect(addLink, 'No-records add link should be visible').toBeVisible({ timeout: 20_000 });
    await addLink.click({ timeout: 20_000 }); // real user-like click — do NOT force
    await waitForNoLoading(this.page, 20_000);

    // Positive post-condition: the empty-state overlay must be gone (a row was actually created).
    await expect(this.noRecordsAddLink(), `${logTag}: no-records overlay should disappear after adding a row`).toBeHidden({
      timeout: 20_000,
    });
  }
}
