const fs = require('fs');
const path = require('path');

class TestReporter {
  constructor({ baseUrl, cdpEndpoint, reportFile, screenshotDir, screenshots }) {
    this.reportFile = reportFile;
    this.screenshotDir = screenshotDir;
    this.screenshots = screenshots;
    this.report = {
      timestamp: new Date().toISOString(),
      baseUrl,
      cdpEndpoint,
      results: [],
      summary: {
        totalTests: 0,
        passed: 0,
        failed: 0,
        skipped: 0,
      },
    };
  }

  setup() {
    if (this.screenshots && !fs.existsSync(this.screenshotDir)) {
      fs.mkdirSync(this.screenshotDir, { recursive: true });
    }
  }

  async screenshot(page, testId, stepName) {
    if (!this.screenshots) return null;
    const safeStep = stepName.replace(/[^A-Za-z0-9_.-]+/g, '_');
    const fileName = `${testId}-${safeStep}-${Date.now()}.png`;
    const filePath = path.join(this.screenshotDir, fileName);
    try {
      await page.screenshot({ path: filePath });
      return filePath;
    } catch (error) {
      return null;
    }
  }

  async record(page, testId, testName, stepName, passed, message, screenshotName = stepName) {
    const screenshot = await this.screenshot(page, testId, screenshotName);
    this.report.results.push({
      testId,
      testName,
      stepName,
      passed,
      message,
      timestamp: new Date().toISOString(),
      screenshot,
    });
    this.report.summary.totalTests += 1;
    if (passed) {
      this.report.summary.passed += 1;
    } else {
      this.report.summary.failed += 1;
    }
    console.log(`[${passed ? 'PASS' : 'FAIL'}] ${testId} - ${stepName}: ${message}`);
    return passed;
  }

  write() {
    fs.writeFileSync(this.reportFile, JSON.stringify(this.report, null, 2));
  }

  printSummary() {
    const summary = this.report.summary;
    const passRate = summary.totalTests
      ? ((summary.passed / summary.totalTests) * 100).toFixed(2)
      : '0.00';

    console.log('');
    console.log('========================================');
    console.log('Test Summary');
    console.log('========================================');
    console.log(`Total: ${summary.totalTests}  Passed: ${summary.passed}  Failed: ${summary.failed}`);
    console.log(`Pass Rate: ${passRate}%`);
    console.log(`Report: ${this.reportFile}`);
    if (this.screenshots) console.log(`Screenshots: ${this.screenshotDir}`);
  }
}

module.exports = { TestReporter };
