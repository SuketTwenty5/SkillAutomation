import { expect, type Locator, type Page, type TestInfo } from 'playwright/test';
import { waitForNoLoading } from '../twentyfive-cdp';
import { fastClick } from '../twentyfive-ui';
import { ciContainsText, normalizeText, xpathLiteral } from './xpath';

/**
 * Generic Twenty5/ExtJS controls shared by every page: the active tab panel,
 * label-scoped field inputs, dropdown selection, the Save menu, toasts and dialogs.
 *
 * All locators are ExtJS-aware and anchored on visible labels/roles (never on
 * generated ids). Grid-specific controls live in EstimatePage (which extends this).
 */
export class BasePage {
  constructor(protected readonly page: Page) {}

  /** The single visible tab panel — scope every in-page lookup through this. */
  activeTabPanel(): Locator {
    return this.page.locator("xpath=//*[@role='tabpanel' and @aria-hidden='false']").first();
  }

  /** The visible input for a field, resolved by its visible label (with placeholder fallbacks). */
  fieldInput(label: string): Locator {
    const page = this.page;
    if (label === 'Title') {
      return page
        .locator("xpath=//input[@placeholder='Enter a brief description of the bid (something you can remember to find it later by)']")
        .first();
    }

    const placeholderFallback: Record<string, string> = {
      'Line of Business': 'Select Company',
      Region: 'Select Department',
      'Planned Start': 'Select Start',
      'Planned End': 'Select End',
    };
    const placeholder = placeholderFallback[label];
    if (placeholder) {
      return this.labeledInput(label)
        .or(page.locator(`xpath=//input[@placeholder=${xpathLiteral(placeholder)} and not(@aria-hidden='true')]`).first())
        .first();
    }

    return this.labeledInput(label).first();
  }

  /** An input/textarea scoped to the ExtJS field container that carries the given visible label. */
  labeledInput(label: string): Locator {
    return this.page.locator(
      `xpath=(//*[self::label or self::span or self::div][${ciContainsText(label)}]/ancestor::div[contains(@class,'x-field ')][1]//*[self::input or self::textarea][not(@aria-hidden='true')])[1]`,
    );
  }

  /** Type into a label-scoped dropdown and click the matching option. */
  async selectDropdownByField(label: string, searchText: string, expectedText = searchText): Promise<void> {
    const input = this.fieldInput(label);
    await expect(input, `${label} field should be visible`).toBeVisible({ timeout: 20_000 });
    await input.scrollIntoViewIfNeeded();
    await input.click({ force: true, noWaitAfter: true });
    await this.openDropdown(input);
    await this.setInputText(input, searchText);

    const option = this.visibleOption(expectedText).or(this.visibleOption(searchText)).first();
    await fastClick(option, `${label} option ${expectedText}`, 25_000);
    await this.expectFieldValue(label, expectedText);
  }

  /** Set a label-scoped field value via the ExtJS component setter. */
  async setFieldValue(label: string, value: string): Promise<void> {
    const input = this.fieldInput(label);
    await expect(input, `${label} field should be visible`).toBeVisible({ timeout: 20_000 });
    await input.scrollIntoViewIfNeeded();
    await this.setExtInputText(input, value);
  }

  async expectFieldValue(label: string, expectedText: string): Promise<void> {
    await this.waitForFieldValue(label, expectedText, 10_000);
  }

  async waitForFieldValue(label: string, expectedText: string, timeoutMs: number): Promise<void> {
    const input = this.fieldInput(label);
    const candidates = await this.resolveExpectedFieldText(expectedText);
    await expect
      .poll(
        async () => {
          const actual = normalizeText(await this.readLocatorValue(input));
          return candidates.some((candidate) => actual.includes(candidate));
        },
        { message: `${label} should contain one of: ${candidates.join(' | ')}`, timeout: timeoutMs },
      )
      .toBe(true);
  }

