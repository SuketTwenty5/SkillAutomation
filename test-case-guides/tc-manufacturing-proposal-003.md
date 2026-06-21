# TC-Manufacturing-Proposal-003 - Create an Estimate

| Field | Value |
| --- | --- |
| Test ID | TC-Manufacturing-Proposal-003 |
| Title | Create an Estimate |
| RTA page | https://twenty5.atlassian.net/wiki/spaces/RTA/pages/228261889/TC-Manufacturing-Proposal-003+Create+an+Estimate |
| Map source | `RTA_TEST_SUITE_FEATURE_MAP.md` |

## Run Instructions

When a consultant asks to run this test, use the default app URL unless they provide another URL:

```text
https://approuter-twenty5ipe-dev.cfapps.us10.hana.ondemand.com/#quote
```

For Claude Desktop, ask before launching Selenium Chrome with the default URL. If the user provides another URL, launch that URL instead.

```bash
scripts/start-debug-chrome.sh "https://approuter-twenty5ipe-dev.cfapps.us10.hana.ondemand.com/#quote"
scripts/run-twentyfive-test.sh @TCM-003
```

Duplicate feature matches exist for this Confluence page. The tag command may run more than one matching scenario until the canonical feature file is confirmed.

## Default mapped Selenium scenario

| Field | Value |
| --- | --- |
| Feature | 4. Setup Manufacturing Proposal |
| Scenario | TC-Manufacturing-Proposal-003: Create an Estimate |
| Tags | `@TCM-003 @RUN` |
| File | `imported/twentyfive-regtest/tests/src/test/resources/features/testfolder/manafacturing_proposal_setup.feature:184` |

### Scenario Sections

#### 1. Navigate to Sets/Phases tab

- click on 'Phases' tab
- page with name 'Phases page' is opened
- text of 'Total Price' widget equals 'USD 0'
- text of 'Total Cost' widget equals 'USD 0'
- text of 'Margin as Percentage' widget equals '0 %'
- 'Status' widget is displayed
- text of 'Cost & Price' status equals 'Updated'
- 'Update Cost & Prices' link is displayed
- text of 'Status & logs' link equals 'Errors: 0 jobs, 0 checks'

#### 2. If not already selected, select the view "Regression Test - Manufacturing"

- I select 'Regression Test - Manufacturing' in the 'View' dropdown
- I click on 'Save' button
- text of 'View' dropdown equals 'View: Regression Test - Manufacturing (shared & preferred)'

#### 3. Verify that The Sets/Phases grid is initialized

- number of rows in 'Phases' table equals 0
- 'No records found, click here to add' button is displayed

#### 4. Click the button No records found, click here to add

- I click on 'No records found, click here to add' button
- number of rows in 'Phases' table equals 1
- 'Phases' table contain row with following data:

#### 5. Enter Test Value Data into the Description field

- I set value 'Test Phase 1' to the cell of the column 'Description' of the 'Phases' table for the row with the following data:

#### 6. Click Save

- I click on 'Save' button

#### 7. Click Create link in the Estimate field

- I click 'Estimate' in table 'Phases' table for row with data:
- 'Estimate' popUp is displayed
- I wait for 7 seconds
- text of 'Owner' EstimatePopup equals 'Tech User'_-Warning-_
- I select 'Tech User' in the 'Owner' EstimatePopup
- I verify 'Owner' of WBS Dialog page is enabled, required, drop down and has text 'Tech User'
- I verify 'Estimating Strategy' of WBS Dialog page is enabled, required, free text and has tags 'Labor,Other,Proposal BOM,Procurement & Production'
- I verify 'Design to Cost Target' of WBS Dialog page is enabled, not required, free text and has text '0.00'
- I verify 'DTC Target Hours' of WBS Dialog page is enabled, not required, free text and is empty
- 'Confirm & Release' button is displayed
- 'Confirm & Open' button is displayed
- 'Close' button is displayed

#### 8. Click Confirm & Release button on the Estimate popup

- I click on 'Confirm & Release' button
- 'Estimate' popUp is not displayed
- 'Phases' table contain row with following data:

#### 9. Navigate to Cost Structure tab

- I click on 'Cost Structure' tab
- page with name 'Cost Structure page' is opened
- I click on 'Reload' page
- I wait for 7 seconds
- page with name 'Cost Structure page' is opened
- I select 'TEST:-DONOT_CHANGE' in the 'View' dropdown
- I click on 'Save' button
- text of 'View' dropdown equals 'View: TEST:-DONOT_CHANGE (preferred)'
- number of rows in 'WBS' table equals 2
- 'WBS' table contain row with following data:
- 'WBS' table contain row with following data:
- the cell value of the 'WP' column of the 'WBS' table is checked for a row with the following data:

#### 10. Click the "+" sign of the 2nd row and select Add (Below)

- I click '+' in table 'WBS' table for row with data:
- I click on 'Add Below' option
- 'WBS' table contain row with following data:
- the cell value of the 'WP' column of the 'WBS' table is unchecked for a row with the following data:

#### 11. In the Cost Structure field of the 2nd row enter Test Value Data

