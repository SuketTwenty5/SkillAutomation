# TC-Prof-Services-003 - Validate Copy Template Functionality

| Field | Value |
| --- | --- |
| Test ID | TC-Prof-Services-003 |
| Title | Validate Copy Template Functionality |
| RTA page | https://twenty5.atlassian.net/wiki/spaces/RTA/pages/190087187/TC-Prof-Services-003+Validate+Copy+Template+Functionality |
| Map source | `RTA_TEST_SUITE_FEATURE_MAP.md` |

## Run Instructions

When a consultant asks to run this test, use the default app URL unless they provide another URL:

```text
https://approuter-twenty5ipe-dev.cfapps.us10.hana.ondemand.com/#quote
```

For Claude Desktop, ask before launching Selenium Chrome with the default URL. If the user provides another URL, launch that URL instead.

```bash
scripts/start-debug-chrome.sh "https://approuter-twenty5ipe-dev.cfapps.us10.hana.ondemand.com/#quote"
scripts/run-twentyfive-test.sh @TC-003
```

## Default mapped Selenium scenario

| Field | Value |
| --- | --- |
| Feature | 1. Setup Professional Services Proposal |
| Scenario | TC-Prof-Services-003: Validate Copy Template Functionality |
| Tags | `@TC-003 @RUN` |
| File | `imported/twentyfive-regtest/tests/src/test/resources/features/testfolder/setup_professional_services_proposal.feature:161` |

### Scenario Sections

#### 1. Enable Select a Template radio button

- I hover on 'Select a template' radio
- I verify 'Select a Template' of setup page is enabled, not required, radio buttons and is checked

#### 2. Search for Template from Library and select REG TEST Professional Services Template

- I enter data into the next fields:
- text of 'Search for Template from Library' field equals 'REG TEST Professional Services Template'

#### 3. Verify selections for which proposal elements to create or copy

- 'proposal elements to create or copy' radioButtons is displayed
- I verify 'Phases/options (No sets)' of setup page is not enabled, not required, check box and is unchecked
- I verify 'Priced deliverables (No items)' of setup page is not enabled, not required, check box and is unchecked
- I verify 'Risk & opportunities (No risks)' of setup page is not enabled, not required, check box and is unchecked
- I verify 'Labor estimates (15608 hrs)' of setup page is enabled, not required, check box and is checked
- I verify 'WBS or work-packages (1 elements)' of setup page is enabled, not required, check box and is checked
- I verify 'Billing milestones (No payments)' of setup page is not enabled, not required, check box and is unchecked
- I verify 'Sourcing & purchasing history (No docs)' of setup page is not enabled, not required, check box and is unchecked
- I verify 'Proposal bill of material (No items)' of setup page is not enabled, not required, check box and is unchecked
- I verify 'Tasks & sub-tasks (No tasks)' of setup page is not enabled, not required, check box and is unchecked
- I verify 'Billing rates (No proposal-rates)' of setup page is not enabled, not required, check box and is unchecked
- I verify 'Tags & category values (No tags)' of setup page is not enabled, not required, check box and is unchecked
- I verify 'Material estimates (No parts)' of setup page is not enabled, not required, check box and is unchecked
- I verify 'Requirements (No items)' of setup page is not enabled, not required, check box and is unchecked
- I verify 'Cost/pricing parameters' of setup page is enabled, not required, check box and is checked
- I verify 'Funding sources (No funds)' of setup page is not enabled, not required, check box and is unchecked
- I verify 'Travel/other estimates (1 items)' of setup page is enabled, not required, check box and is checked
- I verify 'Reset dates to ' of setup page is enabled, not required, radio buttons and is unchecked
- I verify 'Re-schedule proposal' of setup page is enabled, not required, radio buttons and is checked
- page with name 'Setup page' is opened

#### 4. Click Copy button

- click on 'Copy' button
- warning messages displayed in 15 seconds are:
- page with name 'Setup page' is opened

#### 5. From Leading Company Currency, click Edit Rates hyperlink

- I click on 'Edit Rates' link
- 'Maintain Proposal-specific Currency Exchange Rates' popup is displayed
- I wait for 7 seconds

#### 6. Select Escalation or Inflation Rates tab

- I click on 'Escalation or Inflation Rates' tab
- page with name 'Setup page' is opened
- 'Maintain Proposal-specific Currency Exchange Rates' popup is displayed
- I should see the following columns in the 'Escalation and Inflammation Rates' table:

#### 7. Select the US Escalation United States (CALENDAR) Escalation Factor

- I click on 'Escalation first' cell

#### 8. Click Update Rates to start updating Rates

- I click on 'Update Rates' button

#### 9. Select Turn off Escalation (Reset all Rates to 1)

- I click on 'Reset all Rates to 1' option
- I wait for 7 seconds

#### 10. Click Confirm

- I click on 'Confirm' button
- I wait for 3 seconds
- 'Save Confirmation' popUp is displayed
- I wait for 4 seconds

#### 11. Click Yes to Save the Escalation update

- I click on 'Yes' button

#### 12. Click Yes to force update of all costs

- if 'Force Update' popUp displayed then click 'Force Update Yes' button
- if 'Maintain Proposal-specific Currency Exchange Rates' popup displayed then click 'Close' button
- if 'Save Confirmation' popUp displayed then click 'Yes and Update Costs' button
- if 'Force Update' popUp displayed then click 'Force Update Yes' button
- I wait for 7 seconds
- page with name 'Setup page' is opened

