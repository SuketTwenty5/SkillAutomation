import { expect, type Locator, type Page } from 'playwright/test';
import { test } from 'playwright/test';
import { connectToTwentyFive, waitForNoLoading } from './support/twentyfive-cdp';
import { ProposalSetupPage } from './support/proposal-setup-page';
import { ReadmeEvidenceRecorder } from './support/readme-evidence';
import { clickAttached, clickTab, fastClick, openEstimateFromActiveTab, textLocator } from './support/twentyfive-ui';

test.setTimeout(360_000);

const testData = {
  source:
    'playwrightRecording/20260629_175331/create-capital-project-from-prior-project-and-update-labor-fte-2026-06-29-17-53-31-ist.md',
  projectName: 'regression test capital',
  probableProjectStart: '6/29/2029',
  projectEnd: '6/29/2037',
  leadingSite: 'IT - Italy (Modena)',
  clientSearchText: 'USD',
  clientCustomer: 'Regression Test - Customer USD',
  priorProjectSearch: '(AS) Copy',
  priorProjectResult: '(AS) Copy from Template v2',
  laborPool: 'Supplier Engineer',
  laborFte: '2',
};

test.describe('Recording 20260629_175331', () => {
  test('Create capital project from prior project and update labor FTE', async ({}, testInfo) => {
    testInfo.annotations.push({
      type: 'source',
      description: testData.source,
    });

    const session = await connectToTwentyFive();
    const page = session.page;
    const setup = new ProposalSetupPage(page);
    const runtimeProjectName = uniqueProjectName(testData.projectName);
    let evidencePage = page;
    const evidence = new ReadmeEvidenceRecorder(testInfo, {
      command:
        'scripts/run-playwright-test.sh tests/playwright/create-capital-project-from-prior-project-and-update-labor-fte.spec.ts --headed --retries=0',
      readmePath: testData.source,
      runLabel: 'latest',
    });
    evidence.setRuntimeObjectName(runtimeProjectName);

    try {
      await evidence.step('Open the Proposals page', () => evidencePage, async () => {
        await setup.navigateToProposals();
      });

      await evidence.step('Start a new project', () => evidencePage, async () => {
        await setup.startNewProposal();
        await setup.waitForSetupPage();
      });

      await evidence.step('Enter Project Name', () => evidencePage, async () => {
        await setInputField(page, 'Project Name', runtimeProjectName);
        await expectFieldValue(page, 'Project Name', runtimeProjectName);
      });

      await evidence.step('Enter Probable Project Start', () => evidencePage, async () => {
        await setInputField(page, 'Probable Project Start', testData.probableProjectStart);
        await expectFieldValue(page, 'Probable Project Start', testData.probableProjectStart);
      });

      await evidence.step('Enter Project End', () => evidencePage, async () => {
        await setInputField(page, 'Project End', testData.projectEnd);
        await expectFieldValue(page, 'Project End', testData.projectEnd);
      });

      await evidence.step('Select Leading Site', () => evidencePage, async () => {
        await selectDropdownByField(page, 'Leading Site', 'Italy', testData.leadingSite);
      });

      await evidence.step('Select Client/Customer (Sell-to)', () => evidencePage, async () => {
        await selectDropdownByField(page, 'Client/Customer (Sell-to)', testData.clientSearchText, testData.clientCustomer);
      });

      await evidence.step('Choose Select a Prior Project', () => evidencePage, async () => {
        await choosePriorProjectRadio(page);
      });

      await evidence.step('Search for and select prior project', () => evidencePage, async () => {
        await searchAndSelectPriorProject(page, testData.priorProjectSearch, testData.priorProjectResult);
      });

      await evidence.step('Click Copy', () => evidencePage, async () => {
        await fastClick(page.getByRole('button', { name: 'Copy' }).first(), 'Copy prior project button', 20_000);
        await waitForNoLoading(page, 30_000);
        await expect(textLocator(page, 'Estimates')).toBeVisible({ timeout: 30_000 });
      });

      await evidence.step('Open the Estimates tab', () => evidencePage, async () => {
        await clickTab(page, 'Estimates');
      });

      await evidence.step('Open the estimate', () => evidencePage, async () => {
        const estimatePage = await openEstimateFromActiveTab(page, { label: 'recorded estimate' });
        evidencePage = estimatePage;
        evidence.setCreatedObjectUrl(estimatePage.url());
        console.log(`[recording-20260629_175331] estimate url: ${estimatePage.url()}`);
      });

      await evidence.step('Open the Labor tab', () => evidencePage, async () => {
        await clickVisibleTab(evidencePage, 'Labor');
      });

      await evidence.step('Add or activate a blank labor row', () => evidencePage, async () => {
        await addOrActivateLaborRow(evidencePage);
      });

      await evidence.step('Select Supplier Engineer as the Labor Pool', () => evidencePage, async () => {
        await selectLaborPoolForSelectedResourceRow(evidencePage, testData.laborPool);
        await expect(textLocator(evidencePage, testData.laborPool)).toBeVisible({ timeout: 10_000 });
      });

      await evidence.step('Enter 2 in the FTE editor', () => evidencePage, async () => {
        await setFteForSelectedPlanRow(evidencePage, testData.laborFte);
      });
      evidence.finalize('passed');
    } catch (error) {
      evidence.finalize('failed');
      throw error;
    }
  });
});

