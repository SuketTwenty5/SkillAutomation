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

It then clones this repo to `~/SkillAutomation`, installs the Codex skill copy, starts Chrome with remote debugging on port `9222`, and launches `claude` in the workspace.

Override defaults like this:

```bash
WORKSPACE_DIR="$HOME/Work/SkillAutomation" \
OPEN_CLAUDE=0 \
/bin/bash -c "$(curl -fsSL https://raw.githubusercontent.com/SuketTwenty5/SkillAutomation/main/scripts/macos-consultant-setup.sh)"
```

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
