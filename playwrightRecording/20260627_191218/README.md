# Recording — 2026-06-27 19:12:18 IST

Human-readable guide for the consultant flow captured in
`recording.spec.ts` (raw Playwright codegen) and converted to the workspace
automation spec `tests/playwright/recording-20260627-191218.spec.ts`.

## Summary

A short Quote-app flow that authenticates through SAP IAS (OAuth) and starts a
new proposal of type **Engineering/Services Proposal**.

| Item | Value |
|------|-------|
| Source recording | `playwrightRecording/20260627_191218/recording.spec.ts` |
| Login user | `suket.suman@twenty5.com` (SAP IAS / OAuth) |
| App | Twenty5 / iPE Quote (dev) |
| Object created | New proposal — **Engineering/Services Proposal** |
| Object-type picker | `#iBEBusObjType-6041-trigger-picker` |

## Captured steps

1. Navigate to the SAP IAS OAuth authorize URL for the Quote app.
2. Enter **Email or User Name** = `suket.suman@twenty5.com` and click **Continue**.
3. After landing in the app, click **New** to start a new object.
4. Open the business-object-type dropdown (`#iBEBusObjType-6041-trigger-picker`).
5. Select the option **Engineering/Services Proposal**.

A browser `dialog` handler is registered before the option click to auto-dismiss
any confirmation/leave-page prompt.

## Expected result

The Quote app opens a new proposal in **Setup** with proposal type
**Engineering/Services Proposal** selected.

## How this was converted

The raw codegen drives the public SAP OAuth login page directly with a hardcoded
email. The workspace pattern instead reuses the already-signed-in managed Chrome
session through `connectToTwentyFive()` (CDP), then drives the in-app steps with
the shared `ProposalSetupPage` helper. This avoids re-typing credentials and
keeps selectors stable.

Mapping:

| Codegen action | Workspace equivalent |
|----------------|----------------------|
| `page.goto(<OAuth URL>)` + email + Continue | `connectToTwentyFive()` (reuses signed-in session) |
| `getByRole('button', { name: 'New' }).click()` | `setup.navigateToProposals()` + `setup.startNewProposal()` |
| object-type picker + `getByRole('option', { name: 'Engineering/Services Proposal' })` | `setup.selectProposalType('Engineering/Services Proposal')` |

## Run

```bash
scripts/run-playwright-test.sh tests/playwright/recording-20260627-191218.spec.ts --headed --retries=0
```

If the managed session is not signed in, the helper auto-logs-in with the test
user from `.env.local` (`IPE_USERNAME` / `IPE_PASSWORD`). Ask the consultant only
if MFA/SSO interaction is required.
