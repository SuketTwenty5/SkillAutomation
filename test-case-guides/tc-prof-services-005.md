# TC-Prof-Services-005 - Validate Workstream WBS Copy Release Estimate

| Field | Value |
| --- | --- |
| Test ID | TC-Prof-Services-005 |
| Title | Validate Workstream WBS Copy Release Estimate |
| RTA page | https://twenty5.atlassian.net/wiki/spaces/RTA/pages/200966255/TC-Prof-Services-005+Validate+Workstream+WBS+Copy+Release+Estimate |
| Map source | `RTA_TEST_SUITE_FEATURE_MAP.md` |

## Run Instructions

When a consultant asks to run this test, use the default app URL unless they provide another URL:

```text
https://approuter-twenty5ipe-dev.cfapps.us10.hana.ondemand.com/#quote
```

For Claude Desktop, ask before launching Selenium Chrome with the default URL. If the user provides another URL, launch that URL instead.

```bash
scripts/start-debug-chrome.sh "https://approuter-twenty5ipe-dev.cfapps.us10.hana.ondemand.com/#quote"
scripts/run-twentyfive-test.sh @TC-005
```

## Default mapped Selenium scenario

| Field | Value |
| --- | --- |
| Feature | 2. Cost Creation for Professional Services |
| Scenario | TC-Prof-Services-005: Validate Workstream [WBS] Copy & Release Estimate |
| Tags | `@TC-005 @START` |
| File | `imported/twentyfive-regtest/tests/src/test/resources/features/testfolder/cost_creation_for_professional_services.feature:5` |

### Scenario Sections

#### 1. Navigate to Workstreams

- open TC-Prof-Services-003 quote
- I perform login
- page with name 'Setup page' is opened
- I enter data into the next fields:
- page with name 'Setup page' is opened
- I enter data into the next fields:
- page with name 'Setup page' is opened
- I click on 'Save' button
- I wait for 7 seconds
- page with name 'Setup page' is opened
- I click on 'WBS' tab
- page with name 'WBS page' is opened
- I click on 'Update Cost & Prices' link
- I wait for 30 seconds
- I click on 'WBS' tab
- page with name 'WBS page' is opened
- if 'Needs Refresh' status is visible then refresh page
- page with name 'WBS page' is opened
- text of 'Total Price' widget equals 'USD 0'
- text of 'Total Cost' widget equals 'USD 2.7M'_-Warning-_
- ... 5 more steps

#### 2. Check selected View

- I select 'TEST:-DONOT_CHANGE' in the 'WBS View' dropdown
- I click on 'Save' button
- text of 'WBS View' dropdown equals 'View: TEST:-DONOT_CHANGE (preferred)'

#### 3. Review WBS grid

- I should see the following columns in the 'WBS' table:

#### 4. Verify WBS structure pulled in from Template and values

- 'WBS' table contain row with following data_-Warning-_:
- 'WBS' table contain row with following data:
- '1 Prof Services Estimate' WP is enabled

#### 5. Run Update Cost and Prices to refresh model via hyperlink

- I click on 'Update Cost & Prices' link
- I wait for 50 seconds

#### 6. Validate Cost & Prices update

- text of 'Total Cost' widget equals 'USD 2.7M'_-Warning-_

#### 7. Validate Cost & Prices update

- I select 'TEST:-DONOT_CHANGE' in the 'WBS View' dropdown
- I click on 'Save' button
- text of 'WBS View' dropdown equals 'View: TEST:-DONOT_CHANGE (preferred)'
- 'WBS' table contain row with following data_-Warning-_:
- 'WBS' table contain row with following data:

#### 8. Click Create hyperlink to Release Estimate

- I click 'Open' in table 'WBS' table for row with data:

#### 6. Navigate to the Estimate by clicking on the Confirm & Open button

