# Locator Index & Step Mapping

This is the "reuse, don't regenerate" core from the architecture proposal,
implemented in-place on top of the existing Twenty5/IPE page objects. It exists
so that a manual test step is mapped to a **proven, already-written locator**
instead of a freshly guessed one each run — the main source of flakiness.

It is built entirely from annotations that already exist in the codebase:

```java
@ElementName("'Sets' tab")                          // human-readable name (the key)
@FindBy(xpath = "//a//span[text()='Sets']")         // the locator
BaseWebElement setsTab;                              // field name + element type
// ... inside a class annotated:
@WebPage(value = "Sets page", ...)                  // the page it lives on
```

No parallel locator library is introduced. The index is a generated *view* of the
existing source, so it can be regenerated any time the page objects change.

## Files

| File | Purpose |
|------|---------|
| `scripts/build_locator_index.py` | Scan Java source → `references/locator-index.generated.json` |
| `references/locator-index.generated.json` | Searchable catalog: human name → locator, page, class, field, element type, source |
| `scripts/normalize_manual_test.py --json` | Pasted manual test → `steps.json` |
| `scripts/map_steps.py` | `steps.json` + index → `mapping-report.json` (mapped / needs-selector / note) |
| `scripts/validate_locators.py` | Static, browser-free check that every indexed locator still exists in source |

## Pipeline

```bash
# 1. (Re)build the index from the page objects. Re-run whenever they change.
python3 skills/selenium-automation/scripts/build_locator_index.py

# 2. Normalize a pasted/manual test into steps.json
python3 skills/selenium-automation/scripts/normalize_manual_test.py input.txt --json --out test.steps.json

# 3. Produce a transparency mapping report (which steps reuse proven locators)
python3 skills/selenium-automation/scripts/map_steps.py test.steps.json --out test.mapping.json

# 4. (optional) Fail fast if any indexed locator went stale
python3 skills/selenium-automation/scripts/validate_locators.py --quiet
```

The mapping report drives test generation: `mapped` steps call the existing page
object field/method shown in `candidates`; `needs-selector` steps are the only
ones that may require a new selector; `note` steps are navigation/verification.

## Index entry schema

```json
{
  "name": "'Sets' tab",
  "app": "twentyfive",
  "page": "Sets page",
  "class": "setsPage",
  "field": "setsTab",
  "element_type": "tab",
  "java_type": "BaseWebElement",
  "by": "xpath",
  "locator": "//a[@aria-selected='true']//span[text()='Sets' or text()='Sets/Phases']",
  "source_file": "ipe/src/main/java/.../setsPage.java",
  "source_line": 19
}
```

## Mapping report schema

```json
{
  "steps_total": 7,
  "summary": { "mapped": 6, "needs-selector": 0, "note": 1 },
  "details": [
    {
      "step": 1,
      "action": "Click on PROPOSALS tab",
      "status": "mapped",
      "candidates": [
        { "score": 1.0, "name": "'PROPOSALS' tab", "class": "BaseIpePage",
          "field": "bidsTab", "element_type": "tab",
          "locator": "//span[text()='PROPOSALS']", "source_file": "..." }
      ]
    }
  ]
}
```

`candidates` is the top-3 ranked matches. The mapper is a deterministic,
browser-free pre-pass — it narrows the search; the skill still confirms the right
candidate before generating glue. Tune match strictness with `--min-score`
(default `0.34`).
