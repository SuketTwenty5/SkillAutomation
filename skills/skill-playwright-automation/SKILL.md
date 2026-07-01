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
2. Existing page objects in `tests/playwright/support/pom/` — the single source of locators — plus support helpers (`twentyfive-ui.ts`, `twentyfive-cdp.ts`).
3. Existing Playwright specs that already call the POM.
4. Fresh page-scanned locators (`scan-page-locators.mjs`), added into a page object.
5. Historical catalog as a naming/seed suggestion only, via `node skills/skill-playwright-automation/scripts/find-historical-locator.mjs "<query>"`; re-anchor against the live page before use.
6. New Playwright glue using stable live locators, added into a page object (never inline in a spec).

## Running Tests

Use the repo's Playwright runners and tests:

```bash
npm run test:pw:manufacturing
npm run test:pw:prof-services-001
scripts/run-playwright-test.sh tests/playwright/<spec>.spec.ts --headed --retries=0
```

## Auth And Chrome

**First-time setup:** copy the credentials template to a git-ignored `.env.local` at the repo root and fill in real values (or set `IPE_USERNAME` / `IPE_PASSWORD` in your environment):

```bash
cp .env.local.example .env.local   # then edit .env.local with your creds
```

`.env.local` is git-ignored, so credentials never get committed. Do not commit `.env.local`; commit only `.env.local.example`.

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

### App Shell Wait And Session Recovery

When the signed-in Twenty5 app shell does not appear, recover in tiers — never wipe first. A missing
app shell has many causes (slow ExtJS loader stalling near 95%, a stale locator, a network blip, a
genuinely lost session); only the last is fixed by wiping, and wiping the others destroys a valid
warm session and forces a slower cold login.

1. **Wait the full budget continuously.** Poll for the app shell for the whole
   `PLAYWRIGHT_LOGIN_WAIT_MS` window before any reload. A premature reload restarts the SPA and burns
   the budget — this, not a poisoned session, is the usual cold-start timeout cause.
2. **One soft reload** — only when there is positive evidence the session is gone or the tab is dead:
   a visible login/identity-provider form, `about:blank`, or a `chrome-error:` page. If the URL is on
   the signed-in app origin (e.g. `#quote`), the session is valid and the app is merely slow — do
   **not** reload and do **not** wipe.
3. **Re-login** from `.env.local`/env credentials when a login form is actually present (the
   auto-login/auth-state path already does this).
4. **Wipe only as a bounded last resort.** If, and only if, the full-budget wait + reload + re-login
   have all failed *and* the page still shows a login/auth-error state, wipe
   `.skillautomation/auth-state.json` and `.skillautomation/playwright-chrome/` and cold-login **once**
   (guard with a per-run counter so it cannot loop; log what was wiped). Otherwise keep the warm
   profile — "do not clear cache unless the user asks or the app is demonstrably poisoned."

Session cookies persisted in the profile (`.skillautomation/playwright-chrome/Default/Cookies`) and
`auth-state.json` are bearer credentials: keep `.skillautomation/` git-ignored, never print them or
attach them to reports, and treat failure `trace.zip` files (which capture request headers) as
sensitive.

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
7. Create a **new timestamped spec file** for this conversion (see "Spec File Naming And Reuse Rule"): copy from a full/partial match when one exists, otherwise generate fresh. Keep the change the smallest that covers the source steps.
8. Run the narrowest headed Playwright command for the **newly created** spec. Do not run the original spec it was copied from, and do not run a nearby suite, such as Manufacturing Proposal, just because it contains useful reference code.
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

## Spec File Naming And Reuse Rule

Whenever anyone asks to convert a source (PDF, Scribe, Confluence/manual, `.md` test case, or plain-English steps) into a Playwright script, **always create a brand-new spec file** — never overwrite, edit, or re-run an existing spec in place.

