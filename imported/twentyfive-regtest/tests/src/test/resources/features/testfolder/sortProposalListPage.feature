@NEW-1 @ALL @SMOKE-TEST
Feature: 0. Smoke Test

  Background:
    And info: ----------------------- Background ----------------------
    Given open site
    And I perform login
    Then page with name 'Main page' is opened

  @SORT-PROPOSAL
  Scenario: Check sorting in proposal list page
    And info: ----------------------- Check sorting in proposal list page ----------------------
    And I click on 'PROPOSALS' tab
    Then page with name 'Proposals list page' is opened
    When I select 'Default (shared' in the 'Proposal View' dropdown
    Then page with name 'Proposals list page' is opened
    And info: ----- Verify that 'Default (shared)' is selected in the 'Proposal View' dropdown
#    Then text of 'Proposal View' dropdown equals 'View: Default (shared)'
    Then I verify sorting in 'Proposal' column of proposal table
    Then I verify sorting in 'Created On' column of proposal table
    Then I verify sorting in 'Price' column of proposal table
    Then I verify sorting in 'Cost' column of proposal table

  @RESCHEDULE-PROPOSAL
  Scenario: Reschedule Proposal
    And info: ----- Reschedule Proposal ----------------------
    Given generate values and store into the variables:
      | $uniqueProposalName | TEST Reschedule Proposal ${CUR_DATE,yyyy-MM-dd hh:mm} |
    And I click on 'PROPOSALS' tab
    Then page with name 'Proposals list page' is opened
    And I make a new proposal of type Regression Test Only - Consulting
    And page with name 'Setup page' is opened
    When I enter data into the next fields:
      | 'Planned Start' field | 1/1/2026              |
      | 'Description' field   | $uniqueProposalName |
      | 'End' field           | 12/31/2031            |
      | 'Several' radio       | check               |
    And page with name 'Setup page' is opened
    When I enter data into the next fields:
      | 'Capability' field              | Regression Test                |
      | 'Leading Site' field            | US - New York                  |
#      | 'Project Currency' field        | USD                            |
    And I if visible click on 'Confirmation Yes' button
    And page with name 'Setup page' is opened
    When I enter data into the next fields:
      | 'Client Customer sell-to' field        | Regression Test - Customer USD |
    And page with name 'Setup page' is opened
#    And text of 'Client Customer sell-to' field equals 'MONTEREY MUSHROOMS INC (MONTERY)'
#    And page with name 'Setup page' is opened
    And I check 'Select a template' radio
    And page with name 'Setup page' is opened
    And info: ----- Select Template and Copy ----------------------
    When I enter data into the next fields:
      | 'Search for Template from Library' field | Regression Test Template 1 |
    And click on 'Copy' button
    And page with name 'Setup page' is opened
    When I enter data into the next fields:
      | 'Planned Start' field | 1/1/2028              |
    Then I click on 'Save' button
    And page with name 'Setup page' is opened
    And info: ----- Reschedule Proposal ----------------------
    Then I click on 'Hamburger' menu
    Then I click on 'Reschedule Proposal' menuItem
    Then 'Reschedule Project' window is displayed
    Then I click on 'Keep Exact Dates' radioButton
    Then I wait for 7 seconds
    Then I click on 'Reschedule' button
    Then page with name 'Setup page' is opened
    Then I click on 'WBS' tab
    Then page with name 'WBS page' is opened
    When I select 'TEST:-DONOT_CHANGE' in the 'View' dropdown
    And I click on 'Save' button
    Then text of 'WBS View' dropdown equals 'View: TEST:-DONOT_CHANGE (preferred)'
    And 'WBS' table contain row with following data:
      | WBS                   | 1 Estimate               |
      | Start                 | 1/1/28                   |
      | End                   | 12/31/31                 |

    @VERIFY-CUSTOMERS
    Scenario: Verify Customers in proposal setup page
      And info: ----------------------- Verify Customers in proposal setup page ----------------------
      Given generate values and store into the variables:
        | $uniqueProposalName | TEST ${CUR_DATE,yyyy-MM-dd hh:mm} |
      And I click on 'PROPOSALS' tab
      Then page with name 'Proposals list page' is opened
      And I make a new proposal of type Regression Test Only - Consulting
      And page with name 'Setup page' is opened
      And info: ----- Verify Customers ----------------------
      Then I click on 'Client Customer sell-to' dropdown
      When I enter data into the next fields:
        | 'Planned Start' field | 1/1/2026              |
        | 'Description' field   | $uniqueProposalName |
        | 'End' field           | 12/31/2031            |
        | 'Several' radio       | check               |
      And page with name 'Setup page' is opened
      When I enter data into the next fields:
        | 'Capability' field              | Regression Test                |
        | 'Leading Site' field            | US - New York                  |
        | 'Profit Center' field           | Products                       |
      Then I wait for 3 seconds
      And info: ----- Verify Customers ----------------------
      Then I click on 'Client Customer sell-to' dropdown
      Then I wait for 7 seconds
      Then I verify list of all customers

  @CREATE-CONSULTING-PROPOSAL
  Scenario: Create Consulting Proposal
  1. Create Proposal            |
  2. Review Phases              |
  3. Change Dates in Phases     |
  4. Change date in WBS         |
  5. Release Estimate           |
  6. Add Labors                 |
  7. Remove Escalation/Inflation|
  8. Verify Cost Analysis       |
    And info: ----------------------- Create Consulting Proposal ----------------------
    Given generate values and store into the variables:
      | $uniqueProposalName | TEST ${CUR_DATE,yyyy-MM-dd hh:mm} |
    And I click on 'PROPOSALS' tab
    Then page with name 'Proposals list page' is opened
    And I make a new proposal of type Regression Test Only - Consulting
    And page with name 'Setup page' is opened
    When I enter data into the next fields:
      | 'Planned Start' field | 1/1/2026              |
      | 'Description' field   | $uniqueProposalName |
      | 'End' field           | 12/31/2031            |
      | 'Several' radio       | check               |
    And page with name 'Setup page' is opened
    When I enter data into the next fields:
      | 'Capability' field              | Regression Test                |
      | 'Leading Site' field            | US - New York                  |
