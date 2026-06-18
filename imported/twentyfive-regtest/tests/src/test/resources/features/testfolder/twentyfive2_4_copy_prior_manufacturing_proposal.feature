@copyManufacturingPriorProposal-2-4
Feature: 7. Copy Prior Proposal

  @copyManufacturingProposalSearchOpenProposal-1 @START
  Scenario: TC-Copy-Manufacturing-Proposal-001: Search & Open Proposal
    Given open site
    And I perform Mfg 2.4 login
    Then page with name 'Main page' is opened
    And info: ---1. Navigate to Proposals and verify environment is accessible---
    Given I click on 'PROPOSALS' tab
    Then page with name 'Proposals list page' is opened
    And info: ---2. Click + to add a filter and verify that dropdown appears---
    When I if visible click on 'First Add Filter' button
    Then page with name 'Proposals list page' is opened
    And I click on 'First Filter' dropdown
    And 'First Filter' dropbox is displayed
    And info: ---3. Type in Proposal and select, verify Proposal is available for selection and second filter field appears---
    And I enter text 'Proposal' in the 'First Filter' combobox
    And I click on 'Proposal' option
    Then page with name 'Proposals list page' is opened
    And info: ---4. Type in "Regression Test Proposal for Copy Scenario - Manufacturing (Do Not Edit)" and verify filter is applied where Proposal is displayed---
    And I enter text 'Regression Test Proposal for Copy Scenario - Manufacturing (Do Not Edit)' in the 'First Filter' input
    Then page with name 'Proposals list page' is opened
    And info: ---5. Click on the Proposal name to open Proposal and verify Setup tab is opened without errors---
    And I click on 'Regression Test Proposal for Copy Scenario - Manufacturing' proposal
    Then page with name 'Setup page' is opened
    And info: ---6. Click on Cost Structure tab and verify Cost Structure tab is opened---
    And I click on 'Cost Structure' tab
    Then page with name 'Cost Structure page' is opened
    And info: ---7. Click on Proposals to navigate back to Proposals screen and verify user is redirected---
    And I click on 'PROPOSALS' tab
    Then page with name 'Proposals list page' is opened

  @copyManufacturingProposalSetupAndCopyPriorProposal-2 @RUN
  Scenario: TC-Copy-Manufacturing-Proposal-002: Setup & Copy Prior Proposal
    Given generate values and store into the variables:
      | $uniqueProposalName | TC-Copy-Manufacturing-Proposal-Test ${CUR_DATE,yyyy-MM-dd hh:mm} |
    And info: ---1. Click New to start creating new Proposal and verify user is directed to Setup tab---
    When I click on 'New' button
    Then page with name 'Setup page' is opened
    And info: ---2. Fill Proposal Title with valid value (1–255 chars) → TC-Copy-Manufacturing-Proposal-Test---
    When I enter data into the next fields:
      | 'Description' field   | $uniqueProposalName |
    Then page with name 'Setup page' is opened
    When I enter data into the next fields:
      | 'Proposal Type' field | Regression Test Only - Manufacturing |
    And page with name 'Setup page' is opened
    And info: ---3. Fill Estimated Project Start with valid date → 1/1/27---
    When I enter data into the next fields:
      | 'Planned Start' field | 1/1/27              |
    Then page with name 'Setup page' is opened
    And info: ---4. Fill Planned End with later date → 12/31/30---
    When I enter data into the next fields:
      | 'End' field           | 12/31/30           |
    Then page with name 'Setup page' is opened
    And info: ---5. Select "Regression Test" from Your Company dropdown---
    When I enter data into the next fields:
      | 'Company' field              | Regression Test                |
    Then page with name 'Setup page' is opened
    And info: ---6. Select "US - New York" from Leading Site/Department dropdown---
    When I enter data into the next fields:
      | 'Leading Department' field | US - New York                  |
    Then page with name 'Setup page' is opened
    And info: ---7. Select "Regression Test - Customer USD" from Client/Customer dropdown---
    When I enter data into the next fields:
      | 'Client Customer sell-to' field | Regression Test - Customer USD |
    Then page with name 'Setup page' is opened
    And info: ---8. Fill Project Goals/Remarks with rich text, bullets & table (verify bold/italic, bullets, table supported)---
    And Fill Project Goals or Remarks with rich text, bullets, and table
    Then page with name 'Setup page' is opened
    And info: ---9. Click View to review options and verify dropdown appears---
    Then I click on 'Rich Text Editor View Dropdown' button
    And info: ---10. Select Fullscreen and verify Project Goals/Remarks displayed fullscreen---
    Then I click on 'Full Screen' option
    Then page with name 'Setup page' is opened
    And info: ---11. Add new row in Remarks table under Remark 3 and verify row added---
    And I 'Insert row after' row 3 in the table inside the Rich Text Editor
    Then page with name 'Setup page' is opened
    And info: ---12. Remove the added row and verify row removed---
    And I 'Delete row' row 4 in the table inside the Rich Text Editor
    Then page with name 'Setup page' is opened
    And info: ---13. Exit Fullscreen → redirected to Setup tab with Project Goals/Remarks retained---
    Then I click on 'Rich Text Editor View Dropdown' button
    Then I click on 'Normal' option
    Then page with name 'Setup page' is opened
    And info: ---14. Click Save and verify success message "Data saved successfully"---
    Then I click on 'Save' button
    And 'Data saved successfully' warning message is displayed in 30 seconds
    And info: ---15. Scroll to section "How do you want to Start your Project Plan"---
    Then page with name 'Setup page' is opened
    And info: ---16. Enable "Select a Prior Proposal" radio button and verify dropdown options appear---
    And I check 'Select a prior project' radio
    Then page with name 'Setup page' is opened
    And info: ---17. Enter "Regression Test Proposal for Copy Scenario - Manufacturing (Do Not Edit)" and verify proposal available to copy---
    When I enter data into the next fields:
      | 'Search Box' field | Regression Test Proposal for Copy Scenario - Manufacturing (Do Not Edit) |
    And page with name 'Setup page' is opened
    And info: ---18. Select options: Copy WBS/work-packages, Tasks & sub-tasks, Transfer to US - New York, Reset dates (1/1/27–12/31/30)---
    And I check 'Reset dates to' radio
    And I verify 'WBS or work-packages (9 elements)' of setup page is enabled, not required, check box and is checked
    And I verify 'Tasks & sub-tasks (11 tasks)' of setup page is enabled, not required, check box and is checked
    And I verify 'Transfer to US - New York' of setup page is enabled, not required, check box and is checked
    And I verify 'Reset dates to ' of setup page is enabled, not required, radio buttons and is checked
    And info: ---19. Click Copy and verify background message about BOE copy + success message "Data saved successfully"---
    And click on 'Copy' button
    And page with name 'Setup page' is opened
    And info: ---20. Click Cost Structure tab and verify navigation to Cost Structure---
    Then I click on 'Cost Structure' tab
    And page with name 'Cost Structure page' is opened

  @copyManufacturingProposalPerformCostStructureValidation-3 @END
  Scenario: TC-Copy-Manufacturing-Proposal-003: Perform Cost Structure [Estimate] Validation
    And info: ---1. Validate widget values → Total Revenue = USD 0, Total Cost = USD 0, Margin = 0%---
    And page with name 'Cost Structure page' is opened
    And text of 'Total Revenue' widget equals 'USD 0'
    And text of 'Total Cost' widget equals 'USD 0'
    And text of 'Margin' widget equals '0 %'
    And info: ---2. Verify selected View = "REG Test - Manufacturing Proposal Copy Validation" (if not, select from dropdown; if unavailable → error)---
    When I select 'REG Test - Manufacturing Proposal Copy Validation' in the 'WBS View' dropdown
    And I wait for 10 seconds
    And I click on 'Save' button
    Then text of 'WBS View' dropdown equals 'View: REG Test - Manufacturing Proposal Copy Validation'
    And page with name 'Cost Structure page' is opened
    And info: ---3. Verify WBS grid → Main row present, columns = Cost Structure, WP, Type, WP Owner, Work Package, Start, End, Total Cost w/ Risk, Labor Cost, Labor Hours, Material Cost, Other Cost---
    And number of rows in 'WBS' table equals 2
    And 'WBS' table contain row with following data:
      | Cost Structure        | $uniqueProposalName     |
    And I wait for 5 seconds
    Then I should see the following columns in the 'WBS' table:
      | Cost Structure   |
      | WP              |
      | Type            |
      | WP Owner        |
      | Work Package    |
      | Start           |
      | End             |
      | Total Cost with Risk Cost |
      | Labor Cost     |
      | Labor Hours    |
      | Material Cost  |
      | Other Cost     |
    And info: ---4. Click gear ⚙️ next to "Manual - TC-Copy-Manufacturing-Proposal-001: Setup & Copy Prior Proposal" → verify menu options: Expand/Collapse, Import Below, Copy Cost to DTC, Reset Min/Max Costs, Re-number WBS Below, Reschedule WBS/Task, Generate Cost Assignments---
    And I hover 'Cost Structure' cell in table 'WBS' table and click ⚙️ for row with data:
      | Cost Structure | $uniqueProposalName |
    And 'Gear' menuItems contains items:
      | Expand/Collapse        |
      | Import Below          |
      | Copy Cost to DTC      |
      | Reset Min/Max Costs   |
      | Re-number WBS Below   |
      | Generate Cost Assignments |
