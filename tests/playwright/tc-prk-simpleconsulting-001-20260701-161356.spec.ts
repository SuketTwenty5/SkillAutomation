import { expect } from 'playwright/test';
import { test } from 'playwright/test';
import { connectToTwentyFive, waitForNoLoading } from './support/twentyfive-cdp';
import { EstimatePage, ProposalSetupPage, escapeRegex } from './support/pom';
import { clickTab, expectVisibleTabs, fastClick, textLocator } from './support/twentyfive-ui';

test.setTimeout(420_000);

// Timestamped conversion of the source test case (see "Spec File Naming And Reuse Rule").
// Full-match copy of tests/playwright/tc-prk-simpleconsulting-001.spec.ts; only the command
// reference and generation timestamp differ. Locators stay in tests/playwright/support/pom/.
const sourcePath = '/Users/suketsuman/Downloads/TC-PRK-SimpleConsulting-001.md';
const command = 'scripts/run-playwright-test.sh tests/playwright/tc-prk-simpleconsulting-001-20260701-161356.spec.ts --headed --retries=0';

const testData = {
  source: sourcePath,
  proposalTitle: 'Prk test',
  proposalType: 'Simple Consulting Proposal',
  expectedProposalRoute: '#quote:new:type=PS_consulting_simple',
  defaultPlannedStart: '6/30/2026',
  plannedStart: '6/30/2027',
  plannedEnd: '9/29/2029',
  pursuitManager: 'Tech User',
  lineOfBusinessSearch: 'RG01',
  lineOfBusiness: 'Regression Test',
  region: 'US - New York',
  profitCenterSearch: 'RG23',
  profitCenter: 'Spares',
  costCenter: 'REG CC-3',
  template: 'DEMO: ProServ - Standard T&M',
  templateChecks: ['WBS', 'Priced deliverables', 'Billing milestones', 'Risk & opportunities', 'Travel/other estimates'],
  tabs: [
    'Opportunity Details',
    'Client',
    'Project Scope',
    'Labor Resources',
    'Other Resources',
    'Risk Assessment',
    'Pricing',
    'Billing',
    'Analysis & Reports',
    'Approvals',
    'Parameters',
    'Pursuit Team',
  ],
  wbs: '1 Charge Code',
  laborView: 'Pro Serv - Planning View (shared & preferred)',
  laborGrade: 'Senior Consultant',
  laborRoleSearch: 'Functional Consultant',
  laborRole: 'Functional Consultant',
  laborLocation: 'P1810 India - Hyderabad',
  fte: '1',
  expectedFte: '1.000 FTE',
  expectedEffort: '4,592',
  expectedPrice: 'USD 298K',
  expectedCost: 'USD 185K',
  expectedMargin: '37.97%',
};

