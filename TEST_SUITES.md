# Twenty5 Test Suite Map

Use this map when a consultant asks to run a named test suite, for example:

```bash
scripts/run-twentyfive-test.sh --suite "Manufacturing Proposal"
```

The runner executes each mapped Cucumber tag one by one, in the order shown here. For suite runs, it runs `mvn clean test` for the first tag and `mvn test` for the remaining tags.

| Suite name | Cucumber tags, in run order |
| --- | --- |
| Smoke Tests | `@SMOKE-TEST` |
| Professional Services Proposal | `@SETUP-PROFF-SERVICES`, `@COST-CREATION-PROFF-SERVICES`, `@PRICING-ASSIGNMENT-PROFF-SERVICES` |
| Manufacturing Proposal | `@SETUP-MANUFACTURING`, `@COST-CREATION-MANUFACTURING_PROPOSAL`, `@OVERHEAD-COST-CREATION-MANUFACTURING_PROPOSAL`, `@AMORTIZATION-DEPRECIATION-MANUFACTURING-PROPOSAL` |
| Copy Prior Manufacturing Proposal | `@copyManufacturingPriorProposal` |
| Distribute Labor Costs using Manual Effort input | `@DistributeLaborCosts` |
| Check that Labor Inflation is calculated correctly over the length of the proposal | `@laborInflationProposal` |
| BOM Import | `@basicImportBOM` |
| Excel BOM Import | `@excelBOMImport` |
| Performance | `@FCP` |

List available suites from the runner:

```bash
scripts/run-twentyfive-test.sh --list-suites
```

The runner also accepts these environment variables for compatibility with CI-style callers:

```bash
TEST_SUITE="Manufacturing Proposal" scripts/run-twentyfive-test.sh
Test_Suite="Manufacturing Proposal" scripts/run-twentyfive-test.sh
SUITE="Manufacturing Proposal" scripts/run-twentyfive-test.sh
```
