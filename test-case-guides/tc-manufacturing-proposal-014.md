# TC-Manufacturing-Proposal-014 - Switch from Auto to Manual Distribution

| Field | Value |
| --- | --- |
| Test ID | TC-Manufacturing-Proposal-014 |
| Title | Switch from Auto to Manual Distribution |
| RTA page | https://twenty5.atlassian.net/wiki/spaces/RTA/pages/375193629/TC-Manufacturing-Proposal-014+Switch+from+Auto+to+Manual+Distribution |
| Map source | `RTA_TEST_SUITE_FEATURE_MAP.md` |

## Run Instructions

When a consultant asks to run this test, use the default app URL unless they provide another URL:

```text
https://approuter-twenty5ipe-dev.cfapps.us10.hana.ondemand.com/#quote
```

For Claude Desktop, ask before launching Selenium Chrome with the default URL. If the user provides another URL, launch that URL instead.

```bash
scripts/start-debug-chrome.sh "https://approuter-twenty5ipe-dev.cfapps.us10.hana.ondemand.com/#quote"
scripts/run-twentyfive-test.sh @TCM-014
```

Duplicate feature matches exist for this Confluence page. The tag command may run more than one matching scenario until the canonical feature file is confirmed.

## Default mapped Selenium scenario

| Field | Value |
| --- | --- |
| Feature | 8. Distribute Labor Costs using Manual Effort input |
| Scenario | TC-Manufacturing-Proposal-014: Switch from Auto to Manual Distribution |
| Tags | `@TCM-014 @END` |
| File | `imported/twentyfive-regtest/tests/src/test/resources/features/testfolder/addManualLaborManuProposal.feature:221` |

### Scenario Sections

#### 1. For REG_CONSULTANT3 row enter Test Data Value in Planned Effort  900, and verify field accepts the value

- I set value '900' to the cell of the column 'Effort' of the 'Labor Dual' table for the row with the following data:
- I wait for 3 seconds

#### 2. Click Save and Refresh  verify data is saved, page refreshed, Effort Grid for REG_CONSULTANT3 = 75 for all months

- I click on 'Save' button
- I wait for 20 seconds
- I click on 'Refresh' button
- I wait for 30 seconds
- I select 'Regression Test - Manufacturing' in the 'View' dropdown
- I click on 'Save' button
- text of 'View' dropdown equals 'View: Regression Test - Manufacturing (shared & preferred)'
- 'Labor Dual' table contain row with following data:

#### 3. For REG_CONSULTANT3 select Test Data Value in Distribution field  Manual (Monthly), and verify value is accepted

- I set value 'Manual (Monthly)' to the cell of the column 'Distribution' of the 'Labor Dual' table for the row with the following data:
- I wait for 3 seconds

#### 4. Click Save and Refresh  verify data is saved, page refreshed, Distribution = Manual (Monthly), Effort Grid = 75 for all months

- I click on 'Save' button
- I wait for 20 seconds
- I click on 'Refresh' button
- I wait for 30 seconds
- I select 'Regression Test - Manufacturing' in the 'View' dropdown
- I click on 'Save' button
- text of 'View' dropdown equals 'View: Regression Test - Manufacturing (shared & preferred)'

#### 5. In REG_CONSULTANT3 Effort Grid update values  Apr-25 = NULL, May-25 = 150, Oct-25 = NULL, Nov-25 = 150, verify fields accept data

