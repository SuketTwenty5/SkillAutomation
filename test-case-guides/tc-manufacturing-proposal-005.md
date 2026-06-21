# TC-Manufacturing-Proposal-005 - Create Labor Cost

| Field | Value |
| --- | --- |
| Test ID | TC-Manufacturing-Proposal-005 |
| Title | Create Labor Cost |
| RTA page | https://twenty5.atlassian.net/wiki/spaces/RTA/pages/234946565/TC-Manufacturing-Proposal-005+Create+Labor+Cost |
| Map source | `RTA_TEST_SUITE_FEATURE_MAP.md` |

## Run Instructions

When a consultant asks to run this test, use the default app URL unless they provide another URL:

```text
https://approuter-twenty5ipe-dev.cfapps.us10.hana.ondemand.com/#quote
```

For Claude Desktop, ask before launching Selenium Chrome with the default URL. If the user provides another URL, launch that URL instead.

```bash
scripts/start-debug-chrome.sh "https://approuter-twenty5ipe-dev.cfapps.us10.hana.ondemand.com/#quote"
scripts/run-twentyfive-test.sh @TCM-005
```

Duplicate feature matches exist for this Confluence page. The tag command may run more than one matching scenario until the canonical feature file is confirmed.

## Default mapped Selenium scenario

| Field | Value |
| --- | --- |
| Feature | 5. Cost Creation for Manufacturing Proposal |
| Scenario | TC-Manufacturing-Proposal-005: Create Labor Cost |
| Tags | `@TCM-005 @START` |
| File | `imported/twentyfive-regtest/tests/src/test/resources/features/testfolder/costCreationForManufacturingProposal.feature:5` |

### Scenario Sections

#### 1. Navigate to the Labor tab of the Estimate

- open TC-Manufacturing-Proposal-004 boe
- I perform login
- page with name 'Estimate Labor' is opened
- I select 'Test View for Regression' in the 'View' dropdown
- I wait for 30 seconds
- page with name 'Estimate Labor' is opened
- I select 'Regression Test - Manufacturing' in the 'View' dropdown
- I wait for 50 seconds
- I click on 'Save' button
- text of 'View' dropdown equals 'View: Regression Test - Manufacturing (shared & preferred)'
- page with name 'Estimate Labor' is opened

#### 2. Click button No records found - click or add or select Add resource per task from gear menu  click here to add

- I click on 'No records found, click here to add' button
- I wait for 5 seconds
- page with name 'Estimate Labor' is opened
- 'Labor Dual' table contain row with following data:

#### 3. Click and expand the WBS field

- I click 'WBS' in table 'Labor Dual' table in row number 1
- I click 'WBS Dropdown' in table 'Labor Dual' table in row number 1
- I wait for 7 seconds
- 'Opened' menu contains 2 items_-Warning-_
- 'Opened' menu contains items:

#### 4. Click and expand the Task field

- I click 'Task' in table 'Labor Dual' table in row number 1
- I click 'Task Dropdown' in table 'Labor Dual' table in row number 1
- I wait for 7 seconds
- 'Opened' menu contains 2 items_-Warning-_
- 'Opened' menu contains items:

#### 5. Select Test Data Value in the WBS field

- I set value '1.2 Test WBS 2' to the cell of the column 'WBS' of the 'Labor Dual' table for the row with the index '1'

#### 6. Click and expand the Task field

- I click 'Task' in table 'Labor Dual' table in row number 1
- I click 'Task Dropdown' in table 'Labor Dual' table in row number 1
- I wait for 7 seconds
- 'Opened' menu contains 1 items_-Warning-_
- 'Opened' menu contains items:

#### 7. Select Test Data Value in the Task field

- I set value '1.2.1 Task 1 for Test WBS 2' to the cell of the column 'Task' of the 'Labor Dual' table for the row with the index '1'
- page with name 'Estimate Labor' is opened

#### 8. Select Test Data Value in the WBS field

- I set value '1.1 Test WBS 1' to the cell of the column 'WBS' of the 'Labor Dual' table for the row with the index '1'
- 'Labor Dual' table contain row with following data:

#### 9. Select Test Data Value in the Task field

- I set value '1.1.1 Task 1 for Test WBS 1' to the cell of the column 'Task' of the 'Labor Dual' table for the row with the index '1'
- page with name 'Estimate Labor' is opened

#### 10. Select Test Data Value in the Resource Group field

- I set value 'REG_CONSULTANT1' to the cell of the column 'Resource Group' of the 'Labor Dual' table for the row with the index '1'
- 'Labor Dual' table contain row with following data:

#### 11. Click the "+" icon of the REG_CONSULTANT1 row

- I click '+' in table 'Labor Dual' table in row number 1

#### 12. Select Test Data Value in the Task field of the new row

- I set value '1.1.2 Task 2 for Test WBS 1' to the cell of the column 'Task' of the 'Labor Dual' table for the row with the index '2'

#### 13. Select Test Data Value in the Resource Group field of the new row

- I set value 'REG_PROJECTMANAGER' to the cell of the column 'Resource Group' of the 'Labor Dual' table for the row with the index '2'
- 'Labor Dual' table contain row with following data:

#### 14. For the REG_CONSULTANT1 row in the Distribution field select Test Data Value

