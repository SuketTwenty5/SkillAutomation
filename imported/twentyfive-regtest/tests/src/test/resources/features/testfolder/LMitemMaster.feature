@ItemMaster
Feature: 4. Item Master

  @ART-215 @LM-TC-003_1 @START
  Scenario: TC-Item-Master-001: Navigate to Products & Services
    Given open site
    And I perform Mfg 2.4 login
    Then page with name 'Main page' is opened
    And info: ---1. Click Master Data tab. Sub-tab options are displayed.---
    When I click on 'Master Data' tab
    Then 'Master Data' options is displayed
    And I wait for 3 seconds
    And info: ---2. Select Products & Services option. Verify that user is redirected to Products / Services page.---
    When I click on 'Products & Services' sub-tab
    And I wait for 5 seconds
    Then page with name 'Item list page' is opened
#    And I click on 'New' button
#    And I wait for 5 seconds
    And info: ---3. Hover on the New button. Verify that Create from Template, Create from Type, Create Template, and Edit Template options are displayed.---
    When I hover on 'New' button
    Then 'New' options contains items:
        | Create from Type     |
        | Create Template      |
        | Edit Templates       |
    And info: ---4. Hover on Create from Type option. Verify that additional options are displayed.---
    When I hover on 'Create from Type' option
    Then 'Create from Type' dropDown is displayed
    And info: ---5. Select Purchased Part. Verify that user is redirected to KEY INFO page and the following sub-tabs are available: Key Info, Product Management, Planning & Estimating, Cost & Prices, Supplier Sources, Comments & Files, Texts.---
    When I click on 'Purchased Part' option
    And I wait for 5 seconds
    Then page with name 'KEY INFO page' is opened
    Then I see the following TABS in the 'KEY INFO page' Top Menu_-Warning-_:
      | KEY INFO              |
      | Product Management    |
      | PLANNING & ESTIMATING |
      | COSTS & PRICES        |
      | SUPPLIER SOURCES      |
      | COMMENTS & FILES      |
      | Texts                 |

  @ART-214 @LM-TC-003_2 @RUN
  Scenario:TC-Item-Master-002: Create Purchased Part
    And info: ---1. Click Save button without editing any fields. Verify that an error message appears listing required fields and missed required fields’ borders are highlighted in red: Part or Service Number, Description, Base Unit.---
    When I click on 'Save' button
    And 'Error' dialog is displayed
    And I hover on 'Error' dialog
    Then text of 'Error' dialog equals_-Warning-_:
    """
    Part or Service Number * is required in Key Info tab
    Description * is required in Key Info tab
    Base Unit *
    Manage Other Units
    is required in Key Info tab
    (click to hold)
    """
    And verify border-color css of 'Error' dialog is 'red'
    And verify border-color css of 'Part or Service Number' field is 'red'
    And verify border-color css of 'Description' field is 'red'
    And verify border-color css of 'Base Unit' field is 'red'
#    And I refresh page
    And I wait for 30 seconds
#    Then page with name 'KEY INFO page' is opened
    And info: ---2. Fill Part or Service Number field with a value between 1 and 255 characters. Field accepts the value “Regression Test <MMDDYYYY>”.---
#    And I wait for 5 seconds
    Given generate values and store into the variables:
      | $uniquePurchasedPart | Regression Test Purchased Part ${CUR_DATE,yyyy-MM-dd hh:mm} |
    When I enter data into the next fields:
      | 'Part or Service Number' field            | $uniquePurchasedPart   |
    Then page with name 'KEY INFO page' is opened
    And info: ---3. Fill Description field by copying and pasting the Part or Service Number value. Field accepts the value.---
    When I enter data into the next fields:
      | 'Description' field            | $uniquePurchasedPart   |
    Then page with name 'KEY INFO page' is opened
    And info: ---4. Verify contents of Type of Product or Service. Verify that dropdown is enabled and “Purchased Part” is selected by default.---
    When I enter data into the next fields:
      | 'Type of Product or Service' field            | Purchased Part   |
    Then page with name 'KEY INFO page' is opened
    And info: ---5. Select Commodity Code / Material Group. Verify that dropdown is enabled and empty by default. “Electronic Components” is available and is applied after selecting---
    When I enter data into the next fields:
      | 'Select Commodity Code or Material Group' field  | Electronics   |
    Then page with name 'KEY INFO page' is opened
    And info: ---6. Select Base Unit by clicking on dropdown. Verify that dropdown is enabled and empty by default. “ea” is available and is applied after selecting.---
    When I enter data into the next fields:
      | 'Base Unit' field            | each   |
    Then page with name 'KEY INFO page' is opened
    And I wait for 3 seconds
    And info: ---7. Click Save. Success message “Data saved successfully” appears.---
    When I click on 'Save' button
    Then 'Data saved successfully' dialog is displayed
    And I wait for 5 seconds
    And if visible click on 'Yes' button
    Then page with name 'KEY INFO page' is opened


  @ART-216 @LM-TC-003_3 @END
  Scenario: TC-Item-Master-002.1: Update Planning & Estimating for Purchased Part
    And info: ---1. Click on Planning & Estimating sub-tab. Verify that user is redirected to the Planning & Estimating tab.---
    When I click on 'Planning & Estimating' tab
    Then page with name 'Planning & Estimating page' is opened
    And info: ---2. Select “How should the system estimate cost of this item, product & service” dropdown. Verify that dropdown is enabled and “MFC-Purchased” is available for selection.---
    When I enter data into the next fields:
      | 'How should the system estimate cost of this item, product and service' field  | Purchased   |
    Then page with name 'Planning & Estimating page' is opened
    And info: ---3. Select “Which quantity rule should be used to help estimate price?” dropdown. Verify that dropdown is enabled and “Total quantity of quote (with optional phases separate)” is available for selection.---
    When I enter data into the next fields:
      | 'How are proposal BOM quantities consolidated for costing' field  | Total quantity of quote (with optional phases separate)   |
    Then page with name 'Planning & Estimating page' is opened
    And info: ---4. Click in Procurement Lead-time (in days) field and type in 1. Verify that value is accepted.---
    When I enter data into the next fields:
      | 'Procurement Lead time in days' field  | 1   |
    Then page with name 'Planning & Estimating page' is opened
    And info: ---5. Click in Receipt & Inspection Time dropdown and type in 1. Verify that value is accepted.---
    When I enter data into the next fields:
      | 'Receipt & Inspection Time' field  | 1   |
    Then page with name 'Planning & Estimating page' is opened
    And info: ---6. Select Quantity Curve dropdown. Verify that dropdown is enabled and “Power Curve (5% reduced for 2x qty)” is available for selection.---
    When I enter data into the next fields:
      | 'Quantity Curve' field  | Power Curve (5% reduced for 2x qty)   |
    Then page with name 'Planning & Estimating page' is opened
    And info: ---7. Select Escalation Factor dropdown. Verify that dropdown is enabled and “Escalation US Commodities” is available for selection.---
    When I enter data into the next fields:
      | 'Escalation Factor' field  | Escalation United States   |
    Then page with name 'Planning & Estimating page' is opened
    And info: ---8. Click Save. Success message “Data saved successfully” appears.---
    When I click on 'Save' button
    Then 'Data saved successfully' dialog is displayed
    And I wait for 5 seconds
#    And if visible click on 'Yes' button
#    Then page with name 'KEY INFO page' is opened