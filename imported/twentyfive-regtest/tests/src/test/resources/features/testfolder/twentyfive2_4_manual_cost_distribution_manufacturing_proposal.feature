@DistributeLaborCosts-2-4
Feature: 5. Distribute Labor Costs using Manual Effort input

  @TCM-013 @START
  Scenario: TC-Manufacturing-Proposal-013: Labor Manual Effort
    Given open site
    And I perform Mfg 2.4 login
    Then page with name 'Main page' is opened
    Given generate values and store into the variables:
      | $uniqueProposalName | TEST Labor Manual Effort ${CUR_DATE,yyyy-MM-dd hh:mm} |
    And info: ---1. Start creating a new Proposal with Type Regression Test Only - Manufacturing and verify Setup tab is opened---
    Given I click on 'PROPOSALS' tab
    Then page with name 'Proposals list page' is opened
    Then click on 'New' button
    And page with name 'Setup page' is opened
    When I enter data into the next fields:
      | 'Description' field   | $uniqueProposalName |
    And page with name 'Setup page' is opened
    When I enter data into the next fields:
      | 'Proposal Type' field | Regression Test Only - Manufacturing |
    And page with name 'Setup page' is opened
    And info: ---2. Fill required fields with test data → Estimated Project Start = 4/1/25, Project End = 3/31/26---
    When I enter data into the next fields:
      | 'Planned Start' field | 4/1/25            |
    And page with name 'Setup page' is opened
    When I enter data into the next fields:
      | 'End' field                  | 3/31/26          |
    When I enter data into the next fields:
      | 'Your Company' field            | Regression Test |
    And page with name 'Setup page' is opened
    When I enter data into the next fields:
      | 'Leading Department' field            | US - New York   |
    And page with name 'Setup page' is opened
    When I enter data into the next fields:
      | 'Client Customer sell-to' field | Regression Test - Customer USD |
    And page with name 'Setup page' is opened
    And info: ---3. In "How do you want to Start your Project Plan", ensure "Select a template" radio button is selected and choose "(AS) Regression - Labor Manual Effort & Inflation - Template" from dropdown---
    And I check 'Select a template' radio
    When I enter data into the next fields:
      | 'Search Box' field | (AS) Regression - Labor Manual Effort & Inflation - Template |
    And page with name 'Setup page' is opened
    And info: ---4. Click Copy button → system saves data and reloads page---
    And I click on 'Copy' button
    Then I wait for 20 seconds
    Then page with name 'Setup page' is opened
    And info: ---5. Navigate to Pricing tab, select "Regression Test - Bill Rates" from "How are Labor/Item Rates Calculated" dropdown and verify value is accepted---
    Then I click on 'Pricing' tab
    And page with name 'Pricing page' is opened
    When I enter data into the next fields:
      | 'How are Labor/Item Rates Calculated' field | Regression Test - Bill Rates |
    And info: ---6. Click Save and verify data is saved---
    Then I click on 'Save' button
    Then I wait for 7 seconds
    And page with name 'Pricing page' is opened
    And info: ---7. Navigate to Cost Structure tab, click Open in Work Package field → verify Estimate is opened in new tab, Labor tab selected, and grid shows Resource Group, Description, Distribution, Rate in Resource Unit with consultant data---
    Then I click on 'Cost Structure' tab
    And page with name 'Cost Structure page' is opened
    When I select 'TEST:-DONOT_CHANGE' in the 'View' dropdown
    And I click on 'Save' button
    Then text of 'View' dropdown equals 'View: TEST:-DONOT_CHANGE (preferred)'
    And I click 'Open' in table 'WBS' table for row with data:
      | WBS Code              | 1                   |
    Then I switch to tab 2
    And I wait for 7 seconds
    And I click on 'Labor' tab
    When I select 'Test View for Regression - DONOT CHANGE' in the 'View' dropdown
    And I wait for 7 seconds
    And I click on 'Save' button
    Then text of 'View' dropdown equals 'View: Test View for Regression - DONOT CHANGE'
