# TC-Prof-Services-007 - Update Labor after Template Copy

| Field | Value |
| --- | --- |
| Test ID | TC-Prof-Services-007 |
| Title | Update Labor after Template Copy |
| RTA page | https://twenty5.atlassian.net/wiki/spaces/RTA/pages/201097217/TC-Prof-Services-007+Update+Labor+after+Template+Copy |
| Map source | `RTA_TEST_SUITE_FEATURE_MAP.md` |

## Run Instructions

When a consultant asks to run this test, use the default app URL unless they provide another URL:

```text
https://approuter-twenty5ipe-dev.cfapps.us10.hana.ondemand.com/#quote
```

For Claude Desktop, ask before launching Selenium Chrome with the default URL. If the user provides another URL, launch that URL instead.

```bash
scripts/start-debug-chrome.sh "https://approuter-twenty5ipe-dev.cfapps.us10.hana.ondemand.com/#quote"
scripts/run-twentyfive-test.sh @TC-007
```

## Default mapped Selenium scenario

| Field | Value |
| --- | --- |
| Feature | 2. Cost Creation for Professional Services |
| Scenario | TC-Prof-Services-007: Update Labor after Template Copy |
| Tags | `@TC-007 @RUN` |
| File | `imported/twentyfive-regtest/tests/src/test/resources/features/testfolder/cost_creation_for_professional_services.feature:249` |

### Scenario Sections

#### 1. Click the Add Resources [ + ] button

- I click '+' in table 'Labor' table in row number 3
- I wait for 5 seconds
- I set value 'Escalation United States' to the cell of the column 'Escalation Factor' of the 'Labor' table for the row with the following data:
- 'Labor' table contain row with following data:

#### 2. Enter Resource Group and type REG_QAMANAGER

- I set value 'REG_QAMANAGER' to the cell of the column 'Resource Group' of the 'Labor' table for the row with the following data:

#### 3. Press Enter

- 'Labor' table contain row with following data:

#### 4. Update Distribution by selecting dropdown

- I set value 'Flat (working days per month)' to the cell of the column 'Distribution' of the 'Labor' table for the row with the following data:

#### 5. Update FTE with 1.000 FTE column for new line item REG_QAMANAGER and press Enter

- I set value '1.00' to the cell of the column 'FTE' of the 'Labor' table for the row with the following data:

#### 6. Click Save button

- click on 'Save' button
- 'Data saved successfully' warning message is displayed in 30 seconds
- I wait for 7 seconds
- 'Labor' table contain row with following data:

#### 7. Update REG_PROJECTMANAGER line item by adjusting the Effort value to 1500 hours and press Enter

- I set value '1500' to the cell of the column 'Effort' of the 'Labor' table for the row with the following data:

#### 8. Click Save button

- click on 'Save' button
- 'Data saved successfully' warning message is displayed in 30 seconds
- 'Labor' table contain row with following data:

#### 9. Update the Formula-based Costs by selecting Hamburger menu and click Update Formula-based Costs option

- 'Hamburger' menu is displayed
- I click on 'Hamburger' menu
- I choose in 'Hamburger' menu the next menu chain:
- warning messages displayed in 60 seconds are:
- page with name 'Estimate Labor Resources' is opened
- Grand total cell value of the 'FTE' column of the 'Labor Dual' table equals '3.639 FTE'
- Grand total cell value of the 'Effort' column of the 'Labor Dual' table equals '22212 hr'
- Grand total cell value of the 'Cost in Local Currency' column of the 'Labor Dual' table equals '$ 2,364,800.00'

### Gherkin Excerpt