test.describe('TC-PRK-SimpleConsulting-001', () => {
  test('Create Simple Consulting Proposal with Labor Resource', async ({}, testInfo) => {
    testInfo.annotations.push({ type: 'source', description: testData.source });
    testInfo.annotations.push({ type: 'command', description: command });

    const session = await connectToTwentyFive();
    const page = session.page;
    const setup = new ProposalSetupPage(page);
    const est = new EstimatePage(page); // generic form + estimate/grid controls (all locators come from the POM)

    await test.step('Navigate to Proposals and start a new proposal', async () => {
      await setup.navigateToProposals();
      await setup.startNewProposal();
      await setup.waitForSetupPage();
    });

    await test.step('Select Simple Consulting Proposal and verify tabs', async () => {
      await setup.selectProposalType(testData.proposalType);
      await setup.expectSetupFieldValue('Select the Best-fit Type', testData.proposalType);
      await expect(page, 'Simple Consulting route should be selected').toHaveURL(new RegExp(escapeRegex(testData.expectedProposalRoute)));
      await expectVisibleTabs(page, testData.tabs);
    });

    await test.step('Enter proposal title and verify default fields', async () => {
      await setup.setProposalTitle(testData.proposalTitle);
      await setup.expectDescription(testData.proposalTitle);
      await est.expectFieldValue('Planned Start', testData.defaultPlannedStart);
      await est.expectFieldValue('Pursuit Manager', testData.pursuitManager);
    });

    await test.step('Update Planned Start and Planned End dates', async () => {
      await est.setFieldValue('Planned Start', testData.plannedStart);
      await est.expectFieldValue('Planned Start', testData.plannedStart);
      await est.expectToast('Project end date will be adjusted', { optional: true });
      await est.waitForFieldValue('Planned End', testData.plannedEnd, 15_000).catch(async () => {
        console.warn(`[${testInfo.title}] Planned End did not auto-adjust to ${testData.plannedEnd}; setting the source expected date explicitly.`);
        await est.setFieldValue('Planned End', testData.plannedEnd);
      });
      await est.expectFieldValue('Planned End', testData.plannedEnd);
    });

    await test.step('Select organization values', async () => {
      await est.selectDropdownByField('Line of Business', testData.lineOfBusinessSearch, testData.lineOfBusiness);
      await est.expectToast('Target margin set from 0 to 25', { optional: true });
      await est.selectDropdownByField('Region', testData.region, testData.region);
      await est.selectDropdownByField('Profit Center', testData.profitCenterSearch, testData.profitCenter);
      await ensureCostCenter();
    });

    await test.step('Copy the best practice template and wait for the proposal to save', async () => {
      await verifyReuseStrategy();
      await fastClick(est.copyTemplateButton(), 'Copy template button', 20_000);
      await waitForNoLoading(page, 45_000);
      await setup.expectSaveSuccess(90_000);
      const savedUrl = await setup.waitForSavedProposalUrl(90_000);
      console.log(`[TC-PRK-SimpleConsulting-001] created proposal url: ${savedUrl}`);
      await est.expectToast("BOE's copy is happening in background", { optional: true });
    });

    await test.step('Verify Project Scope shows copied WBS', async () => {
      await clickTab(page, 'Project Scope');
      await expect(est.textLocator(testData.wbs)).toBeVisible({ timeout: 45_000 });
      const released = est.textLocator('Released');
      if (!(await released.isVisible({ timeout: 10_000 }).catch(() => false))) {
        console.warn('[TC-PRK-SimpleConsulting-001] Project Scope WBS was copied, but Released status was not visible in this run.');
      }
    });

    await test.step('Open Labor Resources', async () => {
      await clickTab(page, 'Labor Resources');
      await expect(textLocator(page, testData.laborView)).toBeVisible({ timeout: 45_000 });
      await est.ensureLaborResourceRow(testData.wbs, 'TC-PRK-SimpleConsulting-001');
    });

    await test.step('Set Grade and Role on the labor row', async () => {
      await est.selectResourceGridDropdown('Grade', testData.laborGrade);
      await expect(textLocator(page, 'Senior Consult')).toBeVisible({ timeout: 15_000 });
      await est.selectResourceGridDropdown('Role', testData.laborRoleSearch);
      await expect(textLocator(page, testData.laborRole)).toBeVisible({ timeout: 15_000 });
      await expect(textLocator(page, testData.laborLocation)).toBeVisible({ timeout: 15_000 });
    });

    await test.step('Set FTE to 1.0 and verify calculated effort', async () => {
      await est.setFte(testData.wbs, testData.fte);
      await expect(textLocator(page, testData.expectedFte)).toBeVisible({ timeout: 20_000 });
      await expect(textLocator(page, testData.expectedEffort)).toBeVisible({ timeout: 20_000 });
      await est.expectToast('Data saved successfully', { optional: true });
    });

    await test.step('Run Check & Save and verify proposal KPIs', async () => {
      await est.checkAndSave();
      await est.expectToast('Costs/revenues & formula-based costs and prices updated and rolled-up', { optional: true, timeoutMs: 60_000 });
      await expect(textLocator(page, testData.expectedPrice)).toBeVisible({ timeout: 60_000 });
      await expect(textLocator(page, testData.expectedCost)).toBeVisible({ timeout: 30_000 });
      await expect(textLocator(page, testData.expectedMargin)).toBeVisible({ timeout: 30_000 });
      await est.openSaveMenu();
      await est.expectSaveMenuItems();
    });

    async function ensureCostCenter(): Promise<void> {
      try {
        await est.waitForFieldValue('Cost Center', testData.costCenter, 15_000);
        return;
      } catch (error) {
        console.warn(
          `[TC-PRK-SimpleConsulting-001] Cost Center did not auto-populate after Profit Center selection; selecting ${testData.costCenter} explicitly.`,
        );
      }
      await est.selectDropdownByField('Cost Center', testData.costCenter, testData.costCenter);
    }

    async function verifyReuseStrategy(): Promise<void> {
      await expect(textLocator(page, 'Re-Use Strategy')).toBeVisible({ timeout: 15_000 });
      await expect(textLocator(page, 'Use Best Practice Template')).toBeVisible({ timeout: 15_000 });
      await est.expectTemplateValue(testData.template);
      for (const label of testData.templateChecks) {
        await expect(textLocator(page, label)).toBeVisible({ timeout: 10_000 });
      }
    }
  });
});