#      | 'Project Currency' field        | USD                            |
    And page with name 'Setup page' is opened
    And I if visible click on 'Confirmation Yes' button
    And page with name 'Setup page' is opened
    And 'Confirmation Yes' button is not displayed
    When I enter data into the next fields:
      | 'Client Customer sell-to' field        | Regression Test - Customer USD |
    And page with name 'Setup page' is opened
#    And text of 'Client Customer sell-to' field equals 'MONTEREY MUSHROOMS INC (MONTERY)'
#    And page with name 'Setup page' is opened
    And I check 'Select a template' radio
    And page with name 'Setup page' is opened
    When I enter data into the next fields:
      | 'Search for Template from Library' field | Regression Test Template 1 |
    And click on 'Copy' button
    And page with name 'Setup page' is opened
    And I store relative url of app in '$uniqueProposalSetupRelative'
    And click on 'Phases' tab
    And info: ------- Review Phases --------------
#    And info: Verify that phases are copied from Template
    Then page with name 'Phases page' is opened
    And 'Phases' table is displayed
    And 'Phases' table contain row with following data:
      | Description | Design & Build Phase |
    And 'Phases' table contain row with following data:
      | Description | Transition Phase |
    And 'Phases' table contain row with following data:
      | Description | Run & Maintain Phase |
    And info: ------- Set dates in Phases --------------
#    And info: Set the start and end dates for each phase
    When I select 'Regression Test - Manufacturing' in the 'View' dropdown
    And I click on 'Save' button
    Then text of 'View' dropdown equals 'View: Regression Test - Manufacturing (shared)'
    And I set value '1/1/2026' to the cell of the column 'Start' of the 'Phases' table for the row with the following data:
      | Description | Design & Build Phase |
    And I set value '6/30/2027' to the cell of the column 'End' of the 'Phases' table for the row with the following data:
      | Description | Design & Build Phase |

    And I set value '7/1/2028' to the cell of the column 'Start' of the 'Phases' table for the row with the following data:
      | Description | Transition Phase |
    And I set value '12/31/2029' to the cell of the column 'End' of the 'Phases' table for the row with the following data:
      | Description | Transition Phase |

    And I set value '1/1/2030' to the cell of the column 'Start' of the 'Phases' table for the row with the following data:
      | Description | Run & Maintain Phase |
    And I set value '12/31/2031' to the cell of the column 'End' of the 'Phases' table for the row with the following data:
      | Description | Run & Maintain Phase |
    And I click on 'Save' button
    And 'Phases' table is displayed

    And info: Verify phases
    And I verify phases page dates
#    And 'Phases' table contain row with following data:
#      | Description | Design & Build Phase |
#      | Start       | 01/01/2026           |
#      | End         | 06/30/2027           |
#
#    And 'Phases' table contain row with following data:
#      | Description | Transition Phase   |
#      | Start       | 07/1/2028          |
#      | End         | 12/31/2029         |
#
#    And 'Phases' table contain row with following data:
#      | Description | Run & Maintain Phase |
#      | Start       | 01/01/2030           |
#      | End         | 12/31/2031           |

    And info: --Set WBS: Release Estimate --------
    Then I click on 'WBS' tab
    Then page with name 'WBS page' is opened
