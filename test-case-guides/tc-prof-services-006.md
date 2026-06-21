# TC-Prof-Services-006 - Validate Labor Copy

| Field | Value |
| --- | --- |
| Test ID | TC-Prof-Services-006 |
| Title | Validate Labor Copy |
| RTA page | https://twenty5.atlassian.net/wiki/spaces/RTA/pages/200933488/TC-Prof-Services-006+Validate+Labor+Copy |
| Map source | `RTA_TEST_SUITE_FEATURE_MAP.md` |

## Run Instructions

When a consultant asks to run this test, use the default app URL unless they provide another URL:

```text
https://approuter-twenty5ipe-dev.cfapps.us10.hana.ondemand.com/#quote
```

For Claude Desktop, ask before launching Selenium Chrome with the default URL. If the user provides another URL, launch that URL instead.

```bash
scripts/start-debug-chrome.sh "https://approuter-twenty5ipe-dev.cfapps.us10.hana.ondemand.com/#quote"
scripts/run-twentyfive-test.sh @TC-006
```

## Default mapped Selenium scenario

| Field | Value |
| --- | --- |
| Feature | 2. Cost Creation for Professional Services |
| Scenario | TC-Prof-Services-006: Validate Labor Copy |
| Tags | `@TC-006 @RUN` |
| File | `imported/twentyfive-regtest/tests/src/test/resources/features/testfolder/cost_creation_for_professional_services.feature:119` |

### Scenario Sections

#### 1. Validate available tabs under Estimates module

- I see the following TABS in the 'Estimates' Top Menu_-Warning-_:

#### 2. Navigate to Labor tab

- I verify 'Estimating Methodology' of Labor page is enabled, not required, drop down and is empty
- 'Learn more' link is displayed
- 'COPY LABOR' button is displayed
- 'COPY LABOR' button is disabled
- number of rows in 'Labor' table equals 3
- 'Cog Settings' menu is displayed
- 'Cog Settings' menu is dropdown
- 'Filter' button is enabled
- 'Download' button is enabled
- 'Upload' button is enabled

#### 3. Check selected View

- I select 'Test View for Regression - DONOT CHANGE' in the 'View' dropdown
- I click on 'Save' button
- text of 'View' dropdown equals 'View: Test View for Regression - DONOT CHANGE (preferred)'
- page with name 'Estimate Labor Resources' is opened

#### 4. Review WBS grid

- I should see the following columns in the 'Labor' table:

#### 5. Confirm values pulled in from Template

- 'Labor' table contain row with following data:
- 'Labor' table contain row with following data:
- 'Labor' table contain row with following data:

#### 6. Click on FTE chevron to display all columns

- I hover on 'FTE' column header
- I click on 'FTE' column chevron
- 'Column' header options is displayed
- I click on 'Columns' option
- 'Columns' options is displayed

#### 7. Select G&A Cost column

- I click on 'GA Cost' option
- I click on 'FTE' column header
- 'Labor' table contain row with following data_-Warning-_:

#### 8. Click on G&A Cost chevron to display all columns

- I hover on 'GA Cost' column header
- I click on 'GA Cost' column chevron
- 'Column' header options is displayed
- I click on 'Columns' option
- 'Columns' options is displayed

#### 9. Select Overhead Cost column

- I click on 'Overhead Cost' option
- I click on 'GA Cost' column header
- 'Labor' table contain row with following data:

#### 10. Click on Overhead Cost chevron to display all columns

- I hover on 'Overhead Cost' column header
- I click on 'Overhead Cost' column chevron
- 'Column' header options is displayed
- I click on 'Columns' option
- 'Columns' options is displayed

#### 11. Select Fringe Cost column

- I click on 'Fringe Cost' option
- I click on 'Overhead Cost' column header
- 'Labor' table contain row with following data:

### Gherkin Excerpt

