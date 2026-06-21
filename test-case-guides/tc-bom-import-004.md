# TC-BOM-Import-004 - Check costed BOM items manufactured

| Field | Value |
| --- | --- |
| Test ID | TC-BOM-Import-004 |
| Title | Check costed BOM items manufactured |
| RTA page | https://twenty5.atlassian.net/wiki/spaces/RTA/pages/552566790/BTP+Gold+TC-BOM-Import-004+Check+costed+BOM+items+manufactured |
| Map source | `RTA_TEST_SUITE_FEATURE_MAP.md` |

## Run Instructions

When a consultant asks to run this test, use the default app URL unless they provide another URL:

```text
https://approuter-twenty5ipe-dev.cfapps.us10.hana.ondemand.com/#quote
```

For Claude Desktop, ask before launching Selenium Chrome with the default URL. If the user provides another URL, launch that URL instead.

```bash
scripts/start-debug-chrome.sh "https://approuter-twenty5ipe-dev.cfapps.us10.hana.ondemand.com/#quote"
scripts/run-twentyfive-test.sh @TC-BOM-004
```

Duplicate feature matches exist for this Confluence page. The tag command may run more than one matching scenario until the canonical feature file is confirmed.

## Default mapped Selenium scenario

| Field | Value |
| --- | --- |
| Feature | 11. SAP BOM Import Scenario |
| Scenario | TC-BOM-Import-004: Check costed BOM items (manufactured) |
| Tags | `@TC-BOM-004 @RUN` |
| File | `imported/twentyfive-regtest/tests/src/test/resources/features/testfolder/basicBOMImport.feature:346` |

### Scenario Sections

#### 1. For the row REG_MATERIAL_BOM_ITEM_2, click the Gear icon [] and select Edit Details. Verify that the "Mfg Part ..." popup is displayed and the Cost Breakdown tab is opened.

- I click on 'Procurement & Production' tab
- page with name 'Estimate Procurement & Production' is opened
- I select 'Regression Test - Manufacturing' in the 'View' dropdown
- I click on 'Save' button
- text of 'View' dropdown equals 'View: Regression Test - Manufacturing (shared & preferred)'
- I hover 'Description' cell in table 'Procurement & Production' table and click  for row with data:
- I click on 'Edit Details' menuItem
- 'Mfg Part' popUp is displayed
- page with name 'Purchased Part: Estimate - Cost Breakdown' is opened

#### 2. Select the view Regression Test - Manufacturing (BOM) if it is not already selected and verify that the grid is displayed according to the selected view. Verify that the grid contains the following values: Resource Group = REG_BOM_RESOURCE_1, Cost Element = Subcontractors, Work Center = QUALITY, Setup Hrs = 100.00 hours, Run Time = 200.00 hours, Rate/Hr = $ 89.00/hr, Total Time = 500.00 hours, and Total Cost = $ 44,500.00.

- I select 'Regression Test - Manufacturing (BOM)' in the 'Dialog View' dropdown
- text of 'Dialog View' dropdown equals 'View: Regression Test - Manufacturing (BOM) (shared & preferred)'
- 'Cost Breakdown' table contain row with following data:

#### 3. Verify that Part Number = REG_MATERIAL_BOM_ITEM_2, Description = REG_MATERIAL_BOM_ITEM_2, Unit Cost = $ 17,800.00/ea (ignore decimals after 2 places), Estimating Source is empty, Supply Site = US - New York, Adj. Unit Cost = $ 22,250.00/ea (ignore decimals after 2 places), Total Cost = $ 44,500.00, Total Setup Hours = 100.00 hours, Sum of Run Hrs/Unit = 200.00 hours, Realization Factor = 400.00, and Total Effort = 500.00 hours.

- I verify 'Part Number' of Cost Breakdown Dialog Table page is enabled, not required, free text and has text 'REG_MATERIAL_BOM_ITEM_2'
- I verify 'Description' of Cost Breakdown Dialog Table page is enabled, not required, free text and has text 'REG_MATERIAL_BOM_ITEM_2'
- I verify 'Unit Cost' of Cost Breakdown Dialog Table page is enabled, not required, free text and has text '17800'
- I verify 'Supply Site' of Cost Breakdown Dialog Table page is enabled, required, drop down and has text 'US - New York'
- I verify 'Adj Unit Cost' of Cost Breakdown Dialog Table page is enabled, not required, free text and has text '22250'
- I verify 'Total Cost' of Cost Breakdown Dialog Table page is enabled, not required, free text and has text '44500'
- I verify 'Total Setup Hours' of Cost Breakdown Dialog Table page is enabled, not required, free text and has text '100.00 hours'
- I verify 'Sum of Run Hrs/Unit' of Cost Breakdown Dialog Table page is enabled, not required, free text and has text '200.00 hours'
- I verify 'Realization Factor' of Cost Breakdown Dialog Table page is enabled, not required, free text and has text '400.00'
- I verify 'Total Effort' of Cost Breakdown Dialog Table page is enabled, not required, free text and has text '500.00 hours'

