@basicImportBOM-2-4
Feature: 8. SAP BOM Import Scenario

  @TC-BOM-001 @START
  Scenario: TC-BOM-Import-001: Basic BOM Import
    Given open site
    And I perform Mfg 2.4 login
    Then page with name 'Main page' is opened
    Given generate values and store into the variables:
      | $uniqueProposalName | TEST BASIC BOM IMPORT ${CUR_DATE,yyyy-MM-dd hh:mm} |
    And info: ---1. Start creating a new Proposal with Type “Regression Test Only – Manufacturing.” Verify that Setup tab opens.---
    Given I click on 'PROPOSALS' tab
    Then page with name 'Proposals list page' is opened
    Then click on 'New' button
    And page with name 'Setup page' is opened
    When I enter data into the next fields:
      | 'Description' field   | $uniqueProposalName |
    And page with name 'Setup page' is opened
    When I enter data into the next fields:
      | 'Proposal Type' field | Regression Test Only - Manufacturing |
    And page with name 'Setup page' is opened
    And info: ---2. Fill all required fields. Set Estimated Project Start = 4/1/2025 and Project End = 3/31/2026. Verify all required fields are populated.---
    When I enter data into the next fields:
      | 'Planned Start' field | 4/1/2025            |
    And page with name 'Setup page' is opened
    When I enter data into the next fields:
      | 'End' field                  | 3/31/2026          |
    And page with name 'Setup page' is opened
    When I enter data into the next fields:
      | 'Your Company' field            | Regression Test |
    And page with name 'Setup page' is opened
    When I enter data into the next fields:
      | 'Leading Department' field            | US - New York   |
    And page with name 'Setup page' is opened
    When I enter data into the next fields:
      | 'Client Customer sell-to' field | Regression Test - Customer USD |
    And page with name 'Setup page' is opened
    And info: ---3. Ensure “Select a template” radio button is selected. Choose template “(AS) Regression - Labor Manual Effort & Inflation - Template.” Verify dropdown accepts value.---
    And I check 'Select a template' radio
    When I enter data into the next fields:
      | 'Search for Template from Library' field | (AS) Regression - Labor Manual Effort & Inflation - Template |
    And page with name 'Setup page' is opened
    And I press escape
    And page with name 'Setup page' is opened
    And info: ---4. Uncheck “Labor estimates (3 hrs)” and “Material estimates (3 parts).” Verify both checkboxes become unchecked.---
    And I uncheck 'Labor estimates' checkbox
    And page with name 'Setup page' is opened
    And I uncheck 'Material estimates' checkbox
    And page with name 'Setup page' is opened
    And info: ---5. Click Copy. Verify data is saved and page reloads.---
    And click on 'Copy' button
    And page with name 'Setup page' is opened
    And info: ---6. Navigate to Pricing tab. Select “Regression Test - Bill Rates” for Labor/Item Rates. Verify value is accepted.---
    Then I click on 'Pricing' tab
    And page with name 'Pricing page' is opened
    When I enter data into the next fields:
      | 'How are Labor/Item Rates Calculated' field | Regression Test - Bill Rates |
    And page with name 'Pricing page' is opened
    And info: ---7. Click Save. Verify data is saved successfully.---
    When I click on 'Save' button
    And I wait for 5 seconds
