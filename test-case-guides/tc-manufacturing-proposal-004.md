# TC-Manufacturing-Proposal-004 - Verify Estimate Layout

| Field | Value |
| --- | --- |
| Test ID | TC-Manufacturing-Proposal-004 |
| Title | Verify Estimate Layout |
| RTA page | https://twenty5.atlassian.net/wiki/spaces/RTA/pages/230719490/TC-Manufacturing-Proposal-004+Verify+Estimate+Layout |
| Map source | `RTA_TEST_SUITE_FEATURE_MAP.md` |

## Run Instructions

When a consultant asks to run this test, use the default app URL unless they provide another URL:

```text
https://approuter-twenty5ipe-dev.cfapps.us10.hana.ondemand.com/#quote
```

For Claude Desktop, ask before launching Selenium Chrome with the default URL. If the user provides another URL, launch that URL instead.

```bash
scripts/start-debug-chrome.sh "https://approuter-twenty5ipe-dev.cfapps.us10.hana.ondemand.com/#quote"
scripts/run-twentyfive-test.sh @TCM-004
```

Duplicate feature matches exist for this Confluence page. The tag command may run more than one matching scenario until the canonical feature file is confirmed.

## Default mapped Selenium scenario

| Field | Value |
| --- | --- |
| Feature | 4. Setup Manufacturing Proposal |
| Scenario | TC-Manufacturing-Proposal-004: Verify Estimate Layout |
| Tags | `@TCM-004 @END` |
| File | `imported/twentyfive-regtest/tests/src/test/resources/features/testfolder/manafacturing_proposal_setup.feature:410` |

### Scenario Sections

#### 1. Click Open in the Work Package field

- I click 'Open' in table 'WBS' table for row with data:
- I switch to tab 2
- page with name 'Estimate Labor' is opened
- I see the following TABS in the 'Estimates' Top Menu_-Warning-_:

#### 2. Click Open in the Work Package field

- text of 'Labor' widget equals 'USD 0'
- text of 'Travel' widget equals 'USD 0'
- text of 'Other' widget equals 'USD 0'
- text of 'Total Effort' widget equals '0 hours'
- 'Status' widget is displayed
- text of 'Cost & Price' status equals 'Updated'_-Warning-_
- 'Update Estimate totals' link is displayed

#### 3. If not already selected, select the view "Regression Test - Manufacturing"

- I select 'Test View for Regression' in the 'View' dropdown
- page with name 'Estimate Labor' is opened
- I select 'Regression Test - Manufacturing' in the 'View' dropdown
- I click on 'Save' button
- text of 'View' dropdown equals 'View: Regression Test - Manufacturing (shared & preferred)'

#### 4. If not already selected, select the view "Regression Test - Manufacturing"

- number of rows in 'Labor' table equals 0
- 'No records found, click here to add' button is displayed

#### 5. Click Other tab,If not already selected, select the view "Regression Test - Manufacturing"

- I click on 'Other' tab
- page with name 'Estimate Other' is opened
- I select 'Regression Test - Manufacturing' in the 'View' dropdown
- I click on 'Save' button
- text of 'View' dropdown equals 'View: Regression Test - Manufacturing (shared & preferred)'
- number of rows in 'Other' table equals 0
- 'No records found, click here to add' button is displayed

#### 6. Click Proposal BOM tab,If not already selected, select the view "Regression Test - Manufacturing"

- I click on 'Proposal BOM' tab
- page with name 'Estimate Proposal BOM' is opened
- I select 'Regression Test - Manufacturing' in the 'View' dropdown
- I click on 'Save' button
- text of 'View' dropdown equals 'View: Regression Test - Manufacturing (shared & preferred)'
- number of rows in 'Proposal BOM' table equals 0
- 'No records found, click here to add' button is displayed

#### 7. Click Procurement & Production tab,If not already selected, select the view "Regression Test - Manufacturing"

- I click on 'Procurement & Production' tab
- page with name 'Estimate Procurement & Production' is opened
- I select 'Regression Test - Manufacturing' in the 'View' dropdown
- I click on 'Save' button
- text of 'View' dropdown equals 'View: Regression Test - Manufacturing (shared & preferred)'
- number of rows in 'Procurement & Production' table equals 0
- 'No records found, click here to add' button is displayed

### Gherkin Excerpt

