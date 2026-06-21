# Test Suite: Master Data - Products & Services

Complete automated test suite for validating the Master Data Products & Services workflow.

---

## 📁 Package Contents

### Core Test Files
- **test_cases.md** - Detailed test case documentation with all 5 test cases
- **test_automation.py** - Python/Selenium automated test script
- **test_automation.js** - JavaScript/Playwright automated test script
- **test_automation_cdp.js** - Recommended CDP entrypoint that reuses the consultant's logged-in Chrome session

### Configuration & Setup
- **package.json** - Node.js dependencies (Playwright)
- **requirements.txt** - Python dependencies (Selenium)
- **run_tests.sh** - Quick launch script (automated setup & execution)
- **scripts/run-master-data-test.sh** - Recommended consultant runner
- **automation/master-data/** - Reusable action, reporting, config, and scenario modules

### Documentation
- **TEST_EXECUTION_GUIDE.md** - Complete guide for running tests
- **MASTER_DATA_TEST_ARCHITECTURE.md** - Architecture for low-token reusable execution
- **README_TEST_SUITE.md** - This file

---

## 🚀 Quick Start (3 Steps)

### Step 1: Navigate to Test Directory
```bash
cd /Users/suketsuman/SkillAutomation
```

### Step 2: Run Tests
```bash
# Recommended local Chrome/CDP runner
scripts/run-master-data-test.sh --to TC5

# Stop early, for example after TC2
scripts/run-master-data-test.sh --to TC2

# Legacy/manual runners remain available when needed
./run_tests.sh python

./run_tests.sh node
```

### Step 3: View Results
```bash
# JSON Report
cat test-report.json

# Screenshots
ls test-screenshots/
```

---

## ✅ What Gets Tested

| Test ID | Test Name | Duration | Key Assertions |
|---------|-----------|----------|-----------------|
| TC-MD-PS-001.1 | Master Data Tab Navigation | ~30s | Tab visible, sub-tabs displayed |
| TC-MD-PS-001.2 | Products & Services Page | ~30s | Page loads, correct URL |
| TC-MD-PS-001.3 | New Button Options | ~40s | 4 dropdown options shown |
| TC-MD-PS-001.4 | Create from Type Options | ~30s | Submenu appears, Purchased Part visible |
| TC-MD-PS-001.5 | Purchased Part Creation | ~50s | 7 sub-tabs visible & accessible |

**Total Duration: 3-5 minutes**

---

## 🛠️ System Requirements

### Python Version
- Python 3.8+
- Chrome/Chromium browser
- Internet connection

### Node.js Version
- Node.js 14+
- Chrome/Chromium browser
- Internet connection

### Network
- Access to: https://app-twenty5ipe-lm-dev.cfapps.us10.hana.ondemand.com/
- Valid user credentials (if required)

---

## 📊 Test Report Output

Tests generate a comprehensive JSON report:
```json
{
  "timestamp": "2026-06-21T10:30:00.000Z",
  "baseUrl": "https://app-twenty5ipe-lm-dev.cfapps.us10.hana.ondemand.com/",
  "results": [...],
  "summary": {
    "totalTests": 15,
    "passed": 14,
    "failed": 1,
    "skipped": 0
  }
}
```

Screenshots captured at each step for visual verification.

---

## 🔧 Customization Options

### Change Base URL
Use an environment variable:
```bash
MASTER_DATA_APP_URL="https://your-url.com/" scripts/run-master-data-test.sh --to TC5
```

### Increase Timeout
For slower networks:
```python
WAIT_TIMEOUT = 20  # increase from 10
```

### Run a Partial Test
```bash
scripts/run-master-data-test.sh --to TC2
```

### Login
Log in manually in the dedicated Chrome debug browser. The CDP runner reuses that browser session.

---

## 🐛 Troubleshooting

### Tests Can't Find Elements
- Check `test-screenshots/` for visual debugging
- Verify element text/selectors match current UI
- Increase `WAIT_TIMEOUT` if UI is slow

### Browser Won't Launch
```bash
# Start the dedicated debug browser
scripts/start-debug-chrome.sh "https://app-twenty5ipe-lm-dev.cfapps.us10.hana.ondemand.com/"
```

### Permission Denied Error
```bash
chmod +x run_tests.sh
```

### Timeout on Page Load
- Check internet connection
- Verify Base URL is accessible
- Increase timeout in test configuration

See **TEST_EXECUTION_GUIDE.md** for more troubleshooting.

---

## 📈 Integration with CI/CD

Ready for GitHub Actions, Jenkins, GitLab CI:

```bash
# Basic CI/CD command
./run_tests.sh && cat test-report.json
```

Example CI configs included in TEST_EXECUTION_GUIDE.md

---

## 📝 Test Case Documentation

See **test_cases.md** for:
- Detailed step-by-step procedures
- Expected results for each step
- Assertion checklist
- Pass/fail criteria
- Test environment details

See **MASTER_DATA_TEST_ARCHITECTURE.md** for the reusable action-layer architecture that avoids repeated selector probing.

---

## 🎯 Key Features

✅ **5 Complete Test Cases** - All scenarios covered  
✅ **Multi-Framework** - Python (Selenium) + JavaScript (Playwright)  
✅ **Screenshot Capture** - Visual evidence of each step  
✅ **JSON Reports** - Machine-readable test results  
✅ **Auto-Setup** - Dependencies installed automatically  
✅ **Well Documented** - Guides for every scenario  
✅ **Extensible** - Easy to add new tests  
✅ **CI/CD Ready** - Works with all major platforms  

---

## 📞 Support

**For Issues:**
1. Check screenshots in `test-screenshots/`
2. Review `test-report.json` for details
3. See troubleshooting in TEST_EXECUTION_GUIDE.md
4. Verify Base URL accessibility
5. Check element selectors if UI changed

**File Structure:**
```
SkillAutomation/
├── test_cases.md                 ← Test case documentation
├── test_automation.py            ← Python/Selenium tests
├── test_automation.js            ← JavaScript/Playwright tests
├── test_automation_cdp.js        ← Recommended CDP wrapper
├── automation/master-data/       ← Action/reporting/scenario modules
├── scripts/run-master-data-test.sh ← Consultant runner
├── package.json                  ← Node.js config
├── requirements.txt              ← Python packages
├── run_tests.sh                  ← Auto-launch script
├── TEST_EXECUTION_GUIDE.md       ← Detailed guide
├── MASTER_DATA_TEST_ARCHITECTURE.md ← Low-token execution architecture
└── README_TEST_SUITE.md          ← This file
```

---

## 📅 Version

**Version:** 1.0  
**Created:** 2026-06-21  
**Base URL:** https://app-twenty5ipe-lm-dev.cfapps.us10.hana.ondemand.com/

---

## 🎉 Ready to Test!

```bash
cd /Users/suketsuman/SkillAutomation
./run_tests.sh
```

Happy testing! 🚀
