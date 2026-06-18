@SETUP-PROFF-SERVICES
Feature: 1. Setup Professional Services Proposal

  @TC-001 @START
  Scenario: TC-Prof-Services-001: Verify “Proposal Setup” Layout
    Given open site
    And I perform login
    Then page with name 'Main page' is opened
    And info: ---1. Navigate to Proposals---
    Given I click on 'PROPOSALS' tab
    Then page with name 'Proposals list page' is opened
    And info: ---2. Click New to start creating new Proposal---
    Then click on 'New' button
    And page with name 'Setup page' is opened
    And info: ---3. On the Setup tab, select Regression Test Only - Consulting from Proposal Type---
    When I enter data into the next fields:
      | 'Proposal Type' field | Regression Test Only - Consulting |
    And page with name 'Setup page' is opened
#    Then 'Description' field and 'Proposal Type' field are aligned
    Then I see the following TABS in the 'Proposal' Top Menu_-Warning-_:
      | Setup         |
      | Phases        |
      | Workstreams   |
      | Billing Items |
      | Schedule      |
      | My Team       |
      | Workflow      |
      | Analysis      |
      | Rates         |
    And info: ---4. Verify contents of Project Details section---
    And I verify 'Title or Brief Description' of setup page is enabled, required, free text and is empty
    And I verify 'Select the Best-fit Type' of setup page is enabled, required, drop down and has text 'Regression Test Only - Consulting'
    And I verify 'Contract Type' of setup page is enabled, not required, drop down and is empty
    And I verify 'SAP Project' of setup page is enabled, not required, drop down and is empty
    And 'Open Project in SAP' link is displayed
    And I verify 'SAP WBS' of setup page is enabled, not required, drop down and is empty
    And I verify 'WBS managed in SAP' of setup page is enabled, not required, radio buttons and is unchecked
    And I verify 'Estimated Project Start' of setup page is enabled, required, date picker and has text '$todayDate'
    And I verify 'Project End' of setup page is enabled, required, date picker and has text '$oneYearFromToday'
    And I verify 'Project Manager' of setup page is enabled, not required, drop down and has text 'Tech User'
    And I verify 'multiple phases' of setup page is enabled, not required, radio buttons and is checked
#    And I verify 'SAP Project Profile' of setup page is enabled, not required, drop down and is empty
    And I verify 'Proposal Hours' of setup page is enabled, not required, drop down and is empty
#    And I verify 'What is your Target Confidence Level' of setup page is enabled, not required, drop down and has text 'Final (0%)'
    And 'Upload RFx Files' button is displayed
    And page with name 'Setup page' is opened
    And info: ---5. Verify contents of Opportunity, CLient, & Your Organization section---
    And I verify 'Leading company' of setup page is enabled, required, drop down and is empty
    And I verify 'Leading department' of setup page is enabled, required, drop down and is empty
    And I verify 'Profit Center' of setup page is enabled, not required, drop down and is empty
    And I verify 'Cost Center' of setup page is enabled, not required, drop down and is empty
    And I verify 'Leading Company Currency' of setup page is enabled, required, free text and has text '$ USA - US Dollar(USD)'
    And 'Edit Rates' link is displayed
    And I verify 'Project Currency' of setup page is enabled, required, drop down and is empty
    And I verify 'Other Departments or Sites Involved' of setup page is enabled, not required, drop down and is empty
    And I verify 'Client/Customer (Sell-to)' of setup page is enabled, required, drop down and is empty
    And I verify 'CRM Opportunity' of setup page is enabled, not required, drop down and is empty
#    And I verify 'Labor Price Book' of setup page is enabled, not required, drop down and is empty
    And I verify 'Customer Contact' of setup page is enabled, not required, drop down and is empty
    And I verify 'Opportunity Status' of setup page is enabled, not required, free text and is empty
    And I verify 'Customer Purchase order' of setup page is enabled, not required, free text and is empty
    And I verify 'Proposal Authorization#' of setup page is enabled, not required, free text and is empty
#    And I verify 'Target - Price to Win' of setup page is enabled, not required, free text and has text '$'
    And page with name 'Setup page' is opened
    And info: ---6. Verify Re-use Prior Proposal or Template section ---
    And I verify 'Select a Template' of setup page is enabled, not required, radio buttons and is checked
    And I verify 'Select a prior proposal' of setup page is enabled, not required, radio buttons and is unchecked
