# TC-Production-or-System-Proposal-CLIN-Upload-Import-BOMs - Bulk CLIN Upload & Import Multiple BOMs

| Field | Value |
| --- | --- |
| Test ID | TC-Production-or-System-Proposal-CLIN-Upload-Import-BOMs |
| Title | Create Production/System Proposal, bulk-upload 160 CLINs from Excel, and Import Multiple BOMs |
| Proposal Type | Regression\|Production/System |
| Map source | `RTA_TEST_SUITE_FEATURE_MAP.md` (Production/System Proposal family, TC-004 .. TC-009) |
| Feature file | `imported/twentyfive-regtest/tests/src/test/resources/features/testfolder/clinUploadImportBOMs.feature` |
| Tags | `@TC-CLIN-UPLOAD-IMPORT @RUN` |

## Preconditions

- The Twenty5 app is reachable and the tester is logged in (SSO).
- Test data file **`CLIN_Upload_Regression_Test_large.xlsx`** (160 CLIN rows) is available on the machine running the test. Set `CLIN_UPLOAD_FILE` if it is not in `~/Downloads`.
- Test Data Values are available for: proposal Title, Estimate name, Regression CLINs View, Regression Test View.

## Run Instructions

When a consultant asks to run this test, use the default app URL unless they provide another URL:

```text
https://app-twenty5ipe-lm-dev.cfapps.us10.hana.ondemand.com/
```

For Claude Desktop, ask before launching Selenium Chrome with the default URL. If the user provides another URL, launch that URL instead.

```bash
scripts/run-test-request.sh "TC-CLIN-UPLOAD-IMPORT"
```

## Test Steps

| # | Test Step Description | Expected Result | Test Data / Screen Capture |
| --- | --- | --- | --- |
| 1 | Start creating a new Proposal with type **Regression\|Production/System** and fill the required fields with Test Data Values. | The required fields are filled with data. | **Title:** `<proposal title>`<br>**Project Type:** Regression\|Production/System<br>**Project Start:** 1/1/27<br>**Project End:** 12/31/31<br>**Business Area:** Missiles & Fire Control<br>**Plant or Site:** LMMFC ORLANDO SITE, Orlando<br>**Client/Customer (Sell-to):** Regression Test - Customer USD (RTC1) |
| 2 | Click **Save**. | Data is saved. The proposal is assigned an ID, displayed in the browser's URL bar. | e.g. `proposal:95f0f3ca-a98c-43ee-bb27-a3325f3e2939` (image-20260614-144114.png) |
| 3 | Navigate to the **WBS Cost Structure** tab. | The content of the WBS Cost Structure tab is displayed. | image-20260614-144306.png |
| 4 | Create an Estimate by clicking the **"+"** icon by the first row and provide a Test Data Value name for it. | A row with an Estimate line is added to the grid (Cost Structure = `Estimate`). | image-20260614-144441.png / image-20260614-144627.png |
| 5 | Click the **Create** link in the Estimate field. | Popup **Estimate** is displayed. Verify: the **Owner** field is filled automatically with the current user's name. | image-20260614-144807.png / image-20260614-144845.png |
| 6 | Click **Confirm & Release**. | Data is saved. WBS Cost Structure tab content is displayed. Verify: the Estimate field displays an **Open** link. | image-20260614-145132.png |
| 7 | Navigate to the **CLINs** tab. | The content of the CLINs tab is displayed. | image-20260614-145323.png |
| 8 | Select view **Regression CLINs View** if not already selected. | The grid is displayed in the selected view. | image-20260614-145432.png |
| 9 | Click the **Upload** button from the top-right of the grid. | Popup **Upload Data from XL or file** is displayed. | image-20260614-145609.png / image-20260614-145759.png |
| 10 | Click the **Select File** button and choose the precondition file. | The file is processed. | `CLIN_Upload_Regression_Test_large.xlsx` (image-20260614-150048.png) |
| 11 | (Wait for processing.) | The records from the file are added as CLIN rows. Verify: number of records added is **160** (top-right of the table shows **Records (1-160/160)**). | image-20260621-093850.png |
| 12 | Click **Save**. | Data is saved. | |
| 13 | Click the **Gear** icon on the top-right of the grid and select **Import Multiple BOMs**. | Popup **Select Contract Lines to Import** is displayed. Verify: the grid in the popup displays **160 rows**. | image-20260614-151319.png / image-20260621-094121.png |
| 14 | Click the checkbox in the **header** of the grid to select all rows. | All rows are selected. | image-20260614-151719.png / image-20260621-094154.png |
| 15 | Click **Import BOMs**. | Popup disappears. Informational messages about Import BOM are displayed and disappear. Verify: the **Import BOM** field displays a **Re-import BOM** link for each row. | image-20260621-094410.png |
| 16 | Open the Estimate by clicking the **pop-out** icon in the Estimate field of the 1st row. Then select view **Regression Test View** if not already selected. | The Estimate opens in a new browser tab and the grid is shown in the selected view. | image-20260614-152313.png / image-20260614-153019.png |