  /**
   * Resolve a field-expectation value to the concrete string(s) to match. Supports the `$todayDate`
   * sentinel (computed in the browser's timezone, in the app's M/D/YYYY form) so specs assert
   * "auto-populated with today" fields date-stably — never a hardcoded recording-day literal or a
   * spec-local `new Date()`. Any other value is matched literally.
   */
  private async resolveExpectedFieldText(expectedText: string): Promise<string[]> {
    if (expectedText === '$todayDate') {
      return this.page.evaluate(() => {
        const d = new Date();
        const m = d.getMonth() + 1;
        const day = d.getDate();
        const y = d.getFullYear();
        const pad = (n: number) => String(n).padStart(2, '0');
        return [`${m}/${day}/${y}`, `${pad(m)}/${pad(day)}/${y}`];
      });
    }
    return [normalizeText(expectedText)];
  }

  /**
   * Assert a USER/master-data-derived field is POPULATED, and record a variance (not a failure) when
   * its value differs from the source-recorded value. Use for fields whose value depends on the
   * logged-in user / environment (e.g. Pursuit Manager, Location) so a fresh run on a different
   * tenant does not hard-fail on a recording-time value.
   */
  async expectFieldValueOrVariance(
    label: string,
    sourceExpected: string,
    testInfo: TestInfo,
    opts: { fieldKind?: string } = {},
  ): Promise<void> {
    const input = this.fieldInput(label);
    await expect(input, `${label} field should be visible`).toBeVisible({ timeout: 20_000 });
    const actual = normalizeText(await this.readLocatorValue(input));
    expect(actual, `${label} should be populated (auto-fill mechanism)`).not.toBe('');
    if (!actual.toLowerCase().includes(normalizeText(sourceExpected).toLowerCase())) {
      const note = `${label}: source expected "${sourceExpected}", actual "${actual}" (${opts.fieldKind ?? 'user/master-data'} derived).`;
      testInfo.annotations.push({ type: 'variance', description: note });
      console.warn(`[variance] ${note}`);
    }
  }

  /** Open the ExtJS trigger arrow for a combo/date field. */
  async openDropdown(input: Locator): Promise<void> {
    const triggerCandidates = [
      input.locator("xpath=./ancestor::*[@data-ref='triggerWrap'][1]//*[contains(@class,'x-form-arrow-trigger')]").first(),
      input.locator("xpath=./parent::div/following-sibling::div[contains(@class,'x-form-arrow-trigger')]").first(),
      input
        .locator("xpath=./ancestor::*[@data-ref='triggerWrap'][1]//*[contains(@class,'x-form-trigger') and not(contains(@class,'x-form-clear-trigger'))]")
        .first(),
    ];

    for (const trigger of triggerCandidates) {
      if ((await trigger.count()) > 0 && (await trigger.isVisible().catch(() => false))) {
        await trigger.click({ force: true, noWaitAfter: true });
        await input.page().waitForTimeout(300);
        return;
      }
    }
  }

  async setInputText(input: Locator, value: string): Promise<void> {
    await input.fill('');
    await input.fill(value);
    await input.evaluate((element) => {
      element.dispatchEvent(new Event('input', { bubbles: true }));
      element.dispatchEvent(new Event('change', { bubbles: true }));
    });
  }

  /** Commit a value through the ExtJS field component so validation/auto-calc fires. */
  async setExtInputText(locator: Locator, value: string): Promise<void> {
    await locator.evaluate((element, text) => {
      if (!(element instanceof HTMLInputElement || element instanceof HTMLTextAreaElement)) return;

      const win = element.ownerDocument.defaultView as
        | (Window & {
            Ext?: {
              ComponentQuery?: {
                query?: (selector: string) => Array<{
                  inputEl?: { dom?: Element };
                  setValue?: (value: string) => void;
                  setRawValue?: (value: string) => void;
                  checkChange?: () => void;
                  validate?: () => void;
                  fireEvent?: (name: string, ...args: unknown[]) => void;
                }>;
              };
            };
          })
        | null;
      const field = win?.Ext?.ComponentQuery?.query?.('field')?.find((component) => component.inputEl?.dom === element);

      if (field?.setValue) {
        field.setValue(text);
      } else if (field?.setRawValue) {
        field.setRawValue(text);
      } else {
        element.value = text;
      }

      element.value = text;
      element.dispatchEvent(new Event('input', { bubbles: true }));
      element.dispatchEvent(new Event('change', { bubbles: true }));
      element.dispatchEvent(new KeyboardEvent('keyup', { bubbles: true }));
      field?.checkChange?.();
      field?.validate?.();
      field?.fireEvent?.('change', field, text);
      element.blur();
    }, value);
  }

