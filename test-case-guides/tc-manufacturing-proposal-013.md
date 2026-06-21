# TC-Manufacturing-Proposal-013 - Labor Manual Effort

| Field | Value |
| --- | --- |
| Test ID | TC-Manufacturing-Proposal-013 |
| Title | Labor Manual Effort |
| RTA page | https://twenty5.atlassian.net/wiki/spaces/RTA/pages/366247937/TC-Manufacturing-Proposal-013+Labor+Manual+Effort |
| Map source | `RTA_TEST_SUITE_FEATURE_MAP.md` |

## Run Instructions

When a consultant asks to run this test, use the default app URL unless they provide another URL:

```text
https://approuter-twenty5ipe-dev.cfapps.us10.hana.ondemand.com/#quote
```

For Claude Desktop, ask before launching Selenium Chrome with the default URL. If the user provides another URL, launch that URL instead.

```bash
scripts/start-debug-chrome.sh "https://approuter-twenty5ipe-dev.cfapps.us10.hana.ondemand.com/#quote"
scripts/run-twentyfive-test.sh @TCM-013
```

Duplicate feature matches exist for this Confluence page. The tag command may run more than one matching scenario until the canonical feature file is confirmed.

## Default mapped Selenium scenario

| Field | Value |
| --- | --- |
| Feature | 8. Distribute Labor Costs using Manual Effort input |
| Scenario | TC-Manufacturing-Proposal-013: Labor Manual Effort |
| Tags | `@TCM-013 @START` |
| File | `imported/twentyfive-regtest/tests/src/test/resources/features/testfolder/addManualLaborManuProposal.feature:5` |

### Scenario Sections

#### Setup

- open site
- I perform login
- page with name 'Main page' is opened
- generate values and store into the variables:

#### 1. Start creating a new Proposal with Type Regression Test Only - Manufacturing and verify Setup tab is opened

- I click on 'PROPOSALS' tab
- page with name 'Proposals list page' is opened
- click on 'New' button
- page with name 'Setup page' is opened
- I enter data into the next fields:
- page with name 'Setup page' is opened
- I enter data into the next fields:
- page with name 'Setup page' is opened

#### 2. Fill required fields with test data  Estimated Project Start = 4/1/25, Project End = 3/31/26

- I enter data into the next fields:
- page with name 'Setup page' is opened
- I enter data into the next fields:
- I enter data into the next fields:
- page with name 'Setup page' is opened
- I enter data into the next fields:
- page with name 'Setup page' is opened
- I enter data into the next fields:
- page with name 'Setup page' is opened

#### 3. In "How do you want to Start your Project Plan", ensure "Select a template" radio button is selected and choose "(AS) Regression - Labor Manual Effort & Inflation - Template" from dropdown

- I check 'Select a template' radio
- I enter data into the next fields:
- page with name 'Setup page' is opened

#### 4. Click Copy button  system saves data and reloads page

- I click on 'Copy' button
- I wait for 20 seconds
- page with name 'Setup page' is opened

#### 5. Navigate to Pricing tab, select "Regression Test - Bill Rates" from "How are Labor/Item Rates Calculated" dropdown and verify value is accepted

- I click on 'Pricing' tab
- page with name 'Pricing page' is opened
- I enter data into the next fields:

#### 6. Click Save and verify data is saved

- I click on 'Save' button
- I wait for 7 seconds
- page with name 'Pricing page' is opened

#### 7. Navigate to Cost Structure tab, click Open in Work Package field  verify Estimate is opened in new tab, Labor tab selected, and grid shows Resource Group, Description, Distribution, Cost Rate (Alt. Unit) with consultant data

- I click on 'Cost Structure' tab
- page with name 'Cost Structure page' is opened
- I select 'TEST:-DONOT_CHANGE' in the 'View' dropdown
- I click on 'Save' button
- text of 'View' dropdown equals 'View: TEST:-DONOT_CHANGE (preferred)'
- I click 'Open' in table 'WBS' table for row with data:
- I switch to tab 2
- page with name 'Estimate Labor' is opened
- I select 'Regression Test - Manufacturing' in the 'View' dropdown
- I wait for 30 seconds
- I click on 'Save' button
- text of 'View' dropdown equals 'View: Regression Test - Manufacturing (shared & preferred)'
- 'Labor Dual' table contain row with following data:
- 'Labor Dual' table contain row with following data:
- 'Labor Dual' table contain row with following data:

#### 8. Click Gear icon above grid  Change Planned Effort Input Mode  Hours By Month

- I choose in 'Cog Settings' menu the next menu chain:
- I wait for 7 seconds

#### 9. Popup "Change Planning Mode in Other Estimates" appears

- 'Change Planning Mode' window is displayed

#### 10. Click Yes on popup  verify popup disappears and grid refreshed with MMM-YY columns from Apr-25 to Mar-26, empty effort values

- I if visible click on 'Yes' button
- 'Change Planning Mode' window is not displayed
- page with name 'Estimate Labor' is opened
- I verify column Hours by Month distribution between dates '4/1/2025' and '3/31/2026'
- I verify effort values in 'Labor Dual' table are empty for rows with data:
- I verify effort values in 'Labor Dual' table are empty for rows with data:
- I verify effort values in 'Labor Dual' table are empty for rows with data:

#### 11. Enter Test Data Values in REG_CONSULTANT1 row  Apr-25 = 500, Oct-25 = 700, and verify values accepted

- I set value '500' to the cell of the column 'Apr-25' of the 'Labor Dual' table for the row with the following data:
- I wait for 3 seconds
- I set value '700' to the cell of the column 'Oct-25' of the 'Labor Dual' table for the row with the following data:
- I wait for 3 seconds

#### 12. Verify for REG_CONSULTANT1  Planned Effort = 1200 hours

- 'Labor Dual' table contain row with following data:
- I verify effort values in 'Labor Dual' table are empty for rows with data:

#### 13. Enter Test Data Values in REG_CONSULTANT2 row  all months = 100 each, and verify values accepted

- I set value '100' to the cell of the column 'Apr-25' of the 'Labor Dual' table for the row with the following data:
- I wait for 3 seconds
- I set value '100' to the cell of the column 'May-25' of the 'Labor Dual' table for the row with the following data:
- I wait for 3 seconds
- I set value '100' to the cell of the column 'Jun-25' of the 'Labor Dual' table for the row with the following data:
- I wait for 3 seconds
- I set value '100' to the cell of the column 'Jul-25' of the 'Labor Dual' table for the row with the following data:
- I wait for 3 seconds
- I set value '100' to the cell of the column 'Aug-25' of the 'Labor Dual' table for the row with the following data:
- I wait for 3 seconds
- I set value '100' to the cell of the column 'Sep-25' of the 'Labor Dual' table for the row with the following data:
- I wait for 3 seconds
- I set value '100' to the cell of the column 'Oct-25' of the 'Labor Dual' table for the row with the following data:
- I wait for 3 seconds
- I set value '100' to the cell of the column 'Nov-25' of the 'Labor Dual' table for the row with the following data:
- I wait for 3 seconds
- I set value '100' to the cell of the column 'Dec-25' of the 'Labor Dual' table for the row with the following data:
- I wait for 3 seconds
- I set value '100' to the cell of the column 'Jan-26' of the 'Labor Dual' table for the row with the following data:
- I wait for 3 seconds
- ... 4 more steps

#### 14. Verify for REG_CONSULTANT2  Planned Effort = 1200 hours

- 'Labor Dual' table contain row with following data:

#### 15. Click Save and verify data is saved

- I click on 'Save' button
- 'Data saved successfully' warning message is displayed in 30 seconds

#### 16. In Status widget, click Update Estimate Totals  verify page refreshed and costs for REG_CONSULTANT1 & REG_CONSULTANT2 are NON-ZERO

- I click on 'Update Cost & Prices' link
- I wait for 60 seconds
- page with name 'Estimate Labor' is opened
- I click on 'Refresh' button
- page with name 'Estimate Labor' is opened
- I select 'Regression Test - Manufacturing' in the 'View' dropdown
- I click on 'Save' button
- text of 'View' dropdown equals 'View: Regression Test - Manufacturing (shared & preferred)'
- the cell value of the 'Cost in Company Currency' column of the 'Labor Dual' table is greater than '0' for a row with the following data:
- the cell value of the 'Cost in Company Currency' column of the 'Labor Dual' table is greater than '0' for a row with the following data:

#### 17. Verify that the Effort Grid shows correct values  REG_CONSULTANT1: Apr-25 = 500, Oct-25 = 700; REG_CONSULTANT2: all months = 100 each

- 'Labor Dual' table contain row with following data:
- 'Labor Dual' table contain row with following data:

### Gherkin Excerpt

