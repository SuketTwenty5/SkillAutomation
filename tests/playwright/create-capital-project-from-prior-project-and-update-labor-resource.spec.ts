import { expect, type Locator, type Page } from 'playwright/test';
import { test } from 'playwright/test';
import { connectToTwentyFive, waitForNoLoading } from './support/twentyfive-cdp';
import { ProposalSetupPage } from './support/pom';
import { clickAttached, clickTab, fastClick, openEstimateFromActiveTab, textLocator } from './support/twentyfive-ui';

const testData = {
  source: 'playwrightRecording/20260629_094237/create-capital-project-from-prior-project-and-update-labor-resource-2026-06-29-09-42-37-ist.md',
  projectName: 'Test Capital Project Copy',
  probableProjectStart: '6/29/2031',
  projectEnd: '6/28/2037',
  leadingSite: 'SE- Sweden (Lund)',
  clientSearchText: 'usd',
  clientCustomer: 'Regression Test - Customer USD',
  priorProjectSearch: '(AS) Copy from template v5',
  priorProjectResult: '(AS) Copy from Template v5',
  newLaborResource: 'REG_CONSULTANT999999',
  laborPool: 'Project Manager',
  copiedLaborResource: 'REG_CONSULTANT1',
};

test.describe('Recording 20260629_094237', () => {
  test('Create capital project from prior project and update labor resource', async ({}, testInfo) => {
    testInfo.annotations.push({
      type: 'source',
      description: testData.source,
    });

    const session = await connectToTwentyFive();
    const page = session.page;
    const setup = new ProposalSetupPage(page);

    await test.step('Open Proposals and start a new capital project', async () => {
      await setup.navigateToProposals();
      await setup.startNewProposal();
      await setup.waitForSetupPage();
    });

    await test.step('Fill recorded capital project setup values', async () => {
      await setInputField(page, 'Project Name', uniqueProjectName(testData.projectName));
      await setInputField(page, 'Probable Project Start', testData.probableProjectStart);
      await setInputField(page, 'Project End', testData.projectEnd);
      await selectDropdownByField(page, 'Leading Site', 'Sweden', testData.leadingSite);
      await selectDropdownByField(page, 'Client/Customer (Sell-to)', testData.clientSearchText, testData.clientCustomer);

      await expectFieldValue(page, 'Probable Project Start', testData.probableProjectStart);
      await expectFieldValue(page, 'Project End', testData.projectEnd);
      await expectFieldValue(page, 'Client/Customer (Sell-to)', testData.clientCustomer);
    });

    await test.step('Select prior project and copy project data', async () => {
      await selectPriorProject(page, testData.priorProjectSearch, testData.priorProjectResult);
      await fastClick(page.getByRole('button', { name: 'Copy' }).first(), 'Copy prior project button', 20_000);
      await waitForNoLoading(page, 30_000);
      await expect(textLocator(page, 'Estimates')).toBeVisible({ timeout: 30_000 });
    });

    await test.step('Open estimate from Estimates tab', async () => {
      await clickTab(page, 'Estimates');
      const estimatePage = await openEstimate(page);
      console.log(`[recording-20260629_094237] estimate url: ${estimatePage.url()}`);

      await test.step('Add labor row and set labor pool', async () => {
        await clickTab(estimatePage, 'Labor');
        await addLaborRow(estimatePage);
        await setNewLaborResource(estimatePage, testData.newLaborResource);
        await selectLaborPoolForNewRow(estimatePage, testData.laborPool);
        await saveEstimate(estimatePage);
        await expect(textLocator(estimatePage, testData.copiedLaborResource)).toBeVisible({ timeout: 15_000 });
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
  const addIcon = activePanel
    .locator(
      "xpath=.//*[contains(@class,'x-action-col-icon') and (contains(@class,'x-action-col-0') or contains(@class,'add') or contains(@data-qtip,'Add'))]",
    )
    .first();
  const addButton = activePanel
    .locator("xpath=.//*[@role='button' and @aria-hidden='false' and (@data-qtip='Add' or .//*[normalize-space()='Add'])]")
    .first();

  if ((await addIcon.count()) > 0 && (await addIcon.isVisible().catch(() => false))) {
    await clickAttached(addIcon, 'labor grid add-row icon', 15_000);
    return;
  }

  await fastClick(addButton, 'labor grid Add button', 15_000);
}

async function setNewLaborResource(page: Page, value: string): Promise<void> {
  const activePanel = page.locator("xpath=//*[@role='tabpanel' and @aria-hidden='false']").first();
  const editableCell = activePanel
    .locator(
      "xpath=(.//*[contains(@class,'resourceGridFreeTextColumn') and not(ancestor-or-self::*[@aria-hidden='true'])] | .//*[@role='gridcell' and not(ancestor-or-self::*[@aria-hidden='true'])])[last()]",
    )
    .first();
  await clickAttached(editableCell, 'new labor resource cell', 15_000);

  const editorInput = page
    .locator("xpath=//input[not(@aria-hidden='true') and not(@type='hidden') and not(ancestor-or-self::*[@aria-hidden='true'])]")
    .last();
  await expect(editorInput, 'new labor resource editor should be visible').toBeVisible({ timeout: 10_000 });
  await setInputText(editorInput, value);
  await editorInput.press('Enter');
  await expect(textLocator(page, value)).toBeVisible({ timeout: 10_000 });
}

async function selectLaborPoolForNewRow(page: Page, value: string): Promise<void> {
  const row = page.locator(`xpath=//*[contains(normalize-space(.), ${xpathLiteral(testData.newLaborResource)})]/ancestor::*[@role='row'][1]`).first();
  const poolCellByColumnId = row
    .locator(
      "xpath=.//*[@role='gridcell' and not(ancestor-or-self::*[@aria-hidden='true']) and (contains(translate(@data-columnid,'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'laborpool') or .//*[contains(translate(@data-columnid,'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'laborpool')])]",
    )
    .first();
  const poolCellByClass = row.locator("xpath=.//*[contains(@class,'resourceGridLaborPoolColumn')]").first();
  const poolCellFallback = row.locator("xpath=.//*[@role='gridcell' and not(ancestor-or-self::*[@aria-hidden='true'])]").last().first();
  const poolCell =
    (await poolCellByColumnId.count()) > 0 ? poolCellByColumnId : (await poolCellByClass.count()) > 0 ? poolCellByClass : poolCellFallback;

  await clickAttached(poolCell, 'new labor pool cell', 15_000);
  const editorInput = page
    .locator("xpath=//input[not(@aria-hidden='true') and not(@type='hidden') and not(ancestor-or-self::*[@aria-hidden='true'])]")
    .last();
  await expect(editorInput, 'labor pool editor should be visible').toBeVisible({ timeout: 10_000 });
  await editorInput.click({ force: true, noWaitAfter: true });
  await openDropdown(editorInput);

  const option = visibleOption(page, value).first();
  await fastClick(option, `labor pool option ${value}`, 20_000);
  await expect(textLocator(page, value)).toBeVisible({ timeout: 10_000 });
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
  if (label === 'Your Company') {
    return page.locator("xpath=//input[@placeholder='Select Company' and not(@aria-hidden='true')]").first();
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
