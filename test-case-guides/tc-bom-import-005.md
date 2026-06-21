# TC-BOM-Import-005 - Modify costed BOM items

| Field | Value |
| --- | --- |
| Test ID | TC-BOM-Import-005 |
| Title | Modify costed BOM items |
| RTA page | https://twenty5.atlassian.net/wiki/spaces/RTA/pages/558170114/BTP+Gold+TC-BOM-Import-005+Modify+costed+BOM+items |
| Map source | `RTA_TEST_SUITE_FEATURE_MAP.md` |

## Run Instructions

When a consultant asks to run this test, use the default app URL unless they provide another URL:

```text
https://approuter-twenty5ipe-dev.cfapps.us10.hana.ondemand.com/#quote
```

For Claude Desktop, ask before launching Selenium Chrome with the default URL. If the user provides another URL, launch that URL instead.

```bash
scripts/start-debug-chrome.sh "https://approuter-twenty5ipe-dev.cfapps.us10.hana.ondemand.com/#quote"
scripts/run-twentyfive-test.sh @TC-BOM-005
```

Duplicate feature matches exist for this Confluence page. The tag command may run more than one matching scenario until the canonical feature file is confirmed.

## Default mapped Selenium scenario

| Field | Value |
| --- | --- |
| Feature | 11. SAP BOM Import Scenario |
| Scenario | TC-BOM-Import-005: Modify costed BOM items |
| Tags | `@TC-BOM-005 @END` |
| File | `imported/twentyfive-regtest/tests/src/test/resources/features/testfolder/basicBOMImport.feature:407` |

### Scenario Sections

#### 1. For the row REG_MATERIAL_BOM_ITEM_1, click the Gear icon [] and select Edit Details. Verify that the "Purchased For ..." popup is displayed.

- I click on 'Procurement & Production' tab
- page with name 'Estimate Procurement & Production' is opened
- I select 'Regression Test - Manufacturing' in the 'View' dropdown
- I click on 'Save' button
- text of 'View' dropdown equals 'View: Regression Test - Manufacturing (shared & preferred)'
- I hover 'Description' cell in table 'Procurement & Production' table and click  for row with data:
- I click on 'Edit Details' menuItem
- 'Purchased Part' popup is displayed
- page with name 'Purchased Part: Estimate - Cost Estimate' is opened

#### 2. In the Purchase History grid, select Use for REG_PURCHASE1/1 and verify that REG_PURCHASE1/1 is selected as Use.

- I activate checkbox 'Use' in table 'Cost Estimate' table for row with data:
- the cell value of the 'Use' column of the 'Cost Estimate' table is checked for a row with the following data:

#### 3. Verify that Estimating Source = Purchase Orders (SAP), Source Description = NB REG_PURCHASE1 Custom Plastics, Inc., Escalation Index is empty, Escalation Factor = 1.00, Source Unit Cost = $ 122,000.00/ea, Complexity Factor = 1.00, and Decrement Factor = 1.00.

- I verify 'Estimating Source' of Cost Estimate Dialog Table page is enabled, not required, drop down and has text 'Purchase Orders (SAP)'
- I verify 'Source Description' of Cost Estimate Dialog Table page is enabled, not required, free text and has text 'NB REG_PURCHASE1 Custom Plastics, Inc.'
- I verify 'Escalation Index' of Cost Estimate Dialog Table page is enabled, not required, drop down and is empty
- I verify 'Escalation Factor' of Cost Estimate Dialog Table page is enabled, not required, free text and has text '1.00'
- I verify 'Source Unit Cost' of Cost Estimate Dialog Table page is enabled, not required, free text and has text '122,000.00'
- I verify 'Complexity Factor' of Cost Estimate Dialog Table page is enabled, not required, free text and has text '1.00'
- I verify 'Decrement Factor' of Cost Estimate Dialog Table page is enabled, not required, free text and has text '1.00'

#### 4. Set QAF - Qty Adj Factor to 2.00 and verify that Changed Adj Unit Cost = $ 244,000.00/ea and Total Cost = $ 488,000.00.

- I enter data into the next fields:
- I verify 'QAF - Qty Adj Factor' of Cost Estimate Dialog Table page is enabled, not required, free text and has text '2.00'
- I verify 'Changed Adj Unit Cost' of Cost Estimate Dialog Table page is enabled, not required, free text and has text '244,000.00'
- I verify 'Total Cost' of Cost Estimate Dialog Table page is enabled, not required, free text and has text '488,000.00'

#### 5. Set Complexity Factor to 1.50 and verify that Changed Adj Unit Cost = $ 366,000.00/ea and Total Cost = $ 732,000.00.

