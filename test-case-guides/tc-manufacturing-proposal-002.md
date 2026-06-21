# TC-Manufacturing-Proposal-002 - Fill in Required Fields Validate Save

| Field | Value |
| --- | --- |
| Test ID | TC-Manufacturing-Proposal-002 |
| Title | Fill in Required Fields Validate Save |
| RTA page | https://twenty5.atlassian.net/wiki/spaces/RTA/pages/200966145/TC-Manufacturing-Proposal-002+Fill+in+Required+Fields+Validate+Save |
| Map source | `RTA_TEST_SUITE_FEATURE_MAP.md` |

## Run Instructions

When a consultant asks to run this test, use the default app URL unless they provide another URL:

```text
https://approuter-twenty5ipe-dev.cfapps.us10.hana.ondemand.com/#quote
```

For Claude Desktop, ask before launching Selenium Chrome with the default URL. If the user provides another URL, launch that URL instead.

```bash
scripts/start-debug-chrome.sh "https://approuter-twenty5ipe-dev.cfapps.us10.hana.ondemand.com/#quote"
scripts/run-twentyfive-test.sh @TCM-002
```

Duplicate feature matches exist for this Confluence page. The tag command may run more than one matching scenario until the canonical feature file is confirmed.

## Default mapped Selenium scenario

| Field | Value |
| --- | --- |
| Feature | 4. Setup Manufacturing Proposal |
| Scenario | TC-Manufacturing-Proposal-002: Fill in Required Fields & Validate Save |
| Tags | `@TCM-002 @RUN` |
| File | `imported/twentyfive-regtest/tests/src/test/resources/features/testfolder/manafacturing_proposal_setup.feature:81` |

### Scenario Sections

#### Setup

- generate values and store into the variables:

#### 1. Clear values from fields: Estimated Project Start, Project End

- I enter data into the next fields:

#### 2. Click Save button without editing any fields

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
- verify border-color css of 'Planned Start' field is 'red'
- verify border-color css of 'End' field is 'red'
- page with name 'Setup page' is opened

#### 3. Fill Proposal Title field with a value between 1 and 255 characters.

- I enter data into the next fields:
- page with name 'Setup page' is opened

#### 4. Fill Estimated Project Start field with a valid date (e.g., 4/1/25).

- I enter data into the next fields:

#### 5. Fill Project End field with an earlier date than start (e.g., 3/31/25).

- I click on 'End' field
- page with name 'Setup page' is opened
- I enter data into the next fields:
- verify border-color css of 'End' field is 'red'

#### 6. Fill Planned End field with a later date (e.g., 3/31/26).

- page with name 'Setup page' is opened
- I enter data into the next fields:

#### 7. Select Test Data Value from Your Company dropdown

- I enter data into the next fields:

#### 8. Click to expand the Leading Site or Department dropdown field

- I click on 'Leading Site' dropdown
- 'Leading Site' menuItem contains items:
- I click on 'Leading Site' dropdown
- page with name 'Setup page' is opened

#### 9. Select Test Data Value from Leading Site or Department dropdown

- I enter data into the next fields:

#### 10. Select Test Data Value from Client/Customer (Sell-to) dropdown

- I enter data into the next fields:
- page with name 'Setup page' is opened

#### 11. Fill Project Goals or Remarks with text Test Data Value

- I enter data into the next fields:

#### 12. Click Save button.

- I click on 'Save' button
- 'Data saved successfully' warning message is displayed in 30 seconds

#### 13. Click the the Pricing tab

- I click on 'Pricing' tab
- page with name 'Pricing page' is opened

#### 14. In the dropdown field How are Labor/Item Rates Calculated select "Company Standard Bill Rates"

- I enter data into the next fields:
- page with name 'Pricing page' is opened

#### 15. Click Save button

- I click on 'Save' button
- 'Data saved successfully' warning message is displayed in 30 seconds

#### 16. Go the the Setup tab

