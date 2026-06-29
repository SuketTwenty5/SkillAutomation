# Create Capital Project From Prior Project And Update Labor FTE

## Source

- Recording folder: `playwrightRecording/20260629_175331`
- Raw recording: `recording.spec.js`
- Normalized steps: `recording.steps.json`
- Maintained Playwright spec: `tests/playwright/create-capital-project-from-prior-project-and-update-labor-fte.spec.ts`
- Recorded time: `2026-06-29 17:53:31 IST`

## Test Data

| Field | Value |
| --- | --- |
| Project Name | `regression test capital <timestamp>` |
| Probable Project Start | `6/29/2029` |
| Project End | `6/29/2037` |
| Leading Site | `IT - Italy (Modena)` |
| Client/Customer (Sell-to) search | `USD` |
| Client/Customer (Sell-to) | `Regression Test - Customer USD` |
| Prior Project search | `(AS) Copy` |
| Prior Project | `(AS) Copy from Template v2` |
| Labor Pool | `Supplier Engineer` |
| FTE | `2` |

## Test Steps

1. Open the Proposals page.
2. Start a new project.
3. Enter `Project Name *`.
4. Enter `Probable Project Start *`.
5. Enter `Project End *`.
6. Select `Leading Site *`.
7. Select `Client/Customer (Sell-to) *`.
8. Choose `Select a Prior Project`.
9. Search for and select `(AS) Copy from Template v2`.
10. Click `Copy`.
11. Open the `Estimates` tab.
12. Open the estimate.
13. Open the `Labor` tab.
14. Add or activate a blank labor row.
15. Select `Supplier Engineer` as the Labor Pool.
16. Enter `2` in the FTE editor.

## Automation Notes

- The raw recording used generated ExtJS ids for several controls. The maintained spec replaces the prior-project search with the visible `iBESearchComboBox` search component and uses the shared `openEstimateFromActiveTab(...)` helper for the estimate popup.
- The Labor empty-grid add action uses the ExtJS link `//*[@data-grigaddlink='true']`, because a normal visible click on the empty-grid text may not fire the row creation behavior reliably.
- The Labor Pool cell is resolved from row-scoped grid metadata where possible and logs the resolved `data-columnid` while running.
- The estimate popup helper refreshes once if a blank white page appears before continuing.
- The Project Name is timestamped at runtime to avoid collisions while preserving the recorded base value.

## Latest Execution Evidence

- Status: passed
- Started: `2026-06-29T15:05:06.611Z`
- Created object URL: `https://approuter-twenty5ipe-dev.cfapps.us10.hana.ondemand.com/#boe:fa5c8fc1-73cb-11f1-8576-e308d8910780`
- Runtime object: `regression test capital 2026-06-29 15:05`
- Playwright JSON result: `../../test-results/playwright/results.json`

### Step Screenshots

| # | Step | Status | Screenshot |
| ---: | --- | --- | --- |
| 1 | Open Proposals and start a new capital project | passed | [01-open-proposals-and-start-a-new-capital-project.png](evidence/latest/01-open-proposals-and-start-a-new-capital-project.png) |
| 2 | Fill recorded project setup values | passed | [02-fill-recorded-project-setup-values.png](evidence/latest/02-fill-recorded-project-setup-values.png) |
| 3 | Select prior project and copy project data | passed | [03-select-prior-project-and-copy-project-data.png](evidence/latest/03-select-prior-project-and-copy-project-data.png) |
| 4 | Open estimate and update Labor FTE | passed | [04-open-estimate-and-update-labor-fte.png](evidence/latest/04-open-estimate-and-update-labor-fte.png) |
