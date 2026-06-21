@clinUploadImportBOMs
Feature: CLIN Bulk Upload & Import Multiple BOMs (Production/System Proposal)

  # Reuses existing Production/System glue (setup, WBS estimate, CLINs view,
  # Group Gear -> Import Multiple BOMs). Steps 9-11 (CLINs "Upload Data from XL
  # or file" dialog + 160-record count) and the per-row "Re-import BOM" check
  # require NEW glue in ClinsPage/IpeSteps, and the data file
  # CLIN_Upload_Regression_Test_large.xlsx must be present.

  @TC-CLIN-UPLOAD-IMPORT @RUN
  Scenario: TC-Production-or-System-Proposal-CLIN-Upload-Import-BOMs: Bulk CLIN upload & Import Multiple BOMs
    Given open site
    And I perform Mfg 2.4 login
    Then page with name 'Main page' is opened
    Given generate values and store into the variables:
      | $uniqueProposalName | TC-Production-System CLIN Upload ${CUR_DATE,yyyy-MM-dd hh:mm} |
      | $estimateName       | Estimate                                                    |

    And info: ---1. Create a new Proposal of type Regression | Production/System and fill required fields ---
    And I click on 'Proposals' tab
    Then page with name 'Proposals list page' is opened
    When I click on 'New' button
    And I wait for 5 seconds
    Then page with name 'Setup page' is opened
    # Business Area = 'Leading Company' field (placeholder 'Select Company');
    # Plant or Site = 'Leading Site or Department' field (placeholder 'Select Department').
    When I enter data into the next fields:
      | 'Project Type' field | Regression \| Production/System Proposal |
    And I wait for 5 seconds
    Then page with name 'Setup page' is opened
    When I enter data into the next fields:
      | 'Title or Brief Description' field | $uniqueProposalName |
    Then page with name 'Setup page' is opened
    When I enter data into the next fields:
      | 'Project Start' field | 1/1/27 |
    Then page with name 'Setup page' is opened
    When I enter data into the next fields:
      | 'Project End' field | 12/31/31 |
    Then page with name 'Setup page' is opened
    When I enter data into the next fields:
      | 'Leading Company' field | Missiles & Fire Control |
    Then page with name 'Setup page' is opened
    When I enter data into the next fields:
      | 'Leading Site or Department' field | LMMFC ORLANDO SITE, Orlando |
    Then page with name 'Setup page' is opened
    When I enter data into the next fields:
      | 'Client Customer sell-to' field | Regression Test - Customer USD |
    Then page with name 'Setup page' is opened

    And info: ---2. Save and verify the proposal ID is assigned in the URL ---
    When I click on 'Save' button
    And 'Data saved successfully' warning message is displayed in 30 seconds
    And I verify current page URL contains "#(proposal|quote):[0-9a-fA-F\\-]{36}"

    And info: ---3. Navigate to the WBS Cost Structure tab ---
    And I click on 'WBS Cost Structure' tab
    Then page with name 'WBS Cost Structure page' is opened

    And info: ---4. Add an Estimate row via the + icon and name it ---
    And I click '+' in table 'WBS Cost Structure' table in row number 1
    And 'WBS Cost Structure' table contain row with following data:
      | WBS Code | 1      |
      | Estimate | Create |
    And I set value '$estimateName' to the cell of the column 'Cost Structure' of the 'WBS Cost Structure' table for the row with the following data:
      | WBS Code | 1 |

    And info: ---5. Click Create link; verify Owner auto-filled with the current user ---
    When I click 'Create' in table 'WBS Cost Structure' table for row with data:
      | WBS Code | 1 |
    And 'Estimate' popUp is displayed
    And I wait for 7 seconds
    And I verify 'Owner' of WBS Dialog page is enabled, required, drop down and has text 'Suket Suman'

    And info: ---6. Confirm & Release; verify Estimate field shows Open link ---
    When I click on 'Confirm & Release' button
    And 'Estimate' popUp is not displayed
    And 'WBS Cost Structure' table contain row with following data:
      | WBS Code  | 1    |
      | Estimate  | Open |

    And info: ---7. Navigate to the CLINs tab ---
    And I click on 'CLINs' tab
    Then page with name 'CLINs page' is opened

    And info: ---8. Select Regression CLINs View ---
    When I select 'Regression CLINs View' in the 'View' dropdown
    And I click on 'Save' button
    Then text of 'View' dropdown equals 'View: Regression CLINs View (shared)'

    And info: ---9-11. Upload CLINs from XL (NEW GLUE) and verify 160 records ---
    # --- requires new glue: CLINs 'Upload' button + 'Upload Data from XL or file' dialog ---
    When I click on 'Upload' button
    And 'Upload Data from XL or file' dialog is displayed
    And I upload CLIN file '/Users/suketsuman/Downloads/CLIN_Upload_Regression_Test_large.xlsx'
    And I wait for 20 seconds
    Then I verify 160 records are loaded in the CLINs grid

    And info: ---12. Save ---
    And I click on 'Save' button
    And I wait for 5 seconds

    And info: ---13. Gear -> Import Multiple BOMs; verify popup shows 160 rows ---
    And I click on 'Group Gear' menu
    Then 'Group Gear' options is displayed
    When I choose in 'Group Gear' menu the next menu chain:
      | Import Multiple BOMs |
    And 'Select Contract Lines to Import' popUp is displayed
    And I wait for 5 seconds
    Then I verify 160 records are loaded in the import grid

    And info: ---14. Select all rows via header checkbox ---
    When I click on 'Top row' checkbox
    And I wait for 3 seconds
    Then I verify that all rows in 'Select Contract Lines to Import' table are selected

    And info: ---15. Import BOMs; verify Re-import BOM link per row ---
    When I click on 'Import BOMs' button
    And 'Select Contract Lines to Import' popUp is not displayed
    Then 'Wait for Importing BOMs to complete' message is displayed
    Then 'BOMs imported successfully' message is displayed_-Warning-_
    And I wait for 5 seconds

    And info: ---16. Open the Estimate via pop-out icon of the 1st row; select Regression Test View ---
    When I click 'Estimate Hyperlink' in table 'CLINs' table in row number 1
    Then I switch to tab 2
    And I wait for 5 seconds
    When I select 'Regression Test View' in the 'View' dropdown
    And I click on 'Save' button
    Then text of 'View' dropdown equals 'View: Regression Test View'
