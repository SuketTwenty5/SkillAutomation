# TC-Copy-Manufacturing-Proposal-002 - Setup Copy Prior Proposal

| Field | Value |
| --- | --- |
| Test ID | TC-Copy-Manufacturing-Proposal-002 |
| Title | Setup Copy Prior Proposal |
| RTA page | https://twenty5.atlassian.net/wiki/spaces/RTA/pages/301105153/TC-Copy-Manufacturing-Proposal-002+Setup+Copy+Prior+Proposal |
| Map source | `RTA_TEST_SUITE_FEATURE_MAP.md` |

## Run Instructions

When a consultant asks to run this test, use the default app URL unless they provide another URL:

```text
https://approuter-twenty5ipe-dev.cfapps.us10.hana.ondemand.com/#quote
```

For Claude Desktop, ask before launching Selenium Chrome with the default URL. If the user provides another URL, launch that URL instead.

```bash
scripts/start-debug-chrome.sh "https://approuter-twenty5ipe-dev.cfapps.us10.hana.ondemand.com/#quote"
scripts/run-twentyfive-test.sh @copyManufacturingProposalSetupAndCopyPriorProposal-2
```

Duplicate feature matches exist for this Confluence page. The tag command may run more than one matching scenario until the canonical feature file is confirmed.

## Default mapped Selenium scenario

| Field | Value |
| --- | --- |
| Feature | 10. Copy Prior Proposal |
| Scenario | TC-Copy-Manufacturing-Proposal-002: Setup & Copy Prior Proposal |
| Tags | `@copyManufacturingProposalSetupAndCopyPriorProposal-2 @RUN` |
| File | `imported/twentyfive-regtest/tests/src/test/resources/features/testfolder/copyPriorManufacturingProposal.feature:35` |

### Scenario Sections

#### Setup

- generate values and store into the variables:

#### 1. Click New to start creating new Proposal and verify user is directed to Setup tab

- I click on 'New' button
- page with name 'Setup page' is opened

#### 2. Fill Proposal Title with valid value (1-255 chars)  TC-Copy-Manufacturing-Proposal-Test

- I enter data into the next fields:
- page with name 'Setup page' is opened
- I enter data into the next fields:
- page with name 'Setup page' is opened

#### 3. Fill Estimated Project Start with valid date  1/1/27

- I enter data into the next fields:
- page with name 'Setup page' is opened

#### 4. Fill Planned End with later date  12/31/30

- I enter data into the next fields:
- page with name 'Setup page' is opened

#### 5. Select "Regression Test" from Your Company dropdown

- I enter data into the next fields:
- page with name 'Setup page' is opened

#### 6. Select "US - New York" from Leading Site/Department dropdown

- I enter data into the next fields:
- page with name 'Setup page' is opened

#### 7. Select "Regression Test - Customer USD" from Client/Customer dropdown

- I enter data into the next fields:
- page with name 'Setup page' is opened

#### 8. Fill Project Goals/Remarks with rich text, bullets & table (verify bold/italic, bullets, table supported)

- Fill Project Goals or Remarks with rich text, bullets, and table
- page with name 'Setup page' is opened

#### 9. Click View to review options and verify dropdown appears

- I click on 'Rich Text Editor View Dropdown' button

#### 10. Select Fullscreen and verify Project Goals/Remarks displayed fullscreen

- I click on 'Full Screen' option
- page with name 'Setup page' is opened

#### 11. Add new row in Remarks table under Remark 3 and verify row added

- I 'Insert row after' row 3 in the table inside the Rich Text Editor
- page with name 'Setup page' is opened

#### 12. Remove the added row and verify row removed

- I 'Delete row' row 4 in the table inside the Rich Text Editor
- page with name 'Setup page' is opened

#### 13. Exit Fullscreen  redirected to Setup tab with Project Goals/Remarks retained

- I click on 'Rich Text Editor View Dropdown' button
- I click on 'Normal' option
- page with name 'Setup page' is opened

