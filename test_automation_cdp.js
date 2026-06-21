/**
 * CDP runner for TC-MD-PS-001 (Master Data - Products & Services).
 * Attaches Playwright to an already-running, logged-in Chrome on port 9222
 * so the consultant's authenticated SSO session is reused.
 */

const { chromium } = require('playwright');
const fs = require('fs');
const path = require('path');

const CDP_ENDPOINT = process.env.CDP_ENDPOINT || 'http://127.0.0.1:9222';
const BASE_URL = 'https://app-twenty5ipe-lm-dev.cfapps.us10.hana.ondemand.com/';
const SCREENSHOT_DIR = './test-screenshots';
const REPORT_FILE = './test-report.json';

const testReport = {
  timestamp: new Date().toISOString(),
  baseUrl: BASE_URL,
  cdpEndpoint: CDP_ENDPOINT,
  results: [],
  summary: { totalTests: 0, passed: 0, failed: 0, skipped: 0 }
};

function setupScreenshotDirectory() {
  if (!fs.existsSync(SCREENSHOT_DIR)) fs.mkdirSync(SCREENSHOT_DIR, { recursive: true });
}

async function takeScreenshot(page, testName, stepName) {
  const fileName = `${testName}-${stepName.replace(/\s+/g, '_')}-${Date.now()}.png`;
  const filePath = path.join(SCREENSHOT_DIR, fileName);
  try { await page.screenshot({ path: filePath }); } catch (e) { return null; }
  return filePath;
}

function logTestResult(testId, testName, stepName, passed, message, screenshot = null) {
  testReport.results.push({ testId, testName, stepName, passed, message, timestamp: new Date().toISOString(), screenshot });
  testReport.summary.totalTests++;
  passed ? testReport.summary.passed++ : testReport.summary.failed++;
  console.log(`[${passed ? '✓ PASS' : '✗ FAIL'}] ${testId} - ${stepName}: ${message}`);
}

// Hover an element and wait up to `timeoutMs` for `revealSel` to become visible.
// If it doesn't appear, click outside to reset, then hover again once.
async function hoverToReveal(page, target, revealSel, timeoutMs = 5000) {
  const revealVisible = async () => {
    const loc = page.locator(revealSel).first();
    return (await loc.count()) > 0 && await loc.isVisible().catch(() => false);
  };

  await target.hover().catch(() => {});
  try {
    await page.locator(revealSel).first().waitFor({ state: 'visible', timeout: timeoutMs });
    return true;
  } catch (e) { /* not revealed on first hover */ }

  if (await revealVisible()) return true;

  // Click outside to dismiss any stuck state, then hover again.
  await page.mouse.click(5, 5).catch(() => {});
  await page.waitForTimeout(500);
  await target.hover().catch(() => {});
  try {
    await page.locator(revealSel).first().waitFor({ state: 'visible', timeout: timeoutMs });
    return true;
  } catch (e) { /* still not revealed */ }
  return revealVisible();
}

async function firstVisible(page, selectors) {
  for (const selector of selectors) {
    try {
      const loc = page.locator(selector).first();
      if (await loc.count() > 0 && await loc.isVisible()) return loc;
    } catch (e) { /* try next */ }
  }
  return null;
}

// TC1: Master Data tab + sub-tabs
async function testCase1(page) {
  const testId = 'TC-MD-PS-001.1', testName = 'Navigate to Master Data Tab';
  console.log(`\n▶ ${testId}: ${testName}`);
  await page.waitForLoadState('networkidle').catch(() => {});
  let shot = await takeScreenshot(page, testId, 'initial-load');
  logTestResult(testId, testName, 'Page loaded', true, `URL: ${page.url()}`, shot);

  const md = await firstVisible(page, [
    'text=Master Data', '[aria-label*="Master Data"]',
    'button:has-text("Master Data")', 'a:has-text("Master Data")'
  ]);
  if (!md) {
    logTestResult(testId, testName, 'Master Data Tab Found', false, 'Could not locate Master Data tab', await takeScreenshot(page, testId, 'md-not-found'));
    return false;
  }
  await md.click();
  await page.waitForTimeout(1500);
  logTestResult(testId, testName, 'Master Data Tab Clicked', true, 'Clicked Master Data', await takeScreenshot(page, testId, 'md-clicked'));

  const sub = await firstVisible(page, ['text=Products & Services', 'text=Products / Services', '[role="menuitem"]']);
  logTestResult(testId, testName, 'Sub-tabs Visible', !!sub, sub ? 'Sub-tabs displayed' : 'Sub-tabs not found', await takeScreenshot(page, testId, 'subtabs'));
  return !!sub;
}

