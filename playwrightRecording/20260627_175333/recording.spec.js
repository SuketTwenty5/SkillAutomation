const { chromium } = require('playwright');

(async () => {
  const browser = await chromium.launch({
    headless: false
  });
  const context = await browser.newContext();
  await page.goto('https://ap1torut9.accounts.ondemand.com/oauth2/authorize?client_id=01ff4b6f-5501-4a1f-a6ff-6b8c05c1f215&response_type=code&redirect_uri=https%3A%2F%2Ftwenty5ipe-dev.authentication.us10.hana.ondemand.com%2Flogin%2Fcallback%2Fsap.custom&state=OvFYB79hbb&code_challenge=Q4aMpo4XD5zJmRvIGEZXP6UgwQmf2ZSPkPVznX83Gx8&code_challenge_method=S256&scope=email+openid+profile&nonce=wLLu36rey9Xh');
  await page.close();

  // ---------------------
  await context.close();
  await browser.close();
})();