```gherkin
  Scenario: TC-Manufacturing-Proposal-013: Labor Manual Effort
    Given open site
    And I perform login
    Then page with name 'Main page' is opened
    Given generate values and store into the variables:
      | $uniqueProposalName | TEST Labor Manual Effort ${CUR_DATE,yyyy-MM-dd hh:mm} |
    And info: ---1. Start creating a new Proposal with Type Regression Test Only - Manufacturing and verify Setup tab is opened---
    Given I click on 'PROPOSALS' tab
    Then page with name 'Proposals list page' is opened
    Then click on 'New' button
    And page with name 'Setup page' is opened
    When I enter data into the next fields:
      | 'Description' field   | $uniqueProposalName |
    And page with name 'Setup page' is opened
    When I enter data into the next fields:
      | 'Proposal Type' field | Regression Test Only - Manufacturing |
    And page with name 'Setup page' is opened
    And info: ---2. Fill required fields with test data  Estimated Project Start = 4/1/25, Project End = 3/31/26---
    When I enter data into the next fields:
      | 'Planned Start' field | 4/1/25            |
    And page with name 'Setup page' is opened
    When I enter data into the next fields:
      | 'End' field                  | 3/31/26          |
    When I enter data into the next fields:
      | 'Your Company' field            | Regression Test |
    And page with name 'Setup page' is opened
    When I enter data into the next fields:
      | 'Leading Department' field            | US - New York   |
    And page with name 'Setup page' is opened
    When I enter data into the next fields:
      | 'Client Customer sell-to' field | Regression Test - Customer USD |
    And page with name 'Setup page' is opened
    And info: ---3. In "How do you want to Start your Project Plan", ensure "Select a template" radio button is selected and choose "(AS) Regression - Labor Manual Effort & Inflation - Template" from dropdown---
    And I check 'Select a template' radio
    When I enter data into the next fields:
      | 'Search Box' field | (AS) Regression - Labor Manual Effort & Inflation - Template |
    And page with name 'Setup page' is opened
    And info: ---4. Click Copy button  system saves data and reloads page---
    And I click on 'Copy' button
    Then I wait for 20 seconds
    Then page with name 'Setup page' is opened
    And info: ---5. Navigate to Pricing tab, select "Regression Test - Bill Rates" from "How are Labor/Item Rates Calculated" dropdown and verify value is accepted---
    Then I click on 'Pricing' tab
    And page with name 'Pricing page' is opened
    When I enter data into the next fields:
      | 'How are Labor/Item Rates Calculated' field | Regression Test - Bill Rates |
    And info: ---6. Click Save and verify data is saved---
    Then I click on 'Save' button
    Then I wait for 7 seconds
    And page with name 'Pricing page' is opened
    And info: ---7. Navigate to Cost Structure tab, click Open in Work Package field  verify Estimate is opened in new tab, Labor tab selected, and grid shows Resource Group, Description, Distribution, Cost Rate (Alt. Unit) with consultant data---
    Then I click on 'Cost Structure' tab
    And page with name 'Cost Structure page' is opened
      When I select 'TEST:-DONOT_CHANGE' in the 'View' dropdown
      And I click on 'Save' button
      Then text of 'View' dropdown equals 'View: TEST:-DONOT_CHANGE (preferred)'
    And I click 'Open' in table 'WBS' table for row with data:
      | WBS Code              | 1                   |
    Then I switch to tab 2
    And page with name 'Estimate Labor' is opened
    When I select 'Regression Test - Manufacturing' in the 'View' dropdown
    And I wait for 30 seconds
    And I click on 'Save' button
    Then text of 'View' dropdown equals 'View: Regression Test - Manufacturing (shared & preferred)'
    And 'Labor Dual' table contain row with following data:
       | Resource Group   | REG_CONSULTANT1  |
       | Description      | REG_CONSULTANT1  |
       | Distribution     | Manual (Monthly) |
       | Cost Rate (Alt. Unit) | $ 120.00/hr      |
    And 'Labor Dual' table contain row with following data:
      | Resource Group   | REG_CONSULTANT2  |
      | Description      | REG_CONSULTANT2  |
      | Distribution     | Manual (Monthly) |
      | Cost Rate (Alt. Unit) | $ 90.00/hr       |
    And 'Labor Dual' table contain row with following data:
      | Resource Group   | REG_CONSULTANT3  |
      | Description      | REG_CONSULTANT3  |
      | Distribution     | Flat (all months equal) |
      | Cost Rate (Alt. Unit) | $ 100.00/hr      |
    And info: ---8. Click Gear icon above grid  Change Planned Effort Input Mode  Hours By Month---
    Then I choose in 'Cog Settings' menu the next menu chain:
      | Change Planned Effort Input Mode |
      | Hours by Month                   |
    Then I wait for 7 seconds
    And info: ---9. Popup "Change Planning Mode in Other Estimates" appears---
    Then 'Change Planning Mode' window is displayed
    And info: ---10. Click Yes on popup  verify popup disappears and grid refreshed with MMM-YY columns from Apr-25 to Mar-26, empty effort values---
    And I if visible click on 'Yes' button
    Then 'Change Planning Mode' window is not displayed
    And page with name 'Estimate Labor' is opened
    Then I verify column Hours by Month distribution between dates '4/1/2025' and '3/31/2026'
    And I verify effort values in 'Labor Dual' table are empty for rows with data:
      | Resource Group  | REG_CONSULTANT1 |
    And I verify effort values in 'Labor Dual' table are empty for rows with data:
      | Resource Group  | REG_CONSULTANT2 |
    And I verify effort values in 'Labor Dual' table are empty for rows with data:
      | Resource Group  | REG_CONSULTANT3 |
    And info: ---11. Enter Test Data Values in REG_CONSULTANT1 row  Apr-25 = 500, Oct-25 = 700, and verify values accepted---
    And I set value '500' to the cell of the column 'Apr-25' of the 'Labor Dual' table for the row with the following data:
      | Resource Group  | REG_CONSULTANT1 |
    And I wait for 3 seconds
    And I set value '700' to the cell of the column 'Oct-25' of the 'Labor Dual' table for the row with the following data:
      | Resource Group  | REG_CONSULTANT1 |
    And I wait for 3 seconds
    And info: ---12. Verify for REG_CONSULTANT1  Planned Effort = 1200 hours---
    And 'Labor Dual' table contain row with following data:
      | Resource Group   | REG_CONSULTANT1  |
      | Description      | REG_CONSULTANT1  |
      | Distribution     | Manual (Monthly) |
      | Cost Rate (Alt. Unit) | $ 120.00/hr      |
      | Effort   | 1200             |
      | Apr-25           | 500              |
      | Oct-25           | 700              |
    And I verify effort values in 'Labor Dual' table are empty for rows with data:
      | Resource Group  | REG_CONSULTANT2 |
    And info: ---13. Enter Test Data Values in REG_CONSULTANT2 row  all months = 100 each, and verify values accepted---
    And I set value '100' to the cell of the column 'Apr-25' of the 'Labor Dual' table for the row with the following data:
      | Resource Group  | REG_CONSULTANT2 |
    And I wait for 3 seconds
    And I set value '100' to the cell of the column 'May-25' of the 'Labor Dual' table for the row with the following data:
      | Resource Group  | REG_CONSULTANT2 |
    And I wait for 3 seconds
    And I set value '100' to the cell of the column 'Jun-25' of the 'Labor Dual' table for the row with the following data:
      | Resource Group  | REG_CONSULTANT2 |
    And I wait for 3 seconds
    And I set value '100' to the cell of the column 'Jul-25' of the 'Labor Dual' table for the row with the following data:
      | Resource Group  | REG_CONSULTANT2 |
    And I wait for 3 seconds
    And I set value '100' to the cell of the column 'Aug-25' of the 'Labor Dual' table for the row with the following data:
      | Resource Group  | REG_CONSULTANT2 |
    And I wait for 3 seconds
    And I set value '100' to the cell of the column 'Sep-25' of the 'Labor Dual' table for the row with the following data:
      | Resource Group  | REG_CONSULTANT2 |
    And I wait for 3 seconds
    And I set value '100' to the cell of the column 'Oct-25' of the 'Labor Dual' table for the row with the following data:
      | Resource Group  | REG_CONSULTANT2 |
    And I wait for 3 seconds
    And I set value '100' to the cell of the column 'Nov-25' of the 'Labor Dual' table for the row with the following data:
      | Resource Group  | REG_CONSULTANT2 |
    And I wait for 3 seconds
    And I set value '100' to the cell of the column 'Dec-25' of the 'Labor Dual' table for the row with the following data:
      | Resource Group  | REG_CONSULTANT2 |
    And I wait for 3 seconds
    And I set value '100' to the cell of the column 'Jan-26' of the 'Labor Dual' table for the row with the following data:
      | Resource Group  | REG_CONSULTANT2 |
    And I wait for 3 seconds
    And I set value '100' to the cell of the column 'Feb-26' of the 'Labor Dual' table for the row with the following data:
      | Resource Group  | REG_CONSULTANT2 |
    And I wait for 3 seconds
    And I set value '100' to the cell of the column 'Mar-26' of the 'Labor Dual' table for the row with the following data:
      | Resource Group  | REG_CONSULTANT2 |
    And I wait for 3 seconds
    And info: ---14. Verify for REG_CONSULTANT2  Planned Effort = 1200 hours---
    And 'Labor Dual' table contain row with following data:
      | Resource Group   | REG_CONSULTANT2  |
      | Description      | REG_CONSULTANT2  |
      | Distribution     | Manual (Monthly) |
      | Cost Rate (Alt. Unit) | $ 90.00/hr       |
      | Effort   | 1200             |
      | Apr-25           | 100              |
      | May-25           | 100              |
      | Jun-25           | 100              |
      | Jul-25           | 100              |
      | Aug-25           | 100              |
      | Sep-25           | 100              |
      | Oct-25           | 100              |
      | Nov-25           | 100              |
      | Dec-25           | 100              |
      | Jan-26           | 100              |
      | Feb-26           | 100              |
      | Mar-26           | 100              |
    And info: ---15. Click Save and verify data is saved---
    And I click on 'Save' button
    And 'Data saved successfully' warning message is displayed in 30 seconds
    And info: ---16. In Status widget, click Update Estimate Totals  verify page refreshed and costs for REG_CONSULTANT1 & REG_CONSULTANT2 are NON-ZERO---
    Then I click on 'Update Cost & Prices' link
    Then I wait for 60 seconds
    And page with name 'Estimate Labor' is opened
    And I click on 'Refresh' button
    And page with name 'Estimate Labor' is opened
    When I select 'Regression Test - Manufacturing' in the 'View' dropdown
    And I click on 'Save' button
    Then text of 'View' dropdown equals 'View: Regression Test - Manufacturing (shared & preferred)'
    And the cell value of the 'Cost in Company Currency' column of the 'Labor Dual' table is greater than '0' for a row with the following data:
      | Resource Group  | REG_CONSULTANT1 |
    And the cell value of the 'Cost in Company Currency' column of the 'Labor Dual' table is greater than '0' for a row with the following data:
      | Resource Group  | REG_CONSULTANT2 |
    And info: ---17. Verify that the Effort Grid shows correct values  REG_CONSULTANT1: Apr-25 = 500, Oct-25 = 700; REG_CONSULTANT2: all months = 100 each---
    And 'Labor Dual' table contain row with following data:
      | Resource Group   | REG_CONSULTANT1  |
      | Description      | REG_CONSULTANT1  |
      | Distribution     | Manual (Monthly) |
      | Cost Rate (Alt. Unit) | $ 120.00/hr      |
      | Effort   | 1200             |
      | Apr-25           | 500              |
      | Oct-25           | 700              |
    And 'Labor Dual' table contain row with following data:
      | Resource Group   | REG_CONSULTANT2  |
      | Description      | REG_CONSULTANT2  |
      | Distribution     | Manual (Monthly) |
      | Cost Rate (Alt. Unit) | $ 90.00/hr       |
      | Effort   | 1200             |
      | Apr-25           | 100              |
      | May-25           | 100              |
      | Jun-25           | 100              |
      | Jul-25           | 100              |
      | Aug-25           | 100              |
      | Sep-25           | 100              |
      | Oct-25           | 100              |
      | Nov-25           | 100              |
      | Dec-25           | 100              |
      | Jan-26           | 100              |
      | Feb-26           | 100              |
      | Mar-26           | 100              |

```

