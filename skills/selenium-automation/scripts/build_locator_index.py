#!/usr/bin/env python3
"""Build a searchable locator index from existing Selenide/Cucumber page objects.

The Twenty5/IPE page objects already encode a human-readable locator catalog via
three coordinated annotations:

    @ElementName("'Sets' tab")                 <- human-readable name (the key)
    @FindBy(xpath = "//a//span[text()='Sets']") <- the locator
    BaseWebElement setsTab;                      <- field name + element type

and the enclosing class carries:

    @WebPage(value = "Sets page", ...)           <- the page the element lives on

This script walks the Java source, resolves @ElementName string constants, and
emits a deterministic locator-index.json so manual test steps can be mapped to a
proven, reusable locator instead of re-guessing one per run.

Usage:
    build_locator_index.py [SOURCE_ROOT] [--out PATH] [--app NAME]

Defaults are tuned for this repo: it scans imported/twentyfive-regtest and writes
skills/selenium-automation/references/locator-index.generated.json.
"""

from __future__ import annotations

import argparse
import datetime as _dt
import json
import re
from pathlib import Path

# ---- annotation / declaration patterns -------------------------------------

WEBPAGE_RE = re.compile(r'@WebPage\(\s*value\s*=\s*"([^"]+)"')
CLASS_RE = re.compile(r"\bclass\s+([A-Za-z0-9_]+)")
ELEMENT_NAME_RE = re.compile(r'@ElementName\(\s*("(?:[^"\\]|\\.)*"|[A-Za-z_][A-Za-z0-9_.]*)\s*\)')
# @FindBy(xpath = "..."), (css = "..."), (id = "..."), (using = "..."), etc.
FINDBY_RE = re.compile(
    r'@FindBy\(\s*(?:how\s*=\s*How\.([A-Z_]+)\s*,\s*using|([a-zA-Z]+))\s*=\s*"((?:[^"\\]|\\.)*)"'
)
CONST_RE = re.compile(r'static\s+final\s+String\s+([A-Z0-9_]+)\s*=\s*"((?:[^"\\]|\\.)*)"')
# A field declaration like:  BaseWebElement setsTab;  /  TextElement totalPriceWidget;
FIELD_RE = re.compile(r"^\s*(?:public|protected|private|final|static|\s)*([A-Za-z0-9_<>,. ]+?)\s+([a-zA-Z_][A-Za-z0-9_]*)\s*;")

IGNORED_PARTS = {"target", "build", ".git", ".gradle", "node_modules"}

# Map declared field types -> a normalized element_type for the index.
TYPE_HINTS = [
    ("dropdown", "dropdown"),
    ("combobox", "dropdown"),
    ("checkbox", "checkbox"),
    ("datepicker", "date-picker"),
    ("datefield", "date-picker"),
    ("table", "table"),
    ("dialog", "dialog"),
    ("popup", "dialog"),
    ("button", "button"),
    ("input", "text-input"),
    ("field", "text-input"),
    ("text", "text"),
    ("tab", "tab"),
    ("menu", "menu"),
    ("link", "link"),
    ("tree", "tree"),
]


def java_files(root: Path) -> list[Path]:
    return [
        p
        for p in root.rglob("*.java")
        if not any(part in IGNORED_PARTS for part in p.parts)
    ]


def normalize_element_type(java_type: str, human_name: str) -> str:
    haystack = f"{java_type} {human_name}".lower()
    for needle, etype in TYPE_HINTS:
        if needle in haystack:
            return etype
    return "element"


def collect_constants(files: list[Path]) -> dict[str, str]:
    """Global map of `static final String NAME = "value"` for @ElementName(CONST)."""
    consts: dict[str, str] = {}
    for path in files:
        text = path.read_text(encoding="utf-8", errors="ignore")
        for name, value in CONST_RE.findall(text):
            consts.setdefault(name, value)
    return consts


def resolve_name(raw: str, consts: dict[str, str]) -> str | None:
    raw = raw.strip()
    if raw.startswith('"') and raw.endswith('"'):
        return raw[1:-1]
    # bare identifier or Class.CONST -> take the last segment and look it up
    key = raw.split(".")[-1]
    return consts.get(key)


def parse_file(path: Path, root: Path, consts: dict[str, str], app: str) -> list[dict]:
    text = path.read_text(encoding="utf-8", errors="ignore")
    page_match = WEBPAGE_RE.search(text)
    class_match = CLASS_RE.search(text)
    page = page_match.group(1) if page_match else None
    klass = class_match.group(1) if class_match else path.stem
    rel = path.relative_to(root)

    entries: list[dict] = []
    pending_name: str | None = None
    pending_by: str | None = None
    pending_locator: str | None = None
    pending_line: int | None = None

    for lineno, line in enumerate(text.splitlines(), start=1):
        m_name = ELEMENT_NAME_RE.search(line)
        if m_name:
            pending_name = resolve_name(m_name.group(1), consts)
            pending_line = lineno
            continue

        m_find = FINDBY_RE.search(line)
        if m_find:
            pending_by = (m_find.group(1) or m_find.group(2) or "").lower()
            pending_locator = m_find.group(3)
            if pending_line is None:
                pending_line = lineno
            continue

        # A field declaration closes the current annotation block.
        if pending_locator is not None or pending_name is not None:
            m_field = FIELD_RE.match(line)
            if m_field:
                java_type = m_field.group(1).strip().split()[-1].split("<")[0]
                field = m_field.group(2)
                name = pending_name or field
                entries.append(
                    {
                        "name": name,
                        "app": app,
                        "page": page,
                        "class": klass,
                        "field": field,
                        "element_type": normalize_element_type(java_type, name),
                        "java_type": java_type,
                        "by": pending_by or "xpath",
                        "locator": pending_locator,
                        "source_file": str(rel),
                        "source_line": pending_line or lineno,
                    }
                )
                pending_name = pending_by = pending_locator = pending_line = None

    return entries


def main() -> int:
    repo_root = Path(__file__).resolve().parents[3]
    parser = argparse.ArgumentParser(description=__doc__)
    parser.add_argument(
        "source",
        nargs="?",
        default=str(repo_root / "imported" / "twentyfive-regtest"),
        help="Automation source root to scan",
    )
    parser.add_argument(
        "--out",
        default=str(
            repo_root
            / "skills"
            / "selenium-automation"
            / "references"
            / "locator-index.generated.json"
        ),
        help="Output locator-index JSON path",
    )
    parser.add_argument("--app", default="twentyfive", help="App namespace for entries")
    args = parser.parse_args()

    source = Path(args.source).resolve()
    if not source.exists():
        parser.error(f"source root not found: {source}")

    files = java_files(source)
    consts = collect_constants(files)

    locators: list[dict] = []
    for path in files:
        locators.extend(parse_file(path, source, consts, args.app))

    # Stable ordering keeps the generated file diff-friendly.
    locators.sort(key=lambda e: (e["app"], e["page"] or "", e["class"], e["name"]))

    payload = {
        "app": args.app,
        "generated_from": str(source.relative_to(repo_root)) if source.is_relative_to(repo_root) else str(source),
        "generated_at": _dt.date.today().isoformat(),
        "count": len(locators),
        "pages": sorted({e["page"] for e in locators if e["page"]}),
        "locators": locators,
    }

    out = Path(args.out)
    out.parent.mkdir(parents=True, exist_ok=True)
    out.write_text(json.dumps(payload, indent=2, ensure_ascii=False) + "\n", encoding="utf-8")
    print(f"Wrote {out} ({len(locators)} locators across {len(payload['pages'])} pages)")
    return 0


if __name__ == "__main__":
    raise SystemExit(main())
