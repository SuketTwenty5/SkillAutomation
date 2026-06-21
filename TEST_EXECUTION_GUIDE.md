# Test Execution Guide
## Master Data - Products & Services Test Suite

**Document ID:** TEST-GUIDE-001  
**Base URL:** https://app-twenty5ipe-lm-dev.cfapps.us10.hana.ondemand.com/  
**Last Updated:** 2026-06-21

---

## Quick Start

### Prerequisites
- Node.js 14+
- Chrome/Chromium browser installed
- Dedicated Chrome debug session on port `9222`
- Valid credentials for the test environment

### Installation & Execution

#### Option 1: Local Chrome/CDP Runner (Recommended)

**Step 1: Install Dependencies**
```bash
PLAYWRIGHT_SKIP_BROWSER_DOWNLOAD=1 npm install
```

**Step 2: Run Tests**
```bash
scripts/run-master-data-test.sh --to TC5

# Or stop early, for example after TC2
scripts/run-master-data-test.sh --to TC2
```

**Step 3: View Results**
- Test report: `test-report.json`
- Screenshots: `./test-screenshots/`

---

#### Option 2: Legacy Python/Selenium Runner

**Step 1: Install Dependencies**
```bash
pip install -r requirements.txt
```

**Step 2: Run Tests**
```bash
python test_automation.py
```

**Step 3: View Results**
- Test report: `test-report.json`
- Screenshots: `./test-screenshots/`

---

## Test Cases Overview

### Test Case 1: Navigate to Master Data Tab
- **ID:** TC-MD-PS-001.1
- **Steps:** 2
- **Expected Duration:** ~30 seconds
- **Key Assertions:** Master Data tab visible, sub-tabs displayed

### Test Case 2: Navigate to Products & Services Page
- **ID:** TC-MD-PS-001.2
- **Steps:** 3
- **Expected Duration:** ~30 seconds
- **Key Assertions:** Page loads, header displayed

### Test Case 3: Verify New Button Options
- **ID:** TC-MD-PS-001.3
- **Steps:** 3
- **Expected Duration:** ~40 seconds
- **Key Assertions:** 4 dropdown options displayed correctly

### Test Case 4: Verify Create from Type Options
- **ID:** TC-MD-PS-001.4
- **Steps:** 2
- **Expected Duration:** ~30 seconds
- **Key Assertions:** Submenu appears, Purchased Part option visible

### Test Case 5: Create Purchased Part and Verify Sub-tabs
- **ID:** TC-MD-PS-001.5
- **Steps:** 3
- **Expected Duration:** ~50 seconds
- **Key Assertions:** 7 sub-tabs visible and accessible

**Total Expected Duration:** ~3-5 minutes

---

## Test Report Format

### Report Structure
```json
{
  "timestamp": "2026-06-21T10:30:00.000Z",
  "baseUrl": "https://app-twenty5ipe-lm-dev.cfapps.us10.hana.ondemand.com/",
  "results": [
    {
      "testId": "TC-MD-PS-001.1",
      "testName": "Navigate to Master Data Tab",
      "stepName": "Page loaded",
      "passed": true,
      "message": "Application loaded successfully",
      "timestamp": "2026-06-21T10:30:05.000Z",
      "screenshot": "./test-screenshots/TC-MD-PS-001.1-initial-load-1234567890.png"
    }
  ],
  "summary": {
    "totalTests": 15,
    "passed": 14,
    "failed": 1,
    "skipped": 0
  }
}
```

---

## Troubleshooting

### Issue: Tests Cannot Find Elements

**Possible Causes:**
1. Application not fully loaded
2. Element selectors need updating
3. UI layout has changed

**Solutions:**
- Check screenshots in `./test-screenshots/` for debugging
- Verify element text hasn't changed
- Update selectors in test code if UI changed
- Increase WAIT_TIMEOUT in test script

### Issue: Browser Connection Error

