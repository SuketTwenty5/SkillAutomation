#!/usr/bin/env python3
"""Turn a simple pasted manual test into YAML scaffolding."""

from __future__ import annotations

import argparse
import json
import re
from pathlib import Path


STEP_RE = re.compile(r"^\s*(?:step\s*)?(\d+)[.)\-\s:]+(.+)$", re.IGNORECASE)


def yaml_scalar(value: str | None) -> str:
    if not value:
        return "null"
    escaped = value.replace('"', '\\"')
    return f'"{escaped}"'


def parse(text: str) -> dict:
    """Parse pasted manual-test text into a structured dict (shared by YAML/JSON)."""
    title = None
    preconditions: list[str] = []
    test_data: list[str] = []
    steps: list[tuple[str, str, str | None]] = []

    current_step: tuple[str, str, str | None] | None = None
    for raw_line in text.splitlines():
        line = raw_line.strip()
        if not line:
            continue
        lower = line.lower()
        if lower.startswith(("title:", "scenario:", "test name:")):
            title = line.split(":", 1)[1].strip()
            continue
        if lower.startswith(("precondition:", "preconditions:", "prerequisite:", "setup:")):
            preconditions.append(line.split(":", 1)[1].strip())
            continue
        if lower.startswith(("test data:", "data:", "input:")):
            test_data.append(line.split(":", 1)[1].strip())
            continue
        if lower.startswith(("expected:", "expected result:", "result:")) and current_step:
            step_id, action, _ = current_step
            current_step = (step_id, action, line.split(":", 1)[1].strip())
            steps[-1] = current_step
            continue
        match = STEP_RE.match(line)
        if match:
            current_step = (match.group(1), match.group(2).strip(), None)
            steps.append(current_step)

    return {
        "title": title,
        "preconditions": preconditions,
        "test_data": test_data,
        "steps": [
            {"id": sid, "action": action, "target": None, "value": None, "expected": expected}
            for sid, action, expected in steps
        ],
    }


def to_json(text: str) -> str:
    """Emit steps.json consumable by map_steps.py."""
    parsed = parse(text)
    payload = {
        "test_id": None,
        "title": parsed["title"],
        "source": "pasted-manual-test",
        "environment": None,
        "preconditions": parsed["preconditions"],
        "test_data": parsed["test_data"],
        "steps": parsed["steps"],
    }
    return json.dumps(payload, indent=2, ensure_ascii=False) + "\n"


def normalize(text: str) -> str:
    parsed = parse(text)
    title = parsed["title"]
    preconditions = parsed["preconditions"]
    test_data = parsed["test_data"]
    steps = [(s["id"], s["action"], s["expected"]) for s in parsed["steps"]]

    lines = [
        "test_id: null",
        f"title: {yaml_scalar(title)}",
        "source: pasted-manual-test",
        "environment: null",
    ]
    if preconditions:
        lines.append("preconditions:")
        lines.extend(f"  - {yaml_scalar(item)}" for item in preconditions)
    else:
        lines.append("preconditions: []")
    if test_data:
        lines.append("test_data:")
        lines.extend(f"  - {yaml_scalar(item)}" for item in test_data)
    else:
        lines.append("test_data: []")
    if steps:
        lines.append("steps:")
        for step_id, action, expected in steps:
            lines.append(f"  - id: {step_id}")
            lines.append(f"    action: {yaml_scalar(action)}")
            lines.append("    target: null")
            lines.append("    value: null")
            lines.append(f"    expected: {yaml_scalar(expected)}")
    else:
        lines.append("steps: []")
    return "\n".join(lines) + "\n"


def main() -> int:
    parser = argparse.ArgumentParser()
    parser.add_argument("input", help="Text file containing pasted manual test")
    parser.add_argument("--out", default="-", help="Output path or '-'")
    parser.add_argument(
        "--json",
        action="store_true",
        help="Emit steps.json (feeds map_steps.py) instead of YAML",
    )
    args = parser.parse_args()

    text = Path(args.input).read_text(encoding="utf-8", errors="ignore")
    output = to_json(text) if args.json else normalize(text)
    if args.out == "-":
        print(output, end="")
    else:
        Path(args.out).write_text(output, encoding="utf-8")
        print(f"Wrote {args.out}")
    return 0


if __name__ == "__main__":
    raise SystemExit(main())
