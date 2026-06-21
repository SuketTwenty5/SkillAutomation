# TC-Copy-Manufacturing-Proposal-001 - Search Open Proposal

| Field | Value |
| --- | --- |
| Test ID | TC-Copy-Manufacturing-Proposal-001 |
| Title | Search Open Proposal |
| RTA page | https://twenty5.atlassian.net/wiki/spaces/RTA/pages/316309505/TC-Copy-Manufacturing-Proposal-001+Search+Open+Proposal |
| Map source | `RTA_TEST_SUITE_FEATURE_MAP.md` |

## Run Instructions

When a consultant asks to run this test, use the default app URL unless they provide another URL:

```text
https://approuter-twenty5ipe-dev.cfapps.us10.hana.ondemand.com/#quote
```

For Claude Desktop, ask before launching Selenium Chrome with the default URL. If the user provides another URL, launch that URL instead.

```bash
scripts/start-debug-chrome.sh "https://approuter-twenty5ipe-dev.cfapps.us10.hana.ondemand.com/#quote"
scripts/run-twentyfive-test.sh @copyManufacturingProposalSearchOpenProposal-1
```

Duplicate feature matches exist for this Confluence page. The tag command may run more than one matching scenario until the canonical feature file is confirmed.

## Default mapped Selenium scenario

| Field | Value |
| --- | --- |
| Feature | 10. Copy Prior Proposal |
| Scenario | TC-Copy-Manufacturing-Proposal-001: Search & Open Proposal |
| Tags | `@copyManufacturingProposalSearchOpenProposal-1 @START` |
| File | `imported/twentyfive-regtest/tests/src/test/resources/features/testfolder/copyPriorManufacturingProposal.feature:5` |

### Scenario Sections

#### Setup

- open site
- I perform login
- page with name 'Main page' is opened

#### 1. Navigate to Proposals and verify environment is accessible

- I click on 'PROPOSALS' tab
- page with name 'Proposals list page' is opened

#### 2. Click + to add a filter and verify that dropdown appears

- I if visible click on 'First Add Filter' button
- page with name 'Proposals list page' is opened
- I click on 'First Filter' dropdown
- 'First Filter' dropbox is displayed

#### 3. Type in Proposal and select, verify Proposal is available for selection and second filter field appears

- I enter text 'Proposal' in the 'First Filter' combobox
- I click on 'Proposal' option
- page with name 'Proposals list page' is opened

#### 4. Type in "Regression Test Proposal for Copy Scenario - Manufacturing (Do Not Edit)" and verify filter is applied where Proposal is displayed

- I enter text 'Regression Test Proposal for Copy Scenario - Manufacturing (Do Not Edit)' in the 'First Filter' input
- page with name 'Proposals list page' is opened

#### 5. Click on the Proposal name to open Proposal and verify Setup tab is opened without errors

- I click on 'Regression Test Proposal for Copy Scenario - Manufacturing' proposal
- page with name 'Setup page' is opened

#### 6. Click on Cost Structure tab and verify Cost Structure tab is opened

- I click on 'Cost Structure' tab
- page with name 'Cost Structure page' is opened

#### 7. Click on Proposals to navigate back to Proposals screen and verify user is redirected

- I click on 'PROPOSALS' tab
- page with name 'Proposals list page' is opened

### Gherkin Excerpt

```gherkin
  Scenario: TC-Copy-Manufacturing-Proposal-001: Search & Open Proposal
    Given open site
    And I perform login
    Then page with name 'Main page' is opened
    And info: ---1. Navigate to Proposals and verify environment is accessible---
    Given I click on 'PROPOSALS' tab
    Then page with name 'Proposals list page' is opened
    And info: ---2. Click + to add a filter and verify that dropdown appears---
    When I if visible click on 'First Add Filter' button
    Then page with name 'Proposals list page' is opened
    And I click on 'First Filter' dropdown
    And 'First Filter' dropbox is displayed
    And info: ---3. Type in Proposal and select, verify Proposal is available for selection and second filter field appears---
    And I enter text 'Proposal' in the 'First Filter' combobox
    And I click on 'Proposal' option
    Then page with name 'Proposals list page' is opened
    And info: ---4. Type in "Regression Test Proposal for Copy Scenario - Manufacturing (Do Not Edit)" and verify filter is applied where Proposal is displayed---
    And I enter text 'Regression Test Proposal for Copy Scenario - Manufacturing (Do Not Edit)' in the 'First Filter' input
    Then page with name 'Proposals list page' is opened
    And info: ---5. Click on the Proposal name to open Proposal and verify Setup tab is opened without errors---
    And I click on 'Regression Test Proposal for Copy Scenario - Manufacturing' proposal
    Then page with name 'Setup page' is opened
    And info: ---6. Click on Cost Structure tab and verify Cost Structure tab is opened---
    And I click on 'Cost Structure' tab
    Then page with name 'Cost Structure page' is opened
    And info: ---7. Click on Proposals to navigate back to Proposals screen and verify user is redirected---
    And I click on 'PROPOSALS' tab
    Then page with name 'Proposals list page' is opened

```

