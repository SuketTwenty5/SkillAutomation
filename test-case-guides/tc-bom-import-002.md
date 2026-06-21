# TC-BOM-Import-002 - Cost Imported BOM

| Field | Value |
| --- | --- |
| Test ID | TC-BOM-Import-002 |
| Title | Cost Imported BOM |
| RTA page | https://twenty5.atlassian.net/wiki/spaces/RTA/pages/544342017/BTP+Gold+TC-BOM-Import-002+Cost+Imported+BOM |
| Map source | `RTA_TEST_SUITE_FEATURE_MAP.md` |

## Run Instructions

When a consultant asks to run this test, use the default app URL unless they provide another URL:

```text
https://approuter-twenty5ipe-dev.cfapps.us10.hana.ondemand.com/#quote
```

For Claude Desktop, ask before launching Selenium Chrome with the default URL. If the user provides another URL, launch that URL instead.

```bash
scripts/start-debug-chrome.sh "https://approuter-twenty5ipe-dev.cfapps.us10.hana.ondemand.com/#quote"
scripts/run-twentyfive-test.sh @TC-BOM-002
```

Duplicate feature matches exist for this Confluence page. The tag command may run more than one matching scenario until the canonical feature file is confirmed.

## Default mapped Selenium scenario

| Field | Value |
| --- | --- |
| Feature | 11. SAP BOM Import Scenario |
| Scenario | TC-BOM-Import-002: Cost Imported BOM |
| Tags | `@TC-BOM-002 @RUN` |
| File | `imported/twentyfive-regtest/tests/src/test/resources/features/testfolder/basicBOMImport.feature:181` |

### Scenario Sections

#### 1. Click the More menu in the the right-top corner of the screen

- I click on 'Proposal BOM' tab
- page with name 'Estimate Proposal BOM' is opened
- I click on 'Hamburger' menu

#### 2. Select Update & Cost Consolidated BOM > All parts

- I choose in 'Hamburger' menu the next menu chain:
- 'Consolidate and Cost Bill of Material' dialog is displayed

#### 3. Click Purchase History tab

- I click on 'Purchase History' tab
- 'Cost purchased and subcontract parts' radio is selected
- 'Exclude manual purchase history' radio is not selected
- 'Only consider current valid quotes or contracts' radio is not selected
- 'Escalate based on commitment' radio is not selected
- 'Disable all quantity curving' radio is not selected

#### 4. Click Production History tab

- I click on 'Production History' tab
- 'Cost make parts' radio is selected
- I verify 'Exclude Prod. Order Types from History' of Billing Item Dialog page is enabled, not required, drop down and is empty
- I verify 'Exclude Operation Control Keys from History' of Billing Item Dialog page is enabled, not required, drop down and is empty
- I verify 'Distribution Make Part Labor > 1 Month' of Billing Item Dialog page is enabled, not required, drop down and is empty

#### 5. Click Advanced Options tab

- I click on 'Advanced Options' tab
- I verify 'Per product/ service' of Billing Item Dialog page is enabled, not required, radio buttons and is checked
- I verify 'Per lead- time offsets' of Billing Item Dialog page is enabled, not required, radio buttons and is unchecked
- I verify 'To earliest requirement' of Billing Item Dialog page is enabled, not required, radio buttons and is unchecked
- I verify 'Curved to estimate' of Billing Item Dialog page is enabled, not required, radio buttons and is unchecked
- I verify 'Per assigned task' of Billing Item Dialog page is enabled, not required, radio buttons and is unchecked
- I verify 'Override Inflation or Escalation Index' of Billing Item Dialog page is enabled, not required, drop down and is empty
- I verify 'Include max. or optional sets in TINA thresholds' of Billing Item Dialog page is enabled, not required, check box and is unchecked
- I verify 'Include commercial & current contract items in TINA' of Billing Item Dialog page is enabled, not required, check box and is unchecked
- I verify 'Price quota sources based on full quantity' of Billing Item Dialog page is enabled, not required, check box and is unchecked
- I verify 'Absolute TINA values (negative adjustment are positive)' of Billing Item Dialog page is enabled, not required, check box and is unchecked
- I verify 'Escalate using Full Year Rates' of Billing Item Dialog page is enabled, not required, check box and is unchecked
- I verify 'Extended logging' of Billing Item Dialog page is enabled, not required, check box and is unchecked

#### 6. Click Run - All Parts button

- I click on 'Run All Parts' button
- 'Rolled-up the costed BOM' notification is displayed
- 'Cost consolidated bill of material has completed and material estimates will be reloaded' notification is displayed
- 'Consolidate and Cost Bill of Material' dialog is not displayed
- page with name 'Estimate Proposal BOM' is opened

#### 7. Click Procurement & Production tab

- I click on 'Procurement & Production' tab
- page with name 'Estimate Procurement & Production' is opened
- I select 'Regression Test - Manufacturing' in the 'View' dropdown
- I click on 'Save' button
- text of 'View' dropdown equals 'View: Regression Test - Manufacturing (shared & preferred)'
- 'Procurement & Production' table contain row with following data:
- 'Procurement & Production' table contain row with following data:
- 'Procurement & Production' table contain row with following data:

#### 8. Verify the Qty - Proposed or lot-size field

- 'Procurement & Production' table contain row with following data:
- 'Procurement & Production' table contain row with following data:
- 'Procurement & Production' table contain row with following data:

#### 9. Verify the Complexity Factor field

- 'Procurement & Production' table contain row with following data:
- 'Procurement & Production' table contain row with following data:
- 'Procurement & Production' table contain row with following data:

#### 10. Verify the Source Unit Cost field

