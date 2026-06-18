@COST-CREATION-PROFF-SERVICES
Feature: 2. Cost Creation for Professional Services

  @TC-005 @START
  Scenario: TC-Prof-Services-005: Validate Workstream [WBS] Copy & Release Estimate
    And info: ---1. Navigate to Workstreams---
    Given open TC-Prof-Services-003 quote
    And I perform login
    And page with name 'Setup page' is opened
    When I enter data into the next fields:
     |'Proposal Type' field | Regression Test Only - Consulting |
    And page with name 'Setup page' is opened
    When I enter data into the next fields:
      | 'Client Customer sell-to' field      | Regression Test - Customer USD |
    And page with name 'Setup page' is opened
    Then I click on 'Save' button
    And I wait for 7 seconds
    And page with name 'Setup page' is opened
    Given I click on 'WBS' tab
    And page with name 'WBS page' is opened
    And I click on 'Update Cost & Prices' link
    And I wait for 30 seconds
    Given I click on 'WBS' tab
    And page with name 'WBS page' is opened
    And if 'Needs Refresh' status is visible then refresh page
    And page with name 'WBS page' is opened
    And text of 'Total Price' widget equals 'USD 0'
    And text of 'Total Cost' widget equals 'USD 2.7M'_-Warning-_
    And text of 'Margin as Percentage' widget equals '0 %'
    And 'Status' widget is displayed
    And text of 'Cost & Price' status equals 'Updated'_-Warning-_
    And 'Update Cost & Prices' link is displayed
    And text of 'Status & logs' link equals 'Errors: 0 jobs, 0 checks'
    And info: ---2. Check selected View---
    When I select 'TEST:-DONOT_CHANGE' in the 'WBS View' dropdown
    And I click on 'Save' button
    Then text of 'WBS View' dropdown equals 'View: TEST:-DONOT_CHANGE (preferred)'
    And info: ---3. Review WBS grid---
    Then I should see the following columns in the 'WBS' table:
      | WBS                                |
      | WBS Code                           |
      | WP                                 |
      | Start                              |
      | End                                |
      | Estimate                           |
      | Total Cost with Risk (without Opt) Cost in Local Currency |
      | Labor Cost                         |
      | Material Cost                      |
      | Other Cost                         |
      | Delivery Organization              |
      | Profit Center                      |
      | Cost Center                        |
    And info: ---4. Verify WBS structure pulled in from Template and values ---
    And 'WBS' table contain row with following data_-Warning-_:
      | Estimate                                               | Open                     |
    And 'WBS' table contain row with following data:
      | WBS                                                    | 1 Prof Services Estimate   |
      | WBS Code                                               | 1                          |
      | Start                                                  | 1/1/2026                   |
      | End                                                    | 12/31/2028                 |
      | Total Cost with Risk (without Opt) Cost in Local Currency | $ 2,704,635.20          |
      | Labor Cost                                             | $ 1,790,960.00             |
      | Material Cost                                          | $ 0.00                     |
      | Other Cost                                             | $ 0.00                     |
      | Delivery Organization                                  | RG10 US - New York         |
      | Profit Center                                          | Services                   |
      | Cost Center                                            | REG CC-2                   |

    And '1 Prof Services Estimate' WP is enabled
    And info: ---5. Run Update Cost and Prices to refresh model via hyperlink ---
    And I click on 'Update Cost & Prices' link
    And I wait for 50 seconds
    And info: ---6. Validate Cost & Prices update ---
    Then text of 'Total Cost' widget equals 'USD 2.7M'_-Warning-_
    And info: ---7. Validate Cost & Prices update ---
    When I select 'TEST:-DONOT_CHANGE' in the 'WBS View' dropdown
    And I click on 'Save' button
    Then text of 'WBS View' dropdown equals 'View: TEST:-DONOT_CHANGE (preferred)'
    And 'WBS' table contain row with following data_-Warning-_:
      | Estimate                                               | Create                     |
    And 'WBS' table contain row with following data:
      | WBS                                                    | 1 Prof Services Estimate   |
      | WBS Code                                               | 1                          |
      | Start                                                  | 1/1/26                     |
      | End                                                    | 12/31/28                   |
      | Total Cost with Risk (without Opt) Cost in Local Currency | $ 2,704,635.20          |
      | Labor Cost                                             | $ 1,790,960.00             |
      | Material Cost                                          | $ 0.00                     |
      | Other Cost                                             | $ 0.00                     |
      | Delivery Organization                                  | RG10 US - New York         |
      | Profit Center                                          | Services                   |
      | Cost Center                                            | REG CC-2                   |
    And info: ---8. Click Create hyperlink to Release Estimate---
    And I click 'Open' in table 'WBS' table for row with data:
      | WBS  | 1 Prof Services Estimate   |
