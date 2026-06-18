@TEST
Feature: test

  Background:
    Given open site
    And I perform Mfg 2.4 login
    And I wait for 10 seconds
#    And page with name 'Estimate Labor' is opened
    Then page with name 'Main page' is opened

  Scenario: TC-Production-or-System-Proposal-008: Setup CLINs
    And I click on 'Proposal BOM' tab
    And page with name 'Estimate Proposal BOM' is opened
    And info: ---2. Select the view “MFC [default]” if not already selected. Verify Proposal BOM grid initializes.---
    When I select 'Regression Test - Manufacturing' in the 'View' dropdown
    And I click on 'Save' button
    Then text of 'View' dropdown equals 'View: Regression Test - Manufacturing'
    And page with name 'Estimate Proposal BOM' is opened
    When I select 'Regression Test View' in the 'View' dropdown
    And I click on 'Save' button
    Then text of 'View' dropdown equals 'View: Regression Test View'
    And page with name 'Estimate Proposal BOM' is opened
    And number of rows in 'Proposal BOM' table equals 3
    And info: ---3. Click the gear icon [⚙️] in Part Number column for Node = 1 and Description = LOT_SMOKE_TEST. Verify menu options appear.---
    When I hover 'Part Number' cell in table 'Proposal BOM' table and click ⚙️ for row with data:
      | Node        | 1                 |
      | Description | LOT_SMOKETEST_01  |
    Then 'Part Number Menu' options is displayed
    And info: ---4. Hover on Expand/Collapse → choose “Expand All.” Verify Proposal BOM structure expands.---
    And I hover on 'Expand or Collapse' menuItem
    And I hover on 'Expand All' menuItem
    Then I click on 'Expand All' menuItem
    And I wait for 7 seconds
    And page with name 'Estimate Proposal BOM' is opened
    And 'Proposal BOM' table is expanded
    And info: ---5. Select the view “Data Awareness: Change Notifications (shared).” Verify view updates and loads with no console errors.---
    And I start console log check session
    When I select 'Data Awareness: Change Notifications' in the 'View' dropdown
    And I wait for 7 seconds
    And I click on 'Save' button
    Then text of 'View' dropdown equals 'View: Data Awareness: Change Notifications (shared)'
    And page with name 'Estimate Proposal BOM' is opened
    And I stop console log check session_-Warning-_
    And info: ---6. Select the view “Data Awareness: Estimating Methods (shared).” Verify view updates and loads with no console errors.---
    And I start console log check session
    And I select 'Data Awareness: Estimating Methods' in the 'View' dropdown
    And I wait for 7 seconds
    And I click on 'Save' button
    Then text of 'View' dropdown equals 'View: Data Awareness: Estimating Methods (shared)'
    And page with name 'Estimate Proposal BOM' is opened
    And I stop console log check session_-Warning-_
    And info: ---7. Select the view “Data Awareness: Hours & Roll Up (shared).” Verify view updates and loads with no console errors.---
    And I start console log check session
    And I select 'Data Awareness: Hours & Roll Up' in the 'View' dropdown
    And I wait for 7 seconds
    And I click on 'Save' button
    Then text of 'View' dropdown equals 'View: Data Awareness: Hours & Roll Up (shared)'
    And page with name 'Estimate Proposal BOM' is opened
    And I stop console log check session_-Warning-_
    And info: ---8. Select the view “Data Awareness: Plant (shared).” Verify view updates and loads with no console errors.---
    And I start console log check session
    And I select 'Data Awareness: Plant' in the 'View' dropdown
    And I wait for 7 seconds
    And I click on 'Save' button
    Then text of 'View' dropdown equals 'View: Data Awareness: Plant (shared)'
    And page with name 'Estimate Proposal BOM' is opened
    And I stop console log check session_-Warning-_
    And info: ---9. Select the view “Data Awareness: Scheduling Lead Time & Due Before (shared).” Verify view updates and loads with no console errors.---
    And I start console log check session
    And I select 'Data Awareness: Scheduling Lead Time & Due Before' in the 'View' dropdown
    And I wait for 7 seconds
    And I click on 'Save' button
    Then text of 'View' dropdown equals 'View: Data Awareness: Scheduling Lead Time & Due Before (shared)'
    And page with name 'Estimate Proposal BOM' is opened
    And I stop console log check session_-Warning-_
    And info: ---10. Select the view “Data Awareness: Sets, CLINs, WBS (shared & preferred).” Verify view updates and loads with no console errors.---
    And I start console log check session
    And I select 'Data Awareness: Sets, CLINs, WBS' in the 'View' dropdown
    And I wait for 7 seconds
    And I click on 'Save' button
    Then text of 'View' dropdown equals 'View: Data Awareness: Sets, CLINs, WBS (shared & preferred)'
    And page with name 'Estimate Proposal BOM' is opened
    And I stop console log check session_-Warning-_
    And info: ---11. Select the view “Data Awareness: Similar-To & Complexity (shared).” Verify view updates and loads with no console errors.---
    And I start console log check session
    And I select 'Data Awareness: Similar-To & Complexity' in the 'View' dropdown
    And I wait for 7 seconds
    And I click on 'Save' button
    Then text of 'View' dropdown equals 'View: Data Awareness: Similar-To & Complexity (shared)'
    And page with name 'Estimate Proposal BOM' is opened
    And I stop console log check session_-Warning-_
    And info: ---12. Re-select the view “MFC [default].” Verify view updates and loads with no console errors.---
    And I start console log check session
    And I select 'MCE[default]' in the 'View' dropdown
    And I wait for 7 seconds
    And I click on 'Save' button
    Then text of 'View' dropdown equals 'View: MCE[default]'
    And page with name 'Estimate Proposal BOM' is opened
    And I stop console log check session_-Warning-_

  @ART-200 @LM-TC-003_2 @RUN
  Scenario: TC-Production/System-Proposal-011: Update & Cost Consolidated BOM
    And page with name 'Estimate Proposal BOM' is opened
    When I select 'Regression Test View' in the 'View' dropdown
    And I click on 'Save' button
    Then text of 'View' dropdown equals 'View: Regression Test View'
    And info: ---1. Select hamburger menu. Verify that menu options are displayed and the available options are Update Formula-based Costs, Update & Cost Consolidated BOM, Project Change Requests, Generate Sourcing Plan, BOM Version Comparison, Open, and Delete.---
    And page with name 'Estimate Proposal BOM' is opened
    When I click on 'Hamburger' menu
    Then 'Hamburger' menu options contains items:
      | Update Formula-based Costs     |
      | Update & Cost Consolidated BOM |
      | Project Change Requests        |
      | Generate Sourcing Plan         |
      | BOM Version Comparison         |
      | Open                           |