- I enter data into the next fields:
- I verify 'Complexity Factor' of Cost Estimate Dialog Table page is enabled, not required, free text and has text '1.50'
- I verify 'Changed Adj Unit Cost' of Cost Estimate Dialog Table page is enabled, not required, free text and has text '366,000.00'
- I verify 'Total Cost' of Cost Estimate Dialog Table page is enabled, not required, free text and has text '732,000.00'

#### 6. Set Decrement Factor to 0.50 and verify that Changed Adj Unit Cost = $ 183,000.00/ea and Total Cost = $ 366,000.00.

- I enter data into the next fields:
- I verify 'Decrement Factor' of Cost Estimate Dialog Table page is enabled, not required, free text and has text '0.50'
- I verify 'Changed Adj Unit Cost' of Cost Estimate Dialog Table page is enabled, not required, free text and has text '183,000.00'
- I verify 'Total Cost' of Cost Estimate Dialog Table page is enabled, not required, free text and has text '366,000.00'

#### 7. Click Confirm and verify that the Purchased For ... popup is closed.

- I click on 'Confirm' button
- 'Purchased Part' popup is not displayed
- page with name 'Estimate Procurement & Production' is opened

#### 8. For the row REG_MATERIAL_BOM_ITEM_1, verify that Complexity Factor = 1.50, Decrement Factor = 0.50, QAF = 2.00, Source Unit Cost = $ 122,000.00/ea, and Cost in Company Currency = $ 366,000.00.

- I select 'Regression Test - Manufacturing' in the 'View' dropdown
- I click on 'Save' button
- text of 'View' dropdown equals 'View: Regression Test - Manufacturing (shared & preferred)'
- 'Procurement & Production' table contain row with following data:

#### 9. For the row REG_MATERIAL_BOM_ITEM_2, click the Gear icon [] and select Edit Details. Verify that the "Mfg Part ..." popup is displayed and the Cost Breakdown tab is opened.

- I hover 'Description' cell in table 'Procurement & Production' table and click  for row with data:
- I click on 'Edit Details' menuItem
- 'Mfg Part' popUp is displayed
- page with name 'Mfg Part: Estimate - Cost Breakdown' is opened

#### 10. In the Resource Grid, set Setup Hrs = 120.00 and verify that Total Setup Hours = 120.00, Adj Unit Cost = $ 23,140.00/ea (ignore decimals after 2 places), Total Cost = $ 46,280.00, Total Effort = 520.00 hours, Total Time (Grid) = 520.00 HR, and Total Cost (Grid) = $ 46,280.00.

- I set value '120.00' to the cell of the column 'Setup Hrs' of the 'Cost Breakdown' table for the row with the following data:
- I verify 'Total Setup Hours' of Mfg Part: Estimate - Cost Breakdown Dialog Table page is enabled, not required, free text and has text '120.00 hours'
- I verify 'Adj Unit Cost' of Mfg Part: Estimate - Cost Breakdown Dialog Table page is enabled, not required, free text and has text '23,140.00'
- I verify 'Total Cost' of Mfg Part: Estimate - Cost Breakdown Dialog Table page is enabled, not required, free text and has text '46,280.00'
- I verify 'Total Effort' of Mfg Part: Estimate - Cost Breakdown Dialog Table page is enabled, not required, free text and has text '520.00'
- 'Cost Breakdown' table contain row with following data:

#### 11. In the Resource Grid, set Run Time = 240.00 and verify that Sum of Run Hrs/Unit = 240.00, Unit Cost = $ 21,360.00/ea (ignore decimals after 2 places), Adj Unit Cost = $ 26,700.00/ea (ignore decimals after 2 places), Realization Factor = 400.00, Total Cost = $ 53,400.00, Total Effort = 600.00 hours, Total Time (Grid) = 600.00 HR, and Total Cost (Grid) = $ 53,400.00.

- I set value '240.00' to the cell of the column 'Run Time' of the 'Cost Breakdown' table for the row with the following data:
- I verify 'Sum of Run Hrs/Unit' of Mfg Part: Estimate - Cost Breakdown Dialog Table page is enabled, not required, free text and has text '240.00 hours'
- I verify 'Unit Cost' of Mfg Part: Estimate - Cost Breakdown Dialog Table page is enabled, not required, free text and has text '21,360.00'
- I verify 'Adj Unit Cost' of Mfg Part: Estimate - Cost Breakdown Dialog Table page is enabled, not required, free text and has text '26,700.00'
- I verify 'Realization Factor' of Mfg Part: Estimate - Cost Breakdown Dialog Table page is enabled, not required, free text and has text '480.00'
- I verify 'Total Cost' of Mfg Part: Estimate - Cost Breakdown Dialog Table page is enabled, not required, free text and has text '53,400.00'
- I verify 'Total Effort' of Mfg Part: Estimate - Cost Breakdown Dialog Table page is enabled, not required, free text and has text '600.00 hours'
- 'Cost Breakdown' table contain row with following data:

#### 12. Click Confirm and verify that the Mfg Part ... popup is closed.

- I click on 'Confirm' button
- page with name 'Estimate Procurement & Production' is opened
- 'Mfg Part' popUp is not displayed
- I wait for 5 seconds
- I click on 'Update Estimate totals' link
- I wait for 20 seconds
- page with name 'Estimate Procurement & Production' is opened
- I refresh page
- I wait for 30 seconds
- I click on 'Procurement & Production' tab

#### 13. For the row REG_MATERIAL_BOM_ITEM_2, verify that Source Unit Cost = $ 26,700.00/ea and Cost in Company Currency = $ 53,400.00.

- I select 'Regression Test - Manufacturing' in the 'View' dropdown
- I click on 'Save' button
- text of 'View' dropdown equals 'View: Regression Test - Manufacturing (shared & preferred)'
- page with name 'Estimate Procurement & Production' is opened
- 'Procurement & Production' table contain row with following data:

#### 14. For the row REG_MATERIAL_BOM, click the Gear icon [] and select Edit Details. Verify that the Mfg Part ... popup is displayed, the Cost Breakdown tab is opened, and the Resource Grid contains Resource Group = REG_BOM_RESOURCE_1.

- I hover 'Description' cell in table 'Procurement & Production' table and click  for row with data:
- I click on 'Edit Details' menuItem
- 'Mfg Part' popUp is displayed
- page with name 'Mfg Part: Estimate - Cost Breakdown' is opened
- I select 'Regression Test - Manufacturing (BOM)' in the 'Dialog View' dropdown
- I wait for 3 seconds
- text of 'Dialog View' dropdown equals 'View: Regression Test - Manufacturing (BOM) (shared & preferred)'
- 'Cost Breakdown' table contain row with following data:

#### 15. Click the "+" icon to add a new row and verify that a new row is added to the Resource Grid.

- I click '+' in table 'Cost Breakdown' table for row with data:

#### 16. For the new row, select Resource Group = REG_CONSULTANT1 and verify that Cost Element = Consultants - Local and Rate/Hr = $ 120.00/hr.

- I set value 'REG_CONSULTANT1' to the cell of the column 'Resource Group' of the 'Cost Breakdown' table for the row with the following data:
- 'Cost Breakdown' table contain row with following data:

#### 17. For the REG_CONSULTANT1 row, select Work Center = QUALITY.

- I set value 'QUALITY' to the cell of the column 'Work Center' of the 'Cost Breakdown' table for the row with the following data:
- 'Cost Breakdown' table contain row with following data:

#### 18. For the REG_CONSULTANT1 row, set Run Time = 250 and verify that Total Time = 250.00 HR and Total Cost = $ 30,000.00.

- I set value '250' to the cell of the column 'Run Time' of the 'Cost Breakdown' table for the row with the following data:
- 'Cost Breakdown' table contain row with following data:

#### 19. Verify that Total Setup Hours = 50.00, Sum of Run Hrs/Unit = 350.00, Unit Cost = $ 38,900.00/ea (ignore decimals after 2 places), Adj Unit Cost = $ 43,350.00/ea (ignore decimals after 2 places), Realization Factor = 350.00, Total Cost = $ 43,350.00, and Total Effort = 400.00 hours.

- I verify 'Total Setup Hours' of Mfg Part: Estimate - Cost Breakdown Dialog Table page is enabled, not required, free text and has text '50.00 hours'
- I verify 'Sum of Run Hrs/Unit' of Mfg Part: Estimate - Cost Breakdown Dialog Table page is enabled, not required, free text and has text '350.00 hours'
- I verify 'Unit Cost' of Mfg Part: Estimate - Cost Breakdown Dialog Table page is enabled, not required, free text and has text '38,900.00'
- I wait for 3 seconds
- I verify 'Adj Unit Cost' of Mfg Part: Estimate - Cost Breakdown Dialog Table page is enabled, not required, free text and has text '43,350.00'
- I verify 'Realization Factor' of Mfg Part: Estimate - Cost Breakdown Dialog Table page is enabled, not required, free text and has text '350.00'
- I verify 'Total Cost' of Mfg Part: Estimate - Cost Breakdown Dialog Table page is enabled, not required, free text and has text '43,350.00'
- I verify 'Total Effort' of Mfg Part: Estimate - Cost Breakdown Dialog Table page is enabled, not required, free text and has text '400.00 hours'

