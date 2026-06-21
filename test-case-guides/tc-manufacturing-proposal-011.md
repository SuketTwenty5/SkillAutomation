# TC-Manufacturing-Proposal-011 - Labor Amortization

| Field | Value |
| --- | --- |
| Test ID | TC-Manufacturing-Proposal-011 |
| Title | Labor Amortization |
| RTA page | https://twenty5.atlassian.net/wiki/spaces/RTA/pages/279740417/TC-Manufacturing-Proposal-011+Labor+Amortization |
| Map source | `RTA_TEST_SUITE_FEATURE_MAP.md` |

## Run Instructions

When a consultant asks to run this test, use the default app URL unless they provide another URL:

```text
https://approuter-twenty5ipe-dev.cfapps.us10.hana.ondemand.com/#quote
```

For Claude Desktop, ask before launching Selenium Chrome with the default URL. If the user provides another URL, launch that URL instead.

```bash
scripts/start-debug-chrome.sh "https://approuter-twenty5ipe-dev.cfapps.us10.hana.ondemand.com/#quote"
scripts/run-twentyfive-test.sh @TCM-011
```

Duplicate feature matches exist for this Confluence page. The tag command may run more than one matching scenario until the canonical feature file is confirmed.

## Default mapped Selenium scenario

| Field | Value |
| --- | --- |
| Feature | 7. Amortization & Depreciation for Manufacturing Proposal |
| Scenario | TC-Manufacturing-Proposal-011: Labor Amortization |
| Tags | `@TCM-011 @START` |
| File | `imported/twentyfive-regtest/tests/src/test/resources/features/testfolder/amortizationAndDepreciationforManufacturing.feature:5` |

### Scenario Sections

#### Setup

- open TC-Manufacturing-Proposal-004 boe
- I perform login

#### 1. Navigate to the Labor tab

- page with name 'Estimate Labor' is opened
- I select 'Test View for Regression' in the 'View' dropdown
- page with name 'Estimate Labor' is opened
- I select 'Regression Test - Manufacturing' in the 'View' dropdown
- I click on 'Save' button
- text of 'View' dropdown equals 'View: Regression Test - Manufacturing (shared & preferred)'

#### 2. For REG_CONSULTANT1 click the Gear icon and click Edit Details

- I hover 'Description' cell in table 'Labor Dual' table and click  for row with data:
- I click on 'Edit Details' menuItem
- 'Estimate for Labor' popup is displayed

#### 3. Verify that: Total Value field = $ 232,704.00 (Labor Cost + Fringe)

- I click on 'Labor Amortization' tab
- I wait for 7 seconds
- text of 'Total Value' dropdown equals '232,704.00'

#### 4. In the Amortization Method field select Test Data Value

- I enter data into the next fields:
- I click on 'Estimate for Labor' popup
- text of 'Amortization Method' field equals 'Straight Line'

#### 5. In the Start of Amortization field enter Test Data Value

- I enter data into the next fields:
- I click on 'Estimate for Labor' popup
- I wait for 3 seconds
- text of 'Start of Amortization' datePicker equals '10/1/25'

#### 6. Click the checkbox End amortization at contract end (3/31/26)

- I check 'End of Amortization' checkBox
- I click on 'Estimate for Labor' popup
- I wait for 3 seconds
- text of 'End of Amortization' datePicker equals '3/31/26'
- I verify 'Amortization Period (months)' of Labor page is enabled, not required, numeric selection and has text '6'
- 'Amortization Schedule' table contain row with following data:
- 'Amortization Schedule' table contain row with following data:
- 'Amortization Schedule' table contain row with following data:
- 'Amortization Schedule' table contain row with following data:
- 'Amortization Schedule' table contain row with following data:
- 'Amortization Schedule' table contain row with following data:
- Grand total cell value of the 'Incurred Cost' column of the 'Amortization Schedule' table equals '$ 232,704.00'
- Grand total cell value of the 'Amortization' column of the 'Amortization Schedule' table equals '$ 232,704.00'

#### 7. Click Confirm button

- I click on 'Confirm' button
- 'Estimate for Labor' popup is not displayed
- page with name 'Estimate Labor Resources' is opened