#    And 'Data saved successfully' warning message is displayed in 30 seconds
    And page with name 'Pricing page' is opened
    And info: ---8. Navigate to Line Items tab. Verify view “Regression Test - Manufacturing” is selected and “No records found, click here to add” button is visible.---
    And I click on 'Line Items' tab
    And page with name 'Line Items page' is opened
    When I select 'Regression Test - Manufacturing' in the 'View' dropdown
    And page with name 'Line Items page' is opened
    Then text of 'View' dropdown equals 'View: Regression Test - Manufacturing'
    And info: ---9. Click “No records found, click here to add.” Verify a new row is added to the Deliverables Grid.---
    When I click on 'No records found, click here to add' button
    And number of rows in 'Line Items' table equals 1
    And info: ---10. Select the Part Number Test Data value. Verify Description = REG_MATERIAL_BOM (link), Estimate = 1 Test Phase 1, Cost BOM = Select WBS, Supply Site = RG10 US - New York.---
    When I set value 'REG_MATERIAL_BOM' to the cell of the column 'Part Number' of the 'Line Items' table for the row with the following data:
      | Item | 1 |
    And page with name 'Line Items page' is opened
    And 'Line Items' table contain row with following data:
      | Item                 | 1                |
      | Description         | REG_MATERIAL_BOM |
      | Estimate            | 1 Test Phase 1   |
      | Cost BOM            | Select WBS       |
      | Supply Site         | RG10 US - New York |
    And info: ---11. In Cost Allocation field, select Test Data value. Verify Cost BOM updates to “Import BOM” and WBS becomes “Test WBS 1.”---
    When I set value '1.1 Test WBS 1' to the cell of the column 'Cost Allocation' of the 'Line Items' table for the row with the following data:
      | Item | 1 |
    And page with name 'Line Items page' is opened
    And 'Line Items' table contain row with following data:
      | Item                 | 1                |
      | Description          | REG_MATERIAL_BOM |
      | Estimate             | 1 Test Phase 1   |
      | Supply Site          | RG10 US - New York |
      | Cost Allocation      | 1.1 Test WBS 1|
      | Cost BOM             | Import BOM |
    And info: ---12. Select Test Data value for End of Delivery. Verify value = 7/21/2025.---
    When I set value '7/21/2025' to the cell of the column 'End of Delivery' of the 'Line Items' table for the row with the following data:
      | Item | 1 |
    And I click on 'Save' button
    And I wait for 5 seconds
    And page with name 'Line Items page' is opened
    And I click on 'Update Cost & Prices' link
    And I wait for 15 seconds
    And info: ---13. Click “Import BOM” in Cost BOM field. Verify Import completes, data saves, and Cost BOM updates to “Re-import BOM.”---
    And I click 'Import BOM' in table 'Line Items' table for row with data:
      | Item | 1 |
    And page with name 'Line Items page' is opened
    And I wait for 25 seconds
    And 'Line Items' table contain row with following data:
      | Item                 | 1                |
      | Description          | REG_MATERIAL_BOM |
      | Estimate             | 1 Test Phase 1   |
      | Supply Site          | RG10 US - New York |
      | Cost Allocation      | 1.1 Test WBS 1       |
      | Cost BOM             | Re-import BOM    |
    And info: ---14. Click the Open icon in the Estimate field. Verify Estimate opens in a new browser tab.---
    And I click 'Open' in table 'Line Items' table for row with data:
      | Item | 1 |
    And page with name 'Line Items page' is opened
    Then I switch to tab 2
    And I click on 'Labor' tab
    When I select 'Regression Test - Manufacturing' in the 'View' dropdown
    And page with name 'Estimate Labor' is opened
    And info: ---15. Navigate to Proposal BOM tab. Verify view “Regression Test - Manufacturing” is selected and REG_MATERIAL_BOM appears in the grid.---
    And I click on 'Proposal BOM' tab
    And page with name 'Estimate Proposal BOM' is opened
    When I select 'Regression Test - Manufacturing' in the 'View' dropdown
    And page with name 'Estimate Proposal BOM' is opened
    Then text of 'View' dropdown equals 'View: Regression Test - Manufacturing'
    And 'Proposal BOM' table contain row with following data:
      | Part Number         | REG_MATERIAL_BOM |
      | Description          | REG_MATERIAL_BOM |
      | Estimating Method   | Mfg Part - Routing Priority |
    And info: ---16. Expand REG_MATERIAL_BOM row. Verify sub-level items display: REG_MATERIAL_BOM_ITEM_1 and REG_MATERIAL_BOM_ITEM_2.---
    When I click 'Expand' in table 'Proposal BOM' table for row with data:
      | Description | REG_MATERIAL_BOM |
    And I wait for 7 seconds
    And 'Proposal BOM' table contain row with following data:
      | Part Number          | REG_MATERIAL_BOM_ITEM_1 |
      | Description          | REG_MATERIAL_BOM_ITEM_1    |
    And 'Proposal BOM' table contain row with following data:
      | Part Number          | REG_MATERIAL_BOM_ITEM_2 |
      | Description          | REG_MATERIAL_BOM_ITEM_2    |
    And info: ---17. For REG_MATERIAL_BOM row verify: Node = 1, Description = REG_MATERIAL_BOM, Estimating Method = Mfg Part - Routing Priority, Supply Site = US - New York, WBS = 1.1 Test WBS 1, Lead Time = 65d, Proposal Qty = 1 ea, Purchased = unchecked.---
    And the cell value of the 'Purchased' column of the 'Proposal BOM' table is unchecked for a row with the following data:
      | Node               | 1                     |
      | Description        | REG_MATERIAL_BOM      |
      | Estimating Method  | Mfg Part - Routing Priority |
      | Supply Site       | US - New York        |
      | Lead Time         | 65.00 d                  |
      | Proposal Qty      | 1 ea                 |
#      | WBS                | 1.1 Test WBS 1       |
    And 'Proposal BOM' table contain row with following data_-Warning-_:
      | Node               | 1                     |
      | Description        | REG_MATERIAL_BOM      |
      | WBS                | 1.1 Test WBS 1       |
    And info: ---18. For REG_MATERIAL_BOM_ITEM_1 row verify: Node = 1.2, Description = REG_MATERIAL_BOM_ITEM_1, Estimating Method = Purchased Part - Vendor Quotes/LTAs/POs, Supply Site = US - New York, WBS = 1.1 Test WBS 1, Lead Time = 15d, Proposal Qty = 2 ea, Purchased = checked.---
    And the cell value of the 'Purchased' column of the 'Proposal BOM' table is checked for a row with the following data:
      | Node               | 1.2                     |
      | Description        | REG_MATERIAL_BOM_ITEM_1 |
      | Estimating Method  | Purchased Part - Vendor Quotes, LTAs & POs |
      | Supply Site        | US - New York           |
      | Lead Time          | 15.00 d                 |
      | Proposal Qty       | 2 ea                    |
#      | WBS                | 1.1 Test WBS 1          |
    And 'Proposal BOM' table contain row with following data_-Warning-_:
      | Node               | 1.2                     |
      | Description        | REG_MATERIAL_BOM_ITEM_1 |
      | WBS                | 1.1 Test WBS 1          |
    And info: ---19. For REG_MATERIAL_BOM_ITEM_2 row verify: Node = 1.3, Description = REG_MATERIAL_BOM_ITEM_2, Estimating Method = Mfg Part - Routing Priority, Supply Site = US - New York, WBS = 1.1 Test WBS 1, Lead Time = 20d, Proposal Qty = 2 ea, Purchased = unchecked.---
    And the cell value of the 'Purchased' column of the 'Proposal BOM' table is unchecked for a row with the following data:
      | Node               | 1.3                     |
      | Description        | REG_MATERIAL_BOM_ITEM_2 |
      | Estimating Method  | Mfg Part - Routing Priority |
      | Supply Site        | US - New York        |
      | Lead Time          | 20.00 d              |
      | Proposal Qty       | 2 ea                 |