async function clickVisibleTab(page: Page, tab: string): Promise<void> {
  const strictTab = page
    .locator(`xpath=//*[@role='tab' and @aria-hidden='false']//*[normalize-space(.)=${xpathLiteral(tab)}]/ancestor::*[@role='tab'][1]`)
    .first();
  const relaxedTab = page
    .locator(
      `xpath=//*[@role='tab' and not(ancestor-or-self::*[@aria-hidden='true'])]//*[normalize-space(.)=${xpathLiteral(tab)}]/ancestor::*[@role='tab'][1]`,
    )
    .first();
  const tabLocator = (await strictTab.count()) > 0 && (await strictTab.isVisible().catch(() => false)) ? strictTab : relaxedTab;
  await fastClick(tabLocator, `${tab} tab`, 15_000);
  await waitForNoLoading(page, 10_000);
}

async function setInputField(page: Page, label: string, value: string): Promise<void> {
  const input = inputForField(page, label);
  await expect(input, `${label} field should be visible`).toBeVisible({ timeout: 15_000 });
  await input.scrollIntoViewIfNeeded();
  await setInputText(input, value);
}

async function expectFieldValue(page: Page, label: string, value: string): Promise<void> {
  const input = inputForField(page, label);
  await expect
    .poll(async () => normalizeText(await readLocatorValue(input)), {
      message: `${label} should contain ${value}`,
      timeout: 8_000,
    })
    .toContain(normalizeText(value));
}

async function selectDropdownByField(page: Page, label: string, searchText: string, expectedText = searchText): Promise<void> {
  const input = inputForField(page, label);
  await expect(input, `${label} field should be visible`).toBeVisible({ timeout: 15_000 });
  await input.scrollIntoViewIfNeeded();
  await input.click({ force: true, noWaitAfter: true });
  await openDropdown(input);
  await setInputText(input, searchText);

  const option = visibleOption(page, expectedText).or(visibleOption(page, searchText)).first();
  await fastClick(option, `${label} option ${expectedText}`, 25_000);
}

async function choosePriorProjectRadio(page: Page): Promise<void> {
  const radio = page.getByRole('radio', { name: /Select a prior project/i }).first();
  await expect(radio, 'Select a prior project radio should be visible').toBeVisible({ timeout: 15_000 });
  await radio.check({ force: true });
  await expect(radio).toBeChecked();
}

async function searchAndSelectPriorProject(page: Page, searchText: string, expectedText: string): Promise<void> {
  const input = page
    .locator(
      "xpath=(//*[contains(@data-componentid,'iBESearchComboBox') and @aria-hidden='false']//input[not(@aria-hidden='true')] | //*[contains(@data-componentid,'iBESearchComboBox') and @aria-hidden='false' and self::input])[1]",
    )
    .first();
  await expect(input, 'Prior project search field should be visible').toBeVisible({ timeout: 10_000 });
  await input.click({ force: true, noWaitAfter: true });
  await setInputText(input, searchText);

  const option = visibleOption(page, expectedText).first();
  await fastClick(option, `prior project option ${expectedText}`, 25_000);
}