## Alternate mapped Selenium scenario 2

| Field | Value |
| --- | --- |
| Feature | 5. Distribute Labor Costs using Manual Effort input |
| Scenario | TC-Manufacturing-Proposal-013: Labor Manual Effort |
| Tags | `@TCM-013 @START` |
| File | `imported/twentyfive-regtest/tests/src/test/resources/features/testfolder/twentyfive2_4_manual_cost_distribution_manufacturing_proposal.feature:5` |

### Scenario Sections

#### Setup

- open site
- I perform Mfg 2.4 login
- page with name 'Main page' is opened
- generate values and store into the variables:

#### 1. Start creating a new Proposal with Type Regression Test Only - Manufacturing and verify Setup tab is opened

- I click on 'PROPOSALS' tab
- page with name 'Proposals list page' is opened
- click on 'New' button
- page with name 'Setup page' is opened
- I enter data into the next fields:
- page with name 'Setup page' is opened
- I enter data into the next fields:
- page with name 'Setup page' is opened

#### 2. Fill required fields with test data  Estimated Project Start = 4/1/25, Project End = 3/31/26

- I enter data into the next fields:
- page with name 'Setup page' is opened
- I enter data into the next fields:
- I enter data into the next fields:
- page with name 'Setup page' is opened
- I enter data into the next fields:
- page with name 'Setup page' is opened
- I enter data into the next fields:
- page with name 'Setup page' is opened

