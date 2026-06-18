@SETUP-MANUFACTURING-2-4
Feature: 1. Setup Manufacturing Proposal

  @TCM-001 @START
  Scenario: TC-Manufacturing-Proposal-001: Verify "Proposal Setup" Layout
    Given open site
    And I perform Mfg 2.4 login
    Then page with name 'Main page' is opened
    And info: ---1. Navigate to Proposals---
    Given I click on 'PROPOSALS' tab
    Then page with name 'Proposals list page' is opened
    And info: ---2. Click New to start creating new Proposal---
    Then click on 'New' button
    And page with name 'Setup page' is opened
    And info: ---3. On the Setup tab, select Regression Test Only - Consulting from Proposal Type---
    When I enter data into the next fields:
      | 'Proposal Type' field | Regression Test Only - Manufacturing |
    And page with name 'Setup page' is opened
    Then 'Description' field and 'Proposal Type' field are aligned
    Then I see the following TABS in the 'New Page' Top Menu_-Warning-_:
      | Analysis          |
      | Change Requests   |
      | Chat              |
      | Cost Structure    |
      | Deliverables      |
      | Pricing           |
      | Pursuit Milestones|
      | Pursuit Team      |
      | Schedule          |
      | Sets/Phases       |
      | Setup             |
    And info: ---4. Verify contents of Project Details section---
    And I verify 'Proposal Title' of setup page is enabled, required, free text and is empty
    And I verify 'Proposal Type' of setup page is enabled, required, drop down and has text 'Regression Test Only - Manufacturing'
    And I verify 'SAP Project' of setup page is enabled, not required, drop down and is empty
#    And I verify 'SAP Project' of setup page is enabled, not required, drop down and is empty
    And 'Open Project in SAP' link is displayed
#    And I verify 'WBS managed in SAP' of setup page is enabled, not required, radio buttons and is unchecked
    And I verify 'Estimated Project Start' of setup page is enabled, required, date picker and has text '$todayDate'
    And I verify 'Project End' of setup page is enabled, required, date picker and has text '$oneYearFromToday'
    And I verify 'Bid Manager' of setup page is enabled, not required, drop down and has text 'Suket Suman'
    And I verify 'Several Phases' of setup page is enabled, not required, radio buttons and is checked
#    And I verify 'SAP Project Profile' of setup page is enabled, not required, drop down and has text 'Z000001-ON PREM CUSTOMER PROJ PROF'
#    And I verify 'Proposal Hours' of setup page is enabled, not required, drop down and is empty
#    And I verify 'What is your Target Confidence Level' of setup page is enabled, not required, drop down and has text 'Final (0%)'
    And 'Upload RFx Files' button is displayed
    And page with name 'Setup page' is opened
    And info: ---5. Verify contents of Opportunity, CLient, & Your Organization section---
    And I verify 'Your Company' of setup page is enabled, required, drop down and is empty
    And I verify 'Leading Site or Department' of setup page is enabled, required, drop down and is empty
    And I verify 'Profit Center' of setup page is enabled, not required, drop down and is empty
#    And I verify 'Cost Center' of setup page is enabled, not required, drop down and is empty
    And I verify 'Leading Company Currency' of setup page is enabled, required, free text and has text '$ USA - US Dollar(USD)'
    And 'Edit Rates' link is displayed
#    And I verify 'Customer Currency' of setup page is enabled, required, drop down and is empty
    And I verify 'Other Departments or Sites Involved' of setup page is enabled, not required, drop down and is empty
    And I verify 'Client/Customer (Sell-to)' of setup page is enabled, required, drop down and is empty
#    And I verify 'Other Departments or Sites Involved' of setup page is enabled, not required, drop down and is empty
#    And I verify 'Labor Price Book' of setup page is enabled, not required, drop down and is empty
#    And I verify 'Customer Contact' of setup page is enabled, not required, drop down and is empty
#    And I verify 'Opportunity Status' of setup page is enabled, not required, free text and is empty
#    And I verify 'Customer Purchase order' of setup page is enabled, not required, free text and is empty
#    And I verify 'Proposal Authorization#' of setup page is enabled, not required, free text and is empty
    And I verify 'Target - Price to Win' of setup page is enabled, not required, free text and has text '0.00'
    And page with name 'Setup page' is opened
    And info: ---6. Verify Re-use Prior Proposal or Template section ---
    And I verify 'Select a template' of setup page is enabled, not required, radio buttons and is checked
    And I verify 'Select a prior project' of setup page is enabled, not required, radio buttons and is unchecked
