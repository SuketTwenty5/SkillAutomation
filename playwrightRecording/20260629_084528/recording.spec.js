const { chromium } = require('playwright');

(async () => {
  const browser = await chromium.launch({
    headless: false
  });
  const context = await browser.newContext();
  await page.goto('https://ap1torut9.accounts.ondemand.com/oauth2/authorize?client_id=01ff4b6f-5501-4a1f-a6ff-6b8c05c1f215&response_type=code&redirect_uri=https%3A%2F%2Ftwenty5ipe-dev.authentication.us10.hana.ondemand.com%2Flogin%2Fcallback%2Fsap.custom&state=r3LTKZQ6NJ&code_challenge=AKG834-SpTOTDDSePwhRtfjv7T3DvtztODoZvFCU9nw&code_challenge_method=S256&scope=email+openid+profile&nonce=DvFRlycLE0xD');
  await page.locator('div').filter({ hasText: 'Email or User Name' }).nth(2).click();
  await page.getByRole('textbox', { name: 'Email or User Name' }).dblclick();
  await page.getByRole('textbox', { name: 'Email or User Name' }).click();
  await page.getByRole('textbox', { name: 'Email or User Name' }).click();
  await page.getByRole('textbox', { name: 'Email or User Name' }).fill('suket.suman@twenty5.com');
  await page.getByRole('button', { name: 'Continue' }).click();
  page.once('dialog', dialog => {
    console.log(`Dialog message: ${dialog.message()}`);
    dialog.dismiss().catch(() => {});
  });
  await page.getByRole('button', { name: 'Continue' }).click();
  await page.getByRole('button', { name: 'New' }).click();
  await page.getByRole('textbox', { name: 'Project Name *' }).click();
  await page.getByRole('textbox', { name: 'Project Name *' }).fill('Test eng proposal test');
  await page.locator('#iBEBusObjType-6183-trigger-picker').click();
  await page.getByRole('option', { name: 'Engineering/Services Proposal' }).click();
  await page.locator('#iBEDateTime-8072-trigger-picker').click();
  await page.getByRole('combobox', { name: 'Estimated Project Start *' }).click();
  await page.getByRole('combobox', { name: 'Estimated Project Start *' }).fill('6/29/2030');
  await page.locator('#fieldset-8061-table').click();
  await page.getByRole('combobox', { name: 'Project End *' }).click();
  await page.getByRole('combobox', { name: 'Project End *' }).fill('6/28/2035');
  await page.locator('#fieldset-8061-table').click();
  await page.locator('#iBEBusUnitLocal-8086-trigger-picker').click();
  await page.getByText('Poland').click();
  await page.locator('#iBEBusUnitLocal-8087-trigger-picker').click();
  await page.getByText('IT - Italy (Modena)').click();
  await page.locator('#ext-element-193').click();
  await page.locator('span').filter({ hasText: 'RFX Number' }).first().click();
  await page.locator('#iBECustomerComboRemote-8093-trigger-picker').click();
  await page.getByRole('combobox', { name: 'Client/Customer (Sell-to) *' }).fill('usd');
  await page.getByText('Regression Test - Customer USD').click();
  await page.getByRole('radio', { name: 'Select a prior project' }).check();
  await page.locator('#iBESearchComboBox-8108-inputEl').click();
  await page.locator('#iBESearchComboBox-8108-inputEl').fill('PRK test 03/09 2017');
  await page.locator('#ext-14320').getByText('| PRK test 03/09').click();
  await page.locator('#ext-14322').getByText('| PRK test 03/09').click();
  await page.getByRole('group', { name: 'Opportunity, Client & Your' }).click();
  await page.getByRole('button', { name: 'Copy' }).click();
  await page.getByRole('tab', { name: 'Cost Structure' }).click();
  await page.getByRole('button').filter({ hasText: /^$/ }).nth(1).click();
  await page.locator('.x-grid-tree-node-leaf > .x-grid-cell.x-grid-td.x-grid-cell-treecolumn-10623 > .x-grid-cell-inner').click();
  await page.locator('#iBESearchComboBox-12324-inputEl').fill('Eng');
  await page.getByText('Checkbox is checked. Press space to uncheckCheckbox is not checked. Press space').click();
  await page.locator('#button-1109').click();
  page.once('dialog', dialog => {
    console.log(`Dialog message: ${dialog.message()}`);
    dialog.dismiss().catch(() => {});
  });
  const page1Promise = page.waitForEvent('popup');
  await page.getByRole('link', { name: 'Open' }).click();
  const page1 = await page1Promise;
  await page1.getByText('No records found, click here').click();
  await page1.locator('.x-grid-cell.x-grid-td.x-grid-cell-gridcolumn-1172 > .x-grid-cell-inner').click();
  await page1.locator('.x-grid-cell.x-grid-td.x-grid-cell-gridcolumn-1174 > .x-grid-cell-inner').click();
  await page1.locator('#ext-element-17 > .x-grid-cell-inner').click();
  await page1.locator('.x-grid-cell.x-grid-td.x-grid-cell-gridcolumn-1175 > .x-grid-cell-inner').click();
  await page1.locator('.x-grid-cell.x-grid-td.x-grid-cell-iBEComboColumn-1177 > .x-grid-cell-inner').click();
  await page1.locator('.x-grid-cell.x-grid-td.x-grid-cell-gridcolumn-1178 > .x-grid-cell-inner').click();
  await page1.locator('#ext-element-20 > .x-grid-cell-inner').click();
  await page1.locator('#iBEComboBox-2998-trigger-picker').click();
  await page1.locator('#gridview-1170').click();
  await page1.getByRole('tab', { name: 'Risk & Contingency' }).click();
  await page1.getByText('No records found, click here').click();
  await page1.locator('.x-grid-cell.x-grid-td.x-grid-cell-riskGridColorColumn > .x-grid-cell-inner').first().click();
  await page1.getByRole('tab', { name: 'Work Packages' }).click();
  await page1.getByRole('tab', { name: 'Workflow' }).click();
  page1.once('dialog', dialog => {
    console.log(`Dialog message: ${dialog.message()}`);
    dialog.dismiss().catch(() => {});
  });
  await page1.locator('#button-1109').click();
  await page1.close();
  await page.close();
  const page = await context.newPage();
  await page.goto('chrome://new-tab-page/');
  await page.close();

  // ---------------------
  await context.close();
  await browser.close();
})();