#      | Delete                         |
    And info: ---2. Hover on “Update & Cost Consolidated BOM” and select “All Parts”. Verify that the “Consolidate and Cost Bill of Material” popup is displayed and the available tabs are Proposal Controls, Purchase History, Production History, and Advanced Options.---
    When I hover on 'Update & Cost Consolidated BOM' menuItem
    And I click on 'All Parts' menuItem
    Then 'Consolidate and Cost Bill of Material' popup is displayed
    And 'Consolidate and Cost Bill of Material' headers contains items:
      | Proposal Controls  |
      | Purchase History   |
      | Production History |
      | Advanced Options   |
    And I wait for 3 seconds
    And info: ---3. Click “Set Default” and select “MFC [defaults] shared”. Verify that “MFC [defaults] shared” is available and the button is updated to “MFC [defaults] shared”.---
    And I click on 'Set Default' button
    And I click on 'Regression Test BOM Costing Profile' dropdown
    And text of 'Set Default' button equals 'Regression Test BOM Costing Profile (shared)'_-Warning-_
    And I click on 'Set Default' button
    And I click on 'MFC defaults shared' dropdown
    And text of 'Set Default' button equals 'MFC/M510 (shared)'_-Warning-_
    And I click on 'Set Default' button
    And I click on 'Regression Test BOM Costing Profile' dropdown
    And text of 'Set Default' button equals 'Regression Test BOM Costing Profile (shared)'
    And info: ---4. Click “Run - All Parts”. Verify that a notification message is displayed.---
    When I click on 'Run - All Parts' button
    Then 'Bill of material is being consolidated' notification is displayed
    Then 'Consolidate and Cost Bill of Material' popup is not displayed
    And I wait for 9 seconds
    And I refresh page
    And I wait for 9 seconds
    And page with name 'Estimate Proposal BOM' is opened
