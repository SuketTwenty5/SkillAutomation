# TC-Manufacturing-Proposal-009 - Create Overhead Cost

| Field | Value |
| --- | --- |
| Test ID | TC-Manufacturing-Proposal-009 |
| Title | Create Overhead Cost |
| RTA page | https://twenty5.atlassian.net/wiki/spaces/RTA/pages/260702209/TC-Manufacturing-Proposal-009+Create+Overhead+Cost |
| Map source | `RTA_TEST_SUITE_FEATURE_MAP.md` |

## Run Instructions

When a consultant asks to run this test, use the default app URL unless they provide another URL:

```text
https://approuter-twenty5ipe-dev.cfapps.us10.hana.ondemand.com/#quote
```

For Claude Desktop, ask before launching Selenium Chrome with the default URL. If the user provides another URL, launch that URL instead.

```bash
scripts/start-debug-chrome.sh "https://approuter-twenty5ipe-dev.cfapps.us10.hana.ondemand.com/#quote"
scripts/run-twentyfive-test.sh @TCM-009
```

Duplicate feature matches exist for this Confluence page. The tag command may run more than one matching scenario until the canonical feature file is confirmed.

## Default mapped Selenium scenario

| Field | Value |
| --- | --- |
| Feature | 6. Fringe, G&A, Overhead Cost Creation for Manufacturing Proposal |
| Scenario | TC-Manufacturing-Proposal-009: Create Overhead Cost |
| Tags | `@TCM-009 @RUN` |
| File | `imported/twentyfive-regtest/tests/src/test/resources/features/testfolder/Overhead_Cost_Creation_Manufacturing_Proposal.feature:64` |

### Scenario Sections

#### 1. Navigate to the Procurement & Production tab of the Estimate

- I click on 'Procurement & Production' tab
- page with name 'Estimate Procurement & Production' is opened
- I select 'Regression Test - Manufacturing' in the 'View' dropdown
- I click on 'Save' button
- text of 'View' dropdown equals 'View: Regression Test - Manufacturing'
- page with name 'Estimate Procurement & Production' is opened

#### 2. In the Overhead Formula field of the REG_MATERIAL1 row select Test Data Value

- I click 'Overhead Formula' in table 'Procurement & Production' table in row number 1
- 'Confirm Formula, Define Local Parameters' popUp is displayed
- 'Purchased Parts Base' parameter is displayed
- 'Material & Equipment Burden Rate' parameter is displayed

#### 3. Enter the Test Data Value for the Material & Equipment Burden Rate field

- I set value '0.0' to the cell of the column 'Value' of the 'Formula' table for the row with the following data:
- I wait for 7 seconds
- text of 'Dialog Result' input equals '0'

#### 4. Click Confirm button

- I click on 'Dialog Confirm' button
- 'Confirm Formula, Define Local Parameters' popUp is not displayed
- page with name 'Estimate Procurement & Production' is opened
- 'Procurement & Production' table contain row with following data:

#### 5. Click the Pencil icon in the Overhead Formula field of the REG_MATERIAL1 row

- I click 'Overhead Formula' in table 'Procurement & Production' table in row number 1
- 'Confirm Formula, Define Local Parameters' popUp is displayed
- 'Formula' table contain row with following data:

#### 6. Enter the Test Data Value for the Material & Equipment Burden Rate field

- I set value '0.00' to the cell of the column 'Value' of the 'Formula' table for the row with the following data:
- I wait for 7 seconds
- I set value '0.07' to the cell of the column 'Value' of the 'Formula' table for the row with the following data:
- I wait for 7 seconds
- text of 'Dialog Result' input equals '8400'

#### 7. Click Confirm button

- I click on 'Dialog Confirm' button
- 'Confirm Formula, Define Local Parameters' popUp is not displayed
- I click on 'Save' button
- I wait for 10 seconds
- I click on 'Update Estimate totals' link
- I wait for 35 seconds
- page with name 'Estimate Procurement & Production' is opened
- 'Procurement & Production' table contain row with following data:

#### 8. In the Overhead Formula field of the REG_MATERIAL1 Copy row select Test Data Value