#    And I verify 'Select a Template' of setup page is enabled, not required, drop down and is empty
    And page with name 'Setup page' is opened
    And info: ---7. Verify Project Goals or Remarks section---
    And 'Project Goals or Remarks' textbox is displayed
    When I click on 'Project Goals or Remarks' textbox
    Then 'Text edit' toolbox is displayed
    And page with name 'Setup page' is opened
    And info: ---8. Verify Tags to New Dimensions of Costs & Revenue Model ---
    And I verify 'Select Tag to Add' of setup page is enabled, not required, drop down and is empty
    And page with name 'Setup page' is opened

  @TCM-002 @RUN
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
    And info: ---14. In the dropdown field How are Labor/Item Rates Calculated select “Company Standard Bill Rates”---
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

  @TCM-003 @RUN
  Scenario: TC-Manufacturing-Proposal-003: Create an Estimate
    And info: ---1. Navigate to Sets/Phases tab---
    And click on 'Phases' tab
    Then page with name 'Phases page' is opened
    And text of 'Total Price' widget equals 'USD 0'
    And text of 'Total Cost' widget equals 'USD 0'
    And text of 'Margin as Percentage' widget equals '0 %'
    And 'Status' widget is displayed
    And text of 'Cost & Price' status equals 'Updated'
    And 'Update Cost & Prices' link is displayed
    And text of 'Status & logs' link equals 'Errors: 0 jobs, 0 checks'
    And info: ---2. If not already selected, select the view “Regression Test - Manufacturing“---
    When I select 'Regression Test View' in the 'View' dropdown
    And I click on 'Save' button
    Then text of 'View' dropdown equals 'View: Regression Test View'
    And info: ---3. Verify that The Sets/Phases grid is initialized---
    And number of rows in 'Phases' table equals 0
    And 'No records found, click here to add' button is displayed
    And info: ---4. Click the button No records found, click here to add---
    Then I click on 'No records found, click here to add' button
    And number of rows in 'Phases' table equals 1
    And 'Phases' table contain row with following data:
      | Set#        | 1                    |
      | Description |                      |
      | Start       | 4/1/25               |
      | End         | 3/31/26              |
      | Estimate    | Create               |
    And info: ---5. Enter Test Value Data into the Description field---
    And I set value 'Test Phase 1' to the cell of the column 'Description' of the 'Phases' table for the row with the following data:
      | Set#        | 1                    |
    And info: ---6. Click Save---
    And I click on 'Save' button
    And info: ---7. Click Create link in the Estimate field---
    And I click 'Estimate' in table 'Phases' table for row with data:
      | Set#        | 1                    |
    And 'Estimate' popUp is displayed
    And I wait for 7 seconds
    And text of 'Owner' EstimatePopup equals 'Suket Suman'_-Warning-_
    And I select 'Suket Suman' in the 'Owner' EstimatePopup
    And I verify 'Owner' of WBS Dialog page is enabled, required, drop down and has text 'Suket Suman'
    And I verify 'Estimating Strategy' of WBS Dialog page is enabled, required, free text and has tags 'Engineering/Functional Labor,Other Direct Costs & Factors,Proposal BOM,Consolidated BOM'
    And I verify 'Design to Cost Target' of WBS Dialog page is enabled, not required, free text and has text '0.00'
    And I verify 'DTC Target Hours' of WBS Dialog page is enabled, not required, free text and is empty
    Then 'Confirm & Release' button is displayed
    Then 'Confirm & Open' button is displayed
    Then 'Close' button is displayed
    And info: ---8. Click Confirm & Release button on the Estimate popup---
    And I click on 'Confirm & Release' button
    And 'Estimate' popUp is not displayed
    And 'Phases' table contain row with following data:
      | Set#        | 1                    |
      | Description | Test Phase 1         |
      | Start       | 4/1/25               |
      | End         | 3/31/26              |
      | Estimate    | Open                 |
    And info: ---9. Navigate to Cost Structure tab---
    Then I click on 'Cost Structure' tab
    And page with name 'Cost Structure page' is opened
    And I click on 'Reload' page
    Then I wait for 7 seconds
    And page with name 'Cost Structure page' is opened
    When I select 'TEST:-DONOT_CHANGE' in the 'View' dropdown
    And I click on 'Save' button
    Then text of 'View' dropdown equals 'View: TEST:-DONOT_CHANGE (preferred)'
    And number of rows in 'WBS' table equals 2
    And 'WBS' table contain row with following data:
      | WBS Code              |                       |
      | Cost Structure        | $uniqueProposalName   |
    And 'WBS' table contain row with following data:
      | WBS Code              | 1                   |
      | Cost Structure        | 1 Test Phase 1      |
      | Type                  | Estimate            |
      | WP Owner              | Suket Suman           |
      | Work Package          | Open                |
      | Start                 | 4/1/25              |
      | End                   | 3/31/26             |
    And the cell value of the 'WP' column of the 'WBS' table is checked for a row with the following data:
      | WBS Code              | 1                   |
    And info: ---10. Click the “+“ sign of the 2nd row and select Add (Below)---
    And I click '+' in table 'WBS' table for row with data:
      | WBS Code              | 1                   |
    And I click on 'Add Below' option
    And 'WBS' table contain row with following data:
      | WBS Code              | 1.1                 |
      | Cost Structure        | 1.1                 |
      | Type                  | WBS Element         |
    And the cell value of the 'WP' column of the 'WBS' table is unchecked for a row with the following data:
      | WBS Code              | 1.1                   |
    And info: ---11. In the Cost Structure field of the 2nd row enter Test Value Data---
    And I set value 'Test WBS 1' to the cell of the column 'Cost Structure' of the 'WBS' table for the row with the following data:
      | WBS Code              | 1.1                 |
    And page with name 'Cost Structure page' is opened
    And info: ---12. Click the “+“ sign of the 1.1 Test WBS 1 row and select Add (Below)---
    And I click '+' in table 'WBS' table for row with data:
      | WBS Code              | 1.1                  |
    And I click on 'Add Below' option
    And 'WBS' table contain row with following data:
      | WBS Code              | 1.1.1                 |
      | Cost Structure        | 1.1.1                 |
      | Type                  | WBS Element           |
    And the cell value of the 'WP' column of the 'WBS' table is unchecked for a row with the following data:
      | WBS Code              | 1.1.1                   |
    And info: ---13. Click the Type field  of the new row and select External Task value---
    And I set value 'External Task' to the cell of the column 'Type' of the 'WBS' table for the row with the following data:
      | WBS Code              | 1.1.1                   |
    And page with name 'Cost Structure page' is opened
    And info: ---14. In the Cost Structure field of the row enter Test Value Data---
    And I set value 'Task 1 for Test WBS 1' to the cell of the column 'Cost Structure' of the 'WBS' table for the row with the following data:
      | WBS Code              | 1.1.1                   |
    And page with name 'Cost Structure page' is opened
    And info: ---15. Click the “+“ sign of the 1.1.1 Task 1 for Test WBS 1 row and select Add (Current Level)---
    And I click '+' in table 'WBS' table for row with data:
      | WBS Code              | 1.1                  |
    And I click on 'Add Below' option
    And 'WBS' table contain row with following data:
      | WBS Code              | 1.1.2                 |
      | Cost Structure        | 1.1.2                 |
      | Type                  | WBS Element           |
    And the cell value of the 'WP' column of the 'WBS' table is unchecked for a row with the following data:
      | WBS Code              | 1.1.2                  |
    And info: ---16. Click the Type field  of the new row and select External Task value---
    And I set value 'External Task' to the cell of the column 'Type' of the 'WBS' table for the row with the following data:
      | WBS Code              | 1.1.2                   |
    And page with name 'Cost Structure page' is opened
    And info: ---17. In the Cost Structure field of the row enter Test Value Data---
    And I set value 'Task 2 for Test WBS 1' to the cell of the column 'Cost Structure' of the 'WBS' table for the row with the following data:
      | WBS Code              | 1.1.2                   |
    And page with name 'Cost Structure page' is opened
    And info: ---18. Click the “+“ sign of the 1.1 Test WBS 1 row and select Add (Current Level)---
    And I click '+' in table 'WBS' table for row with data:
      | WBS Code              | 1.1                |
    And I click on 'Add Current Level' option
    And 'WBS' table contain row with following data:
      | WBS Code              | 1.2                 |
      | Cost Structure        | 1.2                 |
      | Type                  | WBS Element           |
    And the cell value of the 'WP' column of the 'WBS' table is unchecked for a row with the following data:
      | WBS Code              | 1.2                  |
    And info: ---19. In the Cost Structure field of the row enter Test Value Data---
    And I set value 'Test WBS 2' to the cell of the column 'Cost Structure' of the 'WBS' table for the row with the following data:
      | WBS Code              | 1.2                   |
    And page with name 'Cost Structure page' is opened
    And info: ---20. Click the “+“ sign of the 1.2 Test WBS 2 row and select Add (Below)---
    And I click '+' in table 'WBS' table for row with data:
      | WBS Code              | 1.2                |
    And I click on 'Add Below' option
    And 'WBS' table contain row with following data:
      | WBS Code              | 1.2.1                 |
      | Cost Structure        | 1.2.1                 |
      | Type                  | WBS Element           |
    And the cell value of the 'WP' column of the 'WBS' table is unchecked for a row with the following data:
      | WBS Code              | 1.2.1                 |
    And info: ---21. Click the Type field  of the new row and select External Task value---
    And I set value 'External Task' to the cell of the column 'Type' of the 'WBS' table for the row with the following data:
      | WBS Code              | 1.2.1                 |
    And page with name 'Cost Structure page' is opened
    And info: ---22. In the Cost Structure field of the row enter Test Value Data---
    And I set value 'Task 1 for Test WBS 2' to the cell of the column 'Cost Structure' of the 'WBS' table for the row with the following data:
      | WBS Code              | 1.2.1                   |
    And page with name 'Cost Structure page' is opened
    And info: ---23. Click Save---
    Then I click on 'Save' button
    Then I wait for 7 seconds
    And info: ---24. Refresh the page---
    When I select node with name '$uniqueProposalName' in 'WBS' tree
    Then I choose in 'Cog' menu the next menu chain:
      | Expand/Collapse |
      | Expand All      |
    And I click on 'Reload' page
    Then I wait for 7 seconds