#### 20. Click Confirm and verify that the Mfg Part ... popup is closed.

- I click on 'Confirm' button
- 'Mfg Part' popUp is not displayed
- I wait for 5 seconds
- I click on 'Update Estimate totals' link
- I wait for 20 seconds
- page with name 'Estimate Procurement & Production' is opened
- I refresh page
- I wait for 30 seconds
- I click on 'Procurement & Production' tab
- I select 'Regression Test - Manufacturing' in the 'View' dropdown
- I click on 'Save' button
- text of 'View' dropdown equals 'View: Regression Test - Manufacturing (shared & preferred)'
- page with name 'Estimate Procurement & Production' is opened

#### 21. For the row REG_MATERIAL_BOM, verify that Source Unit Cost = $ 43,350.00/ea and Cost in Company Currency = $ 43,350.00.

- 'Procurement & Production' table contain row with following data:

### Gherkin Excerpt

```gherkin
  Scenario: TC-BOM-Import-005: Modify costed BOM items
    And info: ---1. For the row REG_MATERIAL_BOM_ITEM_1, click the Gear icon [] and select Edit Details. Verify that the "Purchased For ..." popup is displayed.---
    And I click on 'Procurement & Production' tab
    Then page with name 'Estimate Procurement & Production' is opened
    When I select 'Regression Test - Manufacturing' in the 'View' dropdown
    And I click on 'Save' button
    Then text of 'View' dropdown equals 'View: Regression Test - Manufacturing (shared & preferred)'
    And I hover 'Description' cell in table 'Procurement & Production' table and click  for row with data:
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
    And info: ---4. Set QAF - Qty Adj Factor to 2.00 and verify that Changed Adj Unit Cost = $ 244,000.00/ea and Total Cost = $ 488,000.00.---
    When I enter data into the next fields:
      | 'QAF Qty Adj Factor' field  |  2.00  |
    And I verify 'QAF - Qty Adj Factor' of Cost Estimate Dialog Table page is enabled, not required, free text and has text '2.00'
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
    And info: ---7. Click Confirm and verify that the Purchased For ... popup is closed.---
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
    And info: ---9. For the row REG_MATERIAL_BOM_ITEM_2, click the Gear icon [] and select Edit Details. Verify that the "Mfg Part ..." popup is displayed and the Cost Breakdown tab is opened.---
    And I hover 'Description' cell in table 'Procurement & Production' table and click  for row with data:
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
    And info: ---12. Click Confirm and verify that the Mfg Part ... popup is closed.---
    And I click on 'Confirm' button
    Then page with name 'Estimate Procurement & Production' is opened
    And 'Mfg Part' popUp is not displayed
    And I wait for 5 seconds
    And I click on 'Update Estimate totals' link
    And I wait for 20 seconds
    Then page with name 'Estimate Procurement & Production' is opened
    And I refresh page
    And I wait for 30 seconds
    And I click on 'Procurement & Production' tab
    And info: ---13. For the row REG_MATERIAL_BOM_ITEM_2, verify that Source Unit Cost = $ 26,700.00/ea and Cost in Company Currency = $ 53,400.00.---
    When I select 'Regression Test - Manufacturing' in the 'View' dropdown
    And I click on 'Save' button
    Then text of 'View' dropdown equals 'View: Regression Test - Manufacturing (shared & preferred)'
    And page with name 'Estimate Procurement & Production' is opened
    And 'Procurement & Production' table contain row with following data:
      | Description                | REG_MATERIAL_BOM_ITEM_2 |
      | Source Unit Cost           | 26,700.00               |
      | Cost in Company Currency   | 53,400.00               |
    And info: ---14. For the row REG_MATERIAL_BOM, click the Gear icon [] and select Edit Details. Verify that the Mfg Part ... popup is displayed, the Cost Breakdown tab is opened, and the Resource Grid contains Resource Group = REG_BOM_RESOURCE_1.---
    And I hover 'Description' cell in table 'Procurement & Production' table and click  for row with data:
      | Description                | REG_MATERIAL_BOM |
    And I click on 'Edit Details' menuItem
    And 'Mfg Part' popUp is displayed
    And page with name 'Mfg Part: Estimate - Cost Breakdown' is opened
    When I select 'Regression Test - Manufacturing (BOM)' in the 'Dialog View' dropdown
    And I wait for 3 seconds
    Then text of 'Dialog View' dropdown equals 'View: Regression Test - Manufacturing (BOM) (shared & preferred)'
    And 'Cost Breakdown' table contain row with following data:
      | Resource Group | REG_BOM_RESOURCE_1 |
    And info: ---15. Click the "+" icon to add a new row and verify that a new row is added to the Resource Grid.---
    And I click '+' in table 'Cost Breakdown' table for row with data:
      | Resource Group | REG_BOM_RESOURCE_1 |
    And info: ---16. For the new row, select Resource Group = REG_CONSULTANT1 and verify that Cost Element = Consultants - Local and Rate/Hr = $ 120.00/hr.---
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
    And info: ---20. Click Confirm and verify that the Mfg Part ... popup is closed.---
    And I click on 'Confirm' button
    Then 'Mfg Part' popUp is not displayed
    And I wait for 5 seconds
    And I click on 'Update Estimate totals' link
    And I wait for 20 seconds
    Then page with name 'Estimate Procurement & Production' is opened
    And I refresh page
    And I wait for 30 seconds
    And I click on 'Procurement & Production' tab
    When I select 'Regression Test - Manufacturing' in the 'View' dropdown
    And I click on 'Save' button
    Then text of 'View' dropdown equals 'View: Regression Test - Manufacturing (shared & preferred)'
    And page with name 'Estimate Procurement & Production' is opened
    And info: ---21. For the row REG_MATERIAL_BOM, verify that Source Unit Cost = $ 43,350.00/ea and Cost in Company Currency = $ 43,350.00.---
    And 'Procurement & Production' table contain row with following data:
      | Description                | REG_MATERIAL_BOM |
      | Source Unit Cost           | 43,350.00        |
      | Cost in Company Currency   | 43,350.00        |
```

