#!/usr/bin/env python3
"""Generate a detailed Markdown test report from a Cucumber JSON file."""

import json
import sys
import os
from datetime import datetime, timezone


CUCUMBER_JSON = os.path.join(
    os.path.dirname(__file__),
    "../imported/twentyfive-regtest/tests/target/cucumber-reports/Cucumber.json",
)
REPORTS_DIR = os.path.join(os.path.dirname(__file__), "../reports")

STATUS_ICON = {"passed": "✅", "failed": "❌", "skipped": "⏭️", "pending": "⏳"}


def ns_to_ms(ns):
    return ns / 1_000_000


def ns_to_s(ns):
    return ns / 1_000_000_000


def format_duration(total_ms):
    if total_ms < 1000:
        return f"{total_ms:.0f}ms"
    elif total_ms < 60_000:
        return f"{total_ms/1000:.1f}s"
    else:
        m = int(total_ms // 60_000)
        s = (total_ms % 60_000) / 1000
        return f"{m}m {s:.0f}s"


def parse_timestamp(ts_str):
    """Parse ISO timestamp string to a readable local-style string."""
    try:
        dt = datetime.fromisoformat(ts_str.replace("Z", "+00:00"))
        local_dt = dt.astimezone()
        return local_dt.strftime("%Y-%m-%d %H:%M:%S %Z")
    except Exception:
        return ts_str


def group_steps_by_section(steps):
    """Group steps under their nearest preceding info: section header."""
    sections = []
    current_section = {"title": "Setup", "steps": []}
    for step in steps:
        name = step["name"]
        if name.startswith("info: ---") and name.endswith("---"):
            if current_section["steps"]:
                sections.append(current_section)
            title = name.replace("info: ---", "").replace("---", "").strip()
            current_section = {"title": title, "steps": []}
        else:
            current_section["steps"].append(step)
    if current_section["steps"]:
        sections.append(current_section)
    return sections


def render_step_row(i, step):
    status = step["result"]["status"]
    icon = STATUS_ICON.get(status, "❓")
    dur = format_duration(ns_to_ms(step["result"]["duration"]))
    keyword = step["keyword"].strip()
    name = step["name"]
    error = step["result"].get("error_message", "")
    row = f"| {i} | {icon} | `{keyword}` {name} | {dur} |"
    if error:
        row += f"\n|   | | ⚠️ `{error[:200]}` | |"
    return row


def generate_report(cucumber_json_path):
    with open(cucumber_json_path) as f:
        data = json.load(f)

    lines = []

    for feature in data:
        feature_name = feature.get("name", "Unknown Feature")

        for scenario in feature.get("elements", []):
            scenario_name = scenario.get("name", "Unknown Scenario")
            start_ts = parse_timestamp(scenario.get("start_timestamp", ""))
            tags = [t["name"] for t in scenario.get("tags", [])]
            steps = scenario.get("steps", [])

            # Compute totals
            statuses = [s["result"]["status"] for s in steps]
            total_duration_ms = sum(ns_to_ms(s["result"]["duration"]) for s in steps)
            passed = statuses.count("passed")
            failed = statuses.count("failed")
            skipped = statuses.count("skipped")
            overall = "PASSED" if failed == 0 and skipped == 0 else "FAILED"
            overall_icon = "✅" if overall == "PASSED" else "❌"

            # Header
            lines.append(f"# Test Report — {scenario_name}")
            lines.append("")
            lines.append(f"| | |")
            lines.append(f"|---|---|")
            lines.append(f"| **Status** | {overall_icon} {overall} |")
            lines.append(f"| **Feature** | {feature_name} |")
            lines.append(f"| **Tags** | {' '.join(tags)} |")
            lines.append(f"| **Started** | {start_ts} |")
            lines.append(f"| **Duration** | {format_duration(total_duration_ms)} |")
            lines.append(f"| **Steps** | {passed} passed / {failed} failed / {skipped} skipped / {len(steps)} total |")
            lines.append("")

            # Section-by-section breakdown
            lines.append("## Step-by-Step Results")
            lines.append("")
            sections = group_steps_by_section(steps)
            step_counter = 0

            for section in sections:
                lines.append(f"### {section['title']}")
                lines.append("")
                lines.append("| # | Status | Step | Duration |")
                lines.append("|---|--------|------|----------|")
                for step in section["steps"]:
                    step_counter += 1
                    lines.append(render_step_row(step_counter, step))
                lines.append("")

            # Failures detail
            failures = [(i + 1, s) for i, s in enumerate(steps) if s["result"].get("error_message")]
            if failures:
                lines.append("## Failures")
                lines.append("")
                for num, step in failures:
                    lines.append(f"### Step {num}: {step['keyword'].strip()} {step['name']}")
                    lines.append("")
                    lines.append("```")
                    lines.append(step["result"]["error_message"])
                    lines.append("```")
                    lines.append("")

            # Console errors from after hook
            after_hooks = scenario.get("after", [])
            console_errors = []
            for hook in after_hooks:
                output = hook.get("output", [])
                for line in output:
                    if "[SEVERE]" in line:
                        console_errors.append(line.strip())

            if console_errors:
                lines.append("## Browser Console Errors")
                lines.append("")
                for err in console_errors[:10]:
                    lines.append(f"- `{err}`")
                if len(console_errors) > 10:
                    lines.append(f"- _...and {len(console_errors) - 10} more_")
                lines.append("")

            # Screenshots
            screenshot_dir = os.path.join(
                os.path.dirname(cucumber_json_path),
                "../../previousScreenshot",
            )
            screenshots = []
            if os.path.isdir(screenshot_dir):
                prefix = scenario_name.replace(" ", "_").replace('"', '"').replace('"', '"')
                screenshots = sorted(
                    f for f in os.listdir(screenshot_dir)
                    if f.endswith(".png") and "Proposal_Setup" in f
                )

            lines.append(f"## Screenshots")
            lines.append("")
            if screenshots:
                lines.append(f"{len(screenshots)} screenshots captured during this run.")
                lines.append("")
                lines.append(f"Location: `imported/twentyfive-regtest/tests/previousScreenshot/`")
            else:
                lines.append("No screenshots found.")
            lines.append("")

            lines.append("---")
            lines.append(f"_Generated at {datetime.now().strftime('%Y-%m-%d %H:%M:%S')}_")

    return "\n".join(lines)


def print_summary(data):
    """Print a short terminal summary — the only thing that goes to stdout."""
    for feature in data:
        for scenario in feature.get("elements", []):
            steps = scenario.get("steps", [])
            statuses = [s["result"]["status"] for s in steps]
            passed = statuses.count("passed")
            failed = statuses.count("failed")
            skipped = statuses.count("skipped")
            overall = "PASSED" if failed == 0 and skipped == 0 else "FAILED"
            icon = "✅" if overall == "PASSED" else "❌"
            total_ms = sum(ns_to_ms(s["result"]["duration"]) for s in steps)

            print(f"\n{icon} {overall}  —  {scenario['name']}")
            print(f"   Steps : {passed} passed / {failed} failed / {skipped} skipped / {len(steps)} total")
            print(f"   Time  : {format_duration(total_ms)}")

            if failed > 0:
                print("\n   Failed steps:")
                for i, s in enumerate(steps, 1):
                    if s["result"]["status"] == "failed":
                        print(f"     [{i}] {s['keyword'].strip()} {s['name']}")
                        err = s["result"].get("error_message", "")
                        if err:
                            print(f"          {err.splitlines()[0][:120]}")


def main():
    json_path = sys.argv[1] if len(sys.argv) > 1 else CUCUMBER_JSON

    if not os.path.exists(json_path):
        print(f"ERROR: Cucumber JSON not found: {json_path}", file=sys.stderr)
        sys.exit(1)

    os.makedirs(REPORTS_DIR, exist_ok=True)

    with open(json_path) as f:
        data = json.load(f)

    # Write full report to file (not stdout)
    report = generate_report(json_path)
    timestamp = datetime.now().strftime("%Y%m%d_%H%M%S")
    report_path = os.path.join(REPORTS_DIR, f"report_{timestamp}.md")
    with open(report_path, "w") as f:
        f.write(report)
    latest_path = os.path.join(REPORTS_DIR, "latest.md")
    with open(latest_path, "w") as f:
        f.write(report)

    # Print only the short summary to stdout
    print_summary(data)
    print(f"\n   Full report : reports/report_{timestamp}.md")


if __name__ == "__main__":
    main()