- I set value 'Flat (working days per month)' to the cell of the column 'Distribution' of the 'Labor Dual' table for the row with the following data:

#### 15. For the REG_CONSULTANT1 row in the Effort field enter Test Data Value

- I set value '1920' to the cell of the column 'Effort' of the 'Labor Dual' table for the row with the following data:
- 'Labor Dual' table contain row with following data:
- the cell value of the 'Effort' column of the 'Labor Dual' table equals '1,920.00 hours' for a row with the following data:_-ROUNDOFF CHECK-_
- the cell value of the 'FTE' column of the 'Labor Dual' table equals '0.945 FTE' for a row with the following data:_-ROUNDOFF CHECK-_

#### 16. For the REG_PROJECTMANAGER row in the Distribution field select Test Data Value

- I set value 'Flat (working days per month)' to the cell of the column 'Distribution' of the 'Labor Dual' table for the row with the following data:

#### 17. For the REG_PROJECTMANAGER row in the End field enter Test Data Value

- I set value '7/31/25' to the cell of the column 'End' of the 'Labor Dual' table for the row with the following data:
- I wait for 7 seconds
- I click on 'Save' button
- 'Data saved successfully' warning message is displayed in 30 seconds

#### 18. For the REG_PROJECTMANAGER row in the FTE field enter Test Data Value

- I set value '1.2' to the cell of the column 'FTE' of the 'Labor Dual' table for the row with the following data:
- I wait for 7 seconds
- the cell value of the 'Effort' column of the 'Labor Dual' table equals '825.6 hours' for a row with the following data:_-ROUNDOFF CHECK-_
- the cell value of the 'FTE' column of the 'Labor Dual' table equals '1.200 FTE' for a row with the following data:_-ROUNDOFF CHECK-_
- 'Labor Dual' table contain row with following data:

#### 19. Click Save

- I click on 'Save' button
- 'Data saved successfully' warning message is displayed in 30 seconds

#### 20. Navigate to the Proposal module of the proposal and click the Setup tab

- open TC-Manufacturing-Proposal-003 quote
- I if visible click on 'Yes' dialogButton
- I click on 'Setup' tab
- page with name 'Setup page' is opened

#### 21. Click the Edit Rates link near the Leading Company Currency field

- I click on 'Edit Rates' link
- 'Maintain Proposal-specific Currency Exchange Rates' popup is displayed

#### 22. Click Escalation or Inflation Rates tab on the popup

- I click on 'Escalation or Inflation Rates' tab
- page with name 'Setup page' is opened
- 'Maintain Proposal-specific Currency Exchange Rates' popup is displayed
- I wait for 7 seconds
- I should see the following columns in the 'Escalation and Inflammation Rates' table:

#### 23. In the Year field update the existing value to the Test Data Value

- I click on 'Escalation first' cell
- I click on 'Update Rates' button
- I click on 'Reset all Rates to 1' option
- I wait for 10 seconds

#### 24. Click Confirm button

- I click on 'Confirm' button
- I wait for 5 seconds
- 'Save Confirmation' popUp is displayed

#### 25. Click Yes button

- I click on 'Yes' button
- I click on 'Close' button
- I click on 'Yes and Update Costs' button
- 'Force Update' popUp is displayed_-Warning-_

#### 26. Click Yes button

- I click on 'Force Update Yes' button

#### 27. Wait until the page is refreshed

- page with name 'Setup page' is opened
- I wait for 10 seconds

#### 28. Navigate to the Estimate of the proposal

- open TC-Manufacturing-Proposal-004 boe
- I if visible click on 'Yes' dialogButton
- page with name 'Estimate Labor' is opened

#### 29. In the Status widget click Update Estimate Totals

- I click on 'Update Estimate totals' link
- I wait for 50 seconds
- page with name 'Estimate Labor' is opened
- 'Labor Dual' table contain row with following data:
- 'Labor Dual' table contain row with following data:

#### 30. Verify the Summary line

- Grand total cell value of the 'Billing Rate' column of the 'Labor Dual' table equals '$ 215.52/hr'
- Grand total cell value of the 'Cost in Company Currency' column of the 'Labor Dual' table equals '$ 321,216.00'
- Grand total cell value of the 'T&M Revenue' column of the 'Labor Dual' table equals '$ 591,744.00'
- Grand total cell value of the 'FTE' column of the 'Labor Dual' table equals '2.145 FTE'_-ROUNDOFF CHECK-_
- Grand total cell value of the 'Effort' column of the 'Labor Dual' table equals '2746 hr'_-ROUNDOFF CHECK-_

#### 31. Verify the Labor Cost widget

- text of 'Labor Revenue' widget equals 'USD 592K'
- text of 'Labor Cost' widget equals 'USD 321K'

#### 32. Verify the Total Effort widget

- text of 'Labor Hours' widget equals '2746 hours'_-ROUNDOFF CHECK-_
- text of 'Labor Days' widget equals '343 days'

### Gherkin Excerpt

