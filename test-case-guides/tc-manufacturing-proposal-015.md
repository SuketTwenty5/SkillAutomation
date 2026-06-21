# TC-Manufacturing-Proposal-015 - Labor Inflation

| Field | Value |
| --- | --- |
| Test ID | TC-Manufacturing-Proposal-015 |
| Title | Labor Inflation |
| RTA page | https://twenty5.atlassian.net/wiki/spaces/RTA/pages/379846657/TC-Manufacturing-Proposal-015+Labor+Inflation |
| Map source | `RTA_TEST_SUITE_FEATURE_MAP.md` |

## Run Instructions

When a consultant asks to run this test, use the default app URL unless they provide another URL:

```text
https://approuter-twenty5ipe-dev.cfapps.us10.hana.ondemand.com/#quote
```

For Claude Desktop, ask before launching Selenium Chrome with the default URL. If the user provides another URL, launch that URL instead.

```bash
scripts/start-debug-chrome.sh "https://approuter-twenty5ipe-dev.cfapps.us10.hana.ondemand.com/#quote"
scripts/run-twentyfive-test.sh @TCM-015
```

Duplicate feature matches exist for this Confluence page. The tag command may run more than one matching scenario until the canonical feature file is confirmed.

## Default mapped Selenium scenario

| Field | Value |
| --- | --- |
| Feature | 9. Check that Labor Inflation is calculated correctly over the length of the proposal |
| Scenario | TC-Manufacturing-Proposal-015: Labor Inflation |
| Tags | `@TCM-015 @START` |
| File | `imported/twentyfive-regtest/tests/src/test/resources/features/testfolder/laborInflationCalculatedCorrectly.feature:5` |

### Scenario Sections

#### Setup

- open site
- I perform login
- page with name 'Main page' is opened
- generate values and store into the variables:

#### 1. Start creating a new Proposal with Type Regression Test Only - Manufacturing. The Setup tab is opened.

- I click on 'PROPOSALS' tab
- page with name 'Proposals list page' is opened
- click on 'New' button
- page with name 'Setup page' is opened
- I enter data into the next fields:

#### 2. Fill in the required fields. For Estimated Project Start and Project End use Test Data Values. Estimated Project Start = 1/1/2030, Project End = 12/31/2032.

- page with name 'Setup page' is opened
- I enter data into the next fields:
- page with name 'Setup page' is opened
- I enter data into the next fields:
- page with name 'Setup page' is opened
- I enter data into the next fields:
- I enter data into the next fields:
- page with name 'Setup page' is opened
- I enter data into the next fields:
- page with name 'Setup page' is opened
- I enter data into the next fields:
- page with name 'Setup page' is opened

#### 3. In the section How do you want to Start your Project Plan make sure the radio-button Select a template is selected. In the dropdown field select Test Data Value: (AS) Regression - Labor Manual Effort & Inflation - Template.

- I check 'Select a template' radio
- I enter data into the next fields:
- page with name 'Setup page' is opened

#### 4. Click Copy button. The system saves the data and reloads the page.

- I click on 'Copy' button
- page with name 'Setup page' is opened

#### 5. Click the Edit Rates link by the Leading Company Currency field. The Maintain Proposal-specific Currency Exchange Rates popup is displayed.

- I click on 'Edit Rates' link
- 'Maintain Proposal-specific Currency Exchange Rates' popup is displayed
- I wait for 7 seconds

#### 6. Click Escalation or Inflation Rates tab on the popup. Tab content is displayed.

- I click on 'Escalation or Inflation Rates' tab
- page with name 'Setup page' is opened
- 'Maintain Proposal-specific Currency Exchange Rates' popup is displayed
- I should see the following columns in the 'Escalation and Inflammation Rates' table:

#### 7. Enter Test Data Values for years 2030, 2031, 2032. Values: 2030 = 1.05, 2031 = 1.10, 2032 = 1.15.

- I set value '1.05' to the cell of the column '2030' of the 'Escalation and Inflammation Rates' table for the row with the following data:
- I wait for 3 seconds
- I set value '1.10' to the cell of the column '2031' of the 'Escalation and Inflammation Rates' table for the row with the following data:
- I wait for 3 seconds
- I set value '1.15' to the cell of the column '2032' of the 'Escalation and Inflammation Rates' table for the row with the following data:
- I wait for 3 seconds

