# TC-Manufacturing-Proposal-007 - Create Material Cost

| Field | Value |
| --- | --- |
| Test ID | TC-Manufacturing-Proposal-007 |
| Title | Create Material Cost |
| RTA page | https://twenty5.atlassian.net/wiki/spaces/RTA/pages/243662849/TC-Manufacturing-Proposal-007+Create+Material+Cost |
| Map source | `RTA_TEST_SUITE_FEATURE_MAP.md` |

## Run Instructions

When a consultant asks to run this test, use the default app URL unless they provide another URL:

```text
https://approuter-twenty5ipe-dev.cfapps.us10.hana.ondemand.com/#quote
```

For Claude Desktop, ask before launching Selenium Chrome with the default URL. If the user provides another URL, launch that URL instead.

```bash
scripts/start-debug-chrome.sh "https://approuter-twenty5ipe-dev.cfapps.us10.hana.ondemand.com/#quote"
scripts/run-twentyfive-test.sh @TCM-007
```

Duplicate feature matches exist for this Confluence page. The tag command may run more than one matching scenario until the canonical feature file is confirmed.

## Default mapped Selenium scenario

| Field | Value |
| --- | --- |
| Feature | 5. Cost Creation for Manufacturing Proposal |
| Scenario | TC-Manufacturing-Proposal-007: Create Material Cost |
| Tags | `@TCM-007 @END` |
| File | `imported/twentyfive-regtest/tests/src/test/resources/features/testfolder/costCreationForManufacturingProposal.feature:353` |

### Scenario Sections

#### 1. Navigate to the Procurement & Production tab of the Estimate

- I wait for 7 second
- I click on 'Procurement & Production' tab
- page with name 'Estimate Procurement & Production' is opened
- I select 'Regression Test - Manufacturing' in the 'View' dropdown
- I click on 'Save' button
- text of 'View' dropdown equals 'View: Regression Test - Manufacturing (preferred)'

#### 2. Click button No records found, click here to add

- I click on 'No records found, click here to add' button
- 'Procurement & Production' table contain row with following data_-Warning-_:
- I set value '1 Test Phase 1' to the cell of the column 'Phase' of the 'Procurement & Production' table for the row with the index '1'
- 'Procurement & Production' table contain row with following data:

#### 3. Click and expand the WBS field

- I click 'WBS' in table 'Procurement & Production' table in row number 1
- I click 'WBS Dropdown' in table 'Procurement & Production' table in row number 1
- I wait for 7 seconds
- 'Opened' menu contains 2 items_-Warning-_
- 'Opened' menu contains items:

#### 4. Click and expand the Task field

- I click 'Task' in table 'Procurement & Production' table in row number 1
- I click 'Task Dropdown' in table 'Procurement & Production' table in row number 1
- I wait for 7 seconds
- 'Opened' menu contains 2 items_-Warning-_
- 'Opened' menu contains items:

#### 5. Select Test Data Value in the WBS field

- I set value '1.2 Test WBS 2' to the cell of the column 'WBS' of the 'Procurement & Production' table for the row with the index '1'

#### 6. Click and expand the Task field

- I click 'Task' in table 'Procurement & Production' table in row number 1
- I click 'Task Dropdown' in table 'Procurement & Production' table in row number 1
- I wait for 7 seconds
- 'Opened' menu contains 1 items_-Warning-_
- 'Opened' menu contains items:

#### 7. Select Test Data Value in the Task field

- I set value '1.2.1 Task 1 for Test WBS 2' to the cell of the column 'Task' of the 'Procurement & Production' table for the row with the index '1'

#### 8. Select Test Data Value in the WBS field

- I set value '1.1 Test WBS 1' to the cell of the column 'WBS' of the 'Procurement & Production' table for the row with the index '1'
- 'Procurement & Production' table contain row with following data:

#### 9. Select Test Data Value in the Task field

- I set value '1.1.1 Task 1 for Test WBS 1' to the cell of the column 'Task' of the 'Procurement & Production' table for the row with the index '1'
- page with name 'Estimate Procurement & Production' is opened

#### 10. Select Test Data Value in the Resource Group field

- I set value 'REG_MATERIAL1' to the cell of the column 'Part Number' of the 'Procurement & Production' table for the row with the index '1'

#### 11. Click Yes button on the Recost popup up

- 'Procurement & Production' table contain row with following data:
- 'Procurement & Production' table contain row with following data:
- the cell value of the 'Purchased' column of the 'Procurement & Production' table is checked for a row with the following data:

#### 12. Update the End field to the Test Data Value

- I set value '4/1/25' to the cell of the column 'End' of the 'Procurement & Production' table for the row with the index '1'

