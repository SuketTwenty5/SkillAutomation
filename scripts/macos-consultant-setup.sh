#!/usr/bin/env bash
set -euo pipefail

REPO_URL="${REPO_URL:-https://github.com/SuketTwenty5/SkillAutomation.git}"
WORKSPACE_DIR_FROM_ENV="${WORKSPACE_DIR+x}"
WORKSPACE_DIR="${WORKSPACE_DIR:-$HOME/SkillAutomation}"
SKILL_NAME="${SKILL_NAME:-selenium-automation}"
CHROME_PROFILE="${CHROME_PROFILE:-$HOME/.selenium-ai-chrome}"
CHROME_DEBUG_PORT="${CHROME_DEBUG_PORT:-9222}"
CHROME_BIN="${CHROME_BIN:-/Applications/Google Chrome.app/Contents/MacOS/Google Chrome}"
OPEN_CLAUDE="${OPEN_CLAUDE:-1}"
OPEN_AGENT="${OPEN_AGENT:-$OPEN_CLAUDE}"
AI_AGENT="${AI_AGENT:-}"
RUN_NPX_SKILLS="${RUN_NPX_SKILLS:-0}"
APP_URL="${APP_URL:-}"
APP_CHOICE="${APP_CHOICE:-}"
SKIP_APP_URL_PROMPT="${SKIP_APP_URL_PROMPT:-0}"
DEFAULT_APP_LABEL="Twenty5 Internal (BTP Golden)"
DEFAULT_APP_URL="https://approuter-twenty5ipe-dev.cfapps.us10.hana.ondemand.com/#quote"
SELECTED_APP_LABEL=""
SELECTED_APP_URL=""
SELECTED_AI_AGENT=""
WORKSPACE_ENV_FILE=""
CLAUDE_DESKTOP_PROMPT_FILE=""

APP_LABELS=(
  "Twenty5 Internal (BTP Golden)"
  "AI-MFG-DEMO"
  "HPSI"
  "ZS DEV"
  "ZS QA"
  "RTX SBX"
  "Gainwell DEV"
  "Gainwell NONPROD"
  "Gainwell QA"
  "KPMG DEV (decommed)"
  "KPMG UK QA (decommed)"
  "KPMG UK UAT (decommed)"
  "KPMG SBX24"
  "Accenture DEV"
  "SSTL DEV"
  "SSTL QA"
  "Tetra Pak PoV SBX"
  "Lockheed Martin DEV"
  "MBDA POC"
  "BearingPoint POC"
  "BearingPoint DEV"
  "BearingPoint QA"
  "BearingPoint PROD"
  "P&G PROD quotes"
  "P&G DEV23"
  "P&G QA quotes"
  "AIQUILLA"
  "Internal system quotes"
  "Salesforce Dev Environment"
)

APP_URLS=(
  "https://approuter-twenty5ipe-dev.cfapps.us10.hana.ondemand.com"
  "https://app-twenty5ipe-ai-mfg-demo.cfapps.us20.hana.ondemand.com"
  "https://app-twenty5ipe-hpsi-dev.cfapps.us21.hana.ondemand.com"
  "https://app-twenty5ipe-zs-dev.cfapps.us10.hana.ondemand.com"
  "https://app-twenty5ipe-zs-qa.cfapps.us10.hana.ondemand.com"
  "https://app-twenty5ipe-rtx-sbx.cfapps.us21.hana.ondemand.com/"
  "https://app-twenty5ipe-gwt-dev.cfapps.us10.hana.ondemand.com"
  "https://app-twenty5ipe-gwt-nonprod.cfapps.us10.hana.ondemand.com/"
  "https://app-twenty5ipe-gwt-qa.cfapps.us10.hana.ondemand.com"
  "https://app-twenty5ipe-kpmg-dev.cfapps.eu20.hana.ondemand.com"
  "https://app-twenty5ipe-kpmg-uk-qa.cfapps.eu20.hana.ondemand.com"
  "https://app-twenty5ipe-kpmg-uk-uat.cfapps.eu20.hana.ondemand.com"
  "https://app-twenty5ipe-sbx24.cfapps.eu20.hana.ondemand.com"
  "https://app-twenty5ipe-accenture-dev.cfapps.us21.hana.ondemand.com"
  "https://app-twenty5ipe-sstl-dev.cfapps.eu10-004.hana.ondemand.com/"
  "https://app-twenty5ipe-sstl-qa.cfapps.eu10-004.hana.ondemand.com"
  "https://app-twenty5ipe-tetra-pak-pov-sbx.cfapps.eu20.hana.ondemand.com/"
  "https://app-twenty5ipe-lm-dev.cfapps.us10.hana.ondemand.com"
  "https://app-twenty5ipe-mbda-poc.cfapps.eu20-001.hana.ondemand.com"
  "https://app-twenty5ipe-bp-poc.cfapps.eu10-004.hana.ondemand.com"
  "https://app-twenty5ipe-bp-dev.cfapps.eu10-004.hana.ondemand.com"
  "https://app-twenty5ipe-be-qa.cfapps.eu10-004.hana.ondemand.com"
  "https://app-twenty5ipe-be-prod.cfapps.eu10-004.hana.ondemand.com"
  "https://approuter-twenty5ipe.cfapps.us20.hana.ondemand.com/#quotes"
  "https://app-twenty5ipe-pg-dev23.cfapps.us20.hana.ondemand.com"
  "https://approuter-twenty5ipe-qa.cfapps.us20.hana.ondemand.com/#quotes"
  "https://app-twenty5ipe-aiquilla.cfapps.us21.hana.ondemand.com"
  "https://approuter-twenty5ipe-internal-quotes.cfapps.us10.hana.ondemand.com/"
  "https://developer.salesforce.com"
)

