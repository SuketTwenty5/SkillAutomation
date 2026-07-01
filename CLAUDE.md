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

- Use one managed **headed** Playwright Chrome launch — do not start Chrome separately or attach to
  a debug port. Persistent profile lives under `.skillautomation/`.
- Reuse browser state (`auth.json` / `.skillautomation/auth-state.json`). Auto-login from
  environment variables or a git-ignored `.env.local` at the repo root (`IPE_USERNAME`,
  `IPE_PASSWORD`); override the path with `IPE_ENV_FILE`.
- **Never print, echo, store, or commit credentials or `auth-state.json`.** `.skillautomation/`
  must stay git-ignored. Only ask the consultant if MFA/SSO is required, creds are missing, or
  auto-login fails (report as `blocked`).

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

## Locator, date, and title rules

- Prefer role/name, label, placeholder, visible text → stable attributes → scoped ExtJS →
  historical XPath → positional XPath (fallback only, mark it). Replace generated ExtJS IDs with
  stable locators; never infer a field name from an ExtJS id/class — confirm from the visible label.
- **Dates are a coupled control.** After setting a start date, let ExtJS auto-calc settle; ensure
  `Project End` is valid (fallback = start + 1 year − 1 day) before clicking Next/Save/Create.
  Prefer `ProposalSetupPage.setEstimatedProjectStart(...)`.
- **Bid description/title** must end with a human-readable timestamp — use
  `ProposalSetupPage.setProposalTitle(...)`; don't double-stamp.

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