#### 14. Click Save and verify success message "Data saved successfully"

- I click on 'Save' button
- 'Data saved successfully' warning message is displayed in 30 seconds

#### 15. Scroll to section "How do you want to Start your Project Plan"

- page with name 'Setup page' is opened

#### 16. Enable "Select a Prior Proposal" radio button and verify dropdown options appear

- I check 'Select a prior project' radio
- page with name 'Setup page' is opened

#### 17. Enter "Regression Test Proposal for Copy Scenario - Manufacturing (Do Not Edit)" and verify proposal available to copy

- I enter data into the next fields:
- page with name 'Setup page' is opened

#### 18. Select options: Copy WBS/work-packages, Tasks & sub-tasks, Transfer to US - New York, Reset dates (1/1/27-12/31/30)

- I check 'Reset dates to' radio
- I verify 'WBS or work-packages (9 elements)' of setup page is enabled, not required, check box and is checked
- I verify 'Tasks & sub-tasks (11 tasks)' of setup page is enabled, not required, check box and is checked
- I verify 'Transfer to US - New York' of setup page is enabled, not required, check box and is checked
- I verify 'Reset dates to ' of setup page is enabled, not required, radio buttons and is checked

#### 19. Click Copy and verify background message about BOE copy + success message "Data saved successfully"

- click on 'Copy' button
- page with name 'Setup page' is opened

#### 20. Click Cost Structure tab and verify navigation to Cost Structure

- I click on 'Cost Structure' tab
- page with name 'Cost Structure page' is opened

### Gherkin Excerpt

