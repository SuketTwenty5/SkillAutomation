# TC-Manufacturing-Proposal-006.1 - Create Travel Cost

| Field | Value |
| --- | --- |
| Test ID | TC-Manufacturing-Proposal-006.1 |
| Title | Create Travel Cost |
| RTA page | https://twenty5.atlassian.net/wiki/spaces/RTA/pages/245530625/TC-Manufacturing-Proposal-006.1+Create+Travel+Cost |
| Map source | `RTA_TEST_SUITE_FEATURE_MAP.md` |

## Run Instructions

When a consultant asks to run this test, use the default app URL unless they provide another URL:

```text
https://approuter-twenty5ipe-dev.cfapps.us10.hana.ondemand.com/#quote
```

For Claude Desktop, ask before launching Selenium Chrome with the default URL. If the user provides another URL, launch that URL instead.

```bash
scripts/start-debug-chrome.sh "https://approuter-twenty5ipe-dev.cfapps.us10.hana.ondemand.com/#quote"
scripts/run-twentyfive-test.sh @TCM-006.1
```

Duplicate feature matches exist for this Confluence page. The tag command may run more than one matching scenario until the canonical feature file is confirmed.

## Default mapped Selenium scenario

| Field | Value |
| --- | --- |
| Feature | 5. Cost Creation for Manufacturing Proposal |
| Scenario | TC-Manufacturing-Proposal-006.1: Create Travel Cost |
| Tags | `@TCM-006.1 @RUN` |
| File | `imported/twentyfive-regtest/tests/src/test/resources/features/testfolder/costCreationForManufacturingProposal.feature:302` |

### Scenario Sections

#### Setup

- I refresh page
- I wait for 15 seconds
- page with name 'Estimate Labor' is opened
- I click on 'Other' tab
- page with name 'Estimate Other' is opened
- I select 'Regression Test - Manufacturing' in the 'View' dropdown
- I click on 'Save' button
- text of 'View' dropdown equals 'View: Regression Test - Manufacturing (preferred)'

#### 1. Click the "+" icon of the Central Engineering row

- I click '+' in table 'Other' table in row number 2

#### 2. Select Test Data Value in the WBS field of the new row

- I set value '1.2 Test WBS 2' to the cell of the column 'WBS' of the 'Other' table for the row with the index '3'

#### 3. Select Test Data Value in the Task field of the new row

- I set value '1.2.1 Task 1 for Test WBS 2' to the cell of the column 'Task' of the 'Other' table for the row with the index '3'

#### 4. Select Test Data Value in the Type field of the new row

- I set value 'Travel costs' to the cell of the column 'Type' of the 'Other' table for the row with the index '3'

#### 5. Select Test Data Value in the Cost Element field

- I set value '300007 Travel Expenses' to the cell of the column 'Cost Element' of the 'Other' table for the row with the index '3'

#### 6. Enter Test Data Value in the Description field

- I set value 'Travel Expenses' to the cell of the column 'Description' of the 'Other' table for the row with the index '3'

#### 7. For the Travel Expenses row enter Test Data Value in the Unit Cost field

- I set value '5,000.00' to the cell of the column 'Unit Cost' of the 'Other' table for the row with the index '3'
- 'Other' table contain row with following data:

#### 8. For the Travel Expenses row enter Test Data Value in the Quantity field

- I set value '6' to the cell of the column 'Quantity' of the 'Other' table for the row with the following data:
- 'Other' table contain row with following data:

#### 9.

- Grand total cell value of the 'Cost in Company Currency' column of the 'Other' table equals '$ 630,000.00'

#### 10. Click Save

- I click on 'Save' button
- 'Data saved successfully' warning message is displayed in 30 seconds

#### 11. In the Status widget click Update Estimate Totals

- I click on 'Update Estimate totals' link
- I wait for 30 seconds
- I refresh page
- I wait for 15 seconds
- I click on 'Other' tab
- page with name 'Estimate Other' is opened

#### 12.

- text of 'Travel Revenue' widget equals 'USD 0'
- text of 'Travel Expenses' widget equals 'USD 30K'

### Gherkin Excerpt

