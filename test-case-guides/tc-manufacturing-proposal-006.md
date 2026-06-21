# TC-Manufacturing-Proposal-006 - Create Other Cost

| Field | Value |
| --- | --- |
| Test ID | TC-Manufacturing-Proposal-006 |
| Title | Create Other Cost |
| RTA page | https://twenty5.atlassian.net/wiki/spaces/RTA/pages/236519425/TC-Manufacturing-Proposal-006+Create+Other+Cost |
| Map source | `RTA_TEST_SUITE_FEATURE_MAP.md` |

## Run Instructions

When a consultant asks to run this test, use the default app URL unless they provide another URL:

```text
https://approuter-twenty5ipe-dev.cfapps.us10.hana.ondemand.com/#quote
```

For Claude Desktop, ask before launching Selenium Chrome with the default URL. If the user provides another URL, launch that URL instead.

```bash
scripts/start-debug-chrome.sh "https://approuter-twenty5ipe-dev.cfapps.us10.hana.ondemand.com/#quote"
scripts/run-twentyfive-test.sh @TC-006
```

Duplicate feature matches exist for this Confluence page. The tag command may run more than one matching scenario until the canonical feature file is confirmed.

## Default mapped Selenium scenario

| Field | Value |
| --- | --- |
| Feature | 5. Cost Creation for Manufacturing Proposal |
| Scenario | TC-Manufacturing-Proposal-006: Create Other Cost |
| Tags | `@TC-006 @RUN` |
| File | `imported/twentyfive-regtest/tests/src/test/resources/features/testfolder/costCreationForManufacturingProposal.feature:184` |

### Scenario Sections

#### 1. Navigate to the Other tab of the Estimate

- I click on 'Other' tab
- page with name 'Estimate Other' is opened
- I select 'Regression Test - Manufacturing' in the 'View' dropdown
- I click on 'Save' button
- text of 'View' dropdown equals 'View: Regression Test - Manufacturing (preferred)'

#### 2. Click button No records found, click here to add

- I click on 'No records found, click here to add' button
- 'Other' table contain row with following data:
- the cell value of the 'Quantity' column of the 'Other' table equals '1 ea' for a row with the following data:_-ROUNDOFF CHECK-_

#### 3. Click and expand the WBS field

- I click 'WBS' in table 'Other' table in row number 1
- I click 'WBS Dropdown' in table 'Other' table in row number 1
- I wait for 7 seconds
- 'Opened' menu contains 2 items_-Warning-_
- 'Opened' menu contains items:

#### 4. Click and expand the Task field

- I click 'Task' in table 'Other' table in row number 1
- I click 'Task Dropdown' in table 'Other' table in row number 1
- I wait for 7 seconds
- 'Opened' menu contains 2 items_-Warning-_
- 'Opened' menu contains items:

#### 5. Select Test Data Value in the WBS field

- I set value '1.2 Test WBS 2' to the cell of the column 'WBS' of the 'Other' table for the row with the index '1'

#### 6. Click and expand the Task field

- I click 'Task' in table 'Other' table in row number 1
- I click 'Task Dropdown' in table 'Other' table in row number 1
- I wait for 7 seconds
- 'Opened' menu contains 1 items_-Warning-_
- 'Opened' menu contains items:

#### 7. Select Test Data Value in the Task field

- I set value '1.2.1 Task 1 for Test WBS 2' to the cell of the column 'Task' of the 'Other' table for the row with the index '1'

#### 8. Select Test Data Value in the WBS field

- I set value '1.1 Test WBS 1' to the cell of the column 'WBS' of the 'Other' table for the row with the index '1'
- 'Other' table contain row with following data:

#### 9. Select Test Data Value in the Task field

- I set value '1.1.1 Task 1 for Test WBS 1' to the cell of the column 'Task' of the 'Other' table for the row with the index '1'
- page with name 'Estimate Other' is opened

#### 10. Select Test Data Value in the Cost Element field

- I set value '200008 Construction Management' to the cell of the column 'Cost Element' of the 'Other' table for the row with the index '1'
- 'Other' table contain row with following data:

#### 11. Enter Test Data Value in the Description field

- I set value 'Construction Management' to the cell of the column 'Description' of the 'Other' table for the row with the index '1'
- 'Other' table contain row with following data:

#### 12. For the Construction Management row enter Test Data Value in the Unit Cost field

- I set value '180,000.00' to the cell of the column 'Unit Cost' of the 'Other' table for the row with the index '1'
- 'Other' table contain row with following data:

#### 13. For the Construction Management row enter Test Data Value in the End field

- I set value '9/30/25' to the cell of the column 'End' of the 'Other' table for the row with the index '1'
- 'Other' table contain row with following data:

#### 14. Click the "+" icon of the Construction Management row

- I click '+' in table 'Other' table in row number 1

#### 15. Select Test Data Value in the Task field of the new row

- I set value '1.1.2 Task 2 for Test WBS 1' to the cell of the column 'Task' of the 'Other' table for the row with the index '2'

#### 16. Select Test Data Value in the Cost Element field

- I set value '200009 Central Engineering' to the cell of the column 'Cost Element' of the 'Other' table for the row with the index '2'

#### 17. Enter Test Data Value in the Description field

- I set value 'Central Engineering' to the cell of the column 'Description' of the 'Other' table for the row with the index '2'

#### 18. For the Central Engineering row enter Test Data Value in the Unit Cost field

- I set value '240,000.00' to the cell of the column 'Unit Cost' of the 'Other' table for the row with the index '2'
- 'Other' table contain row with following data:

#### 19. For the Central Engineering row enter Test Data Value in the Start field

- I set value '10/1/25' to the cell of the column 'Start' of the 'Other' table for the row with the index '2'
- 'Other' table contain row with following data:

#### 20. For the Construction Management row enter Test Data Value in the Quantity field

- I set value '2' to the cell of the column 'Quantity' of the 'Other' table for the row with the following data:
- 'Other' table contain row with following data:

#### 22. Click Save

- I click on 'Save' button
- 'Data saved successfully' warning message is displayed in 30 seconds

#### 23. In the Status widget click Update Estimate Totals

- I click on 'Update Estimate totals' link
- I wait for 30 seconds
- I refresh page
- I wait for 15 seconds
- I click on 'Other' tab
- page with name 'Estimate Other' is opened

#### 24.

- text of 'Other Revenue' widget equals 'USD 0'
- text of 'Other Cost' widget equals 'USD 600K'

### Gherkin Excerpt

