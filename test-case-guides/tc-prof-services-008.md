# TC-Prof-Services-008 - Add Labor Amortization

| Field | Value |
| --- | --- |
| Test ID | TC-Prof-Services-008 |
| Title | Add Labor Amortization |
| RTA page | https://twenty5.atlassian.net/wiki/spaces/RTA/pages/211615745/TC-Prof-Services-008+Add+Labor+Amortization |
| Map source | `RTA_TEST_SUITE_FEATURE_MAP.md` |

## Run Instructions

When a consultant asks to run this test, use the default app URL unless they provide another URL:

```text
https://approuter-twenty5ipe-dev.cfapps.us10.hana.ondemand.com/#quote
```

For Claude Desktop, ask before launching Selenium Chrome with the default URL. If the user provides another URL, launch that URL instead.

```bash
scripts/start-debug-chrome.sh "https://approuter-twenty5ipe-dev.cfapps.us10.hana.ondemand.com/#quote"
scripts/run-twentyfive-test.sh @TC-008
```

## Default mapped Selenium scenario

| Field | Value |
| --- | --- |
| Feature | 2. Cost Creation for Professional Services |
| Scenario | TC-Prof-Services-008: Add Labor Amortization |
| Tags | `@TC-008 @RUN` |
| File | `imported/twentyfive-regtest/tests/src/test/resources/features/testfolder/cost_creation_for_professional_services.feature:339` |

### Scenario Sections

#### Setup

- page with name 'Estimate Labor Resources' is opened

#### 1. Hover on the REG_PROJECTMANAGER resource Description line item \|\| 2. Click the gear icon [  ]

- I hover 'Description' cell in table 'Labor' table and click  for row with data:
- 'Description Gear' menuItems contains items:
- 'Description Gear' menuItems contains items_-Warning-_:

#### 3. Click Edit Details

- I click on 'Edit Details' menuItem
- 'Estimate for Labor' popup is displayed
- I verify tabs available in the popup are:
- I click on 'Labor Amortization' tab
- I verify 'Amortization Method' of Labor page is enabled, not required, drop down and is empty
- 'Update Cost' link is displayed
- I verify 'Amortization Period (months)' of Labor page is enabled, not required, numeric selection and has text '0'
- I verify 'Start of Amortization' of Labor page is enabled, not required, date picker and is empty
- I verify 'Set start of amortization to Go-live or Incurred' of Labor page is enabled, not required, check box and is unchecked
- text of 'Total Value' dropdown equals '165,000.00'
- I verify 'End of Amortization' of Labor page is enabled, not required, date picker and is empty
- I verify 'Set end of amortization to contract end' of Labor page is enabled, not required, check box and is unchecked
- I should see the following columns in the 'Amortization Schedule' table:
- the cell value of the 'Incurred Cost' column of the 'Amortization Schedule' table equals '$ 0.00' for a row with the index '1'
- the cell value of the 'Amortization' column of the 'Amortization Schedule' table equals '$ 0.00' for a row with the index '1'
- 'Confirm' button is displayed
- 'Close' button is displayed

#### 4. Update the Amortization Method via the dropdown

- I enter data into the next fields:
- I click on 'Estimate for Labor' popup
- text of 'Amortization Method' field equals 'Straight Line'

#### 5. Enable Set start of amortization to Go-live or incurred checkbox

- I click on 'Start of Amortization' checkBox
- I click on 'Estimate for Labor' popup
- text of 'Start of Amortization' datePicker equals '1/1/26'
- I click on 'End of Amortization' checkBox
- I click on 'Estimate for Labor' popup

#### 6. Click Close

- I click on 'Close' button
- page with name 'Estimate Labor Resources' is opened

#### 7. Navigate back to Estimate for Labor - REG_PROJECTMANAGER popup by clicking gear icon [  ] and Edit Details