#    When I select 'Regression Test - Manufacturing' in the 'View' dropdown
#    And I wait for 7 seconds
#    And I click on 'Save' button
#    Then text of 'View' dropdown equals 'View: Regression Test - Manufacturing (shared & preferred)'
    And page with name 'Estimate Labor' is opened
    And 'Labor Dual' table contain row with following data:
      | Resource Group   | REG_CONSULTANT1  |
      | Resource Group - Free Text      | REG_CONSULTANT1  |
      | Distribution     | Manual (Monthly) |
      | Rate in Resource Unit | $ 120.00/hr      |
    And 'Labor Dual' table contain row with following data:
      | Resource Group   | REG_CONSULTANT2  |
      | Resource Group - Free Text      | REG_CONSULTANT2  |
      | Distribution     | Manual (Monthly) |
      | Rate in Resource Unit | $ 90.00/hr       |
    And 'Labor Dual' table contain row with following data:
      | Resource Group   | REG_CONSULTANT3  |
      | Resource Group - Free Text      | REG_CONSULTANT3  |
      | Distribution     | Flat (all months equal) |
      | Rate in Resource Unit | $ 100.00/hr      |
    And info: ---8. Click Gear icon above grid → Change Planned Effort Input Mode → Hours By Month---
    Then I choose in 'Cog Settings' menu the next menu chain:
      | Change Planned Effort Input Mode |
      | Hours by Month                   |
    Then I wait for 7 seconds
    And info: ---9. Popup "Change Planning Mode in Other Estimates" appears---
    Then 'Change Planning Mode' window is displayed
    And info: ---10. Click Yes on popup → verify popup disappears and grid refreshed with MMM-YY columns from Apr-25 to Mar-26, empty effort values---
    And I if visible click on 'Yes' button
    Then 'Change Planning Mode' window is not displayed
    And page with name 'Estimate Labor' is opened
    Then I verify column Hours by Month distribution between dates '4/1/2025' and '3/31/2026'
    And I verify effort values in 'Labor Dual' table are empty for rows with data:
      | Resource Group  | REG_CONSULTANT1 |
    And I verify effort values in 'Labor Dual' table are empty for rows with data:
      | Resource Group  | REG_CONSULTANT2 |
    And I verify effort values in 'Labor Dual' table are empty for rows with data:
      | Resource Group  | REG_CONSULTANT3 |
    And info: ---11. Enter Test Data Values in REG_CONSULTANT1 row → Apr-25 = 500, Oct-25 = 700, and verify values accepted---
    And I set value '500' to the cell of the column 'Apr-25' of the 'Labor Dual' table for the row with the following data:
      | Resource Group  | REG_CONSULTANT1 |
    And I wait for 3 seconds
    And I set value '700' to the cell of the column 'Oct-25' of the 'Labor Dual' table for the row with the following data:
      | Resource Group  | REG_CONSULTANT1 |
    And I wait for 3 seconds
    And info: ---12. Verify for REG_CONSULTANT1 → Planned Effort = 1200 hours---
    And 'Labor Dual' table contain row with following data:
      | Resource Group   | REG_CONSULTANT1  |
      | Resource Group - Free Text      | REG_CONSULTANT1  |
      | Distribution     | Manual (Monthly) |
      | Rate in Resource Unit | $ 120.00/hr      |
      | Effort   | 1200             |
      | Apr-25           | 500              |
      | Oct-25           | 700              |
    And I verify effort values in 'Labor Dual' table are empty for rows with data:
      | Resource Group  | REG_CONSULTANT2 |
    And info: ---13. Enter Test Data Values in REG_CONSULTANT2 row → all months = 100 each, and verify values accepted---
    And I set value '100' to the cell of the column 'Apr-25' of the 'Labor Dual' table for the row with the following data:
      | Resource Group  | REG_CONSULTANT2 |
    And I wait for 3 seconds
    And I set value '100' to the cell of the column 'May-25' of the 'Labor Dual' table for the row with the following data:
      | Resource Group  | REG_CONSULTANT2 |
    And I wait for 3 seconds
    And I set value '100' to the cell of the column 'Jun-25' of the 'Labor Dual' table for the row with the following data:
      | Resource Group  | REG_CONSULTANT2 |
    And I wait for 3 seconds
    And I set value '100' to the cell of the column 'Jul-25' of the 'Labor Dual' table for the row with the following data:
      | Resource Group  | REG_CONSULTANT2 |
    And I wait for 3 seconds
    And I set value '100' to the cell of the column 'Aug-25' of the 'Labor Dual' table for the row with the following data:
      | Resource Group  | REG_CONSULTANT2 |
    And I wait for 3 seconds
    And I set value '100' to the cell of the column 'Sep-25' of the 'Labor Dual' table for the row with the following data:
      | Resource Group  | REG_CONSULTANT2 |
    And I wait for 3 seconds
    And I set value '100' to the cell of the column 'Oct-25' of the 'Labor Dual' table for the row with the following data:
      | Resource Group  | REG_CONSULTANT2 |
    And I wait for 3 seconds
    And I set value '100' to the cell of the column 'Nov-25' of the 'Labor Dual' table for the row with the following data:
      | Resource Group  | REG_CONSULTANT2 |
    And I wait for 3 seconds
    And I set value '100' to the cell of the column 'Dec-25' of the 'Labor Dual' table for the row with the following data:
      | Resource Group  | REG_CONSULTANT2 |
    And I wait for 3 seconds
    And I set value '100' to the cell of the column 'Jan-26' of the 'Labor Dual' table for the row with the following data:
      | Resource Group  | REG_CONSULTANT2 |
    And I wait for 3 seconds
    And I set value '100' to the cell of the column 'Feb-26' of the 'Labor Dual' table for the row with the following data:
      | Resource Group  | REG_CONSULTANT2 |
    And I wait for 3 seconds
    And I set value '100' to the cell of the column 'Mar-26' of the 'Labor Dual' table for the row with the following data:
      | Resource Group  | REG_CONSULTANT2 |
    And I wait for 3 seconds
    And info: ---14. Verify for REG_CONSULTANT2 → Planned Effort = 1200 hours---
    And 'Labor Dual' table contain row with following data:
      | Resource Group   | REG_CONSULTANT2  |
      | Resource Group - Free Text      | REG_CONSULTANT2  |
      | Distribution     | Manual (Monthly) |
      | Rate in Resource Unit | $ 90.00/hr       |
      | Effort           | 1200             |
      | Apr-25           | 100              |
      | May-25           | 100              |
      | Jun-25           | 100              |
      | Jul-25           | 100              |
      | Aug-25           | 100              |
      | Sep-25           | 100              |
      | Oct-25           | 100              |
      | Nov-25           | 100              |
      | Dec-25           | 100              |
      | Jan-26           | 100              |
      | Feb-26           | 100              |
      | Mar-26           | 100              |
    And info: ---15. Click Save and verify data is saved---
    And I click on 'Save' button
    And 'Data saved successfully' warning message is displayed in 30 seconds
    And info: ---16. In Status widget, click Update Estimate Totals → verify page refreshed and costs for REG_CONSULTANT1 & REG_CONSULTANT2 are NON-ZERO---
    Then I click on 'Update Cost & Prices' link
    Then I wait for 40 seconds
    And page with name 'Estimate Labor' is opened
    When I select 'Regression Test - Manufacturing' in the 'View' dropdown
    And I click on 'Save' button
    Then text of 'View' dropdown equals 'View: Regression Test - Manufacturing (shared & preferred)'
    And the cell value of the 'Cost in Company Currency' column of the 'Labor Dual' table is greater than '0' for a row with the following data:
      | Resource Group  | REG_CONSULTANT1 |
    And the cell value of the 'Cost in Company Currency' column of the 'Labor Dual' table is greater than '0' for a row with the following data:
      | Resource Group  | REG_CONSULTANT2 |
    And info: ---17. Verify that the Effort Grid shows correct values → REG_CONSULTANT1: Apr-25 = 500, Oct-25 = 700; REG_CONSULTANT2: all months = 100 each---
    And 'Labor Dual' table contain row with following data:
      | Resource Group   | REG_CONSULTANT1  |
      | Resource Group - Free Text      | REG_CONSULTANT1  |
      | Distribution     | Manual (Monthly) |
      | Rate in Resource Unit | $ 120.00/hr      |
      | Effort   | 1200             |
      | Apr-25           | 500              |
      | Oct-25           | 700              |
    And 'Labor Dual' table contain row with following data:
      | Resource Group   | REG_CONSULTANT2  |
      | Resource Group - Free Text      | REG_CONSULTANT2  |
      | Distribution     | Manual (Monthly) |
      | Rate in Resource Unit | $ 90.00/hr       |
      | Effort   | 1200             |
      | Apr-25           | 100              |
      | May-25           | 100              |
      | Jun-25           | 100              |
      | Jul-25           | 100              |
      | Aug-25           | 100              |
      | Sep-25           | 100              |
      | Oct-25           | 100              |
      | Nov-25           | 100              |
      | Dec-25           | 100              |
      | Jan-26           | 100              |
      | Feb-26           | 100              |
      | Mar-26           | 100              |

  @TCM-014 @END
  Scenario: TC-Manufacturing-Proposal-014: Switch from Auto to Manual Distribution
    And info: ---1. For REG_CONSULTANT3 row enter Test Data Value in Planned Effort → 900, and verify field accepts the value---
    And I set value '900' to the cell of the column 'Effort' of the 'Labor Dual' table for the row with the following data:
      | Resource Group  | REG_CONSULTANT3 |
    And I wait for 3 seconds
    And info: ---2. Click Save and Refresh → verify data is saved, page refreshed, Effort Grid for REG_CONSULTANT3 = 75 for all months---
    And I click on 'Save' button
    Then I wait for 20 seconds
    And I click on 'Refresh' button
    Then I wait for 30 seconds
    When I select 'Regression Test - Manufacturing' in the 'View' dropdown
    And I click on 'Save' button
    Then text of 'View' dropdown equals 'View: Regression Test - Manufacturing (shared & preferred)'
    And 'Labor Dual' table contain row with following data:
      | Resource Group - Free Text      | REG_CONSULTANT3  |
      | Distribution     | Flat (all months equal) |
      | Effort   | 900              |
      | Apr-25           | 75               |
      | May-25           | 75               |
      | Jun-25           | 75               |
      | Jul-25           | 75               |
      | Aug-25           | 75               |
      | Sep-25           | 75               |
      | Oct-25           | 75               |
      | Nov-25           | 75               |
      | Dec-25           | 75               |
      | Jan-26           | 75               |
      | Feb-26           | 75               |
      | Mar-26           | 75               |
    And info: ---3. For REG_CONSULTANT3 select Test Data Value in Distribution field → Manual (Monthly), and verify value is accepted---
    And I set value 'Manual (Monthly)' to the cell of the column 'Distribution' of the 'Labor Dual' table for the row with the following data:
      | Resource Group  | REG_CONSULTANT3 |
    And I wait for 3 seconds