#### 13. In the Source Unit Cost field enter the Test Data Value

- I set value '120000' to the cell of the column 'Source Unit Cost' of the 'Procurement & Production' table for the row with the index '1'
- 'Procurement & Production' table contain row with following data:
- 'Procurement & Production' table contain row with following data:

#### 14. Click the Copy icon of the REG_MATERIAL1 row

- I click 'Copy Row' in table 'Procurement & Production' table in row number 1

#### 15. Update the Description field to the Test Data Value

- I set value 'REG_MATERIAL1 Copy' to the cell of the column 'Description' of the 'Procurement & Production' table for the row with the index '2'
- 'Procurement & Production' table contain row with following data:
- the cell value of the 'Purchased' column of the 'Procurement & Production' table is checked for a row with the following data:

#### 16. Update the Start field to the Test Data Value

- I set value '7/1/25' to the cell of the column 'Start' of the 'Procurement & Production' table for the row with the following data:
- 'Procurement & Production' table contain row with following data:

#### 17. In the Source Unit Cost field of the REG_MATERIAL1 Copy row enter the Test Data Value

- I set value '60000' to the cell of the column 'Source Unit Cost' of the 'Procurement & Production' table for the row with the following data:
- 'Procurement & Production' table contain row with following data:

#### 18. Click the "+" symbol of the REG_MATERIAL1 Copy row

- I click '+' in table 'Procurement & Production' table in row number 2
- I set value '1 Test Phase 1' to the cell of the column 'Phase' of the 'Procurement & Production' table for the row with the index '3'

#### 19. Select Test Data Value for the WBS field

- I set value '1.2 Test WBS 2' to the cell of the column 'WBS' of the 'Procurement & Production' table for the row with the index '3'

#### 20. Select Test Data Value for the Task field

- I set value '1.2.1 Task 1 for Test WBS 2' to the cell of the column 'Task' of the 'Procurement & Production' table for the row with the index '3'

#### 21. Select Test Data Value for the Part Number field

- I set value 'REG_MATERIAL2' to the cell of the column 'Part Number' of the 'Procurement & Production' table for the row with the index '3'
- 'Procurement & Production' table contain row with following data:
- 'Procurement & Production' table contain row with following data:
- the cell value of the 'Purchased' column of the 'Procurement & Production' table is unchecked for a row with the following data:

#### 22. Update the Start field to the Test Data Value

- I set value '1/1/26' to the cell of the column 'Start' of the 'Procurement & Production' table for the row with the index '3'

#### 23. Click Save button

- I wait for 7 seconds
- I click on 'Save' button
- I wait for 7 seconds

#### 24. Click on the Gear icon of the Description field for the REG_MATERIAL2 row and click Edit Details

- I hover 'Description' cell in table 'Procurement & Production' table and click  for row with data:
- I click on 'Edit Details' menuItem
- 'Mfg Part' popUp is displayed
- I wait for 5 seconds

#### 25. Click the No records found, click here to add button in the popup's grid

- I click on 'No records found, click here to add' button
- I wait for 7 seconds

#### 26. Select Test Data Value in the Resource Group field

- I set value 'REG_CONSULTANT2' to the cell of the column 'Resource Group' of the 'Mfg' table for the row with the index '1'
- 'Mfg' table contain row with following data:

#### 27. Enter Test Data Value in the Run Time field

- I set value '900' to the cell of the column 'Run Time' of the 'Mfg' table for the row with the index '1'
- 'Mfg' table contain row with following data:

#### 28. Click Confirm Button

- I click on 'Confirm' button
- 'Mfg Part' popUp is not displayed
- 'Procurement & Production' table contain row with following data:

#### 29. Click Save button

- I wait for 5 seconds
- I click on 'Save' button
- I wait for 5 seconds

#### 30. Check the Summary row

- Grand total cell value of the 'Cost in Company Currency' column of the 'Procurement & Production' table equals '$ 261,000.00'

### Gherkin Excerpt

