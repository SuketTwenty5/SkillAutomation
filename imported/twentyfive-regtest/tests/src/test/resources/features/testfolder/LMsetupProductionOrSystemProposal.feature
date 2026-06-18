@LMSetupProposal
Feature: 1. Setup Production or System Proposal

  @ART-207 @LM-TC-001_1 @START
  Scenario: TC-Production-or-System-Proposal-004: Create New Proposal & Verify “Proposal Setup” Layout
    Given open site
    And I perform Mfg 2.4 login
    Then page with name 'Main page' is opened
    And I click on 'Proposals' tab
    Then page with name 'Proposals list page' is opened
    And info: ---1. Click New to start creating a new Proposal. Verify that user is redirected to the Setup tab.---
    When I click on 'New' button
    And I wait for 5 seconds
    Then page with name 'Setup page' is opened
    And info: ---2. On the Setup tab, select "Regression | Production/System Proposal" from Project Type. Verify that page updates to match the selected type and all related tabs (Setup, WBS, Workbench, etc.) are displayed.---
    When I enter data into the next fields:
      | 'Project Type' field | Regression \| Production/System Proposal |
    And I wait for 5 seconds
    Then page with name 'Setup page' is opened
    Then I see the following TABS in the 'Proposal' Top Menu_-Warning-_:
      | Advanced            |
      | Analysis            |
      | Billing Milestones  |
      | Chat                |
      | Cost Structure      |
      | Customer            |
      | Deliverables        |
      | Pricing             |
      | Pursuit Milestones  |
      | Pursuit Team        |
      | Risks               |
      | Schedule            |
      | Sets/Phases         |
      | Setup               |
    And info: ---3. Verify Setup tab. Ensure all key fields are enabled, required where applicable, and defaults such as Project Type = "Regression | Production/System" and Project Manager = current user are set.---
    And I verify 'Proposal Title' of setup page is enabled, required, free text and is empty
    And I verify 'Proposal Type' of setup page is enabled, required, drop down and has text 'Regression | Production/System Proposal'
#    And I verify 'Contract Type' of setup page is enabled, not required, drop down and is empty
#    And I verify 'SAP Project' of setup page is enabled, not required, drop down and is empty
    And 'Open Project in SAP' link is displayed
#    And I verify 'SAP WBS' of setup page is disabled, not required, free text and is empty
#    And I verify 'WBS managed in SAP' of setup page is enabled, not required, radio buttons and is unchecked
#    And I verify 'Project Start' of setup page is enabled, required, date picker and is empty
#    And I verify 'Project End' of setup page is enabled, required, date picker and is empty
    And I verify 'Bid Manager' of setup page is enabled, not required, drop down and has text 'Suket Suman'
    And I verify 'Several Phases' of setup page is enabled, not required, radio buttons and is checked
#    And I verify 'SAP Project Profile' of setup page is enabled, not required, drop down and is empty
#    And I verify 'Proposal Hours' of setup page is enabled, not required, drop down and is empty
#    And I verify 'Target Confidence Level' of setup page is enabled, not required, drop down and has text 'Final (0%)'
    And 'Upload RFx Files' button is displayed
    And page with name 'Setup page' is opened
    And info: ---4. Verify Setup tab. Ensure dropdowns are enabled, required fields are empty, "$ USA - US Dollar (USD)" is selected for Company Currency, and all expected fields and links are visible.---
#    And I verify 'Business Area' of setup page is enabled, required, drop down and is empty
#    And I verify 'Plant or Site' of setup page is enabled, not required, drop down and is empty
#    And I verify 'Company Currency' of setup page is disabled, required, drop down and is empty
#    And I verify 'Cost Center' of setup page is enabled, not required, drop down and is empty
    And I verify 'Leading Company Currency' of setup page is enabled, required, free text and has text '$ USA - US Dollar(USD)'
    And 'Edit Rates' link is displayed
#    And I verify 'Project Currency' of setup page is enabled, required, drop down and is empty
#    And I verify 'Other Departments or Sites Involved' of setup page is enabled, not required, drop down and is empty
#    And I verify 'Client/Customer (Sell-to)' of setup page is enabled, required, drop down and is empty
    And 'Edit Role or Alt Cust' link is displayed
#    And I verify 'CRM Opportunity' of setup page is enabled, not required, drop down and is empty
#    And I verify 'Labor Price Book' of setup page is enabled, not required, drop down and is empty
#    And I verify 'Customer Contact' of setup page is enabled, not required, drop down and is empty
#    And I verify 'Customer Purchase order' of setup page is enabled, not required, free text and is empty
#    And I verify 'Proposal Authorization#' of setup page is enabled, not required, free text and is empty
#    And I verify 'Target - Price to Win' of setup page is enabled, not required, free text and has text '$'
    And page with name 'Setup page' is opened
    And info: ---5. Verify "How do you want to Start your Project Plan" section. Ensure "Select a Template" is selected by default and dropdown fields are enabled and empty.---
    And I verify 'Select a template' of setup page is enabled, not required, radio buttons and is checked
    And I verify 'Select a prior project' of setup page is enabled, not required, radio buttons and is unchecked