```gherkin
  Scenario: TC-Manufacturing-Proposal-004: Verify Estimate Layout
    And info: ---1. Click Open in the Work Package field---
    And I click 'Open' in table 'WBS' table for row with data:
      | WBS Code              | 1                   |
    Then I switch to tab 2
    And page with name 'Estimate Labor' is opened
#    And I refresh page
#    And page with name 'Estimate Labor' is opened
    Then I see the following TABS in the 'Estimates' Top Menu_-Warning-_:
      | Labor                       |
      | Other                       |
      | Proposal BOM                |
      | Procurement & Production    |
      | Risks & Contingency         |
      | Work Packages               |
      | Workflow                    |
    And info: ---2. Click Open in the Work Package field---
    And text of 'Labor' widget equals 'USD 0'
    And text of 'Travel' widget equals 'USD 0'
    And text of 'Other' widget equals 'USD 0'
    And text of 'Total Effort' widget equals '0 hours'
    And 'Status' widget is displayed
    And text of 'Cost & Price' status equals 'Updated'_-Warning-_
    And 'Update Estimate totals' link is displayed
    And info: ---3. If not already selected, select the view "Regression Test - Manufacturing"---
    When I select 'Test View for Regression' in the 'View' dropdown
    And page with name 'Estimate Labor' is opened
    When I select 'Regression Test - Manufacturing' in the 'View' dropdown
    And I click on 'Save' button
    Then text of 'View' dropdown equals 'View: Regression Test - Manufacturing (shared & preferred)'
    And info: ---4. If not already selected, select the view "Regression Test - Manufacturing"---
    And number of rows in 'Labor' table equals 0
    And 'No records found, click here to add' button is displayed
    And info: ---5. Click Other tab,If not already selected, select the view "Regression Test - Manufacturing"---
    And I click on 'Other' tab
    And page with name 'Estimate Other' is opened
    When I select 'Regression Test - Manufacturing' in the 'View' dropdown
    And I click on 'Save' button
    Then text of 'View' dropdown equals 'View: Regression Test - Manufacturing (shared & preferred)'
    And number of rows in 'Other' table equals 0
    And 'No records found, click here to add' button is displayed
    And info: ---6. Click Proposal BOM tab,If not already selected, select the view "Regression Test - Manufacturing"---
    And I click on 'Proposal BOM' tab
    And page with name 'Estimate Proposal BOM' is opened
    When I select 'Regression Test - Manufacturing' in the 'View' dropdown
    And I click on 'Save' button
    Then text of 'View' dropdown equals 'View: Regression Test - Manufacturing (shared & preferred)'
    And number of rows in 'Proposal BOM' table equals 0
    And 'No records found, click here to add' button is displayed
    And info: ---7. Click Procurement & Production tab,If not already selected, select the view "Regression Test - Manufacturing"---
    And I click on 'Procurement & Production' tab
    And page with name 'Estimate Procurement & Production' is opened
    When I select 'Regression Test - Manufacturing' in the 'View' dropdown
    And I click on 'Save' button
    Then text of 'View' dropdown equals 'View: Regression Test - Manufacturing (shared & preferred)'
    And number of rows in 'Procurement & Production' table equals 0
    And 'No records found, click here to add' button is displayed
```

## Alternate mapped Selenium scenario 2

| Field | Value |
| --- | --- |
| Feature | 1. Setup Manufacturing Proposal |
| Scenario | TC-Manufacturing-Proposal-004: Verify Estimate Layout |
| Tags | `@TCM-004 @END` |
| File | `imported/twentyfive-regtest/tests/src/test/resources/features/testfolder/twentyfive2_4_setup_manufacturing_proposal.feature:417` |

### Scenario Sections

#### 1. Click Open in the Work Package field

- I click 'Open' in table 'WBS' table for row with data:
- I switch to tab 2
- page with name 'Main page' is opened
- I click on 'Labor' tab
- I select 'Regression Test - Manufacturing' in the 'View' dropdown
- I click on 'Save' button
- text of 'View' dropdown equals 'View: Regression Test - Manufacturing'
- page with name 'Estimate Labor' is opened
- I see the following TABS in the 'Estimates' Top Menu_-Warning-_:

#### 2. Click Open in the Work Package field

- text of 'Labor' widget equals 'USD 0'
- text of 'Travel' widget equals 'USD 0'
- text of 'Other' widget equals 'USD 0'
- text of 'Total Effort' widget equals '0 hours'
- 'Status' widget is displayed
- text of 'Cost & Price' status equals 'Updated'_-Warning-_
- 'Update Estimate totals' link is displayed

#### 3. If not already selected, select the view "Regression Test - Manufacturing"

- I select 'Test View for Regression' in the 'View' dropdown
- page with name 'Estimate Labor' is opened
- I select 'Regression Test - Manufacturing' in the 'View' dropdown
- I click on 'Save' button
- text of 'View' dropdown equals 'View: Regression Test - Manufacturing (shared & preferred)'

#### 4. If not already selected, select the view "Regression Test - Manufacturing"

- number of rows in 'Labor' table equals 0
- 'No records found, click here to add' button is displayed

#### 5. Click Other tab,If not already selected, select the view "Regression Test - Manufacturing"

- I click on 'Other' tab
- page with name 'Estimate Other' is opened
- I select 'Regression Test - Manufacturing' in the 'View' dropdown
- I click on 'Save' button
- text of 'View' dropdown equals 'View: Regression Test - Manufacturing (shared & preferred)'
- number of rows in 'Other' table equals 0
- 'No records found, click here to add' button is displayed