```gherkin
  Scenario: TC-Prof-Services-007: Update Labor after Template Copy
    And info: ---1. Click the Add Resources [ + ] button---
    And I click '+' in table 'Labor' table in row number 3
    And I wait for 5 seconds
    And I set value 'Escalation United States' to the cell of the column 'Escalation Factor' of the 'Labor' table for the row with the following data:
      | Sequence                 | 4                   |
    And 'Labor' table contain row with following data:
      | Resource Group           |                     |
      | Description              |                     |
      | Dept                     | RG10 US - New York  |
      | Start                    | 1/1/26              |
      | End                      | 12/31/28            |
      | FTE                      | 0.000 FTE           |
      | Effort           |                     |
      | Rate/Hr         | $ 0.00/hr           |
      | Cost in Local Currency   | $ 0.00              |
      | Sequence                 | 4                   |
      | Escalation Factor        | Escalation United States |
#      | Billing Rate             | $ 0.00/hr           |

    And info: ---2. Enter Resource Group and type REG_QAMANAGER---
    And I set value 'REG_QAMANAGER' to the cell of the column 'Resource Group' of the 'Labor' table for the row with the following data:
      | Sequence                 | 4                   |
    And info: ---3. Press Enter---
    And 'Labor' table contain row with following data:
      | Resource Group           | REG_QAMANAGER       |
      | Description              | REG_QAMANAGER       |
      | Dept                     | RG10 US - New York  |
      | Start                    | 1/1/26              |
      | End                      | 12/31/28            |
      | FTE                      | 0.000 FTE           |
      | Effort                   |                     |
      | Distribution             | Manual (Monthly)    |
      | Rate/Hr          | $ 85.00/hr          |
      | Cost in Local Currency   | $ 0.00              |
      | Sequence                 | 4                   |
#      | Billing Rate             | $ 0.00/hr           |

    And info: ---4. Update Distribution by selecting dropdown---
    And I set value 'Flat (working days per month)' to the cell of the column 'Distribution' of the 'Labor' table for the row with the following data:
      | Resource Group | REG_QAMANAGER |
    And info: ---5. Update FTE with 1.000 FTE column for new line item REG_QAMANAGER and press Enter---
    And I set value '1.00' to the cell of the column 'FTE' of the 'Labor' table for the row with the following data:
      | Resource Group | REG_QAMANAGER |
    And info: ---6. Click Save button---
    Then click on 'Save' button
    And 'Data saved successfully' warning message is displayed in 30 seconds
    And I wait for 7 seconds
    And 'Labor' table contain row with following data:
      | Resource Group           | REG_QAMANAGER       |
      | Start                    | 1/1/26              |
      | End                      | 12/31/28            |
      | FTE                      | 1.000 FTE           |
      | Rate/Hr                  | $ 85.00/hr          |
#      | Cost in Local Currency   | $ 85,000.00         |
#      | Sequence                 | 4                   |
#      | Billing Rate             | $ 0.00/hr           |
    And info: ---7. Update REG_PROJECTMANAGER line item by adjusting the Effort value to 1500 hours and press Enter---
    And I set value '1500' to the cell of the column 'Effort' of the 'Labor' table for the row with the following data:
      | Resource Group | REG_PROJECTMANAGER |
#    And I click on 'Total Effort' checkBox
#    When I enter data into the next fields:
#      | 'Distribute Total Effort' field | Flat (all months equal) |
#    Then I click on 'Confirm' button
    And info: ---8. Click Save button ---
    Then click on 'Save' button
#    And page with name 'Estimate Labor Resources' is opened
    And 'Data saved successfully' warning message is displayed in 30 seconds
    And 'Labor' table contain row with following data:
      | Resource Group | REG_PROJECTMANAGER |
      | Effort | 1500 hours         |
    And info: ---9. Update the Formula-based Costs by selecting Hamburger menu and click Update Formula-based Costs option ---
    And 'Hamburger' menu is displayed
    Then I click on 'Hamburger' menu
    Then I choose in 'Hamburger' menu the next menu chain:
      | Update Formula-based Costs  |
    And warning messages displayed in 60 seconds are:
    """
    Formula costs being re-calculated and rolled-up to WBS and contract lines
    ---
    Costs/revenues & formula-based costs updated & rolled-up successfully
    """
    And info: ---10.
    And page with name 'Estimate Labor Resources' is opened
    And Grand total cell value of the 'FTE' column of the 'Labor Dual' table equals '3.639 FTE'
    And Grand total cell value of the 'Effort' column of the 'Labor Dual' table equals '22212 hr'
    And Grand total cell value of the 'Cost in Local Currency' column of the 'Labor Dual' table equals '$ 2,364,800.00'


```

---

_Generated by `scripts/generate-testcase-guides.py` from the RTA mapping and local feature files._