**Possible Causes:**
1. Chrome/Chromium not installed
2. WebDriver version mismatch

**Solutions:**
```bash
# Python: Update webdriver
pip install --upgrade webdriver-manager

# JavaScript: Reinstall Playwright browsers
npx playwright install
```

### Issue: Authentication Required

**Solutions:**
1. Modify test script to add login steps
2. Use environment variables for credentials
3. Pre-authenticate before running tests

### Issue: Timeout Errors

**Solutions:**
```python
# Python: Increase timeout
WAIT_TIMEOUT = 20  # Increase from 10

# JavaScript: Modify timeout
const HEADLESS = false; // Set to false to debug
```

---

## Customization

### Adding New Test Cases

**Python Example:**
```python
def test_case_6(self):
    """Test Case 6: Your new test"""
    test_id = 'TC-MD-PS-001.6'
    test_name = 'Your test name'
    
    print(f"\n▶ Running {test_id}: {test_name}")
    
    try:
        # Your test steps here
        self.log_result(test_id, test_name, 'Step description', True, 'Success message')
        return True
    except Exception as error:
        self.log_result(test_id, test_name, 'Error', False, str(error))
        return False
```

### Modifying Element Selectors

**Python:**
```python
# Update find_element_by_text method or add custom selector
element = self.driver.find_element(By.CSS_SELECTOR, ".your-selector")
```

**JavaScript:**
```javascript
// Update selector in testCase function
const element = await page.locator('.your-selector').first();
```

---

## Best Practices

### Before Running Tests
- [ ] Verify Base URL is accessible
- [ ] Ensure you have valid login credentials
- [ ] Close unnecessary browser tabs
- [ ] Clear browser cache if needed

### During Test Execution
- [ ] Monitor browser for unexpected popups/dialogs
- [ ] Don't interact with browser during test run
- [ ] Allow tests to complete fully

### After Test Execution
- [ ] Review test report JSON
- [ ] Check screenshots for failures
- [ ] Document any issues found
- [ ] Update selectors if UI changed

---

## Integration with CI/CD

### GitHub Actions Example
```yaml
name: Run Tests

on: [push, pull_request]

jobs:
  test:
    runs-on: self-hosted
    steps:
      - uses: actions/checkout@v2
      - uses: actions/setup-node@v4
        with:
          node-version: '20'
      - run: PLAYWRIGHT_SKIP_BROWSER_DOWNLOAD=1 npm install
      - run: scripts/start-debug-chrome.sh "https://app-twenty5ipe-lm-dev.cfapps.us10.hana.ondemand.com/"
      - run: scripts/run-master-data-test.sh --to TC5
      - uses: actions/upload-artifact@v2
        if: always()
        with:
          name: test-results
          path: |
            test-report.json
            test-screenshots/
```

### Jenkins Pipeline Example
```groovy
pipeline {
    agent any
    
    stages {
        stage('Setup') {
            steps {
                sh 'PLAYWRIGHT_SKIP_BROWSER_DOWNLOAD=1 npm install'
            }
        }
        stage('Test') {
            steps {
                sh 'scripts/run-master-data-test.sh --to TC5'
            }
        }
        stage('Report') {
            steps {
                archiveArtifacts artifacts: 'test-report.json,test-screenshots/**'
            }
        }
    }
}
```

---

## Support & Contact

**Questions or Issues?**
- Check test screenshots for visual debugging
- Review test-report.json for detailed results
- Update selectors if application UI changes
- Ensure proper network connectivity

---

## Glossary

| Term | Definition |
|------|-----------|
| TC | Test Case |
| PASS | Test assertion succeeded |
| FAIL | Test assertion failed |
| Selector | CSS or XPath to locate elements |
| Screenshot | Visual capture at specific test point |
| Timeout | Maximum wait time for element appearance |

---

## Version History

| Version | Date | Changes |
|---------|------|---------|
| 1.0 | 2026-06-21 | Initial test suite creation |

---

**Happy Testing! 🎉**
