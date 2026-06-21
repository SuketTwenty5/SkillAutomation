# Master Data Test Architecture

This architecture is intended to reduce live AI debugging loops. Consultants should get fast results by reusing mapped tests, stable actions, and existing locators before asking the AI to create new browser code.

## Flow

```text
Consultant request
  -> identify mapped test or suite
  -> run existing wrapper
  -> use reusable action library
  -> return pass/fail evidence
  -> generate new code only when an action is missing
```

## Entry Point

Use the wrapper for Master Data Products & Services:

```bash
scripts/run-master-data-test.sh --to TC5
```

Useful variants:

```bash
scripts/run-master-data-test.sh --to TC2
scripts/run-master-data-test.sh --only TC4
MASTER_DATA_APP_URL="https://app-twenty5ipe-lm-dev.cfapps.us10.hana.ondemand.com/" scripts/run-master-data-test.sh --to TC5
```

The wrapper:

- installs Node dependencies when needed without downloading Playwright browser binaries
- attaches to the consultant's Chrome debug session on port `9222`
- starts the debug browser when allowed by `AUTO_START_CHROME=true`
- keeps screenshots and `test-report.json` local
- delegates to `test_automation_cdp.js`

## Layers

| Layer | File | Responsibility |
| --- | --- | --- |
| Shell wrapper | `scripts/run-master-data-test.sh` | Consultant-friendly command, dependency check, Chrome CDP readiness |
| Compatibility entrypoint | `test_automation_cdp.js` | Keeps old node command working |
| Scenario runner | `automation/master-data/tc-md-ps-001.js` | TC1-TC5 orchestration and stop-at/only behavior |
| Action catalog | `automation/master-data/actions.js` | Stable browser actions and locators |
| Reporting | `automation/master-data/reporter.js` | JSON report and local screenshots |
| Config | `automation/master-data/config.js` | CLI/env parsing |

## Reusable Actions

The action catalog captures learned UI behavior so future runs do not spend tokens rediscovering it:

- reset the SPA to the base URL before a run
- click Master Data
- open Products & Services
- hover New and wait 5 seconds for the menu
- click outside and retry hover once if the menu does not appear
- hover Create from Type and wait for the submenu
- click the visible `Purchased Part` menu item while excluding grid/table matches
- verify the seven Purchased Part sub-tabs

## Agent Rule

When a consultant asks for this test, the agent should run the wrapper:

```bash
scripts/run-master-data-test.sh --to TC5
```

The agent should not create probe scripts or write new selectors unless `automation/master-data/actions.js` is missing the required behavior.

For RTA/Cucumber tests, keep using:

```bash
scripts/run-twentyfive-test.sh @TC-001
scripts/run-twentyfive-test.sh --suite "Manufacturing Proposal"
```
