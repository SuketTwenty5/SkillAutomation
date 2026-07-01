# CLAUDE.md

> **Canonical runbook:** [`skills/skill-playwright-automation/SKILL.md`](skills/skill-playwright-automation/SKILL.md)
>
> This file is a short, auto-loaded pointer + the critical guardrails. **Do not duplicate the
> full workflow here** — the SKILL.md is the single source of truth for both Codex and Claude.
> Before doing any automation work, read the SKILL.md plus only the reference files a task needs.

## What this workspace does

Playwright automation for the Twenty5 / iPE Quote UI. This is the `SkillAutomation` repo — clone it
anywhere (typically `/Users/<username>/SkillAutomation`) and treat that checkout as the **repo root**.
Resolve all paths relative to the repo root, not a fixed `/Users/<name>/...` path.

## Operating principle

- **Playwright is the executor.**
- Locator source priority: cached source artifacts → existing specs/helpers → runtime locators in
  `tests/**/support/*locators*` → fresh page scans → historical catalog
  (`references/ipe-auto-tests-locators.json`) → new glue with stable live locators.

## Commands

```bash
npm run test:pw:manufacturing
npm run test:pw:prof-services-001
scripts/run-playwright-test.sh tests/playwright/<spec>.spec.ts --headed --retries=0
```

## Auth & Chrome (guardrails)

- **First-time setup:** `cp .env.local.example .env.local` at the repo root, then fill in real
  `IPE_USERNAME` / `IPE_PASSWORD` (or set them as env vars). `.env.local` is git-ignored — never
  commit it; commit only `.env.local.example`.
- Use one managed **headed** Playwright Chrome launch — do not start Chrome separately or attach to
  a debug port. Persistent profile lives under `.skillautomation/`.
- Reuse browser state (`auth.json` / `.skillautomation/auth-state.json`). Auto-login from
  environment variables or a git-ignored `.env.local` at the repo root (`IPE_USERNAME`,
  `IPE_PASSWORD`); override the path with `IPE_ENV_FILE`.
- **Never print, echo, store, or commit credentials or `auth-state.json`.** `.skillautomation/`
  must stay git-ignored. Only ask the consultant if MFA/SSO is required, creds are missing, or
  auto-login fails (report as `blocked`).
- **App-shell wait is tiered, never wipe-first.** If the signed-in app shell is missing, give the
  loader the full `PLAYWRIGHT_LOGIN_WAIT_MS` budget before any reload; recover (reload/re-login) only
  on positive evidence of a lost session (visible login form / `about:blank` / `chrome-error:`). A
  slow-but-authenticated app origin (e.g. `#quote`) is not a reason to reload or wipe. Wipe the
  persistent profile only as a bounded last resort after re-login has also failed. Full policy in the
  SKILL.md "App Shell Wait And Session Recovery" section.

## Source-of-truth rules

- **PDF / Scribe / manual source is the source of truth.** Preserve every source value exactly
  (titles, dates, proposal types, companies, clients, templates, users). Do not substitute suite
  fixture values. Keep the source value in `test_data` and add a clearly named `runtime_*` value
  when a unique value is required; report the variance.
- Before re-extracting a source, check for an existing artifact:
  ```bash
  node skills/skill-playwright-automation/scripts/find-source-artifact.mjs "<url|path|title>"
  ```
- Classify each step: `mapped`, `scanned-locator`, `generated-glue`, or `blocked`. A step is
  `mapped` only if the automation performs that same source action with that same source value.
- **Every conversion creates a new timestamped spec.** When asked to convert a source to a
  Playwright script, always write a NEW `tests/playwright/<slug>-<YYYYMMDD-HHMMSS>.spec.ts` — never
  overwrite or edit an existing spec in place. On a full match copy the matching spec's content into
  the new file; on a partial match copy the reusable steps/helpers; otherwise generate fresh. Then
  run the new file (not the original). Full detail in the SKILL.md "Spec File Naming And Reuse Rule".

## Locator, date, and title rules

- **Page Object Model:** locators live only in `tests/playwright/support/pom/`; specs import from
  `./support/pom` and never declare inline `.locator()`/`getBy*()` (enforced by `npm run lint:pom`,
  which `run-playwright-test.sh` runs automatically). Find a control by visible label in
  `pom/POM-INDEX.md` (`npm run pom:index`); seed names on demand with `npm run locator:find -- "<label>"`
  then re-anchor live — never bulk-generate the historical catalog. Trust is computed:
  `POM_VERIFY=1 … && npm run pom:verify` → `.verified.json`. Full detail in the SKILL.md "Page Object Model" section.