#    And I verify 'Select a template' of setup page is enabled, not required, drop down and is empty
    And page with name 'Setup page' is opened
    And info: ---6. Verify Project Goals or Remarks section. Ensure free text field is enabled and rich text editor appears on click.---
    And 'Project Goals or Remarks' textbox is displayed
    When I click on 'Project Goals or Remarks' textbox
    Then 'Text edit' toolbox is displayed
    And page with name 'Setup page' is opened
    And info: ---7. Verify Use Tags to add New Dimensions section. Ensure "Select a Tag to Add" dropdown is enabled and empty.---
    And I verify 'Select Tag to Add' of setup page is enabled, not required, drop down and is empty
    And page with name 'Setup page' is opened

  @ART-208 @LM-TC-001_2 @END
  Scenario: TC-Production-or-System-Proposal-005: Fill in Required Fields & Validate Save
    And info: ---1. Click Save without editing any fields. Verify that an error message appears, and all required fields are highlighted in red (Title, Start, End, Leading Company, Site, Currency, Client).---
    When I click on 'Save' button
    And 'Error' dialog is displayed
    And I hover on 'Error' dialog
    Then text of 'Error' dialog equals_-Warning-_:
    """
    Proposal Title * is required in Setup tab
    Your Company * is required in Setup tab
    Leading Site or Department * is required in Setup tab
    Project Currency * is required in Setup tab
    """
    And verify border-color css of 'Error' dialog is 'red'_-Warning-_
    And verify border-color css of 'Title or Brief Description' field is 'red'_-Warning-_
#    And verify border-color css of 'Project Start' field is 'red'
#    And verify border-color css of 'Project End' field is 'red'
    And verify border-color css of 'Leading Company' field is 'red'_-Warning-_
    And verify border-color css of 'Leading Site or Department' field is 'red'_-Warning-_
    And verify border-color css of 'Project Currency' field is 'red'_-Warning-_
#    And verify border-color css of 'Client Customer sell-to' field is 'red'_-Warning-_
    And info: ---2. Fill Proposal Title with a valid value (1–255 chars). Verify field accepts value. Example: TC-Regression-Production/System-Proposal <YYYYMMDD>.---
    Given generate values and store into the variables:
      | $uniqueProposalName | TC-Regression-Production/System-Proposal ${CUR_DATE,yyyy-MM-dd hh:mm} |
    When I enter data into the next fields:
      | 'Title or Brief Description' field | $uniqueProposalName |
    Then page with name 'Setup page' is opened
    And info: ---3. Select SAP Project dropdown and type MFG01 ---
#    When I enter data into the next fields:
#      | 'SAP Project' field | MFG0              |
#    Then page with name 'Setup page' is opened
    And info: ---4. Enter Project Start date (e.g., 4/1/25). Verify field accepts value and Project End auto-populates (1/1/27).---
    When I enter data into the next fields:
      | 'Project Start' field | 1/1/27              |
    Then page with name 'Setup page' is opened
    Then text of 'Project Start' field equals '1/1/27'
    And info: ---5. Set Project End before Project Start (e.g., 12/31/25). Verify value not accepted and border turns red.---
    When I enter data into the next fields:
      | 'Project End' field | 12/31/25              |
    Then page with name 'Setup page' is opened
    And verify border-color css of 'Project End' field is 'red'
    And info: ---6. Update Project End to later date (e.g., 12/31/30). Verify field accepts value.---
    When I enter data into the next fields:
      | 'Project End' field | 12/31/30              |
    Then page with name 'Setup page' is opened
    And info: ---7. Select “Regression Test” from Leading Company dropdown. Verify field populated successfully.---
    When I enter data into the next fields:
      | 'Leading Company' field | A&D - Missiles & Armaments    |
    Then page with name 'Setup page' is opened
    And info: ---8. Expand Leading Site/Department dropdown. Verify “RG10 US - New York” is available and selectable.---
    When I enter data into the next fields:
      | 'Leading Site or Department' field | US - Tucson, AZ   |
    Then page with name 'Setup page' is opened
    And info: ---9. Review Company Currency field. Verify non-editable with value "$ USA - US Dollar (USD)".---
    And I verify 'Leading Company Currency' of setup page is enabled, required, free text and has text '$ USA - US Dollar(USD)'
    And info: ---10. In Client/Customer dropdown, type “REG” and select “RTC1 Regression Test - Customer USD”. Verify selection is retained.---
    When I enter data into the next fields:
      | 'Client Customer sell-to' field | Regression Test - Customer USD  |
    Then page with name 'Setup page' is opened
