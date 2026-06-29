const { chromium } = require('playwright');

(async () => {
  const browser = await chromium.launch({
    headless: false
  });
  const context = await browser.newContext();
  await page.goto('https://ap1torut9.accounts.ondemand.com/oauth2/authorize?client_id=01ff4b6f-5501-4a1f-a6ff-6b8c05c1f215&response_type=code&redirect_uri=https%3A%2F%2Ftwenty5ipe-dev.authentication.us10.hana.ondemand.com%2Flogin%2Fcallback%2Fsap.custom&state=iJJPMJBAy4&code_challenge=tqBITJOotQVbIKScOww9mnImJneZzMOjR7KOa4TCLsI&code_challenge_method=S256&scope=email+openid+profile&nonce=jcq0GAo0_juA');
  await page.getByRole('button', { name: 'Continue' }).click();
  await page.getByRole('button', { name: 'Continue' }).click();
  await page.getByRole('button', { name: 'New' }).click();
  await page.getByRole('combobox', { name: 'Probable Project Start *' }).click();
  await page.getByRole('combobox', { name: 'Probable Project Start *' }).fill('6/29/2031');
  await page.locator('#fieldset-8870-table').click();
  await page.getByRole('combobox', { name: 'Project End *' }).fill('6/28/2037');
  await page.locator('#fieldset-8870-table').click();
  await page.getByRole('textbox', { name: 'Project Name *' }).click();
  await page.getByRole('textbox', { name: 'Project Name *' }).fill('Test capital Copy');
  await page.locator('#fieldset-8870-table').click();
  await page.locator('#iBEBusUnitLocal-8896-trigger-picker').click();
  await page.getByText('SE- Sweden (Lund)').click();
  await page.locator('#fieldset-8894-table').click();
  await page.getByRole('combobox', { name: 'Client/Customer (Sell-to) *' }).click();
  await page.getByRole('combobox', { name: 'Client/Customer (Sell-to) *' }).fill('usd');
  await page.getByText('RTC1').click();
  await page.getByRole('radio', { name: 'Select a Prior Project' }).check();
  await page.locator('#iBESearchComboBox-8917-inputEl').click();
  await page.locator('#iBESearchComboBox-8917-inputEl').fill('()');
  await page.locator('#iBESearchComboBox-8917-inputEl').press('ArrowLeft');
  await page.locator('#iBESearchComboBox-8917-inputEl').fill('(AS)');
  await page.locator('#iBESearchComboBox-8917-inputEl').press('ArrowRight');
  await page.locator('#iBESearchComboBox-8917-inputEl').fill('(AS) Copy from template');
  await page.getByText('| (AS) Copy from Template v5').click();
  await page.getByRole('button', { name: 'Copy' }).click();
  await page.getByRole('tab', { name: 'Estimates' }).click();
  await page.getByRole('link', { name: 'Open' }).click();
  page.once('dialog', dialog => {
    console.log(`Dialog message: ${dialog.message()}`);
    dialog.dismiss().catch(() => {});
  });
  const page1Promise = page.waitForEvent('popup');
  await page.getByRole('button', { name: 'Yes' }).click();
  const page1 = await page1Promise;
  await page1.getByRole('tab', { name: 'Labor' }).click();
  await page1.locator('#gridview-2077-record-8173 > tbody > .x-grid-row > .x-grid-cell.x-grid-td.x-grid-cell-addRemoveDeleteColumn > .x-grid-cell-inner > .x-action-col-icon.x-action-col-0').click();
  await page1.locator('#gridview-2077-record-31573 > tbody > .x-grid-row > .x-grid-cell.x-grid-td.x-grid-cell-resourceGridLaborPoolColumn > .x-grid-cell-inner').click();
  await page1.locator('#iBEComboBoxRemote-3032-trigger-picker').click();
  await page1.getByRole('option', { name: 'Project Manager' }).click();
  await page1.locator('#gridview-2077').click();
  await page1.locator('#gridview-2079-record-31573').getByText('FTE').click();
  await page1.getByRole('row', { name: '6/29/2031  6/28/2037  Flat' }).getByLabel('', { exact: true }).fill('2');
  await page1.locator('#gridview-2079').click();
  await page1.getByText('$ 125.00/hr').click();
  await page1.locator('#button-1109').click();
  await page1.locator('#gridview-2077-record-8173 > tbody > .x-grid-row > .x-grid-cell.x-grid-td.x-grid-cell-addRemoveDeleteColumn > .x-grid-cell-inner > .x-action-col-icon.x-action-col-1').click();
  await page1.getByRole('button').filter({ hasText: /^$/ }).nth(5).click();
  await page1.getByRole('button').filter({ hasText: /^$/ }).nth(2).click();
  await page1.locator('#button-1109').click();
  await page1.locator('#container-1859-innerCt').getByText('Update Cost').click();
  page1.once('dialog', dialog => {
    console.log(`Dialog message: ${dialog.message()}`);
    dialog.dismiss().catch(() => {});
  });
  await page1.locator('#container-3775-innerCt').getByText('USD 3.28M').click();
  await page1.close();
  await page.close();
  const page = await context.newPage();
  await page.goto('chrome://new-tab-page/');
  await page.close();

  // ---------------------
  await context.close();
  await browser.close();
})();