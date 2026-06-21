# TC-Prof-Services-002 - Fill in Required Fields Validate Save

| Field | Value |
| --- | --- |
| Test ID | TC-Prof-Services-002 |
| Title | Fill in Required Fields Validate Save |
| RTA page | https://twenty5.atlassian.net/wiki/spaces/RTA/pages/190087169/TC-Prof-Services-002+Fill+in+Required+Fields+Validate+Save |
| Map source | `RTA_TEST_SUITE_FEATURE_MAP.md` |

## Run Instructions

When a consultant asks to run this test, use the default app URL unless they provide another URL:

```text
https://approuter-twenty5ipe-dev.cfapps.us10.hana.ondemand.com/#quote
```

For Claude Desktop, ask before launching Selenium Chrome with the default URL. If the user provides another URL, launch that URL instead.

```bash
scripts/start-debug-chrome.sh "https://approuter-twenty5ipe-dev.cfapps.us10.hana.ondemand.com/#quote"
scripts/run-twentyfive-test.sh @TC-002
```

## Default mapped Selenium scenario

| Field | Value |
| --- | --- |
| Feature | 1. Setup Professional Services Proposal |
| Scenario | TC-Prof-Services-002: Fill in Required Fields & Validate Save |
| Tags | `@TC-002 @RUN` |
| File | `imported/twentyfive-regtest/tests/src/test/resources/features/testfolder/setup_professional_services_proposal.feature:80` |

### Scenario Sections

#### Setup

- generate values and store into the variables:

#### 1. Click Save button without editing any fields

- page with name 'Setup page' is opened
- I hover on 'Save' button
- I click on 'Save' button
- 'Error' dialog is displayed
- I hover on 'Error' dialog
- text of 'Error' dialog equals_-Warning-_:
- verify border-color css of 'Error' dialog is 'red'
- verify border-color css of 'Description' field is 'red'
- verify border-color css of 'Leading Company' field is 'red'_-Warning-_
- verify border-color css of 'Leading Department' field is 'red'_-Warning-_
- verify border-color css of 'Client Customer sell-to' field is 'red'
- verify border-color css of 'Customer Currency' field is 'red'
- page with name 'Setup page' is opened

#### 2. Fill in Title or Brief Description field

- I enter data into the next fields:
- page with name 'Setup page' is opened

#### 4. Fill in Estimated Project \|\| 5. Fill in Project End \|\| 6. Select Leading Company from dropdown

- page with name 'Setup page' is opened
- I enter data into the next fields:

#### 7. Verify that Leading Department dropdown contains only departments relevant to selected Leading Company

- I enter data into the next fields:

#### 8. Verify that Profit Center dropdown contains only centers relevant to selected Leading Department

- I enter data into the next fields:
- page with name 'Setup page' is opened

#### 9. Verify that Cost Center dropdown is populated after selecting value from Profit Center

- I enter data into the next fields:
- page with name 'Setup page' is opened
- text of 'Cost Center' field equals 'REG CC-2'

#### 10. Select Leading Company Currency, if different from default of $USA - US Dollars (USD)

- text of 'Leading Company Currency' field equals '$ USA - US Dollar(USD)'

#### 11. Select Client / Customer (Sell-to)

- page with name 'Setup page' is opened
- I enter data into the next fields:
- page with name 'Setup page' is opened
- text of 'Customer Currency' field equals '$ USA - US Dollar(USD)'
- page with name 'Setup page' is opened

#### 12. Click Save button

- I click on 'Save' button
- 'Data saved successfully' warning message is displayed in 30 seconds
- page with name 'Setup page' is opened

#### 13. Review URL in browser

- I verify current page URL contains "#quote:[0-9a-fA-F\\-]{36}"

### Gherkin Excerpt

