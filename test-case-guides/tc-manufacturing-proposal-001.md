# TC-Manufacturing-Proposal-001 - Verify Proposal Setup Layout

| Field | Value |
| --- | --- |
| Test ID | TC-Manufacturing-Proposal-001 |
| Title | Verify Proposal Setup Layout |
| RTA page | https://twenty5.atlassian.net/wiki/spaces/RTA/pages/200933377/TC-Manufacturing-Proposal-001+Verify+Proposal+Setup+Layout |
| Map source | `RTA_TEST_SUITE_FEATURE_MAP.md` |

## Run Instructions

When a consultant asks to run this test, use the default app URL unless they provide another URL:

```text
https://approuter-twenty5ipe-dev.cfapps.us10.hana.ondemand.com/#quote
```

For Claude Desktop, ask before launching Selenium Chrome with the default URL. If the user provides another URL, launch that URL instead.

```bash
scripts/start-debug-chrome.sh "https://approuter-twenty5ipe-dev.cfapps.us10.hana.ondemand.com/#quote"
scripts/run-twentyfive-test.sh @TCM-001
```

Duplicate feature matches exist for this Confluence page. The tag command may run more than one matching scenario until the canonical feature file is confirmed.

## Default mapped Selenium scenario

| Field | Value |
| --- | --- |
| Feature | 4. Setup Manufacturing Proposal |
| Scenario | TC-Manufacturing-Proposal-001: Verify "Proposal Setup" Layout |
| Tags | `@TCM-001 @START` |
| File | `imported/twentyfive-regtest/tests/src/test/resources/features/testfolder/manafacturing_proposal_setup.feature:5` |

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
- 'Description' field and 'Proposal Type' field are aligned
- I see the following TABS in the 'New Page' Top Menu_-Warning-_:

#### 4. Verify contents of Project Details section

- I verify 'Proposal Title' of setup page is enabled, required, free text and is empty
- I verify 'Proposal Type' of setup page is enabled, required, drop down and has text 'Regression Test Only - Manufacturing'
- I verify 'SAP Project' of setup page is enabled, not required, drop down and is empty
- 'Open Project in SAP' link is displayed
- I verify 'Estimated Project Start' of setup page is enabled, required, date picker and has text '$todayDate'
- I verify 'Project End' of setup page is enabled, required, date picker and has text '$oneYearFromToday'
- I verify 'Bid Manager' of setup page is enabled, not required, drop down and has text 'Tech User'
- I verify 'Several Phases' of setup page is enabled, not required, radio buttons and is checked
- 'Upload RFx Files' button is displayed
- page with name 'Setup page' is opened

#### 5. Verify contents of Opportunity, CLient, & Your Organization section

- I verify 'Your Company' of setup page is enabled, required, drop down and is empty
- I verify 'Leading Site or Department' of setup page is enabled, required, drop down and is empty
- I verify 'Profit Center' of setup page is enabled, not required, drop down and is empty
- I verify 'Leading Company Currency' of setup page is enabled, required, free text and has text '$ USA - US Dollar(USD)'
- 'Edit Rates' link is displayed
- I verify 'Other Departments or Sites Involved' of setup page is enabled, not required, drop down and is empty
- I verify 'Client/Customer (Sell-to)' of setup page is enabled, required, drop down and is empty
- I verify 'Target - Price to Win' of setup page is enabled, not required, free text and has text '0.00'
- page with name 'Setup page' is opened

#### 6. Verify Re-use Prior Proposal or Template section

- I verify 'Select a template' of setup page is enabled, not required, radio buttons and is checked
- I verify 'Select a prior project' of setup page is enabled, not required, radio buttons and is unchecked
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
  Scenario: TC-Manufacturing-Proposal-001: Verify "Proposal Setup" Layout
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
      | 'Proposal Type' field | Regression Test Only - Manufacturing |
    And page with name 'Setup page' is opened
    Then 'Description' field and 'Proposal Type' field are aligned
    Then I see the following TABS in the 'New Page' Top Menu_-Warning-_:
      | Setup           |
      | Sets/Phases     |
      | Line Items      |
      | Cost Structure  |
      | Schedule        |
      | Pursuit Team    |
      | Pursuit Milestones      |
      | Analysis        |
      | Pricing         |
      | Change Requests |
      | Chat            |
    And info: ---4. Verify contents of Project Details section---
    And I verify 'Proposal Title' of setup page is enabled, required, free text and is empty
    And I verify 'Proposal Type' of setup page is enabled, required, drop down and has text 'Regression Test Only - Manufacturing'
    And I verify 'SAP Project' of setup page is enabled, not required, drop down and is empty
#    And I verify 'SAP Project' of setup page is enabled, not required, drop down and is empty
    And 'Open Project in SAP' link is displayed
