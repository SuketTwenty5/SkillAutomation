import { test } from 'playwright/test';
import { connectToTwentyFive } from './support/twentyfive-cdp';
import { ProposalSetupPage } from './support/proposal-setup-page';

const recordingData = {
  source: 'playwrightRecording/20260627_191218/recording.spec.ts',
  loginUser: 'suket.suman@twenty5.com',
  proposalType: 'Engineering/Services Proposal',
};

test.describe('Recording 20260627_191218', () => {
  test('Start a new Engineering/Services proposal from recorded login flow', async ({}, testInfo) => {
    testInfo.annotations.push({
      type: 'source',
      description: recordingData.source,
    });

    // Reuses the already-signed-in managed Chrome session (CDP) instead of
    // re-driving the SAP IAS OAuth login captured in the raw codegen.
    const session = await connectToTwentyFive();
    const page = session.page;
    const setup = new ProposalSetupPage(page);

    await test.step('Open Proposals', async () => {
      await setup.navigateToProposals();
    });

    await test.step('Click New to start a proposal', async () => {
      await setup.startNewProposal();
    });

    await test.step(`Select proposal type "${recordingData.proposalType}"`, async () => {
      await setup.dismissVisibleDialog();
      await setup.selectProposalType(recordingData.proposalType);
    });

    await test.step('Confirm Setup page loaded', async () => {
      await setup.waitForSetupPage();
    });
  });
});