#### 8. Click Confirm button. Popup Save Confirmation is displayed.

- I click on 'Confirm' button
- I wait for 3 seconds
- 'Save Confirmation' popUp is displayed
- I wait for 4 seconds

#### 9. Click Yes. After a few seconds Force Update of all Costs - Important popup is displayed.

- I click on 'Yes' button
- if 'Force Update' popUp displayed then click 'Force Update Yes' button
- if 'Maintain Proposal-specific Currency Exchange Rates' popup displayed then click 'Close' button
- if 'Save Confirmation' popUp displayed then click 'Yes and Update Costs' button

#### 10. Click Yes. After a few seconds page is refreshed.

- if 'Force Update' popUp displayed then click 'Force Update Yes' button
- page with name 'Setup page' is opened

#### 11. Navigate to the Pricing tab and in the dropdown field How are Labor/Item Rates Calculated select Test Data Value: Regression Test - Bill Rates.

- I click on 'Pricing' tab
- page with name 'Pricing page' is opened
- I enter data into the next fields:
- page with name 'Pricing page' is opened
- I click on 'Pricing' tab

#### 12. Click Save. Data is saved.

- I click on 'Save' button
- I wait for 5 seconds
- page with name 'Pricing page' is opened

#### 13. Navigate to Cost Structure tab and click Open in the Work Package field of the Grid. The Estimate is opened in a new tab. Labor tab is selected.

- I click on 'Cost Structure' tab
- page with name 'Cost Structure page' is opened
- I select 'TEST:-DONOT_CHANGE' in the 'View' dropdown
- I click on 'Save' button
- text of 'View' dropdown equals 'View: TEST:-DONOT_CHANGE (preferred)'
- I click 'Open' in table 'WBS' table for row with data:
- I switch to tab 2
- I click on 'Labor' tab
- page with name 'Estimate Labor' is opened
- I select 'Regression Test - Manufacturing' in the 'View' dropdown
- I wait for 30 seconds
- I click on 'Save' button
- text of 'View' dropdown equals 'View: Regression Test - Manufacturing (shared & preferred)'

#### 14. Click the Gear icon above the right side of the Grid and go to Change Planned Effort Input Mode > Hours By Month.

- I choose in 'Cog Settings' menu the next menu chain:
- I wait for 7 seconds

#### 15. Popup Change Planning Mode in Other Estimates appears.

- page with name 'Estimate Labor' is opened
- 'Change Planning Mode' window is displayed

#### 16. Click Yes on the Popup. The Popup disappears. The Grid is refreshed.

- I click on 'Yes' button
- page with name 'Estimate Labor' is opened

#### 17. For REG_CONSULTANT3 row enter Test Data Value in the Planned Effort field: 3600.

- I set value '3600' to the cell of the column 'Effort' of the 'Labor Dual' table for the row with the following data:
- I wait for 3 seconds

#### 18. Click Save. Data is saved.

- I click on 'Save' button
- 'Data saved successfully' warning message is displayed in 30 seconds
- page with name 'Estimate Labor' is opened
- I retrieve the number of open browser windows

#### 19. Open Cost Price Analysis via the top-right menu. A new browser tab is opened.

- I click on 'Hamburger' menu
- I choose in 'Hamburger' menu the next menu chain:
- page with name 'Estimate Labor' is opened
- I switch to new tab
- page with name 'Cost Price Analysis Wbs page' is opened

#### 20. Click the Workbench tab. The Workbench tab is opened.

- I click on 'Workbench' tab
- page with name 'Workbench page' is opened

#### 21. Select view Regression Test - Inflation. The view is selected, and the grid is displayed according to the view.

- I select 'Regression Test - Inflation' in the 'View' dropdown
- page with name 'Workbench page' is opened
- text of 'View' dropdown equals 'View: Regression Test - Inflation'

#### 22. Verify that for REG_CONSULTANT3 row the following yearly amounts are displayed: 2030 - $3,225.78, 2031 - $12,725.62, 2032 - $29,620.81.

- 'Workbench' table contain row with following data:

### Gherkin Excerpt

