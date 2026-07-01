---
name: skill-playwright-automation
description: DEFAULT WORKSPACE SKILL. Execute any Twenty5/iPE Quote UI automation, testing, test creation, locator scanning, Playwright test execution, test case mapping, evidence capture, or automation script work. Use for the SkillAutomation repo checkout. Covers PDFs, Scribe guides, Confluence/manual test cases, plain-English run requests, Playwright specs, page-object generation, locator catalogs, and browser state management.
---

# Twenty5 Playwright Automation

Use this as the single canonical skill/runbook for Twenty5/iPE Quote automation. It lives in the `SkillAutomation` repo and is the single source of truth for everyone who clones it.

The repo can be cloned to any location, so refer to your checkout as the **repo root** (the folder that contains this `skills/` directory) rather than a fixed absolute path. Do not assume a `/Users/<name>/...` path — resolve everything relative to the repo root.

Read this `SKILL.md` first, then inspect the active workspace.

## Codex And Claude Usage

Codex loads this file through the normal skill mechanism. Claude Code auto-loads the repo-root `CLAUDE.md` at session start — this `SKILL.md` is not auto-loaded — so that thin `CLAUDE.md` pointer is how Claude discovers this runbook. If Claude isn't pointed here automatically, point it at the runbook relative to the repo root:

```text
skills/skill-playwright-automation/SKILL.md
```

Keep `SKILL.md` as the single source of truth for the full workflow. `CLAUDE.md` must stay a short pointer plus critical guardrails that link back here — never a second copy or a drifting alternate. When Claude needs the full workflow, it reads this `SKILL.md` plus the referenced files a task needs.

## Operating Principle

Use Playwright as the browser executor. Reuse local browser state, focused action helpers, and locator catalogs.

Prefer this order:

1. Cached source artifacts for the exact PDF/Scribe/manual URL or title.
2. Existing Playwright specs and support helpers in the active workspace.
3. Focused runtime locators in `tests/**/support/*locators*`.
4. Fresh page-scanned locators saved back into this skill for future reference.
5. Historical locator catalog in `references/ipe-auto-tests-locators.json`.
6. New Playwright glue using stable live locators.

## Running Tests

Use the repo's Playwright runners and tests:

```bash
npm run test:pw:manufacturing
npm run test:pw:prof-services-001
scripts/run-playwright-test.sh tests/playwright/<spec>.spec.ts --headed --retries=0
```

## Auth And Chrome

Use a managed headed Playwright Chrome launch. Keep the launch simple: one approved command should both open Chrome and run the target spec. Do not start Chrome separately and do not attach to a debug port.

- In `SkillAutomation`, run the spec directly with `scripts/run-playwright-test.sh tests/playwright/<spec>.spec.ts --headed --retries=0`.
- In Codex Desktop with managed filesystem sandboxing, request escalation for headed Playwright before the first launch instead of trying a sandboxed headed run first. Chrome for Testing can initialize macOS Crashpad under `~/Library/Application Support/Google/Chrome for Testing` before Playwright flags take effect, which causes a predictable sandbox-only launch failure. Ask once for the `scripts/run-playwright-test.sh` prefix; do not ask separately to launch Chrome.
- The default helper path launches a persistent headed Playwright Chrome profile inside the active workspace. It uses `PLAYWRIGHT_PROFILE_DIR`, then `CHROME_PROFILE`, then `.skillautomation/playwright-chrome`.
- Keep Chrome runtime state inside the workspace when possible: set the browser process `HOME` to `.skillautomation/browser-home`, set crash dumps to `.skillautomation/crash-dumps`, and launch with Crashpad/crash-reporter disabled. This keeps persistent profile and crash artifacts out of user home directories, but it is not a substitute for escalation when the macOS sandbox blocks headed Chrome startup.
- During the same chat/thread and future runs, preserve that managed browser profile's cache, cookies, local storage, and session storage for all retries and follow-up runs. Do not clear cache unless the user asks or the app is demonstrably poisoned by stale state.
- The workspace helper should save reusable signed-in state to `.skillautomation/auth-state.json` after a successful app-shell load. On the next run, restore fresh state before navigating: add cookies, inject localStorage, and inject sessionStorage for the app origin. Treat `PLAYWRIGHT_AUTH_STATE_MAX_AGE_MS` as the freshness window; the default is 8 hours. If the restored state is stale, expired, or rejected by the app, then use automatic login and overwrite the state file after login succeeds.
- Never print or commit `.skillautomation/auth-state.json`; it can contain sensitive cookies/session tokens. `.skillautomation/` must remain ignored by git.
- Immediately after Chrome launch/navigation, check whether the app is already signed in. If not, automatically log in with the test user from environment variables or a git-ignored `.env.local` at the repo root (override the path with `IPE_ENV_FILE`); do not wait for the consultant to complete ordinary username/password login.
- Load authentication details from environment variables first, then a git-ignored `.env.local` at the repo root (override with `IPE_ENV_FILE`). Parse only the required variables (`IPE_USERNAME`, `IPE_PASSWORD`) and never print, store in reports, commit, or echo their values.
- If the login/session expires during the same chat, re-login with the `.env.local` credentials and continue using the same managed browser profile so cache/local storage are preserved after re-authentication.
- When using Playwright browser state, the primary state file is `auth.json`.
- Capture state with `npm run auth` when that script exists, using the `.env.local` credentials if the auth script supports them, or:

```bash
npx playwright open --save-storage=auth.json https://approuter-twenty5ipe-dev.cfapps.us10.hana.ondemand.com/#quote
```

- Keep tests headed for consultant-visible execution unless the user asks for headless.
- If `auth.json` is missing or expired, use automatic login from environment variables or a git-ignored `.env.local` at the repo root (override with `IPE_ENV_FILE`).
- Ask the consultant only if the app requires MFA/SSO interaction, the credentials are missing, or automatic login fails. Report that condition as `blocked` with the exact page/error observed.
- Never store usernames/passwords in source, reports, skill files, or committed config.

## Speed And Interaction Rules

Keep interactions fast and reliable. Prefer targeted polling and visible-state checks over fixed long sleeps.

- Do not wait for full page idle before clicking `New`. Navigate with `waitUntil: 'commit'`, then poll only for the quote list `New` button and click it as soon as it is visible.
- Initial app bootstrap may wait up to 120 seconds because the Twenty5 loader can pause near 95%. Keep all post-bootstrap UI waits targeted and short.
- After selecting a Proposal Type, wait for the rebuilt form before filling the title or other fields.
- Text fields: wait a few hundred ms for field readiness, focus the actual input, clear with the keyboard, type with the keyboard, then verify the input value.
- Dropdown/combo fields: focus the actual input, clear with the keyboard, type the search text, wait ~250 ms, poll visible options every ~100 ms, click as soon as the matching option appears, then verify the selected value.
- Avoid sequential long fallback waits such as `15s + 15s`. Prefer targeted polling over fixed sleeps.

## PDF Or Manual Test Workflow

Treat the PDF/manual/Scribe source as the source of truth. Existing suites are implementation references, not replacement run targets, unless they match the same source steps and test data exactly.

### Fast Source Lookup

Before opening a URL in a browser, using web search, or re-extracting a PDF/Scribe/manual source, check whether the active workspace already has a normalized artifact for the same source. This avoids spending time and tokens on source scanning that has already been done.

Run this from the active workspace root when the user gives a PDF path, Scribe URL, title, or natural-language source name:

```bash
node skills/skill-playwright-automation/scripts/find-source-artifact.mjs "<source URL, path, or title>"
```

If it returns a match with `confidence` of `exact`, `url`, `path`, or `title`, read only the listed artifact files and continue from mapping/execution. Do not fetch the source URL again unless the user asks for a fresh scan or the artifact is clearly stale.

Known reusable artifacts should be indexed in `skills/skill-playwright-automation/references/source-artifacts.json`. Also check common workspace locations directly when the index has no match:

- `tests/playwright/*.steps.json`
- `reports/*.normalized.yaml`
- `reports/*.steps.json`
- `reports/*.mapping.json`
- `reports/*.txt`

For Scribe URLs specifically, prefer an existing `*.steps.json` artifact over browser/web extraction. Scribe pages can be slow or script-rendered, and the concise local artifact is usually the lowest-token source of truth.