## Alternate mapped Selenium scenario 2

| Field | Value |
| --- | --- |
| Feature | 8. SAP BOM Import Scenario |
| Scenario | TC-BOM-Import-005: Modify costed BOM items |
| Tags | `@TC-BOM-005 @END` |
| File | `imported/twentyfive-regtest/tests/src/test/resources/features/testfolder/twentyfive2_4_SAP_BOM_import.feature:399` |

### Scenario Sections

#### 1. For the row REG_MATERIAL_BOM_ITEM_1, click the Gear icon [] and select Edit Details. Verify that the "Purchased For ..." popup is displayed.

- I click on 'Procurement & Production' tab
- page with name 'Estimate Procurement & Production' is opened
- I select 'Regression Test - Manufacturing' in the 'View' dropdown
- I click on 'Save' button
- text of 'View' dropdown equals 'View: Regression Test - Manufacturing (shared & preferred)'
- I hover 'Description' cell in table 'Procurement & Production' table and click  for row with data:
- I click on 'Edit Details' menuItem
- 'Purchased Part' popup is displayed
- page with name 'Purchased Part: Estimate - Cost Estimate' is opened

#### 2. In the Purchase History grid, select Use for REG_PURCHASE1/1 and verify that REG_PURCHASE1/1 is selected as Use.

- I activate checkbox 'Use' in table 'Cost Estimate' table for row with data:
- the cell value of the 'Use' column of the 'Cost Estimate' table is checked for a row with the following data:

#### 3. Verify that Estimating Source = Purchase Orders (SAP), Source Description = NB REG_PURCHASE1 Custom Plastics, Inc., Escalation Index is empty, Escalation Factor = 1.00, Source Unit Cost = $ 122,000.00/ea, Complexity Factor = 1.00, and Decrement Factor = 1.00.

- I verify 'Estimating Source' of Cost Estimate Dialog Table page is enabled, not required, drop down and has text 'Purchase Orders (SAP)'
- I verify 'Source Description' of Cost Estimate Dialog Table page is enabled, not required, free text and has text 'NB REG_PURCHASE1 Custom Plastics, Inc.'
- I verify 'Escalation Index' of Cost Estimate Dialog Table page is enabled, not required, drop down and is empty
- I verify 'Escalation Factor' of Cost Estimate Dialog Table page is enabled, not required, free text and has text '1.00'
- I verify 'Source Unit Cost' of Cost Estimate Dialog Table page is enabled, not required, free text and has text '122,000.00'
- I verify 'Complexity Factor' of Cost Estimate Dialog Table page is enabled, not required, free text and has text '1.00'
- I verify 'Decrement Factor' of Cost Estimate Dialog Table page is enabled, not required, free text and has text '1.00'

#### 4. Set QAF - Qty Adj Factor to 2.00 and verify that Changed Adj Unit Cost = $ 244,000.00/ea and Total Cost = $ 488,000.00.