- I click 'Overhead Formula' in table 'Procurement & Production' table in row number 2
- I set value '0.00' to the cell of the column 'Value' of the 'Formula' table for the row with the following data:
- I wait for 7 seconds
- I set value '0.07' to the cell of the column 'Value' of the 'Formula' table for the row with the following data:
- 'Confirm Formula, Define Local Parameters' popUp is displayed
- 'Formula' table contain row with following data:
- 'Formula' table contain row with following data:
- text of 'Dialog Result' input equals '4200'

#### 9. Click Confirm button

- I click on 'Dialog Confirm' button
- 'Confirm Formula, Define Local Parameters' popUp is not displayed
- I wait for 7 seconds
- page with name 'Estimate Procurement & Production' is opened
- 'Procurement & Production' table contain row with following data:

#### 10. Verify for REG_MATERIAL2 row that: Field Overhead Formula is empty and Field Overhead Cost = $ 0.00

- 'Procurement & Production' table contain row with following data:

#### 11. Click Save button

- I click on 'Save' button
- page with name 'Estimate Procurement & Production' is opened

#### 12. In the Status widget click Update Estimate Totals

- 'Procurement & Production' table contain row with following data:
- 'Procurement & Production' table contain row with following data:
- 'Procurement & Production' table contain row with following data:

### Gherkin Excerpt

```gherkin
  Scenario: TC-Manufacturing-Proposal-009: Create Overhead Cost
    And info: ---1. Navigate to the Procurement & Production tab of the Estimate---
    And I click on 'Procurement & Production' tab
    And page with name 'Estimate Procurement & Production' is opened
    When I select 'Regression Test - Manufacturing' in the 'View' dropdown
    And I click on 'Save' button
    Then text of 'View' dropdown equals 'View: Regression Test - Manufacturing'
    And page with name 'Estimate Procurement & Production' is opened
#    Then Grand total cell value of the 'Cost in Company Currency' column of the 'Procurement & Production' table equals '$ 261,000.00'
    And info: ---2. In the Overhead Formula field of the REG_MATERIAL1 row select Test Data Value---
    And I click 'Overhead Formula' in table 'Procurement & Production' table in row number 1
    And 'Confirm Formula, Define Local Parameters' popUp is displayed
    And 'Purchased Parts Base' parameter is displayed
    And 'Material & Equipment Burden Rate' parameter is displayed
    And info: ---3. Enter the Test Data Value for the Material & Equipment Burden Rate field---
    And I set value '0.0' to the cell of the column 'Value' of the 'Formula' table for the row with the following data:
      | Parameter | Material & Equipment Burden Rate |
    Then I wait for 7 seconds
    And text of 'Dialog Result' input equals '0'
    And info: ---4. Click Confirm button---
    And I click on 'Dialog Confirm' button
    And 'Confirm Formula, Define Local Parameters' popUp is not displayed
    And page with name 'Estimate Procurement & Production' is opened
    And 'Procurement & Production' table contain row with following data:
      | Description              | REG_MATERIAL1   |
      | Overhead Cost            | $ 0.00          |
    And info: ---5. Click the Pencil icon in the Overhead Formula field of the REG_MATERIAL1 row---
    And I click 'Overhead Formula' in table 'Procurement & Production' table in row number 1
    And 'Confirm Formula, Define Local Parameters' popUp is displayed
    And 'Formula' table contain row with following data:
      | Parameter                | Purchased Parts Base (USD)   |
      | Value                    | 120000                       |
    And info: ---6. Enter the Test Data Value for the Material & Equipment Burden Rate field---
    And I set value '0.00' to the cell of the column 'Value' of the 'Formula' table for the row with the following data:
      | Parameter | Material & Equipment Burden Rate |
    Then I wait for 7 seconds
    And I set value '0.07' to the cell of the column 'Value' of the 'Formula' table for the row with the following data:
      | Parameter | Material & Equipment Burden Rate |
    Then I wait for 7 seconds
    And text of 'Dialog Result' input equals '8400'
    And info: ---7. Click Confirm button---
    And I click on 'Dialog Confirm' button
    And 'Confirm Formula, Define Local Parameters' popUp is not displayed
    And I click on 'Save' button
    Then I wait for 10 seconds
    And I click on 'Update Estimate totals' link
    Then I wait for 35 seconds
    And page with name 'Estimate Procurement & Production' is opened
    And 'Procurement & Production' table contain row with following data:
      | Description              | REG_MATERIAL1   |
      | Overhead Cost            | $ 8,400.00      |
    And info: ---8. In the Overhead Formula field of the REG_MATERIAL1 Copy row select Test Data Value---
    And I click 'Overhead Formula' in table 'Procurement & Production' table in row number 2
    And I set value '0.00' to the cell of the column 'Value' of the 'Formula' table for the row with the following data:
      | Parameter | Material & Equipment Burden Rate |
    Then I wait for 7 seconds
    And I set value '0.07' to the cell of the column 'Value' of the 'Formula' table for the row with the following data:
      | Parameter | Material & Equipment Burden Rate |
    And 'Confirm Formula, Define Local Parameters' popUp is displayed
    And 'Formula' table contain row with following data:
      | Parameter                | Purchased Parts Base (USD)   |
      | Value                    | 60000                        |
    And 'Formula' table contain row with following data:
      | Parameter                | Material & Equipment Burden Rate   |
      | Value                    | 0.07                               |
    And text of 'Dialog Result' input equals '4200'
    And info: ---9. Click Confirm button---
    And I click on 'Dialog Confirm' button
    And 'Confirm Formula, Define Local Parameters' popUp is not displayed
    Then I wait for 7 seconds
    And page with name 'Estimate Procurement & Production' is opened
    And 'Procurement & Production' table contain row with following data:
      | Description              | REG_MATERIAL1 Copy   |
      | Overhead Cost            | $ 4,200.00           |
    And info: ---10. Verify for REG_MATERIAL2 row that: Field Overhead Formula is empty and Field Overhead Cost = $ 0.00---
    And 'Procurement & Production' table contain row with following data:
      | Description              | REG_MATERIAL2        |
      | Overhead Formula         |                      |
      | Overhead Cost            | $ 0.00               |
    And info: ---11. Click Save button---
    And I click on 'Save' button
    And page with name 'Estimate Procurement & Production' is opened
    And info: ---12. In the Status widget click Update Estimate Totals---
    And 'Procurement & Production' table contain row with following data:
      | Description              | REG_MATERIAL1                    |
      | Overhead Formula         | Regression - Purchasing Burden   |
      | Overhead Cost            | $ 8,400.00                       |
    And 'Procurement & Production' table contain row with following data:
      | Description              | REG_MATERIAL1 Copy              |
      | Overhead Formula         | Regression - Purchasing Burden  |
      | Overhead Cost            | $ 4,200.00                      |
    And 'Procurement & Production' table contain row with following data:
      | Description              | REG_MATERIAL2        |
      | Overhead Formula         |                      |
      | Overhead Cost            | $ 0.00               |

```