#    And I verify 'Select a Template' of setup page is enabled, not required, drop down and is empty
    And page with name 'Setup page' is opened
    And info: ---7. Verify Project Goals or Remarks section---
    And 'Project Goals or Remarks' textbox is displayed
    When I click on 'Project Goals or Remarks' textbox
    Then 'Text edit' toolbox is displayed
    And page with name 'Setup page' is opened
    And info: ---8. Verify Tags to New Dimensions of Costs & Revenue Model ---
    And I verify 'Select Tag to Add' of setup page is enabled, not required, drop down and is empty
    And page with name 'Setup page' is opened

  @TC-002 @RUN
  Scenario: TC-Prof-Services-002: Fill in Required Fields & Validate Save
    Given generate values and store into the variables:
      | $uniqueProposalName | Regression Test Professional Services Proposal ${CUR_DATE,yyyy-MM-dd hh:mm} |
    And info: ---1. Click Save button without editing any fields ---
    And page with name 'Setup page' is opened
    And I hover on 'Save' button
    And I click on 'Save' button
    And 'Error' dialog is displayed
    And I hover on 'Error' dialog
    Then text of 'Error' dialog equals_-Warning-_:
    """
    Title or Brief Description * is required in Setup tab
    Leading company * is required in Setup tab
    Leading department * is required in Setup tab
    Project Currency * is required in Setup tab
    Client/Customer (Sell-to) * is required in Setup tab
    """
    And verify border-color css of 'Error' dialog is 'red'
    And verify border-color css of 'Description' field is 'red'
    And verify border-color css of 'Leading Company' field is 'red'_-Warning-_
    And verify border-color css of 'Leading Department' field is 'red'_-Warning-_
    And verify border-color css of 'Client Customer sell-to' field is 'red'
    And verify border-color css of 'Customer Currency' field is 'red'

    And page with name 'Setup page' is opened
    And info: ---2. Fill in Title or Brief Description field---
    When I enter data into the next fields:
      | 'Description' field   | $uniqueProposalName |
    And page with name 'Setup page' is opened
#    And info: ---3. Fill in Estimated Project Start with earlier date---
#    When I enter data into the next fields:
#      | 'Planned Start' field | 4/1/2025            |
#    And verify border-color css of 'Planned Start' field is 'red'
    And info: ---4. Fill in Estimated Project || 5. Fill in Project End || 6. Select Leading Company from dropdown---
    And page with name 'Setup page' is opened
    When I enter data into the next fields:
      | 'Planned Start' field        | 1/1/2026            |
      | 'Leading Company' field      | Regression Test     |
      | 'End' field                  | 12/31/2028          |
    And info: ---7. Verify that Leading Department dropdown contains only departments relevant to selected Leading Company---
    When I enter data into the next fields:
      | 'Leading Department' field            | US - New York   |
    And info: ---8. Verify that Profit Center dropdown contains only centers relevant to selected Leading Department ---
    When I enter data into the next fields:
      | 'Profit Center' field           | Services        |
    And page with name 'Setup page' is opened
    And info: ---9. Verify that Cost Center dropdown is populated after selecting value from Profit Center---
    And I enter data into the next fields:
      | 'Cost Center' field            | REG CC-2       |
    And page with name 'Setup page' is opened
    And text of 'Cost Center' field equals 'REG CC-2'
    And info: ---10. Select Leading Company Currency, if different from default of $USA - US Dollars (USD)---
    And text of 'Leading Company Currency' field equals '$ USA - US Dollar(USD)'