### Gherkin Excerpt

```gherkin
  Scenario: TC-Prof-Services-003: Validate Copy Template Functionality
    And info: ---1. Enable Select a Template radio button  ---
    And I hover on 'Select a template' radio
    And I verify 'Select a Template' of setup page is enabled, not required, radio buttons and is checked
    And info: ---2. Search for Template from Library and select REG TEST Professional Services Template  ---
    When I enter data into the next fields:
      | 'Search for Template from Library' field | REG TEST Professional Services Template |
    And text of 'Search for Template from Library' field equals 'REG TEST Professional Services Template'
    And info: ---3. Verify selections for which proposal elements to create or copy ---
    And 'proposal elements to create or copy' radioButtons is displayed
    And I verify 'Phases/options (No sets)' of setup page is not enabled, not required, check box and is unchecked
    And I verify 'Priced deliverables (No items)' of setup page is not enabled, not required, check box and is unchecked
    And I verify 'Risk & opportunities (No risks)' of setup page is not enabled, not required, check box and is unchecked
    And I verify 'Labor estimates (15608 hrs)' of setup page is enabled, not required, check box and is checked
    And I verify 'WBS or work-packages (1 elements)' of setup page is enabled, not required, check box and is checked
    And I verify 'Billing milestones (No payments)' of setup page is not enabled, not required, check box and is unchecked
    And I verify 'Sourcing & purchasing history (No docs)' of setup page is not enabled, not required, check box and is unchecked
    And I verify 'Proposal bill of material (No items)' of setup page is not enabled, not required, check box and is unchecked
    And I verify 'Tasks & sub-tasks (No tasks)' of setup page is not enabled, not required, check box and is unchecked
    And I verify 'Billing rates (No proposal-rates)' of setup page is not enabled, not required, check box and is unchecked
    And I verify 'Tags & category values (No tags)' of setup page is not enabled, not required, check box and is unchecked
    And I verify 'Material estimates (No parts)' of setup page is not enabled, not required, check box and is unchecked
    And I verify 'Requirements (No items)' of setup page is not enabled, not required, check box and is unchecked
    And I verify 'Cost/pricing parameters' of setup page is enabled, not required, check box and is checked
    And I verify 'Funding sources (No funds)' of setup page is not enabled, not required, check box and is unchecked
    And I verify 'Travel/other estimates (1 items)' of setup page is enabled, not required, check box and is checked
#    And I verify 'Transfer to US - New York' of setup page is enabled, not required, check box and is unchecked

    And I verify 'Reset dates to ' of setup page is enabled, not required, radio buttons and is unchecked
    And I verify 'Re-schedule proposal' of setup page is enabled, not required, radio buttons and is checked

    And page with name 'Setup page' is opened
    And info: ---4. Click Copy button ---
    And click on 'Copy' button
    And warning messages displayed in 15 seconds are:
    """
    BOE's copy is happening in background, please check back after some time ---
    Rescheduling during copy will reset all labor etc. dates to their assigned WBS or task
    """
    And page with name 'Setup page' is opened
    And info: ---5. From Leading Company Currency, click Edit Rates hyperlink---
    And I click on 'Edit Rates' link
    And 'Maintain Proposal-specific Currency Exchange Rates' popup is displayed
    And I wait for 7 seconds
    And info: ---6. Select Escalation or Inflation Rates tab ---
    Then I click on 'Escalation or Inflation Rates' tab
    And page with name 'Setup page' is opened
    And 'Maintain Proposal-specific Currency Exchange Rates' popup is displayed
    Then I should see the following columns in the 'Escalation and Inflammation Rates' table:
      | Escalation Type    |
      | 2026               |
      | 2027               |
      | 2028               |
    And info: ---7. Select the US Escalation United States (CALENDAR) Escalation Factor ---
    Then I click on 'Escalation first' cell
    And info: ---8. Click Update Rates to start updating Rates ---
    Then I click on 'Update Rates' button
    And info: ---9. Select Turn off Escalation (Reset all Rates to 1) ---
    Then I click on 'Reset all Rates to 1' option
    And I wait for 7 seconds
    And info: ---10. Click Confirm  ---
    Then I click on 'Confirm' button
    And I wait for 3 seconds
    And 'Save Confirmation' popUp is displayed
    And I wait for 4 seconds
    And info: ---11. Click Yes to Save the Escalation update  ---
    Then I click on 'Yes' button
#    And I wait for 7 seconds
#    And 'Force Update' popUp is displayed
    And info: ---12. Click Yes to force update of all costs  ---
    Then if 'Force Update' popUp displayed then click 'Force Update Yes' button
    Then if 'Maintain Proposal-specific Currency Exchange Rates' popup displayed then click 'Close' button
    Then if 'Save Confirmation' popUp displayed then click 'Yes and Update Costs' button
    Then if 'Force Update' popUp displayed then click 'Force Update Yes' button
    And I wait for 7 seconds
    And page with name 'Setup page' is opened
#    And I refresh page
#    And page with name 'Main page' is opened
#    And page with name 'Setup page' is opened


```

---

_Generated by `scripts/generate-testcase-guides.py` from the RTA mapping and local feature files._