// TC2: Products & Services page
async function testCase2(page) {
  const testId = 'TC-MD-PS-001.2', testName = 'Navigate to Products & Services Page';
  console.log(`\n▶ ${testId}: ${testName}`);
  const ps = await firstVisible(page, ['text=Products & Services', 'text=Products / Services', 'a:has-text("Products & Services")']);
  if (!ps) {
    logTestResult(testId, testName, 'Products & Services Click', false, 'Option not found', await takeScreenshot(page, testId, 'ps-not-found'));
    return false;
  }
  await ps.click();
  await page.waitForTimeout(2500);
  const shot = await takeScreenshot(page, testId, 'products-page');
  logTestResult(testId, testName, 'Page Navigated', true, 'Navigated to Products & Services', shot);
  // This is a hash-routed SPA; the URL legitimately does not contain
  // "product"/"service". Verify we reached the list page by its header instead.
  const header = page.getByText(/Products\s*[\/&]\s*Services/i).first();
  const onPage = (await header.count()) > 0 && await header.isVisible().catch(() => false);
  logTestResult(testId, testName, 'Products/Services Page Loaded', onPage, `Header present: ${onPage} | URL: ${page.url()}`, shot);
  return true;
}

// TC3: New button options
async function testCase3(page) {
  const testId = 'TC-MD-PS-001.3', testName = 'Verify New Button Options';
  console.log(`\n▶ ${testId}: ${testName}`);
  const newBtn = await firstVisible(page, ['button:has-text("New")', '[aria-label*="New"]', 'text=New']);
  if (!newBtn) {
    logTestResult(testId, testName, 'New Button Found', false, 'New button not found', await takeScreenshot(page, testId, 'new-not-found'));
    return false;
  }
  // Hover (not click) to reveal the dropdown; wait 5s, retry with click-outside if needed.
  const revealed = await hoverToReveal(page, newBtn, 'text=Create from Type', 5000);
  logTestResult(testId, testName, 'New Button Hover Menu', revealed, revealed ? 'Dropdown revealed on hover' : 'Dropdown did not appear after hover + retry', await takeScreenshot(page, testId, 'new-opened'));

  const expected = ['Create from Template', 'Create from Type', 'Create Template', 'Edit Template'];
  const found = [];
  for (const opt of expected) {
    const loc = page.locator(`text=${opt}`).first();
    if (await loc.count() > 0 && await loc.isVisible().catch(() => false)) found.push(opt);
  }
  const ok = found.length === expected.length;
  logTestResult(testId, testName, 'Dropdown Options Verified', ok, `Found ${found.length}/${expected.length}: ${found.join(', ')}`, await takeScreenshot(page, testId, 'dropdown'));
  return ok;
}

// TC4: Create from Type -> Purchased Part submenu
async function testCase4(page) {
  const testId = 'TC-MD-PS-001.4', testName = 'Verify Create from Type Submenu';
  console.log(`\n▶ ${testId}: ${testName}`);
  const cft = await firstVisible(page, ['text=Create from Type']);
  if (!cft) {
    logTestResult(testId, testName, 'Create from Type Found', false, 'Option not found', await takeScreenshot(page, testId, 'cft-not-found'));
    return false;
  }
  // Hover "Create from Type" to reveal the "Purchased Part" submenu (5s wait + retry).
  const ppFound = await hoverToReveal(page, cft, 'text=Purchased Part', 5000);
  logTestResult(testId, testName, 'Purchased Part Submenu', ppFound, ppFound ? 'Purchased Part visible on hover' : 'Purchased Part not found after hover + retry', await takeScreenshot(page, testId, 'submenu'));
  return ppFound;
}

