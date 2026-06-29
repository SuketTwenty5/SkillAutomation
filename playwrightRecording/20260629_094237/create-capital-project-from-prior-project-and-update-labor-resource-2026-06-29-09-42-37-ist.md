# Test Case: Create Capital Project From Prior Project And Update Labor Resource

## Source Recording

- Recording folder: `playwrightRecording/20260629_094237`
- Source file: `recording.spec.js`
- Normalized steps: `recording.steps.json`
- Recorded at: `2026-06-29 09:42 IST`
- Application: Twenty5 iPE Quote dev environment
- Start URL: `https://approuter-twenty5ipe-dev.cfapps.us10.hana.ondemand.com/#quote`

## Objective

Verify that a user can create a capital project from a prior project, open the estimate, add a labor line, select a labor pool, and inspect copied labor data.

## Preconditions

- User has access to the Twenty5 iPE Quote dev environment.
- User is able to sign in to the SAP authentication page.
- The leading site `SE- Sweden (Lund)` is available.
- The customer `Regression Test - Customer USD` is available.
- The prior project `(AS) Copy from Template v5` is available for copy.
- The estimate popup can be opened from the `Estimates` tab.
- The labor pool option `Project Manager` is available.

## Test Data

| Field | Value |
| --- | --- |
| Project Name | `Test Capital Project Copy` |
| Probable Project Start | `6/29/2031` |
| Project End | `6/28/2037` |
| Leading Site | `SE- Sweden (Lund)` |
| Client / Customer Search | `usd` |
| Client / Customer | `Regression Test - Customer USD` |
| Copy Mode | `Select a Prior Project` |
| Prior Project Search | `(AS) Copy from template v5` |
| Prior Project Result | `\| (AS) Copy from Template v5` |
| Estimate Tab | `Estimates` |
| Estimate Section | `Labor` |
| New Labor Resource | `REG_CONSULTANT999999` |
| Labor Pool | `Project Manager` |
| Copied Labor Resource To Inspect | `REG_CONSULTANT1 (row 1)` |

## Test Steps

1. Navigate to the Twenty5 iPE Quote dev application.
2. Sign in through the SAP authentication page.
3. Click `Continue` on the SAP authentication screen.
4. Click `Continue` again if the second confirmation screen is shown.
5. From the quote list page, click `New`.
6. Enter `Test Capital Project Copy` in the `Project Name` field.
7. Set `Probable Project Start` to `6/29/2031`.
8. Set `Project End` to `6/28/2037`.
9. Open the `Leading Site` picker.
10. Select `SE- Sweden (Lund)`.
11. Open the `Client/Customer (Sell-to)` field.
12. Search for `usd`.
13. Select `Regression Test - Customer USD`.
14. Select the `Select a Prior Project` option.
15. Search for `(AS) Copy from template v5` in the visible prior-project search component `//*[contains(@data-componentid,'iBESearchComboBox') and @aria-hidden="false"]`.
16. Select the matching `| (AS) Copy from Template v5` prior project result.
17. Click `Copy` to copy project data from the prior project.
18. Open the `Estimates` tab.
19. Click the `Open` link.
20. Confirm the app prompt by clicking `Yes`.
21. In the opened estimate window, open the `Labor` tab.
22. Click the add-row icon in the labor grid.
23. Select the new labor resource free-text cell.
24. Enter `REG_CONSULTANT999999`.
25. Press `Enter` to commit the labor resource value.
26. Open the labor pool picker for the new row.
27. Select `Project Manager`.
28. Click the save button in the estimate window.
29. Double-click `REG_CONSULTANT1 (row 1)` to inspect the copied labor resource row.
30. Repeat the inspection if the row remains selected and no blocking dialog is shown.
31. Close the estimate window.
32. Close the original quote window.

## Expected Results

- The user can sign in successfully.
- The `New` project workflow opens.
- Required setup fields accept the recorded values.
- The customer search returns `Regression Test - Customer USD`.
- The prior project search returns `| (AS) Copy from Template v5`.
- Clicking `Copy` copies project data from the selected prior project.
- The `Estimates` tab is available after copy.
- The estimate opens in a new browser window after confirming with `Yes`.
- The `Labor` tab is accessible.
- A new labor row can be added.
- The new labor row accepts `REG_CONSULTANT999999`.
- The labor pool value can be set to `Project Manager`.
- Saving the estimate completes without an unexpected blocking error.
- The copied labor resource `REG_CONSULTANT1 (row 1)` remains visible for inspection.

## Automation Notes

- The raw codegen script starts at an SAP OAuth URL. A maintained test should navigate to the application URL and reuse the project authentication helper or storage state.
- The generated script currently uses `page` before explicitly creating it. A maintained test should create a page from the browser context or use the repository's existing Playwright test fixtures.
- The raw recording contains generated ExtJS IDs, including `#fieldset-6181-table`, `#iBEBusUnitLocal-6207-trigger-picker`, `#iBESearchComboBox-6228-inputEl`, `#gridview-2077-record-8102`, `#gridview-2077-record-31574`, `#iBEComboBoxRemote-3034-trigger-picker`, `#gridview-2079`, and `#button-1109`. Replace these with stable locators before this becomes a maintained Playwright test. The `#iBEBusUnitLocal-6207-trigger-picker` id was mapped back to the visible `Leading Site *` field; do not describe it as `Org. Unit *` or `Business Unit` in consultant-facing steps.
- Replace generated ExtJS IDs with role, label, text, placeholder, or scoped field locators.
- Use existing workspace helpers before adding new selectors.
- For picker triggers, scope from the visible field label to the trigger in the same field container.
- For prior-project copy searches, enter step 15 text in the visible `//*[contains(@data-componentid,'iBESearchComboBox') and @aria-hidden="false"]` search component, not in a nearby label-scoped field.
- Run locator scanning when stable locators are unclear.
- Wrap unavoidable ExtJS fallbacks in named helpers with comments.
- The recorded script includes dialog dismissal handlers. A converted automation should capture the dialog message and assert whether it is expected.
- The recorded project name is static. A maintained test should append a timestamp or other unique suffix if the application requires unique project names.
- The labor resource `REG_CONSULTANT999999` appears intentionally invalid or edge-case-like. Confirm whether the expected behavior is acceptance, validation, or an error dialog before turning this into a pass/fail automation assertion.

## Suggested Assertions

- Assert the quote list or application shell is visible after login.
- Assert the new project form is visible after clicking `New`.
- Assert `Project Name`, `Probable Project Start`, and `Project End` contain the entered values.
- Assert `SE- Sweden (Lund)` is selected in `Leading Site` before searching for the customer.
- Assert `Regression Test - Customer USD` is selected before choosing copy mode.
- Assert the prior project value is selected before clicking `Copy`.
- Assert the `Estimates` tab loads successfully.
- Assert the estimate popup opens after clicking `Yes`.
- Assert the `Labor` tab becomes active.
- Assert a new labor row is added.
- Assert the labor resource cell contains `REG_CONSULTANT999999` after pressing `Enter`, or assert the expected validation message if this value is intentionally invalid.
- Assert the labor pool cell contains `Project Manager`.
- Assert save completes without an unexpected blocking dialog.
- Assert `REG_CONSULTANT1 (row 1)` is visible in the labor grid.

## Latest Execution Evidence

- Status: `not run`
- Command: Not run yet. This artifact was converted from the raw recording only.
- Blocking step: None from conversion. Maintained automation still needs locator hardening before execution.
- Screenshot: Not available.
- Trace: Not available.
- Error context: Not available.