```gherkin
  Scenario: TC-Manufacturing-Proposal-007: Create Material Cost
    And info: ---1. Navigate to the Procurement & Production tab of the Estimate---
    And I wait for 7 second
    And I click on 'Procurement & Production' tab
    And page with name 'Estimate Procurement & Production' is opened
    When I select 'Regression Test - Manufacturing' in the 'View' dropdown
    And I click on 'Save' button
    Then text of 'View' dropdown equals 'View: Regression Test - Manufacturing (preferred)'
    And info: ---2. Click button No records found, click here to add---
    And I click on 'No records found, click here to add' button
    And 'Procurement & Production' table contain row with following data_-Warning-_:
      | Phase        | 1 Test Phase 1  |
    And I set value '1 Test Phase 1' to the cell of the column 'Phase' of the 'Procurement & Production' table for the row with the index '1'
    And 'Procurement & Production' table contain row with following data:
      | WBS              | 1.1 Test WBS 1  |
      | Distribute Costs | Flat (working days per month)  |
      | Start            | 4/1/25          |
      | End              | 3/31/26         |
    And info: ---3. Click and expand the WBS field---
    And I click 'WBS' in table 'Procurement & Production' table in row number 1
    And I click 'WBS Dropdown' in table 'Procurement & Production' table in row number 1
    And I wait for 7 seconds
    And 'Opened' menu contains 2 items_-Warning-_
    And 'Opened' menu contains items:
      | Test WBS 1 |
      | Test WBS 2 |
    And info: ---4. Click and expand the Task field---
    And I click 'Task' in table 'Procurement & Production' table in row number 1
    And I click 'Task Dropdown' in table 'Procurement & Production' table in row number 1
    And I wait for 7 seconds
    And 'Opened' menu contains 2 items_-Warning-_
    And 'Opened' menu contains items:
      | Task 1 for Test WBS 1 |
      | Task 2 for Test WBS 1 |
    And info: ---5. Select Test Data Value in the WBS field---
    And I set value '1.2 Test WBS 2' to the cell of the column 'WBS' of the 'Procurement & Production' table for the row with the index '1'
    And info: ---6. Click and expand the Task field---
    And I click 'Task' in table 'Procurement & Production' table in row number 1
    And I click 'Task Dropdown' in table 'Procurement & Production' table in row number 1
    And I wait for 7 seconds
    And 'Opened' menu contains 1 items_-Warning-_
    And 'Opened' menu contains items:
      | Task 1 for Test WBS 2 |
    And info: ---7. Select Test Data Value in the Task field---
    And I set value '1.2.1 Task 1 for Test WBS 2' to the cell of the column 'Task' of the 'Procurement & Production' table for the row with the index '1'
    And info: ---8. Select Test Data Value in the WBS field---
    And I set value '1.1 Test WBS 1' to the cell of the column 'WBS' of the 'Procurement & Production' table for the row with the index '1'
    And 'Procurement & Production' table contain row with following data:
      | Phase        | 1 Test Phase 1  |
      | WBS          | 1.1 Test WBS 1  |
      | Task         |                 |
    And info: ---9. Select Test Data Value in the Task field---
    And I set value '1.1.1 Task 1 for Test WBS 1' to the cell of the column 'Task' of the 'Procurement & Production' table for the row with the index '1'
    And page with name 'Estimate Procurement & Production' is opened
    And info: ---10. Select Test Data Value in the Resource Group field---
    And I set value 'REG_MATERIAL1' to the cell of the column 'Part Number' of the 'Procurement & Production' table for the row with the index '1'
    And info: ---11. Click Yes button on the Recost popup up---
    And 'Procurement & Production' table contain row with following data:
      | Part Number  | REG_MATERIAL1  |
      | WBS          | 1.1 Test WBS 1  |
    And 'Procurement & Production' table contain row with following data:
      | Description  | REG_MATERIAL1   |
      | Cost Element | Purchased Parts |
    And the cell value of the 'Purchased' column of the 'Procurement & Production' table is checked for a row with the following data:
      | Part Number  | REG_MATERIAL1   |
    And info: ---12. Update the End field to the Test Data Value---
    And I set value '4/1/25' to the cell of the column 'End' of the 'Procurement & Production' table for the row with the index '1'
    And info: ---13. In the Source Unit Cost field enter the Test Data Value---
    And I set value '120000' to the cell of the column 'Source Unit Cost' of the 'Procurement & Production' table for the row with the index '1'
    And 'Procurement & Production' table contain row with following data:
      | Part Number  | REG_MATERIAL1   |
      | WBS          | 1.1 Test WBS 1  |
    And 'Procurement & Production' table contain row with following data:
      | Description              | REG_MATERIAL1   |
      | Cost Element             | Purchased Parts |
      | Source Unit Cost         | $ 120,000.00/ea |
      | Cost in Company Currency | $ 120,000.00    |
      | Start                    | 4/1/25              |
      | End                      | 4/1/25              |
    And info: ---14. Click the Copy icon of the REG_MATERIAL1 row---
    And I click 'Copy Row' in table 'Procurement & Production' table in row number 1
    And info: ---15. Update the Description field to the Test Data Value---
    And I set value 'REG_MATERIAL1 Copy' to the cell of the column 'Description' of the 'Procurement & Production' table for the row with the index '2'
    And 'Procurement & Production' table contain row with following data:
      | Description              | REG_MATERIAL1 Copy  |
      | Cost Element             | Purchased Parts |
      | Source Unit Cost         | $ 120,000.00/ea |
      | Cost in Company Currency | $ 120,000.00    |
    And the cell value of the 'Purchased' column of the 'Procurement & Production' table is checked for a row with the following data:
      | Description              | REG_MATERIAL1 Copy  |
    And info: ---16. Update the Start field to the Test Data Value---
    And I set value '7/1/25' to the cell of the column 'Start' of the 'Procurement & Production' table for the row with the following data:
      | Description              | REG_MATERIAL1 Copy  |
    And 'Procurement & Production' table contain row with following data:
      | Description              | REG_MATERIAL1 Copy  |
      | Start                    | 7/1/25              |
      | End                      | 7/1/25              |
    And info: ---17. In the Source Unit Cost field of the REG_MATERIAL1 Copy row enter the Test Data Value---
    And I set value '60000' to the cell of the column 'Source Unit Cost' of the 'Procurement & Production' table for the row with the following data:
      | Description              | REG_MATERIAL1 Copy  |
    And 'Procurement & Production' table contain row with following data:
      | Description              | REG_MATERIAL1 Copy  |
      | Start                    | 7/1/25              |
      | End                      | 7/1/25              |
      | Source Unit Cost         | $ 60,000.00/ea      |
      | Cost in Company Currency | $ 60,000.00         |
    And info: ---18. Click the "+" symbol of the REG_MATERIAL1 Copy row---
    And I click '+' in table 'Procurement & Production' table in row number 2
    And I set value '1 Test Phase 1' to the cell of the column 'Phase' of the 'Procurement & Production' table for the row with the index '3'
    And info: ---19. Select Test Data Value for the WBS field---
    And I set value '1.2 Test WBS 2' to the cell of the column 'WBS' of the 'Procurement & Production' table for the row with the index '3'
    And info: ---20. Select Test Data Value for the Task field---
    And I set value '1.2.1 Task 1 for Test WBS 2' to the cell of the column 'Task' of the 'Procurement & Production' table for the row with the index '3'
    And info: ---21. Select Test Data Value for the Part Number field---
    And I set value 'REG_MATERIAL2' to the cell of the column 'Part Number' of the 'Procurement & Production' table for the row with the index '3'
    And 'Procurement & Production' table contain row with following data:
      | WBS          | 1.2 Test WBS 2 |
      | Part Number  | REG_MATERIAL2   |
    And 'Procurement & Production' table contain row with following data:
      | Description              | REG_MATERIAL2           |
      | Cost Element             | Construction Management |
    And the cell value of the 'Purchased' column of the 'Procurement & Production' table is unchecked for a row with the following data:
      | Part Number  | REG_MATERIAL2   |
    And info: ---22. Update the Start field to the Test Data Value---
    And I set value '1/1/26' to the cell of the column 'Start' of the 'Procurement & Production' table for the row with the index '3'
    And info: ---23. Click Save button---
    And I wait for 7 seconds
    And I click on 'Save' button
#    And 'Data saved successfully' popUp is displayed
    And I wait for 7 seconds
    And info: ---24. Click on the Gear icon of the Description field for the REG_MATERIAL2 row and click Edit Details---
    And I hover 'Description' cell in table 'Procurement & Production' table and click  for row with data:
      | Description              | REG_MATERIAL2           |
    And I click on 'Edit Details' menuItem
    And 'Mfg Part' popUp is displayed
    And I wait for 5 seconds
    And info: ---25. Click the No records found, click here to add button in the popup's grid---
    And I click on 'No records found, click here to add' button
    And I wait for 7 seconds
    And info: ---26. Select Test Data Value in the Resource Group field---
    And I set value 'REG_CONSULTANT2' to the cell of the column 'Resource Group' of the 'Mfg' table for the row with the index '1'
    And 'Mfg' table contain row with following data:
      | Resource Group              | REG_CONSULTANT2          |
      | Rate/Hr                     | $ 90.00/hr               |
    And info: ---27. Enter Test Data Value in the Run Time field---
    And I set value '900' to the cell of the column 'Run Time' of the 'Mfg' table for the row with the index '1'
    And 'Mfg' table contain row with following data:
      | Resource Group              | REG_CONSULTANT2          |
      | Rate/Hr                     | $ 90.00/hr               |
      | Total Cost                  | $ 81,000.00              |
    And info: ---28. Click Confirm Button---
    And I click on 'Confirm' button
    And 'Mfg Part' popUp is not displayed
    And 'Procurement & Production' table contain row with following data:
      | Description              | REG_MATERIAL2           |
      | Source Unit Cost         | $ 81,000.00/ea          |
      | Cost in Company Currency | $ 81,000.00             |
      | Start                    | 1/1/26              |
      | End                      | 3/31/26             |
    And info: ---29. Click Save button---
    Then I wait for 5 seconds
    And I click on 'Save' button
    Then I wait for 5 seconds
#    And 'Data saved successfully' popUp is displayed
    And info: ---30. Check the Summary row---
    Then Grand total cell value of the 'Cost in Company Currency' column of the 'Procurement & Production' table equals '$ 261,000.00'
```

