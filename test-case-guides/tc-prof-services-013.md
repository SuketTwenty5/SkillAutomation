# TC-Prof-Services-013 - Complete Pricing Proposal

| Field | Value |
| --- | --- |
| Test ID | TC-Prof-Services-013 |
| Title | Complete Pricing Proposal |
| RTA page | https://twenty5.atlassian.net/wiki/spaces/RTA/pages/230753189/TC-Prof-Services-013+Complete+Pricing+Proposal |
| Map source | `RTA_TEST_SUITE_FEATURE_MAP.md` |

## Run Instructions

When a consultant asks to run this test, use the default app URL unless they provide another URL:

```text
https://approuter-twenty5ipe-dev.cfapps.us10.hana.ondemand.com/#quote
```

For Claude Desktop, ask before launching Selenium Chrome with the default URL. If the user provides another URL, launch that URL instead.

```bash
scripts/start-debug-chrome.sh "https://approuter-twenty5ipe-dev.cfapps.us10.hana.ondemand.com/#quote"
scripts/run-twentyfive-test.sh @TC-013
```

## Default mapped Selenium scenario

| Field | Value |
| --- | --- |
| Feature | 3. Pricing Assignment for Professional Services |
| Scenario | TC-Prof-Services-013: Complete Pricing Proposal |
| Tags | `@TC-013 @END` |
| File | `imported/twentyfive-regtest/tests/src/test/resources/features/testfolder/pricing_assignments_for_professional_services.feature:174` |

### Scenario Sections

#### Setup

- I click on 'Billing Items' tab

#### 1. Click + to create additional line items

- I click '+' in table 'Billing Items' table in row number 1
- I set value 'Business / Technical Consulting' to the cell of the column 'Description' of the 'Billing Items' table for the row with the index '2'
- I wait for 3 seconds
- I click '+' in table 'Billing Items' table in row number 2
- I set value 'Implementation & Integration Services' to the cell of the column 'Description' of the 'Billing Items' table for the row with the following data:
- I wait for 3 seconds
- I click '+' in table 'Billing Items' table in row number 3
- I set value 'Hypercare' to the cell of the column 'Description' of the 'Billing Items' table for the row with the following data:
- page with name 'Billing Items page' is opened

#### 2. Click Save

- I click on 'Save' button
- 'Data saved successfully' warning message is displayed in 30 seconds

#### 3. Click pencil [  ] icon to the right of Description to view the Business / Technical Consulting Billing Items popup and navigate to Cost Allocations tab

- I click 'Description' in table 'Billing Items' table for row with data:
- 'Billing Items' popUp is displayed

#### 4. Create new record and select 1 Prof Services Estimate as Sender WBS with 20% Cost Allocation

- I click on 'Cost Allocations' tab
- number of rows in 'Billing Items Cost Allocation' table equals 0
- I click on 'No records found, click here to add' button
- number of rows in 'Billing Items Cost Allocation' table equals 1
- I set value '1 Prof Services Estimate' to the cell of the column 'Sender WBS' of the 'Billing Items Cost Allocation' table for the row with the index '1'
- 'Billing Items' popUp is displayed
- I set '% Cost' to '20.00%' in 'Billing Items Cost Allocation' table and verify 'Current Cost' for a row with the index '1'
- 'Billing Items Cost Allocation' table contain row with following data:

#### 5. Navigate to Pricing tab

- I click on 'Billing Items Pricing' tab
- I wait for 3 seconds

#### 6. Select Cost plus Pricing from Pricing Strategy dropdown

- I enter data into the next fields:
- 'Billing Items Pricing' table is displayed
- I click on 'Re-price' button
- I wait for 10 seconds
- 'Billing Items Pricing' table is displayed

#### 7. Enter Fixed Fee of 60% with a Discount or Surcharge of 2%

- I set value '60.00' to the cell of the column 'Percentage' of the 'Billing Items Pricing' table for the row with the following data:
- I wait for 7 seconds
- I set value '2.00' to the cell of the column 'Percentage' of the 'Billing Items Pricing' table for the row with the following data:
- I wait for 7 seconds

#### 8. Click Reprice

- I click on 'Re-price' button
- I wait for 7 seconds
- I verify 'Company Revenue' of 'Billing Items Pricing' Dialog page is enabled, not required, free text and has text '1,070,094.30'
- I verify 'Margin' of 'Billing Items Pricing' Dialog page is enabled, not required, free text and has text '38.73'

#### 9. Click Confirm

- I click on 'Confirm' button
- page with name 'Billing Items page' is opened

#### 10. Click pencil [  ] icon to the right of Description to view the Implementation & Integration Services Billing Items popup and navigate to Cost Allocations tab

- I click 'Description' in table 'Billing Items' table for row with data:
- 'Billing Items' popUp is displayed

