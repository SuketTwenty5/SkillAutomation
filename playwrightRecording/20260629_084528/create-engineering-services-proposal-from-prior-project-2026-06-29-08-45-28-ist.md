# Test Case: Create Engineering/Services Proposal From Prior Project

## Source Recording

- Recording folder: `playwrightRecording/20260629_084528`
- Source file: `recording.spec.js`
- Recorded at: `2026-06-29 08:45 IST`
- Application: Twenty5 iPE Quote dev environment
- Start URL: `https://approuter-twenty5ipe-dev.cfapps.us10.hana.ondemand.com/#quote`

## Objective

Verify that a user can create a new Engineering/Services proposal, populate required setup fields, copy data from a prior project, update the cost structure, open the generated proposal, review key proposal tabs, and save the proposal.

## Preconditions

- User has access to the Twenty5 iPE Quote dev environment.
- User is able to sign in to the SAP authentication page.
- The prior project `PRK test 03/09 2017` is available for copy.
- The customer `Regression Test - Customer USD` is available.
- The required business unit, department, and cost structure options are available.

## Test Data

| Field | Value |
| --- | --- |
| Project Name | `Test eng proposal test` |
| Proposal Type / Project Type | `Engineering/Services Proposal` |
| Estimated Project Start | `6/29/2030` |
| Project End | `6/28/2035` |
| Business Unit / Region | `Poland` |
| Department / Location | `IT - Italy (Modena)` |
| Client / Customer | `Regression Test - Customer USD` |
| Prior Project Search | `TEST Reschedule Proposal 2026-06-29 03:58` |
| Cost Structure Search | `Eng` |

## Test Steps

1. Navigate to the Twenty5 iPE Quote dev application.
2. Sign in through the SAP authentication page.
3. From the quote list page, click `New`.
4. Enter `Test eng proposal test` in the `Project Name` field.
5. Open the proposal type picker and select `Engineering/Services Proposal`.
6. Set `Estimated Project Start` to `6/29/2030`.
7. Set `Project End` to `6/28/2035`.
8. Select `Poland` in the business unit or region field.
9. Select `IT - Italy (Modena)` in the department or location field.
10. Open the `Client/Customer (Sell-to)` picker.
11. Search for `usd`.
12. Select `Regression Test - Customer USD`.
13. Select the `Select a prior project` option.
14. Search for `TEST Reschedule Proposal 2026-06-29 03:58` in the visible prior-project search component `//*[contains(@data-componentid,'iBESearchComboBox') and @aria-hidden="false"]`.
15. Select the matching `TEST Reschedule Proposal 2026-06-29 03:58` prior project result.
16. Click `Copy` to copy proposal data from the prior project.
17. Open the `Cost Structure` tab.
18. Open the cost structure selection control.
19. Search for `Eng`.
20. Select the matching engineering cost structure option.
21. Confirm the cost structure selection.
22. Click the `Open` link to open the created proposal in a new browser window.
23. In the opened proposal window, review the initial grid area.
24. Click through the visible proposal grid columns or cells to verify the copied proposal data is present.
25. Open the `Risk & Contingency` tab.
26. Add or inspect the risk grid row shown by the application.
27. Open the `Work Packages` tab.
28. Open the `Workflow` tab.
29. Click the save button.
30. Close the proposal window.
31. Close the original quote window.

## Expected Results

- The user can sign in successfully.
- The `New` proposal workflow opens.
- Required setup fields accept the recorded values.
- The prior project search returns `TEST Reschedule Proposal 2026-06-29 03:58`.
- Proposal data is copied from the selected prior project.
- The `Cost Structure` tab is available.
- The engineering cost structure search returns a selectable result for `Eng`.
- The created proposal opens in a new browser window.
- The proposal window shows copied proposal data instead of an unrecoverable error.
- `Risk & Contingency`, `Work Packages`, and `Workflow` tabs are accessible.
- Saving from the proposal window completes without a blocking error.

## Automation Notes

- The raw recording contains generated ExtJS IDs, such as `#iBEBusObjType-6183-trigger-picker`, `#iBEDateTime-8072-trigger-picker`, and `#button-1109`. These should be replaced with stable locators before this becomes a maintained Playwright test.
- Replace generated ExtJS IDs with role, label, text, placeholder, or scoped field locators.
- Use existing workspace helpers before adding new selectors.
- For picker triggers, scope from the visible field label to the trigger in the same field container.
- For prior-project copy searches, enter step 14 text in the visible `//*[contains(@data-componentid,'iBESearchComboBox') and @aria-hidden="false"]` search component, not in a nearby label-scoped field.
- Run locator scanning when stable locators are unclear.
- Wrap unavoidable ExtJS fallbacks in named helpers with comments.
- The recorded script includes dialog dismissal handlers. A converted automation should capture the dialog message and assert whether it is expected.
- The raw codegen script starts at an SAP OAuth URL. A maintained test should navigate to the application URL and reuse the project authentication helper or storage state.
- The generated script currently uses `page` before explicitly creating it. A maintained test should create a page from the browser context or use the repository's existing Playwright test fixtures.
- The recorded project name is static. A maintained test should append a timestamp or other unique suffix if the application requires unique proposal names.

## Suggested Assertions

- Assert the quote list or application shell is visible after login.
- Assert the new proposal form is visible after clicking `New`.
- Assert each required setup field contains the selected or entered value.
- Assert the prior project value is selected before clicking `Copy`.
- Assert the `Cost Structure` tab loads successfully.
- Assert the selected engineering cost structure is visible after confirmation.
- Assert the proposal popup opens successfully.
- Assert the proposal popup contains expected copied data.
- Assert the `Risk & Contingency`, `Work Packages`, and `Workflow` tabs become active when clicked.
- Assert save completes without an unexpected blocking dialog.

## Latest Execution Evidence

- Status: `failed`
- Command: `scripts/run-playwright-test.sh tests/playwright/create-engineering-services-proposal-from-prior-project.spec.ts --headed --retries=0`
- Blocking step: Step 15, prior project result `TEST Reschedule Proposal 2026-06-29 03:58` was not visible after searching in the visible `iBESearchComboBox` component.
- Screenshot: [test-failed-1.png](../../test-results/create-engineering-service-78d52-ct-and-verify-estimate-tabs/test-failed-1.png)
- Trace: [trace.zip](../../test-results/create-engineering-service-78d52-ct-and-verify-estimate-tabs/trace.zip)
- Error context: [error-context.md](../../test-results/create-engineering-service-78d52-ct-and-verify-estimate-tabs/error-context.md)