- I enter data into the next fields:
- I verify 'QAF - Qty Adj Factor' of Cost Estimate Dialog Table page is enabled, not required, free text and has text '2.00'
- I verify 'Changed Adj Unit Cost' of Cost Estimate Dialog Table page is enabled, not required, free text and has text '244,000.00'
- I verify 'Total Cost' of Cost Estimate Dialog Table page is enabled, not required, free text and has text '488,000.00'

#### 5. Set Complexity Factor to 1.50 and verify that Changed Adj Unit Cost = $ 366,000.00/ea and Total Cost = $ 732,000.00.

- I enter data into the next fields:
- I verify 'Complexity Factor' of Cost Estimate Dialog Table page is enabled, not required, free text and has text '1.50'
- I verify 'Changed Adj Unit Cost' of Cost Estimate Dialog Table page is enabled, not required, free text and has text '366,000.00'
- I verify 'Total Cost' of Cost Estimate Dialog Table page is enabled, not required, free text and has text '732,000.00'

#### 6. Set Decrement Factor to 0.50 and verify that Changed Adj Unit Cost = $ 183,000.00/ea and Total Cost = $ 366,000.00.

- I enter data into the next fields:
- I verify 'Decrement Factor' of Cost Estimate Dialog Table page is enabled, not required, free text and has text '0.50'
- I verify 'Changed Adj Unit Cost' of Cost Estimate Dialog Table page is enabled, not required, free text and has text '183,000.00'
- I verify 'Total Cost' of Cost Estimate Dialog Table page is enabled, not required, free text and has text '366,000.00'

#### 7. Click Confirm and verify that the Purchased For ... popup is closed.

- I click on 'Confirm' button
- 'Purchased Part' popup is not displayed
- page with name 'Estimate Procurement & Production' is opened

#### 8. For the row REG_MATERIAL_BOM_ITEM_1, verify that Complexity Factor = 1.50, Decrement Factor = 0.50, QAF = 2.00, Source Unit Cost = $ 122,000.00/ea, and Cost in Company Currency = $ 366,000.00.

- I select 'Regression Test - Manufacturing' in the 'View' dropdown
- I click on 'Save' button
- text of 'View' dropdown equals 'View: Regression Test - Manufacturing (shared & preferred)'
- 'Procurement & Production' table contain row with following data:

#### 9. For the row REG_MATERIAL_BOM_ITEM_2, click the Gear icon [] and select Edit Details. Verify that the "Mfg Part ..." popup is displayed and the Cost Breakdown tab is opened.

- I hover 'Description' cell in table 'Procurement & Production' table and click  for row with data:
- I click on 'Edit Details' menuItem
- 'Mfg Part' popUp is displayed
- page with name 'Mfg Part: Estimate - Cost Breakdown' is opened

#### 10. In the Resource Grid, set Setup Hrs = 120.00 and verify that Total Setup Hours = 120.00, Adj Unit Cost = $ 23,140.00/ea (ignore decimals after 2 places), Total Cost = $ 46,280.00, Total Effort = 520.00 hours, Total Time (Grid) = 520.00 HR, and Total Cost (Grid) = $ 46,280.00.

- I set value '120.00' to the cell of the column 'Setup Hrs' of the 'Cost Breakdown' table for the row with the following data:
- I verify 'Total Setup Hours' of Mfg Part: Estimate - Cost Breakdown Dialog Table page is enabled, not required, free text and has text '120.00 hours'
- I verify 'Adj Unit Cost' of Mfg Part: Estimate - Cost Breakdown Dialog Table page is enabled, not required, free text and has text '23,140.00'
- I verify 'Total Cost' of Mfg Part: Estimate - Cost Breakdown Dialog Table page is enabled, not required, free text and has text '46,280.00'
- I verify 'Total Effort' of Mfg Part: Estimate - Cost Breakdown Dialog Table page is enabled, not required, free text and has text '520.00'
- 'Cost Breakdown' table contain row with following data:

#### 11. In the Resource Grid, set Run Time = 240.00 and verify that Sum of Run Hrs/Unit = 240.00, Unit Cost = $ 21,360.00/ea (ignore decimals after 2 places), Adj Unit Cost = $ 26,700.00/ea (ignore decimals after 2 places), Realization Factor = 400.00, Total Cost = $ 53,400.00, Total Effort = 600.00 hours, Total Time (Grid) = 600.00 HR, and Total Cost (Grid) = $ 53,400.00.