#    And page with name 'Setup page' is opened
    Then I click on 'Cost Structure' tab
    Then I wait for 3 seconds
    And page with name 'Cost Structure page' is opened
    And number of rows in 'WBS' table equals 2
    And info: ---25. Expand all collapsed rows---
    When I select node with name '$uniqueProposalName' in 'WBS' tree
    Then I choose in 'Cog' menu the next menu chain:
      | Expand/Collapse |
      | Expand All      |
    And page with name 'Cost Structure page' is opened
    Then 'WBS' table contain row with following data:
      | WBS Code       |                     |
      | Cost Structure | $uniqueProposalName   |
      | Type           |                     |
      | Start          |                     |
      | End            |                     |
    Then 'WBS' table contain row with following data:
      | WBS Code       | 1              |
      | Cost Structure | 1 Test Phase 1 |
      | Type           | Estimate       |
      | Start          | 4/1/25         |
      | End            | 3/31/26        |
    Then 'WBS' table contain row with following data:
      | WBS Code       | 1.1            |
      | Cost Structure | 1.1 Test WBS 1 |
      | Type           | WBS Element    |
      | Start          | 4/1/25              |
      | End            | 3/31/26             |
    Then 'WBS' table contain row with following data:
      | WBS Code       | 1.1.1        |
      | Cost Structure | 1.1.1 Task 1 for Test WBS 1        |
      | Type           | External Task |
      | Start          | 4/1/25              |
      | End            | 3/31/26             |
    Then 'WBS' table contain row with following data:
      | WBS Code       | 1.1.2        |
      | Cost Structure | 1.1.2 Task 2 for Test WBS 1        |
      | Type           | External Task |
      | Start          | 4/1/25              |
      | End            | 3/31/26             |
    Then 'WBS' table contain row with following data:
      | WBS Code       | 1.2          |
      | Cost Structure | 1.2 Test WBS 2          |
      | Type           | WBS Element  |
      | Start          | 4/1/25              |
      | End            | 3/31/26             |
    Then 'WBS' table contain row with following data:
      | WBS Code       | 1.2.1        |
      | Cost Structure | 1.2.1 Task 1 for Test WBS 2       |
      | Type           | External Task |
      | Start          | 4/1/25              |
      | End            | 3/31/26             |


  @TCM-004 @END
  Scenario: TC-Manufacturing-Proposal-004: Verify Estimate Layout
    And info: ---1. Click Open in the Work Package field---
    And I click 'Open' in table 'WBS' table for row with data:
      | WBS Code              | 1                   |
    Then I switch to tab 2
    Then page with name 'Main page' is opened
    And I click on 'Labor' tab
    When I select 'Regression Test - Manufacturing' in the 'View' dropdown
    And I click on 'Save' button
    Then text of 'View' dropdown equals 'View: Regression Test - Manufacturing'
    And page with name 'Estimate Labor' is opened