```gherkin
  Scenario: TC-Manufacturing-Proposal-006.1: Create Travel Cost
    And I refresh page
    And I wait for 15 seconds
    And page with name 'Estimate Labor' is opened
    And I click on 'Other' tab
    And page with name 'Estimate Other' is opened
    When I select 'Regression Test - Manufacturing' in the 'View' dropdown
    And I click on 'Save' button
    Then text of 'View' dropdown equals 'View: Regression Test - Manufacturing (preferred)'
    And info: ---1. Click the "+" icon of the Central Engineering row---
    And I click '+' in table 'Other' table in row number 2
    And info: ---2. Select Test Data Value in the WBS field of the new row---
    And I set value '1.2 Test WBS 2' to the cell of the column 'WBS' of the 'Other' table for the row with the index '3'
    And info: ---3. Select Test Data Value in the Task field of the new row---
    And I set value '1.2.1 Task 1 for Test WBS 2' to the cell of the column 'Task' of the 'Other' table for the row with the index '3'
    And info: ---4. Select Test Data Value in the Type field of the new row---
    And I set value 'Travel costs' to the cell of the column 'Type' of the 'Other' table for the row with the index '3'
    And info: ---5. Select Test Data Value in the Cost Element field---
    And I set value '300007 Travel Expenses' to the cell of the column 'Cost Element' of the 'Other' table for the row with the index '3'
    And info: ---6. Enter Test Data Value in the Description field---
    And I set value 'Travel Expenses' to the cell of the column 'Description' of the 'Other' table for the row with the index '3'
    And info: ---7. For the Travel Expenses row enter Test Data Value in the Unit Cost field---
    And I set value '5,000.00' to the cell of the column 'Unit Cost' of the 'Other' table for the row with the index '3'
    And 'Other' table contain row with following data:
      | Description               | Travel Expenses |
      | Unit Cost                 | $ 5,000.00      |
      | Cost in Company Currency  | $ 5,000.00      |
    And info: ---8. For the Travel Expenses row enter Test Data Value in the Quantity field---
    And I set value '6' to the cell of the column 'Quantity' of the 'Other' table for the row with the following data:
      | Description               | Travel Expenses |
    And 'Other' table contain row with following data:
      | Description               | Travel Expenses |
      | Unit Cost                 | $ 5,000.00      |
      | Cost in Company Currency  | $ 30,000.00     |
    And info: ---9. ---
    Then Grand total cell value of the 'Cost in Company Currency' column of the 'Other' table equals '$ 630,000.00'
    And info: ---10. Click Save---
    And I click on 'Save' button
    And 'Data saved successfully' warning message is displayed in 30 seconds
    And info: ---11. In the Status widget click Update Estimate Totals---
    When I click on 'Update Estimate totals' link
    Then I wait for 30 seconds
    And I refresh page
    Then I wait for 15 seconds
    And I click on 'Other' tab
    And page with name 'Estimate Other' is opened
    And info: ---12. ---
    Then text of 'Travel Revenue' widget equals 'USD 0'
    Then text of 'Travel Expenses' widget equals 'USD 30K'

```

## Alternate mapped Selenium scenario 2

| Field | Value |
| --- | --- |
| Feature | 2. Create Labor, Other, Travel and Material Cost in the Estimate of the Manufacturing proposal |
| Scenario | TC-Manufacturing-Proposal-006.1: Create Travel Cost |
| Tags | `@TCM-006.1 @RUN` |
| File | `imported/twentyfive-regtest/tests/src/test/resources/features/testfolder/twentyfive2_4_create_labor__other__travel__material_cost.feature:303` |

### Scenario Sections

#### Setup

- I refresh page
- I wait for 15 seconds
- page with name 'Estimate Labor' is opened
- I click on 'Other' tab
- page with name 'Estimate Other' is opened
- I select 'Regression Test - Manufacturing' in the 'View' dropdown
- I click on 'Save' button
- text of 'View' dropdown equals 'View: Regression Test - Manufacturing (preferred)'

#### 1. Click the "+" icon of the Central Engineering row

- I click '+' in table 'Other' table in row number 2

#### 2. Select Test Data Value in the WBS field of the new row

- I set value '1.2 Test WBS 2' to the cell of the column 'WBS' of the 'Other' table for the row with the index '3'

#### 3. Select Test Data Value in the Task field of the new row

- I set value '1.2.1 Task 1 for Test WBS 2' to the cell of the column 'Task' of the 'Other' table for the row with the index '3'

#### 4. Select Test Data Value in the Type field of the new row

- I set value 'Travel costs' to the cell of the column 'Type' of the 'Other' table for the row with the index '3'

#### 5. Select Test Data Value in the Cost Element field

- I set value '300007 Travel Expenses' to the cell of the column 'Cost Element' of the 'Other' table for the row with the index '3'

