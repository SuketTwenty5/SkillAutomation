@AMORTIZATION-DEPRECIATION-MANUFACTURING-PROPOSAL-2-4
Feature: 4. Amortization & Depreciation for Manufacturing Proposal

  @TCM-011 @START
  Scenario: TC-Manufacturing-Proposal-011: Labor Amortization
    Given open TC-Manufacturing-Proposal-004 boe
    And I perform Mfg 2.4 login
    And info: ---1. Navigate to the Labor tab---
    And page with name 'Estimate Labor' is opened
    When I select 'Test View for Regression' in the 'View' dropdown
    And page with name 'Estimate Labor' is opened
    When I select 'Regression Test - Manufacturing' in the 'View' dropdown
    And I click on 'Save' button
    Then text of 'View' dropdown equals 'View: Regression Test - Manufacturing (shared & preferred)'
    And info: ---2. For REG_CONSULTANT1 click the Gear icon and click Edit Details---
    And I hover 'Description' cell in table 'Labor Dual' table and click ⚙️ for row with data:
      | Description | REG_CONSULTANT1 |
    And I click on 'Edit Details' menuItem
    And 'Estimate for Labor' popup is displayed
    And info: ---3. Verify that: Total Value field = $ 232,704.00 (Labor Cost + Fringe)---
    And I click on 'Labor Amortization' tab
    Then I wait for 7 seconds
    And text of 'Total Value' dropdown equals '232,704.00'
    And info: ---4. In the Amortization Method field select Test Data Value---
    When I enter data into the next fields:
      | 'Amortization Method' field        | Straight Line           |
    And I click on 'Estimate for Labor' popup
    And text of 'Amortization Method' field equals 'Straight Line'
    And info: ---5. In the Start of Amortization field enter Test Data Value---
    When I enter data into the next fields:
      | 'Start of Amortization' datePicker        | 10/1/25           |
    And I click on 'Estimate for Labor' popup
    Then I wait for 3 seconds
    And text of 'Start of Amortization' datePicker equals '10/1/25'
    And info: ---6. Click the checkbox End amortization at contract end (3/31/26)---
    And I check 'End of Amortization' checkBox
    And I click on 'Estimate for Labor' popup
    Then I wait for 3 seconds
    And text of 'End of Amortization' datePicker equals '3/31/26'
    And I verify 'Amortization Period (months)' of Labor page is enabled, not required, numeric selection and has text '6'
    And 'Amortization Schedule' table contain row with following data:
      | Month                    | Oct-2025            |
      | Amortization             | 22,739.14           |
    And 'Amortization Schedule' table contain row with following data:
      | Month                    | Nov-2025            |
      | Amortization             | 26,564.37           |
    And 'Amortization Schedule' table contain row with following data:
      | Month                    | Dec-2025            |
      | Amortization             | 31,505.32           |
    And 'Amortization Schedule' table contain row with following data:
      | Month                    | Jan-2026            |
      | Amortization             | 38,093.32           |
    And 'Amortization Schedule' table contain row with following data:
      | Month                    | Feb-2026            |
      | Amortization             | 47,018.92           |
    And 'Amortization Schedule' table contain row with following data:
      | Month                    | Mar-2026            |
      | Amortization             | 66,782.93           |
    Then Grand total cell value of the 'Incurred Cost' column of the 'Amortization Schedule' table equals '$ 232,704.00'
    Then Grand total cell value of the 'Amortization' column of the 'Amortization Schedule' table equals '$ 232,704.00'
    And info: ---7. Click Confirm button---
    Then I click on 'Confirm' button
    And 'Estimate for Labor' popup is not displayed
    And page with name 'Estimate Labor Resources' is opened
    And info: ---8. For REG_PROJECTMANAGER click the Gear icon and click Edit Details---
    And I hover 'Description' cell in table 'Labor Dual' table and click ⚙️ for row with data:
      | Description | REG_PROJECTMANAGER |
    And I click on 'Edit Details' menuItem
    And 'Estimate for Labor' popup is displayed
    And info: ---9. Verify that: Total Value field = $ 90,816.00---
    And text of 'Total Value' dropdown equals '90,816.00'
    And info: ---10. In the Amortization Method field select Test Data Value---
    When I enter data into the next fields:
      | 'Amortization Method' field        | Straight Line           |
    And I click on 'Estimate for Labor' popup
    And text of 'Amortization Method' field equals 'Straight Line'
    And info: ---11. Click the checkbox Start amortization at incurred start (4/1/25)---
    And I check 'Start of Amortization' checkBox
    And I click on 'Estimate for Labor' popup
    And text of 'Start of Amortization' datePicker equals '4/1/25'
    And info: ---12. In the End of Amortization field enter Test Data Value and verify period & grid values---
    When I enter data into the next fields:
      | 'End of Amortization' datePicker        | 9/30/25           |
    And I click on 'Estimate for Labor' popup
    Then I wait for 7 seconds
    And text of 'End of Amortization' datePicker equals '9/30/25'
    And I verify 'Amortization Period (months)' of Labor page is enabled, not required, numeric selection and has text '6'
    And 'Amortization Schedule' table contain row with following data:
      | Month                    | Apr-2025            |
      | Amortization             | 3,721.97             |
    And 'Amortization Schedule' table contain row with following data:
      | Month                    | May-2025            |
      | Amortization             | 8,337.21            |
    And 'Amortization Schedule' table contain row with following data:
      | Month                    | Jun-2025            |
      | Amortization             | 13,920.17           |
    And 'Amortization Schedule' table contain row with following data:
      | Month                    | Jul-2025            |
      | Amortization             | 21,612.22           |
    And 'Amortization Schedule' table contain row with following data:
      | Month                    | Aug-2025            |
      | Amortization             | 21,612.22           |
    And 'Amortization Schedule' table contain row with following data:
      | Month                    | Sep-2025            |
      | Amortization             | 21,612.22           |
    Then Grand total cell value of the 'Incurred Cost' column of the 'Amortization Schedule' table equals '$ 90,816.00'
    Then Grand total cell value of the 'Amortization' column of the 'Amortization Schedule' table equals '$ 90,816.00'
    And info: ---13. Click Confirm button---
    Then I click on 'Confirm' button
    And 'Estimate for Labor' popup is not displayed
    And page with name 'Estimate Labor Resources' is opened
    And info: ---14. Via the menu, go to Open → Open Cost Price Analysis---
    Then I click on 'Hamburger' menu
    Then I choose in 'Hamburger' menu the next menu chain:
      | Open                     |
      | Open Cost Price Analysis |
    And page with name 'Estimate Labor' is opened
    Then I switch to tab 2
    And page with name 'Cost Price Analysis Wbs page' is opened
    And info: ---15. Click the Workbench tab (unless it is already presented after previous step)---
    Then I click on 'Workbench' tab
    Then page with name 'Workbench page' is opened
    And info: ---16. Select view Regression Test - Amortization & Depreciation---
    When I select 'Regression Test - Amortization & Depreciation' in the 'View' dropdown
    Then page with name 'Workbench page' is opened
    And info: ---17. Expand: Metrics, Depreciation or Amortization, Labor costs, Years: 2025, 2026---
    Then I expand collapsed items
    Then page with name 'Workbench page' is opened
    And info: ---18. Verify that: Amortization values for REG_CONSULTANT1 and REG_PROJECTMANAGER are as expected---
    And 'Workbench' table contain row with following data:
      | Description              | REG_CONSULTANT1    |
      | Cost: Company Currency   | $ 0.00             |
      | 2025 Qtr: 2              | $ -58,016.60       |
      | 2025 Qtr: 3              | $ -58,654.44       |
      | 2025 Qtr: 4              | $ 22,155.09        |
      | 2026 Qtr: 1              | $ 94,515.95        |
    And 'Workbench' table contain row with following data:
      | Description              | REG_PROJECTMANAGER |
      | Cost: Company Currency   | $ 0.00             |
      | 2025 Qtr: 2              | $ -51,336.59       |
      | 2025 Qtr: 3              | $ 14,130.22        |
      | 2025 Qtr: 4              | $ 37,206.38        |

  @TCM-012 @END
  Scenario: TC-Manufacturing-Proposal-012: Material Depreciation
    And I close current browser tab
    Then I switch to tab 1
    And info: ---1. Navigate to the Procurement & Production tab---
    And I click on 'Procurement & Production' tab
    And page with name 'Estimate Procurement & Production' is opened
    When I select 'Regression Test - Manufacturing' in the 'View' dropdown
    And I click on 'Save' button
    Then text of 'View' dropdown equals 'View: Regression Test - Manufacturing (preferred)'
    And page with name 'Estimate Procurement & Production' is opened
    And info: ---2. For REG_MATERIAL1 click the Gear icon and click Edit Details---
    And I hover 'Description' cell in table 'Procurement & Production' table and click ⚙️ for row with data:
      | Description              | REG_MATERIAL1 |
      | Cost in Company Currency | $ 120,000.00  |
    And I click on 'Edit Details' menuItem
    And 'Estimate for Labor' popup is displayed
    Then I wait for 7 seconds
    And info: ---3. Open the Asset Depreciation tab on the popup---
    And I click on 'Asset Depreciation' tab
    Then I wait for 7 seconds
    And info: ---4. Verify that: Total Value field = $ 141,240.00 (Source Unit Cost + Overhead + G&A)---
    And text of 'Total Value' dropdown equals '141,240.00'
    And info: ---5. In the Depreciation Method field select Test Data Value---
    When I enter data into the next fields:
      | 'Depreciation Method' field        | Straight Line           |
    And I click on 'Estimate for Labor' popup
    And text of 'Depreciation Method' field equals 'Straight Line'
    And info: ---6. In the In-Service Date (start of depreciation) field enter Test Data Value---
    When I enter data into the next fields:
      | 'Start of Depreciation' datePicker        | 10/1/25           |
    And I click on 'Estimate for Labor' popup
    Then I wait for 3 seconds
    And text of 'Start of Depreciation' datePicker equals '10/1/25'
    And info: ---7. Click the checkbox Transfer asset to customer at contract end and verify depreciation details---
    And I check 'End of Depreciation' checkBox
    And I click on 'Estimate for Labor' popup
    And text of 'End of Depreciation' datePicker equals '3/31/26'
    And I verify 'Depreciation Period (months)' of Labor page is enabled, not required, numeric selection and has text '6'
    And 'Amortization Schedule' table contain row with following data:
      | Month                    | Oct-2025            |
      | Depreciation             | 23,540.00           |
    And 'Amortization Schedule' table contain row with following data:
      | Month                    | Nov-2025            |
      | Depreciation             | 23,540.00           |
    And 'Amortization Schedule' table contain row with following data:
      | Month                    | Dec-2025            |
      | Depreciation             | 23,540.00           |
    And 'Amortization Schedule' table contain row with following data:
      | Month                    | Jan-2026            |
      | Depreciation             | 23,540.00           |
    And 'Amortization Schedule' table contain row with following data:
      | Month                    | Feb-2026            |
      | Depreciation             | 23,540.00           |
    And 'Amortization Schedule' table contain row with following data:
      | Month                    | Mar-2026            |
      | Depreciation             | 23,540.00           |
    Then Grand total cell value of the 'Incurred Cost' column of the 'Amortization Schedule' table equals '$ 141,240.00'
    Then Grand total cell value of the 'Depreciation' column of the 'Amortization Schedule' table equals '$ 141,240.00'
    And info: ---8. Click Confirm button---
    Then I click on 'Confirm' button
    And 'Estimate for Labor' popup is not displayed
    And page with name 'Estimate Procurement & Production' is opened
    And info: ---9. For REG_MATERIAL1 Copy click the Gear icon and click Edit Details---
    And I hover 'Description' cell in table 'Procurement & Production' table and click ⚙️ for row with data:
      | Description              | REG_MATERIAL1 Copy |
      | Cost in Company Currency | $ 60,000.00        |
    And I click on 'Edit Details' menuItem
    And 'Estimate for Labor' popup is displayed
    Then I wait for 7 seconds
    And I click on 'Asset Depreciation' tab
    Then I wait for 7 seconds
    And info: ---10. Verify that: Total Value field = $70,620.00 (Source Unit Cost + Overhead + G&A)---
    And text of 'Total Value' dropdown equals '70,620.00'
    And info: ---11. In the Depreciation Method field select Test Data Value---
    When I enter data into the next fields:
      | 'Depreciation Method' field        | Straight Line           |
    And I click on 'Estimate for Labor' popup
    And text of 'Depreciation Method' field equals 'Straight Line'
    And info: ---12. Click the checkbox Start depreciation at incurred start (7/1/25)---
    And I check 'Start of Depreciation' checkBox
    And I click on 'Estimate for Labor' popup
    And text of 'Start of Depreciation' datePicker equals '7/1/25'
    And info: ---13. In the End of Life (end of depreciation) field enter Test Data Value and verify depreciation schedule---
    When I enter data into the next fields:
      | 'End of Depreciation' datePicker        | 12/31/25           |
    And I click on 'Estimate for Labor' popup
    Then I wait for 7 seconds
    And text of 'End of Depreciation' datePicker equals '12/31/25'
    And I verify 'Depreciation Period (months)' of Labor page is enabled, not required, numeric selection and has text '6'
    And 'Amortization Schedule' table contain row with following data:
      | Month                    | Jul-2025            |
      | Depreciation             | 11,770.00           |
    And 'Amortization Schedule' table contain row with following data:
      | Month                    | Aug-2025            |
      | Depreciation             | 11,770.00           |
    And 'Amortization Schedule' table contain row with following data:
      | Month                    | Sep-2025            |
      | Depreciation             | 11,770.00           |
    And 'Amortization Schedule' table contain row with following data:
      | Month                    | Oct-2025            |
      | Depreciation             | 11,770.00           |
    And 'Amortization Schedule' table contain row with following data:
      | Month                    | Nov-2025            |
      | Depreciation             | 11,770.00           |
    And 'Amortization Schedule' table contain row with following data:
      | Month                    | Dec-2025            |
      | Depreciation             | 11,770.00           |
    Then Grand total cell value of the 'Incurred Cost' column of the 'Amortization Schedule' table equals '$ 70,620.00'
    Then Grand total cell value of the 'Depreciation' column of the 'Amortization Schedule' table equals '$ 70,620.00'
    And info: ---14. Click Confirm button---
    Then I click on 'Confirm' button
    And 'Estimate for Labor' popup is not displayed
    And page with name 'Estimate Procurement & Production' is opened
    And I click on 'Save' button
    And page with name 'Estimate Procurement & Production' is opened
    When I click on 'Update Estimate totals' link
    Then I wait for 30 seconds
    And page with name 'Estimate Procurement & Production' is opened
    And info: ---15. Via the menu, go to Open → Open Cost Price Analysis and ensure Workbench tab is shown---
    Then I click on 'Hamburger' menu
    Then I choose in 'Hamburger' menu the next menu chain:
      | Open                     |
      | Open Cost Price Analysis |
    And page with name 'Estimate Procurement & Production' is opened
    Then I switch to tab 2
    And I wait for 10 seconds
    Then I click on 'Workbench' tab
    Then page with name 'Workbench page' is opened
    And info: ---16. Select view Regression Test - Amortization & Depreciation---
    When I select 'Regression Test - Amortization & Depreciation' in the 'View' dropdown
    Then page with name 'Workbench page' is opened
    And info: ---17. Expand: Metrics, Depreciation or Amortization, Material costs, Years: 2025, 2026---
    Then I expand collapsed items
    Then page with name 'Workbench page' is opened
    And info: ---18. Verify that: Depreciation values for REG_MATERIAL1 and REG_MATERIAL1 Copy are correctly reflected in quarterly data---
    And 'Workbench' table contain row with following data:
      | Description              | REG_MATERIAL1      |
      | Cost: Company Currency   | $ 0.00             |
      | 2025 Qtr: 2              | $ -141,240.00      |
      | 2025 Qtr: 3              |                    |
      | 2025 Qtr: 4              | $ 70,620.00        |
      | 2026 Qtr: 1              | $ 70,620.00        |
    And 'Workbench' table contain row with following data:
      | Description              | REG_MATERIAL1 Copy |
      | Cost: Company Currency   | $ 0.00             |
      | 2025 Qtr: 2              |                    |
      | 2025 Qtr: 3              | $ -35,310.00       |
      | 2025 Qtr: 4              | $ 35,310.00        |
      | 2026 Qtr: 1              |                    |