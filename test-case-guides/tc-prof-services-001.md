# TC-Prof-Services-001 - Verify Proposal Setup Layout

| Field | Value |
| --- | --- |
| Test ID | TC-Prof-Services-001 |
| Title | Verify Proposal Setup Layout |
| RTA page | https://twenty5.atlassian.net/wiki/spaces/RTA/pages/189956097/TC-Prof-Services-001+Verify+Proposal+Setup+Layout |
| Map source | `RTA_TEST_SUITE_FEATURE_MAP.md` |

## Run Instructions

When a consultant asks to run this test, use the default app URL unless they provide another URL:

```text
https://approuter-twenty5ipe-dev.cfapps.us10.hana.ondemand.com/#quote
```

For Claude Desktop, ask before launching Selenium Chrome with the default URL. If the user provides another URL, launch that URL instead.

```bash
scripts/start-debug-chrome.sh "https://approuter-twenty5ipe-dev.cfapps.us10.hana.ondemand.com/#quote"
scripts/run-twentyfive-test.sh @TC-001
```

## Default mapped Selenium scenario

| Field | Value |
| --- | --- |
| Feature | 1. Setup Professional Services Proposal |
| Scenario | TC-Prof-Services-001: Verify "Proposal Setup" Layout |
| Tags | `@TC-001 @START` |
| File | `imported/twentyfive-regtest/tests/src/test/resources/features/testfolder/setup_professional_services_proposal.feature:5` |

### Scenario Sections

#### Setup

- open site
- I perform login
- page with name 'Main page' is opened

#### 1. Navigate to Proposals

- I click on 'PROPOSALS' tab
- page with name 'Proposals list page' is opened

#### 2. Click New to start creating new Proposal

- click on 'New' button
- page with name 'Setup page' is opened

#### 3. On the Setup tab, select Regression Test Only - Consulting from Proposal Type

- I enter data into the next fields:
- page with name 'Setup page' is opened
- I see the following TABS in the 'Proposal' Top Menu_-Warning-_:

#### 4. Verify contents of Project Details section

- I verify 'Title or Brief Description' of setup page is enabled, required, free text and is empty
- I verify 'Select the Best-fit Type' of setup page is enabled, required, drop down and has text 'Regression Test Only - Consulting'
- I verify 'Contract Type' of setup page is enabled, not required, drop down and is empty
- I verify 'SAP Project' of setup page is enabled, not required, drop down and is empty
- 'Open Project in SAP' link is displayed
- I verify 'SAP WBS' of setup page is enabled, not required, drop down and is empty
- I verify 'WBS managed in SAP' of setup page is enabled, not required, radio buttons and is unchecked
- I verify 'Estimated Project Start' of setup page is enabled, required, date picker and has text '$todayDate'
- I verify 'Project End' of setup page is enabled, required, date picker and has text '$oneYearFromToday'
- I verify 'Project Manager' of setup page is enabled, not required, drop down and has text 'Tech User'
- I verify 'multiple phases' of setup page is enabled, not required, radio buttons and is checked
- I verify 'Proposal Hours' of setup page is enabled, not required, drop down and is empty
- 'Upload RFx Files' button is displayed
- page with name 'Setup page' is opened

#### 5. Verify contents of Opportunity, CLient, & Your Organization section

- I verify 'Leading company' of setup page is enabled, required, drop down and is empty
- I verify 'Leading department' of setup page is enabled, required, drop down and is empty
- I verify 'Profit Center' of setup page is enabled, not required, drop down and is empty
- I verify 'Cost Center' of setup page is enabled, not required, drop down and is empty
- I verify 'Leading Company Currency' of setup page is enabled, required, free text and has text '$ USA - US Dollar(USD)'
- 'Edit Rates' link is displayed
- I verify 'Project Currency' of setup page is enabled, required, drop down and is empty
- I verify 'Other Departments or Sites Involved' of setup page is enabled, not required, drop down and is empty
- I verify 'Client/Customer (Sell-to)' of setup page is enabled, required, drop down and is empty
- I verify 'CRM Opportunity' of setup page is enabled, not required, drop down and is empty
- I verify 'Customer Contact' of setup page is enabled, not required, drop down and is empty
- I verify 'Opportunity Status' of setup page is enabled, not required, free text and is empty
- I verify 'Customer Purchase order' of setup page is enabled, not required, free text and is empty
- I verify 'Proposal Authorization#' of setup page is enabled, not required, free text and is empty
- page with name 'Setup page' is opened

#### 6. Verify Re-use Prior Proposal or Template section

- I verify 'Select a Template' of setup page is enabled, not required, radio buttons and is checked
- I verify 'Select a prior proposal' of setup page is enabled, not required, radio buttons and is unchecked
- page with name 'Setup page' is opened

#### 7. Verify Project Goals or Remarks section

- 'Project Goals or Remarks' textbox is displayed
- I click on 'Project Goals or Remarks' textbox
- 'Text edit' toolbox is displayed
- page with name 'Setup page' is opened

#### 8. Verify Tags to New Dimensions of Costs & Revenue Model

