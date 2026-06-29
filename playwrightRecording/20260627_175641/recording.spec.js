const { chromium } = require('playwright');

(async () => {
  const browser = await chromium.launch({
    headless: false
  });
  const context = await browser.newContext();
  await page.goto('https://ap1torut9.accounts.ondemand.com/oauth2/authorize?client_id=01ff4b6f-5501-4a1f-a6ff-6b8c05c1f215&response_type=code&redirect_uri=https%3A%2F%2Ftwenty5ipe-dev.authentication.us10.hana.ondemand.com%2Flogin%2Fcallback%2Fsap.custom&state=Fqn3pAkELV&code_challenge=HIXCMX-2EKdSsuZXq3xlsoY5g34UOBoaycwtYlNi-ng&code_challenge_method=S256&scope=email+openid+profile&nonce=WrCyePZtq8rM');
  await page.getByRole('button', { name: 'Continue' }).click();
  await page.getByRole('button', { name: 'Continue' }).click();
  await page.getByRole('button', { name: 'New' }).click();
  await page.locator('#iBEBusObjType-8815-trigger-picker').click();
  await page.getByRole('option', { name: 'Engineering/Services Proposal' }).click();
  await page.getByRole('textbox', { name: 'Proposal Title *' }).click();
  await page.getByRole('textbox', { name: 'Proposal Title *' }).fill('test proposal');
  await page.getByRole('combobox', { name: 'Estimated Project Start *' }).click();
  await page.getByRole('combobox', { name: 'Estimated Project Start *' }).fill('6/27/2031');
  await page.getByRole('group', { name: 'Opportunity, Client & Your' }).click();
  await page.locator('#iBEBusUnitLocal-10718-trigger-picker').click();
  await page.getByText('Australia').click();
  await page.locator('#iBEBusUnitLocal-10719-trigger-picker').click();
  await page.getByText('AI SOLUTIONS').click();
  await page.getByRole('combobox', { name: 'Client/Customer (Sell-to) *' }).click();
  await page.getByRole('combobox', { name: 'Client/Customer (Sell-to) *' }).fill('usd');
  await page.getByText('Regression Test - Customer USD').click();
  await page.locator('#button-1109').click();
  await page.getByRole('tab', { name: 'Sets/Phases' }).click();
  await page.getByText('No records found, click here').click();
  await page.locator('.x-grid-cell.x-grid-td.x-grid-cell-phaseTabDescription > .x-grid-cell-inner').click();
  await page.getByRole('tabpanel', { name: 'Sets/Phases' }).getByLabel('', { exact: true }).fill('test');
  await page.locator('#gridview-13008').click();
  await page.locator('#button-1109').click();
  await page.getByText('Create', { exact: true }).click();
  await page.getByText('Create', { exact: true }).click();
  await page.getByRole('tab', { name: 'Cost Structure' }).click();
  await page.getByRole('tab', { name: 'Setup' }).click();
  await page.locator('#iBESearchComboBox-12859-trigger-picker').click();
  await page.locator('#iBESearchComboBox-12859-inputEl').fill('regression');
  await page.locator('#ext-20838').getByText('RG01 (Regression Test Only -').click();
  await page.getByText('2025-4837 | (AS) Regression').click();
  await page.getByRole('button', { name: 'Copy' }).click();
  await page.locator('span').filter({ hasText: 'Leading Site or Department *' }).first().click();
  await page.getByRole('tab', { name: 'Cost Structure' }).click();
  page.once('dialog', dialog => {
    console.log(`Dialog message: ${dialog.message()}`);
    dialog.dismiss().catch(() => {});
  });
  const page1Promise = page.waitForEvent('popup');
  await page.getByRole('link', { name: 'Open' }).click();
  const page1 = await page1Promise;
  await page1.getByRole('tab', { name: 'Labor' }).click();
  await page1.locator('#gridview-1595-record-29430 > tbody > .x-grid-row > .x-grid-cell.x-grid-td.x-grid-cell-addRemoveDeleteColumn > .x-grid-cell-inner > .x-action-col-icon.x-action-col-0').click();
  await page1.locator('#gridview-1595-record-31531 > tbody > .x-grid-row > .x-grid-cell.x-grid-td.x-grid-cell-resourceGridFreeTextColumn > .x-grid-cell-inner').click();
  await page1.getByRole('row', { name: '4   ' }).getByLabel('', { exact: true }).fill('consultant 4');
  await page1.locator('#gridview-1595').click();
  await page1.locator('.x-grid-cell.x-grid-td.x-grid-cell-resourceGridLaborPoolColumn.x-grid-item-focused > .x-grid-cell-inner').click();
  await page1.locator('#iBEComboBoxRemote-3085-trigger-picker').click();
  await page1.getByRole('option', { name: 'Junior Consultant' }).click();
  await page1.getByRole('option', { name: 'Junior Consultant' }).click();
  await page1.getByRole('row', { name: '/1/2031 12/30/2031 Flat (working days per month) 0.000 FTE Fixed effort $ 75.00' }).getByLabel('', { exact: true }).fill('2000');
  page1.once('dialog', dialog => {
    console.log(`Dialog message: ${dialog.message()}`);
    dialog.dismiss().catch(() => {});
  });
  await page1.getByRole('row', { name: '/1/2031 12/30/2031 Flat (working days per month) 0.000 FTE Fixed effort $ 75.00' }).getByLabel('', { exact: true }).press('Enter');
  await page1.close();
  await page.close();
  const page = await context.newPage();
  await page.goto('chrome://new-tab-page/');
  await page.close();

  // ---------------------
  await context.close();
  await browser.close();
})();