@ALL @ADD-BILLING-ITEMS
Feature: Add Billing Items to a Proposal created from another Proposal

  Background:
    Given open site
    And I perform login
    Then page with name 'Main page' is opened

  @CONSULTING-PROPOSAL-FROM-PROPOSAL
  Scenario: Make consulting proposal from another proposal
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
      | 'Capability' field              | REG Company Code               |
      | 'Leading Site' field            | RG Dept-1                      |
      | 'Profit Center' field           | REG PC-1                       |
      | 'CRM Opportunity' field         | DS4220 opportunities territory |
    And page with name 'Setup page' is opened
    And text of 'Client Customer sell-to' field equals 'MONTEREY MUSHROOMS INC (MONTERY)'
    And page with name 'Setup page' is opened
    And I check 'Select a prior project' radio
    And page with name 'Setup page' is opened
    When I enter data into the next fields:
      | 'Search for historical projects to copy' field | REGRESSION TEST FOR COPY |
    And click on 'Copy' button
    And page with name 'Setup page' is opened
    Then I click on 'Billing Items' tab
#    Then update cost and price
    Then I click on 'Update Cost' button
    Then I wait for 30 seconds
    Then I click on 'Update Cost' button
    Then I wait for 30 seconds
    Then text of 'Estimated Cost' textBox equals '97,760.00'

  Scenario: Add Billing Items
    And I click on 'PROPOSALS' tab
    Then page with name 'Proposals list page' is opened
    When I select 'Default (shared)' in the 'Proposal View' dropdown
    Then page with name 'Proposals list page' is opened
    Then text of 'Proposal View' dropdown equals 'View: Default (shared)'
    Then I search '$uniqueProposalName' Proposal in Proposal List page
    Then page with name 'Proposals list page' is opened
    Then I click on 'first' proposal
    And I wait for 30 seconds
    And page with name 'Setup page' is opened
    Then I click on 'Billing Items' tab
    And page with name 'Billing Items page' is opened
    Then I click on 'Click here to add' link
    And I set value 'REG Test Billing Item 01' to the cell of the column 'Description' of the 'Billing Items' table for the row with the following data:
      | Item | 1 |
    And I click 'Cost Allocation' in table 'Billing Items' table for row with data:
      | Item | 1 |
    Then 'Billing Items' popUp is displayed
    Then I click on 'Click here to add' link
    And I set value '1 Estimate' to the cell of the column 'Sender WBS' of the 'Contract Lines Allocations' table for the row with the following data:
      | % Cost | 0.00% |
    And I click on 'Material Cost' check
    And I set value '75' to the cell of the column '% Cost' of the 'Contract Lines Allocations' table for the row with the following data:
      | Sender WBS | 1 Estimate |

    And I click on 'Dialog Pricing' tab
#    Then page with name 'Lines Items page' is opened
    When I enter data into the next fields:
      | 'Revenue Recognition Method' field | Cash based, or based on payment milestones |
      | 'Pricing Strategy' field           | Fixed price with T&M rates                 |
#    Then I click on 'No Records '
    And I set value '80000' to the cell of the column 'Amount' of the 'Pricing&Qty' table for the row with the following data:
      | Description | Labor price (adjusted) |
    And I set value '10000' to the cell of the column 'Amount' of the 'Pricing&Qty' table for the row with the following data:
      | Description | Discount |
    And I set value '2000' to the cell of the column 'Amount' of the 'Pricing&Qty' table for the row with the following data:
      | Description | Sales tax |
    And I click on 'Confirm' button
    And page with name 'Billing Items page' is opened
    Then I click on 'Update Cost' button
    Then I wait for 30 seconds
