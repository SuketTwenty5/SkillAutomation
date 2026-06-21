const { chromium } = require('playwright');

const selectors = {
  masterData: [
    'text=Master Data',
    '[aria-label*="Master Data"]',
    'button:has-text("Master Data")',
    'a:has-text("Master Data")',
  ],
  productsServices: [
    'text=Products & Services',
    'text=Products / Services',
    'a:has-text("Products & Services")',
    'button:has-text("Products & Services")',
  ],
  newButton: [
    'button:has-text("New")',
    '[aria-label*="New"]',
    'text=New',
  ],
  createFromType: 'text=Create from Type',
  purchasedPart: 'text=Purchased Part',
  gridAncestor: 'table, [role=grid], [role=row], [role=gridcell]',
};

const purchasedPartSubtabs = [
  'Key Info',
  'Product Management',
  'Planning & Estimating',
  'Cost & Prices',
  'Supplier Sources',
  'Comments & Files',
  'Texts',
];

async function connectToConsultantChrome({ cdpEndpoint, baseUrl }) {
  const browser = await chromium.connectOverCDP(cdpEndpoint);
  const context = browser.contexts()[0];
  if (!context) throw new Error('No browser context found over CDP.');

  let page = context.pages().find((candidate) => candidate.url().includes('hana.ondemand.com'));
  if (!page) page = context.pages()[0];
  if (!page) page = await context.newPage();

  await page.bringToFront().catch(() => {});
  await resetToBaseUrl(page, baseUrl);
  return { browser, context, page };
}

async function resetToBaseUrl(page, baseUrl) {
  await page.goto(baseUrl, { waitUntil: 'networkidle' }).catch(async () => {
    await page.goto(baseUrl, { waitUntil: 'domcontentloaded' }).catch(() => {});
  });
  await page.waitForTimeout(1500);
}

async function firstVisible(page, candidates) {
  for (const selector of candidates) {
    const locator = page.locator(selector).first();
    if ((await locator.count()) > 0 && (await locator.isVisible().catch(() => false))) {
      return locator;
    }
  }
  return null;
}

async function isVisible(page, selector) {
  const locator = page.locator(selector).first();
  return (await locator.count()) > 0 && (await locator.isVisible().catch(() => false));
}

async function hoverToReveal(page, target, revealSelector, timeoutMs = 5000) {
  await target.hover().catch(() => {});
  try {
    await page.locator(revealSelector).first().waitFor({ state: 'visible', timeout: timeoutMs });
    return true;
  } catch (error) {
    // Retry once after dismissing stale hover/menu state.
  }

  if (await isVisible(page, revealSelector)) return true;

  await page.mouse.click(5, 5).catch(() => {});
  await page.waitForTimeout(500);
  await target.hover().catch(() => {});
  try {
    await page.locator(revealSelector).first().waitFor({ state: 'visible', timeout: timeoutMs });
    return true;
  } catch (error) {
    return isVisible(page, revealSelector);
  }
}

async function clickVisibleTextOutsideGrid(page, text) {
  const matches = page.locator(`text=${text}`);
  const count = await matches.count();
  for (let index = 0; index < count; index += 1) {
    const candidate = matches.nth(index);
    if (!(await candidate.isVisible().catch(() => false))) continue;
    const inGrid = await candidate
      .evaluate((element, gridSelector) => Boolean(element.closest(gridSelector)), selectors.gridAncestor)
      .catch(() => false);
    if (!inGrid) {
      await candidate.click();
      return true;
    }
  }
  return false;
}

async function goToMasterData(page) {
  const tab = await firstVisible(page, selectors.masterData);
  if (!tab) return false;
  await tab.click();
  await page.waitForTimeout(1500);
  return true;
}

async function masterDataSubtabsVisible(page) {
  return Boolean(await firstVisible(page, selectors.productsServices.concat(['[role="menuitem"]'])));
}

async function goToProductsServices(page) {
  const option = await firstVisible(page, selectors.productsServices);
  if (!option) return false;
  await option.click();
  await page.waitForTimeout(2500);
  return true;
}

async function productsServicesPageLoaded(page) {
  const header = page.getByText(/Products\s*[\/&]\s*Services/i).first();
  return (await header.count()) > 0 && (await header.isVisible().catch(() => false));
}

async function revealNewMenu(page) {
  const newButton = await firstVisible(page, selectors.newButton);
  if (!newButton) return false;
  return hoverToReveal(page, newButton, selectors.createFromType, 5000);
}

async function visibleNewMenuOptions(page) {
  const expected = ['Create from Template', 'Create from Type', 'Create Template', 'Edit Template'];
  const found = [];
  for (const option of expected) {
    if (await isVisible(page, `text=${option}`)) found.push(option);
  }
  return { expected, found };
}

async function revealCreateFromTypeMenu(page) {
  const createFromType = await firstVisible(page, [selectors.createFromType]);
  if (!createFromType) return false;
  return hoverToReveal(page, createFromType, selectors.purchasedPart, 5000);
}

async function clickPurchasedPartFromMenu(page) {
  if (!(await revealNewMenu(page))) return false;
  if (!(await revealCreateFromTypeMenu(page))) return false;
  return clickVisibleTextOutsideGrid(page, 'Purchased Part');
}

async function visiblePurchasedPartSubtabs(page) {
  const found = [];
  for (const tab of purchasedPartSubtabs) {
    if (await isVisible(page, `text=${tab}`)) found.push(tab);
  }
  return { expected: purchasedPartSubtabs, found };
}

module.exports = {
  selectors,
  purchasedPartSubtabs,
  connectToConsultantChrome,
  resetToBaseUrl,
  firstVisible,
  hoverToReveal,
  clickVisibleTextOutsideGrid,
  goToMasterData,
  masterDataSubtabsVisible,
  goToProductsServices,
  productsServicesPageLoaded,
  revealNewMenu,
  visibleNewMenuOptions,
  revealCreateFromTypeMenu,
  clickPurchasedPartFromMenu,
  visiblePurchasedPartSubtabs,
};
