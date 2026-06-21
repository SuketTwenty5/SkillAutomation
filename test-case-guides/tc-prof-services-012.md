# TC-Prof-Services-012 - Assign Pricing Strategy Update Prices

| Field | Value |
| --- | --- |
| Test ID | TC-Prof-Services-012 |
| Title | Assign Pricing Strategy Update Prices |
| RTA page | https://twenty5.atlassian.net/wiki/spaces/RTA/pages/230752713/TC-Prof-Services-012+Assign+Pricing+Strategy+Update+Prices |
| Map source | `RTA_TEST_SUITE_FEATURE_MAP.md` |

## Run Instructions

When a consultant asks to run this test, use the default app URL unless they provide another URL:

```text
https://approuter-twenty5ipe-dev.cfapps.us10.hana.ondemand.com/#quote
```

For Claude Desktop, ask before launching Selenium Chrome with the default URL. If the user provides another URL, launch that URL instead.

```bash
scripts/start-debug-chrome.sh "https://approuter-twenty5ipe-dev.cfapps.us10.hana.ondemand.com/#quote"
scripts/run-twentyfive-test.sh @TC-012
```

## Default mapped Selenium scenario

| Field | Value |
| --- | --- |
| Feature | 3. Pricing Assignment for Professional Services |
| Scenario | TC-Prof-Services-012: Assign Pricing Strategy & Update Prices |
| Tags | `@TC-012 @RUN` |
| File | `imported/twentyfive-regtest/tests/src/test/resources/features/testfolder/pricing_assignments_for_professional_services.feature:118` |

### Scenario Sections

#### 1. Navigate to the Pricing tab

- 'Billing Items' popUp is displayed
- I click on 'Billing Items Pricing' tab

#### 2. Select Cost plus Pricing from Pricing Strategy dropdown

- I enter data into the next fields:
- I wait for 5 seconds
- 'Billing Items Pricing' table is displayed

#### 3. Review Pricing Strategy fields

- 'Description' column contains items_-Warning-_:

#### 4. Select Re-price to pull in Cost values

- I click on 'Re-price' button
- I wait for 10 seconds
- 'Billing Items Pricing' table is displayed

#### 5. Enter 15 as the Fixed Fee percentage and press Enter

- I select 'REG Prof Services' in the 'Dialog box View' dropdown
- I wait for 3 seconds
- text of 'Dialog box View' dropdown equals 'View: REG Prof Services'
- I set value '15.00' to the cell of the column 'Percentage' of the 'Billing Items Pricing' table for the row with the following data:
- I wait for 7 seconds
- I verify 'Fixed fee' Amount is 15% of 'Cost' Amount

#### 6. Enter 3 as the Discount or Surcharge percentage and press Enter

- I set value '3.00' to the cell of the column 'Percentage' of the 'Billing Items Pricing' table for the row with the following data:
- I wait for 7 seconds
- I verify 'Discount or Surcharge' Amount is 3% of 'List price' Amount

#### 7. Review updated Prices

- I click on 'Re-price' button
- I wait for 7 seconds

#### 8. Review updated Prices

- I verify 'Unit Price' of Billing Items Dialog page is enabled, not required, free text and has text '1,941,676.94'
- I verify 'Company Revenue' of Billing Items Dialog page is enabled, not required, free text and has text '1,941,676.94'
- I verify 'Margin' of Billing Items Dialog page is enabled, not required, free text and has text '15.58'

#### 9. Click Confirm

- I click on 'Confirm' button

#### 10. Click pencil [  ] icon to the right of Description to view the same Billing Items popup

- I click 'Description' in table 'Billing Items' table in row number 1
- I wait for 10 seconds
- I verify 'Unit Price' of Billing Items Dialog page is enabled, not required, free text and has text '1,941,676.94'
- I verify 'Company Revenue' of Billing Items Dialog page is enabled, not required, free text and has text '1,941,676.94'
- I verify 'Margin' of Billing Items Dialog page is enabled, not required, free text and has text '15.58'

#### 11. Click Cancel

- I click on 'Cancel' button
- I refresh page

### Gherkin Excerpt

```gherkin
  Scenario: TC-Prof-Services-012: Assign Pricing Strategy & Update Prices
    And info: ---1. Navigate to the Pricing tab  ---
    And 'Billing Items' popUp is displayed
    And I click on 'Billing Items Pricing' tab
    And info: ---2. Select Cost plus Pricing from Pricing Strategy dropdown ---
    When I enter data into the next fields:
      | 'Pricing Strategy' field  | Cost plus Pricing - Regression (DO NOT CHANGE)  |
    And I wait for 5 seconds
    And 'Billing Items Pricing' table is displayed
    And info: ---3. Review Pricing Strategy fields ---
    And 'Description' column contains items_-Warning-_:
    | Cost |
    | Fixed fee |
    | List price |
    | Discount or Surcharge |
    | Pocket price |
    | Sales tax |
    | Invoice price |
    And info: ---4. Select Re-price to pull in Cost values ---
    And I click on 'Re-price' button
    And I wait for 10 seconds
    And 'Billing Items Pricing' table is displayed
    And info: ---5. Enter 15 as the Fixed Fee percentage and press Enter ---
    When I select 'REG Prof Services' in the 'Dialog box View' dropdown
    And I wait for 3 seconds
    Then text of 'Dialog box View' dropdown equals 'View: REG Prof Services'
    And I set value '15.00' to the cell of the column 'Percentage' of the 'Billing Items Pricing' table for the row with the following data:
      | Description | Fixed fee |
    And I wait for 7 seconds
    Then I verify 'Fixed fee' Amount is 15% of 'Cost' Amount
    And info: ---6. Enter 3 as the Discount or Surcharge percentage and press Enter  ---
    And I set value '3.00' to the cell of the column 'Percentage' of the 'Billing Items Pricing' table for the row with the following data:
      | Description | Discount or Surcharge |
    And I wait for 7 seconds
    Then I verify 'Discount or Surcharge' Amount is 3% of 'List price' Amount
    And info: ---7. Review updated Prices ---
    And I click on 'Re-price' button
    And I wait for 7 seconds
    And info: ---8. Review updated Prices ---
    And I verify 'Unit Price' of Billing Items Dialog page is enabled, not required, free text and has text '1,941,676.94'
    And I verify 'Company Revenue' of Billing Items Dialog page is enabled, not required, free text and has text '1,941,676.94'
    And I verify 'Margin' of Billing Items Dialog page is enabled, not required, free text and has text '15.58'
    And info: ---9. Click Confirm ---
    And I click on 'Confirm' button
    And info: ---10. Click pencil [  ] icon to the right of Description to view the same Billing Items popup ---
    And I click 'Description' in table 'Billing Items' table in row number 1
    And I wait for 10 seconds
    And I verify 'Unit Price' of Billing Items Dialog page is enabled, not required, free text and has text '1,941,676.94'
    And I verify 'Company Revenue' of Billing Items Dialog page is enabled, not required, free text and has text '1,941,676.94'
    And I verify 'Margin' of Billing Items Dialog page is enabled, not required, free text and has text '15.58'
    And info: ---11. Click Cancel ---
    Then I click on 'Cancel' button
    And I refresh page


```

---

_Generated by `scripts/generate-testcase-guides.py` from the RTA mapping and local feature files._
