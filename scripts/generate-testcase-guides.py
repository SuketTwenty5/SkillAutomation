#!/usr/bin/env python3
"""Generate Markdown execution guides from the RTA-to-feature map."""

from __future__ import annotations

import re
import shutil
import unicodedata
from dataclasses import dataclass
from pathlib import Path
from urllib.parse import unquote


REPO_ROOT = Path(__file__).resolve().parents[1]
MAP_PATH = REPO_ROOT / "RTA_TEST_SUITE_FEATURE_MAP.md"
OUTPUT_DIR = REPO_ROOT / "test-case-guides"
DEFAULT_APP_URL = "https://approuter-twenty5ipe-dev.cfapps.us10.hana.ondemand.com/#quote"
LIFECYCLE_TAGS = {"@START", "@RUN", "@END"}


@dataclass
class FeatureMatch:
    path: str
    line: int
    feature_name: str = ""
    scenario_name: str = ""
    tags: list[str] | None = None
    block: list[str] | None = None
    sections: list[tuple[str, list[str]]] | None = None


@dataclass
class TestCase:
    test_id: str
    title: str
    page_url: str
    status: str
    notes: str = ""
    matches: list[FeatureMatch] | None = None
    filename: str = ""


def ascii_text(value: str) -> str:
    replacements = {
        "\u2018": "'",
        "\u2019": "'",
        "\u201c": '"',
        "\u201d": '"',
        "\u2013": "-",
        "\u2014": "-",
        "\u00a0": " ",
    }
    for src, dst in replacements.items():
        value = value.replace(src, dst)
    return unicodedata.normalize("NFKD", value).encode("ascii", "ignore").decode("ascii")


def markdown_escape(value: str) -> str:
    return ascii_text(value).replace("|", "\\|").strip()


def slugify(value: str) -> str:
    value = ascii_text(value).lower()
    value = re.sub(r"[^a-z0-9]+", "-", value)
    return value.strip("-") or "test-case"


def title_from_url(url: str, test_id: str) -> str:
    slug = unquote(url.rstrip("/").split("/")[-1]).replace("+", " ")
    slug = ascii_text(slug).strip()
    if test_id and test_id not in {"(no TC ID)", ""}:
        prefix = f"{test_id} "
        if slug.startswith(prefix):
            return slug[len(prefix):].strip()
        if test_id in slug:
            title = slug.split(test_id, 1)[1].strip(" -")
            if title:
                return title
    return slug


def page_id_from_url(url: str) -> str:
    match = re.search(r"/pages/(\d+)/", url)
    return match.group(1) if match else "page"


def parse_table_row(line: str) -> list[str] | None:
    if not line.startswith("|") or line.startswith("| ---"):
        return None
    cells = [cell.strip() for cell in line.strip().strip("|").split("|")]
    return cells if len(cells) == 3 else None


def parse_feature_matches(cell: str) -> list[FeatureMatch]:
    matches: list[FeatureMatch] = []
    for path, line in re.findall(r"`([^`]+):(\d+)`", cell):
        matches.append(FeatureMatch(path=path, line=int(line)))
    return matches


def parse_mapping() -> list[TestCase]:
    tests: list[TestCase] = []
    section = ""
    for raw_line in MAP_PATH.read_text(encoding="utf-8").splitlines():
        line = raw_line.strip()
        if line.startswith("## Matched Pages"):
            section = "matched"
            continue
        if line.startswith("## Not Found In Feature Files"):
            section = "not_found"
            continue
        if line.startswith("## Duplicate Match Notes"):
            section = ""
            continue

        cells = parse_table_row(line)
        if not cells or cells[0] == "Test ID":
            continue

        test_id, page_url, detail = cells
        title = title_from_url(page_url, test_id)
        if section == "matched":
            tests.append(
                TestCase(
                    test_id=ascii_text(test_id),
                    title=title,
                    page_url=page_url,
                    status="mapped",
                    matches=parse_feature_matches(detail),
                )
            )
        elif section == "not_found":
            tests.append(
                TestCase(
                    test_id=ascii_text(test_id),
                    title=title,
                    page_url=page_url,
                    status="not_automated",
                    notes=ascii_text(detail),
                    matches=[],
                )
            )

    assign_filenames(tests)
    return tests


def assign_filenames(tests: list[TestCase]) -> None:
    seen: set[str] = set()
    for test in tests:
        if test.test_id == "(no TC ID)":
            base = f"page-{page_id_from_url(test.page_url)}-{slugify(test.title)}"
        else:
            base = slugify(test.test_id)
        filename = f"{base}.md"
        if filename in seen:
            filename = f"{base}-{slugify(test.title)}.md"
        if filename in seen:
            filename = f"{base}-{page_id_from_url(test.page_url)}.md"
        seen.add(filename)
        test.filename = filename