```gherkin
  Scenario: TC-Manufacturing-Proposal-015: Labor Inflation
    Given open site
    And I perform login
    Then page with name 'Main page' is opened
    Given generate values and store into the variables:
      | $uniqueProposalName | TEST Labor Inflation ${CUR_DATE,yyyy-MM-dd hh:mm} |
    And info: ---1. Start creating a new Proposal with Type Regression Test Only - Manufacturing. The Setup tab is opened.---
    Given I click on 'PROPOSALS' tab
    Then page with name 'Proposals list page' is opened
    Then click on 'New' button
    And page with name 'Setup page' is opened
    When I enter data into the next fields:
      | 'Proposal Type' field | Regression Test Only - Manufacturing |
    And info: ---2. Fill in the required fields. For Estimated Project Start and Project End use Test Data Values. Estimated Project Start = 1/1/2030, Project End = 12/31/2032.---
    And page with name 'Setup page' is opened
    When I enter data into the next fields:
      | 'Description' field   | $uniqueProposalName |
    And page with name 'Setup page' is opened
    When I enter data into the next fields:
      | 'Planned Start' field | 1/1/2030            |
    And page with name 'Setup page' is opened
    When I enter data into the next fields:
      | 'End' field                  | 12/31/2032         |
    When I enter data into the next fields:
      | 'Your Company' field            | Regression Test |
    And page with name 'Setup page' is opened
    When I enter data into the next fields:
      | 'Leading Department' field            | US - New York   |
    And page with name 'Setup page' is opened
    When I enter data into the next fields:
      | 'Client Customer sell-to' field | Regression Test - Customer USD |
    And page with name 'Setup page' is opened
    And info: ---3. In the section How do you want to Start your Project Plan make sure the radio-button Select a template is selected. In the dropdown field select Test Data Value: (AS) Regression - Labor Manual Effort & Inflation - Template.---
    And I check 'Select a template' radio
    When I enter data into the next fields:
      | 'Search Box' field | (AS) Regression - Labor Manual Effort & Inflation - Template |
    And page with name 'Setup page' is opened
    And info: ---4. Click Copy button. The system saves the data and reloads the page.---
    When I click on 'Copy' button
    Then page with name 'Setup page' is opened
    And info: ---5. Click the Edit Rates link by the Leading Company Currency field. The Maintain Proposal-specific Currency Exchange Rates popup is displayed.---
    When I click on 'Edit Rates' link
    And 'Maintain Proposal-specific Currency Exchange Rates' popup is displayed
    And I wait for 7 seconds
    And info: ---6. Click Escalation or Inflation Rates tab on the popup. Tab content is displayed.---
    Then I click on 'Escalation or Inflation Rates' tab
    And page with name 'Setup page' is opened
    And 'Maintain Proposal-specific Currency Exchange Rates' popup is displayed
    Then I should see the following columns in the 'Escalation and Inflammation Rates' table:
      | Escalation Type    |
      | 2030               |
      | 2031               |
      | 2032               |
    And info: ---7. Enter Test Data Values for years 2030, 2031, 2032. Values: 2030 = 1.05, 2031 = 1.10, 2032 = 1.15.---
    And I set value '1.05' to the cell of the column '2030' of the 'Escalation and Inflammation Rates' table for the row with the following data:
      | Escalation Type | Escalation United States (CALENDAR) |
    And I wait for 3 seconds
    And I set value '1.10' to the cell of the column '2031' of the 'Escalation and Inflammation Rates' table for the row with the following data:
      | Escalation Type | Escalation United States (CALENDAR) |
    And I wait for 3 seconds
    And I set value '1.15' to the cell of the column '2032' of the 'Escalation and Inflammation Rates' table for the row with the following data:
      | Escalation Type | Escalation United States (CALENDAR) |
    And I wait for 3 seconds
    And info: ---8. Click Confirm button. Popup Save Confirmation is displayed.---
    Then I click on 'Confirm' button
    And I wait for 3 seconds
    And 'Save Confirmation' popUp is displayed
    And I wait for 4 seconds
    And info: ---9. Click Yes. After a few seconds Force Update of all Costs - Important popup is displayed.---
    Then I click on 'Yes' button
    Then if 'Force Update' popUp displayed then click 'Force Update Yes' button
    Then if 'Maintain Proposal-specific Currency Exchange Rates' popup displayed then click 'Close' button
    Then if 'Save Confirmation' popUp displayed then click 'Yes and Update Costs' button
    And info: ---10. Click Yes. After a few seconds page is refreshed.---
    Then if 'Force Update' popUp displayed then click 'Force Update Yes' button
    And page with name 'Setup page' is opened
    And info: ---11. Navigate to the Pricing tab and in the dropdown field How are Labor/Item Rates Calculated select Test Data Value: Regression Test - Bill Rates.---
    Then I click on 'Pricing' tab
    And page with name 'Pricing page' is opened
    When I enter data into the next fields:
      | 'How are Labor/Item Rates Calculated' field | Regression Test - Bill Rates |
    And page with name 'Pricing page' is opened
    Then I click on 'Pricing' tab
    And info: ---12. Click Save. Data is saved.---
    When I click on 'Save' button
    And I wait for 5 seconds
    Then page with name 'Pricing page' is opened
    And info: ---13. Navigate to Cost Structure tab and click Open in the Work Package field of the Grid. The Estimate is opened in a new tab. Labor tab is selected.---
    And I click on 'Cost Structure' tab
    Then page with name 'Cost Structure page' is opened
    When I select 'TEST:-DONOT_CHANGE' in the 'View' dropdown
    And I click on 'Save' button
    Then text of 'View' dropdown equals 'View: TEST:-DONOT_CHANGE (preferred)'
    And I click 'Open' in table 'WBS' table for row with data:
      | WBS Code              | 1                   |
    Then I switch to tab 2
    And I click on 'Labor' tab
    And page with name 'Estimate Labor' is opened
#    When I select 'TEST:-DONOT_CHANGE' in the 'View' dropdown
#    And I wait for 30 seconds
#    And I click on 'Save' button
#    Then text of 'View' dropdown equals 'View: TEST:-DONOT_CHANGE (preferred)'
    When I select 'Regression Test - Manufacturing' in the 'View' dropdown
    And I wait for 30 seconds
    And I click on 'Save' button
    Then text of 'View' dropdown equals 'View: Regression Test - Manufacturing (shared & preferred)'
    And info: ---14. Click the Gear icon above the right side of the Grid and go to Change Planned Effort Input Mode > Hours By Month.---
    Then I choose in 'Cog Settings' menu the next menu chain:
      | Change Planned Effort Input Mode |
      | Hours by Month                   |
    Then I wait for 7 seconds
    And info: ---15. Popup Change Planning Mode in Other Estimates appears.---
    And page with name 'Estimate Labor' is opened
    Then 'Change Planning Mode' window is displayed
    And info: ---16. Click Yes on the Popup. The Popup disappears. The Grid is refreshed.---
    And I click on 'Yes' button
    And page with name 'Estimate Labor' is opened
    And info: ---17. For REG_CONSULTANT3 row enter Test Data Value in the Planned Effort field: 3600.---
    And I set value '3600' to the cell of the column 'Effort' of the 'Labor Dual' table for the row with the following data:
      | Description      | REG_CONSULTANT3 |
    And I wait for 3 seconds
    And info: ---18. Click Save. Data is saved.---
    And I click on 'Save' button
    And 'Data saved successfully' warning message is displayed in 30 seconds
    Then page with name 'Estimate Labor' is opened
    When I retrieve the number of open browser windows
    And info: ---19. Open Cost Price Analysis via the top-right menu. A new browser tab is opened.---
    Then I click on 'Hamburger' menu
    Then I choose in 'Hamburger' menu the next menu chain:
      | Open                     |
      | Open Cost Price Analysis |
    Then page with name 'Estimate Labor' is opened
    Then I switch to new tab
    And page with name 'Cost Price Analysis Wbs page' is opened
    And info: ---20. Click the Workbench tab. The Workbench tab is opened.---
    Then I click on 'Workbench' tab
    Then page with name 'Workbench page' is opened
    And info: ---21. Select view Regression Test - Inflation. The view is selected, and the grid is displayed according to the view.---
    When I select 'Regression Test - Inflation' in the 'View' dropdown
    Then page with name 'Workbench page' is opened
    Then text of 'View' dropdown equals 'View: Regression Test - Inflation'
    And info: ---22. Verify that for REG_CONSULTANT3 row the following yearly amounts are displayed: 2030 - $3,225.78, 2031 - $12,725.62, 2032 - $29,620.81.---
    And 'Workbench' table contain row with following data:
      | Description                        | REG_CONSULTANT3  |
      | 2030                               | $ 3,225.78       |
      | 2031                               | $ 12,725.62      |
      | 2032                               | $ 29,620.81      |

```

