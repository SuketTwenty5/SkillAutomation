@T5A-11
Feature: Golden Dev Sim Part I

  Background:
    Given open site
    And I perform login
#    And I wait for 25 seconds
    Then page with name 'Main page' is opened

  @OG-CREATE-CONSULTING-PROPOSAL
  Scenario: Create Consulting Proposal
            1. Create Proposal            |
            2. Review Phases              |
            3. Change Dates in Phases     |
            4. Change date in WBS         |
            5. Release Estimate           |
            6. Add Labors                 |
            7. Remove Escalation/Inflation|
            8. Verify Cost Analysis       |
    Given generate values and store into the variables:
      | $uniqueProposalName | TEST ${CUR_DATE,yyyy-MM-dd hh:mm} |
    And I click on 'PROPOSALS' tab
    Then page with name 'Proposals list page' is opened
    And I make a new proposal of type Consulting Proposal
    And page with name 'Setup page' is opened
    When I enter data into the next fields:
      | 'Planned Start' field | 1/1/2026              |
      | 'Description' field   | $uniqueProposalName |
      | 'End' field           | 12/31/2031            |
      | 'Several' radio       | check               |
    And page with name 'Setup page' is opened
    When I enter data into the next fields:
      | 'Capability' field              | Regression Test                |
      | 'Leading Site' field            | RG Dept-1                      |
      | 'Profit Center' field           | REG PC-1                       |
      | 'CRM Opportunity' field         | DS4220 opportunities territory |
    And page with name 'Setup page' is opened
    And text of 'Client Customer sell-to' field equals 'MONTEREY MUSHROOMS INC (MONTERY)'
    And page with name 'Setup page' is opened
    And I check 'Select a template' radio
    And page with name 'Setup page' is opened
    When I enter data into the next fields:
      | 'Search for Template from Library' field | P1711-2023-0174 TEST: Regression Test Template 1 |
    And click on 'Copy' button
    And page with name 'Setup page' is opened
    And I store relative url of app in '$uniqueProposalSetupRelative'
    And click on 'Phases' tab
    And info: ------- Review Phases --------------
    And info: Verify that phases are copied from Template
    Then page with name 'Phases page' is opened
    And 'Phases' table is displayed
    And 'Phases' table contain row with following data:
      | Description | Design & Build Phase |
    And 'Phases' table contain row with following data:
      | Description | Transition Phase |
    And 'Phases' table contain row with following data:
      | Description | Run & Maintain Phase |
    And info: ------- Set dates in Phases --------------
    And info: Set the start and end dates for each phase
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
    When I select 'Default (shared)' in the 'WBS View' dropdown
    And I click on 'Save' button
    Then text of 'WBS View' dropdown equals 'View: Default (shared)'

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
    And info: ------ PPT Slide 3-----------------
    And I click on 'Labor' tab
    And page with name 'Estimate Labor' is opened
    When I select 'Test View for Regression' in the 'View' dropdown
    And I click on 'Save' button
    Then text of 'View' dropdown equals 'View: Test View for Regression - DONOT CHANGE'
    And info: Change planning mode to Manual(Monthly)
    And info:  ------ PPT Slide 9-----------------
    Then I choose in 'Cog Settings' menu the next menu chain:
      | Change Planned Effort Input Mode |
      | Hours by Month                   |
    And page with name 'Estimate Labor' is opened
    Then 'Change Planning Mode' window is displayed
    And I click on 'Yes' button
    And page with name 'Estimate Labor' is opened

    And info: Labor: Add Labor Line Items REG_CONSULTANT2
    Then I click on 'Reload' page
    And page with name 'Estimate Labor' is opened
    Then I click on 'Click here to add' link
    And page with name 'Estimate Labor' is opened
    And I set value 'RG01 REG Company Code' to the cell of the column 'Capability' of the 'Labor' table for the row with the following data:
      | Sequence | 1 |
    And I set value 'REG_CONSULTANT2' to the cell of the column 'Resource Group' of the 'Labor' table for the row with the following data:
      | Sequence | 1 |
    And I set value 'Linear' to the cell of the column 'Distribution' of the 'Labor' table for the row with the following data:
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
    Then text of 'View' dropdown equals 'View: Test View for Regression - DONOT CHANGE'

    And info: Labor: Add Labor Line Items REG_PROJECTMANAGER
    And I click '+' in table 'Labor' table for row with data:
      | Sequence | 1 |
    And I set value '1.2.1 Cut Over WBS' to the cell of the column 'WBS' of the 'Labor' table for the row with the following data:
      | Sequence | 2 |
    And I set value 'RG01 REG Company Code' to the cell of the column 'Capability' of the 'Labor' table for the row with the following data:
      | Sequence | 2 |
    And I set value 'RG10 RG Dept-1' to the cell of the column 'Delivery Organization' of the 'Labor' table for the row with the following data:
      | Sequence | 2 |
    And I set value 'REG_PROJECTMANAGER' to the cell of the column 'Resource Group' of the 'Labor' table for the row with the following data:
      | Sequence | 2 |
    And I set value 'Linear' to the cell of the column 'Distribution' of the 'Labor' table for the row with the following data:
      | Sequence | 2 |
    And I set value '1000' to the cell of the column 'Planned Effort' of the 'Labor' table for the row with the following data:
      | Sequence | 2 |
