# TC-Prof-Services-010 - Create Billing Item

| Field | Value |
| --- | --- |
| Test ID | TC-Prof-Services-010 |
| Title | Create Billing Item |
| RTA page | https://twenty5.atlassian.net/wiki/spaces/RTA/pages/230621185/TC-Prof-Services-010+Create+Billing+Item |
| Map source | `RTA_TEST_SUITE_FEATURE_MAP.md` |

## Run Instructions

When a consultant asks to run this test, use the default app URL unless they provide another URL:

```text
https://approuter-twenty5ipe-dev.cfapps.us10.hana.ondemand.com/#quote
```

For Claude Desktop, ask before launching Selenium Chrome with the default URL. If the user provides another URL, launch that URL instead.

```bash
scripts/start-debug-chrome.sh "https://approuter-twenty5ipe-dev.cfapps.us10.hana.ondemand.com/#quote"
scripts/run-twentyfive-test.sh @TC-010
```

## Default mapped Selenium scenario

| Field | Value |
| --- | --- |
| Feature | 3. Pricing Assignment for Professional Services |
| Scenario | TC-Prof-Services-010: Create Billing Item |
| Tags | `@TC-010 @START` |
| File | `imported/twentyfive-regtest/tests/src/test/resources/features/testfolder/pricing_assignments_for_professional_services.feature:5` |

### Scenario Sections

#### 1. Navigate to Billing Items tab

- open TC-Prof-Services-003 quote
- I perform login
- page with name 'Main page' is opened
- I click on 'Setup' tab
- page with name 'Setup page' is opened
- I enter data into the next fields:
- page with name 'Setup page' is opened
- I click on 'Save' button
- I wait for 7 seconds
- page with name 'Setup page' is opened
- I click on 'Billing Items' tab
- page with name 'Billing Items page' is opened
- I verify 'Est. Revenue (excl. options/pass-thru)' of Billing Item page is enabled, not required, free text and has text '0.00'
- 'PTW 0' hyperlink is displayed
- I verify 'Estimated Cost (from WBS)' of Billing Item page is enabled, not required, free text and has text '3,278,475.20'
- '0 Allocated' hyperlink is displayed
- I verify 'Margin' of Billing Item page is enabled, not required, free text and has text '0.00'
- I verify 'Revenue Recognition Method' of Billing Item page is enabled, not required, drop down and is empty
- I verify 'Labor Rate Card/MSA' of Billing Item page is enabled, not required, drop down and is empty
- I verify 'Product/Service Price Catalog' of Billing Item page is enabled, not required, drop down and is empty
- ... 1 more steps

#### 2. Check selected View

- I select 'REG Prof Services' in the 'View' dropdown
- I click on 'Save' button
- text of 'View' dropdown equals 'View: REG Prof Services (preferred)'

#### 3. Review WBS grid

- I if visible click on 'No records found, click here to add' button
- number of rows in 'Billing Items' table equals 1
- I should see the following columns in the 'Billing Items' table:

#### 4. Click grey box to Add Resources

- I set value 'Project Management Services' to the cell of the column 'Description' of the 'Billing Items' table for the row with the index '1'

#### 5. Enter Project Management Services in the Description field to enter

- the cell value of the 'Description' column of the 'Billing Items' table equals 'Project Management Services' for a row with the index '1'

#### 6. Click Save

- I click on 'Save' button
- 'Data saved successfully' warning message is displayed in 30 seconds

### Gherkin Excerpt

```gherkin
  Scenario: TC-Prof-Services-010: Create Billing Item
    And info: ---1. Navigate to Billing Items tab ---
    Given open TC-Prof-Services-003 quote
    And I perform login
    And page with name 'Main page' is opened
    And I click on 'Setup' tab
    And page with name 'Setup page' is opened
    When I enter data into the next fields:
      | 'Client Customer sell-to' field      | Regression Test - Customer USD |
    And page with name 'Setup page' is opened
    Then I click on 'Save' button
    And I wait for 7 seconds
    And page with name 'Setup page' is opened
    And I click on 'Billing Items' tab
    And page with name 'Billing Items page' is opened
    And I verify 'Est. Revenue (excl. options/pass-thru)' of Billing Item page is enabled, not required, free text and has text '0.00'
    And 'PTW 0' hyperlink is displayed
    And I verify 'Estimated Cost (from WBS)' of Billing Item page is enabled, not required, free text and has text '3,278,475.20'
    And '0 Allocated' hyperlink is displayed
    And I verify 'Margin' of Billing Item page is enabled, not required, free text and has text '0.00'
#    And I verify 'Default Pricing Strategy' of Billing Item page is enabled, not required, drop down and is empty
    And I verify 'Revenue Recognition Method' of Billing Item page is enabled, not required, drop down and is empty
    And I verify 'Labor Rate Card/MSA' of Billing Item page is enabled, not required, drop down and is empty
    And I verify 'Product/Service Price Catalog' of Billing Item page is enabled, not required, drop down and is empty
#    And text of 'Product or Service Price Catalog' dropdown equals 'CRM Service USA (sample)'_-Warning-_
    And text of 'Default Pricing Strategy' dropdown equals ''_-Warning-_

    And info: ---2. Check selected View ---
    When I select 'REG Prof Services' in the 'View' dropdown
    And I click on 'Save' button
    Then text of 'View' dropdown equals 'View: REG Prof Services (preferred)'
    And info: ---3. Review WBS grid ---
    And I if visible click on 'No records found, click here to add' button
    And number of rows in 'Billing Items' table equals 1
    Then I should see the following columns in the 'Billing Items' table:
      | Item              |
      | Description       |
      | Cost Allocation   |
      | Pricing Strategy  |
      | Profit            |
      | Cost              |
      | Revenue           |
    And info: ---4. Click grey box to Add Resources ---
    And I set value 'Project Management Services' to the cell of the column 'Description' of the 'Billing Items' table for the row with the index '1'
    And info: ---5. Enter Project Management Services in the Description field to enter ---
    And the cell value of the 'Description' column of the 'Billing Items' table equals 'Project Management Services' for a row with the index '1'
    And info: ---6. Click Save ---
    Then I click on 'Save' button
    And 'Data saved successfully' warning message is displayed in 30 seconds

```

---

_Generated by `scripts/generate-testcase-guides.py` from the RTA mapping and local feature files._
