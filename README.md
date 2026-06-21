# MindRally Selenium Automation Skill

This repo packages a consultant-ready AI + Selenium automation workspace for converting Confluence/manual QA test cases into executable Selenium or Selenide runs in the consultant's own Chrome browser.

Consultants do not need to already own the Selenium project. They need a runner workspace from you that contains:

- the `selenium-automation` skill
- the required Selenium/Selenide automation code or private dependency
- Maven/Gradle runner configuration
- any allowed action catalog/examples

In this repo, `imported/twentyfive-regtest` is the bundled Twenty5 automation reference/runner copy.

## Skill Install Only

Use the skills installer command from the consultant machine:

```bash
npx skills add https://github.com/mindrally/skills --skill selenium-automation
```

If the installer expects a folder path instead of skill discovery, point it to:

```text
skills/selenium-automation
```

Skill-only install is useful only when the consultant already has an automation project or runner workspace. For consultants without your Selenium project, give them this whole runner workspace or a private packaged equivalent.

## Recommended Consultant Model

Give consultants one private repo or zip:

```text
twentyfive-ai-test-runner/
  README.md
  skills/selenium-automation/
  imported/twentyfive-regtest/
```

They open that folder in Codex/Claude Code, not an empty workspace.

## Consultant Prerequisites

- Java 17+
- Maven or Gradle, depending on the project
- Chrome installed locally
- ChromeDriver available through Selenium Manager or project dependency setup
- Access to the target application and Confluence/test content
- Codex, Claude Code, Claude for Desktop, or another local agent that can read files and run shell commands
- This runner workspace, unless they already have the Selenium project

## One-command macOS setup

```bash
/bin/bash -c "$(curl -fsSL https://raw.githubusercontent.com/SuketTwenty5/SkillAutomation/main/scripts/macos-consultant-setup.sh)"
```

This checks/installs prerequisites, asks which workspace path to use, asks whether to open Claude Code, Claude for Desktop, or Codex, clones/updates the workspace, installs the skill copy for Codex, and opens the selected AI agent in the workspace. For Codex and Claude Code it also starts Chrome with remote debugging and opens the default BTP Golden URL. For Claude Desktop, Chrome is launched later when the user asks to run a test.

Useful overrides:

```bash
WORKSPACE_DIR="$HOME/Work/SkillAutomation" AI_AGENT=codex /bin/bash -c "$(curl -fsSL https://raw.githubusercontent.com/SuketTwenty5/SkillAutomation/main/scripts/macos-consultant-setup.sh)"
```

For consultants who already use Claude for Desktop:

```bash
AI_AGENT=claude-desktop /bin/bash -c "$(curl -fsSL https://raw.githubusercontent.com/SuketTwenty5/SkillAutomation/main/scripts/macos-consultant-setup.sh)"
```

This also creates `.skillautomation/CLAUDE_DESKTOP_RUN_TEST.md` in the workspace and copies a starter prompt to the clipboard. Claude Desktop should ask before launching Selenium Chrome with the default URL, or use an alternate URL provided by the user.

Override the default app URL:

```bash
APP_URL="https://app-twenty5ipe-lm-dev.cfapps.us10.hana.ondemand.com" /bin/bash -c "$(curl -fsSL https://raw.githubusercontent.com/SuketTwenty5/SkillAutomation/main/scripts/macos-consultant-setup.sh)"
```

Skip opening an AI agent:

```bash
AI_AGENT=none /bin/bash -c "$(curl -fsSL https://raw.githubusercontent.com/SuketTwenty5/SkillAutomation/main/scripts/macos-consultant-setup.sh)"
```

On second run, installed tools are skipped, the existing workspace is pulled, the skill copy is refreshed, and an existing Chrome debug session is reused.

## Run a Twenty5 test

After setup and manual login in Chrome:

```bash
cd ~/SkillAutomation
scripts/run-twentyfive-test.sh @TC-001
```

To run a named suite, use the suite name directly:

```bash
scripts/run-twentyfive-test.sh --suite "Manufacturing Proposal"
```

Available suite mappings are in `TEST_SUITES.md`; list them from the runner with:

```bash
scripts/run-twentyfive-test.sh --list-suites
```

The runner defaults to `https://approuter-twenty5ipe-dev.cfapps.us10.hana.ondemand.com/#quote`. You can override it:

```bash
APP_URL="https://approuter-twenty5ipe-dev.cfapps.us10.hana.ondemand.com/#quote" \
CUCUMBER_TAGS="@TC-001" \
scripts/run-twentyfive-test.sh
```

The setup script starts the dedicated Chrome debug profile on port `9222` before opening Codex or Claude Code. For Claude Desktop, launch Chrome when the user asks to run a test by asking whether to use the default URL, then running `scripts/start-debug-chrome.sh "<url>"`. The runner then attaches Selenium to that browser. For Claude Code and normal Terminal runs, the runner can also auto-launch that browser if it was closed. For Codex, restart the dedicated browser from Terminal before running tests if you closed it:

```bash
scripts/start-debug-chrome.sh "https://approuter-twenty5ipe-dev.cfapps.us10.hana.ondemand.com/#quote"
```

This avoids Codex GUI-launch approval prompts during the test run while keeping Claude users on the auto-launch path.

## RTA Test Case Guides

The scoped RTA Confluence test cases are generated as Markdown guides under:

```text
test-case-guides/README.md
```

Each mapped guide includes the RTA page link, local feature/scenario, Cucumber tags, run command, section summary, and Gherkin excerpt. Pages without an exact local feature match are still listed and marked as not automated yet.

Regenerate the guide set after updating `RTA_TEST_SUITE_FEATURE_MAP.md`:

```bash
python3 scripts/generate-testcase-guides.py
```

## Important: Test Runner Behavior

**When working with Claude agents (Codex, Claude Code, Claude Desktop):**

- **Always ask the consultant for explicit confirmation before restarting or rerunning a test suite**, even if the consultant repeats a command like "run Manufacturing Proposal."
- Do not automatically assume a repeated command means to restart a stopped test.
- This prevents unnecessary reruns and respects the consultant's time and intent.

## Standard Use

1. Open this runner workspace in the local AI coding agent.
2. Install/copy the skill if the agent does not auto-detect local skills.
3. Restart the AI coding agent so it discovers the skill.
4. Open a dedicated Chrome session:

```bash
/Applications/Google\ Chrome.app/Contents/MacOS/Google\ Chrome \
  --remote-debugging-port=9222 \
  --user-data-dir="$HOME/.selenium-ai-chrome"
```

5. Log in to the application manually in that Chrome window.
6. Ask the agent:

```text
Use the selenium-automation skill.
Run this Confluence/manual test case in my local Chrome:
<paste test case>
```

The agent should extract the test, map steps to existing Selenium/Selenide code, generate minimal glue code, run the test, and return evidence.

## What This Does Not Do

The skill does not magically give a cloud chatbot control over Chrome. Execution must happen in a local agent/runtime on the consultant machine, such as Codex desktop/CLI, Claude Code, or Claude for Desktop.
