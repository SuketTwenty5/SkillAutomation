import { expect, type Locator, type Page } from 'playwright/test';
import { test } from 'playwright/test';
import { connectToTwentyFive } from './support/twentyfive-cdp';
import { ProposalSetupPage } from './support/pom';
import { clickAttached, clickTab, fastClick, openEstimateFromActiveTab, textLocator } from './support/twentyfive-ui';

const testData = {
  source: 'playwrightRecording/20260629_084528/create-engineering-services-proposal-from-prior-project-2026-06-29-08-45-28-ist.md',
  proposalTitle: 'Test eng proposal test',
  proposalType: 'Engineering/Services Proposal',
  estimatedProjectStart: '6/29/2030',
  projectEnd: '6/28/2035',
  businessUnit: 'Poland',
  department: 'IT - Italy (Modena)',
  clientSearchText: 'usd',
  clientCustomer: 'Regression Test - Customer USD',
  priorProjectSearch: 'TEST Reschedule Proposal 2026-06-29 03:58',
  priorProjectResult: 'TEST Reschedule Proposal 2026-06-29 03:58',
  costStructureSearch: 'Eng',
};

test.describe('Recording 20260629_084528', () => {
  test('Create Engineering/Services proposal from prior project and verify estimate tabs', async ({}, testInfo) => {
    testInfo.annotations.push({
      type: 'source',
      description: testData.source,
    });

    const session = await connectToTwentyFive();
    const page = session.page;
    const setup = new ProposalSetupPage(page);

    await test.step('Open Proposals and start a new proposal', async () => {
      await setup.navigateToProposals();
      await setup.startNewProposal();
      await setup.waitForSetupPage();
    });

    await test.step('Fill recorded proposal setup values', async () => {
      await setup.selectProposalType(testData.proposalType);
      await setup.setProposalTitle(testData.proposalTitle);
      await setup.setEstimatedProjectStart(testData.estimatedProjectStart);
      await setup.setProjectEnd(testData.projectEnd);
      await selectDropdownByField(page, 'Your Company', testData.businessUnit);
      await selectDropdownByField(page, 'Leading Site or Department', testData.department);
      await selectDropdownByField(page, 'Client/Customer (Sell-to)', testData.clientSearchText, testData.clientCustomer);

      await setup.expectDescription(testData.proposalTitle);
      await setup.expectSetupFieldValue('Estimated Project Start', testData.estimatedProjectStart);
      await setup.expectSetupFieldValue('Project End', testData.projectEnd);
      await setup.expectSetupFieldValue('Client/Customer (Sell-to)', testData.clientCustomer);
    });

    await test.step('Select prior project and copy proposal data', async () => {
      await selectPriorProject(page, testData.priorProjectSearch, testData.priorProjectResult);
      await fastClick(page.getByRole('button', { name: 'Copy' }).first(), 'Copy prior project button', 15_000);
      await waitForNotBusy(page);
      await expect(textLocator(page, 'Cost Structure')).toBeVisible({ timeout: 30_000 });
    });

    await test.step('Select engineering cost structure and open the estimate', async () => {
      await clickTab(page, 'Cost Structure');
      await chooseCostStructure(page, testData.costStructureSearch);
      const estimatePage = await openEstimate(page);

      await expect(textLocator(estimatePage, 'Risk & Contingency')).toBeVisible({ timeout: 60_000 });
      await clickTab(estimatePage, 'Risk & Contingency');
      await expect(textLocator(estimatePage, 'Risk & Contingency')).toBeVisible();
      await clickTab(estimatePage, 'Work Packages');
      await expect(textLocator(estimatePage, 'Work Packages')).toBeVisible();
      await clickTab(estimatePage, 'Workflow');
      await expect(textLocator(estimatePage, 'Workflow')).toBeVisible();

      await saveEstimate(estimatePage);
      console.log(`[recording-20260629_084528] estimate url: ${estimatePage.url()}`);
      if (estimatePage !== page) {
        await estimatePage.close();
      }
    });
  });
});

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

