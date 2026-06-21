# TC-BOM-Import-001 - Basic BOM Import

| Field | Value |
| --- | --- |
| Test ID | TC-BOM-Import-001 |
| Title | Basic BOM Import |
| RTA page | https://twenty5.atlassian.net/wiki/spaces/RTA/pages/427720705/BTP+Gold+TC-BOM-Import-001+Basic+BOM+Import |
| Map source | `RTA_TEST_SUITE_FEATURE_MAP.md` |

## Run Instructions

When a consultant asks to run this test, use the default app URL unless they provide another URL:

```text
https://approuter-twenty5ipe-dev.cfapps.us10.hana.ondemand.com/#quote
```

For Claude Desktop, ask before launching Selenium Chrome with the default URL. If the user provides another URL, launch that URL instead.

```bash
scripts/start-debug-chrome.sh "https://approuter-twenty5ipe-dev.cfapps.us10.hana.ondemand.com/#quote"
scripts/run-twentyfive-test.sh @TC-BOM-001
```

Duplicate feature matches exist for this Confluence page. The tag command may run more than one matching scenario until the canonical feature file is confirmed.

## Default mapped Selenium scenario

| Field | Value |
| --- | --- |
| Feature | 11. SAP BOM Import Scenario |
| Scenario | TC-BOM-Import-001: Basic BOM Import |
| Tags | `@TC-BOM-001 @START` |
| File | `imported/twentyfive-regtest/tests/src/test/resources/features/testfolder/basicBOMImport.feature:5` |

### Scenario Sections

#### Setup

- open site
- I perform login
- page with name 'Main page' is opened
- generate values and store into the variables:

#### 1. Start creating a new Proposal with Type "Regression Test Only - Manufacturing." Verify that Setup tab opens.

- I click on 'PROPOSALS' tab
- page with name 'Proposals list page' is opened
- click on 'New' button
- page with name 'Setup page' is opened
- I enter data into the next fields:
- page with name 'Setup page' is opened
- I enter data into the next fields:
- page with name 'Setup page' is opened

#### 2. Fill all required fields. Set Estimated Project Start = 4/1/2025 and Project End = 3/31/2026. Verify all required fields are populated.

- I enter data into the next fields:
- page with name 'Setup page' is opened
- I enter data into the next fields:
- page with name 'Setup page' is opened
- I enter data into the next fields:
- page with name 'Setup page' is opened
- I enter data into the next fields:
- page with name 'Setup page' is opened
- I enter data into the next fields:
- page with name 'Setup page' is opened

#### 3. Ensure "Select a template" radio button is selected. Choose template "(AS) Regression - Labor Manual Effort & Inflation - Template." Verify dropdown accepts value.

- I check 'Select a template' radio
- I enter data into the next fields:
- page with name 'Setup page' is opened
- I press escape
- page with name 'Setup page' is opened

#### 4. Uncheck "Labor estimates (3 hrs)" and "Material estimates (3 parts)." Verify both checkboxes become unchecked.

- I uncheck 'Labor estimates' checkbox
- page with name 'Setup page' is opened
- I uncheck 'Material estimates' checkbox
- page with name 'Setup page' is opened

#### 5. Click Copy. Verify data is saved and page reloads.

- click on 'Copy' button
- page with name 'Setup page' is opened

#### 6. Navigate to Pricing tab. Select "Regression Test - Bill Rates" for Labor/Item Rates. Verify value is accepted.

- I click on 'Pricing' tab
- page with name 'Pricing page' is opened
- I enter data into the next fields:
- page with name 'Pricing page' is opened

#### 7. Click Save. Verify data is saved successfully.

- I click on 'Save' button
- 'Data saved successfully' warning message is displayed in 30 seconds
- page with name 'Pricing page' is opened

#### 8. Navigate to Line Items tab. Verify view "Regression Test - Manufacturing" is selected and "No records found, click here to add" button is visible.

- I click on 'Line Items' tab
- page with name 'Line Items page' is opened
- I select 'Regression Test - Manufacturing' in the 'View' dropdown
- page with name 'Line Items page' is opened
- text of 'View' dropdown equals 'View: Regression Test - Manufacturing'

#### 9. Click "No records found, click here to add." Verify a new row is added to the Deliverables Grid.

- I click on 'No records found, click here to add' button
- number of rows in 'Line Items' table equals 1

