@COST-CREATION-MANUFACTURING_PROPOSAL-2-4
Feature: 2. Create Labor, Other, Travel and Material Cost in the Estimate of the Manufacturing proposal

  @TCM-005 @START
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
    And info: ---11. Click the “+“ icon of the REG_CONSULTANT1 row---
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

  @TC-006 @RUN
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
    And info: ---14. Click the “+“ icon of the Construction Management row---
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


  @TCM-006.1 @RUN
  Scenario: TC-Manufacturing-Proposal-006.1: Create Travel Cost
    And I refresh page
    And I wait for 15 seconds
    And page with name 'Estimate Labor' is opened
    And I click on 'Other' tab
    And page with name 'Estimate Other' is opened
    When I select 'Regression Test - Manufacturing' in the 'View' dropdown
    And I click on 'Save' button
    Then text of 'View' dropdown equals 'View: Regression Test - Manufacturing (preferred)'
    And info: ---1. Click the “+“ icon of the Central Engineering row---
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

  @TCM-007 @END
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
    And info: ---18. Click the “+” symbol of the REG_MATERIAL1 Copy row---
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
    And I hover 'Description' cell in table 'Procurement & Production' table and click ⚙️ for row with data:
      | Description              | REG_MATERIAL2           |
    And I click on 'Edit Details' menuItem
    And 'Mfg Part' popUp is displayed
    And I wait for 5 seconds
    And info: ---25. Click the No records found, click here to add button in the popup’s grid---
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