def read_feature_match(match: FeatureMatch) -> FeatureMatch:
    feature_path = REPO_ROOT / match.path
    lines = feature_path.read_text(encoding="utf-8").splitlines()

    feature_name = ""
    for line in lines:
        feature_match = re.match(r"\s*Feature:\s*(.+)", line)
        if feature_match:
            feature_name = ascii_text(feature_match.group(1).strip())
            break

    scenario_index = max(match.line - 1, 0)
    scenario_line = lines[scenario_index]
    scenario_match = re.match(r"\s*Scenario(?: Outline)?:\s*(.+)", scenario_line)
    scenario_name = ascii_text(scenario_match.group(1).strip()) if scenario_match else ""

    tag_lines: list[str] = []
    scan = scenario_index - 1
    while scan >= 0:
        stripped = lines[scan].strip()
        if not stripped:
            scan -= 1
            continue
        if stripped.startswith("@"):
            tag_lines.insert(0, stripped)
            scan -= 1
            continue
        break
    tags = [tag for line in tag_lines for tag in line.split()]

    end = len(lines)
    for index in range(scenario_index + 1, len(lines)):
        if lines[index].strip().startswith("@") and next_nonblank_is_scenario(lines, index + 1):
            end = index
            break
        if re.match(r"\s*Scenario(?: Outline)?:", lines[index]):
            end = index
            break

    block = [ascii_text(line.rstrip()) for line in lines[scenario_index:end]]
    sections = extract_sections(block)

    match.feature_name = feature_name
    match.scenario_name = scenario_name
    match.tags = tags
    match.block = block
    match.sections = sections
    return match


def next_nonblank_is_scenario(lines: list[str], start: int) -> bool:
    for index in range(start, len(lines)):
        stripped = lines[index].strip()
        if not stripped:
            continue
        return bool(re.match(r"Scenario(?: Outline)?:", stripped))
    return False


def clean_step(line: str) -> str:
    stripped = line.strip()
    stripped = re.sub(r"^(Given|When|Then|And|But|\*)\s+", "", stripped)
    return ascii_text(stripped).strip()


def extract_sections(block: list[str]) -> list[tuple[str, list[str]]]:
    sections: list[tuple[str, list[str]]] = []
    current_title = "Setup"
    current_steps: list[str] = []

    for raw_line in block[1:]:
        line = raw_line.strip()
        if not line or line.startswith("#") or line.startswith("|") or line in {'"""', "'''"}:
            continue

        info_match = re.search(r"info:\s*---\s*(.*?)\s*---", line)
        if info_match:
            if current_steps:
                sections.append((current_title, current_steps))
            current_title = ascii_text(info_match.group(1).strip())
            current_steps = []
            continue

        if re.match(r"^(Given|When|Then|And|But|\*)\s+", line):
            step = clean_step(line)
            if step and not step.startswith("info:"):
                current_steps.append(step)

    if current_steps:
        sections.append((current_title, current_steps))
    return sections


def primary_tag(match: FeatureMatch) -> str:
    for tag in match.tags or []:
        if tag not in LIFECYCLE_TAGS:
            return tag
    return (match.tags or [""])[0]


def render_source_table(test: TestCase) -> list[str]:
    return [
        "| Field | Value |",
        "| --- | --- |",
        f"| Test ID | {markdown_escape(test.test_id)} |",
        f"| Title | {markdown_escape(test.title)} |",
        f"| RTA page | {test.page_url} |",
        f"| Map source | `RTA_TEST_SUITE_FEATURE_MAP.md` |",
    ]


def render_run_instructions(test: TestCase, default_match: FeatureMatch) -> list[str]:
    tag = primary_tag(default_match)
    lines = [
        "## Run Instructions",
        "",
        "When a consultant asks to run this test, use the default app URL unless they provide another URL:",
        "",
        f"```text",
        DEFAULT_APP_URL,
        "```",
        "",
        "For Claude Desktop, ask before launching Selenium Chrome with the default URL. If the user provides another URL, launch that URL instead.",
        "",
        "```bash",
        f'scripts/start-debug-chrome.sh "{DEFAULT_APP_URL}"',
        "scripts/run-twentyfive-test.sh " + tag,
        "```",
    ]
    if len(test.matches or []) > 1:
        lines.extend(
            [
                "",
                "Duplicate feature matches exist for this Confluence page. The tag command may run more than one matching scenario until the canonical feature file is confirmed.",
            ]
        )
    return lines