#### 8. For REG_PROJECTMANAGER click the Gear icon and click Edit Details

- I hover 'Description' cell in table 'Labor Dual' table and click  for row with data:
- I click on 'Edit Details' menuItem
- 'Estimate for Labor' popup is displayed

#### 9. Verify that: Total Value field = $ 90,816.00

- text of 'Total Value' dropdown equals '90,816.00'

#### 10. In the Amortization Method field select Test Data Value

- I enter data into the next fields:
- I click on 'Estimate for Labor' popup
- text of 'Amortization Method' field equals 'Straight Line'

#### 11. Click the checkbox Start amortization at incurred start (4/1/25)

- I check 'Start of Amortization' checkBox
- I click on 'Estimate for Labor' popup
- text of 'Start of Amortization' datePicker equals '4/1/25'

#### 12. In the End of Amortization field enter Test Data Value and verify period & grid values

- I enter data into the next fields:
- I click on 'Estimate for Labor' popup
- I wait for 7 seconds
- text of 'End of Amortization' datePicker equals '9/30/25'
- I verify 'Amortization Period (months)' of Labor page is enabled, not required, numeric selection and has text '6'
- 'Amortization Schedule' table contain row with following data:
- 'Amortization Schedule' table contain row with following data:
- 'Amortization Schedule' table contain row with following data:
- 'Amortization Schedule' table contain row with following data:
- 'Amortization Schedule' table contain row with following data:
- 'Amortization Schedule' table contain row with following data:
- Grand total cell value of the 'Incurred Cost' column of the 'Amortization Schedule' table equals '$ 90,816.00'
- Grand total cell value of the 'Amortization' column of the 'Amortization Schedule' table equals '$ 90,816.00'

#### 13. Click Confirm button

- I click on 'Confirm' button
- 'Estimate for Labor' popup is not displayed
- page with name 'Estimate Labor Resources' is opened

#### 14. Via the menu, go to Open  Open Cost Price Analysis

- I click on 'Hamburger' menu
- I choose in 'Hamburger' menu the next menu chain:
- page with name 'Estimate Labor' is opened
- I switch to tab 2
- page with name 'Cost Price Analysis Wbs page' is opened

#### 15. Click the Workbench tab (unless it is already presented after previous step)

- I click on 'Workbench' tab
- page with name 'Workbench page' is opened

#### 16. Select view Regression Test - Amortization & Depreciation

- I select 'Regression Test - Amortization & Depreciation' in the 'View' dropdown
- page with name 'Workbench page' is opened

#### 17. Expand: Metrics, Depreciation or Amortization, Labor costs, Years: 2025, 2026

- I expand collapsed items
- page with name 'Workbench page' is opened

#### 18. Verify that: Amortization values for REG_CONSULTANT1 and REG_PROJECTMANAGER are as expected

- 'Workbench' table contain row with following data:
- 'Workbench' table contain row with following data:

### Gherkin Excerpt

