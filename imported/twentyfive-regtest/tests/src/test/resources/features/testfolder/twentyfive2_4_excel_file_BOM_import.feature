@excelBOMImport-2-4
Feature: 9. Excel File BOM Import Scenario

  @TC-X-BOM-001
  Scenario: TC-BOM-Import-Excel-001: Import BOM from File
    Given open site
    And I perform Mfg 2.4 login
    Then page with name 'Main page' is opened
    Given generate values and store into the variables:
      | $uniqueProposalName | TEST EXCEL BOM IMPORT ${CUR_DATE,yyyy-MM-dd hh:mm} |
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
    And info: ---13. Click Save ---
    And I click on 'Save' button
    And I wait for 5 seconds
    And page with name 'Line Items page' is opened
    And I click on 'Update Cost & Prices' link
    And I wait for 15 seconds
    And page with name 'Line Items page' is opened
    And info: ---14. Navigate to Cost Structure tab and Open the Estimate ---
    And I click on 'Cost Structure' tab
    And page with name 'Cost Structure page' is opened
    And I click 'Open' in table 'WBS' table for row with data:
      | Cost Structure             | 1 Test Phase 1  |
    Then I switch to tab 2
    And page with name 'Estimate Labor' is opened
    And info: ---15. Go to the Proposal BOM tab ---
    And I click on 'Proposal BOM' tab
    And page with name 'Estimate Proposal BOM' is opened
    And info: ---16. Click Upload button near the right-top of the grid so that Popup Upload a List of Objects is displayed ---
    And click on 'BOM Upload' button
    And 'Upload a List of Objects' dialog is displayed
    And info: ---17. Select file from preconditions:  TC-BOM-Import-Excel-File.xlsx ---
    And I import BOM file 'TC-BOM-Import-Excel-File.xlsx'
    And 'Bill of Material was updated successfully' notification is displayed
    And page with name 'Estimate Proposal BOM' is opened
    And info: ---18. Verify REG_MATERIAL_BOM row is displayed in the grid.---
    When I select 'Regression Test - Manufacturing' in the 'View' dropdown
    And page with name 'Estimate Proposal BOM' is opened
    Then text of 'View' dropdown equals 'View: Regression Test - Manufacturing'
    And 'Proposal BOM' table contain row with following data:
      | Node                 | 1                |
      | Part Number          | REG_MATERIAL_BOM |
    And info: ---19. Expand REG_MATERIAL_BOM. Verify rows REG_MATERIAL_BOM_ITEM_1 and REG_MATERIAL_BOM_ITEM_2 appear.---
    And I click 'Expand' in table 'Proposal BOM' table for row with data:
      | Node                 | 1                |
      | Part Number          | REG_MATERIAL_BOM |
    And I wait for 5 seconds
    And 'Proposal BOM' table contain row with following data:
      | Part Number          | REG_MATERIAL_BOM_ITEM_1 |
    And 'Proposal BOM' table contain row with following data:
      | Part Number          | REG_MATERIAL_BOM_ITEM_2 |
    And info: ---20. Verify Node values: REG_MATERIAL_BOM = 1, ITEM_1 = 1.1, ITEM_2 = 1.2.---
    And 'Proposal BOM' table contain row with following data:
      | Node                 | 1.1              |
      | Part Number          | REG_MATERIAL_BOM_ITEM_1 |
    And 'Proposal BOM' table contain row with following data:
      | Node                 | 1.2              |
      | Part Number          | REG_MATERIAL_BOM_ITEM_2 |
    And info: ---21. Verify Description values: REG_MATERIAL_BOM, REG_MATERIAL_BOM_ITEM_1, REG_MATERIAL_BOM_ITEM_2.---
    And 'Proposal BOM' table contain row with following data:
      | Part Number          | REG_MATERIAL_BOM |
      | Description          | REG_MATERIAL_BOM |
    And 'Proposal BOM' table contain row with following data:
      | Part Number          | REG_MATERIAL_BOM_ITEM_1 |
      | Description          | REG_MATERIAL_BOM_ITEM_1 |
    And 'Proposal BOM' table contain row with following data:
      | Part Number          | REG_MATERIAL_BOM_ITEM_2 |
      | Description          | REG_MATERIAL_BOM_ITEM_2 |
    And info: ---22. Verify Estimating Method: BOM = Mfg Routing Priority, ITEM_1 = Purchased Part (Vendor Quotes/LTAs/POs), ITEM_2 = Mfg Routing Priority.---
    And 'Proposal BOM' table contain row with following data:
      | Part Number          | REG_MATERIAL_BOM |
      | Estimating Method    | Mfg Part - Routing Priority |
    And 'Proposal BOM' table contain row with following data:
      | Part Number          | REG_MATERIAL_BOM_ITEM_1 |
      | Estimating Method    | Purchased Part - Vendor Quotes, LTAs & POs |
    And 'Proposal BOM' table contain row with following data:
      | Part Number          | REG_MATERIAL_BOM_ITEM_2 |
      | Estimating Method    | Mfg Part - Routing Priority |
    And info: ---23. Verify Supply Site for all rows = RG10 US - New York.---
    And 'Proposal BOM' table contain row with following data:
      | Part Number          | REG_MATERIAL_BOM |
      | Supply Site          | RG10 US - New York |
    And 'Proposal BOM' table contain row with following data:
      | Part Number          | REG_MATERIAL_BOM_ITEM_1 |
      | Supply Site          | RG10 US - New York |
    And 'Proposal BOM' table contain row with following data:
      | Part Number          | REG_MATERIAL_BOM_ITEM_2 |
      | Supply Site          | RG10 US - New York |
    And info: ---24. Verify WBS for all rows = 1.1 Test WBS 1.---
    And 'Proposal BOM' table contain row with following data:
      | Part Number          | REG_MATERIAL_BOM |
      | WBS                  | 1.1 Test WBS 1 |
    And 'Proposal BOM' table contain row with following data:
      | Part Number          | REG_MATERIAL_BOM_ITEM_1 |
      | WBS                  | 1.1 Test WBS 1 |
    And 'Proposal BOM' table contain row with following data:
      | Part Number          | REG_MATERIAL_BOM_ITEM_2 |
      | WBS                  | 1.1 Test WBS 1 |
    And info: ---25. Verify Lead Time: BOM = 65d, ITEM_1 = 15d, ITEM_2 = 20d.---
    And 'Proposal BOM' table contain row with following data:
      | Part Number          | REG_MATERIAL_BOM    |
      | Lead Time            | 65                  |
    And 'Proposal BOM' table contain row with following data:
      | Part Number          | REG_MATERIAL_BOM_ITEM_1 |
      | Lead Time            | 15                      |
    And 'Proposal BOM' table contain row with following data:
      | Part Number          | REG_MATERIAL_BOM_ITEM_2 |
      | Lead Time            | 20                      |
    And info: ---26. Verify Purchased checkbox: BOM unchecked, ITEM_1 checked, ITEM_2 unchecked.---
    And the cell value of the 'Purchased' column of the 'Proposal BOM' table is unchecked for a row with the following data:
      | Part Number          | REG_MATERIAL_BOM |
    And the cell value of the 'Purchased' column of the 'Proposal BOM' table is checked for a row with the following data:
      | Part Number          | REG_MATERIAL_BOM_ITEM_1 |
    And the cell value of the 'Purchased' column of the 'Proposal BOM' table is unchecked for a row with the following data:
      | Part Number          | REG_MATERIAL_BOM_ITEM_2 |
    And info: ---27. Verify Qty in NHA: BOM = 1 ea, ITEM_1 = 2 ea, ITEM_2 = 2 ea.---
    And 'Proposal BOM' table contain row with following data:
      | Part Number          | REG_MATERIAL_BOM |
      | Qty in NHA           | 1 ea             |
    And 'Proposal BOM' table contain row with following data:
      | Part Number          | REG_MATERIAL_BOM_ITEM_1 |
      | Qty in NHA           | 2 ea             |
    And 'Proposal BOM' table contain row with following data:
      | Part Number          | REG_MATERIAL_BOM_ITEM_2 |
      | Qty in NHA           | 2 ea             |
    And info: ---28. Verify Complexity Factor: BOM = 1.00, ITEM_1 = 2.00, ITEM_2 = 3.00.---
    And 'Proposal BOM' table contain row with following data:
      | Part Number          | REG_MATERIAL_BOM |
      | Complexity Factor    | 1.00             |
    And 'Proposal BOM' table contain row with following data:
      | Part Number          | REG_MATERIAL_BOM_ITEM_1 |
      | Complexity Factor    | 2.00             |
    And 'Proposal BOM' table contain row with following data:
      | Part Number          | REG_MATERIAL_BOM_ITEM_2 |
      | Complexity Factor    | 3.00             |
    And page with name 'Estimate Proposal BOM' is opened
    And info: ---29. Open remarks for REG_MATERIAL_BOM. Verify text = “Remark 1”.---
    And I click 'Remark' in table 'Proposal BOM' table for row with data:
      | Part Number          | REG_MATERIAL_BOM |
    Then 'Remarks' dialog is displayed
    And text of 'Remarks' iframe equals 'Remark 1'
    And I click on 'Close' button
    And 'Remarks' dialog is not displayed
    And page with name 'Estimate Proposal BOM' is opened
    And info: ---30. Open remarks for REG_MATERIAL_BOM_ITEM_1. Verify text = “Remark 2”.---
    And I click 'Remark' in table 'Proposal BOM' table for row with data:
      | Part Number          | REG_MATERIAL_BOM_ITEM_1 |
    Then 'Remarks' dialog is displayed
    And text of 'Remarks' iframe equals 'Remark 2'
    And I click on 'Close' button
    And 'Remarks' dialog is not displayed
    And page with name 'Estimate Proposal BOM' is opened
    And info: ---31. Open remarks for REG_MATERIAL_BOM_ITEM_2. Verify text = “Remark 3”.---
    And I click 'Remark' in table 'Proposal BOM' table for row with data:
      | Part Number          | REG_MATERIAL_BOM_ITEM_2 |
    Then 'Remarks' dialog is displayed
    And text of 'Remarks' iframe equals 'Remark 3'
    And I click on 'Close' button
    And 'Remarks' dialog is not displayed
    And page with name 'Estimate Proposal BOM' is opened