log() {
  printf '\n==> %s\n' "$*"
}

warn() {
  printf '\nWARN: %s\n' "$*" >&2
}

die() {
  printf '\nERROR: %s\n' "$*" >&2
  exit 1
}

have() {
  command -v "$1" >/dev/null 2>&1
}

is_interactive() {
  [[ -t 0 && -t 1 ]]
}

normalize_agent() {
  case "$1" in
    claude|Claude|CLAUDE|claude-code|Claude-Code|CLAUDE-CODE)
      printf 'claude'
      ;;
    claude-desktop|Claude-Desktop|CLAUDE-DESKTOP|claude_desktop|ClaudeDesktop|claude-app|Claude-App)
      printf 'claude-desktop'
      ;;
    codex|Codex|CODEX|openai-codex|OpenAI-Codex)
      printf 'codex'
      ;;
    none|no|skip|off|0)
      printf 'none'
      ;;
    *)
      printf ''
      ;;
  esac
}

ensure_macos() {
  [[ "$(uname -s)" == "Darwin" ]] || die "This setup script is for macOS only."
}

select_workspace_dir() {
  if [[ -n "$WORKSPACE_DIR_FROM_ENV" ]]; then
    log "Using WORKSPACE_DIR from environment: $WORKSPACE_DIR"
    return
  fi

  if ! is_interactive; then
    log "Using default workspace: $WORKSPACE_DIR"
    return
  fi

  printf '\nWorkspace to clone/open [%s]: ' "$WORKSPACE_DIR"
  local chosen_workspace
  read -r chosen_workspace
  if [[ -n "$chosen_workspace" ]]; then
    WORKSPACE_DIR="${chosen_workspace/#\~/$HOME}"
  fi
  log "Workspace: $WORKSPACE_DIR"
}

select_ai_agent() {
  if [[ "$OPEN_AGENT" == "0" ]]; then
    SELECTED_AI_AGENT="none"
    log "Skipping AI agent launch"
    return
  fi

  if [[ -n "$AI_AGENT" ]]; then
    SELECTED_AI_AGENT="$(normalize_agent "$AI_AGENT")"
    [[ -n "$SELECTED_AI_AGENT" ]] || die "AI_AGENT must be claude, claude-desktop, codex, or none."
    log "Using AI_AGENT=$SELECTED_AI_AGENT"
    return
  fi

  if ! is_interactive; then
    SELECTED_AI_AGENT="claude"
    log "Using default AI agent: Claude Code"
    return
  fi

  printf '\nWhich AI agent should open this workspace?\n\n'
  printf '1) Claude Code\n'
  printf '2) Claude for Desktop\n'
  printf '3) Codex\n'
  printf '4) Do not open an agent\n'

  local choice
  while true; do
    printf '\nSelection [1]: '
    read -r choice
    choice="${choice:-1}"
    case "$choice" in
      1)
        SELECTED_AI_AGENT="claude"
        ;;
      2)
        SELECTED_AI_AGENT="claude-desktop"
        ;;
      3)
        SELECTED_AI_AGENT="codex"
        ;;
      4)
        SELECTED_AI_AGENT="none"
        ;;
      *)
        warn "Enter 1, 2, 3, or 4."
        continue
        ;;
    esac
    log "Selected AI agent: $SELECTED_AI_AGENT"
    return
  done
}

