#!/usr/bin/env bash
set -euo pipefail

REPO_URL="${REPO_URL:-https://github.com/SuketTwenty5/SkillAutomation.git}"
WORKSPACE_DIR="${WORKSPACE_DIR:-$HOME/SkillAutomation}"
SKILL_NAME="${SKILL_NAME:-selenium-automation}"
CHROME_PROFILE="${CHROME_PROFILE:-$HOME/.selenium-ai-chrome}"
CHROME_DEBUG_PORT="${CHROME_DEBUG_PORT:-9222}"
OPEN_CLAUDE="${OPEN_CLAUDE:-1}"
RUN_NPX_SKILLS="${RUN_NPX_SKILLS:-0}"
APP_URL="${APP_URL:-}"
APP_CHOICE="${APP_CHOICE:-}"
SKIP_APP_URL_PROMPT="${SKIP_APP_URL_PROMPT:-0}"
SELECTED_APP_LABEL=""
SELECTED_APP_URL=""

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

ensure_macos() {
  [[ "$(uname -s)" == "Darwin" ]] || die "This setup script is for macOS only."
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

clone_or_update_workspace() {
  if [[ -d "$WORKSPACE_DIR/.git" ]]; then
    log "Updating existing workspace: $WORKSPACE_DIR"
    git -C "$WORKSPACE_DIR" pull --ff-only
  elif [[ -e "$WORKSPACE_DIR" ]]; then
    die "$WORKSPACE_DIR already exists but is not a Git repo. Set WORKSPACE_DIR to another path."
  else
    log "Cloning runner workspace"
    git clone "$REPO_URL" "$WORKSPACE_DIR"
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
  if [[ "$SKIP_APP_URL_PROMPT" == "1" ]]; then
    log "Skipping app URL selection"
    return
  fi

  if [[ -n "$APP_URL" ]]; then
    SELECTED_APP_LABEL="Custom APP_URL"
    SELECTED_APP_URL="$APP_URL"
    log "Using APP_URL: $SELECTED_APP_URL"
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

open_app_url_in_debug_chrome() {
  [[ -n "$SELECTED_APP_URL" ]] || return

  log "Opening $SELECTED_APP_LABEL in Chrome"
  local encoded_url
  encoded_url="$(node -e 'process.stdout.write(encodeURIComponent(process.argv[1]))' "$SELECTED_APP_URL")"

  if curl -fsS -X PUT "http://127.0.0.1:${CHROME_DEBUG_PORT}/json/new?${encoded_url}" >/dev/null 2>&1; then
    return
  fi

  warn "Could not open URL through Chrome debug endpoint; opening through macOS instead."
  open -a "Google Chrome" "$SELECTED_APP_URL"
}

start_debug_chrome() {
  mkdir -p "$CHROME_PROFILE"

  if lsof -nP -iTCP:"$CHROME_DEBUG_PORT" -sTCP:LISTEN >/dev/null 2>&1; then
    log "Chrome debug port $CHROME_DEBUG_PORT is already listening"
    open_app_url_in_debug_chrome
    return
  fi

  log "Starting Chrome on debug port $CHROME_DEBUG_PORT"
  local chrome_args=(
    --remote-debugging-port="$CHROME_DEBUG_PORT"
    --user-data-dir="$CHROME_PROFILE"
  )

  if [[ -n "$SELECTED_APP_URL" ]]; then
    chrome_args+=("$SELECTED_APP_URL")
  fi

  open -na "Google Chrome" --args "${chrome_args[@]}"

  if ! wait_for_chrome_debug_port; then
    warn "Chrome started, but debug port $CHROME_DEBUG_PORT was not detected yet."
  fi
}

print_next_prompt() {
  cat <<EOF

Setup complete.

Selected app:
${SELECTED_APP_LABEL:-none}
${SELECTED_APP_URL:-no URL opened}

In the Chrome window that opened:
1. Log in to the target application manually.
2. Keep that Chrome window open.

In Claude Code, paste:

Use \$selenium-automation.
Run this Confluence/manual test in my local Chrome.
Use bundled Twenty5 automation code first.
Generate only missing glue code.

<paste test case here>

Workspace:
$WORKSPACE_DIR
EOF
}

open_claude_code() {
  if [[ "$OPEN_CLAUDE" != "1" ]]; then
    return
  fi

  log "Opening Claude Code in $WORKSPACE_DIR"
  cd "$WORKSPACE_DIR"
  exec claude
}

main() {
  ensure_macos
  ensure_xcode_cli_tools
  ensure_homebrew
  brew_install_formula git
  brew_install_formula maven
  brew_install_formula ripgrep
  ensure_java17
  ensure_node18
  ensure_chrome
  ensure_claude_code
  clone_or_update_workspace
  install_codex_skill_copy
  install_with_npx_skills_if_requested
  select_app_url
  start_debug_chrome
  print_next_prompt
  open_claude_code
}

main "$@"
