@copyProdSystemProposalCLINs
Feature: 3. Copy Prior Production/System Proposal & Verify CLINs

  @ART-CPS-001 @TC-Copy-ProdSys-CLINs-001 @START @END
  Scenario: TC-Copy-ProductionSystem-Proposal-001: Copy Prior Proposal & Verify CLINs Grid (incl. Quantities per Month)
    Given open site
    And I perform Mfg 2.4 login
    Then page with name 'Main page' is opened
    And I click on 'Proposals' tab
    Then page with name 'Proposals list page' is opened

    And info: ---1. Navigate and click New -> Create from Type -> Regression | Production/System. Verify Setup tab content is displayed.---
    When I click on 'New' button
    And I wait for 5 seconds
    Then page with name 'Setup page' is opened
    When I enter data into the next fields:
      | 'Project Type' field | Regression \| Production/System Proposal |
    And I wait for 5 seconds
    Then page with name 'Setup page' is opened

    And info: ---2. Fill in the fields with Test Data Values. Note some fields are filled in by default with other values.---
    Given generate values and store into the variables:
      | $uniqueProposalName | TC-Copy-ProductionSystem-Proposal ${CUR_DATE,yyyy-MM-dd hh:mm} |
    When I enter data into the next fields:
      | 'Title or Brief Description' field | $uniqueProposalName |
    Then page with name 'Setup page' is opened
    When I enter data into the next fields:
      | 'Project Start' field | 1/1/2029 |
    Then page with name 'Setup page' is opened
    When I enter data into the next fields:
      | 'Project End' field | 12/31/2033 |
    Then page with name 'Setup page' is opened
    When I enter data into the next fields:
      | 'Business Area' field | Missiles & Fire Control |
    Then page with name 'Setup page' is opened
    When I enter data into the next fields:
      | 'Plant or Site' field | LMMFC ORLANDO SITE, Orlando |
    Then page with name 'Setup page' is opened
    When I enter data into the next fields:
      | 'Client/Customer (Sell-to)' field | Regression Test - Customer USD |
    Then page with name 'Setup page' is opened
    When I enter data into the next fields:
      | 'Proposal Authorization#' field | 123456 |
    Then page with name 'Setup page' is opened
    And info: ---Verify field values are retained.---
    And I verify 'Title or Brief Description' of setup page is enabled, required, free text and has text '$uniqueProposalName'
    And I verify 'Proposal Type' of setup page is enabled, required, drop down and has text 'Regression | Production/System Proposal'
    And I verify 'Company Currency' of setup page is enabled, required, free text and has text '$ USA - US Dollar(USD)'

    And info: ---3. Click Save. Verify data is saved and an ID is assigned to the proposal in the browser URL bar.---
    When I click on 'Save' button
    And 'Data saved successfully' warning message is displayed in 30 seconds
    Then page with name 'Setup page' is opened
    And I verify current page URL contains "#(proposal|quote):[0-9a-fA-F\\-]{36}"

    And info: ---4. In "Re-use Prior Work to Start from a Better Place", select "Select a prior proposal" and choose the Test Data Value. Verify copy options are checked.---
    When I click on 'Select a prior proposal' radio button
    And I wait for 2 seconds
    When I enter data into the next fields:
      | 'Search Box' field | AA Modular - Regression Testing Example - Copy Proposal - End Item Delivery Date Consistency |
    And I wait for 2 seconds
    Then page with name 'Setup page' is opened
    And I verify 'WBS or work-packages (5 elements)' of setup page is enabled, not required, check box and is checked
    And I verify 'Priced Line Items' of setup page is enabled, not required, check box and is checked
    And I verify 'Transfer to LMMFC ORLANDO SITE, Orlando' of setup page is enabled, not required, check box and is checked

    And info: ---5. Click Copy. Verify data is copied, page refreshes, and the Setup tab is displayed.---
    And click on 'Copy' button
    And I wait for 10 seconds
    Then page with name 'Setup page' is opened

    And info: ---6. Navigate to the CLINs tab. Verify CLINs tab content is displayed.---
    And I click on 'CLINs' tab
    Then page with name 'CLINs page' is opened

    And info: ---7. If not already selected, select the view "Regression CLINs View". Verify grid is displayed in the selected view.---
    When I select 'Regression CLINs View' in the 'View' dropdown
    And I click on 'Save' button
    Then text of 'View' dropdown equals 'View: Regression CLINs View (shared)'

    And info: ---8 & 9. Verify Item 1 (Set is NULL, CLIN 10000, PART 991 - Single Delivery).---
    And 'CLINs' table contain row with following data:
      | Item              | 1                       |
      | CLIN              | 10000                   |
      | Part Number       | 2011M                   |
      | Description       | PART 991 - Single Delivery |
      | Type              | Hardware & Software     |
      | Estimate          | 1 Estimate              |
      | Cost Allocation   | 1.1 PART 991 (2011M)    |
      | Import BOM        | Est. Cost               |
      | Quantity          | 1 ea                    |
      | Quantity per Month | 0 ea                   |
      | End of Delivery   | 12/31/2029              |
      | Delivery Plant    | LMMFC ORLANDO SITE, Orlando |

    And info: ---10. Verify Item 2 (Set is NULL, CLIN 10001, PART 991 - 12 Months).---
    And 'CLINs' table contain row with following data:
      | Item              | 2                       |
      | CLIN              | 10001                   |
      | Part Number       | 2011M                   |
      | Description       | PART 991 - 12 Months    |
      | Type              | Hardware & Software     |
      | Estimate          | 1 Estimate              |
      | Cost Allocation   | 1.2 PART 991 (2011M)    |
      | Import BOM        | Est. Cost               |
      | Quantity          | 120 ea                  |
      | Quantity per Month | 10 ea                  |
      | Start of Delivery | 1/1/2029                |
      | End of Delivery   | 12/31/2029              |
      | Delivery Plant    | LMMFC ORLANDO SITE, Orlando |

    And info: ---11. Verify Item 3 (Set is NULL, CLIN 10002, PART 991 - 48 Months).---
    And 'CLINs' table contain row with following data:
      | Item              | 3                       |
      | CLIN              | 10002                   |
      | Part Number       | 2011M                   |
      | Description       | PART 991 - 48 Months    |
      | Type              | Hardware & Software     |
      | Estimate          | 1 Estimate              |
      | Cost Allocation   | 1.3 PART 991 (2011M)    |
      | Import BOM        | Est. Cost               |
      | Quantity          | 480 ea                  |
      | Quantity per Month | 10 ea                  |
      | Start of Delivery | 1/1/2029                |
      | End of Delivery   | 12/31/2032              |
      | Delivery Plant    | LMMFC ORLANDO SITE, Orlando |

    And info: ---12. Verify Item 4 (Set is NULL, CLIN 10002, PART 991 - Discrete).---
    And 'CLINs' table contain row with following data:
      | Item              | 4                       |
      | Part Number       | 2011M                   |
      | Description       | PART 991 - Discrete     |
      | Type              | Hardware & Software     |
      | Estimate          | 1 Estimate              |
      | Cost Allocation   | 1.4 PART 991 - 12 Months (2011M) |
      | Import BOM        | Est. Cost               |
      | Quantity          | 153 ea                  |
      | Quantity per Month | 0 ea                   |
      | Start of Delivery | 1/31/2029               |
      | End of Delivery   | 6/30/2030               |
      | Delivery Plant    | LMMFC ORLANDO SITE, Orlando |

    And info: ---13. On the top-right of the Grid click the Gear icon and select Contract Line Quantities per Month. Verify the Monthly Grid is added to the right of the table.---
    And I click on 'Group Gear' menu
    Then 'Group Gear' options is displayed
    When I choose in 'Group Gear' menu the next menu chain:
      | Contract Line Quantities per Month |
    And I wait for 5 seconds
    Then page with name 'CLINs page' is opened

    And info: ---14. Verify for Item 1 all months are NULL.---
    And 'CLINs' table contain row with following data:
      | Item   | 1 |
      | Jan-29 |   |
      | Dec-29 |   |

    And info: ---15. Verify for Item 2, from Jan-29 to Dec-29 the value for each month is 10 ea.---
    And 'CLINs' table contain row with following data:
      | Item   | 2     |
      | Jan-29 | 10 ea |
      | Feb-29 | 10 ea |
      | Mar-29 | 10 ea |
      | Apr-29 | 10 ea |
      | May-29 | 10 ea |
      | Jun-29 | 10 ea |
      | Jul-29 | 10 ea |
      | Aug-29 | 10 ea |
      | Sep-29 | 10 ea |
      | Oct-29 | 10 ea |
      | Nov-29 | 10 ea |
      | Dec-29 | 10 ea |

    And info: ---16. Verify for Item 3, from Jan-29 to Dec-32 the value for each month is 10 ea.---
    And 'CLINs' table contain row with following data:
      | Item   | 3     |
      | Jan-29 | 10 ea |
      | Dec-29 | 10 ea |
      | Jan-30 | 10 ea |
      | Dec-30 | 10 ea |
      | Jan-31 | 10 ea |
      | Dec-31 | 10 ea |
      | Jan-32 | 10 ea |
      | Dec-32 | 10 ea |

    And info: ---17. Verify Item 4 monthly distribution.---
    And 'CLINs' table contain row with following data:
      | Item   | 4     |
      | Feb-29 | 1 ea  |
      | Mar-29 | 10 ea |
      | Apr-29 | 6 ea  |
      | May-29 | 10 ea |
      | Jun-29 | 12 ea |
      | Aug-29 | 14 ea |
      | Sep-29 | 10 ea |
      | Oct-29 | 16 ea |
      | Nov-29 | 10 ea |
      | Dec-29 | 18 ea |
      | Feb-30 | 22 ea |
      | Jun-30 | 24 ea |