ensure_xcode_cli_tools() {
  if xcode-select -p >/dev/null 2>&1; then
    log "Xcode Command Line Tools found"
    return
  fi

  log "Installing Xcode Command Line Tools"
  xcode-select --install || true
  warn "Finish the Apple installer dialog, then run this script again."
  exit 1
}

ensure_homebrew() {
  if have brew; then
    log "Homebrew found"
  else
    log "Installing Homebrew"
    NONINTERACTIVE=1 /bin/bash -c "$(curl -fsSL https://raw.githubusercontent.com/Homebrew/install/HEAD/install.sh)"
  fi

  if [[ -x /opt/homebrew/bin/brew ]]; then
    eval "$(/opt/homebrew/bin/brew shellenv)"
  elif [[ -x /usr/local/bin/brew ]]; then
    eval "$(/usr/local/bin/brew shellenv)"
  fi

  have brew || die "Homebrew installation finished, but brew is not on PATH. Open a new terminal and rerun this script."
}

brew_install_formula() {
  local formula="$1"
  if brew list --formula "$formula" >/dev/null 2>&1; then
    log "$formula already installed"
  else
    log "Installing $formula"
    brew install "$formula"
  fi
}

brew_install_cask() {
  local cask="$1"
  if brew list --cask "$cask" >/dev/null 2>&1; then
    log "$cask already installed"
  else
    log "Installing $cask"
    brew install --cask "$cask"
  fi
}

java_major_version() {
  if ! have java; then
    return 1
  fi

  java -version 2>&1 | awk -F '"' '/version/ {
    split($2, parts, ".")
    if (parts[1] == "1") print parts[2]; else print parts[1]
  }'
}

ensure_java17() {
  local major
  major="$(java_major_version || true)"

  if [[ -n "$major" && "$major" -ge 17 ]]; then
    log "Java $major found"
    return
  fi

  brew_install_formula openjdk@17

  local java_home
  java_home="$(brew --prefix openjdk@17)/libexec/openjdk.jdk/Contents/Home"
  export JAVA_HOME="$java_home"
  export PATH="$JAVA_HOME/bin:$PATH"

  local shell_rc="$HOME/.zshrc"
  if [[ -f "$shell_rc" ]] && grep -q "SkillAutomation Java 17" "$shell_rc"; then
    log "Java 17 shell config already present"
  else
    log "Adding Java 17 config to $shell_rc"
    {
      printf '\n# SkillAutomation Java 17\n'
      printf 'export JAVA_HOME="%s"\n' "$java_home"
      printf 'export PATH="$JAVA_HOME/bin:$PATH"\n'
    } >> "$shell_rc"
  fi
}

ensure_node18() {
  local major=""
  if have node; then
    major="$(node -p 'Number(process.versions.node.split(".")[0])' 2>/dev/null || true)"
  fi

  if [[ -n "$major" && "$major" -ge 18 ]]; then
    log "Node.js $(node --version) found"
  else
    brew_install_formula node
  fi
}

ensure_chrome() {
  if [[ -d "/Applications/Google Chrome.app" ]]; then
    log "Google Chrome found"
  else
    brew_install_cask google-chrome
  fi
}

ensure_claude_code() {
  if have claude; then
    log "Claude Code found: $(claude --version 2>/dev/null || printf 'installed')"
    return
  fi

  log "Installing Claude Code with Homebrew"
  if brew install --cask claude-code; then
    :
  else
    warn "Homebrew cask install failed; trying Anthropic native installer"
    curl -fsSL https://claude.ai/install.sh | bash
    export PATH="$HOME/.local/bin:$PATH"
  fi

  if ! have claude && [[ -x "$HOME/.local/bin/claude" ]]; then
    export PATH="$HOME/.local/bin:$PATH"
  fi

  have claude || die "Claude Code installation completed, but 'claude' is not on PATH. Open a new terminal and rerun this script."
}

ensure_claude_desktop() {
  if [[ -d "/Applications/Claude.app" ]]; then
    log "Claude for Desktop found"
    return
  fi

  brew_install_cask claude
}

