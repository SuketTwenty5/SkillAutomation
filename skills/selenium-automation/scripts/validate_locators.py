#!/usr/bin/env python3
"""Static (browser-free) validation of the generated locator index.

This is the fast "fail before you run" check from the architecture proposal,
implemented without a live browser: it confirms every locator in the index is
still backed by real source code (the @FindBy string still exists in the file it
was extracted from). A live-DOM validator is the heavier follow-on; this catches
the common case where a page object was edited and the index went stale.

Exit code is non-zero if any locator is stale, so it can gate a run in scripts.

Usage:
    validate_locators.py [--index PATH] [--source-root PATH] [--quiet]
"""

from __future__ import annotations

import argparse
import json
import sys
from pathlib import Path


def main() -> int:
    repo_root = Path(__file__).resolve().parents[3]
    parser = argparse.ArgumentParser(description=__doc__)
    parser.add_argument(
        "--index",
        default=str(
            repo_root
            / "skills"
            / "selenium-automation"
            / "references"
            / "locator-index.generated.json"
        ),
    )
    parser.add_argument("--source-root", default=str(repo_root / "imported" / "twentyfive-regtest"))
    parser.add_argument("--quiet", action="store_true", help="Only print the summary + failures")
    args = parser.parse_args()

    index_path = Path(args.index)
    if not index_path.exists():
        raise SystemExit(
            f"locator index not found: {index_path}\n"
            f"Run: python3 skills/selenium-automation/scripts/build_locator_index.py"
        )

    source_root = Path(args.source_root)
    payload = json.loads(index_path.read_text(encoding="utf-8"))
    locators = payload.get("locators", [])

    # Cache file contents so we read each source file at most once.
    file_cache: dict[str, str] = {}
    stale: list[dict] = []
    missing_file: list[dict] = []
    ok = 0

    for loc in locators:
        src = source_root / loc["source_file"]
        key = str(src)
        if key not in file_cache:
            try:
                file_cache[key] = src.read_text(encoding="utf-8", errors="ignore")
            except FileNotFoundError:
                file_cache[key] = None  # type: ignore[assignment]
        text = file_cache[key]
        if text is None:
            missing_file.append(loc)
            continue
        if loc["locator"] and loc["locator"] in text:
            ok += 1
            if not args.quiet:
                print(f"  OK   {loc['name']}  <-  {loc['source_file']}")
        else:
            stale.append(loc)

    print()
    print(f"[validate_locators] {ok} ok / {len(stale)} stale / {len(missing_file)} missing-file "
          f"(of {len(locators)})")
    for loc in stale:
        print(f"  STALE  '{loc['name']}' expected in {loc['source_file']} (locator no longer present)",
              file=sys.stderr)
    for loc in missing_file:
        print(f"  GONE   source file removed: {loc['source_file']} ('{loc['name']}')",
              file=sys.stderr)

    return 0 if not stale and not missing_file else 1


if __name__ == "__main__":
    raise SystemExit(main())
