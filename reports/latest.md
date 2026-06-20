# Test Report — TC-Prof-Services-001: Verify “Proposal Setup” Layout

| | |
|---|---|
| **Status** | ✅ PASSED |
| **Feature** | 1. Setup Professional Services Proposal |
| **Tags** | @SETUP-PROFF-SERVICES @TC-001 @START |
| **Started** | 2026-06-20 17:26:44 IST |
| **Duration** | 2m 8s |
| **Steps** | 56 passed / 0 failed / 0 skipped / 56 total |

## Step-by-Step Results

### Setup

| # | Status | Step | Duration |
|---|--------|------|----------|
| 1 | ✅ | `Given` open site | 1.1s |
| 2 | ✅ | `And` I perform login | 3.0s |
| 3 | ✅ | `Then` page with name 'Main page' is opened | 100ms |

### 1. Navigate to Proposals

| # | Status | Step | Duration |
|---|--------|------|----------|
| 4 | ✅ | `Given` I click on 'PROPOSALS' tab | 623ms |
| 5 | ✅ | `Then` page with name 'Proposals list page' is opened | 2.0s |

### 2. Click New to start creating new Proposal

| # | Status | Step | Duration |
|---|--------|------|----------|
| 6 | ✅ | `Then` click on 'New' button | 4.7s |
| 7 | ✅ | `And` page with name 'Setup page' is opened | 15.5s |

### 3. On the Setup tab, select Regression Test Only - Consulting from Proposal Type

| # | Status | Step | Duration |
|---|--------|------|----------|
| 8 | ✅ | `When` I enter data into the next fields: | 6.3s |
| 9 | ✅ | `And` page with name 'Setup page' is opened | 15.6s |
| 10 | ✅ | `Then` I see the following TABS in the 'Proposal' Top Menu_-Warning-_: | 270ms |

### 4. Verify contents of Project Details section

| # | Status | Step | Duration |
|---|--------|------|----------|
| 11 | ✅ | `And` I verify 'Title or Brief Description' of setup page is enabled, required, free text and is empty | 55ms |
| 12 | ✅ | `And` I verify 'Select the Best-fit Type' of setup page is enabled, required, drop down and has text 'Regression Test Only - Consulting' | 44ms |
| 13 | ✅ | `And` I verify 'Contract Type' of setup page is enabled, not required, drop down and is empty | 42ms |
| 14 | ✅ | `And` I verify 'SAP Project' of setup page is enabled, not required, drop down and is empty | 38ms |
| 15 | ✅ | `And` 'Open Project in SAP' link is displayed | 136ms |
| 16 | ✅ | `And` I verify 'SAP WBS' of setup page is enabled, not required, drop down and is empty | 40ms |
| 17 | ✅ | `And` I verify 'WBS managed in SAP' of setup page is enabled, not required, radio buttons and is unchecked | 86ms |
| 18 | ✅ | `And` I verify 'Estimated Project Start' of setup page is enabled, required, date picker and has text '$todayDate' | 48ms |
| 19 | ✅ | `And` I verify 'Project End' of setup page is enabled, required, date picker and has text '$oneYearFromToday' | 58ms |
| 20 | ✅ | `And` I verify 'Project Manager' of setup page is enabled, not required, drop down and has text 'Tech User' | 36ms |
| 21 | ✅ | `And` I verify 'multiple phases' of setup page is enabled, not required, radio buttons and is checked | 68ms |
| 22 | ✅ | `And` I verify 'Proposal Hours' of setup page is enabled, not required, drop down and is empty | 41ms |
| 23 | ✅ | `And` 'Upload RFx Files' button is displayed | 122ms |
| 24 | ✅ | `And` page with name 'Setup page' is opened | 15.5s |

### 5. Verify contents of Opportunity, CLient, & Your Organization section

| # | Status | Step | Duration |
|---|--------|------|----------|
| 25 | ✅ | `And` I verify 'Leading company' of setup page is enabled, required, drop down and is empty | 45ms |
| 26 | ✅ | `And` I verify 'Leading department' of setup page is enabled, required, drop down and is empty | 38ms |
| 27 | ✅ | `And` I verify 'Profit Center' of setup page is enabled, not required, drop down and is empty | 37ms |
| 28 | ✅ | `And` I verify 'Cost Center' of setup page is enabled, not required, drop down and is empty | 39ms |
| 29 | ✅ | `And` I verify 'Leading Company Currency' of setup page is enabled, required, free text and has text '$ USA - US Dollar(USD)' | 31ms |
| 30 | ✅ | `And` 'Edit Rates' link is displayed | 110ms |
| 31 | ✅ | `And` I verify 'Project Currency' of setup page is enabled, required, drop down and is empty | 44ms |
| 32 | ✅ | `And` I verify 'Other Departments or Sites Involved' of setup page is enabled, not required, drop down and is empty | 48ms |
| 33 | ✅ | `And` I verify 'Client/Customer (Sell-to)' of setup page is enabled, required, drop down and is empty | 40ms |
| 34 | ✅ | `And` I verify 'CRM Opportunity' of setup page is enabled, not required, drop down and is empty | 46ms |
| 35 | ✅ | `And` I verify 'Customer Contact' of setup page is enabled, not required, drop down and is empty | 46ms |
| 36 | ✅ | `And` I verify 'Opportunity Status' of setup page is enabled, not required, free text and is empty | 47ms |
| 37 | ✅ | `And` I verify 'Customer Purchase order' of setup page is enabled, not required, free text and is empty | 62ms |
| 38 | ✅ | `And` I verify 'Proposal Authorization#' of setup page is enabled, not required, free text and is empty | 65ms |
| 39 | ✅ | `And` page with name 'Setup page' is opened | 15.4s |

### 6. Verify Re-use Prior Proposal or Template section

| # | Status | Step | Duration |
|---|--------|------|----------|
| 40 | ✅ | `And` I verify 'Select a Template' of setup page is enabled, not required, radio buttons and is checked | 51ms |
| 41 | ✅ | `And` I verify 'Select a prior proposal' of setup page is enabled, not required, radio buttons and is unchecked | 50ms |
| 42 | ✅ | `And` page with name 'Setup page' is opened | 15.4s |

### 7. Verify Project Goals or Remarks section

| # | Status | Step | Duration |
|---|--------|------|----------|
| 43 | ✅ | `And` 'Project Goals or Remarks' textbox is displayed | 124ms |
| 44 | ✅ | `When` I click on 'Project Goals or Remarks' textbox | 366ms |
| 45 | ✅ | `Then` 'Text edit' toolbox is displayed | 99ms |
| 46 | ✅ | `And` page with name 'Setup page' is opened | 15.4s |

### 8. Verify Tags to New Dimensions of Costs & Revenue Model

| # | Status | Step | Duration |
|---|--------|------|----------|
| 47 | ✅ | `And` I verify 'Select Tag to Add' of setup page is enabled, not required, drop down and is empty | 54ms |
| 48 | ✅ | `And` page with name 'Setup page' is opened | 15.4s |

## Screenshots

19 screenshots captured during this run.

Location: `imported/twentyfive-regtest/tests/previousScreenshot/`

---
_Generated at 2026-06-20 17:34:17_