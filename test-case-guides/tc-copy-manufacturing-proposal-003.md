# TC-Copy-Manufacturing-Proposal-003 - Perform Cost Structure Estimate Validation

| Field | Value |
| --- | --- |
| Test ID | TC-Copy-Manufacturing-Proposal-003 |
| Title | Perform Cost Structure Estimate Validation |
| RTA page | https://twenty5.atlassian.net/wiki/spaces/RTA/pages/301105320/TC-Copy-Manufacturing-Proposal-003+Perform+Cost+Structure+Estimate+Validation |
| Map source | `RTA_TEST_SUITE_FEATURE_MAP.md` |

## Run Instructions

When a consultant asks to run this test, use the default app URL unless they provide another URL:

```text
https://approuter-twenty5ipe-dev.cfapps.us10.hana.ondemand.com/#quote
```

For Claude Desktop, ask before launching Selenium Chrome with the default URL. If the user provides another URL, launch that URL instead.

```bash
scripts/start-debug-chrome.sh "https://approuter-twenty5ipe-dev.cfapps.us10.hana.ondemand.com/#quote"
scripts/run-twentyfive-test.sh @copyManufacturingProposalPerformCostStructureValidation-3
```

Duplicate feature matches exist for this Confluence page. The tag command may run more than one matching scenario until the canonical feature file is confirmed.

## Default mapped Selenium scenario

| Field | Value |
| --- | --- |
| Feature | 10. Copy Prior Proposal |
| Scenario | TC-Copy-Manufacturing-Proposal-003: Perform Cost Structure [Estimate] Validation |
| Tags | `@copyManufacturingProposalPerformCostStructureValidation-3 @END` |
| File | `imported/twentyfive-regtest/tests/src/test/resources/features/testfolder/copyPriorManufacturingProposal.feature:112` |

### Scenario Sections

#### 1. Validate widget values  Total Revenue = USD 0, Total Cost = USD 0, Margin = 0%

- page with name 'Cost Structure page' is opened
- text of 'Total Revenue' widget equals 'USD 0'
- text of 'Total Cost' widget equals 'USD 0'
- text of 'Margin' widget equals '0 %'

#### 2. Verify selected View = "REG Test - Manufacturing Proposal Copy Validation" (if not, select from dropdown; if unavailable  error)

- I select 'REG Test - Manufacturing Proposal Copy Validation' in the 'WBS View' dropdown
- I wait for 10 seconds
- I click on 'Save' button
- text of 'WBS View' dropdown equals 'View: REG Test - Manufacturing Proposal Copy Validation'
- page with name 'Cost Structure page' is opened

#### 3. Verify WBS grid  Main row present, columns = Cost Structure, WP, Type, WP Owner, Work Package, Start, End, Total Cost w/ Risk, Labor Cost, Labor Hours, Material Cost, Other Cost

- number of rows in 'WBS' table equals 2
- 'WBS' table contain row with following data:
- I wait for 5 seconds
- I should see the following columns in the 'WBS' table:

#### 4. Click gear  next to "Manual - TC-Copy-Manufacturing-Proposal-001: Setup & Copy Prior Proposal"  verify menu options: Expand/Collapse, Import Below, Copy Cost to DTC, Reset Min/Max Costs, Re-number WBS Below, Reschedule WBS/Task, Generate Cost Assignments

- I hover 'Cost Structure' cell in table 'WBS' table and click  for row with data:
- 'Gear' menuItems contains items:

#### 5. Hover Expand/Collapse  verify options: Collapse All, Expand, Expand All

- I hover on 'Expand and Collapse' menuItem
- 'Gear' menuItems contains items:

#### 6. Select Expand All  verify full Cost Structure expanded

- I click on 'Expand All' menuItem
- page with name 'Cost Structure page' is opened
- number of rows in 'WBS' table equals 21

#### 7. Click gear  again for "Manual - TC-Copy-Manufacturing-Proposal-001: Setup & Copy Prior Proposal"  verify overlay displayed

- I hover 'Cost Structure' cell in table 'WBS' table and click  for row with data:
- page with name 'Cost Structure page' is opened
- 'Gear' menuItems contains items:

#### 8. Hover Expand/Collapse  verify overlay still displayed

- I hover on 'Expand and Collapse' menuItem
- 'Gear' menuItems contains items:

#### 9. Select Collapse All  verify Cost Structure collapsed