## Alternate mapped Selenium scenario 2

| Field | Value |
| --- | --- |
| Feature | 2. Create Labor, Other, Travel and Material Cost in the Estimate of the Manufacturing proposal |
| Scenario | TC-Manufacturing-Proposal-007: Create Material Cost |
| Tags | `@TCM-007 @END` |
| File | `imported/twentyfive-regtest/tests/src/test/resources/features/testfolder/twentyfive2_4_create_labor__other__travel__material_cost.feature:354` |

### Scenario Sections

#### 1. Navigate to the Procurement & Production tab of the Estimate

- I wait for 7 second
- I click on 'Procurement & Production' tab
- page with name 'Estimate Procurement & Production' is opened
- I select 'Regression Test - Manufacturing' in the 'View' dropdown
- I click on 'Save' button
- text of 'View' dropdown equals 'View: Regression Test - Manufacturing (preferred)'

#### 2. Click button No records found, click here to add

- I click on 'No records found, click here to add' button
- 'Procurement & Production' table contain row with following data_-Warning-_:
- I set value '1 Test Phase 1' to the cell of the column 'Phase' of the 'Procurement & Production' table for the row with the index '1'
- 'Procurement & Production' table contain row with following data:

#### 3. Click and expand the WBS field

- I click 'WBS' in table 'Procurement & Production' table in row number 1
- I click 'WBS Dropdown' in table 'Procurement & Production' table in row number 1
- I wait for 7 seconds
- 'Opened' menu contains 2 items_-Warning-_
- 'Opened' menu contains items:

#### 4. Click and expand the Task field

- I click 'Task' in table 'Procurement & Production' table in row number 1
- I click 'Task Dropdown' in table 'Procurement & Production' table in row number 1
- I wait for 7 seconds
- 'Opened' menu contains 2 items_-Warning-_
- 'Opened' menu contains items:

#### 5. Select Test Data Value in the WBS field

- I set value '1.2 Test WBS 2' to the cell of the column 'WBS' of the 'Procurement & Production' table for the row with the index '1'

#### 6. Click and expand the Task field

- I click 'Task' in table 'Procurement & Production' table in row number 1
- I click 'Task Dropdown' in table 'Procurement & Production' table in row number 1
- I wait for 7 seconds
- 'Opened' menu contains 1 items_-Warning-_
- 'Opened' menu contains items:

#### 7. Select Test Data Value in the Task field

- I set value '1.2.1 Task 1 for Test WBS 2' to the cell of the column 'Task' of the 'Procurement & Production' table for the row with the index '1'

#### 8. Select Test Data Value in the WBS field

- I set value '1.1 Test WBS 1' to the cell of the column 'WBS' of the 'Procurement & Production' table for the row with the index '1'
- 'Procurement & Production' table contain row with following data:

#### 9. Select Test Data Value in the Task field

- I set value '1.1.1 Task 1 for Test WBS 1' to the cell of the column 'Task' of the 'Procurement & Production' table for the row with the index '1'
- page with name 'Estimate Procurement & Production' is opened

#### 10. Select Test Data Value in the Resource Group field

- I set value 'REG_MATERIAL1' to the cell of the column 'Part Number' of the 'Procurement & Production' table for the row with the index '1'

#### 11. Click Yes button on the Recost popup up

- 'Procurement & Production' table contain row with following data:
- 'Procurement & Production' table contain row with following data:
- the cell value of the 'Purchased' column of the 'Procurement & Production' table is checked for a row with the following data:

#### 12. Update the End field to the Test Data Value

- I set value '4/1/25' to the cell of the column 'End' of the 'Procurement & Production' table for the row with the index '1'

#### 13. In the Source Unit Cost field enter the Test Data Value

- I set value '120000' to the cell of the column 'Source Unit Cost' of the 'Procurement & Production' table for the row with the index '1'
- 'Procurement & Production' table contain row with following data:
- 'Procurement & Production' table contain row with following data:

#### 14. Click the Copy icon of the REG_MATERIAL1 row

- I click 'Copy Row' in table 'Procurement & Production' table in row number 1

#### 15. Update the Description field to the Test Data Value