```gherkin
  Scenario: TC-Copy-Manufacturing-Proposal-002: Setup & Copy Prior Proposal
    Given generate values and store into the variables:
      | $uniqueProposalName | TC-Copy-Manufacturing-Proposal-Test ${CUR_DATE,yyyy-MM-dd hh:mm} |
    And info: ---1. Click New to start creating new Proposal and verify user is directed to Setup tab---
    When I click on 'New' button
    Then page with name 'Setup page' is opened
    And info: ---2. Fill Proposal Title with valid value (1-255 chars)  TC-Copy-Manufacturing-Proposal-Test---
    When I enter data into the next fields:
      | 'Description' field   | $uniqueProposalName |
    Then page with name 'Setup page' is opened
    When I enter data into the next fields:
      | 'Proposal Type' field | Regression Test Only - Manufacturing |
    And page with name 'Setup page' is opened
    And info: ---3. Fill Estimated Project Start with valid date  1/1/27---
    When I enter data into the next fields:
      | 'Planned Start' field | 1/1/27              |
    Then page with name 'Setup page' is opened
    And info: ---4. Fill Planned End with later date  12/31/30---
    When I enter data into the next fields:
      | 'End' field           | 12/31/30           |
    Then page with name 'Setup page' is opened
    And info: ---5. Select "Regression Test" from Your Company dropdown---
    When I enter data into the next fields:
      | 'Company' field              | Regression Test                |
    Then page with name 'Setup page' is opened
    And info: ---6. Select "US - New York" from Leading Site/Department dropdown---
    When I enter data into the next fields:
      | 'Leading Department' field | US - New York                  |
    Then page with name 'Setup page' is opened
    And info: ---7. Select "Regression Test - Customer USD" from Client/Customer dropdown---
    When I enter data into the next fields:
      | 'Client Customer sell-to' field | Regression Test - Customer USD |
    Then page with name 'Setup page' is opened
    And info: ---8. Fill Project Goals/Remarks with rich text, bullets & table (verify bold/italic, bullets, table supported)---
    And Fill Project Goals or Remarks with rich text, bullets, and table
    Then page with name 'Setup page' is opened
    And info: ---9. Click View to review options and verify dropdown appears---
    Then I click on 'Rich Text Editor View Dropdown' button
    And info: ---10. Select Fullscreen and verify Project Goals/Remarks displayed fullscreen---
    Then I click on 'Full Screen' option
    Then page with name 'Setup page' is opened
    And info: ---11. Add new row in Remarks table under Remark 3 and verify row added---
    And I 'Insert row after' row 3 in the table inside the Rich Text Editor
    Then page with name 'Setup page' is opened
    And info: ---12. Remove the added row and verify row removed---
    And I 'Delete row' row 4 in the table inside the Rich Text Editor
    Then page with name 'Setup page' is opened
    And info: ---13. Exit Fullscreen  redirected to Setup tab with Project Goals/Remarks retained---
    Then I click on 'Rich Text Editor View Dropdown' button
    Then I click on 'Normal' option
    Then page with name 'Setup page' is opened
    And info: ---14. Click Save and verify success message "Data saved successfully"---
    Then I click on 'Save' button
    And 'Data saved successfully' warning message is displayed in 30 seconds
    And info: ---15. Scroll to section "How do you want to Start your Project Plan"---
    Then page with name 'Setup page' is opened
    And info: ---16. Enable "Select a Prior Proposal" radio button and verify dropdown options appear---
    And I check 'Select a prior project' radio
    Then page with name 'Setup page' is opened
    And info: ---17. Enter "Regression Test Proposal for Copy Scenario - Manufacturing (Do Not Edit)" and verify proposal available to copy---
    When I enter data into the next fields:
      | 'Search Box' field | Regression Test Proposal for Copy Scenario - Manufacturing (Do Not Edit) |
    And page with name 'Setup page' is opened
    And info: ---18. Select options: Copy WBS/work-packages, Tasks & sub-tasks, Transfer to US - New York, Reset dates (1/1/27-12/31/30)---
    And I check 'Reset dates to' radio
    And I verify 'WBS or work-packages (9 elements)' of setup page is enabled, not required, check box and is checked
    And I verify 'Tasks & sub-tasks (11 tasks)' of setup page is enabled, not required, check box and is checked
    And I verify 'Transfer to US - New York' of setup page is enabled, not required, check box and is checked
    And I verify 'Reset dates to ' of setup page is enabled, not required, radio buttons and is checked
    And info: ---19. Click Copy and verify background message about BOE copy + success message "Data saved successfully"---
    And click on 'Copy' button
    And page with name 'Setup page' is opened
    And info: ---20. Click Cost Structure tab and verify navigation to Cost Structure---
    Then I click on 'Cost Structure' tab
    And page with name 'Cost Structure page' is opened

```

## Alternate mapped Selenium scenario 2

| Field | Value |
| --- | --- |
| Feature | 7. Copy Prior Proposal |
| Scenario | TC-Copy-Manufacturing-Proposal-002: Setup & Copy Prior Proposal |
| Tags | `@copyManufacturingProposalSetupAndCopyPriorProposal-2 @RUN` |
| File | `imported/twentyfive-regtest/tests/src/test/resources/features/testfolder/twentyfive2_4_copy_prior_manufacturing_proposal.feature:35` |

### Scenario Sections

#### Setup

- generate values and store into the variables:

#### 1. Click New to start creating new Proposal and verify user is directed to Setup tab

- I click on 'New' button
- page with name 'Setup page' is opened

#### 2. Fill Proposal Title with valid value (1-255 chars)  TC-Copy-Manufacturing-Proposal-Test

- I enter data into the next fields:
- page with name 'Setup page' is opened
- I enter data into the next fields:
- page with name 'Setup page' is opened

#### 3. Fill Estimated Project Start with valid date  1/1/27

- I enter data into the next fields:
- page with name 'Setup page' is opened

#### 4. Fill Planned End with later date  12/31/30

- I enter data into the next fields:
- page with name 'Setup page' is opened

#### 5. Select "Regression Test" from Your Company dropdown

- I enter data into the next fields:
- page with name 'Setup page' is opened