#    And info: ---11. Click in Proposal Authorization # and type 1234567 ---
#    When I enter data into the next fields:
#      | 'Proposal Authorization#' field | 1234567   |
#    Then page with name 'Setup page' is opened
    And info: ---12. Click dropdown while “Select a template” is enabled. Verify options are displayed.---
    When I click on 'Select a Template' drop down
    Then 'Select a Template' options is displayed
    And info: ---13. Enable “Select a prior proposal” radio button. Verify radio activates.---
    When I click on 'Select a prior proposal' radio button
    And I wait for 2 seconds
#    Then 'Select a prior proposal' radio button is enabled
    And info: ---14. Click dropdown while “Select a prior proposal” is enabled. Verify options are displayed.---
    When I click on 'Select a prior proposal' dropdown
    And I wait for 2 seconds
    And 'Select a prior proposal' options is displayed
    And info: ---15. Enter “Test Data Value” in Project Goals/Remarks. Verify text supports rich formatting (bold, italic, etc.).---
    When I enter data into the next fields:
      | 'Project Goals or Remarks' field | Test Project Description   |
    Then page with name 'Setup page' is opened
    And info: ---16. Open Tags dropdown. Verify options are available.---
    When I click on 'Select Tag to Add' drop down
    Then 'Select Tag to Add' options is displayed
    And info: ---17. Select “Model” tag. Verify field populated and Delete link appears.---
    And I click on 'REGTAG' option
    Then I press escape
    And 'REGTAG' field is displayed
    Then page with name 'Setup page' is opened
    And 'Delete' link is displayed
    And info: ---18. Click Save. Verify success message “Data saved successfully” appears.---
    When I click on 'Save' button
    And 'Data saved successfully' warning message is displayed in 30 seconds
    And info: ---19. Review Proposal Header. Verify Proposal Number (*) is displayed.---
    And 'Proposal Number' in Proposal Header is displayed
    And info: ---20. Review page URL. Verify Proposal ID appended at end of URL.---
    And I verify current page URL contains "#(proposal|quote):[0-9a-fA-F\\-]{36}"
    Then I wait for 7 seconds
    And page with name 'Setup page' is opened
    And info: ---21. Navigate to Proposals page. Verify page loads successfully.---
    When I click on 'Proposals' tab
    Then page with name 'Proposals list page' is opened
    Then I wait for 7 seconds
    Then page with name 'Proposals list page' is opened
    When I select 'Test View | Do Not Edit' in the 'Proposal View' dropdown
    Then page with name 'Proposals list page' is opened
    And info: ---22. Click Owner column chevron. Verify filter options appear.---
    And I hover on 'Owner' column header
    And I click on 'Owner' column chevron
    Then 'Column' header options is displayed
    And info: ---23. Click Columns. Verify options are displayed.---
    When I click on 'Columns' option
    Then 'Columns' options is displayed
    And info: ---24. Select Created On column. Verify column appears in table.---
    And I click on 'Created On' option
    And I click on 'Owner' column header
    Then 'Created On' column is displayed
    And info: ---25. Click Created On column. Verify sort order changes to ASC.---
    And info: ---26. Click Created On again. Verify sort order changes to DESC.---
    Then I verify sorting in 'Created On' column of proposal table
    And info: ---27. Open the newly created proposal. Verify all entered data is retained.---
    When I click on 'plus' searchIcon
    When I select 'Proposal' in the 'Filter by' dropdown
    Then I enter text '$uniqueProposalName' in the 'Filter' field
    And press enter
    And table row is displayed with the name $uniqueProposalName
    And I select table row with name $uniqueProposalName
    And I click on 'Setup' tab
    Then page with name 'Setup page' is opened
    And I verify 'Proposal Title' of setup page is enabled, required, free text and has text '$uniqueProposalName'
    And I verify 'Estimated Project Start' of setup page is enabled, required, date picker and has text '1/1/2027'
    And I verify 'Project End' of setup page is enabled, required, date picker and has text '12/31/2030'
#    And I verify 'Business Area' of setup page is enabled, required, drop down and has text 'Missiles & Fire Control'
#    And I verify 'Leading Site or Department' of setup page is enabled, required, drop down and has text 'US - New York'
#    And I verify 'Project Currency' of setup page is enabled, required, drop down and has text '$ USA - US Dollar(USD)'
#    And I verify 'Client/Customer (Sell-to)' of setup page is enabled, required, drop down and has text 'Regression Test - Customer USD (RTC1)'
#    Then text of 'Project Goals or Remarks' field equals 'Test Project Description'