import { expect, type Page } from 'playwright/test';
import { test } from 'playwright/test';
import { connectToTwentyFive, waitForNoLoading, type CdpSession } from './support/twentyfive-cdp';
import { ProposalSetupPage } from './support/pom';
import { clickAttached, clickTab, expectVisibleTabs, expectVisibleTexts, openAppHash, tabLocator, textLocator } from './support/twentyfive-ui';

const FIXTURES = {
  quote003: 'quote:f1d15119-15da-4d1d-86ac-82165af94e06',
  quote004: 'quote:a3db9f3c-166f-30b9-b52a-c2046f4b8890',
  boe004: 'boe:3cd6c4bd-d2ee-3ea2-bd25-9f73ccf87bd9',
  boe011: 'boe:664d2693-9005-359b-85c1-491581dd092e',
};

test.describe('Manufacturing Proposal Playwright fast suite', () => {
  let session: CdpSession;
  let page: Page;
  let createdProposalTitle: string;
  let createdProposalUrl: string;
  let createdEstimateUrl: string;

  test.beforeAll(async () => {
    session = await connectToTwentyFive();
    page = session.page;
  });

  test('TCM-001 Verify Manufacturing Proposal Setup layout', async () => {
    const setup = new ProposalSetupPage(page);
    await setup.navigateToProposals();
    await setup.startNewProposal();
    await setup.selectProposalType('Regression Test Only - Manufacturing');
    await expectVisibleTabs(page, [
      'Setup',
      'Sets/Phases',
      'Cost Structure',
      'Schedule',
      'Pursuit Team',
      'Pursuit Milestones',
      'Analysis',
      'Pricing',
    ]);
    await expectVisibleTexts(page, [
      'Proposal Title',
      'Proposal Type',
      'SAP Project',
      'Estimated Project Start',
      'Project End',
      'Your Company',
      'Leading Site or Department',
      'Client/Customer',
      'Project Goals or Remarks',
    ]);
  });

  test('TCM-002 Create Manufacturing proposal, save, capture URL, and reopen', async () => {
    const setup = new ProposalSetupPage(page);
    await setup.ensureManufacturingSetupPage();

    const title = `TC-Manufacturing-Proposal-002 ${new Date().toISOString().slice(0, 16).replace('T', ' ')}`;
    await setup.fillManufacturingRequiredFields({
      title,
      plannedStart: '4/1/25',
      end: '3/31/26',
      company: 'Regression Test',
      leadingDepartment: 'US - New York',
      clientCustomer: 'Regression Test - Customer USD',
      projectGoals: 'Test Project Description',
    });
    await setup.saveViaToolbar();
    await setup.expectSaveSuccess();
    createdProposalTitle = title;
    createdProposalUrl = await setup.waitForSavedProposalUrl();
    console.log(`[manufacturing] created proposal url: ${createdProposalUrl}`);

    await page.goto(createdProposalUrl, { waitUntil: 'domcontentloaded', timeout: 45_000 }).catch((error) => {
      const message = error instanceof Error ? error.message : String(error);
      if (!message.includes('ERR_ABORTED')) throw error;
    });
    await setup.waitForSetupPage();
    await setup.expectDescription(createdProposalTitle);
  });

  test('TCM-003 Verify existing Manufacturing proposal can open Phases and Cost Structure', async () => {
    await openCreatedProposalOrFixture(page, createdProposalUrl, FIXTURES.quote003);
    await clickTab(page, 'Sets/Phases');
    await expectVisibleTexts(page, ['Total Revenue', 'Total Cost', 'Updated'], 8_000);
    createdEstimateUrl = await createOrOpenEstimateFromPhase(page);
    console.log(`[manufacturing] created estimate url: ${createdEstimateUrl}`);
    await page.goto(createdProposalUrl, { waitUntil: 'domcontentloaded', timeout: 45_000 }).catch((error) => {
      const message = error instanceof Error ? error.message : String(error);
      if (!message.includes('ERR_ABORTED')) throw error;
    });
    await clickTab(page, 'Cost Structure');
    await expectVisibleTexts(page, ['Cost Structure'], 8_000);
  });

  test('TCM-004 Verify Manufacturing estimate layout', async () => {
    await openCreatedEstimateOrFixture(page, createdEstimateUrl, FIXTURES.boe004);
    await expectEstimateShell(page);
    await expectVisibleTabs(page, ['Labor', 'Other', 'Proposal BOM', 'Procurement & Production', 'Work Packages', 'Workflow']);
  });

  test('TCM-005 Verify Labor cost fixture state', async () => {
    await openBoe(page, FIXTURES.boe004);
    await clickTab(page, 'Labor');
    await expectVisibleTexts(page, ['Labor', 'REG_CONSULTANT1', 'REG_PROJECTMANAGER'], 10_000);
  });

  test('TCM-006 Verify Other cost fixture state', async () => {
    await openBoe(page, FIXTURES.boe004);
    await clickTab(page, 'Other');
    await expectVisibleTexts(page, ['Other', 'Construction Management', 'Central Engineering'], 10_000);
  });

  test('TCM-006.1 Verify Travel cost fixture state', async () => {
    await openBoe(page, FIXTURES.boe004);
    await clickTab(page, 'Other');
    await expectVisibleTexts(page, ['Travel Expenses', 'Travel'], 10_000);
  });

  test('TCM-007 Verify Procurement & Production fixture state', async () => {
    await openBoe(page, FIXTURES.boe004);
    await clickTab(page, 'Procurement & Production');
    await expectVisibleTexts(page, ['REG_MATERIAL1', 'REG_MATERIAL1 Copy', 'REG_MATERIAL2'], 10_000);
  });

  test('TCM-008 Verify Fringe cost fixture state', async () => {
    await openBoe(page, FIXTURES.boe004);
    await clickTab(page, 'Labor');
    await expectVisibleTexts(page, ['REG_CONSULTANT1', 'Fringe'], 10_000);
  });

  test('TCM-009 Verify Overhead cost fixture state', async () => {
    await openBoe(page, FIXTURES.boe004);
    await clickTab(page, 'Procurement & Production');
    await expectVisibleTexts(page, ['REG_MATERIAL1', 'Overhead'], 10_000);
  });

  test('TCM-010 Verify G&A cost fixture state', async () => {
    await openBoe(page, FIXTURES.boe004);
    await clickTab(page, 'Procurement & Production');
    await expectVisibleTexts(page, ['REG_MATERIAL1', 'G&A'], 10_000);
  });

  test('TCM-011 Verify Labor amortization fixture opens', async () => {
    await openBoe(page, FIXTURES.boe011);
    await clickTab(page, 'Labor');
    await expectVisibleTexts(page, ['REG_CONSULTANT1', 'REG_PROJECTMANAGER'], 10_000);
  });

  test('TCM-012 Verify Material depreciation fixture opens', async () => {
    await openBoe(page, FIXTURES.boe011);
    await clickTab(page, 'Procurement & Production');
    await expectVisibleTexts(page, ['REG_MATERIAL1', 'REG_MATERIAL1 Copy'], 10_000);
  });
});

