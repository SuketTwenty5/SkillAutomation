@laborInflationProposal-2-4
Feature: 6. Check that Labor Inflation is calculated correctly over the length of the proposal

  @TCM-015 @START
  Scenario: TC-Manufacturing-Proposal-015: Labor Inflation
    Given open site
    And I perform Mfg 2.4 login
    Then page with name 'Main page' is opened
    Given generate values and store into the variables:
      | $uniqueProposalName | TEST Labor Inflation ${CUR_DATE,yyyy-MM-dd hh:mm} |
    And info: ---1. Start creating a new Proposal with Type Regression Test Only - Manufacturing. The Setup tab is opened.---
    Given I click on 'PROPOSALS' tab
    Then page with name 'Proposals list page' is opened
    Then click on 'New' button
    And page with name 'Setup page' is opened
    When I enter data into the next fields:
      | 'Proposal Type' field | Regression Test Only - Manufacturing |
    And info: ---2. Fill in the required fields. For Estimated Project Start and Project End use Test Data Values. Estimated Project Start = 1/1/2030, Project End = 12/31/2032.---
    And page with name 'Setup page' is opened
    When I enter data into the next fields:
      | 'Description' field   | $uniqueProposalName |
    And page with name 'Setup page' is opened
    When I enter data into the next fields:
      | 'Planned Start' field | 1/1/2030            |
    And page with name 'Setup page' is opened
    When I enter data into the next fields:
      | 'End' field                  | 12/31/2032         |
    When I enter data into the next fields:
      | 'Your Company' field            | Regression Test |
    And page with name 'Setup page' is opened
    When I enter data into the next fields:
      | 'Leading Department' field            | US - New York   |
    And page with name 'Setup page' is opened
    When I enter data into the next fields:
      | 'Client Customer sell-to' field | Regression Test - Customer USD |
    And page with name 'Setup page' is opened
    And info: ---3. In the section How do you want to Start your Project Plan make sure the radio-button Select a template is selected. In the dropdown field select Test Data Value: (AS) Regression - Labor Manual Effort & Inflation - Template.---
    And I check 'Select a template' radio
    When I enter data into the next fields:
      | 'Search Box' field | (AS) Regression - Labor Manual Effort & Inflation - Template |
    And page with name 'Setup page' is opened
    And I click on 'Re schedule' radio
    And page with name 'Setup page' is opened
    And info: ---4. Click Copy button. The system saves the data and reloads the page.---
    When I click on 'Copy' button
    Then page with name 'Setup page' is opened
    And info: ---5. Click the Edit Rates link by the Leading Company Currency field. The Maintain Proposal-specific Currency Exchange Rates popup is displayed.---
    When I click on 'Edit Rates' link
    And 'Maintain Proposal-specific Currency Exchange Rates' popup is displayed
    And I wait for 7 seconds
    And info: ---6. Click Escalation or Inflation Rates tab on the popup. Tab content is displayed.---
    Then I click on 'Escalation or Inflation Rates' tab
    And page with name 'Setup page' is opened
    And 'Maintain Proposal-specific Currency Exchange Rates' popup is displayed
    Then I should see the following columns in the 'Escalation and Inflammation Rates' table:
      | Escalation Type    |
      | 2030               |
      | 2031               |
      | 2032               |
    And info: ---7. Enter Test Data Values for years 2030, 2031, 2032. Values: 2030 = 1.05, 2031 = 1.10, 2032 = 1.15.---
    And I set value '1.05' to the cell of the column '2030' of the 'Escalation and Inflammation Rates' table for the row with the following data:
      | Escalation Type | Escalation United States (CALENDAR) |
    And I wait for 3 seconds
    And I set value '1.10' to the cell of the column '2031' of the 'Escalation and Inflammation Rates' table for the row with the following data:
      | Escalation Type | Escalation United States (CALENDAR) |
    And I wait for 3 seconds
    And I set value '1.15' to the cell of the column '2032' of the 'Escalation and Inflammation Rates' table for the row with the following data:
      | Escalation Type | Escalation United States (CALENDAR) |
    And I wait for 3 seconds
    And info: ---8. Click Confirm button. Popup Save Confirmation is displayed.---
    Then I click on 'Confirm' button
    And I wait for 3 seconds
    And 'Save Confirmation' popUp is displayed
    And I wait for 4 seconds
    And info: ---9. Click Yes. After a few seconds Force Update of all Costs - Important popup is displayed.---
    Then I click on 'Yes' button
    Then if 'Force Update' popUp displayed then click 'Force Update Yes' button
    Then if 'Maintain Proposal-specific Currency Exchange Rates' popup displayed then click 'Close' button
    Then if 'Save Confirmation' popUp displayed then click 'Yes and Update Costs' button
    And info: ---10. Click Yes. After a few seconds page is refreshed.---
    Then if 'Force Update' popUp displayed then click 'Force Update Yes' button
    And page with name 'Setup page' is opened
    And info: ---11. Navigate to the Pricing tab and in the dropdown field How are Labor/Item Rates Calculated select Test Data Value: Regression Test - Bill Rates.---
    Then I click on 'Pricing' tab
    And page with name 'Pricing page' is opened
    When I enter data into the next fields:
      | 'How are Labor/Item Rates Calculated' field | Regression Test - Bill Rates |
    And page with name 'Pricing page' is opened
    Then I click on 'Pricing' tab
    And info: ---12. Click Save. Data is saved.---
    When I click on 'Save' button
    And I wait for 5 seconds
    Then page with name 'Pricing page' is opened
    And info: ---13. Navigate to Cost Structure tab and click Open in the Work Package field of the Grid. The Estimate is opened in a new tab. Labor tab is selected.---
    And I click on 'Cost Structure' tab
    Then page with name 'Cost Structure page' is opened
    When I select 'TEST:-DONOT_CHANGE' in the 'View' dropdown
    And I click on 'Save' button
    Then text of 'View' dropdown equals 'View: TEST:-DONOT_CHANGE (preferred)'
    And I click 'Open' in table 'WBS' table for row with data:
      | WBS Code              | 1                   |
    Then I switch to tab 2
    And I wait for 7 seconds
    And I click on 'Labor' tab
    When I select 'Regression Test - Manufacturing' in the 'View' dropdown
    And I wait for 7 seconds
    And I click on 'Save' button
    Then text of 'View' dropdown equals 'View: Regression Test - Manufacturing (shared & preferred)'
    And page with name 'Estimate Labor' is opened
    And info: ---14. Click the Gear icon above the right side of the Grid and go to Change Planned Effort Input Mode > Hours By Month.---
    Then I choose in 'Cog Settings' menu the next menu chain:
      | Change Planned Effort Input Mode |
      | Hours by Month                   |
    Then I wait for 7 seconds
    And info: ---15. Popup Change Planning Mode in Other Estimates appears.---
    And page with name 'Estimate Labor' is opened
    Then 'Change Planning Mode' window is displayed
    And info: ---16. Click Yes on the Popup. The Popup disappears. The Grid is refreshed.---
    And I click on 'Yes' button
    And page with name 'Estimate Labor' is opened
    And info: ---17. For REG_CONSULTANT3 row enter Test Data Value in the Planned Effort field: 3600.---
    And I set value '3600' to the cell of the column 'Effort' of the 'Labor Dual' table for the row with the following data:
      | Resource Group - Free Text      | REG_CONSULTANT3 |
    And I wait for 3 seconds
    And info: ---18. Click Save. Data is saved.---
    And I click on 'Save' button
    And 'Data saved successfully' warning message is displayed in 30 seconds
    Then page with name 'Estimate Labor' is opened
    When I retrieve the number of open browser windows
    And info: ---19. Open Cost Price Analysis via the top-right menu. A new browser tab is opened.---
    Then I click on 'Hamburger' menu
    Then I choose in 'Hamburger' menu the next menu chain:
      | Open                     |
      | Open Cost Price Analysis |
    Then page with name 'Estimate Labor' is opened
    Then I switch to new tab
    And page with name 'Cost Price Analysis Wbs page' is opened
    And info: ---20. Click the Workbench tab. The Workbench tab is opened.---
    Then I click on 'Workbench' tab
    Then page with name 'Workbench page' is opened
    And info: ---21. Select view Regression Test - Inflation. The view is selected, and the grid is displayed according to the view.---
    When I select 'Regression Test - Inflation' in the 'View' dropdown
    Then page with name 'Workbench page' is opened
    Then text of 'View' dropdown equals 'View: Regression Test - Inflation'
    And info: ---22. Verify that for REG_CONSULTANT3 row the following yearly amounts are displayed: 2030 - $3,225.78, 2031 - $12,725.62, 2032 - $29,620.81.---
    And 'Workbench' table contain row with following data:
      | Description                        | REG_CONSULTANT3  |
      | 2030                               | $ 3,225.78       |
      | 2031                               | $ 12,725.62      |
      | 2032                               | $ 29,620.81      |

  @TCM-016 @END
  Scenario: TC-Manufacturing-Proposal-016: Material Inflation
    When page with name 'Workbench page' is opened
    Then I switch back to previous tab
    And I wait for 7 seconds
    And info: ---1. Open the Procurement & Production tab of the Estimate from previous test case. The Procurement & Production tab is opened.---
    And I click on 'Procurement & Production' tab
    And page with name 'Estimate Procurement & Production' is opened
    When I select 'Regression Test - Manufacturing' in the 'View' dropdown
    And I click on 'Save' button
    Then text of 'View' dropdown equals 'View: Regression Test - Manufacturing (preferred)'
    And page with name 'Estimate Procurement & Production' is opened
    And info: ---2. For the first row REG_MATERIAL1 enter Test Data Values for Start and End Dates. Start Date = 12/31/2030, End Date = 12/31/2030.---
    And I set value '12/31/2030' to the cell of the column 'Start' of the 'Procurement & Production' table for the row with the index '1'
    And I wait for 3 seconds
    And I set value '12/31/2030' to the cell of the column 'End' of the 'Procurement & Production' table for the row with the index '1'
    And I wait for 3 seconds
    And info: ---3. For the second row REG_MATERIAL1 enter Test Data Values for Start and End Dates. Start Date = 12/31/2031, End Date = 12/31/2031.---
    And I set value '12/31/2031' to the cell of the column 'Start' of the 'Procurement & Production' table for the row with the index '2'
    And I wait for 3 seconds
    And I set value '12/31/2031' to the cell of the column 'End' of the 'Procurement & Production' table for the row with the index '2'
    And I wait for 3 seconds
    And info: ---4. Click Save. Data is saved.---
    And I click on 'Save' button