#    And I set value '7/1/26' to the cell of the column 'Start' of the 'Labor' table for the row with the following data:
#      | Sequence | 2 |
#    And I set value '12/31/2028' to the cell of the column 'End' of the 'Labor' table for the row with the following data:
#      | Sequence | 2 |
#    And I set value '1/1/26' to the cell of the column 'Source Date' of the 'Labor' table for the row with the following data:
#      | Sequence | 2 |
    Then click on 'Save' button
    And page with name 'Estimate Labor' is opened

    And info: Labor: Add Labor Line Items REG_DATAANALYST
    And I click '+' in table 'Labor' table for row with data:
      | Sequence | 2 |
    And I set value '1.3.1 Set-up WBS' to the cell of the column 'WBS' of the 'Labor' table for the row with the following data:
      | Sequence | 3 |
    And I set value 'RG01 REG Company Code' to the cell of the column 'Capability' of the 'Labor' table for the row with the following data:
      | Sequence | 3 |
    And I set value 'RG10 RG Dept-1' to the cell of the column 'Delivery Organization' of the 'Labor' table for the row with the following data:
      | Sequence | 3 |
    And I set value 'REG_DATAANALYST' to the cell of the column 'Resource Group' of the 'Labor' table for the row with the following data:
      | Sequence | 3 |
    And I set value '1' to the cell of the column 'FTE' of the 'Labor' table for the row with the following data:
      | Sequence | 3 |
    And I set value 'Manual (Monthly)' to the cell of the column 'Distribution' of the 'Labor' table for the row with the following data:
      | Sequence | 3 |
#    And I set value '1/1/2030' to the cell of the column 'Start' of the 'Labor' table for the row with the following data:
#      | Sequence | 3 |
    And page with name 'Estimate Labor' is opened
#    Then click on 'Confirm' button
#    And I set value '1/1/2026' to the cell of the column 'Source Date' of the 'Labor' table for the row with the following data:
#      | Sequence | 3 |

#    And I set value '12/31/2031' to the cell of the column 'End' of the 'Labor' table for the row with the following data:
#      | Sequence | 3 |
    Then click on 'Save' button
    And page with name 'Estimate Labor' is opened

#    Then click on 'Save' button
#    And page with name 'Estimate Labor' is opened
#    Then I click on 'Update Cost' button
    And wait for 5 seconds
#    And page with name 'Estimate Labor' is opened

    And 'Labor' table contain row with following data:
      | Sequence                 | 1                |
      | Resource Group           | REG_CONSULTANT2  |
#      | Cost in Company Currency | $ 280,800.00     |
      | WBS                      | 1.1.1 Design WBS |
      | Planned Effort           | 3048 hours       |
      | Start                    | 1/1/26       |
      | End                      | 6/30/27       |


    And 'Labor' table contain row with following data:
      | Sequence                 | 2                  |
      | Resource Group           | REG_PROJECTMANAGER |
#      | Cost in Company Currency | $ 110,000.00       |
      | WBS                      | 1.2.1 Cut Over WBS |
      | Planned Effort           | 1000 hours         |
      | Start                    | 7/1/28         |
      | End                      | 12/31/29         |

    And 'Labor' table contain row with following data:
      | Sequence                 | 3                          |
      | Resource Group           | REG_DATAANALYST            |
#      | Cost in Company Currency | $ 459,360.00               |
      | WBS                      | 1.3.1 Set-up WBS           |
      | Planned Effort           | 4120 hours                 |
      | Start                    | 1/1/30                     |
      | End                      | 12/31/31                   |

    And info: ---Labor: Open Cost Price Analysis
    Then I click on 'Hamburger' menu
    Then I choose in 'Hamburger' menu the next menu chain:
      | Open                     |
      | Open Cost Price Analysis |

    And info: ---Cost Price Analysis
    And page with name 'Estimate Labor' is opened
#    And I close current browser tab
#    Then I switch to browser tab 'Twenty5 iPE: Consulting'
    Then I switch to tab 3
    And page with name 'Cost Price Analysis Wbs page' is opened
    Then I click on 'Workbench' tab
    Then page with name 'Workbench page' is opened

    And info: ---Workbench: Setup Costing Workbench View
    When I select 'Cost and Revenue Component by Description over Time (shared & preferred)' in the 'View' dropdown
    Then page with name 'Workbench page' is opened
    Then text of 'View' dropdown equals 'View: Cost and Revenue Component by Description over Time (shared & preferred)'
    Then page with name 'Workbench page' is opened
    Then I expand collapsed items
    Then page with name 'Workbench page' is opened
#    And stop
    And info: ---Workbench: Result
     #    Commented due to open defects - need to check the correct values and update results
    And 'Workbench' table contain row with following data:
      | Cost Type                          | Grand total  |
      | 2026 Cost: Company Currency        | $ 188,329.09 |
      | 2026 Hours                         | 2,037.58     |
      | 2027 Cost: Company Currency        | $ 96,845.23  |
      | 2027 Hours                         | 1,010.42     |
      | 2028 Cost: Company Currency        | $ 36,867.05  |
      | 2028 Hours                         | 335.15       |
      | 2029 Cost: Company Currency        | $ 73,132.95  |
      | 2029 Hours                         | 664.84       |
      | 2030 Cost: Company Currency        | $ 223,520.00 |
      | 2030 Hours                         | 2,032.00     |
      | 2031 Cost: Company Currency        | $ 229,680.00 |
      | 2031 Hours                         | 2,088.00     |
      | Grand total Cost: Company Currency | $ 848,374.31 |
      | Grand total Hours                  | 8,168.00     |