#    And info: ---11. Click Edit Rates---
#    Then I click on 'Edit Rates' link
#    And 'Maintain Proposal-specific Currency Exchange Rates' popup is displayed
#    Then I verify tabs available in the popup are:
#    | Currency Exchange Rates |
#    | Escalation or Inflation Rates |
#    And 'Update Rates' button is enabled
#    And 'Confirm' button is enabled
#    And 'Close' button is enabled
#    And info: ---12. Select Escalation or Inflation Rates tab---
#    And I click on 'Escalation or Inflation Rates' tab
#    And page with name 'Escalation or Inflation Rates page' is opened
#    Then I click on 'Customer Currency' field
    And info: ---11. Select Client / Customer (Sell-to)---
    And page with name 'Setup page' is opened
    When I enter data into the next fields:
      | 'Client Customer sell-to' field      | Regression Test - Customer USD |
    And page with name 'Setup page' is opened
    And text of 'Customer Currency' field equals '$ USA - US Dollar(USD)'
    And page with name 'Setup page' is opened
    And info: ---12. Click Save button ---
    And I click on 'Save' button
    And 'Data saved successfully' warning message is displayed in 30 seconds
    And page with name 'Setup page' is opened
    And info: ---13. Review URL in browser ---
    And I verify current page URL contains "#quote:[0-9a-fA-F\\-]{36}"

  @TC-003 @RUN
  Scenario: TC-Prof-Services-003: Validate Copy Template Functionality
    And info: ---1. Enable Select a Template radio button  ---
    And I hover on 'Select a template' radio
    And I verify 'Select a Template' of setup page is enabled, not required, radio buttons and is checked
    And info: ---2. Search for Template from Library and select REG TEST Professional Services Template  ---
    When I enter data into the next fields:
      | 'Search for Template from Library' field | REG TEST Professional Services Template |
    And text of 'Search for Template from Library' field equals 'REG TEST Professional Services Template'
    And info: ---3. Verify selections for which proposal elements to create or copy ---
    And 'proposal elements to create or copy' radioButtons is displayed
    And I verify 'Phases/options (No sets)' of setup page is not enabled, not required, check box and is unchecked
    And I verify 'Priced deliverables (No items)' of setup page is not enabled, not required, check box and is unchecked
    And I verify 'Risk & opportunities (No risks)' of setup page is not enabled, not required, check box and is unchecked
    And I verify 'Labor estimates (15608 hrs)' of setup page is enabled, not required, check box and is checked
    And I verify 'WBS or work-packages (1 elements)' of setup page is enabled, not required, check box and is checked
    And I verify 'Billing milestones (No payments)' of setup page is not enabled, not required, check box and is unchecked
    And I verify 'Sourcing & purchasing history (No docs)' of setup page is not enabled, not required, check box and is unchecked
    And I verify 'Proposal bill of material (No items)' of setup page is not enabled, not required, check box and is unchecked
    And I verify 'Tasks & sub-tasks (No tasks)' of setup page is not enabled, not required, check box and is unchecked
    And I verify 'Billing rates (No proposal-rates)' of setup page is not enabled, not required, check box and is unchecked
    And I verify 'Tags & category values (No tags)' of setup page is not enabled, not required, check box and is unchecked
    And I verify 'Material estimates (No parts)' of setup page is not enabled, not required, check box and is unchecked
    And I verify 'Requirements (No items)' of setup page is not enabled, not required, check box and is unchecked
    And I verify 'Cost/pricing parameters' of setup page is enabled, not required, check box and is checked
    And I verify 'Funding sources (No funds)' of setup page is not enabled, not required, check box and is unchecked
    And I verify 'Travel/other estimates (1 items)' of setup page is enabled, not required, check box and is checked
#    And I verify 'Transfer to US - New York' of setup page is enabled, not required, check box and is unchecked

    And I verify 'Reset dates to ' of setup page is enabled, not required, radio buttons and is unchecked
    And I verify 'Re-schedule proposal' of setup page is enabled, not required, radio buttons and is checked

    And page with name 'Setup page' is opened
    And info: ---4. Click Copy button ---
    And click on 'Copy' button
    And warning messages displayed in 15 seconds are:
    """
    BOE's copy is happening in background, please check back after some time ---
    Rescheduling during copy will reset all labor etc. dates to their assigned WBS or task
    """
    And page with name 'Setup page' is opened
    And info: ---5. From Leading Company Currency, click Edit Rates hyperlink---
    And I click on 'Edit Rates' link
    And 'Maintain Proposal-specific Currency Exchange Rates' popup is displayed
    And I wait for 7 seconds
    And info: ---6. Select Escalation or Inflation Rates tab ---
    Then I click on 'Escalation or Inflation Rates' tab
    And page with name 'Setup page' is opened
    And 'Maintain Proposal-specific Currency Exchange Rates' popup is displayed
    Then I should see the following columns in the 'Escalation and Inflammation Rates' table:
      | Escalation Type    |
      | 2026               |
      | 2027               |
      | 2028               |
    And info: ---7. Select the US Escalation United States (CALENDAR) Escalation Factor ---
    Then I click on 'Escalation first' cell
    And info: ---8. Click Update Rates to start updating Rates ---
    Then I click on 'Update Rates' button
    And info: ---9. Select Turn off Escalation (Reset all Rates to 1) ---
    Then I click on 'Reset all Rates to 1' option
    And I wait for 7 seconds
    And info: ---10. Click Confirm  ---
    Then I click on 'Confirm' button
    And I wait for 3 seconds
    And 'Save Confirmation' popUp is displayed
    And I wait for 4 seconds
    And info: ---11. Click Yes to Save the Escalation update  ---
    Then I click on 'Yes' button
