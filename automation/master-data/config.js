const path = require('path');

const DEFAULT_BASE_URL = 'https://app-twenty5ipe-lm-dev.cfapps.us10.hana.ondemand.com/';
const DEFAULT_CDP_ENDPOINT = 'http://127.0.0.1:9222';
const DEFAULT_SCREENSHOT_DIR = 'test-screenshots';
const DEFAULT_REPORT_FILE = 'test-report.json';

const TEST_IDS = ['TC1', 'TC2', 'TC3', 'TC4', 'TC5'];

function normalizeTestId(value) {
  if (!value) return null;
  const text = String(value).trim().toUpperCase();
  const match = text.match(/(?:TC-MD-PS-001\.)?([1-5])$/) || text.match(/^TC([1-5])$/);
  return match ? `TC${match[1]}` : null;
}

function printUsage() {
  console.log(`Usage:
  node test_automation_cdp.js [--to TC5] [--only TC3]
  scripts/run-master-data-test.sh [--to TC5]

Options:
  --to <TC1..TC5>          Run from TC1 through this test. Default TC5.
  --only <TC1..TC5>        Run only one test. Use only for debugging; prerequisites may be missing.
  --base-url <url>         Override Master Data base URL.
  --cdp <url>              Chrome CDP endpoint. Default ${DEFAULT_CDP_ENDPOINT}.
  --report <path>          JSON report path. Default ${DEFAULT_REPORT_FILE}.
  --screenshots <dir>      Screenshot directory. Default ${DEFAULT_SCREENSHOT_DIR}.
  --no-screenshots         Do not capture screenshots.
  --help                   Show this help.

Environment:
  MASTER_DATA_APP_URL      Preferred app URL override.
  APP_URL                  Secondary app URL override for direct node usage.
  CDP_ENDPOINT             Chrome CDP endpoint.
`);
}

function parseArgs(argv) {
  const config = {
    baseUrl: process.env.MASTER_DATA_APP_URL || process.env.APP_URL || DEFAULT_BASE_URL,
    cdpEndpoint: process.env.CDP_ENDPOINT || DEFAULT_CDP_ENDPOINT,
    reportFile: process.env.REPORT_FILE || DEFAULT_REPORT_FILE,
    screenshotDir: process.env.SCREENSHOT_DIR || DEFAULT_SCREENSHOT_DIR,
    screenshots: process.env.SCREENSHOTS !== 'false',
    to: normalizeTestId(process.env.STOP_AT || process.env.TO_TEST) || 'TC5',
    only: normalizeTestId(process.env.ONLY_TEST),
    help: false,
  };

  for (let i = 0; i < argv.length; i += 1) {
    const arg = argv[i];
    switch (arg) {
      case '--help':
      case '-h':
        config.help = true;
        break;
      case '--to':
      case '--through':
      case '--stop-at':
        config.to = normalizeTestId(argv[++i]);
        break;
      case '--only':
        config.only = normalizeTestId(argv[++i]);
        break;
      case '--base-url':
      case '--url':
        config.baseUrl = argv[++i];
        break;
      case '--cdp':
        config.cdpEndpoint = argv[++i];
        break;
      case '--report':
        config.reportFile = argv[++i];
        break;
      case '--screenshots':
        config.screenshotDir = argv[++i];
        config.screenshots = true;
        break;
      case '--no-screenshots':
        config.screenshots = false;
        break;
      default: {
        const testId = normalizeTestId(arg);
        if (testId) {
          config.to = testId;
        } else {
          throw new Error(`Unknown argument: ${arg}`);
        }
      }
    }
  }

  if (!config.to && !config.only) {
    throw new Error('Invalid --to value. Use TC1, TC2, TC3, TC4, or TC5.');
  }
  if (config.only && !TEST_IDS.includes(config.only)) {
    throw new Error('Invalid --only value. Use TC1, TC2, TC3, TC4, or TC5.');
  }
  if (config.to && !TEST_IDS.includes(config.to)) {
    throw new Error('Invalid --to value. Use TC1, TC2, TC3, TC4, or TC5.');
  }

  config.reportFile = path.normalize(config.reportFile);
  config.screenshotDir = path.normalize(config.screenshotDir);
  return config;
}

function selectedTests(config) {
  if (config.only) return [config.only];
  return TEST_IDS.slice(0, TEST_IDS.indexOf(config.to) + 1);
}

module.exports = {
  DEFAULT_BASE_URL,
  DEFAULT_CDP_ENDPOINT,
  DEFAULT_SCREENSHOT_DIR,
  DEFAULT_REPORT_FILE,
  TEST_IDS,
  normalizeTestId,
  parseArgs,
  printUsage,
  selectedTests,
};