```gherkin
  Scenario: TC-Manufacturing-Proposal-006: Create Other Cost
    And info: ---1. Navigate to the Other tab of the Estimate---
    And I click on 'Other' tab
    And page with name 'Estimate Other' is opened
    When I select 'Regression Test - Manufacturing' in the 'View' dropdown
    And I click on 'Save' button
    Then text of 'View' dropdown equals 'View: Regression Test - Manufacturing (preferred)'
    And info: ---2. Click button No records found, click here to add---
    And I click on 'No records found, click here to add' button
    And 'Other' table contain row with following data:
      | Work Package               | Test Phase 1   |
      | WBS                        | 1.1 Test WBS 1   |
      | Type                       | Other costs      |
      | Distribution Curve         | Flat (working days per month) |
      | Start                      | 4/1/25           |
      | End                        | 3/31/26          |
    Then the cell value of the 'Quantity' column of the 'Other' table equals '1 ea' for a row with the following data:_-ROUNDOFF CHECK-_
      | Work Package               | Test Phase 1   |
    And info: ---3. Click and expand the WBS field---
    And I click 'WBS' in table 'Other' table in row number 1
    And I click 'WBS Dropdown' in table 'Other' table in row number 1
    And I wait for 7 seconds
    And 'Opened' menu contains 2 items_-Warning-_
    And 'Opened' menu contains items:
      | Test WBS 1 |
      | Test WBS 2 |
    And info: ---4. Click and expand the Task field---
    And I click 'Task' in table 'Other' table in row number 1
    And I click 'Task Dropdown' in table 'Other' table in row number 1
    And I wait for 7 seconds
    And 'Opened' menu contains 2 items_-Warning-_
    And 'Opened' menu contains items:
      | Task 1 for Test WBS 1 |
      | Task 2 for Test WBS 1 |
    And info: ---5. Select Test Data Value in the WBS field---
    And I set value '1.2 Test WBS 2' to the cell of the column 'WBS' of the 'Other' table for the row with the index '1'
    And info: ---6. Click and expand the Task field---
    And I click 'Task' in table 'Other' table in row number 1
    And I click 'Task Dropdown' in table 'Other' table in row number 1
    And I wait for 7 seconds
    And 'Opened' menu contains 1 items_-Warning-_
    And 'Opened' menu contains items:
      | Task 1 for Test WBS 2 |
    And info: ---7. Select Test Data Value in the Task field---
    And I set value '1.2.1 Task 1 for Test WBS 2' to the cell of the column 'Task' of the 'Other' table for the row with the index '1'
    And info: ---8. Select Test Data Value in the WBS field---
    And I set value '1.1 Test WBS 1' to the cell of the column 'WBS' of the 'Other' table for the row with the index '1'
    And 'Other' table contain row with following data:
      | Work Package | Test Phase 1  |
      | WBS          | 1.1 Test WBS 1  |
      | Task         |                 |
    And info: ---9. Select Test Data Value in the Task field---
    And I set value '1.1.1 Task 1 for Test WBS 1' to the cell of the column 'Task' of the 'Other' table for the row with the index '1'
    And page with name 'Estimate Other' is opened
    And info: ---10. Select Test Data Value in the Cost Element field---
    And I set value '200008 Construction Management' to the cell of the column 'Cost Element' of the 'Other' table for the row with the index '1'
    And 'Other' table contain row with following data:
      | Work Package | Test Phase 1  |
      | Cost Element | Construction Management |
    And info: ---11. Enter Test Data Value in the Description field---
    And I set value 'Construction Management' to the cell of the column 'Description' of the 'Other' table for the row with the index '1'
    And 'Other' table contain row with following data:
      | Work Package | Test Phase 1  |
      | Cost Element | Construction Management |
      | Description  | Construction Management |
    And info: ---12. For the Construction Management row enter Test Data Value in the Unit Cost field---
    And I set value '180,000.00' to the cell of the column 'Unit Cost' of the 'Other' table for the row with the index '1'
    And 'Other' table contain row with following data:
      | Description               | Construction Management |
      | Cost in Company Currency  | $ 180,000.00            |
    And info: ---13. For the Construction Management row enter Test Data Value in the End field---
    And I set value '9/30/25' to the cell of the column 'End' of the 'Other' table for the row with the index '1'
    And 'Other' table contain row with following data:
      | Description               | Construction Management |
      | End                       | 9/30/25                 |
    And info: ---14. Click the "+" icon of the Construction Management row---
    And I click '+' in table 'Other' table in row number 1
    And info: ---15. Select Test Data Value in the Task field of the new row---
    And I set value '1.1.2 Task 2 for Test WBS 1' to the cell of the column 'Task' of the 'Other' table for the row with the index '2'
    And info: ---16. Select Test Data Value in the Cost Element field---
    And I set value '200009 Central Engineering' to the cell of the column 'Cost Element' of the 'Other' table for the row with the index '2'
    And info: ---17. Enter Test Data Value in the Description field---
    And I set value 'Central Engineering' to the cell of the column 'Description' of the 'Other' table for the row with the index '2'
    And info: ---18. For the Central Engineering row enter Test Data Value in the Unit Cost field---
    And I set value '240,000.00' to the cell of the column 'Unit Cost' of the 'Other' table for the row with the index '2'
    And 'Other' table contain row with following data:
      | Task                      | Task 2 for Test WBS 1   |
      | Description               | Central Engineering     |
      | Cost in Company Currency  | $ 240,000.00            |
      | Cost Element              | Central Engineering |
    And info: ---19. For the Central Engineering row enter Test Data Value in the Start field---
    And I set value '10/1/25' to the cell of the column 'Start' of the 'Other' table for the row with the index '2'
    And 'Other' table contain row with following data:
      | Description               | Central Engineering |
      | Start                     | 10/1/25            |
    And info: ---20. For the Construction Management row enter Test Data Value in the Quantity field---
    And I set value '2' to the cell of the column 'Quantity' of the 'Other' table for the row with the following data:
      | Description               | Construction Management |
    And 'Other' table contain row with following data:
      | Description               | Construction Management |
      | Unit Cost                 | $ 180,000.00            |
      | Cost in Company Currency  | $ 360,000.00            |
    And info: ---22. Click Save---
    And I click on 'Save' button
    And 'Data saved successfully' warning message is displayed in 30 seconds
    And info: ---23. In the Status widget click Update Estimate Totals---
    When I click on 'Update Estimate totals' link
    Then I wait for 30 seconds
    And I refresh page
    Then I wait for 15 seconds
    And I click on 'Other' tab
    And page with name 'Estimate Other' is opened
    And info: ---24. ---
    Then text of 'Other Revenue' widget equals 'USD 0'
    Then text of 'Other Cost' widget equals 'USD 600K'


```