#      | Reschedule WBS or Task   |
    And info: ---5. Hover Expand/Collapse → verify options: Collapse All, Expand, Expand All---
    And I hover on 'Expand and Collapse' menuItem
    And 'Gear' menuItems contains items:
      | Collapse All |
      | Expand       |
      | Expand All   |
    And info: ---6. Select Expand All → verify full Cost Structure expanded---
    When I click on 'Expand All' menuItem
    And page with name 'Cost Structure page' is opened
    Then number of rows in 'WBS' table equals 21
    And info: ---7. Click gear ⚙️ again for "Manual - TC-Copy-Manufacturing-Proposal-001: Setup & Copy Prior Proposal" → verify overlay displayed---
    And I hover 'Cost Structure' cell in table 'WBS' table and click ⚙️ for row with data:
      | Cost Structure | $uniqueProposalName |
    And page with name 'Cost Structure page' is opened
    And 'Gear' menuItems contains items:
      | Expand/Collapse        |
      | Import Below          |
      | Copy Cost to DTC      |
      | Reset Min/Max Costs   |
      | Re-number WBS Below   |
      | Generate Cost Assignments |
#      | Reschedule WBS or Task   |
    And info: ---8. Hover Expand/Collapse → verify overlay still displayed---
    And I hover on 'Expand and Collapse' menuItem
    And 'Gear' menuItems contains items:
      | Collapse All |
      | Expand       |
      | Expand All   |
    And info: ---9. Select Collapse All → verify Cost Structure collapsed---
    When I click on 'Collapse All' menuItem
    And page with name 'Cost Structure page' is opened
    Then number of rows in 'WBS' table equals 2
    And info: ---10. Click gear ⚙️ next to "1 Overall Proposal" → verify menu options: Expand/Collapse, Add WBS Node, Copy WBS, Delete WBS, Import Below, Copy Cost to DTC, Reset Min/Max Costs, Re-number WBS Below, Reschedule WBS/Task, Generate Cost Assignments, Cascade---
    And I hover 'Cost Structure' cell in table 'WBS' table and click ⚙️ for row with data:
      | Cost Structure | 1 Overall Proposal |
    And 'Gear' menuItems contains items:
      | Expand/Collapse        |
      | Add WBS Node          |
      | Copy WBS              |
      | Copy WBS Element (all estimates) |
      | Delete WBS            |
      | Import Below          |
      | Copy Cost to DTC      |
      | Reset Min/Max Costs   |
      | Generate Cost Assignments  |
      | Open Estimate             |