  async readLocatorValue(locator: Locator): Promise<string> {
    return locator.evaluate((element) => {
      if (element instanceof HTMLInputElement || element instanceof HTMLTextAreaElement || element instanceof HTMLSelectElement) {
        return element.value ?? '';
      }
      return element.textContent ?? '';
    });
  }

  /** A visible ExtJS dropdown option containing the given text. */
  visibleOption(text: string): Locator {
    return this.page.locator(
      `xpath=//*[@role='option' and not(ancestor-or-self::*[@aria-hidden='true']) and contains(normalize-space(.), ${xpathLiteral(text)})]`,
    );
  }

  /** The last visible, editable inline editor input (excludes hidden/aria-hidden). */
  visibleEditorInput(): Locator {
    return this.page
      .locator("xpath=//input[not(@aria-hidden='true') and not(@type='hidden') and not(ancestor-or-self::*[@aria-hidden='true'])]")
      .last();
  }

  /** The Save split-button floppy icon — the hover target that reveals the save menu. */
  private saveIconTarget(): Locator {
    return this.page.locator('xpath=//*[@data-qtip="Save" and @aria-hidden="false"]//*[@data-ref="btnIconEl"]').first();
  }

  /** A Save menu option link by its visible text (Check & Save / Save without Check / Check without Save). */
  private saveMenuOption(option: string): Locator {
    return this.page
      .locator(
        `xpath=//*[@role="menu" and @aria-hidden="false"]//*[@class="x-menu-item-link" and @aria-hidden="false"]//*[text()=${xpathLiteral(option)}]`,
      )
      .first();
  }

  /**
   * Reveal the Save menu. It is HOVER-to-reveal, not click: hover the Save floppy icon and wait for
   * the options to appear, with a click-outside + re-hover retry if the menu doesn't show.
   */
  async openSaveMenu(): Promise<void> {
    const icon = this.saveIconTarget();
    await expect(icon, 'Save icon should be visible').toBeVisible({ timeout: 15_000 });
    const probe = this.saveMenuOption('Save without Check');
    for (let attempt = 1; attempt <= 3; attempt += 1) {
      await icon.hover({ timeout: 10_000 }).catch(() => undefined);
      if (await probe.isVisible({ timeout: 5_000 }).catch(() => false)) return;
      await this.page.mouse.click(5, 5).catch(() => undefined); // click-outside, then re-hover
      await this.page.waitForTimeout(500);
    }
  }

  async expectSaveMenuItems(labels: string[] = ['Check & Save', 'Save without Check', 'Check without Save']): Promise<void> {
    await this.openSaveMenu();
    for (const label of labels) {
      await expect(this.saveMenuOption(label), `${label} menu item should be accessible`).toBeVisible({ timeout: 10_000 });
    }
  }

  /**
   * Save via the hover Save menu, then wait for the action to complete. Default option is
   * 'Save without Check'. Hover reveals the menu; click the option by its visible text.
   */
  async saveViaMenu(option = 'Save without Check'): Promise<void> {
    await this.openSaveMenu();
    const item = this.saveMenuOption(option);
    await expect(item, `Save menu option "${option}" should be visible`).toBeVisible({ timeout: 10_000 });
    await item.click({ timeout: 10_000 });
    await this.waitForActionComplete();
  }

