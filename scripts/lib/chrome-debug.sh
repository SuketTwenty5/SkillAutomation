#!/usr/bin/env bash

CHROME_DEBUG_PORT="${CHROME_DEBUG_PORT:-9222}"
CHROME_PROFILE="${CHROME_PROFILE:-$HOME/.selenium-ai-chrome}"
CHROME_BIN="${CHROME_BIN:-/Applications/Google Chrome.app/Contents/MacOS/Google Chrome}"
LOGIN_WAIT_SECONDS="${LOGIN_WAIT_SECONDS:-30}"

chrome_debug_ready() {
  curl -fsS "http://127.0.0.1:${CHROME_DEBUG_PORT}/json/version" >/dev/null 2>&1
}

wait_for_chrome_debug() {
  local attempt
  for attempt in {1..40}; do
    if chrome_debug_ready; then
      return 0
    fi
    sleep 0.5
  done
  return 1
}

start_chrome_debug() {
  local app_url="${1:-}"

  if [[ "$(uname -s)" != "Darwin" ]]; then
    echo "ERROR: Chrome auto-launch currently supports macOS only." >&2
    return 2
  fi

  if chrome_debug_ready; then
    echo "Reusing existing Chrome debug session on port $CHROME_DEBUG_PORT."
    return 0
  fi

  if [[ ! -x "$CHROME_BIN" ]]; then
    echo "ERROR: Google Chrome binary not found at: $CHROME_BIN" >&2
    echo "Set CHROME_BIN to the Chrome executable path and retry." >&2
    return 2
  fi

  mkdir -p "$CHROME_PROFILE"
  echo "Starting dedicated Chrome debug profile on port $CHROME_DEBUG_PORT."
  local chrome_args=(
    --remote-debugging-port="$CHROME_DEBUG_PORT"
    --user-data-dir="$CHROME_PROFILE"
    --disable-popup-blocking
    --no-first-run
    --no-default-browser-check
  )

  if [[ -n "$app_url" ]]; then
    chrome_args+=("$app_url")
  fi

  "$CHROME_BIN" "${chrome_args[@]}" >/dev/null 2>&1 &
  wait_for_chrome_debug
}

chrome_open_urls() {
  curl -fsS "http://127.0.0.1:${CHROME_DEBUG_PORT}/json" 2>/dev/null \
    | python3 -c 'import json,sys
try:
    tabs=json.load(sys.stdin)
except Exception:
    tabs=[]
for tab in tabs:
    url=tab.get("url") or ""
    if url:
        print(url)
' 2>/dev/null || true
}

chrome_looks_like_login() {
  local urls
  urls="$(chrome_open_urls)"
  [[ -n "$urls" ]] || return 1
  printf '%s\n' "$urls" | grep -Eiq 'login|signin|sign-in|oauth|saml|accounts|authentication|identity'
}

prompt_for_login_if_needed() {
  local launched="${1:-false}"
  local assume_yes="${2:-false}"
  [[ "$launched" == "true" ]] || return 0
  [[ "$LOGIN_WAIT_SECONDS" =~ ^[0-9]+$ ]] || LOGIN_WAIT_SECONDS=30
  [[ "$LOGIN_WAIT_SECONDS" -gt 0 ]] || return 0

  echo "Waiting $LOGIN_WAIT_SECONDS seconds for manual SSO/login if the browser needs it..."
  sleep "$LOGIN_WAIT_SECONDS"

  if chrome_looks_like_login; then
    echo "The browser still appears to be on a login/authentication page."
    if [[ -t 0 && "$assume_yes" != "true" ]]; then
      read -r -p "Complete login in Chrome, then press Enter to continue the test run. " _
    else
      echo "Complete login in Chrome before rerunning if the test fails at login."
    fi
  fi
}

ensure_chrome_debug() {
  local app_url="$1"
  local auto_start="${2:-false}"
  local assume_yes="${3:-false}"

  if chrome_debug_ready; then
    echo "Chrome debug endpoint is ready at http://127.0.0.1:$CHROME_DEBUG_PORT."
    return 0
  fi

  if [[ "$auto_start" == "true" ]]; then
    start_chrome_debug "$app_url"
    prompt_for_login_if_needed true "$assume_yes"
    return 0
  fi

  cat >&2 <<EOF
ERROR: Chrome debug endpoint is not listening on port $CHROME_DEBUG_PORT.

Start the dedicated Chrome browser:

  scripts/start-debug-chrome.sh "$app_url"

Then log in and rerun the command.
EOF
  return 3
}