#### 10. Select the Part Number Test Data value. Verify Description = REG_MATERIAL_BOM (link), Estimate = 1 Test Phase 1, Cost BOM = Select WBS, Supply Site = RG10 US - New York.

- I set value 'REG_MATERIAL_BOM' to the cell of the column 'Part Number' of the 'Line Items' table for the row with the following data:
- page with name 'Line Items page' is opened
- 'Line Items' table contain row with following data:

#### 11. In Cost Allocation field, select Test Data value. Verify Cost BOM updates to "Import BOM" and WBS becomes "Test WBS 1."

- I set value '1.1 Test WBS 1' to the cell of the column 'Cost Allocation' of the 'Line Items' table for the row with the following data:
- page with name 'Line Items page' is opened
- 'Line Items' table contain row with following data:

#### 12. Select Test Data value for End of Delivery. Verify value = 7/21/2025.

- I set value '7/21/2025' to the cell of the column 'End of Delivery' of the 'Line Items' table for the row with the following data:
- I click on 'Save' button
- I wait for 5 seconds
- page with name 'Line Items page' is opened
- I click on 'Update Cost & Prices' link
- I wait for 15 seconds

#### 13. Click "Import BOM" in Cost BOM field. Verify Import completes, data saves, and Cost BOM updates to "Re-import BOM."

- I click 'Import BOM' in table 'Line Items' table for row with data:
- page with name 'Line Items page' is opened
- I wait for 25 seconds
- 'Line Items' table contain row with following data:

#### 14. Click the Open icon in the Estimate field. Verify Estimate opens in a new browser tab.

- I click 'Open' in table 'Line Items' table for row with data:
- page with name 'Line Items page' is opened
- I switch to tab 2
- I click on 'Labor' tab
- page with name 'Estimate Labor' is opened

#### 15. Navigate to Proposal BOM tab. Verify view "Regression Test - Manufacturing" is selected and REG_MATERIAL_BOM appears in the grid.

- I click on 'Proposal BOM' tab
- page with name 'Estimate Proposal BOM' is opened
- I select 'Regression Test - Manufacturing' in the 'View' dropdown
- page with name 'Estimate Proposal BOM' is opened
- text of 'View' dropdown equals 'View: Regression Test - Manufacturing'
- 'Proposal BOM' table contain row with following data:

#### 16. Expand REG_MATERIAL_BOM row. Verify sub-level items display: REG_MATERIAL_BOM_ITEM_1 and REG_MATERIAL_BOM_ITEM_2.

- I click 'Expand' in table 'Proposal BOM' table for row with data:
- I wait for 7 seconds
- 'Proposal BOM' table contain row with following data:
- 'Proposal BOM' table contain row with following data:

#### 17. For REG_MATERIAL_BOM row verify: Node = 1, Description = REG_MATERIAL_BOM, Estimating Method = Mfg Part - Routing Priority, Supply Site = US - New York, WBS = 1.1 Test WBS 1, Lead Time = 65d, Proposal Qty = 1 ea, Purchased = unchecked.

- the cell value of the 'Purchased' column of the 'Proposal BOM' table is unchecked for a row with the following data:
- 'Proposal BOM' table contain row with following data_-Warning-_:

#### 18. For REG_MATERIAL_BOM_ITEM_1 row verify: Node = 1.2, Description = REG_MATERIAL_BOM_ITEM_1, Estimating Method = Purchased Part - Vendor Quotes/LTAs/POs, Supply Site = US - New York, WBS = 1.1 Test WBS 1, Lead Time = 15d, Proposal Qty = 2 ea, Purchased = checked.

- the cell value of the 'Purchased' column of the 'Proposal BOM' table is checked for a row with the following data:
- 'Proposal BOM' table contain row with following data_-Warning-_:

#### 19. For REG_MATERIAL_BOM_ITEM_2 row verify: Node = 1.3, Description = REG_MATERIAL_BOM_ITEM_2, Estimating Method = Mfg Part - Routing Priority, Supply Site = US - New York, WBS = 1.1 Test WBS 1, Lead Time = 20d, Proposal Qty = 2 ea, Purchased = unchecked.

- the cell value of the 'Purchased' column of the 'Proposal BOM' table is unchecked for a row with the following data:
- 'Proposal BOM' table contain row with following data_-Warning-_:

### Gherkin Excerpt

