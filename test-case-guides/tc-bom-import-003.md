# TC-BOM-Import-003 - Check costed BOM items purchased

| Field | Value |
| --- | --- |
| Test ID | TC-BOM-Import-003 |
| Title | Check costed BOM items purchased |
| RTA page | https://twenty5.atlassian.net/wiki/spaces/RTA/pages/554500097/BTP+Gold+TC-BOM-Import-003+Check+costed+BOM+items+purchased |
| Map source | `RTA_TEST_SUITE_FEATURE_MAP.md` |

## Run Instructions

When a consultant asks to run this test, use the default app URL unless they provide another URL:

```text
https://approuter-twenty5ipe-dev.cfapps.us10.hana.ondemand.com/#quote
```

For Claude Desktop, ask before launching Selenium Chrome with the default URL. If the user provides another URL, launch that URL instead.

```bash
scripts/start-debug-chrome.sh "https://approuter-twenty5ipe-dev.cfapps.us10.hana.ondemand.com/#quote"
scripts/run-twentyfive-test.sh @TC-BOM-003
```

Duplicate feature matches exist for this Confluence page. The tag command may run more than one matching scenario until the canonical feature file is confirmed.

## Default mapped Selenium scenario

| Field | Value |
| --- | --- |
| Feature | 11. SAP BOM Import Scenario |
| Scenario | TC-BOM-Import-003: Check costed BOM items (purchased) |
| Tags | `@TC-BOM-003 @RUN` |
| File | `imported/twentyfive-regtest/tests/src/test/resources/features/testfolder/basicBOMImport.feature:288` |

### Scenario Sections

#### 1. For the row REG_MATERIAL_BOM_ITEM_1, click the Gear icon [] and select Edit Details. Verify that the "Purchased For ..." popup is displayed.

- I click on 'Procurement & Production' tab
- page with name 'Estimate Procurement & Production' is opened
- I select 'Regression Test - Manufacturing' in the 'View' dropdown
- I click on 'Save' button
- text of 'View' dropdown equals 'View: Regression Test - Manufacturing (shared & preferred)'
- I hover 'Description' cell in table 'Procurement & Production' table and click  for row with data:
- I click on 'Edit Details' menuItem
- page with name 'Purchased Part: Estimate - Cost Estimate' is opened

#### 2. Verify that Source Doc REG_PURCHASE2/2 is checked as Use in the Purchase History grid.

- the cell value of the 'Use' column of the 'Cost Estimate' table is checked for a row with the following data:

#### 3. Verify that the Source Unit Cost field is equal to $ 138,000.00/ea.

- text of 'Source Unit Cost' field equals '138,000.00'

#### 4. Remove the value from the Escalation Index field and verify it is empty. Verify that the Escalation Factor field is equal to 1.00.

- I enter data into the next fields:
- text of 'Estimating Source' dropdown equals ''
- I enter data into the next fields:
- text of 'Escalation Index' dropdown equals ''
- I enter data into the next fields:
- text of 'Escalation Factor' field equals '1.00'

#### 5. Set the value for QAF - Qty Adj Factor to the Test Data Value 2.00 and verify that the field is updated accordingly.

- I enter data into the next fields:
- I wait for 3 seconds
- text of 'QAF Qty Adj Factor' field equals '2.00'

#### 6. Verify that the Changed Adj Unit Cost field is equal to $ 276,000.00/ea.

- text of 'Changed Adj Unit Cost' field equals '276,000.00'

#### 7. Set the value for QAF - Qty Adj Factor to the Test Data Value 1.00 and verify that the field is updated accordingly.

- I enter data into the next fields:
- text of 'QAF Qty Adj Factor' field equals '1.00'

#### 8. Verify that the Changed Adj Unit Cost field is equal to $ 138,000.00/ea.

- I wait for 3 seconds
- text of 'Changed Adj Unit Cost' field equals '138,000.00'

#### 9. Verify that the Total Cost field is equal to $ 276,000.00.

- text of 'Total Cost' field equals '276,000.00'

#### 10. Click the Confirm button and verify that the Retain Changes popup is displayed.

- I click on 'Confirm' button
- 'Retain Changes' popup is displayed

#### 11. Click No - Retain for Costing and verify that the "Data saved successfully" message is displayed.

- I click on 'No Retain for Costing' button
- 'Data saved successfully' warning message is displayed in 30 seconds

