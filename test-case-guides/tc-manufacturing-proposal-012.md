# TC-Manufacturing-Proposal-012 - Material Depreciation

| Field | Value |
| --- | --- |
| Test ID | TC-Manufacturing-Proposal-012 |
| Title | Material Depreciation |
| RTA page | https://twenty5.atlassian.net/wiki/spaces/RTA/pages/279773185/TC-Manufacturing-Proposal-012+Material+Depreciation |
| Map source | `RTA_TEST_SUITE_FEATURE_MAP.md` |

## Run Instructions

When a consultant asks to run this test, use the default app URL unless they provide another URL:

```text
https://approuter-twenty5ipe-dev.cfapps.us10.hana.ondemand.com/#quote
```

For Claude Desktop, ask before launching Selenium Chrome with the default URL. If the user provides another URL, launch that URL instead.

```bash
scripts/start-debug-chrome.sh "https://approuter-twenty5ipe-dev.cfapps.us10.hana.ondemand.com/#quote"
scripts/run-twentyfive-test.sh @TCM-012
```

Duplicate feature matches exist for this Confluence page. The tag command may run more than one matching scenario until the canonical feature file is confirmed.

## Default mapped Selenium scenario

| Field | Value |
| --- | --- |
| Feature | 7. Amortization & Depreciation for Manufacturing Proposal |
| Scenario | TC-Manufacturing-Proposal-012: Material Depreciation |
| Tags | `@TCM-012 @END` |
| File | `imported/twentyfive-regtest/tests/src/test/resources/features/testfolder/amortizationAndDepreciationforManufacturing.feature:145` |

### Scenario Sections

#### Setup

- I close current browser tab
- I switch to tab 1

#### 1. Navigate to the Procurement & Production tab

- I click on 'Procurement & Production' tab
- page with name 'Estimate Procurement & Production' is opened
- I select 'Regression Test - Manufacturing' in the 'View' dropdown
- I click on 'Save' button
- text of 'View' dropdown equals 'View: Regression Test - Manufacturing (preferred)'
- page with name 'Estimate Procurement & Production' is opened

#### 2. For REG_MATERIAL1 click the Gear icon and click Edit Details

- I hover 'Description' cell in table 'Procurement & Production' table and click  for row with data:
- I click on 'Edit Details' menuItem
- 'Estimate for Labor' popup is displayed
- I wait for 7 seconds

#### 3. Open the Asset Depreciation tab on the popup

- I click on 'Asset Depreciation' tab
- I wait for 7 seconds

#### 4. Verify that: Total Value field = $ 141,240.00 (Source Unit Cost + Overhead + G&A)

- text of 'Total Value' dropdown equals '141,240.00'

#### 5. In the Depreciation Method field select Test Data Value

- I enter data into the next fields:
- I click on 'Estimate for Labor' popup
- text of 'Depreciation Method' field equals 'Straight Line'

#### 6. In the In-Service Date (start of depreciation) field enter Test Data Value

- I enter data into the next fields:
- I click on 'Estimate for Labor' popup
- I wait for 3 seconds
- text of 'Start of Depreciation' datePicker equals '10/1/25'

#### 7. Click the checkbox Transfer asset to customer at contract end and verify depreciation details

- I check 'End of Depreciation' checkBox
- I click on 'Estimate for Labor' popup
- text of 'End of Depreciation' datePicker equals '3/31/26'
- I verify 'Depreciation Period (months)' of Labor page is enabled, not required, numeric selection and has text '6'
- 'Amortization Schedule' table contain row with following data:
- 'Amortization Schedule' table contain row with following data:
- 'Amortization Schedule' table contain row with following data:
- 'Amortization Schedule' table contain row with following data:
- 'Amortization Schedule' table contain row with following data:
- 'Amortization Schedule' table contain row with following data:
- Grand total cell value of the 'Incurred Cost' column of the 'Amortization Schedule' table equals '$ 141,240.00'
- Grand total cell value of the 'Depreciation' column of the 'Amortization Schedule' table equals '$ 141,240.00'

