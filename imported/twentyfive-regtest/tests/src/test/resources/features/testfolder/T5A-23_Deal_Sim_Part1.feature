@T5A-23
Feature: iPE Deal Sim 2 – Part 1

  Background:
    Given open site
    And I perform login
    Then page with name 'Main page' is opened

  Scenario: iPE Deal Sim 2 – Part 1
    Given generate values and store into the variables:
      | $uniqueProposalName | Test Proposal ${CUR_DATE,yyyy-MM-dd hh:mm} |

    And info: ------ Getting Started -------------
    And info: ------ PPT Slide 1 -----------------
    And I click on 'PROPOSALS' tab
    Then page with name 'Proposals list page' is opened
    Then click on 'New' button
    And page with name 'Setup page' is opened

    And info: -- Setup: Enter Project Details-----
    When I enter data into the next fields:
      | 'Proposal Type' field | Manufacturing project |
    And page with name 'Setup page' is opened
    When I enter data into the next fields:
      | 'Planned Start' field | 1/1/25              |
      | 'Description' field   | $uniqueProposalName |
      | 'End' field           | 12/31/29            |
      | 'Single phase' radio  | check               |
    And page with name 'Setup page' is opened

    And info: ------ PPT Slide 2 -----------------
    And info: -- Setup: Opportunity, Client & Your Organization-
    When I enter data into the next fields:
      | 'Capability' field      | REG Company Code               |
      | 'Leading Site' field    | RG Dept-1                      |
      | 'Client Customer sell-to' field | MONTEREY MUSHROOMS INC         |
    And page with name 'Setup page' is opened
    And text of 'Leading Company Currency' field equals '$ USA - US Dollar(USD)'
    And text of 'Client Customer sell-to' field equals 'MONTEREY MUSHROOMS INC'

    And info: -- Setup: How do you want to Start your Project Plan-
    And I check 'Select a template' radio
    When I enter data into the next fields:
      | 'Search for Template from Library' field | BOM Test Template |
    And click on 'Copy' button
    And page with name 'Setup page' is opened

    And info: ------ PPT Slide 3 -----------------
    Then I click on 'WBS Structure' tab
    Then page with name 'WBS page' is opened

    When I select node with name '$uniqueProposalName' in 'WBS' tree
    Then I choose in 'Cog' menu the next menu chain:
      | Expand/Collapse |
      | Expand All      |

    And page with name 'WBS page' is opened
    And I set value 'Bill of Material' to the cell of the column 'Estimating Strategy' of the 'WBS' table for the row with the following data:
      | Cost Structure | 1 Overall |
    And I click on 'Save' button
    And page with name 'WBS page' is opened
    And I click 'Create' in table 'WBS' table for row with data:
      | Cost Structure | 1 Overall |

    And info: -- Setup: Estimate--
    When I enter data into the next fields:
      | 'Owner' field | Tech Twenty5 |
    And I click on 'Confirm & Open' button