#### 12. Click Confirm again and verify that the Purchased For popup is closed.

- I click on 'Confirm' button
- page with name 'Estimate Procurement & Production' is opened

#### 13. For the row REG_MATERIAL_BOM_ITEM_1, verify that the Qty - Proposed or lot-size field is equal to 2 ea and the Cost in Company Currency field is equal to $ 276,000.00.

- 'Procurement & Production' table contain row with following data:

### Gherkin Excerpt

```gherkin
  Scenario: TC-BOM-Import-003: Check costed BOM items (purchased)
    And info: ---1. For the row REG_MATERIAL_BOM_ITEM_1, click the Gear icon [] and select Edit Details. Verify that the "Purchased For ..." popup is displayed.---
    And I click on 'Procurement & Production' tab
    Then page with name 'Estimate Procurement & Production' is opened
    When I select 'Regression Test - Manufacturing' in the 'View' dropdown
    And I click on 'Save' button
    Then text of 'View' dropdown equals 'View: Regression Test - Manufacturing (shared & preferred)'
    And I hover 'Description' cell in table 'Procurement & Production' table and click  for row with data:
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
    And info: ---5. Set the value for QAF - Qty Adj Factor to the Test Data Value 2.00 and verify that the field is updated accordingly.---
    When I enter data into the next fields:
      | 'QAF Qty Adj Factor' field  |  2.00  |
    And I wait for 3 seconds
    And text of 'QAF Qty Adj Factor' field equals '2.00'
    And info: ---6. Verify that the Changed Adj Unit Cost field is equal to $ 276,000.00/ea.---
    Then text of 'Changed Adj Unit Cost' field equals '276,000.00'
    And info: ---7. Set the value for QAF - Qty Adj Factor to the Test Data Value 1.00 and verify that the field is updated accordingly.---
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
    And info: ---11. Click No - Retain for Costing and verify that the "Data saved successfully" message is displayed.---
    And I click on 'No Retain for Costing' button
    And 'Data saved successfully' warning message is displayed in 30 seconds
    And info: ---12. Click Confirm again and verify that the Purchased For popup is closed.---
    And I click on 'Confirm' button
    Then page with name 'Estimate Procurement & Production' is opened
    And info: ---13. For the row REG_MATERIAL_BOM_ITEM_1, verify that the Qty - Proposed or lot-size field is equal to 2 ea and the Cost in Company Currency field is equal to $ 276,000.00.---
    And 'Procurement & Production' table contain row with following data:
        | Description                | REG_MATERIAL_BOM_ITEM_1 |
        | Qty - Proposed or lot-size | 2 ea                    |
        | Cost in Company Currency   | $ 276,000.00            |

```

## Alternate mapped Selenium scenario 2

| Field | Value |
| --- | --- |
| Feature | 8. SAP BOM Import Scenario |
| Scenario | TC-BOM-Import-003: Check costed BOM items (purchased) |
| Tags | `@TC-BOM-003 @RUN` |
| File | `imported/twentyfive-regtest/tests/src/test/resources/features/testfolder/twentyfive2_4_SAP_BOM_import.feature:289` |

### Scenario Sections

#### 1. For the row REG_MATERIAL_BOM_ITEM_1, click the Gear icon [] and select Edit Details. Verify that the "Purchased For ..." popup is displayed.

- I click on 'Procurement & Production' tab
- page with name 'Estimate Procurement & Production' is opened
- I select 'Regression Test - Manufacturing' in the 'View' dropdown
- I click on 'Save' button
- text of 'View' dropdown equals 'View: Regression Test - Manufacturing (shared & preferred)'
- I hover 'Description' cell in table 'Procurement & Production' table and click  for row with data:
- I click on 'Edit Details' menuItem
- page with name 'Purchased Part: Estimate - Cost Estimate' is opened

#### 2. Verify that Source Doc REG_PURCHASE2/2 is checked as Use in the Purchase History grid.

- the cell value of the 'Use' column of the 'Cost Estimate' table is checked for a row with the following data:

#### 3. Verify that the Source Unit Cost field is equal to $ 138,000.00/ea.

- text of 'Source Unit Cost' field equals '138,000.00'

#### 4. Remove the value from the Escalation Index field and verify it is empty. Verify that the Escalation Factor field is equal to 1.00.