#### 11. Create new record and select 1 Prof Services Estimate as Sender WBS with 20% Cost Allocation

- I click on 'Cost Allocations' tab
- number of rows in 'Billing Items Cost Allocation' table equals 0
- I click on 'No records found, click here to add' button
- number of rows in 'Billing Items Cost Allocation' table equals 1
- I set value '1 Prof Services Estimate' to the cell of the column 'Sender WBS' of the 'Billing Items Cost Allocation' table for the row with the index '1'
- 'Billing Items' popUp is displayed
- I set '% Cost' to '20.00%' in 'Billing Items Cost Allocation' table and verify 'Current Cost' for a row with the index '1'

#### 12. Navigate to Pricing tab

- I click on 'Billing Items Pricing' tab
- I wait for 3 seconds

#### 13. Select Cost plus Pricing from Pricing Strategy dropdown

- I enter data into the next fields:
- 'Billing Items Pricing' table is displayed
- I click on 'Re-price' button
- I wait for 10 seconds
- 'Billing Items Pricing' table is displayed

#### 14. Enter Fixed Fee of 20% with a Discount or Surcharge of 1%

- I select 'REG Prof Services' in the 'Dialog box View' dropdown
- I wait for 3 seconds
- text of 'Dialog box View' dropdown equals 'View: REG Prof Services'
- I set value '20.00' to the cell of the column 'Percentage' of the 'Billing Items Pricing' table for the row with the following data:
- I wait for 7 seconds
- I set value '1.00' to the cell of the column 'Percentage' of the 'Billing Items Pricing' table for the row with the following data:
- I wait for 7 seconds

#### 15. Click Confirm

- I click on 'Re-price' button
- I wait for 7 seconds
- I click on 'Confirm' button
- page with name 'Billing Items page' is opened

#### 16. Click pencil [  ] icon to the right of Description to view the Hypercare Billing Items popup and navigate to Cost Allocations tab

- I click 'Description' in table 'Billing Items' table for row with data:
- 'Billing Items' popUp is displayed

#### 17. Create new record and select 1 Prof Services Estimate as Sender WBS with 10% Cost Allocation

- I click on 'Cost Allocations' tab
- number of rows in 'Billing Items Cost Allocation' table equals 0
- I click on 'No records found, click here to add' button
- number of rows in 'Billing Items Cost Allocation' table equals 1
- I set value '1 Prof Services Estimate' to the cell of the column 'Sender WBS' of the 'Billing Items Cost Allocation' table for the row with the index '1'
- 'Billing Items' popUp is displayed
- I set '% Cost' to '10.00%' in 'Billing Items Cost Allocation' table and verify 'Current Cost' for a row with the index '1'

#### 18. Navigate to Pricing tab

- I click on 'Billing Items Pricing' tab
- I wait for 3 seconds

#### 19. Select Cost plus Pricing - Regression (DO NOT CHANGE) from Pricing Strategy dropdown

- I enter data into the next fields:
- 'Billing Items Pricing' table is displayed
- I click on 'Re-price' button
- I wait for 10 seconds

#### 20. Enter Fixed Fee of 20%

- I select 'REG Prof Services' in the 'Dialog box View' dropdown
- I wait for 3 seconds
- text of 'Dialog box View' dropdown equals 'View: REG Prof Services'
- I set value '20.00' to the cell of the column 'Percentage' of the 'Billing Items Pricing' table for the row with the following data:
- I wait for 7 seconds

#### 21. Click Confirm

- I click on 'Re-price' button
- I wait for 7 seconds
- I click on 'Confirm' button
- page with name 'Billing Items page' is opened

#### 22. Run Update Cost & Prices via the hyperlink

- I click on 'Update Cost' button
- I wait for 15 seconds
- page with name 'Billing Items page' is opened

#### 23. Validate widget Totals

- text of 'Total Price' widget equals 'USD 3.35M'
- text of 'Total Cost' widget equals 'USD 3.28M'
- text of 'Margin as Percentage' widget equals '2.06 %'

#### 24. Verify Estimated Revenue, Estimated Cost, and Margin fields

- I verify 'Est. Revenue (excl. options/pass-thru)' of Billing Item page is enabled, not required, free text and has text '3,347,323.18'
- I verify 'Estimated Cost (from WBS)' of Billing Item page is enabled, not required, free text and has text '3,278,475.20'
- I verify 'Margin' of Billing Item page is enabled, not required, free text and has text '68,847.98'
- I verify 'Margin %' of Billing Item page is enabled, not required, free text and has text '2.06'

#### 25. Validate Profit total

- Grand total cell value of the 'Profit' column of the 'Billing Items' table equals '$ 68,847.98'

### Gherkin Excerpt