#    Then Debug test
    When I select 'TEST:-DONOT_CHANGE' in the 'WBS View' dropdown
    And I click on 'Save' button
    Then text of 'WBS View' dropdown equals 'View: TEST:-DONOT_CHANGE (preferred)'

    When I select node with name '$uniqueProposalName' in 'WBS' tree
    Then I choose in 'Cog' menu the next menu chain:
      | Expand/Collapse |
      | Expand All      |
    And page with name 'WBS page' is opened
    And info: Set the start and end dates for each line in WBS table
    And I set value '1/1/2026' to the cell of the column 'Start' of the 'WBS' table for the row with the following data:
      | WBS | 1.1 Design & Build Phase |
    And I set value '6/30/2027' to the cell of the column 'End' of the 'WBS' table for the row with the following data:
      | WBS | 1.1 Design & Build Phase |

    And I set value '1/1/2026' to the cell of the column 'Start' of the 'WBS' table for the row with the following data:
      | WBS | 1.1.1 Design WBS |
    And I set value '6/30/2027' to the cell of the column 'End' of the 'WBS' table for the row with the following data:
      | WBS | 1.1.1 Design WBS |

    And I set value '1/1/2026' to the cell of the column 'Start' of the 'WBS' table for the row with the following data:
      | WBS | 1.1.2 Build WBS |
    And I set value '6/30/2027' to the cell of the column 'End' of the 'WBS' table for the row with the following data:
      | WBS | 1.1.2 Build WBS |


    And I set value '7/1/2028' to the cell of the column 'Start' of the 'WBS' table for the row with the following data:
      | WBS | 1.2 Transition Phase |
    And I set value '12/31/2029' to the cell of the column 'End' of the 'WBS' table for the row with the following data:
      | WBS | 1.2 Transition Phase |

    And I set value '7/1/2028' to the cell of the column 'Start' of the 'WBS' table for the row with the following data:
      | WBS | 1.2.1 Cut Over WBS |
    And I set value '12/31/2029' to the cell of the column 'End' of the 'WBS' table for the row with the following data:
      | WBS | 1.2.1 Cut Over WBS |

    And I set value '7/1/2028' to the cell of the column 'Start' of the 'WBS' table for the row with the following data:
      | WBS | 1.2.2 Hypercare WBS |
    And I set value '12/31/2029' to the cell of the column 'End' of the 'WBS' table for the row with the following data:
      | WBS | 1.2.2 Hypercare WBS |


    And I set value '1/1/2030' to the cell of the column 'Start' of the 'WBS' table for the row with the following data:
      | WBS | 1.3 Run & Maintain |
    And I set value '12/31/2031' to the cell of the column 'End' of the 'WBS' table for the row with the following data:
      | WBS | 1.3 Run & Maintain |

    And I set value '1/1/2030' to the cell of the column 'Start' of the 'WBS' table for the row with the following data:
      | WBS | 1.3.1 Set-up WBS |
    And I set value '12/31/2031' to the cell of the column 'End' of the 'WBS' table for the row with the following data:
      | WBS | 1.3.1 Set-up WBS |

    And I set value '1/1/2030' to the cell of the column 'Start' of the 'WBS' table for the row with the following data:
      | WBS | 1.3.2 Run WBS |
    And I set value '12/31/2031' to the cell of the column 'End' of the 'WBS' table for the row with the following data:
      | WBS | 1.3.2 Run WBS |
    And I click on 'Save' button
    And page with name 'WBS page' is opened
    And I verify wbs page dates

    And I click 'Open' in table 'WBS' table for row with data:
      | WBS | 1 Estimate |
#    Then 'Release Basis of Estimate' window is displayed
#    And I click on 'Yes' button
    And page with name 'WBS page' is opened
#
#    And info: --Success – Part I Complete --------
#    And info: ------ PPT Slide 11-----------------
#    And page with name 'WBS page' is opened
#    And I close current browser tab
#    Then switch to one of the browser tabs 'Twenty5 iPE: Estimate' or 'Twenty5 iPE: Estimates'
    Then I switch to tab 2
    And info: Labor: Add Phase 1 Labor Line Items
#    And info: ------ PPT Slide 3-----------------
    And I click on 'Labor' tab
    And page with name 'Estimate Labor' is opened
    When I select 'Test View for Regression' in the 'View' dropdown
    And I click on 'Save' button
    Then text of 'View' dropdown equals 'View: Test View for Regression - DONOT CHANGE (preferred)'
    And info: Change planning mode to Manual(Monthly)