- I click on 'Setup' tab
- page with name 'Setup page' is opened

#### 17. Navigate to the Proposals page

- I click on 'PROPOSALS' tab
- page with name 'Proposals list page' is opened

#### 18. Open the newly created proposal

- I select 'Default (shared' in the 'Proposal View' dropdown
- page with name 'Proposals list page' is opened
- I search '$uniqueProposalName' Proposal in Proposal List page
- page with name 'Proposals list page' is opened
- I click on 'first' proposal
- I wait for 30 seconds
- page with name 'Setup page' is opened
- text of 'Description' field equals '$uniqueProposalName'
- text of 'Planned Start' field equals '4/1/25'
- text of 'End' field equals '3/31/26'
- I verify 'Bid Manager' of Setup page is enabled, not required, drop down and has text 'Tech User'
- text of 'Your Company' field equals 'Regression Test'
- text of 'Leading Department' field equals 'US - New York'
- text of 'Client Customer sell-to' field equals 'Regression Test - Customer USD (RTC1)'

### Gherkin Excerpt

```gherkin
  Scenario: TC-Manufacturing-Proposal-002: Fill in Required Fields & Validate Save
    Given generate values and store into the variables:
      | $uniqueProposalName | TC-Manufacturing-Proposal-002 ${CUR_DATE,yyyy-MM-dd hh:mm} |
    And info: ---1. Clear values from fields: Estimated Project Start, Project End---
    When I enter data into the next fields:
      | 'Planned Start' field        |          |
      | 'End' field                  |          |
    And info: ---2. Click Save button without editing any fields ---
    And page with name 'Setup page' is opened
    And I hover on 'Save' button
    And I click on 'Save' button
    And 'Error' dialog is displayed
    And I hover on 'Error' dialog
    Then text of 'Error' dialog equals_-Warning-_:
    """
    Proposal Title * is required in Setup tab
    Estimated Project Start * is required in Setup tab
    End Date is required in Setup tab
    Your Company * is required in Setup tab
    Leading Site or Department * is required in Setup tab
    Client/Customer (Sell-to) * is required in Setup tab
    """
    And verify border-color css of 'Error' dialog is 'red'
    And verify border-color css of 'Description' field is 'red'
    And verify border-color css of 'Leading Company' field is 'red'_-Warning-_
    And verify border-color css of 'Leading Department' field is 'red'_-Warning-_
    And verify border-color css of 'Planned Start' field is 'red'
    And verify border-color css of 'End' field is 'red'
    And page with name 'Setup page' is opened
    And info: ---3. Fill Proposal Title field with a value between 1 and 255 characters.---
    When I enter data into the next fields:
      | 'Description' field   | $uniqueProposalName |
    And page with name 'Setup page' is opened
    And info: ---4. Fill Estimated Project Start field with a valid date (e.g., 4/1/25).---
    When I enter data into the next fields:
      | 'Planned Start' field | 4/1/25            |
    And info: ---5. Fill Project End field with an earlier date than start (e.g., 3/31/25).---
    And I click on 'End' field
    And page with name 'Setup page' is opened
    When I enter data into the next fields:
      | 'End' field                  | 3/31/25          |
    And verify border-color css of 'End' field is 'red'
    And info: ---6. Fill Planned End field with a later date (e.g., 3/31/26).---
    And page with name 'Setup page' is opened
    When I enter data into the next fields:
      | 'End' field                  | 3/31/26          |
    And info: ---7. Select Test Data Value from Your Company dropdown---
    When I enter data into the next fields:
      | 'Your Company' field            | Regression Test |
    And info: ---8. Click to expand the Leading Site or Department dropdown field---
    And I click on 'Leading Site' dropdown
    And 'Leading Site' menuItem contains items:
      | UK - Bristol |
      | US - New York  |
    And I click on 'Leading Site' dropdown
    And page with name 'Setup page' is opened
    And info: ---9. Select Test Data Value from Leading Site or Department dropdown---
    When I enter data into the next fields:
      | 'Leading Department' field            | US - New York   |
    And info: ---10. Select Test Data Value from Client/Customer (Sell-to) dropdown ---
    When I enter data into the next fields:
      | 'Client Customer sell-to' field | Regression Test - Customer USD |
    And page with name 'Setup page' is opened
    And info: ---11. Fill Project Goals or Remarks with text Test Data Value---
    When I enter data into the next fields:
      | 'Project Goals or Remarks' field | Test Project Description |
    And info: ---12. Click Save button.---
    And I click on 'Save' button
    And 'Data saved successfully' warning message is displayed in 30 seconds
    And info: ---13. Click the the Pricing tab---
    Then I click on 'Pricing' tab
    And page with name 'Pricing page' is opened
    And info: ---14. In the dropdown field How are Labor/Item Rates Calculated select "Company Standard Bill Rates"---
    When I enter data into the next fields:
      | 'How are Labor/Item Rates Calculated' field | Company Standard Bill Rates |
    And page with name 'Pricing page' is opened
    And info: ---15. Click Save button---
    And I click on 'Save' button
    And 'Data saved successfully' warning message is displayed in 30 seconds
    And info: ---16. Go the the Setup tab---
    Then I click on 'Setup' tab
    And page with name 'Setup page' is opened
    And info: ---17. Navigate to the Proposals page---
    Given I click on 'PROPOSALS' tab
    Then page with name 'Proposals list page' is opened
    And info: ---18. Open the newly created proposal---
    When I select 'Default (shared' in the 'Proposal View' dropdown
    Then page with name 'Proposals list page' is opened
#    Then text of 'Proposal View' dropdown equals 'View: Default (shared'
    Then I search '$uniqueProposalName' Proposal in Proposal List page
    Then page with name 'Proposals list page' is opened
    Then I click on 'first' proposal
    Then I wait for 30 seconds
    And page with name 'Setup page' is opened
    And text of 'Description' field equals '$uniqueProposalName'
    And text of 'Planned Start' field equals '4/1/25'
    And text of 'End' field equals '3/31/26'
    And I verify 'Bid Manager' of Setup page is enabled, not required, drop down and has text 'Tech User'
    And text of 'Your Company' field equals 'Regression Test'
    And text of 'Leading Department' field equals 'US - New York'
    And text of 'Client Customer sell-to' field equals 'Regression Test - Customer USD (RTC1)'

```