- Prefer role/name, label, placeholder, visible text → stable attributes → scoped ExtJS →
  historical XPath → positional XPath (fallback only, mark it). Replace generated ExtJS IDs with
  stable locators; never infer a field name from an ExtJS id/class — confirm from the visible label.
- **Dates are a coupled control.** After setting a start date, let ExtJS auto-calc settle; ensure
  `Project End` is valid (fallback = start + 1 year − 1 day) before clicking Next/Save/Create.
  Prefer `ProposalSetupPage.setEstimatedProjectStart(...)`.
- **Bid description/title** must end with a human-readable timestamp — use
  `ProposalSetupPage.setProposalTitle(...)`; don't double-stamp.
- **Pre-generation guards (enforced on every run).** `run-playwright-test.sh` runs `eslint` **and**
  `tsc --noEmit`. Note `ProposalSetupPage` does NOT extend `BasePage`, so call shared helpers on `est`
  (EstimatePage), never on `setup`. Assert "auto-populated with today" dates via the `$todayDate`
  sentinel (never a literal / spec `new Date()`); assert user/master-data-derived fields (Pursuit
  Manager, Location) via `EstimatePage.expectFieldValueOrVariance(...)`; end every mutating step with a
  hard positive post-condition; never `page.reload()` in a spec (lint-banned). Full policy in the
  SKILL.md "Pre-Generation Checklist And Enforced Guards".
- **Save is a hover-to-reveal split button.** Hover `//*[@data-qtip="Save" and @aria-hidden="false"]//*[@data-ref="btnIconEl"]`
  (don't click), then click the option link `//*[@role="menu" and @aria-hidden="false"]//*[@class="x-menu-item-link" and @aria-hidden="false"]//*[text()="${option}"]`
  (default `Save without Check`), then **wait for completion** (Data-saved toast + Ext.Ajax idle + no
  progressbar/modal) before proceeding — don't race the save API. Use `EstimatePage.saveViaMenu(...)`;
  persist FTE/grid edits this way BEFORE any refresh/roll-up. Full policy in the SKILL.md "Save Menu Rule".
- **Cost/price roll-up is recognised by intent, not the step name.** Trigger the procedure when a step
  does **any** of: expects the toast `Costs/revenues & formula-based costs and prices updated and rolled-up`;
  tells you to click **Update Cost & Price(s)**; targets `//*[@titlelink="updateCostsWithFormula"]`;
  requires verifying a status as **Updated** (`Needs Refresh`/`Updating (n)`/`Currently running`); or
  asks to verify/assert **Cost summary cards / cost widget values** (KPI Total Price/Cost/Margin). Then:
  refresh → click `//*[@titlelink="updateCostsWithFormula"]` → confirm the running status → poll the
  More-menu item `[itemId="mp_more_item_update_costs_btn"]` until `Finished` (reload while running). Use
  `EstimatePage.updateCostsAndWaitForFinished(...)`; never drive cost roll-ups through the Save-menu
  "Check & Save". Full policy in the SKILL.md "Cost/Price Update And Status-Check Rule".
- **Labor/estimate grids are ExtJS locked (split) grids.** Add the first row via a **real** click on
  `a[data-grigaddlink] div[text()]` (a forced click is a no-op); resolve cells by header-x + row-y
  (never absolute `nth()`); edit in-cell combos by selecting the ExtJS **store record on the combo
  component** + Tab-to-commit (the boundlist UI paints `offsetParent=null` for a combo opened after a
  prior edit, so clicking options is unreliable); treat Role→Location as a coupled control (assert
  mechanism, record value variance). Full policy in the SKILL.md "Estimate / Labor Locked-Grid Rules".

## Safety

- Run the **narrowest** headed command for the target spec — don't run a nearby suite just for
  reference code.
- Stop before destructive / production-like mutations unless the user clearly confirms the
  environment and data safety.

## Output

End every run with a polished, chat-visible Markdown report (don't make the user open JSON/traces).
Required fields:

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

## Environment note

LM / Production-System tests use the `app-twenty5ipe-lm-dev` base URL, not the BTP Golden default.