## Alternate mapped Selenium scenario 2

| Field | Value |
| --- | --- |
| Feature | 7. Copy Prior Proposal |
| Scenario | TC-Copy-Manufacturing-Proposal-001: Search & Open Proposal |
| Tags | `@copyManufacturingProposalSearchOpenProposal-1 @START` |
| File | `imported/twentyfive-regtest/tests/src/test/resources/features/testfolder/twentyfive2_4_copy_prior_manufacturing_proposal.feature:5` |

### Scenario Sections

#### Setup

- open site
- I perform Mfg 2.4 login
- page with name 'Main page' is opened

#### 1. Navigate to Proposals and verify environment is accessible

- I click on 'PROPOSALS' tab
- page with name 'Proposals list page' is opened

#### 2. Click + to add a filter and verify that dropdown appears

- I if visible click on 'First Add Filter' button
- page with name 'Proposals list page' is opened
- I click on 'First Filter' dropdown
- 'First Filter' dropbox is displayed

#### 3. Type in Proposal and select, verify Proposal is available for selection and second filter field appears

- I enter text 'Proposal' in the 'First Filter' combobox
- I click on 'Proposal' option
- page with name 'Proposals list page' is opened

#### 4. Type in "Regression Test Proposal for Copy Scenario - Manufacturing (Do Not Edit)" and verify filter is applied where Proposal is displayed

- I enter text 'Regression Test Proposal for Copy Scenario - Manufacturing (Do Not Edit)' in the 'First Filter' input
- page with name 'Proposals list page' is opened

#### 5. Click on the Proposal name to open Proposal and verify Setup tab is opened without errors

- I click on 'Regression Test Proposal for Copy Scenario - Manufacturing' proposal
- page with name 'Setup page' is opened

#### 6. Click on Cost Structure tab and verify Cost Structure tab is opened

- I click on 'Cost Structure' tab
- page with name 'Cost Structure page' is opened

#### 7. Click on Proposals to navigate back to Proposals screen and verify user is redirected

- I click on 'PROPOSALS' tab
- page with name 'Proposals list page' is opened

### Gherkin Excerpt

```gherkin
  Scenario: TC-Copy-Manufacturing-Proposal-001: Search & Open Proposal
    Given open site
    And I perform Mfg 2.4 login
    Then page with name 'Main page' is opened
    And info: ---1. Navigate to Proposals and verify environment is accessible---
    Given I click on 'PROPOSALS' tab
    Then page with name 'Proposals list page' is opened
    And info: ---2. Click + to add a filter and verify that dropdown appears---
    When I if visible click on 'First Add Filter' button
    Then page with name 'Proposals list page' is opened
    And I click on 'First Filter' dropdown
    And 'First Filter' dropbox is displayed
    And info: ---3. Type in Proposal and select, verify Proposal is available for selection and second filter field appears---
    And I enter text 'Proposal' in the 'First Filter' combobox
    And I click on 'Proposal' option
    Then page with name 'Proposals list page' is opened
    And info: ---4. Type in "Regression Test Proposal for Copy Scenario - Manufacturing (Do Not Edit)" and verify filter is applied where Proposal is displayed---
    And I enter text 'Regression Test Proposal for Copy Scenario - Manufacturing (Do Not Edit)' in the 'First Filter' input
    Then page with name 'Proposals list page' is opened
    And info: ---5. Click on the Proposal name to open Proposal and verify Setup tab is opened without errors---
    And I click on 'Regression Test Proposal for Copy Scenario - Manufacturing' proposal
    Then page with name 'Setup page' is opened
    And info: ---6. Click on Cost Structure tab and verify Cost Structure tab is opened---
    And I click on 'Cost Structure' tab
    Then page with name 'Cost Structure page' is opened
    And info: ---7. Click on Proposals to navigate back to Proposals screen and verify user is redirected---
    And I click on 'PROPOSALS' tab
    Then page with name 'Proposals list page' is opened

```

---

_Generated by `scripts/generate-testcase-guides.py` from the RTA mapping and local feature files._
