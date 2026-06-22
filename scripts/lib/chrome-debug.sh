#!/usr/bin/env bash

CHROME_DEBUG_PORT="${CHROME_DEBUG_PORT:-9222}"
CHROME_PROFILE="${CHROME_PROFILE:-$HOME/.selenium-ai-chrome}"
CHROME_BIN="${CHROME_BIN:-/Applications/Google Chrome.app/Contents/MacOS/Google Chrome}"
LOGIN_WAIT_SECONDS="${LOGIN_WAIT_SECONDS:-30}"
CLEAR_CHROME_CACHE="${CLEAR_CHROME_CACHE:-false}"
FRESH_CHROME_PROFILE="${FRESH_CHROME_PROFILE:-false}"
FRESH_CHROME_ROOT="${FRESH_CHROME_ROOT:-$HOME/.selenium-ai-chrome-sessions}"
FRESH_CHROME_SESSION="${FRESH_CHROME_SESSION:-latest}"
RESET_FRESH_CHROME_PROFILE="${RESET_FRESH_CHROME_PROFILE:-false}"

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

  if [[ "$FRESH_CHROME_PROFILE" == "true" ]]; then
    if chrome_debug_ready; then
      echo "ERROR: FRESH_CHROME_PROFILE=true was requested, but Chrome debug is already running on port $CHROME_DEBUG_PORT." >&2
      echo "Close the existing Selenium Chrome window before starting a fresh test session." >&2
      return 4
    fi
  fi

  configure_fresh_chrome_profile_if_requested

  if chrome_debug_ready; then
    echo "Reusing existing Chrome debug session on port $CHROME_DEBUG_PORT."
    return 0
  fi

  if [[ ! -x "$CHROME_BIN" ]]; then
    echo "ERROR: Google Chrome binary not found at: $CHROME_BIN" >&2
    echo "Set CHROME_BIN to the Chrome executable path and retry." >&2
    return 2
  fi

  clear_chrome_cache_if_requested
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

configure_fresh_chrome_profile_if_requested() {
  [[ "$FRESH_CHROME_PROFILE" == "true" ]] || return 0

  local root="${FRESH_CHROME_ROOT%/}"
  local pointer_file="$root/$FRESH_CHROME_SESSION.path"
  mkdir -p "$root"

  if [[ "$RESET_FRESH_CHROME_PROFILE" != "true" && -f "$pointer_file" ]]; then
    local saved_profile
    saved_profile="$(cat "$pointer_file")"
    if [[ -n "$saved_profile" && -d "$saved_profile" ]]; then
      CHROME_PROFILE="$saved_profile"
      export CHROME_PROFILE
      echo "Reusing saved fresh Chrome profile for session '$FRESH_CHROME_SESSION': $CHROME_PROFILE"
      echo "Cookies, cache, and local storage are preserved for faster repeat login."
      return 0
    fi
  fi

  CHROME_PROFILE="$(mktemp -d "$root/$FRESH_CHROME_SESSION.XXXXXX")"
  export CHROME_PROFILE
  printf '%s\n' "$CHROME_PROFILE" > "$pointer_file"
  echo "Using new fresh Chrome profile for session '$FRESH_CHROME_SESSION': $CHROME_PROFILE"
  echo "This profile will be reused next time to keep cookies, cache, and local storage."
}

clear_chrome_cache_if_requested() {
  [[ "$CLEAR_CHROME_CACHE" == "true" ]] || return 0

  if [[ -z "$CHROME_PROFILE" || "$CHROME_PROFILE" == "/" ]]; then
    echo "ERROR: Refusing to clear Chrome cache because CHROME_PROFILE is unsafe: $CHROME_PROFILE" >&2
    return 2
  fi

  if chrome_debug_ready; then
    echo "WARN: CLEAR_CHROME_CACHE=true was requested, but Chrome debug is already running."
    echo "      Close the Selenium Chrome window and rerun to clear cache before launch."
    return 0
  fi

  echo "Clearing dedicated Chrome cache for profile: $CHROME_PROFILE"
  local cache_paths=(
    "$CHROME_PROFILE/Default/Cache"
    "$CHROME_PROFILE/Default/Code Cache"
    "$CHROME_PROFILE/Default/GPUCache"
    "$CHROME_PROFILE/Default/Service Worker/CacheStorage"
    "$CHROME_PROFILE/Default/Service Worker/ScriptCache"
    "$CHROME_PROFILE/GrShaderCache"
    "$CHROME_PROFILE/ShaderCache"
  )

  local path
  for path in "${cache_paths[@]}"; do
    if [[ -e "$path" ]]; then
      rm -rf -- "$path"
    fi
  done
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

  if [[ "$FRESH_CHROME_PROFILE" == "true" ]]; then
    if chrome_debug_ready; then
      echo "ERROR: FRESH_CHROME_PROFILE=true was requested, but Chrome debug is already running on port $CHROME_DEBUG_PORT." >&2
      echo "Close the existing Selenium Chrome window before starting a fresh test session." >&2
      return 4
    fi
  fi

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
