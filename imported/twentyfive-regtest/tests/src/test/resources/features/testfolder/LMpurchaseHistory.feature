@purchaseHistory
Feature: 5. Purchase History

  @ART-209 @LM-TC-004_1 @START
  Scenario: TC-Purchase-History-001: Navigate to Cost Source History
    Given open site
    And I perform Mfg 2.4 login
    Then page with name 'Main page' is opened
    And info: ---1. Click Material Cost Estimates tab. Verify that sub-tab options are displayed.---
    When I click on 'Material Cost Estimates' tab
    Then 'Material Cost Estimates' options is displayed
    And info: ---2. Select Cost Source History option. Note: Cost Source History may be Purchase History in other environments. Verify that user is redirected to Cost Source History page.---
    When I click on 'Cost Source History' sub-tab
    And I wait for 7 seconds
    Then page with name 'Main page' is opened
    And info: ---3. Hover on the New button. Verify that "Copy Selected" and "Create from Category" options are displayed.---
    When I hover on 'New' button
    Then 'New' options contains items:
        | Copy Selected        |
        | Create from Category |

  @ART-213 @LM-TC-004_2 @RUN
  Scenario: TC-Purchase-History-Supplier-Quote-002: Verify Cost Source History: Key Info Layout
    And info: ---1. Hover on Create from Category option. Verify that "Purchase Order", "Contract", "Long-term Agreement", "Manual Cost Estimate", "Supplier Quote", and "RFQ" options are displayed.---
    When I hover on 'Create from Category' option
    Then 'Create from Category' dropDown is displayed
    And info: ---2. Select Supplier Quote. Verify that user is redirected to Create Cost Source History KEY INFO page and "Key Info", "Items", and "Pricing" sub-tabs are available.---
    When I click on 'Supplier Quote' option
    And I wait for 7 seconds
    Then page with name 'Create Cost Source History KEY INFO page' is opened
    Then I see the following TABS in the 'Create Cost Source History KEY INFO page' Top Menu_-Warning-_:
      | approvals      |
      | comments       |
      | copd comparison|
      | files tab      |
      | history        |
      | items          |
      | key info       |
      | pricing        |
    And info: ---3. Click Save button without editing any fields. Verify that error message appears listing required fields and missed required fields' borders are highlighted red, including Supplier Quote Type.---
    When I click on 'Save' button
    And I click on 'Error' dialog
    And 'Error' dialog is displayed
    And verify border-color css of 'Error' dialog is 'red'