#    And I refresh page
#    And page with name 'Estimate Labor' is opened
    Then I see the following TABS in the 'Estimates' Top Menu_-Warning-_:
      | Assumptions |
      | Bom         |
      | Comments     |
      | Due By       |
      | History      |
      | Labor        |
      | Material     |
      | Other        |
      | Parameters   |
      | Requirements |
      | Risks         |
      | Work         |
    And info: ---2. Click Open in the Work Package field---
    And text of 'Labor' widget equals 'USD 0'
    And text of 'Travel' widget equals 'USD 0'
    And text of 'Other' widget equals 'USD 0'
    And text of 'Total Effort' widget equals '0 hours'
    And 'Status' widget is displayed
    And text of 'Cost & Price' status equals 'Updated'_-Warning-_
    And 'Update Estimate totals' link is displayed
    And info: ---3. If not already selected, select the view “Regression Test - Manufacturing“---
    When I select 'Test View for Regression' in the 'View' dropdown
    And page with name 'Estimate Labor' is opened
    When I select 'Regression Test - Manufacturing' in the 'View' dropdown
    And I click on 'Save' button
    Then text of 'View' dropdown equals 'View: Regression Test - Manufacturing (shared & preferred)'
    And info: ---4. If not already selected, select the view “Regression Test - Manufacturing“---
    And number of rows in 'Labor' table equals 0
    And 'No records found, click here to add' button is displayed
    And info: ---5. Click Other tab,If not already selected, select the view “Regression Test - Manufacturing“---
    And I click on 'Other' tab
    And page with name 'Estimate Other' is opened
    When I select 'Regression Test - Manufacturing' in the 'View' dropdown
    And I click on 'Save' button
    Then text of 'View' dropdown equals 'View: Regression Test - Manufacturing (shared & preferred)'
    And number of rows in 'Other' table equals 0
    And 'No records found, click here to add' button is displayed
    And info: ---6. Click Proposal BOM tab,If not already selected, select the view “Regression Test - Manufacturing“---
    And I click on 'Proposal BOM' tab
    And page with name 'Estimate Proposal BOM' is opened
    When I select 'Regression Test - Manufacturing' in the 'View' dropdown
    And I click on 'Save' button
    Then text of 'View' dropdown equals 'View: Regression Test - Manufacturing (shared & preferred)'
    And number of rows in 'Proposal BOM' table equals 0
    And 'No records found, click here to add' button is displayed
    And info: ---7. Click Procurement & Production tab,If not already selected, select the view “Regression Test - Manufacturing“---
    And I click on 'Procurement & Production' tab
    And page with name 'Estimate Procurement & Production' is opened
    When I select 'Regression Test - Manufacturing' in the 'View' dropdown
    And I click on 'Save' button
    Then text of 'View' dropdown equals 'View: Regression Test - Manufacturing (shared & preferred)'
    And number of rows in 'Procurement & Production' table equals 0
    And 'No records found, click here to add' button is displayed