import { expect, type Locator, type Page } from 'playwright/test';
import { test } from 'playwright/test';
import { connectToTwentyFive, waitForNoLoading } from './support/twentyfive-cdp';
import { ProposalSetupPage } from './support/proposal-setup-page';
import { clickAttached, clickTab, fastClick, openEstimateFromActiveTab, textLocator } from './support/twentyfive-ui';

test.setTimeout(420_000);

const testData = {
  source:
    'playwrightRecording/20260629_100320/create-capital-project-from-prior-project-and-update-labor-fte-and-cost-2026-06-29-10-03-20-ist.md',
  projectName: 'Test capital Copy',
  probableProjectStart: '6/29/2031',
  projectEnd: '6/28/2037',
  leadingSite: 'SE- Sweden (Lund)',
  clientSearchText: 'usd',
  clientCustomer: 'RTC1',
  priorProjectSearch: '(AS) Copy from template',
  priorProjectResult: '(AS) Copy from Template v5',
  laborPool: 'Project Manager',
  laborFte: '2',
  laborRate: '$ 125.00/hr',
  updatedCost: 'USD 3.28M',
};

test.describe('Recording 20260629_100320', () => {
  test('Create capital project from prior project and update labor FTE and cost', async ({}, testInfo) => {
    testInfo.annotations.push({
      type: 'source',
      description: testData.source,
    });

    const session = await connectToTwentyFive();
    const page = session.page;
    const setup = new ProposalSetupPage(page);
    const runtimeProjectName = uniqueProjectName(testData.projectName);

    await test.step('Open Proposals and start a new capital project', async () => {
      await setup.navigateToProposals();
      await setup.startNewProposal();
      await setup.waitForSetupPage();
    });

    await test.step('Fill recorded capital project setup values', async () => {
      await setInputField(page, 'Probable Project Start', testData.probableProjectStart);
      await setInputField(page, 'Project End', testData.projectEnd);
      await setInputField(page, 'Project Name', runtimeProjectName);
      await selectDropdownByField(page, 'Leading Site', 'Sweden', testData.leadingSite);
      await selectDropdownByField(page, 'Client/Customer (Sell-to)', testData.clientSearchText, testData.clientCustomer);

      await expectFieldValue(page, 'Probable Project Start', testData.probableProjectStart);
      await expectFieldValue(page, 'Project End', testData.projectEnd);
      await expectFieldValue(page, 'Project Name', runtimeProjectName);
    });

    await test.step('Select prior project and copy project data', async () => {
      await selectPriorProject(page, testData.priorProjectSearch, testData.priorProjectResult);
      await fastClick(page.getByRole('button', { name: 'Copy' }).first(), 'Copy prior project button', 20_000);
      await waitForNoLoading(page, 30_000);
      await expect(textLocator(page, 'Estimates')).toBeVisible({ timeout: 30_000 });
    });

    await test.step('Open estimate and update Labor cost inputs', async () => {
      await clickTab(page, 'Estimates');
      const estimatePage = await openEstimate(page);
      console.log(`[recording-20260629_100320] estimate url: ${estimatePage.url()}`);

      await clickTab(estimatePage, 'Labor');
      await addLaborRow(estimatePage);
      await selectLaborPoolForSelectedResourceRow(estimatePage, testData.laborPool);
      await setFteForSelectedPlanRow(estimatePage, testData.laborFte);
      await expect(textLocator(estimatePage, testData.laborRate)).toBeVisible({ timeout: 15_000 });
      await saveEstimate(estimatePage);
      await deleteFirstCopiedLaborRow(estimatePage);
      await saveEstimate(estimatePage);
      await updateCost(estimatePage);
      await expect(textLocator(estimatePage, testData.updatedCost)).toBeVisible({ timeout: 30_000 });
      await testInfo.attach('updated-cost-page', {
        body: await estimatePage.screenshot({ fullPage: true }),
        contentType: 'image/png',
      });

      if (estimatePage !== page) {
        await estimatePage.close();
      }
    });
  });
});

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