```gherkin
  Scenario: TC-Manufacturing-Proposal-011: Labor Amortization
    Given open TC-Manufacturing-Proposal-004 boe
    And I perform login
    And info: ---1. Navigate to the Labor tab---
    And page with name 'Estimate Labor' is opened
    When I select 'Test View for Regression' in the 'View' dropdown
    And page with name 'Estimate Labor' is opened
    When I select 'Regression Test - Manufacturing' in the 'View' dropdown
    And I click on 'Save' button
    Then text of 'View' dropdown equals 'View: Regression Test - Manufacturing (shared & preferred)'
    And info: ---2. For REG_CONSULTANT1 click the Gear icon and click Edit Details---
    And I hover 'Description' cell in table 'Labor Dual' table and click  for row with data:
      | Description | REG_CONSULTANT1 |
    And I click on 'Edit Details' menuItem
    And 'Estimate for Labor' popup is displayed
    And info: ---3. Verify that: Total Value field = $ 232,704.00 (Labor Cost + Fringe)---
    And I click on 'Labor Amortization' tab
    Then I wait for 7 seconds
    And text of 'Total Value' dropdown equals '232,704.00'
    And info: ---4. In the Amortization Method field select Test Data Value---
    When I enter data into the next fields:
      | 'Amortization Method' field        | Straight Line           |
    And I click on 'Estimate for Labor' popup
    And text of 'Amortization Method' field equals 'Straight Line'
    And info: ---5. In the Start of Amortization field enter Test Data Value---
    When I enter data into the next fields:
      | 'Start of Amortization' datePicker        | 10/1/25           |
    And I click on 'Estimate for Labor' popup
    Then I wait for 3 seconds
    And text of 'Start of Amortization' datePicker equals '10/1/25'
    And info: ---6. Click the checkbox End amortization at contract end (3/31/26)---
    And I check 'End of Amortization' checkBox
    And I click on 'Estimate for Labor' popup
    Then I wait for 3 seconds
    And text of 'End of Amortization' datePicker equals '3/31/26'
    And I verify 'Amortization Period (months)' of Labor page is enabled, not required, numeric selection and has text '6'
    And 'Amortization Schedule' table contain row with following data:
      | Month                    | Oct-2025            |
      | Amortization             | 22,739.14           |
    And 'Amortization Schedule' table contain row with following data:
      | Month                    | Nov-2025            |
      | Amortization             | 26,564.37           |
    And 'Amortization Schedule' table contain row with following data:
      | Month                    | Dec-2025            |
      | Amortization             | 31,505.32           |
    And 'Amortization Schedule' table contain row with following data:
      | Month                    | Jan-2026            |
      | Amortization             | 38,093.32           |
    And 'Amortization Schedule' table contain row with following data:
      | Month                    | Feb-2026            |
      | Amortization             | 47,018.92           |
    And 'Amortization Schedule' table contain row with following data:
      | Month                    | Mar-2026            |
      | Amortization             | 66,782.93           |
    Then Grand total cell value of the 'Incurred Cost' column of the 'Amortization Schedule' table equals '$ 232,704.00'
    Then Grand total cell value of the 'Amortization' column of the 'Amortization Schedule' table equals '$ 232,704.00'
    And info: ---7. Click Confirm button---
    Then I click on 'Confirm' button
    And 'Estimate for Labor' popup is not displayed
    And page with name 'Estimate Labor Resources' is opened
    And info: ---8. For REG_PROJECTMANAGER click the Gear icon and click Edit Details---
    And I hover 'Description' cell in table 'Labor Dual' table and click  for row with data:
      | Description | REG_PROJECTMANAGER |
    And I click on 'Edit Details' menuItem
    And 'Estimate for Labor' popup is displayed
    And info: ---9. Verify that: Total Value field = $ 90,816.00---
    And text of 'Total Value' dropdown equals '90,816.00'
    And info: ---10. In the Amortization Method field select Test Data Value---
    When I enter data into the next fields:
      | 'Amortization Method' field        | Straight Line           |
    And I click on 'Estimate for Labor' popup
    And text of 'Amortization Method' field equals 'Straight Line'
    And info: ---11. Click the checkbox Start amortization at incurred start (4/1/25)---
    And I check 'Start of Amortization' checkBox
    And I click on 'Estimate for Labor' popup
    And text of 'Start of Amortization' datePicker equals '4/1/25'
    And info: ---12. In the End of Amortization field enter Test Data Value and verify period & grid values---
    When I enter data into the next fields:
      | 'End of Amortization' datePicker        | 9/30/25           |
    And I click on 'Estimate for Labor' popup
    Then I wait for 7 seconds
    And text of 'End of Amortization' datePicker equals '9/30/25'
    And I verify 'Amortization Period (months)' of Labor page is enabled, not required, numeric selection and has text '6'
    And 'Amortization Schedule' table contain row with following data:
      | Month                    | Apr-2025            |
      | Amortization             | 3,721.97             |
    And 'Amortization Schedule' table contain row with following data:
      | Month                    | May-2025            |
      | Amortization             | 8,337.21            |
    And 'Amortization Schedule' table contain row with following data:
      | Month                    | Jun-2025            |
      | Amortization             | 13,920.17           |
    And 'Amortization Schedule' table contain row with following data:
      | Month                    | Jul-2025            |
      | Amortization             | 21,612.22           |
    And 'Amortization Schedule' table contain row with following data:
      | Month                    | Aug-2025            |
      | Amortization             | 21,612.22           |
    And 'Amortization Schedule' table contain row with following data:
      | Month                    | Sep-2025            |
      | Amortization             | 21,612.22           |
    Then Grand total cell value of the 'Incurred Cost' column of the 'Amortization Schedule' table equals '$ 90,816.00'
    Then Grand total cell value of the 'Amortization' column of the 'Amortization Schedule' table equals '$ 90,816.00'
    And info: ---13. Click Confirm button---
    Then I click on 'Confirm' button
    And 'Estimate for Labor' popup is not displayed
    And page with name 'Estimate Labor Resources' is opened
    And info: ---14. Via the menu, go to Open  Open Cost Price Analysis---
    Then I click on 'Hamburger' menu
    Then I choose in 'Hamburger' menu the next menu chain:
      | Open                     |
      | Open Cost Price Analysis |
    And page with name 'Estimate Labor' is opened
    Then I switch to tab 2
    And page with name 'Cost Price Analysis Wbs page' is opened
    And info: ---15. Click the Workbench tab (unless it is already presented after previous step)---
    Then I click on 'Workbench' tab
    Then page with name 'Workbench page' is opened
    And info: ---16. Select view Regression Test - Amortization & Depreciation---
    When I select 'Regression Test - Amortization & Depreciation' in the 'View' dropdown
    Then page with name 'Workbench page' is opened
    And info: ---17. Expand: Metrics, Depreciation or Amortization, Labor costs, Years: 2025, 2026---
    Then I expand collapsed items
    Then page with name 'Workbench page' is opened
    And info: ---18. Verify that: Amortization values for REG_CONSULTANT1 and REG_PROJECTMANAGER are as expected---
    And 'Workbench' table contain row with following data:
      | Description              | REG_CONSULTANT1    |
      | Cost: Company Currency   | $ 0.00             |
      | 2025 Qtr: 2              | $ -58,016.60       |
      | 2025 Qtr: 3              | $ -58,654.44       |
      | 2025 Qtr: 4              | $ 22,155.09        |
      | 2026 Qtr: 1              | $ 94,515.95        |
    And 'Workbench' table contain row with following data:
      | Description              | REG_PROJECTMANAGER |
      | Cost: Company Currency   | $ 0.00             |
      | 2025 Qtr: 2              | $ -51,336.59       |
      | 2025 Qtr: 3              | $ 14,130.22        |
      | 2025 Qtr: 4              | $ 37,206.38        |

```