#### 3. In "How do you want to Start your Project Plan", ensure "Select a template" radio button is selected and choose "(AS) Regression - Labor Manual Effort & Inflation - Template" from dropdown

- I check 'Select a template' radio
- I enter data into the next fields:
- page with name 'Setup page' is opened

#### 4. Click Copy button  system saves data and reloads page

- I click on 'Copy' button
- I wait for 20 seconds
- page with name 'Setup page' is opened

#### 5. Navigate to Pricing tab, select "Regression Test - Bill Rates" from "How are Labor/Item Rates Calculated" dropdown and verify value is accepted

- I click on 'Pricing' tab
- page with name 'Pricing page' is opened
- I enter data into the next fields:

#### 6. Click Save and verify data is saved

- I click on 'Save' button
- I wait for 7 seconds
- page with name 'Pricing page' is opened

#### 7. Navigate to Cost Structure tab, click Open in Work Package field  verify Estimate is opened in new tab, Labor tab selected, and grid shows Resource Group, Description, Distribution, Rate in Resource Unit with consultant data

- I click on 'Cost Structure' tab
- page with name 'Cost Structure page' is opened
- I select 'TEST:-DONOT_CHANGE' in the 'View' dropdown
- I click on 'Save' button
- text of 'View' dropdown equals 'View: TEST:-DONOT_CHANGE (preferred)'
- I click 'Open' in table 'WBS' table for row with data:
- I switch to tab 2
- I wait for 7 seconds
- I click on 'Labor' tab
- I select 'Test View for Regression - DONOT CHANGE' in the 'View' dropdown
- I wait for 7 seconds
- I click on 'Save' button
- text of 'View' dropdown equals 'View: Test View for Regression - DONOT CHANGE'
- page with name 'Estimate Labor' is opened
- 'Labor Dual' table contain row with following data:
- 'Labor Dual' table contain row with following data:
- 'Labor Dual' table contain row with following data:

#### 8. Click Gear icon above grid  Change Planned Effort Input Mode  Hours By Month

- I choose in 'Cog Settings' menu the next menu chain:
- I wait for 7 seconds

#### 9. Popup "Change Planning Mode in Other Estimates" appears

- 'Change Planning Mode' window is displayed

#### 10. Click Yes on popup  verify popup disappears and grid refreshed with MMM-YY columns from Apr-25 to Mar-26, empty effort values

- I if visible click on 'Yes' button
- 'Change Planning Mode' window is not displayed
- page with name 'Estimate Labor' is opened
- I verify column Hours by Month distribution between dates '4/1/2025' and '3/31/2026'
- I verify effort values in 'Labor Dual' table are empty for rows with data:
- I verify effort values in 'Labor Dual' table are empty for rows with data:
- I verify effort values in 'Labor Dual' table are empty for rows with data:

#### 11. Enter Test Data Values in REG_CONSULTANT1 row  Apr-25 = 500, Oct-25 = 700, and verify values accepted

- I set value '500' to the cell of the column 'Apr-25' of the 'Labor Dual' table for the row with the following data:
- I wait for 3 seconds
- I set value '700' to the cell of the column 'Oct-25' of the 'Labor Dual' table for the row with the following data:
- I wait for 3 seconds

#### 12. Verify for REG_CONSULTANT1  Planned Effort = 1200 hours

- 'Labor Dual' table contain row with following data:
- I verify effort values in 'Labor Dual' table are empty for rows with data:

#### 13. Enter Test Data Values in REG_CONSULTANT2 row  all months = 100 each, and verify values accepted

