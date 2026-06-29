# Test Case: Create Capital Project From Prior Project And Update Labor FTE And Cost

## Source Recording

- Recording folder: `playwrightRecording/20260629_100320`
- Source file: `recording.spec.js`
- Normalized steps: `recording.steps.json`
- Recorded at: `2026-06-29 10:03 IST`
- Application: Twenty5 iPE Quote dev environment
- Start URL: `https://approuter-twenty5ipe-dev.cfapps.us10.hana.ondemand.com/#quote`

## Objective

Verify that a user can create a capital project from a prior project, open the estimate, add a labor line, set labor pool and FTE, remove a copied row, update cost, and verify the calculated total.

## Preconditions

- User has access to the Twenty5 iPE Quote dev environment.
- User is able to sign in to the SAP authentication page.
- The leading site `SE- Sweden (Lund)` is available.
- The customer search value `usd` returns `RTC1`.
- The prior project `(AS) Copy from Template v5` is available for copy.
- The estimate popup can be opened from the `Estimates` tab.
- The labor pool option `Project Manager` is available.

## Test Data

| Field | Value |
| --- | --- |
| Project Name | `Test capital Copy` |
| Probable Project Start | `6/29/2031` |
| Project End | `6/28/2037` |
| Leading Site | `SE- Sweden (Lund)` |
| Client / Customer Search | `usd` |
| Client / Customer Result | `RTC1` |
| Copy Mode | `Select a Prior Project` |
| Prior Project Search | `(AS) Copy from template` |
| Prior Project Result | `\| (AS) Copy from Template v5` |
| Estimate Tab | `Estimates` |
| Estimate Section | `Labor` |
| Labor Pool | `Project Manager` |
| Labor FTE | `2` |
| Labor Rate | `$ 125.00/hr` |
| Expected Updated Cost | `USD 3.28M` |

## Test Steps

1. Navigate to the Twenty5 iPE Quote dev application.
2. Sign in through the SAP authentication page.
3. Click `Continue` on the SAP authentication screen.
4. Click `Continue` again if the second confirmation screen is shown.
5. From the quote list page, click `New`.
6. Set `Probable Project Start` to `6/29/2031`.
7. Set `Project End` to `6/28/2037`.
8. Enter `Test capital Copy` in the `Project Name` field.
9. Open the `Leading Site` picker.
10. Select `SE- Sweden (Lund)`.
11. Open the `Client/Customer (Sell-to)` field.
12. Search for `usd`.
13. Select `RTC1`.
14. Select the `Select a Prior Project` option.
15. Search for `(AS) Copy from template` in the visible prior-project search component `//*[contains(@data-componentid,'iBESearchComboBox') and @aria-hidden="false"]`.
16. Select the matching `| (AS) Copy from Template v5` prior project result.
17. Click `Copy` to copy project data from the prior project.
18. Open the `Estimates` tab.
19. Click the `Open` link.
20. Confirm the app prompt by clicking `Yes`.
21. In the opened estimate window, open the `Labor` tab.
22. Click the add-row icon in the labor grid.
23. Select the new labor row's labor pool cell.
24. Open the labor pool picker.
25. Select `Project Manager`.
26. Enter `2` in the new labor row's FTE field.
27. Verify the labor rate displays `$ 125.00/hr`.
28. Click the save button in the estimate window.
29. Click the delete icon for the copied labor row.
30. Confirm the delete prompt.
31. Click the save button again.
32. Click `Update Cost`.
33. Verify the updated cost total displays `USD 3.28M`.
34. Close the estimate window.
35. Close the original quote window.

## Expected Results

- The user can sign in successfully.
- The `New` project workflow opens.
- Required setup fields accept the recorded values.
- The customer search returns `RTC1`.
- The prior project search returns `| (AS) Copy from Template v5`.
- Clicking `Copy` copies project data from the selected prior project.
- The `Estimates` tab is available after copy.
- The estimate opens in a new browser window after confirming with `Yes`.
- The `Labor` tab is accessible.
- A new labor row can be added.
- The labor pool value can be set to `Project Manager`.
- The FTE field accepts `2`.
- The labor rate displays `$ 125.00/hr`.
- Saving the estimate completes without an unexpected blocking error.
- The copied labor row can be deleted after confirmation.
- Clicking `Update Cost` recalculates the estimate and displays `USD 3.28M`.