- I click on 'Collapse All' menuItem
- page with name 'Cost Structure page' is opened
- number of rows in 'WBS' table equals 2

#### 10. Click gear  next to "1 Overall Proposal"  verify menu options: Expand/Collapse, Add WBS Node, Copy WBS, Delete WBS, Import Below, Copy Cost to DTC, Reset Min/Max Costs, Re-number WBS Below, Reschedule WBS/Task, Generate Cost Assignments, Cascade

- I hover 'Cost Structure' cell in table 'WBS' table and click  for row with data:
- 'Gear' menuItems contains items:

#### 11. Click away on screen  verify overlay closed

- I click on 'Cost Structure' tab
- page with name 'Cost Structure page' is opened
- 'Gear' menuItems is not displayed

#### 12. Click + next to "1 Overall Proposal"  verify WBS expanded 1 level with:

- I click on '1 Overall Proposal' expander
- page with name 'Cost Structure page' is opened
- the cell value of the 'WP' column of the 'WBS' table is checked for a row with the following data:
- the cell value of the 'WP' column of the 'WBS' table is checked for a row with the following data:
- the cell value of the 'WP' column of the 'WBS' table is checked for a row with the following data:
- the cell value of the 'WP' column of the 'WBS' table is checked for a row with the following data:
- the cell value of the 'WP' column of the 'WBS' table is checked for a row with the following data:
- the cell value of the 'WP' column of the 'WBS' table is checked for a row with the following data:
- the cell value of the 'WP' column of the 'WBS' table is checked for a row with the following data:
- the cell value of the 'WP' column of the 'WBS' table is checked for a row with the following data:
- 'WBS' table contain row with following data:
- 'WBS' table contain row with following data:
- 'WBS' table contain row with following data:
- 'WBS' table contain row with following data:
- 'WBS' table contain row with following data:
- 'WBS' table contain row with following data:
- 'WBS' table contain row with following data:
- 'WBS' table contain row with following data:

### Gherkin Excerpt