#    And 'Estimate' popUp is displayed
#    And I verify 'Owner' of WBS Dialog page is enabled, required, drop down and has text 'Tech User'
#    And I verify 'Approver' of WBS Dialog page is enabled, not required, drop down and is empty
#    And I verify 'Estimating Strategy' of WBS Dialog page is enabled, required, free text and has tags 'Labor,Other,Procurement & Production'
#    And I verify 'Response Due' of WBS Dialog page is enabled, not required, date picker and is empty
#    And I verify 'Work-stream' of WBS Dialog page is enabled, not required, drop down and is empty
#    And I verify 'Sub-work' of WBS Dialog page is enabled, not required, drop down and is empty
#    And I verify 'Design to Cost Target' of WBS Dialog page is enabled, not required, free text and has text '$'
#    And I verify 'DTC Target Hours' of WBS Dialog page is enabled, not required, free text and is empty
#    And I click on 'Confirm & Open' button
    And info: ---6. Navigate to the Estimate by clicking on the Confirm & Open button---
    Then I switch to tab 2
    And page with name 'Estimate Labor Resources' is opened
    And 'UNLOCK FOR OTHERS' link is displayed
    And I click on 'Update Estimate Totals' link
    And I wait for 30 seconds
    And page with name 'Estimate Labor Resources' is opened
    And I refresh page
    And I wait for 30 seconds
    And I click on 'Labor' tab
    And page with name 'Estimate Labor Resources' is opened

  @TC-006 @RUN
  Scenario: TC-Prof-Services-006: Validate Labor Copy
    And info: ---1. Validate available tabs under Estimates module---
    Then I see the following TABS in the 'Estimates' Top Menu_-Warning-_:
      | Labor                 |
      | Other                 |
      | Procurement & Production |
      | Risks & Contingency |
      | Work Packages |
      | Workflow              |
#    Then I see the following TABS in the 'Estimates' Top Menu_-Warning-_:
#      | Labor Resources  |
#      | Service          |
#      | IP               |
#      | Expenses         |
#      | Risk & Contingency |
#      | Work Packages    |
#      | Workflow         |
    And info: ---2. Navigate to Labor tab---
    And I verify 'Estimating Methodology' of Labor page is enabled, not required, drop down and is empty
    And 'Learn more' link is displayed
    And 'COPY LABOR' button is displayed
    And 'COPY LABOR' button is disabled
    And number of rows in 'Labor' table equals 3
    And 'Cog Settings' menu is displayed
    And 'Cog Settings' menu is dropdown
    And 'Filter' button is enabled
    And 'Download' button is enabled
    And 'Upload' button is enabled
    And info: ---3. Check selected View---
    When I select 'Test View for Regression - DONOT CHANGE' in the 'View' dropdown
    And I click on 'Save' button
    Then text of 'View' dropdown equals 'View: Test View for Regression - DONOT CHANGE (preferred)'
    And page with name 'Estimate Labor Resources' is opened
    And info: ---4. Review WBS grid---
    Then I should see the following columns in the 'Labor' table:
      | Resource Group     |
      | Description        |
      | Dept               |
      | Start              |
      | End                |
      | Distribution      |
      | FTE                |
      | Effort             |
      | Rate/Hr            |
      | Cost in Local Currency |
      | Distribution      |

    And info: ---5. Confirm values pulled in from Template---
    And 'Labor' table contain row with following data:
      | Resource Group           | REG_PROJECTMANAGER  |
      | Description              | REG_PROJECTMANAGER  |
      | Dept                     | RG10 US - New York  |
      | Start                    | 1/1/26              |
      | End                      | 12/31/28            |
      | Distribution             | Flat (working days per month)    |
      | Effort                   | 1000 hours          |
      | Rate/Hr                  | $ 110.00/hr         |
      | Cost in Local Currency   | $ 110,000.00        |