ensure_codex_available() {
  if have codex; then
    log "Codex CLI found: $(codex --version 2>/dev/null || printf 'installed')"
    return
  fi

  if [[ -d "/Applications/Codex.app" ]]; then
    log "Codex desktop app found"
    return
  fi

  warn "Codex was selected but no 'codex' command or /Applications/Codex.app was found."
  warn "Install/open Codex manually, or rerun with AI_AGENT=claude or AI_AGENT=claude-desktop."
}

ensure_selected_agent() {
  case "$SELECTED_AI_AGENT" in
    claude)
      ensure_claude_code
      ;;
    claude-desktop)
      ensure_claude_desktop
      ;;
    codex)
      ensure_codex_available
      ;;
    none)
      log "No AI agent install/check requested"
      ;;
    *)
      die "No AI agent selected"
      ;;
  esac
}

clone_or_update_workspace() {
  if [[ -d "$WORKSPACE_DIR/.git" ]]; then
    log "Updating existing workspace: $WORKSPACE_DIR"
    remove_known_untracked_transition_files
    stash_local_tracked_changes_before_update
    git -C "$WORKSPACE_DIR" pull --ff-only
  elif [[ -e "$WORKSPACE_DIR" ]]; then
    die "$WORKSPACE_DIR already exists but is not a Git repo. Set WORKSPACE_DIR to another path."
  else
    log "Cloning runner workspace"
    git clone "$REPO_URL" "$WORKSPACE_DIR"
  fi
}

stash_local_tracked_changes_before_update() {
  local status stash_message
  status="$(git -C "$WORKSPACE_DIR" status --porcelain --untracked-files=no)"
  [[ -n "$status" ]] || return 0

  stash_message="SkillAutomation setup auto-stash before update $(date -u +%Y%m%dT%H%M%SZ)"
  warn "Local tracked changes found in $WORKSPACE_DIR; stashing them before update."
  git -C "$WORKSPACE_DIR" stash push -m "$stash_message"
  warn "Local changes were preserved in git stash: $stash_message"
  warn "To inspect or restore later: cd \"$WORKSPACE_DIR\" && git stash list && git stash pop"
}

remove_known_untracked_transition_files() {
  local file status
  local transition_files=(
    "imported/twentyfive-regtest/Core/src/main/java/t5/ipe/cucumber/core/web/AzureDeploymentChecker.java"
    "imported/twentyfive-regtest/Core/src/main/java/t5/ipe/cucumber/core/web/TicketHandler.java"
  )

  for file in "${transition_files[@]}"; do
    [[ -e "$WORKSPACE_DIR/$file" ]] || continue
    status="$(git -C "$WORKSPACE_DIR" status --porcelain -- "$file")"
    if [[ "$status" == "?? $file" ]]; then
      log "Removing old generated untracked file before pull: $file"
      rm -f "$WORKSPACE_DIR/$file"
    fi
  done
}

write_workspace_env() {
  WORKSPACE_ENV_FILE="$WORKSPACE_DIR/.skillautomation.env"
  log "Writing workspace environment: $WORKSPACE_ENV_FILE"
  {
    printf 'APP_LABEL=%q\n' "$SELECTED_APP_LABEL"
    printf 'APP_URL=%q\n' "$SELECTED_APP_URL"
    printf 'CHROME_DEBUG_PORT=%q\n' "$CHROME_DEBUG_PORT"
    printf 'CHROME_PROFILE=%q\n' "$CHROME_PROFILE"
    if [[ "$SELECTED_AI_AGENT" == "claude-desktop" ]]; then
      printf 'AUTO_START_CHROME=false\n'
    else
      printf 'AUTO_START_CHROME=true\n'
    fi
  } > "$WORKSPACE_ENV_FILE"
}