#    And 'Data saved successfully' popUp is displayed
#    And 'Data saved successfully' warning message is displayed in 30 seconds
    And info: ---5. Open Cost Price Analysis via the top-right menu. The Cost Price Analysis is opened in a new browser tab with Workbench tab selected (if not, click Workbench tab to navigate).---
    Then I switch back to previous tab
    And I refresh page
    Then I click on 'Workbench' tab
    Then page with name 'Workbench page' is opened
    And info: ---6. Select view Regression Test - Inflation. The view is selected, and the grid is displayed according to the view.---
    When I select 'Regression Test - Inflation' in the 'View' dropdown
    Then page with name 'Workbench page' is opened
    Then text of 'View' dropdown equals 'View: Regression Test - Inflation'
    And info: ---7. In the Description filter enter Test Data Value. The Workbench Grid displays results of REG_MATERIAL1.---
    And I enter data into the next fields:
      | 'Description' filter | REG_MATERIAL1 |
    Then page with name 'Workbench page' is opened
    Then I wait for 5 seconds
    And info: ---8. Verify that for REG_MATERIAL1 row the following yearly amounts are displayed: 2030 - $6,000.00, 2031 - $18,600.00, 2032 - $39,390.00.---
    And 'Workbench' table contain row with following data:
      | Description                        | REG_MATERIAL1    |
      | 2030                               | $ 6,000.00       |
      | 2031                               | $ 18,600.00      |
      | 2032                               | $ 39,390.00      |
