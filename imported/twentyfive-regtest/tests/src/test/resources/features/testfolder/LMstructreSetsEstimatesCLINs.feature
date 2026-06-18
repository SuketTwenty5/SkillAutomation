@structureSetsEstimatesCLINs
Feature: 2. Structure Sets, Estimates, CLIN, & Import BOMs

  @ART-206 @LM-TC-002_1 @START
  Scenario:   TC-Production-or-System-Proposal-006: Structure Sets
    Given open TC-Production-or-System-Proposal-005 quote
    And I perform Mfg 2.4 login
    And page with name 'Main page' is opened
    And info: ---1. Navigate to the Sets tab. Verify that the Sets tab is displayed.---
    When I click on 'Sets' tab
    Then page with name 'Sets page' is opened
    And info: ---2. Select “Regression Test View” if not already selected. Verify grid initializes with expected columns (Set, Set Group, Max in Series, Description, Option, Start, End, WBS, CLINs, Estimate, Costing Profile, Cost, Baseline).---
    When I select 'Regression Test View' in the 'View' dropdown
    And I click on 'Save' button
    Then text of 'View' dropdown equals 'View: Regression Test View'
    And 'Column' header contains items:
        | Option        |
        | Set#          |
        | Description   |
        | Estimate      |
        | Start         |
        | End           |
        | WBS           |
        | Cost          |
        | Revenue       |
        | Baseline      |
        | Deliverables  |
    And info: ---3. Click “No records found, click here to add”. Verify new row appears with default values (Set# = 1, Baseline ticked, others empty, Start/End match Proposal dates, Estimate shows Create link).---
    When I click on 'No records found, click here to add' button
    And 'Sets' table contain row with following data:
        | Set#       | 1           |
    And info: ---4. Enter “A” in Description for Set 1. Verify free text entry is accepted and populated.---
    And I set value 'A' to the cell of the column 'Description' of the 'Sets' table for the row with the following data:
      | Set# | 1 |
    And 'Sets' table contain row with following data:
      | Set#                | 1        |
      | Description        | A        |
    And info: ---5. Click “Copy” icon to add another record. Verify new row is added to grid.---
    And I click 'Copy' in table 'Sets' table for row with data:
      | Set# | 1 |
    And 'Sets' table contain row with following data:
        | Set#                | 2        |
        | Description        | A        |
    And info: ---6. Enter “B” in Description for Set 2. Verify value is accepted and displayed.---
    And I set value 'B' to the cell of the column 'Description' of the 'Sets' table for the row with the following data:
      | Set# | 2 |
    And 'Sets' table contain row with following data:
      | Set#                | 2        |
      | Description        | B        |
    And info: ---7. Click the copy row icon and update Description to C for copied row ---
    And I click 'Copy' in table 'Sets' table for row with data:
      | Set# | 2 |
    And 'Sets' table contain row with following data:
      | Set#                | 3        |
      | Description        | B        |
    And I set value 'C' to the cell of the column 'Description' of the 'Sets' table for the row with the following data:
      | Set# | 3 |
    And 'Sets' table contain row with following data:
      | Set#                | 3        |
      | Description        | C        |
    And info: ---8. Tick the Option boxes for Sets 2 and 3.---
    And I click 'Option' in table 'Sets' table for row with data:
      | Set# | 2 |
    And I click 'Option' in table 'Sets' table for row with data:
      | Set# | 3 |
    And 'Sets' table contain row with following data_-Warning-_:
      | Set#                | 2        |
      | Description        | B        |
      | Option Combination |Independent Option |
    And 'Sets' table contain row with following data_-Warning-_:
      | Set#                | 3        |
      | Description        | C        |
      | Option Combination |Independent Option |
    And info: ---9. Click Save. Verify data is saved successfully.---
    Then I click on 'Save' button
    Then 'Data saved successfully' popUp is displayed

  @ART-205 @LM-TC-002_2 @RUN
  Scenario: TC-Production-or-System-Proposal-007: Edit WBS Cost Structure
    And info: ---1. Navigate to WBS Cost Structure tab. Verify tab content loads.---
    And I click on 'WBS Cost Structure' tab
    Then page with name 'WBS Cost Structure page' is opened
    And info: ---2. Select view “Regression WBS Cost Structure View” if not selected. Verify Sets grid initializes.---
    When I select 'Regression WBS Cost Structure View' in the 'View' dropdown
    And I click on 'Save' button
    Then text of 'View' dropdown equals 'View: Regression WBS Cost Structure View (shared)'
    And info: ---3. Click + icon to add row. Verify new row added and WBS Code = 1.---
    And I click '+' in table 'WBS Cost Structure' table in row number 1
    And 'WBS Cost Structure' table contain row with following data:
      | WBS Code | 1      |
      | Estimate | Create |
    And info: ---4. Enter “Estimate” in Cost Structure field. Verify value is retained.---
    And I set value 'Estimate' to the cell of the column 'Cost Structure' of the 'WBS Cost Structure' table for the row with the following data:
      | WBS Code | 1 |
    And 'WBS Cost Structure' table contain row with following data:
      | WBS Code       | 1        |
      | Cost Structure | Estimate |
      | Estimate        | Create |
    And info: ---5. Click Create hyperlink in Estimate field. Verify Estimate popup displays with owner name, default strategy options, Design to Cost Target = $0.00, and buttons Confirm & Release / Confirm & Open / Close.---
    When I click 'Create' in table 'WBS Cost Structure' table for row with data:
      | WBS Code | 1 |
    And 'Estimate' popUp is displayed
    And I wait for 7 seconds
    And I verify 'Owner' of WBS Dialog page is enabled, required, drop down and has text 'Suket Suman'
    And I verify 'Approver' of WBS Dialog page is enabled, not required, drop down and is empty
    And I verify 'Estimating Strategy' of WBS Dialog page is enabled, required, free text and has tags 'Engineering/Functional Labor,Other Direct Costs & Factors,Proposal BOM,Consolidated BOM'
    And I verify 'Design to Cost Target' of WBS Dialog page is enabled, not required, free text and has text '$'
    And 'Confirm & Open' button is displayed
    And 'Confirm & Release' button is displayed
    And 'Close' button is displayed
    And info: ---6. Click Confirm & Release. Verify popup closes and hyperlink text changes to Open.---
    When I click on 'Confirm & Release' button
    And 'Estimate' popUp is not displayed
    And 'WBS Cost Structure' table contain row with following data:
      | WBS Code       | 1        |
      | Cost Structure | Estimate |
      | Estimate       | Open     |
    And info: ---7. Click gear icon ⚙️ and select Assign Existing SAP Project ID. Verify option appears and popup opens.---
    Then I choose in 'Cog Settings' menu the next menu chain:
      | Assign Existing SAP Project ID |
    And 'Assign Existing SAP Project ID' popup is displayed
    And I wait for 5 seconds
    And info: ---8. Select SAP Project “MFG01”. Verify value is available and retained.---
    When I enter data into the next fields:
      | 'Select an SAP Project to assign to all WBS elements' field | A0003        |
    And info: ---9. Click Confirm. Verify popup collapses.---
    When I click on 'Confirm' button
    And 'Assign Existing SAP Project ID' popup is not displayed

  @ART-204 @LM-TC-002_3 @RUN
  Scenario: TC-Production-or-System-Proposal-008: Setup CLINs
    And info: ---1. Navigate to CLINs tab. Verify tab content loads.---
    And I click on 'CLINs' tab
    Then page with name 'CLINs page' is opened
    And info: ---2. Select the view “Regression CLINs View” if not selected. Verify CLINs grid initializes.---
    When I select 'Regression CLINs View' in the 'View' dropdown
    And I click on 'Save' button
    Then text of 'View' dropdown equals 'View: Regression CLINs View (shared)'
    And info: ---3. Click “No records found, click here to add.” Verify new row added and Item = 1.---
    When I click on 'No records found, click here to add' button
    And 'CLINs' table contain row with following data:
      | Item | 1 |
    And info: ---4. Enter 10000 in CLIN column. Verify value is retained.---
    And I set value '10000' to the cell of the column 'CLIN' of the 'CLINs' table for the row with the following data:
      | Item | 1 |
    And 'CLINs' table contain row with following data:
      | Item | 1      |
      | CLIN  | 10000  |
    And info: ---5. In Part Number field, type LOT_SMOKETEST_01 and press Enter. Verify list loads, filtering works, value selectable, description updates, and Estimate defaults to “1 Estimate.”---
    And I set value 'LOT_SMOKETEST_01' to the cell of the column 'Part Number' of the 'CLINs' table for the row with the following data:
      | Item | 1 |
    And 'CLINs' table contain row with following data:
      | Item           | 1                |
      | CLIN          | 10000            |
      | Part Number    | LOT_SMOKETEST_01 |
      | Description    | LOT_SMOKETEST_01 |
      | Estimate       | 1 Estimate       |
    And info: ---6. Enter 12/31/2029 in End of Delivery. Verify value accepted.---
    And I set value '12/31/2029' to the cell of the column 'End of Delivery' of the 'CLINs' table for the row with the following data:
      | Item | 1 |
    And 'CLINs' table contain row with following data:
      | Item                     | 1                |
      | CLIN                      | 10000            |
      | Part Number              | LOT_SMOKETEST_01 |
      | Description              | LOT_SMOKETEST_01 |
      | Estimate                 | 1 Estimate       |
      | End of Delivery | 12/31/2029       |
    And info: ---7. Enter 1/1/2027 in Start of Delivery. Verify value accepted.---
    And I set value '1/1/2027' to the cell of the column 'Start of Delivery' of the 'CLINs' table for the row with the following data:
      | Item | 1 |
    And 'CLINs' table contain row with following data:
      | Item                      | 1                |
      | CLIN                     | 10000            |
      | Part Number               | LOT_SMOKETEST_01 |
      | Description              | LOT_SMOKETEST_01 |
      | Estimate                  | 1 Estimate       |
      | End of Delivery  | 12/31/2029       |
      | Start of Delivery| 1/1/2027         |
    And info: ---8. Enter 2 in Quantity per Month. Verify accepted and Quantity auto-updates to 72.---
    And I set value '2' to the cell of the column 'Quantity per Month' of the 'CLINs' table for the row with the following data:
      | Item | 1 |
    And 'CLINs' table contain row with following data:
      | Item                      | 1                |
      | CLIN                     | 10000            |
      | Part Number               | LOT_SMOKETEST_01 |
      | Description               | LOT_SMOKETEST_01 |
      | Estimate                  | 1 Estimate       |
      | End of Delivery  | 12/31/2029       |
      | Start of Delivery| 1/1/2027         |
      | Quantity per Month        | 2 ea             |
      | Quantity                  | 72 ea            |
    And info: ---9. Click Copy Row icon. Verify row copied and CLIN updates to 10001.---
    And I click 'Copy' in table 'CLINs' table for row with data:
      | Item | 1 |
    And 'CLINs' table contain row with following data:
      | Item                      | 2                |
      | CLIN                     | 10001            |
      | Part Number               | LOT_SMOKETEST_01 |
      | Description               | LOT_SMOKETEST_01 |
      | End of Delivery  | 12/31/2029       |
      | Start of Delivery| 1/1/2027         |
      | Quantity per Month        | 2 ea             |
      | Quantity                  | 72 ea            |
    And info: ---10. Click Copy Row icon again. Verify row copied and CLIN updates to 10002.---
    And I click 'Copy' in table 'CLINs' table for row with data:
      | Item | 2 |
    And 'CLINs' table contain row with following data:
      | Item                      | 3                |
      | CLIN                     | 10002            |
      | Part Number               | LOT_SMOKETEST_01 |
      | Description               | LOT_SMOKETEST_01 |
      | End of Delivery  | 12/31/2029       |
      | Start of Delivery| 1/1/2027         |
      | Quantity per Month        | 2 ea             |
      | Quantity                  | 72 ea            |
    And info: ---11. Set value to A where CLIN = 10000. Verify value retained.---
    And I set value 'A' to the cell of the column 'Set' of the 'CLINs' table for the row with the following data:
      | CLIN | 10000 |
    And 'CLINs' table contain row with following data:
      | Set                       | A                |
      | CLIN                     | 10000            |
      | Part Number               | LOT_SMOKETEST_01 |
      | Description               | LOT_SMOKETEST_01 |
      | Estimate                  | 1 Estimate       |
      | End of Delivery  | 12/31/2029       |
      | Start of Delivery| 1/1/2027         |
      | Quantity per Month        | 2 ea             |
      | Quantity                  | 72 ea            |
    And info: ---12. Set value to B where CLIN = 10001. Verify value retained.---
    And I set value 'B' to the cell of the column 'Set' of the 'CLINs' table for the row with the following data:
      | CLIN | 10001 |
    And 'CLINs' table contain row with following data:
      | Set                       | B                |
      | CLIN                     | 10001            |
      | Part Number               | LOT_SMOKETEST_01 |
      | Description               | LOT_SMOKETEST_01 |
      | End of Delivery  | 12/31/2029       |
      | Start of Delivery| 1/1/2027         |
      | Quantity per Month        | 2 ea             |
      | Quantity                  | 72 ea            |
    And info: ---13. Set value to C where CLIN = 10002. Verify value retained.---
    And I set value 'C' to the cell of the column 'Set' of the 'CLINs' table for the row with the following data:
      | CLIN | 10002 |
    And 'CLINs' table contain row with following data:
      | Set                       | C                |
      | CLIN                     | 10002            |
      | Part Number               | LOT_SMOKETEST_01 |
      | Description               | LOT_SMOKETEST_01 |
      | End of Delivery  | 12/31/2029       |
      | Start of Delivery| 1/1/2027         |
      | Quantity per Month        | 2 ea             |
      | Quantity                  | 72 ea            |
    And info: ---14. Update Estimate to “1 Estimate” where CLIN = 10001. Verify value selectable and retained.---
    And I set value '1 Estimate' to the cell of the column 'Estimate' of the 'CLINs' table for the row with the following data:
      | CLIN | 10001 |
    And 'CLINs' table contain row with following data:
      | Set                       | B                |
      | CLIN                     | 10001            |
      | Part Number               | LOT_SMOKETEST_01 |
      | Description               | LOT_SMOKETEST_01 |
      | Estimate                  | 1 Estimate       |
    And info: ---15. Update Estimate to “1 Estimate” where CLIN = 10002. Verify value retained.---
    And I set value '1 Estimate' to the cell of the column 'Estimate' of the 'CLINs' table for the row with the following data:
      | CLIN | 10002 |
    And 'CLINs' table contain row with following data:
      | Set                       | C                |
      | CLIN                     | 10002            |
      | Part Number               | LOT_SMOKETEST_01 |
      | Description               | LOT_SMOKETEST_01 |
      | Estimate                  | 1 Estimate       |
    And info: ---16. In Cost Allocation for CLIN 10000, choose Create New. Verify Create New option visible and popup opens.---
    And I click 'Cost Allocation' in table 'CLINs' table for row with data:
      | CLIN | 10000 |
    And I click on 'Cell dropdown' arrow
    And 'Cell' dropdown is displayed
    And I click on 'Create New' button
    And 'Cost Allocation' popUp is displayed
    And I wait for 10 seconds
    And if visible click on 'Reset Now' button
    And I wait for 3 seconds
    And info: ---17. In popup verify fields, click Confirm. Verify Description updates to LOT_SMOKETEST_01, Insert under set to 1 Estimate, Number = 1.1, Type = WBS Element, and Cost Allocation updated to 1.1 LOT_SMOKETEST_01.---
    And I verify 'Description' of Cost Allocation Dialog page is enabled, required, free text and has text 'LOT_SMOKETEST_01 (LOT_SMOKETEST_01)'
    And I verify 'Insert new WBS under' of Cost Allocation Dialog page is enabled, required, drop down and has text '1 Estimate'
    And I verify 'Number' of Cost Allocation Dialog page is enabled, required, free text and has text '1.1'
    And I verify 'Type' of Cost Allocation Dialog page is enabled, required, drop down and has text 'WBS Element'
    And I click on 'Confirm' button
    And 'Cost Allocation' popUp is not displayed
    And 'CLINs' table contain row with following data:
      | Set                       | A                |
      | CLIN                     | 10000            |
      | Part Number               | LOT_SMOKETEST_01 |
      | Description               | LOT_SMOKETEST_01 |
      | Estimate                  | 1 Estimate       |
      | Cost Allocation           | 1.1 |
    And 'CLINs' table contain row with following data:
      | Set                       | A                |
      | CLIN                     | 10000            |
      | Part Number               | LOT_SMOKETEST_01 |
      | Description               | LOT_SMOKETEST_01 |
      | Estimate                  | 1 Estimate       |
      | Cost Allocation           | LOT_SMOKETEST_01 |
    And info: ---18. For CLIN 10001 select Create New and Confirm. Verify popup shows Number = 1.2 and Cost Allocation = 1.2 LOT_SMOKETEST_01.---
    And I click 'Cost Allocation' in table 'CLINs' table for row with data:
      | CLIN | 10001 |
    And I click on 'Cell dropdown' arrow
    And 'Cell' dropdown is displayed
    And I click on 'Create New' button
    And 'Cost Allocation' popUp is displayed
    And I wait for 15 seconds
    And I click on 'Confirm' button
    And 'Cost Allocation' popUp is not displayed
    And 'CLINs' table contain row with following data:
      | Set                       | B                |
      | CLIN                     | 10001            |
      | Part Number               | LOT_SMOKETEST_01 |
      | Description               | LOT_SMOKETEST_01 |
      | Estimate                  | 1 Estimate       |
      | Cost Allocation           | 1.2 |
    And 'CLINs' table contain row with following data:
      | Set                       | B                |
      | CLIN                     | 10001            |
      | Part Number               | LOT_SMOKETEST_01 |
      | Description               | LOT_SMOKETEST_01 |
      | Estimate                  | 1 Estimate       |
      | Cost Allocation           | LOT_SMOKETEST_01 |
    And info: ---19. For CLIN 10002 select Create New and Confirm. Verify popup shows Number = 1.3 and Cost Allocation = 1.3 LOT_SMOKETEST_01.---
    And I click 'Cost Allocation' in table 'CLINs' table for row with data:
      | CLIN | 10002 |
    And I click on 'Cell dropdown' arrow
    And 'Cell' dropdown is displayed
    And I click on 'Create New' button
    And 'Cost Allocation' popUp is displayed
    And I wait for 15 seconds
    And I click on 'Confirm' button
    And 'Cost Allocation' popUp is not displayed
    And 'CLINs' table contain row with following data:
      | Set                       | C                |
      | CLIN                     | 10002            |
      | Part Number               | LOT_SMOKETEST_01 |
      | Description               | LOT_SMOKETEST_01 |
      | Estimate                  | 1 Estimate       |
      | Cost Allocation           | 1.3 |
    And 'CLINs' table contain row with following data:
      | Set                       | C                |
      | CLIN                     | 10002            |
      | Part Number               | LOT_SMOKETEST_01 |
      | Description               | LOT_SMOKETEST_01 |
      | Estimate                  | 1 Estimate       |
      | Cost Allocation           | LOT_SMOKETEST_01 |
    And I click on 'Save' button

  @ART-203 @LM-TC-002_4 @END
  Scenario: TC-Production-or-System-Proposal-009: Import Multiple BOMs
    And info: ---1. Click on gear icon [⚙️] above the CLINs grid. Verify menu options are displayed.---
    And I click on 'Group Gear' menu
    Then 'Group Gear' options is displayed
    And I click on 'CLINs' tab
    Then 'Group Gear' options is not displayed
    And info: ---2. Select “Import Multiple BOMs.” Verify that the popup “Select Contract Lines to Import” is displayed.---
    When I choose in 'Group Gear' menu the next menu chain:
      | Import Multiple BOMs |
    And 'Select Contract Lines to Import' popUp is displayed
    And I wait for 5 seconds
    And info: ---3. Tick the top row checkbox. Verify all records are selected.---
    When I click on 'Top row' checkbox
    And I wait for 3 seconds
    Then I verify that all rows in 'Select Contract Lines to Import' table are selected
    And info: ---4. Click the “Import BOMs” button. Verify popup collapses and notifications are displayed.---
    When I click on 'Import BOMs' button
    And 'Select Contract Lines to Import' popUp is not displayed
    Then 'Wait for Importing BOMs to complete' message is displayed
    Then 'BOMs imported successfully' message is displayed_-Warning-_
    And I wait for 5 seconds
    And info: ---5. Click on the Estimate hyperlink where CLIN = 10000. Verify a new browser tab opens and user is redirected to Estimates app.---
    When I click 'Estimate Hyperlink' in table 'CLINs' table for row with data:
      | CLIN | 10000 |
    Then page with name 'CLINs page' is opened
    Then I switch to tab 2
    And I click on 'Proposal BOM' tab
    And page with name 'Estimate Proposal BOM' is opened