#    And I set value '900' to the cell of the column 'Effort' of the 'Labor Dual' table for the row with the following data:
#      | Resource Group  | REG_CONSULTANT3 |
#    And I wait for 3 seconds
    And info: ---4. Click Save and Refresh → verify data is saved, page refreshed, Distribution = Manual (Monthly), Effort Grid = 75 for all months---
    And I click on 'Save' button
    Then I wait for 20 seconds
    And I click on 'Refresh' button
    Then I wait for 30 seconds
    When I select 'Regression Test - Manufacturing' in the 'View' dropdown
    And I click on 'Save' button
    Then text of 'View' dropdown equals 'View: Regression Test - Manufacturing (shared & preferred)'
    And info: ---5. In REG_CONSULTANT3 Effort Grid update values → Apr-25 = NULL, May-25 = 150, Oct-25 = NULL, Nov-25 = 150, verify fields accept data---
    And I set value ' ' to the cell of the column 'Apr-25' of the 'Labor Dual' table for the row with the following data:
      | Resource Group  | REG_CONSULTANT3 |
    And I wait for 3 seconds
    And I set value '150' to the cell of the column 'May-25' of the 'Labor Dual' table for the row with the following data:
      | Resource Group  | REG_CONSULTANT3 |
    And I wait for 3 seconds
    And I set value '75' to the cell of the column 'Jun-25' of the 'Labor Dual' table for the row with the following data:
      | Resource Group  | REG_CONSULTANT3 |
    And I wait for 3 seconds
    And I set value '75' to the cell of the column 'Jul-25' of the 'Labor Dual' table for the row with the following data:
      | Resource Group  | REG_CONSULTANT3 |
    And I wait for 3 seconds
    And I set value '75' to the cell of the column 'Aug-25' of the 'Labor Dual' table for the row with the following data:
      | Resource Group  | REG_CONSULTANT3 |
    And I wait for 3 seconds
    And I set value '75' to the cell of the column 'Sep-25' of the 'Labor Dual' table for the row with the following data:
      | Resource Group  | REG_CONSULTANT3 |
    And I wait for 3 seconds
    And I set value ' ' to the cell of the column 'Oct-25' of the 'Labor Dual' table for the row with the following data:
      | Resource Group  | REG_CONSULTANT3 |
    And I wait for 3 seconds
    And I set value '150' to the cell of the column 'Nov-25' of the 'Labor Dual' table for the row with the following data:
      | Resource Group  | REG_CONSULTANT3 |
    And I wait for 3 seconds
    And I set value '75' to the cell of the column 'Dec-25' of the 'Labor Dual' table for the row with the following data:
      | Resource Group  | REG_CONSULTANT3 |
    And I wait for 3 seconds
    And I set value '75' to the cell of the column 'Jan-26' of the 'Labor Dual' table for the row with the following data:
      | Resource Group  | REG_CONSULTANT3 |
    And I wait for 3 seconds
    And I set value '75' to the cell of the column 'Feb-26' of the 'Labor Dual' table for the row with the following data:
      | Resource Group  | REG_CONSULTANT3 |
    And I wait for 3 seconds
    And I set value '75' to the cell of the column 'Mar-26' of the 'Labor Dual' table for the row with the following data:
      | Resource Group  | REG_CONSULTANT3 |
    And I wait for 3 seconds
    And info: ---6. Click Save and Refresh → verify data is saved, page refreshed, Distribution = Manual (Monthly), Effort Grid contains Apr-25 = NULL, May-25 = 150, Jul-25 to Sep-25 = 75, Oct-25 = NULL, Nov-25 = 150, Dec-25 to Mar-26 = 75---
    And I click on 'Save' button
    Then I wait for 20 seconds
    And I click on 'Refresh' button
    Then I wait for 30 seconds
    When I select 'Regression Test - Manufacturing' in the 'View' dropdown
    And I click on 'Save' button
    Then text of 'View' dropdown equals 'View: Regression Test - Manufacturing (shared & preferred)'
    And 'Labor Dual' table contain row with following data:
      | Resource Group - Free Text      | REG_CONSULTANT3  |
      | Distribution     | Manual (Monthly) |
      | Effort           | 900              |
      | Apr-25           |                  |
      | May-25           | 150              |
      | Jun-25           | 75               |
      | Jul-25           | 75               |
      | Aug-25           | 75               |
      | Sep-25           | 75               |
      | Oct-25           |                  |
      | Nov-25           | 150              |
      | Dec-25           | 75               |
      | Jan-26           | 75               |
      | Feb-26           | 75               |
      | Mar-26           | 75               |
    And info: ---7. For REG_CONSULTANT3 select Test Data Value in Distribution field → Flat (all months equal), verify value is accepted---
    And I set value 'Flat (all months equal)' to the cell of the column 'Distribution' of the 'Labor Dual' table for the row with the following data:
      | Resource Group  | REG_CONSULTANT3 |
    And I wait for 3 seconds
