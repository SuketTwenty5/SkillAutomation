# Confluence Test Extraction

Manual test pages often contain inconsistent headings. Extract defensively.

## Common Fields

Map these headings to normalized fields:

```text
Test Case ID, Key, Scenario ID -> test_id
Title, Scenario, Test Name -> title
Preconditions, Prerequisites, Setup -> preconditions
Test Data, Data, Inputs -> test_data
Steps, Procedure, Action -> steps[].action
Expected Result, Expected, Verification -> steps[].expected
Environment, Target, URL -> environment
```

## Table Patterns

For tables like:

```text
Step | Action | Expected Result
1    | Login  | Dashboard opens
```

produce:

```yaml
steps:
  - id: 1
    action: Login
    expected: Dashboard opens
```

For tables like:

```text
Action | Data | Result
```

produce:

```yaml
steps:
  - id: 1
    action:
    value:
    expected:
```

## Ambiguity Rules

- Preserve original wording in `action` when unsure.
- Put unclear values in `notes` instead of guessing.
- Mark missing expected results as `expected: null`.
- Treat destructive actions such as delete, submit, approve, cancel, payment, and production updates as requiring explicit environment confirmation.