#    And info:  ------ PPT Slide 9-----------------
    Then I choose in 'Cog Settings' menu the next menu chain:
      | Change Planned Effort Input Mode |
      | Hours by Month                   |
    And page with name 'Estimate Labor' is opened
    Then 'Change Planning Mode' window is displayed
    And I click on 'Yes' button
    And page with name 'Estimate Labor' is opened

    And info: Labor: Add Labor Line Items REG_CONSULTANT2
#    Then I click on 'Reload' page
#    And page with name 'Estimate Labor' is opened
    Then I click on 'Click here to add' link
    And page with name 'Estimate Labor' is opened
    And I set value 'RG01 Regression Test' to the cell of the column 'Company' of the 'Labor' table for the row with the following data:
      | Sequence | 1 |
    And I set value 'REG_CONSULTANT2' to the cell of the column 'Resource Group' of the 'Labor' table for the row with the following data:
      | Sequence | 1 |
    And I set value 'Flat (working days per month)' to the cell of the column 'Distribution' of the 'Labor' table for the row with the following data:
      | Sequence | 1 |
    And I set value '1' to the cell of the column 'FTE' of the 'Labor' table for the row with the following data:
      | Sequence | 1 |
#    And I set value '1/1/26' to the cell of the column 'Start' of the 'Labor' table for the row with the following data:
#      | Sequence | 1 |
#    And I set value '1/1/27' to the cell of the column 'End' of the 'Labor' table for the row with the following data:
#      | Sequence | 1 |
#    And I set value '1/1/2026' to the cell of the column 'Source Date' of the 'Labor' table for the row with the following data:
#      | Sequence | 1 |
    Then click on 'Save' button
    And page with name 'Estimate Labor' is opened

    Then I switch to tab 1
    Then I click on 'Setup' tab
    And page with name 'Setup page' is opened
    Then Turn off Escalation

    Then I switch to tab 2
    And I click on 'Labor' tab
    And page with name 'Estimate Labor' is opened
    When I select 'Test View for Regression - DONOT CHANGE' in the 'View' dropdown
    And I click on 'Save' button
    Then text of 'View' dropdown equals 'View: Test View for Regression - DONOT CHANGE (preferred)'

    And info: Labor: Add Labor Line Items REG_PROJECTMANAGER
    And I click '+' in table 'Labor' table for row with data:
      | Sequence | 1 |
    And I set value '1.2.1 Cut Over WBS' to the cell of the column 'WBS' of the 'Labor' table for the row with the following data:
      | Sequence | 2 |
    And I set value 'RG01 Regression Test' to the cell of the column 'Company' of the 'Labor' table for the row with the following data:
      | Sequence | 2 |
    And I set value 'RG10 US - New York' to the cell of the column 'Dept' of the 'Labor' table for the row with the following data:
      | Sequence | 2 |
    And I set value 'REG_PROJECTMANAGER' to the cell of the column 'Resource Group' of the 'Labor' table for the row with the following data:
      | Sequence | 2 |
    And I set value 'Flat (working days per month)' to the cell of the column 'Distribution' of the 'Labor' table for the row with the following data:
      | Sequence | 2 |
    And I set value '1000' to the cell of the column 'Effort' of the 'Labor' table for the row with the following data:
      | Sequence | 2 |
#    And I set value '7/1/26' to the cell of the column 'Start' of the 'Labor' table for the row with the following data:
#      | Sequence | 2 |
#    And I set value '12/31/2028' to the cell of the column 'End' of the 'Labor' table for the row with the following data:
#      | Sequence | 2 |
#    And I set value '1/1/26' to the cell of the column 'Source Date' of the 'Labor' table for the row with the following data:
#      | Sequence | 2 |
    Then click on 'Save' button
    And I wait for 15 seconds

    And info: Labor: Add Labor Line Items REG_QAMANAGER
    And I click '+' in table 'Labor' table for row with data:
      | Sequence | 2 |
    And I set value '1.3.1 Set-up WBS' to the cell of the column 'WBS' of the 'Labor' table for the row with the following data:
      | Sequence | 3 |
    And I set value 'RG01 Regression Test' to the cell of the column 'Company' of the 'Labor' table for the row with the following data:
      | Sequence | 3 |
    And I set value 'RG10 US - New York' to the cell of the column 'Dept' of the 'Labor' table for the row with the following data:
      | Sequence | 3 |
    And I set value 'REG_QAMANAGER' to the cell of the column 'Resource Group' of the 'Labor' table for the row with the following data:
      | Sequence | 3 |
    And I set value '1' to the cell of the column 'FTE' of the 'Labor' table for the row with the following data:
      | Sequence | 3 |
    And I set value 'Manual (Monthly)' to the cell of the column 'Distribution' of the 'Labor' table for the row with the following data:
      | Sequence | 3 |
    And I wait for 3 seconds