- I set value ' ' to the cell of the column 'Apr-25' of the 'Labor Dual' table for the row with the following data:
- I wait for 3 seconds
- I set value '150' to the cell of the column 'May-25' of the 'Labor Dual' table for the row with the following data:
- I wait for 3 seconds
- I set value '75' to the cell of the column 'Jun-25' of the 'Labor Dual' table for the row with the following data:
- I wait for 3 seconds
- I set value '75' to the cell of the column 'Jul-25' of the 'Labor Dual' table for the row with the following data:
- I wait for 3 seconds
- I set value '75' to the cell of the column 'Aug-25' of the 'Labor Dual' table for the row with the following data:
- I wait for 3 seconds
- I set value '75' to the cell of the column 'Sep-25' of the 'Labor Dual' table for the row with the following data:
- I wait for 3 seconds
- I set value ' ' to the cell of the column 'Oct-25' of the 'Labor Dual' table for the row with the following data:
- I wait for 3 seconds
- I set value '150' to the cell of the column 'Nov-25' of the 'Labor Dual' table for the row with the following data:
- I wait for 3 seconds
- I set value '75' to the cell of the column 'Dec-25' of the 'Labor Dual' table for the row with the following data:
- I wait for 3 seconds
- I set value '75' to the cell of the column 'Jan-26' of the 'Labor Dual' table for the row with the following data:
- I wait for 3 seconds
- ... 4 more steps

#### 6. Click Save and Refresh  verify data is saved, page refreshed, Distribution = Manual (Monthly), Effort Grid contains Apr-25 = NULL, May-25 = 150, Jul-25 to Sep-25 = 75, Oct-25 = NULL, Nov-25 = 150, Dec-25 to Mar-26 = 75

- I click on 'Save' button
- I wait for 20 seconds
- I click on 'Refresh' button
- I wait for 30 seconds
- I select 'Regression Test - Manufacturing' in the 'View' dropdown
- I click on 'Save' button
- text of 'View' dropdown equals 'View: Regression Test - Manufacturing (shared & preferred)'
- 'Labor Dual' table contain row with following data:

#### 7. For REG_CONSULTANT3 select Test Data Value in Distribution field  Flat (all months equal), verify value is accepted

- I set value 'Flat (all months equal)' to the cell of the column 'Distribution' of the 'Labor Dual' table for the row with the following data:
- I wait for 3 seconds

#### 8. Click Save and Refresh  verify data is saved, page refreshed, Distribution = Flat (all months equal), Effort Grid = 75 for all months

- I click on 'Save' button
- I wait for 20 seconds
- I click on 'Refresh' button
- I wait for 30 seconds
- I select 'Regression Test - Manufacturing' in the 'View' dropdown
- I click on 'Save' button
- text of 'View' dropdown equals 'View: Regression Test - Manufacturing (shared & preferred)'
- 'Labor Dual' table contain row with following data:

### Gherkin Excerpt