#### 6. Click Proposal BOM tab,If not already selected, select the view "Regression Test - Manufacturing"

- I click on 'Proposal BOM' tab
- page with name 'Estimate Proposal BOM' is opened
- I select 'Regression Test - Manufacturing' in the 'View' dropdown
- I click on 'Save' button
- text of 'View' dropdown equals 'View: Regression Test - Manufacturing (shared & preferred)'
- number of rows in 'Proposal BOM' table equals 0
- 'No records found, click here to add' button is displayed

#### 7. Click Procurement & Production tab,If not already selected, select the view "Regression Test - Manufacturing"

- I click on 'Procurement & Production' tab
- page with name 'Estimate Procurement & Production' is opened
- I select 'Regression Test - Manufacturing' in the 'View' dropdown
- I click on 'Save' button
- text of 'View' dropdown equals 'View: Regression Test - Manufacturing (shared & preferred)'
- number of rows in 'Procurement & Production' table equals 0
- 'No records found, click here to add' button is displayed

### Gherkin Excerpt

```gherkin
  Scenario: TC-Manufacturing-Proposal-004: Verify Estimate Layout
    And info: ---1. Click Open in the Work Package field---
    And I click 'Open' in table 'WBS' table for row with data:
      | WBS Code              | 1                   |
    Then I switch to tab 2
    Then page with name 'Main page' is opened
    And I click on 'Labor' tab
    When I select 'Regression Test - Manufacturing' in the 'View' dropdown
    And I click on 'Save' button
    Then text of 'View' dropdown equals 'View: Regression Test - Manufacturing'
    And page with name 'Estimate Labor' is opened
#    And I refresh page
#    And page with name 'Estimate Labor' is opened
    Then I see the following TABS in the 'Estimates' Top Menu_-Warning-_:
      | Assumptions |
      | Bom         |
      | Comments     |
      | Due By       |
      | History      |
      | Labor        |
      | Material     |
      | Other        |
      | Parameters   |
      | Requirements |
      | Risks         |
      | Work         |
    And info: ---2. Click Open in the Work Package field---
    And text of 'Labor' widget equals 'USD 0'
    And text of 'Travel' widget equals 'USD 0'
    And text of 'Other' widget equals 'USD 0'
    And text of 'Total Effort' widget equals '0 hours'
    And 'Status' widget is displayed
    And text of 'Cost & Price' status equals 'Updated'_-Warning-_
    And 'Update Estimate totals' link is displayed
    And info: ---3. If not already selected, select the view "Regression Test - Manufacturing"---
    When I select 'Test View for Regression' in the 'View' dropdown
    And page with name 'Estimate Labor' is opened
    When I select 'Regression Test - Manufacturing' in the 'View' dropdown
    And I click on 'Save' button
    Then text of 'View' dropdown equals 'View: Regression Test - Manufacturing (shared & preferred)'
    And info: ---4. If not already selected, select the view "Regression Test - Manufacturing"---
    And number of rows in 'Labor' table equals 0
    And 'No records found, click here to add' button is displayed
    And info: ---5. Click Other tab,If not already selected, select the view "Regression Test - Manufacturing"---
    And I click on 'Other' tab
    And page with name 'Estimate Other' is opened
    When I select 'Regression Test - Manufacturing' in the 'View' dropdown
    And I click on 'Save' button
    Then text of 'View' dropdown equals 'View: Regression Test - Manufacturing (shared & preferred)'
    And number of rows in 'Other' table equals 0
    And 'No records found, click here to add' button is displayed
    And info: ---6. Click Proposal BOM tab,If not already selected, select the view "Regression Test - Manufacturing"---
    And I click on 'Proposal BOM' tab
    And page with name 'Estimate Proposal BOM' is opened
    When I select 'Regression Test - Manufacturing' in the 'View' dropdown
    And I click on 'Save' button
    Then text of 'View' dropdown equals 'View: Regression Test - Manufacturing (shared & preferred)'
    And number of rows in 'Proposal BOM' table equals 0
    And 'No records found, click here to add' button is displayed
    And info: ---7. Click Procurement & Production tab,If not already selected, select the view "Regression Test - Manufacturing"---
    And I click on 'Procurement & Production' tab
    And page with name 'Estimate Procurement & Production' is opened
    When I select 'Regression Test - Manufacturing' in the 'View' dropdown
    And I click on 'Save' button
    Then text of 'View' dropdown equals 'View: Regression Test - Manufacturing (shared & preferred)'
    And number of rows in 'Procurement & Production' table equals 0
    And 'No records found, click here to add' button is displayed
```

---

_Generated by `scripts/generate-testcase-guides.py` from the RTA mapping and local feature files._