#    And I set value '1/1/2030' to the cell of the column 'Start' of the 'Labor' table for the row with the following data:
#      | Sequence | 3 |
#    And page with name 'Estimate Labor' is opened
#    Then click on 'Confirm' button
#    And I set value '1/1/2026' to the cell of the column 'Source Date' of the 'Labor' table for the row with the following data:
#      | Sequence | 3 |

#    And I set value '12/31/2031' to the cell of the column 'End' of the 'Labor' table for the row with the following data:
#      | Sequence | 3 |
    Then click on 'Save' button
    And page with name 'Estimate Labor' is opened

#    Then click on 'Save' button
    Then I click on 'Update Cost' button
    Then I wait for 30 seconds
    And page with name 'Estimate Labor' is opened

#    And wait for 7 seconds
#    And page with name 'Estimate Labor' is opened

    And 'Labor' table contain row with following data:
      | Sequence                 | 1                |
      | Resource Group           | REG_CONSULTANT2  |
      | Cost in Company Currency | $ 274,320.00     |
      | WBS                      | 1.1.1 Design WBS |
      | Effort                   | 3048 hours       |
      | Start                    | 1/1/26           |
      | End                      | 6/30/27          |


    And 'Labor' table contain row with following data_-Warning-_:
      | Sequence                 | 2                  |
      | Resource Group           | REG_PROJECTMANAGER |
      | Cost in Company Currency | $ 110,000.00       |
      | WBS                      | 1.2.1 Cut Over WBS |
      | Effort                   | 1000 hours         |
      | Start                    | 7/1/28             |
      | End                      | 12/31/29           |

    And 'Labor' table contain row with following data_-Warning-_:
      | Sequence                 | 3                          |
      | Resource Group           | REG_QAMANAGER              |
      | Cost in Company Currency | $ 350,200.00               |
      | WBS                      | 1.3.1 Set-up WBS           |
      | Effort                   | 4120 hours                 |
      | Start                    | 1/1/30                     |
      | End                      | 12/31/31                   |
    And page with name 'Estimate Labor' is opened
    When I retrieve the number of open browser windows
    And info: ---Labor: Open Cost Price Analysis
    Then I click on 'Hamburger' menu
    Then I choose in 'Hamburger' menu the next menu chain:
      | Open                     |
      | Open Cost Price Analysis |
#    And info: ---Cost Price Analysis
    And page with name 'Estimate Labor' is opened
#    And I wait for 15 seconds
#    And I close current browser tab
#    Then I switch to browser tab 'Twenty5 iPE: Consulting'
    Then I switch to new tab
    And page with name 'Cost Price Analysis Wbs page' is opened
    Then I click on 'Workbench' tab
    Then page with name 'Workbench page' is opened

    And info: ---Workbench: Setup Costing Workbench View
    When I select 'Cost and Revenue Component by Description over Time' in the 'View' dropdown
    Then page with name 'Workbench page' is opened
#    Then text of 'View' dropdown equals 'View: Cost and Revenue Component by Description over Time (shared)'
#    Then page with name 'Workbench page' is opened
    Then I expand collapsed items
    Then page with name 'Workbench page' is opened
#    And stop
    And info: ---Workbench: Result
     #    Commented due to open defects - need to check the correct values and update results
    And 'Workbench' table contain row with following data:
      | Cost Type                          | Grand total  |
      | Grand total Hours                  | 8,168.00     |
      | Grand total Cost: Company Currency | $ 734,520.00 |
      | 2026 Hours                         | 2,037.58     |
      | 2026 Cost: Company Currency        | $ 183,382.37 |
      | 2027 Hours                         | 1,010.42     |
      | 2027 Cost: Company Currency        | $ 90,937.63  |
      | 2028 Hours                         | 335.1        |
      | 2028 Cost: Company Currency        | $ 36,867.05  |
      | 2029 Hours                         | 664.8        |
      | 2029 Cost: Company Currency        | $ 73,132.95  |
      | 2030 Hours                         | 2,060.00     |
      | 2030 Cost: Company Currency        | $ 175,100.00 |
      | 2031 Hours                         | 2,060.00     |
      | 2031 Cost: Company Currency        | $ 175,100.00 |


