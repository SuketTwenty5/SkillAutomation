# TC-Prof-Services-001: Verify "Proposal Setup" Layout - Execution Guide

## Test Case Summary
- **Test ID**: TC-Prof-Services-001
- **Title**: Verify "Proposal Setup" Layout
- **Type**: UI Layout Verification
- **Feature File**: `imported/twentyfive-regtest/tests/src/test/resources/features/testfolder/setup_professional_services_proposal.feature` (Line 5)
- **Cucumber Tags**: `@TC-001`, `@START`

## Test Configuration

### Application Details
- **App Label**: Twenty5 Internal (BTP Golden)
- **App URL**: https://approuter-twenty5ipe-dev.cfapps.us10.hana.ondemand.com
- **Chrome Debug Port**: 9222
- **Chrome Profile**: ~/.selenium-ai-chrome

### Environment Files
- Config file: `.skillautomation.env`
- Test root: `imported/twentyfive-regtest`

## Test Execution Steps

### Prerequisites
1. Ensure you have Chrome installed (required for remote debugging)
2. Network access to the target application URL
3. User account with appropriate permissions for Professional Services proposals

### Step 1: Start Chrome in Debug Mode
Run the debug Chrome startup script:
```bash
cd /Users/suketsuman/SkillAutomation
bash scripts/start-debug-chrome.sh "https://approuter-twenty5ipe-dev.cfapps.us10.hana.ondemand.com"
```

This will:
- Open a dedicated Chrome window with remote debugging enabled on port 9222
- Navigate to the application URL
- Keep the debug endpoint listening for test connections

### Step 2: Log In to the Application
Once Chrome opens:
1. Navigate to the application URL if not already there
2. Enter your credentials to log in
3. Keep the Chrome window open (do not close it)

### Step 3: Run the Test Case
In a new terminal, run:
```bash
cd /Users/suketsuman/SkillAutomation
bash scripts/run-twentyfive-test.sh @TC-001
```

Or with explicit parameters:
```bash
APP_URL="https://approuter-twenty5ipe-dev.cfapps.us10.hana.ondemand.com" \
CUCUMBER_TAGS="@TC-001" \
bash scripts/run-twentyfive-test.sh
```

### Optional: Run with Metrics Enabled
```bash
METRICS_ENABLED=true bash scripts/run-twentyfive-test.sh @TC-001
```

## Test Verification Points

The test verifies the following layout elements on the "Proposal Setup" page:

### 1. Navigation & Page Load
- [ ] Successfully navigate to PROPOSALS tab
- [ ] Click "New" button to create a proposal
- [ ] Proposal Setup page opens

### 2. Proposal Type Selection
- [ ] Select "Regression Test Only - Consulting" from Proposal Type dropdown

### 3. Top Menu Tabs
The following tabs should be visible:
- [ ] Setup
- [ ] Phases
- [ ] Workstreams
- [ ] Billing Items
- [ ] Schedule
- [ ] My Team
- [ ] Workflow
- [ ] Analysis
- [ ] Rates

### 4. Project Details Section
All fields should have correct properties:
- [ ] **Title or Brief Description** - enabled, required, free text, empty
- [ ] **Select the Best-fit Type** - enabled, required, dropdown, shows "Regression Test Only - Consulting"
- [ ] **Contract Type** - enabled, not required, dropdown, empty
- [ ] **SAP Project** - enabled, not required, dropdown, empty
- [ ] **Open Project in SAP** - link displayed
- [ ] **SAP WBS** - enabled, not required, dropdown, empty
- [ ] **WBS managed in SAP** - enabled, not required, radio buttons, unchecked
- [ ] **Estimated Project Start** - enabled, required, date picker, has current date
- [ ] **Project End** - enabled, required, date picker, has one year from today
- [ ] **Project Manager** - enabled, not required, dropdown, shows "Tech User"
- [ ] **Multiple phases** - enabled, not required, radio buttons, checked
- [ ] **Proposal Hours** - enabled, not required, dropdown, empty
- [ ] **Upload RFx Files** - button displayed

### 5. Opportunity, Client & Organization Section
- [ ] **Leading company** - enabled, required, dropdown, empty
- [ ] **Leading department** - enabled, required, dropdown, empty
- [ ] **Profit Center** - enabled, not required, dropdown, empty
- [ ] **Cost Center** - enabled, not required, dropdown, empty
- [ ] **Leading Company Currency** - enabled, required, free text, shows "$ USA - US Dollar(USD)"
- [ ] **Edit Rates** - link displayed
- [ ] **Project Currency** - enabled, required, dropdown, empty
- [ ] **Other Departments or Sites Involved** - enabled, not required, dropdown, empty
- [ ] **Client/Customer (Sell-to)** - enabled, required, dropdown, empty
- [ ] **CRM Opportunity** - enabled, not required, dropdown, empty
- [ ] **Customer Contact** - enabled, not required, dropdown, empty
- [ ] **Opportunity Status** - enabled, not required, free text, empty
- [ ] **Customer Purchase order** - enabled, not required, free text, empty
- [ ] **Proposal Authorization#** - enabled, not required, free text, empty

### 6. Re-use Prior Proposal or Template Section
- [ ] **Select a Template** - enabled, not required, radio buttons, checked
- [ ] **Select a prior proposal** - enabled, not required, radio buttons, unchecked

### 7. Project Goals or Remarks Section
- [ ] **Project Goals or Remarks** - textbox displayed
- [ ] Clicking textbox shows "Text edit" toolbox

### 8. Tags to New Dimensions of Costs & Revenue Model Section
- [ ] **Select Tag to Add** - enabled, not required, dropdown, empty

## Expected Test Result
✅ **PASS** - All layout elements are present and have the correct properties and states.

## Troubleshooting

### Chrome Debug Port Not Listening
```bash
# Manually start Chrome with debugging
open -na "Google Chrome" --args \
  --remote-debugging-port=9222 \
  --user-data-dir="$HOME/.selenium-ai-chrome" \
  "https://approuter-twenty5ipe-dev.cfapps.us10.hana.ondemand.com"
```

### Maven Not Found
Ensure Maven is installed and in your PATH:
```bash
mvn --version
```

### Test Timeout
Increase the debug hold browser timeout or check Chrome connection status:
```bash
curl -i http://127.0.0.1:9222/json/version
```

## Related Test Cases
- **TC-Prof-Services-002**: Fill in Required Fields & Validate Save
- **TC-Prof-Services-003**: Validate Copy Template Functionality
- **TC-Prof-Services-004**: Validate Copy Proposal Results Reschedule Functionality

## Additional Resources
- Confluence RTA Page: https://twenty5.atlassian.net/wiki/spaces/RTA/pages/189956097/TC-Prof-Services-001+Verify+Proposal+Setup+Layout
- Feature File: `setup_professional_services_proposal.feature`
- Test Suite Map: `RTA_TEST_SUITE_FEATURE_MAP.md`