## Alternate mapped Selenium scenario 2

| Field | Value |
| --- | --- |
| Feature | 1. Setup Manufacturing Proposal |
| Scenario | TC-Manufacturing-Proposal-002: Fill in Required Fields & Validate Save |
| Tags | `@TCM-002 @RUN` |
| File | `imported/twentyfive-regtest/tests/src/test/resources/features/testfolder/twentyfive2_4_setup_manufacturing_proposal.feature:81` |

### Scenario Sections

#### Setup

- generate values and store into the variables:

#### 1. Clear values from fields: Estimated Project Start, Project End

- I enter data into the next fields:

#### 2. Click Save button without editing any fields

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
- verify border-color css of 'Planned Start' field is 'red'
- verify border-color css of 'End' field is 'red'
- page with name 'Setup page' is opened

#### 3. Fill Proposal Title field with a value between 1 and 255 characters.

- I enter data into the next fields:
- page with name 'Setup page' is opened

#### 4. Fill Estimated Project Start field with a valid date (e.g., 4/1/25).

- I enter data into the next fields:

#### 5. Fill Project End field with an earlier date than start (e.g., 3/31/25).

- I click on 'End' field
- page with name 'Setup page' is opened
- I enter data into the next fields:
- verify border-color css of 'End' field is 'red'

#### 6. Fill Planned End field with a later date (e.g., 3/31/26).

- page with name 'Setup page' is opened
- I enter data into the next fields:

#### 7. Select Test Data Value from Your Company dropdown

- I enter data into the next fields:

#### 8. Click to expand the Leading Site or Department dropdown field

- I click on 'Leading Site' dropdown
- if visible click on 'Reset Now' button
- 'Leading Site' menuItem contains items:
- I click on 'Leading Site' dropdown
- page with name 'Setup page' is opened

#### 9. Select Test Data Value from Leading Site or Department dropdown

- I enter data into the next fields:

#### 10. Select Test Data Value from Client/Customer (Sell-to) dropdown

- I enter data into the next fields:
- page with name 'Setup page' is opened

#### 11. Fill Project Goals or Remarks with text Test Data Value

- I enter data into the next fields:

#### 12. Click Save button.

- I click on 'Save' button
- I wait for 30 seconds

#### 13. Click the the Pricing tab

- I click on 'Pricing' tab
- page with name 'Pricing page' is opened

#### 14. In the dropdown field How are Labor/Item Rates Calculated select "Company Standard Bill Rates"

- I enter data into the next fields:
- page with name 'Pricing page' is opened

#### 15. Click Save button

- I click on 'Save' button
- 'Data saved successfully' warning message is displayed in 30 seconds

#### 16. Go the the Setup tab

- I click on 'Setup' tab
- page with name 'Setup page' is opened

#### 17. Navigate to the Proposals page

- I click on 'PROPOSALS' tab
- page with name 'Proposals list page' is opened

#### 18. Open the newly created proposal

- I select 'Default (shared' in the 'Proposal View' dropdown
- page with name 'Proposals list page' is opened
- I search '$uniqueProposalName' Proposal in Proposal List page
- page with name 'Proposals list page' is opened
- I wait for 7 seconds
- I click on 'first' proposal
- I wait for 23 seconds
- page with name 'Setup page' is opened
- text of 'Description' field equals '$uniqueProposalName'
- text of 'Planned Start' field equals '4/1/25'
- text of 'End' field equals '3/31/26'
- I verify 'Bid Manager' of Setup page is enabled, not required, drop down and has text 'Suket Suman'
- text of 'Your Company' field equals 'Regression Test'
- text of 'Leading Department' field equals 'US - New York'
- text of 'Client Customer sell-to' field equals 'Regression Test - Customer USD (RTC1)'

### Gherkin Excerpt