- 'Procurement & Production' table contain row with following data:
- 'Procurement & Production' table contain row with following data:
- 'Procurement & Production' table contain row with following data:

#### 11. Verify the Start field

- 'Procurement & Production' table contain row with following data:
- 'Procurement & Production' table contain row with following data:
- 'Procurement & Production' table contain row with following data:

#### 12. Verify the End field

- 'Procurement & Production' table contain row with following data:
- 'Procurement & Production' table contain row with following data:
- 'Procurement & Production' table contain row with following data:

### Gherkin Excerpt

```gherkin
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

```

## Alternate mapped Selenium scenario 2

| Field | Value |
| --- | --- |
| Feature | 8. SAP BOM Import Scenario |
| Scenario | TC-BOM-Import-002: Cost Imported BOM |
| Tags | `@TC-BOM-002 @RUN` |
| File | `imported/twentyfive-regtest/tests/src/test/resources/features/testfolder/twentyfive2_4_SAP_BOM_import.feature:182` |

### Scenario Sections

#### 1. Click the More menu in the the right-top corner of the screen

- I click on 'Proposal BOM' tab
- page with name 'Estimate Proposal BOM' is opened
- I click on 'Hamburger' menu

#### 2. Select Update & Cost Consolidated BOM > All parts

- I choose in 'Hamburger' menu the next menu chain:
- 'Consolidate and Cost Bill of Material' dialog is displayed

#### 3. Click Purchase History tab

- I click on 'Purchase History' tab
- 'Cost purchased and subcontract parts' radio is selected
- 'Exclude manual purchase history' radio is not selected
- 'Only consider current valid quotes or contracts' radio is not selected
- 'Escalate based on commitment' radio is not selected
- 'Disable all quantity curving' radio is not selected

#### 4. Click Production History tab

- I click on 'Production History' tab
- 'Cost make parts' radio is selected
- I verify 'Exclude Prod. Order Types from History' of Billing Item Dialog page is enabled, not required, drop down and is empty
- I verify 'Exclude Operation Control Keys from History' of Billing Item Dialog page is enabled, not required, drop down and is empty
- I verify 'Distribution Make Part Labor > 1 Month' of Billing Item Dialog page is enabled, not required, drop down and is empty

#### 5. Click Advanced Options tab

- I click on 'Advanced Options' tab
- I verify 'Per product/ service' of Billing Item Dialog page is enabled, not required, radio buttons and is checked
- I verify 'Per lead- time offsets' of Billing Item Dialog page is enabled, not required, radio buttons and is unchecked
- I verify 'To earliest requirement' of Billing Item Dialog page is enabled, not required, radio buttons and is unchecked
- I verify 'Curved to estimate' of Billing Item Dialog page is enabled, not required, radio buttons and is unchecked
- I verify 'Per assigned task' of Billing Item Dialog page is enabled, not required, radio buttons and is unchecked
- I verify 'Override Inflation or Escalation Index' of Billing Item Dialog page is enabled, not required, drop down and is empty
- I verify 'Include max. or optional sets in TINA thresholds' of Billing Item Dialog page is enabled, not required, check box and is unchecked
- I verify 'Include commercial & current contract items in TINA' of Billing Item Dialog page is enabled, not required, check box and is unchecked
- I verify 'Price quota sources based on full quantity' of Billing Item Dialog page is enabled, not required, check box and is unchecked
- I verify 'Absolute TINA values (negative adjustment are positive)' of Billing Item Dialog page is enabled, not required, check box and is unchecked
- I verify 'Escalate using Full Year Rates' of Billing Item Dialog page is enabled, not required, check box and is unchecked
- I verify 'Extended logging' of Billing Item Dialog page is enabled, not required, check box and is unchecked

#### 6. Click Run - All Parts button

- I click on 'Run All Parts' button
- 'Rolled-up the costed BOM' notification is displayed
- 'Cost consolidated bill of material has completed and material estimates will be reloaded' notification is displayed
- 'Consolidate and Cost Bill of Material' dialog is not displayed
- page with name 'Estimate Proposal BOM' is opened

#### 7. Click Procurement & Production tab

- I click on 'Procurement & Production' tab
- page with name 'Estimate Procurement & Production' is opened
- I select 'Regression Test - Manufacturing' in the 'View' dropdown
- I click on 'Save' button
- text of 'View' dropdown equals 'View: Regression Test - Manufacturing (shared & preferred)'
- 'Procurement & Production' table contain row with following data:
- 'Procurement & Production' table contain row with following data:
- 'Procurement & Production' table contain row with following data:

#### 8. Verify the Qty - Proposed or lot-size field

- 'Procurement & Production' table contain row with following data:
- 'Procurement & Production' table contain row with following data:
- 'Procurement & Production' table contain row with following data:

#### 9. Verify the Complexity Factor field

- 'Procurement & Production' table contain row with following data:
- 'Procurement & Production' table contain row with following data:
- 'Procurement & Production' table contain row with following data:

#### 10. Verify the Source Unit Cost field

- 'Procurement & Production' table contain row with following data:
- 'Procurement & Production' table contain row with following data:
- 'Procurement & Production' table contain row with following data:

#### 11. Verify the Start field

- 'Procurement & Production' table contain row with following data:
- 'Procurement & Production' table contain row with following data:
- 'Procurement & Production' table contain row with following data:

#### 12. Verify the End field

- 'Procurement & Production' table contain row with following data:
- 'Procurement & Production' table contain row with following data:
- 'Procurement & Production' table contain row with following data:

### Gherkin Excerpt

```gherkin
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

```

---

_Generated by `scripts/generate-testcase-guides.py` from the RTA mapping and local feature files._
