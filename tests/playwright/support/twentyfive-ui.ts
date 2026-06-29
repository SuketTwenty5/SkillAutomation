import { expect, type Locator, type Page } from 'playwright/test';
import { waitForAnyVisible, waitForNoLoading } from './twentyfive-cdp';

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
  await waitForAnyVisible(page, ready, 45_000, label);
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

function xpathLiteral(value: string): string {
  if (!value.includes("'")) return `'${value}'`;
  if (!value.includes('"')) return `"${value}"`;
  return `concat(${value
    .split("'")
    .map((part) => `'${part}'`)
    .join(', "\'", ')})`;
}