#      | FTE                      | 0.164 FTE           |
    #      | Sequence                 | 1                   |
#      | Billing Rate             | $ 240.00/hr         |

    And 'Labor' table contain row with following data:
      | Resource Group           | REG_CONSULTANT1     |
      | Description              | REG_CONSULTANT1     |
      | Dept                     | RG10 US - New York  |
      | Start                    | 1/1/26              |
      | End                      | 12/31/28            |
      | Distribution             | Flat (working days per month)    |
      | FTE                      | 2.000 FTE           |
      | Rate/Hr                  | $ 120.00/hr         |
      | Cost in Local Currency   | $ 1,464,960.00      |
#      | Effort                   | 12208 hours          |
    #      | Sequence                 | 2                   |
#      | Billing Rate             | $ 0.00/hr           |

    And 'Labor' table contain row with following data:
      | Resource Group           | REG_CONSULTANT2     |
      | Description              | REG_CONSULTANT2     |
      | Dept                     | RG10 US - New York  |
      | Start                    | 1/1/26              |
      | End                      | 12/31/28            |
      | Distribution             | Flat (working days per month)    |
      | Effort                   | 2400 hours          |
      | Rate/Hr                  | $ 90.00/hr          |
      | Cost in Local Currency   | $ 216,000.00        |
#      | FTE                      | 0.393 FTE           |
    #      | Sequence                 | 3                   |
#      | Billing Rate             | $ 0.00/hr           |
    And info: ---6. Click on FTE chevron to display all columns ---
    And I hover on 'FTE' column header
    And I click on 'FTE' column chevron
    Then 'Column' header options is displayed
    When I click on 'Columns' option
    Then 'Columns' options is displayed
    And info: ---7. Select G&A Cost column---
    And I click on 'GA Cost' option
    And I click on 'FTE' column header
    And 'Labor' table contain row with following data_-Warning-_:
      | Resource Group           | REG_CONSULTANT1     |
      | Description              | REG_CONSULTANT1     |
      | G&A Cost                 | $ 158,215.68        |
    And info: ---8. Click on G&A Cost chevron to display all columns ---
    And I hover on 'GA Cost' column header
    And I click on 'GA Cost' column chevron
    Then 'Column' header options is displayed
    When I click on 'Columns' option
    Then 'Columns' options is displayed
    And info: ---9. Select Overhead Cost column ---
    And I click on 'Overhead Cost' option
    And I click on 'GA Cost' column header
    And 'Labor' table contain row with following data:
      | Resource Group           | REG_CONSULTANT1     |
      | Description              | REG_CONSULTANT1     |
      | Overhead Cost            | $ 395,539.20        |
    And info: ---10. Click on Overhead Cost chevron to display all columns ---
    And I hover on 'Overhead Cost' column header
    And I click on 'Overhead Cost' column chevron
    Then 'Column' header options is displayed
    When I click on 'Columns' option
    Then 'Columns' options is displayed
    And info: ---11. Select Fringe Cost column ---
    And I click on 'Fringe Cost' option
    And I click on 'Overhead Cost' column header
    And 'Labor' table contain row with following data:
      | Resource Group           | REG_CONSULTANT1     |
      | Description              | REG_CONSULTANT1     |
      | Fringe Cost              | $ 512,736.00        |

  @TC-007 @RUN
  Scenario: TC-Prof-Services-007: Update Labor after Template Copy
    And info: ---1. Click the Add Resources [ + ] button---
    And I click '+' in table 'Labor' table in row number 3
    And I wait for 5 seconds
    And I set value 'Escalation United States' to the cell of the column 'Escalation Factor' of the 'Labor' table for the row with the following data:
      | Sequence                 | 4                   |
    And 'Labor' table contain row with following data:
      | Resource Group           |                     |
      | Description              |                     |
      | Dept                     | RG10 US - New York  |
      | Start                    | 1/1/26              |
      | End                      | 12/31/28            |
      | FTE                      | 0.000 FTE           |
      | Effort           |                     |
      | Rate/Hr         | $ 0.00/hr           |
      | Cost in Local Currency   | $ 0.00              |
      | Sequence                 | 4                   |
      | Escalation Factor        | Escalation United States |