#      | Reschedule WBS or Task   |
    And info: ---11. Click away on screen → verify overlay closed---
    When I click on 'Cost Structure' tab
    Then page with name 'Cost Structure page' is opened
    And 'Gear' menuItems is not displayed
    And info: ---12. Click + next to "1 Overall Proposal" → verify WBS expanded 1 level with:---
    And I click on '1 Overall Proposal' expander
    And page with name 'Cost Structure page' is opened
    And the cell value of the 'WP' column of the 'WBS' table is checked for a row with the following data:
      | Cost Structure | 1.1 Engineering |
    And the cell value of the 'WP' column of the 'WBS' table is checked for a row with the following data:
      | Cost Structure | 1.2 Project Management     |
    And the cell value of the 'WP' column of the 'WBS' table is checked for a row with the following data:
      | Cost Structure | 1.3 Decommissioning        |
    And the cell value of the 'WP' column of the 'WBS' table is checked for a row with the following data:
      | Cost Structure | 1.4 Maintenance & Support |
    And the cell value of the 'WP' column of the 'WBS' table is checked for a row with the following data:
      | Cost Structure | 1.5 Launch & Recovery  |
    And the cell value of the 'WP' column of the 'WBS' table is checked for a row with the following data:
      | Cost Structure | 1.6 Operational Readiness |
    And the cell value of the 'WP' column of the 'WBS' table is checked for a row with the following data:
      | Cost Structure | 1.7 Installation & Support |
    And the cell value of the 'WP' column of the 'WBS' table is checked for a row with the following data:
      | Cost Structure | 1.8 Production |
    And 'WBS' table contain row with following data:
      | Cost Structure | 1.1 Engineering |
      | Type           | Estimate                  |
    And 'WBS' table contain row with following data:
      | Cost Structure | 1.2 Project Management     |
      | Type           | Estimate                    |
    And 'WBS' table contain row with following data:
      | Cost Structure | 1.3 Decommissioning        |
      | Type           | Estimate                    |
    And 'WBS' table contain row with following data:
      | Cost Structure | 1.4 Maintenance & Support |
      | Type           | Estimate                  |
    And 'WBS' table contain row with following data:
      | Cost Structure | 1.5 Launch & Recovery  |
      | Type           | Estimate               |
    And 'WBS' table contain row with following data:
      | Cost Structure | 1.6 Operational Readiness |
      | Type           | Estimate                   |
    And 'WBS' table contain row with following data:
      | Cost Structure | 1.7 Installation & Support |
      | Type           | Estimate                    |
    And 'WBS' table contain row with following data:
      | Cost Structure | 1.8 Production |
      | Type           | Estimate        |
