#!/usr/bin/env python3
"""Map normalized manual-test steps to existing locators (transparency report).

Given a steps file (steps.json from normalize_manual_test.py --json, or any JSON
with a top-level "steps" list) and the generated locator index, this script
matches each step's target to a proven locator and classifies it:

    mapped         - a confident locator already exists for this target
    needs-selector - the step targets an element with no matching locator
    note           - navigation / verification step that needs no locator

It is intentionally deterministic and browser-free: it does the reuse lookup so
Claude (the test-builder) can focus on glue, not on re-deriving selectors.

Usage:
    map_steps.py STEPS.json [--index PATH] [--out PATH] [--min-score 0.34]
"""

from __future__ import annotations

import argparse
import json
import re
import sys
from pathlib import Path

# Actions that operate on a page rather than a single element.
NON_ELEMENT_ACTIONS = {"navigate", "open", "go", "goto", "login", "wait", "verify", "assert", "check"}
STOPWORDS = {"the", "a", "an", "on", "in", "to", "into", "of", "for", "field", "button",
             "tab", "page", "dropdown", "menu", "link", "icon", "and", "click", "select",
             "enter", "type", "input", "value", "with", "is", "are", "be", "from", "this",
             "that", "it", "then", "should"}


def tokenize(text: str) -> set[str]:
    text = text.lower().replace("'", " ").replace('"', " ")
    tokens = re.findall(r"[a-z0-9]+", text)
    return {t for t in tokens if t not in STOPWORDS and len(t) > 1}


def score(target_tokens: set[str], name: str) -> float:
    name_tokens = tokenize(name)
    if not name_tokens or not target_tokens:
        return 0.0
    overlap = target_tokens & name_tokens
    if not overlap:
        return 0.0
    # Jaccard-ish, biased toward covering the locator name's tokens.
    return len(overlap) / len(target_tokens | name_tokens)


def load_steps(path: Path) -> list[dict]:
    raw = path.read_text(encoding="utf-8", errors="ignore")
    try:
        data = json.loads(raw)
    except json.JSONDecodeError:
        try:
            import yaml  # type: ignore
            data = yaml.safe_load(raw)
        except Exception as exc:  # pragma: no cover - guidance path
            raise SystemExit(
                f"Could not parse {path} as JSON. Re-run normalize_manual_test.py with "
                f"--json, or install PyYAML. ({exc})"
            )
    steps = data.get("steps") if isinstance(data, dict) else data
    if not isinstance(steps, list):
        raise SystemExit(f"{path} has no 'steps' list")
    return steps


def build_index(index_path: Path) -> list[dict]:
    payload = json.loads(index_path.read_text(encoding="utf-8"))
    return payload.get("locators", [])


def best_matches(target: str, locators: list[dict], min_score: float) -> list[dict]:
    tt = tokenize(target)
    scored = []
    for loc in locators:
        s = score(tt, loc["name"])
        if s >= min_score:
            scored.append((s, loc))
    scored.sort(key=lambda x: x[0], reverse=True)
    return [
        {
            "score": round(s, 3),
            "name": loc["name"],
            "page": loc["page"],
            "class": loc["class"],
            "field": loc["field"],
            "element_type": loc["element_type"],
            "locator": loc["locator"],
            "source_file": loc["source_file"],
        }
        for s, loc in scored[:3]
    ]


def main() -> int:
    repo_root = Path(__file__).resolve().parents[3]
    parser = argparse.ArgumentParser(description=__doc__)
    parser.add_argument("steps", help="steps.json (or JSON with a 'steps' list)")
    parser.add_argument(
        "--index",
        default=str(
            repo_root
            / "skills"
            / "selenium-automation"
            / "references"
            / "locator-index.generated.json"
        ),
        help="locator-index.generated.json path",
    )
    parser.add_argument("--out", default="-", help="Output mapping-report JSON path or '-'")
    parser.add_argument("--min-score", type=float, default=0.34, help="Match threshold")
    args = parser.parse_args()

    index_path = Path(args.index)
    if not index_path.exists():
        raise SystemExit(
            f"locator index not found: {index_path}\n"
            f"Run: python3 skills/selenium-automation/scripts/build_locator_index.py"
        )

    locators = build_index(index_path)
    steps = load_steps(Path(args.steps))

    details = []
    counts = {"mapped": 0, "needs-selector": 0, "note": 0}
    for i, step in enumerate(steps, start=1):
        action = str(step.get("action") or "").strip()
        target = str(step.get("target") or "").strip()
        first_word = re.split(r"\W+", action.lower(), maxsplit=1)[0] if action else ""

        # Use an explicit target if present, otherwise derive from the action text.
        lookup = target or action
        candidates = best_matches(lookup, locators, args.min_score) if lookup else []

        if candidates:
            status = "mapped"
        elif first_word in NON_ELEMENT_ACTIONS and not target:
            status = "note"
        else:
            status = "needs-selector"
        counts[status] += 1

        details.append(
            {
                "step": step.get("id", i),
                "action": action,
                "target": target or None,
                "status": status,
                "candidates": candidates,
            }
        )

    report = {
        "index": str(index_path.relative_to(repo_root)) if index_path.is_relative_to(repo_root) else str(index_path),
        "steps_total": len(steps),
        "summary": counts,
        "details": details,
    }

    rendered = json.dumps(report, indent=2, ensure_ascii=False) + "\n"
    if args.out == "-":
        print(rendered, end="")
    else:
        Path(args.out).write_text(rendered, encoding="utf-8")
        print(f"Wrote {args.out}")
    # Console summary on stderr so stdout stays clean JSON when --out is '-'.
    print(
        f"[map_steps] {counts['mapped']} mapped / "
        f"{counts['needs-selector']} needs-selector / {counts['note']} note "
        f"(of {len(steps)} steps)",
        file=sys.stderr,
        flush=True,
    )
    return 0


if __name__ == "__main__":
    raise SystemExit(main())