- I set value 'Test WBS 1' to the cell of the column 'Cost Structure' of the 'WBS' table for the row with the following data:
- page with name 'Cost Structure page' is opened

#### 12. Click the "+" sign of the 1.1 Test WBS 1 row and select Add (Below)

- I click '+' in table 'WBS' table for row with data:
- I click on 'Add Below' option
- 'WBS' table contain row with following data:
- the cell value of the 'WP' column of the 'WBS' table is unchecked for a row with the following data:

#### 13. Click the Type field  of the new row and select External Task value

- I set value 'External Task' to the cell of the column 'Type' of the 'WBS' table for the row with the following data:
- page with name 'Cost Structure page' is opened

#### 14. In the Cost Structure field of the row enter Test Value Data

- I set value 'Task 1 for Test WBS 1' to the cell of the column 'Cost Structure' of the 'WBS' table for the row with the following data:
- page with name 'Cost Structure page' is opened

#### 15. Click the "+" sign of the 1.1.1 Task 1 for Test WBS 1 row and select Add (Current Level)

- I click '+' in table 'WBS' table for row with data:
- I click on 'Add Below' option
- 'WBS' table contain row with following data:
- the cell value of the 'WP' column of the 'WBS' table is unchecked for a row with the following data:

#### 16. Click the Type field  of the new row and select External Task value

- I set value 'External Task' to the cell of the column 'Type' of the 'WBS' table for the row with the following data:
- page with name 'Cost Structure page' is opened

#### 17. In the Cost Structure field of the row enter Test Value Data

- I set value 'Task 2 for Test WBS 1' to the cell of the column 'Cost Structure' of the 'WBS' table for the row with the following data:
- page with name 'Cost Structure page' is opened

#### 18. Click the "+" sign of the 1.1 Test WBS 1 row and select Add (Current Level)

- I click '+' in table 'WBS' table for row with data:
- I click on 'Add Current Level' option
- 'WBS' table contain row with following data:
- the cell value of the 'WP' column of the 'WBS' table is unchecked for a row with the following data:

#### 19. In the Cost Structure field of the row enter Test Value Data

- I set value 'Test WBS 2' to the cell of the column 'Cost Structure' of the 'WBS' table for the row with the following data:
- page with name 'Cost Structure page' is opened

#### 20. Click the "+" sign of the 1.2 Test WBS 2 row and select Add (Below)

- I click '+' in table 'WBS' table for row with data:
- I click on 'Add Below' option
- 'WBS' table contain row with following data:
- the cell value of the 'WP' column of the 'WBS' table is unchecked for a row with the following data:

#### 21. Click the Type field  of the new row and select External Task value

- I set value 'External Task' to the cell of the column 'Type' of the 'WBS' table for the row with the following data:
- page with name 'Cost Structure page' is opened

#### 22. In the Cost Structure field of the row enter Test Value Data

- I set value 'Task 1 for Test WBS 2' to the cell of the column 'Cost Structure' of the 'WBS' table for the row with the following data:
- page with name 'Cost Structure page' is opened

#### 23. Click Save

- I click on 'Save' button
- I wait for 7 seconds

#### 24. Refresh the page

- I select node with name '$uniqueProposalName' in 'WBS' tree
- I choose in 'Cog' menu the next menu chain:
- I click on 'Reload' page
- I wait for 7 seconds
- I click on 'Cost Structure' tab
- I wait for 3 seconds
- page with name 'Cost Structure page' is opened
- number of rows in 'WBS' table equals 2

#### 25. Expand all collapsed rows

- I select node with name '$uniqueProposalName' in 'WBS' tree
- I choose in 'Cog' menu the next menu chain:
- page with name 'Cost Structure page' is opened
- 'WBS' table contain row with following data:
- 'WBS' table contain row with following data:
- 'WBS' table contain row with following data:
- 'WBS' table contain row with following data:
- 'WBS' table contain row with following data:
- 'WBS' table contain row with following data:
- 'WBS' table contain row with following data:

### Gherkin Excerpt