- **New file, every time.** Write the spec to `tests/playwright/<slug>-<YYYYMMDD-HHMMSS>.spec.ts`, where `<slug>` is derived from the source (e.g. the test-case ID lowercased) and the stamp is the current local date and time (matching the repo's existing `recording-YYYYMMDD-HHMMSS` convention). Two conversions of the same source therefore never collide, and each run is traceable to when it was generated.
- **Reuse existing work by copying, not editing.** Before generating, look for an existing match with `node skills/skill-playwright-automation/scripts/find-source-artifact.mjs "<url|path|title>"` and by scanning existing specs and `*.steps.json` artifacts:
  - **Full match** — an existing spec already covers the same source steps and test data: copy that spec's content into the new timestamped file as the starting point, then only re-point the source path and refresh timestamps/derived runtime values.
  - **Partial match** — an existing spec covers some steps or shares helper/POM patterns: copy the reusable portions (matching `test.step` blocks, helper calls, POM usage) into the new file and fill the remaining steps with newly mapped/scanned actions.
  - **No match** — generate a fresh spec in the new file.
- **Run the new file.** Always execute the newly created timestamped spec with the narrowest headed command; do not run the original spec it was copied from.
- **POM rule still applies.** Only the spec file is new — locators still live in `tests/playwright/support/pom/` and the spec imports them from the barrel (see "Page Object Model"). Companion artifacts (e.g. `*.steps.json`) should share the new spec's file stem so evidence stays traceable.

## Pre-Generation Checklist And Enforced Guards

A full-match copy is a starting point, not a validated spec — its recording-time assumptions can be
stale against the current app/master-data. Before running a converted spec, satisfy this checklist. The
runner (`run-playwright-test.sh`) enforces the first two automatically:

- **Lint + type-check both pass** — the runner runs `eslint` (POM guard) **and** `tsc --noEmit` on every
  run. The type-check exists because **`ProposalSetupPage` does NOT extend `BasePage`** (the other POM
  classes do): a shared helper like `startToastObserver`/`saveViaMenu`/`expectFieldValue` must be called
  on an `EstimatePage` instance (`est.*`), never on `setup` — calling it on `setup` is now a build error,
  not a runtime crash.
- **Every value the source labels "auto-populated"/"derived" is asserted by mechanism, never by a
  recording-time literal:**
  - today-populated dates → assert with the **`$todayDate`** sentinel (`est.expectFieldValue('Planned Start', '$todayDate')`); never a hardcoded date or a spec-local `new Date()`.
  - user/master-data-derived fields (Pursuit Manager, Location, rates) → `est.expectFieldValueOrVariance(label, sourceValue, testInfo, {...})` (asserts populated, records a `variance` annotation on mismatch); the exact KPI values (Price/Cost/Margin) → assert the band contains `USD` + record variances. Keep the source value in `test_data`.
- **Every mutating `test.step` ends with at least one HARD positive post-condition** — a bare
  `await expect(...).toBe*` with no `.catch`/`{optional:true}` proving the action happened (saved URL
  present, no-records overlay hidden, cell value committed, KPI band shows `USD`). `.catch(() => …)` /
  `{optional: true}` are permitted **only** for genuinely env-derived variances that are also recorded
  via `testInfo.annotations.push({ type: 'variance', … })`. Never infer success from "no error thrown".
- **Live-probe any control the template touches whose value/markup is env-derived** before trusting the
  copied assertion — do not re-run the full suite to discover a stale value; a targeted probe is cheaper.
- **Never `page.reload()` in a spec** (lint-banned): a reload over an unsaved ExtJS form discards edits.
  When a value is late because an ExtJS field auto-calcs from another (dates, Location←Role, Cost
  Center←Profit Center, KPIs), **poll the coupled field to settle** (`waitForFieldValue`/`expect.poll`);
  reloading is done only inside POM methods (`updateCostsAndWaitForFinished`, which self-guards by
  saving first).
- **Legacy grid specs are NOT reuse sources.** `create-capital-project-*` and
  `create-engineering-services-*` predate the POM and use banned inline positional gridcell locators
  (they survive only in the ESLint `BASELINE`). Do not copy them for a "similar prior project"
  conversion — start from a POM-based spec such as `tc-prk-simpleconsulting-001*.spec.ts`.

## Page Object Model

Locators live **only** in page objects under `tests/playwright/support/pom/`. Specs import from the barrel (`./support/pom`) and call methods/getters — never declare inline locators.

- **The rule is enforced.** `npm run lint:pom` (ESLint, also run automatically by `scripts/run-playwright-test.sh`) fails any spec that calls `.locator(...)` or `getBy*(...)` directly. Builder helpers imported from `support/` (e.g. `textLocator`, `clickTab`) are allowed — they are not inline locators. Bypass only in an emergency with `POM_GUARD=off`. There is a ratchet baseline in `eslint.config.mjs` of specs that predate the rule; burn it down (remove entries) as you migrate them, and never add to it.
- **Two-shaped.** A page object exposes stable single elements as `readonly Locator` getters, and dynamic controls (grid cell by column, inline editors, dropdowns, dialogs) as parameterized methods. See `EstimatePage` for the grid pattern and `BasePage` for shared form/save/toast controls. `tc-prk-simpleconsulting-001.spec.ts` is the reference for a fully POM-sourced spec.
- **Build a spec from a source using the POM:** for each step, find the control by its **visible label** in `tests/playwright/support/pom/POM-INDEX.md` (regenerate with `npm run pom:index`) → call that member. If it is missing, add it to the right page object first (proven locator → live scan → historical seed), then call it.
- **On-demand seeding only — never bulk-generate.** The historical catalog is 100% stale (old build) and mostly untouched pages. Pull a *naming/intent* suggestion for one control with `npm run locator:find -- "<label or class>"`, then **re-anchor** the actual locator against the live page (`scan-page-locators.mjs`, preferring visible label/role) before adding it. Do not generate the catalog into the POM wholesale.
- **Never infer names from ExtJS ids.** Name getters from the visible label; put any id/class hint in a comment. Tag brittle fallbacks (e.g. positional `nth()`) with `@fallback-positional` so a passing run cannot launder them into "trusted".
- **Computed trust, not comments.** Trust is derived from execution, not prose. Run a verification pass with `POM_VERIFY=1 scripts/run-playwright-test.sh <spec> --retries=0` (forces a trace on green runs), then `npm run pom:verify` to write `tests/playwright/support/pom/.verified.json` (generated; selectors + run metadata only). A locator counts as verified iff it appears there from the latest green run, so trust lapses automatically when a run is red or absent. Add `@label <visible label>` JSDoc to locator members so they surface in the index.

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

## Save Menu Rule

The Save control is a **hover-to-reveal split button**, not a click target. Whenever a step saves via the
Save icon (e.g. source step "Click the Save icon (floppy disk) dropdown → Save options appear: Check &
Save, Save without Check, Check without Save"):

1. **Hover** the Save floppy icon: `//*[@data-qtip="Save" and @aria-hidden="false"]//*[@data-ref="btnIconEl"]`
   (hover, do not click; retry with click-outside + re-hover if the menu doesn't appear).
2. **Click** the option link by its visible text:
   `//*[@role="menu" and @aria-hidden="false"]//*[@class="x-menu-item-link" and @aria-hidden="false"]//*[text()="${option}"]`.
   **Default `option` = `Save without Check`.**
3. **Wait for the action to complete before proceeding** — do not race ahead. Confirm completion by: the
   `Data saved successfully` toast (captured by the toast observer even if transient), then ExtJS settle —
   `!Ext.Ajax.isLoading()`, no `.x-mask[role=progressbar]` visible, and no `window[modal=true]` visible.

In `SkillAutomation`, use `EstimatePage.saveViaMenu(option = 'Save without Check')`, which hovers, clicks
the option by text, and calls `waitForActionComplete()`. Persist grid edits (e.g. FTE) with this BEFORE a
refresh/roll-up — refreshing over an unsaved edit discards it and makes the roll-up compute on empty data.

## Cost/Price Update And Status-Check Rule

**Recognise this step by intent, not by its name or number.** A step is a cost/price roll-up step
whenever **any** of the following is true — treat them as equivalent triggers for the procedure below:

- its expected result contains the toast `Costs/revenues & formula-based costs and prices updated and rolled-up`; **or**
- it tells you to click **Update Cost & Price(s)** (by that label/instruction); **or**
- it targets the element `//*[@titlelink="updateCostsWithFormula"]`; **or**
- it requires verifying/checking a calculation **status as "Updated"** (or reads a status such as
  `Needs Refresh` / `Updating (n)` / `Currently running`).

Do **not** key off the step label/number (it may say "Check & Save", "Update Costs", "Calculate", etc.).
When any trigger above matches, apply this procedure:

1. **Refresh first, then wait for load.** Reload the proposal and wait for the app shell + KPI header to
   finish loading before interacting — the header is often mid-`Updating (n)` right after a grid edit.
2. **Trigger the calculation** by clicking the Update Cost & Prices control:
   `//*[@titlelink="updateCostsWithFormula"]`. Do **not** drive cost roll-ups through the Save
   split-button "Check & Save" menu — that path is timing-fragile while background jobs are in flight.
3. **Confirm the running status.** A `Currently running` / `Running` status becomes visible a few seconds
   after clicking `updateCostsWithFormula` — poll for it rather than asserting immediately.
4. **Wait for Finished** by polling the status item in the More menu, reloading while it is still
   running. Open the menu via the `button[tooltip=More]` component, read the item
   `[itemId="mp_more_item_update_costs_btn"]` text, and close the menu between reads:
   - text includes `Finished` → done.
   - text includes `Currently running` → fire the `Reload` component's handler
     (`tooltip`/`qtip === "Reload"`), then wait until `!Ext.Ajax.isLoading()`, no
     `window[modal=true]` is visible, and no `.x-mask[role=progressbar]` is visible, then poll again.
   Bound the loop (~15 min) and log each iteration's status text.

In `SkillAutomation`, use `EstimatePage.updateCostsAndWaitForFinished(...)`, which implements exactly this
refresh → `updateCostsWithFormula` → running → poll-More-status(Finished/reload) sequence. Only the exact
KPI values that follow (Total Price/Cost/Margin) are rate/master-data derived — verify the roll-up ran and
record value differences as variances (see the coupled-control principle below).

## Estimate / Labor Locked-Grid Rules

The Labor Resources and estimate grids are ExtJS **locked (split) grids**: one logical row is rendered
across a frozen left sub-grid (`x-grid-inner-locked`: WBS, Grade, Location, Role, …) and a scrollable
right sub-grid (`x-grid-inner-normal`: Start, End, FTE, Effort, …), plus a hidden pivot grid. Generate
grid code with these rules — each is proven against the live app:

- **Add the first row on an empty grid** by clicking the empty-state add link — the inner `div[text()]`
  of the `a[data-grigaddlink='true']` anchor — with a **real (non-forced) Playwright click** so the
  ExtJS `ibe-add-row` delegated handler fires. A forced or synthetic `element.click()` on the anchor
  wrapper is a no-op that never creates a row. Assert the overlay is gone / a real row exists as the
  post-condition; never infer success from "no error thrown". Locator:
  `//*[@role='tabpanel' and @aria-hidden='false']//*[@aria-hidden='false' and (@role='grid' or @role='treegrid')]//*[@data-grigaddlink='true']//div[text()]`
- **Never resolve cells with absolute `getByRole('gridcell').nth(N)`.** That index counts across BOTH
  sub-grids and hidden columns, so a right-grid column (FTE/Effort) is unreachable positionally. Resolve
  a cell by the column **header's x-position + the data row's y-position** and click that point
  (coordinate resolution). Header text carries a hidden "Cell value has been edited" a11y span, so match
  headers with `starts-with(normalize-space(.), 'FTE')`, not `= 'FTE'`.
- **Edit an in-cell combo by selecting the STORE RECORD, not the boundlist UI.** Double-click the cell
  to open + focus the ExtJS cell editor, wait a fixed settle (~700ms), then type real keystrokes into the
  focused editor to drive the remote/`forceSelection`/`minChars` query and load the combo's store. Then
  select the matching record **on the combo component itself** and `Tab` to commit. Do NOT rely on
  clicking a boundlist item: for a combo opened *after a preceding cell edit* (e.g. Role right after
  Grade), the boundlist renders with `offsetParent=null` — it paints as not-visible and no `role=option`
  is ever clickable, even though the store is fully loaded. Native selection is deterministic:
  `const combo = Ext.ComponentQuery.query('combobox').filter(c=>c.isVisible()).find(c=>c.hasFocus); const rec = combo.getStore().getRange().find(r => r.get(combo.displayField) === value); combo.select(rec); combo.fireEvent('select', combo, rec);` then `Tab`.
  Selecting the record alone does not write the cell — the `Tab` commit is required (verified it retains).
  Do NOT type into a re-located input (`.x-grid-cell-editor input`) — that steals focus from the freshly
  opened combo so its query never fires.
- **Coupled auto-populate.** Some cells populate from others (Role → Location, Grade → rate) and the
  exact value is user/master-data derived. Wait for the coupled field to settle, assert the mechanism
  (non-empty / changed), and record the runtime value as a variance rather than hardcoding a
  recording-time value — same principle as the Date Field Rule.

`EstimatePage` implements these: `ensureLaborResourceRow`/`addLaborResourceRow`, `openGridCellEditorByHeader`,
`selectResourceGridDropdown`, `activeCellEditorInput`, `boundlistOption`, and `updateCostsAndWaitForFinished`.

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