## Automation Notes

- The raw codegen script starts at an SAP OAuth URL. A maintained test should navigate to the application URL and reuse the project authentication helper or storage state.
- The generated script currently uses `page` before explicitly creating it. A maintained test should create a page from the browser context or use the repository's existing Playwright test fixtures.
- The raw recording contains generated ExtJS IDs, including `#fieldset-8870-table`, `#iBEBusUnitLocal-8896-trigger-picker`, `#iBESearchComboBox-8917-inputEl`, `#gridview-2077-record-8173`, `#gridview-2077-record-31573`, `#iBEComboBoxRemote-3032-trigger-picker`, `#gridview-2079-record-31573`, `#button-1109`, `#container-1859-innerCt`, and `#container-3775-innerCt`. Replace these with stable locators before this becomes a maintained Playwright test.
- The `#iBEBusUnitLocal-8896-trigger-picker` id was mapped back to the visible `Leading Site *` field; do not describe it as `Org. Unit *` or `Business Unit` in consultant-facing steps.
- Replace generated ExtJS IDs with role, label, text, placeholder, or scoped field locators.
- Use existing workspace helpers before adding new selectors.
- For picker triggers, scope from the visible field label to the trigger in the same field container.
- For prior-project copy searches, enter step 15 text in the visible `//*[contains(@data-componentid,'iBESearchComboBox') and @aria-hidden="false"]` search component, not in a nearby label-scoped field.
- The delete confirmation was recorded through icon-only positional buttons. A converted automation should identify the visible dialog text and button labels before clicking.
- The recorded script includes dialog dismissal handlers around estimate open and cost update. A converted automation should capture the dialog message and assert whether it is expected.
- The recorded project name is static. A maintained test should append a timestamp or other unique suffix if the application requires unique project names.
- Run locator scanning when stable locators are unclear.
- Wrap unavoidable ExtJS fallbacks in named helpers with comments.

## Suggested Assertions

- Assert the quote list or application shell is visible after login.
- Assert the new project form is visible after clicking `New`.
- Assert `Probable Project Start`, `Project End`, and `Project Name` contain the entered values.
- Assert `SE- Sweden (Lund)` is selected in `Leading Site` before searching for the customer.
- Assert `RTC1` is selected before choosing copy mode.
- Assert the prior project value is selected before clicking `Copy`.
- Assert the `Estimates` tab loads successfully.
- Assert the estimate popup opens after clicking `Yes`.
- Assert the `Labor` tab becomes active.
- Assert a new labor row is added.
- Assert the labor pool cell contains `Project Manager`.
- Assert the FTE field contains `2`.
- Assert the labor rate displays `$ 125.00/hr`.
- Assert save completes without an unexpected blocking dialog.
- Assert the copied row is removed after delete confirmation.
- Assert `Update Cost` completes and `USD 3.28M` is visible.

## Latest Execution Evidence

- Status: `passed`
- Executed at: `2026-06-29 11:08:58 IST`
- Duration: `3.3m`
- Command: `scripts/run-playwright-test.sh tests/playwright/create-capital-project-from-prior-project-and-update-labor-fte-and-cost.spec.ts --headed --retries=0`
- Generated spec: `tests/playwright/create-capital-project-from-prior-project-and-update-labor-fte-and-cost.spec.ts`
- Result JSON: `test-results/playwright/results.json`
- Last-run summary: `test-results/.last-run.json`
- Screenshot: [`evidence/updated-cost-page-2026-06-29-11-08-58-ist.png`](evidence/updated-cost-page-2026-06-29-11-08-58-ist.png)
- Trace: Not available from the final passed run.
- Error context: Not available from the final passed run.
- Created estimate URL: `https://approuter-twenty5ipe-dev.cfapps.us10.hana.ondemand.com/#boe:e6cc6207-737c-11f1-9284-693b7f1a364d`
- Runtime evidence: estimate popup opened, `Labor` tab loaded, labor pool cell resolved to `resourceGridLaborPoolColumn`, `Project Manager` was selected, FTE was set to `2`, the copied row was deleted, `Update Cost` completed, and the final screenshot shows `USD 3.28M`.