## Alternate mapped Selenium scenario 2

| Field | Value |
| --- | --- |
| Feature | 6. Check that Labor Inflation is calculated correctly over the length of the proposal |
| Scenario | TC-Manufacturing-Proposal-015: Labor Inflation |
| Tags | `@TCM-015 @START` |
| File | `imported/twentyfive-regtest/tests/src/test/resources/features/testfolder/twentyfive2_4_inflation_labor__material_manufacturing_proposal.feature:5` |

### Scenario Sections

#### Setup

- open site
- I perform Mfg 2.4 login
- page with name 'Main page' is opened
- generate values and store into the variables:

#### 1. Start creating a new Proposal with Type Regression Test Only - Manufacturing. The Setup tab is opened.

- I click on 'PROPOSALS' tab
- page with name 'Proposals list page' is opened
- click on 'New' button
- page with name 'Setup page' is opened
- I enter data into the next fields:

#### 2. Fill in the required fields. For Estimated Project Start and Project End use Test Data Values. Estimated Project Start = 1/1/2030, Project End = 12/31/2032.

- page with name 'Setup page' is opened
- I enter data into the next fields:
- page with name 'Setup page' is opened
- I enter data into the next fields:
- page with name 'Setup page' is opened
- I enter data into the next fields:
- I enter data into the next fields:
- page with name 'Setup page' is opened
- I enter data into the next fields:
- page with name 'Setup page' is opened
- I enter data into the next fields:
- page with name 'Setup page' is opened

