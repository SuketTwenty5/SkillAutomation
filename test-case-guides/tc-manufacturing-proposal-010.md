# TC-Manufacturing-Proposal-010 - Create G A Cost

| Field | Value |
| --- | --- |
| Test ID | TC-Manufacturing-Proposal-010 |
| Title | Create G A Cost |
| RTA page | https://twenty5.atlassian.net/wiki/spaces/RTA/pages/260702268/TC-Manufacturing-Proposal-010+Create+G+A+Cost |
| Map source | `RTA_TEST_SUITE_FEATURE_MAP.md` |

## Run Instructions

When a consultant asks to run this test, use the default app URL unless they provide another URL:

```text
https://approuter-twenty5ipe-dev.cfapps.us10.hana.ondemand.com/#quote
```

For Claude Desktop, ask before launching Selenium Chrome with the default URL. If the user provides another URL, launch that URL instead.

```bash
scripts/start-debug-chrome.sh "https://approuter-twenty5ipe-dev.cfapps.us10.hana.ondemand.com/#quote"
scripts/run-twentyfive-test.sh @TCM-010
```

Duplicate feature matches exist for this Confluence page. The tag command may run more than one matching scenario until the canonical feature file is confirmed.

## Default mapped Selenium scenario

| Field | Value |
| --- | --- |
| Feature | 6. Fringe, G&A, Overhead Cost Creation for Manufacturing Proposal |
| Scenario | TC-Manufacturing-Proposal-010: Create G&A Cost |
| Tags | `@TCM-010 @END` |
| File | `imported/twentyfive-regtest/tests/src/test/resources/features/testfolder/Overhead_Cost_Creation_Manufacturing_Proposal.feature:161` |

### Scenario Sections

#### 1. Navigate to the Procurement & Production tab of the Estimate

- I click on 'Procurement & Production' tab
- page with name 'Estimate Procurement & Production' is opened

#### 2. In the G&A Formula field of the REG_MATERIAL1 row select Test Data Value

- I set value 'Regression - General & Admin (Purchased)' to the cell of the column 'G&A Formula' of the 'Procurement & Production' table for the row with the index '1'
- 'Confirm Formula, Define Local Parameters' popUp is displayed
- 'Purchased Parts Base' parameter is displayed
- 'Regression - General & Admin Rate' parameter is displayed
- 'Material & Equipment Burden Rate' parameter is displayed

#### 3. Enter the Test Data Value for the Regression - General & Admin Rate field

- I set value '0.0' to the cell of the column 'Value' of the 'Formula' table for the row with the following data:
- I wait for 7 seconds
- text of 'Dialog Result' input equals '0'

#### 4. Click Confirm button

- I click on 'Dialog Confirm' button
- I wait for 3 seconds
- 'Confirm Formula, Define Local Parameters' popUp is not displayed
- I wait for 7 seconds
- I click 'G&A Formula' in table 'Procurement & Production' table in row number 1
- I wait for 5 seconds
- I click on 'Dialog Confirm' button
- 'Confirm Formula, Define Local Parameters' popUp is not displayed
- I wait for 5 seconds
- page with name 'Estimate Procurement & Production' is opened
- 'Procurement & Production' table contain row with following data:

#### 5. Click the Pencil icon in the G&A Formula field of the REG_MATERIAL1 row

- I click 'G&A Formula' in table 'Procurement & Production' table in row number 1
- 'Confirm Formula, Define Local Parameters' popUp is displayed
- 'Formula' table contain row with following data:

#### 6. Enter the Test Data Value for the Regression - General & Admin Rate field

- I set value '0.1' to the cell of the column 'Value' of the 'Formula' table for the row with the following data:
- I wait for 7 seconds
- text of 'Dialog Result' input equals '12840'

#### 7. Click Confirm button

- I click on 'Dialog Confirm' button
- 'Confirm Formula, Define Local Parameters' popUp is not displayed
- I wait for 7 seconds
- I click 'G&A Formula' in table 'Procurement & Production' table in row number 1
- I wait for 5 seconds
- I click on 'Dialog Confirm' button
- 'Confirm Formula, Define Local Parameters' popUp is not displayed
- I wait for 5 seconds
- page with name 'Estimate Procurement & Production' is opened
- 'Procurement & Production' table contain row with following data:

#### 8. In the G&A Formula field of the REG_MATERIAL1 Copy row select Test Data Value

- I set value 'Regression - General & Admin (Purchased)' to the cell of the column 'G&A Formula' of the 'Procurement & Production' table for the row with the index '2'
- 'Confirm Formula, Define Local Parameters' popUp is displayed
- 'Formula' table contain row with following data:
- 'Formula' table contain row with following data:
- 'Formula' table contain row with following data:

#### 9. Click Confirm button

- I click on 'Dialog Confirm' button
- 'Confirm Formula, Define Local Parameters' popUp is not displayed
- I wait for 7 seconds
- page with name 'Estimate Procurement & Production' is opened
- 'Procurement & Production' table contain row with following data:

#### 10. Verify for REG_MATERIAL2 row that: Field G&A Formula is empty and Field G&A Cost = $ 0.00

- 'Procurement & Production' table contain row with following data:

#### 11. In the Status widget click Update Estimate Totals

- I wait for 7 seconds
- I click on 'Update Estimate totals' link
- I wait for 7 seconds
- page with name 'Estimate Procurement & Production' is opened
- 'Procurement & Production' table contain row with following data:
- 'Procurement & Production' table contain row with following data:
- 'Procurement & Production' table contain row with following data:

### Gherkin Excerpt

```gherkin
  Scenario: TC-Manufacturing-Proposal-010: Create G&A Cost
    And info: ---1. Navigate to the Procurement & Production tab of the Estimate---
    And I click on 'Procurement & Production' tab
    And page with name 'Estimate Procurement & Production' is opened
    And info: ---2. In the G&A Formula field of the REG_MATERIAL1 row select Test Data Value---
    And I set value 'Regression - General & Admin (Purchased)' to the cell of the column 'G&A Formula' of the 'Procurement & Production' table for the row with the index '1'
    And 'Confirm Formula, Define Local Parameters' popUp is displayed
    And 'Purchased Parts Base' parameter is displayed
    And 'Regression - General & Admin Rate' parameter is displayed
    And 'Material & Equipment Burden Rate' parameter is displayed
    And info: ---3. Enter the Test Data Value for the Regression - General & Admin Rate field---
    And I set value '0.0' to the cell of the column 'Value' of the 'Formula' table for the row with the following data:
      | Parameter | Regression - General & Admin Rate |
    Then I wait for 7 seconds
    And text of 'Dialog Result' input equals '0'
    And info: ---4. Click Confirm button---
    And I click on 'Dialog Confirm' button
    Then I wait for 3 seconds
    And 'Confirm Formula, Define Local Parameters' popUp is not displayed
    Then I wait for 7 seconds
    And I click 'G&A Formula' in table 'Procurement & Production' table in row number 1
    Then I wait for 5 seconds
    And I click on 'Dialog Confirm' button
    And 'Confirm Formula, Define Local Parameters' popUp is not displayed
    Then I wait for 5 seconds
    And page with name 'Estimate Procurement & Production' is opened
    And 'Procurement & Production' table contain row with following data:
      | Description              | REG_MATERIAL1   |
      | G&A Cost                 | $ 0.00          |
    And info: ---5. Click the Pencil icon in the G&A Formula field of the REG_MATERIAL1 row---
    And I click 'G&A Formula' in table 'Procurement & Production' table in row number 1
    And 'Confirm Formula, Define Local Parameters' popUp is displayed
    And 'Formula' table contain row with following data:
      | Parameter                | Purchased Parts Base (USD)   |
      | Value                    | 120000                       |
    And info: ---6. Enter the Test Data Value for the Regression - General & Admin Rate field---
    And I set value '0.1' to the cell of the column 'Value' of the 'Formula' table for the row with the following data:
      | Parameter | Regression - General & Admin Rate |
    Then I wait for 7 seconds
    And text of 'Dialog Result' input equals '12840'
    And info: ---7. Click Confirm button---
    And I click on 'Dialog Confirm' button
    And 'Confirm Formula, Define Local Parameters' popUp is not displayed
    Then I wait for 7 seconds
    And I click 'G&A Formula' in table 'Procurement & Production' table in row number 1
    Then I wait for 5 seconds
    And I click on 'Dialog Confirm' button
    And 'Confirm Formula, Define Local Parameters' popUp is not displayed
    Then I wait for 5 seconds
    And page with name 'Estimate Procurement & Production' is opened
    And 'Procurement & Production' table contain row with following data:
      | Description              | REG_MATERIAL1   |
      | G&A Cost                 | $ 12,840.00     |
    And info: ---8. In the G&A Formula field of the REG_MATERIAL1 Copy row select Test Data Value---
    And I set value 'Regression - General & Admin (Purchased)' to the cell of the column 'G&A Formula' of the 'Procurement & Production' table for the row with the index '2'
    And 'Confirm Formula, Define Local Parameters' popUp is displayed
    And 'Formula' table contain row with following data:
      | Parameter                | Purchased Parts Base (USD)   |
      | Value                    | 60000                        |
    And 'Formula' table contain row with following data:
      | Parameter                | Regression - General & Admin Rate   |
      | Value                    | 0.1                                 |
    And 'Formula' table contain row with following data:
      | Parameter                | Material & Equipment Burden Rate   |
      | Value                    | 0.07                               |
    And info: ---9. Click Confirm button---
    And I click on 'Dialog Confirm' button
    And 'Confirm Formula, Define Local Parameters' popUp is not displayed
    Then I wait for 7 seconds
    And page with name 'Estimate Procurement & Production' is opened
    And 'Procurement & Production' table contain row with following data:
      | Description              | REG_MATERIAL1 Copy   |
      | G&A Cost                 | $ 6,420.00           |
    And info: ---10. Verify for REG_MATERIAL2 row that: Field G&A Formula is empty and Field G&A Cost = $ 0.00---
    And 'Procurement & Production' table contain row with following data:
      | Description              | REG_MATERIAL2        |
      | G&A Formula              |                      |
      | G&A Cost                 | $ 0.00               |
    And info: ---11. In the Status widget click Update Estimate Totals---
    Then I wait for 7 seconds
    And I click on 'Update Estimate totals' link
    Then I wait for 7 seconds
    And page with name 'Estimate Procurement & Production' is opened
    And 'Procurement & Production' table contain row with following data:
      | Description              | REG_MATERIAL1                    |
      | G&A Formula              | Regression - General & Admin (Purchased)   |
      | G&A Cost                 | $ 12,840.00                       |
    And 'Procurement & Production' table contain row with following data:
      | Description              | REG_MATERIAL1 Copy              |
      | G&A Formula              | Regression - General & Admin (Purchased)  |
      | G&A Cost                 | $ 6,420.00                      |
    And 'Procurement & Production' table contain row with following data:
      | Description              | REG_MATERIAL2        |
      | G&A Formula              |                      |
      | G&A Cost                 | $ 0.00               |
```

