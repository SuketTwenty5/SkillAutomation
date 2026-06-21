# TC-Prof-Services-004 - Validate Copy Proposal Results Reschedule Functionality

| Field | Value |
| --- | --- |
| Test ID | TC-Prof-Services-004 |
| Title | Validate Copy Proposal Results Reschedule Functionality |
| RTA page | https://twenty5.atlassian.net/wiki/spaces/RTA/pages/191102977/TC-Prof-Services-004+Validate+Copy+Proposal+Results+Reschedule+Functionality |
| Map source | `RTA_TEST_SUITE_FEATURE_MAP.md` |

## Run Instructions

When a consultant asks to run this test, use the default app URL unless they provide another URL:

```text
https://approuter-twenty5ipe-dev.cfapps.us10.hana.ondemand.com/#quote
```

For Claude Desktop, ask before launching Selenium Chrome with the default URL. If the user provides another URL, launch that URL instead.

```bash
scripts/start-debug-chrome.sh "https://approuter-twenty5ipe-dev.cfapps.us10.hana.ondemand.com/#quote"
scripts/run-twentyfive-test.sh @TC-004
```

## Default mapped Selenium scenario

| Field | Value |
| --- | --- |
| Feature | 1. Setup Professional Services Proposal |
| Scenario | TC-Prof-Services-004: Verify Template Copy Results & Reschedule Functionality |
| Tags | `@TC-004 @END` |
| File | `imported/twentyfive-regtest/tests/src/test/resources/features/testfolder/setup_professional_services_proposal.feature:243` |

### Scenario Sections

#### 1. Navigate to Workstreams

- I click on 'WBS' tab
- page with name 'WBS page' is opened
- 'Running' status is not displayed
- I click on 'Update Cost' button
- I wait for 5 seconds
- 'Running' status is not displayed
- I click on 'WBS' tab
- page with name 'WBS page' is opened
- if 'Needs Refresh' status is visible then refresh page
- page with name 'WBS page' is opened
- text of 'Total Price' widget equals 'USD 0'
- text of 'Total Cost' widget equals 'USD 2.7M'_-Warning-_
- text of 'Margin as Percentage' widget equals '0 %'
- 'Status' widget is displayed
- text of 'Cost & Price' status equals 'Updated'
- 'Update Cost & Prices' link is displayed
- text of 'Status & logs' link equals 'Errors: 0 jobs, 0 checks'

#### 2. Check selected View

- I select 'TEST:-DONOT_CHANGE' in the 'WBS View' dropdown
- I click on 'Save' button
- text of 'WBS View' dropdown equals 'View: TEST:-DONOT_CHANGE (preferred)'

#### 3. Review WBS grid

- I should see the following columns in the 'WBS' table:

#### 4. Check values pulled in from selected Template

- 'WBS' table contain row with following data_-Warning-_:
- 'WBS' table contain row with following data_-Warning-_:
- '1 Prof Services Estimate' WP is enabled

#### 5. Check Estimate further by clicking on Create hyperlink

- I click 'Open' in table 'WBS' table for row with data:

#### 6. Navigate to the Estimate by clicking on the Confirm & Open button

- page with name 'WBS page' is opened
- I switch to tab 2
- I click on 'Labor' tab
- page with name 'Estimate Labor Resources' is opened
- 'UNLOCK FOR OTHERS' link is displayed

#### 7. Check available tabs within Estimates

- I see the following TABS in the 'Estimates' Top Menu_-Warning-_:

#### 8. Review Labor Resources tab

- I verify 'Estimating Methodology' of labor page is enabled, not required, drop down and is empty
- 'Learn more' link is displayed
- 'COPY LABOR' button is disabled

#### 9. Check selected View

- I select 'Test View for Regression' in the 'View' dropdown
- I click on 'Save' button
- text of 'View' dropdown equals 'View: Test View for Regression - DONOT CHANGE (preferred)'

#### 10. Review Labor grid

- I should see the following columns in the 'Labor' table:

#### 11. Check Labor grid for Monthly Distribution values

- I choose in 'Cog Settings' menu the next menu chain:
- I wait for 7 seconds
- I if visible click on 'Yes' button
- I verify column Hours by Week distribution between dates '1/1/2026' and '12/31/2028'
- I choose in 'Cog Settings' menu the next menu chain:
- I wait for 7 seconds
- I if visible click on 'Yes' button
- I wait for 20 seconds
- I verify column Hours by Month distribution between dates '1/1/2026' and '12/31/2028'

### Gherkin Excerpt

