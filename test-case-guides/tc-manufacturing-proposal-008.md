# TC-Manufacturing-Proposal-008 - Create Fringe Cost

| Field | Value |
| --- | --- |
| Test ID | TC-Manufacturing-Proposal-008 |
| Title | Create Fringe Cost |
| RTA page | https://twenty5.atlassian.net/wiki/spaces/RTA/pages/260603905/TC-Manufacturing-Proposal-008+Create+Fringe+Cost |
| Map source | `RTA_TEST_SUITE_FEATURE_MAP.md` |

## Run Instructions

When a consultant asks to run this test, use the default app URL unless they provide another URL:

```text
https://approuter-twenty5ipe-dev.cfapps.us10.hana.ondemand.com/#quote
```

For Claude Desktop, ask before launching Selenium Chrome with the default URL. If the user provides another URL, launch that URL instead.

```bash
scripts/start-debug-chrome.sh "https://approuter-twenty5ipe-dev.cfapps.us10.hana.ondemand.com/#quote"
scripts/run-twentyfive-test.sh @TCM-008
```

Duplicate feature matches exist for this Confluence page. The tag command may run more than one matching scenario until the canonical feature file is confirmed.

## Default mapped Selenium scenario

| Field | Value |
| --- | --- |
| Feature | 6. Fringe, G&A, Overhead Cost Creation for Manufacturing Proposal |
| Scenario | TC-Manufacturing-Proposal-008: Create Fringe Cost |
| Tags | `@TCM-008 @START` |
| File | `imported/twentyfive-regtest/tests/src/test/resources/features/testfolder/Overhead_Cost_Creation_Manufacturing_Proposal.feature:5` |

### Scenario Sections

#### Setup

- open TC-Manufacturing-Proposal-004 boe
- I perform login

#### 1. Navigate to the Labor tab of the Estimate

- page with name 'Estimate Labor' is opened
- I select 'Regression Test - Manufacturing' in the 'View' dropdown
- I click on 'Save' button
- text of 'View' dropdown equals 'View: Regression Test - Manufacturing'
- page with name 'Estimate Labor' is opened
- 'Labor Dual' table contain row with following data_-Warning-_:
- I click on 'Reload' page
- I wait for 15 seconds
- page with name 'Estimate Labor' is opened
- 'Labor Dual' table contain row with following data:

#### 2. In the Fringe Formula field for REG_CONSULTANT1 row select Test Data Value

- I set value 'Regression - Field Labor Fringe' to the cell of the column 'Fringe Formula' of the 'Labor Dual' table for the row with the following data:
- 'Confirm Formula, Define Local Parameters' popUp is displayed
- 'Regression Service Labor Base' parameter is displayed
- 'Regression Normal Labor Fringe Rate' parameter is displayed

#### 3. Enter the Test Data Value for the Regression Normal Labor Fringe Rate field

- I set value '0.0' to the cell of the column 'Value' of the 'Formula' table for the row with the following data:
- text of 'Dialog Result' input equals '0'

#### 4. Click Confirm button

- I click on 'Dialog Confirm' button
- 'Confirm Formula, Define Local Parameters' popUp is not displayed

#### 5. Click the Pencil icon in the Fringe Formula field of the REG_CONSULTANT1 row

- I click 'Fringe Formula' in table 'Labor Dual' table for row with data:
- 'Confirm Formula, Define Local Parameters' popUp is displayed

#### 6. Enter the Test Data Value for the Regression Normal Labor Fringe Rate field

- I set value '0.01' to the cell of the column 'Value' of the 'Formula' table for the row with the following data:
- 'Confirm Formula, Define Local Parameters' popUp is displayed
- text of 'Dialog Result' input equals '2304'

#### 7. Click Confirm button

- I click on 'Dialog Confirm' button
- 'Confirm Formula, Define Local Parameters' popUp is not displayed

#### 8. Click Save button

- I click on 'Save' button
- 'Data saved successfully' warning message is displayed in 30 seconds

#### 9. In the Status widget click Update Estimate Totals

- I click on 'Update Estimate totals' link
- I wait for 10 seconds
- page with name 'Estimate Labor' is opened
- 'Labor Dual' table contain row with following data:

### Gherkin Excerpt