1. Extract the PDF/manual/Scribe steps into a normalized YAML or JSON artifact.
2. Preserve every variable exactly as written in the source: titles, dates, proposal types, companies, departments, clients, templates, phase names, estimate views, and row values. Do not substitute suite fixture values, historical defaults, or "similar" values from another test.
3. Preserve the source step order and make the generated Playwright test read almost the same as the PDF. Step names should mirror the PDF wording closely enough that a consultant can compare them line by line.
4. Inspect active Playwright tests and helpers before writing code. Use similar tests only to borrow helper calls, waits, locator patterns, and page-object structure.
5. Scan the live page(s) for locators before adding new selectors.
6. Map each manual step to an existing action, scanned locator, or historical locator.
7. Generate the smallest PDF-specific Playwright spec/helper change needed.
8. Run the narrowest headed Playwright command for the PDF-specific spec. Do not run a nearby suite, such as Manufacturing Proposal, just because it contains useful reference code.
9. Save screenshots, traces, JSON results, and generated reports under the repo's evidence/report folders.

If the app requires a unique value but the PDF gives a fixed value, keep the PDF value in `test_data` and create an explicitly named derived runtime value such as `runtime_proposal_title`. Report the variance in `Mapped steps` or `Unmapped/blocked steps`; do not silently replace the PDF variable.

For the current "How To Create A New Project Proposal In Twentyfive" PDF, preserve values such as `PRK regression test 2`, `Regression Test Only - Manufacturing`, `Engineering/Services Proposal`, `June 30, 2026`, `September 27, 2028`, `Regression Test`, `US - New York`, `Products`, `82nd Airborne Division`, `(AS) Regression - Copy from template`, `Test Phase 1`, `REG_CONSULTANT2`, `REG_CONSULTANT3`, `REG_CONSULTANT1`, `PRK`, and `Pro Serv - Planning View (shared & preferred)`.

For the current "How To Create A New Project Proposal In Twentyfive" PDF, do not collapse steps 5-8 into a generic setup-fill helper:

- Step 5 `Proposal Type` / `Project Type`: after selecting `Regression Test Only - Manufacturing`, scan the live setup page for a separate `Project Type` field. If it is visible, select and verify `Engineering/Services Proposal`. If it is not visible, record a runtime variance with evidence; do not silently mark the value as mapped.
- Step 6 `SAP Project dropdown`: explicitly open the SAP Project dropdown and verify visible SAP Project options are displayed. Use visible `role=option` locators scoped away from hidden ExtJS listboxes; do not treat a hidden shared listbox as proof of failure.
- Step 7 `Estimated Project Start` and step 8 `Project End`: set and verify these dates in their own `test.step` blocks before filling organization/client fields. This prevents project-type UI changes from hiding date-entry regressions.

Classify every step:

- `mapped`: covered by existing Playwright action/locator.
- `scanned-locator`: implemented using a locator captured from the live page.
- `generated-glue`: small Playwright wrapper around existing behavior.
- `blocked`: requires login, missing data, unsafe production mutation, or unclear intent.

When reporting coverage, classify against the PDF steps, not against a similar existing suite. A step is `mapped` only if the automation performs that same PDF action with the same PDF variable.

## Locator Scanning Rule

Scan each relevant page/view when mapping a new flow or repairing a brittle selector. Store the scan in this skill so future Codex or Claude runs can reuse it.

Use the scanner from the active workspace root:

```bash
node skills/skill-playwright-automation/scripts/scan-page-locators.mjs \
  --url "https://approuter-twenty5ipe-dev.cfapps.us10.hana.ondemand.com/#quote" \
  --name "quote-list" \
  --storage-state auth.json \
  --out-dir skills/skill-playwright-automation/references/page-locators \
  --headed
```

Locator preference:

1. Role/name, label, placeholder, visible button/tab text.
2. Stable attributes: `data-testid`, `data-qtip`, `aria-label`, `name`, meaningful `id`.
3. ExtJS patterns scoped to visible panels/dialogs: `role`, `aria-hidden='false'`, tabpanel/dialog ancestors.
4. Historical XPath from `references/ipe-auto-tests-locators.json`.
5. Positional XPath only when no stable alternative exists; mark it as fallback.

When a scan discovers new useful locators, update or create a small focused locator module such as `tests/support/ipe-locators.ts`. Do not paste a giant scan directly into test code.

## Date Field Rule

Whenever a workflow sets `Estimated Project Start`, `Planned Start`, or any equivalent project start date field, treat the date pair as a coupled UI control:

- Prefer the shared workspace helper `ProposalSetupPage.setEstimatedProjectStart(...)` or another helper that explicitly implements this rule.
- After setting the start date, wait for ExtJS/app auto-calculation and field validation to settle before clicking another tab or button.
- Read `Project End`. If it is blank, marked invalid, or earlier than the start date, set it to a valid fallback date, normally one year minus one day after the start date unless the source test case specifies a different end date.
- Press `Tab`/blur or use the ExtJS field setter so both date fields are committed before continuing.
- Verify both start and end dates after entry. Do not click `Sets/Phases`, `Cost Structure`, `Next`, `Save`, or `Create` while `Project End` is invalid.
- If the source document includes an explicit Project End value, set and verify that value after the start-date helper has settled.

## Bid Description Timestamp Rule

Whenever automation enters text in the bid description/title field located by:

```xpath
//*[@placeholder="Enter a brief description of the bid (something you can remember to find it later by)"]
```

the final value typed into the application must end with a human-readable date and time stamp, such as `Jun 27, 2026 6:42 PM IST`. In `SkillAutomation`, use `ProposalSetupPage.setProposalTitle(...)` or another `ProposalSetupPage` setup-fill helper so the shared timestamp suffix is applied automatically.

- Preserve the source title in test data, then enter the timestamped runtime value in the UI.
- Do not append a second timestamp if the value already ends with a date/time suffix.
- Assertions against the description/title field should compare against the timestamped runtime value.
- When converting source steps into a spec, replace direct `.fill(...)` calls for this placeholder with the shared helper or an equivalent local wrapper that applies the same suffix.

## Reference Files

Read only what is needed:

- `references/ipe-auto-tests-locators.json`: historical locator catalog.
- `references/ipe-auto-tests-locators.md`: readable locator table for quick search.
- `references/page-locators/`: live page scans produced by `scan-page-locators.mjs`.
- `references/confluence-test-format.md`: manual/Confluence extraction patterns.
- `references/examples.md`: examples of step mapping.

## Code Rules

- Write Playwright TypeScript or JavaScript, matching the active repo.
- Reuse existing helper APIs (`openQuoteList`, `selectInputXpath`, `setInputXpath`, `ProposalSetupPage`, `clickTab`, etc.) before adding helpers.
- Keep PDF test data in one visible fixture object or imported normalized artifact, using names that match the PDF labels. Avoid hiding PDF values in helper defaults.
- Keep selectors close to locator modules, not scattered through specs.
- Use targeted polling and visible-state checks; avoid fixed long sleeps.
- Verify field values after filling before taking evidence screenshots.
- Keep data unique with timestamps only when required for successful execution, and keep the original PDF value alongside the runtime-derived value.
- When entering dropdown text that includes a parenthesized code, preserve the source value in test data but try the visible label before the code as a fallback. For example, if the source says `19th Aug Customer (CUST091)` and no exact option appears, retry entry/search as `19th Aug Customer` and report that runtime variance.
- Stop before destructive production-like operations unless the user clearly confirms the environment and data safety.

## Output Format

Always end automation runs with a polished, human-readable chat report. The report must be visible directly in the final chat reply; do not make the user open JSON, traces, or screenshots just to understand what happened. Use the raw artifacts as evidence sources, then summarize them in consultant-friendly language.

The final chat reply should include:

- A short status headline: test name, pass/fail/blocked state, environment, run time, and created or touched business object URL/ID when available.
- A concise executive summary: what was executed, what succeeded, what failed or needed manual help, and whether the source PDF/manual steps were fully covered.
- A step-by-step coverage table or scannable list with source step number/name, automation action, data used, classification (`mapped`, `scanned-locator`, `generated-glue`, or `blocked`), and result.
- Test data and runtime variances: show important source values, derived unique values, dates, proposal types, companies, departments, clients, templates, users, and any intentional substitutions.
- Evidence summary: command, result artifact paths, screenshot/trace/report paths when present, key console lines, saved URL, and relevant timestamps/durations.
- Failure analysis when anything fails: failing step, observed error, likely cause, artifact path, retry status, and the next concrete fix.
- Next fixes or follow-ups: keep this actionable and specific; write `None required` when the run is clean.

Keep the report visually pleasant in Markdown: use short headings, compact tables where helpful, status markers as words such as `Passed`, `Failed`, or `Blocked`, and clickable local file links when referencing workspace files. Avoid dumping full JSON into chat. Include enough detail that a consultant can read the chat reply alone and understand the run outcome.

Minimum required fields:

```text
Test:
Status:
Command:
Evidence folder:
Generated/modified files:
Executive summary:
Run details:
Step coverage:
Test data and variances:
Mapped steps:
Unmapped/blocked steps:
Next fixes:
```