```gherkin
  Scenario: TC-Manufacturing-Proposal-003: Create an Estimate
    And info: ---1. Navigate to Sets/Phases tab---
    And click on 'Phases' tab
    Then page with name 'Phases page' is opened
    And text of 'Total Price' widget equals 'USD 0'
    And text of 'Total Cost' widget equals 'USD 0'
    And text of 'Margin as Percentage' widget equals '0 %'
    And 'Status' widget is displayed
    And text of 'Cost & Price' status equals 'Updated'
    And 'Update Cost & Prices' link is displayed
    And text of 'Status & logs' link equals 'Errors: 0 jobs, 0 checks'
    And info: ---2. If not already selected, select the view "Regression Test - Manufacturing"---
    When I select 'Regression Test - Manufacturing' in the 'View' dropdown
    And I click on 'Save' button
    Then text of 'View' dropdown equals 'View: Regression Test - Manufacturing (shared & preferred)'
    And info: ---3. Verify that The Sets/Phases grid is initialized---
    And number of rows in 'Phases' table equals 0
    And 'No records found, click here to add' button is displayed
    And info: ---4. Click the button No records found, click here to add---
    Then I click on 'No records found, click here to add' button
    And number of rows in 'Phases' table equals 1
    And 'Phases' table contain row with following data:
      | Set#        | 1                    |
      | Description |                      |
      | Start       | 4/1/25               |
      | End         | 3/31/26              |
      | Estimate    | Create               |
    And info: ---5. Enter Test Value Data into the Description field---
    And I set value 'Test Phase 1' to the cell of the column 'Description' of the 'Phases' table for the row with the following data:
      | Set#        | 1                    |
    And info: ---6. Click Save---
    And I click on 'Save' button
    And info: ---7. Click Create link in the Estimate field---
    And I click 'Estimate' in table 'Phases' table for row with data:
      | Set#        | 1                    |
    And 'Estimate' popUp is displayed
    And I wait for 7 seconds
    And text of 'Owner' EstimatePopup equals 'Tech User'_-Warning-_
    And I select 'Tech User' in the 'Owner' EstimatePopup
    And I verify 'Owner' of WBS Dialog page is enabled, required, drop down and has text 'Tech User'
    And I verify 'Estimating Strategy' of WBS Dialog page is enabled, required, free text and has tags 'Labor,Other,Proposal BOM,Procurement & Production'
    And I verify 'Design to Cost Target' of WBS Dialog page is enabled, not required, free text and has text '0.00'
    And I verify 'DTC Target Hours' of WBS Dialog page is enabled, not required, free text and is empty
    Then 'Confirm & Release' button is displayed
    Then 'Confirm & Open' button is displayed
    Then 'Close' button is displayed
    And info: ---8. Click Confirm & Release button on the Estimate popup---
    And I click on 'Confirm & Release' button
    And 'Estimate' popUp is not displayed
    And 'Phases' table contain row with following data:
      | Set#        | 1                    |
      | Description | Test Phase 1         |
      | Start       | 4/1/25               |
      | End         | 3/31/26              |
      | Estimate    | Open                 |
    And info: ---9. Navigate to Cost Structure tab---
    Then I click on 'Cost Structure' tab
    And page with name 'Cost Structure page' is opened
    And I click on 'Reload' page
    Then I wait for 7 seconds
    And page with name 'Cost Structure page' is opened
    When I select 'TEST:-DONOT_CHANGE' in the 'View' dropdown
    And I click on 'Save' button
    Then text of 'View' dropdown equals 'View: TEST:-DONOT_CHANGE (preferred)'
    And number of rows in 'WBS' table equals 2
    And 'WBS' table contain row with following data:
      | WBS Code              |                       |
      | Cost Structure        | $uniqueProposalName   |
    And 'WBS' table contain row with following data:
      | WBS Code              | 1                   |
      | Cost Structure        | 1 Test Phase 1      |
      | Type                  | Estimate            |
      | WP Owner              | Tech User           |
      | Work Package          | Open                |
      | Start                 | 4/1/25              |
      | End                   | 3/31/26             |
    And the cell value of the 'WP' column of the 'WBS' table is checked for a row with the following data:
      | WBS Code              | 1                   |
    And info: ---10. Click the "+" sign of the 2nd row and select Add (Below)---
    And I click '+' in table 'WBS' table for row with data:
      | WBS Code              | 1                   |
    And I click on 'Add Below' option
    And 'WBS' table contain row with following data:
      | WBS Code              | 1.1                 |
      | Cost Structure        | 1.1                 |
      | Type                  | WBS Element         |
    And the cell value of the 'WP' column of the 'WBS' table is unchecked for a row with the following data:
      | WBS Code              | 1.1                   |
    And info: ---11. In the Cost Structure field of the 2nd row enter Test Value Data---
    And I set value 'Test WBS 1' to the cell of the column 'Cost Structure' of the 'WBS' table for the row with the following data:
      | WBS Code              | 1.1                 |
    And page with name 'Cost Structure page' is opened
    And info: ---12. Click the "+" sign of the 1.1 Test WBS 1 row and select Add (Below)---
    And I click '+' in table 'WBS' table for row with data:
      | WBS Code              | 1.1                  |
    And I click on 'Add Below' option
    And 'WBS' table contain row with following data:
      | WBS Code              | 1.1.1                 |
      | Cost Structure        | 1.1.1                 |
      | Type                  | WBS Element           |
    And the cell value of the 'WP' column of the 'WBS' table is unchecked for a row with the following data:
      | WBS Code              | 1.1.1                   |
    And info: ---13. Click the Type field  of the new row and select External Task value---
    And I set value 'External Task' to the cell of the column 'Type' of the 'WBS' table for the row with the following data:
      | WBS Code              | 1.1.1                   |
    And page with name 'Cost Structure page' is opened
    And info: ---14. In the Cost Structure field of the row enter Test Value Data---
    And I set value 'Task 1 for Test WBS 1' to the cell of the column 'Cost Structure' of the 'WBS' table for the row with the following data:
      | WBS Code              | 1.1.1                   |
    And page with name 'Cost Structure page' is opened
    And info: ---15. Click the "+" sign of the 1.1.1 Task 1 for Test WBS 1 row and select Add (Current Level)---
    And I click '+' in table 'WBS' table for row with data:
      | WBS Code              | 1.1                  |
    And I click on 'Add Below' option
    And 'WBS' table contain row with following data:
      | WBS Code              | 1.1.2                 |
      | Cost Structure        | 1.1.2                 |
      | Type                  | WBS Element           |
    And the cell value of the 'WP' column of the 'WBS' table is unchecked for a row with the following data:
      | WBS Code              | 1.1.2                  |
    And info: ---16. Click the Type field  of the new row and select External Task value---
    And I set value 'External Task' to the cell of the column 'Type' of the 'WBS' table for the row with the following data:
      | WBS Code              | 1.1.2                   |
    And page with name 'Cost Structure page' is opened
    And info: ---17. In the Cost Structure field of the row enter Test Value Data---
    And I set value 'Task 2 for Test WBS 1' to the cell of the column 'Cost Structure' of the 'WBS' table for the row with the following data:
      | WBS Code              | 1.1.2                   |
    And page with name 'Cost Structure page' is opened
    And info: ---18. Click the "+" sign of the 1.1 Test WBS 1 row and select Add (Current Level)---
    And I click '+' in table 'WBS' table for row with data:
      | WBS Code              | 1.1                |
    And I click on 'Add Current Level' option
    And 'WBS' table contain row with following data:
      | WBS Code              | 1.2                 |
      | Cost Structure        | 1.2                 |
      | Type                  | WBS Element           |
    And the cell value of the 'WP' column of the 'WBS' table is unchecked for a row with the following data:
      | WBS Code              | 1.2                  |
    And info: ---19. In the Cost Structure field of the row enter Test Value Data---
    And I set value 'Test WBS 2' to the cell of the column 'Cost Structure' of the 'WBS' table for the row with the following data:
      | WBS Code              | 1.2                   |
    And page with name 'Cost Structure page' is opened
    And info: ---20. Click the "+" sign of the 1.2 Test WBS 2 row and select Add (Below)---
    And I click '+' in table 'WBS' table for row with data:
      | WBS Code              | 1.2                |
    And I click on 'Add Below' option
    And 'WBS' table contain row with following data:
      | WBS Code              | 1.2.1                 |
      | Cost Structure        | 1.2.1                 |
      | Type                  | WBS Element           |
    And the cell value of the 'WP' column of the 'WBS' table is unchecked for a row with the following data:
      | WBS Code              | 1.2.1                 |
    And info: ---21. Click the Type field  of the new row and select External Task value---
    And I set value 'External Task' to the cell of the column 'Type' of the 'WBS' table for the row with the following data:
      | WBS Code              | 1.2.1                 |
    And page with name 'Cost Structure page' is opened
    And info: ---22. In the Cost Structure field of the row enter Test Value Data---
    And I set value 'Task 1 for Test WBS 2' to the cell of the column 'Cost Structure' of the 'WBS' table for the row with the following data:
      | WBS Code              | 1.2.1                   |
    And page with name 'Cost Structure page' is opened
    And info: ---23. Click Save---
    Then I click on 'Save' button
    Then I wait for 7 seconds
    And info: ---24. Refresh the page---
    When I select node with name '$uniqueProposalName' in 'WBS' tree
    Then I choose in 'Cog' menu the next menu chain:
      | Expand/Collapse |
      | Expand All      |
    And I click on 'Reload' page
    Then I wait for 7 seconds
#    And page with name 'Setup page' is opened
    Then I click on 'Cost Structure' tab
    Then I wait for 3 seconds
    And page with name 'Cost Structure page' is opened
    And number of rows in 'WBS' table equals 2
    And info: ---25. Expand all collapsed rows---
    When I select node with name '$uniqueProposalName' in 'WBS' tree
    Then I choose in 'Cog' menu the next menu chain:
      | Expand/Collapse |
      | Expand All      |
    And page with name 'Cost Structure page' is opened
    Then 'WBS' table contain row with following data:
      | WBS Code       |                     |
      | Cost Structure | $uniqueProposalName   |
      | Type           |                     |
      | Start          |                     |
      | End            |                     |
    Then 'WBS' table contain row with following data:
      | WBS Code       | 1              |
      | Cost Structure | 1 Test Phase 1 |
      | Type           | Estimate       |
      | Start          | 4/1/25         |
      | End            | 3/31/26        |
    Then 'WBS' table contain row with following data:
      | WBS Code       | 1.1            |
      | Cost Structure | 1.1 Test WBS 1 |
      | Type           | WBS Element    |
      | Start          | 4/1/25              |
      | End            | 3/31/26             |
    Then 'WBS' table contain row with following data:
      | WBS Code       | 1.1.1        |
      | Cost Structure | 1.1.1 Task 1 for Test WBS 1        |
      | Type           | External Task |
      | Start          | 4/1/25              |
      | End            | 3/31/26             |
    Then 'WBS' table contain row with following data:
      | WBS Code       | 1.1.2        |
      | Cost Structure | 1.1.2 Task 2 for Test WBS 1        |
      | Type           | External Task |
      | Start          | 4/1/25              |
      | End            | 3/31/26             |
    Then 'WBS' table contain row with following data:
      | WBS Code       | 1.2          |
      | Cost Structure | 1.2 Test WBS 2          |
      | Type           | WBS Element  |
      | Start          | 4/1/25              |
      | End            | 3/31/26             |
    Then 'WBS' table contain row with following data:
      | WBS Code       | 1.2.1        |
      | Cost Structure | 1.2.1 Task 1 for Test WBS 2       |
      | Type           | External Task |
      | Start          | 4/1/25              |
      | End            | 3/31/26             |


```