## Alternate mapped Selenium scenario 2

| Field | Value |
| --- | --- |
| Feature | 4. Amortization & Depreciation for Manufacturing Proposal |
| Scenario | TC-Manufacturing-Proposal-011: Labor Amortization |
| Tags | `@TCM-011 @START` |
| File | `imported/twentyfive-regtest/tests/src/test/resources/features/testfolder/twentyfive2_4_amortization__depreciation_manufacturing_proposal.feature:5` |

### Scenario Sections

#### Setup

- open TC-Manufacturing-Proposal-004 boe
- I perform Mfg 2.4 login

#### 1. Navigate to the Labor tab

- page with name 'Estimate Labor' is opened
- I select 'Test View for Regression' in the 'View' dropdown
- page with name 'Estimate Labor' is opened
- I select 'Regression Test - Manufacturing' in the 'View' dropdown
- I click on 'Save' button
- text of 'View' dropdown equals 'View: Regression Test - Manufacturing (shared & preferred)'

#### 2. For REG_CONSULTANT1 click the Gear icon and click Edit Details

- I hover 'Description' cell in table 'Labor Dual' table and click  for row with data:
- I click on 'Edit Details' menuItem
- 'Estimate for Labor' popup is displayed

#### 3. Verify that: Total Value field = $ 232,704.00 (Labor Cost + Fringe)

- I click on 'Labor Amortization' tab
- I wait for 7 seconds
- text of 'Total Value' dropdown equals '232,704.00'

#### 4. In the Amortization Method field select Test Data Value