```gherkin
  Scenario: TC-Prof-Services-002: Fill in Required Fields & Validate Save
    Given generate values and store into the variables:
      | $uniqueProposalName | Regression Test Professional Services Proposal ${CUR_DATE,yyyy-MM-dd hh:mm} |
    And info: ---1. Click Save button without editing any fields ---
    And page with name 'Setup page' is opened
    And I hover on 'Save' button
    And I click on 'Save' button
    And 'Error' dialog is displayed
    And I hover on 'Error' dialog
    Then text of 'Error' dialog equals_-Warning-_:
    """
    Title or Brief Description * is required in Setup tab
    Leading company * is required in Setup tab
    Leading department * is required in Setup tab
    Project Currency * is required in Setup tab
    Client/Customer (Sell-to) * is required in Setup tab
    """
    And verify border-color css of 'Error' dialog is 'red'
    And verify border-color css of 'Description' field is 'red'
    And verify border-color css of 'Leading Company' field is 'red'_-Warning-_
    And verify border-color css of 'Leading Department' field is 'red'_-Warning-_
    And verify border-color css of 'Client Customer sell-to' field is 'red'
    And verify border-color css of 'Customer Currency' field is 'red'

    And page with name 'Setup page' is opened
    And info: ---2. Fill in Title or Brief Description field---
    When I enter data into the next fields:
      | 'Description' field   | $uniqueProposalName |
    And page with name 'Setup page' is opened
#    And info: ---3. Fill in Estimated Project Start with earlier date---
#    When I enter data into the next fields:
#      | 'Planned Start' field | 4/1/2025            |
#    And verify border-color css of 'Planned Start' field is 'red'
    And info: ---4. Fill in Estimated Project || 5. Fill in Project End || 6. Select Leading Company from dropdown---
    And page with name 'Setup page' is opened
    When I enter data into the next fields:
      | 'Planned Start' field        | 1/1/2026            |
      | 'Leading Company' field      | Regression Test     |
      | 'End' field                  | 12/31/2028          |
    And info: ---7. Verify that Leading Department dropdown contains only departments relevant to selected Leading Company---
    When I enter data into the next fields:
      | 'Leading Department' field            | US - New York   |
    And info: ---8. Verify that Profit Center dropdown contains only centers relevant to selected Leading Department ---
    When I enter data into the next fields:
      | 'Profit Center' field           | Services        |
    And page with name 'Setup page' is opened
    And info: ---9. Verify that Cost Center dropdown is populated after selecting value from Profit Center---
    And I enter data into the next fields:
      | 'Cost Center' field            | REG CC-2       |
    And page with name 'Setup page' is opened
    And text of 'Cost Center' field equals 'REG CC-2'
    And info: ---10. Select Leading Company Currency, if different from default of $USA - US Dollars (USD)---
    And text of 'Leading Company Currency' field equals '$ USA - US Dollar(USD)'
#    And info: ---11. Click Edit Rates---
#    Then I click on 'Edit Rates' link
#    And 'Maintain Proposal-specific Currency Exchange Rates' popup is displayed
#    Then I verify tabs available in the popup are:
#    | Currency Exchange Rates |
#    | Escalation or Inflation Rates |
#    And 'Update Rates' button is enabled
#    And 'Confirm' button is enabled
#    And 'Close' button is enabled
#    And info: ---12. Select Escalation or Inflation Rates tab---
#    And I click on 'Escalation or Inflation Rates' tab
#    And page with name 'Escalation or Inflation Rates page' is opened
#    Then I click on 'Customer Currency' field
    And info: ---11. Select Client / Customer (Sell-to)---
    And page with name 'Setup page' is opened
    When I enter data into the next fields:
      | 'Client Customer sell-to' field      | Regression Test - Customer USD |
    And page with name 'Setup page' is opened
    And text of 'Customer Currency' field equals '$ USA - US Dollar(USD)'
    And page with name 'Setup page' is opened
    And info: ---12. Click Save button ---
    And I click on 'Save' button
    And 'Data saved successfully' warning message is displayed in 30 seconds
    And page with name 'Setup page' is opened
    And info: ---13. Review URL in browser ---
    And I verify current page URL contains "#quote:[0-9a-fA-F\\-]{36}"

```

---

_Generated by `scripts/generate-testcase-guides.py` from the RTA mapping and local feature files._
