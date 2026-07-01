import { expect, type Locator, type Page } from 'playwright/test';
import { waitForAnyVisible, waitForNoLoading } from './twentyfive-cdp';
import { xpathLiteral } from './pom/xpath';

const DEFAULT_APP_URL = 'https://approuter-twenty5ipe-dev.cfapps.us10.hana.ondemand.com/#quote';
const TIMING_LOGS = process.env.PLAYWRIGHT_TIMING !== 'false';

export function appUrlForHash(hash: string): string {
  const appUrl = new URL(process.env.APP_URL ?? DEFAULT_APP_URL);
  return `${appUrl.origin}${appUrl.pathname}${appUrl.search}#${hash.replace(/^#/, '')}`;
}

export async function openAppHash(page: Page, hash: string, ready: Locator[], label: string): Promise<void> {
  const startedAt = Date.now();
  await page.goto(appUrlForHash(hash), { waitUntil: 'domcontentloaded', timeout: 45_000 }).catch((error) => {
    const message = error instanceof Error ? error.message : String(error);
    if (!message.includes('ERR_ABORTED')) throw error;
  });
  await waitForNoLoading(page, 30_000);
  await refreshOnceIfBlankPage(page, label);
  await waitForAnyVisible(page, ready, 45_000, label).catch(async (error) => {
    await refreshOnceIfBlankPage(page, label, { force: true });
    await waitForAnyVisible(page, ready, 45_000, label).catch(() => {
      throw error;
    });
  });
  await waitForNoLoading(page, 5_000);
  logTiming(`open ${label}`, startedAt);
}

export function textLocator(page: Page, text: string): Locator {
  return page.locator(`xpath=//*[contains(normalize-space(.), ${xpathLiteral(text)})]`).first();
}

export function tabLocator(page: Page, tab: string): Locator {
  return page
    .locator(`xpath=//*[@role='tab' and @aria-hidden='false']//*[normalize-space(.)=${xpathLiteral(tab)}]/ancestor::*[@role='tab'][1]`)
    .first();
}

export async function clickTab(page: Page, tab: string): Promise<void> {
  const locator = tabLocator(page, tab);
  await fastClick(locator, `${tab} tab`);
  await waitForNoLoading(page, 5_000);
}

export async function openEstimateFromActiveTab(page: Page, options: { label?: string } = {}): Promise<Page> {
  const label = options.label ?? 'estimate';
  const startedAt = Date.now();
  const activePanel = page.locator("xpath=//*[@role='tabpanel' and @aria-hidden='false']").first();
  const openLink = activePanel.getByRole('link', { name: /^Open$/ }).first();
  const createLink = activePanel.getByRole('link', { name: /^Create$/ }).first();
  const link = (await openLink.count()) > 0 && (await openLink.isVisible().catch(() => false)) ? openLink : createLink;

  await expect(link, `visible ${label} Open/Create link should be available`).toBeVisible({ timeout: 20_000 });
  await link.scrollIntoViewIfNeeded();
  await link.click({ timeout: 20_000 });

  const popupPromise = page.waitForEvent('popup', { timeout: 60_000 });
  const yesButton = page.getByRole('button', { name: /^Yes$/i }).first();
  const confirmAndOpen = page.getByRole('button', { name: /Confirm.*Open/i }).first();

  if ((await yesButton.count()) > 0 && (await yesButton.isVisible().catch(() => false))) {
    await yesButton.click({ timeout: 10_000 });
  } else if ((await confirmAndOpen.count()) > 0 && (await confirmAndOpen.isVisible().catch(() => false))) {
    await confirmAndOpen.click({ timeout: 10_000 });
  }

  const estimatePage = await popupPromise;
  await estimatePage.waitForLoadState('domcontentloaded', { timeout: 45_000 }).catch(() => undefined);
  await estimatePage.bringToFront().catch(() => undefined);
  await waitForNoLoading(estimatePage, 30_000);
  await refreshOnceIfBlankPage(estimatePage, label);
  logTiming(`open ${label} popup`, startedAt);
  return estimatePage;
}

export async function refreshOnceIfBlankPage(page: Page, label = 'page', options: { force?: boolean } = {}): Promise<void> {
  if (!options.force && !(await looksBlank(page))) return;

  console.warn(`[${label}] page looked blank or did not expose expected UI; refreshing once before continuing.`);
  await page.reload({ waitUntil: 'domcontentloaded', timeout: 45_000 }).catch((error) => {
    const message = error instanceof Error ? error.message : String(error);
    if (!message.includes('ERR_ABORTED')) throw error;
  });
  await waitForNoLoading(page, 30_000);
}

async function looksBlank(page: Page): Promise<boolean> {
  if (/^(about:blank|chrome-error:)/i.test(page.url())) return true;

  const bodyText = await page.locator('body').innerText({ timeout: 5_000 }).catch(() => '');
  if (bodyText.replace(/\s+/g, '').length > 0) return false;

  const visibleLandmark = page
    .locator("xpath=//*[@role='tab' or @role='button' or self::a or contains(@class,'x-panel') or contains(@class,'x-grid')]")
    .first();
  return !(await visibleLandmark.isVisible({ timeout: 2_000 }).catch(() => false));
}

export async function fastClick(locator: Locator, name: string, timeoutMs = 5_000): Promise<void> {
  const startedAt = Date.now();
  await expect(locator, `${name} should be visible before click`).toBeVisible({ timeout: timeoutMs });
  try {
    await locator.click({ timeout: timeoutMs, force: true, noWaitAfter: true });
  } catch (error) {
    await locator.evaluate((element) => {
      if (element instanceof HTMLElement) {
        element.click();
      } else {
        element.dispatchEvent(new MouseEvent('click', { bubbles: true, cancelable: true, view: window }));
      }
    });
  } finally {
    logTiming(`click ${name}`, startedAt);
  }
}

export async function clickAttached(locator: Locator, name: string, timeoutMs = 5_000): Promise<void> {
  const startedAt = Date.now();
  await locator.waitFor({ state: 'attached', timeout: timeoutMs });
  try {
    await locator.click({ timeout: timeoutMs, force: true, noWaitAfter: true });
  } catch (error) {
    await locator.evaluate((element) => {
      if (element instanceof HTMLElement) {
        element.click();
      } else {
        element.dispatchEvent(new MouseEvent('click', { bubbles: true, cancelable: true, view: window }));
      }
    });
  } finally {
    logTiming(`click ${name}`, startedAt);
  }
}

export async function expectVisibleTexts(page: Page, texts: string[], timeout = 5_000): Promise<void> {
  for (const text of texts) {
    await expect(textLocator(page, text), `Expected visible text: ${text}`).toBeVisible({ timeout });
  }
}

export function visibleTabTexts(page: Page): Promise<string[]> {
  return page
    .locator("xpath=//*[@role='tab' and @aria-hidden='false']//*[@data-ref='btnInnerEl' or contains(@class,'x-tab-inner')]")
    .allTextContents()
    .then((texts) => texts.map((text) => text.replace(/\s+/g, ' ').trim()).filter(Boolean));
}

export async function expectVisibleTabs(page: Page, expectedTabs: string[]): Promise<void> {
  const actual = (await visibleTabTexts(page)).map((value) => value.toLowerCase()).sort();
  for (const tab of expectedTabs) {
    expect(actual, `Expected tab '${tab}' among visible tabs: ${actual.join(', ')}`).toContain(tab.toLowerCase());
  }
}

export function logTiming(label: string, startedAt: number): void {
  if (!TIMING_LOGS) return;
  console.log(`[timing] ${label}: ${Date.now() - startedAt}ms`);
}