```gherkin
  Scenario: TC-Prof-Services-013: Complete Pricing Proposal
    And I click on 'Billing Items' tab
    And info: ---1. Click + to create additional line items ---
    And I click '+' in table 'Billing Items' table in row number 1
    And I set value 'Business / Technical Consulting' to the cell of the column 'Description' of the 'Billing Items' table for the row with the index '2'
    And I wait for 3 seconds
    And I click '+' in table 'Billing Items' table in row number 2
    And I set value 'Implementation & Integration Services' to the cell of the column 'Description' of the 'Billing Items' table for the row with the following data:
      | Item | 3 |
    And I wait for 3 seconds
    And I click '+' in table 'Billing Items' table in row number 3
    And I set value 'Hypercare' to the cell of the column 'Description' of the 'Billing Items' table for the row with the following data:
      | Item | 4 |
    And page with name 'Billing Items page' is opened
    And info: ---2. Click Save ---
    Then I click on 'Save' button
    And 'Data saved successfully' warning message is displayed in 30 seconds
    And info: ---3. Click pencil [  ] icon to the right of Description to view the Business / Technical Consulting Billing Items popup and navigate to Cost Allocations tab ---
    And I click 'Description' in table 'Billing Items' table for row with data:
      | Description | Business / Technical Consulting |
    And 'Billing Items' popUp is displayed
    And info: ---4. Create new record and select 1 Prof Services Estimate as Sender WBS with 20% Cost Allocation ---
    And I click on 'Cost Allocations' tab
    And number of rows in 'Billing Items Cost Allocation' table equals 0
    And I click on 'No records found, click here to add' button
    And number of rows in 'Billing Items Cost Allocation' table equals 1
    Then I set value '1 Prof Services Estimate' to the cell of the column 'Sender WBS' of the 'Billing Items Cost Allocation' table for the row with the index '1'
    And 'Billing Items' popUp is displayed
    And I set '% Cost' to '20.00%' in 'Billing Items Cost Allocation' table and verify 'Current Cost' for a row with the index '1'
    And 'Billing Items Cost Allocation' table contain row with following data:
      | Sender WBS                 | 1 Prof Services Estimate     |
      | Current Cost               | $ 655,695.04                 |
    And info: ---5. Navigate to Pricing tab ---
    And I click on 'Billing Items Pricing' tab
    And I wait for 3 seconds
    And info: ---6. Select Cost plus Pricing from Pricing Strategy dropdown ---
    When I enter data into the next fields:
      | 'Pricing Strategy' field  | Cost plus Pricing - Regression (DO NOT CHANGE)  |
    And 'Billing Items Pricing' table is displayed
    And I click on 'Re-price' button
    And I wait for 10 seconds
    And 'Billing Items Pricing' table is displayed
    And info: ---7. Enter Fixed Fee of 60% with a Discount or Surcharge of 2% ---
#    When I select 'REG Prof Services' in the 'Dialog box View' dropdown
#    And I wait for 3 seconds
#    Then text of 'Dialog box View' dropdown equals 'View: REG Prof Services'
    And I set value '60.00' to the cell of the column 'Percentage' of the 'Billing Items Pricing' table for the row with the following data:
      | Description | Fixed fee |
    And I wait for 7 seconds
    And I set value '2.00' to the cell of the column 'Percentage' of the 'Billing Items Pricing' table for the row with the following data:
      | Description | Discount or Surcharge |
    And I wait for 7 seconds
    And info: ---8. Click Reprice ---
    And I click on 'Re-price' button
    And I wait for 7 seconds
    And I verify 'Company Revenue' of 'Billing Items Pricing' Dialog page is enabled, not required, free text and has text '1,070,094.30'
    And I verify 'Margin' of 'Billing Items Pricing' Dialog page is enabled, not required, free text and has text '38.73'
    And info: ---9. Click Confirm ---
    And I click on 'Confirm' button
    And page with name 'Billing Items page' is opened
    And info: ---10. Click pencil [  ] icon to the right of Description to view the Implementation & Integration Services Billing Items popup and navigate to Cost Allocations tab ---
    And I click 'Description' in table 'Billing Items' table for row with data:
      | Description | Implementation & Integration Services |
    And 'Billing Items' popUp is displayed
    And info: ---11. Create new record and select 1 Prof Services Estimate as Sender WBS with 20% Cost Allocation ---
    And I click on 'Cost Allocations' tab
    And number of rows in 'Billing Items Cost Allocation' table equals 0
    And I click on 'No records found, click here to add' button
    And number of rows in 'Billing Items Cost Allocation' table equals 1
    Then I set value '1 Prof Services Estimate' to the cell of the column 'Sender WBS' of the 'Billing Items Cost Allocation' table for the row with the index '1'
    And 'Billing Items' popUp is displayed
    And I set '% Cost' to '20.00%' in 'Billing Items Cost Allocation' table and verify 'Current Cost' for a row with the index '1'
    And info: ---12. Navigate to Pricing tab ---
    And I click on 'Billing Items Pricing' tab
    And I wait for 3 seconds
    And info: ---13. Select Cost plus Pricing from Pricing Strategy dropdown ---
    When I enter data into the next fields:
      | 'Pricing Strategy' field  | Cost plus Pricing - Regression (DO NOT CHANGE)  |
    And 'Billing Items Pricing' table is displayed
    And I click on 'Re-price' button
    And I wait for 10 seconds
    And 'Billing Items Pricing' table is displayed
    And info: ---14. Enter Fixed Fee of 20% with a Discount or Surcharge of 1% ---
    When I select 'REG Prof Services' in the 'Dialog box View' dropdown
    And I wait for 3 seconds
    Then text of 'Dialog box View' dropdown equals 'View: REG Prof Services'
    And I set value '20.00' to the cell of the column 'Percentage' of the 'Billing Items Pricing' table for the row with the following data:
      | Description | Fixed fee |
    And I wait for 7 seconds
    And I set value '1.00' to the cell of the column 'Percentage' of the 'Billing Items Pricing' table for the row with the following data:
      | Description | Discount or Surcharge |
    And I wait for 7 seconds
    And info: ---15. Click Confirm ---
    And I click on 'Re-price' button
    And I wait for 7 seconds
    And I click on 'Confirm' button
    And page with name 'Billing Items page' is opened
    And info: ---16. Click pencil [  ] icon to the right of Description to view the Hypercare Billing Items popup and navigate to Cost Allocations tab ---
    And I click 'Description' in table 'Billing Items' table for row with data:
      | Description | Hypercare |
    And 'Billing Items' popUp is displayed
    And info: ---17. Create new record and select 1 Prof Services Estimate as Sender WBS with 10% Cost Allocation ---
    And I click on 'Cost Allocations' tab
    And number of rows in 'Billing Items Cost Allocation' table equals 0
    And I click on 'No records found, click here to add' button
    And number of rows in 'Billing Items Cost Allocation' table equals 1
    Then I set value '1 Prof Services Estimate' to the cell of the column 'Sender WBS' of the 'Billing Items Cost Allocation' table for the row with the index '1'
    And 'Billing Items' popUp is displayed
    And I set '% Cost' to '10.00%' in 'Billing Items Cost Allocation' table and verify 'Current Cost' for a row with the index '1'
    And info: ---18. Navigate to Pricing tab ---
    And I click on 'Billing Items Pricing' tab
    And I wait for 3 seconds
    And info: ---19. Select Cost plus Pricing - Regression (DO NOT CHANGE) from Pricing Strategy dropdown ---
    When I enter data into the next fields:
      | 'Pricing Strategy' field  | Cost plus Pricing - Regression (DO NOT CHANGE)  |
    And 'Billing Items Pricing' table is displayed
    And I click on 'Re-price' button
    And I wait for 10 seconds
    And info: ---20. Enter Fixed Fee of 20% ---
    When I select 'REG Prof Services' in the 'Dialog box View' dropdown
    And I wait for 3 seconds
    Then text of 'Dialog box View' dropdown equals 'View: REG Prof Services'
    And I set value '20.00' to the cell of the column 'Percentage' of the 'Billing Items Pricing' table for the row with the following data:
      | Description | Fixed fee |
    And I wait for 7 seconds
    And info: ---21. Click Confirm ---
    And I click on 'Re-price' button
    And I wait for 7 seconds
    And I click on 'Confirm' button
    And page with name 'Billing Items page' is opened
    And info: ---22. Run Update Cost & Prices via the hyperlink ---
    Then I click on 'Update Cost' button
    And I wait for 15 seconds
    And page with name 'Billing Items page' is opened
    And info: ---23. Validate widget Totals ---
    And text of 'Total Price' widget equals 'USD 3.35M'
    And text of 'Total Cost' widget equals 'USD 3.28M'
    And text of 'Margin as Percentage' widget equals '2.06 %'
    And info: ---24. Verify Estimated Revenue, Estimated Cost, and Margin fields ---
    And I verify 'Est. Revenue (excl. options/pass-thru)' of Billing Item page is enabled, not required, free text and has text '3,347,323.18'
    And I verify 'Estimated Cost (from WBS)' of Billing Item page is enabled, not required, free text and has text '3,278,475.20'
    And I verify 'Margin' of Billing Item page is enabled, not required, free text and has text '68,847.98'
    And I verify 'Margin %' of Billing Item page is enabled, not required, free text and has text '2.06'
    And info: ---25. Validate Profit total  ---
    And Grand total cell value of the 'Profit' column of the 'Billing Items' table equals '$ 68,847.98'
```

---

_Generated by `scripts/generate-testcase-guides.py` from the RTA mapping and local feature files._
