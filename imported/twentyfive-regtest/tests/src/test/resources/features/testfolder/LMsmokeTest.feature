@ART-79 @LM-SMOKE-TEST
Feature: 0. Smoke Test

  @ART-196 @START
  Scenario: TC-Production/System-Proposal-001: Verify User Login
    And info: -----------------1. LOGIN ----------------------
    Given open site
    And I perform Mfg 2.4 login
    Then page with name 'Main page' is opened

  @ART-197 @RUN
  Scenario: TC-Production/System-Proposal-002: Application Configuration Validation
    And info: ---1. Verify the application configuration. Verify that tabs available are: Inbox, Proposals, Estimates, Cost Analysis, Risk Mgt, Material Cost Estimates (with dropdown), Master Data (with dropdown), Setup (with dropdown), User (e.g., Denise Bowes) (with dropdown), and Help. Also verify that Search icon, Refresh icon, and Hamburger menu are present.---
    Then top menu contains items:
      | PROPOSALS |
      | ESTIMATES |
      | COST/REV ANALYSIS |
      | SOURCING & COST HISTORY |
      | RISKS |
      | MASTER DATA |
      | SETUP |
      | Suket Suman |
      | HELP |
    And 'Search' icon is displayed
    And 'Refresh' icon is displayed
#    And 'Hamburger' menu is displayed
    And info: ---2. Click on Material Cost Estimates to verify sub-options. Verify that available options are: Material Cost Estimates, Cost Source History, Sourcing & Send to Ariba, Production Order History, Supplier RFX/Sourcing.---
    When I click on 'Material Cost Estimates' tab
    Then 'Material Cost Estimates' options is displayed
    Then top menu 'Material Cost Estimates' contains items:
        | Supplier RFPs |
        | Purchase History     |
        | Production History     |
        | Material Cost Estimates |
        | Old Sourcing App (do not use) |
    When I click on 'Material Cost Estimates' tab
    Then page with name 'Main page' is opened
    And info: ---3. Click on Master Data to verify sub-options. Verify that available options are: Products & Services, Customers, Suppliers, Price Book Types, Resource Groups & Rates, Formulas, Tags, Users, Organizational Structure.---
    And I click on 'Master Data' tab
    Then 'Master Data' options is displayed
    Then top menu 'Master Data' contains items:
        | Products & Services        |
        | Project Templates          |
        | Labor Resource Groups      |
        | Customers                  |
        | Suppliers                  |
        | Price Books                |
        | Formulas                   |
        | Tags                       |
        | Organizational Structure   |
        | Tax Rates                  |
        | Update Forecast Cycle      |
        | Users                      |
    And I click on 'Master Data' tab
    Then page with name 'Main page' is opened
    And info: ---4. Click on Setup to verify sub-options. Verify that available options are: Proposal Types, Project Types, WBS / Task Types, Estimating Method, Risk Type, Risk Policy, Product Type, Screen Controls, Output Form Layouts, Workflow Templates, System Administration, Edit Import/Export Mapping.---
    And I click on 'SETUP' tab
    Then 'SETUP' options is displayed
    Then top menu 'Setup' contains items:
        | Screen Layout |
        | Workflow Templates |
        | Proposal Types |
        | WBS/Task Types |
        | Costing & Pricing Methods |
        | Product Types |
        | Risk Types |
        | Risk Policy |
        | Output Form Layouts |
        | Data Import & Export Mappings |
        | System Administration |
    And I click on 'SETUP' tab
    Then page with name 'Main page' is opened
    And info: ---5. Click on User (e.g., Denise Bowes) to verify sub-options. Verify that available options are: Edit My Profile, Sign Out.---
    And I click on 'Users' tab
    Then 'Users' options is displayed
    Then top menu 'Users' contains items:
        | Edit My Profile |
        | Sign Out        |
    And I click on 'Users' tab
    Then page with name 'Main page' is opened

  @ART-195 @END
  Scenario: TC-Production/System-Proposal-003: Navigate to Proposals & Review List
    And info: ---1. Navigate to Proposals. Verify that module loads as expected and proposal list is displayed.---
    When I click on 'Proposals' tab
    Then page with name 'Proposals list page' is opened
    And info: ---2. Validate available options. Verify that "+" sign to apply filter, View dropdown, Filter icon, and Download icon are present.---
    Then 'plus' searchIcon is displayed
    And 'List View' dropdown is displayed
    And 'Filter' icon is displayed
    And 'Download' icon is displayed
    And info: ---3. Hover on the Download icon. Verify that options available are "Download to Excel" and "Download to PDF".---
    And I hover on 'Download' icon
    Then 'Download' options contains items:
        | Download to Excel |
        | Download to PDF   |
    And info: ---4. Click on the "+" icon. Verify that dropdown displays.---
    When I click on 'plus' searchIcon
    Then 'Filter by' dropdown is displayed
    And info: ---5. Click on the dropdown for the filter. Verify that options are available in the dropdown.---
    When I click on 'Filter by' dropDownArrow
    Then 'Filter by' options is displayed
    And info: ---6. Select "Owner" as the filter. Verify that dropdown is populated with Owner and a second dropdown appears.---
    When I select 'Owner' in the 'Filter by' dropdown
    Then text of 'Filter by' dropdown equals 'Owner'
    And info: ---7. Click on the second dropdown for the filter. Verify that options are available in the dropdown.---
    When I click on 'Owner' dropDownArrow
    Then 'Filter by' options is displayed
    And info: ---8. Start typing "Allen" as the Owner. Verify that list only displays users with the name "Allen".---
    Then I enter text 'Allen' in the 'Filter' field
    Then In 'Filter by' options all contains text 'Allen'
    And info: ---9. Select "Allen Azar" from the dropdown list. Verify that dropdown is now populated with the Owner as "Allen Azar" and proposals list is filtered with "Allen Azar" as the Owner.---
    And I click on 'Allen Azar' option
    And I click on 'Filter' field
    Then text of 'Filter' selectedTags equals 'Allen Azar'
    Then In 'Owner' column all contains text 'Allen Azar'