async function openQuote(page: Page, hash: string): Promise<void> {
  await openAppHash(page, hash, [tabLocator(page, 'Setup'), textLocator(page, 'Setup')], hash);
}

async function openCreatedProposalOrFixture(page: Page, url: string | undefined, fallbackHash: string): Promise<void> {
  if (!url) {
    await openQuote(page, fallbackHash);
    return;
  }

  const startedAt = Date.now();
  await page.goto(url, { waitUntil: 'domcontentloaded', timeout: 45_000 }).catch((error) => {
    const message = error instanceof Error ? error.message : String(error);
    if (!message.includes('ERR_ABORTED')) throw error;
  });
  await expect(tabLocator(page, 'Setup')).toBeVisible({ timeout: 45_000 });
  console.log(`[timing] reopen created proposal: ${Date.now() - startedAt}ms`);
}

async function openBoe(page: Page, hash: string): Promise<void> {
  await openAppHash(page, hash, [tabLocator(page, 'Labor'), tabLocator(page, 'Other'), textLocator(page, 'Labor')], hash);
}

async function openCreatedEstimateOrFixture(page: Page, url: string | undefined, fallbackHash: string): Promise<void> {
  if (!url) {
    await openBoe(page, fallbackHash);
    return;
  }

  const startedAt = Date.now();
  await page.goto(url, { waitUntil: 'domcontentloaded', timeout: 45_000 }).catch((error) => {
    const message = error instanceof Error ? error.message : String(error);
    if (!message.includes('ERR_ABORTED')) throw error;
  });
  await expect(tabLocator(page, 'Labor')).toBeVisible({ timeout: 60_000 });
  console.log(`[timing] reopen created estimate: ${Date.now() - startedAt}ms`);
}

