@OVERHEAD-COST-CREATION-MANUFACTURING_PROPOSAL-2-4
Feature: 3. Fringe, G&A, Overhead Cost Creation for Manufacturing Proposal

  @TCM-008 @START
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


  @TCM-009 @RUN
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

  @TCM-010 @END
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