#### 6. Select "US - New York" from Leading Site/Department dropdown

- I enter data into the next fields:
- page with name 'Setup page' is opened

#### 7. Select "Regression Test - Customer USD" from Client/Customer dropdown

- I enter data into the next fields:
- page with name 'Setup page' is opened

#### 8. Fill Project Goals/Remarks with rich text, bullets & table (verify bold/italic, bullets, table supported)

- Fill Project Goals or Remarks with rich text, bullets, and table
- page with name 'Setup page' is opened

#### 9. Click View to review options and verify dropdown appears

- I click on 'Rich Text Editor View Dropdown' button

#### 10. Select Fullscreen and verify Project Goals/Remarks displayed fullscreen

- I click on 'Full Screen' option
- page with name 'Setup page' is opened

#### 11. Add new row in Remarks table under Remark 3 and verify row added

- I 'Insert row after' row 3 in the table inside the Rich Text Editor
- page with name 'Setup page' is opened

#### 12. Remove the added row and verify row removed

- I 'Delete row' row 4 in the table inside the Rich Text Editor
- page with name 'Setup page' is opened

#### 13. Exit Fullscreen  redirected to Setup tab with Project Goals/Remarks retained

- I click on 'Rich Text Editor View Dropdown' button
- I click on 'Normal' option
- page with name 'Setup page' is opened

#### 14. Click Save and verify success message "Data saved successfully"

- I click on 'Save' button
- 'Data saved successfully' warning message is displayed in 30 seconds

#### 15. Scroll to section "How do you want to Start your Project Plan"

- page with name 'Setup page' is opened

#### 16. Enable "Select a Prior Proposal" radio button and verify dropdown options appear

- I check 'Select a prior project' radio
- page with name 'Setup page' is opened

#### 17. Enter "Regression Test Proposal for Copy Scenario - Manufacturing (Do Not Edit)" and verify proposal available to copy

- I enter data into the next fields:
- page with name 'Setup page' is opened

#### 18. Select options: Copy WBS/work-packages, Tasks & sub-tasks, Transfer to US - New York, Reset dates (1/1/27-12/31/30)

- I check 'Reset dates to' radio
- I verify 'WBS or work-packages (9 elements)' of setup page is enabled, not required, check box and is checked
- I verify 'Tasks & sub-tasks (11 tasks)' of setup page is enabled, not required, check box and is checked
- I verify 'Transfer to US - New York' of setup page is enabled, not required, check box and is checked
- I verify 'Reset dates to ' of setup page is enabled, not required, radio buttons and is checked

#### 19. Click Copy and verify background message about BOE copy + success message "Data saved successfully"

- click on 'Copy' button
- page with name 'Setup page' is opened

#### 20. Click Cost Structure tab and verify navigation to Cost Structure

- I click on 'Cost Structure' tab
- page with name 'Cost Structure page' is opened

### Gherkin Excerpt