#### 3. In the section How do you want to Start your Project Plan make sure the radio-button Select a template is selected. In the dropdown field select Test Data Value: (AS) Regression - Labor Manual Effort & Inflation - Template.

- I check 'Select a template' radio
- I enter data into the next fields:
- page with name 'Setup page' is opened
- I click on 'Re schedule' radio
- page with name 'Setup page' is opened

#### 4. Click Copy button. The system saves the data and reloads the page.

- I click on 'Copy' button
- page with name 'Setup page' is opened

#### 5. Click the Edit Rates link by the Leading Company Currency field. The Maintain Proposal-specific Currency Exchange Rates popup is displayed.

- I click on 'Edit Rates' link
- 'Maintain Proposal-specific Currency Exchange Rates' popup is displayed
- I wait for 7 seconds

#### 6. Click Escalation or Inflation Rates tab on the popup. Tab content is displayed.

- I click on 'Escalation or Inflation Rates' tab
- page with name 'Setup page' is opened
- 'Maintain Proposal-specific Currency Exchange Rates' popup is displayed
- I should see the following columns in the 'Escalation and Inflammation Rates' table:

#### 7. Enter Test Data Values for years 2030, 2031, 2032. Values: 2030 = 1.05, 2031 = 1.10, 2032 = 1.15.

- I set value '1.05' to the cell of the column '2030' of the 'Escalation and Inflammation Rates' table for the row with the following data:
- I wait for 3 seconds
- I set value '1.10' to the cell of the column '2031' of the 'Escalation and Inflammation Rates' table for the row with the following data:
- I wait for 3 seconds
- I set value '1.15' to the cell of the column '2032' of the 'Escalation and Inflammation Rates' table for the row with the following data:
- I wait for 3 seconds

#### 8. Click Confirm button. Popup Save Confirmation is displayed.

- I click on 'Confirm' button
- I wait for 3 seconds
- 'Save Confirmation' popUp is displayed
- I wait for 4 seconds

#### 9. Click Yes. After a few seconds Force Update of all Costs - Important popup is displayed.

