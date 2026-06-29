import { expect, type Locator, type Page } from 'playwright/test';
import { test } from 'playwright/test';
import { connectToTwentyFive } from './support/twentyfive-cdp';
import { ProposalSetupPage } from './support/proposal-setup-page';
import { clickAttached, clickTab, fastClick, textLocator } from './support/twentyfive-ui';

const recordingData = {
  source: 'playwrightRecording/20260627_175641/recording-2026-06-27-17-56-41-ist.md',
  proposalType: 'Engineering/Services Proposal',
  proposalTitle: 'test proposal',
  estimatedProjectStart: '6/27/2031',
  projectEnd: '12/30/2031',
  businessUnit: 'Australia',
  department: 'AI SOLUTIONS',
  clientSearchText: 'usd',
  clientCustomer: 'Regression Test - Customer USD',
  phaseDescription: 'test',
  templateSearchText: 'regression',
  templateSource: 'RG01',
  copySource: '2025-4837 | (AS) Regression',
  laborResource: 'consultant 4',
  laborRole: 'Junior Consultant',
  laborEffort: '2000',
};

test.describe('Recording 20260627_175641', () => {
  test('Create Engineering/Services proposal and add labor resource from timestamped recording guide', async ({}, testInfo) => {
    testInfo.annotations.push({
      type: 'source',
      description: recordingData.source,
    });

    const session = await connectToTwentyFive();
    const page = session.page;
    const setup = new ProposalSetupPage(page);

    await test.step('Open Proposals and start a new proposal', async () => {
      await setup.navigateToProposals();
      await setup.startNewProposal();
    });

    await test.step('Fill proposal setup fields from the recording', async () => {
      await setup.selectProposalType(recordingData.proposalType);
      await setup.setProposalTitle(recordingData.proposalTitle);
      await setup.setEstimatedProjectStart(recordingData.estimatedProjectStart);
      await setup.setProjectEnd(recordingData.projectEnd);

      await selectDropdownByLabel(page, 'Your Company', recordingData.businessUnit);
      await selectDropdownByLabel(page, 'Leading Site or Department', recordingData.department);
      await selectDropdownByLabel(page, 'Client/Customer (Sell-to)', recordingData.clientSearchText, recordingData.clientCustomer);

      await setup.expectDescription(recordingData.proposalTitle);
      await setup.expectSetupFieldValue('Estimated Project Start', recordingData.estimatedProjectStart);
      await setup.expectSetupFieldValue('Project End', recordingData.projectEnd);
      await setup.expectSetupFieldValue('Client/Customer (Sell-to)', recordingData.clientCustomer);
    });

    await test.step('Save setup, add phase, and create proposal', async () => {
      await setup.saveViaToolbar();
      await clickTab(page, 'Sets/Phases');
      await addPhase(page, recordingData.phaseDescription);
      await setup.saveViaToolbar();
      await clickCreateIfVisible(page);
      await clickCreateIfVisible(page);
      await expect(textLocator(page, 'Cost Structure')).toBeVisible({ timeout: 30_000 });
      console.log(`[recording-20260627_175641] proposal url after create: ${page.url()}`);
    });

    await test.step('Copy from regression template when setup copy controls are available', async () => {
      await clickTab(page, 'Setup');
      const templateInput = page
        .locator("xpath=//input[contains(@id,'iBESearchComboBox') and not(@aria-hidden='true')]")
        .first();

      if ((await templateInput.count()) === 0 || !(await templateInput.isVisible().catch(() => false))) {
        console.log('[recording-20260627_175641] template copy input was not visible; continuing to Cost Structure.');
        return;
      }

      await templateInput.click({ force: true, noWaitAfter: true });
      await templateInput.fill(recordingData.templateSearchText);
      await fastClick(
        page.locator(`xpath=//*[@role='option' and contains(normalize-space(.), '${recordingData.templateSource}')]`).first(),
        `template option ${recordingData.templateSource}`,
        20_000,
      );

      const copySource = page.locator(`xpath=//*[contains(normalize-space(.), '${recordingData.copySource}')]`).first();
      if ((await copySource.count()) > 0 && (await copySource.isVisible().catch(() => false))) {
        await fastClick(copySource, `copy source ${recordingData.copySource}`, 10_000);
      }

      await fastClick(page.getByRole('button', { name: 'Copy' }).first(), 'Copy button', 10_000);
    });

    await test.step('Open cost structure and add labor row', async () => {
      await clickTab(page, 'Cost Structure');
      const estimatePage = await openCostStructure(page);
      await clickTab(estimatePage, 'Labor');
      await addLaborResource(estimatePage);
      await expect(textLocator(estimatePage, recordingData.laborResource)).toBeVisible({ timeout: 10_000 });
      await estimatePage.close();
    });
  });
});

async function selectDropdownByLabel(page: Page, label: string, searchText: string, expectedText = searchText): Promise<void> {
  const input = inputForField(page, label);
  await expect(input, `${label} field should be visible`).toBeVisible({ timeout: 10_000 });
  await input.scrollIntoViewIfNeeded();
  await input.click({ force: true, noWaitAfter: true });
  await openDropdown(input);
  await input.fill(searchText);

  const option = page
    .locator(`xpath=//ul[contains(@class,'x-list-plain') and @aria-hidden='false']//*[@role='option' and contains(normalize-space(.), '${expectedText}')]`)
    .first();
  await fastClick(option, `${label} option ${expectedText}`, 20_000);
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
      `xpath=(//*[self::label or self::span or self::div][contains(normalize-space(.), '${label}')]/ancestor::div[contains(@class,'x-field ')][1]//*[self::input or self::textarea][not(@aria-hidden='true')])[1]`,
    )
    .first();
}

