const { chromium } = require('playwright');

(async () => {
  const browser = await chromium.launch({
    headless: false
  });
  const context = await browser.newContext();
  await page.goto('https://ap1torut9.accounts.ondemand.com/oauth2/authorize?client_id=01ff4b6f-5501-4a1f-a6ff-6b8c05c1f215&response_type=code&redirect_uri=https%3A%2F%2Ftwenty5ipe-dev.authentication.us10.hana.ondemand.com%2Flogin%2Fcallback%2Fsap.custom&state=J35CYr2qXp&code_challenge=9QiLmK4XrGjD1HMjWAZO0MQOEYbCLFaz6T8qXrx9XKY&code_challenge_method=S256&scope=email+openid+profile&nonce=OO73wOQq--b8');
  await page.getByRole('button', { name: 'Continue' }).click();
  await page.getByRole('button', { name: 'Continue' }).click();
  await page.getByRole('button', { name: 'New' }).click();
  await page.getByRole('textbox', { name: 'Project Name *' }).click();
  await page.getByRole('textbox', { name: 'Project Name *' }).fill('Test Capital Project Copy');
  await page.getByRole('combobox', { name: 'Probable Project Start *' }).click();
  await page.getByRole('combobox', { name: 'Probable Project Start *' }).fill('6/29/2031');
  await page.getByRole('combobox', { name: 'Project End *' }).click();
  await page.getByRole('combobox', { name: 'Project End *' }).fill('6/28/2037');
  await page.locator('#fieldset-6181-table').click();
  await page.locator('#iBEBusUnitLocal-6207-trigger-picker').click();
  await page.getByText('SE- Sweden (Lund)').click();
  await page.getByRole('combobox', { name: 'Client/Customer (Sell-to) *' }).click();
  await page.getByRole('combobox', { name: 'Client/Customer (Sell-to) *' }).fill('usd');
  await page.getByText('Regression Test - Customer USD').click();
  await page.getByRole('radio', { name: 'Select a Prior Project' }).check();
  await page.locator('#iBESearchComboBox-6228-inputEl').click();
  await page.locator('#iBESearchComboBox-6228-inputEl').fill('()');
  await page.locator('#iBESearchComboBox-6228-inputEl').press('ArrowLeft');
  await page.locator('#iBESearchComboBox-6228-inputEl').fill('(AS)');
  await page.locator('#iBESearchComboBox-6228-inputEl').press('ArrowRight');
  await page.locator('#iBESearchComboBox-6228-inputEl').fill('(AS) Copy from template v5');
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
  await page1.locator('#gridview-2077-record-8102 > tbody > .x-grid-row > .x-grid-cell.x-grid-td.x-grid-cell-addRemoveDeleteColumn > .x-grid-cell-inner > .x-action-col-icon.x-action-col-0').click();
  await page1.locator('#gridview-2077-record-31574 > tbody > .x-grid-row > .x-grid-cell.x-grid-td.x-grid-cell-resourceGridFreeTextColumn > .x-grid-cell-inner').click();
  await page1.getByRole('row', { name: '4   ' }).getByLabel('', { exact: true }).fill('REG_CONSULTANT999999');
  await page1.getByRole('row', { name: '4   ' }).getByLabel('', { exact: true }).press('Enter');
  await page1.locator('#gridview-2077-record-31574 > tbody > .x-grid-row > .x-grid-cell.x-grid-td.x-grid-cell-resourceGridLaborPoolColumn > .x-grid-cell-inner').click();
  await page1.locator('#iBEComboBoxRemote-3034-trigger-picker').click();
  await page1.getByRole('option', { name: 'Project Manager' }).click();
  await page1.locator('#gridview-2079').click();
  await page1.locator('#button-1109').click();
  await page1.getByText('REG_CONSULTANT1 (row 1)').dblclick();
  page1.once('dialog', dialog => {
    console.log(`Dialog message: ${dialog.message()}`);
    dialog.dismiss().catch(() => {});
  });
  await page1.getByText('REG_CONSULTANT1 (row 1)').dblclick();
  await page1.close();
  await page.close();
  const page = await context.newPage();
  await page.goto('chrome://new-tab-page/');
  await page.close();

  // ---------------------
  await context.close();
  await browser.close();
})();