```gherkin
  Scenario: TC-Copy-Manufacturing-Proposal-002: Setup & Copy Prior Proposal
    Given generate values and store into the variables:
      | $uniqueProposalName | TC-Copy-Manufacturing-Proposal-Test ${CUR_DATE,yyyy-MM-dd hh:mm} |
    And info: ---1. Click New to start creating new Proposal and verify user is directed to Setup tab---
    When I click on 'New' button
    Then page with name 'Setup page' is opened
    And info: ---2. Fill Proposal Title with valid value (1-255 chars)  TC-Copy-Manufacturing-Proposal-Test---
    When I enter data into the next fields:
      | 'Description' field   | $uniqueProposalName |
    Then page with name 'Setup page' is opened
    When I enter data into the next fields:
      | 'Proposal Type' field | Regression Test Only - Manufacturing |
    And page with name 'Setup page' is opened
    And info: ---3. Fill Estimated Project Start with valid date  1/1/27---
    When I enter data into the next fields:
      | 'Planned Start' field | 1/1/27              |
    Then page with name 'Setup page' is opened
    And info: ---4. Fill Planned End with later date  12/31/30---
    When I enter data into the next fields:
      | 'End' field           | 12/31/30           |
    Then page with name 'Setup page' is opened
    And info: ---5. Select "Regression Test" from Your Company dropdown---
    When I enter data into the next fields:
      | 'Company' field              | Regression Test                |
    Then page with name 'Setup page' is opened
    And info: ---6. Select "US - New York" from Leading Site/Department dropdown---
    When I enter data into the next fields:
      | 'Leading Department' field | US - New York                  |
    Then page with name 'Setup page' is opened
    And info: ---7. Select "Regression Test - Customer USD" from Client/Customer dropdown---
    When I enter data into the next fields:
      | 'Client Customer sell-to' field | Regression Test - Customer USD |
    Then page with name 'Setup page' is opened
    And info: ---8. Fill Project Goals/Remarks with rich text, bullets & table (verify bold/italic, bullets, table supported)---
    And Fill Project Goals or Remarks with rich text, bullets, and table
    Then page with name 'Setup page' is opened
    And info: ---9. Click View to review options and verify dropdown appears---
    Then I click on 'Rich Text Editor View Dropdown' button
    And info: ---10. Select Fullscreen and verify Project Goals/Remarks displayed fullscreen---
    Then I click on 'Full Screen' option
    Then page with name 'Setup page' is opened
    And info: ---11. Add new row in Remarks table under Remark 3 and verify row added---
    And I 'Insert row after' row 3 in the table inside the Rich Text Editor
    Then page with name 'Setup page' is opened
    And info: ---12. Remove the added row and verify row removed---
    And I 'Delete row' row 4 in the table inside the Rich Text Editor
    Then page with name 'Setup page' is opened
    And info: ---13. Exit Fullscreen  redirected to Setup tab with Project Goals/Remarks retained---
    Then I click on 'Rich Text Editor View Dropdown' button
    Then I click on 'Normal' option
    Then page with name 'Setup page' is opened
    And info: ---14. Click Save and verify success message "Data saved successfully"---
    Then I click on 'Save' button
    And 'Data saved successfully' warning message is displayed in 30 seconds
    And info: ---15. Scroll to section "How do you want to Start your Project Plan"---
    Then page with name 'Setup page' is opened
    And info: ---16. Enable "Select a Prior Proposal" radio button and verify dropdown options appear---
    And I check 'Select a prior project' radio
    Then page with name 'Setup page' is opened
    And info: ---17. Enter "Regression Test Proposal for Copy Scenario - Manufacturing (Do Not Edit)" and verify proposal available to copy---
    When I enter data into the next fields:
      | 'Search Box' field | Regression Test Proposal for Copy Scenario - Manufacturing (Do Not Edit) |
    And page with name 'Setup page' is opened
    And info: ---18. Select options: Copy WBS/work-packages, Tasks & sub-tasks, Transfer to US - New York, Reset dates (1/1/27-12/31/30)---
    And I check 'Reset dates to' radio
    And I verify 'WBS or work-packages (9 elements)' of setup page is enabled, not required, check box and is checked
    And I verify 'Tasks & sub-tasks (11 tasks)' of setup page is enabled, not required, check box and is checked
    And I verify 'Transfer to US - New York' of setup page is enabled, not required, check box and is checked
    And I verify 'Reset dates to ' of setup page is enabled, not required, radio buttons and is checked
    And info: ---19. Click Copy and verify background message about BOE copy + success message "Data saved successfully"---
    And click on 'Copy' button
    And page with name 'Setup page' is opened
    And info: ---20. Click Cost Structure tab and verify navigation to Cost Structure---
    Then I click on 'Cost Structure' tab
    And page with name 'Cost Structure page' is opened

```

---

_Generated by `scripts/generate-testcase-guides.py` from the RTA mapping and local feature files._