- I hover 'Description' cell in table 'Labor' table and click  for row with data:
- I click on 'Edit Details' menuItem
- I verify tabs available in the popup are:
- I verify 'Amortization Method' of Labor page is enabled, not required, drop down and is empty
- 'Update Cost' link is displayed
- I verify 'Amortization Period (months)' of Labor page is enabled, not required, numeric selection and has text '0'
- I verify 'Start of Amortization' of Labor page is enabled, not required, date picker and is empty
- I verify 'Set start of amortization to Go-live or Incurred' of Labor page is enabled, not required, check box and is unchecked
- text of 'Total Value' dropdown equals '165,000.00'
- I verify 'End of Amortization' of Labor page is enabled, not required, date picker and is empty
- I verify 'Set end of amortization to contract end' of Labor page is enabled, not required, check box and is unchecked
- I should see the following columns in the 'Amortization Schedule' table:
- the cell value of the 'Incurred Cost' column of the 'Amortization Schedule' table equals '$ 0.00' for a row with the index '1'
- the cell value of the 'Amortization' column of the 'Amortization Schedule' table equals '$ 0.00' for a row with the index '1'
- 'Confirm' button is displayed
- 'Close' button is displayed

#### 8. Update the Amortization Method via the dropdown

- I enter data into the next fields:
- text of 'Amortization Method' field equals 'Straight Line'

#### 9. Enable Set start of amortization to Go-live or incurred checkbox

- I click on 'Start of Amortization' checkBox
- text of 'Start of Amortization' datePicker equals '1/1/26'

#### 10. Enable Set end of amortization to contract end checkbox

- I click on 'End of Amortization' checkBox
- text of 'End of Amortization' datePicker equals '12/31/28'

#### 11. Check the totals for Incurred Cost, and Amortization

- I verify 'Amortization Period (months)' of Labor page is enabled, not required, numeric selection and has text '36'
- Grand total cell value of the 'Incurred Cost' column of the 'Amortization Schedule' table equals '$ 165,000.00'
- Grand total cell value of the 'Amortization' column of the 'Amortization Schedule' table equals '$ 165,000.00'

#### 12. Check the Balance (Residual Value) field

- 'Amortization Schedule' table contain row with following data:

#### 13. Click Confirm

- I click on 'Confirm' button
- 'Estimate for Labor' popUp is not displayed
- page with name 'Estimate Labor Resources' is opened

#### 14. Navigate back to Navigate back to Estimate for Labor - REG_PROJECTMANAGER popup by clicking gear icon [  ] and Edit Details

- I hover 'Description' cell in table 'Labor' table and click  for row with data:
- I click on 'Edit Details' menuItem
- I verify tabs available in the popup are:
- I verify 'Amortization Method' of Labor page is enabled, not required, drop down and has text 'Straight Line'
- 'Update Cost' link is displayed
- I verify 'Amortization Period (months)' of Labor page is enabled, not required, numeric selection and has text '36'
- text of 'Start of Amortization' datePicker equals '1/1/26'
- I verify 'Start amortization at incurred start' of Labor page is enabled, not required, check box and is checked
- text of 'Total Value' dropdown equals '165,000.00'
- text of 'End of Amortization' datePicker equals '12/31/28'
- I verify 'End amortization at contract end' of Labor page is enabled, not required, check box and is checked
- I should see the following columns in the 'Amortization Schedule' table:
- Grand total cell value of the 'Incurred Cost' column of the 'Amortization Schedule' table equals '$ 165,000.00'
- Grand total cell value of the 'Amortization' column of the 'Amortization Schedule' table equals '$ 165,000.00'
- 'Amortization Schedule' table contain row with following data:
- 'Confirm' button is displayed
- 'Close' button is displayed

#### 15. Click Close

- I click on 'Close' button
- page with name 'Estimate Labor Resources' is opened

#### 16. Click Save

- click on 'Save' button
- I wait for 7 seconds

### Gherkin Excerpt