```gherkin
  Scenario: TC-Copy-Manufacturing-Proposal-003: Perform Cost Structure [Estimate] Validation
    And info: ---1. Validate widget values  Total Revenue = USD 0, Total Cost = USD 0, Margin = 0%---
    And page with name 'Cost Structure page' is opened
    And text of 'Total Revenue' widget equals 'USD 0'
    And text of 'Total Cost' widget equals 'USD 0'
    And text of 'Margin' widget equals '0 %'
    And info: ---2. Verify selected View = "REG Test - Manufacturing Proposal Copy Validation" (if not, select from dropdown; if unavailable  error)---
    When I select 'REG Test - Manufacturing Proposal Copy Validation' in the 'WBS View' dropdown
    And I wait for 10 seconds
    And I click on 'Save' button
    Then text of 'WBS View' dropdown equals 'View: REG Test - Manufacturing Proposal Copy Validation'
    And page with name 'Cost Structure page' is opened
    And info: ---3. Verify WBS grid  Main row present, columns = Cost Structure, WP, Type, WP Owner, Work Package, Start, End, Total Cost w/ Risk, Labor Cost, Labor Hours, Material Cost, Other Cost---
    And number of rows in 'WBS' table equals 2
    And 'WBS' table contain row with following data:
      | Cost Structure        | $uniqueProposalName     |
    And I wait for 5 seconds
    Then I should see the following columns in the 'WBS' table:
        | Cost Structure   |
        | WP              |
        | Type            |
        | WP Owner        |
        | Work Package    |
        | Start           |
        | End             |
        | Total Cost with Risk Cost |
        | Labor Cost     |
        | Labor Hours    |
        | Material Cost  |
        | Other Cost     |
    And info: ---4. Click gear  next to "Manual - TC-Copy-Manufacturing-Proposal-001: Setup & Copy Prior Proposal"  verify menu options: Expand/Collapse, Import Below, Copy Cost to DTC, Reset Min/Max Costs, Re-number WBS Below, Reschedule WBS/Task, Generate Cost Assignments---
    And I hover 'Cost Structure' cell in table 'WBS' table and click  for row with data:
      | Cost Structure | $uniqueProposalName |
    And 'Gear' menuItems contains items:
        | Expand/Collapse        |
        | Import Below          |
        | Copy Cost to DTC      |
        | Reset Min/Max Costs   |
        | Re-number WBS Below   |
        | Reschedule WBS or Task   |
        | Generate Cost Assignments |
    And info: ---5. Hover Expand/Collapse  verify options: Collapse All, Expand, Expand All---
    And I hover on 'Expand and Collapse' menuItem
    And 'Gear' menuItems contains items:
        | Collapse All |
        | Expand       |
        | Expand All   |
    And info: ---6. Select Expand All  verify full Cost Structure expanded---
    When I click on 'Expand All' menuItem
    And page with name 'Cost Structure page' is opened
    Then number of rows in 'WBS' table equals 21
    And info: ---7. Click gear  again for "Manual - TC-Copy-Manufacturing-Proposal-001: Setup & Copy Prior Proposal"  verify overlay displayed---
    And I hover 'Cost Structure' cell in table 'WBS' table and click  for row with data:
      | Cost Structure | $uniqueProposalName |
    And page with name 'Cost Structure page' is opened
    And 'Gear' menuItems contains items:
      | Expand/Collapse        |
      | Import Below          |
      | Copy Cost to DTC      |
      | Reset Min/Max Costs   |
      | Re-number WBS Below   |
      | Reschedule WBS or Task   |
      | Generate Cost Assignments |
    And info: ---8. Hover Expand/Collapse  verify overlay still displayed---
    And I hover on 'Expand and Collapse' menuItem
    And 'Gear' menuItems contains items:
      | Collapse All |
      | Expand       |
      | Expand All   |
    And info: ---9. Select Collapse All  verify Cost Structure collapsed---
    When I click on 'Collapse All' menuItem
    And page with name 'Cost Structure page' is opened
    Then number of rows in 'WBS' table equals 2
    And info: ---10. Click gear  next to "1 Overall Proposal"  verify menu options: Expand/Collapse, Add WBS Node, Copy WBS, Delete WBS, Import Below, Copy Cost to DTC, Reset Min/Max Costs, Re-number WBS Below, Reschedule WBS/Task, Generate Cost Assignments, Cascade---
    And I hover 'Cost Structure' cell in table 'WBS' table and click  for row with data:
      | Cost Structure | 1 Overall Proposal |
    And 'Gear' menuItems contains items:
      | Expand/Collapse        |
      | Add WBS Node          |
      | Copy WBS              |
      | Delete WBS            |
      | Import Below          |
      | Copy Cost to DTC      |
      | Reset Min/Max Costs   |
      | Re-number WBS Below   |
    And info: ---11. Click away on screen  verify overlay closed---
    When I click on 'Cost Structure' tab
    Then page with name 'Cost Structure page' is opened
    And 'Gear' menuItems is not displayed
    And info: ---12. Click + next to "1 Overall Proposal"  verify WBS expanded 1 level with:---
    And I click on '1 Overall Proposal' expander
    And page with name 'Cost Structure page' is opened
    And the cell value of the 'WP' column of the 'WBS' table is checked for a row with the following data:
      | Cost Structure | 1.1 Engineering |
    And the cell value of the 'WP' column of the 'WBS' table is checked for a row with the following data:
      | Cost Structure | 1.2 Project Management     |
    And the cell value of the 'WP' column of the 'WBS' table is checked for a row with the following data:
      | Cost Structure | 1.3 Decommissioning        |
    And the cell value of the 'WP' column of the 'WBS' table is checked for a row with the following data:
      | Cost Structure | 1.4 Maintenance & Support |
    And the cell value of the 'WP' column of the 'WBS' table is checked for a row with the following data:
      | Cost Structure | 1.5 Launch & Recovery  |
    And the cell value of the 'WP' column of the 'WBS' table is checked for a row with the following data:
      | Cost Structure | 1.6 Operational Readiness |
    And the cell value of the 'WP' column of the 'WBS' table is checked for a row with the following data:
      | Cost Structure | 1.7 Installation & Support |
    And the cell value of the 'WP' column of the 'WBS' table is checked for a row with the following data:
      | Cost Structure | 1.8 Production |
    And 'WBS' table contain row with following data:
      | Cost Structure | 1.1 Engineering |
      | Type           | Estimate                  |
    And 'WBS' table contain row with following data:
      | Cost Structure | 1.2 Project Management     |
      | Type           | Estimate                    |
    And 'WBS' table contain row with following data:
      | Cost Structure | 1.3 Decommissioning        |
      | Type           | Estimate                    |
    And 'WBS' table contain row with following data:
      | Cost Structure | 1.4 Maintenance & Support |
      | Type           | Estimate                  |
    And 'WBS' table contain row with following data:
      | Cost Structure | 1.5 Launch & Recovery  |
      | Type           | Estimate               |
    And 'WBS' table contain row with following data:
      | Cost Structure | 1.6 Operational Readiness |
      | Type           | Estimate                   |
    And 'WBS' table contain row with following data:
      | Cost Structure | 1.7 Installation & Support |
      | Type           | Estimate                    |
    And 'WBS' table contain row with following data:
      | Cost Structure | 1.8 Production |
      | Type           | Estimate        |
```