// TC5: Create Purchased Part + sub-tabs
async function testCase5(page) {
  const testId = 'TC-MD-PS-001.5', testName = 'Create Purchased Part and Verify Sub-tabs';
  console.log(`\n▶ ${testId}: ${testName}`);
  // Re-open the hover chain: New -> Create from Type -> Purchased Part, since the
  // menu closes between test cases. Hover (not click) with the same 5s + retry rule.
  const newBtn = await firstVisible(page, ['button:has-text("New")', '[aria-label*="New"]', 'text=New']);
  if (!newBtn || !(await hoverToReveal(page, newBtn, 'text=Create from Type', 5000))) {
    logTestResult(testId, testName, 'Open New Menu', false, 'New hover menu did not appear', await takeScreenshot(page, testId, 'new-menu-fail'));
    return false;
  }
  const cft = page.locator('text=Create from Type').first();
  await cft.hover().catch(() => {});
  await page.waitForTimeout(5000);

  // "Purchased Part" text also exists in the data grid; click the one in the
  // popup submenu (not inside any table/grid/row).
  const ppMatches = page.locator('text=Purchased Part');
  let ppMenuItem = null;
  for (let i = 0; i < await ppMatches.count(); i++) {
    const el = ppMatches.nth(i);
    if (!(await el.isVisible().catch(() => false))) continue;
    const inGrid = await el.evaluate(e => !!e.closest('table, [role=grid], [role=row], [role=gridcell]')).catch(() => false);
    if (!inGrid) { ppMenuItem = el; break; }
  }
  if (!ppMenuItem) {
    logTestResult(testId, testName, 'Open Create from Type Submenu', false, 'Purchased Part submenu item not found', await takeScreenshot(page, testId, 'submenu-fail'));
    return false;
  }
  await ppMenuItem.click();
  await page.waitForTimeout(3000);
  logTestResult(testId, testName, 'Create Page Loaded', true, `URL: ${page.url()}`, await takeScreenshot(page, testId, 'create-page'));

  const required = ['Key Info', 'Product Management', 'Planning & Estimating', 'Cost & Prices', 'Supplier Sources', 'Comments & Files', 'Texts'];
  const found = [];
  for (const t of required) {
    const loc = page.locator(`text=${t}`).first();
    if ((await loc.count()) > 0 && await loc.isVisible().catch(() => false)) found.push(t);
  }
  const ok = found.length === required.length;
  logTestResult(testId, testName, 'Sub-tabs Verified', ok, `Found ${found.length}/${required.length}: ${found.join(', ')}`, await takeScreenshot(page, testId, 'subtabs'));
  return ok;
}

async function run() {
  console.log('========================================');
  console.log('TC-MD-PS-001  (CDP attach to ' + CDP_ENDPOINT + ')');
  console.log('========================================');
  setupScreenshotDirectory();

  const browser = await chromium.connectOverCDP(CDP_ENDPOINT);
  const contexts = browser.contexts();
  const context = contexts[0];
  if (!context) { console.error('No browser context found over CDP.'); process.exit(1); }
  const pages = context.pages();
  let page = pages.find(p => p.url().includes('hana.ondemand.com')) || pages[0];
  if (!page) page = await context.newPage();
  await page.bringToFront().catch(() => {});

  // Always reset to the app home for a deterministic starting state
  // (session is already authenticated via the persistent profile).
  await page.goto(BASE_URL, { waitUntil: 'networkidle' }).catch(() => {});
  await page.waitForTimeout(1500);

  try {
    await testCase1(page);
    await testCase2(page);
    await testCase3(page);
    await testCase4(page);
    await testCase5(page);
  } finally {
    const s = testReport.summary;
    console.log('\n========================================');
    console.log('Test Summary');
    console.log('========================================');
    console.log(`Total: ${s.totalTests}  Passed: ${s.passed}  Failed: ${s.failed}`);
    console.log(`Pass Rate: ${s.totalTests ? ((s.passed / s.totalTests) * 100).toFixed(2) : 0}%`);
    fs.writeFileSync(REPORT_FILE, JSON.stringify(testReport, null, 2));
    console.log(`Report: ${REPORT_FILE}  Screenshots: ${SCREENSHOT_DIR}`);
    // Detach without closing the consultant's browser.
    await browser.close().catch(() => {});
  }
}

run().catch(err => { console.error('Fatal:', err); process.exit(1); });
