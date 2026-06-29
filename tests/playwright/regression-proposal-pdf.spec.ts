import { test } from 'playwright/test';
import { connectToTwentyFive } from './support/twentyfive-cdp';
import { ProposalSetupPage } from './support/proposal-setup-page';

const PDF_SOURCE = '/Users/suketsuman/Downloads/HowToCreateANewProjectProposalInTwentyfive-a71946f5-534b-4bdf-bf92-c.pdf';

const pdfTestData = {
  proposalTitle: 'PRK regression test 2',
  runtimeProposalTitle: `PRK regression test 2 ${new Date().toISOString().slice(0, 16).replace('T', ' ')}`,
  bestFitType: 'Regression Test Only - Manufacturing',
  proposalType: 'Engineering/Services Proposal',
  estimatedProjectStart: 'June 30, 2026',
  estimatedProjectStartInput: '6/30/2026',
  projectEnd: 'September 27, 2028',
  projectEndInput: '9/27/2028',
  leadingCompany: 'Regression Test',
  leadingDepartment: 'US - New York',
  profitCenter: 'Products',
  clientCustomer: '82nd Airborne Division',
  projectPlanTemplate: '(AS) Regression - Copy from template',
  phase: 'Test Phase 1',
  laborResources: ['REG_CONSULTANT2', 'REG_CONSULTANT3', 'REG_CONSULTANT1'],
  estimateViews: ['PRK', 'Pro Serv - Planning View (shared & preferred)'],
};

test.describe('PDF - How To Create A New Project Proposal In Twentyfive', () => {
  test('Create and save the project proposal setup from the PDF steps', async ({}, testInfo) => {
    const session = await connectToTwentyFive();
    const setup = new ProposalSetupPage(session.page);

    testInfo.annotations.push({
      type: 'source',
      description: PDF_SOURCE,
    });

    await test.step('Click "PROPOSALS" in top navigation bar', async () => {
      await setup.navigateToProposals();
    });

    await test.step('Click "New" to create a new proposal', async () => {
      await setup.startNewProposal();
    });

    await test.step('Type "PRK regression test 2" into "Title or Brief Description" field', async () => {
      await setup.setProposalTitle(pdfTestData.runtimeProposalTitle);
      await setup.expectDescription(pdfTestData.runtimeProposalTitle);
    });

    await test.step('Select "Regression Test Only - Manufacturing" from "Best-fit Type" dropdown', async () => {
      await setup.selectProposalType(pdfTestData.bestFitType);
      await setup.expectSetupFieldValue('Select the Best-fit Type', pdfTestData.bestFitType);
    });

    await test.step('Select "Engineering/Services Proposal" from "Project Type" when the field is present', async () => {
      const selected = await setup.selectProjectTypeIfVisible(pdfTestData.proposalType);
      console.log(`[pdf-project-proposal] project type selection attempted: ${selected}`);
    });

    await test.step('Open "SAP Project" dropdown and verify options are displayed', async () => {
      await setup.openSapProjectDropdownAndClose();
    });

    await test.step('Set "Estimated Project Start" to "June 30, 2026"', async () => {
      await setup.setEstimatedProjectStart(pdfTestData.estimatedProjectStartInput);
      await setup.expectSetupFieldValue('Estimated Project Start', pdfTestData.estimatedProjectStartInput);
    });

    await test.step('Set "Project End" to "September 27, 2028"', async () => {
      await setup.setProjectEnd(pdfTestData.projectEndInput);
      await setup.expectSetupFieldValue('Project End', pdfTestData.projectEndInput);
    });

    await test.step('Set organizational details from the PDF', async () => {
      await setup.fillProjectProposalPdfSetupFields({
        title: pdfTestData.runtimeProposalTitle,
        company: pdfTestData.leadingCompany,
        leadingDepartment: pdfTestData.leadingDepartment,
        profitCenter: pdfTestData.profitCenter,
        clientCustomer: pdfTestData.clientCustomer,
      });
    });

    await test.step('Verify all PDF setup field values are populated', async () => {
      await setup.expectDescription(pdfTestData.runtimeProposalTitle);
      await setup.expectSetupFieldValue('Estimated Project Start', pdfTestData.estimatedProjectStartInput);
      await setup.expectSetupFieldValue('Project End', pdfTestData.projectEndInput);
      await setup.expectSetupFieldValue('Your Company', pdfTestData.leadingCompany);
      await setup.expectSetupFieldValue('Leading Site or Department', pdfTestData.leadingDepartment);
      await setup.expectSetupFieldValue('Profit Center', pdfTestData.profitCenter);
      await setup.expectSetupFieldValue('Client/Customer (Sell-to)', pdfTestData.clientCustomer);
    });

    await test.step('Save the new regression proposal', async () => {
      await setup.saveViaToolbar();
      await setup.expectSaveSuccess();
      console.log(`[pdf-project-proposal] created proposal url: ${await setup.waitForSavedProposalUrl()}`);
    });
  });
});