#    When I click on 'Save' button
#    And 'Error' dialog is displayed
#    And I hover on 'Error' dialog
    Then text of 'Error' dialog equals_-Warning-_:
    """
    Supplier Quote Type * is required in KEY INFO tab
    """
    And verify border-color css of 'Supplier Quote Type' field is 'red'
    And I click on 'Supplier quote warning' close
    And info: ---4. Verify contents of Key Info page. Verify that:- Supplier Quote Type: enabled, required, free text, empty- Document Number: enabled, not required, free text, empty- Supplier: enabled, not required, dropdown, empty- Date: enabled, required, date picker, empty- Buyer: enabled, not required, dropdown, empty- Requester: enabled, not required, dropdown, empty- Supplier Quote Valid From: enabled, required, date picker, empty- Supplier Quote Valid Until: enabled, required, date picker, empty- Organization: enabled, not required, dropdown, empty- Site or Department: enabled, not required, dropdown, empty- Revision: enabled, not required, number picker, default to 0- Manually Added (not from SAP): checkbox, ticked by default- Currency: enabled, not required, dropdown, empty- Estimating Source: enabled, not required, dropdown, empty- Award Type: enabled, not required, dropdown, empty- Subcontract (parts provided): checkbox, un-ticked by default- Proposal: enabled, not required, dropdown, empty- Upload Files: button visible- Notes or Remarks: enabled, not required, empty---
    And I verify 'Supplier Quote Type' of setup page is enabled, required, free text and is empty
    And I verify 'Document Number' of setup page is enabled, not required, free text and is empty
    And I verify 'Supplier Code' of setup page is enabled, not required, drop down and is empty
    And I verify 'Date' of setup page is enabled, not required, date picker and is empty
    And I verify 'Buyer' of setup page is enabled, not required, drop down and is empty
    And I verify 'Requester' of setup page is enabled, not required, drop down and is empty
    And I verify 'Supplier Quote Valid From' of setup page is enabled, not required, date picker and is empty
    And I verify 'Supplier Quote Valid Until' of setup page is enabled, not required, date picker and is empty
    And I verify 'Organization' of setup page is enabled, not required, drop down and is empty
    And I verify 'Site or Department' of setup page is enabled, not required, drop down and is empty
    And I verify 'Revision' of setup page is enabled, not required, numeric selection and has text '0'
    And I verify 'Manually Added (not from SAP)' of setup page is disabled, not required, check box and is checked
    And I verify 'Currency' of setup page is enabled, not required, drop down and is empty
    And I verify 'Estimating Source' of setup page is enabled, not required, drop down and is empty
    And I verify 'Award Type' of setup page is enabled, not required, drop down and is empty
    And I verify 'Subcontract (parts provided)' of setup page is enabled, not required, check box and is unchecked
    And I verify 'Proposal' of setup page is enabled, not required, drop down and is empty
    And 'Upload Files' button is displayed
    And I verify 'Notes or remarks' of setup page is enabled, not required, free text and is empty

  @ART-211 @LM-TC-004_3 @RUN
  Scenario: TC-Purchase-History-Supplier-Quote-003: Create [Supplier Quote] Cost Source History Record
    And I wait for 5 seconds
    Then page with name 'Create Cost Source History KEY INFO page' is opened
    And info: ---1. Click on dropdown for Supplier Quote Type and select "Purchased Supplier Quote Manual". Verify that field accepts the value.---
    And I wait for 5 seconds
    When I enter data into the next fields:
      | 'Supplier Quote Type' field            | Purchase Supplier Quote [Manual]   |
    And info: ---2. Review Document Number field. Verify that field populates to "-system generated-" and editing is not permitted.---
    And I verify 'Document Number' of setup page is enabled, not required, free text and is empty
    And I wait for 7 seconds
    And info: ---3. Click on dropdown for Supplier and start typing "Regression Test Supplier". Verify that list is filtered based on typed entry and field accepts the value.---
    When I enter data into the next fields:
      | 'Supplier' field            | Regression Test - Supplier USD   |
    And info: ---4. Click Date field and type "1/1/25". Verify that field accepts the value.---
    When I enter data into the next fields:
      | 'Date' field            | 1/1/25   |
    And info: ---5. Click on dropdown for Buyer and start typing "Alexander Chambers". Verify that list is filtered based on typed entry and field accepts the value.---
    When I enter data into the next fields:
      | 'Buyer' field            | Ananth Te   |
    And info: ---6. Click on dropdown for Requester and start typing "Andrealiz Martinez". Verify that list is filtered based on typed entry and field accepts the value.---
    When I enter data into the next fields:
      | 'Requester' field            | Rajeev   |
    And info: ---7. Click Supplier Quote Valid From field and type "1/1/25". Verify that field accepts the value.---
    When I enter data into the next fields:
      | 'Supplier Quote Valid From' field            | 1/1/25   |
    And info: ---8. Click Supplier Quote Valid Until field and type "1/1/25". Verify that field accepts the value.---
    When I enter data into the next fields:
      | 'Supplier Quote Valid Until' field            | 1/1/25   |
    And info: ---9. Click on dropdown for Organization and select "Regression Test". Verify that field accepts the value and "Site or Department" auto-populates to "US - New York".---
    When I enter data into the next fields:
      | 'Organization' field            | Regression Test   |