#    And I set value '900' to the cell of the column 'Effort' of the 'Labor Dual' table for the row with the following data:
#      | Resource Group  | REG_CONSULTANT3 |
#    And I wait for 3 seconds
    And info: ---8. Click Save and Refresh → verify data is saved, page refreshed, Distribution = Flat (all months equal), Effort Grid = 75 for all months---
    And I click on 'Save' button
    Then I wait for 20 seconds
    And I click on 'Refresh' button
    Then I wait for 30 seconds
    When I select 'Regression Test - Manufacturing' in the 'View' dropdown
    And I click on 'Save' button
    Then text of 'View' dropdown equals 'View: Regression Test - Manufacturing (shared & preferred)'
    And 'Labor Dual' table contain row with following data:
      | Resource Group - Free Text      | REG_CONSULTANT3  |
      | Distribution     | Flat (all months equal) |
      | Effort           | 900              |
      | Apr-25           | 75               |
      | May-25           | 75               |
      | Jun-25           | 75               |
      | Jul-25           | 75               |
      | Aug-25           | 75               |
      | Sep-25           | 75               |
      | Oct-25           | 75               |
      | Nov-25           | 75               |
      | Dec-25           | 75               |
      | Jan-26           | 75               |
      | Feb-26           | 75               |
      | Mar-26           | 75               |