- I set value '100' to the cell of the column 'Apr-25' of the 'Labor Dual' table for the row with the following data:
- I wait for 3 seconds
- I set value '100' to the cell of the column 'May-25' of the 'Labor Dual' table for the row with the following data:
- I wait for 3 seconds
- I set value '100' to the cell of the column 'Jun-25' of the 'Labor Dual' table for the row with the following data:
- I wait for 3 seconds
- I set value '100' to the cell of the column 'Jul-25' of the 'Labor Dual' table for the row with the following data:
- I wait for 3 seconds
- I set value '100' to the cell of the column 'Aug-25' of the 'Labor Dual' table for the row with the following data:
- I wait for 3 seconds
- I set value '100' to the cell of the column 'Sep-25' of the 'Labor Dual' table for the row with the following data:
- I wait for 3 seconds
- I set value '100' to the cell of the column 'Oct-25' of the 'Labor Dual' table for the row with the following data:
- I wait for 3 seconds
- I set value '100' to the cell of the column 'Nov-25' of the 'Labor Dual' table for the row with the following data:
- I wait for 3 seconds
- I set value '100' to the cell of the column 'Dec-25' of the 'Labor Dual' table for the row with the following data:
- I wait for 3 seconds
- I set value '100' to the cell of the column 'Jan-26' of the 'Labor Dual' table for the row with the following data:
- I wait for 3 seconds
- ... 4 more steps

#### 14. Verify for REG_CONSULTANT2  Planned Effort = 1200 hours

- 'Labor Dual' table contain row with following data:

#### 15. Click Save and verify data is saved

- I click on 'Save' button
- 'Data saved successfully' warning message is displayed in 30 seconds

#### 16. In Status widget, click Update Estimate Totals  verify page refreshed and costs for REG_CONSULTANT1 & REG_CONSULTANT2 are NON-ZERO

- I click on 'Update Cost & Prices' link
- I wait for 40 seconds
- page with name 'Estimate Labor' is opened
- I select 'Regression Test - Manufacturing' in the 'View' dropdown
- I click on 'Save' button
- text of 'View' dropdown equals 'View: Regression Test - Manufacturing (shared & preferred)'
- the cell value of the 'Cost in Company Currency' column of the 'Labor Dual' table is greater than '0' for a row with the following data:
- the cell value of the 'Cost in Company Currency' column of the 'Labor Dual' table is greater than '0' for a row with the following data:

#### 17. Verify that the Effort Grid shows correct values  REG_CONSULTANT1: Apr-25 = 500, Oct-25 = 700; REG_CONSULTANT2: all months = 100 each

- 'Labor Dual' table contain row with following data:
- 'Labor Dual' table contain row with following data:

### Gherkin Excerpt