#    And I wait for 5 seconds
#    Then I verify 'Site or Department' of setup page is enabled, not required, drop down and has text 'US - New York'
    And info: ---10. Review Currency field. Verify that currency is auto-populated based on Supplier selection, which is "$ USA - US Dollar (USD)". Raised an issue for this item as it did not occur during manual execution – captured under T5-4956: LMCO DEV | Purchase History App | Currency is not picked up from Supplier when creating Cost Source History > Supplier Quote.---
    And I verify 'Currency' of setup page is enabled, not required, drop down and has text '$ USA - US Dollar(USD)'
    And info: ---11. Click on dropdown for Estimating Source and select "Commercial Sole-Sourced Fixed Price Quotation". Verify that field accepts the value.---
    When I enter data into the next fields:
      | 'Estimating Source' field | Commercial Sole-Sourced Fixed Price Quotation |
    And info: ---12. Click Save. Verify that success message "Data saved successfully" appears.---
    When I click on 'Save' button
    Then 'Data saved successfully' dialog is displayed
    And I wait for 7 seconds
    And info: ---13. Review Document Number field. Verify that field populates to "QT-#####" and editing is not permitted.---
    And I verify 'Document Number' of setup page is enabled, not required, free text and has text 'QT-#####'

  @ART-210 @LM-TC-004_4 @RUN
  Scenario: TC-Purchase-History-Supplier-Quote-004: Enter Items for New Cost Source History Supplier Quote
    And info: ---1. Navigate to the Items tab. Verify that user is redirected to the Items tab.---
    And I click on 'Items' tab
    Then page with name 'Items page' is opened
    And info: ---2. Select the “REG View” view if not already selected. Verify that view is available and selected.---
    Then I select 'REG View' in the 'View' dropdown
    And I click on 'Save' button
    Then text of 'View' dropdown equals 'View: REG View (shared)'
    And info: ---3. Click the box labeled “No records found, click here to add.” Verify that a new record is created.---
    And I delete all rows from 'Items' table
    And I if visible click on 'No records found, click here to add' button
    Then page with name 'Items page' is opened
#    And 'Items' table contain row with following data:
#        | Product or Service |             |
#        | Part Description  |             |
#        | Item Text*         |             |
#        | Escalate From     |             |
#        | Escalate to Commitment date |             |
    And info: ---4. Click in the Product or Service field. Verify that a dropdown list loads.---
#    And I click 'Product or Service' in table 'Items' table in row number 1
#    Then 'Part Number' dropDown is displayed
    And info: ---5. Start typing “REG-TC004.” Verify that list filters based on user input and “REG-TC004” is available for selection.---
    And I set value 'REG-TC004' to the cell of the column 'Product or Service' of the 'Items' table for the row with the following data:
      | Item | 1 |
    Then page with name 'Items page' is opened
    And info: ---6. Select “REG-TC004.” Verify that field is populated, and Part Description and Item Text* auto-populate with “Purchased Part for Regression Testing (Supplier Quote Creation).”---
    And 'Items' table contain row with following data:
        | Product or Service        | REG-TC004                           |
        | Description               | Purchased Part for Regression Testing (Supplier Quote Creation) |
