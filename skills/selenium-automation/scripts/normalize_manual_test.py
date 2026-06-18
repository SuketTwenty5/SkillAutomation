#!/usr/bin/env python3
"""Turn a simple pasted manual test into YAML scaffolding."""

from __future__ import annotations

import argparse
import re
from pathlib import Path


STEP_RE = re.compile(r"^\s*(?:step\s*)?(\d+)[.)\-\s:]+(.+)$", re.IGNORECASE)


def yaml_scalar(value: str | None) -> str:
    if not value:
        return "null"
    escaped = value.replace('"', '\\"')
    return f'"{escaped}"'


def normalize(text: str) -> str:
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
    parser.add_argument("--out", default="-", help="Output YAML path or '-'")
    args = parser.parse_args()

    text = Path(args.input).read_text(encoding="utf-8", errors="ignore")
    output = normalize(text)
    if args.out == "-":
        print(output, end="")
    else:
        Path(args.out).write_text(output, encoding="utf-8")
        print(f"Wrote {args.out}")
    return 0


if __name__ == "__main__":
    raise SystemExit(main())