- I set value '240.00' to the cell of the column 'Run Time' of the 'Cost Breakdown' table for the row with the following data:
- I verify 'Sum of Run Hrs/Unit' of Mfg Part: Estimate - Cost Breakdown Dialog Table page is enabled, not required, free text and has text '240.00 hours'
- I verify 'Unit Cost' of Mfg Part: Estimate - Cost Breakdown Dialog Table page is enabled, not required, free text and has text '21,360.00'
- I verify 'Adj Unit Cost' of Mfg Part: Estimate - Cost Breakdown Dialog Table page is enabled, not required, free text and has text '26,700.00'
- I verify 'Realization Factor' of Mfg Part: Estimate - Cost Breakdown Dialog Table page is enabled, not required, free text and has text '480.00'
- I verify 'Total Cost' of Mfg Part: Estimate - Cost Breakdown Dialog Table page is enabled, not required, free text and has text '53,400.00'
- I verify 'Total Effort' of Mfg Part: Estimate - Cost Breakdown Dialog Table page is enabled, not required, free text and has text '600.00 hours'
- 'Cost Breakdown' table contain row with following data:

#### 12. Click Confirm and verify that the Mfg Part ... popup is closed.

- I click on 'Confirm' button
- page with name 'Estimate Procurement & Production' is opened
- 'Mfg Part' popUp is not displayed
- I wait for 5 seconds
- I click on 'Update Estimate totals' link
- I wait for 20 seconds
- page with name 'Estimate Procurement & Production' is opened
- I refresh page
- I wait for 30 seconds
- page with name 'Estimate Procurement & Production' is opened

#### 13. For the row REG_MATERIAL_BOM_ITEM_2, verify that Source Unit Cost = $ 26,700.00/ea and Cost in Company Currency = $ 53,400.00.

- I select 'Regression Test - Manufacturing' in the 'View' dropdown
- I click on 'Save' button
- text of 'View' dropdown equals 'View: Regression Test - Manufacturing (shared & preferred)'
- 'Procurement & Production' table contain row with following data:

#### 14. For the row REG_MATERIAL_BOM, click the Gear icon [] and select Edit Details. Verify that the Mfg Part ... popup is displayed, the Cost Breakdown tab is opened, and the Resource Grid contains Resource Group = REG_BOM_RESOURCE_1.

- I hover 'Description' cell in table 'Procurement & Production' table and click  for row with data:
- I click on 'Edit Details' menuItem
- 'Mfg Part' popUp is displayed
- page with name 'Mfg Part: Estimate - Cost Breakdown' is opened
- I select 'Regression Test - Manufacturing (BOM)' in the 'Dialog View' dropdown
- I wait for 3 seconds
- text of 'Dialog View' dropdown equals 'View: Regression Test - Manufacturing (BOM) (shared & preferred)'
- 'Cost Breakdown' table contain row with following data:

#### 15. Click the "+" icon to add a new row and verify that a new row is added to the Resource Grid.

- I click '+' in table 'Cost Breakdown' table for row with data:

#### 16. For the new row, select Resource Group = REG_CONSULTANT1 and verify that Cost Element = Consultants - Local and Rate/Hr = $ 120.00/hr.

- I set value 'REG_CONSULTANT1' to the cell of the column 'Resource Group' of the 'Cost Breakdown' table for the row with the following data:
- 'Cost Breakdown' table contain row with following data:

#### 17. For the REG_CONSULTANT1 row, select Work Center = QUALITY.

- I set value 'QUALITY' to the cell of the column 'Work Center' of the 'Cost Breakdown' table for the row with the following data:
- 'Cost Breakdown' table contain row with following data:

#### 18. For the REG_CONSULTANT1 row, set Run Time = 250 and verify that Total Time = 250.00 HR and Total Cost = $ 30,000.00.

- I set value '250' to the cell of the column 'Run Time' of the 'Cost Breakdown' table for the row with the following data:
- 'Cost Breakdown' table contain row with following data:

#### 19. Verify that Total Setup Hours = 50.00, Sum of Run Hrs/Unit = 350.00, Unit Cost = $ 38,900.00/ea (ignore decimals after 2 places), Adj Unit Cost = $ 43,350.00/ea (ignore decimals after 2 places), Realization Factor = 350.00, Total Cost = $ 43,350.00, and Total Effort = 400.00 hours.