## Alternate mapped Selenium scenario 2

| Field | Value |
| --- | --- |
| Feature | 1. Setup Manufacturing Proposal |
| Scenario | TC-Manufacturing-Proposal-003: Create an Estimate |
| Tags | `@TCM-003 @RUN` |
| File | `imported/twentyfive-regtest/tests/src/test/resources/features/testfolder/twentyfive2_4_setup_manufacturing_proposal.feature:191` |

### Scenario Sections

#### 1. Navigate to Sets/Phases tab

- click on 'Phases' tab
- page with name 'Phases page' is opened
- text of 'Total Price' widget equals 'USD 0'
- text of 'Total Cost' widget equals 'USD 0'
- text of 'Margin as Percentage' widget equals '0 %'
- 'Status' widget is displayed
- text of 'Cost & Price' status equals 'Updated'
- 'Update Cost & Prices' link is displayed
- text of 'Status & logs' link equals 'Errors: 0 jobs, 0 checks'

#### 2. If not already selected, select the view "Regression Test - Manufacturing"

- I select 'Regression Test View' in the 'View' dropdown
- I click on 'Save' button
- text of 'View' dropdown equals 'View: Regression Test View'

#### 3. Verify that The Sets/Phases grid is initialized

- number of rows in 'Phases' table equals 0
- 'No records found, click here to add' button is displayed

