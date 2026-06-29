# Java to Playwright TypeScript Migration

This repo now has a Playwright TypeScript path beside the existing Java/Cucumber/Selenide suite. The Java suite is still the source of truth for unconverted tests; the first fully converted case is:

- `TC-Prof-Services-001: Verify Proposal Setup Layout`
- Source feature: `imported/twentyfive-regtest/tests/src/test/resources/features/testfolder/setup_professional_services_proposal.feature`
- Source Java page object: `imported/twentyfive-regtest/ipe/src/main/java/t5/ipe/cucumber/objects/pages/proposals/SetUpPage.java`
- Passing Java baseline: `reports/report_20260620_173321.md`

## Run the converted Playwright test

Start or reuse the dedicated Chrome debug profile, then run:

```bash
scripts/run-playwright-prof-services-001.sh
```

Or run it directly:

```bash
APP_URL="https://approuter-twenty5ipe-dev.cfapps.us10.hana.ondemand.com/#quote" \
CDP_ENDPOINT="http://127.0.0.1:9222" \
npm run test:pw:prof-services-001
```

The Playwright runner attaches to the same Chrome debug session used by the Java local runner. If Chrome is on a login page, complete login in that browser window and the test continues automatically.

The converted `TC-Prof-Services-001` uses fast force-clicks by default, but still follows the real UI path for creating the proposal because `#quote:new:type=PS_consulting` opens plain `Consulting Proposal`, not `Regression Test Only - Consulting`.

There is an experimental route shortcut:

```text
#quote:new:type=PS_consulting
```

Enable it only when you explicitly want to compare route behavior:

```bash
PLAYWRIGHT_FAST_SETUP_ROUTE=true scripts/run-playwright-prof-services-001.sh
```

Timing logs are enabled by default. Disable them with `PLAYWRIGHT_TIMING=false`.

## Manufacturing Proposal fast suite

The Manufacturing Proposal suite has a Playwright fast runner:

```bash
scripts/run-playwright-manufacturing-suite.sh
```

It maps the Java suite IDs `TCM-001` through `TCM-012`. To keep the run fast, setup validation uses a fresh unsaved proposal, while later cost/amortization coverage opens the existing quote/BOE fixtures directly:

- `TC-Manufacturing-Proposal-003-quote.txt`
- `TC-Manufacturing-Proposal-004-boe.txt`
- `TC-Manufacturing-Proposal-011-boe.txt`

This is intentionally a fast Playwright suite over the Manufacturing proposal flow and fixtures. The older Java scenarios include deep grid mutations and formula edits; those should be migrated scenario-by-scenario when exact mutation parity is required.

## Migration pattern

The first conversion keeps the Java semantics but removes repeated fixed page-load waits:

- Java `RunTest.java` and Cucumber tags -> `playwright.config.ts` and `*.spec.ts`
- Java `SetUpPage.java` locators -> `tests/playwright/support/proposal-setup-page.ts`
- Java local Chrome/Selenium profile -> Playwright `chromium.connectOverCDP`
- Java `page with name ... is opened` repeated checks -> targeted Playwright readiness checks
- Java field verification step -> typed `SetupFieldExpectation` data

## Whole-suite conversion plan

The full Java suite is large, so convert it in vertical slices:

1. Convert one feature scenario into a Playwright spec.
2. Move shared page behavior into `tests/playwright/support`.
3. Keep the original Java feature/report as the acceptance baseline.
4. Run the Java test and Playwright test against the same app profile until both agree.
5. Repeat for the next scenario, promoting duplicated helpers only after two or more specs need them.

This avoids a risky mechanical rewrite of every page object before there is a working Playwright equivalent.
