# Create Capital Project From Prior Project And Update Labor FTE

## Summary

- Status: Passed
- Start time: `2026-06-29T12:59:28.747Z`
- Duration: `164925 ms`
- Command: `scripts/run-playwright-test.sh tests/playwright/create-capital-project-from-prior-project-and-update-labor-fte.spec.ts --headed --retries=0`
- Spec: `tests/playwright/create-capital-project-from-prior-project-and-update-labor-fte.spec.ts`
- Recording README: `playwrightRecording/20260629_175331/create-capital-project-from-prior-project-and-update-labor-fte-2026-06-29-17-53-31-ist.md`
- JSON result: `test-results/playwright/results.json`
- Last-run status: `test-results/.last-run.json`

## Created Object

- Estimate URL: `https://approuter-twenty5ipe-dev.cfapps.us10.hana.ondemand.com/#boe:7bab14ac-73ba-11f1-8c4d-97fb428503b5`
- Runtime project title: `regression test capital 2026-06-29 13:03`

## Step Timings

| Step | Duration |
| --- | ---: |
| Open Proposals and start a new capital project | `21729 ms` |
| Fill recorded project setup values | `8749 ms` |
| Select prior project and copy project data | `31981 ms` |
| Open estimate and update Labor FTE | `84683 ms` |

## Evidence

- Screenshot: `playwrightRecording/20260629_175331/evidence/labor-fte-page-1.png`
- Playwright stored this screenshot as an embedded attachment in `test-results/playwright/results.json`; it has also been extracted to the PNG path above.
- Trace: not generated for the passing run by the current Playwright reporter/config.

## Selector Evidence

- Labor add link: `//*[@data-grigaddlink='true']`
- Labor add link runtime grid id: `iBEResourcesGrid-1906`
- Labor Pool cell `data-columnid`: `resourceGridLaborPoolColumn`
- White-screen recovery: configured in `openEstimateFromActiveTab(...)`; this run did not require a refresh.