- I set value 'REG_MATERIAL1 Copy' to the cell of the column 'Description' of the 'Procurement & Production' table for the row with the index '2'
- 'Procurement & Production' table contain row with following data:
- the cell value of the 'Purchased' column of the 'Procurement & Production' table is checked for a row with the following data:

#### 16. Update the Start field to the Test Data Value

- I set value '7/1/25' to the cell of the column 'Start' of the 'Procurement & Production' table for the row with the following data:
- 'Procurement & Production' table contain row with following data:

#### 17. In the Source Unit Cost field of the REG_MATERIAL1 Copy row enter the Test Data Value

- I set value '60000' to the cell of the column 'Source Unit Cost' of the 'Procurement & Production' table for the row with the following data:
- 'Procurement & Production' table contain row with following data:

#### 18. Click the "+" symbol of the REG_MATERIAL1 Copy row

- I click '+' in table 'Procurement & Production' table in row number 2
- I set value '1 Test Phase 1' to the cell of the column 'Phase' of the 'Procurement & Production' table for the row with the index '3'

#### 19. Select Test Data Value for the WBS field

- I set value '1.2 Test WBS 2' to the cell of the column 'WBS' of the 'Procurement & Production' table for the row with the index '3'

#### 20. Select Test Data Value for the Task field

- I set value '1.2.1 Task 1 for Test WBS 2' to the cell of the column 'Task' of the 'Procurement & Production' table for the row with the index '3'

#### 21. Select Test Data Value for the Part Number field

- I set value 'REG_MATERIAL2' to the cell of the column 'Part Number' of the 'Procurement & Production' table for the row with the index '3'
- 'Procurement & Production' table contain row with following data:
- 'Procurement & Production' table contain row with following data:
- the cell value of the 'Purchased' column of the 'Procurement & Production' table is unchecked for a row with the following data:

#### 22. Update the Start field to the Test Data Value

- I set value '1/1/26' to the cell of the column 'Start' of the 'Procurement & Production' table for the row with the index '3'

#### 23. Click Save button

- I wait for 7 seconds
- I click on 'Save' button
- I wait for 7 seconds

#### 24. Click on the Gear icon of the Description field for the REG_MATERIAL2 row and click Edit Details

- I hover 'Description' cell in table 'Procurement & Production' table and click  for row with data:
- I click on 'Edit Details' menuItem
- 'Mfg Part' popUp is displayed
- I wait for 5 seconds

#### 25. Click the No records found, click here to add button in the popup's grid

- I click on 'No records found, click here to add' button
- I wait for 7 seconds

#### 26. Select Test Data Value in the Resource Group field

- I set value 'REG_CONSULTANT2' to the cell of the column 'Resource Group' of the 'Mfg' table for the row with the index '1'
- 'Mfg' table contain row with following data:

#### 27. Enter Test Data Value in the Run Time field

- I set value '900' to the cell of the column 'Run Time' of the 'Mfg' table for the row with the index '1'
- 'Mfg' table contain row with following data:

#### 28. Click Confirm Button

- I click on 'Confirm' button
- 'Mfg Part' popUp is not displayed
- 'Procurement & Production' table contain row with following data:

#### 29. Click Save button

- I wait for 5 seconds
- I click on 'Save' button
- I wait for 5 seconds

#### 30. Check the Summary row

- Grand total cell value of the 'Cost in Company Currency' column of the 'Procurement & Production' table equals '$ 261,000.00'

### Gherkin Excerpt