```gherkin
  Scenario: TC-Manufacturing-Proposal-014: Switch from Auto to Manual Distribution
    And info: ---1. For REG_CONSULTANT3 row enter Test Data Value in Planned Effort  900, and verify field accepts the value---
    And I set value '900' to the cell of the column 'Effort' of the 'Labor Dual' table for the row with the following data:
      | Resource Group  | REG_CONSULTANT3 |
    And I wait for 3 seconds
    And info: ---2. Click Save and Refresh  verify data is saved, page refreshed, Effort Grid for REG_CONSULTANT3 = 75 for all months---
    And I click on 'Save' button
    Then I wait for 20 seconds
    And I click on 'Refresh' button
    Then I wait for 30 seconds
    When I select 'Regression Test - Manufacturing' in the 'View' dropdown
    And I click on 'Save' button
    Then text of 'View' dropdown equals 'View: Regression Test - Manufacturing (shared & preferred)'
    And 'Labor Dual' table contain row with following data:
      | Description      | REG_CONSULTANT3  |
      | Distribution     | Flat (all months equal) |
      | Effort   | 900              |
      | Apr-25           | 75               |
      | May-25           | 75               |
      | Jun-25           | 75               |
      | Jul-25           | 75               |
      | Aug-25           | 75               |
      | Sep-25           | 75               |
      | Oct-25           | 75               |
      | Nov-25           | 75               |
      | Dec-25           | 75               |
      | Jan-26           | 75               |
      | Feb-26           | 75               |
      | Mar-26           | 75               |
    And info: ---3. For REG_CONSULTANT3 select Test Data Value in Distribution field  Manual (Monthly), and verify value is accepted---
    And I set value 'Manual (Monthly)' to the cell of the column 'Distribution' of the 'Labor Dual' table for the row with the following data:
      | Resource Group  | REG_CONSULTANT3 |
    And I wait for 3 seconds
#    And I set value '900' to the cell of the column 'Effort' of the 'Labor Dual' table for the row with the following data:
#      | Resource Group  | REG_CONSULTANT3 |
#    And I wait for 3 seconds
    And info: ---4. Click Save and Refresh  verify data is saved, page refreshed, Distribution = Manual (Monthly), Effort Grid = 75 for all months---
    And I click on 'Save' button
    Then I wait for 20 seconds
    And I click on 'Refresh' button
    Then I wait for 30 seconds
    When I select 'Regression Test - Manufacturing' in the 'View' dropdown
    And I click on 'Save' button
    Then text of 'View' dropdown equals 'View: Regression Test - Manufacturing (shared & preferred)'
    And info: ---5. In REG_CONSULTANT3 Effort Grid update values  Apr-25 = NULL, May-25 = 150, Oct-25 = NULL, Nov-25 = 150, verify fields accept data---
    And I set value ' ' to the cell of the column 'Apr-25' of the 'Labor Dual' table for the row with the following data:
      | Resource Group  | REG_CONSULTANT3 |
    And I wait for 3 seconds
    And I set value '150' to the cell of the column 'May-25' of the 'Labor Dual' table for the row with the following data:
      | Resource Group  | REG_CONSULTANT3 |
    And I wait for 3 seconds
    And I set value '75' to the cell of the column 'Jun-25' of the 'Labor Dual' table for the row with the following data:
      | Resource Group  | REG_CONSULTANT3 |
    And I wait for 3 seconds
    And I set value '75' to the cell of the column 'Jul-25' of the 'Labor Dual' table for the row with the following data:
      | Resource Group  | REG_CONSULTANT3 |
    And I wait for 3 seconds
    And I set value '75' to the cell of the column 'Aug-25' of the 'Labor Dual' table for the row with the following data:
      | Resource Group  | REG_CONSULTANT3 |
    And I wait for 3 seconds
    And I set value '75' to the cell of the column 'Sep-25' of the 'Labor Dual' table for the row with the following data:
      | Resource Group  | REG_CONSULTANT3 |
    And I wait for 3 seconds
    And I set value ' ' to the cell of the column 'Oct-25' of the 'Labor Dual' table for the row with the following data:
      | Resource Group  | REG_CONSULTANT3 |
    And I wait for 3 seconds
    And I set value '150' to the cell of the column 'Nov-25' of the 'Labor Dual' table for the row with the following data:
      | Resource Group  | REG_CONSULTANT3 |
    And I wait for 3 seconds
    And I set value '75' to the cell of the column 'Dec-25' of the 'Labor Dual' table for the row with the following data:
      | Resource Group  | REG_CONSULTANT3 |
    And I wait for 3 seconds
    And I set value '75' to the cell of the column 'Jan-26' of the 'Labor Dual' table for the row with the following data:
      | Resource Group  | REG_CONSULTANT3 |
    And I wait for 3 seconds
    And I set value '75' to the cell of the column 'Feb-26' of the 'Labor Dual' table for the row with the following data:
      | Resource Group  | REG_CONSULTANT3 |
    And I wait for 3 seconds
    And I set value '75' to the cell of the column 'Mar-26' of the 'Labor Dual' table for the row with the following data:
      | Resource Group  | REG_CONSULTANT3 |
    And I wait for 3 seconds
    And info: ---6. Click Save and Refresh  verify data is saved, page refreshed, Distribution = Manual (Monthly), Effort Grid contains Apr-25 = NULL, May-25 = 150, Jul-25 to Sep-25 = 75, Oct-25 = NULL, Nov-25 = 150, Dec-25 to Mar-26 = 75---
    And I click on 'Save' button
    Then I wait for 20 seconds
    And I click on 'Refresh' button
    Then I wait for 30 seconds
    When I select 'Regression Test - Manufacturing' in the 'View' dropdown
    And I click on 'Save' button
    Then text of 'View' dropdown equals 'View: Regression Test - Manufacturing (shared & preferred)'
    And 'Labor Dual' table contain row with following data:
      | Description      | REG_CONSULTANT3  |
      | Distribution     | Manual (Monthly) |
      | Effort           | 900              |
      | Apr-25           |                  |
      | May-25           | 150              |
      | Jun-25           | 75               |
      | Jul-25           | 75               |
      | Aug-25           | 75               |
      | Sep-25           | 75               |
      | Oct-25           |                  |
      | Nov-25           | 150              |
      | Dec-25           | 75               |
      | Jan-26           | 75               |
      | Feb-26           | 75               |
      | Mar-26           | 75               |
    And info: ---7. For REG_CONSULTANT3 select Test Data Value in Distribution field  Flat (all months equal), verify value is accepted---
    And I set value 'Flat (all months equal)' to the cell of the column 'Distribution' of the 'Labor Dual' table for the row with the following data:
      | Resource Group  | REG_CONSULTANT3 |
    And I wait for 3 seconds
#    And I set value '900' to the cell of the column 'Effort' of the 'Labor Dual' table for the row with the following data:
#      | Resource Group  | REG_CONSULTANT3 |
#    And I wait for 3 seconds
    And info: ---8. Click Save and Refresh  verify data is saved, page refreshed, Distribution = Flat (all months equal), Effort Grid = 75 for all months---
    And I click on 'Save' button
    Then I wait for 20 seconds
    And I click on 'Refresh' button
    Then I wait for 30 seconds
    When I select 'Regression Test - Manufacturing' in the 'View' dropdown
    And I click on 'Save' button
    Then text of 'View' dropdown equals 'View: Regression Test - Manufacturing (shared & preferred)'
    And 'Labor Dual' table contain row with following data:
      | Description      | REG_CONSULTANT3  |
      | Distribution     | Flat (all months equal) |
      | Effort           | 900              |
      | Apr-25           | 75               |
      | May-25           | 75               |
      | Jun-25           | 75               |
      | Jul-25           | 75               |
      | Aug-25           | 75               |
      | Sep-25           | 75               |
      | Oct-25           | 75               |
      | Nov-25           | 75               |
      | Dec-25           | 75               |
      | Jan-26           | 75               |
      | Feb-26           | 75               |
      | Mar-26           | 75               |
```