## Alternate mapped Selenium scenario 2

| Field | Value |
| --- | --- |
| Feature | 3. Fringe, G&A, Overhead Cost Creation for Manufacturing Proposal |
| Scenario | TC-Manufacturing-Proposal-010: Create G&A Cost |
| Tags | `@TCM-010 @END` |
| File | `imported/twentyfive-regtest/tests/src/test/resources/features/testfolder/twentyfive2_4_create_fringe__overhead__ga_cost.feature:161` |

### Scenario Sections

#### 1. Navigate to the Procurement & Production tab of the Estimate

- I click on 'Procurement & Production' tab
- page with name 'Estimate Procurement & Production' is opened

#### 2. In the G&A Formula field of the REG_MATERIAL1 row select Test Data Value

- I set value 'Regression - General & Admin (Purchased)' to the cell of the column 'G&A Formula' of the 'Procurement & Production' table for the row with the index '1'
- 'Confirm Formula, Define Local Parameters' popUp is displayed
- 'Purchased Parts Base' parameter is displayed
- 'Regression - General & Admin Rate' parameter is displayed
- 'Material & Equipment Burden Rate' parameter is displayed

#### 3. Enter the Test Data Value for the Regression - General & Admin Rate field

- I set value '0.0' to the cell of the column 'Value' of the 'Formula' table for the row with the following data:
- I wait for 7 seconds
- text of 'Dialog Result' input equals '0'

#### 4. Click Confirm button