async function chooseCostStructure(page: Page, searchText: string): Promise<void> {
  const activePanel = page.locator("xpath=//*[@role='tabpanel' and @aria-hidden='false']").first();
  await expect(activePanel, 'Cost Structure tab panel should be visible').toBeVisible({ timeout: 15_000 });

  const existingOpen = activePanel.locator("xpath=.//*[self::a or self::div or self::span][normalize-space()='Open']").first();
  if ((await existingOpen.count()) > 0 && (await existingOpen.isVisible().catch(() => false))) {
    return;
  }

  const emptyGridLink = activePanel
    .locator("xpath=.//*[@data-grigaddlink='true' or contains(normalize-space(.),'No records found, click here')]")
    .first();
  if ((await emptyGridLink.count()) > 0 && (await emptyGridLink.isVisible().catch(() => false))) {
    await clickAttached(emptyGridLink, 'empty cost structure grid add link', 15_000);
  } else {
    const addButton = activePanel
      .locator("xpath=.//*[@role='button' and @aria-hidden='false' and (@data-qtip='Add' or .//*[normalize-space()='Add'])]")
      .first();
    if ((await addButton.count()) > 0 && (await addButton.isVisible().catch(() => false))) {
      await fastClick(addButton, 'Add cost structure button', 10_000);
    }
  }

  const searchInput = page
    .locator("xpath=//input[not(@aria-hidden='true') and (contains(@id,'iBESearchComboBox') or contains(@placeholder,'Search') or contains(@placeholder,'search'))]")
    .first();
  await expect(searchInput, 'Cost structure search input should be visible').toBeVisible({ timeout: 15_000 });
  await searchInput.click({ force: true, noWaitAfter: true });
  await setInputText(searchInput, searchText);

  const option = visibleOption(page, searchText)
    .or(page.locator(`xpath=//*[@role='gridcell' and not(ancestor-or-self::*[@aria-hidden='true']) and contains(normalize-space(.), ${xpathLiteral(searchText)})]`))
    .first();
  await fastClick(option, `cost structure option ${searchText}`, 25_000);

  const confirm = page.getByRole('button', { name: /^(OK|Select|Confirm|Apply)$/i }).first();
  if ((await confirm.count()) > 0 && (await confirm.isVisible().catch(() => false))) {
    await fastClick(confirm, 'Confirm cost structure selection', 10_000);
  }
}

async function openEstimate(page: Page): Promise<Page> {
  return openEstimateFromActiveTab(page);
}

async function saveEstimate(page: Page): Promise<void> {
  const unexpectedDialogs: string[] = [];
  page.on('dialog', async (dialog) => {
    unexpectedDialogs.push(dialog.message());
    await dialog.dismiss().catch(() => undefined);
  });

  const saveButton = page.locator("xpath=//a[@data-qtip='Save' and @aria-hidden='false']").first();
  await fastClick(saveButton, 'estimate Save button', 10_000);
  await waitForNotBusy(page);

  const errorDialog = page.locator("xpath=//*[@role='dialog' and @aria-hidden='false']//*[normalize-space()='Error']/ancestor::*[@role='dialog'][1]").first();
  await expect(errorDialog, 'Save should not show an Error dialog').toBeHidden({ timeout: 5_000 });
  expect(unexpectedDialogs, `Unexpected browser dialogs during save: ${unexpectedDialogs.join(' | ')}`).toEqual([]);
}

function inputForField(page: Page, label: string): Locator {
  if (label === 'Your Company') {
    return page.locator("xpath=//input[@placeholder='Select Company' and not(@aria-hidden='true')]").first();
  }
  if (label === 'Leading Site or Department') {
    return page.locator("xpath=//input[@placeholder='Select Department' and not(@aria-hidden='true')]").first();
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

async function waitForNotBusy(page: Page): Promise<void> {
  const loadingMask = page.locator(
    "xpath=//*[contains(normalize-space(.), 'Loading Twenty5') or contains(normalize-space(.), 'Loading document') or (contains(@class,'x-mask-msg-text') and contains(normalize-space(.), 'Loading'))]",
  );
  if ((await loadingMask.count()) > 0) {
    await loadingMask.first().waitFor({ state: 'hidden', timeout: 20_000 }).catch(() => undefined);
  }
}

function visibleOption(page: Page, text: string): Locator {
  return page.locator(
    `xpath=//*[@role='option' and not(ancestor-or-self::*[@aria-hidden='true']) and contains(normalize-space(.), ${xpathLiteral(text)})]`,
  );
}

function xpathLiteral(value: string): string {
  if (!value.includes("'")) return `'${value}'`;
  if (!value.includes('"')) return `"${value}"`;
  return `concat(${value
    .split("'")
    .map((part) => `'${part}'`)
    .join(', "\'", ')})`;
}