async function selectPriorProject(page: Page, searchText: string, expectedText: string): Promise<void> {
  const radio = page.getByRole('radio', { name: /Select a prior project/i }).first();
  await expect(radio, 'Select a prior project radio should be visible').toBeVisible({ timeout: 15_000 });
  await radio.check({ force: true });
  await expect(radio).toBeChecked();

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

async function openEstimate(page: Page): Promise<Page> {
  return openEstimateFromActiveTab(page);
}

async function addLaborRow(page: Page): Promise<void> {
  const activePanel = page.locator("xpath=//*[@role='tabpanel' and @aria-hidden='false']").first();
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

  if ((await firstResourceRowAdd.count()) > 0 && (await firstResourceRowAdd.isVisible().catch(() => false))) {
    await clickAttached(firstResourceRowAdd, 'first resource row add icon', 15_000);
    await expect(newBlankResourceRow(activePanel), 'new blank labor resource row should appear after row add').toBeVisible({ timeout: 15_000 });
    return;
  }

  if ((await plusButton.count()) > 0 && (await plusButton.isVisible().catch(() => false))) {
    await clickAttached(plusButton, 'labor grid plus row button', 15_000);
    await expect(newBlankResourceRow(activePanel), 'new blank labor resource row should appear after plus add').toBeVisible({ timeout: 15_000 });
    return;
  }

  if ((await addIcon.count()) > 0 && (await addIcon.isVisible().catch(() => false))) {
    await clickAttached(addIcon, 'labor grid add-row icon', 15_000);
    await expect(newBlankResourceRow(activePanel), 'new blank labor resource row should appear after add icon').toBeVisible({ timeout: 15_000 });
    return;
  }

  await fastClick(addButton, 'labor grid Add button', 15_000);
  await expect(newBlankResourceRow(activePanel), 'new blank labor resource row should appear after Add button').toBeVisible({ timeout: 15_000 });
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
  const poolCellFallback = blankResourceRow
    .locator("xpath=.//*[@role='gridcell' and not(ancestor-or-self::*[@aria-hidden='true'])]")
    .last()
    .first();
  const poolCell = (await poolCellByColumnId.count()) > 0 ? poolCellByColumnId : poolCellFallback;

  const poolColumnId = await nearestColumnId(poolCell);
  console.log(`[recording-20260629_100320] labor pool cell data-columnid: ${poolColumnId || '(none)'}`);
  if (poolColumnId) {
    expect(normalizeText(poolColumnId).toLowerCase(), 'labor pool cell should be in a laborpool data-columnid column').toContain('laborpool');
  }

  await clickAttached(poolCell, 'new resource group cell', 15_000);
  const editorInput = page
    .locator("xpath=//input[not(@aria-hidden='true') and not(@type='hidden') and not(ancestor-or-self::*[@aria-hidden='true'])]")
    .last();
  await expect(editorInput, 'resource group editor should be visible').toBeVisible({ timeout: 10_000 });
  await editorInput.click({ force: true, noWaitAfter: true });
  await openDropdown(editorInput);
  await setInputText(editorInput, value);

  const option = visibleOption(page, value).first();
  if ((await option.count()) > 0 && (await option.isVisible().catch(() => false))) {
    await fastClick(option, `resource group option ${value}`, 20_000);
  } else {
    await editorInput.press('Enter');
  }
  await expect(textLocator(page, value)).toBeVisible({ timeout: 10_000 });
}

function newBlankResourceRow(activePanel: Locator): Locator {
  return activePanel
    .locator(
      "xpath=.//*[@role='row' and .//*[@role='gridcell' and normalize-space()='4'] and not(.//*[contains(normalize-space(.), 'REG_CONSULTANT')])]",
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
  await expect(newPlanRow, 'new labor planning row should be visible').toBeVisible({ timeout: 15_000 });

  const fteCell = newPlanRow.locator("xpath=.//*[@role='gridcell' and contains(normalize-space(.), 'FTE')]").first();

  console.log('[recording-20260629_100320] setting FTE in new labor planning detail row');
  await clickAttached(fteCell, 'new labor detail FTE cell', 15_000);

  const editorInput = page
    .locator(
      "xpath=//input[not(@aria-hidden='true') and not(@type='hidden') and not(@readonly) and not(contains(@id,'-unit')) and not(ancestor-or-self::*[@aria-hidden='true'])]",
    )
    .last();
  await expect(editorInput, 'editable FTE value editor should be visible').toBeVisible({ timeout: 10_000 });
  await setInputText(editorInput, value);
  await editorInput.press('Tab');
}

async function deleteFirstCopiedLaborRow(page: Page): Promise<void> {
  const activePanel = page.locator("xpath=//*[@role='tabpanel' and @aria-hidden='false']").first();
  const deleteIcon = activePanel
    .locator("xpath=.//*[contains(@class,'x-action-col-icon') and (contains(@class,'x-action-col-1') or contains(@data-qtip,'Delete'))]")
    .first();
  await clickAttached(deleteIcon, 'copied labor row delete icon', 15_000);

  const dialog = page.locator("xpath=//*[@role='dialog' and @aria-hidden='false']").first();
  if ((await dialog.count()) > 0 && (await dialog.isVisible().catch(() => false))) {
    const yes = dialog.getByRole('button', { name: /^(Yes|OK|Delete|Remove)$/i }).first();
    if ((await yes.count()) > 0 && (await yes.isVisible().catch(() => false))) {
      await fastClick(yes, 'delete confirmation button', 10_000);
    } else {
      const visibleButtons = dialog.locator("xpath=.//*[@role='button' and not(ancestor-or-self::*[@aria-hidden='true'])]");
      await fastClick(visibleButtons.last(), 'delete confirmation fallback button', 10_000);
    }
  }
  await waitForNoLoading(page, 15_000);
}

async function saveEstimate(page: Page): Promise<void> {
  const unexpectedDialogs: string[] = [];
  page.on('dialog', async (dialog) => {
    unexpectedDialogs.push(dialog.message());
    await dialog.dismiss().catch(() => undefined);
  });

  const saveButton = page.locator("xpath=//a[@data-qtip='Save' and @aria-hidden='false']").first();
  await fastClick(saveButton, 'estimate Save button', 10_000);
  await waitForNoLoading(page, 30_000);

  const errorDialog = page.locator("xpath=//*[@role='dialog' and @aria-hidden='false']//*[normalize-space()='Error']/ancestor::*[@role='dialog'][1]").first();
  await expect(errorDialog, 'Save should not show an Error dialog').toBeHidden({ timeout: 5_000 });
  expect(unexpectedDialogs, `Unexpected browser dialogs during save: ${unexpectedDialogs.join(' | ')}`).toEqual([]);
}

async function updateCost(page: Page): Promise<void> {
  const updateCostButton = page
    .locator("xpath=//*[not(ancestor-or-self::*[@aria-hidden='true']) and normalize-space()='Update Cost']")
    .first();
  await fastClick(updateCostButton, 'Update Cost', 15_000);
  await waitForNoLoading(page, 30_000);
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

function escapeRegex(value: string): string {
  return value.replace(/[.*+?^${}()|[\]\\]/g, '\\$&');
}

function xpathLiteral(value: string): string {
  if (!value.includes("'")) return `'${value}'`;
  if (!value.includes('"')) return `"${value}"`;
  return `concat(${value
    .split("'")
    .map((part) => `'${part}'`)
    .join(', "\'", ')})`;
}