```gherkin
  Scenario: TC-BOM-Import-001: Basic BOM Import
    Given open site
    And I perform login
    Then page with name 'Main page' is opened
    Given generate values and store into the variables:
      | $uniqueProposalName | TEST BASIC BOM IMPORT ${CUR_DATE,yyyy-MM-dd hh:mm} |
    And info: ---1. Start creating a new Proposal with Type "Regression Test Only - Manufacturing." Verify that Setup tab opens.---
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
    And info: ---3. Ensure "Select a template" radio button is selected. Choose template "(AS) Regression - Labor Manual Effort & Inflation - Template." Verify dropdown accepts value.---
    And I check 'Select a template' radio
    When I enter data into the next fields:
      | 'Search for Template from Library' field | (AS) Regression - Labor Manual Effort & Inflation - Template |
    And page with name 'Setup page' is opened
    And I press escape
    And page with name 'Setup page' is opened
    And info: ---4. Uncheck "Labor estimates (3 hrs)" and "Material estimates (3 parts)." Verify both checkboxes become unchecked.---
    And I uncheck 'Labor estimates' checkbox
    And page with name 'Setup page' is opened
    And I uncheck 'Material estimates' checkbox
    And page with name 'Setup page' is opened
    And info: ---5. Click Copy. Verify data is saved and page reloads.---
    And click on 'Copy' button
    And page with name 'Setup page' is opened
    And info: ---6. Navigate to Pricing tab. Select "Regression Test - Bill Rates" for Labor/Item Rates. Verify value is accepted.---
    Then I click on 'Pricing' tab
    And page with name 'Pricing page' is opened
    When I enter data into the next fields:
      | 'How are Labor/Item Rates Calculated' field | Regression Test - Bill Rates |
    And page with name 'Pricing page' is opened
    And info: ---7. Click Save. Verify data is saved successfully.---
    When I click on 'Save' button
    And 'Data saved successfully' warning message is displayed in 30 seconds
    And page with name 'Pricing page' is opened
    And info: ---8. Navigate to Line Items tab. Verify view "Regression Test - Manufacturing" is selected and "No records found, click here to add" button is visible.---
    And I click on 'Line Items' tab
    And page with name 'Line Items page' is opened
    When I select 'Regression Test - Manufacturing' in the 'View' dropdown
    And page with name 'Line Items page' is opened
    Then text of 'View' dropdown equals 'View: Regression Test - Manufacturing'
    And info: ---9. Click "No records found, click here to add." Verify a new row is added to the Deliverables Grid.---
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
    And info: ---11. In Cost Allocation field, select Test Data value. Verify Cost BOM updates to "Import BOM" and WBS becomes "Test WBS 1."---
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
    And info: ---13. Click "Import BOM" in Cost BOM field. Verify Import completes, data saves, and Cost BOM updates to "Re-import BOM."---
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
    And page with name 'Estimate Labor' is opened
    And info: ---15. Navigate to Proposal BOM tab. Verify view "Regression Test - Manufacturing" is selected and REG_MATERIAL_BOM appears in the grid.---
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

```

## Alternate mapped Selenium scenario 2

| Field | Value |
| --- | --- |
| Feature | 8. SAP BOM Import Scenario |
| Scenario | TC-BOM-Import-001: Basic BOM Import |
| Tags | `@TC-BOM-001 @START` |
| File | `imported/twentyfive-regtest/tests/src/test/resources/features/testfolder/twentyfive2_4_SAP_BOM_import.feature:5` |

### Scenario Sections

#### Setup

- open site
- I perform Mfg 2.4 login
- page with name 'Main page' is opened
- generate values and store into the variables:

#### 1. Start creating a new Proposal with Type "Regression Test Only - Manufacturing." Verify that Setup tab opens.

- I click on 'PROPOSALS' tab
- page with name 'Proposals list page' is opened
- click on 'New' button
- page with name 'Setup page' is opened
- I enter data into the next fields:
- page with name 'Setup page' is opened
- I enter data into the next fields:
- page with name 'Setup page' is opened

#### 2. Fill all required fields. Set Estimated Project Start = 4/1/2025 and Project End = 3/31/2026. Verify all required fields are populated.

- I enter data into the next fields:
- page with name 'Setup page' is opened
- I enter data into the next fields:
- page with name 'Setup page' is opened
- I enter data into the next fields:
- page with name 'Setup page' is opened
- I enter data into the next fields:
- page with name 'Setup page' is opened
- I enter data into the next fields:
- page with name 'Setup page' is opened