#### 8. Click Confirm button

- I click on 'Confirm' button
- 'Estimate for Labor' popup is not displayed
- page with name 'Estimate Procurement & Production' is opened

#### 9. For REG_MATERIAL1 Copy click the Gear icon and click Edit Details

- I hover 'Description' cell in table 'Procurement & Production' table and click  for row with data:
- I click on 'Edit Details' menuItem
- 'Estimate for Labor' popup is displayed
- I wait for 7 seconds
- I click on 'Asset Depreciation' tab
- I wait for 7 seconds

#### 10. Verify that: Total Value field = $70,620.00 (Source Unit Cost + Overhead + G&A)

- text of 'Total Value' dropdown equals '70,620.00'

#### 11. In the Depreciation Method field select Test Data Value

- I enter data into the next fields:
- I click on 'Estimate for Labor' popup
- text of 'Depreciation Method' field equals 'Straight Line'

#### 12. Click the checkbox Start depreciation at incurred start (7/1/25)

- I check 'Start of Depreciation' checkBox
- I click on 'Estimate for Labor' popup
- text of 'Start of Depreciation' datePicker equals '7/1/25'

#### 13. In the End of Life (end of depreciation) field enter Test Data Value and verify depreciation schedule

- I enter data into the next fields:
- I click on 'Estimate for Labor' popup
- I wait for 7 seconds
- text of 'End of Depreciation' datePicker equals '12/31/25'
- I verify 'Depreciation Period (months)' of Labor page is enabled, not required, numeric selection and has text '6'
- 'Amortization Schedule' table contain row with following data:
- 'Amortization Schedule' table contain row with following data:
- 'Amortization Schedule' table contain row with following data:
- 'Amortization Schedule' table contain row with following data:
- 'Amortization Schedule' table contain row with following data:
- 'Amortization Schedule' table contain row with following data:
- Grand total cell value of the 'Incurred Cost' column of the 'Amortization Schedule' table equals '$ 70,620.00'
- Grand total cell value of the 'Depreciation' column of the 'Amortization Schedule' table equals '$ 70,620.00'

#### 14. Click Confirm button

- I click on 'Confirm' button
- 'Estimate for Labor' popup is not displayed
- page with name 'Estimate Procurement & Production' is opened
- I click on 'Save' button
- page with name 'Estimate Procurement & Production' is opened
- I click on 'Update Estimate totals' link
- I wait for 30 seconds
- page with name 'Estimate Procurement & Production' is opened

#### 15. Via the menu, go to Open  Open Cost Price Analysis and ensure Workbench tab is shown

- I click on 'Hamburger' menu
- I choose in 'Hamburger' menu the next menu chain:
- page with name 'Estimate Procurement & Production' is opened
- I switch to tab 2
- I wait for 10 seconds
- I click on 'Workbench' tab
- page with name 'Workbench page' is opened

#### 16. Select view Regression Test - Amortization & Depreciation

- I select 'Regression Test - Amortization & Depreciation' in the 'View' dropdown
- page with name 'Workbench page' is opened

#### 17. Expand: Metrics, Depreciation or Amortization, Material costs, Years: 2025, 2026

- I expand collapsed items
- page with name 'Workbench page' is opened

#### 18. Verify that: Depreciation values for REG_MATERIAL1 and REG_MATERIAL1 Copy are correctly reflected in quarterly data

- 'Workbench' table contain row with following data:
- 'Workbench' table contain row with following data:

### Gherkin Excerpt