write_claude_desktop_handoff() {
  [[ "$SELECTED_AI_AGENT" == "claude-desktop" ]] || return 0

  mkdir -p "$WORKSPACE_DIR/.skillautomation"
  CLAUDE_DESKTOP_PROMPT_FILE="$WORKSPACE_DIR/.skillautomation/CLAUDE_DESKTOP_RUN_TEST.md"
  log "Writing Claude Desktop handoff: $CLAUDE_DESKTOP_PROMPT_FILE"

  cat > "$CLAUDE_DESKTOP_PROMPT_FILE" <<EOF
# Claude Desktop Test Runner Handoff

Use this workspace:

\`\`\`text
$WORKSPACE_DIR
\`\`\`

The selected app URL is saved in:

\`\`\`text
$WORKSPACE_ENV_FILE
\`\`\`

Selected app:

\`\`\`text
${SELECTED_APP_LABEL:-none}
${SELECTED_APP_URL:-no URL selected}
\`\`\`

Chrome should be launched only when I ask to run a test. Use remote debugging on:

\`\`\`text
127.0.0.1:$CHROME_DEBUG_PORT
\`\`\`

When I paste a Confluence/manual test case, do not try to detect the URL from browser settings. Use the saved workspace environment file or this default URL:

\`\`\`text
${SELECTED_APP_URL:-}
\`\`\`

Before running the first test, ask whether I want to launch the Selenium Chrome browser with the default URL shown above. If I provide a different URL, launch Chrome with that URL instead. Use:

\`\`\`bash
cd "$WORKSPACE_DIR"
scripts/start-debug-chrome.sh "${SELECTED_APP_URL:-}"
\`\`\`

After Chrome is open and I have logged in, run tests through the request runner first:

\`\`\`bash
cd "$WORKSPACE_DIR"
scripts/run-test-request.sh "TC-Prof-Services-001: Verify Proposal Setup Layout"
\`\`\`

If I ask to run a named suite such as "Manufacturing Proposal", use:

\`\`\`bash
cd "$WORKSPACE_DIR"
scripts/run-test-request.sh "Manufacturing Proposal"
\`\`\`

List available mapped tests and suites with \`scripts/run-test-request.sh --list\`; the mapping is in \`config/test-registry.json\`, \`config/test-suites.json\`, and \`TEST_SUITES.md\`.

If I ask for Master Data Products & Services, \`TC-MD-PS-001\`, or to run through a specific TC such as TC2 or TC5, use the reusable Master Data runner instead of creating a new Playwright script:

\`\`\`bash
cd "$WORKSPACE_DIR"
scripts/run-test-request.sh "TC-MD-PS-001"
\`\`\`

The action layer is in \`automation/master-data/\`; add missing actions there only when needed.

If the pasted test case has a different tag, replace \`@TC-001\` with that tag. If there is no tag, inspect the existing feature files and generate only the missing glue needed for the pasted case.

Important:

- Reuse the bundled Twenty5 automation code under \`imported/twentyfive-regtest\`.
- Attach Selenium to the Chrome debug session on \`127.0.0.1:$CHROME_DEBUG_PORT\`.
- Do not ask me to change Chrome site permissions unless the Selenium run reports a specific browser permission failure.
- Do not start Chrome during setup; start it only after I ask to run a test and approve the default URL or provide an alternate URL.
- Use \`scripts/run-test-request.sh "<my request>"\` before creating new files. Only author new automation when the resolver cannot map the request.
EOF
}

copy_claude_desktop_prompt() {
  [[ "$SELECTED_AI_AGENT" == "claude-desktop" ]] || return 0
  have pbcopy || return 0

  log "Copying Claude Desktop starter prompt to clipboard"
  pbcopy <<EOF
Use the local workspace at:
$WORKSPACE_DIR

Read this handoff file first:
$CLAUDE_DESKTOP_PROMPT_FILE

The default app URL is:
${SELECTED_APP_URL:-no URL selected}

When I ask to run a test or named suite, use scripts/run-test-request.sh "<my request>" first. It will show the default app URL, allow an alternate URL, launch/reuse Selenium Chrome when needed, preflight mapped data files/tags, and delegate to the correct runner. Use config/test-registry.json, config/test-suites.json, TEST_REQUEST_RUNNER.md, TEST_SUITES.md, MASTER_DATA_TEST_ARCHITECTURE.md, and the saved .skillautomation.env; do not try to infer the URL from Chrome settings.

<paste test case here>
EOF
}

install_node_dependencies() {
  # Install the workspace Node dependencies (Playwright lives in package.json)
  # so consultants do not have to run `npm install` by hand. Browser binaries
  # are skipped on purpose: tests attach to the consultant's Chrome over CDP
  # (remote debugging port), so the bundled Chromium download is not needed.
  local pkg_json="$WORKSPACE_DIR/package.json"
  [[ -f "$pkg_json" ]] || { log "No package.json in workspace; skipping npm install"; return; }

  if ! have npm; then
    warn "npm not found on PATH; skipping Node dependency install (Playwright will be missing)."
    return
  fi

  log "Installing workspace Node dependencies (Playwright, browser download skipped)"
  if ! ( cd "$WORKSPACE_DIR" && PLAYWRIGHT_SKIP_BROWSER_DOWNLOAD=1 npm install ); then
    warn "npm install failed; run it manually in $WORKSPACE_DIR if Playwright is needed."
  fi
}

install_codex_skill_copy() {
  local source_skill="$WORKSPACE_DIR/skills/$SKILL_NAME"
  local dest_root="$HOME/.codex/skills"
  local dest_skill="$dest_root/$SKILL_NAME"

  [[ -d "$source_skill" ]] || die "Skill folder not found at $source_skill"

  log "Installing Codex skill copy: $dest_skill"
  mkdir -p "$dest_root"
  rm -rf "$dest_skill"
  cp -R "$source_skill" "$dest_skill"
}

install_with_npx_skills_if_requested() {
  if [[ "$RUN_NPX_SKILLS" != "1" ]]; then
    return
  fi

  log "Installing skill through npx skills"
  npx --yes skills add "$REPO_URL" --skill "$SKILL_NAME" || warn "npx skills install failed; local Codex copy is already installed."
}

select_app_url() {
  if [[ -n "$APP_URL" ]]; then
    SELECTED_APP_LABEL="Custom APP_URL"
    SELECTED_APP_URL="$APP_URL"
    log "Using APP_URL: $SELECTED_APP_URL"
    return
  fi

  SELECTED_APP_LABEL="$DEFAULT_APP_LABEL"
  SELECTED_APP_URL="$DEFAULT_APP_URL"
  log "Using default app URL: $SELECTED_APP_URL"

  if [[ "$SKIP_APP_URL_PROMPT" == "1" || -z "$APP_CHOICE" ]]; then
    return
  fi

  local custom_choice skip_choice choice index
  custom_choice=$((${#APP_LABELS[@]} + 1))
  skip_choice=$((${#APP_LABELS[@]} + 2))

  printf '\nChoose the base URL to open in Chrome:\n\n'
  for index in "${!APP_LABELS[@]}"; do
    printf '%2d) %-34s %s\n' "$((index + 1))" "${APP_LABELS[$index]}" "${APP_URLS[$index]}"
  done
  printf '%2d) Enter custom URL\n' "$custom_choice"
  printf '%2d) Skip opening app URL\n' "$skip_choice"

  while true; do
    if [[ -n "$APP_CHOICE" ]]; then
      choice="$APP_CHOICE"
      printf '\nUsing APP_CHOICE=%s\n' "$choice"
    else
      printf '\nSelection [1]: '
      read -r choice
      choice="${choice:-1}"
    fi

    if ! [[ "$choice" =~ ^[0-9]+$ ]]; then
      warn "Enter a number from the list."
      APP_CHOICE=""
      continue
    fi

    if [[ "$choice" -ge 1 && "$choice" -le "${#APP_LABELS[@]}" ]]; then
      index=$((choice - 1))
      SELECTED_APP_LABEL="${APP_LABELS[$index]}"
      SELECTED_APP_URL="${APP_URLS[$index]}"
      log "Selected $SELECTED_APP_LABEL"
      return
    fi

    if [[ "$choice" -eq "$custom_choice" ]]; then
      printf 'Enter custom URL: '
      read -r SELECTED_APP_URL
      [[ "$SELECTED_APP_URL" =~ ^https?:// ]] || die "Custom URL must start with http:// or https://"
      SELECTED_APP_LABEL="Custom URL"
      return
    fi

    if [[ "$choice" -eq "$skip_choice" ]]; then
      log "No app URL will be opened"
      return
    fi

    warn "Enter a number from the list."
    APP_CHOICE=""
  done
}

wait_for_chrome_debug_port() {
  local attempt
  for attempt in {1..30}; do
    if lsof -nP -iTCP:"$CHROME_DEBUG_PORT" -sTCP:LISTEN >/dev/null 2>&1; then
      return 0
    fi
    sleep 0.5
  done
  return 1
}

start_debug_chrome() {
  if [[ "$SELECTED_AI_AGENT" == "claude-desktop" ]]; then
    log "Skipping Chrome launch for Claude Desktop; it will launch when a test run is requested."
    return
  fi

  mkdir -p "$CHROME_PROFILE"

  # Reuse an already-running debug session; do not open a duplicate window or tab.
  # The persistent profile keeps you logged in across runs.
  if lsof -nP -iTCP:"$CHROME_DEBUG_PORT" -sTCP:LISTEN >/dev/null 2>&1; then
    log "Reusing existing Chrome debug session on port $CHROME_DEBUG_PORT (sign-in preserved)"
    return
  fi

  if [[ ! -x "$CHROME_BIN" ]]; then
    warn "Google Chrome binary not found at: $CHROME_BIN (set CHROME_BIN to override). Skipping Chrome launch."
    return
  fi

  log "Starting Chrome on debug port $CHROME_DEBUG_PORT"
  # Launch the Chrome binary directly (not 'open -na') with the dedicated profile so
  # Chrome reuses a single instance instead of forcing duplicate windows.
  local chrome_args=(
    --remote-debugging-port="$CHROME_DEBUG_PORT"
    --user-data-dir="$CHROME_PROFILE"
    --disable-popup-blocking
    --no-first-run
    --no-default-browser-check
  )

  if [[ -n "$SELECTED_APP_URL" ]]; then
    chrome_args+=("$SELECTED_APP_URL")
  fi

  "$CHROME_BIN" "${chrome_args[@]}" >/dev/null 2>&1 &

  if ! wait_for_chrome_debug_port; then
    warn "Chrome started, but debug port $CHROME_DEBUG_PORT was not detected yet."
  fi
}

print_next_prompt() {
  if [[ "$SELECTED_AI_AGENT" == "claude-desktop" ]]; then
    cat <<EOF

Setup complete.

Selected app:
${SELECTED_APP_LABEL:-none}
${SELECTED_APP_URL:-no URL opened}

No browser was opened for Claude Desktop setup. When you ask Claude Desktop to run a test, it should ask whether to launch Selenium Chrome with the default URL above or with an alternate URL you provide.

For Claude Desktop:
1. Paste the starter prompt copied to your clipboard.
2. Attach or reference this handoff file if Claude asks for context:
   $CLAUDE_DESKTOP_PROMPT_FILE
3. Paste the Confluence/manual test case after the prompt.

EOF
    return
  fi

  cat <<EOF

Setup complete.

Selected app:
${SELECTED_APP_LABEL:-none}
${SELECTED_APP_URL:-no URL opened}

In the Chrome window that opened:
1. Log in to the target application manually.
2. Keep that Chrome window open.

In the selected AI agent, paste:

Use \$selenium-automation.
Run this Confluence/manual test in my local Chrome.
Use bundled Twenty5 automation code first.
Generate only missing glue code.

<paste test case here>

Workspace:
$WORKSPACE_DIR
EOF
}

open_selected_agent() {
  case "$SELECTED_AI_AGENT" in
    claude)
      log "Opening Claude Code in $WORKSPACE_DIR"
      cd "$WORKSPACE_DIR"
      exec claude
      ;;
    claude-desktop)
      log "Opening Claude for Desktop with $WORKSPACE_DIR"
      open -a "Claude" "$WORKSPACE_DIR"
      ;;
    codex)
      log "Opening Codex in $WORKSPACE_DIR"
      cd "$WORKSPACE_DIR"
      if have codex; then
        exec codex
      fi
      if [[ -d "/Applications/Codex.app" ]]; then
        open -a "Codex" "$WORKSPACE_DIR"
        return
      fi
      warn "Codex is not installed. Opening the workspace folder instead."
      open "$WORKSPACE_DIR"
      ;;
    none)
      log "Workspace is ready. No AI agent opened."
      ;;
  esac
}

main() {
  ensure_macos
  select_workspace_dir
  select_ai_agent
  ensure_xcode_cli_tools
  ensure_homebrew
  brew_install_formula git
  brew_install_formula maven
  brew_install_formula ripgrep
  ensure_java17
  ensure_node18
  ensure_chrome
  ensure_selected_agent
  clone_or_update_workspace
  install_node_dependencies
  install_codex_skill_copy
  install_with_npx_skills_if_requested
  select_app_url
  write_workspace_env
  write_claude_desktop_handoff
  copy_claude_desktop_prompt
  start_debug_chrome
  print_next_prompt
  open_selected_agent
}

main "$@"