#### 6. Enter Test Data Value in the Description field

- I set value 'Travel Expenses' to the cell of the column 'Description' of the 'Other' table for the row with the index '3'

#### 7. For the Travel Expenses row enter Test Data Value in the Unit Cost field

- I set value '5,000.00' to the cell of the column 'Unit Cost' of the 'Other' table for the row with the index '3'
- 'Other' table contain row with following data:

#### 8. For the Travel Expenses row enter Test Data Value in the Quantity field

- I set value '6' to the cell of the column 'Quantity' of the 'Other' table for the row with the following data:
- 'Other' table contain row with following data:

#### 9.

- Grand total cell value of the 'Cost in Company Currency' column of the 'Other' table equals '$ 630,000.00'

#### 10. Click Save

- I click on 'Save' button
- 'Data saved successfully' warning message is displayed in 30 seconds

#### 11. In the Status widget click Update Estimate Totals

- I click on 'Update Estimate totals' link
- I wait for 30 seconds
- I refresh page
- I wait for 15 seconds
- I click on 'Other' tab
- page with name 'Estimate Other' is opened

#### 12.

- text of 'Travel Revenue' widget equals 'USD 0'
- text of 'Travel Expenses' widget equals 'USD 30K'

### Gherkin Excerpt

```gherkin
  Scenario: TC-Manufacturing-Proposal-006.1: Create Travel Cost
    And I refresh page
    And I wait for 15 seconds
    And page with name 'Estimate Labor' is opened
    And I click on 'Other' tab
    And page with name 'Estimate Other' is opened
    When I select 'Regression Test - Manufacturing' in the 'View' dropdown
    And I click on 'Save' button
    Then text of 'View' dropdown equals 'View: Regression Test - Manufacturing (preferred)'
    And info: ---1. Click the "+" icon of the Central Engineering row---
    And I click '+' in table 'Other' table in row number 2
    And info: ---2. Select Test Data Value in the WBS field of the new row---
    And I set value '1.2 Test WBS 2' to the cell of the column 'WBS' of the 'Other' table for the row with the index '3'
    And info: ---3. Select Test Data Value in the Task field of the new row---
    And I set value '1.2.1 Task 1 for Test WBS 2' to the cell of the column 'Task' of the 'Other' table for the row with the index '3'
    And info: ---4. Select Test Data Value in the Type field of the new row---
    And I set value 'Travel costs' to the cell of the column 'Type' of the 'Other' table for the row with the index '3'
    And info: ---5. Select Test Data Value in the Cost Element field---
    And I set value '300007 Travel Expenses' to the cell of the column 'Cost Element' of the 'Other' table for the row with the index '3'
    And info: ---6. Enter Test Data Value in the Description field---
    And I set value 'Travel Expenses' to the cell of the column 'Description' of the 'Other' table for the row with the index '3'
    And info: ---7. For the Travel Expenses row enter Test Data Value in the Unit Cost field---
    And I set value '5,000.00' to the cell of the column 'Unit Cost' of the 'Other' table for the row with the index '3'
    And 'Other' table contain row with following data:
      | Description               | Travel Expenses |
      | Unit Cost                 | $ 5,000.00      |
      | Cost in Company Currency  | $ 5,000.00      |
    And info: ---8. For the Travel Expenses row enter Test Data Value in the Quantity field---
    And I set value '6' to the cell of the column 'Quantity' of the 'Other' table for the row with the following data:
      | Description               | Travel Expenses |
    And 'Other' table contain row with following data:
      | Description               | Travel Expenses |
      | Unit Cost                 | $ 5,000.00      |
      | Cost in Company Currency  | $ 30,000.00     |
    And info: ---9. ---
    Then Grand total cell value of the 'Cost in Company Currency' column of the 'Other' table equals '$ 630,000.00'
    And info: ---10. Click Save---
    And I click on 'Save' button
    And 'Data saved successfully' warning message is displayed in 30 seconds
    And info: ---11. In the Status widget click Update Estimate Totals---
    When I click on 'Update Estimate totals' link
    Then I wait for 30 seconds
    And I refresh page
    Then I wait for 15 seconds
    And I click on 'Other' tab
    And page with name 'Estimate Other' is opened
    And info: ---12. ---
    Then text of 'Travel Revenue' widget equals 'USD 0'
    Then text of 'Travel Expenses' widget equals 'USD 30K'

```

---

_Generated by `scripts/generate-testcase-guides.py` from the RTA mapping and local feature files._
