# TC-Manufacturing-Proposal-016 - Material Inflation

| Field | Value |
| --- | --- |
| Test ID | TC-Manufacturing-Proposal-016 |
| Title | Material Inflation |
| RTA page | https://twenty5.atlassian.net/wiki/spaces/RTA/pages/385253377/TC-Manufacturing-Proposal-016+Material+Inflation |
| Map source | `RTA_TEST_SUITE_FEATURE_MAP.md` |

## Run Instructions

When a consultant asks to run this test, use the default app URL unless they provide another URL:

```text
https://approuter-twenty5ipe-dev.cfapps.us10.hana.ondemand.com/#quote
```

For Claude Desktop, ask before launching Selenium Chrome with the default URL. If the user provides another URL, launch that URL instead.

```bash
scripts/start-debug-chrome.sh "https://approuter-twenty5ipe-dev.cfapps.us10.hana.ondemand.com/#quote"
scripts/run-twentyfive-test.sh @TCM-016
```

Duplicate feature matches exist for this Confluence page. The tag command may run more than one matching scenario until the canonical feature file is confirmed.

## Default mapped Selenium scenario

| Field | Value |
| --- | --- |
| Feature | 9. Check that Labor Inflation is calculated correctly over the length of the proposal |
| Scenario | TC-Manufacturing-Proposal-016: Material Inflation |
| Tags | `@TCM-016 @END` |
| File | `imported/twentyfive-regtest/tests/src/test/resources/features/testfolder/laborInflationCalculatedCorrectly.feature:154` |

### Scenario Sections

#### Setup

- page with name 'Workbench page' is opened
- I switch back to previous tab
- I wait for 7 seconds

#### 1. Open the Procurement & Production tab of the Estimate from previous test case. The Procurement & Production tab is opened.

- I click on 'Procurement & Production' tab
- page with name 'Estimate Procurement & Production' is opened
- I select 'Regression Test - Manufacturing' in the 'View' dropdown
- I click on 'Save' button
- text of 'View' dropdown equals 'View: Regression Test - Manufacturing (preferred)'
- page with name 'Estimate Procurement & Production' is opened

#### 2. For the first row REG_MATERIAL1 enter Test Data Values for Start and End Dates. Start Date = 12/31/2030, End Date = 12/31/2030.

- I set value '12/31/2030' to the cell of the column 'Start' of the 'Procurement & Production' table for the row with the index '1'
- I wait for 3 seconds
- I set value '12/31/2030' to the cell of the column 'End' of the 'Procurement & Production' table for the row with the index '1'
- I wait for 3 seconds

#### 3. For the second row REG_MATERIAL1 enter Test Data Values for Start and End Dates. Start Date = 12/31/2031, End Date = 12/31/2031.

- I set value '12/31/2031' to the cell of the column 'Start' of the 'Procurement & Production' table for the row with the index '2'
- I wait for 3 seconds
- I set value '12/31/2031' to the cell of the column 'End' of the 'Procurement & Production' table for the row with the index '2'
- I wait for 3 seconds

#### 4. Click Save. Data is saved.

- I click on 'Save' button

#### 5. Open Cost Price Analysis via the top-right menu. The Cost Price Analysis is opened in a new browser tab with Workbench tab selected (if not, click Workbench tab to navigate).

- I switch back to previous tab
- I refresh page
- I click on 'Workbench' tab
- page with name 'Workbench page' is opened

#### 6. Select view Regression Test - Inflation. The view is selected, and the grid is displayed according to the view.

- I select 'Regression Test - Inflation' in the 'View' dropdown
- page with name 'Workbench page' is opened
- text of 'View' dropdown equals 'View: Regression Test - Inflation'

#### 7. In the Description filter enter Test Data Value. The Workbench Grid displays results of REG_MATERIAL1.

- I enter data into the next fields:
- page with name 'Workbench page' is opened
- I wait for 5 seconds

#### 8. Verify that for REG_MATERIAL1 row the following yearly amounts are displayed: 2030 - $6,000.00, 2031 - $18,600.00, 2032 - $39,390.00.

- 'Workbench' table contain row with following data:

### Gherkin Excerpt

