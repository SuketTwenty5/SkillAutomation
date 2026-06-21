# Consultant Test Request Runner

Use one command for mapped consultant requests:

```bash
scripts/run-test-request.sh "run TC-Prof-Services-001: Verify Proposal Setup Layout"
scripts/run-test-request.sh "Manufacturing Proposal"
scripts/run-test-request.sh "TC-CLIN-UPLOAD-IMPORT"
scripts/run-test-request.sh "TC-MD-PS-001"
```

The runner resolves the request through:

- `config/test-registry.json` for mapped test cases
- `config/test-suites.json` for named suites

It then:

- shows the default URL for the resolved test or suite
- lets an interactive user paste an alternate URL
- preflights required files and Cucumber tags
- launches/reuses the dedicated Chrome debug session when allowed
- waits briefly for manual SSO/login after launching Chrome
- delegates to the correct low-level runner

## Useful Options

```bash
scripts/run-test-request.sh --list
scripts/run-test-request.sh --dry-run "Manufacturing Proposal"
scripts/run-test-request.sh --url "https://app-twenty5ipe-lm-dev.cfapps.us10.hana.ondemand.com/" "TC-CLIN-UPLOAD-IMPORT"
scripts/run-test-request.sh --yes "TC-Prof-Services-001"
```

## CLIN Upload Data

For `TC-CLIN-UPLOAD-IMPORT`, the workbook must exist locally. By default the runner expects:

```text
~/Downloads/CLIN_Upload_Regression_Test_large.xlsx
```

Override it for one run:

```bash
CLIN_UPLOAD_FILE="/path/to/CLIN_Upload_Regression_Test_large.xlsx" scripts/run-test-request.sh "TC-CLIN-UPLOAD-IMPORT"
```

## Agent Rule

When a consultant asks in plain English, first try this runner. Only switch to authoring mode when the resolver says no mapped test or suite exists.