```gherkin
  Scenario: TC-Manufacturing-Proposal-005: Create Labor Cost
    And info: ---1. Navigate to the Labor tab of the Estimate---
    Given open TC-Manufacturing-Proposal-004 boe
    And I perform login
    And page with name 'Estimate Labor' is opened
    When I select 'Test View for Regression' in the 'View' dropdown
    And I wait for 30 seconds
    And page with name 'Estimate Labor' is opened
    When I select 'Regression Test - Manufacturing' in the 'View' dropdown
    And I wait for 50 seconds
    And I click on 'Save' button
    Then text of 'View' dropdown equals 'View: Regression Test - Manufacturing (shared & preferred)'
    And page with name 'Estimate Labor' is opened
    And info: ---2. Click button No records found - click or add or select Add resource per task from gear menu  click here to add---
    And I click on 'No records found, click here to add' button
    Then I wait for 5 seconds
    And page with name 'Estimate Labor' is opened
    And 'Labor Dual' table contain row with following data:
      | Work Package | 1 Test Phase 1  |
      | WBS | 1.1 Test WBS 1  |
      | Distribution | Flat (working days per month)  |
      | Start        | 4/1/25          |
      | End          | 3/31/26         |
    And info: ---3. Click and expand the WBS field---
    And I click 'WBS' in table 'Labor Dual' table in row number 1
    And I click 'WBS Dropdown' in table 'Labor Dual' table in row number 1
    And I wait for 7 seconds
    And 'Opened' menu contains 2 items_-Warning-_
    And 'Opened' menu contains items:
      | Test WBS 1 |
      | Test WBS 2 |
    And info: ---4. Click and expand the Task field---
    And I click 'Task' in table 'Labor Dual' table in row number 1
    And I click 'Task Dropdown' in table 'Labor Dual' table in row number 1
    And I wait for 7 seconds
    And 'Opened' menu contains 2 items_-Warning-_
    And 'Opened' menu contains items:
      | Task 1 for Test WBS 1 |
      | Task 2 for Test WBS 1 |
    And info: ---5. Select Test Data Value in the WBS field---
    And I set value '1.2 Test WBS 2' to the cell of the column 'WBS' of the 'Labor Dual' table for the row with the index '1'
    And info: ---6. Click and expand the Task field---
    And I click 'Task' in table 'Labor Dual' table in row number 1
    And I click 'Task Dropdown' in table 'Labor Dual' table in row number 1
    And I wait for 7 seconds
    And 'Opened' menu contains 1 items_-Warning-_
    And 'Opened' menu contains items:
      | Task 1 for Test WBS 2 |
    And info: ---7. Select Test Data Value in the Task field---
    And I set value '1.2.1 Task 1 for Test WBS 2' to the cell of the column 'Task' of the 'Labor Dual' table for the row with the index '1'
    And page with name 'Estimate Labor' is opened
    And info: ---8. Select Test Data Value in the WBS field---
    And I set value '1.1 Test WBS 1' to the cell of the column 'WBS' of the 'Labor Dual' table for the row with the index '1'
    And 'Labor Dual' table contain row with following data:
      | Work Package | 1 Test Phase 1  |
      | WBS          | 1.1 Test WBS 1  |
      | Task         |                 |
    And info: ---9. Select Test Data Value in the Task field---
    And I set value '1.1.1 Task 1 for Test WBS 1' to the cell of the column 'Task' of the 'Labor Dual' table for the row with the index '1'
    And page with name 'Estimate Labor' is opened
    And info: ---10. Select Test Data Value in the Resource Group field---
    And I set value 'REG_CONSULTANT1' to the cell of the column 'Resource Group' of the 'Labor Dual' table for the row with the index '1'
    And 'Labor Dual' table contain row with following data:
      | Description           | REG_CONSULTANT1  |
      | Distribution          | Manual (Monthly) |
      | Cost Rate (Alt. Unit) | $ 120.00/        |
      | Billing Rate          | $ 205.00/hr      |
    And info: ---11. Click the "+" icon of the REG_CONSULTANT1 row---
    And I click '+' in table 'Labor Dual' table in row number 1
    And info: ---12. Select Test Data Value in the Task field of the new row---
    And I set value '1.1.2 Task 2 for Test WBS 1' to the cell of the column 'Task' of the 'Labor Dual' table for the row with the index '2'
    And info: ---13. Select Test Data Value in the Resource Group field of the new row---
    And I set value 'REG_PROJECTMANAGER' to the cell of the column 'Resource Group' of the 'Labor Dual' table for the row with the index '2'
    And 'Labor Dual' table contain row with following data:
      | Description           | REG_PROJECTMANAGER  |
      | Distribution          | Manual (Monthly)    |
      | Cost Rate (Alt. Unit) | $ 110.00/           |
      | Billing Rate          | $ 240.00/hr         |
    And info: ---14. For the REG_CONSULTANT1 row in the Distribution field select Test Data Value---
    And I set value 'Flat (working days per month)' to the cell of the column 'Distribution' of the 'Labor Dual' table for the row with the following data:
      | Description           | REG_CONSULTANT1  |
    And info: ---15. For the REG_CONSULTANT1 row in the Effort field enter Test Data Value---
    And I set value '1920' to the cell of the column 'Effort' of the 'Labor Dual' table for the row with the following data:
      | Description           | REG_CONSULTANT1  |
    And 'Labor Dual' table contain row with following data:
      | Description           | REG_CONSULTANT1  |
      | T&M Revenue           | $ 393,600.00 |
    Then the cell value of the 'Effort' column of the 'Labor Dual' table equals '1,920.00 hours' for a row with the following data:_-ROUNDOFF CHECK-_
      |Description           | REG_CONSULTANT1  |
    Then the cell value of the 'FTE' column of the 'Labor Dual' table equals '0.945 FTE' for a row with the following data:_-ROUNDOFF CHECK-_
      |Description           | REG_CONSULTANT1  |
    And info: ---16. For the REG_PROJECTMANAGER row in the Distribution field select Test Data Value---
    And I set value 'Flat (working days per month)' to the cell of the column 'Distribution' of the 'Labor Dual' table for the row with the following data:
      | Description           | REG_PROJECTMANAGER  |
    And info: ---17. For the REG_PROJECTMANAGER row in the End field enter Test Data Value---
    And I set value '7/31/25' to the cell of the column 'End' of the 'Labor Dual' table for the row with the following data:
      | Description           | REG_PROJECTMANAGER  |
    Then I wait for 7 seconds
    And I click on 'Save' button
    And 'Data saved successfully' warning message is displayed in 30 seconds
    And info: ---18. For the REG_PROJECTMANAGER row in the FTE field enter Test Data Value---
    And I set value '1.2' to the cell of the column 'FTE' of the 'Labor Dual' table for the row with the following data:
      | Description           | REG_PROJECTMANAGER  |
    Then I wait for 7 seconds
    Then the cell value of the 'Effort' column of the 'Labor Dual' table equals '825.6 hours' for a row with the following data:_-ROUNDOFF CHECK-_
      |Description           | REG_PROJECTMANAGER  |
    Then the cell value of the 'FTE' column of the 'Labor Dual' table equals '1.200 FTE' for a row with the following data:_-ROUNDOFF CHECK-_
      |Description           | REG_PROJECTMANAGER  |
    And 'Labor Dual' table contain row with following data:
      | Description           | REG_PROJECTMANAGER  |
      | T&M Revenue           | $ 198,144.00 |
    And info: ---19. Click Save---
    And I click on 'Save' button
    And 'Data saved successfully' warning message is displayed in 30 seconds
    And info: ---20. Navigate to the Proposal module of the proposal and click the Setup tab---
    Then open TC-Manufacturing-Proposal-003 quote
    And I if visible click on 'Yes' dialogButton
    And I click on 'Setup' tab
    And page with name 'Setup page' is opened
    And info: ---21. Click the Edit Rates link near the Leading Company Currency field---
    And I click on 'Edit Rates' link
    And 'Maintain Proposal-specific Currency Exchange Rates' popup is displayed
    And info: ---22. Click Escalation or Inflation Rates tab on the popup---
    Then I click on 'Escalation or Inflation Rates' tab
    And page with name 'Setup page' is opened
    And 'Maintain Proposal-specific Currency Exchange Rates' popup is displayed
    And I wait for 7 seconds
    Then I should see the following columns in the 'Escalation and Inflammation Rates' table:
      | Escalation Type    |
      | 2025               |
      | 2026               |
    And info: ---23. In the Year field update the existing value to the Test Data Value---
    Then I click on 'Escalation first' cell
    Then I click on 'Update Rates' button
    Then I click on 'Reset all Rates to 1' option
    And I wait for 10 seconds
    And info: ---24. Click Confirm button---
    Then I click on 'Confirm' button
    And I wait for 5 seconds
    And 'Save Confirmation' popUp is displayed
    And info: ---25. Click Yes button---
    Then I click on 'Yes' button
    And I click on 'Close' button
    And I click on 'Yes and Update Costs' button
    And 'Force Update' popUp is displayed_-Warning-_
    And info: ---26. Click Yes button---
    Then I click on 'Force Update Yes' button
    And info: ---27. Wait until the page is refreshed---
    And page with name 'Setup page' is opened
#    And I if visible click on 'Close' button
    Then I wait for 10 seconds
    And info: ---28. Navigate to the Estimate of the proposal---
    Then open TC-Manufacturing-Proposal-004 boe
    And I if visible click on 'Yes' dialogButton
    And page with name 'Estimate Labor' is opened
    And info: ---29. In the Status widget click Update Estimate Totals---
    When I click on 'Update Estimate totals' link
    Then I wait for 50 seconds
    And page with name 'Estimate Labor' is opened
    And 'Labor Dual' table contain row with following data:
      | Description               | REG_CONSULTANT1  |
      | Cost in Company Currency  | $ 230,400.00     |
    And 'Labor Dual' table contain row with following data:
      | Description              | REG_PROJECTMANAGER    |
      | Cost in Company Currency | $ 90,816.00           |
    And info: ---30. Verify the Summary line---
    Then Grand total cell value of the 'Billing Rate' column of the 'Labor Dual' table equals '$ 215.52/hr'
    Then Grand total cell value of the 'Cost in Company Currency' column of the 'Labor Dual' table equals '$ 321,216.00'
    Then Grand total cell value of the 'T&M Revenue' column of the 'Labor Dual' table equals '$ 591,744.00'
    Then Grand total cell value of the 'FTE' column of the 'Labor Dual' table equals '2.145 FTE'_-ROUNDOFF CHECK-_
    Then Grand total cell value of the 'Effort' column of the 'Labor Dual' table equals '2746 hr'_-ROUNDOFF CHECK-_
    And info: ---31. Verify the Labor Cost widget---
    Then text of 'Labor Revenue' widget equals 'USD 592K'
    Then text of 'Labor Cost' widget equals 'USD 321K'
    And info: ---32. Verify the Total Effort widget---
    Then text of 'Labor Hours' widget equals '2746 hours'_-ROUNDOFF CHECK-_
    Then text of 'Labor Days' widget equals '343 days'

```