  /**
   * Wait for an in-app save/calc action to finish before proceeding. Positive signal: the toast
   * (captured even if transient by the toast observer); then ExtJS must settle — Ajax idle, no
   * progressbar mask, no modal window. Prevents racing ahead while the save API is still in flight.
   */
  async waitForActionComplete(opts: { toast?: string; timeoutMs?: number } = {}): Promise<void> {
    await this.expectToast(opts.toast ?? 'Data saved successfully', { optional: true, timeoutMs: 20_000 });
    await this.waitForExtSettled(opts.timeoutMs ?? 60_000);
  }

  async checkAndSave(): Promise<void> {
    await this.openSaveMenu();
    const checkAndSave = this.saveMenuOption('Check & Save');
    await expect(checkAndSave, 'Check & Save menu item should be visible').toBeVisible({ timeout: 15_000 });
    await checkAndSave.click({ timeout: 15_000 });
    await this.waitForActionComplete({ timeoutMs: 90_000 });
    await expect(this.errorDialog(), 'Check & Save should not show an Error dialog').toBeHidden({ timeout: 5_000 });
  }

  /** The "Update Cost & Prices" link in the KPI header. @label Update Cost & Prices */
  updateCostsLink(): Locator {
    return this.page.locator('xpath=//*[@titlelink="updateCostsWithFormula"]').first();
  }

  /**
   * Run a cost/price roll-up and wait for it to finish, per the runbook "Cost/Price Update And
   * Status-Check Rule". Refresh first (the KPI header can be mid-"Updating (n)"), click the Update
   * Cost & Prices link, confirm the running status, then poll the More-menu status item until it
   * reports "Finished", firing the ExtJS Reload handler while it is still "Currently running".
   * This replaces the timing-fragile Save-menu "Check & Save" path for cost updates.
   */
  async updateCostsAndWaitForFinished(opts: { logTag?: string; totalTimeoutMs?: number } = {}): Promise<void> {
    const logTag = opts.logTag ?? 'EstimatePage';
    const totalTimeout = opts.totalTimeoutMs ?? 15 * 60 * 1000;

    // 0) Self-guard the ordering: if any grid has unsaved edits, PERSIST them before the refresh.
    // A reload over an unsaved FTE/labor edit discards it and the roll-up computes on empty data
    // (the USD-0 bug). This makes the save-before-roll-up rule enforced, not just convention.
    const hasUnsavedEdits = await this.page
      .evaluate(() => {
        const Ext = (window as unknown as { Ext?: any }).Ext;
        if (!Ext) return false;
        return (Ext.ComponentQuery.query('grid') || []).some((g: any) => ((g.getStore?.()?.getModifiedRecords?.() as unknown[]) || []).length > 0);
      })
      .catch(() => false);
    if (hasUnsavedEdits) {
      console.warn(`[${logTag}] Unsaved grid edits detected before roll-up refresh — persisting via 'Save without Check' first.`);
      await this.saveViaMenu('Save without Check').catch((error) => {
        console.warn(`[${logTag}] pre-roll-up save failed: ${error instanceof Error ? error.message : String(error)}`);
      });
    }

    // 1) Refresh, then wait for the app shell + KPI header before interacting.
    await this.page.reload({ waitUntil: 'domcontentloaded', timeout: 60_000 }).catch(() => undefined);
    await waitForNoLoading(this.page, 90_000);
    const link = this.updateCostsLink();
    await expect(link, 'Update Cost & Prices link should be visible after refresh').toBeVisible({ timeout: 90_000 });

    // 2) Trigger the calculation.
    await link.click({ timeout: 15_000 });

    // 3) Confirm the running status appears (a few seconds after the click).
    await this.page
      .waitForFunction(() => /currently running|running/i.test(document.body?.innerText || ''), undefined, {
        timeout: 20_000,
        polling: 500,
      })
      .catch(() => console.warn(`[${logTag}] running status not observed within 20s; continuing to status poll.`));

    // 4) Poll the More-menu status item until Finished, reloading while it is still running.
    const deadline = Date.now() + totalTimeout;
    while (Date.now() < deadline) {
      const status = await this.readCostUpdateStatus().catch(() => '');
      console.log(`[${logTag}] cost update status: ${status || '(unread)'}`);
      if (/finished/i.test(status)) return;
      if (/currently running|running/i.test(status)) {
        await this.reloadViaExtHandler();
        await this.waitForExtSettled(60_000);
      } else {
        await this.page.waitForTimeout(3_000); // status blank/unknown — let the backend catch up
      }
    }
    throw new Error(`${logTag}: cost/price update did not reach "Finished" within ${Math.round(totalTimeout / 1000)}s`);
  }