#    And I wait until uploading formula completed


  @ART-199 @LM-TC-003_3 @RUN
  Scenario: TC-Production/System-Proposal-012: Validate UI Behavior [Consolidated BOM]
    And info: ---1. Select the Consolidated BOM tab. Verify that the Consolidated BOM tab is displayed.---
    And page with name 'Estimate Proposal BOM' is opened
    When I click on 'Consolidated BOM' tab
    And I wait for 7 seconds
    Then page with name 'Estimate Consolidated BOM' is opened
    And info: ---2. Select the view “Data Assignments: Sets, WBSs, Hours, Costs (shared)”. Verify that the view is updated and loaded, and no console errors are present.---
    And I start console log check session
    When I select 'Data Assignments: Sets, WBSs, Hours, Costs' in the 'View' dropdown
    And I wait for 7 seconds
    And I click on 'Save' button
    Then text of 'View' dropdown equals 'View: Data Assignments: Sets, WBSs, Hours, Costs (shared)'
    And page with name 'Estimate Consolidated BOM' is opened
    And I stop console log check session_-Warning-_
    And info: ---3. Select the view “Data Awareness: High Adj Unit Cost (shared)”. Verify that the view is updated and loaded, and no console errors are present.---
    And I start console log check session
    When I select 'Data Awareness:  High Adj Unit Cost' in the 'View' dropdown
    And I wait for 7 seconds
    And I click on 'Save' button
    Then text of 'View' dropdown equals 'View: Data Awareness: High Adj Unit Cost (shared)'
    And page with name 'Estimate Consolidated BOM' is opened
    And I stop console log check session_-Warning-_
    And info: ---4. Select the view “Data Quality: Low Confidence / Sourcing Necessary (shared)”. Verify that the view is updated and loaded, and no console errors are present.---
    And I start console log check session
    When I select 'Data Quality: Low Confidence / Sourcing Necessary' in the 'View' dropdown
    And I wait for 7 seconds
    And I click on 'Save' button
    Then text of 'View' dropdown equals 'View: Data Quality: Low Confidence / Sourcing Necessary (shared)'
    And page with name 'Estimate Consolidated BOM' is opened
    And I stop console log check session_-Warning-_
    And info: ---5. Tick the Sourcing Required checkbox where WBS = 1.2 LOT_SMOKETEST_01 (LOT_SMOKETEST), Part Number = AVC_RBT_BASE_FRAME, and Description = Base Frame. Verify that the record is available and the Sourcing Required checkbox is ticked.---
    Then I select 'Regression Test View' in the 'View' dropdown
    And I click on 'Save' button
    Then text of 'View' dropdown equals 'View: Regression Test View'
    And I activate checkbox 'Sourcing Required' in table 'Consolidated BOM' table for row with data:
      | WBS        | 1.2 LOT_SMOKETEST_01 (LOT_SMOKETEST) |
      | Part Number | AVC_RBT_BASE_FRAME                 |
      | Description | Base Frame                         |
    Then the cell value of the 'Sourcing Required' column of the 'Consolidated BOM' table is checked for a row with the following data:
      | WBS        | 1.2 LOT_SMOKETEST_01 (LOT_SMOKETEST) |
      | Part Number | AVC_RBT_BASE_FRAME                 |
      | Description | Base Frame                         |

  @ART-198 @LM-TC-003_4 @RUN
  Scenario: TC-Production/System-Proposal-013: Update Consolidated BOM & Generate Sourcing Plan
    And info: ---1. Tick the Sourcing Required checkbox for the following records:• WBS = 1.2 LOT_SMOKETEST_01 (LOT_SMOKETEST), Part Number = AVC_RBT_BASE_FRAME, Description = Base Frame• WBS = 1.3 LOT_SMOKETEST_01 (LOT_SMOKETEST), Part Number = AVC_RBT_BASE_FRAME, Description = Base Frame Verify that the records are available and the Sourcing Required checkboxes are ticked.---
    And I activate checkbox 'Sourcing Required' in table 'Consolidated BOM' table for row with data:
      | WBS        | 1.2 LOT_SMOKETEST_01 (LOT_SMOKETEST) |
      | Part Number | AVC_RBT_BASE_FRAME                 |
      | Description | Base Frame                         |
    Then the cell value of the 'Sourcing Required' column of the 'Consolidated BOM' table is checked for a row with the following data:
      | WBS        | 1.2 LOT_SMOKETEST_01 (LOT_SMOKETEST) |
      | Part Number | AVC_RBT_BASE_FRAME                 |
      | Description | Base Frame                         |
    And I activate checkbox 'Sourcing Required' in table 'Consolidated BOM' table for row with data:
      | WBS        | 1.3 LOT_SMOKETEST_01 (LOT_SMOKETEST) |
      | Part Number | AVC_RBT_BASE_FRAME                 |
      | Description | Base Frame                         |
    Then the cell value of the 'Sourcing Required' column of the 'Consolidated BOM' table is checked for a row with the following data:
      | WBS        | 1.3 LOT_SMOKETEST_01 (LOT_SMOKETEST) |
      | Part Number | AVC_RBT_BASE_FRAME                 |
      | Description | Base Frame                         |
    And info: ---2. Click Save. Verify that data is saved and Sourcing Event boxes turn orange for the ticked records.---
    When I click on 'Save' button
