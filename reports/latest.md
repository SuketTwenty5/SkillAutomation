# Test Report - PDF Creating A New Regression Test Proposal In Twentyfive

| Field | Value |
| --- | --- |
| Source PDF | `/Users/suketsuman/Downloads/CreatingANewRegressionTestProposalInTwentyfive-d668d150-d8f7-4016-be.pdf` |
| Spec | `tests/playwright/regression-proposal-pdf.spec.ts` |
| Command | `scripts/run-playwright-test.sh tests/playwright/regression-proposal-pdf.spec.ts --headed --retries=0` |
| Status | Blocked |
| Evidence | `test-results/regression-proposal-pdf-PD-70788-proposal-from-the-PDF-steps/` |

The PDF-specific spec was generated and discovered successfully, but the run was blocked by sandbox denial when attaching to Chrome at `127.0.0.1:9222`.

```text
Error: browserType.connectOverCDP: connect EPERM 127.0.0.1:9222
```

The follow-up escalated run request was rejected, so the Twenty5 UI steps did not execute.