#### 4. Click the button No records found, click here to add

- I click on 'No records found, click here to add' button
- number of rows in 'Phases' table equals 1
- 'Phases' table contain row with following data:

#### 5. Enter Test Value Data into the Description field

- I set value 'Test Phase 1' to the cell of the column 'Description' of the 'Phases' table for the row with the following data:

#### 6. Click Save

- I click on 'Save' button

#### 7. Click Create link in the Estimate field

- I click 'Estimate' in table 'Phases' table for row with data:
- 'Estimate' popUp is displayed
- I wait for 7 seconds
- text of 'Owner' EstimatePopup equals 'Suket Suman'_-Warning-_
- I select 'Suket Suman' in the 'Owner' EstimatePopup
- I verify 'Owner' of WBS Dialog page is enabled, required, drop down and has text 'Suket Suman'
- I verify 'Estimating Strategy' of WBS Dialog page is enabled, required, free text and has tags 'Engineering/Functional Labor,Other Direct Costs & Factors,Proposal BOM,Consolidated BOM'
- I verify 'Design to Cost Target' of WBS Dialog page is enabled, not required, free text and has text '0.00'
- I verify 'DTC Target Hours' of WBS Dialog page is enabled, not required, free text and is empty
- 'Confirm & Release' button is displayed
- 'Confirm & Open' button is displayed
- 'Close' button is displayed

#### 8. Click Confirm & Release button on the Estimate popup

- I click on 'Confirm & Release' button
- 'Estimate' popUp is not displayed
- 'Phases' table contain row with following data:

#### 9. Navigate to Cost Structure tab

- I click on 'Cost Structure' tab
- page with name 'Cost Structure page' is opened
- I click on 'Reload' page
- I wait for 7 seconds
- page with name 'Cost Structure page' is opened
- I select 'TEST:-DONOT_CHANGE' in the 'View' dropdown
- I click on 'Save' button
- text of 'View' dropdown equals 'View: TEST:-DONOT_CHANGE (preferred)'
- number of rows in 'WBS' table equals 2
- 'WBS' table contain row with following data:
- 'WBS' table contain row with following data:
- the cell value of the 'WP' column of the 'WBS' table is checked for a row with the following data:

#### 10. Click the "+" sign of the 2nd row and select Add (Below)

- I click '+' in table 'WBS' table for row with data:
- I click on 'Add Below' option
- 'WBS' table contain row with following data:
- the cell value of the 'WP' column of the 'WBS' table is unchecked for a row with the following data:

#### 11. In the Cost Structure field of the 2nd row enter Test Value Data

- I set value 'Test WBS 1' to the cell of the column 'Cost Structure' of the 'WBS' table for the row with the following data:
- page with name 'Cost Structure page' is opened

#### 12. Click the "+" sign of the 1.1 Test WBS 1 row and select Add (Below)

- I click '+' in table 'WBS' table for row with data:
- I click on 'Add Below' option
- 'WBS' table contain row with following data:
- the cell value of the 'WP' column of the 'WBS' table is unchecked for a row with the following data:

#### 13. Click the Type field  of the new row and select External Task value

- I set value 'External Task' to the cell of the column 'Type' of the 'WBS' table for the row with the following data:
- page with name 'Cost Structure page' is opened