```gherkin
  Scenario: TC-Manufacturing-Proposal-008: Create Fringe Cost
    Given open TC-Manufacturing-Proposal-004 boe
    And I perform login
    And info: ---1. Navigate to the Labor tab of the Estimate---
    And page with name 'Estimate Labor' is opened
    When I select 'Regression Test - Manufacturing' in the 'View' dropdown
    And I click on 'Save' button
    Then text of 'View' dropdown equals 'View: Regression Test - Manufacturing'
    And page with name 'Estimate Labor' is opened
    And 'Labor Dual' table contain row with following data_-Warning-_:
      | Description               | REG_CONSULTANT1  |
      | Cost in Company Currency  | $ 230,400.00     |
    And I click on 'Reload' page
    And I wait for 15 seconds
    And page with name 'Estimate Labor' is opened
    And 'Labor Dual' table contain row with following data:
      | Description               | REG_CONSULTANT1  |
      | Cost in Company Currency  | $ 230,400.00     |
#    And 'Labor Dual' table contain row with following data_-Warning-_:
#      | Description              | REG_PROJECTMANAGER    |
#      | Cost in Company Currency | $ 92,928.00           |
    And info: ---2. In the Fringe Formula field for REG_CONSULTANT1 row select Test Data Value---
    And I set value 'Regression - Field Labor Fringe' to the cell of the column 'Fringe Formula' of the 'Labor Dual' table for the row with the following data:
      | Description               | REG_CONSULTANT1  |
    And 'Confirm Formula, Define Local Parameters' popUp is displayed
    And 'Regression Service Labor Base' parameter is displayed
    And 'Regression Normal Labor Fringe Rate' parameter is displayed
    And info: ---3. Enter the Test Data Value for the Regression Normal Labor Fringe Rate field---
    And I set value '0.0' to the cell of the column 'Value' of the 'Formula' table for the row with the following data:
      | Parameter | Regression Normal Labor Fringe Rate |
    And text of 'Dialog Result' input equals '0'
    And info: ---4. Click Confirm button---
    And I click on 'Dialog Confirm' button
    And 'Confirm Formula, Define Local Parameters' popUp is not displayed
    And info: ---5. Click the Pencil icon in the Fringe Formula field of the REG_CONSULTANT1 row---
    And I click 'Fringe Formula' in table 'Labor Dual' table for row with data:
      | Description               | REG_CONSULTANT1  |
    And 'Confirm Formula, Define Local Parameters' popUp is displayed
    And info: ---6. Enter the Test Data Value for the Regression Normal Labor Fringe Rate field---
    And I set value '0.01' to the cell of the column 'Value' of the 'Formula' table for the row with the following data:
      | Parameter | Regression Normal Labor Fringe Rate |
    And 'Confirm Formula, Define Local Parameters' popUp is displayed
    And text of 'Dialog Result' input equals '2304'
    And info: ---7. Click Confirm button---
    And I click on 'Dialog Confirm' button
    And 'Confirm Formula, Define Local Parameters' popUp is not displayed
    And info: ---8. Click Save button---
    And I click on 'Save' button
    And 'Data saved successfully' warning message is displayed in 30 seconds
    And info: ---9. In the Status widget click Update Estimate Totals---
    When I click on 'Update Estimate totals' link
    Then I wait for 10 seconds
    And page with name 'Estimate Labor' is opened
    And 'Labor Dual' table contain row with following data:
      | Description               | REG_CONSULTANT1  |
      | Fringe Cost               | $ 2,304.00       |


```

## Alternate mapped Selenium scenario 2

| Field | Value |
| --- | --- |
| Feature | 3. Fringe, G&A, Overhead Cost Creation for Manufacturing Proposal |
| Scenario | TC-Manufacturing-Proposal-008: Create Fringe Cost |
| Tags | `@TCM-008 @START` |
| File | `imported/twentyfive-regtest/tests/src/test/resources/features/testfolder/twentyfive2_4_create_fringe__overhead__ga_cost.feature:5` |

### Scenario Sections

#### Setup

- open TC-Manufacturing-Proposal-004 boe
- I perform Mfg 2.4 login

#### 1. Navigate to the Labor tab of the Estimate

- page with name 'Estimate Labor' is opened
- I select 'Regression Test - Manufacturing' in the 'View' dropdown
- I click on 'Save' button
- text of 'View' dropdown equals 'View: Regression Test - Manufacturing'
- page with name 'Estimate Labor' is opened
- 'Labor Dual' table contain row with following data_-Warning-_:
- I click on 'Reload' page
- I wait for 15 seconds
- page with name 'Estimate Labor' is opened
- 'Labor Dual' table contain row with following data:

#### 2. In the Fringe Formula field for REG_CONSULTANT1 row select Test Data Value

- I set value 'Regression - Field Labor Fringe' to the cell of the column 'Fringe Formula' of the 'Labor Dual' table for the row with the following data:
- 'Confirm Formula, Define Local Parameters' popUp is displayed
- 'Regression Service Labor Base' parameter is displayed
- 'Regression Normal Labor Fringe Rate' parameter is displayed

#### 3. Enter the Test Data Value for the Regression Normal Labor Fringe Rate field

- I set value '0.0' to the cell of the column 'Value' of the 'Formula' table for the row with the following data:
- text of 'Dialog Result' input equals '0'

#### 4. Click Confirm button