- I switch to tab 2
- page with name 'Estimate Labor Resources' is opened
- 'UNLOCK FOR OTHERS' link is displayed
- I click on 'Update Estimate Totals' link
- I wait for 30 seconds
- page with name 'Estimate Labor Resources' is opened
- I refresh page
- I wait for 30 seconds
- I click on 'Labor' tab
- page with name 'Estimate Labor Resources' is opened

### Gherkin Excerpt

```gherkin
  Scenario: TC-Prof-Services-005: Validate Workstream [WBS] Copy & Release Estimate
    And info: ---1. Navigate to Workstreams---
    Given open TC-Prof-Services-003 quote
    And I perform login
    And page with name 'Setup page' is opened
    When I enter data into the next fields:
     |'Proposal Type' field | Regression Test Only - Consulting |
    And page with name 'Setup page' is opened
    When I enter data into the next fields:
      | 'Client Customer sell-to' field      | Regression Test - Customer USD |
    And page with name 'Setup page' is opened
    Then I click on 'Save' button
    And I wait for 7 seconds
    And page with name 'Setup page' is opened
    Given I click on 'WBS' tab
    And page with name 'WBS page' is opened
    And I click on 'Update Cost & Prices' link
    And I wait for 30 seconds
    Given I click on 'WBS' tab
    And page with name 'WBS page' is opened
    And if 'Needs Refresh' status is visible then refresh page
    And page with name 'WBS page' is opened
    And text of 'Total Price' widget equals 'USD 0'
    And text of 'Total Cost' widget equals 'USD 2.7M'_-Warning-_
    And text of 'Margin as Percentage' widget equals '0 %'
    And 'Status' widget is displayed
    And text of 'Cost & Price' status equals 'Updated'_-Warning-_
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
    And info: ---4. Verify WBS structure pulled in from Template and values ---
    And 'WBS' table contain row with following data_-Warning-_:
      | Estimate                                               | Open                     |
    And 'WBS' table contain row with following data:
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

    And '1 Prof Services Estimate' WP is enabled
    And info: ---5. Run Update Cost and Prices to refresh model via hyperlink ---
    And I click on 'Update Cost & Prices' link
    And I wait for 50 seconds
    And info: ---6. Validate Cost & Prices update ---
    Then text of 'Total Cost' widget equals 'USD 2.7M'_-Warning-_
    And info: ---7. Validate Cost & Prices update ---
    When I select 'TEST:-DONOT_CHANGE' in the 'WBS View' dropdown
    And I click on 'Save' button
    Then text of 'WBS View' dropdown equals 'View: TEST:-DONOT_CHANGE (preferred)'
    And 'WBS' table contain row with following data_-Warning-_:
      | Estimate                                               | Create                     |
    And 'WBS' table contain row with following data:
      | WBS                                                    | 1 Prof Services Estimate   |
      | WBS Code                                               | 1                          |
      | Start                                                  | 1/1/26                     |
      | End                                                    | 12/31/28                   |
      | Total Cost with Risk (without Opt) Cost in Local Currency | $ 2,704,635.20          |
      | Labor Cost                                             | $ 1,790,960.00             |
      | Material Cost                                          | $ 0.00                     |
      | Other Cost                                             | $ 0.00                     |
      | Delivery Organization                                  | RG10 US - New York         |
      | Profit Center                                          | Services                   |
      | Cost Center                                            | REG CC-2                   |
    And info: ---8. Click Create hyperlink to Release Estimate---
    And I click 'Open' in table 'WBS' table for row with data:
      | WBS  | 1 Prof Services Estimate   |
#    And 'Estimate' popUp is displayed
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
    Then I switch to tab 2
    And page with name 'Estimate Labor Resources' is opened
    And 'UNLOCK FOR OTHERS' link is displayed
    And I click on 'Update Estimate Totals' link
    And I wait for 30 seconds
    And page with name 'Estimate Labor Resources' is opened
    And I refresh page
    And I wait for 30 seconds
    And I click on 'Labor' tab
    And page with name 'Estimate Labor Resources' is opened

```

---

_Generated by `scripts/generate-testcase-guides.py` from the RTA mapping and local feature files._