- I verify 'Select Tag to Add' of setup page is enabled, not required, drop down and is empty
- page with name 'Setup page' is opened

### Gherkin Excerpt

```gherkin
  Scenario: TC-Prof-Services-001: Verify "Proposal Setup" Layout
    Given open site
    And I perform login
    Then page with name 'Main page' is opened
    And info: ---1. Navigate to Proposals---
    Given I click on 'PROPOSALS' tab
    Then page with name 'Proposals list page' is opened
    And info: ---2. Click New to start creating new Proposal---
    Then click on 'New' button
    And page with name 'Setup page' is opened
    And info: ---3. On the Setup tab, select Regression Test Only - Consulting from Proposal Type---
    When I enter data into the next fields:
      | 'Proposal Type' field | Regression Test Only - Consulting |
    And page with name 'Setup page' is opened
#    Then 'Description' field and 'Proposal Type' field are aligned
    Then I see the following TABS in the 'Proposal' Top Menu_-Warning-_:
      | Setup         |
      | Phases        |
      | Workstreams   |
      | Billing Items |
      | Schedule      |
      | My Team       |
      | Workflow      |
      | Analysis      |
      | Rates         |
    And info: ---4. Verify contents of Project Details section---
    And I verify 'Title or Brief Description' of setup page is enabled, required, free text and is empty
    And I verify 'Select the Best-fit Type' of setup page is enabled, required, drop down and has text 'Regression Test Only - Consulting'
    And I verify 'Contract Type' of setup page is enabled, not required, drop down and is empty
    And I verify 'SAP Project' of setup page is enabled, not required, drop down and is empty
    And 'Open Project in SAP' link is displayed
    And I verify 'SAP WBS' of setup page is enabled, not required, drop down and is empty
    And I verify 'WBS managed in SAP' of setup page is enabled, not required, radio buttons and is unchecked
    And I verify 'Estimated Project Start' of setup page is enabled, required, date picker and has text '$todayDate'
    And I verify 'Project End' of setup page is enabled, required, date picker and has text '$oneYearFromToday'
    And I verify 'Project Manager' of setup page is enabled, not required, drop down and has text 'Tech User'
    And I verify 'multiple phases' of setup page is enabled, not required, radio buttons and is checked
#    And I verify 'SAP Project Profile' of setup page is enabled, not required, drop down and is empty
    And I verify 'Proposal Hours' of setup page is enabled, not required, drop down and is empty
#    And I verify 'What is your Target Confidence Level' of setup page is enabled, not required, drop down and has text 'Final (0%)'
    And 'Upload RFx Files' button is displayed
    And page with name 'Setup page' is opened
    And info: ---5. Verify contents of Opportunity, CLient, & Your Organization section---
    And I verify 'Leading company' of setup page is enabled, required, drop down and is empty
    And I verify 'Leading department' of setup page is enabled, required, drop down and is empty
    And I verify 'Profit Center' of setup page is enabled, not required, drop down and is empty
    And I verify 'Cost Center' of setup page is enabled, not required, drop down and is empty
    And I verify 'Leading Company Currency' of setup page is enabled, required, free text and has text '$ USA - US Dollar(USD)'
    And 'Edit Rates' link is displayed
    And I verify 'Project Currency' of setup page is enabled, required, drop down and is empty
    And I verify 'Other Departments or Sites Involved' of setup page is enabled, not required, drop down and is empty
    And I verify 'Client/Customer (Sell-to)' of setup page is enabled, required, drop down and is empty
    And I verify 'CRM Opportunity' of setup page is enabled, not required, drop down and is empty
#    And I verify 'Labor Price Book' of setup page is enabled, not required, drop down and is empty
    And I verify 'Customer Contact' of setup page is enabled, not required, drop down and is empty
    And I verify 'Opportunity Status' of setup page is enabled, not required, free text and is empty
    And I verify 'Customer Purchase order' of setup page is enabled, not required, free text and is empty
    And I verify 'Proposal Authorization#' of setup page is enabled, not required, free text and is empty
#    And I verify 'Target - Price to Win' of setup page is enabled, not required, free text and has text '$'
    And page with name 'Setup page' is opened
    And info: ---6. Verify Re-use Prior Proposal or Template section ---
    And I verify 'Select a Template' of setup page is enabled, not required, radio buttons and is checked
    And I verify 'Select a prior proposal' of setup page is enabled, not required, radio buttons and is unchecked
#    And I verify 'Select a Template' of setup page is enabled, not required, drop down and is empty
    And page with name 'Setup page' is opened
    And info: ---7. Verify Project Goals or Remarks section---
    And 'Project Goals or Remarks' textbox is displayed
    When I click on 'Project Goals or Remarks' textbox
    Then 'Text edit' toolbox is displayed
    And page with name 'Setup page' is opened
    And info: ---8. Verify Tags to New Dimensions of Costs & Revenue Model ---
    And I verify 'Select Tag to Add' of setup page is enabled, not required, drop down and is empty
    And page with name 'Setup page' is opened

```

---

_Generated by `scripts/generate-testcase-guides.py` from the RTA mapping and local feature files._