```gherkin
  Scenario: TC-Manufacturing-Proposal-007: Create Material Cost
    And info: ---1. Navigate to the Procurement & Production tab of the Estimate---
    And I wait for 7 second
    And I click on 'Procurement & Production' tab
    And page with name 'Estimate Procurement & Production' is opened
    When I select 'Regression Test - Manufacturing' in the 'View' dropdown
    And I click on 'Save' button
    Then text of 'View' dropdown equals 'View: Regression Test - Manufacturing (preferred)'
    And info: ---2. Click button No records found, click here to add---
    And I click on 'No records found, click here to add' button
    And 'Procurement & Production' table contain row with following data_-Warning-_:
      | Phase        | 1 Test Phase 1  |
    And I set value '1 Test Phase 1' to the cell of the column 'Phase' of the 'Procurement & Production' table for the row with the index '1'
    And 'Procurement & Production' table contain row with following data:
      | WBS              | 1.1 Test WBS 1  |
      | Distribute Costs | Flat (working days per month)  |
      | Start            | 4/1/25          |
      | End              | 3/31/26         |
    And info: ---3. Click and expand the WBS field---
    And I click 'WBS' in table 'Procurement & Production' table in row number 1
    And I click 'WBS Dropdown' in table 'Procurement & Production' table in row number 1
    And I wait for 7 seconds
    And 'Opened' menu contains 2 items_-Warning-_
    And 'Opened' menu contains items:
      | Test WBS 1 |
      | Test WBS 2 |
    And info: ---4. Click and expand the Task field---
    And I click 'Task' in table 'Procurement & Production' table in row number 1
    And I click 'Task Dropdown' in table 'Procurement & Production' table in row number 1
    And I wait for 7 seconds
    And 'Opened' menu contains 2 items_-Warning-_
    And 'Opened' menu contains items:
      | Task 1 for Test WBS 1 |
      | Task 2 for Test WBS 1 |
    And info: ---5. Select Test Data Value in the WBS field---
    And I set value '1.2 Test WBS 2' to the cell of the column 'WBS' of the 'Procurement & Production' table for the row with the index '1'
    And info: ---6. Click and expand the Task field---
    And I click 'Task' in table 'Procurement & Production' table in row number 1
    And I click 'Task Dropdown' in table 'Procurement & Production' table in row number 1
    And I wait for 7 seconds
    And 'Opened' menu contains 1 items_-Warning-_
    And 'Opened' menu contains items:
      | Task 1 for Test WBS 2 |
    And info: ---7. Select Test Data Value in the Task field---
    And I set value '1.2.1 Task 1 for Test WBS 2' to the cell of the column 'Task' of the 'Procurement & Production' table for the row with the index '1'
    And info: ---8. Select Test Data Value in the WBS field---
    And I set value '1.1 Test WBS 1' to the cell of the column 'WBS' of the 'Procurement & Production' table for the row with the index '1'
    And 'Procurement & Production' table contain row with following data:
      | Phase        | 1 Test Phase 1  |
      | WBS          | 1.1 Test WBS 1  |
      | Task         |                 |
    And info: ---9. Select Test Data Value in the Task field---
    And I set value '1.1.1 Task 1 for Test WBS 1' to the cell of the column 'Task' of the 'Procurement & Production' table for the row with the index '1'
    And page with name 'Estimate Procurement & Production' is opened
    And info: ---10. Select Test Data Value in the Resource Group field---
    And I set value 'REG_MATERIAL1' to the cell of the column 'Part Number' of the 'Procurement & Production' table for the row with the index '1'
    And info: ---11. Click Yes button on the Recost popup up---
    And 'Procurement & Production' table contain row with following data:
      | Part Number  | REG_MATERIAL1  |
      | WBS          | 1.1 Test WBS 1  |
    And 'Procurement & Production' table contain row with following data:
      | Description  | REG_MATERIAL1   |
      | Cost Element | Purchased Parts |
    And the cell value of the 'Purchased' column of the 'Procurement & Production' table is checked for a row with the following data:
      | Part Number  | REG_MATERIAL1   |
    And info: ---12. Update the End field to the Test Data Value---
    And I set value '4/1/25' to the cell of the column 'End' of the 'Procurement & Production' table for the row with the index '1'
    And info: ---13. In the Source Unit Cost field enter the Test Data Value---
    And I set value '120000' to the cell of the column 'Source Unit Cost' of the 'Procurement & Production' table for the row with the index '1'
    And 'Procurement & Production' table contain row with following data:
      | Part Number  | REG_MATERIAL1   |
      | WBS          | 1.1 Test WBS 1  |
    And 'Procurement & Production' table contain row with following data:
      | Description              | REG_MATERIAL1   |
      | Cost Element             | Purchased Parts |
      | Source Unit Cost         | $ 120,000.00/ea |
      | Cost in Company Currency | $ 120,000.00    |
      | Start                    | 4/1/25              |
      | End                      | 4/1/25              |
    And info: ---14. Click the Copy icon of the REG_MATERIAL1 row---
    And I click 'Copy Row' in table 'Procurement & Production' table in row number 1
    And info: ---15. Update the Description field to the Test Data Value---
    And I set value 'REG_MATERIAL1 Copy' to the cell of the column 'Description' of the 'Procurement & Production' table for the row with the index '2'
    And 'Procurement & Production' table contain row with following data:
      | Description              | REG_MATERIAL1 Copy  |
      | Cost Element             | Purchased Parts |
      | Source Unit Cost         | $ 120,000.00/ea |
      | Cost in Company Currency | $ 120,000.00    |
    And the cell value of the 'Purchased' column of the 'Procurement & Production' table is checked for a row with the following data:
      | Description              | REG_MATERIAL1 Copy  |
    And info: ---16. Update the Start field to the Test Data Value---
    And I set value '7/1/25' to the cell of the column 'Start' of the 'Procurement & Production' table for the row with the following data:
      | Description              | REG_MATERIAL1 Copy  |
    And 'Procurement & Production' table contain row with following data:
      | Description              | REG_MATERIAL1 Copy  |
      | Start                    | 7/1/25              |
      | End                      | 7/1/25              |
    And info: ---17. In the Source Unit Cost field of the REG_MATERIAL1 Copy row enter the Test Data Value---
    And I set value '60000' to the cell of the column 'Source Unit Cost' of the 'Procurement & Production' table for the row with the following data:
      | Description              | REG_MATERIAL1 Copy  |
    And 'Procurement & Production' table contain row with following data:
      | Description              | REG_MATERIAL1 Copy  |
      | Start                    | 7/1/25              |
      | End                      | 7/1/25              |
      | Source Unit Cost         | $ 60,000.00/ea      |
      | Cost in Company Currency | $ 60,000.00         |
    And info: ---18. Click the "+" symbol of the REG_MATERIAL1 Copy row---
    And I click '+' in table 'Procurement & Production' table in row number 2
    And I set value '1 Test Phase 1' to the cell of the column 'Phase' of the 'Procurement & Production' table for the row with the index '3'
    And info: ---19. Select Test Data Value for the WBS field---
    And I set value '1.2 Test WBS 2' to the cell of the column 'WBS' of the 'Procurement & Production' table for the row with the index '3'
    And info: ---20. Select Test Data Value for the Task field---
    And I set value '1.2.1 Task 1 for Test WBS 2' to the cell of the column 'Task' of the 'Procurement & Production' table for the row with the index '3'
    And info: ---21. Select Test Data Value for the Part Number field---
    And I set value 'REG_MATERIAL2' to the cell of the column 'Part Number' of the 'Procurement & Production' table for the row with the index '3'
    And 'Procurement & Production' table contain row with following data:
      | WBS          | 1.2 Test WBS 2 |
      | Part Number  | REG_MATERIAL2   |
    And 'Procurement & Production' table contain row with following data:
      | Description              | REG_MATERIAL2           |
      | Cost Element             | Construction Management |
    And the cell value of the 'Purchased' column of the 'Procurement & Production' table is unchecked for a row with the following data:
      | Part Number  | REG_MATERIAL2   |
    And info: ---22. Update the Start field to the Test Data Value---
    And I set value '1/1/26' to the cell of the column 'Start' of the 'Procurement & Production' table for the row with the index '3'
    And info: ---23. Click Save button---
    And I wait for 7 seconds
    And I click on 'Save' button
#    And 'Data saved successfully' popUp is displayed
    And I wait for 7 seconds
    And info: ---24. Click on the Gear icon of the Description field for the REG_MATERIAL2 row and click Edit Details---
    And I hover 'Description' cell in table 'Procurement & Production' table and click  for row with data:
      | Description              | REG_MATERIAL2           |
    And I click on 'Edit Details' menuItem
    And 'Mfg Part' popUp is displayed
    And I wait for 5 seconds
    And info: ---25. Click the No records found, click here to add button in the popup's grid---
    And I click on 'No records found, click here to add' button
    And I wait for 7 seconds
    And info: ---26. Select Test Data Value in the Resource Group field---
    And I set value 'REG_CONSULTANT2' to the cell of the column 'Resource Group' of the 'Mfg' table for the row with the index '1'
    And 'Mfg' table contain row with following data:
      | Resource Group              | REG_CONSULTANT2          |
      | Rate/Hr                     | $ 90.00/hr               |
    And info: ---27. Enter Test Data Value in the Run Time field---
    And I set value '900' to the cell of the column 'Run Time' of the 'Mfg' table for the row with the index '1'
    And 'Mfg' table contain row with following data:
      | Resource Group              | REG_CONSULTANT2          |
      | Rate/Hr                     | $ 90.00/hr               |
      | Total Cost                  | $ 81,000.00              |
    And info: ---28. Click Confirm Button---
    And I click on 'Confirm' button
    And 'Mfg Part' popUp is not displayed
    And 'Procurement & Production' table contain row with following data:
      | Description              | REG_MATERIAL2           |
      | Source Unit Cost         | $ 81,000.00/ea          |
      | Cost in Company Currency | $ 81,000.00             |
      | Start                    | 1/1/26              |
      | End                      | 3/31/26             |
    And info: ---29. Click Save button---
    Then I wait for 5 seconds
    And I click on 'Save' button
    Then I wait for 5 seconds
#    And 'Data saved successfully' popUp is displayed
    And info: ---30. Check the Summary row---
    Then Grand total cell value of the 'Cost in Company Currency' column of the 'Procurement & Production' table equals '$ 261,000.00'
```

---

_Generated by `scripts/generate-testcase-guides.py` from the RTA mapping and local feature files._
