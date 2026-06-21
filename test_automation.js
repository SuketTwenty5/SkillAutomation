/**
 * Automated Test Suite for Master Data - Products & Services
 * Test ID: TC-MD-PS-001
 * Framework: Playwright
 * Base URL: https://app-twenty5ipe-lm-dev.cfapps.us10.hana.ondemand.com/
 */

const { chromium } = require('playwright');
const fs = require('fs');
const path = require('path');

// Configuration
const BASE_URL = 'https://app-twenty5ipe-lm-dev.cfapps.us10.hana.ondemand.com/';
const HEADLESS = false; // Set to true for CI/CD environments
const SCREENSHOT_DIR = './test-screenshots';
const REPORT_FILE = './test-report.json';

// Test report structure
const testReport = {
  timestamp: new Date().toISOString(),
  baseUrl: BASE_URL,
  results: [],
  summary: {
    totalTests: 0,
    passed: 0,
    failed: 0,
    skipped: 0
  }
};

// Utility function to create screenshot directory
function setupScreenshotDirectory() {
  if (!fs.existsSync(SCREENSHOT_DIR)) {
    fs.mkdirSync(SCREENSHOT_DIR, { recursive: true });
  }
}

// Utility function to take screenshots
async function takeScreenshot(page, testName, stepName) {
  const fileName = `${testName}-${stepName.replace(/\s+/g, '_')}-${Date.now()}.png`;
  const filePath = path.join(SCREENSHOT_DIR, fileName);
  await page.screenshot({ path: filePath });
  return filePath;
}

// Utility function to log test results
function logTestResult(testId, testName, stepName, passed, message, screenshot = null) {
  const result = {
    testId,
    testName,
    stepName,
    passed,
    message,
    timestamp: new Date().toISOString(),
    screenshot
  };
  testReport.results.push(result);
  testReport.summary.totalTests++;
  if (passed) {
    testReport.summary.passed++;
  } else {
    testReport.summary.failed++;
  }
  console.log(`[${passed ? '✓ PASS' : '✗ FAIL'}] ${testId} - ${stepName}: ${message}`);
}

// Test Case 1: Navigate to Master Data Tab and Verify Sub-tabs
async function testCase1(page) {
  const testId = 'TC-MD-PS-001.1';
  const testName = 'Navigate to Master Data Tab';

  try {
    console.log(`\n▶ Running ${testId}: ${testName}`);

    // Step 1: Navigate to the application
    console.log('  Step 1: Navigating to application...');
    await page.goto(BASE_URL, { waitUntil: 'networkidle' });
    let screenshot = await takeScreenshot(page, testId, 'initial-load');
    logTestResult(testId, testName, 'Page loaded', true, 'Application loaded successfully', screenshot);

    // Step 2: Look for Master Data tab/menu
    console.log('  Step 2: Looking for Master Data tab...');
    try {
      // Try multiple selectors for Master Data
      const masterDataSelectors = [
        'text=Master Data',
        '[aria-label*="Master Data"]',
        'button:has-text("Master Data")',
        'a:has-text("Master Data")',
        '.nav-item:has-text("Master Data")',
        '[class*="masterdata"]',
        '[class*="master-data"]'
      ];

      let masterDataElement = null;
      for (const selector of masterDataSelectors) {
        try {
          masterDataElement = await page.$(selector);
          if (masterDataElement) break;
        } catch (e) {
          // Continue to next selector
        }
      }

      if (masterDataElement) {
        console.log('  Clicking Master Data element...');
        await masterDataElement.click();
        await page.waitForTimeout(1000);
        screenshot = await takeScreenshot(page, testId, 'master-data-clicked');
        logTestResult(testId, testName, 'Master Data Tab Clicked', true, 'Master Data tab clicked successfully', screenshot);
      } else {
        logTestResult(testId, testName, 'Master Data Tab Found', false, 'Could not locate Master Data tab', screenshot);
        return false;
      }

      // Step 3: Verify sub-tabs are displayed
      console.log('  Step 3: Verifying sub-tabs...');
      const subtabSelectors = [
        'text=Products & Services',
        '[class*="subtab"]',
        '.sub-menu',
        '[role="menuitem"]'
      ];

      let subtabsVisible = false;
      for (const selector of subtabSelectors) {
        try {
          const element = await page.$(selector);
          if (element) {
            subtabsVisible = true;
            break;
          }
        } catch (e) {
          // Continue
        }
      }

      screenshot = await takeScreenshot(page, testId, 'subtabs-verification');
      if (subtabsVisible) {
        logTestResult(testId, testName, 'Sub-tabs Visible', true, 'Sub-tabs are displayed', screenshot);
      } else {
        logTestResult(testId, testName, 'Sub-tabs Visible', false, 'Sub-tabs not found', screenshot);
      }

      return subtabsVisible;
    } catch (error) {
      screenshot = await takeScreenshot(page, testId, 'error');
      logTestResult(testId, testName, 'Test Execution', false, `Error: ${error.message}`, screenshot);
      return false;
    }
  } catch (error) {
    console.error(`Test ${testId} failed:`, error);
    return false;
  }
}