## Alternate mapped Selenium scenario 2

| Field | Value |
| --- | --- |
| Feature | 2. Create Labor, Other, Travel and Material Cost in the Estimate of the Manufacturing proposal |
| Scenario | TC-Manufacturing-Proposal-005: Create Labor Cost |
| Tags | `@TCM-005 @START` |
| File | `imported/twentyfive-regtest/tests/src/test/resources/features/testfolder/twentyfive2_4_create_labor__other__travel__material_cost.feature:5` |

### Scenario Sections

#### 1. Navigate to the Labor tab of the Estimate

- open TC-Manufacturing-Proposal-004 boe
- I perform Mfg 2.4 login
- page with name 'Main page' is opened
- I click on 'Labor' tab
- I select 'Regression Test - Manufacturing' in the 'View' dropdown
- I click on 'Save' button
- text of 'View' dropdown equals 'View: Regression Test - Manufacturing'
- page with name 'Estimate Labor' is opened
- I select 'Regression Test - Manufacturing' in the 'View' dropdown
- I click on 'Save' button
- text of 'View' dropdown equals 'View: Regression Test - Manufacturing (shared & preferred)'
- page with name 'Estimate Labor' is opened

#### 2. Click button No records found - click or add or select Add resource per task from gear menu  click here to add

- I click on 'No records found, click here to add' button
- I wait for 5 seconds
- page with name 'Estimate Labor' is opened
- 'Labor Dual' table contain row with following data:

#### 3. Click and expand the WBS field

- I click 'WBS' in table 'Labor Dual' table in row number 1
- I click 'WBS Dropdown' in table 'Labor Dual' table in row number 1
- I wait for 7 seconds
- 'Opened' menu contains 2 items_-Warning-_
- 'Opened' menu contains items:

#### 4. Click and expand the Task field

- I click 'Task' in table 'Labor Dual' table in row number 1
- I click 'Task Dropdown' in table 'Labor Dual' table in row number 1
- I wait for 7 seconds
- 'Opened' menu contains 2 items_-Warning-_
- 'Opened' menu contains items:

#### 5. Select Test Data Value in the WBS field

- I set value '1.2 Test WBS 2' to the cell of the column 'WBS' of the 'Labor Dual' table for the row with the index '1'

#### 6. Click and expand the Task field

- I click 'Task' in table 'Labor Dual' table in row number 1
- I click 'Task Dropdown' in table 'Labor Dual' table in row number 1
- I wait for 7 seconds
- 'Opened' menu contains 1 items_-Warning-_
- 'Opened' menu contains items:

#### 7. Select Test Data Value in the Task field

- I set value '1.2.1 Task 1 for Test WBS 2' to the cell of the column 'Task' of the 'Labor Dual' table for the row with the index '1'
- page with name 'Estimate Labor' is opened

#### 8. Select Test Data Value in the WBS field

- I set value '1.1 Test WBS 1' to the cell of the column 'WBS' of the 'Labor Dual' table for the row with the index '1'
- 'Labor Dual' table contain row with following data:

#### 9. Select Test Data Value in the Task field

- I set value '1.1.1 Task 1 for Test WBS 1' to the cell of the column 'Task' of the 'Labor Dual' table for the row with the index '1'
- page with name 'Estimate Labor' is opened

#### 10. Select Test Data Value in the Resource Group field

- I set value 'REG_CONSULTANT1' to the cell of the column 'Resource Group' of the 'Labor Dual' table for the row with the index '1'
- 'Labor Dual' table contain row with following data:

#### 11. Click the "+" icon of the REG_CONSULTANT1 row

- I click '+' in table 'Labor Dual' table in row number 1

#### 12. Select Test Data Value in the Task field of the new row

- I set value '1.1.2 Task 2 for Test WBS 1' to the cell of the column 'Task' of the 'Labor Dual' table for the row with the index '2'

#### 13. Select Test Data Value in the Resource Group field of the new row

- I set value 'REG_PROJECTMANAGER' to the cell of the column 'Resource Group' of the 'Labor Dual' table for the row with the index '2'
- 'Labor Dual' table contain row with following data:

#### 14. For the REG_CONSULTANT1 row in the Distribution field select Test Data Value

- I set value 'Flat (working days per month)' to the cell of the column 'Distribution' of the 'Labor Dual' table for the row with the following data:

#### 15. For the REG_CONSULTANT1 row in the Effort field enter Test Data Value

- I set value '1920' to the cell of the column 'Effort' of the 'Labor Dual' table for the row with the following data:
- 'Labor Dual' table contain row with following data:
- the cell value of the 'Effort' column of the 'Labor Dual' table equals '1,920.00 hours' for a row with the following data:_-ROUNDOFF CHECK-_
- the cell value of the 'FTE' column of the 'Labor Dual' table equals '0.945 FTE' for a row with the following data:_-ROUNDOFF CHECK-_

#### 16. For the REG_PROJECTMANAGER row in the Distribution field select Test Data Value

- I set value 'Flat (working days per month)' to the cell of the column 'Distribution' of the 'Labor Dual' table for the row with the following data:

#### 17. For the REG_PROJECTMANAGER row in the End field enter Test Data Value

- I set value '7/31/25' to the cell of the column 'End' of the 'Labor Dual' table for the row with the following data:
- I wait for 7 seconds
- I click on 'Save' button
- 'Data saved successfully' warning message is displayed in 30 seconds

#### 18. For the REG_PROJECTMANAGER row in the FTE field enter Test Data Value

- I set value '1.2' to the cell of the column 'FTE' of the 'Labor Dual' table for the row with the following data:
- I wait for 7 seconds
- the cell value of the 'Effort' column of the 'Labor Dual' table equals '825.6 hours' for a row with the following data:_-ROUNDOFF CHECK-_
- the cell value of the 'FTE' column of the 'Labor Dual' table equals '1.200 FTE' for a row with the following data:_-ROUNDOFF CHECK-_
- 'Labor Dual' table contain row with following data:

#### 19. Click Save

- I click on 'Save' button
- 'Data saved successfully' warning message is displayed in 30 seconds

#### 20. Navigate to the Proposal module of the proposal and click the Setup tab

- open TC-Manufacturing-Proposal-003 quote
- I if visible click on 'Yes' dialogButton
- I click on 'Setup' tab
- page with name 'Setup page' is opened

#### 21. Click the Edit Rates link near the Leading Company Currency field

- I click on 'Edit Rates' link
- 'Maintain Proposal-specific Currency Exchange Rates' popup is displayed

#### 22. Click Escalation or Inflation Rates tab on the popup

- I click on 'Escalation or Inflation Rates' tab
- page with name 'Setup page' is opened
- 'Maintain Proposal-specific Currency Exchange Rates' popup is displayed
- I wait for 7 seconds
- I should see the following columns in the 'Escalation and Inflammation Rates' table:

#### 23. In the Year field update the existing value to the Test Data Value

- I click on 'Escalation first' cell
- I click on 'Update Rates' button
- I click on 'Reset all Rates to 1' option
- I wait for 15 seconds

#### 24. Click Confirm button

- I click on 'Confirm' button
- I wait for 3 seconds
- 'Save Confirmation' popUp is displayed

#### 25. Click Yes button

- I click on 'Yes' button
- I click on 'Close' button
- I click on 'Yes and Update Costs' button
- 'Force Update' popUp is displayed_-Warning-_

#### 26. Click Yes button

- I click on 'Force Update Yes' button

#### 27. Wait until the page is refreshed

- page with name 'Setup page' is opened
- I wait for 10 seconds

#### 28. Navigate to the Estimate of the proposal

- open TC-Manufacturing-Proposal-004 boe
- I if visible click on 'Yes' dialogButton
- page with name 'Estimate Labor' is opened

#### 29. In the Status widget click Update Estimate Totals

- I click on 'Update Estimate totals' link
- I wait for 50 seconds
- page with name 'Estimate Labor' is opened
- 'Labor Dual' table contain row with following data:
- 'Labor Dual' table contain row with following data:

#### 30. Verify the Summary line

- Grand total cell value of the 'T&M Billing Rate' column of the 'Labor Dual' table equals '$ 215.52/hr'
- Grand total cell value of the 'Cost in Company Currency' column of the 'Labor Dual' table equals '$ 321,216.00'
- Grand total cell value of the 'T&M Revenue' column of the 'Labor Dual' table equals '$ 591,744.00'
- Grand total cell value of the 'FTE' column of the 'Labor Dual' table equals '2.145 FTE'_-ROUNDOFF CHECK-_
- Grand total cell value of the 'Effort' column of the 'Labor Dual' table equals '2746 hr'_-ROUNDOFF CHECK-_

#### 31. Verify the Labor Cost widget

- text of 'Labor Revenue' widget equals 'USD 592K'
- text of 'Labor Cost' widget equals 'USD 321K'

#### 32. Verify the Total Effort widget

- text of 'Labor Hours' widget equals '2746 hours'_-ROUNDOFF CHECK-_
- text of 'Labor Days' widget equals '343 days'

### Gherkin Excerpt