## Alternate mapped Selenium scenario 2

| Field | Value |
| --- | --- |
| Feature | 3. Fringe, G&A, Overhead Cost Creation for Manufacturing Proposal |
| Scenario | TC-Manufacturing-Proposal-009: Create Overhead Cost |
| Tags | `@TCM-009 @RUN` |
| File | `imported/twentyfive-regtest/tests/src/test/resources/features/testfolder/twentyfive2_4_create_fringe__overhead__ga_cost.feature:64` |

### Scenario Sections

#### 1. Navigate to the Procurement & Production tab of the Estimate

- I click on 'Procurement & Production' tab
- page with name 'Estimate Procurement & Production' is opened
- I select 'Regression Test - Manufacturing' in the 'View' dropdown
- I click on 'Save' button
- text of 'View' dropdown equals 'View: Regression Test - Manufacturing'
- page with name 'Estimate Procurement & Production' is opened

#### 2. In the Overhead Formula field of the REG_MATERIAL1 row select Test Data Value

- I click 'Overhead Formula' in table 'Procurement & Production' table in row number 1
- 'Confirm Formula, Define Local Parameters' popUp is displayed
- 'Purchased Parts Base' parameter is displayed
- 'Material & Equipment Burden Rate' parameter is displayed

#### 3. Enter the Test Data Value for the Material & Equipment Burden Rate field

- I set value '0.0' to the cell of the column 'Value' of the 'Formula' table for the row with the following data:
- I wait for 7 seconds
- text of 'Dialog Result' input equals '0'

#### 4. Click Confirm button