#    And I verify 'WBS managed in SAP' of setup page is enabled, not required, radio buttons and is unchecked
    And I verify 'Estimated Project Start' of setup page is enabled, required, date picker and has text '$todayDate'
    And I verify 'Project End' of setup page is enabled, required, date picker and has text '$oneYearFromToday'
    And I verify 'Bid Manager' of setup page is enabled, not required, drop down and has text 'Tech User'
    And I verify 'Several Phases' of setup page is enabled, not required, radio buttons and is checked
#    And I verify 'SAP Project Profile' of setup page is enabled, not required, drop down and has text 'Z000001-ON PREM CUSTOMER PROJ PROF'
#    And I verify 'Proposal Hours' of setup page is enabled, not required, drop down and is empty
#    And I verify 'What is your Target Confidence Level' of setup page is enabled, not required, drop down and has text 'Final (0%)'
    And 'Upload RFx Files' button is displayed
    And page with name 'Setup page' is opened
    And info: ---5. Verify contents of Opportunity, CLient, & Your Organization section---
    And I verify 'Your Company' of setup page is enabled, required, drop down and is empty
    And I verify 'Leading Site or Department' of setup page is enabled, required, drop down and is empty
    And I verify 'Profit Center' of setup page is enabled, not required, drop down and is empty
#    And I verify 'Cost Center' of setup page is enabled, not required, drop down and is empty
    And I verify 'Leading Company Currency' of setup page is enabled, required, free text and has text '$ USA - US Dollar(USD)'
    And 'Edit Rates' link is displayed
#    And I verify 'Customer Currency' of setup page is enabled, required, drop down and is empty
    And I verify 'Other Departments or Sites Involved' of setup page is enabled, not required, drop down and is empty
    And I verify 'Client/Customer (Sell-to)' of setup page is enabled, required, drop down and is empty
#    And I verify 'Other Departments or Sites Involved' of setup page is enabled, not required, drop down and is empty
#    And I verify 'Labor Price Book' of setup page is enabled, not required, drop down and is empty
#    And I verify 'Customer Contact' of setup page is enabled, not required, drop down and is empty
#    And I verify 'Opportunity Status' of setup page is enabled, not required, free text and is empty
#    And I verify 'Customer Purchase order' of setup page is enabled, not required, free text and is empty
#    And I verify 'Proposal Authorization#' of setup page is enabled, not required, free text and is empty
    And I verify 'Target - Price to Win' of setup page is enabled, not required, free text and has text '0.00'
    And page with name 'Setup page' is opened
    And info: ---6. Verify Re-use Prior Proposal or Template section ---
    And I verify 'Select a template' of setup page is enabled, not required, radio buttons and is checked
    And I verify 'Select a prior project' of setup page is enabled, not required, radio buttons and is unchecked
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

## Alternate mapped Selenium scenario 2

| Field | Value |
| --- | --- |
| Feature | 1. Setup Manufacturing Proposal |
| Scenario | TC-Manufacturing-Proposal-001: Verify "Proposal Setup" Layout |
| Tags | `@TCM-001 @START` |
| File | `imported/twentyfive-regtest/tests/src/test/resources/features/testfolder/twentyfive2_4_setup_manufacturing_proposal.feature:5` |

### Scenario Sections

#### Setup

- open site
- I perform Mfg 2.4 login
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
- 'Description' field and 'Proposal Type' field are aligned
- I see the following TABS in the 'New Page' Top Menu_-Warning-_:

#### 4. Verify contents of Project Details section

- I verify 'Proposal Title' of setup page is enabled, required, free text and is empty
- I verify 'Proposal Type' of setup page is enabled, required, drop down and has text 'Regression Test Only - Manufacturing'
- I verify 'SAP Project' of setup page is enabled, not required, drop down and is empty
- 'Open Project in SAP' link is displayed
- I verify 'Estimated Project Start' of setup page is enabled, required, date picker and has text '$todayDate'
- I verify 'Project End' of setup page is enabled, required, date picker and has text '$oneYearFromToday'
- I verify 'Bid Manager' of setup page is enabled, not required, drop down and has text 'Suket Suman'
- I verify 'Several Phases' of setup page is enabled, not required, radio buttons and is checked
- 'Upload RFx Files' button is displayed
- page with name 'Setup page' is opened

#### 5. Verify contents of Opportunity, CLient, & Your Organization section

- I verify 'Your Company' of setup page is enabled, required, drop down and is empty
- I verify 'Leading Site or Department' of setup page is enabled, required, drop down and is empty
- I verify 'Profit Center' of setup page is enabled, not required, drop down and is empty
- I verify 'Leading Company Currency' of setup page is enabled, required, free text and has text '$ USA - US Dollar(USD)'
- 'Edit Rates' link is displayed
- I verify 'Other Departments or Sites Involved' of setup page is enabled, not required, drop down and is empty
- I verify 'Client/Customer (Sell-to)' of setup page is enabled, required, drop down and is empty
- I verify 'Target - Price to Win' of setup page is enabled, not required, free text and has text '0.00'
- page with name 'Setup page' is opened