- I click on 'Yes' button
- if 'Force Update' popUp displayed then click 'Force Update Yes' button
- if 'Maintain Proposal-specific Currency Exchange Rates' popup displayed then click 'Close' button
- if 'Save Confirmation' popUp displayed then click 'Yes and Update Costs' button

#### 10. Click Yes. After a few seconds page is refreshed.

- if 'Force Update' popUp displayed then click 'Force Update Yes' button
- page with name 'Setup page' is opened

#### 11. Navigate to the Pricing tab and in the dropdown field How are Labor/Item Rates Calculated select Test Data Value: Regression Test - Bill Rates.

- I click on 'Pricing' tab
- page with name 'Pricing page' is opened
- I enter data into the next fields:
- page with name 'Pricing page' is opened
- I click on 'Pricing' tab

#### 12. Click Save. Data is saved.

- I click on 'Save' button
- I wait for 5 seconds
- page with name 'Pricing page' is opened

#### 13. Navigate to Cost Structure tab and click Open in the Work Package field of the Grid. The Estimate is opened in a new tab. Labor tab is selected.

- I click on 'Cost Structure' tab
- page with name 'Cost Structure page' is opened
- I select 'TEST:-DONOT_CHANGE' in the 'View' dropdown
- I click on 'Save' button
- text of 'View' dropdown equals 'View: TEST:-DONOT_CHANGE (preferred)'
- I click 'Open' in table 'WBS' table for row with data:
- I switch to tab 2
- I wait for 7 seconds
- I click on 'Labor' tab
- I select 'Regression Test - Manufacturing' in the 'View' dropdown
- I wait for 7 seconds
- I click on 'Save' button
- text of 'View' dropdown equals 'View: Regression Test - Manufacturing (shared & preferred)'
- page with name 'Estimate Labor' is opened

#### 14. Click the Gear icon above the right side of the Grid and go to Change Planned Effort Input Mode > Hours By Month.

- I choose in 'Cog Settings' menu the next menu chain:
- I wait for 7 seconds

#### 15. Popup Change Planning Mode in Other Estimates appears.

- page with name 'Estimate Labor' is opened
- 'Change Planning Mode' window is displayed

#### 16. Click Yes on the Popup. The Popup disappears. The Grid is refreshed.

- I click on 'Yes' button
- page with name 'Estimate Labor' is opened

#### 17. For REG_CONSULTANT3 row enter Test Data Value in the Planned Effort field: 3600.

- I set value '3600' to the cell of the column 'Effort' of the 'Labor Dual' table for the row with the following data:
- I wait for 3 seconds

#### 18. Click Save. Data is saved.

- I click on 'Save' button
- 'Data saved successfully' warning message is displayed in 30 seconds
- page with name 'Estimate Labor' is opened
- I retrieve the number of open browser windows

#### 19. Open Cost Price Analysis via the top-right menu. A new browser tab is opened.

- I click on 'Hamburger' menu
- I choose in 'Hamburger' menu the next menu chain:
- page with name 'Estimate Labor' is opened
- I switch to new tab
- page with name 'Cost Price Analysis Wbs page' is opened

#### 20. Click the Workbench tab. The Workbench tab is opened.

- I click on 'Workbench' tab
- page with name 'Workbench page' is opened

#### 21. Select view Regression Test - Inflation. The view is selected, and the grid is displayed according to the view.

- I select 'Regression Test - Inflation' in the 'View' dropdown
- page with name 'Workbench page' is opened
- text of 'View' dropdown equals 'View: Regression Test - Inflation'

#### 22. Verify that for REG_CONSULTANT3 row the following yearly amounts are displayed: 2030 - $3,225.78, 2031 - $12,725.62, 2032 - $29,620.81.

- 'Workbench' table contain row with following data:

### Gherkin Excerpt