```gherkin
  Scenario: TC-Manufacturing-Proposal-016: Material Inflation
    When page with name 'Workbench page' is opened
    Then I switch back to previous tab
    And I wait for 7 seconds
    And info: ---1. Open the Procurement & Production tab of the Estimate from previous test case. The Procurement & Production tab is opened.---
    And I click on 'Procurement & Production' tab
    And page with name 'Estimate Procurement & Production' is opened
    When I select 'Regression Test - Manufacturing' in the 'View' dropdown
    And I click on 'Save' button
    Then text of 'View' dropdown equals 'View: Regression Test - Manufacturing (preferred)'
    And page with name 'Estimate Procurement & Production' is opened
    And info: ---2. For the first row REG_MATERIAL1 enter Test Data Values for Start and End Dates. Start Date = 12/31/2030, End Date = 12/31/2030.---
    And I set value '12/31/2030' to the cell of the column 'Start' of the 'Procurement & Production' table for the row with the index '1'
    And I wait for 3 seconds
    And I set value '12/31/2030' to the cell of the column 'End' of the 'Procurement & Production' table for the row with the index '1'
    And I wait for 3 seconds
    And info: ---3. For the second row REG_MATERIAL1 enter Test Data Values for Start and End Dates. Start Date = 12/31/2031, End Date = 12/31/2031.---
    And I set value '12/31/2031' to the cell of the column 'Start' of the 'Procurement & Production' table for the row with the index '2'
    And I wait for 3 seconds
    And I set value '12/31/2031' to the cell of the column 'End' of the 'Procurement & Production' table for the row with the index '2'
    And I wait for 3 seconds
    And info: ---4. Click Save. Data is saved.---
    And I click on 'Save' button
#    And 'Data saved successfully' popUp is displayed
#    And 'Data saved successfully' warning message is displayed in 30 seconds
    And info: ---5. Open Cost Price Analysis via the top-right menu. The Cost Price Analysis is opened in a new browser tab with Workbench tab selected (if not, click Workbench tab to navigate).---
    Then I switch back to previous tab
    And I refresh page
    Then I click on 'Workbench' tab
    Then page with name 'Workbench page' is opened
    And info: ---6. Select view Regression Test - Inflation. The view is selected, and the grid is displayed according to the view.---
    When I select 'Regression Test - Inflation' in the 'View' dropdown
    Then page with name 'Workbench page' is opened
    Then text of 'View' dropdown equals 'View: Regression Test - Inflation'
    And info: ---7. In the Description filter enter Test Data Value. The Workbench Grid displays results of REG_MATERIAL1.---
    And I enter data into the next fields:
    | 'Description' filter | REG_MATERIAL1 |
    Then page with name 'Workbench page' is opened
    Then I wait for 5 seconds
    And info: ---8. Verify that for REG_MATERIAL1 row the following yearly amounts are displayed: 2030 - $6,000.00, 2031 - $18,600.00, 2032 - $39,390.00.---
    And 'Workbench' table contain row with following data:
      | Description                        | REG_MATERIAL1    |
      | 2030                               | $ 6,000.00       |
      | 2031                               | $ 18,600.00      |
      | 2032                               | $ 39,390.00      |
```

## Alternate mapped Selenium scenario 2

| Field | Value |
| --- | --- |
| Feature | 6. Check that Labor Inflation is calculated correctly over the length of the proposal |
| Scenario | TC-Manufacturing-Proposal-016: Material Inflation |
| Tags | `@TCM-016 @END` |
| File | `imported/twentyfive-regtest/tests/src/test/resources/features/testfolder/twentyfive2_4_inflation_labor__material_manufacturing_proposal.feature:153` |

### Scenario Sections

#### Setup

- page with name 'Workbench page' is opened
- I switch back to previous tab
- I wait for 7 seconds

#### 1. Open the Procurement & Production tab of the Estimate from previous test case. The Procurement & Production tab is opened.

- I click on 'Procurement & Production' tab
- page with name 'Estimate Procurement & Production' is opened
- I select 'Regression Test - Manufacturing' in the 'View' dropdown
- I click on 'Save' button
- text of 'View' dropdown equals 'View: Regression Test - Manufacturing (preferred)'
- page with name 'Estimate Procurement & Production' is opened

#### 2. For the first row REG_MATERIAL1 enter Test Data Values for Start and End Dates. Start Date = 12/31/2030, End Date = 12/31/2030.

- I set value '12/31/2030' to the cell of the column 'Start' of the 'Procurement & Production' table for the row with the index '1'
- I wait for 3 seconds
- I set value '12/31/2030' to the cell of the column 'End' of the 'Procurement & Production' table for the row with the index '1'
- I wait for 3 seconds

#### 3. For the second row REG_MATERIAL1 enter Test Data Values for Start and End Dates. Start Date = 12/31/2031, End Date = 12/31/2031.