## Alternate mapped Selenium scenario 2

| Field | Value |
| --- | --- |
| Feature | 7. Copy Prior Proposal |
| Scenario | TC-Copy-Manufacturing-Proposal-003: Perform Cost Structure [Estimate] Validation |
| Tags | `@copyManufacturingProposalPerformCostStructureValidation-3 @END` |
| File | `imported/twentyfive-regtest/tests/src/test/resources/features/testfolder/twentyfive2_4_copy_prior_manufacturing_proposal.feature:112` |

### Scenario Sections

#### 1. Validate widget values  Total Revenue = USD 0, Total Cost = USD 0, Margin = 0%

- page with name 'Cost Structure page' is opened
- text of 'Total Revenue' widget equals 'USD 0'
- text of 'Total Cost' widget equals 'USD 0'
- text of 'Margin' widget equals '0 %'

#### 2. Verify selected View = "REG Test - Manufacturing Proposal Copy Validation" (if not, select from dropdown; if unavailable  error)

- I select 'REG Test - Manufacturing Proposal Copy Validation' in the 'WBS View' dropdown
- I wait for 10 seconds
- I click on 'Save' button
- text of 'WBS View' dropdown equals 'View: REG Test - Manufacturing Proposal Copy Validation'
- page with name 'Cost Structure page' is opened

#### 3. Verify WBS grid  Main row present, columns = Cost Structure, WP, Type, WP Owner, Work Package, Start, End, Total Cost w/ Risk, Labor Cost, Labor Hours, Material Cost, Other Cost

- number of rows in 'WBS' table equals 2
- 'WBS' table contain row with following data:
- I wait for 5 seconds
- I should see the following columns in the 'WBS' table:

#### 4. Click gear  next to "Manual - TC-Copy-Manufacturing-Proposal-001: Setup & Copy Prior Proposal"  verify menu options: Expand/Collapse, Import Below, Copy Cost to DTC, Reset Min/Max Costs, Re-number WBS Below, Reschedule WBS/Task, Generate Cost Assignments

- I hover 'Cost Structure' cell in table 'WBS' table and click  for row with data:
- 'Gear' menuItems contains items:

#### 5. Hover Expand/Collapse  verify options: Collapse All, Expand, Expand All

- I hover on 'Expand and Collapse' menuItem
- 'Gear' menuItems contains items:

#### 6. Select Expand All  verify full Cost Structure expanded

- I click on 'Expand All' menuItem
- page with name 'Cost Structure page' is opened
- number of rows in 'WBS' table equals 21

#### 7. Click gear  again for "Manual - TC-Copy-Manufacturing-Proposal-001: Setup & Copy Prior Proposal"  verify overlay displayed

- I hover 'Cost Structure' cell in table 'WBS' table and click  for row with data:
- page with name 'Cost Structure page' is opened
- 'Gear' menuItems contains items:

#### 8. Hover Expand/Collapse  verify overlay still displayed

- I hover on 'Expand and Collapse' menuItem
- 'Gear' menuItems contains items:

#### 9. Select Collapse All  verify Cost Structure collapsed

- I click on 'Collapse All' menuItem
- page with name 'Cost Structure page' is opened
- number of rows in 'WBS' table equals 2

#### 10. Click gear  next to "1 Overall Proposal"  verify menu options: Expand/Collapse, Add WBS Node, Copy WBS, Delete WBS, Import Below, Copy Cost to DTC, Reset Min/Max Costs, Re-number WBS Below, Reschedule WBS/Task, Generate Cost Assignments, Cascade

- I hover 'Cost Structure' cell in table 'WBS' table and click  for row with data:
- 'Gear' menuItems contains items:

#### 11. Click away on screen  verify overlay closed

- I click on 'Cost Structure' tab
- page with name 'Cost Structure page' is opened
- 'Gear' menuItems is not displayed

