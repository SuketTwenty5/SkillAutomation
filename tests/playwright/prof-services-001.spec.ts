import { test } from 'playwright/test';
import { connectToTwentyFive } from './support/twentyfive-cdp';
import { ProposalSetupPage, proposalSetupFields } from './support/pom';

test.describe('TC-Prof-Services-001 Playwright migration', () => {
  test('Verify Proposal Setup Layout', async ({}, testInfo) => {
    const session = await connectToTwentyFive();
    const setup = new ProposalSetupPage(session.page);

    testInfo.annotations.push({
      type: 'source',
      description: 'Converted from imported/twentyfive-regtest/tests/src/test/resources/features/testfolder/setup_professional_services_proposal.feature @TC-001',
    });

    await test.step('Navigate to Proposals', async () => {
      await setup.navigateToProposals();
    });

    await test.step('Click New to start creating a proposal', async () => {
      await setup.startNewProposal();
    });

    await test.step('Select Regression Test Only - Consulting proposal type', async () => {
      await setup.selectProposalType('Regression Test Only - Consulting');
    });

    await test.step('Verify Proposal top menu tabs', async () => {
      await setup.expectTopTabs(['Setup', 'Phases', 'Workstreams', 'Billing Items', 'Schedule', 'My Team', 'Workflow', 'Analysis', 'Rates']);
    });

    await test.step('Verify Proposal Setup fields', async () => {
      for (const field of proposalSetupFields) {
        await setup.verifyField(field);
      }
    });

    await test.step('Verify setup links and buttons', async () => {
      await setup.expectVisible(setup.openProjectInSapLink, 'Open Project in SAP link');
      await setup.expectVisible(setup.uploadRfxFilesButton, 'Upload RFx Files button');
      await setup.expectVisible(setup.editRatesLink, 'Edit Rates link');
    });

    await test.step('Verify Project Goals or Remarks rich text editor', async () => {
      await setup.expectVisible(setup.projectGoalsTextbox, 'Project Goals or Remarks textbox');
      await setup.clickProjectGoalsTextbox();
      await setup.expectVisible(setup.textEditToolbox, 'Text edit toolbox');
    });
  });
});