async function addOrActivateLaborRow(page: Page): Promise<void> {
  const activePanel = page.locator("xpath=//*[@role='tabpanel' and @aria-hidden='false']").first();
  const extGridAddLink = page.locator("xpath=//*[@data-grigaddlink='true']").first();
  if ((await extGridAddLink.count()) > 0) {
    await expect(extGridAddLink, 'Labor ExtJS grid add link should exist').toBeAttached({ timeout: 15_000 });
    const addLinkInfo = await extGridAddLink.evaluate((element) => ({
      dataGridId: element.getAttribute('data-grid-id'),
      text: element.textContent?.replace(/\s+/g, ' ').trim(),
    }));
    console.log(`[recording-20260629_175331] Labor ExtJS add link: ${JSON.stringify(addLinkInfo)}`);
    await extGridAddLink.evaluate((element) => {
      if (element instanceof HTMLElement) element.click();
      else element.dispatchEvent(new MouseEvent('click', { bubbles: true, cancelable: true, view: window }));
    });
    await waitForNoLoading(page, 15_000);
    await expect(newBlankResourceRow(activePanel), 'new blank labor resource row should appear after ExtJS add link').toBeVisible({
      timeout: 15_000,
    });
    return;
  }

  const newRow = newBlankResourceRow(activePanel);
  if ((await newRow.count()) > 0 && (await newRow.isVisible().catch(() => false))) return;

  const firstResourceRowAdd = activePanel
    .locator("xpath=.//*[@role='row' and .//*[contains(normalize-space(.), 'REG_CONSULTANT1')]]//*[@role='button' and contains(normalize-space(.), '')]")
    .first();
  const plusButton = activePanel
    .locator("xpath=.//*[@role='button' and not(ancestor-or-self::*[@aria-hidden='true']) and contains(normalize-space(.), '')]")
    .first();
  const addIcon = activePanel
    .locator(
      "xpath=.//*[contains(@class,'x-action-col-icon') and (contains(@class,'x-action-col-0') or contains(@class,'add') or contains(@data-qtip,'Add'))]",
    )
    .first();
  const addButton = activePanel
    .locator("xpath=.//*[@role='button' and @aria-hidden='false' and (@data-qtip='Add' or .//*[normalize-space()='Add'])]")
    .first();

  for (const [locator, label] of [
    [firstResourceRowAdd, 'first resource row add icon'],
    [plusButton, 'labor grid plus row button'],
    [addIcon, 'labor grid add-row icon'],
    [addButton, 'labor grid Add button'],
  ] as const) {
    if ((await locator.count()) > 0 && (await locator.isVisible().catch(() => false))) {
      await clickAttached(locator, label, 15_000);
      await expect(newBlankResourceRow(activePanel), 'new blank labor resource row should appear after add').toBeVisible({ timeout: 15_000 });
      return;
    }
  }

  await expect(newBlankResourceRow(activePanel), 'blank labor resource row or add control should be available').toBeVisible({ timeout: 5_000 });
}

async function selectLaborPoolForSelectedResourceRow(page: Page, value: string): Promise<void> {
  const activePanel = page.locator("xpath=//*[@role='tabpanel' and @aria-hidden='false']").first();
  const blankResourceRow = newBlankResourceRow(activePanel);
  await expect(blankResourceRow, 'new blank labor resource row should be visible').toBeVisible({ timeout: 15_000 });

  const poolCellByColumnId = blankResourceRow
    .locator(
      "xpath=.//*[@role='gridcell' and not(ancestor-or-self::*[@aria-hidden='true']) and (contains(translate(@data-columnid,'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'laborpool') or .//*[contains(translate(@data-columnid,'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'laborpool')])]",
    )
    .first();
  const poolCellByClass = blankResourceRow
    .locator("xpath=.//*[contains(@class,'resourceGridLaborPoolColumn') or contains(@class,'laborPool')]")
    .first();
  const poolCellFallback = blankResourceRow
    .locator("xpath=(.//*[@role='gridcell' and not(ancestor-or-self::*[@aria-hidden='true'])])[3]")
    .first();
  const poolCell =
    (await poolCellByColumnId.count()) > 0
      ? poolCellByColumnId
      : (await poolCellByClass.count()) > 0
        ? poolCellByClass
        : poolCellFallback;

  const poolColumnId = await nearestColumnId(poolCell);
  console.log(`[recording-20260629_175331] labor pool cell data-columnid: ${poolColumnId || '(none)'}`);
  if (poolColumnId) {
    expect(normalizeText(poolColumnId).toLowerCase(), 'labor pool cell should be in a laborpool data-columnid column').toContain('laborpool');
  }

  await clickAttached(poolCell, 'new resource labor pool cell', 15_000);
  const editorInput = page
    .locator("xpath=//input[not(@aria-hidden='true') and not(@type='hidden') and not(ancestor-or-self::*[@aria-hidden='true'])]")
    .last();
  await expect(editorInput, 'labor pool editor should be visible').toBeVisible({ timeout: 10_000 });
  await editorInput.click({ force: true, noWaitAfter: true });
  await openDropdown(editorInput);
  await setInputText(editorInput, value);

  const option = visibleOption(page, value).first();
  if ((await option.count()) > 0 && (await option.isVisible().catch(() => false))) {
    await fastClick(option, `labor pool option ${value}`, 20_000);
  } else {
    await editorInput.press('Enter');
  }
}