#    Then 'Data saved successfully' popUp is displayed
    And I wait for 10 seconds
    Then the cell color of the 'Sourcing Event' column of the 'Consolidated BOM' table is 'orange' for a row with the following data_-Warning-_:
      | WBS         | 1.2 LOT_SMOKETEST_01 (LOT_SMOKETEST) |
      | Part Number | AVC_RBT_BASE_FRAME                 |
      | Description | Base Frame                         |
    Then the cell color of the 'Sourcing Event' column of the 'Consolidated BOM' table is 'orange' for a row with the following data_-Warning-_:
      | WBS         | 1.3 LOT_SMOKETEST_01 (LOT_SMOKETEST) |
      | Part Number | AVC_RBT_BASE_FRAME                 |
      | Description | Base Frame                         |
    And info: ---3. Tick the record selector checkbox for the following records:• WBS = 1.2 LOT_SMOKETEST_01 (LOT_SMOKETEST), Part Number = AVC_RBT_BASE_FRAME, Description = Base Frame• WBS = 1.3 LOT_SMOKETEST_01 (LOT_SMOKETEST), Part Number = AVC_RBT_BASE_FRAME, Description = Base Frame Verify that the record selector checkboxes are ticked.---
    And I activate checkbox 'Record Selector' in table 'Consolidated BOM' table for row with data:
      | WBS        | 1.2 LOT_SMOKETEST_01 (LOT_SMOKETEST) |
      | Part Number | AVC_RBT_BASE_FRAME                 |
      | Description | Base Frame                         |
    Then the cell value of the 'Record Selector' column of the 'Consolidated BOM' table is checked for a row with the following data:
      | WBS        | 1.2 LOT_SMOKETEST_01 (LOT_SMOKETEST) |
      | Part Number | AVC_RBT_BASE_FRAME                 |
      | Description | Base Frame                         |
    And I activate checkbox 'Record Selector' in table 'Consolidated BOM' table for row with data:
      | WBS        | 1.3 LOT_SMOKETEST_01 (LOT_SMOKETEST) |
      | Part Number | AVC_RBT_BASE_FRAME                 |
      | Description | Base Frame                         |
    Then the cell value of the 'Record Selector' column of the 'Consolidated BOM' table is checked for a row with the following data:
      | WBS        | 1.3 LOT_SMOKETEST_01 (LOT_SMOKETEST) |
      | Part Number | AVC_RBT_BASE_FRAME                 |
      | Description | Base Frame                         |
    And info: ---4. Select the hamburger menu. Verify that menu options are presented.---
    When I click on 'Hamburger' menu
    Then 'Hamburger' menu options contains items:
      | Update Formula-based Costs     |
      | Update & Cost Consolidated BOM |
      | Project Change Requests        |
      | Generate Sourcing Plan         |
      | BOM Version Comparison         |
      | Open                           |