#### 4. Click the Confirm button and verify that the Mfg Part ... popup is closed.

- I click on 'Confirm' button
- page with name 'Estimate Procurement & Production' is opened
- 'Mfg Part' popUp is not displayed
- I click on 'Update Cost & Prices' link
- I wait for 30 seconds
- I refresh page
- I wait for 30 seconds
- I click on 'Procurement & Production' tab
- page with name 'Estimate Procurement & Production' is opened
- I select 'Regression Test - Manufacturing' in the 'View' dropdown
- I click on 'Save' button
- text of 'View' dropdown equals 'View: Regression Test - Manufacturing (shared & preferred)'

#### 5. For the row REG_MATERIAL_BOM_ITEM_2, verify that the Qty - Proposed or lot-size field is equal to 2 ea and the Cost in Company Currency field is equal to $ 44,500.00.

- 'Procurement & Production' table contain row with following data:

#### 6. For the row REG_MATERIAL_BOM, verify that the Qty - Proposed or lot-size field is equal to 1 ea and the Cost in Company Currency field is equal to $ 13,350.00.

- 'Procurement & Production' table contain row with following data:

### Gherkin Excerpt

```gherkin
  Scenario: TC-BOM-Import-004: Check costed BOM items (manufactured)
    And info: ---1. For the row REG_MATERIAL_BOM_ITEM_2, click the Gear icon [] and select Edit Details. Verify that the "Mfg Part ..." popup is displayed and the Cost Breakdown tab is opened.---
    And I click on 'Procurement & Production' tab
    Then page with name 'Estimate Procurement & Production' is opened
    When I select 'Regression Test - Manufacturing' in the 'View' dropdown
    And I click on 'Save' button
    Then text of 'View' dropdown equals 'View: Regression Test - Manufacturing (shared & preferred)'
    And I hover 'Description' cell in table 'Procurement & Production' table and click  for row with data:
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
    And info: ---4. Click the Confirm button and verify that the Mfg Part ... popup is closed.---
    And I click on 'Confirm' button
    Then page with name 'Estimate Procurement & Production' is opened
    And 'Mfg Part' popUp is not displayed
    And I click on 'Update Cost & Prices' link
    And I wait for 30 seconds
    And I refresh page
    And I wait for 30 seconds
    And I click on 'Procurement & Production' tab
    Then page with name 'Estimate Procurement & Production' is opened
    When I select 'Regression Test - Manufacturing' in the 'View' dropdown
    And I click on 'Save' button
    Then text of 'View' dropdown equals 'View: Regression Test - Manufacturing (shared & preferred)'
    And info: ---5. For the row REG_MATERIAL_BOM_ITEM_2, verify that the Qty - Proposed or lot-size field is equal to 2 ea and the Cost in Company Currency field is equal to $ 44,500.00.---
    And 'Procurement & Production' table contain row with following data:
      | Description                | REG_MATERIAL_BOM_ITEM_2 |
      | Qty - Proposed or lot-size | 2 ea                    |
      | Cost in Company Currency   | $ 44,500.00             |
    And info: ---6. For the row REG_MATERIAL_BOM, verify that the Qty - Proposed or lot-size field is equal to 1 ea and the Cost in Company Currency field is equal to $ 13,350.00.---
    And 'Procurement & Production' table contain row with following data:
      | Description                | REG_MATERIAL_BOM |
      | Qty - Proposed or lot-size | 1 ea             |
      | Cost in Company Currency   | $ 13,350.00      |

```

## Alternate mapped Selenium scenario 2

| Field | Value |
| --- | --- |
| Feature | 8. SAP BOM Import Scenario |
| Scenario | TC-BOM-Import-004: Check costed BOM items (manufactured) |
| Tags | `@TC-BOM-004 @RUN` |
| File | `imported/twentyfive-regtest/tests/src/test/resources/features/testfolder/twentyfive2_4_SAP_BOM_import.feature:347` |

### Scenario Sections

#### 1. For the row REG_MATERIAL_BOM_ITEM_2, click the Gear icon [] and select Edit Details. Verify that the "Mfg Part ..." popup is displayed and the Cost Breakdown tab is opened.

- I click on 'Procurement & Production' tab
- page with name 'Estimate Procurement & Production' is opened
- I select 'Regression Test - Manufacturing' in the 'View' dropdown
- I click on 'Save' button
- text of 'View' dropdown equals 'View: Regression Test - Manufacturing (shared & preferred)'
- I hover 'Description' cell in table 'Procurement & Production' table and click  for row with data:
- I click on 'Edit Details' menuItem
- 'Mfg Part' popUp is displayed
- page with name 'Purchased Part: Estimate - Cost Breakdown' is opened

#### 2. Select the view Regression Test - Manufacturing (BOM) if it is not already selected and verify that the grid is displayed according to the selected view. Verify that the grid contains the following values: Resource Group = REG_BOM_RESOURCE_1, Cost Element = Subcontractors, Work Center = QUALITY, Setup Hrs = 100.00 hours, Run Time = 200.00 hours, Rate/Hr = $ 89.00/hr, Total Time = 500.00 hours, and Total Cost = $ 44,500.00.