  /** Read the cost-update status text from the More menu (opens it, reads the item, closes it). */
  private async readCostUpdateStatus(): Promise<string> {
    return this.page.evaluate(async () => {
      const Ext = (window as unknown as { Ext?: any }).Ext;
      if (!Ext) return '';
      const sleep = (ms: number) => new Promise((resolve) => setTimeout(resolve, ms));
      const more = Ext.ComponentQuery.query('button[tooltip=More]')[0];
      if (!more) return '';
      more.click();
      await sleep(800);
      const item = Ext.ComponentQuery.query('[itemId="mp_more_item_update_costs_btn"]')[0];
      const text: string = item?.getEl?.()?.dom?.innerText || '';
      more.click(); // close the menu
      await sleep(300);
      return text.replace(/\s+/g, ' ').trim();
    });
  }

  /** Fire the ExtJS Reload component handler (refreshes grid data without a full page reload). */
  private async reloadViaExtHandler(): Promise<void> {
    await this.page.evaluate(() => {
      const Ext = (window as unknown as { Ext?: any }).Ext;
      const reload = Ext?.ComponentQuery.query('*').find((c: any) => c.tooltip === 'Reload' || c.qtip === 'Reload');
      reload?.fireHandler?.();
    });
  }

  /** Wait until ExtJS Ajax is idle, no modal window is visible, and no progressbar mask is visible. */
  private async waitForExtSettled(timeoutMs: number): Promise<void> {
    await this.page
      .waitForFunction(
        () => {
          const Ext = (window as unknown as { Ext?: any }).Ext;
          if (!Ext) return true;
          if (Ext.Ajax?.isLoading?.()) return false;
          const modalVisible = (Ext.ComponentQuery.query('window[modal=true]') || []).some((w: any) => w.isVisible?.());
          if (modalVisible) return false;
          const barVisible = Array.from(document.querySelectorAll('.x-mask[role=progressbar]')).some(
            (el) => (el as HTMLElement).offsetParent !== null,
          );
          return !barVisible;
        },
        undefined,
        { timeout: timeoutMs, polling: 500 },
      )
      .catch(() => undefined);
    await this.page.waitForTimeout(500);
  }

  errorDialog(): Locator {
    return this.page.locator("xpath=//*[@role='dialog' and @aria-hidden='false']//*[normalize-space()='Error']/ancestor::*[@role='dialog'][1]").first();
  }

  /** @label Copy */
  copyTemplateButton(): Locator {
    return this.page.getByRole('button', { name: /^Copy$/ }).first();
  }

  /** The Re-Use Strategy fieldset/group. */
  reUseStrategyGroup(): Locator {
    return this.page
      .locator("xpath=//*[contains(normalize-space(.), 'Re-Use Strategy')]/ancestor::*[contains(@class,'x-fieldset') or @role='group'][1]")
      .first();
  }

  /** The best-practice-template combobox inside the Re-Use Strategy group. */
  templateInput(): Locator {
    return this.reUseStrategyGroup()
      .locator("xpath=.//input[not(@aria-hidden='true') and (@role='combobox' or contains(@class,'x-form-field'))]")
      .first();
  }