```gherkin
  Scenario: TC-Prof-Services-006: Validate Labor Copy
    And info: ---1. Validate available tabs under Estimates module---
    Then I see the following TABS in the 'Estimates' Top Menu_-Warning-_:
      | Labor                 |
      | Other                 |
      | Procurement & Production |
      | Risks & Contingency |
      | Work Packages |
      | Workflow              |
#    Then I see the following TABS in the 'Estimates' Top Menu_-Warning-_:
#      | Labor Resources  |
#      | Service          |
#      | IP               |
#      | Expenses         |
#      | Risk & Contingency |
#      | Work Packages    |
#      | Workflow         |
    And info: ---2. Navigate to Labor tab---
    And I verify 'Estimating Methodology' of Labor page is enabled, not required, drop down and is empty
    And 'Learn more' link is displayed
    And 'COPY LABOR' button is displayed
    And 'COPY LABOR' button is disabled
    And number of rows in 'Labor' table equals 3
    And 'Cog Settings' menu is displayed
    And 'Cog Settings' menu is dropdown
    And 'Filter' button is enabled
    And 'Download' button is enabled
    And 'Upload' button is enabled
    And info: ---3. Check selected View---
    When I select 'Test View for Regression - DONOT CHANGE' in the 'View' dropdown
    And I click on 'Save' button
    Then text of 'View' dropdown equals 'View: Test View for Regression - DONOT CHANGE (preferred)'
    And page with name 'Estimate Labor Resources' is opened
    And info: ---4. Review WBS grid---
    Then I should see the following columns in the 'Labor' table:
      | Resource Group     |
      | Description        |
      | Dept               |
      | Start              |
      | End                |
      | Distribution      |
      | FTE                |
      | Effort             |
      | Rate/Hr            |
      | Cost in Local Currency |
      | Distribution      |

    And info: ---5. Confirm values pulled in from Template---
    And 'Labor' table contain row with following data:
      | Resource Group           | REG_PROJECTMANAGER  |
      | Description              | REG_PROJECTMANAGER  |
      | Dept                     | RG10 US - New York  |
      | Start                    | 1/1/26              |
      | End                      | 12/31/28            |
      | Distribution             | Flat (working days per month)    |
      | Effort                   | 1000 hours          |
      | Rate/Hr                  | $ 110.00/hr         |
      | Cost in Local Currency   | $ 110,000.00        |
#      | FTE                      | 0.164 FTE           |
    #      | Sequence                 | 1                   |
#      | Billing Rate             | $ 240.00/hr         |

    And 'Labor' table contain row with following data:
      | Resource Group           | REG_CONSULTANT1     |
      | Description              | REG_CONSULTANT1     |
      | Dept                     | RG10 US - New York  |
      | Start                    | 1/1/26              |
      | End                      | 12/31/28            |
      | Distribution             | Flat (working days per month)    |
      | FTE                      | 2.000 FTE           |
      | Rate/Hr                  | $ 120.00/hr         |
      | Cost in Local Currency   | $ 1,464,960.00      |
#      | Effort                   | 12208 hours          |
    #      | Sequence                 | 2                   |
#      | Billing Rate             | $ 0.00/hr           |

    And 'Labor' table contain row with following data:
      | Resource Group           | REG_CONSULTANT2     |
      | Description              | REG_CONSULTANT2     |
      | Dept                     | RG10 US - New York  |
      | Start                    | 1/1/26              |
      | End                      | 12/31/28            |
      | Distribution             | Flat (working days per month)    |
      | Effort                   | 2400 hours          |
      | Rate/Hr                  | $ 90.00/hr          |
      | Cost in Local Currency   | $ 216,000.00        |
#      | FTE                      | 0.393 FTE           |
    #      | Sequence                 | 3                   |
#      | Billing Rate             | $ 0.00/hr           |
    And info: ---6. Click on FTE chevron to display all columns ---
    And I hover on 'FTE' column header
    And I click on 'FTE' column chevron
    Then 'Column' header options is displayed
    When I click on 'Columns' option
    Then 'Columns' options is displayed
    And info: ---7. Select G&A Cost column---
    And I click on 'GA Cost' option
    And I click on 'FTE' column header
    And 'Labor' table contain row with following data_-Warning-_:
      | Resource Group           | REG_CONSULTANT1     |
      | Description              | REG_CONSULTANT1     |
      | G&A Cost                 | $ 158,215.68        |
    And info: ---8. Click on G&A Cost chevron to display all columns ---
    And I hover on 'GA Cost' column header
    And I click on 'GA Cost' column chevron
    Then 'Column' header options is displayed
    When I click on 'Columns' option
    Then 'Columns' options is displayed
    And info: ---9. Select Overhead Cost column ---
    And I click on 'Overhead Cost' option
    And I click on 'GA Cost' column header
    And 'Labor' table contain row with following data:
      | Resource Group           | REG_CONSULTANT1     |
      | Description              | REG_CONSULTANT1     |
      | Overhead Cost            | $ 395,539.20        |
    And info: ---10. Click on Overhead Cost chevron to display all columns ---
    And I hover on 'Overhead Cost' column header
    And I click on 'Overhead Cost' column chevron
    Then 'Column' header options is displayed
    When I click on 'Columns' option
    Then 'Columns' options is displayed
    And info: ---11. Select Fringe Cost column ---
    And I click on 'Fringe Cost' option
    And I click on 'Overhead Cost' column header
    And 'Labor' table contain row with following data:
      | Resource Group           | REG_CONSULTANT1     |
      | Description              | REG_CONSULTANT1     |
      | Fringe Cost              | $ 512,736.00        |

```

---

_Generated by `scripts/generate-testcase-guides.py` from the RTA mapping and local feature files._