```gherkin
  Scenario: TC-Manufacturing-Proposal-015: Labor Inflation
    Given open site
    And I perform Mfg 2.4 login
    Then page with name 'Main page' is opened
    Given generate values and store into the variables:
      | $uniqueProposalName | TEST Labor Inflation ${CUR_DATE,yyyy-MM-dd hh:mm} |
    And info: ---1. Start creating a new Proposal with Type Regression Test Only - Manufacturing. The Setup tab is opened.---
    Given I click on 'PROPOSALS' tab
    Then page with name 'Proposals list page' is opened
    Then click on 'New' button
    And page with name 'Setup page' is opened
    When I enter data into the next fields:
      | 'Proposal Type' field | Regression Test Only - Manufacturing |
    And info: ---2. Fill in the required fields. For Estimated Project Start and Project End use Test Data Values. Estimated Project Start = 1/1/2030, Project End = 12/31/2032.---
    And page with name 'Setup page' is opened
    When I enter data into the next fields:
      | 'Description' field   | $uniqueProposalName |
    And page with name 'Setup page' is opened
    When I enter data into the next fields:
      | 'Planned Start' field | 1/1/2030            |
    And page with name 'Setup page' is opened
    When I enter data into the next fields:
      | 'End' field                  | 12/31/2032         |
    When I enter data into the next fields:
      | 'Your Company' field            | Regression Test |
    And page with name 'Setup page' is opened
    When I enter data into the next fields:
      | 'Leading Department' field            | US - New York   |
    And page with name 'Setup page' is opened
    When I enter data into the next fields:
      | 'Client Customer sell-to' field | Regression Test - Customer USD |
    And page with name 'Setup page' is opened
    And info: ---3. In the section How do you want to Start your Project Plan make sure the radio-button Select a template is selected. In the dropdown field select Test Data Value: (AS) Regression - Labor Manual Effort & Inflation - Template.---
    And I check 'Select a template' radio
    When I enter data into the next fields:
      | 'Search Box' field | (AS) Regression - Labor Manual Effort & Inflation - Template |
    And page with name 'Setup page' is opened
    And I click on 'Re schedule' radio
    And page with name 'Setup page' is opened
    And info: ---4. Click Copy button. The system saves the data and reloads the page.---
    When I click on 'Copy' button
    Then page with name 'Setup page' is opened
    And info: ---5. Click the Edit Rates link by the Leading Company Currency field. The Maintain Proposal-specific Currency Exchange Rates popup is displayed.---
    When I click on 'Edit Rates' link
    And 'Maintain Proposal-specific Currency Exchange Rates' popup is displayed
    And I wait for 7 seconds
    And info: ---6. Click Escalation or Inflation Rates tab on the popup. Tab content is displayed.---
    Then I click on 'Escalation or Inflation Rates' tab
    And page with name 'Setup page' is opened
    And 'Maintain Proposal-specific Currency Exchange Rates' popup is displayed
    Then I should see the following columns in the 'Escalation and Inflammation Rates' table:
      | Escalation Type    |
      | 2030               |
      | 2031               |
      | 2032               |
    And info: ---7. Enter Test Data Values for years 2030, 2031, 2032. Values: 2030 = 1.05, 2031 = 1.10, 2032 = 1.15.---
    And I set value '1.05' to the cell of the column '2030' of the 'Escalation and Inflammation Rates' table for the row with the following data:
      | Escalation Type | Escalation United States (CALENDAR) |
    And I wait for 3 seconds
    And I set value '1.10' to the cell of the column '2031' of the 'Escalation and Inflammation Rates' table for the row with the following data:
      | Escalation Type | Escalation United States (CALENDAR) |
    And I wait for 3 seconds
    And I set value '1.15' to the cell of the column '2032' of the 'Escalation and Inflammation Rates' table for the row with the following data:
      | Escalation Type | Escalation United States (CALENDAR) |
    And I wait for 3 seconds
    And info: ---8. Click Confirm button. Popup Save Confirmation is displayed.---
    Then I click on 'Confirm' button
    And I wait for 3 seconds
    And 'Save Confirmation' popUp is displayed
    And I wait for 4 seconds
    And info: ---9. Click Yes. After a few seconds Force Update of all Costs - Important popup is displayed.---
    Then I click on 'Yes' button
    Then if 'Force Update' popUp displayed then click 'Force Update Yes' button
    Then if 'Maintain Proposal-specific Currency Exchange Rates' popup displayed then click 'Close' button
    Then if 'Save Confirmation' popUp displayed then click 'Yes and Update Costs' button
    And info: ---10. Click Yes. After a few seconds page is refreshed.---
    Then if 'Force Update' popUp displayed then click 'Force Update Yes' button
    And page with name 'Setup page' is opened
    And info: ---11. Navigate to the Pricing tab and in the dropdown field How are Labor/Item Rates Calculated select Test Data Value: Regression Test - Bill Rates.---
    Then I click on 'Pricing' tab
    And page with name 'Pricing page' is opened
    When I enter data into the next fields:
      | 'How are Labor/Item Rates Calculated' field | Regression Test - Bill Rates |
    And page with name 'Pricing page' is opened
    Then I click on 'Pricing' tab
    And info: ---12. Click Save. Data is saved.---
    When I click on 'Save' button
    And I wait for 5 seconds
    Then page with name 'Pricing page' is opened
    And info: ---13. Navigate to Cost Structure tab and click Open in the Work Package field of the Grid. The Estimate is opened in a new tab. Labor tab is selected.---
    And I click on 'Cost Structure' tab
    Then page with name 'Cost Structure page' is opened
    When I select 'TEST:-DONOT_CHANGE' in the 'View' dropdown
    And I click on 'Save' button
    Then text of 'View' dropdown equals 'View: TEST:-DONOT_CHANGE (preferred)'
    And I click 'Open' in table 'WBS' table for row with data:
      | WBS Code              | 1                   |
    Then I switch to tab 2
    And I wait for 7 seconds
    And I click on 'Labor' tab
    When I select 'Regression Test - Manufacturing' in the 'View' dropdown
    And I wait for 7 seconds
    And I click on 'Save' button
    Then text of 'View' dropdown equals 'View: Regression Test - Manufacturing (shared & preferred)'
    And page with name 'Estimate Labor' is opened
    And info: ---14. Click the Gear icon above the right side of the Grid and go to Change Planned Effort Input Mode > Hours By Month.---
    Then I choose in 'Cog Settings' menu the next menu chain:
      | Change Planned Effort Input Mode |
      | Hours by Month                   |
    Then I wait for 7 seconds
    And info: ---15. Popup Change Planning Mode in Other Estimates appears.---
    And page with name 'Estimate Labor' is opened
    Then 'Change Planning Mode' window is displayed
    And info: ---16. Click Yes on the Popup. The Popup disappears. The Grid is refreshed.---
    And I click on 'Yes' button
    And page with name 'Estimate Labor' is opened
    And info: ---17. For REG_CONSULTANT3 row enter Test Data Value in the Planned Effort field: 3600.---
    And I set value '3600' to the cell of the column 'Effort' of the 'Labor Dual' table for the row with the following data:
      | Resource Group - Free Text      | REG_CONSULTANT3 |
    And I wait for 3 seconds
    And info: ---18. Click Save. Data is saved.---
    And I click on 'Save' button
    And 'Data saved successfully' warning message is displayed in 30 seconds
    Then page with name 'Estimate Labor' is opened
    When I retrieve the number of open browser windows
    And info: ---19. Open Cost Price Analysis via the top-right menu. A new browser tab is opened.---
    Then I click on 'Hamburger' menu
    Then I choose in 'Hamburger' menu the next menu chain:
      | Open                     |
      | Open Cost Price Analysis |
    Then page with name 'Estimate Labor' is opened
    Then I switch to new tab
    And page with name 'Cost Price Analysis Wbs page' is opened
    And info: ---20. Click the Workbench tab. The Workbench tab is opened.---
    Then I click on 'Workbench' tab
    Then page with name 'Workbench page' is opened
    And info: ---21. Select view Regression Test - Inflation. The view is selected, and the grid is displayed according to the view.---
    When I select 'Regression Test - Inflation' in the 'View' dropdown
    Then page with name 'Workbench page' is opened
    Then text of 'View' dropdown equals 'View: Regression Test - Inflation'
    And info: ---22. Verify that for REG_CONSULTANT3 row the following yearly amounts are displayed: 2030 - $3,225.78, 2031 - $12,725.62, 2032 - $29,620.81.---
    And 'Workbench' table contain row with following data:
      | Description                        | REG_CONSULTANT3  |
      | 2030                               | $ 3,225.78       |
      | 2031                               | $ 12,725.62      |
      | 2032                               | $ 29,620.81      |

```

---

_Generated by `scripts/generate-testcase-guides.py` from the RTA mapping and local feature files._
