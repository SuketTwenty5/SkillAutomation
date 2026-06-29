import { expect, type Page } from 'playwright/test';
import { test } from 'playwright/test';
import { connectToTwentyFive, waitForNoLoading } from './support/twentyfive-cdp';
import { fastClick } from './support/twentyfive-ui';

test.setTimeout(180_000);

const targetUrl = 'https://approuter-twenty5ipe-dev.cfapps.us10.hana.ondemand.com/#boe:4a6038ac-73b7-11f1-90d4-db53647973ef';

test('probe Labor add link on recorded estimate', async ({}, testInfo) => {
  const session = await connectToTwentyFive();
  const page = session.page;

  await page.goto(targetUrl, { waitUntil: 'domcontentloaded', timeout: 45_000 }).catch((error) => {
    const message = error instanceof Error ? error.message : String(error);
    if (!message.includes('ERR_ABORTED')) throw error;
  });
  await waitForNoLoading(page, 30_000);

  await clickLaborTab(page);
  const addLink = page.locator("xpath=//*[@data-grigaddlink='true']").first();
  await expect(addLink, 'Labor empty-grid add link should exist').toBeAttached({ timeout: 20_000 });
  const addLinkInfo = await addLink.evaluate((element) => ({
    tagName: element.tagName,
    className: element.getAttribute('class'),
    dataGridId: element.getAttribute('data-grid-id'),
    text: element.textContent?.replace(/\s+/g, ' ').trim(),
    hiddenByOffset: (element as HTMLElement).offsetParent === null,
  }));
  console.log(`[probe-labor-grid-addlink] add link info: ${JSON.stringify(addLinkInfo)}`);
  await addLink.evaluate((element) => {
    if (element instanceof HTMLElement) element.click();
    else element.dispatchEvent(new MouseEvent('click', { bubbles: true, cancelable: true, view: window }));
  });
  await waitForNoLoading(page, 15_000);

  await testInfo.attach('after-labor-addlink-click', {
    body: await page.screenshot({ fullPage: true }),
    contentType: 'image/png',
  });

  const snapshot = await page.locator('body').innerText({ timeout: 10_000 }).catch(() => '');
  console.log(`[probe-labor-grid-addlink] url: ${page.url()}`);
  console.log(`[probe-labor-grid-addlink] visible text after click:\n${snapshot.slice(0, 4000)}`);
});

async function clickLaborTab(page: Page): Promise<void> {
  const laborTab = page
    .locator(
      "xpath=//*[@role='tab' and not(ancestor-or-self::*[@aria-hidden='true'])]//*[normalize-space()='Labor']/ancestor::*[@role='tab'][1]",
    )
    .first();
  await fastClick(laborTab, 'Labor tab', 20_000);
  await waitForNoLoading(page, 20_000);
}