- I enter data into the next fields:
- I click on 'Estimate for Labor' popup
- text of 'Amortization Method' field equals 'Straight Line'

#### 5. In the Start of Amortization field enter Test Data Value

- I enter data into the next fields:
- I click on 'Estimate for Labor' popup
- I wait for 3 seconds
- text of 'Start of Amortization' datePicker equals '10/1/25'

#### 6. Click the checkbox End amortization at contract end (3/31/26)

- I check 'End of Amortization' checkBox
- I click on 'Estimate for Labor' popup
- I wait for 3 seconds
- text of 'End of Amortization' datePicker equals '3/31/26'
- I verify 'Amortization Period (months)' of Labor page is enabled, not required, numeric selection and has text '6'
- 'Amortization Schedule' table contain row with following data:
- 'Amortization Schedule' table contain row with following data:
- 'Amortization Schedule' table contain row with following data:
- 'Amortization Schedule' table contain row with following data:
- 'Amortization Schedule' table contain row with following data:
- 'Amortization Schedule' table contain row with following data:
- Grand total cell value of the 'Incurred Cost' column of the 'Amortization Schedule' table equals '$ 232,704.00'
- Grand total cell value of the 'Amortization' column of the 'Amortization Schedule' table equals '$ 232,704.00'

#### 7. Click Confirm button

- I click on 'Confirm' button
- 'Estimate for Labor' popup is not displayed
- page with name 'Estimate Labor Resources' is opened

#### 8. For REG_PROJECTMANAGER click the Gear icon and click Edit Details

- I hover 'Description' cell in table 'Labor Dual' table and click  for row with data:
- I click on 'Edit Details' menuItem
- 'Estimate for Labor' popup is displayed

#### 9. Verify that: Total Value field = $ 90,816.00

- text of 'Total Value' dropdown equals '90,816.00'

#### 10. In the Amortization Method field select Test Data Value

- I enter data into the next fields:
- I click on 'Estimate for Labor' popup
- text of 'Amortization Method' field equals 'Straight Line'

#### 11. Click the checkbox Start amortization at incurred start (4/1/25)

- I check 'Start of Amortization' checkBox
- I click on 'Estimate for Labor' popup
- text of 'Start of Amortization' datePicker equals '4/1/25'

#### 12. In the End of Amortization field enter Test Data Value and verify period & grid values

- I enter data into the next fields:
- I click on 'Estimate for Labor' popup
- I wait for 7 seconds
- text of 'End of Amortization' datePicker equals '9/30/25'
- I verify 'Amortization Period (months)' of Labor page is enabled, not required, numeric selection and has text '6'
- 'Amortization Schedule' table contain row with following data:
- 'Amortization Schedule' table contain row with following data:
- 'Amortization Schedule' table contain row with following data:
- 'Amortization Schedule' table contain row with following data:
- 'Amortization Schedule' table contain row with following data:
- 'Amortization Schedule' table contain row with following data:
- Grand total cell value of the 'Incurred Cost' column of the 'Amortization Schedule' table equals '$ 90,816.00'
- Grand total cell value of the 'Amortization' column of the 'Amortization Schedule' table equals '$ 90,816.00'

#### 13. Click Confirm button

- I click on 'Confirm' button
- 'Estimate for Labor' popup is not displayed
- page with name 'Estimate Labor Resources' is opened

#### 14. Via the menu, go to Open  Open Cost Price Analysis

- I click on 'Hamburger' menu
- I choose in 'Hamburger' menu the next menu chain:
- page with name 'Estimate Labor' is opened
- I switch to tab 2
- page with name 'Cost Price Analysis Wbs page' is opened

#### 15. Click the Workbench tab (unless it is already presented after previous step)

- I click on 'Workbench' tab
- page with name 'Workbench page' is opened

#### 16. Select view Regression Test - Amortization & Depreciation

- I select 'Regression Test - Amortization & Depreciation' in the 'View' dropdown
- page with name 'Workbench page' is opened

#### 17. Expand: Metrics, Depreciation or Amortization, Labor costs, Years: 2025, 2026

- I expand collapsed items
- page with name 'Workbench page' is opened