#### 14. In the Cost Structure field of the row enter Test Value Data

- I set value 'Task 1 for Test WBS 1' to the cell of the column 'Cost Structure' of the 'WBS' table for the row with the following data:
- page with name 'Cost Structure page' is opened

#### 15. Click the "+" sign of the 1.1.1 Task 1 for Test WBS 1 row and select Add (Current Level)

- I click '+' in table 'WBS' table for row with data:
- I click on 'Add Below' option
- 'WBS' table contain row with following data:
- the cell value of the 'WP' column of the 'WBS' table is unchecked for a row with the following data:

#### 16. Click the Type field  of the new row and select External Task value

- I set value 'External Task' to the cell of the column 'Type' of the 'WBS' table for the row with the following data:
- page with name 'Cost Structure page' is opened

#### 17. In the Cost Structure field of the row enter Test Value Data

- I set value 'Task 2 for Test WBS 1' to the cell of the column 'Cost Structure' of the 'WBS' table for the row with the following data:
- page with name 'Cost Structure page' is opened

#### 18. Click the "+" sign of the 1.1 Test WBS 1 row and select Add (Current Level)

- I click '+' in table 'WBS' table for row with data:
- I click on 'Add Current Level' option
- 'WBS' table contain row with following data:
- the cell value of the 'WP' column of the 'WBS' table is unchecked for a row with the following data:

#### 19. In the Cost Structure field of the row enter Test Value Data

- I set value 'Test WBS 2' to the cell of the column 'Cost Structure' of the 'WBS' table for the row with the following data:
- page with name 'Cost Structure page' is opened

#### 20. Click the "+" sign of the 1.2 Test WBS 2 row and select Add (Below)

- I click '+' in table 'WBS' table for row with data:
- I click on 'Add Below' option
- 'WBS' table contain row with following data:
- the cell value of the 'WP' column of the 'WBS' table is unchecked for a row with the following data:

#### 21. Click the Type field  of the new row and select External Task value

- I set value 'External Task' to the cell of the column 'Type' of the 'WBS' table for the row with the following data:
- page with name 'Cost Structure page' is opened

#### 22. In the Cost Structure field of the row enter Test Value Data

- I set value 'Task 1 for Test WBS 2' to the cell of the column 'Cost Structure' of the 'WBS' table for the row with the following data:
- page with name 'Cost Structure page' is opened

#### 23. Click Save

- I click on 'Save' button
- I wait for 7 seconds

#### 24. Refresh the page

- I select node with name '$uniqueProposalName' in 'WBS' tree
- I choose in 'Cog' menu the next menu chain:
- I click on 'Reload' page
- I wait for 7 seconds
- I click on 'Cost Structure' tab
- I wait for 3 seconds
- page with name 'Cost Structure page' is opened
- number of rows in 'WBS' table equals 2

#### 25. Expand all collapsed rows

- I select node with name '$uniqueProposalName' in 'WBS' tree
- I choose in 'Cog' menu the next menu chain:
- page with name 'Cost Structure page' is opened
- 'WBS' table contain row with following data:
- 'WBS' table contain row with following data:
- 'WBS' table contain row with following data:
- 'WBS' table contain row with following data:
- 'WBS' table contain row with following data:
- 'WBS' table contain row with following data:
- 'WBS' table contain row with following data:

### Gherkin Excerpt