#    And I wait for 7 seconds
#    And 'Force Update' popUp is displayed
    And info: ---12. Click Yes to force update of all costs  ---
    Then if 'Force Update' popUp displayed then click 'Force Update Yes' button
    Then if 'Maintain Proposal-specific Currency Exchange Rates' popup displayed then click 'Close' button
    Then if 'Save Confirmation' popUp displayed then click 'Yes and Update Costs' button
    Then if 'Force Update' popUp displayed then click 'Force Update Yes' button
    And I wait for 7 seconds
    And page with name 'Setup page' is opened
#    And I refresh page
#    And page with name 'Main page' is opened
#    And page with name 'Setup page' is opened


  @TC-004 @END
  Scenario: TC-Prof-Services-004: Verify Template Copy Results & Reschedule Functionality
    And info: ---1. Navigate to Workstreams---
    Given I click on 'WBS' tab
    And page with name 'WBS page' is opened
    And 'Running' status is not displayed
    Then I click on 'Update Cost' button
    And I wait for 5 seconds
    And 'Running' status is not displayed
    Given I click on 'WBS' tab
    And page with name 'WBS page' is opened
    And if 'Needs Refresh' status is visible then refresh page
    And page with name 'WBS page' is opened
    And text of 'Total Price' widget equals 'USD 0'
    And text of 'Total Cost' widget equals 'USD 2.7M'_-Warning-_
    And text of 'Margin as Percentage' widget equals '0 %'
    And 'Status' widget is displayed
    And text of 'Cost & Price' status equals 'Updated'
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
    And info: ---4. Check values pulled in from selected Template---
    And 'WBS' table contain row with following data_-Warning-_:
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
#      | Delivery Organization             | Professional Services          |
#      | Profit Center           | US - West Coast                |
#      | Cost Center           | Engineering Consulting         |

    And 'WBS' table contain row with following data_-Warning-_:
      | Estimate                                               | Create                       |
    And '1 Prof Services Estimate' WP is enabled
    And info: ---5. Check Estimate further by clicking on Create hyperlink---
    And I click 'Open' in table 'WBS' table for row with data:
      | WBS  | 1 Prof Services Estimate   |
#    And I click 'Create' in table 'WBS' table for row with data:
#      | WBS  | 1 Prof Services Estimate   |
#    And 'Estimate' popUp is displayed
#    And I wait for 7 seconds
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
    And page with name 'WBS page' is opened
    Then I switch to tab 2
    And I click on 'Labor' tab
    And page with name 'Estimate Labor Resources' is opened
    And 'UNLOCK FOR OTHERS' link is displayed
    And info: ---7. Check available tabs within Estimates---
    Then I see the following TABS in the 'Estimates' Top Menu_-Warning-_:
      | Labor                 |
      | Other                 |
      | Procurement & Production |
      | Risks & Contingency |
      | Work Packages |
      | Workflow              |
#      | Labor Resources       |
#  | Service               |
#  | IP                    |
#  | Expenses              |
#  | Risk & Contingency    |
#  | Work Packages         |
    And info: ---8. Review Labor Resources tab---
    And I verify 'Estimating Methodology' of labor page is enabled, not required, drop down and is empty
    And 'Learn more' link is displayed
    And 'COPY LABOR' button is disabled
    And info: ---9. Check selected View---
    When I select 'Test View for Regression' in the 'View' dropdown
    And I click on 'Save' button
    Then text of 'View' dropdown equals 'View: Test View for Regression - DONOT CHANGE (preferred)'
    And info: ---10. Review Labor grid---
    Then I should see the following columns in the 'Labor' table:
      | Description        |
      | Resource Group     |
      | Dept               |
      | Start              |
      | End                |
      | Distribution       |
      | FTE                |
      | Effort             |
      | Rate/Hr            |
      | Cost in Local Currency |
    And info: ---11. Check Labor grid for Monthly Distribution values---
    Then I choose in 'Cog Settings' menu the next menu chain:
      | Change Planned Effort Input Mode |
      | Hours by Week                    |
    Then I wait for 7 seconds
#    And page with name 'Estimate Labor Resources' is opened
#    Then 'Change Planning Mode' window is displayed
    And I if visible click on 'Yes' button
#    And page with name 'Estimate Labor Resources' is opened
    Then I verify column Hours by Week distribution between dates '1/1/2026' and '12/31/2028'
    Then I choose in 'Cog Settings' menu the next menu chain:
      | Change Planned Effort Input Mode |
      | Hours by Month                   |
    Then I wait for 7 seconds
#    And page with name 'Estimate Labor Resources' is opened
#    Then 'Change Planning Mode' window is displayed
    And I if visible click on 'Yes' button
    Then I wait for 20 seconds
    Then I verify column Hours by Month distribution between dates '1/1/2026' and '12/31/2028'






