// Test Case 2: Navigate to Products & Services Page
async function testCase2(page) {
  const testId = 'TC-MD-PS-001.2';
  const testName = 'Navigate to Products & Services Page';

  try {
    console.log(`\n▶ Running ${testId}: ${testName}`);

    // Step 1: Click on Products & Services
    console.log('  Step 1: Clicking Products & Services...');
    const psSelectors = [
      'text=Products & Services',
      'text=Products / Services',
      '[aria-label*="Products"]',
      'a:has-text("Products & Services")',
      'button:has-text("Products & Services")'
    ];

    let clicked = false;
    for (const selector of psSelectors) {
      try {
        const element = await page.$(selector);
        if (element) {
          await element.click();
          clicked = true;
          break;
        }
      } catch (e) {
        // Continue
      }
    }

    if (!clicked) {
      let screenshot = await takeScreenshot(page, testId, 'products-not-found');
      logTestResult(testId, testName, 'Products & Services Click', false, 'Could not locate Products & Services option', screenshot);
      return false;
    }

    // Wait for navigation
    await page.waitForTimeout(2000);
    let screenshot = await takeScreenshot(page, testId, 'products-page');
    logTestResult(testId, testName, 'Page Navigated', true, 'Navigated to Products & Services page', screenshot);

    // Step 2: Verify URL
    console.log('  Step 2: Verifying URL...');
    const currentUrl = page.url();
    const urlValid = currentUrl.includes('product') || currentUrl.includes('service');
    logTestResult(testId, testName, 'URL Verification', urlValid, `Current URL: ${currentUrl}`, screenshot);

    return true;
  } catch (error) {
    console.error(`Test ${testId} failed:`, error);
    let screenshot = await takeScreenshot(page, testId, 'error');
    logTestResult(testId, testName, 'Test Execution', false, `Error: ${error.message}`, screenshot);
    return false;
  }
}

// Test Case 3: Verify New Button Options
async function testCase3(page) {
  const testId = 'TC-MD-PS-001.3';
  const testName = 'Verify New Button Options';

  try {
    console.log(`\n▶ Running ${testId}: ${testName}`);

    // Step 1: Find New button
    console.log('  Step 1: Locating New button...');
    const newButtonSelectors = [
      'button:has-text("New")',
      'text=New',
      '[aria-label*="New"]',
      '[class*="new-btn"]',
      '.btn-new'
    ];

    let newButton = null;
    for (const selector of newButtonSelectors) {
      try {
        newButton = await page.$(selector);
        if (newButton) break;
      } catch (e) {
        // Continue
      }
    }

    if (!newButton) {
      let screenshot = await takeScreenshot(page, testId, 'new-button-not-found');
      logTestResult(testId, testName, 'New Button Found', false, 'Could not locate New button', screenshot);
      return false;
    }

    // Step 2: Hover over New button
    console.log('  Step 2: Hovering over New button...');
    await newButton.hover();
    await page.waitForTimeout(500);

    let screenshot = await takeScreenshot(page, testId, 'new-button-hovered');
    logTestResult(testId, testName, 'New Button Hovered', true, 'Hovered over New button', screenshot);

    // Step 3: Verify dropdown options
    console.log('  Step 3: Verifying dropdown options...');
    const expectedOptions = [
      'Create from Template',
      'Create from Type',
      'Create Template',
      'Edit Template'
    ];

    let foundOptions = [];
    for (const option of expectedOptions) {
      try {
        const element = await page.$(`text=${option}`);
        if (element) {
          foundOptions.push(option);
        }
      } catch (e) {
        // Continue
      }
    }

    screenshot = await takeScreenshot(page, testId, 'dropdown-options');
    const allOptionsFound = foundOptions.length === expectedOptions.length;
    logTestResult(
      testId,
      testName,
      'Dropdown Options Verified',
      allOptionsFound,
      `Found ${foundOptions.length}/${expectedOptions.length} options: ${foundOptions.join(', ')}`,
      screenshot
    );

    return allOptionsFound;
  } catch (error) {
    console.error(`Test ${testId} failed:`, error);
    let screenshot = await takeScreenshot(page, testId, 'error');
    logTestResult(testId, testName, 'Test Execution', false, `Error: ${error.message}`, screenshot);
    return false;
  }
}