```gherkin
  Scenario: TC-Manufacturing-Proposal-013: Labor Manual Effort
    Given open site
    And I perform Mfg 2.4 login
    Then page with name 'Main page' is opened
    Given generate values and store into the variables:
      | $uniqueProposalName | TEST Labor Manual Effort ${CUR_DATE,yyyy-MM-dd hh:mm} |
    And info: ---1. Start creating a new Proposal with Type Regression Test Only - Manufacturing and verify Setup tab is opened---
    Given I click on 'PROPOSALS' tab
    Then page with name 'Proposals list page' is opened
    Then click on 'New' button
    And page with name 'Setup page' is opened
    When I enter data into the next fields:
      | 'Description' field   | $uniqueProposalName |
    And page with name 'Setup page' is opened
    When I enter data into the next fields:
      | 'Proposal Type' field | Regression Test Only - Manufacturing |
    And page with name 'Setup page' is opened
    And info: ---2. Fill required fields with test data  Estimated Project Start = 4/1/25, Project End = 3/31/26---
    When I enter data into the next fields:
      | 'Planned Start' field | 4/1/25            |
    And page with name 'Setup page' is opened
    When I enter data into the next fields:
      | 'End' field                  | 3/31/26          |
    When I enter data into the next fields:
      | 'Your Company' field            | Regression Test |
    And page with name 'Setup page' is opened
    When I enter data into the next fields:
      | 'Leading Department' field            | US - New York   |
    And page with name 'Setup page' is opened
    When I enter data into the next fields:
      | 'Client Customer sell-to' field | Regression Test - Customer USD |
    And page with name 'Setup page' is opened
    And info: ---3. In "How do you want to Start your Project Plan", ensure "Select a template" radio button is selected and choose "(AS) Regression - Labor Manual Effort & Inflation - Template" from dropdown---
    And I check 'Select a template' radio
    When I enter data into the next fields:
      | 'Search Box' field | (AS) Regression - Labor Manual Effort & Inflation - Template |
    And page with name 'Setup page' is opened
    And info: ---4. Click Copy button  system saves data and reloads page---
    And I click on 'Copy' button
    Then I wait for 20 seconds
    Then page with name 'Setup page' is opened
    And info: ---5. Navigate to Pricing tab, select "Regression Test - Bill Rates" from "How are Labor/Item Rates Calculated" dropdown and verify value is accepted---
    Then I click on 'Pricing' tab
    And page with name 'Pricing page' is opened
    When I enter data into the next fields:
      | 'How are Labor/Item Rates Calculated' field | Regression Test - Bill Rates |
    And info: ---6. Click Save and verify data is saved---
    Then I click on 'Save' button
    Then I wait for 7 seconds
    And page with name 'Pricing page' is opened
    And info: ---7. Navigate to Cost Structure tab, click Open in Work Package field  verify Estimate is opened in new tab, Labor tab selected, and grid shows Resource Group, Description, Distribution, Rate in Resource Unit with consultant data---
    Then I click on 'Cost Structure' tab
    And page with name 'Cost Structure page' is opened
    When I select 'TEST:-DONOT_CHANGE' in the 'View' dropdown
    And I click on 'Save' button
    Then text of 'View' dropdown equals 'View: TEST:-DONOT_CHANGE (preferred)'
    And I click 'Open' in table 'WBS' table for row with data:
      | WBS Code              | 1                   |
    Then I switch to tab 2
    And I wait for 7 seconds
    And I click on 'Labor' tab
    When I select 'Test View for Regression - DONOT CHANGE' in the 'View' dropdown
    And I wait for 7 seconds
    And I click on 'Save' button
    Then text of 'View' dropdown equals 'View: Test View for Regression - DONOT CHANGE'
#    When I select 'Regression Test - Manufacturing' in the 'View' dropdown
#    And I wait for 7 seconds
#    And I click on 'Save' button
#    Then text of 'View' dropdown equals 'View: Regression Test - Manufacturing (shared & preferred)'
    And page with name 'Estimate Labor' is opened
    And 'Labor Dual' table contain row with following data:
      | Resource Group   | REG_CONSULTANT1  |
      | Resource Group - Free Text      | REG_CONSULTANT1  |
      | Distribution     | Manual (Monthly) |
      | Rate in Resource Unit | $ 120.00/hr      |
    And 'Labor Dual' table contain row with following data:
      | Resource Group   | REG_CONSULTANT2  |
      | Resource Group - Free Text      | REG_CONSULTANT2  |
      | Distribution     | Manual (Monthly) |
      | Rate in Resource Unit | $ 90.00/hr       |
    And 'Labor Dual' table contain row with following data:
      | Resource Group   | REG_CONSULTANT3  |
      | Resource Group - Free Text      | REG_CONSULTANT3  |
      | Distribution     | Flat (all months equal) |
      | Rate in Resource Unit | $ 100.00/hr      |
    And info: ---8. Click Gear icon above grid  Change Planned Effort Input Mode  Hours By Month---
    Then I choose in 'Cog Settings' menu the next menu chain:
      | Change Planned Effort Input Mode |
      | Hours by Month                   |
    Then I wait for 7 seconds
    And info: ---9. Popup "Change Planning Mode in Other Estimates" appears---
    Then 'Change Planning Mode' window is displayed
    And info: ---10. Click Yes on popup  verify popup disappears and grid refreshed with MMM-YY columns from Apr-25 to Mar-26, empty effort values---
    And I if visible click on 'Yes' button
    Then 'Change Planning Mode' window is not displayed
    And page with name 'Estimate Labor' is opened
    Then I verify column Hours by Month distribution between dates '4/1/2025' and '3/31/2026'
    And I verify effort values in 'Labor Dual' table are empty for rows with data:
      | Resource Group  | REG_CONSULTANT1 |
    And I verify effort values in 'Labor Dual' table are empty for rows with data:
      | Resource Group  | REG_CONSULTANT2 |
    And I verify effort values in 'Labor Dual' table are empty for rows with data:
      | Resource Group  | REG_CONSULTANT3 |
    And info: ---11. Enter Test Data Values in REG_CONSULTANT1 row  Apr-25 = 500, Oct-25 = 700, and verify values accepted---
    And I set value '500' to the cell of the column 'Apr-25' of the 'Labor Dual' table for the row with the following data:
      | Resource Group  | REG_CONSULTANT1 |
    And I wait for 3 seconds
    And I set value '700' to the cell of the column 'Oct-25' of the 'Labor Dual' table for the row with the following data:
      | Resource Group  | REG_CONSULTANT1 |
    And I wait for 3 seconds
    And info: ---12. Verify for REG_CONSULTANT1  Planned Effort = 1200 hours---
    And 'Labor Dual' table contain row with following data:
      | Resource Group   | REG_CONSULTANT1  |
      | Resource Group - Free Text      | REG_CONSULTANT1  |
      | Distribution     | Manual (Monthly) |
      | Rate in Resource Unit | $ 120.00/hr      |
      | Effort   | 1200             |
      | Apr-25           | 500              |
      | Oct-25           | 700              |
    And I verify effort values in 'Labor Dual' table are empty for rows with data:
      | Resource Group  | REG_CONSULTANT2 |
    And info: ---13. Enter Test Data Values in REG_CONSULTANT2 row  all months = 100 each, and verify values accepted---
    And I set value '100' to the cell of the column 'Apr-25' of the 'Labor Dual' table for the row with the following data:
      | Resource Group  | REG_CONSULTANT2 |
    And I wait for 3 seconds
    And I set value '100' to the cell of the column 'May-25' of the 'Labor Dual' table for the row with the following data:
      | Resource Group  | REG_CONSULTANT2 |
    And I wait for 3 seconds
    And I set value '100' to the cell of the column 'Jun-25' of the 'Labor Dual' table for the row with the following data:
      | Resource Group  | REG_CONSULTANT2 |
    And I wait for 3 seconds
    And I set value '100' to the cell of the column 'Jul-25' of the 'Labor Dual' table for the row with the following data:
      | Resource Group  | REG_CONSULTANT2 |
    And I wait for 3 seconds
    And I set value '100' to the cell of the column 'Aug-25' of the 'Labor Dual' table for the row with the following data:
      | Resource Group  | REG_CONSULTANT2 |
    And I wait for 3 seconds
    And I set value '100' to the cell of the column 'Sep-25' of the 'Labor Dual' table for the row with the following data:
      | Resource Group  | REG_CONSULTANT2 |
    And I wait for 3 seconds
    And I set value '100' to the cell of the column 'Oct-25' of the 'Labor Dual' table for the row with the following data:
      | Resource Group  | REG_CONSULTANT2 |
    And I wait for 3 seconds
    And I set value '100' to the cell of the column 'Nov-25' of the 'Labor Dual' table for the row with the following data:
      | Resource Group  | REG_CONSULTANT2 |
    And I wait for 3 seconds
    And I set value '100' to the cell of the column 'Dec-25' of the 'Labor Dual' table for the row with the following data:
      | Resource Group  | REG_CONSULTANT2 |
    And I wait for 3 seconds
    And I set value '100' to the cell of the column 'Jan-26' of the 'Labor Dual' table for the row with the following data:
      | Resource Group  | REG_CONSULTANT2 |
    And I wait for 3 seconds
    And I set value '100' to the cell of the column 'Feb-26' of the 'Labor Dual' table for the row with the following data:
      | Resource Group  | REG_CONSULTANT2 |
    And I wait for 3 seconds
    And I set value '100' to the cell of the column 'Mar-26' of the 'Labor Dual' table for the row with the following data:
      | Resource Group  | REG_CONSULTANT2 |
    And I wait for 3 seconds
    And info: ---14. Verify for REG_CONSULTANT2  Planned Effort = 1200 hours---
    And 'Labor Dual' table contain row with following data:
      | Resource Group   | REG_CONSULTANT2  |
      | Resource Group - Free Text      | REG_CONSULTANT2  |
      | Distribution     | Manual (Monthly) |
      | Rate in Resource Unit | $ 90.00/hr       |
      | Effort           | 1200             |
      | Apr-25           | 100              |
      | May-25           | 100              |
      | Jun-25           | 100              |
      | Jul-25           | 100              |
      | Aug-25           | 100              |
      | Sep-25           | 100              |
      | Oct-25           | 100              |
      | Nov-25           | 100              |
      | Dec-25           | 100              |
      | Jan-26           | 100              |
      | Feb-26           | 100              |
      | Mar-26           | 100              |
    And info: ---15. Click Save and verify data is saved---
    And I click on 'Save' button
    And 'Data saved successfully' warning message is displayed in 30 seconds
    And info: ---16. In Status widget, click Update Estimate Totals  verify page refreshed and costs for REG_CONSULTANT1 & REG_CONSULTANT2 are NON-ZERO---
    Then I click on 'Update Cost & Prices' link
    Then I wait for 40 seconds
    And page with name 'Estimate Labor' is opened
    When I select 'Regression Test - Manufacturing' in the 'View' dropdown
    And I click on 'Save' button
    Then text of 'View' dropdown equals 'View: Regression Test - Manufacturing (shared & preferred)'
    And the cell value of the 'Cost in Company Currency' column of the 'Labor Dual' table is greater than '0' for a row with the following data:
      | Resource Group  | REG_CONSULTANT1 |
    And the cell value of the 'Cost in Company Currency' column of the 'Labor Dual' table is greater than '0' for a row with the following data:
      | Resource Group  | REG_CONSULTANT2 |
    And info: ---17. Verify that the Effort Grid shows correct values  REG_CONSULTANT1: Apr-25 = 500, Oct-25 = 700; REG_CONSULTANT2: all months = 100 each---
    And 'Labor Dual' table contain row with following data:
      | Resource Group   | REG_CONSULTANT1  |
      | Resource Group - Free Text      | REG_CONSULTANT1  |
      | Distribution     | Manual (Monthly) |
      | Rate in Resource Unit | $ 120.00/hr      |
      | Effort   | 1200             |
      | Apr-25           | 500              |
      | Oct-25           | 700              |
    And 'Labor Dual' table contain row with following data:
      | Resource Group   | REG_CONSULTANT2  |
      | Resource Group - Free Text      | REG_CONSULTANT2  |
      | Distribution     | Manual (Monthly) |
      | Rate in Resource Unit | $ 90.00/hr       |
      | Effort   | 1200             |
      | Apr-25           | 100              |
      | May-25           | 100              |
      | Jun-25           | 100              |
      | Jul-25           | 100              |
      | Aug-25           | 100              |
      | Sep-25           | 100              |
      | Oct-25           | 100              |
      | Nov-25           | 100              |
      | Dec-25           | 100              |
      | Jan-26           | 100              |
      | Feb-26           | 100              |
      | Mar-26           | 100              |

```

---

_Generated by `scripts/generate-testcase-guides.py` from the RTA mapping and local feature files._