```gherkin
  Scenario: TC-Manufacturing-Proposal-012: Material Depreciation
    And I close current browser tab
    Then I switch to tab 1
    And info: ---1. Navigate to the Procurement & Production tab---
    And I click on 'Procurement & Production' tab
    And page with name 'Estimate Procurement & Production' is opened
    When I select 'Regression Test - Manufacturing' in the 'View' dropdown
    And I click on 'Save' button
    Then text of 'View' dropdown equals 'View: Regression Test - Manufacturing (preferred)'
    And page with name 'Estimate Procurement & Production' is opened
    And info: ---2. For REG_MATERIAL1 click the Gear icon and click Edit Details---
    And I hover 'Description' cell in table 'Procurement & Production' table and click  for row with data:
      | Description              | REG_MATERIAL1 |
      | Cost in Company Currency | $ 120,000.00  |
    And I click on 'Edit Details' menuItem
    And 'Estimate for Labor' popup is displayed
    Then I wait for 7 seconds
    And info: ---3. Open the Asset Depreciation tab on the popup---
    And I click on 'Asset Depreciation' tab
    Then I wait for 7 seconds
    And info: ---4. Verify that: Total Value field = $ 141,240.00 (Source Unit Cost + Overhead + G&A)---
    And text of 'Total Value' dropdown equals '141,240.00'
    And info: ---5. In the Depreciation Method field select Test Data Value---
    When I enter data into the next fields:
      | 'Depreciation Method' field        | Straight Line           |
    And I click on 'Estimate for Labor' popup
    And text of 'Depreciation Method' field equals 'Straight Line'
    And info: ---6. In the In-Service Date (start of depreciation) field enter Test Data Value---
    When I enter data into the next fields:
      | 'Start of Depreciation' datePicker        | 10/1/25           |
    And I click on 'Estimate for Labor' popup
    Then I wait for 3 seconds
    And text of 'Start of Depreciation' datePicker equals '10/1/25'
    And info: ---7. Click the checkbox Transfer asset to customer at contract end and verify depreciation details---
    And I check 'End of Depreciation' checkBox
    And I click on 'Estimate for Labor' popup
    And text of 'End of Depreciation' datePicker equals '3/31/26'
    And I verify 'Depreciation Period (months)' of Labor page is enabled, not required, numeric selection and has text '6'
    And 'Amortization Schedule' table contain row with following data:
      | Month                    | Oct-2025            |
      | Depreciation             | 23,540.00           |
    And 'Amortization Schedule' table contain row with following data:
      | Month                    | Nov-2025            |
      | Depreciation             | 23,540.00           |
    And 'Amortization Schedule' table contain row with following data:
      | Month                    | Dec-2025            |
      | Depreciation             | 23,540.00           |
    And 'Amortization Schedule' table contain row with following data:
      | Month                    | Jan-2026            |
      | Depreciation             | 23,540.00           |
    And 'Amortization Schedule' table contain row with following data:
      | Month                    | Feb-2026            |
      | Depreciation             | 23,540.00           |
    And 'Amortization Schedule' table contain row with following data:
      | Month                    | Mar-2026            |
      | Depreciation             | 23,540.00           |
    Then Grand total cell value of the 'Incurred Cost' column of the 'Amortization Schedule' table equals '$ 141,240.00'
    Then Grand total cell value of the 'Depreciation' column of the 'Amortization Schedule' table equals '$ 141,240.00'
    And info: ---8. Click Confirm button---
    Then I click on 'Confirm' button
    And 'Estimate for Labor' popup is not displayed
    And page with name 'Estimate Procurement & Production' is opened
    And info: ---9. For REG_MATERIAL1 Copy click the Gear icon and click Edit Details---
    And I hover 'Description' cell in table 'Procurement & Production' table and click  for row with data:
      | Description              | REG_MATERIAL1 Copy |
      | Cost in Company Currency | $ 60,000.00        |
    And I click on 'Edit Details' menuItem
    And 'Estimate for Labor' popup is displayed
    Then I wait for 7 seconds
    And I click on 'Asset Depreciation' tab
    Then I wait for 7 seconds
    And info: ---10. Verify that: Total Value field = $70,620.00 (Source Unit Cost + Overhead + G&A)---
    And text of 'Total Value' dropdown equals '70,620.00'
    And info: ---11. In the Depreciation Method field select Test Data Value---
    When I enter data into the next fields:
      | 'Depreciation Method' field        | Straight Line           |
    And I click on 'Estimate for Labor' popup
    And text of 'Depreciation Method' field equals 'Straight Line'
    And info: ---12. Click the checkbox Start depreciation at incurred start (7/1/25)---
    And I check 'Start of Depreciation' checkBox
    And I click on 'Estimate for Labor' popup
    And text of 'Start of Depreciation' datePicker equals '7/1/25'
    And info: ---13. In the End of Life (end of depreciation) field enter Test Data Value and verify depreciation schedule---
    When I enter data into the next fields:
      | 'End of Depreciation' datePicker        | 12/31/25           |
    And I click on 'Estimate for Labor' popup
    Then I wait for 7 seconds
    And text of 'End of Depreciation' datePicker equals '12/31/25'
    And I verify 'Depreciation Period (months)' of Labor page is enabled, not required, numeric selection and has text '6'
    And 'Amortization Schedule' table contain row with following data:
      | Month                    | Jul-2025            |
      | Depreciation             | 11,770.00           |
    And 'Amortization Schedule' table contain row with following data:
      | Month                    | Aug-2025            |
      | Depreciation             | 11,770.00           |
    And 'Amortization Schedule' table contain row with following data:
      | Month                    | Sep-2025            |
      | Depreciation             | 11,770.00           |
    And 'Amortization Schedule' table contain row with following data:
      | Month                    | Oct-2025            |
      | Depreciation             | 11,770.00           |
    And 'Amortization Schedule' table contain row with following data:
      | Month                    | Nov-2025            |
      | Depreciation             | 11,770.00           |
    And 'Amortization Schedule' table contain row with following data:
      | Month                    | Dec-2025            |
      | Depreciation             | 11,770.00           |
    Then Grand total cell value of the 'Incurred Cost' column of the 'Amortization Schedule' table equals '$ 70,620.00'
    Then Grand total cell value of the 'Depreciation' column of the 'Amortization Schedule' table equals '$ 70,620.00'
    And info: ---14. Click Confirm button---
    Then I click on 'Confirm' button
    And 'Estimate for Labor' popup is not displayed
    And page with name 'Estimate Procurement & Production' is opened
    And I click on 'Save' button
    And page with name 'Estimate Procurement & Production' is opened
    When I click on 'Update Estimate totals' link
    Then I wait for 30 seconds
    And page with name 'Estimate Procurement & Production' is opened
    And info: ---15. Via the menu, go to Open  Open Cost Price Analysis and ensure Workbench tab is shown---
    Then I click on 'Hamburger' menu
    Then I choose in 'Hamburger' menu the next menu chain:
      | Open                     |
      | Open Cost Price Analysis |
    And page with name 'Estimate Procurement & Production' is opened
    Then I switch to tab 2
    And I wait for 10 seconds
    Then I click on 'Workbench' tab
    Then page with name 'Workbench page' is opened
    And info: ---16. Select view Regression Test - Amortization & Depreciation---
    When I select 'Regression Test - Amortization & Depreciation' in the 'View' dropdown
    Then page with name 'Workbench page' is opened
    And info: ---17. Expand: Metrics, Depreciation or Amortization, Material costs, Years: 2025, 2026---
    Then I expand collapsed items
    Then page with name 'Workbench page' is opened
    And info: ---18. Verify that: Depreciation values for REG_MATERIAL1 and REG_MATERIAL1 Copy are correctly reflected in quarterly data---
    And 'Workbench' table contain row with following data:
      | Description              | REG_MATERIAL1      |
      | Cost: Company Currency   | $ 0.00             |
      | 2025 Qtr: 2              | $ -141,240.00      |
      | 2025 Qtr: 3              |                    |
      | 2025 Qtr: 4              | $ 70,620.00        |
      | 2026 Qtr: 1              | $ 70,620.00        |
    And 'Workbench' table contain row with following data:
      | Description              | REG_MATERIAL1 Copy |
      | Cost: Company Currency   | $ 0.00             |
      | 2025 Qtr: 2              |                    |
      | 2025 Qtr: 3              | $ -35,310.00       |
      | 2025 Qtr: 4              | $ 35,310.00        |
      | 2026 Qtr: 1              |                    |
```