function newBlankResourceRow(activePanel: Locator): Locator {
  return activePanel
    .locator(
      "xpath=.//*[@role='row' and .//*[@role='gridcell' and normalize-space()='1'] and .//*[@role='gridcell' and .//*[@role='button']]]",
    )
    .first();
}

async function setFteForSelectedPlanRow(page: Page, value: string): Promise<void> {
  const activePanel = page.locator("xpath=//*[@role='tabpanel' and @aria-hidden='false']").first();
  const newPlanRow = activePanel
    .locator(
      `xpath=.//*[@role='row' and .//*[contains(normalize-space(.), ${xpathLiteral(testData.probableProjectStart)})] and .//*[contains(normalize-space(.), ${xpathLiteral(testData.projectEnd)})] and .//*[contains(normalize-space(.), '0.000 FTE')]]`,
    )
    .first();
  const recordedFteCell = activePanel.getByRole('gridcell', { name: /FTE/ }).locator("xpath=.//*[not(@aria-hidden='true')]").last();
  const fteCell =
    (await newPlanRow.count()) > 0 && (await newPlanRow.isVisible().catch(() => false))
      ? newPlanRow.locator("xpath=.//*[@role='gridcell' and contains(normalize-space(.), 'FTE')]").first()
      : recordedFteCell;

  console.log('[recording-20260629_175331] setting FTE in labor planning detail row');
  await clickAttached(fteCell, 'labor detail FTE cell', 15_000);

  const editorInput = page
    .locator(
      "xpath=//input[not(@aria-hidden='true') and not(@type='hidden') and not(@readonly) and not(contains(@id,'-unit')) and not(ancestor-or-self::*[@aria-hidden='true'])]",
    )
    .last();
  await expect(editorInput, 'editable FTE value editor should be visible').toBeVisible({ timeout: 10_000 });
  await setInputText(editorInput, value);
  await editorInput.press('Enter');
}

function inputForField(page: Page, label: string): Locator {
  if (label === 'Project Name') {
    return page.getByRole('textbox', { name: /Project Name/i }).first();
  }
  if (label === 'Probable Project Start') {
    return page.getByRole('combobox', { name: /Probable Project Start/i }).first();
  }
  if (label === 'Project End') {
    return page.getByRole('combobox', { name: /Project End/i }).first();
  }
  if (label === 'Leading Site') {
    return page.getByRole('combobox', { name: /Leading Site/i }).first();
  }
  if (label === 'Client/Customer (Sell-to)') {
    return page
      .locator("xpath=//input[contains(@placeholder,'Enter company') and contains(@placeholder,'name to search') and not(@aria-hidden='true')]")
      .first();
  }

  return page
    .locator(
      `xpath=(//*[self::label or self::span or self::div][contains(normalize-space(.), ${xpathLiteral(label)})]/ancestor::div[contains(@class,'x-field ')][1]//*[self::input or self::textarea][not(@aria-hidden='true')])[1]`,
    )
    .first();
}

async function openDropdown(input: Locator): Promise<void> {
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

async function setInputText(input: Locator, value: string): Promise<void> {
  await input.fill('');
  await input.fill(value);
  await input.evaluate((element) => {
    element.dispatchEvent(new Event('input', { bubbles: true }));
    element.dispatchEvent(new Event('change', { bubbles: true }));
  });
}

async function readLocatorValue(locator: Locator): Promise<string> {
  return locator.evaluate((element) => {
    if (element instanceof HTMLInputElement || element instanceof HTMLTextAreaElement) {
      return element.value;
    }
    return element.textContent ?? '';
  });
}

async function nearestColumnId(locator: Locator): Promise<string> {
  return locator.evaluate((element) => {
    const direct = element.getAttribute('data-columnid');
    if (direct) return direct;

    const descendant = element.querySelector('[data-columnid]');
    if (descendant) return descendant.getAttribute('data-columnid') ?? '';

    const ancestor = element.closest('[data-columnid]');
    return ancestor?.getAttribute('data-columnid') ?? '';
  });
}

function visibleOption(page: Page, text: string): Locator {
  return page.locator(
    `xpath=//*[@role='option' and not(ancestor-or-self::*[@aria-hidden='true']) and contains(normalize-space(.), ${xpathLiteral(text)})]`,
  );
}

function uniqueProjectName(baseName: string): string {
  return `${baseName} ${new Date().toISOString().slice(0, 16).replace('T', ' ')}`;
}

function normalizeText(value: string): string {
  return value.replace(/\s+/g, ' ').trim();
}

function xpathLiteral(value: string): string {
  if (!value.includes("'")) return `'${value}'`;
  if (!value.includes('"')) return `"${value}"`;
  return `concat(${value
    .split("'")
    .map((part) => `'${part}'`)
    .join(', "\'", ')})`;
}
