const {
  connectToConsultantChrome,
  goToMasterData,
  masterDataSubtabsVisible,
  goToProductsServices,
  productsServicesPageLoaded,
  revealNewMenu,
  visibleNewMenuOptions,
  revealCreateFromTypeMenu,
  clickPurchasedPartFromMenu,
  visiblePurchasedPartSubtabs,
} = require('./actions');
const { TestReporter } = require('./reporter');
const { selectedTests } = require('./config');

async function tc1(page, reporter) {
  const testId = 'TC-MD-PS-001.1';
  const testName = 'Navigate to Master Data Tab';
  console.log(`\nRunning ${testId}: ${testName}`);

  await reporter.record(page, testId, testName, 'Page loaded', true, `URL: ${page.url()}`, 'initial-load');

  const clicked = await goToMasterData(page);
  if (!(await reporter.record(page, testId, testName, 'Master Data Tab Clicked', clicked, clicked ? 'Clicked Master Data' : 'Could not locate Master Data tab', clicked ? 'md-clicked' : 'md-not-found'))) {
    return false;
  }

  const subtabs = await masterDataSubtabsVisible(page);
  await reporter.record(page, testId, testName, 'Sub-tabs Visible', subtabs, subtabs ? 'Sub-tabs displayed' : 'Sub-tabs not found', 'subtabs');
  return subtabs;
}

async function tc2(page, reporter) {
  const testId = 'TC-MD-PS-001.2';
  const testName = 'Navigate to Products & Services Page';
  console.log(`\nRunning ${testId}: ${testName}`);

  const clicked = await goToProductsServices(page);
  if (!(await reporter.record(page, testId, testName, 'Products & Services Click', clicked, clicked ? 'Clicked Products & Services' : 'Option not found', clicked ? 'products-clicked' : 'ps-not-found'))) {
    return false;
  }

  const loaded = await productsServicesPageLoaded(page);
  await reporter.record(page, testId, testName, 'Products/Services Page Loaded', loaded, `Header present: ${loaded} | URL: ${page.url()}`, 'products-page');
  return loaded;
}

async function tc3(page, reporter) {
  const testId = 'TC-MD-PS-001.3';
  const testName = 'Verify New Button Options';
  console.log(`\nRunning ${testId}: ${testName}`);

  const revealed = await revealNewMenu(page);
  if (!(await reporter.record(page, testId, testName, 'New Button Hover Menu', revealed, revealed ? 'Dropdown revealed on hover' : 'Dropdown did not appear after hover + retry', revealed ? 'new-opened' : 'new-not-found'))) {
    return false;
  }

  const { expected, found } = await visibleNewMenuOptions(page);
  const ok = found.length === expected.length;
  await reporter.record(page, testId, testName, 'Dropdown Options Verified', ok, `Found ${found.length}/${expected.length}: ${found.join(', ')}`, 'dropdown');
  return ok;
}

async function tc4(page, reporter) {
  const testId = 'TC-MD-PS-001.4';
  const testName = 'Verify Create from Type Submenu';
  console.log(`\nRunning ${testId}: ${testName}`);

  const newMenu = await revealNewMenu(page);
  if (!newMenu) {
    await reporter.record(page, testId, testName, 'New Button Hover Menu', false, 'New menu did not appear before Create from Type hover', 'new-menu-fail');
    return false;
  }

  const revealed = await revealCreateFromTypeMenu(page);
  await reporter.record(page, testId, testName, 'Purchased Part Submenu', revealed, revealed ? 'Purchased Part visible on hover' : 'Purchased Part not found after hover + retry', 'submenu');
  return revealed;
}

async function tc5(page, reporter) {
  const testId = 'TC-MD-PS-001.5';
  const testName = 'Create Purchased Part and Verify Sub-tabs';
  console.log(`\nRunning ${testId}: ${testName}`);

  const clicked = await clickPurchasedPartFromMenu(page);
  if (clicked) await page.waitForTimeout(3000);
  if (!(await reporter.record(page, testId, testName, 'Purchased Part Clicked', clicked, clicked ? `URL: ${page.url()}` : 'Purchased Part submenu item not found', clicked ? 'create-page' : 'submenu-fail'))) {
    return false;
  }

  const { expected, found } = await visiblePurchasedPartSubtabs(page);
  const ok = found.length === expected.length;
  await reporter.record(page, testId, testName, 'Sub-tabs Verified', ok, `Found ${found.length}/${expected.length}: ${found.join(', ')}`, 'subtabs');
  return ok;
}

const TESTS = {
  TC1: tc1,
  TC2: tc2,
  TC3: tc3,
  TC4: tc4,
  TC5: tc5,
};

async function runMasterDataScenario(config) {
  const tests = selectedTests(config);
  const reporter = new TestReporter(config);
  reporter.setup();

  console.log('========================================');
  console.log('TC-MD-PS-001 Master Data Products & Services');
  console.log(`CDP: ${config.cdpEndpoint}`);
  console.log(`Base URL: ${config.baseUrl}`);
  console.log(`Selected tests: ${tests.join(', ')}`);
  console.log('========================================');

  let browser;
  let page;
  try {
    const session = await connectToConsultantChrome(config);
    browser = session.browser;
    page = session.page;

    for (const testId of tests) {
      await TESTS[testId](page, reporter);
    }
  } finally {
    reporter.write();
    reporter.printSummary();
    if (browser) await browser.close().catch(() => {});
  }

  return reporter.report.summary.failed === 0 ? 0 : 1;
}

module.exports = {
  TESTS,
  runMasterDataScenario,
};
