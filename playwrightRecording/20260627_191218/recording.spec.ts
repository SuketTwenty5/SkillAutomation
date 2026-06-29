import { test, expect } from '@playwright/test';

test('test', async ({ page }) => {
  await page.goto('https://ap1torut9.accounts.ondemand.com/oauth2/authorize?client_id=01ff4b6f-5501-4a1f-a6ff-6b8c05c1f215&response_type=code&redirect_uri=https%3A%2F%2Ftwenty5ipe-dev.authentication.us10.hana.ondemand.com%2Flogin%2Fcallback%2Fsap.custom&state=JVhFuUJyKM&code_challenge=PWwbSsEIqxy9lksctJ5yEanm5hDgUp0e41L_gqt-4xo&code_challenge_method=S256&scope=email+openid+profile&nonce=ekXkbyRYNQ1m');
  await page.getByRole('textbox', { name: 'Email or User Name' }).click();
  await page.getByRole('textbox', { name: 'Email or User Name' }).click();
  await page.getByRole('textbox', { name: 'Email or User Name' }).fill('suket.suman@twenty5.com');
  await page.getByRole('button', { name: 'Continue' }).click();
  await page.getByRole('button', { name: 'Continue' }).click();
  await page.getByRole('button', { name: 'New' }).click();
  await page.locator('#iBEBusObjType-6041-trigger-picker').click();
  page.once('dialog', dialog => {
    console.log(`Dialog message: ${dialog.message()}`);
    dialog.dismiss().catch(() => {});
  });
  await page.getByRole('option', { name: 'Engineering/Services Proposal' }).click();
});