#### 3. Ensure "Select a template" radio button is selected. Choose template "(AS) Regression - Labor Manual Effort & Inflation - Template." Verify dropdown accepts value.

- I check 'Select a template' radio
- I enter data into the next fields:
- page with name 'Setup page' is opened
- I press escape
- page with name 'Setup page' is opened

#### 4. Uncheck "Labor estimates (3 hrs)" and "Material estimates (3 parts)." Verify both checkboxes become unchecked.

- I uncheck 'Labor estimates' checkbox
- page with name 'Setup page' is opened
- I uncheck 'Material estimates' checkbox
- page with name 'Setup page' is opened

#### 5. Click Copy. Verify data is saved and page reloads.

- click on 'Copy' button
- page with name 'Setup page' is opened

#### 6. Navigate to Pricing tab. Select "Regression Test - Bill Rates" for Labor/Item Rates. Verify value is accepted.

- I click on 'Pricing' tab
- page with name 'Pricing page' is opened
- I enter data into the next fields:
- page with name 'Pricing page' is opened

#### 7. Click Save. Verify data is saved successfully.

- I click on 'Save' button
- I wait for 5 seconds
- page with name 'Pricing page' is opened

#### 8. Navigate to Line Items tab. Verify view "Regression Test - Manufacturing" is selected and "No records found, click here to add" button is visible.

- I click on 'Line Items' tab
- page with name 'Line Items page' is opened
- I select 'Regression Test - Manufacturing' in the 'View' dropdown
- page with name 'Line Items page' is opened
- text of 'View' dropdown equals 'View: Regression Test - Manufacturing'

#### 9. Click "No records found, click here to add." Verify a new row is added to the Deliverables Grid.

- I click on 'No records found, click here to add' button
- number of rows in 'Line Items' table equals 1

#### 10. Select the Part Number Test Data value. Verify Description = REG_MATERIAL_BOM (link), Estimate = 1 Test Phase 1, Cost BOM = Select WBS, Supply Site = RG10 US - New York.

- I set value 'REG_MATERIAL_BOM' to the cell of the column 'Part Number' of the 'Line Items' table for the row with the following data:
- page with name 'Line Items page' is opened
- 'Line Items' table contain row with following data:

#### 11. In Cost Allocation field, select Test Data value. Verify Cost BOM updates to "Import BOM" and WBS becomes "Test WBS 1."

- I set value '1.1 Test WBS 1' to the cell of the column 'Cost Allocation' of the 'Line Items' table for the row with the following data:
- page with name 'Line Items page' is opened
- 'Line Items' table contain row with following data:

#### 12. Select Test Data value for End of Delivery. Verify value = 7/21/2025.

- I set value '7/21/2025' to the cell of the column 'End of Delivery' of the 'Line Items' table for the row with the following data:
- I click on 'Save' button
- I wait for 5 seconds
- page with name 'Line Items page' is opened
- I click on 'Update Cost & Prices' link
- I wait for 15 seconds

#### 13. Click "Import BOM" in Cost BOM field. Verify Import completes, data saves, and Cost BOM updates to "Re-import BOM."

- I click 'Import BOM' in table 'Line Items' table for row with data:
- page with name 'Line Items page' is opened
- I wait for 25 seconds
- 'Line Items' table contain row with following data:

#### 14. Click the Open icon in the Estimate field. Verify Estimate opens in a new browser tab.

- I click 'Open' in table 'Line Items' table for row with data:
- page with name 'Line Items page' is opened
- I switch to tab 2
- I click on 'Labor' tab
- I select 'Regression Test - Manufacturing' in the 'View' dropdown
- page with name 'Estimate Labor' is opened

#### 15. Navigate to Proposal BOM tab. Verify view "Regression Test - Manufacturing" is selected and REG_MATERIAL_BOM appears in the grid.

- I click on 'Proposal BOM' tab
- page with name 'Estimate Proposal BOM' is opened
- I select 'Regression Test - Manufacturing' in the 'View' dropdown
- page with name 'Estimate Proposal BOM' is opened
- text of 'View' dropdown equals 'View: Regression Test - Manufacturing'
- 'Proposal BOM' table contain row with following data:

#### 16. Expand REG_MATERIAL_BOM row. Verify sub-level items display: REG_MATERIAL_BOM_ITEM_1 and REG_MATERIAL_BOM_ITEM_2.

- I click 'Expand' in table 'Proposal BOM' table for row with data:
- I wait for 7 seconds
- 'Proposal BOM' table contain row with following data:
- 'Proposal BOM' table contain row with following data:

#### 17. For REG_MATERIAL_BOM row verify: Node = 1, Description = REG_MATERIAL_BOM, Estimating Method = Mfg Part - Routing Priority, Supply Site = US - New York, WBS = 1.1 Test WBS 1, Lead Time = 65d, Proposal Qty = 1 ea, Purchased = unchecked.

- the cell value of the 'Purchased' column of the 'Proposal BOM' table is unchecked for a row with the following data:
- 'Proposal BOM' table contain row with following data_-Warning-_:

#### 18. For REG_MATERIAL_BOM_ITEM_1 row verify: Node = 1.2, Description = REG_MATERIAL_BOM_ITEM_1, Estimating Method = Purchased Part - Vendor Quotes/LTAs/POs, Supply Site = US - New York, WBS = 1.1 Test WBS 1, Lead Time = 15d, Proposal Qty = 2 ea, Purchased = checked.

- the cell value of the 'Purchased' column of the 'Proposal BOM' table is checked for a row with the following data:
- 'Proposal BOM' table contain row with following data_-Warning-_:

#### 19. For REG_MATERIAL_BOM_ITEM_2 row verify: Node = 1.3, Description = REG_MATERIAL_BOM_ITEM_2, Estimating Method = Mfg Part - Routing Priority, Supply Site = US - New York, WBS = 1.1 Test WBS 1, Lead Time = 20d, Proposal Qty = 2 ea, Purchased = unchecked.

- the cell value of the 'Purchased' column of the 'Proposal BOM' table is unchecked for a row with the following data:
- 'Proposal BOM' table contain row with following data_-Warning-_:

### Gherkin Excerpt

```gherkin
  Scenario: TC-BOM-Import-001: Basic BOM Import
    Given open site
    And I perform Mfg 2.4 login
    Then page with name 'Main page' is opened
    Given generate values and store into the variables:
      | $uniqueProposalName | TEST BASIC BOM IMPORT ${CUR_DATE,yyyy-MM-dd hh:mm} |
    And info: ---1. Start creating a new Proposal with Type "Regression Test Only - Manufacturing." Verify that Setup tab opens.---
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
    And info: ---3. Ensure "Select a template" radio button is selected. Choose template "(AS) Regression - Labor Manual Effort & Inflation - Template." Verify dropdown accepts value.---
    And I check 'Select a template' radio
    When I enter data into the next fields:
      | 'Search for Template from Library' field | (AS) Regression - Labor Manual Effort & Inflation - Template |
    And page with name 'Setup page' is opened
    And I press escape
    And page with name 'Setup page' is opened
    And info: ---4. Uncheck "Labor estimates (3 hrs)" and "Material estimates (3 parts)." Verify both checkboxes become unchecked.---
    And I uncheck 'Labor estimates' checkbox
    And page with name 'Setup page' is opened
    And I uncheck 'Material estimates' checkbox
    And page with name 'Setup page' is opened
    And info: ---5. Click Copy. Verify data is saved and page reloads.---
    And click on 'Copy' button
    And page with name 'Setup page' is opened
    And info: ---6. Navigate to Pricing tab. Select "Regression Test - Bill Rates" for Labor/Item Rates. Verify value is accepted.---
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
    And info: ---8. Navigate to Line Items tab. Verify view "Regression Test - Manufacturing" is selected and "No records found, click here to add" button is visible.---
    And I click on 'Line Items' tab
    And page with name 'Line Items page' is opened
    When I select 'Regression Test - Manufacturing' in the 'View' dropdown
    And page with name 'Line Items page' is opened
    Then text of 'View' dropdown equals 'View: Regression Test - Manufacturing'
    And info: ---9. Click "No records found, click here to add." Verify a new row is added to the Deliverables Grid.---
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
    And info: ---11. In Cost Allocation field, select Test Data value. Verify Cost BOM updates to "Import BOM" and WBS becomes "Test WBS 1."---
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
    And info: ---13. Click "Import BOM" in Cost BOM field. Verify Import completes, data saves, and Cost BOM updates to "Re-import BOM."---
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
    And info: ---15. Navigate to Proposal BOM tab. Verify view "Regression Test - Manufacturing" is selected and REG_MATERIAL_BOM appears in the grid.---
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
```

---

_Generated by `scripts/generate-testcase-guides.py` from the RTA mapping and local feature files._
