# TC-Prof-Services-011 - Assign Cost Allocations

| Field | Value |
| --- | --- |
| Test ID | TC-Prof-Services-011 |
| Title | Assign Cost Allocations |
| RTA page | https://twenty5.atlassian.net/wiki/spaces/RTA/pages/230752257/TC-Prof-Services-011+Assign+Cost+Allocations |
| Map source | `RTA_TEST_SUITE_FEATURE_MAP.md` |

## Run Instructions

When a consultant asks to run this test, use the default app URL unless they provide another URL:

```text
https://approuter-twenty5ipe-dev.cfapps.us10.hana.ondemand.com/#quote
```

For Claude Desktop, ask before launching Selenium Chrome with the default URL. If the user provides another URL, launch that URL instead.

```bash
scripts/start-debug-chrome.sh "https://approuter-twenty5ipe-dev.cfapps.us10.hana.ondemand.com/#quote"
scripts/run-twentyfive-test.sh @TC-011
```

## Default mapped Selenium scenario

| Field | Value |
| --- | --- |
| Feature | 3. Pricing Assignment for Professional Services |
| Scenario | TC-Prof-Services-011: Assign Cost Allocations |
| Tags | `@TC-011 @RUN` |
| File | `imported/twentyfive-regtest/tests/src/test/resources/features/testfolder/pricing_assignments_for_professional_services.feature:56` |

### Scenario Sections

#### 1. Click hyperlink for Project Management Services in the Description column to view the Billing Items popup

- I click 'Description' in table 'Billing Items' table in row number 1
- 'Billing Items' popUp is displayed
- I verify tabs available in the popup are:
- I verify 'Product or Service' of Billing Items Dialog page is enabled, not required, drop down and is empty
- I verify 'Revenue Recognition Method' of Billing Items Dialog page is enabled, not required, drop down and is empty
- I verify 'Material Price Book' of Billing Items Dialog page is enabled, not required, drop down and is empty
- I verify 'Labor Price Book' of Billing Items Dialog page is enabled, not required, drop down and is empty
- I verify 'Contract Type' of Billing Items Dialog page is enabled, not required, drop down and is empty
- I verify 'Quantity to Deliver' of Billing Items Dialog page is enabled, not required, free text and has text '1'
- I verify 'Unit Price' of Billing Items Dialog page is enabled, not required, free text and has text '0.00'
- I verify 'Company Revenue' of Billing Items Dialog page is enabled, not required, free text and has text '0.00'
- I verify 'Margin' of Billing Items Dialog page is enabled, not required, free text and has text '0'
- text of 'Dialog Pricing Strategy' dropdown equals ''_-Warning-_
- 'Re-price' button is displayed
- 'Confirm' button is displayed
- 'Cancel' button is displayed

#### 2. Check selected View

- I select 'REG Prof Services' in the 'Dialog box View' dropdown
- I wait for 3 seconds
- text of 'Dialog box View' dropdown equals 'View: REG Prof Services'

#### 3. Navigate to Cost Allocations tab

- I click on 'Cost Allocations' tab
- number of rows in 'Billing Items Cost Allocation' table equals 0
- I should see the following columns in the 'Billing Items Cost Allocation' table:

#### 4. Add new record

- I click on 'No records found, click here to add' button
- number of rows in 'Billing Items Cost Allocation' table equals 1

#### 5. Select 1 Prof Services Estimate as Sender WBS from dropdown

- I set value '1 Prof Services Estimate' to the cell of the column 'Sender WBS' of the 'Billing Items Cost Allocation' table for the row with the index '1'
- 'Billing Items' popUp is displayed

#### 6. Re-allocate the % Cost to be 50.00%

- I set '% Cost' to '50.00%' in 'Billing Items Cost Allocation' table and verify 'Current Cost' for a row with the index '1'
- 'Billing Items Cost Allocation' table contain row with following data:

#### 7. Click Confirm

- I click on 'Confirm' button
- 'Billing Items' popUp is not displayed

#### 8. Click hyperlink for Project Management Services in the Description column to view the Billing Items popup