```gherkin
  Scenario: TC-Manufacturing-Proposal-002: Fill in Required Fields & Validate Save
    Given generate values and store into the variables:
      | $uniqueProposalName | TC-Manufacturing-Proposal-002 ${CUR_DATE,yyyy-MM-dd hh:mm} |
    And info: ---1. Clear values from fields: Estimated Project Start, Project End---
    When I enter data into the next fields:
      | 'Planned Start' field        |          |
      | 'End' field                  |          |
    And info: ---2. Click Save button without editing any fields ---
    And page with name 'Setup page' is opened
    And I hover on 'Save' button
    And I click on 'Save' button
    And 'Error' dialog is displayed
    And I hover on 'Error' dialog
    Then text of 'Error' dialog equals_-Warning-_:
    """
    Proposal Title * is required in Setup tab
    Estimated Project Start * is required in Setup tab
    End Date is required in Setup tab
    Your Company * is required in Setup tab
    Leading Site or Department * is required in Setup tab
    """
    And verify border-color css of 'Error' dialog is 'red'
    And verify border-color css of 'Description' field is 'red'
    And verify border-color css of 'Leading Company' field is 'red'_-Warning-_
    And verify border-color css of 'Leading Department' field is 'red'_-Warning-_
    And verify border-color css of 'Planned Start' field is 'red'
    And verify border-color css of 'End' field is 'red'
    And page with name 'Setup page' is opened
    And info: ---3. Fill Proposal Title field with a value between 1 and 255 characters.---
    When I enter data into the next fields:
      | 'Description' field   | $uniqueProposalName |
    And page with name 'Setup page' is opened
    And info: ---4. Fill Estimated Project Start field with a valid date (e.g., 4/1/25).---
    When I enter data into the next fields:
      | 'Planned Start' field | 4/1/25            |
    And info: ---5. Fill Project End field with an earlier date than start (e.g., 3/31/25).---
    And I click on 'End' field
    And page with name 'Setup page' is opened
    When I enter data into the next fields:
      | 'End' field                  | 3/31/25          |
    And verify border-color css of 'End' field is 'red'
    And info: ---6. Fill Planned End field with a later date (e.g., 3/31/26).---
    And page with name 'Setup page' is opened
    When I enter data into the next fields:
      | 'End' field                  | 3/31/26          |
    And info: ---7. Select Test Data Value from Your Company dropdown---
    When I enter data into the next fields:
      | 'Your Company' field            | Regression Test |
    And info: ---8. Click to expand the Leading Site or Department dropdown field---
    And I click on 'Leading Site' dropdown
    And if visible click on 'Reset Now' button
#    And I wait for 10 seconds
    And 'Leading Site' menuItem contains items:
      | UK - Bristol   |
      | US - New York  |
    And I click on 'Leading Site' dropdown
    And page with name 'Setup page' is opened
    And info: ---9. Select Test Data Value from Leading Site or Department dropdown---
    When I enter data into the next fields:
      | 'Leading Department' field            | US - New York   |
    And info: ---10. Select Test Data Value from Client/Customer (Sell-to) dropdown ---
    When I enter data into the next fields:
      | 'Client Customer sell-to' field | Regression Test - Customer USD |
    And page with name 'Setup page' is opened
    And info: ---11. Fill Project Goals or Remarks with text Test Data Value---
    When I enter data into the next fields:
      | 'Project Goals or Remarks' field | Test Project Description |
    And info: ---12. Click Save button.---
    And I click on 'Save' button
    And I wait for 30 seconds
#    And 'Data saved successfully' warning message is displayed in 30 seconds
#    And warning messages displayed in 30 seconds are:
#    """
#    Data saved successfully
#    """
    And info: ---13. Click the the Pricing tab---
    Then I click on 'Pricing' tab
    And page with name 'Pricing page' is opened
    And info: ---14. In the dropdown field How are Labor/Item Rates Calculated select "Company Standard Bill Rates"---
    When I enter data into the next fields:
      | 'How are Labor/Item Rates Calculated' field | Company Standard Bill Rates |
    And page with name 'Pricing page' is opened
    And info: ---15. Click Save button---
    And I click on 'Save' button
    And 'Data saved successfully' warning message is displayed in 30 seconds
    And info: ---16. Go the the Setup tab---
    Then I click on 'Setup' tab
    And page with name 'Setup page' is opened
    And info: ---17. Navigate to the Proposals page---
    Given I click on 'PROPOSALS' tab
    Then page with name 'Proposals list page' is opened
    And info: ---18. Open the newly created proposal---
    When I select 'Default (shared' in the 'Proposal View' dropdown
    Then page with name 'Proposals list page' is opened
#    Then text of 'Proposal View' dropdown equals 'View: Default (shared'
    Then I search '$uniqueProposalName' Proposal in Proposal List page
    Then page with name 'Proposals list page' is opened
    And I wait for 7 seconds
    Then I click on 'first' proposal
    Then I wait for 23 seconds
    And page with name 'Setup page' is opened
    And text of 'Description' field equals '$uniqueProposalName'
    And text of 'Planned Start' field equals '4/1/25'
    And text of 'End' field equals '3/31/26'
    And I verify 'Bid Manager' of Setup page is enabled, not required, drop down and has text 'Suket Suman'
    And text of 'Your Company' field equals 'Regression Test'
    And text of 'Leading Department' field equals 'US - New York'
    And text of 'Client Customer sell-to' field equals 'Regression Test - Customer USD (RTC1)'

```

---

_Generated by `scripts/generate-testcase-guides.py` from the RTA mapping and local feature files._