#      | WBS                | 1.1 Test WBS 1       |
    And 'Proposal BOM' table contain row with following data_-Warning-_:
      | Node               | 1.3                     |
      | Description        | REG_MATERIAL_BOM_ITEM_2 |
      | WBS                | 1.1 Test WBS 1          |
  @TC-BOM-002 @RUN
  Scenario: TC-BOM-Import-002: Cost Imported BOM
    And info: ---1. Click the More menu in the the right-top corner of the screen ---
    And I click on 'Proposal BOM' tab
    And page with name 'Estimate Proposal BOM' is opened
    Then I click on 'Hamburger' menu
    And info: ---2. Select Update & Cost Consolidated BOM > All parts ---
    Then I choose in 'Hamburger' menu the next menu chain:
      | Update & Cost Consolidated BOM |
      | All parts                      |
    And 'Consolidate and Cost Bill of Material' dialog is displayed
    And info: ---3. Click Purchase History tab ---
    And I click on 'Purchase History' tab
    And 'Cost purchased and subcontract parts' radio is selected
    And 'Exclude manual purchase history' radio is not selected
    And 'Only consider current valid quotes or contracts' radio is not selected
    And 'Escalate based on commitment' radio is not selected
    And 'Disable all quantity curving' radio is not selected
    And info: ---4. Click Production History tab ---
    And I click on 'Production History' tab
    Then 'Cost make parts' radio is selected
    And I verify 'Exclude Prod. Order Types from History' of Billing Item Dialog page is enabled, not required, drop down and is empty
    And I verify 'Exclude Operation Control Keys from History' of Billing Item Dialog page is enabled, not required, drop down and is empty
    And I verify 'Distribution Make Part Labor > 1 Month' of Billing Item Dialog page is enabled, not required, drop down and is empty
    And info: ---5. Click Advanced Options tab ---
    And I click on 'Advanced Options' tab
    And I verify 'Per product/ service' of Billing Item Dialog page is enabled, not required, radio buttons and is checked
    And I verify 'Per lead- time offsets' of Billing Item Dialog page is enabled, not required, radio buttons and is unchecked
    And I verify 'To earliest requirement' of Billing Item Dialog page is enabled, not required, radio buttons and is unchecked
    And I verify 'Curved to estimate' of Billing Item Dialog page is enabled, not required, radio buttons and is unchecked
    And I verify 'Per assigned task' of Billing Item Dialog page is enabled, not required, radio buttons and is unchecked
    And I verify 'Override Inflation or Escalation Index' of Billing Item Dialog page is enabled, not required, drop down and is empty
    And I verify 'Include max. or optional sets in TINA thresholds' of Billing Item Dialog page is enabled, not required, check box and is unchecked
    And I verify 'Include commercial & current contract items in TINA' of Billing Item Dialog page is enabled, not required, check box and is unchecked
    And I verify 'Price quota sources based on full quantity' of Billing Item Dialog page is enabled, not required, check box and is unchecked
    And I verify 'Absolute TINA values (negative adjustment are positive)' of Billing Item Dialog page is enabled, not required, check box and is unchecked
    And I verify 'Escalate using Full Year Rates' of Billing Item Dialog page is enabled, not required, check box and is unchecked
    And I verify 'Extended logging' of Billing Item Dialog page is enabled, not required, check box and is unchecked
    And info: ---6. Click Run - All Parts button ---
    And I click on 'Run All Parts' button
    And 'Rolled-up the costed BOM' notification is displayed
    And 'Cost consolidated bill of material has completed and material estimates will be reloaded' notification is displayed
    Then 'Consolidate and Cost Bill of Material' dialog is not displayed
    And page with name 'Estimate Proposal BOM' is opened
    And info: ---7. Click Procurement & Production tab ---
    And I click on 'Procurement & Production' tab
    Then page with name 'Estimate Procurement & Production' is opened
    When I select 'Regression Test - Manufacturing' in the 'View' dropdown
    And I click on 'Save' button
    Then text of 'View' dropdown equals 'View: Regression Test - Manufacturing (shared & preferred)'
    And 'Procurement & Production' table contain row with following data:
      | Part Number | REG_MATERIAL_BOM |
    And 'Procurement & Production' table contain row with following data:
      | Part Number | REG_MATERIAL_BOM_ITEM_1 |
    And 'Procurement & Production' table contain row with following data:
      | Part Number | REG_MATERIAL_BOM_ITEM_2 |
    And info: ---8. Verify the Qty - Proposed or lot-size field ---
    And 'Procurement & Production' table contain row with following data:
      | Part Number | REG_MATERIAL_BOM |
      | Qty - Proposed or lot-size | 1 ea |
    And 'Procurement & Production' table contain row with following data:
      | Part Number | REG_MATERIAL_BOM_ITEM_1 |
      | Qty - Proposed or lot-size | 2 ea |
    And 'Procurement & Production' table contain row with following data:
      | Part Number                | REG_MATERIAL_BOM_ITEM_2 |
      | Qty - Proposed or lot-size | 2 ea |
    And info: ---9. Verify the Complexity Factor field ---
    And 'Procurement & Production' table contain row with following data:
      | Part Number       | REG_MATERIAL_BOM |
      | Complexity Factor | 1.00 |
    And 'Procurement & Production' table contain row with following data:
      | Part Number       | REG_MATERIAL_BOM_ITEM_1 |
      | Complexity Factor | 1.00 |
    And 'Procurement & Production' table contain row with following data:
      | Part Number       | REG_MATERIAL_BOM_ITEM_2 |
      | Complexity Factor | 1.00 |
    And info: ---10. Verify the Source Unit Cost field ---
    And 'Procurement & Production' table contain row with following data:
      | Part Number      | REG_MATERIAL_BOM |
      | Source Unit Cost | $ 13,350.00/ea   |
    And 'Procurement & Production' table contain row with following data:
      | Part Number      | REG_MATERIAL_BOM_ITEM_1 |
      | Source Unit Cost | $ 138,000.00/ea         |
    And 'Procurement & Production' table contain row with following data:
      | Part Number      | REG_MATERIAL_BOM_ITEM_2 |
      | Source Unit Cost | $ 22,250.00/ea          |
    And info: ---11. Verify the Start field ---
    And 'Procurement & Production' table contain row with following data:
      | Part Number | REG_MATERIAL_BOM |
      | Start       |     4/1/2025     |
    And 'Procurement & Production' table contain row with following data:
      | Part Number | REG_MATERIAL_BOM_ITEM_1 |
      | Start       | 6/30/2025               |
    And 'Procurement & Production' table contain row with following data:
      | Part Number | REG_MATERIAL_BOM_ITEM_2 |
      | Start       | 6/3/2025                |
    And info: ---12. Verify the End field ---
    And 'Procurement & Production' table contain row with following data:
      | Part Number | REG_MATERIAL_BOM |
      | End         | 3/31/2026        |
    And 'Procurement & Production' table contain row with following data:
      | Part Number | REG_MATERIAL_BOM_ITEM_1 |
      | End         | 6/30/2025               |
    And 'Procurement & Production' table contain row with following data:
      | Part Number | REG_MATERIAL_BOM_ITEM_2 |
      | End         | 6/23/2025               |

  @TC-BOM-003 @RUN
  Scenario: TC-BOM-Import-003: Check costed BOM items (purchased)
    And info: ---1. For the row REG_MATERIAL_BOM_ITEM_1, click the Gear icon [⚙️] and select Edit Details. Verify that the “Purchased For …” popup is displayed.---
    And I click on 'Procurement & Production' tab
    Then page with name 'Estimate Procurement & Production' is opened
    When I select 'Regression Test - Manufacturing' in the 'View' dropdown
    And I click on 'Save' button
    Then text of 'View' dropdown equals 'View: Regression Test - Manufacturing (shared & preferred)'
    And I hover 'Description' cell in table 'Procurement & Production' table and click ⚙️ for row with data:
      | Description | REG_MATERIAL_BOM_ITEM_1 |
    And I click on 'Edit Details' menuItem
    And page with name 'Purchased Part: Estimate - Cost Estimate' is opened
    And info: ---2. Verify that Source Doc REG_PURCHASE2/2 is checked as Use in the Purchase History grid.---
    And the cell value of the 'Use' column of the 'Cost Estimate' table is checked for a row with the following data:
      | Source Doc      | REG_PURCHASE2/2 |
    And info: ---3. Verify that the Source Unit Cost field is equal to $ 138,000.00/ea.---
    Then text of 'Source Unit Cost' field equals '138,000.00'
    And info: ---4. Remove the value from the Escalation Index field and verify it is empty. Verify that the Escalation Factor field is equal to 1.00.---
    When I enter data into the next fields:
      | 'Estimating Source' dropdown  |         |
    Then text of 'Estimating Source' dropdown equals ''
    When I enter data into the next fields:
      | 'Escalation Index' dropdown  |         |
    Then text of 'Escalation Index' dropdown equals ''
    When I enter data into the next fields:
      | 'Escalation Factor' field  |  1.00  |
    And text of 'Escalation Factor' field equals '1.00'
    And info: ---5. Set the value for QAF – Qty Adj Factor to the Test Data Value 2.00 and verify that the field is updated accordingly.---
    When I enter data into the next fields:
      | 'QAF Qty Adj Factor' field  |  2.00  |
    And I wait for 3 seconds
    And text of 'QAF Qty Adj Factor' field equals '2.00'
    And info: ---6. Verify that the Changed Adj Unit Cost field is equal to $ 276,000.00/ea.---
    Then text of 'Changed Adj Unit Cost' field equals '276,000.00'
    And info: ---7. Set the value for QAF – Qty Adj Factor to the Test Data Value 1.00 and verify that the field is updated accordingly.---
    When I enter data into the next fields:
      | 'QAF Qty Adj Factor' field  |  1.00  |
    And text of 'QAF Qty Adj Factor' field equals '1.00'
    And info: ---8. Verify that the Changed Adj Unit Cost field is equal to $ 138,000.00/ea.---
    And I wait for 3 seconds
    Then text of 'Changed Adj Unit Cost' field equals '138,000.00'
    And info: ---9. Verify that the Total Cost field is equal to $ 276,000.00.---
    Then text of 'Total Cost' field equals '276,000.00'
    And info: ---10. Click the Confirm button and verify that the Retain Changes popup is displayed.---
    And I click on 'Confirm' button
    Then 'Retain Changes' popup is displayed
    And info: ---11. Click No – Retain for Costing and verify that the “Data saved successfully” message is displayed.---
    And I click on 'No Retain for Costing' button
    And 'Data saved successfully' warning message is displayed in 30 seconds
    And info: ---12. Click Confirm again and verify that the Purchased For popup is closed.---
    And I click on 'Confirm' button
    Then page with name 'Estimate Procurement & Production' is opened
    And info: ---13. For the row REG_MATERIAL_BOM_ITEM_1, verify that the Qty – Proposed or lot-size field is equal to 2 ea and the Cost in Company Currency field is equal to $ 276,000.00.---
    And 'Procurement & Production' table contain row with following data:
      | Description                | REG_MATERIAL_BOM_ITEM_1 |
      | Qty - Proposed or lot-size | 2 ea                    |
      | Cost in Company Currency   | $ 276,000.00            |

  @TC-BOM-004 @RUN
  Scenario: TC-BOM-Import-004: Check costed BOM items (manufactured)
    And info: ---1. For the row REG_MATERIAL_BOM_ITEM_2, click the Gear icon [⚙️] and select Edit Details. Verify that the “Mfg Part …” popup is displayed and the Cost Breakdown tab is opened.---
    And I click on 'Procurement & Production' tab
    Then page with name 'Estimate Procurement & Production' is opened
    When I select 'Regression Test - Manufacturing' in the 'View' dropdown
    And I click on 'Save' button
    Then text of 'View' dropdown equals 'View: Regression Test - Manufacturing (shared & preferred)'
    And I hover 'Description' cell in table 'Procurement & Production' table and click ⚙️ for row with data:
      | Description | REG_MATERIAL_BOM_ITEM_2 |
    And I click on 'Edit Details' menuItem
    And 'Mfg Part' popUp is displayed
    And page with name 'Purchased Part: Estimate - Cost Breakdown' is opened
    And info: ---2. Select the view Regression Test - Manufacturing (BOM) if it is not already selected and verify that the grid is displayed according to the selected view. Verify that the grid contains the following values: Resource Group = REG_BOM_RESOURCE_1, Cost Element = Subcontractors, Work Center = QUALITY, Setup Hrs = 100.00 hours, Run Time = 200.00 hours, Rate/Hr = $ 89.00/hr, Total Time = 500.00 hours, and Total Cost = $ 44,500.00.---
    When I select 'Regression Test - Manufacturing (BOM)' in the 'Dialog View' dropdown
    Then text of 'Dialog View' dropdown equals 'View: Regression Test - Manufacturing (BOM) (shared & preferred)'
    And 'Cost Breakdown' table contain row with following data:
      | Resource Group | REG_BOM_RESOURCE_1 |
      | Cost Element   | Subcontractors     |
      | Work center    | QUALITY            |
      | Setup Hrs      | 100.00 hours       |
      | Run Time       | 200.00 hours       |
      | Rate/Hr        | $ 89.00/hr         |
      | Total time     | 500.00             |
      | Total Cost     | $ 44,500.00        |
    And info: ---3. Verify that Part Number = REG_MATERIAL_BOM_ITEM_2, Description = REG_MATERIAL_BOM_ITEM_2, Unit Cost = $ 17,800.00/ea (ignore decimals after 2 places), Estimating Source is empty, Supply Site = US - New York, Adj. Unit Cost = $ 22,250.00/ea (ignore decimals after 2 places), Total Cost = $ 44,500.00, Total Setup Hours = 100.00 hours, Sum of Run Hrs/Unit = 200.00 hours, Realization Factor = 400.00, and Total Effort = 500.00 hours.---
    And I verify 'Part Number' of Cost Breakdown Dialog Table page is enabled, not required, free text and has text 'REG_MATERIAL_BOM_ITEM_2'
    And I verify 'Description' of Cost Breakdown Dialog Table page is enabled, not required, free text and has text 'REG_MATERIAL_BOM_ITEM_2'
    And I verify 'Unit Cost' of Cost Breakdown Dialog Table page is enabled, not required, free text and has text '17800'