#      | Billing Rate             | $ 0.00/hr           |

    And info: ---2. Enter Resource Group and type REG_QAMANAGER---
    And I set value 'REG_QAMANAGER' to the cell of the column 'Resource Group' of the 'Labor' table for the row with the following data:
      | Sequence                 | 4                   |
    And info: ---3. Press Enter---
    And 'Labor' table contain row with following data:
      | Resource Group           | REG_QAMANAGER       |
      | Description              | REG_QAMANAGER       |
      | Dept                     | RG10 US - New York  |
      | Start                    | 1/1/26              |
      | End                      | 12/31/28            |
      | FTE                      | 0.000 FTE           |
      | Effort                   |                     |
      | Distribution             | Manual (Monthly)    |
      | Rate/Hr          | $ 85.00/hr          |
      | Cost in Local Currency   | $ 0.00              |
      | Sequence                 | 4                   |
#      | Billing Rate             | $ 0.00/hr           |

    And info: ---4. Update Distribution by selecting dropdown---
    And I set value 'Flat (working days per month)' to the cell of the column 'Distribution' of the 'Labor' table for the row with the following data:
      | Resource Group | REG_QAMANAGER |
    And info: ---5. Update FTE with 1.000 FTE column for new line item REG_QAMANAGER and press Enter---
    And I set value '1.00' to the cell of the column 'FTE' of the 'Labor' table for the row with the following data:
      | Resource Group | REG_QAMANAGER |
    And info: ---6. Click Save button---
    Then click on 'Save' button
    And 'Data saved successfully' warning message is displayed in 30 seconds
    And I wait for 7 seconds
    And 'Labor' table contain row with following data:
      | Resource Group           | REG_QAMANAGER       |
      | Start                    | 1/1/26              |
      | End                      | 12/31/28            |
      | FTE                      | 1.000 FTE           |
      | Rate/Hr                  | $ 85.00/hr          |
#      | Cost in Local Currency   | $ 85,000.00         |
#      | Sequence                 | 4                   |
#      | Billing Rate             | $ 0.00/hr           |
    And info: ---7. Update REG_PROJECTMANAGER line item by adjusting the Effort value to 1500 hours and press Enter---
    And I set value '1500' to the cell of the column 'Effort' of the 'Labor' table for the row with the following data:
      | Resource Group | REG_PROJECTMANAGER |
#    And I click on 'Total Effort' checkBox
#    When I enter data into the next fields:
#      | 'Distribute Total Effort' field | Flat (all months equal) |
#    Then I click on 'Confirm' button
    And info: ---8. Click Save button ---
    Then click on 'Save' button