- I click on 'Dialog Confirm' button
- I wait for 3 seconds
- 'Confirm Formula, Define Local Parameters' popUp is not displayed
- I wait for 7 seconds
- I click 'G&A Formula' in table 'Procurement & Production' table in row number 1
- I wait for 5 seconds
- I click on 'Dialog Confirm' button
- 'Confirm Formula, Define Local Parameters' popUp is not displayed
- I wait for 5 seconds
- page with name 'Estimate Procurement & Production' is opened
- 'Procurement & Production' table contain row with following data:

#### 5. Click the Pencil icon in the G&A Formula field of the REG_MATERIAL1 row

- I click 'G&A Formula' in table 'Procurement & Production' table in row number 1
- 'Confirm Formula, Define Local Parameters' popUp is displayed
- 'Formula' table contain row with following data:

#### 6. Enter the Test Data Value for the Regression - General & Admin Rate field

- I set value '0.1' to the cell of the column 'Value' of the 'Formula' table for the row with the following data:
- I wait for 7 seconds
- text of 'Dialog Result' input equals '12840'

#### 7. Click Confirm button

- I click on 'Dialog Confirm' button
- 'Confirm Formula, Define Local Parameters' popUp is not displayed
- I wait for 7 seconds
- I click 'G&A Formula' in table 'Procurement & Production' table in row number 1
- I wait for 5 seconds
- I click on 'Dialog Confirm' button
- 'Confirm Formula, Define Local Parameters' popUp is not displayed
- I wait for 5 seconds
- page with name 'Estimate Procurement & Production' is opened
- 'Procurement & Production' table contain row with following data:

#### 8. In the G&A Formula field of the REG_MATERIAL1 Copy row select Test Data Value

- I set value 'Regression - General & Admin (Purchased)' to the cell of the column 'G&A Formula' of the 'Procurement & Production' table for the row with the index '2'
- 'Confirm Formula, Define Local Parameters' popUp is displayed
- 'Formula' table contain row with following data:
- 'Formula' table contain row with following data:
- 'Formula' table contain row with following data:

#### 9. Click Confirm button

- I click on 'Dialog Confirm' button
- 'Confirm Formula, Define Local Parameters' popUp is not displayed
- I wait for 7 seconds
- page with name 'Estimate Procurement & Production' is opened
- 'Procurement & Production' table contain row with following data:

#### 10. Verify for REG_MATERIAL2 row that: Field G&A Formula is empty and Field G&A Cost = $ 0.00

- 'Procurement & Production' table contain row with following data:

#### 11. In the Status widget click Update Estimate Totals

- I wait for 7 seconds
- I click on 'Update Estimate totals' link
- I wait for 7 seconds
- page with name 'Estimate Procurement & Production' is opened
- 'Procurement & Production' table contain row with following data:
- 'Procurement & Production' table contain row with following data:
- 'Procurement & Production' table contain row with following data:

### Gherkin Excerpt