async function expectEstimateShell(page: Page): Promise<void> {
  await expect(tabLocator(page, 'Labor')).toBeVisible({ timeout: 10_000 });
}

async function createOrOpenEstimateFromPhase(page: Page): Promise<string> {
  const startedAt = Date.now();
  const addPhaseLink = page
    .locator("xpath=//*[@data-grigaddlink='true' or contains(normalize-space(.),'No records found, click here to add')]")
    .first();
  const openEstimateLink = estimateGridLink(page, 'Open');
  const createEstimateLink = estimateGridLink(page, 'Create');

  if (!(await hasAttached(openEstimateLink)) && !(await hasAttached(createEstimateLink)) && (await hasAttached(addPhaseLink))) {
    await clickAttached(addPhaseLink, 'No records add phase link', 8_000);
    await expect(createEstimateLink).toBeAttached({ timeout: 15_000 });
  }

  if (await hasAttached(createEstimateLink)) {
    await new ProposalSetupPage(page).saveViaToolbar();
    await waitForNoLoading(page, 10_000);
    await expect(createEstimateLink).toBeAttached({ timeout: 15_000 });
    await clickAttached(createEstimateLink, 'Create estimate link', 8_000);

    const estimateDialog = page
      .locator("xpath=//*[@role='dialog' and @aria-hidden='false']//*[normalize-space()='Estimate']/ancestor::*[@role='dialog'][1]")
      .first();
    await expect(estimateDialog).toBeVisible({ timeout: 20_000 });
    const confirmOpen = estimateDialog
      .locator("xpath=.//*[@role='button' and @aria-hidden='false']//*[normalize-space()='Confirm & Open']/ancestor::*[@role='button'][1]")
      .first();
    await clickAttached(confirmOpen, 'Confirm & Open estimate', 8_000);
  } else {
    await clickAttached(openEstimateLink, 'Open estimate link', 8_000);
  }

  await page.waitForURL(/#boe:[0-9a-f]{8}(?:-[0-9a-f]{4}){3}-[0-9a-f]{12}$/i, { timeout: 60_000 }).catch(async () => {
    const refreshedOpenEstimateLink = estimateGridLink(page, 'Open');
    if (await hasAttached(refreshedOpenEstimateLink)) {
      await clickAttached(refreshedOpenEstimateLink, 'Open estimate link after confirm', 8_000);
      await page.waitForURL(/#boe:[0-9a-f]{8}(?:-[0-9a-f]{4}){3}-[0-9a-f]{12}$/i, { timeout: 60_000 });
    }
  });
  await expect(tabLocator(page, 'Labor')).toBeVisible({ timeout: 60_000 });
  console.log(`[timing] create/open estimate from phase: ${Date.now() - startedAt}ms`);
  return page.url();
}

function estimateGridLink(page: Page, text: 'Create' | 'Open') {
  return page
    .locator(`xpath=//*[@role='tabpanel' and @aria-hidden='false']//*[self::a or self::div][normalize-space(.)='${text}']`)
    .first();
}

async function hasAttached(locator: ReturnType<Page['locator']>): Promise<boolean> {
  return (await locator.count()) > 0;
}