async function openDropdown(input: Locator): Promise<void> {
  const triggers = [
    input.locator("xpath=./ancestor::*[@data-ref='triggerWrap'][1]//*[contains(@class,'x-form-arrow-trigger')]").first(),
    input.locator("xpath=./parent::div/following-sibling::div[contains(@class,'x-form-arrow-trigger')]").first(),
    input
      .locator("xpath=./ancestor::*[@data-ref='triggerWrap'][1]//*[contains(@class,'x-form-trigger') and not(contains(@class,'x-form-clear-trigger'))]")
      .first(),
  ];

  for (const trigger of triggers) {
    if ((await trigger.count()) > 0 && (await trigger.isVisible().catch(() => false))) {
      await trigger.click({ force: true, noWaitAfter: true });
      await input.page().waitForTimeout(300);
      return;
    }
  }
}

async function addPhase(page: Page, description: string): Promise<void> {
  const addPhaseLink = page
    .locator("xpath=//*[@data-grigaddlink='true' or contains(normalize-space(.),'No records found, click here to add')]")
    .first();
  await clickAttached(addPhaseLink, 'empty phase grid add link', 15_000);

  const phaseDescriptionCell = page
    .locator("xpath=//*[@role='tabpanel' and @aria-hidden='false']//*[contains(@class,'phaseTabDescription')]")
    .first();
  await clickAttached(phaseDescriptionCell, 'phase description cell', 10_000);
  await page.keyboard.type(description);
  await page.keyboard.press('Enter');
  await expect(textLocator(page, description)).toBeVisible({ timeout: 10_000 });
}

async function clickCreateIfVisible(page: Page): Promise<void> {
  const createButton = page.getByText('Create', { exact: true }).first();
  if ((await createButton.count()) > 0 && (await createButton.isVisible().catch(() => false))) {
    await fastClick(createButton, 'Create', 10_000);
  }
}

async function openCostStructure(page: Page): Promise<Page> {
  const openLink = page
    .locator("xpath=//*[@role='tabpanel' and @aria-hidden='false']//*[self::a or self::div][normalize-space(.)='Open']")
    .first();
  const createLink = page
    .locator("xpath=//*[@role='tabpanel' and @aria-hidden='false']//*[self::a or self::div][normalize-space(.)='Create']")
    .first();

  const link = (await openLink.count()) > 0 ? openLink : createLink;
  const popupPromise = page.waitForEvent('popup', { timeout: 30_000 }).catch(() => undefined);
  await clickAttached(link, 'cost structure Open/Create link', 15_000);

  const confirmAndOpen = page.getByRole('button', { name: 'Confirm & Open' }).first();
  if ((await confirmAndOpen.count()) > 0 && (await confirmAndOpen.isVisible().catch(() => false))) {
    await fastClick(confirmAndOpen, 'Confirm & Open', 10_000);
  }

  const popup = await popupPromise;
  const target = popup ?? page;
  await expect(textLocator(target, 'Labor')).toBeVisible({ timeout: 60_000 });
  return target;
}

async function addLaborResource(page: Page): Promise<void> {
  const addIcon = page
    .locator("xpath=//*[@role='tabpanel' and @aria-hidden='false']//*[contains(@class,'x-action-col') or contains(@class,'addRemoveDeleteColumn')]")
    .first();
  await clickAttached(addIcon, 'add labor row icon', 15_000);

  const freeTextCell = page
    .locator("xpath=//*[@role='tabpanel' and @aria-hidden='false']//*[contains(@class,'resourceGridFreeTextColumn')]")
    .last();
  await clickAttached(freeTextCell, 'labor resource free text cell', 10_000);
  await page.keyboard.type(recordingData.laborResource);
  await page.keyboard.press('Tab');

  const laborPoolCell = page
    .locator("xpath=//*[@role='tabpanel' and @aria-hidden='false']//*[contains(@class,'resourceGridLaborPoolColumn')]")
    .last();
  await clickAttached(laborPoolCell, 'labor pool cell', 10_000);

  const laborPoolInput = page
    .locator("xpath=//input[contains(@id,'iBEComboBoxRemote') and not(@aria-hidden='true')]")
    .first();
  await expect(laborPoolInput, 'labor pool editor should open').toBeVisible({ timeout: 10_000 });
  await openDropdown(laborPoolInput);
  await laborPoolInput.fill(recordingData.laborRole);
  await fastClick(
    page
      .locator(
        `xpath=//ul[contains(@class,'x-list-plain') and @aria-hidden='false']//*[@role='option' and contains(normalize-space(.), '${recordingData.laborRole}')]`,
      )
      .first(),
    `labor role ${recordingData.laborRole}`,
    20_000,
  );

  const effortCell = page
    .locator("xpath=//*[@role='tabpanel' and @aria-hidden='false']//*[contains(normalize-space(.),'0.000') or contains(@class,'effort')]")
    .last();
  await clickAttached(effortCell, 'labor effort cell', 10_000);
  await page.keyboard.type(recordingData.laborEffort);
  await page.keyboard.press('Enter');
}