#        | Delete                         |
    And info: ---5. Select Generate Sourcing Plan. Verify that the Generate Supplier RFPs popup is displayed.---
    When I click on 'Generate Sourcing Plan' menuItem
    Then 'Generate Supplier RFPs' popup is displayed
    And info: ---6. Click in the Response Due On field and enter 11/30/26. Verify that the field is available and the value is accepted.---
    When I enter data into the next fields:
      | 'Response Due On' Date | 11/30/26 |
    And I press enter
    And I wait for 5 seconds
    And info: ---7. Click Confirm. Verify that the popup collapses, a notification appears stating “1 supplier RFP’s were generated, to review in sourcing app”, and Sourcing Event boxes are updated for the previously selected records.---
    When I click on 'Confirm' button
    Then Warning Supplier RFP’s notification is visible
    Then 'Generate Supplier RFPs' popup is not displayed
    And I wait for 30 seconds
    Then 'Consolidated BOM' table contain row with following data:
      | WBS            | 1.2 LOT_SMOKETEST_01 (LOT_SMOKETEST) |
      | Part Number    | AVC_RBT_BASE_FRAME                   |
      | Description    | Base Frame                           |
      | Sourcing Event | L003                                 |
    Then 'Consolidated BOM' table contain row with following data:
      | WBS            | 1.3 LOT_SMOKETEST_01 (LOT_SMOKETEST) |
      | Part Number    | AVC_RBT_BASE_FRAME                   |
      | Description    | Base Frame                           |
      | Sourcing Event | L003                                 |

  @ART-202 @LM-TC-003_5 @RUN
  Scenario: TC-Production/System-Proposal-014: Navigate through Purchased Part (CBOM) Popup
    And info: ---1. Hover over the row where Description = Base Frame, WBS = 1.2 LOT_SMOKETEST_01 (LOT_SMOKETEST), and Part Number = AVC_RBT_BASE_FRAME to reveal and select the gear icon [⚙️]. Verify that the gear icon is available and options are displayed.---
    And page with name 'Estimate Consolidated BOM' is opened
    When I hover 'Description' cell in table 'Consolidated BOM' table and click ⚙️ for row with data:
      | WBS        | 1.2 LOT_SMOKETEST_01 (LOT_SMOKETEST) |
      | Part Number | AVC_RBT_BASE_FRAME                 |
      | Description | Base Frame                         |
    Then 'Description Menu' options is displayed
    And info: ---2. Select Edit Details. Verify that the popup “Purchased Part: Estimate for: AVC_RBT_BASE_FRAME Base Frame (1,224.00 ea $ 889,249.09) at Dallas, Grand Prairie” is displayed.---
    And I click on 'Edit Details' option
    And page with name 'Purchased Part: Estimate - Cost Estimate' is opened
    And info: ---3. Select the Additional Data tab. Verify that the tab is loaded and no console errors are present.---
    And I start console log check session
    When I click on 'Additional Data' tab
    Then page with name 'Purchased Part: Estimate - Additional Data' is opened
    And I stop console log check session_-Warning-_
    And info: ---4. Select the Payments tab. Verify that the tab is loaded and no console errors are present.---
    And I start console log check session
    When I click on 'Payments' tab
    Then page with name 'Purchased Part: Estimate - Payments' is opened
    And I stop console log check session_-Warning-_
    And info: ---5. Select the Proposal Requirements tab. Verify that the tab is loaded and no console errors are present.---
    And I start console log check session
    When I click on 'Proposal Requirements' tab
    Then page with name 'Purchased Part: Estimate - Proposal Requirements' is opened
    And I stop console log check session_-Warning-_
    And info: ---6. Select the Tags tab. Verify that the tab is loaded and no console errors are present.---
    And I start console log check session
    When I click on 'Tags' tab
    Then page with name 'Purchased Part: Estimate - Tags' is opened
    And I stop console log check session_-Warning-_
    And info: ---7. Select the Remarks tab. Verify that the tab is loaded and no console errors are present.---
    And I start console log check session
    When I click on 'Remarks' tab
    Then page with name 'Purchased Part: Estimate - Remarks' is opened
    And I stop console log check session_-Warning-_
    And info: ---8. Select the Costing Log tab. Verify that the tab is loaded with data in the Costing Log and no console errors are present.---
    And I start console log check session
    When I click on 'Costing Log' tab
    Then page with name 'Purchased Part: Estimate - Costing Log' is opened
    And I stop console log check session_-Warning-_
    And info: ---9. Select the History tab. Verify that the tab is loaded with History populated and no console errors are present.---
    And I start console log check session
    When I click on 'History' tab
    Then page with name 'Purchased Part: Estimate - History' is opened
    And I stop console log check session_-Warning-_
    And info: ---10. Click Confirm. Verify that the popup collapses and a “Data saved successfully” notification appears.---
    When I click on 'Confirm' button
    Then 'Purchased Part: Estimate' popup is not displayed
    Then 'Data saved successfully' popUp is displayed

  @LM-TC-003_6 @END
  Scenario: TC-Production/System-Proposal-015: Navigate to Cost Price Analysis to Update Workbench
    And page with name 'Main page' is opened
    And info: ---1. Open hamburger menu. Verify options are displayed.---
    And 'Hamburger' menu is displayed
    Then I click on 'Hamburger' menu
    And info: ---2. Hover on Open and select Cost Price Analysis. Verify user is redirected to the Cost Price Analysis app.---
    Then I choose in 'Hamburger' menu the next menu chain:
      | Open  |
      | Open Cost Price Analysis |
    And page with name 'Main page' is opened
    And info: ---3. Navigate to Workbench tab. Verify tab is loaded.---
    Then I switch to tab 2
    And page with name 'Cost Price Analysis Wbs page' is opened
    Then I click on 'Workbench' tab
    Then page with name 'Workbench page' is opened
    And info: ---4. Select view “Test for edit to workbench view (shared & preferred)”. Verify Workbench grid is initialized.---
    When I select 'Test for edit to workbench view (shared & preferred)' in the 'View' dropdown
    And I click on 'Save' button
    Then text of 'View' dropdown equals 'View: Test for edit to workbench view (shared & preferred)'
    And page with name 'Workbench page' is opened
    And info: ---5. Expand Not Assigned row. Verify records expand with no console errors.---
    And I start console log check session
    When I click on 'Not Assigned' expander
    And 'Not Assigned' options is displayed
    And I stop console log check session_-Warning-_
    And info: ---6. Click gear icon and select Change rows and columns. Verify panel opens with options.---
    Then page with name 'Workbench page' is opened
    When I select 'Change Rows & Columns' in the 'Cog Settings' button
    And 'Change Rows & Columns' window is displayed
    And info: ---7. Add Cost Element to Left Dimensions (Rows). Verify it is added under Cost/Revenue Component.---
    When I select 'Add to Left Dimensions (Rows)' in the 'Cost Element' menu item
    Then page with name 'Workbench page' is opened
    When I select 'Move down' in the 'Description of Cost' menu item
    Then page with name 'Workbench page' is opened
    And 'Change Rows & Columns' window is displayed
    And info: ---8. Click Apply & Close. Verify panel collapses.---
    And I click on 'Apply' button
    Then page with name 'Workbench page' is opened
    And I click on 'Apply & Close' button
    Then page with name 'Workbench page' is opened
    And 'Change Rows & Columns' window is not displayed
    And I click on 'Save' button
    And info: ---9. Expand Not Assigned row again. Verify records expand with no console errors.---
    And I start console log check session
    When I click on 'Not Assigned' expander
    And 'Not Assigned' options is displayed
    And I stop console log check session_-Warning-_
    And info: ---10. Expand Direct row. Verify records expand with no console errors.---
    And I start console log check session
    When I click on 'direct' expander
    And 'direct' options is displayed
    And I stop console log check session_-Warning-_
    And info: ---11. Expand Not Assigned under Direct. Verify records expand with no console errors.---
    And I start console log check session
    And I click on 'Direct Other materials' expander
    And 'Direct Other materials' options is displayed
    And I stop console log check session_-Warning-_
    And info: ---12. Collapse Not Assigned under Direct. Verify records collapse with no console errors.---
    And I start console log check session
    And I click on 'Direct Other materials' expander
    And 'Direct Other materials' options is not displayed
    And I stop console log check session_-Warning-_
    And info: ---13. Expand Inflation row. Verify records expand with no console errors.---
    And I start console log check session
    And I click on 'inflation' expander
    And 'inflation' options is displayed
    And I stop console log check session_-Warning-_
    And info: ---14. Expand Not Assigned under Inflation. Verify records expand with no console errors.---
    And I start console log check session
    And I click on 'Inflation Other materials' expander
    And 'Inflation Other materials' options is displayed
    And I stop console log check session_-Warning-_