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
- Codex, Claude Code, or another local agent that can read files and run shell commands
- This runner workspace, unless they already have the Selenium project

## One-command macOS setup

```bash
/bin/bash -c "$(curl -fsSL https://raw.githubusercontent.com/SuketTwenty5/SkillAutomation/main/scripts/macos-consultant-setup.sh)"
```

This checks/installs prerequisites, asks which workspace path to use, asks whether to open Claude Code or Codex, clones/updates the workspace, installs the skill copy for Codex, asks the consultant to choose a customer base URL, starts Chrome with remote debugging, opens that URL, and opens the selected AI agent in the workspace.

Useful overrides:

```bash
WORKSPACE_DIR="$HOME/Work/SkillAutomation" AI_AGENT=codex /bin/bash -c "$(curl -fsSL https://raw.githubusercontent.com/SuketTwenty5/SkillAutomation/main/scripts/macos-consultant-setup.sh)"
```

Open a specific app URL without the menu:

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

The setup script saves the selected app URL in `.skillautomation.env`, so the runner can reuse it. You can override it:

```bash
APP_URL="https://approuter-twenty5ipe-dev.cfapps.us10.hana.ondemand.com/#quote" \
CUCUMBER_TAGS="@TC-001" \
scripts/run-twentyfive-test.sh
```

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

The skill does not magically give a cloud chatbot control over Chrome. Execution must happen in a local agent/runtime on the consultant machine, such as Codex desktop/CLI or Claude Code.