#    And page with name 'Estimate Labor Resources' is opened
    And 'Data saved successfully' warning message is displayed in 30 seconds
    And 'Labor' table contain row with following data:
      | Resource Group | REG_PROJECTMANAGER |
      | Effort | 1500 hours         |
    And info: ---9. Update the Formula-based Costs by selecting Hamburger menu and click Update Formula-based Costs option ---
    And 'Hamburger' menu is displayed
    Then I click on 'Hamburger' menu
    Then I choose in 'Hamburger' menu the next menu chain:
      | Update Formula-based Costs  |
    And warning messages displayed in 60 seconds are:
    """
    Formula costs being re-calculated and rolled-up to WBS and contract lines
    ---
    Costs/revenues & formula-based costs updated & rolled-up successfully
    """
    And info: ---10.
    And page with name 'Estimate Labor Resources' is opened
    And Grand total cell value of the 'FTE' column of the 'Labor Dual' table equals '3.639 FTE'
    And Grand total cell value of the 'Effort' column of the 'Labor Dual' table equals '22212 hr'
    And Grand total cell value of the 'Cost in Local Currency' column of the 'Labor Dual' table equals '$ 2,364,800.00'


  @TC-008 @RUN
  Scenario: TC-Prof-Services-008: Add Labor Amortization
    And page with name 'Estimate Labor Resources' is opened
    And info: ---1. Hover on the REG_PROJECTMANAGER resource Description line item || 2. Click the gear icon [ ⚙️ ]---
    And I hover 'Description' cell in table 'Labor' table and click ⚙️ for row with data:
      | Description | REG_PROJECTMANAGER |
    And 'Description Gear' menuItems contains items:
      | Edit Description |
      | Edit Details     |
      | View Resource Group Master  |
    And 'Description Gear' menuItems contains items_-Warning-_:
      | Mass Update        |
    And info: ---3. Click Edit Details---
    And I click on 'Edit Details' menuItem
    And 'Estimate for Labor' popup is displayed
    Then I verify tabs available in the popup are:
      | LABOR ESTIMATE      |
      | Tags & Factors      |
      | ACTUALS & FORECAST  |
      | Labor Amortization  |
      | Remarks             |
      | History             |
#      | Project Change Requests |

    And I click on 'Labor Amortization' tab
    And I verify 'Amortization Method' of Labor page is enabled, not required, drop down and is empty
    And 'Update Cost' link is displayed
    And I verify 'Amortization Period (months)' of Labor page is enabled, not required, numeric selection and has text '0'
    And I verify 'Start of Amortization' of Labor page is enabled, not required, date picker and is empty
    And I verify 'Set start of amortization to Go-live or Incurred' of Labor page is enabled, not required, check box and is unchecked
    And text of 'Total Value' dropdown equals '165,000.00'
    And I verify 'End of Amortization' of Labor page is enabled, not required, date picker and is empty
    And I verify 'Set end of amortization to contract end' of Labor page is enabled, not required, check box and is unchecked
    Then I should see the following columns in the 'Amortization Schedule' table:
      | Month        |
      | Incurred Cost     |
      | Amortization  |
      | Balance (Residual Value) |
    And the cell value of the 'Incurred Cost' column of the 'Amortization Schedule' table equals '$ 0.00' for a row with the index '1'
    And the cell value of the 'Amortization' column of the 'Amortization Schedule' table equals '$ 0.00' for a row with the index '1'
    And 'Confirm' button is displayed
    And 'Close' button is displayed
    And info: ---4. Update the Amortization Method via the dropdown ---
    When I enter data into the next fields:
      | 'Amortization Method' field        | Straight Line           |
    And I click on 'Estimate for Labor' popup
    And text of 'Amortization Method' field equals 'Straight Line'
    And info: ---5. Enable Set start of amortization to Go-live or incurred checkbox ---
    And I click on 'Start of Amortization' checkBox
    And I click on 'Estimate for Labor' popup
    And text of 'Start of Amortization' datePicker equals '1/1/26'
    And I click on 'End of Amortization' checkBox
    And I click on 'Estimate for Labor' popup
    And info: ---6. Click Close ---
    And I click on 'Close' button
    And page with name 'Estimate Labor Resources' is opened
    And info: ---7. Navigate back to Estimate for Labor - REG_PROJECTMANAGER popup by clicking gear icon [ ⚙️ ] and Edit Details ---
    And I hover 'Description' cell in table 'Labor' table and click ⚙️ for row with data:
      | Description | REG_PROJECTMANAGER |
    And I click on 'Edit Details' menuItem
    Then I verify tabs available in the popup are:
      | LABOR ESTIMATE  |
      | Tags           |
      | ACTUALS & FORECAST      |
      | Labor Amortization      |
      | Remarks                 |
      | History             |
