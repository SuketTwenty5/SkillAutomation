# LocalTestAutomation Playwright Reference

Use this when the active task mentions `/Users/suketsuman/Documents/LocalTestAutomation` or when `SkillAutomation` needs the simpler browser-state workflow from that repo.

## Shape

- Playwright TypeScript tests live under `tests/`.
- Reusable helpers live under `tests/support/`.
- Focused runtime locators live in `tests/support/ipe-locators.ts`.
- Evidence is written under `docs/evidence/`, `test-results/`, and `playwright-report/`.
- PDFs are staged under `fixtures/pdfs/`.

## Auth

Use `auth.json` as the first-choice browser state file.

Run once when the state is missing or expired:

```bash
npm run auth
```

Equivalent raw command:

```bash
npx playwright open --save-storage=auth.json https://approuter-twenty5ipe-dev.cfapps.us10.hana.ondemand.com/#quote
```

Local credential fallback may exist through `IPE_USERNAME`, `IPE_PASSWORD`, or `.env.local`; never write those values into source or reports.

## Common Commands

```bash
npm run pdf -- /absolute/path/to/file.pdf
npm run pdf -- /absolute/path/to/file.pdf --no-report
npm run test:pdf-proposal
npx playwright test tests/create-regression-proposal-from-pdf.spec.ts --headed --retries=0
npx playwright test tests/pdf-proposal-creation.spec.ts --headed --project=chromium --reporter=list
```

## Helpers To Reuse

- `openQuoteList(page)`: opens the quote app with auth fallback and waits for the New button.
- `clickXpath(page, xpath, description)`: robust ExtJS click via DOM events.
- `setInputXpath(page, xpath, value, description)`: focus, clear, type, verify value.
- `selectInputXpath(page, xpath, value, description)`: type into an ExtJS combo and click the visible option.
- `waitForSetupForm(page)`: waits for the proposal setup form shell.
- `waitForText(page, text, description)`: targeted text polling.
- `readFieldValues(page)`: collect form values for verification.

## Porting Rule

When using this pattern from `SkillAutomation`, port the behavior, not another skill file. Add or update the current workspace's Playwright helpers/specs and keep this skill as the only automation skill definition.