  async expectTemplateValue(expectedText: string): Promise<void> {
    const templateInput = this.templateInput();
    await expect(templateInput, 'best practice template combobox should be visible').toBeVisible({ timeout: 15_000 });
    await expect
      .poll(async () => normalizeText(await this.readLocatorValue(templateInput)), {
        message: `Best practice template should equal ${expectedText}`,
        timeout: 10_000,
      })
      .toContain(normalizeText(expectedText));
  }

  /**
   * Observe-don't-poll toast capture. ExtJS toasts are transient — they appear and
   * auto-dismiss in ~2-4s, usually before a post-action assertion runs, so polling
   * live DOM after the fact waited the full timeout for something already gone.
   * This installs a markup-agnostic MutationObserver that records every short text
   * node inserted anywhere under <html> into an in-page ring buffer, so expectToast
   * can match a toast that has already vanished. Idempotent; re-installs on reload
   * via addInitScript and runs once now for the current document.
   */
  async startToastObserver(): Promise<void> {
    const install = () => {
      const w = window as unknown as {
        __ipeToasts?: Array<{ text: string }>;
        __ipeToastObserver?: MutationObserver;
      };
      if (w.__ipeToastObserver || !document.documentElement) return;
      w.__ipeToasts = w.__ipeToasts ?? [];
      const norm = (s: string) => s.replace(/\s+/g, ' ').trim();
      const record = (el: Element) => {
        const raw = (el as HTMLElement).innerText ?? el.textContent ?? '';
        const text = norm(raw);
        if (!text || text.length > 400) return;
        const buf = w.__ipeToasts!;
        if (buf.length && buf[buf.length - 1].text === text) return; // dedupe consecutive repeats
        buf.push({ text });
        if (buf.length > 300) buf.splice(0, buf.length - 300);
      };
      const obs = new MutationObserver((mutations) => {
        for (const m of mutations) {
          m.addedNodes.forEach((node) => {
            if (node.nodeType !== 1) return;
            const el = node as Element;
            const hint = `${el.getAttribute?.('class') ?? ''} ${el.getAttribute?.('role') ?? ''}`;
            // Known toast-ish containers, plus a fallback for small unknown-markup inserts.
            if (/toast|message|notif|tip|alert/i.test(hint) || el.querySelectorAll('*').length < 12) {
              record(el);
            }
          });
        }
      });
      obs.observe(document.documentElement, { childList: true, subtree: true });
      w.__ipeToastObserver = obs;
    };
    await this.page.addInitScript(install);
    await this.page.evaluate(install).catch(() => {});
  }

  async expectToast(text: string, options: { optional?: boolean; timeoutMs?: number } = {}): Promise<void> {
    const target = normalizeText(text).toLowerCase();
    // Observed-path only: clamp to <=10s. The observer captures the toast the instant it fires, so a
    // present toast matches in ~100ms; a larger timeout would only be a dead wait for an absent one.
    const timeout = Math.min(options.timeoutMs ?? 8_000, 10_000);

    // Observed path: the captured buffer holds toasts even after they auto-dismiss,
    // so a toast that fired during the triggering action is matched on the first poll.
    const seen = await this.page
      .waitForFunction(
        (needle) => {
          const w = window as unknown as { __ipeToasts?: Array<{ text: string }> };
          return (w.__ipeToasts ?? []).some((e) => e.text.toLowerCase().includes(needle));
        },
        target,
        { timeout, polling: 100 },
      )
      .then(() => true, () => false);
    if (seen) return;

    // Fallback for pages where the observer was never installed: one quick live check.
    const liveVisible = await this.page
      .locator(`xpath=//*[not(ancestor-or-self::*[@aria-hidden='true']) and contains(normalize-space(.), ${xpathLiteral(text)})]`)
      .first()
      .isVisible()
      .catch(() => false);
    if (liveVisible) return;

    if (!options.optional) {
      throw new Error(`Expected toast text was not visible: ${text}`);
    }
  }
}