```gherkin
  Scenario: TC-Manufacturing-Proposal-003: Create an Estimate
    And info: ---1. Navigate to Sets/Phases tab---
    And click on 'Phases' tab
    Then page with name 'Phases page' is opened
    And text of 'Total Price' widget equals 'USD 0'
    And text of 'Total Cost' widget equals 'USD 0'
    And text of 'Margin as Percentage' widget equals '0 %'
    And 'Status' widget is displayed
    And text of 'Cost & Price' status equals 'Updated'
    And 'Update Cost & Prices' link is displayed
    And text of 'Status & logs' link equals 'Errors: 0 jobs, 0 checks'
    And info: ---2. If not already selected, select the view "Regression Test - Manufacturing"---
    When I select 'Regression Test View' in the 'View' dropdown
    And I click on 'Save' button
    Then text of 'View' dropdown equals 'View: Regression Test View'
    And info: ---3. Verify that The Sets/Phases grid is initialized---
    And number of rows in 'Phases' table equals 0
    And 'No records found, click here to add' button is displayed
    And info: ---4. Click the button No records found, click here to add---
    Then I click on 'No records found, click here to add' button
    And number of rows in 'Phases' table equals 1
    And 'Phases' table contain row with following data:
      | Set#        | 1                    |
      | Description |                      |
      | Start       | 4/1/25               |
      | End         | 3/31/26              |
      | Estimate    | Create               |
    And info: ---5. Enter Test Value Data into the Description field---
    And I set value 'Test Phase 1' to the cell of the column 'Description' of the 'Phases' table for the row with the following data:
      | Set#        | 1                    |
    And info: ---6. Click Save---
    And I click on 'Save' button
    And info: ---7. Click Create link in the Estimate field---
    And I click 'Estimate' in table 'Phases' table for row with data:
      | Set#        | 1                    |
    And 'Estimate' popUp is displayed
    And I wait for 7 seconds
    And text of 'Owner' EstimatePopup equals 'Suket Suman'_-Warning-_
    And I select 'Suket Suman' in the 'Owner' EstimatePopup
    And I verify 'Owner' of WBS Dialog page is enabled, required, drop down and has text 'Suket Suman'
    And I verify 'Estimating Strategy' of WBS Dialog page is enabled, required, free text and has tags 'Engineering/Functional Labor,Other Direct Costs & Factors,Proposal BOM,Consolidated BOM'
    And I verify 'Design to Cost Target' of WBS Dialog page is enabled, not required, free text and has text '0.00'
    And I verify 'DTC Target Hours' of WBS Dialog page is enabled, not required, free text and is empty
    Then 'Confirm & Release' button is displayed
    Then 'Confirm & Open' button is displayed
    Then 'Close' button is displayed
    And info: ---8. Click Confirm & Release button on the Estimate popup---
    And I click on 'Confirm & Release' button
    And 'Estimate' popUp is not displayed
    And 'Phases' table contain row with following data:
      | Set#        | 1                    |
      | Description | Test Phase 1         |
      | Start       | 4/1/25               |
      | End         | 3/31/26              |
      | Estimate    | Open                 |
    And info: ---9. Navigate to Cost Structure tab---
    Then I click on 'Cost Structure' tab
    And page with name 'Cost Structure page' is opened
    And I click on 'Reload' page
    Then I wait for 7 seconds
    And page with name 'Cost Structure page' is opened
    When I select 'TEST:-DONOT_CHANGE' in the 'View' dropdown
    And I click on 'Save' button
    Then text of 'View' dropdown equals 'View: TEST:-DONOT_CHANGE (preferred)'
    And number of rows in 'WBS' table equals 2
    And 'WBS' table contain row with following data:
      | WBS Code              |                       |
      | Cost Structure        | $uniqueProposalName   |
    And 'WBS' table contain row with following data:
      | WBS Code              | 1                   |
      | Cost Structure        | 1 Test Phase 1      |
      | Type                  | Estimate            |
      | WP Owner              | Suket Suman           |
      | Work Package          | Open                |
      | Start                 | 4/1/25              |
      | End                   | 3/31/26             |
    And the cell value of the 'WP' column of the 'WBS' table is checked for a row with the following data:
      | WBS Code              | 1                   |
    And info: ---10. Click the "+" sign of the 2nd row and select Add (Below)---
    And I click '+' in table 'WBS' table for row with data:
      | WBS Code              | 1                   |
    And I click on 'Add Below' option
    And 'WBS' table contain row with following data:
      | WBS Code              | 1.1                 |
      | Cost Structure        | 1.1                 |
      | Type                  | WBS Element         |
    And the cell value of the 'WP' column of the 'WBS' table is unchecked for a row with the following data:
      | WBS Code              | 1.1                   |
    And info: ---11. In the Cost Structure field of the 2nd row enter Test Value Data---
    And I set value 'Test WBS 1' to the cell of the column 'Cost Structure' of the 'WBS' table for the row with the following data:
      | WBS Code              | 1.1                 |
    And page with name 'Cost Structure page' is opened
    And info: ---12. Click the "+" sign of the 1.1 Test WBS 1 row and select Add (Below)---
    And I click '+' in table 'WBS' table for row with data:
      | WBS Code              | 1.1                  |
    And I click on 'Add Below' option
    And 'WBS' table contain row with following data:
      | WBS Code              | 1.1.1                 |
      | Cost Structure        | 1.1.1                 |
      | Type                  | WBS Element           |
    And the cell value of the 'WP' column of the 'WBS' table is unchecked for a row with the following data:
      | WBS Code              | 1.1.1                   |
    And info: ---13. Click the Type field  of the new row and select External Task value---
    And I set value 'External Task' to the cell of the column 'Type' of the 'WBS' table for the row with the following data:
      | WBS Code              | 1.1.1                   |
    And page with name 'Cost Structure page' is opened
    And info: ---14. In the Cost Structure field of the row enter Test Value Data---
    And I set value 'Task 1 for Test WBS 1' to the cell of the column 'Cost Structure' of the 'WBS' table for the row with the following data:
      | WBS Code              | 1.1.1                   |
    And page with name 'Cost Structure page' is opened
    And info: ---15. Click the "+" sign of the 1.1.1 Task 1 for Test WBS 1 row and select Add (Current Level)---
    And I click '+' in table 'WBS' table for row with data:
      | WBS Code              | 1.1                  |
    And I click on 'Add Below' option
    And 'WBS' table contain row with following data:
      | WBS Code              | 1.1.2                 |
      | Cost Structure        | 1.1.2                 |
      | Type                  | WBS Element           |
    And the cell value of the 'WP' column of the 'WBS' table is unchecked for a row with the following data:
      | WBS Code              | 1.1.2                  |
    And info: ---16. Click the Type field  of the new row and select External Task value---
    And I set value 'External Task' to the cell of the column 'Type' of the 'WBS' table for the row with the following data:
      | WBS Code              | 1.1.2                   |
    And page with name 'Cost Structure page' is opened
    And info: ---17. In the Cost Structure field of the row enter Test Value Data---
    And I set value 'Task 2 for Test WBS 1' to the cell of the column 'Cost Structure' of the 'WBS' table for the row with the following data:
      | WBS Code              | 1.1.2                   |
    And page with name 'Cost Structure page' is opened
    And info: ---18. Click the "+" sign of the 1.1 Test WBS 1 row and select Add (Current Level)---
    And I click '+' in table 'WBS' table for row with data:
      | WBS Code              | 1.1                |
    And I click on 'Add Current Level' option
    And 'WBS' table contain row with following data:
      | WBS Code              | 1.2                 |
      | Cost Structure        | 1.2                 |
      | Type                  | WBS Element           |
    And the cell value of the 'WP' column of the 'WBS' table is unchecked for a row with the following data:
      | WBS Code              | 1.2                  |
    And info: ---19. In the Cost Structure field of the row enter Test Value Data---
    And I set value 'Test WBS 2' to the cell of the column 'Cost Structure' of the 'WBS' table for the row with the following data:
      | WBS Code              | 1.2                   |
    And page with name 'Cost Structure page' is opened
    And info: ---20. Click the "+" sign of the 1.2 Test WBS 2 row and select Add (Below)---
    And I click '+' in table 'WBS' table for row with data:
      | WBS Code              | 1.2                |
    And I click on 'Add Below' option
    And 'WBS' table contain row with following data:
      | WBS Code              | 1.2.1                 |
      | Cost Structure        | 1.2.1                 |
      | Type                  | WBS Element           |
    And the cell value of the 'WP' column of the 'WBS' table is unchecked for a row with the following data:
      | WBS Code              | 1.2.1                 |
    And info: ---21. Click the Type field  of the new row and select External Task value---
    And I set value 'External Task' to the cell of the column 'Type' of the 'WBS' table for the row with the following data:
      | WBS Code              | 1.2.1                 |
    And page with name 'Cost Structure page' is opened
    And info: ---22. In the Cost Structure field of the row enter Test Value Data---
    And I set value 'Task 1 for Test WBS 2' to the cell of the column 'Cost Structure' of the 'WBS' table for the row with the following data:
      | WBS Code              | 1.2.1                   |
    And page with name 'Cost Structure page' is opened
    And info: ---23. Click Save---
    Then I click on 'Save' button
    Then I wait for 7 seconds
    And info: ---24. Refresh the page---
    When I select node with name '$uniqueProposalName' in 'WBS' tree
    Then I choose in 'Cog' menu the next menu chain:
      | Expand/Collapse |
      | Expand All      |
    And I click on 'Reload' page
    Then I wait for 7 seconds
#    And page with name 'Setup page' is opened
    Then I click on 'Cost Structure' tab
    Then I wait for 3 seconds
    And page with name 'Cost Structure page' is opened
    And number of rows in 'WBS' table equals 2
    And info: ---25. Expand all collapsed rows---
    When I select node with name '$uniqueProposalName' in 'WBS' tree
    Then I choose in 'Cog' menu the next menu chain:
      | Expand/Collapse |
      | Expand All      |
    And page with name 'Cost Structure page' is opened
    Then 'WBS' table contain row with following data:
      | WBS Code       |                     |
      | Cost Structure | $uniqueProposalName   |
      | Type           |                     |
      | Start          |                     |
      | End            |                     |
    Then 'WBS' table contain row with following data:
      | WBS Code       | 1              |
      | Cost Structure | 1 Test Phase 1 |
      | Type           | Estimate       |
      | Start          | 4/1/25         |
      | End            | 3/31/26        |
    Then 'WBS' table contain row with following data:
      | WBS Code       | 1.1            |
      | Cost Structure | 1.1 Test WBS 1 |
      | Type           | WBS Element    |
      | Start          | 4/1/25              |
      | End            | 3/31/26             |
    Then 'WBS' table contain row with following data:
      | WBS Code       | 1.1.1        |
      | Cost Structure | 1.1.1 Task 1 for Test WBS 1        |
      | Type           | External Task |
      | Start          | 4/1/25              |
      | End            | 3/31/26             |
    Then 'WBS' table contain row with following data:
      | WBS Code       | 1.1.2        |
      | Cost Structure | 1.1.2 Task 2 for Test WBS 1        |
      | Type           | External Task |
      | Start          | 4/1/25              |
      | End            | 3/31/26             |
    Then 'WBS' table contain row with following data:
      | WBS Code       | 1.2          |
      | Cost Structure | 1.2 Test WBS 2          |
      | Type           | WBS Element  |
      | Start          | 4/1/25              |
      | End            | 3/31/26             |
    Then 'WBS' table contain row with following data:
      | WBS Code       | 1.2.1        |
      | Cost Structure | 1.2.1 Task 1 for Test WBS 2       |
      | Type           | External Task |
      | Start          | 4/1/25              |
      | End            | 3/31/26             |


```

---

_Generated by `scripts/generate-testcase-guides.py` from the RTA mapping and local feature files._