#      | Project Change Requests |
    And I verify 'Amortization Method' of Labor page is enabled, not required, drop down and is empty
    And 'Update Cost' link is displayed
    And I verify 'Amortization Period (months)' of Labor page is enabled, not required, numeric selection and has text '0'
    And I verify 'Start of Amortization' of Labor page is enabled, not required, date picker and is empty
    And I verify 'Set start of amortization to Go-live or Incurred' of Labor page is enabled, not required, check box and is unchecked
    And text of 'Total Value' dropdown equals '165,000.00'
    And I verify 'End of Amortization' of Labor page is enabled, not required, date picker and is empty
    And I verify 'Set end of amortization to contract end' of Labor page is enabled, not required, check box and is unchecked
    Then I should see the following columns in the 'Amortization Schedule' table:
      | Month        |
      | Incurred Cost     |
      | Amortization  |
      | Balance (Residual Value) |
    And the cell value of the 'Incurred Cost' column of the 'Amortization Schedule' table equals '$ 0.00' for a row with the index '1'
    And the cell value of the 'Amortization' column of the 'Amortization Schedule' table equals '$ 0.00' for a row with the index '1'
    And 'Confirm' button is displayed
    And 'Close' button is displayed
    And info: ---8. Update the Amortization Method via the dropdown ---
    When I enter data into the next fields:
      | 'Amortization Method' field        | Straight Line           |
    And text of 'Amortization Method' field equals 'Straight Line'
    And info: ---9. Enable Set start of amortization to Go-live or incurred checkbox ---
    And I click on 'Start of Amortization' checkBox
    And text of 'Start of Amortization' datePicker equals '1/1/26'
    And info: ---10. Enable Set end of amortization to contract end checkbox---
    And I click on 'End of Amortization' checkBox
    And text of 'End of Amortization' datePicker equals '12/31/28'
    And info: ---11. Check the totals for Incurred Cost, and Amortization ---
    And I verify 'Amortization Period (months)' of Labor page is enabled, not required, numeric selection and has text '36'
    And Grand total cell value of the 'Incurred Cost' column of the 'Amortization Schedule' table equals '$ 165,000.00'
    And Grand total cell value of the 'Amortization' column of the 'Amortization Schedule' table equals '$ 165,000.00'
    And info: ---12. Check the Balance (Residual Value) field---
    And 'Amortization Schedule' table contain row with following data:
      | Month                    | Dec-2028            |
      | Balance (Residual Value) | 0.00                |
    And info: ---13. Click Confirm---
    Then I click on 'Confirm' button
    Then 'Estimate for Labor' popUp is not displayed
    And page with name 'Estimate Labor Resources' is opened
    And info: ---14. Navigate back to Navigate back to Estimate for Labor - REG_PROJECTMANAGER popup by clicking gear icon [ ⚙️ ] and Edit Details---
    And I hover 'Description' cell in table 'Labor' table and click ⚙️ for row with data:
      | Description | REG_PROJECTMANAGER |
    And I click on 'Edit Details' menuItem
    Then I verify tabs available in the popup are:
      | LABOR ESTIMATE  |
      | Tags           |
      | ACTUALS & FORECAST  |
      | Labor Amortization  |
      | Remarks          |
      | History             |
