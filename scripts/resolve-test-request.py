#!/usr/bin/env python3
"""Resolve a consultant's test request to a known runner entry."""

from __future__ import annotations

import argparse
import json
import re
import shlex
import sys
import unicodedata
from pathlib import Path


REPO_ROOT = Path(__file__).resolve().parents[1]
REGISTRY_PATH = REPO_ROOT / "config" / "test-registry.json"
SUITES_PATH = REPO_ROOT / "config" / "test-suites.json"


def normalize(value: str) -> str:
    value = unicodedata.normalize("NFKD", value).encode("ascii", "ignore").decode("ascii")
    value = value.lower()
    value = value.replace("&", " and ")
    value = re.sub(r"[^a-z0-9]+", " ", value)
    return re.sub(r"\s+", " ", value).strip()


def load_json(path: Path) -> dict:
    with path.open(encoding="utf-8") as handle:
        return json.load(handle)


def all_names(entry: dict) -> list[str]:
    names = [entry.get("id", ""), entry.get("name", ""), entry.get("title", "")]
    names.extend(entry.get("aliases", []))
    return [name for name in names if name]


def score_entry(request_norm: str, entry: dict) -> int:
    best = 0
    for name in all_names(entry):
        name_norm = normalize(name)
        if not name_norm:
            continue
        if request_norm == name_norm:
            best = max(best, 1000)
        elif name_norm in request_norm:
            best = max(best, 800 + len(name_norm))
        elif request_norm in name_norm:
            best = max(best, 500 + len(request_norm))
        else:
            tokens = set(request_norm.split())
            name_tokens = set(name_norm.split())
            if name_tokens:
                overlap = len(tokens & name_tokens)
                best = max(best, overlap * 20)
    return best


def explicit_test_id(request: str) -> str | None:
    match = re.search(r"\bTC-[A-Za-z0-9.-]+(?:-[A-Za-z0-9.-]+)*\b", request, re.IGNORECASE)
    return match.group(0).upper() if match else None


def resolve(request: str) -> dict:
    registry = load_json(REGISTRY_PATH)
    suites = load_json(SUITES_PATH)
    request_norm = normalize(request)
    requested_id = explicit_test_id(request)

    if requested_id:
        for test in registry["tests"]:
            if test["id"].upper() == requested_id:
                return {"kind": "test", **test}

    candidates: list[tuple[int, str, dict]] = []
    for test in registry["tests"]:
        candidates.append((score_entry(request_norm, test), "test", test))
    for suite in suites["suites"]:
        candidates.append((score_entry(request_norm, suite), "suite", suite))

    candidates.sort(key=lambda item: item[0], reverse=True)
    if not candidates or candidates[0][0] < 120:
        raise SystemExit(f"No mapped test or suite found for request: {request}")

    score, kind, entry = candidates[0]
    return {"kind": kind, **entry}


def shell_list(name: str, values: list[str]) -> str:
    if not values:
        return f"{name}=()"
    return f"{name}=({ ' '.join(shlex.quote(value) for value in values) })"


def print_shell(resolved: dict) -> None:
    pairs = {
        "RESOLVED_KIND": resolved["kind"],
        "RESOLVED_RUNNER": resolved["runner"],
        "RESOLVED_ID": resolved.get("id", resolved.get("name", "")),
        "RESOLVED_NAME": resolved.get("name", resolved.get("title", "")),
        "RESOLVED_TITLE": resolved.get("title", resolved.get("name", "")),
        "RESOLVED_DEFAULT_URL": resolved.get("defaultUrl", ""),
        "RESOLVED_FEATURE": resolved.get("feature", ""),
        "RESOLVED_GUIDE": resolved.get("guide", ""),
    }
    for key, value in pairs.items():
        print(f"{key}={shlex.quote(str(value))}")
    print(shell_list("RESOLVED_TAGS", resolved.get("tags", [])))
    print(shell_list("RESOLVED_ARGS", resolved.get("args", [])))

    required_files = resolved.get("requiredFiles", [])
    print(f"RESOLVED_REQUIRED_FILE_COUNT={len(required_files)}")
    for index, item in enumerate(required_files):
        print(f"RESOLVED_REQUIRED_FILE_{index}_NAME={shlex.quote(item.get('name', 'required file'))}")
        print(f"RESOLVED_REQUIRED_FILE_{index}_ENV={shlex.quote(item.get('env', ''))}")
        print(f"RESOLVED_REQUIRED_FILE_{index}_DEFAULT={shlex.quote(item.get('defaultPath', ''))}")


def print_list() -> None:
    registry = load_json(REGISTRY_PATH)
    suites = load_json(SUITES_PATH)
    print("Mapped tests:")
    for test in registry["tests"]:
        print(f"  {test['id']} - {test['title']}")
    print()
    print("Mapped suites:")
    for suite in suites["suites"]:
        print(f"  {suite['name']}")


def main(argv: list[str]) -> int:
    parser = argparse.ArgumentParser(description=__doc__)
    parser.add_argument("request", nargs="*", help="Consultant request text, e.g. run TC-Prof-Services-001")
    parser.add_argument("--shell", action="store_true", help="Print shell assignments for scripts.")
    parser.add_argument("--list", action="store_true", help="List mapped tests and suites.")
    args = parser.parse_args(argv)

    if args.list:
        print_list()
        return 0

    request = " ".join(args.request).strip()
    if not request:
        parser.error("request text is required unless --list is used")

    resolved = resolve(request)
    if args.shell:
        print_shell(resolved)
    else:
        print(json.dumps(resolved, indent=2))
    return 0


if __name__ == "__main__":
    raise SystemExit(main(sys.argv[1:]))