```gherkin
  Scenario: TC-Prof-Services-004: Verify Template Copy Results & Reschedule Functionality
    And info: ---1. Navigate to Workstreams---
    Given I click on 'WBS' tab
    And page with name 'WBS page' is opened
    And 'Running' status is not displayed
    Then I click on 'Update Cost' button
    And I wait for 5 seconds
    And 'Running' status is not displayed
    Given I click on 'WBS' tab
    And page with name 'WBS page' is opened
    And if 'Needs Refresh' status is visible then refresh page
    And page with name 'WBS page' is opened
    And text of 'Total Price' widget equals 'USD 0'
    And text of 'Total Cost' widget equals 'USD 2.7M'_-Warning-_
    And text of 'Margin as Percentage' widget equals '0 %'
    And 'Status' widget is displayed
    And text of 'Cost & Price' status equals 'Updated'
    And 'Update Cost & Prices' link is displayed
    And text of 'Status & logs' link equals 'Errors: 0 jobs, 0 checks'
    And info: ---2. Check selected View---
    When I select 'TEST:-DONOT_CHANGE' in the 'WBS View' dropdown
    And I click on 'Save' button
    Then text of 'WBS View' dropdown equals 'View: TEST:-DONOT_CHANGE (preferred)'
    And info: ---3. Review WBS grid---
    Then I should see the following columns in the 'WBS' table:
      | WBS                                |
      | WBS Code                           |
      | WP                                 |
      | Start                              |
      | End                                |
      | Estimate                           |
      | Total Cost with Risk (without Opt) Cost in Local Currency |
      | Labor Cost                         |
      | Material Cost                      |
      | Other Cost                         |
      | Delivery Organization              |
      | Profit Center                      |
      | Cost Center                        |
    And info: ---4. Check values pulled in from selected Template---
    And 'WBS' table contain row with following data_-Warning-_:
      | WBS                                                    | 1 Prof Services Estimate   |
      | WBS Code                                               | 1                          |
      | Start                                                  | 1/1/2026                   |
      | End                                                    | 12/31/2028                 |
      | Total Cost with Risk (without Opt) Cost in Local Currency | $ 2,704,635.20          |
      | Labor Cost                                             | $ 1,790,960.00             |
      | Material Cost                                          | $ 0.00                     |
      | Other Cost                                             | $ 0.00                     |
      | Delivery Organization                                  | RG10 US - New York         |
      | Profit Center                                          | Services                   |
      | Cost Center                                            | REG CC-2                   |
#      | Delivery Organization             | Professional Services          |
#      | Profit Center           | US - West Coast                |
#      | Cost Center           | Engineering Consulting         |

    And 'WBS' table contain row with following data_-Warning-_:
      | Estimate                                               | Create                       |
    And '1 Prof Services Estimate' WP is enabled
    And info: ---5. Check Estimate further by clicking on Create hyperlink---
    And I click 'Open' in table 'WBS' table for row with data:
      | WBS  | 1 Prof Services Estimate   |
#    And I click 'Create' in table 'WBS' table for row with data:
#      | WBS  | 1 Prof Services Estimate   |
#    And 'Estimate' popUp is displayed
#    And I wait for 7 seconds
#    And I verify 'Owner' of WBS Dialog page is enabled, required, drop down and has text 'Tech User'
#    And I verify 'Approver' of WBS Dialog page is enabled, not required, drop down and is empty
#    And I verify 'Estimating Strategy' of WBS Dialog page is enabled, required, free text and has tags 'Labor,Other,Procurement & Production'
#    And I verify 'Response Due' of WBS Dialog page is enabled, not required, date picker and is empty
#    And I verify 'Work-stream' of WBS Dialog page is enabled, not required, drop down and is empty
#    And I verify 'Sub-work' of WBS Dialog page is enabled, not required, drop down and is empty
#    And I verify 'Design to Cost Target' of WBS Dialog page is enabled, not required, free text and has text '$'
#    And I verify 'DTC Target Hours' of WBS Dialog page is enabled, not required, free text and is empty
#    And I click on 'Confirm & Open' button
    And info: ---6. Navigate to the Estimate by clicking on the Confirm & Open button---
    And page with name 'WBS page' is opened
    Then I switch to tab 2
    And I click on 'Labor' tab
    And page with name 'Estimate Labor Resources' is opened
    And 'UNLOCK FOR OTHERS' link is displayed
    And info: ---7. Check available tabs within Estimates---
    Then I see the following TABS in the 'Estimates' Top Menu_-Warning-_:
      | Labor                 |
      | Other                 |
      | Procurement & Production |
      | Risks & Contingency |
      | Work Packages |
      | Workflow              |
#      | Labor Resources       |
#  | Service               |
#  | IP                    |
#  | Expenses              |
#  | Risk & Contingency    |
#  | Work Packages         |
    And info: ---8. Review Labor Resources tab---
    And I verify 'Estimating Methodology' of labor page is enabled, not required, drop down and is empty
    And 'Learn more' link is displayed
    And 'COPY LABOR' button is disabled
    And info: ---9. Check selected View---
    When I select 'Test View for Regression' in the 'View' dropdown
    And I click on 'Save' button
    Then text of 'View' dropdown equals 'View: Test View for Regression - DONOT CHANGE (preferred)'
    And info: ---10. Review Labor grid---
    Then I should see the following columns in the 'Labor' table:
      | Description        |
      | Resource Group     |
      | Dept               |
      | Start              |
      | End                |
      | Distribution       |
      | FTE                |
      | Effort             |
      | Rate/Hr            |
      | Cost in Local Currency |
    And info: ---11. Check Labor grid for Monthly Distribution values---
    Then I choose in 'Cog Settings' menu the next menu chain:
      | Change Planned Effort Input Mode |
      | Hours by Week                    |
    Then I wait for 7 seconds
#    And page with name 'Estimate Labor Resources' is opened
#    Then 'Change Planning Mode' window is displayed
    And I if visible click on 'Yes' button
#    And page with name 'Estimate Labor Resources' is opened
    Then I verify column Hours by Week distribution between dates '1/1/2026' and '12/31/2028'
    Then I choose in 'Cog Settings' menu the next menu chain:
      | Change Planned Effort Input Mode |
      | Hours by Month                   |
    Then I wait for 7 seconds
#    And page with name 'Estimate Labor Resources' is opened
#    Then 'Change Planning Mode' window is displayed
    And I if visible click on 'Yes' button
    Then I wait for 20 seconds
    Then I verify column Hours by Month distribution between dates '1/1/2026' and '12/31/2028'






















```

---

_Generated by `scripts/generate-testcase-guides.py` from the RTA mapping and local feature files._
