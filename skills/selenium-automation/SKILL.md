---
name: selenium-automation
description: Convert pasted manual QA scripts, Confluence test cases, test tables, or scenario descriptions into executable Selenium or Selenide UI tests using an existing automation codebase. Use when Codex needs to extract test steps, map them to existing page objects/actions, generate minimal test glue, run the test in the user's local Chrome browser, and return pass/fail evidence.
---

# Selenium Automation

## Operating Principle

Use AI as the test translator and orchestrator. Use Selenium/Selenide code as the executor. Prefer existing page objects, selectors, fixtures, and working tests over newly generated automation.

## Standard Workflow

1. Collect the manual test input from pasted text, Confluence content, screenshots transcribed by the user, or a local exported page.
2. Normalize the test into this YAML shape:

```yaml
test_id:
title:
source:
environment:
preconditions:
test_data:
steps:
  - id:
    action:
    target:
    value:
    expected:
```

3. Inspect the project before writing code.

Use `rg` to find:

- existing tests with similar business flow
- page object classes
- Selenide selectors and actions
- Selenium `By` locators
- shared login/session/test-data helpers
- custom waits and assertion helpers

4. Build a step mapping table:

```text
Manual step | Existing method/action | Confidence | Notes
```

For Twenty5/IPE work, do not build this table by hand. Use the locator index to map steps to proven page-object locators deterministically (see "Locator Index" below), then refine the result.

5. Generate only missing glue code. Do not duplicate page objects, locators, or utilities that already exist.
6. Run the narrowest test command available, such as one Maven/Gradle test class or method.
7. Return evidence: command, status, failing step, screenshots, logs, generated files, and unmapped steps.

## Local Chrome Rule

Prefer attaching Selenium to a consultant-controlled Chrome session on `127.0.0.1:9222`. This lets consultants authenticate manually and use their own browser session.

Read `references/local-chrome.md` when browser setup is needed.

## Mapping Rules

Read `references/action-catalog.md` when an action catalog exists. If it does not exist yet, create or update it from the current codebase.

For Twenty5/IPE regression work, also read `references/twentyfive-action-catalog.generated.md` and inspect `imported/twentyfive-regtest` before generating any Selenium/Selenide code. Treat the imported Twenty5 code as the working example library: reuse its page objects, Cucumber step definitions, element wrappers, waits, table helpers, and Maven module structure whenever the pasted manual/Confluence test matches that domain.

For Twenty5/IPE execution, prefer the repository wrapper instead of hand-writing Maven commands:

```bash
scripts/run-test-request.sh "run TC-Prof-Services-001: Verify Proposal Setup Layout"
```

The request runner resolves mapped Confluence test IDs, named suites, Master Data tests, default URLs, required local files, and Chrome debug startup from `config/test-registry.json` and `config/test-suites.json`. Use it first whenever the consultant asks in plain English. Only fall back to lower-level wrappers when the resolver cannot map the request or when you are debugging the runner itself.

For a direct low-level Cucumber run:

```bash
APP_URL="<target-url>" CUCUMBER_TAGS="@TC-001" scripts/run-twentyfive-test.sh
```

If a consultant asks to run a named suite such as "Manufacturing Proposal", use the suite runner instead of expanding tags by hand:

```bash
scripts/run-test-request.sh "Manufacturing Proposal"
```

The suite-to-tag mapping is in `TEST_SUITES.md`; the runner also supports `scripts/run-twentyfive-test.sh --list-suites`.

If `.skillautomation.env` exists from `scripts/macos-consultant-setup.sh`, omit `APP_URL`; the runner will reuse the selected setup URL. The wrapper already includes `-pl tests -am`, `RunTest`, `surefire.failIfNoSpecifiedTests=false`, and Selenium attach defaults.

When a consultant asks to run a test and does not provide a URL, tell them the run will use the default URL `https://approuter-twenty5ipe-dev.cfapps.us10.hana.ondemand.com/#quote`. If they provide a different URL in the request, pass it through `APP_URL="<url>"`. Do not ask for URL selection during setup. For Claude Desktop handoffs, if Chrome is not already running, ask whether to launch Selenium Chrome with the default URL; if the user provides an alternate URL, launch that URL instead.