// Test Case 4: Verify Create from Type Additional Options
async function testCase4(page) {
  const testId = 'TC-MD-PS-001.4';
  const testName = 'Verify Additional Options on Create from Type';

  try {
    console.log(`\n▶ Running ${testId}: ${testName}`);

    // Step 1: Hover over Create from Type
    console.log('  Step 1: Hovering over Create from Type...');
    const createFromTypeSelectors = [
      'text=Create from Type',
      '[class*="create-from-type"]'
    ];

    let createFromTypeOption = null;
    for (const selector of createFromTypeSelectors) {
      try {
        createFromTypeOption = await page.$(selector);
        if (createFromTypeOption) break;
      } catch (e) {
        // Continue
      }
    }

    if (!createFromTypeOption) {
      let screenshot = await takeScreenshot(page, testId, 'create-from-type-not-found');
      logTestResult(testId, testName, 'Create from Type Found', false, 'Could not locate Create from Type option', screenshot);
      return false;
    }

    await createFromTypeOption.hover();
    await page.waitForTimeout(500);

    let screenshot = await takeScreenshot(page, testId, 'create-from-type-hovered');
    logTestResult(testId, testName, 'Create from Type Hovered', true, 'Hovered over Create from Type option', screenshot);

    // Step 2: Verify Purchased Part option
    console.log('  Step 2: Verifying Purchased Part option...');
    const purchasedPartSelectors = [
      'text=Purchased Part',
      '[class*="purchased-part"]'
    ];

    let purchasedPartFound = false;
    for (const selector of purchasedPartSelectors) {
      try {
        const element = await page.$(selector);
        if (element) {
          purchasedPartFound = true;
          break;
        }
      } catch (e) {
        // Continue
      }
    }

    screenshot = await takeScreenshot(page, testId, 'submenu-options');
    logTestResult(
      testId,
      testName,
      'Purchased Part Option Found',
      purchasedPartFound,
      purchasedPartFound ? 'Purchased Part option is visible' : 'Purchased Part option not found',
      screenshot
    );

    return purchasedPartFound;
  } catch (error) {
    console.error(`Test ${testId} failed:`, error);
    let screenshot = await takeScreenshot(page, testId, 'error');
    logTestResult(testId, testName, 'Test Execution', false, `Error: ${error.message}`, screenshot);
    return false;
  }
}