```gherkin
  Scenario: TC-Manufacturing-Proposal-010: Create G&A Cost
    And info: ---1. Navigate to the Procurement & Production tab of the Estimate---
    And I click on 'Procurement & Production' tab
    And page with name 'Estimate Procurement & Production' is opened
    And info: ---2. In the G&A Formula field of the REG_MATERIAL1 row select Test Data Value---
    And I set value 'Regression - General & Admin (Purchased)' to the cell of the column 'G&A Formula' of the 'Procurement & Production' table for the row with the index '1'
    And 'Confirm Formula, Define Local Parameters' popUp is displayed
    And 'Purchased Parts Base' parameter is displayed
    And 'Regression - General & Admin Rate' parameter is displayed
    And 'Material & Equipment Burden Rate' parameter is displayed
    And info: ---3. Enter the Test Data Value for the Regression - General & Admin Rate field---
    And I set value '0.0' to the cell of the column 'Value' of the 'Formula' table for the row with the following data:
      | Parameter | Regression - General & Admin Rate |
    Then I wait for 7 seconds
    And text of 'Dialog Result' input equals '0'
    And info: ---4. Click Confirm button---
    And I click on 'Dialog Confirm' button
    Then I wait for 3 seconds
    And 'Confirm Formula, Define Local Parameters' popUp is not displayed
    Then I wait for 7 seconds
    And I click 'G&A Formula' in table 'Procurement & Production' table in row number 1
    Then I wait for 5 seconds
    And I click on 'Dialog Confirm' button
    And 'Confirm Formula, Define Local Parameters' popUp is not displayed
    Then I wait for 5 seconds
    And page with name 'Estimate Procurement & Production' is opened
    And 'Procurement & Production' table contain row with following data:
      | Description              | REG_MATERIAL1   |
      | G&A Cost                 | $ 0.00          |
    And info: ---5. Click the Pencil icon in the G&A Formula field of the REG_MATERIAL1 row---
    And I click 'G&A Formula' in table 'Procurement & Production' table in row number 1
    And 'Confirm Formula, Define Local Parameters' popUp is displayed
    And 'Formula' table contain row with following data:
      | Parameter                | Purchased Parts Base (USD)   |
      | Value                    | 120000                       |
    And info: ---6. Enter the Test Data Value for the Regression - General & Admin Rate field---
    And I set value '0.1' to the cell of the column 'Value' of the 'Formula' table for the row with the following data:
      | Parameter | Regression - General & Admin Rate |
    Then I wait for 7 seconds
    And text of 'Dialog Result' input equals '12840'
    And info: ---7. Click Confirm button---
    And I click on 'Dialog Confirm' button
    And 'Confirm Formula, Define Local Parameters' popUp is not displayed
    Then I wait for 7 seconds
    And I click 'G&A Formula' in table 'Procurement & Production' table in row number 1
    Then I wait for 5 seconds
    And I click on 'Dialog Confirm' button
    And 'Confirm Formula, Define Local Parameters' popUp is not displayed
    Then I wait for 5 seconds
    And page with name 'Estimate Procurement & Production' is opened
    And 'Procurement & Production' table contain row with following data:
      | Description              | REG_MATERIAL1   |
      | G&A Cost                 | $ 12,840.00     |
    And info: ---8. In the G&A Formula field of the REG_MATERIAL1 Copy row select Test Data Value---
    And I set value 'Regression - General & Admin (Purchased)' to the cell of the column 'G&A Formula' of the 'Procurement & Production' table for the row with the index '2'
    And 'Confirm Formula, Define Local Parameters' popUp is displayed
    And 'Formula' table contain row with following data:
      | Parameter                | Purchased Parts Base (USD)   |
      | Value                    | 60000                        |
    And 'Formula' table contain row with following data:
      | Parameter                | Regression - General & Admin Rate   |
      | Value                    | 0.1                                 |
    And 'Formula' table contain row with following data:
      | Parameter                | Material & Equipment Burden Rate   |
      | Value                    | 0.07                               |
    And info: ---9. Click Confirm button---
    And I click on 'Dialog Confirm' button
    And 'Confirm Formula, Define Local Parameters' popUp is not displayed
    Then I wait for 7 seconds
    And page with name 'Estimate Procurement & Production' is opened
    And 'Procurement & Production' table contain row with following data:
      | Description              | REG_MATERIAL1 Copy   |
      | G&A Cost                 | $ 6,420.00           |
    And info: ---10. Verify for REG_MATERIAL2 row that: Field G&A Formula is empty and Field G&A Cost = $ 0.00---
    And 'Procurement & Production' table contain row with following data:
      | Description              | REG_MATERIAL2        |
      | G&A Formula              |                      |
      | G&A Cost                 | $ 0.00               |
    And info: ---11. In the Status widget click Update Estimate Totals---
    Then I wait for 7 seconds
    And I click on 'Update Estimate totals' link
    Then I wait for 7 seconds
    And page with name 'Estimate Procurement & Production' is opened
    And 'Procurement & Production' table contain row with following data:
      | Description              | REG_MATERIAL1                    |
      | G&A Formula              | Regression - General & Admin (Purchased)   |
      | G&A Cost                 | $ 12,840.00                       |
    And 'Procurement & Production' table contain row with following data:
      | Description              | REG_MATERIAL1 Copy              |
      | G&A Formula              | Regression - General & Admin (Purchased)  |
      | G&A Cost                 | $ 6,420.00                      |
    And 'Procurement & Production' table contain row with following data:
      | Description              | REG_MATERIAL2        |
      | G&A Formula              |                      |
      | G&A Cost                 | $ 0.00               |
```

---

_Generated by `scripts/generate-testcase-guides.py` from the RTA mapping and local feature files._