## Automation Coverage (reuse map)

This case reuses the existing Production/System Proposal glue and the shared CLIN upload step.

| Steps | Reuses | Source |
| --- | --- | --- |
| 1-2 (create + save proposal) | `Project Type` select, required-field entry, Save, URL-ID assertion | `LMsetupProductionOrSystemProposal.feature` (TC-004 / TC-005) |
| 3-6 (WBS estimate + Confirm & Release) | `+` add row, `Create` link, `Estimate` popUp, Owner verify, `Confirm & Release`, `Open` link | `LMstructreSetsEstimatesCLINs.feature` (TC-007) |
| 7-8 (CLINs tab + view) | `CLINs` tab, `Regression CLINs View` select | `LMstructreSetsEstimatesCLINs.feature` (TC-008) |
| **9-11 (bulk CLIN Excel upload + 160-record count)** | CLINs `Upload` button, `Upload Data from XL or file` dialog, file upload, ExtJS store-backed 160-record assertion. | `ClinsPage` + `IpeSteps` |
| 12 (save) | Save | existing |
| 13-15 (Import Multiple BOMs) | `Group Gear` menu, `Import Multiple BOMs`, `Select Contract Lines to Import` popUp, `Top row` checkbox, all-rows-selected verify, `Import BOMs`, notifications | `LMstructreSetsEstimatesCLINs.feature` (TC-009) + `ClinsPage` |
| 15 (Re-import BOM link per row) | partial — `Import BOM` / `Cost BOM` column check exists; per-row `Re-import BOM` assertion is **NEW** | `basicBOMImport.feature` pattern |
| 16 (open estimate via pop-out) | `Estimate Hyperlink` in `ClinsTable`, switch to tab 2, view select | `ClinsTable.java` + TC-009 |

> **Run data:** the data file `CLIN_Upload_Regression_Test_large.xlsx` must be present locally. The runner checks `CLIN_UPLOAD_FILE` first and falls back to `~/Downloads/CLIN_Upload_Regression_Test_large.xlsx`.

## Gherkin Scenario

```gherkin
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
    # On the LM environment: Business Area = 'Leading Company' field (placeholder 'Select Company');
    # Plant or Site = 'Leading Site or Department' field (placeholder 'Select Department').
    When I enter data into the next fields:
      | 'Project Type' field | Regression\|Production/System |
    And I wait for 5 seconds
    When I enter data into the next fields:
      | 'Title or Brief Description' field  | $uniqueProposalName             |
    When I enter data into the next fields:
      | 'Project Start' field               | 1/1/27                          |
    When I enter data into the next fields:
      | 'Project End' field                 | 12/31/31                        |
    When I enter data into the next fields:
      | 'Leading Company' field             | Missiles & Fire Control         |
    When I enter data into the next fields:
      | 'Leading Site or Department' field  | LMMFC ORLANDO SITE, Orlando     |
    When I enter data into the next fields:
      | 'Client Customer sell-to' field     | Regression Test - Customer USD  |
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
    When I click on 'Upload' button
    And 'Upload Data from XL or file' dialog is displayed
    And I upload CLIN file 'CLIN_Upload_Regression_Test_large.xlsx'
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
```

---

_Authored to match the `test-case-guides/` format and reuse the bundled Twenty5 automation under `imported/twentyfive-regtest`._
