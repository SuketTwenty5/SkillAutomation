@PRICING-ASSIGNMENT-PROFF-SERVICES
Feature: 3. Pricing Assignment for Professional Services

  @TC-010 @START
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

  @TC-011 @RUN
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

  @TC-012 @RUN
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
    And info: ---10. Click pencil [ ✏️ ] icon to the right of Description to view the same Billing Items popup ---
    And I click 'Description' in table 'Billing Items' table in row number 1
    And I wait for 10 seconds
    And I verify 'Unit Price' of Billing Items Dialog page is enabled, not required, free text and has text '1,941,676.94'
    And I verify 'Company Revenue' of Billing Items Dialog page is enabled, not required, free text and has text '1,941,676.94'
    And I verify 'Margin' of Billing Items Dialog page is enabled, not required, free text and has text '15.58'
    And info: ---11. Click Cancel ---
    Then I click on 'Cancel' button
    And I refresh page


  @TC-013 @END
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
    And info: ---3. Click pencil [ ✏️ ] icon to the right of Description to view the Business / Technical Consulting Billing Items popup and navigate to Cost Allocations tab ---
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
    And info: ---10. Click pencil [ ✏️ ] icon to the right of Description to view the Implementation & Integration Services Billing Items popup and navigate to Cost Allocations tab ---
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
    And info: ---16. Click pencil [ ✏️ ] icon to the right of Description to view the Hypercare Billing Items popup and navigate to Cost Allocations tab ---
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