- I click on 'Dialog Confirm' button
- 'Confirm Formula, Define Local Parameters' popUp is not displayed

#### 5. Click the Pencil icon in the Fringe Formula field of the REG_CONSULTANT1 row

- I click 'Fringe Formula' in table 'Labor Dual' table for row with data:
- 'Confirm Formula, Define Local Parameters' popUp is displayed

#### 6. Enter the Test Data Value for the Regression Normal Labor Fringe Rate field

- I set value '0.01' to the cell of the column 'Value' of the 'Formula' table for the row with the following data:
- 'Confirm Formula, Define Local Parameters' popUp is displayed
- text of 'Dialog Result' input equals '2304'

#### 7. Click Confirm button

- I click on 'Dialog Confirm' button
- 'Confirm Formula, Define Local Parameters' popUp is not displayed

#### 8. Click Save button

- I click on 'Save' button
- 'Data saved successfully' warning message is displayed in 30 seconds

#### 9. In the Status widget click Update Estimate Totals

- I click on 'Update Estimate totals' link
- I wait for 10 seconds
- page with name 'Estimate Labor' is opened
- 'Labor Dual' table contain row with following data:

### Gherkin Excerpt

```gherkin
  Scenario: TC-Manufacturing-Proposal-008: Create Fringe Cost
    Given open TC-Manufacturing-Proposal-004 boe
    And I perform Mfg 2.4 login
    And info: ---1. Navigate to the Labor tab of the Estimate---
    And page with name 'Estimate Labor' is opened
    When I select 'Regression Test - Manufacturing' in the 'View' dropdown
    And I click on 'Save' button
    Then text of 'View' dropdown equals 'View: Regression Test - Manufacturing'
    And page with name 'Estimate Labor' is opened
    And 'Labor Dual' table contain row with following data_-Warning-_:
      | Description               | REG_CONSULTANT1  |
      | Cost in Company Currency  | $ 230,400.00     |
    And I click on 'Reload' page
    And I wait for 15 seconds
    And page with name 'Estimate Labor' is opened
    And 'Labor Dual' table contain row with following data:
      | Description               | REG_CONSULTANT1  |
      | Cost in Company Currency  | $ 230,400.00     |
#    And 'Labor Dual' table contain row with following data_-Warning-_:
#      | Description              | REG_PROJECTMANAGER    |
#      | Cost in Company Currency | $ 92,928.00           |
    And info: ---2. In the Fringe Formula field for REG_CONSULTANT1 row select Test Data Value---
    And I set value 'Regression - Field Labor Fringe' to the cell of the column 'Fringe Formula' of the 'Labor Dual' table for the row with the following data:
      | Description               | REG_CONSULTANT1  |
    And 'Confirm Formula, Define Local Parameters' popUp is displayed
    And 'Regression Service Labor Base' parameter is displayed
    And 'Regression Normal Labor Fringe Rate' parameter is displayed
    And info: ---3. Enter the Test Data Value for the Regression Normal Labor Fringe Rate field---
    And I set value '0.0' to the cell of the column 'Value' of the 'Formula' table for the row with the following data:
      | Parameter | Regression Normal Labor Fringe Rate |
    And text of 'Dialog Result' input equals '0'
    And info: ---4. Click Confirm button---
    And I click on 'Dialog Confirm' button
    And 'Confirm Formula, Define Local Parameters' popUp is not displayed
    And info: ---5. Click the Pencil icon in the Fringe Formula field of the REG_CONSULTANT1 row---
    And I click 'Fringe Formula' in table 'Labor Dual' table for row with data:
      | Description               | REG_CONSULTANT1  |
    And 'Confirm Formula, Define Local Parameters' popUp is displayed
    And info: ---6. Enter the Test Data Value for the Regression Normal Labor Fringe Rate field---
    And I set value '0.01' to the cell of the column 'Value' of the 'Formula' table for the row with the following data:
      | Parameter | Regression Normal Labor Fringe Rate |
    And 'Confirm Formula, Define Local Parameters' popUp is displayed
    And text of 'Dialog Result' input equals '2304'
    And info: ---7. Click Confirm button---
    And I click on 'Dialog Confirm' button
    And 'Confirm Formula, Define Local Parameters' popUp is not displayed
    And info: ---8. Click Save button---
    And I click on 'Save' button
    And 'Data saved successfully' warning message is displayed in 30 seconds
    And info: ---9. In the Status widget click Update Estimate Totals---
    When I click on 'Update Estimate totals' link
    Then I wait for 10 seconds
    And page with name 'Estimate Labor' is opened
    And 'Labor Dual' table contain row with following data:
      | Description               | REG_CONSULTANT1  |
      | Fringe Cost               | $ 2,304.00       |


```

---

_Generated by `scripts/generate-testcase-guides.py` from the RTA mapping and local feature files._