## Alternate mapped Selenium scenario 2

| Field | Value |
| --- | --- |
| Feature | 4. Amortization & Depreciation for Manufacturing Proposal |
| Scenario | TC-Manufacturing-Proposal-012: Material Depreciation |
| Tags | `@TCM-012 @END` |
| File | `imported/twentyfive-regtest/tests/src/test/resources/features/testfolder/twentyfive2_4_amortization__depreciation_manufacturing_proposal.feature:145` |

### Scenario Sections

#### Setup

- I close current browser tab
- I switch to tab 1

#### 1. Navigate to the Procurement & Production tab

- I click on 'Procurement & Production' tab
- page with name 'Estimate Procurement & Production' is opened
- I select 'Regression Test - Manufacturing' in the 'View' dropdown
- I click on 'Save' button
- text of 'View' dropdown equals 'View: Regression Test - Manufacturing (preferred)'
- page with name 'Estimate Procurement & Production' is opened

#### 2. For REG_MATERIAL1 click the Gear icon and click Edit Details

- I hover 'Description' cell in table 'Procurement & Production' table and click  for row with data:
- I click on 'Edit Details' menuItem
- 'Estimate for Labor' popup is displayed
- I wait for 7 seconds

#### 3. Open the Asset Depreciation tab on the popup

