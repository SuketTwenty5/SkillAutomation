# Consultant Setup

Use this when the consultant does not already have the Selenium project.

## What You Give Them

Give them a private runner workspace, not only a `SKILL.md`:

```text
twentyfive-ai-test-runner/
  skills/selenium-automation/
  imported/twentyfive-regtest/
```

This can be a private GitHub repo, Bitbucket repo, or zip. Do not publish proprietary Selenium code in a public skills repo.

## Consultant Steps

1. Install Java 17+, Maven, Chrome, and Codex/Claude Code.
2. Clone or unzip the runner workspace.
3. Open the runner workspace in Codex/Claude Code.
4. Install the skill locally if needed:

```bash
mkdir -p ~/.codex/skills
cp -R skills/selenium-automation ~/.codex/skills/
```

5. Restart the agent.
6. Start Chrome with remote debugging:

```bash
/Applications/Google\ Chrome.app/Contents/MacOS/Google\ Chrome \
  --remote-debugging-port=9222 \
  --user-data-dir="$HOME/.selenium-ai-chrome"
```

7. Log in manually to the application in that Chrome.
8. Paste the test request:

```text
Use $selenium-automation.
Run this Confluence/manual test in my local Chrome.
Use the bundled Twenty5 automation code first.

<paste test case>
```

## One-command macOS setup

Consultants on macOS can use the bootstrap script:

```bash
/bin/bash -c "$(curl -fsSL https://raw.githubusercontent.com/SuketTwenty5/SkillAutomation/main/scripts/macos-consultant-setup.sh)"
```

The script checks or installs:

- Xcode Command Line Tools
- Homebrew
- Git
- Java 17
- Maven
- Node/npm
- Google Chrome
- Claude Code

It then asks which workspace path to use, asks whether to open Claude Code or Codex, clones/updates this repo, installs the Codex skill copy, asks the consultant to choose a customer base URL, starts Chrome with remote debugging on port `9222`, opens the selected URL, and launches the selected AI agent in the workspace.

Override defaults like this:

```bash
WORKSPACE_DIR="$HOME/Work/SkillAutomation" \
AI_AGENT=codex \
/bin/bash -c "$(curl -fsSL https://raw.githubusercontent.com/SuketTwenty5/SkillAutomation/main/scripts/macos-consultant-setup.sh)"
```

Agent options:

```bash
AI_AGENT=claude   # open Claude Code
AI_AGENT=codex    # open Codex if installed
AI_AGENT=none     # do not open an AI agent
```

For backward compatibility, `OPEN_CLAUDE=0` still skips agent opening:

```bash
OPEN_CLAUDE=0 \
/bin/bash -c "$(curl -fsSL https://raw.githubusercontent.com/SuketTwenty5/SkillAutomation/main/scripts/macos-consultant-setup.sh)"
```

Skip the menu and open one URL directly:

```bash
APP_URL="https://app-twenty5ipe-lm-dev.cfapps.us10.hana.ondemand.com" \
/bin/bash -c "$(curl -fsSL https://raw.githubusercontent.com/SuketTwenty5/SkillAutomation/main/scripts/macos-consultant-setup.sh)"
```

Choose by number without prompting:

```bash
APP_CHOICE=18 \
/bin/bash -c "$(curl -fsSL https://raw.githubusercontent.com/SuketTwenty5/SkillAutomation/main/scripts/macos-consultant-setup.sh)"
```

Skip opening an app URL:

```bash
SKIP_APP_URL_PROMPT=1 \
/bin/bash -c "$(curl -fsSL https://raw.githubusercontent.com/SuketTwenty5/SkillAutomation/main/scripts/macos-consultant-setup.sh)"
```

Second run behavior:

- Already-installed tools are detected and skipped.
- Existing workspace is updated with `git pull --ff-only`.
- Existing skill copy is refreshed from the workspace.
- Existing Chrome debug session is reused when port `9222` is already open.
- The selected app URL is saved to `.skillautomation.env` for later test runs.

## Smooth test execution

After setup and login, use the wrapper script instead of asking the agent to rediscover Maven flags:

```bash
cd ~/SkillAutomation
scripts/run-twentyfive-test.sh @TC-001
```

Or specify the URL and tag explicitly:

```bash
APP_URL="https://approuter-twenty5ipe-dev.cfapps.us10.hana.ondemand.com/#quote" \
CUCUMBER_TAGS="@TC-001" \
scripts/run-twentyfive-test.sh
```

The wrapper:

- reads `.skillautomation.env` from setup
- starts or reuses Chrome debug port `9222`
- runs the correct Maven reactor command
- includes `-Dsurefire.failIfNoSpecifiedTests=false`
- attaches to the existing consultant Chrome when `USE_DEBUG_CHROME=true`

If Codex asks whether to run Maven outside the sandbox, choose **Yes** and allow the same `mvn -pl tests -am test` prefix for future runs.
If Codex asks whether to open Chrome, choose **Yes** once; after Chrome is listening on `9222`, later runs reuse it.

To also try the `npx skills add` installer:

```bash
RUN_NPX_SKILLS=1 \
/bin/bash -c "$(curl -fsSL https://raw.githubusercontent.com/SuketTwenty5/SkillAutomation/main/scripts/macos-consultant-setup.sh)"
```

## Best Packaging Options

Option A: Private runner repo.
Best when consultants are allowed to receive source code.

Option B: Private Maven artifact plus thin runner.
Best when you do not want to expose full Selenium source. Package automation code as a private dependency and give consultants only a runner module.

Option C: Central execution service.
Best when consultants should not receive automation code, but then tests run in your controlled browser/session rather than their own Chrome.
