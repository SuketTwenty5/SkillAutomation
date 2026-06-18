
Feature: POR Verification

  Background:
    Given open site
    And I perform login
    And I wait for 10 seconds
    Then page with name 'Main page' is opened

  Scenario: POR Verification
    Given generate values and store into the variables:
      | $bidName | TEST 2024-02-01 03:37 |



    And I click on 'PROPOSALS' tab
    Then page with name 'Proposals list page' is opened
    When I select 'Proposal' in the 'Filter by' dropdown
    Then I enter text '$bidName' in the 'Filter' field
#    Then page with name 'Bids list page' is opened
    And press enter
#    Then page with name 'Bids list page' is opened
#    And press enter
    Then page with name 'Proposals list page' is opened
    And table row is displayed with the name $bidName
    And I select table row with name $bidName
    And I click on 'Setup' tab
    And page with name 'Setup page' is opened

    Then I click on 'WBS' tab
    Then page with name 'WBS page' is opened

    When I select node with name '$bidName' in 'WBS' tree

    Then I choose in 'Cog' menu the next menu chain:
      | Expand/Collapse |
      | Expand All      |
    And page with name 'WBS page' is opened

    And I click 'Open' in table 'WBS' table for row with data:
      | WBS | 1 Estimate |




    And info: Let’s pick up where we last ended in Part I
    And info: ------------ Step 1 ----------------
#    And I click on 'Setup' tab
    And I click on 'PROPOSALS' tab
    Then page with name 'Proposals list page' is opened
    When I select 'Proposal' in the 'Filter by' dropdown
    Then I enter text '$bidName' in the 'Filter' field
#    Then page with name 'Bids list page' is opened
    And press enter
#    Then page with name 'Bids list page' is opened
#    And press enter
    Then page with name 'Proposals list page' is opened
    And table row is displayed with the name $bidName
    And I select table row with name $bidName
    And I click on 'Setup' tab
    And page with name 'Setup page' is opened

    And info: Billing Plan: Milestones
    And info: ------------ Step 19 ----------------
    And I click on 'Billing Plan' tab
    Then page with name 'Billing page' is opened
    And 'Billing Plan' table contain row with following data:
      | Milestone | Pricing Hardware |
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
#    And I set value '1/1/24' to the cell of the column 'Date' of the 'Billing Plan' table for the row with the following data:
#      | Sequence  | 2             |
#      | Milestone | Pricing Labor |
#    And I set value '40' to the cell of the column '%' of the 'Billing Plan' table for the row with the following data:
#      | Sequence  | 2             |
#      | Milestone | Pricing Labor |
#
#    And I set value '1/1/25' to the cell of the column 'Date' of the 'Billing Plan' table for the row with the following data:
#      | Sequence  | 4             |
#      | Milestone | Pricing Labor |
#    And I set value '40' to the cell of the column '%' of the 'Billing Plan' table for the row with the following data:
#      | Sequence  | 4             |
#      | Milestone | Pricing Labor |
#
#    And I set value '1/1/26' to the cell of the column 'Date' of the 'Billing Plan' table for the row with the following data:
#      | Sequence  | 5             |
#      | Milestone | Pricing Labor |
#    And I set value '20' to the cell of the column '%' of the 'Billing Plan' table for the row with the following data:
#      | Sequence  | 5             |
#      | Milestone | Pricing Labor |
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

    And 'Billing Plan' table contain row with following data:
      | Milestone         | Pricing Hardware |
      | Revenue - Company | $ 200,000.00     |
      | %                 | 100.000%         |

    And 'Billing Plan' table contain row with following data:
      | Milestone         | Pricing Labor |
      | Date              | 1/1/24        |
      | Revenue - Company | $ 86,016.00   |
      | %                 | 40.000%       |

    And 'Billing Plan' table contain row with following data:
      | Milestone         | Pricing Labor |
      | Date              | 1/1/25        |
      | Revenue - Company | $ 86,016.00   |
      | %                 | 40.000%       |

    And 'Billing Plan' table contain row with following data:
      | Milestone         | Pricing Labor |
      | Date              | 1/1/26        |
      | Revenue - Company | $ 43,008.00   |
      | %                 | 20.000%       |

    And 'Billing Plan' table contain row with following data:
      | Milestone         | Pricing Support |
      | Date              | 1/1/24          |
      | Revenue - Company | $ 701,568.00    |
      | %                 | 40.000%         |

    And 'Billing Plan' table contain row with following data:
      | Milestone         | Pricing Support |
      | Date              | 1/1/25          |
      | Revenue - Company | $ 701,568.00    |
      | %                 | 40.000%         |


    And 'Billing Plan' table contain row with following data:
      | Milestone         | Pricing Support |
      | Date              | 1/1/26          |
      | Revenue - Company | $ 350,784.00    |
      | %                 | 20.000%         |