#  Scenario: Second Test                   |
##            1. Add HW/SW & services       |
###    Then I click on 'Hamburger' menu
###    Then I choose in 'Hamburger' menu the next menu chain:
###      | Open Proposal |
#    Then I navigate to relative url '$uniqueProposalSetupRelative'
##    And info: HW/SW & Services: Adding Hardware Estimate
###    And I close current browser tab
###    Then I switch to browser tab 'Twenty5 iPE: Consulting'
##    Then I click on 'WBS' tab
##    Then page with name 'WBS page' is opened
##    And I click 'Open' in table 'WBS' table for row with data:
##      | WBS | 1 Estimate |
###    And I close current browser tab
###    Then switch to one of the browser tabs 'Twenty5 iPE: Estimate' or 'Twenty5 iPE: Estimates'
##    Then I switch to tab 2
##    And page with name 'Estimate Labor' is opened
##
##    And info: HW/SW & Services: Adding Hardware Estimate Equipment
##    And I click on 'Hardware & Software' tab
##    And page with name 'Estimate Hardware Software' is opened
##    When I select 'Cost Distribution (shared)' in the 'View' dropdown
##    And I click on 'Save' button
##    Then text of 'View' dropdown equals 'View: Cost Distribution (shared)'
##    And page with name 'Estimate Hardware Software' is opened
##
##    Then I click on 'Click here to add' link
##    And page with name 'Estimate Hardware Software' is opened
##
##    And I set value '650001 Equipment' to the cell of the column 'Cost Element' of the 'Service' table for the row with the following data:
###      | WBS | 1 Estimate |
##      | WBS | 1.1.1 Design WBS |
##    And I set value 'Equipment' to the cell of the column 'Description' of the 'Service' table for the row with the following data:
###      | WBS | 1 Estimate |
##      | WBS | 1.1.1 Design WBS |
##    And I set value '1.1 Design & Build Phase' to the cell of the column 'WBS' of the 'Service' table for the row with the following data:
##      | Description | Equipment |
##    And I set value '1.1 Design & Build Phase' to the cell of the column 'Work Package' of the 'Service' table for the row with the following data:
##      | Description | Equipment |
##    And I set value '1000' to the cell of the column 'Source Unit Cost' of the 'Service' table for the row with the following data:
##      | Description | Equipment |
##    And I set value '1/1/2024' to the cell of the column 'Start' of the 'Service' table for the row with the following data:
##      | Description | Equipment |
##    And I set value '5/31/2024' to the cell of the column 'End' of the 'Service' table for the row with the following data:
##      | Description | Equipment |
##    And I set value '1/1/24' to the cell of the column 'Source Date' of the 'Service' table for the row with the following data:
##      | Description | Equipment |
##
##    And I click on 'Save' button
##    And page with name 'Estimate Hardware Software' is opened
##
##    And info: HW/SW & Services: Adding Hardware Estimate Raw Materials
##    And I click '+' in table 'Service' table for row with data:
##      | Description | Equipment |
##    And page with name 'Estimate Hardware Software' is opened
##    And I set value '650005 Raw Materials' to the cell of the column 'Cost Element' of the 'Service' table for the row with the following data:
##      | Direct/Indirect Company Cost | $ 0.00 |
##    And page with name 'Estimate Hardware Software' is opened
##    And I set value '1.3 Run & Maintain' to the cell of the column 'Work Package' of the 'Service' table for the row with the following data:
##      | Cost Element | Raw Materials |
##    And I set value '1.3.1 Set-up WBS' to the cell of the column 'WBS' of the 'Service' table for the row with the following data:
##      | Cost Element | Raw Materials |
##    And I set value 'Raw Materials' to the cell of the column 'Description' of the 'Service' table for the row with the following data:
##      | Cost Element | Raw Materials |
##    And I set value '4000' to the cell of the column 'Source Unit Cost' of the 'Service' table for the row with the following data:
##      | Description | Raw Materials |
##    And I set value '1/1/2026' to the cell of the column 'Start' of the 'Service' table for the row with the following data:
##      | Description | Raw Materials |
##    And I set value '12/31/2027' to the cell of the column 'End' of the 'Service' table for the row with the following data:
##      | Description | Raw Materials |
##    And I set value '1/1/24' to the cell of the column 'Source Date' of the 'Service' table for the row with the following data:
##      | Description | Raw Materials |
##    And I click on 'Save' button
##    And page with name 'Estimate Hardware Software' is opened
##
##    And info: HW/SW & Services: Adding Hardware Estimate Field Material
##    And I click '+' in table 'Service' table for row with data:
##      | Description | Raw Materials |
##    And page with name 'Estimate Hardware Software' is opened
##    And I set value '650003 Field Material' to the cell of the column 'Cost Element' of the 'Service' table for the row with the following data:
##      | Direct/Indirect Company Cost | $ 0.00 |
##    And page with name 'Estimate Hardware Software' is opened
##    And I set value '1.2 Transition Phase' to the cell of the column 'Work Package' of the 'Service' table for the row with the following data:
##      | Cost Element | Field Material |
##    And I set value '1.2.1 Cut Over WBS' to the cell of the column 'WBS' of the 'Service' table for the row with the following data:
##      | Cost Element | Field Material |
##    And I set value 'Field Material' to the cell of the column 'Description' of the 'Service' table for the row with the following data:
##      | Cost Element | Field Material |
##    And I set value '2000' to the cell of the column 'Source Unit Cost' of the 'Service' table for the row with the following data:
##      | Description | Field Material |
##    And I set value '7/1/2024' to the cell of the column 'Start' of the 'Service' table for the row with the following data:
##      | Description | Field Material |
##    And I set value '12/31/2025' to the cell of the column 'End' of the 'Service' table for the row with the following data:
##      | Description | Field Material |
##    And I set value '1/1/24' to the cell of the column 'Source Date' of the 'Service' table for the row with the following data:
##      | Description | Field Material |
##    And page with name 'Estimate Hardware Software' is opened
##    And I click on 'Save' button
##
##    And info: HW/SW & Services: Verifying added HW/SW & Services
##    And 'Service' table contain row with following data:
##      | Description | Equipment |
##      | Start       | 1/1/24    |
##      | End         | 5/31/24   |
##
##    And 'Service' table contain row with following data:
##      | Description | Field Material |
##      | Start       | 7/1/24         |
##      | End         | 12/31/25       |
##
##    And 'Service' table contain row with following data:
##      | Description | Raw Materials |
##      | Start       | 1/1/26        |
##      | End         | 12/31/27      |
##    And page with name 'Estimate Hardware Software' is opened
##
##    And info: ---Open Cost Price Analysis
##    Then I click on 'Hamburger' menu
##    Then I choose in 'Hamburger' menu the next menu chain:
##      | Open                     |
##      | Open Cost Price Analysis |
##    And page with name 'Estimate Hardware Software' is opened
##    And I close current browser tab
##    Then I switch to browser tab 'Twenty5 iPE: Consulting'
##    Then I click on 'Workbench' tab
##    Then page with name 'Workbench page' is opened
##
##    When I select 'Cost and Revenue Component by Description over Time (shared & preferred)' in the 'View' dropdown
##    Then page with name 'Workbench page' is opened
##    Then text of 'View' dropdown equals 'View: Cost and Revenue Component by Description over Time (shared & preferred)'
##    Then page with name 'Workbench page' is opened
###    Then I expand collapsed items
###    Then page with name 'Workbench page' is opened
##
##    #    Commented due to open defects - need to check the correct values and update results
###    And info: ---Workbench: Result
###    And info: --Success – Part II Complete --------
####    And 'Workbench' table contain row with following data:
####      | Cost Type                          | Material costs |
####      | 2024 Cost: company currency        | $ 1,670.31     |
####      | 2024 Hours                         | 0.00           |
####      | 2025 Cost: company currency        | $ 1,329.69     |
####      | 2025 Hours                         | 0.00           |
####      | 2026 Cost: company currency        | $ 2,000.00     |
####      | 2026 Hours                         | 0.00           |
####      | 2027 Cost: company currency        | $ 2,000.00     |
####      | 2027 Hours                         | 0.00           |
####      | Grand total Cost: company currency | $ 7,000.00     |
####      | Grand total Hours                  | 0.00           |
###    And 'Workbench' table contain row with following data:
###      | Cost Type                          | Material costs |
###      | 2024 Cost: company currency        | $ 1,692.37     |
###      | 2024 Hours                         | 0.00           |
###      | 2025 Cost: company currency        | $ 1,378.07     |
###      | 2025 Hours                         | 0.00           |
###      | 2026 Cost: company currency        | $ 2,123.70     |
###      | 2026 Hours                         | 0.00           |
###      | 2027 Cost: company currency        | $ 2,155.85     |
###      | 2027 Hours                         | 0.00           |
###      | Grand total Cost: company currency | $ 7,350.00     |
###      | Grand total Hours                  | 0.00           |
###
##    And info: -------PPT Part 3------------------
##    And info: ------ PPT Slide 4-----------------
#    And info: ------ Add Inflation Labor tab-----
##
##    Then I click on 'Hamburger' menu
##    Then I choose in 'Hamburger' menu the next menu chain:
##      | Open Proposal |
##    Then page with name 'Workbench page' is opened
##    And I close current browser tab
##
##    Then I switch to browser tab 'Twenty5 iPE: Consulting'
#    Then I click on 'WBS' tab
#    Then page with name 'WBS page' is opened
#    And I click 'Open' in table 'WBS' table for row with data:
#      | WBS | 1 Estimate |
#    And I wait for 10 seconds
#    Then page with name 'WBS page' is opened
#    And I close current browser tab
#    Then I switch to tab 1
##    Then switch to one of the browser tabs 'Twenty5 iPE: Estimate' or 'Twenty5 iPE: Estimates'
#    Then I click on 'Labor' tab
#    And page with name 'Estimate Labor' is opened
#
#    And page with name 'Estimate Labor' is opened
#    When I select 'Test View for Regression' in the 'View' dropdown
#    And I click on 'Save' button
#    Then text of 'View' dropdown equals 'View: Test View for Regression - DONOT CHANGE'
#
#    And info: ------ PPT Slide 4-----------------
#    And info: ------ Add Inflation Labor tab-----
#    And I set value 'Escalation EUR Commodities' to the cell of the column 'Escalation Factor' of the 'Labor' table for the row with the following data:
#      | Sequence | 2 |
#    And page with name 'Estimate Labor' is opened
#    And I set value 'Escalation Electronics' to the cell of the column 'Escalation Factor' of the 'Labor' table for the row with the following data:
#      | Sequence | 3 |
#
#    And info: ------ PPT Slide 4-----------------
#    And info: ------ Set Source Date-----
#    And I set value '1/1/28' to the cell of the column 'Source Date' of the 'Labor' table for the row with the following data:
#      | Sequence | 1 |
#    And I set value '1/1/28' to the cell of the column 'Source Date' of the 'Labor' table for the row with the following data:
#      | Sequence | 2 |
#    And I set value '1/1/28' to the cell of the column 'Source Date' of the 'Labor' table for the row with the following data:
#      | Sequence | 3 |
#    And page with name 'Estimate Labor' is opened
#    And I click on 'Save' button
#
#    Then I click on 'Update Cost' button
#    And wait for 20 seconds
#    And page with name 'Estimate Labor' is opened
#
#    And info: ------ PPT Slide 5-----------------
#    And info: ------ Add Inflation - HW&SW-------
#    And I click on 'Hardware & Software' tab
#    And page with name 'Estimate Hardware Software' is opened
#
#    And I set value 'Escalation EUR Commodities' to the cell of the column 'Escalation Factor' of the 'Service' table for the row with the following data:
#      | Description | Field Material |
#    And page with name 'Estimate Hardware Software' is opened
#    And I set value 'Escalation Electronics' to the cell of the column 'Escalation Factor' of the 'Service' table for the row with the following data:
#      | Description | Raw Materials |
#    And page with name 'Estimate Hardware Software' is opened
#
#    And info: ------ PPT Slide 5-----------------
#    And info: ------ Set Source Date-----
#    And I set value '1/1/25' to the cell of the column 'Source Date' of the 'Service' table for the row with the following data:
#      | Description | Field Material |
#    And I set value '1/1/25' to the cell of the column 'Source Date' of the 'Service' table for the row with the following data:
#      | Description | Raw Materials |
#    And I set value '1/1/25' to the cell of the column 'Source Date' of the 'Service' table for the row with the following data:
#      | Description | Equipment |
#
#    And I click on 'Save' button
#    Then I click on 'Update Cost' button
#    And wait for 20 seconds
#    And page with name 'Estimate Hardware Software' is opened
#
#    And info: ------ PPT Slide 6-----------------
#    And info: ------ View Impact ----------------
#
#    And info: ---Open Cost Price Analysis
#    Then I click on 'Hamburger' menu
#    Then I choose in 'Hamburger' menu the next menu chain:
#      | Open                     |
#      | Open Cost Price Analysis |
#    And page with name 'Estimate Hardware Software' is opened
#    And I close current browser tab
#    Then I switch to browser tab 'Twenty5 iPE: Consulting'
#    Then I click on 'Workbench' tab
#    Then page with name 'Workbench page' is opened
#
#    When I select 'Cost and Revenue Component by Description over Time (shared & preferred)' in the 'View' dropdown
#    Then page with name 'Workbench page' is opened
#    Then text of 'View' dropdown equals 'View: Cost and Revenue Component by Description over Time (shared & preferred)'
#    Then page with name 'Workbench page' is opened
#
#    Then I expand collapsed items
# #    Commented due to open defects - need to check the correct values and update results
#
#    And 'Workbench' table contain row with following data:
#      | Cost Type                          | Material costs |
#      | 2024 Cost: company currency        | $ 1,670.31     |
#      | 2024 Hours                         | 0.00           |
#      | 2025 Cost: company currency        | $ 1,329.69     |
#      | 2025 Hours                         | 0.00           |
#      | 2026 Cost: company currency        | $ 2,000.00     |
#      | 2026 Hours                         | 0.00           |
#      | 2027 Cost: company currency        | $ 2,000.00     |
#      | 2027 Hours                         | 0.00           |
#      | Grand total Cost: company currency | $ 7,000.00     |
#      | Grand total Hours                  | 0.00           |
#
#    And info: ------ Back to Estimation ----------------
#    Then I click on 'Hamburger' menu
#    Then I choose in 'Hamburger' menu the next menu chain:
#      | Open Proposal |
#    Then page with name 'Workbench page' is opened
#    And I close current browser tab
#    Then I switch to browser tab 'Twenty5 iPE: Consulting'
#
#    And info: ------ PPT Slide 7-----------------
#    And info: -Add Contract Lines (Pricing Labors)
#    Then I click on 'WBS' tab
#    Then page with name 'WBS page' is opened
#    Then I click on 'Hamburger' menu
#    Then I choose in 'Hamburger' menu the next menu chain:
#      | Generate Cost Model incl. Formulas  |
#      | Roll-up Costs & Recalculate Formula |
#
#    And info: ------ PPT Slide 8-----------------