- I click on 'Dialog Confirm' button
- 'Confirm Formula, Define Local Parameters' popUp is not displayed
- page with name 'Estimate Procurement & Production' is opened
- 'Procurement & Production' table contain row with following data:

#### 5. Click the Pencil icon in the Overhead Formula field of the REG_MATERIAL1 row

- I click 'Overhead Formula' in table 'Procurement & Production' table in row number 1
- 'Confirm Formula, Define Local Parameters' popUp is displayed
- 'Formula' table contain row with following data:

#### 6. Enter the Test Data Value for the Material & Equipment Burden Rate field

- I set value '0.00' to the cell of the column 'Value' of the 'Formula' table for the row with the following data:
- I wait for 7 seconds
- I set value '0.07' to the cell of the column 'Value' of the 'Formula' table for the row with the following data:
- I wait for 7 seconds
- text of 'Dialog Result' input equals '8400'

#### 7. Click Confirm button

- I click on 'Dialog Confirm' button
- 'Confirm Formula, Define Local Parameters' popUp is not displayed
- I click on 'Save' button
- I wait for 10 seconds
- I click on 'Update Estimate totals' link
- I wait for 35 seconds
- page with name 'Estimate Procurement & Production' is opened
- 'Procurement & Production' table contain row with following data:

#### 8. In the Overhead Formula field of the REG_MATERIAL1 Copy row select Test Data Value

- I click 'Overhead Formula' in table 'Procurement & Production' table in row number 2
- I set value '0.00' to the cell of the column 'Value' of the 'Formula' table for the row with the following data:
- I wait for 7 seconds
- I set value '0.07' to the cell of the column 'Value' of the 'Formula' table for the row with the following data:
- 'Confirm Formula, Define Local Parameters' popUp is displayed
- 'Formula' table contain row with following data:
- 'Formula' table contain row with following data:
- text of 'Dialog Result' input equals '4200'

#### 9. Click Confirm button

- I click on 'Dialog Confirm' button
- 'Confirm Formula, Define Local Parameters' popUp is not displayed
- I wait for 7 seconds
- page with name 'Estimate Procurement & Production' is opened
- 'Procurement & Production' table contain row with following data:

#### 10. Verify for REG_MATERIAL2 row that: Field Overhead Formula is empty and Field Overhead Cost = $ 0.00

- 'Procurement & Production' table contain row with following data:

#### 11. Click Save button

- I click on 'Save' button
- page with name 'Estimate Procurement & Production' is opened

#### 12. In the Status widget click Update Estimate Totals

- 'Procurement & Production' table contain row with following data:
- 'Procurement & Production' table contain row with following data:
- 'Procurement & Production' table contain row with following data:

### Gherkin Excerpt