#    And 'Billing Plan' table contain row with following data:
#      | Milestone         | Pricing Support |
#      | Date              | 1/1/26          |
#      | Revenue - Company | $ 175,392.00    |
#      | %                 | 40.000%         |

    And info: ------------ Step 20 ----------------
    Then I click on 'Hamburger' menu
    Then page with name 'Billing page' is opened
    Then I choose in 'Hamburger' menu the next menu chain:
      | Open                |
      | Cost Price Analysis |
    Then page with name 'Billing page' is opened
    And I close current browser tab

    And info: ---Cost Price Analysis
    And info: ------------ Step 21 ----------------
    Then I switch to browser tab 'Twenty5 iPE: Consulting'
    Then I click on 'Workbench' tab
    Then page with name 'Workbench page' is opened
    When I select 'Cost and Revenue Component by Description over Time (shared & preferred)' in the 'View' dropdown
    Then page with name 'Workbench page' is opened
    Then text of 'View' dropdown equals 'View: Cost and Revenue Component by Description over Time (shared & preferred)'
    Then page with name 'Workbench page' is opened




#
#    Then I click on 'WBS' tab
#    Then page with name 'WBS page' is opened
#    And I click 'Open' in table 'WBS' table for row with data:
#      | WBS | 1 Estimate |
#    And I close current browser tab
#    Then switch to one of the browser tabs 'Twenty5 iPE: Estimate' or 'Twenty5 iPE: Estimates'
##    And page with name 'Estimate Labor' is opened
#
#    And I click on 'Labor' tab
#    And page with name 'Estimate Labor' is opened
#    When I select 'Labor (shared & preferred)' in the 'View' dropdown
#    And I click on 'Save' button
#    Then text of 'View' dropdown equals 'View: Labor (shared & preferred)'
#
#    Then I choose in 'Cog Settings' menu the next menu chain:
#      | Change Planned Effort Input Mode |
#      | Hours by Month                   |
#    And page with name 'Estimate Labor' is opened
#    Then 'Change Planning Mode' window is displayed
#    And I click on 'Yes' button
#    And page with name 'Estimate Labor' is opened
#
##    And I wait for 60 seconds
##
##    And info: Labor: Add Labor Line Items REG_CONSULTANT2
##    Then I click on 'Click here to add' link
##    And page with name 'Estimate Labor' is opened
##    Then I click on 'Click here to add' link
##    And page with name 'Estimate Hardware Software' is opened
##
##    And I set value '650001 Equipment' to the cell of the column 'Cost Element' of the 'Service' table for the row with the following data:
##      | WBS | 1 Estimate |
##    And I set value 'Equipment' to the cell of the column 'Description' of the 'Service' table for the row with the following data:
##      | WBS | 1 Estimate |
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
##
##    And info: ---Workbench: Result
##    And info: --Success – Part II Complete --------
###    And 'Workbench' table contain row with following data:
###      | Cost Type                          | Material costs |
###      | 2024 Cost: company currency        | $ 1,670.31     |
###      | 2024 Hours                         | 0.00           |
###      | 2025 Cost: company currency        | $ 1,329.69     |
###      | 2025 Hours                         | 0.00           |
###      | 2026 Cost: company currency        | $ 2,000.00     |
###      | 2026 Hours                         | 0.00           |
###      | 2027 Cost: company currency        | $ 2,000.00     |
###      | 2027 Hours                         | 0.00           |
###      | Grand total Cost: company currency | $ 7,000.00     |
###      | Grand total Hours                  | 0.00           |
##    And 'Workbench' table contain row with following data:
##      | Cost Type                          | Material costs |
##      | 2024 Cost: company currency        | $ 1,692.37     |
##      | 2024 Hours                         | 0.00           |
##      | 2025 Cost: company currency        | $ 1,378.07     |
##      | 2025 Hours                         | 0.00           |
##      | 2026 Cost: company currency        | $ 2,123.70     |
##      | 2026 Hours                         | 0.00           |
##      | 2027 Cost: company currency        | $ 2,155.85     |
##      | 2027 Hours                         | 0.00           |
##      | Grand total Cost: company currency | $ 7,350.00     |
##      | Grand total Hours                  | 0.00           |
##
#    And info: -------PPT Part 3------------------
#    And info: ------ PPT Slide 4-----------------
#    And info: ------ Add Inflation Labor tab-----
#
#    Then I click on 'Hamburger' menu
#    Then I choose in 'Hamburger' menu the next menu chain:
#      | Open Proposal |
#    Then page with name 'Workbench page' is opened
#    And I close current browser tab
#
#    Then I switch to browser tab 'Twenty5 iPE: Consulting'
#    And I wait for 10 seconds
#    Then I click on 'WBS' tab
#    Then page with name 'WBS page' is opened
#    And I click 'Open' in table 'WBS' table for row with data:
#      | WBS | 1 Estimate |
#    Then page with name 'WBS page' is opened
#    And I close current browser tab
#
#    Then switch to one of the browser tabs 'Twenty5 iPE: Estimate' or 'Twenty5 iPE: Estimates'
#    Then I click on 'Labor' tab
#    And page with name 'Estimate Labor' is opened
#
#    And page with name 'Estimate Labor' is opened
#    When I select 'Labor (shared & preferred)' in the 'View' dropdown
#    And I click on 'Save' button
#    Then text of 'View' dropdown equals 'View: Labor (shared & preferred)'
#
#    And I set value 'Escalation EUR Commodities' to the cell of the column 'Escalation Factor' of the 'Labor' table for the row with the following data:
#      | Sequence | 2 |
#    And page with name 'Estimate Labor' is opened
#    And I set value 'Escalation Electronics' to the cell of the column 'Escalation Factor' of the 'Labor' table for the row with the following data:
#      | Sequence | 3 |
#
#    And I set value '1/1/25' to the cell of the column 'Source Date' of the 'Labor' table for the row with the following data:
#      | Sequence | 1 |
#    And I set value '1/1/25' to the cell of the column 'Source Date' of the 'Labor' table for the row with the following data:
#      | Sequence | 2 |
#    And I set value '1/1/25' to the cell of the column 'Source Date' of the 'Labor' table for the row with the following data:
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
#    And I set value '1/1/25' to the cell of the column 'Source Date' of the 'Service' table for the row with the following data:
#      | Description | Field Material |
#    And I set value '1/1/25' to the cell of the column 'Source Date' of the 'Service' table for the row with the following data:
#      | Description | Raw Materials |
#    And I set value '1/1/25' to the cell of the column 'Source Date' of the 'Service' table for the row with the following data:
#      | Description | Equipment |
#
#
#    And I click on 'Save' button
#    Then I click on 'Update Cost' button
#    And wait for 20 seconds
#    And page with name 'Estimate Hardware Software' is opened
#
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
##    Then page with name 'Workbench page' is opened
#    Then I click on 'Workbench' tab
#    Then page with name 'Workbench page' is opened
#
##    When I select 'Cost and Revenue Component by Description over Time (shared & preferred)' in the 'View' dropdown
##    Then page with name 'Workbench page' is opened
##    Then text of 'View' dropdown equals 'View: Cost and Revenue Component by Description over Time (shared & preferred)'
##    Then page with name 'Workbench page' is opened
#
##    And 'Workbench' table contain row with following data:
##      | Cost Type                          | Material costs |
##      | 2024 Cost: company currency        | $ 1,670.31     |
##      | 2024 Hours                         | 0.00           |
##      | 2025 Cost: company currency        | $ 1,329.69     |
##      | 2025 Hours                         | 0.00           |
##      | 2026 Cost: company currency        | $ 2,000.00     |
##      | 2026 Hours                         | 0.00           |
##      | 2027 Cost: company currency        | $ 2,000.00     |
##      | 2027 Hours                         | 0.00           |
##      | Grand total Cost: company currency | $ 7,000.00     |
##      | Grand total Hours                  | 0.00           |
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
#    And I wait for 10 seconds
#    Then I click on 'WBS' tab
#    Then page with name 'WBS page' is opened
#    Then I click on 'Hamburger' menu
#    Then I choose in 'Hamburger' menu the next menu chain:
#      | Generate Cost Model incl. Formulas  |
#      | Roll-up Costs & Recalculate Formula |
#
#    And info: ------ PPT Slide 8-----------------
#    And info: ----------Contract Lines tab-----------------
#    Then I click on 'Lines Items' tab
#    Then page with name 'Lines Items page' is opened
##
#    Then I click on 'Click here to add' link
#    Then page with name 'Lines Items page' is opened
#
#    And I set value 'Pricing Labor' to the cell of the column 'Description' of the 'Contract Lines' table for the row with the following data:
#      | Item | 1 |
#    And I set value '1.1 Design & Build Phase' to the cell of the column 'Receiver WBS' of the 'Contract Lines' table for the row with the following data:
#      | Item | 1 |
#    And I click on 'Save' button
#    Then page with name 'Lines Items page' is opened
#
#    And info: ------ PPT Slide 9-----------------
#    And info: Cost Allocations: Add line item
#    And I click 'Pricing Labor' in table 'Contract Lines' table for row with data:
#      | Item | 1 |
#    Then page with name 'Lines Items page' is opened
#    Then 'Line Item Details' window is displayed
#
#    And I click on 'Cost Allocations' tab
#    Then page with name 'Lines Items page' is opened
#    Then I click on 'Click here to add' link
#    Then page with name 'Lines Items page' is opened
#    And I set value '1.1 Design & Build Phase' to the cell of the column 'Sender WBS' of the 'Contract Lines Allocations' table for the row with the following data:
#      | % Cost | 0.00% |
#    Then page with name 'Lines Items page' is opened
#    And I set value '100' to the cell of the column '% Cost' of the 'Contract Lines Allocations' table for the row with the following data:
#      | % Cost | 0.00% |
#
#    And info: ------ PPT Slide 10-----------------
#    And info: Cost Allocations: Add line item
#    And I click on 'Pricing & Qty' tab
#    Then page with name 'Lines Items page' is opened
#
#    When I enter data into the next fields:
#      | 'Revenue Recognition Method' field | Cash based, or based on payment milestones |
#      | 'Pricing Strategy' field           | Fixed price with T&M rates                 |
#    Then page with name 'Lines Items page' is opened
#    Then I click on 'Confirm' button
#    Then page with name 'Lines Items page' is opened
#    Then I click on 'Confirm' button
#    Then page with name 'Lines Items page' is opened
#
#    And info: Add Contract Lines: Pricing Hardware
#    And info: ------ PPT Slide 11-----------------
#    And I click '+' in table 'Contract Lines' table for row with data:
#      | Item | 1 |
#    Then page with name 'Lines Items page' is opened
#
#    And I set value 'Pricing Hardware' to the cell of the column 'Description' of the 'Contract Lines' table for the row with the following data:
#      | Item | 2 |
#    And I set value '1.2 Transition Phase' to the cell of the column 'Receiver WBS' of the 'Contract Lines' table for the row with the following data:
#      | Item | 2 |
#    And I click on 'Save' button
#    Then page with name 'Lines Items page' is opened
#
#    And I click 'Pricing Hardware' in table 'Contract Lines' table for row with data:
#      | Item | 2 |
#    Then page with name 'Lines Items page' is opened
#    Then 'Line Item Details' window is displayed
#
#    And info: ------ PPT Slide 12-----------------
#    And I click on 'Cost Allocations' tab
#    Then page with name 'Lines Items page' is opened
#    Then I click on 'Click here to add' link
#    Then page with name 'Lines Items page' is opened
#    And I set value '1.2 Transition Phase' to the cell of the column 'Sender WBS' of the 'Contract Lines Allocations' table for the row with the following data:
#      | % Cost | 0.00% |
#    And I set value '100' to the cell of the column '% Cost' of the 'Contract Lines Allocations' table for the row with the following data:
#      | % Cost | 0.00% |
#
#
#    And info: ------ PPT Slide 13-----------------
#    And info: Cost Allocations: Add line item
#    And I click on 'Pricing & Qty' tab
#    Then page with name 'Lines Items page' is opened
#
#    When I enter data into the next fields:
#      | 'Pricing Strategy' field           | Fixed pricing                                    |
#      | 'Revenue Recognition Method' field | Accrual based, or based on delivery of end items |
#    Then page with name 'Lines Items page' is opened
#    And I set value '200000' to the cell of the column 'Amount' of the 'Pricing&Qty' table for the row with the following data:
#      | Description | List price |
#    Then page with name 'Lines Items page' is opened
#
#    And info: ------ PPT Slide 14-----------------
#    Then I click on 'Delivery Schedule' tab
#    And I click on 'Click here to add' link
#    Then 'Delivery Schedule' table is displayed
#    Then page with name 'Lines Items page' is opened
#
#    And info: ------ PPT Slide 15-----------------
#    And I set value '0.50' to the cell of the column 'Qty - From' of the 'Delivery Schedule' table for the row with the following data:
#      | Revenue | $ |
#    And I set value '7/1/24' to the cell of the column 'To Date' of the 'Delivery Schedule' table for the row with the following data:
#      | Qty - From | 0.50 ea |
#    And I click '+' in table 'Delivery Schedule' table for row with data:
#      | Qty - From | 0.50 ea |
#    And I set value '7/1/25' to the cell of the column 'To Date' of the 'Delivery Schedule' table for the row with the following data:
#      | Revenue | $ 0.00 |
#    And I set value '0.50' to the cell of the column 'Qty - From' of the 'Delivery Schedule' table for the row with the following data:
#      | To Date | 7/1/25 |
#
#    Then I click on 'Confirm' button
#    Then page with name 'Lines Items page' is opened
#
#
#    And info: ------ PPT Slide 16-----------------
#    And I click '+' in table 'Contract Lines' table for row with data:
#      | Item | 2 |
#    Then page with name 'Lines Items page' is opened
#
#    And I set value 'Pricing Support' to the cell of the column 'Description' of the 'Contract Lines' table for the row with the following data:
#      | Item | 3 |
#    And I click on 'Save' button
#    Then page with name 'Lines Items page' is opened
#
#    And I click 'Pricing Support' in table 'Contract Lines' table for row with data:
#      | Item | 3 |
#    Then page with name 'Lines Items page' is opened
#    Then 'Line Item Details' window is displayed
#
#    And info: ------ PPT Slide 17-----------------
#    And I click on 'Cost Allocations' tab
#    Then page with name 'Lines Items page' is opened
#    Then I click on 'Click here to add' link
#    Then page with name 'Lines Items page' is opened
#    And I set value '1.3 Run & Maintain' to the cell of the column 'Sender WBS' of the 'Contract Lines Allocations' table for the row with the following data:
#      | % Cost | 0.00% |
#    And I set value '100' to the cell of the column '% Cost' of the 'Contract Lines Allocations' table for the row with the following data:
#      | % Cost | 0.00% |
#
#    And info: ------ PPT Slide 18-----------------
#    And info: Cost Allocations: Add line item
#    And I click on 'Pricing & Qty' tab
#    Then page with name 'Lines Items page' is opened
#
#    When I enter data into the next fields:
#      | 'Pricing Strategy' field           | Fixed price with T&M rates                 |
#      | 'Revenue Recognition Method' field | Cash based, or based on payment milestones |
#    Then page with name 'Lines Items page' is opened
#    Then I click on 'Confirm' button
#    Then page with name 'Lines Items page' is opened
#
#    Then I click on 'Confirm' button
#    Then page with name 'Lines Items page' is opened
#
#    And info: Billing Plan: Milestones
#    And info: ------------ Step 19 ----------------
#    And I click on 'Billing Plan' tab
#    Then page with name 'Billing page' is opened
#    And 'Billing Plan' table contain row with following data:
#      | Milestone | Pricing Hardware |
#    And I click '+' in table 'Billing Plan' table for row with data:
#      | Sequence | 1 |
#    Then page with name 'Billing page' is opened
#
#    And I set value '1/1/24' to the cell of the column 'Date' of the 'Billing Plan' table for the row with the following data:
#      | Sequence | 2 |
#
#    And I set value '40' to the cell of the column '%' of the 'Billing Plan' table for the row with the following data:
#      | Sequence | 2 |
#    Then I click on 'Save' button
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
#
#
#
#
#
#
#
#
#
#
#    And info: ------ PPT Slide 8-----------------
#    And info: ----------Contract Lines tab-----------------
#    Then I click on 'Lines Items' tab
#    Then page with name 'Lines Items page' is opened
##
#    Then I click on 'Click here to add' link
#    Then page with name 'Lines Items page' is opened
#
#    And I set value 'Pricing Labor' to the cell of the column 'Description' of the 'Contract Lines' table for the row with the following data:
#      | Item | 1 |
#    And I set value '1.1 Design & Build Phase' to the cell of the column 'Receiver WBS' of the 'Contract Lines' table for the row with the following data:
#      | Item | 1 |
#    And I click on 'Save' button
#    Then page with name 'Lines Items page' is opened
#
#    And info: ------ PPT Slide 9-----------------
#    And info: Cost Allocations: Add line item
#    And I click 'Pricing Labor' in table 'Contract Lines' table for row with data:
#      | Item | 1 |
#    Then page with name 'Lines Items page' is opened
#    Then 'Line Item Details' window is displayed
#
#    And I click on 'Cost Allocations' tab
#    Then page with name 'Lines Items page' is opened
#    Then I click on 'Click here to add' link
#    Then page with name 'Lines Items page' is opened
#    And I set value '1.1 Design & Build Phase' to the cell of the column 'Sender WBS' of the 'Contract Lines Allocations' table for the row with the following data:
#      | % Cost | 0.00% |
#    Then page with name 'Lines Items page' is opened
#    And I set value '100' to the cell of the column '% Cost' of the 'Contract Lines Allocations' table for the row with the following data:
#      | % Cost | 0.00% |
#
#    And info: ------ PPT Slide 10-----------------
#    And info: Cost Allocations: Add line item
#    And I click on 'Pricing & Qty' tab
#    Then page with name 'Lines Items page' is opened
#
#    When I enter data into the next fields:
#      | 'Revenue Recognition Method' field | Cash based, or based on payment milestones |
#      | 'Pricing Strategy' field           | Fixed price with T&M rates                 |
#    Then page with name 'Lines Items page' is opened
#    Then I click on 'Confirm' button
#    Then page with name 'Lines Items page' is opened
#    Then I click on 'Confirm' button
#    Then page with name 'Lines Items page' is opened
#
#
#    And info: Add Contract Lines: Pricing Hardware
#    And info: ------ PPT Slide 11-----------------
#    And I click '+' in table 'Contract Lines' table for row with data:
#      | Item | 1 |
#    Then page with name 'Lines Items page' is opened
#
#    And I set value 'Pricing Hardware' to the cell of the column 'Description' of the 'Contract Lines' table for the row with the following data:
#      | Item | 2 |
#    And I set value '1.2 Transition Phase' to the cell of the column 'Receiver WBS' of the 'Contract Lines' table for the row with the following data:
#      | Item | 2 |
#    And I click on 'Save' button
#    Then page with name 'Lines Items page' is opened
#
#    And I click 'Pricing Hardware' in table 'Contract Lines' table for row with data:
#      | Item | 2 |
#    Then page with name 'Lines Items page' is opened
#    Then 'Line Item Details' window is displayed
#
#    And info: ------ PPT Slide 12-----------------
#    And I click on 'Cost Allocations' tab
#    Then page with name 'Lines Items page' is opened
#    Then I click on 'Click here to add' link
#    Then page with name 'Lines Items page' is opened
#    And I set value '1.2 Transition Phase' to the cell of the column 'Sender WBS' of the 'Contract Lines Allocations' table for the row with the following data:
#      | % Cost | 0.00% |
#    And I set value '100' to the cell of the column '% Cost' of the 'Contract Lines Allocations' table for the row with the following data:
#      | % Cost | 0.00% |
#
#
#    And info: ------ PPT Slide 13-----------------
#    And info: Cost Allocations: Add line item
#    And I click on 'Pricing & Qty' tab
#    Then page with name 'Lines Items page' is opened
#
#    When I enter data into the next fields:
#      | 'Pricing Strategy' field           | Fixed pricing                                    |
#      | 'Revenue Recognition Method' field | Accrual based, or based on delivery of end items |
#    Then page with name 'Lines Items page' is opened
#    And I set value '200000' to the cell of the column 'Amount' of the 'Pricing&Qty' table for the row with the following data:
#      | Description | List price |
#    Then page with name 'Lines Items page' is opened
#
#    Then I click on 'Delivery Schedule' tab
#    And I click on 'Click here to add' link
#    Then 'Delivery Schedule' table is displayed
#    Then page with name 'Lines Items page' is opened
#    And I set value '0.50' to the cell of the column 'Qty - From' of the 'Delivery Schedule' table for the row with the following data:
#      | Revenue | $ |
#    And I set value '7/1/24' to the cell of the column 'To Date' of the 'Delivery Schedule' table for the row with the following data:
#      | Qty - From | 0.50 ea |
#    And I click '+' in table 'Delivery Schedule' table for row with data:
#      | Qty - From | 0.50 ea |
#    And I set value '7/1/25' to the cell of the column 'To Date' of the 'Delivery Schedule' table for the row with the following data:
#      | Revenue | $ 0.00 |
#    And I set value '0.50' to the cell of the column 'Qty - From' of the 'Delivery Schedule' table for the row with the following data:
#      | To Date | 7/1/25 |
#
#    Then I click on 'Confirm' button
#    Then page with name 'Lines Items page' is opened
#
#
#    And info: ------ PPT Slide 14-----------------
#    And I click '+' in table 'Contract Lines' table for row with data:
#      | Item | 2 |
#    Then page with name 'Lines Items page' is opened
#
#    And I set value 'Pricing Support' to the cell of the column 'Description' of the 'Contract Lines' table for the row with the following data:
#      | Item | 3 |
#    And I click on 'Save' button
#    Then page with name 'Lines Items page' is opened
#
#    And I click 'Pricing Support' in table 'Contract Lines' table for row with data:
#      | Item | 3 |
#    Then page with name 'Lines Items page' is opened
#    Then 'Line Item Details' window is displayed
#
#    And info: ------ PPT Slide 15-----------------
#    And I click on 'Cost Allocations' tab
#    Then page with name 'Lines Items page' is opened
#    Then I click on 'Click here to add' link
#    Then page with name 'Lines Items page' is opened
#    And I set value '1.3 Run & Maintain' to the cell of the column 'Sender WBS' of the 'Contract Lines Allocations' table for the row with the following data:
#      | % Cost | 0.00% |
#    And I set value '100' to the cell of the column '% Cost' of the 'Contract Lines Allocations' table for the row with the following data:
#      | % Cost | 0.00% |
#
#    And info: ------ PPT Slide 16-----------------
#    And info: Cost Allocations: Add line item
#    And I click on 'Pricing & Qty' tab
#    Then page with name 'Lines Items page' is opened
#
#    When I enter data into the next fields:
#      | 'Pricing Strategy' field           | Fixed price with T&M rates                 |
#      | 'Revenue Recognition Method' field | Cash based, or based on payment milestones |
#    Then page with name 'Lines Items page' is opened
#    Then I click on 'Confirm' button
#    Then page with name 'Lines Items page' is opened
#
#    Then I click on 'Confirm' button
#    Then page with name 'Lines Items page' is opened
#
#    And info: Billing Plan: Milestones
#    And info: ------------ Step 17 ----------------
#    And I click on 'Billing Plan' tab
#    Then page with name 'Billing page' is opened
#    And 'Billing Plan' table contain row with following data:
#      | Milestone | Pricing Hardware |
#
#    And I click '+' in table 'Billing Plan' table for row with data:
#      | Sequence | 1 |
#    Then page with name 'Billing page' is opened
#
#    And I set value '1/1/24' to the cell of the column 'Date' of the 'Billing Plan' table for the row with the following data:
#      | Sequence | 2 |
#
#    And I set value '40' to the cell of the column '%' of the 'Billing Plan' table for the row with the following data:
#      | Sequence | 2 |
#    Then I click on 'Save' button
#    And I wait for 40 seconds
#
#
#    Then I click on 'Hamburger' menu
#    Then page with name 'Billing page' is opened
#    Then I choose in 'Hamburger' menu the next menu chain:
#      | Open                |
#      | Cost Price Analysis |
#    Then page with name 'Billing page' is opened
#    And I close current browser tab
#
#    And info: ---Cost Price Analysis
#    And info: ------------ Step 18 ----------------
#    Then I switch to browser tab 'Twenty5 iPE: Consulting'
#    Then I click on 'Workbench' tab
#    Then page with name 'Workbench page' is opened