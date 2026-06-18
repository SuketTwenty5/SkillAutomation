@T5A-11 @MFP @ALL
Feature: Golden Dev Sim Part II

  Background:
    Given open site
    And I perform login
    Then page with name 'Main page' is opened

  Scenario: Create Manufacturing Proposal |
            1. Create Proposal            |
    Given generate values and store into the variables:
      | $uniqueProposalName | T5A-24 Test Proposal ${CUR_DATE,yyyy-MM-dd hh:mm} |

    And info: ------ Getting Started -------------

    And info: ------ PPT SLIDE 2 -----------------
    And I click on 'PROPOSALS' tab
    Then page with name 'Proposals list page' is opened
    Then click on 'New' button
    And page with name 'Setup page' is opened

    And info: -- Setup: Enter Project Details-----
    When I enter data into the next fields:
      | 'Proposal Type' field | Manufacturing Proposal |
    And page with name 'Setup page' is opened
    When I enter data into the next fields:
      | 'Planned Start' field | 1/1/25              |
      | 'Description' field   | $uniqueProposalName |
      | 'End' field           | 12/31/29            |
      | 'Single phase' radio  | check               |
    And page with name 'Setup page' is opened


    And info: ------ PPT SLIDE 3 -----------------
    And info: -- Section: Opportunity, Client & Your Organization-
    When I enter data into the next fields:
      | 'Capability' field              | REG Company Code       |
      | 'Leading Site' field            | RG Dept-1              |
#      | 'Client Customer sell-to' field | MONTEREY MUSHROOMS INC |
    And page with name 'Setup page' is opened
    When I enter data into the next fields:
      | 'Profit Center' field           | REG PC-1                       |
#      | 'CRM Opportunity' field         | DS4220 opportunities territory |
    And page with name 'Setup page' is opened
    And text of 'Leading Company Currency' field equals '$ USA - US Dollar(USD)'
#    And text of 'Client Customer sell-to' field equals 'MONTEREY MUSHROOMS INC'

    And info: -- Section: How do you want to Start your Project Plan --
    And I check 'Select a template' radio
    When I enter data into the next fields:
      | 'Search for Template from Library' field | BOM Test Template |
    And click on 'Copy' button
    And page with name 'Setup page' is opened


    And info: ------ PPT SLIDE 4 -----------------
    Then I click on 'WBS Structure' tab
    Then page with name 'WBS page' is opened
    When I select node with name '$uniqueProposalName' in 'WBS' tree
    Then I choose in 'Cog' menu the next menu chain:
      | Expand/Collapse |
      | Expand All      |
    And page with name 'WBS page' is opened
    When I select 'TEST:-DONOT_CHANGE' in the 'WBS View' dropdown
    And I click on 'Save' button
    Then text of 'WBS View' dropdown equals 'View: TEST:-DONOT_CHANGE (preferred)'
    And I set value 'Bill of Material' to the cell of the column 'Estimating Strategy' of the 'WBS' table for the row with the following data:
      | Cost Structure | 1 Overall |
    And I click on 'Save' button
    And page with name 'WBS page' is opened
    And I click 'Create' in table 'WBS' table for row with data:
      | Cost Structure | 1 Overall |
    And info: -- Setup: Estimate--
    When I enter data into the next fields:
      | 'Owner' field | Tech User |
    And I click on 'Confirm & Release' button


    And info: ------ PPT SLIDE 5 -----------------
    Then I click on 'CLINs' tab
    Then I click on 'Reload' page
    Then I wait for 15 seconds
    And page with name 'Line Items page' is opened
    When I select 'Test-Do Not EDIT' in the 'View' dropdown
    And page with name 'Line Items page' is opened
    Then I click on 'Click here to add' link
    And I set value 'Overall' to the cell of the column 'Estimate' of the 'Contract Lines' table for the row with the following data:
      | Item | 1 |
    And I click on 'Save' button
    And I set value 'Regression Finished Goods' to the cell of the column 'Part Number' of the 'Contract Lines' table for the row with the following data:
      | Item | 1 |
    And I set value 'Cost plus Pricing' to the cell of the column 'Pricing Strategy' of the 'Contract Lines' table for the row with the following data:
      | Item | 1 |
    And I click on 'Save' button
    And I wait for 10 seconds


    And info: ------ PPT SLIDE 6 -----------------
    And I click 'Cost Allocation' in table 'Contract Lines' table for row with data:
      | Item | 1 |
    Then I click on 'Click here to add' link


    And info: ------ PPT SLIDE 7 -----------------
    And I set value '1 Overall' to the cell of the column 'Sender WBS' of the 'Contract Lines Allocations' table for the row with the following data:
      | % Cost | 0.00% |
    And I click on 'Material Cost' check
    And I wait for 2 seconds
    And I click on 'Confirm' button
    And I wait for 10 seconds


    And info: ------ PPT SLIDE 8 -----------------
    And I click on 'Import BOM' check
    And I click on 'Save' button
    And I wait for 10 seconds
    Then I click on 'WBS Structure' tab
    Then page with name 'WBS page' is opened


    And info: ------ PPT SLIDE 9 -----------------
    And I click 'Open' in table 'WBS' table for row with data:
      | Cost Structure | 1 Overall |
    Then I switch to browser tab 'Estimate Overall'
    Then I click on 'Bill of Material' tab
    When I select node with name 'REGFG1' in 'Bill Of Material' tree
    Then I choose in 'Cog' menu the next menu chain:
      | Expand/Collapse |
      | Expand All      |
    And I click 'Delete Row' in table 'Bill of Material' table for row with data:
      | Description | Regression Semi Finished Goods 2 |
    And I click on 'Yes' button


    And info: ------ PPT SLIDE 10 -----------------
    And I set value '20' to the cell of the column 'Lead Time' of the 'Bill of Material' table for the row with the following data:
      | Description | Regression Finished Goods 1 |