- I click 'Description' in table 'Billing Items' table in row number 1
- 'Billing Items' popUp is displayed
- 'Billing Items Cost Allocation' table contain row with following data:

### Gherkin Excerpt

```gherkin
  Scenario: TC-Prof-Services-011: Assign Cost Allocations
    And info: ---1. Click hyperlink for Project Management Services in the Description column to view the Billing Items popup  ---
    And I click 'Description' in table 'Billing Items' table in row number 1
    And 'Billing Items' popUp is displayed
    Then I verify tabs available in the popup are:
      | Pricing            |
      | Remarks            |
      | Cost Allocations   |
      | Delivery Schedule  |
      | Tags               |
      | Billing Milestones |
      | Questionnaire      |
    And I verify 'Product or Service' of Billing Items Dialog page is enabled, not required, drop down and is empty
    And I verify 'Revenue Recognition Method' of Billing Items Dialog page is enabled, not required, drop down and is empty
    And I verify 'Material Price Book' of Billing Items Dialog page is enabled, not required, drop down and is empty
    And I verify 'Labor Price Book' of Billing Items Dialog page is enabled, not required, drop down and is empty
    And I verify 'Contract Type' of Billing Items Dialog page is enabled, not required, drop down and is empty
#    And I verify 'Pricing Strategy' of Billing Items Dialog page is enabled, required, drop down and is empty
    And I verify 'Quantity to Deliver' of Billing Items Dialog page is enabled, not required, free text and has text '1'
    And I verify 'Unit Price' of Billing Items Dialog page is enabled, not required, free text and has text '0.00'
    And I verify 'Company Revenue' of Billing Items Dialog page is enabled, not required, free text and has text '0.00'
    And I verify 'Margin' of Billing Items Dialog page is enabled, not required, free text and has text '0'
#    And 'No records found, click here to add' button is displayed
    And text of 'Dialog Pricing Strategy' dropdown equals ''_-Warning-_

    And 'Re-price' button is displayed
    And 'Confirm' button is displayed
    And 'Cancel' button is displayed
    And info: ---2. Check selected View ---
    When I select 'REG Prof Services' in the 'Dialog box View' dropdown
    And I wait for 3 seconds
    Then text of 'Dialog box View' dropdown equals 'View: REG Prof Services'
    And info: ---3. Navigate to Cost Allocations tab ---
    And I click on 'Cost Allocations' tab
    And number of rows in 'Billing Items Cost Allocation' table equals 0
    Then I should see the following columns in the 'Billing Items Cost Allocation' table:
      | Sender WBS    |
      | Material Cost |
      | % Cost        |
      | Current Cost  |
    And info: ---4. Add new record ---
    And I click on 'No records found, click here to add' button
    And number of rows in 'Billing Items Cost Allocation' table equals 1
    And info: ---5. Select 1 Prof Services Estimate as Sender WBS from dropdown ---
    Then I set value '1 Prof Services Estimate' to the cell of the column 'Sender WBS' of the 'Billing Items Cost Allocation' table for the row with the index '1'
    And 'Billing Items' popUp is displayed
    And info: ---6. Re-allocate the % Cost to be 50.00% ---
    And I set '% Cost' to '50.00%' in 'Billing Items Cost Allocation' table and verify 'Current Cost' for a row with the index '1'
    And 'Billing Items Cost Allocation' table contain row with following data:
      | Sender WBS       | 1 Prof Services Estimate |
      | Current Cost     | $ 1,639,237.60           |
    And info: ---7. Click Confirm ---
    And I click on 'Confirm' button
    And 'Billing Items' popUp is not displayed
    And info: ---8. Click hyperlink for Project Management Services in the Description column to view the Billing Items popup ---
    And I click 'Description' in table 'Billing Items' table in row number 1
    And 'Billing Items' popUp is displayed
    And 'Billing Items Cost Allocation' table contain row with following data:
      | Sender WBS       | 1 Prof Services Estimate |
      | % Cost           | 50.00%                   |

```

---

_Generated by `scripts/generate-testcase-guides.py` from the RTA mapping and local feature files._