- I set value '12/31/2031' to the cell of the column 'Start' of the 'Procurement & Production' table for the row with the index '2'
- I wait for 3 seconds
- I set value '12/31/2031' to the cell of the column 'End' of the 'Procurement & Production' table for the row with the index '2'
- I wait for 3 seconds

#### 4. Click Save. Data is saved.

- I click on 'Save' button

#### 5. Open Cost Price Analysis via the top-right menu. The Cost Price Analysis is opened in a new browser tab with Workbench tab selected (if not, click Workbench tab to navigate).

- I switch back to previous tab
- I refresh page
- I click on 'Workbench' tab
- page with name 'Workbench page' is opened

#### 6. Select view Regression Test - Inflation. The view is selected, and the grid is displayed according to the view.

- I select 'Regression Test - Inflation' in the 'View' dropdown
- page with name 'Workbench page' is opened
- text of 'View' dropdown equals 'View: Regression Test - Inflation'

#### 7. In the Description filter enter Test Data Value. The Workbench Grid displays results of REG_MATERIAL1.

- I enter data into the next fields:
- page with name 'Workbench page' is opened
- I wait for 5 seconds

#### 8. Verify that for REG_MATERIAL1 row the following yearly amounts are displayed: 2030 - $6,000.00, 2031 - $18,600.00, 2032 - $39,390.00.

- 'Workbench' table contain row with following data:

### Gherkin Excerpt

```gherkin
  Scenario: TC-Manufacturing-Proposal-016: Material Inflation
    When page with name 'Workbench page' is opened
    Then I switch back to previous tab
    And I wait for 7 seconds
    And info: ---1. Open the Procurement & Production tab of the Estimate from previous test case. The Procurement & Production tab is opened.---
    And I click on 'Procurement & Production' tab
    And page with name 'Estimate Procurement & Production' is opened
    When I select 'Regression Test - Manufacturing' in the 'View' dropdown
    And I click on 'Save' button
    Then text of 'View' dropdown equals 'View: Regression Test - Manufacturing (preferred)'
    And page with name 'Estimate Procurement & Production' is opened
    And info: ---2. For the first row REG_MATERIAL1 enter Test Data Values for Start and End Dates. Start Date = 12/31/2030, End Date = 12/31/2030.---
    And I set value '12/31/2030' to the cell of the column 'Start' of the 'Procurement & Production' table for the row with the index '1'
    And I wait for 3 seconds
    And I set value '12/31/2030' to the cell of the column 'End' of the 'Procurement & Production' table for the row with the index '1'
    And I wait for 3 seconds
    And info: ---3. For the second row REG_MATERIAL1 enter Test Data Values for Start and End Dates. Start Date = 12/31/2031, End Date = 12/31/2031.---
    And I set value '12/31/2031' to the cell of the column 'Start' of the 'Procurement & Production' table for the row with the index '2'
    And I wait for 3 seconds
    And I set value '12/31/2031' to the cell of the column 'End' of the 'Procurement & Production' table for the row with the index '2'
    And I wait for 3 seconds
    And info: ---4. Click Save. Data is saved.---
    And I click on 'Save' button
#    And 'Data saved successfully' popUp is displayed
#    And 'Data saved successfully' warning message is displayed in 30 seconds
    And info: ---5. Open Cost Price Analysis via the top-right menu. The Cost Price Analysis is opened in a new browser tab with Workbench tab selected (if not, click Workbench tab to navigate).---
    Then I switch back to previous tab
    And I refresh page
    Then I click on 'Workbench' tab
    Then page with name 'Workbench page' is opened
    And info: ---6. Select view Regression Test - Inflation. The view is selected, and the grid is displayed according to the view.---
    When I select 'Regression Test - Inflation' in the 'View' dropdown
    Then page with name 'Workbench page' is opened
    Then text of 'View' dropdown equals 'View: Regression Test - Inflation'
    And info: ---7. In the Description filter enter Test Data Value. The Workbench Grid displays results of REG_MATERIAL1.---
    And I enter data into the next fields:
      | 'Description' filter | REG_MATERIAL1 |
    Then page with name 'Workbench page' is opened
    Then I wait for 5 seconds
    And info: ---8. Verify that for REG_MATERIAL1 row the following yearly amounts are displayed: 2030 - $6,000.00, 2031 - $18,600.00, 2032 - $39,390.00.---
    And 'Workbench' table contain row with following data:
      | Description                        | REG_MATERIAL1    |
      | 2030                               | $ 6,000.00       |
      | 2031                               | $ 18,600.00      |
      | 2032                               | $ 39,390.00      |
```

---

_Generated by `scripts/generate-testcase-guides.py` from the RTA mapping and local feature files._