- I enter data into the next fields:
- text of 'Estimating Source' dropdown equals ''
- I enter data into the next fields:
- text of 'Escalation Index' dropdown equals ''
- I enter data into the next fields:
- text of 'Escalation Factor' field equals '1.00'

#### 5. Set the value for QAF - Qty Adj Factor to the Test Data Value 2.00 and verify that the field is updated accordingly.

- I enter data into the next fields:
- I wait for 3 seconds
- text of 'QAF Qty Adj Factor' field equals '2.00'

#### 6. Verify that the Changed Adj Unit Cost field is equal to $ 276,000.00/ea.

- text of 'Changed Adj Unit Cost' field equals '276,000.00'

#### 7. Set the value for QAF - Qty Adj Factor to the Test Data Value 1.00 and verify that the field is updated accordingly.

- I enter data into the next fields:
- text of 'QAF Qty Adj Factor' field equals '1.00'

#### 8. Verify that the Changed Adj Unit Cost field is equal to $ 138,000.00/ea.

- I wait for 3 seconds
- text of 'Changed Adj Unit Cost' field equals '138,000.00'

#### 9. Verify that the Total Cost field is equal to $ 276,000.00.

- text of 'Total Cost' field equals '276,000.00'

#### 10. Click the Confirm button and verify that the Retain Changes popup is displayed.

- I click on 'Confirm' button
- 'Retain Changes' popup is displayed

#### 11. Click No - Retain for Costing and verify that the "Data saved successfully" message is displayed.

- I click on 'No Retain for Costing' button
- 'Data saved successfully' warning message is displayed in 30 seconds

#### 12. Click Confirm again and verify that the Purchased For popup is closed.

- I click on 'Confirm' button
- page with name 'Estimate Procurement & Production' is opened

#### 13. For the row REG_MATERIAL_BOM_ITEM_1, verify that the Qty - Proposed or lot-size field is equal to 2 ea and the Cost in Company Currency field is equal to $ 276,000.00.

- 'Procurement & Production' table contain row with following data:

### Gherkin Excerpt

```gherkin
  Scenario: TC-BOM-Import-003: Check costed BOM items (purchased)
    And info: ---1. For the row REG_MATERIAL_BOM_ITEM_1, click the Gear icon [] and select Edit Details. Verify that the "Purchased For ..." popup is displayed.---
    And I click on 'Procurement & Production' tab
    Then page with name 'Estimate Procurement & Production' is opened
    When I select 'Regression Test - Manufacturing' in the 'View' dropdown
    And I click on 'Save' button
    Then text of 'View' dropdown equals 'View: Regression Test - Manufacturing (shared & preferred)'
    And I hover 'Description' cell in table 'Procurement & Production' table and click  for row with data:
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
    And info: ---5. Set the value for QAF - Qty Adj Factor to the Test Data Value 2.00 and verify that the field is updated accordingly.---
    When I enter data into the next fields:
      | 'QAF Qty Adj Factor' field  |  2.00  |
    And I wait for 3 seconds
    And text of 'QAF Qty Adj Factor' field equals '2.00'
    And info: ---6. Verify that the Changed Adj Unit Cost field is equal to $ 276,000.00/ea.---
    Then text of 'Changed Adj Unit Cost' field equals '276,000.00'
    And info: ---7. Set the value for QAF - Qty Adj Factor to the Test Data Value 1.00 and verify that the field is updated accordingly.---
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
    And info: ---11. Click No - Retain for Costing and verify that the "Data saved successfully" message is displayed.---
    And I click on 'No Retain for Costing' button
    And 'Data saved successfully' warning message is displayed in 30 seconds
    And info: ---12. Click Confirm again and verify that the Purchased For popup is closed.---
    And I click on 'Confirm' button
    Then page with name 'Estimate Procurement & Production' is opened
    And info: ---13. For the row REG_MATERIAL_BOM_ITEM_1, verify that the Qty - Proposed or lot-size field is equal to 2 ea and the Cost in Company Currency field is equal to $ 276,000.00.---
    And 'Procurement & Production' table contain row with following data:
      | Description                | REG_MATERIAL_BOM_ITEM_1 |
      | Qty - Proposed or lot-size | 2 ea                    |
      | Cost in Company Currency   | $ 276,000.00            |

```

---

_Generated by `scripts/generate-testcase-guides.py` from the RTA mapping and local feature files._