#### 6. Verify Re-use Prior Proposal or Template section

- I verify 'Select a template' of setup page is enabled, not required, radio buttons and is checked
- I verify 'Select a prior project' of setup page is enabled, not required, radio buttons and is unchecked
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
  Scenario: TC-Manufacturing-Proposal-001: Verify "Proposal Setup" Layout
    Given open site
    And I perform Mfg 2.4 login
    Then page with name 'Main page' is opened
    And info: ---1. Navigate to Proposals---
    Given I click on 'PROPOSALS' tab
    Then page with name 'Proposals list page' is opened
    And info: ---2. Click New to start creating new Proposal---
    Then click on 'New' button
    And page with name 'Setup page' is opened
    And info: ---3. On the Setup tab, select Regression Test Only - Consulting from Proposal Type---
    When I enter data into the next fields:
      | 'Proposal Type' field | Regression Test Only - Manufacturing |
    And page with name 'Setup page' is opened
    Then 'Description' field and 'Proposal Type' field are aligned
    Then I see the following TABS in the 'New Page' Top Menu_-Warning-_:
      | Analysis          |
      | Change Requests   |
      | Chat              |
      | Cost Structure    |
      | Deliverables      |
      | Pricing           |
      | Pursuit Milestones|
      | Pursuit Team      |
      | Schedule          |
      | Sets/Phases       |
      | Setup             |
    And info: ---4. Verify contents of Project Details section---
    And I verify 'Proposal Title' of setup page is enabled, required, free text and is empty
    And I verify 'Proposal Type' of setup page is enabled, required, drop down and has text 'Regression Test Only - Manufacturing'
    And I verify 'SAP Project' of setup page is enabled, not required, drop down and is empty
#    And I verify 'SAP Project' of setup page is enabled, not required, drop down and is empty
    And 'Open Project in SAP' link is displayed
#    And I verify 'WBS managed in SAP' of setup page is enabled, not required, radio buttons and is unchecked
    And I verify 'Estimated Project Start' of setup page is enabled, required, date picker and has text '$todayDate'
    And I verify 'Project End' of setup page is enabled, required, date picker and has text '$oneYearFromToday'
    And I verify 'Bid Manager' of setup page is enabled, not required, drop down and has text 'Suket Suman'
    And I verify 'Several Phases' of setup page is enabled, not required, radio buttons and is checked
#    And I verify 'SAP Project Profile' of setup page is enabled, not required, drop down and has text 'Z000001-ON PREM CUSTOMER PROJ PROF'
#    And I verify 'Proposal Hours' of setup page is enabled, not required, drop down and is empty
#    And I verify 'What is your Target Confidence Level' of setup page is enabled, not required, drop down and has text 'Final (0%)'
    And 'Upload RFx Files' button is displayed
    And page with name 'Setup page' is opened
    And info: ---5. Verify contents of Opportunity, CLient, & Your Organization section---
    And I verify 'Your Company' of setup page is enabled, required, drop down and is empty
    And I verify 'Leading Site or Department' of setup page is enabled, required, drop down and is empty
    And I verify 'Profit Center' of setup page is enabled, not required, drop down and is empty
#    And I verify 'Cost Center' of setup page is enabled, not required, drop down and is empty
    And I verify 'Leading Company Currency' of setup page is enabled, required, free text and has text '$ USA - US Dollar(USD)'
    And 'Edit Rates' link is displayed
#    And I verify 'Customer Currency' of setup page is enabled, required, drop down and is empty
    And I verify 'Other Departments or Sites Involved' of setup page is enabled, not required, drop down and is empty
    And I verify 'Client/Customer (Sell-to)' of setup page is enabled, required, drop down and is empty
#    And I verify 'Other Departments or Sites Involved' of setup page is enabled, not required, drop down and is empty
#    And I verify 'Labor Price Book' of setup page is enabled, not required, drop down and is empty
#    And I verify 'Customer Contact' of setup page is enabled, not required, drop down and is empty
#    And I verify 'Opportunity Status' of setup page is enabled, not required, free text and is empty
#    And I verify 'Customer Purchase order' of setup page is enabled, not required, free text and is empty
#    And I verify 'Proposal Authorization#' of setup page is enabled, not required, free text and is empty
    And I verify 'Target - Price to Win' of setup page is enabled, not required, free text and has text '0.00'
    And page with name 'Setup page' is opened
    And info: ---6. Verify Re-use Prior Proposal or Template section ---
    And I verify 'Select a template' of setup page is enabled, not required, radio buttons and is checked
    And I verify 'Select a prior project' of setup page is enabled, not required, radio buttons and is unchecked
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