Treat every user request to "run a test", "run this script", "execute this manual test", or similar as a Selenium/Twenty5 runner request when this workspace is active. Run `scripts/run-twentyfive-test.sh` and attach to the dedicated Chrome profile already started by `scripts/macos-consultant-setup.sh` or `scripts/start-debug-chrome.sh`. Do not ask the consultant to change Chrome site permissions unless the Selenium run reports a specific browser permission failure. If Chrome debug is not listening, use the wrapper's auto-start path or request permission to run `scripts/start-debug-chrome.sh` before showing setup instructions to the consultant. If Codex asks for approval to run Maven outside the sandbox, request/accept that path rather than switching to a different harness.

For Master Data Products & Services requests such as `TC-MD-PS-001`, do not create a fresh Playwright script or probe selectors first. Use the reusable Master Data runner:

```bash
scripts/run-master-data-test.sh --to TC5
```

For partial runs, pass the stopping point, for example `scripts/run-master-data-test.sh --to TC2`. The stable action layer is under `automation/master-data/`; add a missing action there only when the requested behavior is not already represented. See `MASTER_DATA_TEST_ARCHITECTURE.md`.

Do not spend time recreating optional external integrations removed from the shared runner. `AzureDeploymentChecker` and `TicketHandler` are intentionally local-safe no-op stubs unless the original private implementations are restored.

Classify each step as:

- `mapped`: implemented by existing automation code
- `generated-glue`: safe to call existing methods with test data
- `needs-selector`: no existing action exists, but a stable selector can be identified
- `blocked`: risky, ambiguous, destructive, or impossible without user input

Stop before executing destructive flows against production unless the user explicitly confirms the environment and data safety.

## Locator Index

For Twenty5/IPE, the page objects already encode a human-named locator catalog via `@ElementName` + `@FindBy` + `@WebPage`. Reuse it instead of re-deriving selectors per run. Read `references/locator-index.md` for the full pipeline. Quick path:

```bash
# (Re)generate the catalog when page objects change:
python3 skills/selenium-automation/scripts/build_locator_index.py

# Manual test -> steps.json -> transparent mapping report:
python3 skills/selenium-automation/scripts/normalize_manual_test.py input.txt --json --out test.steps.json
python3 skills/selenium-automation/scripts/map_steps.py test.steps.json --out test.mapping.json
```

The mapping report classifies each step as `mapped` (call the existing page-object field/method in `candidates`), `needs-selector` (the only steps that may need a new selector), or `note` (navigation/verification). Treat `mapped` candidates as the preferred implementation; only fall back to `needs-selector` handling when no candidate fits. Run `python3 skills/selenium-automation/scripts/validate_locators.py --quiet` to fail fast if an indexed locator has gone stale before running a test.

## Code Generation Rules

Prefer the framework already used by the repo.

For Selenide:

```java
$("#username").shouldBe(visible).setValue(username);
$("#submit").shouldBe(enabled).click();
$(".status").shouldHave(text(expectedStatus));
```

For Selenium:

```java
wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
driver.findElement(locator).click();
assertEquals(expectedStatus, actualStatus);
```

Avoid brittle XPath. Prefer existing page object methods, stable `data-testid` attributes, accessible labels, IDs, or selectors already used by the project.

## Runner Resources

- `scripts/index_actions.py`: scan Java Selenium/Selenide code and produce a starter action catalog
- `scripts/build_locator_index.py`: scan page objects → searchable `references/locator-index.generated.json`
- `scripts/normalize_manual_test.py`: convert simple pasted/manual test text into YAML scaffolding (add `--json` for `steps.json`)
- `scripts/map_steps.py`: map `steps.json` against the locator index into a transparency mapping report
- `scripts/validate_locators.py`: static, browser-free check that indexed locators still exist in source
- `scripts/run_test.sh`: run Maven or Gradle tests and collect evidence
- `references/locator-index.md`: locator index + step mapping pipeline reference
- `references/confluence-test-format.md`: extraction rules for common Confluence test layouts
- `references/examples.md`: examples of manual steps mapped to automation
- `assets/runner-template/`: minimal Selenide Maven template that attaches to local Chrome

## Output Format

Always end with:

```text
Test:
Status:
Command:
Evidence folder:
Generated/modified files:
Mapped steps:
Unmapped/blocked steps:
Next fixes:
```
