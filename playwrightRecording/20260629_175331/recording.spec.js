const { chromium } = require('playwright');

(async () => {
  const browser = await chromium.launch({
    headless: false
  });
  const context = await browser.newContext();
  const page = await context.newPage();
  await page.goto('https://ap1torut9.accounts.ondemand.com/oauth2/authorize?client_id=01ff4b6f-5501-4a1f-a6ff-6b8c05c1f215&response_type=code&redirect_uri=https%3A%2F%2Ftwenty5ipe-dev.authentication.us10.hana.ondemand.com%2Flogin%2Fcallback%2Fsap.custom&state=ySm9fsEp1a&code_challenge=EvfJe6YnUlMRcnwYUYkm9gU34vNEResdWISLl_BGY_Y&code_challenge_method=S256&scope=email+openid+profile&nonce=lTS5Jt5qh9Rh');
  await page.getByRole('button', { name: 'Continue' }).click();
  await page.getByRole('textbox', { name: 'Password' }).click();
  await page.getByRole('textbox', { name: 'Password' }).fill(process.env.IPE_PASSWORD || '');
  await page.getByRole('button', { name: 'Continue' }).click();
  await page.getByRole('button', { name: 'New' }).click();
  await page.getByRole('textbox', { name: 'Project Name *' }).click();
  await page.getByRole('textbox', { name: 'Project Name *' }).fill('regression test capital');
  await page.getByRole('combobox', { name: 'Probable Project Start *' }).click();
  await page.getByRole('combobox', { name: 'Probable Project Start *' }).click();
  await page.getByRole('combobox', { name: 'Probable Project Start *' }).fill('6/29/2029');
  await page.locator('#fieldset-8891-table').click();
  await page.getByRole('combobox', { name: 'Project End *' }).click();
  await page.getByRole('combobox', { name: 'Project End *' }).fill('6/29/2037');
  await page.locator('#fieldset-8891-table').click();
  await page.locator('#iBEBusUnitLocal-8917-trigger-picker').click();
  await page.getByText('IT - Italy (Modena)').click();
  await page.locator('#iBECustomerComboRemote-8923-trigger-picker').click();
  await page.getByRole('combobox', { name: 'Client/Customer (Sell-to) *' }).fill('USD');
  await page.getByText('Regression Test - Customer USD').click();
  await page.getByRole('radio', { name: 'Select a Prior Project' }).check();
  await page.locator('#iBESearchComboBox-8938-inputEl').click();
  await page.locator('#iBESearchComboBox-8938-inputEl').fill('()');
  await page.locator('#iBESearchComboBox-8938-inputEl').press('ArrowLeft');
  await page.locator('#iBESearchComboBox-8938-inputEl').fill('()AS');
  await page.locator('#iBESearchComboBox-8938-inputEl').press('ArrowLeft');
  await page.locator('#iBESearchComboBox-8938-inputEl').press('ArrowLeft');
  await page.locator('#iBESearchComboBox-8938-inputEl').press('ArrowRight');
  await page.locator('#iBESearchComboBox-8938-inputEl').fill('(AS');
  await page.locator('#iBESearchComboBox-8938-inputEl').press('ArrowRight');
  await page.locator('#iBESearchComboBox-8938-inputEl').press('ArrowRight');
  await page.locator('#iBESearchComboBox-8938-inputEl').fill('(AS) Copy');
  await page.getByText('| (AS) Copy from Template v2').click();
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
  await page1.getByText('No records found - click or').click();
  await page1.locator('.x-grid-cell.x-grid-td.x-grid-cell-resourceGridLaborPoolColumn > .x-grid-cell-inner').first().click();
  await page1.locator('#iBEComboBoxRemote-3031-trigger-picker').click();
  await page1.getByRole('option', { name: 'Supplier Engineer' }).click();
  await page1.getByRole('option', { name: 'Supplier Engineer' }).click();
  await page1.getByRole('gridcell', { name: 'FTE' }).getByLabel('', { exact: true }).fill('2');
  await page1.getByRole('gridcell', { name: 'FTE' }).getByLabel('', { exact: true }).press('Enter');
  await page1.close();
  await page.close();
  const page2 = await context.newPage();
  await page2.goto('chrome://new-tab-page/');
  await page2.close();

  // ---------------------
  await context.close();
  await browser.close();
})();