#      | Project Change Requests |
    And I verify 'Amortization Method' of Labor page is enabled, not required, drop down and has text 'Straight Line'
    And 'Update Cost' link is displayed
    And I verify 'Amortization Period (months)' of Labor page is enabled, not required, numeric selection and has text '36'
    And text of 'Start of Amortization' datePicker equals '1/1/26'
    And I verify 'Start amortization at incurred start' of Labor page is enabled, not required, check box and is checked
    And text of 'Total Value' dropdown equals '165,000.00'
    And text of 'End of Amortization' datePicker equals '12/31/28'
    And I verify 'End amortization at contract end' of Labor page is enabled, not required, check box and is checked
    Then I should see the following columns in the 'Amortization Schedule' table:
      | Month        |
      | Incurred Cost     |
      | Amortization  |
      | Balance (Residual Value) |
    And Grand total cell value of the 'Incurred Cost' column of the 'Amortization Schedule' table equals '$ 165,000.00'
    And Grand total cell value of the 'Amortization' column of the 'Amortization Schedule' table equals '$ 165,000.00'
    And 'Amortization Schedule' table contain row with following data:
      | Month                    | Dec-2028            |
      | Balance (Residual Value) | 0.00                |
    And 'Confirm' button is displayed
    And 'Close' button is displayed
    And info: ---15. Click Close---
    And I click on 'Close' button
    And page with name 'Estimate Labor Resources' is opened
    And info: ---16. Click Save---
    Then click on 'Save' button
    And I wait for 7 seconds

  @TC-009 @END
  Scenario: TC-Prof-Services-009: Review Costs in Proposal
    And info: ---1. Click hamburger menu ---
    Then I click on 'Hamburger' menu
    And info: ---2. Hover on Open ▸ line ---
    Then I hover on 'Open' menuItem
    Then 'Open' menuItems contains items:
      | Open Cost Price Analysis |
      | Open Proposal            |
      | Open Sourcing Plan       |
      | Open Deleted Items       |
    And info: ---3. Select Open Proposal ---
    Then I click on 'Open Proposal' menuItem
    And I switch to tab 3
    Then I refresh page
    Then I wait for 30 seconds
#    And I click on 'Setup' tab
    And page with name 'Setup page' is opened
    When I enter data into the next fields:
      | 'Client Customer sell-to' field      | Regression Test - Customer USD |
    And page with name 'Setup page' is opened
    Then I click on 'Save' button
    And I wait for 7 seconds
    And page with name 'Setup page' is opened
    And I click on 'WBS' tab
    And page with name 'WBS page' is opened
    And info: ---4. Run Update Cost and Prices to refresh model via hyperlink ---
    Then I click on 'Update Cost & Prices' link
    And warning messages displayed in 30 seconds are:
    """
    Costs/revenues & formula-based costs and prices updated and rolled-up
    """
    And page with name 'WBS page' is opened
    And info: ---5. Validate Cost & Prices update ---
    Then text of 'Total Cost' widget equals 'USD 3.28M'
    And info: ---6. Validate Cost & Prices update---
    When I select 'TEST:-DONOT_CHANGE' in the 'WBS View' dropdown
    And I click on 'Save' button
    Then text of 'WBS View' dropdown equals 'View: TEST:-DONOT_CHANGE'
#    Then Grand total cell value of the 'Total Cost with Risk (without Opt) Cost in Local Currency' column of the 'WBS' table equals '$ 2,249,227.62'
#    Then Grand total cell value of the 'Labor Cost' column of the 'WBS' table equals '$ 1,918,460.00'
    And 'WBS' table contain row with following data:
      | WBS                                                    | 1 Prof Services Estimate     |
      | Total Cost with Risk (without Opt) Cost in Local Currency | $ 3,278,475.20            |
      | Labor Cost                                             | $ 2,364,800.00               |
