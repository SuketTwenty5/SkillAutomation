#!/usr/bin/env python3
"""Create a starter action catalog from Java Selenium/Selenide files."""

from __future__ import annotations

import argparse
import re
from pathlib import Path


CLASS_RE = re.compile(r"\b(?:class|interface)\s+([A-Za-z0-9_]+)")
METHOD_RE = re.compile(
    r"\b(?:public|protected|private)\s+(?:static\s+)?(?:[A-Za-z0-9_<>, ?\\[\\]]+)\s+([a-zA-Z_][A-Za-z0-9_]*)\s*\(([^)]*)\)"
)
SELECTOR_RE = re.compile(r"(?:By\.[a-zA-Z]+\(|[$][$x]?\(|SelenideElement\s+[A-Za-z0-9_]+\s*=)")


def java_files(root: Path) -> list[Path]:
    ignored = {"target", "build", ".git", ".gradle", "node_modules"}
    return [
        path
        for path in root.rglob("*.java")
        if not any(part in ignored for part in path.parts)
    ]


def extract(path: Path) -> dict[str, object]:
    text = path.read_text(encoding="utf-8", errors="ignore")
    class_match = CLASS_RE.search(text)
    methods = METHOD_RE.findall(text)
    has_selectors = bool(SELECTOR_RE.search(text))
    return {
        "path": path,
        "class": class_match.group(1) if class_match else path.stem,
        "methods": methods,
        "has_selectors": has_selectors,
    }


def main() -> int:
    parser = argparse.ArgumentParser()
    parser.add_argument("root", nargs="?", default=".", help="Automation repo root")
    parser.add_argument(
        "--out",
        default="action-catalog.generated.md",
        help="Output markdown catalog path",
    )
    args = parser.parse_args()

    root = Path(args.root).resolve()
    entries = [extract(path) for path in java_files(root)]

    lines = [
        "# Generated Action Catalog",
        "",
        "Review and curate this file before using it as the final action catalog.",
        "",
    ]

    for entry in entries:
        methods = entry["methods"]
        if not methods and not entry["has_selectors"]:
            continue
        rel = Path(entry["path"]).relative_to(root)
        lines.append(f"## {entry['class']}")
        lines.append("")
        lines.append(f"File: `{rel}`")
        lines.append("")
        if entry["has_selectors"]:
            lines.append("Contains Selenium/Selenide selectors.")
            lines.append("")
        if methods:
            lines.append("Methods:")
            for name, params in methods:
                lines.append(f"- `{entry['class']}.{name}({params})`")
            lines.append("")

    output = Path(args.out)
    output.write_text("\n".join(lines).rstrip() + "\n", encoding="utf-8")
    print(f"Wrote {output}")
    return 0


if __name__ == "__main__":
    raise SystemExit(main())