## Alternate mapped Selenium scenario 2

| Field | Value |
| --- | --- |
| Feature | 5. Distribute Labor Costs using Manual Effort input |
| Scenario | TC-Manufacturing-Proposal-014: Switch from Auto to Manual Distribution |
| Tags | `@TCM-014 @END` |
| File | `imported/twentyfive-regtest/tests/src/test/resources/features/testfolder/twentyfive2_4_manual_cost_distribution_manufacturing_proposal.feature:225` |

### Scenario Sections

#### 1. For REG_CONSULTANT3 row enter Test Data Value in Planned Effort  900, and verify field accepts the value

- I set value '900' to the cell of the column 'Effort' of the 'Labor Dual' table for the row with the following data:
- I wait for 3 seconds

#### 2. Click Save and Refresh  verify data is saved, page refreshed, Effort Grid for REG_CONSULTANT3 = 75 for all months

- I click on 'Save' button
- I wait for 20 seconds
- I click on 'Refresh' button
- I wait for 30 seconds
- I select 'Regression Test - Manufacturing' in the 'View' dropdown
- I click on 'Save' button
- text of 'View' dropdown equals 'View: Regression Test - Manufacturing (shared & preferred)'
- 'Labor Dual' table contain row with following data:

#### 3. For REG_CONSULTANT3 select Test Data Value in Distribution field  Manual (Monthly), and verify value is accepted

- I set value 'Manual (Monthly)' to the cell of the column 'Distribution' of the 'Labor Dual' table for the row with the following data:
- I wait for 3 seconds