- I verify 'Total Setup Hours' of Mfg Part: Estimate - Cost Breakdown Dialog Table page is enabled, not required, free text and has text '50.00 hours'
- I verify 'Sum of Run Hrs/Unit' of Mfg Part: Estimate - Cost Breakdown Dialog Table page is enabled, not required, free text and has text '350.00 hours'
- I verify 'Unit Cost' of Mfg Part: Estimate - Cost Breakdown Dialog Table page is enabled, not required, free text and has text '38,900.00'
- I wait for 3 seconds
- I verify 'Adj Unit Cost' of Mfg Part: Estimate - Cost Breakdown Dialog Table page is enabled, not required, free text and has text '43,350.00'
- I verify 'Realization Factor' of Mfg Part: Estimate - Cost Breakdown Dialog Table page is enabled, not required, free text and has text '350.00'
- I verify 'Total Cost' of Mfg Part: Estimate - Cost Breakdown Dialog Table page is enabled, not required, free text and has text '43,350.00'
- I verify 'Total Effort' of Mfg Part: Estimate - Cost Breakdown Dialog Table page is enabled, not required, free text and has text '400.00 hours'

#### 20. Click Confirm and verify that the Mfg Part ... popup is closed.

- I click on 'Confirm' button
- 'Mfg Part' popUp is not displayed
- I wait for 5 seconds
- I click on 'Update Estimate totals' link
- I wait for 20 seconds
- page with name 'Estimate Procurement & Production' is opened
- I refresh page
- I wait for 30 seconds
- page with name 'Estimate Procurement & Production' is opened

#### 21. For the row REG_MATERIAL_BOM, verify that Source Unit Cost = $ 43,350.00/ea and Cost in Company Currency = $ 43,350.00.

- 'Procurement & Production' table contain row with following data:

### Gherkin Excerpt

```gherkin
  Scenario: TC-BOM-Import-005: Modify costed BOM items
    And info: ---1. For the row REG_MATERIAL_BOM_ITEM_1, click the Gear icon [] and select Edit Details. Verify that the "Purchased For ..." popup is displayed.---
    And I click on 'Procurement & Production' tab
    Then page with name 'Estimate Procurement & Production' is opened
    When I select 'Regression Test - Manufacturing' in the 'View' dropdown
    And I click on 'Save' button
    Then text of 'View' dropdown equals 'View: Regression Test - Manufacturing (shared & preferred)'
    And I hover 'Description' cell in table 'Procurement & Production' table and click  for row with data:
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
    And info: ---4. Set QAF - Qty Adj Factor to 2.00 and verify that Changed Adj Unit Cost = $ 244,000.00/ea and Total Cost = $ 488,000.00.---
    When I enter data into the next fields:
      | 'QAF Qty Adj Factor' field  |  2.00  |
    And I verify 'QAF - Qty Adj Factor' of Cost Estimate Dialog Table page is enabled, not required, free text and has text '2.00'
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
    And info: ---7. Click Confirm and verify that the Purchased For ... popup is closed.---
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
    And info: ---9. For the row REG_MATERIAL_BOM_ITEM_2, click the Gear icon [] and select Edit Details. Verify that the "Mfg Part ..." popup is displayed and the Cost Breakdown tab is opened.---
    And I hover 'Description' cell in table 'Procurement & Production' table and click  for row with data:
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
    And info: ---12. Click Confirm and verify that the Mfg Part ... popup is closed.---
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
    And info: ---14. For the row REG_MATERIAL_BOM, click the Gear icon [] and select Edit Details. Verify that the Mfg Part ... popup is displayed, the Cost Breakdown tab is opened, and the Resource Grid contains Resource Group = REG_BOM_RESOURCE_1.---
    And I hover 'Description' cell in table 'Procurement & Production' table and click  for row with data:
      | Description                | REG_MATERIAL_BOM |
    And I click on 'Edit Details' menuItem
    And 'Mfg Part' popUp is displayed
    And page with name 'Mfg Part: Estimate - Cost Breakdown' is opened
    When I select 'Regression Test - Manufacturing (BOM)' in the 'Dialog View' dropdown
    And I wait for 3 seconds
    Then text of 'Dialog View' dropdown equals 'View: Regression Test - Manufacturing (BOM) (shared & preferred)'
    And 'Cost Breakdown' table contain row with following data:
      | Resource Group | REG_BOM_RESOURCE_1 |
    And info: ---15. Click the "+" icon to add a new row and verify that a new row is added to the Resource Grid.---
    And I click '+' in table 'Cost Breakdown' table for row with data:
      | Resource Group | REG_BOM_RESOURCE_1 |
    And info: ---16. For the new row, select Resource Group = REG_CONSULTANT1 and verify that Cost Element = Consultants - Local and Rate/Hr = $ 120.00/hr.---
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
    And info: ---20. Click Confirm and verify that the Mfg Part ... popup is closed.---
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
```

---

_Generated by `scripts/generate-testcase-guides.py` from the RTA mapping and local feature files._