#    And I set value '30' to the cell of the column 'Lead Time' of the 'Bill of Material' table for the row with the following data:
#      | Description | Regression Raw Material 1 |
    And I click on 'Save' button
    Then I click on 'Hamburger' menu
    Then I choose in 'Hamburger' menu the next menu chain:
      | Update & Cost Consolidated BOM |
      | All parts                      |
    And I click on 'Run All Parts' button
    And I wait for 10 seconds
    Then I click on 'Procurement & Production' tab


    And info: ------ PPT SLIDE 11 -----------------
    And 'Procurement & Production' table contain row with following data:
      | Description | Regression Finished Goods 1 |
#    And 'Procurement & Production' table contain row with following data:
#      | Description | Regression Raw Material 2 |
#    And 'Procurement & Production' table contain row with following data:
#      | Description | Regression Finished Goods 1 |
#    And 'Procurement & Production' table contain row with following data:
#      | Description | Regression Raw Material 1 |

    And 'Procurement & Production' table contain row with following data:
      | Source Unit Cost | $ 1,000.00 |
#    And 'Procurement & Production' table contain row with following data:
#      | Source Unit Cost | $ 200.00 |
#    And 'Procurement & Production' table contain row with following data:
#      | Source Unit Cost | $ 1,000.00 |
#    And 'Procurement & Production' table contain row with following data:
#      | Source Unit Cost | $ 100.00 |

    And I click on 'Labor' tab
    And page with name 'Estimate Labor' is opened
    When I select 'Labor (preferred)' in the 'View' dropdown
    And I click on 'Save' button
    Then I click on 'Click here to add' link

    And info: ------ PPT SLIDE 12 -----------------
    And I set value 'REG_CONSULTANT1' to the cell of the column 'Resource Group' of the 'Labor' table for the row with the following data:
      | Sequence | 1 |
    And I set value '1' to the cell of the column 'FTE' of the 'Labor' table for the row with the following data:
      | Sequence | 1 |
    And 'Labor' table contain row with following data:
      | Effort | 10376 hours |
    And 'Labor' table contain row with following data:
      | Start | 1/1/25 |
    And 'Labor' table contain row with following data:
      | End | 12/31/29 |
    And I click on 'Save' button


    And info: ------ PPT SLIDE 13 -----------------
    Then I switch to previous tab
    Then I click on 'CLINs' tab


    And info: ------ PPT SLIDE 14 -----------------
    Then I click on 'Reload' button
    And I wait for 30 seconds
    And I click on 'Save' button
    And I wait for 10 seconds
    Then I click on 'Regression Finished Goods 1' link
    And I set value '50' to the cell of the column 'Percentage' of the 'Line Items Details' table for the row with the following data:
      | Description | Fixed fee |
    And I click on 'Re-price' button
    And I wait for 30 seconds
   # And 'Line Items Details' table contain row with following data:
   #   | Amount | $ 2,074,032.02 |
    And I click on 'Confirm' button
    And I wait for 10 seconds
    And I click on 'Save' button
    Then I click on 'Hamburger' menu
    Then I choose in 'Hamburger' menu the next menu chain:
      | Open                |
      | Cost Price Analysis |

    And info: ------ PPT SLIDE 15 -----------------
    Then I click on 'Workbench' tab
    Then page with name 'Workbench page' is opened
    When I select 'Detailed Breakdown - A&D (shared)' in the 'View' dropdown
  #  And 'Workbench' table contain row with following data:
   #   | Grand total Hours           | 6,200.00     |