#  Scenario: Third test         |
#            1. Add Line Items  |
#    And info: ----------Contract Lines tab-----------------
#    Then I navigate to relative url '$uniqueProposalSetupRelative'
##    Then I click on 'Lines Items' tab
##    Then page with name 'Lines Items page' is opened
##    When I select 'Test-Do Not EDIT' in the 'View' dropdown
##    And I click on 'Save' button
##    Then text of 'View' dropdown equals 'View: Test-Do Not EDIT'
##    Then I click on 'Click here to add' link
##    Then page with name 'Lines Items page' is opened
##
##    And I set value 'Pricing Labor' to the cell of the column 'Description' of the 'Contract Lines' table for the row with the following data:
##      | Item | 1 |
##    And I set value '1.1 Design & Build Phase' to the cell of the column 'Receiver WBS' of the 'Contract Lines' table for the row with the following data:
##      | Item | 1 |
##    And I click on 'Save' button
##    Then page with name 'Lines Items page' is opened
#
##    And info: ------ PPT Slide 9-----------------
##    And info: Cost Allocations: Add line item
##    And I click 'Pricing Labor' in table 'Contract Lines' table for row with data:
##      | Item | 1 |
##    Then page with name 'Lines Items page' is opened
##    Then 'Line Item Details' window is displayed
##
##    And I click on 'Cost Allocations' tab
##    Then page with name 'Lines Items page' is opened
##    Then I click on 'Click here to add' link
##    Then page with name 'Lines Items page' is opened
##    And I set value '1.1 Design & Build Phase' to the cell of the column 'Sender WBS' of the 'Contract Lines Allocations' table for the row with the following data:
##      | % Cost | 0.00% |
##    Then page with name 'Lines Items page' is opened
##    And I set value '100' to the cell of the column '% Cost' of the 'Contract Lines Allocations' table for the row with the following data:
##      | % Cost | 0.00% |
#
##    And info: ------ PPT Slide 10-----------------
##    And info: Cost Allocations: Add line item
##    And I click on 'Pricing & Qty' tab
##    Then page with name 'Lines Items page' is opened
##
##    When I enter data into the next fields:
##      | 'Revenue Recognition Method' field | Cash based, or based on payment milestones |
##      | 'Pricing Strategy' field           | Fixed price with T&M rates                 |
##    Then page with name 'Lines Items page' is opened
##    Then I click on 'Confirm' button
##    Then page with name 'Lines Items page' is opened
###    Then I click on 'Confirm' button
###    Then page with name 'Lines Items page' is opened
##
##    And info: Add Contract Lines: Pricing Hardware
##    And info: ------ PPT Slide 11-----------------
##    And I click '+' in table 'Contract Lines' table for row with data:
##      | Item | 1 |
##    Then page with name 'Lines Items page' is opened
##    And I set value 'Pricing Hardware' to the cell of the column 'Description' of the 'Contract Lines' table for the row with the following data:
##      | Item | 2 |
##    And I set value '1.2 Transition Phase' to the cell of the column 'Receiver WBS' of the 'Contract Lines' table for the row with the following data:
##      | Item | 2 |
##    And I click on 'Save' button
##    Then page with name 'Lines Items page' is opened
##    And I click 'Pricing Hardware' in table 'Contract Lines' table for row with data:
##      | Item | 2 |
##    Then page with name 'Lines Items page' is opened
##    Then 'Line Item Details' window is displayed
##
##    And info: ------ PPT Slide 12-----------------
##    And I click on 'Cost Allocations' tab
##    Then page with name 'Lines Items page' is opened
##    Then I click on 'Click here to add' link
##    Then page with name 'Lines Items page' is opened
##    And I set value '1.2 Transition Phase' to the cell of the column 'Sender WBS' of the 'Contract Lines Allocations' table for the row with the following data:
##      | % Cost | 0.00% |
##    And I set value '100' to the cell of the column '% Cost' of the 'Contract Lines Allocations' table for the row with the following data:
##      | % Cost | 0.00% |
##
##    And info: ------ PPT Slide 13-----------------
##    And info: Cost Allocations: Add line item
##    And I click on 'Pricing & Qty' tab
##    Then page with name 'Lines Items page' is opened
##    When I enter data into the next fields:
##      | 'Pricing Strategy' field           | Fixed pricing                                    |
##      | 'Revenue Recognition Method' field | Accrual based, or based on delivery of end items |
##    Then page with name 'Lines Items page' is opened
##    And I set value '200000' to the cell of the column 'Amount' of the 'Pricing&Qty' table for the row with the following data:
##      | Description | List price |
##    Then page with name 'Lines Items page' is opened
##
##    And info: ------ PPT Slide 14-----------------
##    Then I click on 'Delivery Schedule' tab
##    And I click on 'Click here to add' link
##    Then 'Delivery Schedule' table is displayed
##    Then page with name 'Lines Items page' is opened
##
##    And info: ------ PPT Slide 15-----------------
##    And I set value '0.50' to the cell of the column 'Qty - From' of the 'Delivery Schedule' table for the row with the following data:
##      | Revenue | $ |
##    And I set value '7/1/26' to the cell of the column 'To Date' of the 'Delivery Schedule' table for the row with the following data:
##      | Qty - From | 0.50 ea |
##    And I click '+' in table 'Delivery Schedule' table for row with data:
##      | Qty - From | 0.50 ea |
##    And I set value '7/1/27' to the cell of the column 'To Date' of the 'Delivery Schedule' table for the row with the following data:
##      | Revenue | $ 0.00 |
##    And I set value '0.50' to the cell of the column 'Qty - From' of the 'Delivery Schedule' table for the row with the following data:
##      | Revenue | $ 0.00 |
##    Then I click on 'Confirm' button
##    Then page with name 'Lines Items page' is opened
##
##    And info: ------ PPT Slide 16-----------------
##    And I click '+' in table 'Contract Lines' table for row with data:
##      | Item | 2 |
##    Then page with name 'Lines Items page' is opened
##    And I set value 'Pricing Support' to the cell of the column 'Description' of the 'Contract Lines' table for the row with the following data:
##      | Item | 3 |
##    And I click on 'Save' button
##    Then page with name 'Lines Items page' is opened
##    And I click 'Pricing Support' in table 'Contract Lines' table for row with data:
##      | Item | 3 |
##    Then page with name 'Lines Items page' is opened
##    Then 'Line Item Details' window is displayed
##
##    And info: ------ PPT Slide 17-----------------
##    And I click on 'Cost Allocations' tab
##    Then page with name 'Lines Items page' is opened
##    Then I click on 'Click here to add' link
##    Then page with name 'Lines Items page' is opened
##    And I set value '1.3 Run & Maintain' to the cell of the column 'Sender WBS' of the 'Contract Lines Allocations' table for the row with the following data:
##      | % Cost | 0.00% |
##    And I set value '100' to the cell of the column '% Cost' of the 'Contract Lines Allocations' table for the row with the following data:
##      | % Cost | 0.00% |
##
##    And info: ------ PPT Slide 18-----------------
##    And info: Cost Allocations: Add line item
##    And I click on 'Pricing & Qty' tab
##    Then page with name 'Lines Items page' is opened
##
##    When I enter data into the next fields:
##      | 'Pricing Strategy' field           | Fixed price with T&M rates                 |
##      | 'Revenue Recognition Method' field | Cash based, or based on payment milestones |
##    Then page with name 'Lines Items page' is opened
##    Then I click on 'Confirm' button
##    Then page with name 'Lines Items page' is opened
#
##    Then I click on 'Confirm' button
##    Then page with name 'Lines Items page' is opened
#
#    And info: Billing Plan: Milestones
#    And info: ------------ Step 19 ----------------
#    And I click on 'Billing Plan' tab
#    Then page with name 'Billing page' is opened
#    And 'Billing Plan' table contain row with following data:
#      | Milestone | Pricing Hardware |
#
#    And info: Workaround to see all 3 Billing items
#    Then I click on 'Lines Items' tab
#    Then page with name 'Lines Items page' is opened
#    And I click 'Pricing Labor' in table 'Contract Lines' table for row with data:
#      | Item | 1 |
#    Then page with name 'Lines Items page' is opened
#    Then 'Line Item Details' window is displayed
#    And I click on 'Pricing & Qty' tab
#    Then page with name 'Lines Items page' is opened
#    When I enter data into the next fields:
#      | 'Revenue Recognition Method' field | Cash based, or based on payment milestones |
#    Then page with name 'Lines Items page' is opened
#    Then I click on 'Confirm' button
#    Then page with name 'Lines Items page' is opened
#    And I click 'Pricing Hardware' in table 'Contract Lines' table for row with data:
#      | Item | 2 |
#    Then page with name 'Lines Items page' is opened
#    Then 'Line Item Details' window is displayed
#    And I click on 'Pricing & Qty' tab
#    Then page with name 'Lines Items page' is opened
#    And I set value '200000' to the cell of the column 'Amount' of the 'Pricing&Qty' table for the row with the following data:
#      | Description | List price |
#    Then page with name 'Lines Items page' is opened
#    Then I click on 'Confirm' button
#    Then page with name 'Lines Items page' is opened
#    And I click 'Pricing Support' in table 'Contract Lines' table for row with data:
#      | Item | 3 |
#    Then page with name 'Lines Items page' is opened
#    Then 'Line Item Details' window is displayed
#
#    When I enter data into the next fields:
#      | 'Revenue Recognition Method' field | Cash based, or based on payment milestones |
#    Then page with name 'Lines Items page' is opened
#    Then I click on 'Confirm' button
#    Then page with name 'Lines Items page' is opened
#    And I click 'Pricing Labor' in table 'Contract Lines' table for row with data:
#      | Item | 1 |
#    Then page with name 'Lines Items page' is opened
#    Then 'Line Item Details' window is displayed
#    And I click on 'Pricing & Qty' tab
#    Then page with name 'Lines Items page' is opened
#    When I enter data into the next fields:
#      | 'Revenue Recognition Method' field | Cash based, or based on payment milestones |
#    Then page with name 'Lines Items page' is opened
#    Then I click on 'Confirm' button
#    Then page with name 'Lines Items page' is opened
#
#    And info: Billing Plan: Milestones
#    And info: ------------ Step 19 ----------------
#    And I click on 'Billing Plan' tab
#    Then page with name 'Billing page' is opened
#    And 'Billing Plan' table contain row with following data:
#      | Milestone | Pricing Hardware |
#    And 'Billing Plan' table contain row with following data:
#      | Milestone | Pricing Labor |
#    And 'Billing Plan' table contain row with following data:
#      | Milestone | Pricing Support |
#
#
#    And I click 'Copy Row' in table 'Billing Plan' table for row with data:
#      | Milestone | Pricing Labor |
#    Then page with name 'Billing page' is opened
#    And I click 'Copy Row' in table 'Billing Plan' table for row with data:
#      | Milestone | Pricing Labor |
#    And I set value '1/1/26' to the cell of the column 'Date' of the 'Billing Plan' table for the row with the following data:
##      | Sequence  | 2             |
#      | Milestone | Pricing Labor |
#    And I set value '40' to the cell of the column '%' of the 'Billing Plan' table for the row with the following data:
##      | Sequence  | 2             |
#      | Milestone | Pricing Labor |
#      | Date      | 1/1/26        |
#
#    And I set value '1/1/25' to the cell of the column 'Date' of the 'Billing Plan' table for the row with the following data:
##      | Sequence  | 4             |
#      | Milestone | Pricing Labor |
#      | Date      | 1/1/24        |
#    And I set value '40' to the cell of the column '%' of the 'Billing Plan' table for the row with the following data:
##      | Sequence  | 4             |
#      | Milestone | Pricing Labor |
#      | Date      | 1/1/25        |
#
#    And I set value '1/1/26' to the cell of the column 'Date' of the 'Billing Plan' table for the row with the following data:
##      | Sequence  | 5             |
#      | Milestone | Pricing Labor |
#      | Date      | 1/1/24        |
#    And I set value '20' to the cell of the column '%' of the 'Billing Plan' table for the row with the following data:
##      | Sequence  | 5             |
#      | Milestone | Pricing Labor |
#      | Date      | 1/1/26        |
#
#    Then I click on 'Save' button
#
#    And I click 'Copy Row' in table 'Billing Plan' table for row with data:
#      | Milestone | Pricing Support |
#    Then page with name 'Billing page' is opened
#    And I click 'Copy Row' in table 'Billing Plan' table for row with data:
#      | Milestone | Pricing Support |
#
#
#    And I set value '1/1/24' to the cell of the column 'Date' of the 'Billing Plan' table for the row with the following data:
#      | Sequence  | 3               |
#      | Milestone | Pricing Support |
#    And I set value '40' to the cell of the column '%' of the 'Billing Plan' table for the row with the following data:
#      | Sequence  | 3               |
#      | Milestone | Pricing Support |
#
#    And I set value '1/1/25' to the cell of the column 'Date' of the 'Billing Plan' table for the row with the following data:
#      | Sequence  | 6               |
#      | Milestone | Pricing Support |
#    And I set value '40' to the cell of the column '%' of the 'Billing Plan' table for the row with the following data:
#      | Sequence  | 6               |
#      | Milestone | Pricing Support |
#
#    And I set value '1/1/26' to the cell of the column 'Date' of the 'Billing Plan' table for the row with the following data:
#      | Sequence  | 7               |
#      | Milestone | Pricing Support |
#    And I set value '20' to the cell of the column '%' of the 'Billing Plan' table for the row with the following data:
#      | Sequence  | 7               |
#      | Milestone | Pricing Support |
#    Then I click on 'Save' button
#    Then page with name 'Billing page' is opened
#
#    Then I click on 'Lines Items' tab
#    Then page with name 'Lines Items page' is opened
#
#    And I click on 'Billing Plan' tab
#    Then page with name 'Billing page' is opened
##
##    And 'Billing Plan' table contain row with following data:
##      | Milestone         | Pricing Hardware |
##      | Revenue - Company | $ 200,000.00     |
##      | %                 | 100.000%         |
##
##    And 'Billing Plan' table contain row with following data:
##      | Milestone         | Pricing Labor |
##      | Date              | 1/1/24        |
##      | Revenue - Company | $ 86,016.00   |
##      | %                 | 40.000%       |
##
##    And 'Billing Plan' table contain row with following data:
##      | Milestone         | Pricing Labor |
##      | Date              | 1/1/25        |
##      | Revenue - Company | $ 86,016.00   |
##      | %                 | 40.000%       |
##
##    And 'Billing Plan' table contain row with following data:
##      | Milestone         | Pricing Labor |
##      | Date              | 1/1/26        |
##      | Revenue - Company | $ 43,008.00   |
##      | %                 | 20.000%       |
##
##    And 'Billing Plan' table contain row with following data:
##      | Milestone         | Pricing Support |
##      | Date              | 1/1/24          |
##      | Revenue - Company | $ 350,784.00    |
##      | %                 | 40.000%         |
##
##
##    And 'Billing Plan' table contain row with following data:
##      | Milestone         | Pricing Support |
##      | Date              | 1/1/25          |
##      | Revenue - Company | $ 350,784.00    |
##      | %                 | 40.000%         |
##
##    And 'Billing Plan' table contain row with following data:
##      | Milestone         | Pricing Support |
##      | Date              | 1/1/26          |
##      | Revenue - Company | $ 175,392.00    |
##      | %                 | 40.000%         |
#
#    And info: ------------ Step 20 ----------------
#    Then I click on 'Hamburger' menu
#    Then page with name 'Billing page' is opened
#    Then I choose in 'Hamburger' menu the next menu chain:
#      | Open                |
#      | Cost Price Analysis |
#    Then page with name 'Billing page' is opened
#    And I close current browser tab
#
#    And info: ---Cost Price Analysis
#    And info: ------------ Step 21 ----------------
#    Then I switch to browser tab 'Twenty5 iPE: Consulting'
#    Then I click on 'Workbench' tab
#    Then page with name 'Workbench page' is opened
#    When I select 'Cost and Revenue Component by Description over Time (shared & preferred)' in the 'View' dropdown
#    Then page with name 'Workbench page' is opened
#    Then text of 'View' dropdown equals 'View: Cost and Revenue Component by Description over Time (shared & preferred)'
#    Then page with name 'Workbench page' is opened
#
#    Then I expand collapsed items
#     Commented due to open defects - need to check the correct values and update results