#### 4. Click Save and Refresh  verify data is saved, page refreshed, Distribution = Manual (Monthly), Effort Grid = 75 for all months

- I click on 'Save' button
- I wait for 20 seconds
- I click on 'Refresh' button
- I wait for 30 seconds
- I select 'Regression Test - Manufacturing' in the 'View' dropdown
- I click on 'Save' button
- text of 'View' dropdown equals 'View: Regression Test - Manufacturing (shared & preferred)'

#### 5. In REG_CONSULTANT3 Effort Grid update values  Apr-25 = NULL, May-25 = 150, Oct-25 = NULL, Nov-25 = 150, verify fields accept data

- I set value ' ' to the cell of the column 'Apr-25' of the 'Labor Dual' table for the row with the following data:
- I wait for 3 seconds
- I set value '150' to the cell of the column 'May-25' of the 'Labor Dual' table for the row with the following data:
- I wait for 3 seconds
- I set value '75' to the cell of the column 'Jun-25' of the 'Labor Dual' table for the row with the following data:
- I wait for 3 seconds
- I set value '75' to the cell of the column 'Jul-25' of the 'Labor Dual' table for the row with the following data:
- I wait for 3 seconds
- I set value '75' to the cell of the column 'Aug-25' of the 'Labor Dual' table for the row with the following data:
- I wait for 3 seconds
- I set value '75' to the cell of the column 'Sep-25' of the 'Labor Dual' table for the row with the following data:
- I wait for 3 seconds
- I set value ' ' to the cell of the column 'Oct-25' of the 'Labor Dual' table for the row with the following data:
- I wait for 3 seconds
- I set value '150' to the cell of the column 'Nov-25' of the 'Labor Dual' table for the row with the following data:
- I wait for 3 seconds
- I set value '75' to the cell of the column 'Dec-25' of the 'Labor Dual' table for the row with the following data:
- I wait for 3 seconds
- I set value '75' to the cell of the column 'Jan-26' of the 'Labor Dual' table for the row with the following data:
- I wait for 3 seconds
- ... 4 more steps

#### 6. Click Save and Refresh  verify data is saved, page refreshed, Distribution = Manual (Monthly), Effort Grid contains Apr-25 = NULL, May-25 = 150, Jul-25 to Sep-25 = 75, Oct-25 = NULL, Nov-25 = 150, Dec-25 to Mar-26 = 75

- I click on 'Save' button
- I wait for 20 seconds
- I click on 'Refresh' button
- I wait for 30 seconds
- I select 'Regression Test - Manufacturing' in the 'View' dropdown
- I click on 'Save' button
- text of 'View' dropdown equals 'View: Regression Test - Manufacturing (shared & preferred)'
- 'Labor Dual' table contain row with following data:

#### 7. For REG_CONSULTANT3 select Test Data Value in Distribution field  Flat (all months equal), verify value is accepted

- I set value 'Flat (all months equal)' to the cell of the column 'Distribution' of the 'Labor Dual' table for the row with the following data:
- I wait for 3 seconds

#### 8. Click Save and Refresh  verify data is saved, page refreshed, Distribution = Flat (all months equal), Effort Grid = 75 for all months

- I click on 'Save' button
- I wait for 20 seconds
- I click on 'Refresh' button
- I wait for 30 seconds
- I select 'Regression Test - Manufacturing' in the 'View' dropdown
- I click on 'Save' button
- text of 'View' dropdown equals 'View: Regression Test - Manufacturing (shared & preferred)'
- 'Labor Dual' table contain row with following data:

### Gherkin Excerpt