## Alternate mapped Selenium scenario 2

| Field | Value |
| --- | --- |
| Feature | 2. Create Labor, Other, Travel and Material Cost in the Estimate of the Manufacturing proposal |
| Scenario | TC-Manufacturing-Proposal-006: Create Other Cost |
| Tags | `@TC-006 @RUN` |
| File | `imported/twentyfive-regtest/tests/src/test/resources/features/testfolder/twentyfive2_4_create_labor__other__travel__material_cost.feature:185` |

### Scenario Sections

#### 1. Navigate to the Other tab of the Estimate

- I click on 'Other' tab
- page with name 'Estimate Other' is opened
- I select 'Regression Test - Manufacturing' in the 'View' dropdown
- I click on 'Save' button
- text of 'View' dropdown equals 'View: Regression Test - Manufacturing (preferred)'

#### 2. Click button No records found, click here to add

- I click on 'No records found, click here to add' button
- 'Other' table contain row with following data:
- the cell value of the 'Quantity' column of the 'Other' table equals '1 ea' for a row with the following data:_-ROUNDOFF CHECK-_

#### 3. Click and expand the WBS field

- I click 'WBS' in table 'Other' table in row number 1
- I click 'WBS Dropdown' in table 'Other' table in row number 1
- I wait for 7 seconds
- 'Opened' menu contains 2 items_-Warning-_
- 'Opened' menu contains items:

#### 4. Click and expand the Task field

- I click 'Task' in table 'Other' table in row number 1
- I click 'Task Dropdown' in table 'Other' table in row number 1
- I wait for 7 seconds
- 'Opened' menu contains 2 items_-Warning-_
- 'Opened' menu contains items:

#### 5. Select Test Data Value in the WBS field

- I set value '1.2 Test WBS 2' to the cell of the column 'WBS' of the 'Other' table for the row with the index '1'

#### 6. Click and expand the Task field

- I click 'Task' in table 'Other' table in row number 1
- I click 'Task Dropdown' in table 'Other' table in row number 1
- I wait for 7 seconds
- 'Opened' menu contains 1 items_-Warning-_
- 'Opened' menu contains items:

#### 7. Select Test Data Value in the Task field

- I set value '1.2.1 Task 1 for Test WBS 2' to the cell of the column 'Task' of the 'Other' table for the row with the index '1'

#### 8. Select Test Data Value in the WBS field

- I set value '1.1 Test WBS 1' to the cell of the column 'WBS' of the 'Other' table for the row with the index '1'
- 'Other' table contain row with following data:

#### 9. Select Test Data Value in the Task field

- I set value '1.1.1 Task 1 for Test WBS 1' to the cell of the column 'Task' of the 'Other' table for the row with the index '1'
- page with name 'Estimate Other' is opened

#### 10. Select Test Data Value in the Cost Element field

- I set value '200008 Construction Management' to the cell of the column 'Cost Element' of the 'Other' table for the row with the index '1'
- 'Other' table contain row with following data:

#### 11. Enter Test Data Value in the Description field

- I set value 'Construction Management' to the cell of the column 'Description' of the 'Other' table for the row with the index '1'
- 'Other' table contain row with following data:

#### 12. For the Construction Management row enter Test Data Value in the Unit Cost field

- I set value '180,000.00' to the cell of the column 'Unit Cost' of the 'Other' table for the row with the index '1'
- 'Other' table contain row with following data:

#### 13. For the Construction Management row enter Test Data Value in the End field

- I set value '9/30/25' to the cell of the column 'End' of the 'Other' table for the row with the index '1'
- 'Other' table contain row with following data:

#### 14. Click the "+" icon of the Construction Management row

- I click '+' in table 'Other' table in row number 1

#### 15. Select Test Data Value in the Task field of the new row