```gherkin
  Scenario: TC-Prof-Services-008: Add Labor Amortization
    And page with name 'Estimate Labor Resources' is opened
    And info: ---1. Hover on the REG_PROJECTMANAGER resource Description line item || 2. Click the gear icon [  ]---
    And I hover 'Description' cell in table 'Labor' table and click  for row with data:
      | Description | REG_PROJECTMANAGER |
    And 'Description Gear' menuItems contains items:
      | Edit Description |
      | Edit Details     |
      | View Resource Group Master  |
    And 'Description Gear' menuItems contains items_-Warning-_:
      | Mass Update        |
    And info: ---3. Click Edit Details---
    And I click on 'Edit Details' menuItem
    And 'Estimate for Labor' popup is displayed
    Then I verify tabs available in the popup are:
      | LABOR ESTIMATE      |
      | Tags & Factors      |
      | ACTUALS & FORECAST  |
      | Labor Amortization  |
      | Remarks             |
      | History             |
#      | Project Change Requests |

    And I click on 'Labor Amortization' tab
    And I verify 'Amortization Method' of Labor page is enabled, not required, drop down and is empty
    And 'Update Cost' link is displayed
    And I verify 'Amortization Period (months)' of Labor page is enabled, not required, numeric selection and has text '0'
    And I verify 'Start of Amortization' of Labor page is enabled, not required, date picker and is empty
    And I verify 'Set start of amortization to Go-live or Incurred' of Labor page is enabled, not required, check box and is unchecked
    And text of 'Total Value' dropdown equals '165,000.00'
    And I verify 'End of Amortization' of Labor page is enabled, not required, date picker and is empty
    And I verify 'Set end of amortization to contract end' of Labor page is enabled, not required, check box and is unchecked
    Then I should see the following columns in the 'Amortization Schedule' table:
      | Month        |
      | Incurred Cost     |
      | Amortization  |
      | Balance (Residual Value) |
    And the cell value of the 'Incurred Cost' column of the 'Amortization Schedule' table equals '$ 0.00' for a row with the index '1'
    And the cell value of the 'Amortization' column of the 'Amortization Schedule' table equals '$ 0.00' for a row with the index '1'
    And 'Confirm' button is displayed
    And 'Close' button is displayed
    And info: ---4. Update the Amortization Method via the dropdown ---
    When I enter data into the next fields:
      | 'Amortization Method' field        | Straight Line           |
    And I click on 'Estimate for Labor' popup
    And text of 'Amortization Method' field equals 'Straight Line'
    And info: ---5. Enable Set start of amortization to Go-live or incurred checkbox ---
    And I click on 'Start of Amortization' checkBox
    And I click on 'Estimate for Labor' popup
    And text of 'Start of Amortization' datePicker equals '1/1/26'
    And I click on 'End of Amortization' checkBox
    And I click on 'Estimate for Labor' popup
    And info: ---6. Click Close ---
    And I click on 'Close' button
    And page with name 'Estimate Labor Resources' is opened
    And info: ---7. Navigate back to Estimate for Labor - REG_PROJECTMANAGER popup by clicking gear icon [  ] and Edit Details ---
    And I hover 'Description' cell in table 'Labor' table and click  for row with data:
      | Description | REG_PROJECTMANAGER |
    And I click on 'Edit Details' menuItem
    Then I verify tabs available in the popup are:
      | LABOR ESTIMATE  |
      | Tags           |
      | ACTUALS & FORECAST      |
      | Labor Amortization      |
      | Remarks                 |
      | History             |
#      | Project Change Requests |
    And I verify 'Amortization Method' of Labor page is enabled, not required, drop down and is empty
    And 'Update Cost' link is displayed
    And I verify 'Amortization Period (months)' of Labor page is enabled, not required, numeric selection and has text '0'
    And I verify 'Start of Amortization' of Labor page is enabled, not required, date picker and is empty
    And I verify 'Set start of amortization to Go-live or Incurred' of Labor page is enabled, not required, check box and is unchecked
    And text of 'Total Value' dropdown equals '165,000.00'
    And I verify 'End of Amortization' of Labor page is enabled, not required, date picker and is empty
    And I verify 'Set end of amortization to contract end' of Labor page is enabled, not required, check box and is unchecked
    Then I should see the following columns in the 'Amortization Schedule' table:
      | Month        |
      | Incurred Cost     |
      | Amortization  |
      | Balance (Residual Value) |
    And the cell value of the 'Incurred Cost' column of the 'Amortization Schedule' table equals '$ 0.00' for a row with the index '1'
    And the cell value of the 'Amortization' column of the 'Amortization Schedule' table equals '$ 0.00' for a row with the index '1'
    And 'Confirm' button is displayed
    And 'Close' button is displayed
    And info: ---8. Update the Amortization Method via the dropdown ---
    When I enter data into the next fields:
      | 'Amortization Method' field        | Straight Line           |
    And text of 'Amortization Method' field equals 'Straight Line'
    And info: ---9. Enable Set start of amortization to Go-live or incurred checkbox ---
    And I click on 'Start of Amortization' checkBox
    And text of 'Start of Amortization' datePicker equals '1/1/26'
    And info: ---10. Enable Set end of amortization to contract end checkbox---
    And I click on 'End of Amortization' checkBox
    And text of 'End of Amortization' datePicker equals '12/31/28'
    And info: ---11. Check the totals for Incurred Cost, and Amortization ---
    And I verify 'Amortization Period (months)' of Labor page is enabled, not required, numeric selection and has text '36'
    And Grand total cell value of the 'Incurred Cost' column of the 'Amortization Schedule' table equals '$ 165,000.00'
    And Grand total cell value of the 'Amortization' column of the 'Amortization Schedule' table equals '$ 165,000.00'
    And info: ---12. Check the Balance (Residual Value) field---
    And 'Amortization Schedule' table contain row with following data:
      | Month                    | Dec-2028            |
      | Balance (Residual Value) | 0.00                |
    And info: ---13. Click Confirm---
    Then I click on 'Confirm' button
    Then 'Estimate for Labor' popUp is not displayed
    And page with name 'Estimate Labor Resources' is opened
    And info: ---14. Navigate back to Navigate back to Estimate for Labor - REG_PROJECTMANAGER popup by clicking gear icon [  ] and Edit Details---
    And I hover 'Description' cell in table 'Labor' table and click  for row with data:
      | Description | REG_PROJECTMANAGER |
    And I click on 'Edit Details' menuItem
    Then I verify tabs available in the popup are:
      | LABOR ESTIMATE  |
      | Tags           |
      | ACTUALS & FORECAST  |
      | Labor Amortization  |
      | Remarks          |
      | History             |
#      | Project Change Requests |
    And I verify 'Amortization Method' of Labor page is enabled, not required, drop down and has text 'Straight Line'
    And 'Update Cost' link is displayed
    And I verify 'Amortization Period (months)' of Labor page is enabled, not required, numeric selection and has text '36'
    And text of 'Start of Amortization' datePicker equals '1/1/26'
    And I verify 'Start amortization at incurred start' of Labor page is enabled, not required, check box and is checked
    And text of 'Total Value' dropdown equals '165,000.00'
    And text of 'End of Amortization' datePicker equals '12/31/28'
    And I verify 'End amortization at contract end' of Labor page is enabled, not required, check box and is checked
    Then I should see the following columns in the 'Amortization Schedule' table:
      | Month        |
      | Incurred Cost     |
      | Amortization  |
      | Balance (Residual Value) |
    And Grand total cell value of the 'Incurred Cost' column of the 'Amortization Schedule' table equals '$ 165,000.00'
    And Grand total cell value of the 'Amortization' column of the 'Amortization Schedule' table equals '$ 165,000.00'
    And 'Amortization Schedule' table contain row with following data:
      | Month                    | Dec-2028            |
      | Balance (Residual Value) | 0.00                |
    And 'Confirm' button is displayed
    And 'Close' button is displayed
    And info: ---15. Click Close---
    And I click on 'Close' button
    And page with name 'Estimate Labor Resources' is opened
    And info: ---16. Click Save---
    Then click on 'Save' button
    And I wait for 7 seconds

```

---

_Generated by `scripts/generate-testcase-guides.py` from the RTA mapping and local feature files._