```gherkin
  Scenario: TC-Manufacturing-Proposal-014: Switch from Auto to Manual Distribution
    And info: ---1. For REG_CONSULTANT3 row enter Test Data Value in Planned Effort  900, and verify field accepts the value---
    And I set value '900' to the cell of the column 'Effort' of the 'Labor Dual' table for the row with the following data:
      | Resource Group  | REG_CONSULTANT3 |
    And I wait for 3 seconds
    And info: ---2. Click Save and Refresh  verify data is saved, page refreshed, Effort Grid for REG_CONSULTANT3 = 75 for all months---
    And I click on 'Save' button
    Then I wait for 20 seconds
    And I click on 'Refresh' button
    Then I wait for 30 seconds
    When I select 'Regression Test - Manufacturing' in the 'View' dropdown
    And I click on 'Save' button
    Then text of 'View' dropdown equals 'View: Regression Test - Manufacturing (shared & preferred)'
    And 'Labor Dual' table contain row with following data:
      | Resource Group - Free Text      | REG_CONSULTANT3  |
      | Distribution     | Flat (all months equal) |
      | Effort   | 900              |
      | Apr-25           | 75               |
      | May-25           | 75               |
      | Jun-25           | 75               |
      | Jul-25           | 75               |
      | Aug-25           | 75               |
      | Sep-25           | 75               |
      | Oct-25           | 75               |
      | Nov-25           | 75               |
      | Dec-25           | 75               |
      | Jan-26           | 75               |
      | Feb-26           | 75               |
      | Mar-26           | 75               |
    And info: ---3. For REG_CONSULTANT3 select Test Data Value in Distribution field  Manual (Monthly), and verify value is accepted---
    And I set value 'Manual (Monthly)' to the cell of the column 'Distribution' of the 'Labor Dual' table for the row with the following data:
      | Resource Group  | REG_CONSULTANT3 |
    And I wait for 3 seconds
#    And I set value '900' to the cell of the column 'Effort' of the 'Labor Dual' table for the row with the following data:
#      | Resource Group  | REG_CONSULTANT3 |
#    And I wait for 3 seconds
    And info: ---4. Click Save and Refresh  verify data is saved, page refreshed, Distribution = Manual (Monthly), Effort Grid = 75 for all months---
    And I click on 'Save' button
    Then I wait for 20 seconds
    And I click on 'Refresh' button
    Then I wait for 30 seconds
    When I select 'Regression Test - Manufacturing' in the 'View' dropdown
    And I click on 'Save' button
    Then text of 'View' dropdown equals 'View: Regression Test - Manufacturing (shared & preferred)'
    And info: ---5. In REG_CONSULTANT3 Effort Grid update values  Apr-25 = NULL, May-25 = 150, Oct-25 = NULL, Nov-25 = 150, verify fields accept data---
    And I set value ' ' to the cell of the column 'Apr-25' of the 'Labor Dual' table for the row with the following data:
      | Resource Group  | REG_CONSULTANT3 |
    And I wait for 3 seconds
    And I set value '150' to the cell of the column 'May-25' of the 'Labor Dual' table for the row with the following data:
      | Resource Group  | REG_CONSULTANT3 |
    And I wait for 3 seconds
    And I set value '75' to the cell of the column 'Jun-25' of the 'Labor Dual' table for the row with the following data:
      | Resource Group  | REG_CONSULTANT3 |
    And I wait for 3 seconds
    And I set value '75' to the cell of the column 'Jul-25' of the 'Labor Dual' table for the row with the following data:
      | Resource Group  | REG_CONSULTANT3 |
    And I wait for 3 seconds
    And I set value '75' to the cell of the column 'Aug-25' of the 'Labor Dual' table for the row with the following data:
      | Resource Group  | REG_CONSULTANT3 |
    And I wait for 3 seconds
    And I set value '75' to the cell of the column 'Sep-25' of the 'Labor Dual' table for the row with the following data:
      | Resource Group  | REG_CONSULTANT3 |
    And I wait for 3 seconds
    And I set value ' ' to the cell of the column 'Oct-25' of the 'Labor Dual' table for the row with the following data:
      | Resource Group  | REG_CONSULTANT3 |
    And I wait for 3 seconds
    And I set value '150' to the cell of the column 'Nov-25' of the 'Labor Dual' table for the row with the following data:
      | Resource Group  | REG_CONSULTANT3 |
    And I wait for 3 seconds
    And I set value '75' to the cell of the column 'Dec-25' of the 'Labor Dual' table for the row with the following data:
      | Resource Group  | REG_CONSULTANT3 |
    And I wait for 3 seconds
    And I set value '75' to the cell of the column 'Jan-26' of the 'Labor Dual' table for the row with the following data:
      | Resource Group  | REG_CONSULTANT3 |
    And I wait for 3 seconds
    And I set value '75' to the cell of the column 'Feb-26' of the 'Labor Dual' table for the row with the following data:
      | Resource Group  | REG_CONSULTANT3 |
    And I wait for 3 seconds
    And I set value '75' to the cell of the column 'Mar-26' of the 'Labor Dual' table for the row with the following data:
      | Resource Group  | REG_CONSULTANT3 |
    And I wait for 3 seconds
    And info: ---6. Click Save and Refresh  verify data is saved, page refreshed, Distribution = Manual (Monthly), Effort Grid contains Apr-25 = NULL, May-25 = 150, Jul-25 to Sep-25 = 75, Oct-25 = NULL, Nov-25 = 150, Dec-25 to Mar-26 = 75---
    And I click on 'Save' button
    Then I wait for 20 seconds
    And I click on 'Refresh' button
    Then I wait for 30 seconds
    When I select 'Regression Test - Manufacturing' in the 'View' dropdown
    And I click on 'Save' button
    Then text of 'View' dropdown equals 'View: Regression Test - Manufacturing (shared & preferred)'
    And 'Labor Dual' table contain row with following data:
      | Resource Group - Free Text      | REG_CONSULTANT3  |
      | Distribution     | Manual (Monthly) |
      | Effort           | 900              |
      | Apr-25           |                  |
      | May-25           | 150              |
      | Jun-25           | 75               |
      | Jul-25           | 75               |
      | Aug-25           | 75               |
      | Sep-25           | 75               |
      | Oct-25           |                  |
      | Nov-25           | 150              |
      | Dec-25           | 75               |
      | Jan-26           | 75               |
      | Feb-26           | 75               |
      | Mar-26           | 75               |
    And info: ---7. For REG_CONSULTANT3 select Test Data Value in Distribution field  Flat (all months equal), verify value is accepted---
    And I set value 'Flat (all months equal)' to the cell of the column 'Distribution' of the 'Labor Dual' table for the row with the following data:
      | Resource Group  | REG_CONSULTANT3 |
    And I wait for 3 seconds
#    And I set value '900' to the cell of the column 'Effort' of the 'Labor Dual' table for the row with the following data:
#      | Resource Group  | REG_CONSULTANT3 |
#    And I wait for 3 seconds
    And info: ---8. Click Save and Refresh  verify data is saved, page refreshed, Distribution = Flat (all months equal), Effort Grid = 75 for all months---
    And I click on 'Save' button
    Then I wait for 20 seconds
    And I click on 'Refresh' button
    Then I wait for 30 seconds
    When I select 'Regression Test - Manufacturing' in the 'View' dropdown
    And I click on 'Save' button
    Then text of 'View' dropdown equals 'View: Regression Test - Manufacturing (shared & preferred)'
    And 'Labor Dual' table contain row with following data:
      | Resource Group - Free Text      | REG_CONSULTANT3  |
      | Distribution     | Flat (all months equal) |
      | Effort           | 900              |
      | Apr-25           | 75               |
      | May-25           | 75               |
      | Jun-25           | 75               |
      | Jul-25           | 75               |
      | Aug-25           | 75               |
      | Sep-25           | 75               |
      | Oct-25           | 75               |
      | Nov-25           | 75               |
      | Dec-25           | 75               |
      | Jan-26           | 75               |
      | Feb-26           | 75               |
      | Mar-26           | 75               |
```

---

_Generated by `scripts/generate-testcase-guides.py` from the RTA mapping and local feature files._