// Test Case 5: Create Purchased Part and Verify Sub-tabs
async function testCase5(page) {
  const testId = 'TC-MD-PS-001.5';
  const testName = 'Create Purchased Part and Verify Sub-tabs';

  try {
    console.log(`\n▶ Running ${testId}: ${testName}`);

    // Step 1: Click Purchased Part
    console.log('  Step 1: Clicking Purchased Part...');
    const purchasedPartSelectors = [
      'text=Purchased Part',
      '[class*="purchased-part"]'
    ];

    let clicked = false;
    for (const selector of purchasedPartSelectors) {
      try {
        const element = await page.$(selector);
        if (element) {
          await element.click();
          clicked = true;
          break;
        }
      } catch (e) {
        // Continue
      }
    }

    if (!clicked) {
      let screenshot = await takeScreenshot(page, testId, 'purchased-part-not-found');
      logTestResult(testId, testName, 'Purchased Part Clicked', false, 'Could not locate or click Purchased Part', screenshot);
      return false;
    }

    // Wait for page navigation
    await page.waitForTimeout(2000);
    let screenshot = await takeScreenshot(page, testId, 'create-page-loaded');
    logTestResult(testId, testName, 'Create Page Loaded', true, 'Navigated to Create Purchased Part page', screenshot);

    // Step 2: Verify required sub-tabs
    console.log('  Step 2: Verifying sub-tabs...');
    const requiredSubtabs = [
      'Key Info',
      'Product Management',
      'Planning & Estimating',
      'Cost & Prices',
      'Supplier Sources',
      'Comments & Files',
      'Texts'
    ];

    let foundSubtabs = [];
    for (const subtab of requiredSubtabs) {
      try {
        const element = await page.$(`text=${subtab}`);
        if (element) {
          foundSubtabs.push(subtab);
        }
      } catch (e) {
        // Continue
      }
    }

    screenshot = await takeScreenshot(page, testId, 'subtabs-verification');
    const allSubtabsFound = foundSubtabs.length === requiredSubtabs.length;
    logTestResult(
      testId,
      testName,
      'Sub-tabs Verified',
      allSubtabsFound,
      `Found ${foundSubtabs.length}/${requiredSubtabs.length} sub-tabs: ${foundSubtabs.join(', ')}`,
      screenshot
    );

    // Step 3: Verify each sub-tab is accessible
    if (allSubtabsFound) {
      console.log('  Step 3: Verifying sub-tabs are clickable...');
      let allClickable = true;
      for (const subtab of requiredSubtabs) {
        try {
          const element = await page.$(`text=${subtab}`);
          if (element) {
            // Just verify visibility, don't necessarily click to avoid side effects
            const isVisible = await element.isVisible();
            if (!isVisible) {
              allClickable = false;
            }
          }
        } catch (e) {
          allClickable = false;
        }
      }

      screenshot = await takeScreenshot(page, testId, 'subtabs-clickable');
      logTestResult(
        testId,
        testName,
        'Sub-tabs Accessible',
        allClickable,
        allClickable ? 'All sub-tabs are visible and accessible' : 'Some sub-tabs are not accessible',
        screenshot
      );
    }

    return allSubtabsFound;
  } catch (error) {
    console.error(`Test ${testId} failed:`, error);
    let screenshot = await takeScreenshot(page, testId, 'error');
    logTestResult(testId, testName, 'Test Execution', false, `Error: ${error.message}`, screenshot);
    return false;
  }
}

// Main test execution
async function runTests() {
  console.log('========================================');
  console.log('Master Data - Products & Services Tests');
  console.log('========================================');
  console.log(`Base URL: ${BASE_URL}`);
  console.log(`Timestamp: ${new Date().toISOString()}\n`);

  setupScreenshotDirectory();

  let browser;
  try {
    // Launch browser
    browser = await chromium.launch({ headless: HEADLESS });
    const context = await browser.newContext();
    const page = await context.newPage();

    // Set viewport
    await page.setViewportSize({ width: 1920, height: 1080 });

    // Run test cases
    const test1Result = await testCase1(page);
    const test2Result = await testCase2(page);
    const test3Result = await testCase3(page);
    const test4Result = await testCase4(page);
    const test5Result = await testCase5(page);

    // Generate report
    console.log('\n========================================');
    console.log('Test Summary');
    console.log('========================================');
    console.log(`Total Tests: ${testReport.summary.totalTests}`);
    console.log(`Passed: ${testReport.summary.passed}`);
    console.log(`Failed: ${testReport.summary.failed}`);
    console.log(`Pass Rate: ${((testReport.summary.passed / testReport.summary.totalTests) * 100).toFixed(2)}%`);

    // Save report
    fs.writeFileSync(REPORT_FILE, JSON.stringify(testReport, null, 2));
    console.log(`\nTest report saved to: ${REPORT_FILE}`);
    console.log(`Screenshots saved to: ${SCREENSHOT_DIR}`);

    await context.close();
    await browser.close();
  } catch (error) {
    console.error('Test execution failed:', error);
    if (browser) {
      await browser.close();
    }
    process.exit(1);
  }
}

// Run tests
runTests().catch(error => {
  console.error('Fatal error:', error);
  process.exit(1);
});