def render_feature_match(match: FeatureMatch, index: int, is_default: bool) -> list[str]:
    label = "Default mapped Selenium scenario" if is_default else f"Alternate mapped Selenium scenario {index}"
    tags = " ".join(match.tags or [])
    lines = [
        f"## {label}",
        "",
        "| Field | Value |",
        "| --- | --- |",
        f"| Feature | {markdown_escape(match.feature_name)} |",
        f"| Scenario | {markdown_escape(match.scenario_name)} |",
        f"| Tags | `{markdown_escape(tags)}` |",
        f"| File | `{match.path}:{match.line}` |",
        "",
    ]

    if match.sections:
        lines.extend(["### Scenario Sections", ""])
        for title, steps in match.sections:
            lines.append(f"#### {markdown_escape(title)}")
            lines.append("")
            for step in steps[:20]:
                lines.append(f"- {markdown_escape(step)}")
            if len(steps) > 20:
                lines.append(f"- ... {len(steps) - 20} more steps")
            lines.append("")

    lines.extend(
        [
            "### Gherkin Excerpt",
            "",
            "```gherkin",
            *(match.block or []),
            "```",
        ]
    )
    return lines


def render_not_automated(test: TestCase) -> list[str]:
    return [
        "## Automation Status",
        "",
        "This Confluence page does not have an exact local Cucumber scenario match in the current feature files.",
        "",
        f"Map note: {markdown_escape(test.notes)}",
        "",
        "## Next Automation Work",
        "",
        "- Confirm whether this page is a real executable test or a template/support page.",
        "- Add or identify a local `.feature` scenario with the exact test ID.",
        "- Regenerate these guides after updating `RTA_TEST_SUITE_FEATURE_MAP.md`.",
    ]


def render_test_case(test: TestCase) -> str:
    lines = [f"# {markdown_escape(test.test_id)} - {markdown_escape(test.title)}", ""]
    lines.extend(render_source_table(test))
    lines.append("")

    if test.status == "not_automated":
        lines.extend(render_not_automated(test))
    else:
        enriched_matches = [read_feature_match(match) for match in (test.matches or [])]
        test.matches = enriched_matches
        default_match = enriched_matches[0]
        lines.extend(render_run_instructions(test, default_match))
        lines.append("")
        for index, match in enumerate(enriched_matches, start=1):
            lines.extend(render_feature_match(match, index, index == 1))
            lines.append("")

    if lines[-1] != "":
        lines.append("")
    lines.extend(
        [
            "---",
            "",
            "_Generated by `scripts/generate-testcase-guides.py` from the RTA mapping and local feature files._",
            "",
        ]
    )
    return "\n".join(lines)


def render_index(tests: list[TestCase]) -> str:
    mapped = sum(1 for test in tests if test.status == "mapped")
    not_automated = len(tests) - mapped
    lines = [
        "# RTA Test Case Guides",
        "",
        "Generated from `RTA_TEST_SUITE_FEATURE_MAP.md` and the local Cucumber feature files.",
        "",
        f"- Total Confluence pages: {len(tests)}",
        f"- Automated mappings: {mapped}",
        f"- Not automated yet: {not_automated}",
        "",
        "| Test ID | Title | Status | Guide |",
        "| --- | --- | --- | --- |",
    ]
    for test in tests:
        status = "Mapped" if test.status == "mapped" else "Not automated"
        lines.append(
            f"| {markdown_escape(test.test_id)} | {markdown_escape(test.title)} | {status} | [{test.filename}]({test.filename}) |"
        )
    lines.append("")
    lines.append("_Regenerate with `python3 scripts/generate-testcase-guides.py`._")
    lines.append("")
    return "\n".join(lines)


def main() -> None:
    tests = parse_mapping()
    if OUTPUT_DIR.exists():
        for item in OUTPUT_DIR.iterdir():
            if item.is_file() and item.suffix == ".md":
                item.unlink()
            elif item.is_dir():
                shutil.rmtree(item)
    else:
        OUTPUT_DIR.mkdir(parents=True)

    for test in tests:
        (OUTPUT_DIR / test.filename).write_text(render_test_case(test), encoding="utf-8")
    (OUTPUT_DIR / "README.md").write_text(render_index(tests), encoding="utf-8")

    mapped = sum(1 for test in tests if test.status == "mapped")
    print(f"Generated {len(tests)} guides in {OUTPUT_DIR}")
    print(f"Mapped: {mapped}")
    print(f"Not automated: {len(tests) - mapped}")


if __name__ == "__main__":
    main()