- I set value '1.1.2 Task 2 for Test WBS 1' to the cell of the column 'Task' of the 'Other' table for the row with the index '2'

#### 16. Select Test Data Value in the Cost Element field

- I set value '200009 Central Engineering' to the cell of the column 'Cost Element' of the 'Other' table for the row with the index '2'

#### 17. Enter Test Data Value in the Description field

- I set value 'Central Engineering' to the cell of the column 'Description' of the 'Other' table for the row with the index '2'

#### 18. For the Central Engineering row enter Test Data Value in the Unit Cost field

- I set value '240,000.00' to the cell of the column 'Unit Cost' of the 'Other' table for the row with the index '2'
- 'Other' table contain row with following data:

#### 19. For the Central Engineering row enter Test Data Value in the Start field

- I set value '10/1/25' to the cell of the column 'Start' of the 'Other' table for the row with the index '2'
- 'Other' table contain row with following data:

#### 20. For the Construction Management row enter Test Data Value in the Quantity field

- I set value '2' to the cell of the column 'Quantity' of the 'Other' table for the row with the following data:
- 'Other' table contain row with following data:

#### 22. Click Save

- I click on 'Save' button
- 'Data saved successfully' warning message is displayed in 30 seconds

#### 23. In the Status widget click Update Estimate Totals

- I click on 'Update Estimate totals' link
- I wait for 30 seconds
- I refresh page
- I wait for 15 seconds
- I click on 'Other' tab
- page with name 'Estimate Other' is opened

#### 24.

- text of 'Other Revenue' widget equals 'USD 0'
- text of 'Other Cost' widget equals 'USD 600K'

### Gherkin Excerpt