#        | Item Text*        | Purchased Part for Regression Testing (Supplier Quote Creation) |
    And info: ---7. Click in Escalation Date (Purchase Item) field and enter “1/1/25.” Verify that it behaves as a date picker and value populates correctly.---
    And I set value '1/1/25' to the cell of the column 'Escalation Date (Purchase Item)' of the 'Items' table for the row with the following data:
      | Item | 1 |
    Then page with name 'Items page' is opened
    And 'Items' table contain row with following data:
        | Item                                | 1             |
        | Escalation Date (Purchase Item)     | 01/01/2025    |
    And info: ---8. Tick the “Escalate to Commitment date” checkbox. Verify that checkbox is ticked.---
    And I activate checkbox 'Escalate to Commitment' in table 'Items' table for row with data:
      | Item | 1 |
    Then page with name 'Items page' is opened
    And info: ---9. Click on Escalate to Commitment chevron and select “Columns.” Verify that available column options are displayed.---
    And I hover on 'Escalate to Commitment' header
    And I click on 'Escalate to Commitment' chevron
    Then 'Columns' dropDown is displayed
    And I hover on 'Columns' option
    Then 'Columns' popUp is displayed
    And info: ---10. Pull in the “Maximum Quantity” column. Verify that column appears in the grid.---
    And I click on 'Maximum Quantity' option
    And 'Maximum Quantity' column is displayed
    And I click on 'Items' tab
    Then 'Columns' dropDown is not displayed
    And info: ---11. Enter “1000” in the Maximum Quantity field. Verify that field is editable and value is accepted.---
    And I set value '1000' to the cell of the column 'Maximum Quantity' of the 'Items' table for the row with the following data:
      | Item | 1 |
    Then page with name 'Items page' is opened
    And 'Items' table contain row with following data:
        | Item              | 1             |
        | Maximum Quantity  | 1,000         |
    And info: ---12. Click Save. Verify that success message “Data saved successfully” appears.---
    When I click on 'Save' button
    Then 'Data saved successfully' popUp is displayed

  @ART-212 @LM-TC-004_5 @END
  Scenario: TC-Purchase-History-Supplier-Quote-005: Enter Costing for New Cost Source History Supplier Quote
    And info: ---1. Click the Costing Coins icon for the “REG-TC004” part. Verify that user is redirected to the Costing tab.---
    And I click 'Costing Coins' in table 'Items' table in row number 1
    And page with name 'Costing page' is opened
    And info: ---2. Select the “REG View” view if not already selected. Verify that the view is available and selected.---
    Then I select 'REG View' in the 'View' dropdown
    And I click on 'Save' button
    Then text of 'View' dropdown equals 'View: REG View (shared)'
    And info: ---3. Click in the Costing Condition Dates field. Verify that the “Edit Price Validity” popup is displayed.---
    And I click 'Costing Condition Dates' in table 'Costing' table in row number 1
    Then 'Edit Price Validity' popUp is displayed
    And info: ---4. Verify contents of the Edit Price Validity popup: Valid From and Valid To fields are enabled and empty date pickers; buttons displayed are Clear Dates, Confirm, and Cancel.---
    And I verify 'Valid From' of Costing Dialog page is enabled, not required, date picker and is empty
    And I verify 'Valid To' of Costing Dialog page is enabled, not required, date picker and is empty
    And 'Clear Dates' button is displayed
    And 'Confirm' button is displayed
    And 'Cancel' button is displayed
    And info: ---5. Enter “1/1/25” in the Valid From box. Verify that value is accepted and retained.---
    When I enter data into the next fields:
      | 'Valid From' field            | 1/1/25   |
    And info: ---6. Enter “12/31/25” in the Valid To box. Verify that value is accepted and retained.---
    When I enter data into the next fields:
      | 'Valid To' field            | 12/31/25   |
    And info: ---7. Click “Clear Dates.” Verify that previously entered dates are cleared and popup collapses.---
    And I click on 'Clear Dates' button
    Then 'Edit Price Validity' popUp is not displayed
    And page with name 'Costing page' is opened
    And 'Costing' table contain row with following data:
      | Item                     | 1             |
      | Pricing Condition Dates  |               |
    And info: ---8. Click in the Costing Condition Dates field again. Verify that popup is displayed.---
    And I click 'Costing Condition Dates' in table 'Costing' table in row number 1
    Then 'Edit Price Validity' popUp is displayed
    And info: ---9. Enter “1/1/25” in the Valid From box. Verify that value is retained.---
    And I wait for 2 seconds
    When I enter data into the next fields:
      | 'Valid From' field            | 1/1/25   |
    And info: ---10. Enter “12/31/25” in the Valid To box. Verify that value is retained.---
    When I enter data into the next fields:
      | 'Valid To' field            | 12/31/25   |
    And info: ---11. Click “Confirm.” Verify that popup collapses and Costing Condition Dates are populated in the grid.---
    And I click on 'Confirm' button
    Then 'Edit Price Validity' popUp is not displayed
    And page with name 'Costing page' is opened
    And 'Costing' table contain row with following data:
      | Item                     | 1             |
      | Pricing Condition Dates  | 1/1/2025-12/31/2025 |
    And info: ---12. Enter “100” in the Price per Unit box. Verify that value is accepted and retained.---
    And I set value '100' to the cell of the column 'Price per Unit' of the 'Costing' table for the row with the following data:
      | Item | 1 |
    And info: ---13. Click the “+” icon to add a new row. Verify that a new blank entry is added to the grid.---
    Then I click '+' in table 'Costing' table for row with data:
      | Item | 1 |
    And 'Costing' table contain row with following data:
        | Item                     | 1             |
        | Pricing Condition Dates  |               |
        | Price per Unit            | /ea           |
    And info: ---14. Click in the Costing Condition Dates field for the new record. Verify that popup is displayed.---
    And I click empty cell 'Costing Condition Dates' in table 'Costing' table
    Then 'Edit Price Validity' popUp is displayed
    And info: ---15. Enter “1/1/26” in the Valid From box. Verify that value is retained.---
    When I enter data into the next fields:
      | 'Valid From' field            | 1/1/26   |
    And info: ---16. Enter “12/31/26” in the Valid To box. Verify that value is retained.---
    When I enter data into the next fields:
      | 'Valid To' field            | 12/31/26   |
    And info: ---17. Click “Confirm.” Verify that popup collapses and dates populate in the grid.---
    And I click on 'Confirm' button
    Then 'Edit Price Validity' popUp is not displayed
    And page with name 'Costing page' is opened
    And 'Costing' table contain row with following data:
      | Item                     | 1             |
      | Pricing Condition Dates  | 1/1/2025-12/31/2025 |
    And 'Costing' table contain row with following data:
      | Item                     | 1             |
      | Pricing Condition Dates  | 1/1/2026-12/31/2026 |
    And info: ---18. Enter “200” in the Price per Unit box. Verify that value is accepted and retained.---
    And I set value '200' to the cell of the column 'Price per Unit' of the 'Costing' table for the row with the following data:
      | Pricing Condition Dates  | 1/1/2026-12/31/2026 |
    And page with name 'Costing page' is opened
    And info: ---19. Click the “+” icon to add another row. Verify that new blank entry is added.---
    Then I click '+' in table 'Costing' table for row with data:
      | Pricing Condition Dates  | 1/1/2026-12/31/2026 |
    And 'Costing' table contain row with following data:
        | Item                     | 1             |
        | Pricing Condition Dates  |               |
        | Price per Unit            | /ea           |
    And info: ---20. Click in the Costing Condition Dates field for the new record. Verify that popup is displayed.---
    And I click empty cell 'Costing Condition Dates' in table 'Costing' table
    Then 'Edit Price Validity' popUp is displayed
    And info: ---21. Enter “1/1/27” in the Valid From box. Verify that value is retained.---
    When I enter data into the next fields:
      | 'Valid From' field            | 1/1/27   |
    And info: ---22. Enter “12/31/27” in the Valid To box. Verify that value is retained.---
    When I enter data into the next fields:
      | 'Valid To' field            | 12/31/27   |
    And info: ---23. Click “Confirm.” Verify that popup collapses and dates populate in the grid.---
    And I click on 'Confirm' button
    Then 'Edit Price Validity' popUp is not displayed
    And page with name 'Costing page' is opened
    And 'Costing' table contain row with following data:
      | Item                     | 1             |
      | Pricing Condition Dates  | 1/1/2025-12/31/2025 |
    And 'Costing' table contain row with following data:
      | Item                     | 1             |
      | Pricing Condition Dates  | 1/1/2026-12/31/2026 |
    And 'Costing' table contain row with following data:
      | Item                     | 1             |
      | Pricing Condition Dates  | 1/1/2027-12/31/2027 |
    And info: ---24. Enter “300” in the Price per Unit box. Verify that value is accepted and retained.---
    And I set value '300' to the cell of the column 'Price per Unit' of the 'Costing' table for the row with the following data:
      | Pricing Condition Dates  | 1/1/2027-12/31/2027 |
    And page with name 'Costing page' is opened
    And info: ---25. Click Save. Verify that success message “Data saved successfully” appears.---
    When I click on 'Save' button
    Then 'Data saved successfully' popUp is displayed
    And 'Costing' table contain row with following data:
      | Item            | 1             |
      | Price per Unit  | 100           |
    And 'Costing' table contain row with following data:
      | Item            | 1             |
      | Price per Unit  | 200           |
    And 'Costing' table contain row with following data:
      | Item            | 1             |
      | Price per Unit  | 300           |