- I click on 'Asset Depreciation' tab
- I wait for 7 seconds

#### 4. Verify that: Total Value field = $ 141,240.00 (Source Unit Cost + Overhead + G&A)

- text of 'Total Value' dropdown equals '141,240.00'

#### 5. In the Depreciation Method field select Test Data Value

- I enter data into the next fields:
- I click on 'Estimate for Labor' popup
- text of 'Depreciation Method' field equals 'Straight Line'

#### 6. In the In-Service Date (start of depreciation) field enter Test Data Value

- I enter data into the next fields:
- I click on 'Estimate for Labor' popup
- I wait for 3 seconds
- text of 'Start of Depreciation' datePicker equals '10/1/25'

#### 7. Click the checkbox Transfer asset to customer at contract end and verify depreciation details

- I check 'End of Depreciation' checkBox
- I click on 'Estimate for Labor' popup
- text of 'End of Depreciation' datePicker equals '3/31/26'
- I verify 'Depreciation Period (months)' of Labor page is enabled, not required, numeric selection and has text '6'
- 'Amortization Schedule' table contain row with following data:
- 'Amortization Schedule' table contain row with following data:
- 'Amortization Schedule' table contain row with following data:
- 'Amortization Schedule' table contain row with following data:
- 'Amortization Schedule' table contain row with following data:
- 'Amortization Schedule' table contain row with following data:
- Grand total cell value of the 'Incurred Cost' column of the 'Amortization Schedule' table equals '$ 141,240.00'
- Grand total cell value of the 'Depreciation' column of the 'Amortization Schedule' table equals '$ 141,240.00'

#### 8. Click Confirm button

- I click on 'Confirm' button
- 'Estimate for Labor' popup is not displayed
- page with name 'Estimate Procurement & Production' is opened

#### 9. For REG_MATERIAL1 Copy click the Gear icon and click Edit Details

- I hover 'Description' cell in table 'Procurement & Production' table and click  for row with data:
- I click on 'Edit Details' menuItem
- 'Estimate for Labor' popup is displayed
- I wait for 7 seconds
- I click on 'Asset Depreciation' tab
- I wait for 7 seconds