#### 18. Verify that: Amortization values for REG_CONSULTANT1 and REG_PROJECTMANAGER are as expected

- 'Workbench' table contain row with following data:
- 'Workbench' table contain row with following data:

### Gherkin Excerpt

```gherkin
  Scenario: TC-Manufacturing-Proposal-011: Labor Amortization
    Given open TC-Manufacturing-Proposal-004 boe
    And I perform Mfg 2.4 login
    And info: ---1. Navigate to the Labor tab---
    And page with name 'Estimate Labor' is opened
    When I select 'Test View for Regression' in the 'View' dropdown
    And page with name 'Estimate Labor' is opened
    When I select 'Regression Test - Manufacturing' in the 'View' dropdown
    And I click on 'Save' button
    Then text of 'View' dropdown equals 'View: Regression Test - Manufacturing (shared & preferred)'
    And info: ---2. For REG_CONSULTANT1 click the Gear icon and click Edit Details---
    And I hover 'Description' cell in table 'Labor Dual' table and click  for row with data:
      | Description | REG_CONSULTANT1 |
    And I click on 'Edit Details' menuItem
    And 'Estimate for Labor' popup is displayed
    And info: ---3. Verify that: Total Value field = $ 232,704.00 (Labor Cost + Fringe)---
    And I click on 'Labor Amortization' tab
    Then I wait for 7 seconds
    And text of 'Total Value' dropdown equals '232,704.00'
    And info: ---4. In the Amortization Method field select Test Data Value---
    When I enter data into the next fields:
      | 'Amortization Method' field        | Straight Line           |
    And I click on 'Estimate for Labor' popup
    And text of 'Amortization Method' field equals 'Straight Line'
    And info: ---5. In the Start of Amortization field enter Test Data Value---
    When I enter data into the next fields:
      | 'Start of Amortization' datePicker        | 10/1/25           |
    And I click on 'Estimate for Labor' popup
    Then I wait for 3 seconds
    And text of 'Start of Amortization' datePicker equals '10/1/25'
    And info: ---6. Click the checkbox End amortization at contract end (3/31/26)---
    And I check 'End of Amortization' checkBox
    And I click on 'Estimate for Labor' popup
    Then I wait for 3 seconds
    And text of 'End of Amortization' datePicker equals '3/31/26'
    And I verify 'Amortization Period (months)' of Labor page is enabled, not required, numeric selection and has text '6'
    And 'Amortization Schedule' table contain row with following data:
      | Month                    | Oct-2025            |
      | Amortization             | 22,739.14           |
    And 'Amortization Schedule' table contain row with following data:
      | Month                    | Nov-2025            |
      | Amortization             | 26,564.37           |
    And 'Amortization Schedule' table contain row with following data:
      | Month                    | Dec-2025            |
      | Amortization             | 31,505.32           |
    And 'Amortization Schedule' table contain row with following data:
      | Month                    | Jan-2026            |
      | Amortization             | 38,093.32           |
    And 'Amortization Schedule' table contain row with following data:
      | Month                    | Feb-2026            |
      | Amortization             | 47,018.92           |
    And 'Amortization Schedule' table contain row with following data:
      | Month                    | Mar-2026            |
      | Amortization             | 66,782.93           |
    Then Grand total cell value of the 'Incurred Cost' column of the 'Amortization Schedule' table equals '$ 232,704.00'
    Then Grand total cell value of the 'Amortization' column of the 'Amortization Schedule' table equals '$ 232,704.00'
    And info: ---7. Click Confirm button---
    Then I click on 'Confirm' button
    And 'Estimate for Labor' popup is not displayed
    And page with name 'Estimate Labor Resources' is opened
    And info: ---8. For REG_PROJECTMANAGER click the Gear icon and click Edit Details---
    And I hover 'Description' cell in table 'Labor Dual' table and click  for row with data:
      | Description | REG_PROJECTMANAGER |
    And I click on 'Edit Details' menuItem
    And 'Estimate for Labor' popup is displayed
    And info: ---9. Verify that: Total Value field = $ 90,816.00---
    And text of 'Total Value' dropdown equals '90,816.00'
    And info: ---10. In the Amortization Method field select Test Data Value---
    When I enter data into the next fields:
      | 'Amortization Method' field        | Straight Line           |
    And I click on 'Estimate for Labor' popup
    And text of 'Amortization Method' field equals 'Straight Line'
    And info: ---11. Click the checkbox Start amortization at incurred start (4/1/25)---
    And I check 'Start of Amortization' checkBox
    And I click on 'Estimate for Labor' popup
    And text of 'Start of Amortization' datePicker equals '4/1/25'
    And info: ---12. In the End of Amortization field enter Test Data Value and verify period & grid values---
    When I enter data into the next fields:
      | 'End of Amortization' datePicker        | 9/30/25           |
    And I click on 'Estimate for Labor' popup
    Then I wait for 7 seconds
    And text of 'End of Amortization' datePicker equals '9/30/25'
    And I verify 'Amortization Period (months)' of Labor page is enabled, not required, numeric selection and has text '6'
    And 'Amortization Schedule' table contain row with following data:
      | Month                    | Apr-2025            |
      | Amortization             | 3,721.97             |
    And 'Amortization Schedule' table contain row with following data:
      | Month                    | May-2025            |
      | Amortization             | 8,337.21            |
    And 'Amortization Schedule' table contain row with following data:
      | Month                    | Jun-2025            |
      | Amortization             | 13,920.17           |
    And 'Amortization Schedule' table contain row with following data:
      | Month                    | Jul-2025            |
      | Amortization             | 21,612.22           |
    And 'Amortization Schedule' table contain row with following data:
      | Month                    | Aug-2025            |
      | Amortization             | 21,612.22           |
    And 'Amortization Schedule' table contain row with following data:
      | Month                    | Sep-2025            |
      | Amortization             | 21,612.22           |
    Then Grand total cell value of the 'Incurred Cost' column of the 'Amortization Schedule' table equals '$ 90,816.00'
    Then Grand total cell value of the 'Amortization' column of the 'Amortization Schedule' table equals '$ 90,816.00'
    And info: ---13. Click Confirm button---
    Then I click on 'Confirm' button
    And 'Estimate for Labor' popup is not displayed
    And page with name 'Estimate Labor Resources' is opened
    And info: ---14. Via the menu, go to Open  Open Cost Price Analysis---
    Then I click on 'Hamburger' menu
    Then I choose in 'Hamburger' menu the next menu chain:
      | Open                     |
      | Open Cost Price Analysis |
    And page with name 'Estimate Labor' is opened
    Then I switch to tab 2
    And page with name 'Cost Price Analysis Wbs page' is opened
    And info: ---15. Click the Workbench tab (unless it is already presented after previous step)---
    Then I click on 'Workbench' tab
    Then page with name 'Workbench page' is opened
    And info: ---16. Select view Regression Test - Amortization & Depreciation---
    When I select 'Regression Test - Amortization & Depreciation' in the 'View' dropdown
    Then page with name 'Workbench page' is opened
    And info: ---17. Expand: Metrics, Depreciation or Amortization, Labor costs, Years: 2025, 2026---
    Then I expand collapsed items
    Then page with name 'Workbench page' is opened
    And info: ---18. Verify that: Amortization values for REG_CONSULTANT1 and REG_PROJECTMANAGER are as expected---
    And 'Workbench' table contain row with following data:
      | Description              | REG_CONSULTANT1    |
      | Cost: Company Currency   | $ 0.00             |
      | 2025 Qtr: 2              | $ -58,016.60       |
      | 2025 Qtr: 3              | $ -58,654.44       |
      | 2025 Qtr: 4              | $ 22,155.09        |
      | 2026 Qtr: 1              | $ 94,515.95        |
    And 'Workbench' table contain row with following data:
      | Description              | REG_PROJECTMANAGER |
      | Cost: Company Currency   | $ 0.00             |
      | 2025 Qtr: 2              | $ -51,336.59       |
      | 2025 Qtr: 3              | $ 14,130.22        |
      | 2025 Qtr: 4              | $ 37,206.38        |

```

---

_Generated by `scripts/generate-testcase-guides.py` from the RTA mapping and local feature files._