#### 12. Click + next to "1 Overall Proposal"  verify WBS expanded 1 level with:

- I click on '1 Overall Proposal' expander
- page with name 'Cost Structure page' is opened
- the cell value of the 'WP' column of the 'WBS' table is checked for a row with the following data:
- the cell value of the 'WP' column of the 'WBS' table is checked for a row with the following data:
- the cell value of the 'WP' column of the 'WBS' table is checked for a row with the following data:
- the cell value of the 'WP' column of the 'WBS' table is checked for a row with the following data:
- the cell value of the 'WP' column of the 'WBS' table is checked for a row with the following data:
- the cell value of the 'WP' column of the 'WBS' table is checked for a row with the following data:
- the cell value of the 'WP' column of the 'WBS' table is checked for a row with the following data:
- the cell value of the 'WP' column of the 'WBS' table is checked for a row with the following data:
- 'WBS' table contain row with following data:
- 'WBS' table contain row with following data:
- 'WBS' table contain row with following data:
- 'WBS' table contain row with following data:
- 'WBS' table contain row with following data:
- 'WBS' table contain row with following data:
- 'WBS' table contain row with following data:
- 'WBS' table contain row with following data:

### Gherkin Excerpt

```gherkin
  Scenario: TC-Copy-Manufacturing-Proposal-003: Perform Cost Structure [Estimate] Validation
    And info: ---1. Validate widget values  Total Revenue = USD 0, Total Cost = USD 0, Margin = 0%---
    And page with name 'Cost Structure page' is opened
    And text of 'Total Revenue' widget equals 'USD 0'
    And text of 'Total Cost' widget equals 'USD 0'
    And text of 'Margin' widget equals '0 %'
    And info: ---2. Verify selected View = "REG Test - Manufacturing Proposal Copy Validation" (if not, select from dropdown; if unavailable  error)---
    When I select 'REG Test - Manufacturing Proposal Copy Validation' in the 'WBS View' dropdown
    And I wait for 10 seconds
    And I click on 'Save' button
    Then text of 'WBS View' dropdown equals 'View: REG Test - Manufacturing Proposal Copy Validation'
    And page with name 'Cost Structure page' is opened
    And info: ---3. Verify WBS grid  Main row present, columns = Cost Structure, WP, Type, WP Owner, Work Package, Start, End, Total Cost w/ Risk, Labor Cost, Labor Hours, Material Cost, Other Cost---
    And number of rows in 'WBS' table equals 2
    And 'WBS' table contain row with following data:
      | Cost Structure        | $uniqueProposalName     |
    And I wait for 5 seconds
    Then I should see the following columns in the 'WBS' table:
      | Cost Structure   |
      | WP              |
      | Type            |
      | WP Owner        |
      | Work Package    |
      | Start           |
      | End             |
      | Total Cost with Risk Cost |
      | Labor Cost     |
      | Labor Hours    |
      | Material Cost  |
      | Other Cost     |
    And info: ---4. Click gear  next to "Manual - TC-Copy-Manufacturing-Proposal-001: Setup & Copy Prior Proposal"  verify menu options: Expand/Collapse, Import Below, Copy Cost to DTC, Reset Min/Max Costs, Re-number WBS Below, Reschedule WBS/Task, Generate Cost Assignments---
    And I hover 'Cost Structure' cell in table 'WBS' table and click  for row with data:
      | Cost Structure | $uniqueProposalName |
    And 'Gear' menuItems contains items:
      | Expand/Collapse        |
      | Import Below          |
      | Copy Cost to DTC      |
      | Reset Min/Max Costs   |
      | Re-number WBS Below   |
      | Generate Cost Assignments |
#      | Reschedule WBS or Task   |
    And info: ---5. Hover Expand/Collapse  verify options: Collapse All, Expand, Expand All---
    And I hover on 'Expand and Collapse' menuItem
    And 'Gear' menuItems contains items:
      | Collapse All |
      | Expand       |
      | Expand All   |
    And info: ---6. Select Expand All  verify full Cost Structure expanded---
    When I click on 'Expand All' menuItem
    And page with name 'Cost Structure page' is opened
    Then number of rows in 'WBS' table equals 21
    And info: ---7. Click gear  again for "Manual - TC-Copy-Manufacturing-Proposal-001: Setup & Copy Prior Proposal"  verify overlay displayed---
    And I hover 'Cost Structure' cell in table 'WBS' table and click  for row with data:
      | Cost Structure | $uniqueProposalName |
    And page with name 'Cost Structure page' is opened
    And 'Gear' menuItems contains items:
      | Expand/Collapse        |
      | Import Below          |
      | Copy Cost to DTC      |
      | Reset Min/Max Costs   |
      | Re-number WBS Below   |
      | Generate Cost Assignments |
#      | Reschedule WBS or Task   |
    And info: ---8. Hover Expand/Collapse  verify overlay still displayed---
    And I hover on 'Expand and Collapse' menuItem
    And 'Gear' menuItems contains items:
      | Collapse All |
      | Expand       |
      | Expand All   |
    And info: ---9. Select Collapse All  verify Cost Structure collapsed---
    When I click on 'Collapse All' menuItem
    And page with name 'Cost Structure page' is opened
    Then number of rows in 'WBS' table equals 2
    And info: ---10. Click gear  next to "1 Overall Proposal"  verify menu options: Expand/Collapse, Add WBS Node, Copy WBS, Delete WBS, Import Below, Copy Cost to DTC, Reset Min/Max Costs, Re-number WBS Below, Reschedule WBS/Task, Generate Cost Assignments, Cascade---
    And I hover 'Cost Structure' cell in table 'WBS' table and click  for row with data:
      | Cost Structure | 1 Overall Proposal |
    And 'Gear' menuItems contains items:
      | Expand/Collapse        |
      | Add WBS Node          |
      | Copy WBS              |
      | Copy WBS Element (all estimates) |
      | Delete WBS            |
      | Import Below          |
      | Copy Cost to DTC      |
      | Reset Min/Max Costs   |
      | Generate Cost Assignments  |
      | Open Estimate             |
#      | Reschedule WBS or Task   |
    And info: ---11. Click away on screen  verify overlay closed---
    When I click on 'Cost Structure' tab
    Then page with name 'Cost Structure page' is opened
    And 'Gear' menuItems is not displayed
    And info: ---12. Click + next to "1 Overall Proposal"  verify WBS expanded 1 level with:---
    And I click on '1 Overall Proposal' expander
    And page with name 'Cost Structure page' is opened
    And the cell value of the 'WP' column of the 'WBS' table is checked for a row with the following data:
      | Cost Structure | 1.1 Engineering |
    And the cell value of the 'WP' column of the 'WBS' table is checked for a row with the following data:
      | Cost Structure | 1.2 Project Management     |
    And the cell value of the 'WP' column of the 'WBS' table is checked for a row with the following data:
      | Cost Structure | 1.3 Decommissioning        |
    And the cell value of the 'WP' column of the 'WBS' table is checked for a row with the following data:
      | Cost Structure | 1.4 Maintenance & Support |
    And the cell value of the 'WP' column of the 'WBS' table is checked for a row with the following data:
      | Cost Structure | 1.5 Launch & Recovery  |
    And the cell value of the 'WP' column of the 'WBS' table is checked for a row with the following data:
      | Cost Structure | 1.6 Operational Readiness |
    And the cell value of the 'WP' column of the 'WBS' table is checked for a row with the following data:
      | Cost Structure | 1.7 Installation & Support |
    And the cell value of the 'WP' column of the 'WBS' table is checked for a row with the following data:
      | Cost Structure | 1.8 Production |
    And 'WBS' table contain row with following data:
      | Cost Structure | 1.1 Engineering |
      | Type           | Estimate                  |
    And 'WBS' table contain row with following data:
      | Cost Structure | 1.2 Project Management     |
      | Type           | Estimate                    |
    And 'WBS' table contain row with following data:
      | Cost Structure | 1.3 Decommissioning        |
      | Type           | Estimate                    |
    And 'WBS' table contain row with following data:
      | Cost Structure | 1.4 Maintenance & Support |
      | Type           | Estimate                  |
    And 'WBS' table contain row with following data:
      | Cost Structure | 1.5 Launch & Recovery  |
      | Type           | Estimate               |
    And 'WBS' table contain row with following data:
      | Cost Structure | 1.6 Operational Readiness |
      | Type           | Estimate                   |
    And 'WBS' table contain row with following data:
      | Cost Structure | 1.7 Installation & Support |
      | Type           | Estimate                    |
    And 'WBS' table contain row with following data:
      | Cost Structure | 1.8 Production |
      | Type           | Estimate        |
```

---

_Generated by `scripts/generate-testcase-guides.py` from the RTA mapping and local feature files._