#### 10. Verify that: Total Value field = $70,620.00 (Source Unit Cost + Overhead + G&A)

- text of 'Total Value' dropdown equals '70,620.00'

#### 11. In the Depreciation Method field select Test Data Value

- I enter data into the next fields:
- I click on 'Estimate for Labor' popup
- text of 'Depreciation Method' field equals 'Straight Line'

#### 12. Click the checkbox Start depreciation at incurred start (7/1/25)

- I check 'Start of Depreciation' checkBox
- I click on 'Estimate for Labor' popup
- text of 'Start of Depreciation' datePicker equals '7/1/25'

#### 13. In the End of Life (end of depreciation) field enter Test Data Value and verify depreciation schedule

- I enter data into the next fields:
- I click on 'Estimate for Labor' popup
- I wait for 7 seconds
- text of 'End of Depreciation' datePicker equals '12/31/25'
- I verify 'Depreciation Period (months)' of Labor page is enabled, not required, numeric selection and has text '6'
- 'Amortization Schedule' table contain row with following data:
- 'Amortization Schedule' table contain row with following data:
- 'Amortization Schedule' table contain row with following data:
- 'Amortization Schedule' table contain row with following data:
- 'Amortization Schedule' table contain row with following data:
- 'Amortization Schedule' table contain row with following data:
- Grand total cell value of the 'Incurred Cost' column of the 'Amortization Schedule' table equals '$ 70,620.00'
- Grand total cell value of the 'Depreciation' column of the 'Amortization Schedule' table equals '$ 70,620.00'

#### 14. Click Confirm button

- I click on 'Confirm' button
- 'Estimate for Labor' popup is not displayed
- page with name 'Estimate Procurement & Production' is opened
- I click on 'Save' button
- page with name 'Estimate Procurement & Production' is opened
- I click on 'Update Estimate totals' link
- I wait for 30 seconds
- page with name 'Estimate Procurement & Production' is opened

#### 15. Via the menu, go to Open  Open Cost Price Analysis and ensure Workbench tab is shown

- I click on 'Hamburger' menu
- I choose in 'Hamburger' menu the next menu chain:
- page with name 'Estimate Procurement & Production' is opened
- I switch to tab 2
- I wait for 10 seconds
- I click on 'Workbench' tab
- page with name 'Workbench page' is opened

#### 16. Select view Regression Test - Amortization & Depreciation

- I select 'Regression Test - Amortization & Depreciation' in the 'View' dropdown
- page with name 'Workbench page' is opened

#### 17. Expand: Metrics, Depreciation or Amortization, Material costs, Years: 2025, 2026

- I expand collapsed items
- page with name 'Workbench page' is opened

#### 18. Verify that: Depreciation values for REG_MATERIAL1 and REG_MATERIAL1 Copy are correctly reflected in quarterly data

- 'Workbench' table contain row with following data:
- 'Workbench' table contain row with following data:

### Gherkin Excerpt