```gherkin
  Scenario: TC-Manufacturing-Proposal-009: Create Overhead Cost
    And info: ---1. Navigate to the Procurement & Production tab of the Estimate---
    And I click on 'Procurement & Production' tab
    And page with name 'Estimate Procurement & Production' is opened
    When I select 'Regression Test - Manufacturing' in the 'View' dropdown
    And I click on 'Save' button
    Then text of 'View' dropdown equals 'View: Regression Test - Manufacturing'
    And page with name 'Estimate Procurement & Production' is opened
#    Then Grand total cell value of the 'Cost in Company Currency' column of the 'Procurement & Production' table equals '$ 261,000.00'
    And info: ---2. In the Overhead Formula field of the REG_MATERIAL1 row select Test Data Value---
    And I click 'Overhead Formula' in table 'Procurement & Production' table in row number 1
    And 'Confirm Formula, Define Local Parameters' popUp is displayed
    And 'Purchased Parts Base' parameter is displayed
    And 'Material & Equipment Burden Rate' parameter is displayed
    And info: ---3. Enter the Test Data Value for the Material & Equipment Burden Rate field---
    And I set value '0.0' to the cell of the column 'Value' of the 'Formula' table for the row with the following data:
      | Parameter | Material & Equipment Burden Rate |
    Then I wait for 7 seconds
    And text of 'Dialog Result' input equals '0'
    And info: ---4. Click Confirm button---
    And I click on 'Dialog Confirm' button
    And 'Confirm Formula, Define Local Parameters' popUp is not displayed
    And page with name 'Estimate Procurement & Production' is opened
    And 'Procurement & Production' table contain row with following data:
      | Description              | REG_MATERIAL1   |
      | Overhead Cost            | $ 0.00          |
    And info: ---5. Click the Pencil icon in the Overhead Formula field of the REG_MATERIAL1 row---
    And I click 'Overhead Formula' in table 'Procurement & Production' table in row number 1
    And 'Confirm Formula, Define Local Parameters' popUp is displayed
    And 'Formula' table contain row with following data:
      | Parameter                | Purchased Parts Base (USD)   |
      | Value                    | 120000                       |
    And info: ---6. Enter the Test Data Value for the Material & Equipment Burden Rate field---
    And I set value '0.00' to the cell of the column 'Value' of the 'Formula' table for the row with the following data:
      | Parameter | Material & Equipment Burden Rate |
    Then I wait for 7 seconds
    And I set value '0.07' to the cell of the column 'Value' of the 'Formula' table for the row with the following data:
      | Parameter | Material & Equipment Burden Rate |
    Then I wait for 7 seconds
    And text of 'Dialog Result' input equals '8400'
    And info: ---7. Click Confirm button---
    And I click on 'Dialog Confirm' button
    And 'Confirm Formula, Define Local Parameters' popUp is not displayed
    And I click on 'Save' button
    Then I wait for 10 seconds
    And I click on 'Update Estimate totals' link
    Then I wait for 35 seconds
    And page with name 'Estimate Procurement & Production' is opened
    And 'Procurement & Production' table contain row with following data:
      | Description              | REG_MATERIAL1   |
      | Overhead Cost            | $ 8,400.00      |
    And info: ---8. In the Overhead Formula field of the REG_MATERIAL1 Copy row select Test Data Value---
    And I click 'Overhead Formula' in table 'Procurement & Production' table in row number 2
    And I set value '0.00' to the cell of the column 'Value' of the 'Formula' table for the row with the following data:
      | Parameter | Material & Equipment Burden Rate |
    Then I wait for 7 seconds
    And I set value '0.07' to the cell of the column 'Value' of the 'Formula' table for the row with the following data:
      | Parameter | Material & Equipment Burden Rate |
    And 'Confirm Formula, Define Local Parameters' popUp is displayed
    And 'Formula' table contain row with following data:
      | Parameter                | Purchased Parts Base (USD)   |
      | Value                    | 60000                        |
    And 'Formula' table contain row with following data:
      | Parameter                | Material & Equipment Burden Rate   |
      | Value                    | 0.07                               |
    And text of 'Dialog Result' input equals '4200'
    And info: ---9. Click Confirm button---
    And I click on 'Dialog Confirm' button
    And 'Confirm Formula, Define Local Parameters' popUp is not displayed
    Then I wait for 7 seconds
    And page with name 'Estimate Procurement & Production' is opened
    And 'Procurement & Production' table contain row with following data:
      | Description              | REG_MATERIAL1 Copy   |
      | Overhead Cost            | $ 4,200.00           |
    And info: ---10. Verify for REG_MATERIAL2 row that: Field Overhead Formula is empty and Field Overhead Cost = $ 0.00---
    And 'Procurement & Production' table contain row with following data:
      | Description              | REG_MATERIAL2        |
      | Overhead Formula         |                      |
      | Overhead Cost            | $ 0.00               |
    And info: ---11. Click Save button---
    And I click on 'Save' button
    And page with name 'Estimate Procurement & Production' is opened
    And info: ---12. In the Status widget click Update Estimate Totals---
    And 'Procurement & Production' table contain row with following data:
      | Description              | REG_MATERIAL1                    |
      | Overhead Formula         | Regression - Purchasing Burden   |
      | Overhead Cost            | $ 8,400.00                       |
    And 'Procurement & Production' table contain row with following data:
      | Description              | REG_MATERIAL1 Copy              |
      | Overhead Formula         | Regression - Purchasing Burden  |
      | Overhead Cost            | $ 4,200.00                      |
    And 'Procurement & Production' table contain row with following data:
      | Description              | REG_MATERIAL2        |
      | Overhead Formula         |                      |
      | Overhead Cost            | $ 0.00               |

```

---

_Generated by `scripts/generate-testcase-guides.py` from the RTA mapping and local feature files._