#    And I verify 'Estimating Source' of Cost Breakdown Dialog Table page is enabled, not required, drop down and has text ''
    And I verify 'Supply Site' of Cost Breakdown Dialog Table page is enabled, required, drop down and has text 'US - New York'
    And I verify 'Adj Unit Cost' of Cost Breakdown Dialog Table page is enabled, not required, free text and has text '22250'
    And I verify 'Total Cost' of Cost Breakdown Dialog Table page is enabled, not required, free text and has text '44500'
    And I verify 'Total Setup Hours' of Cost Breakdown Dialog Table page is enabled, not required, free text and has text '100.00 hours'
    And I verify 'Sum of Run Hrs/Unit' of Cost Breakdown Dialog Table page is enabled, not required, free text and has text '200.00 hours'
    And I verify 'Realization Factor' of Cost Breakdown Dialog Table page is enabled, not required, free text and has text '400.00'
    And I verify 'Total Effort' of Cost Breakdown Dialog Table page is enabled, not required, free text and has text '500.00 hours'
    And info: ---4. Click the Confirm button and verify that the Mfg Part … popup is closed.---
    And I click on 'Confirm' button
    Then page with name 'Estimate Procurement & Production' is opened
    And 'Mfg Part' popUp is not displayed
    And info: ---5. For the row REG_MATERIAL_BOM_ITEM_2, verify that the Qty – Proposed or lot-size field is equal to 2 ea and the Cost in Company Currency field is equal to $ 44,500.00.---
    And 'Procurement & Production' table contain row with following data:
      | Description                | REG_MATERIAL_BOM_ITEM_2 |
      | Qty - Proposed or lot-size | 2 ea                    |
      | Cost in Company Currency   | $ 44,500.00             |
    And info: ---6. For the row REG_MATERIAL_BOM, verify that the Qty – Proposed or lot-size field is equal to 1 ea and the Cost in Company Currency field is equal to $ 13,350.00.---
    And 'Procurement & Production' table contain row with following data:
      | Description                | REG_MATERIAL_BOM |
      | Qty - Proposed or lot-size | 1 ea             |
      | Cost in Company Currency   | $ 13,350.00      |

  @TC-BOM-005 @END
  Scenario: TC-BOM-Import-005: Modify costed BOM items
    And info: ---1. For the row REG_MATERIAL_BOM_ITEM_1, click the Gear icon [⚙️] and select Edit Details. Verify that the “Purchased For …” popup is displayed.---
    And I click on 'Procurement & Production' tab
    Then page with name 'Estimate Procurement & Production' is opened
    When I select 'Regression Test - Manufacturing' in the 'View' dropdown
    And I click on 'Save' button
    Then text of 'View' dropdown equals 'View: Regression Test - Manufacturing (shared & preferred)'
    And I hover 'Description' cell in table 'Procurement & Production' table and click ⚙️ for row with data:
      | Description | REG_MATERIAL_BOM_ITEM_1 |
    And I click on 'Edit Details' menuItem
    And 'Purchased Part' popup is displayed
    And page with name 'Purchased Part: Estimate - Cost Estimate' is opened
    And info: ---2. In the Purchase History grid, select Use for REG_PURCHASE1/1 and verify that REG_PURCHASE1/1 is selected as Use.---
    And I activate checkbox 'Use' in table 'Cost Estimate' table for row with data:
      | Source Doc          | REG_PURCHASE1/1 |
    And the cell value of the 'Use' column of the 'Cost Estimate' table is checked for a row with the following data:
      | Source Doc          | REG_PURCHASE1/1 |
    And info: ---3. Verify that Estimating Source = Purchase Orders (SAP), Source Description = NB REG_PURCHASE1 Custom Plastics, Inc., Escalation Index is empty, Escalation Factor = 1.00, Source Unit Cost = $ 122,000.00/ea, Complexity Factor = 1.00, and Decrement Factor = 1.00.---
    And I verify 'Estimating Source' of Cost Estimate Dialog Table page is enabled, not required, drop down and has text 'Purchase Orders (SAP)'
    And I verify 'Source Description' of Cost Estimate Dialog Table page is enabled, not required, free text and has text 'NB REG_PURCHASE1 Custom Plastics, Inc.'
    And I verify 'Escalation Index' of Cost Estimate Dialog Table page is enabled, not required, drop down and is empty
    And I verify 'Escalation Factor' of Cost Estimate Dialog Table page is enabled, not required, free text and has text '1.00'
    And I verify 'Source Unit Cost' of Cost Estimate Dialog Table page is enabled, not required, free text and has text '122,000.00'
    And I verify 'Complexity Factor' of Cost Estimate Dialog Table page is enabled, not required, free text and has text '1.00'
    And I verify 'Decrement Factor' of Cost Estimate Dialog Table page is enabled, not required, free text and has text '1.00'
    And info: ---4. Set QAF – Qty Adj Factor to 2.00 and verify that Changed Adj Unit Cost = $ 244,000.00/ea and Total Cost = $ 488,000.00.---
    When I enter data into the next fields:
      | 'QAF Qty Adj Factor' field  |  2.00  |
    And I verify 'QAF – Qty Adj Factor' of Cost Estimate Dialog Table page is enabled, not required, free text and has text '2.00'
    And I verify 'Changed Adj Unit Cost' of Cost Estimate Dialog Table page is enabled, not required, free text and has text '244,000.00'
    And I verify 'Total Cost' of Cost Estimate Dialog Table page is enabled, not required, free text and has text '488,000.00'
    And info: ---5. Set Complexity Factor to 1.50 and verify that Changed Adj Unit Cost = $ 366,000.00/ea and Total Cost = $ 732,000.00.---
    When I enter data into the next fields:
      | 'Complexity Factor' field  |  1.50  |
    And I verify 'Complexity Factor' of Cost Estimate Dialog Table page is enabled, not required, free text and has text '1.50'
    And I verify 'Changed Adj Unit Cost' of Cost Estimate Dialog Table page is enabled, not required, free text and has text '366,000.00'
    And I verify 'Total Cost' of Cost Estimate Dialog Table page is enabled, not required, free text and has text '732,000.00'
    And info: ---6. Set Decrement Factor to 0.50 and verify that Changed Adj Unit Cost = $ 183,000.00/ea and Total Cost = $ 366,000.00.---
    When I enter data into the next fields:
      | 'Decrement Factor' field  |  0.50  |
    And I verify 'Decrement Factor' of Cost Estimate Dialog Table page is enabled, not required, free text and has text '0.50'
    And I verify 'Changed Adj Unit Cost' of Cost Estimate Dialog Table page is enabled, not required, free text and has text '183,000.00'
    And I verify 'Total Cost' of Cost Estimate Dialog Table page is enabled, not required, free text and has text '366,000.00'
    And info: ---7. Click Confirm and verify that the Purchased For … popup is closed.---
    When I click on 'Confirm' button
    And 'Purchased Part' popup is not displayed
    Then page with name 'Estimate Procurement & Production' is opened
    And info: ---8. For the row REG_MATERIAL_BOM_ITEM_1, verify that Complexity Factor = 1.50, Decrement Factor = 0.50, QAF = 2.00, Source Unit Cost = $ 122,000.00/ea, and Cost in Company Currency = $ 366,000.00.---
    When I select 'Regression Test - Manufacturing' in the 'View' dropdown
    And I click on 'Save' button
    Then text of 'View' dropdown equals 'View: Regression Test - Manufacturing (shared & preferred)'
    And 'Procurement & Production' table contain row with following data:
      | Description                | REG_MATERIAL_BOM_ITEM_1 |
      | Complexity Factor          | 1.50                    |
      | Decrement Factor           | 0.50                    |
      | QAF                        | 2.00                    |
      | Source Unit Cost           | 122,000.00              |
      | Cost in Company Currency   | 366,000.00              |
    And info: ---9. For the row REG_MATERIAL_BOM_ITEM_2, click the Gear icon [⚙️] and select Edit Details. Verify that the “Mfg Part …” popup is displayed and the Cost Breakdown tab is opened.---
    And I hover 'Description' cell in table 'Procurement & Production' table and click ⚙️ for row with data:
      | Description | REG_MATERIAL_BOM_ITEM_2 |
    And I click on 'Edit Details' menuItem
    And 'Mfg Part' popUp is displayed
    And page with name 'Mfg Part: Estimate - Cost Breakdown' is opened
    And info: ---10. In the Resource Grid, set Setup Hrs = 120.00 and verify that Total Setup Hours = 120.00, Adj Unit Cost = $ 23,140.00/ea (ignore decimals after 2 places), Total Cost = $ 46,280.00, Total Effort = 520.00 hours, Total Time (Grid) = 520.00 HR, and Total Cost (Grid) = $ 46,280.00.---
    And I set value '120.00' to the cell of the column 'Setup Hrs' of the 'Cost Breakdown' table for the row with the following data:
      | Resource Group | REG_BOM_RESOURCE_1 |
    And I verify 'Total Setup Hours' of Mfg Part: Estimate - Cost Breakdown Dialog Table page is enabled, not required, free text and has text '120.00 hours'
    And I verify 'Adj Unit Cost' of Mfg Part: Estimate - Cost Breakdown Dialog Table page is enabled, not required, free text and has text '23,140.00'
    And I verify 'Total Cost' of Mfg Part: Estimate - Cost Breakdown Dialog Table page is enabled, not required, free text and has text '46,280.00'
    And I verify 'Total Effort' of Mfg Part: Estimate - Cost Breakdown Dialog Table page is enabled, not required, free text and has text '520.00'
    And 'Cost Breakdown' table contain row with following data:
      | Resource Group | REG_BOM_RESOURCE_1 |
      | Setup Hrs      | 120.00             |
      | Total Time     | 520.00             |
      | Total Cost     | 46,280.00          |
    And info: ---11. In the Resource Grid, set Run Time = 240.00 and verify that Sum of Run Hrs/Unit = 240.00, Unit Cost = $ 21,360.00/ea (ignore decimals after 2 places), Adj Unit Cost = $ 26,700.00/ea (ignore decimals after 2 places), Realization Factor = 400.00, Total Cost = $ 53,400.00, Total Effort = 600.00 hours, Total Time (Grid) = 600.00 HR, and Total Cost (Grid) = $ 53,400.00.---
    And I set value '240.00' to the cell of the column 'Run Time' of the 'Cost Breakdown' table for the row with the following data:
      | Resource Group | REG_BOM_RESOURCE_1 |
    And I verify 'Sum of Run Hrs/Unit' of Mfg Part: Estimate - Cost Breakdown Dialog Table page is enabled, not required, free text and has text '240.00 hours'
    And I verify 'Unit Cost' of Mfg Part: Estimate - Cost Breakdown Dialog Table page is enabled, not required, free text and has text '21,360.00'
    And I verify 'Adj Unit Cost' of Mfg Part: Estimate - Cost Breakdown Dialog Table page is enabled, not required, free text and has text '26,700.00'
    And I verify 'Realization Factor' of Mfg Part: Estimate - Cost Breakdown Dialog Table page is enabled, not required, free text and has text '480.00'
    And I verify 'Total Cost' of Mfg Part: Estimate - Cost Breakdown Dialog Table page is enabled, not required, free text and has text '53,400.00'
    And I verify 'Total Effort' of Mfg Part: Estimate - Cost Breakdown Dialog Table page is enabled, not required, free text and has text '600.00 hours'
    And 'Cost Breakdown' table contain row with following data:
      | Resource Group | REG_BOM_RESOURCE_1 |
      | Run Time       | 240.00             |
      | Total time     | 600.00             |
      | Total Cost     | 53,400.00          |
    And info: ---12. Click Confirm and verify that the Mfg Part … popup is closed.---
    And I click on 'Confirm' button
    Then page with name 'Estimate Procurement & Production' is opened
    And 'Mfg Part' popUp is not displayed
    And I wait for 5 seconds
    And I click on 'Update Estimate totals' link
    And I wait for 20 seconds
    Then page with name 'Estimate Procurement & Production' is opened
    And I refresh page
    And I wait for 30 seconds
    Then page with name 'Estimate Procurement & Production' is opened
    And info: ---13. For the row REG_MATERIAL_BOM_ITEM_2, verify that Source Unit Cost = $ 26,700.00/ea and Cost in Company Currency = $ 53,400.00.---
    When I select 'Regression Test - Manufacturing' in the 'View' dropdown
    And I click on 'Save' button
    Then text of 'View' dropdown equals 'View: Regression Test - Manufacturing (shared & preferred)'
    And 'Procurement & Production' table contain row with following data:
      | Description                | REG_MATERIAL_BOM_ITEM_2 |
      | Source Unit Cost           | 26,700.00               |
      | Cost in Company Currency   | 53,400.00               |
    And info: ---14. For the row REG_MATERIAL_BOM, click the Gear icon [⚙️] and select Edit Details. Verify that the Mfg Part … popup is displayed, the Cost Breakdown tab is opened, and the Resource Grid contains Resource Group = REG_BOM_RESOURCE_1.---
    And I hover 'Description' cell in table 'Procurement & Production' table and click ⚙️ for row with data:
      | Description                | REG_MATERIAL_BOM |
    And I click on 'Edit Details' menuItem
    And 'Mfg Part' popUp is displayed
    And page with name 'Mfg Part: Estimate - Cost Breakdown' is opened
    When I select 'Regression Test - Manufacturing (BOM)' in the 'Dialog View' dropdown
    And I wait for 3 seconds
    Then text of 'Dialog View' dropdown equals 'View: Regression Test - Manufacturing (BOM) (shared & preferred)'
    And 'Cost Breakdown' table contain row with following data:
      | Resource Group | REG_BOM_RESOURCE_1 |
    And info: ---15. Click the “+” icon to add a new row and verify that a new row is added to the Resource Grid.---
    And I click '+' in table 'Cost Breakdown' table for row with data:
      | Resource Group | REG_BOM_RESOURCE_1 |
    And info: ---16. For the new row, select Resource Group = REG_CONSULTANT1 and verify that Cost Element = Consultants – Local and Rate/Hr = $ 120.00/hr.---
    And I set value 'REG_CONSULTANT1' to the cell of the column 'Resource Group' of the 'Cost Breakdown' table for the row with the following data:
      | Resource Group |   |
    And 'Cost Breakdown' table contain row with following data:
      | Resource Group  | REG_CONSULTANT1      |
      | Cost Element    | Consultants - Local  |
      | Rate/Hr         | 120.00               |
    And info: ---17. For the REG_CONSULTANT1 row, select Work Center = QUALITY.---
    And I set value 'QUALITY' to the cell of the column 'Work Center' of the 'Cost Breakdown' table for the row with the following data:
      | Resource Group | REG_CONSULTANT1      |
    And 'Cost Breakdown' table contain row with following data:
      | Resource Group  | REG_CONSULTANT1      |
      | Work Center     | QUALITY              |
    And info: ---18. For the REG_CONSULTANT1 row, set Run Time = 250 and verify that Total Time = 250.00 HR and Total Cost = $ 30,000.00.---
    And I set value '250' to the cell of the column 'Run Time' of the 'Cost Breakdown' table for the row with the following data:
      | Resource Group | REG_CONSULTANT1      |
    And 'Cost Breakdown' table contain row with following data:
      | Resource Group  | REG_CONSULTANT1      |
      | Total Time      | 250.00               |
      | Total Cost      | 30,000.00            |
    And info: ---19. Verify that Total Setup Hours = 50.00, Sum of Run Hrs/Unit = 350.00, Unit Cost = $ 38,900.00/ea (ignore decimals after 2 places), Adj Unit Cost = $ 43,350.00/ea (ignore decimals after 2 places), Realization Factor = 350.00, Total Cost = $ 43,350.00, and Total Effort = 400.00 hours.---
    And I verify 'Total Setup Hours' of Mfg Part: Estimate - Cost Breakdown Dialog Table page is enabled, not required, free text and has text '50.00 hours'
    And I verify 'Sum of Run Hrs/Unit' of Mfg Part: Estimate - Cost Breakdown Dialog Table page is enabled, not required, free text and has text '350.00 hours'
    And I verify 'Unit Cost' of Mfg Part: Estimate - Cost Breakdown Dialog Table page is enabled, not required, free text and has text '38,900.00'
    And I wait for 3 seconds
    And I verify 'Adj Unit Cost' of Mfg Part: Estimate - Cost Breakdown Dialog Table page is enabled, not required, free text and has text '43,350.00'
    And I verify 'Realization Factor' of Mfg Part: Estimate - Cost Breakdown Dialog Table page is enabled, not required, free text and has text '350.00'
    And I verify 'Total Cost' of Mfg Part: Estimate - Cost Breakdown Dialog Table page is enabled, not required, free text and has text '43,350.00'
    And I verify 'Total Effort' of Mfg Part: Estimate - Cost Breakdown Dialog Table page is enabled, not required, free text and has text '400.00 hours'
    And info: ---20. Click Confirm and verify that the Mfg Part … popup is closed.---
    And I click on 'Confirm' button
    Then 'Mfg Part' popUp is not displayed
    And I wait for 5 seconds
    And I click on 'Update Estimate totals' link
    And I wait for 20 seconds
    Then page with name 'Estimate Procurement & Production' is opened
    And I refresh page
    And I wait for 30 seconds
    Then page with name 'Estimate Procurement & Production' is opened
    And info: ---21. For the row REG_MATERIAL_BOM, verify that Source Unit Cost = $ 43,350.00/ea and Cost in Company Currency = $ 43,350.00.---
    And 'Procurement & Production' table contain row with following data:
      | Description                | REG_MATERIAL_BOM |
      | Source Unit Cost           | 43,350.00        |
      | Cost in Company Currency   | 43,350.00        |