```gherkin
  Scenario: TC-Manufacturing-Proposal-012: Material Depreciation
    And I close current browser tab
    Then I switch to tab 1
    And info: ---1. Navigate to the Procurement & Production tab---
    And I click on 'Procurement & Production' tab
    And page with name 'Estimate Procurement & Production' is opened
    When I select 'Regression Test - Manufacturing' in the 'View' dropdown
    And I click on 'Save' button
    Then text of 'View' dropdown equals 'View: Regression Test - Manufacturing (preferred)'
    And page with name 'Estimate Procurement & Production' is opened
    And info: ---2. For REG_MATERIAL1 click the Gear icon and click Edit Details---
    And I hover 'Description' cell in table 'Procurement & Production' table and click  for row with data:
      | Description              | REG_MATERIAL1 |
      | Cost in Company Currency | $ 120,000.00  |
    And I click on 'Edit Details' menuItem
    And 'Estimate for Labor' popup is displayed
    Then I wait for 7 seconds
    And info: ---3. Open the Asset Depreciation tab on the popup---
    And I click on 'Asset Depreciation' tab
    Then I wait for 7 seconds
    And info: ---4. Verify that: Total Value field = $ 141,240.00 (Source Unit Cost + Overhead + G&A)---
    And text of 'Total Value' dropdown equals '141,240.00'
    And info: ---5. In the Depreciation Method field select Test Data Value---
    When I enter data into the next fields:
      | 'Depreciation Method' field        | Straight Line           |
    And I click on 'Estimate for Labor' popup
    And text of 'Depreciation Method' field equals 'Straight Line'
    And info: ---6. In the In-Service Date (start of depreciation) field enter Test Data Value---
    When I enter data into the next fields:
      | 'Start of Depreciation' datePicker        | 10/1/25           |
    And I click on 'Estimate for Labor' popup
    Then I wait for 3 seconds
    And text of 'Start of Depreciation' datePicker equals '10/1/25'
    And info: ---7. Click the checkbox Transfer asset to customer at contract end and verify depreciation details---
    And I check 'End of Depreciation' checkBox
    And I click on 'Estimate for Labor' popup
    And text of 'End of Depreciation' datePicker equals '3/31/26'
    And I verify 'Depreciation Period (months)' of Labor page is enabled, not required, numeric selection and has text '6'
    And 'Amortization Schedule' table contain row with following data:
      | Month                    | Oct-2025            |
      | Depreciation             | 23,540.00           |
    And 'Amortization Schedule' table contain row with following data:
      | Month                    | Nov-2025            |
      | Depreciation             | 23,540.00           |
    And 'Amortization Schedule' table contain row with following data:
      | Month                    | Dec-2025            |
      | Depreciation             | 23,540.00           |
    And 'Amortization Schedule' table contain row with following data:
      | Month                    | Jan-2026            |
      | Depreciation             | 23,540.00           |
    And 'Amortization Schedule' table contain row with following data:
      | Month                    | Feb-2026            |
      | Depreciation             | 23,540.00           |
    And 'Amortization Schedule' table contain row with following data:
      | Month                    | Mar-2026            |
      | Depreciation             | 23,540.00           |
    Then Grand total cell value of the 'Incurred Cost' column of the 'Amortization Schedule' table equals '$ 141,240.00'
    Then Grand total cell value of the 'Depreciation' column of the 'Amortization Schedule' table equals '$ 141,240.00'
    And info: ---8. Click Confirm button---
    Then I click on 'Confirm' button
    And 'Estimate for Labor' popup is not displayed
    And page with name 'Estimate Procurement & Production' is opened
    And info: ---9. For REG_MATERIAL1 Copy click the Gear icon and click Edit Details---
    And I hover 'Description' cell in table 'Procurement & Production' table and click  for row with data:
      | Description              | REG_MATERIAL1 Copy |
      | Cost in Company Currency | $ 60,000.00        |
    And I click on 'Edit Details' menuItem
    And 'Estimate for Labor' popup is displayed
    Then I wait for 7 seconds
    And I click on 'Asset Depreciation' tab
    Then I wait for 7 seconds
    And info: ---10. Verify that: Total Value field = $70,620.00 (Source Unit Cost + Overhead + G&A)---
    And text of 'Total Value' dropdown equals '70,620.00'
    And info: ---11. In the Depreciation Method field select Test Data Value---
    When I enter data into the next fields:
      | 'Depreciation Method' field        | Straight Line           |
    And I click on 'Estimate for Labor' popup
    And text of 'Depreciation Method' field equals 'Straight Line'
    And info: ---12. Click the checkbox Start depreciation at incurred start (7/1/25)---
    And I check 'Start of Depreciation' checkBox
    And I click on 'Estimate for Labor' popup
    And text of 'Start of Depreciation' datePicker equals '7/1/25'
    And info: ---13. In the End of Life (end of depreciation) field enter Test Data Value and verify depreciation schedule---
    When I enter data into the next fields:
      | 'End of Depreciation' datePicker        | 12/31/25           |
    And I click on 'Estimate for Labor' popup
    Then I wait for 7 seconds
    And text of 'End of Depreciation' datePicker equals '12/31/25'
    And I verify 'Depreciation Period (months)' of Labor page is enabled, not required, numeric selection and has text '6'
    And 'Amortization Schedule' table contain row with following data:
      | Month                    | Jul-2025            |
      | Depreciation             | 11,770.00           |
    And 'Amortization Schedule' table contain row with following data:
      | Month                    | Aug-2025            |
      | Depreciation             | 11,770.00           |
    And 'Amortization Schedule' table contain row with following data:
      | Month                    | Sep-2025            |
      | Depreciation             | 11,770.00           |
    And 'Amortization Schedule' table contain row with following data:
      | Month                    | Oct-2025            |
      | Depreciation             | 11,770.00           |
    And 'Amortization Schedule' table contain row with following data:
      | Month                    | Nov-2025            |
      | Depreciation             | 11,770.00           |
    And 'Amortization Schedule' table contain row with following data:
      | Month                    | Dec-2025            |
      | Depreciation             | 11,770.00           |
    Then Grand total cell value of the 'Incurred Cost' column of the 'Amortization Schedule' table equals '$ 70,620.00'
    Then Grand total cell value of the 'Depreciation' column of the 'Amortization Schedule' table equals '$ 70,620.00'
    And info: ---14. Click Confirm button---
    Then I click on 'Confirm' button
    And 'Estimate for Labor' popup is not displayed
    And page with name 'Estimate Procurement & Production' is opened
    And I click on 'Save' button
    And page with name 'Estimate Procurement & Production' is opened
    When I click on 'Update Estimate totals' link
    Then I wait for 30 seconds
    And page with name 'Estimate Procurement & Production' is opened
    And info: ---15. Via the menu, go to Open  Open Cost Price Analysis and ensure Workbench tab is shown---
    Then I click on 'Hamburger' menu
    Then I choose in 'Hamburger' menu the next menu chain:
      | Open                     |
      | Open Cost Price Analysis |
    And page with name 'Estimate Procurement & Production' is opened
    Then I switch to tab 2
    And I wait for 10 seconds
    Then I click on 'Workbench' tab
    Then page with name 'Workbench page' is opened
    And info: ---16. Select view Regression Test - Amortization & Depreciation---
    When I select 'Regression Test - Amortization & Depreciation' in the 'View' dropdown
    Then page with name 'Workbench page' is opened
    And info: ---17. Expand: Metrics, Depreciation or Amortization, Material costs, Years: 2025, 2026---
    Then I expand collapsed items
    Then page with name 'Workbench page' is opened
    And info: ---18. Verify that: Depreciation values for REG_MATERIAL1 and REG_MATERIAL1 Copy are correctly reflected in quarterly data---
    And 'Workbench' table contain row with following data:
      | Description              | REG_MATERIAL1      |
      | Cost: Company Currency   | $ 0.00             |
      | 2025 Qtr: 2              | $ -141,240.00      |
      | 2025 Qtr: 3              |                    |
      | 2025 Qtr: 4              | $ 70,620.00        |
      | 2026 Qtr: 1              | $ 70,620.00        |
    And 'Workbench' table contain row with following data:
      | Description              | REG_MATERIAL1 Copy |
      | Cost: Company Currency   | $ 0.00             |
      | 2025 Qtr: 2              |                    |
      | 2025 Qtr: 3              | $ -35,310.00       |
      | 2025 Qtr: 4              | $ 35,310.00        |
      | 2026 Qtr: 1              |                    |
```

---

_Generated by `scripts/generate-testcase-guides.py` from the RTA mapping and local feature files._