- I select 'Regression Test - Manufacturing (BOM)' in the 'Dialog View' dropdown
- text of 'Dialog View' dropdown equals 'View: Regression Test - Manufacturing (BOM) (shared & preferred)'
- 'Cost Breakdown' table contain row with following data:

#### 3. Verify that Part Number = REG_MATERIAL_BOM_ITEM_2, Description = REG_MATERIAL_BOM_ITEM_2, Unit Cost = $ 17,800.00/ea (ignore decimals after 2 places), Estimating Source is empty, Supply Site = US - New York, Adj. Unit Cost = $ 22,250.00/ea (ignore decimals after 2 places), Total Cost = $ 44,500.00, Total Setup Hours = 100.00 hours, Sum of Run Hrs/Unit = 200.00 hours, Realization Factor = 400.00, and Total Effort = 500.00 hours.

- I verify 'Part Number' of Cost Breakdown Dialog Table page is enabled, not required, free text and has text 'REG_MATERIAL_BOM_ITEM_2'
- I verify 'Description' of Cost Breakdown Dialog Table page is enabled, not required, free text and has text 'REG_MATERIAL_BOM_ITEM_2'
- I verify 'Unit Cost' of Cost Breakdown Dialog Table page is enabled, not required, free text and has text '17800'
- I verify 'Supply Site' of Cost Breakdown Dialog Table page is enabled, required, drop down and has text 'US - New York'
- I verify 'Adj Unit Cost' of Cost Breakdown Dialog Table page is enabled, not required, free text and has text '22250'
- I verify 'Total Cost' of Cost Breakdown Dialog Table page is enabled, not required, free text and has text '44500'
- I verify 'Total Setup Hours' of Cost Breakdown Dialog Table page is enabled, not required, free text and has text '100.00 hours'
- I verify 'Sum of Run Hrs/Unit' of Cost Breakdown Dialog Table page is enabled, not required, free text and has text '200.00 hours'
- I verify 'Realization Factor' of Cost Breakdown Dialog Table page is enabled, not required, free text and has text '400.00'
- I verify 'Total Effort' of Cost Breakdown Dialog Table page is enabled, not required, free text and has text '500.00 hours'

#### 4. Click the Confirm button and verify that the Mfg Part ... popup is closed.

- I click on 'Confirm' button
- page with name 'Estimate Procurement & Production' is opened
- 'Mfg Part' popUp is not displayed

#### 5. For the row REG_MATERIAL_BOM_ITEM_2, verify that the Qty - Proposed or lot-size field is equal to 2 ea and the Cost in Company Currency field is equal to $ 44,500.00.

- 'Procurement & Production' table contain row with following data:

#### 6. For the row REG_MATERIAL_BOM, verify that the Qty - Proposed or lot-size field is equal to 1 ea and the Cost in Company Currency field is equal to $ 13,350.00.

- 'Procurement & Production' table contain row with following data:

### Gherkin Excerpt

```gherkin
  Scenario: TC-BOM-Import-004: Check costed BOM items (manufactured)
    And info: ---1. For the row REG_MATERIAL_BOM_ITEM_2, click the Gear icon [] and select Edit Details. Verify that the "Mfg Part ..." popup is displayed and the Cost Breakdown tab is opened.---
    And I click on 'Procurement & Production' tab
    Then page with name 'Estimate Procurement & Production' is opened
    When I select 'Regression Test - Manufacturing' in the 'View' dropdown
    And I click on 'Save' button
    Then text of 'View' dropdown equals 'View: Regression Test - Manufacturing (shared & preferred)'
    And I hover 'Description' cell in table 'Procurement & Production' table and click  for row with data:
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
    And info: ---4. Click the Confirm button and verify that the Mfg Part ... popup is closed.---
    And I click on 'Confirm' button
    Then page with name 'Estimate Procurement & Production' is opened
    And 'Mfg Part' popUp is not displayed
    And info: ---5. For the row REG_MATERIAL_BOM_ITEM_2, verify that the Qty - Proposed or lot-size field is equal to 2 ea and the Cost in Company Currency field is equal to $ 44,500.00.---
    And 'Procurement & Production' table contain row with following data:
      | Description                | REG_MATERIAL_BOM_ITEM_2 |
      | Qty - Proposed or lot-size | 2 ea                    |
      | Cost in Company Currency   | $ 44,500.00             |
    And info: ---6. For the row REG_MATERIAL_BOM, verify that the Qty - Proposed or lot-size field is equal to 1 ea and the Cost in Company Currency field is equal to $ 13,350.00.---
    And 'Procurement & Production' table contain row with following data:
      | Description                | REG_MATERIAL_BOM |
      | Qty - Proposed or lot-size | 1 ea             |
      | Cost in Company Currency   | $ 13,350.00      |

```

---

_Generated by `scripts/generate-testcase-guides.py` from the RTA mapping and local feature files._
