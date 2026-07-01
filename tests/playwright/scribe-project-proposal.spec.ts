import { test } from 'playwright/test';
import { connectToTwentyFive } from './support/twentyfive-cdp';
import { ProposalSetupPage } from './support/pom';

const SCRIBE_SOURCE =
  'https://scribehow.com/o/xogHL4gvSieB6fk9ThvLcw/viewer/Creating_A_New_Project_Proposal__66G4u6P8TTe0QOpWDd5HmQ';

const scribeTestData = {
  proposalType: 'Regression Test Only - Manufacturing',
  proposalTitle: 'Project proposal automation with timestamp',
  runtimeProposalTitle: `Project proposal automation ${new Date().toISOString().slice(0, 16).replace('T', ' ')}`,
  estimatedProjectStart: '6/27/2031',
  leadingCompany: 'Brazil',
  leadingDepartment: 'NL - Netherlands',
  clientCustomer: '19th Aug Customer (CUST091)',
};

test.describe('Scribe - Creating A New Project Proposal', () => {
  test('Create and save the project proposal from the Scribe steps', async ({}, testInfo) => {
    const session = await connectToTwentyFive();
    const setup = new ProposalSetupPage(session.page);

    testInfo.annotations.push({
      type: 'source',
      description: SCRIBE_SOURCE,
    });

    await test.step('Navigate to the quote app', async () => {
      await setup.navigateToProposals();
    });

    await test.step('Click "New"', async () => {
      await setup.startNewProposal();
    });

    await test.step('Set Proposal type as Regression Test Only - Manufacturing', async () => {
      await setup.selectProposalType(scribeTestData.proposalType);
      await setup.expectSetupFieldValue('Select the Best-fit Type', scribeTestData.proposalType);
    });

    await test.step('Set Proposal title with timestamp', async () => {
      await setup.setProposalTitle(scribeTestData.runtimeProposalTitle);
      await setup.expectDescription(scribeTestData.runtimeProposalTitle);
    });

    await test.step('Enter start date, company, leading site or department, and client/customer', async () => {
      await setup.fillManufacturingScribeFields({
        title: scribeTestData.runtimeProposalTitle,
        plannedStart: scribeTestData.estimatedProjectStart,
        company: scribeTestData.leadingCompany,
        leadingDepartment: scribeTestData.leadingDepartment,
        clientCustomer: scribeTestData.clientCustomer,
      });
    });

    await test.step('Verify Scribe setup field values are populated', async () => {
      await setup.expectDescription(scribeTestData.runtimeProposalTitle);
      await setup.expectSetupFieldValue('Estimated Project Start', scribeTestData.estimatedProjectStart);
      await setup.expectSetupFieldValue('Your Company', scribeTestData.leadingCompany);
      await setup.expectSetupFieldValue('Leading Site or Department', scribeTestData.leadingDepartment);
      await setup.expectSetupFieldValue('Client/Customer (Sell-to)', scribeTestData.clientCustomer);
    });

    await test.step('Click on Save button', async () => {
      await setup.saveViaToolbar();
      await setup.expectSaveSuccess();
      console.log(`[scribe-project-proposal] created proposal url: ${await setup.waitForSavedProposalUrl()}`);
    });
  });
});