```gherkin
  Scenario: TC-Manufacturing-Proposal-005: Create Labor Cost
    And info: ---1. Navigate to the Labor tab of the Estimate---
    Given open TC-Manufacturing-Proposal-004 boe
    And I perform Mfg 2.4 login
    Then page with name 'Main page' is opened
    And I click on 'Labor' tab
    When I select 'Regression Test - Manufacturing' in the 'View' dropdown
    And I click on 'Save' button
    Then text of 'View' dropdown equals 'View: Regression Test - Manufacturing'
    And page with name 'Estimate Labor' is opened
    When I select 'Regression Test - Manufacturing' in the 'View' dropdown
    And I click on 'Save' button
    Then text of 'View' dropdown equals 'View: Regression Test - Manufacturing (shared & preferred)'
    And page with name 'Estimate Labor' is opened
    And info: ---2. Click button No records found - click or add or select Add resource per task from gear menu  click here to add---
    And I click on 'No records found, click here to add' button
    Then I wait for 5 seconds
    And page with name 'Estimate Labor' is opened
    And 'Labor Dual' table contain row with following data:
      | Work Package | 1 Test Phase 1  |
      | WBS          | 1.1 Test WBS 1  |
      | Distribution | Flat (working days per month)  |
      | Start        | 4/1/25          |
      | End          | 3/31/26         |
    And info: ---3. Click and expand the WBS field---
    And I click 'WBS' in table 'Labor Dual' table in row number 1
    And I click 'WBS Dropdown' in table 'Labor Dual' table in row number 1
    And I wait for 7 seconds
    And 'Opened' menu contains 2 items_-Warning-_
    And 'Opened' menu contains items:
      | Test WBS 1 |
      | Test WBS 2 |
    And info: ---4. Click and expand the Task field---
    And I click 'Task' in table 'Labor Dual' table in row number 1
    And I click 'Task Dropdown' in table 'Labor Dual' table in row number 1
    And I wait for 7 seconds
    And 'Opened' menu contains 2 items_-Warning-_
    And 'Opened' menu contains items:
      | Task 1 for Test WBS 1 |
      | Task 2 for Test WBS 1 |
    And info: ---5. Select Test Data Value in the WBS field---
    And I set value '1.2 Test WBS 2' to the cell of the column 'WBS' of the 'Labor Dual' table for the row with the index '1'
    And info: ---6. Click and expand the Task field---
    And I click 'Task' in table 'Labor Dual' table in row number 1
    And I click 'Task Dropdown' in table 'Labor Dual' table in row number 1
    And I wait for 7 seconds
    And 'Opened' menu contains 1 items_-Warning-_
    And 'Opened' menu contains items:
      | Task 1 for Test WBS 2 |
    And info: ---7. Select Test Data Value in the Task field---
    And I set value '1.2.1 Task 1 for Test WBS 2' to the cell of the column 'Task' of the 'Labor Dual' table for the row with the index '1'
    And page with name 'Estimate Labor' is opened
    And info: ---8. Select Test Data Value in the WBS field---
    And I set value '1.1 Test WBS 1' to the cell of the column 'WBS' of the 'Labor Dual' table for the row with the index '1'
    And 'Labor Dual' table contain row with following data:
      | Work Package | 1 Test Phase 1  |
      | WBS          | 1.1 Test WBS 1  |
      | Task         |                 |
    And info: ---9. Select Test Data Value in the Task field---
    And I set value '1.1.1 Task 1 for Test WBS 1' to the cell of the column 'Task' of the 'Labor Dual' table for the row with the index '1'
    And page with name 'Estimate Labor' is opened
    And info: ---10. Select Test Data Value in the Resource Group field---
    And I set value 'REG_CONSULTANT1' to the cell of the column 'Resource Group' of the 'Labor Dual' table for the row with the index '1'
    And 'Labor Dual' table contain row with following data:
      | Resource Group - Free Text           | REG_CONSULTANT1  |
      | Distribution          | Manual (Monthly) |
      | Rate in Resource Unit | $ 120.00/        |
      | T&M Billing Rate          | $ 205.00/hr      |
    And info: ---11. Click the "+" icon of the REG_CONSULTANT1 row---
    And I click '+' in table 'Labor Dual' table in row number 1
    And info: ---12. Select Test Data Value in the Task field of the new row---
    And I set value '1.1.2 Task 2 for Test WBS 1' to the cell of the column 'Task' of the 'Labor Dual' table for the row with the index '2'
    And info: ---13. Select Test Data Value in the Resource Group field of the new row---
    And I set value 'REG_PROJECTMANAGER' to the cell of the column 'Resource Group' of the 'Labor Dual' table for the row with the index '2'
    And 'Labor Dual' table contain row with following data:
      | Resource Group - Free Text | REG_PROJECTMANAGER  |
      | Distribution          | Manual (Monthly)    |
      | Rate in Resource Unit | $ 110.00/           |
      | T&M Billing Rate          | $ 240.00/hr         |
    And info: ---14. For the REG_CONSULTANT1 row in the Distribution field select Test Data Value---
    And I set value 'Flat (working days per month)' to the cell of the column 'Distribution' of the 'Labor Dual' table for the row with the following data:
      | Resource Group - Free Text           | REG_CONSULTANT1  |
    And info: ---15. For the REG_CONSULTANT1 row in the Effort field enter Test Data Value---
    And I set value '1920' to the cell of the column 'Effort' of the 'Labor Dual' table for the row with the following data:
      | Resource Group - Free Text           | REG_CONSULTANT1  |
    And 'Labor Dual' table contain row with following data:
      | Resource Group - Free Text           | REG_CONSULTANT1  |
      | T&M Revenue           | $ 393,600.00 |
    Then the cell value of the 'Effort' column of the 'Labor Dual' table equals '1,920.00 hours' for a row with the following data:_-ROUNDOFF CHECK-_
      |Resource Group - Free Text           | REG_CONSULTANT1  |
    Then the cell value of the 'FTE' column of the 'Labor Dual' table equals '0.945 FTE' for a row with the following data:_-ROUNDOFF CHECK-_
      |Resource Group - Free Text           | REG_CONSULTANT1  |
    And info: ---16. For the REG_PROJECTMANAGER row in the Distribution field select Test Data Value---
    And I set value 'Flat (working days per month)' to the cell of the column 'Distribution' of the 'Labor Dual' table for the row with the following data:
      | Resource Group - Free Text           | REG_PROJECTMANAGER  |
    And info: ---17. For the REG_PROJECTMANAGER row in the End field enter Test Data Value---
    And I set value '7/31/25' to the cell of the column 'End' of the 'Labor Dual' table for the row with the following data:
      | Resource Group - Free Text           | REG_PROJECTMANAGER  |
    Then I wait for 7 seconds
    And I click on 'Save' button
    And 'Data saved successfully' warning message is displayed in 30 seconds
    And info: ---18. For the REG_PROJECTMANAGER row in the FTE field enter Test Data Value---
    And I set value '1.2' to the cell of the column 'FTE' of the 'Labor Dual' table for the row with the following data:
      | Resource Group - Free Text           | REG_PROJECTMANAGER  |
    Then I wait for 7 seconds
    Then the cell value of the 'Effort' column of the 'Labor Dual' table equals '825.6 hours' for a row with the following data:_-ROUNDOFF CHECK-_
      |Resource Group - Free Text           | REG_PROJECTMANAGER  |
    Then the cell value of the 'FTE' column of the 'Labor Dual' table equals '1.200 FTE' for a row with the following data:_-ROUNDOFF CHECK-_
      |Resource Group - Free Text           | REG_PROJECTMANAGER  |
    And 'Labor Dual' table contain row with following data:
      | Resource Group - Free Text           | REG_PROJECTMANAGER  |
      | T&M Revenue           | $ 198,144.00 |
    And info: ---19. Click Save---
    And I click on 'Save' button
    And 'Data saved successfully' warning message is displayed in 30 seconds
    And info: ---20. Navigate to the Proposal module of the proposal and click the Setup tab---
    Then open TC-Manufacturing-Proposal-003 quote
    And I if visible click on 'Yes' dialogButton
    And I click on 'Setup' tab
    And page with name 'Setup page' is opened
    And info: ---21. Click the Edit Rates link near the Leading Company Currency field---
    And I click on 'Edit Rates' link
    And 'Maintain Proposal-specific Currency Exchange Rates' popup is displayed
    And info: ---22. Click Escalation or Inflation Rates tab on the popup---
    Then I click on 'Escalation or Inflation Rates' tab
    And page with name 'Setup page' is opened
    And 'Maintain Proposal-specific Currency Exchange Rates' popup is displayed
    And I wait for 7 seconds
    Then I should see the following columns in the 'Escalation and Inflammation Rates' table:
      | Escalation Type    |
      | 2025               |
      | 2026               |
    And info: ---23. In the Year field update the existing value to the Test Data Value---
    Then I click on 'Escalation first' cell
    Then I click on 'Update Rates' button
    Then I click on 'Reset all Rates to 1' option
    And I wait for 15 seconds
    And info: ---24. Click Confirm button---
    Then I click on 'Confirm' button
    And I wait for 3 seconds
    And 'Save Confirmation' popUp is displayed
    And info: ---25. Click Yes button---
    Then I click on 'Yes' button
    And I click on 'Close' button
    And I click on 'Yes and Update Costs' button
    And 'Force Update' popUp is displayed_-Warning-_
    And info: ---26. Click Yes button---
    Then I click on 'Force Update Yes' button
    And info: ---27. Wait until the page is refreshed---
    And page with name 'Setup page' is opened
#    And I if visible click on 'Close' button
    Then I wait for 10 seconds
    And info: ---28. Navigate to the Estimate of the proposal---
    Then open TC-Manufacturing-Proposal-004 boe
    And I if visible click on 'Yes' dialogButton
    And page with name 'Estimate Labor' is opened
    And info: ---29. In the Status widget click Update Estimate Totals---
    When I click on 'Update Estimate totals' link
    Then I wait for 50 seconds
    And page with name 'Estimate Labor' is opened
    And 'Labor Dual' table contain row with following data:
      | Resource Group - Free Text               | REG_CONSULTANT1  |
      | Cost in Company Currency  | $ 230,400.00     |
    And 'Labor Dual' table contain row with following data:
      | Resource Group - Free Text              | REG_PROJECTMANAGER    |
      | Cost in Company Currency | $ 90,816.00           |
    And info: ---30. Verify the Summary line---
    Then Grand total cell value of the 'T&M Billing Rate' column of the 'Labor Dual' table equals '$ 215.52/hr'
    Then Grand total cell value of the 'Cost in Company Currency' column of the 'Labor Dual' table equals '$ 321,216.00'
    Then Grand total cell value of the 'T&M Revenue' column of the 'Labor Dual' table equals '$ 591,744.00'
    Then Grand total cell value of the 'FTE' column of the 'Labor Dual' table equals '2.145 FTE'_-ROUNDOFF CHECK-_
    Then Grand total cell value of the 'Effort' column of the 'Labor Dual' table equals '2746 hr'_-ROUNDOFF CHECK-_
    And info: ---31. Verify the Labor Cost widget---
    Then text of 'Labor Revenue' widget equals 'USD 592K'
    Then text of 'Labor Cost' widget equals 'USD 321K'
    And info: ---32. Verify the Total Effort widget---
    Then text of 'Labor Hours' widget equals '2746 hours'_-ROUNDOFF CHECK-_
    Then text of 'Labor Days' widget equals '343 days'

```

---

_Generated by `scripts/generate-testcase-guides.py` from the RTA mapping and local feature files._