```gherkin
  Scenario: TC-Manufacturing-Proposal-006: Create Other Cost
    And info: ---1. Navigate to the Other tab of the Estimate---
    And I click on 'Other' tab
    And page with name 'Estimate Other' is opened
    When I select 'Regression Test - Manufacturing' in the 'View' dropdown
    And I click on 'Save' button
    Then text of 'View' dropdown equals 'View: Regression Test - Manufacturing (preferred)'
    And info: ---2. Click button No records found, click here to add---
    And I click on 'No records found, click here to add' button
    And 'Other' table contain row with following data:
      | Work Package               | Test Phase 1   |
      | WBS                        | 1.1 Test WBS 1   |
      | Type                       | Other costs      |
      | Distribution Curve         | Flat (working days per month) |
      | Start                      | 4/1/25           |
      | End                        | 3/31/26          |
    Then the cell value of the 'Quantity' column of the 'Other' table equals '1 ea' for a row with the following data:_-ROUNDOFF CHECK-_
      | Work Package               | Test Phase 1   |
    And info: ---3. Click and expand the WBS field---
    And I click 'WBS' in table 'Other' table in row number 1
    And I click 'WBS Dropdown' in table 'Other' table in row number 1
    And I wait for 7 seconds
    And 'Opened' menu contains 2 items_-Warning-_
    And 'Opened' menu contains items:
      | Test WBS 1 |
      | Test WBS 2 |
    And info: ---4. Click and expand the Task field---
    And I click 'Task' in table 'Other' table in row number 1
    And I click 'Task Dropdown' in table 'Other' table in row number 1
    And I wait for 7 seconds
    And 'Opened' menu contains 2 items_-Warning-_
    And 'Opened' menu contains items:
      | Task 1 for Test WBS 1 |
      | Task 2 for Test WBS 1 |
    And info: ---5. Select Test Data Value in the WBS field---
    And I set value '1.2 Test WBS 2' to the cell of the column 'WBS' of the 'Other' table for the row with the index '1'
    And info: ---6. Click and expand the Task field---
    And I click 'Task' in table 'Other' table in row number 1
    And I click 'Task Dropdown' in table 'Other' table in row number 1
    And I wait for 7 seconds
    And 'Opened' menu contains 1 items_-Warning-_
    And 'Opened' menu contains items:
      | Task 1 for Test WBS 2 |
    And info: ---7. Select Test Data Value in the Task field---
    And I set value '1.2.1 Task 1 for Test WBS 2' to the cell of the column 'Task' of the 'Other' table for the row with the index '1'
    And info: ---8. Select Test Data Value in the WBS field---
    And I set value '1.1 Test WBS 1' to the cell of the column 'WBS' of the 'Other' table for the row with the index '1'
    And 'Other' table contain row with following data:
      | Work Package | Test Phase 1  |
      | WBS          | 1.1 Test WBS 1  |
      | Task         |                 |
    And info: ---9. Select Test Data Value in the Task field---
    And I set value '1.1.1 Task 1 for Test WBS 1' to the cell of the column 'Task' of the 'Other' table for the row with the index '1'
    And page with name 'Estimate Other' is opened
    And info: ---10. Select Test Data Value in the Cost Element field---
    And I set value '200008 Construction Management' to the cell of the column 'Cost Element' of the 'Other' table for the row with the index '1'
    And 'Other' table contain row with following data:
      | Work Package | Test Phase 1  |
      | Cost Element | Construction Management |
    And info: ---11. Enter Test Data Value in the Description field---
    And I set value 'Construction Management' to the cell of the column 'Description' of the 'Other' table for the row with the index '1'
    And 'Other' table contain row with following data:
      | Work Package | Test Phase 1  |
      | Cost Element | Construction Management |
      | Description  | Construction Management |
    And info: ---12. For the Construction Management row enter Test Data Value in the Unit Cost field---
    And I set value '180,000.00' to the cell of the column 'Unit Cost' of the 'Other' table for the row with the index '1'
    And 'Other' table contain row with following data:
      | Description               | Construction Management |
      | Cost in Company Currency  | $ 180,000.00            |
    And info: ---13. For the Construction Management row enter Test Data Value in the End field---
    And I set value '9/30/25' to the cell of the column 'End' of the 'Other' table for the row with the index '1'
    And 'Other' table contain row with following data:
      | Description               | Construction Management |
      | End                       | 9/30/25                 |
    And info: ---14. Click the "+" icon of the Construction Management row---
    And I click '+' in table 'Other' table in row number 1
    And info: ---15. Select Test Data Value in the Task field of the new row---
    And I set value '1.1.2 Task 2 for Test WBS 1' to the cell of the column 'Task' of the 'Other' table for the row with the index '2'
    And info: ---16. Select Test Data Value in the Cost Element field---
    And I set value '200009 Central Engineering' to the cell of the column 'Cost Element' of the 'Other' table for the row with the index '2'
    And info: ---17. Enter Test Data Value in the Description field---
    And I set value 'Central Engineering' to the cell of the column 'Description' of the 'Other' table for the row with the index '2'
    And info: ---18. For the Central Engineering row enter Test Data Value in the Unit Cost field---
    And I set value '240,000.00' to the cell of the column 'Unit Cost' of the 'Other' table for the row with the index '2'
    And 'Other' table contain row with following data:
      | Task                      | Task 2 for Test WBS 1   |
      | Description               | Central Engineering     |
      | Cost in Company Currency  | $ 240,000.00            |
      | Cost Element              | Central Engineering |
    And info: ---19. For the Central Engineering row enter Test Data Value in the Start field---
    And I set value '10/1/25' to the cell of the column 'Start' of the 'Other' table for the row with the index '2'
    And 'Other' table contain row with following data:
      | Description               | Central Engineering |
      | Start                     | 10/1/25            |
    And info: ---20. For the Construction Management row enter Test Data Value in the Quantity field---
    And I set value '2' to the cell of the column 'Quantity' of the 'Other' table for the row with the following data:
      | Description               | Construction Management |
    And 'Other' table contain row with following data:
      | Description               | Construction Management |
      | Unit Cost                 | $ 180,000.00            |
      | Cost in Company Currency  | $ 360,000.00            |
    And info: ---22. Click Save---
    And I click on 'Save' button
    And 'Data saved successfully' warning message is displayed in 30 seconds
    And info: ---23. In the Status widget click Update Estimate Totals---
    When I click on 'Update Estimate totals' link
    Then I wait for 30 seconds
    And I refresh page
    Then I wait for 15 seconds
    And I click on 'Other' tab
    And page with name 'Estimate Other' is opened
    And info: ---24. ---
    Then text of 'Other Revenue' widget equals 'USD 0'
    Then text of 'Other Cost' widget equals 'USD 600K'


```

---

_Generated by `scripts/generate-testcase-guides.py` from the RTA mapping and local feature files._
