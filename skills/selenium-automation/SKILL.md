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
APP_URL="<target-url>" CUCUMBER_TAGS="@TC-001" scripts/run-twentyfive-test.sh
```

If `.skillautomation.env` exists from `scripts/macos-consultant-setup.sh`, omit `APP_URL`; the runner will reuse the selected setup URL. The wrapper already includes `-pl tests -am`, `RunTest`, `surefire.failIfNoSpecifiedTests=false`, and Selenium attach defaults.

Treat every user request to "run a test", "run this script", "execute this manual test", or similar as a Selenium/Twenty5 runner request when this workspace is active. Run `scripts/run-twentyfive-test.sh` and attach to the dedicated Chrome profile already started by `scripts/macos-consultant-setup.sh` or `scripts/start-debug-chrome.sh`. Do not ask the consultant to change Chrome site permissions unless the Selenium run reports a specific browser permission failure. In Codex, if Chrome debug is not listening, tell the consultant to start the dedicated browser from Terminal with `scripts/start-debug-chrome.sh` rather than launching a GUI browser from inside the agent. In Claude Code or normal Terminal runs, the wrapper may auto-launch Chrome. If Codex asks for approval to run Maven outside the sandbox, request/accept that path rather than switching to a different harness.

Do not spend time recreating optional external integrations removed from the shared runner. `AzureDeploymentChecker` and `TicketHandler` are intentionally local-safe no-op stubs unless the original private implementations are restored.

Classify each step as:

- `mapped`: implemented by existing automation code
- `generated-glue`: safe to call existing methods with test data
- `needs-selector`: no existing action exists, but a stable selector can be identified
- `blocked`: risky, ambiguous, destructive, or impossible without user input

Stop before executing destructive flows against production unless the user explicitly confirms the environment and data safety.

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
- `scripts/normalize_manual_test.py`: convert simple pasted/manual test text into YAML scaffolding
- `scripts/run_test.sh`: run Maven or Gradle tests and collect evidence
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
