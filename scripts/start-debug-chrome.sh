#!/usr/bin/env bash
set -euo pipefail

SCRIPT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"
REPO_ROOT="$(cd "$SCRIPT_DIR/.." && pwd)"
ENV_FILE="${ENV_FILE:-$REPO_ROOT/.skillautomation.env}"

if [[ -f "$ENV_FILE" ]]; then
  # shellcheck disable=SC1090
  source "$ENV_FILE"
fi

APP_URL="${1:-${APP_URL:-${SELECTED_APP_URL:-}}}"
CHROME_DEBUG_PORT="${CHROME_DEBUG_PORT:-9222}"
CHROME_PROFILE="${CHROME_PROFILE:-$HOME/.selenium-ai-chrome}"

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

open_app_url_in_debug_chrome() {
  [[ -n "$APP_URL" ]] || return 0

  local encoded_url
  encoded_url="$(node -e 'process.stdout.write(encodeURIComponent(process.argv[1]))' "$APP_URL")"

  if curl -fsS -X PUT "http://127.0.0.1:${CHROME_DEBUG_PORT}/json/new?${encoded_url}" >/dev/null 2>&1; then
    return 0
  fi

  open -a "Google Chrome" "$APP_URL"
}

if [[ "$(uname -s)" != "Darwin" ]]; then
  echo "ERROR: scripts/start-debug-chrome.sh currently supports macOS only." >&2
  exit 2
fi

mkdir -p "$CHROME_PROFILE"

if chrome_debug_ready; then
  echo "Chrome debug port $CHROME_DEBUG_PORT is already listening."
  open_app_url_in_debug_chrome
  exit 0
fi

echo "Starting dedicated Chrome debug profile on port $CHROME_DEBUG_PORT."

chrome_args=(
  --remote-debugging-port="$CHROME_DEBUG_PORT"
  --user-data-dir="$CHROME_PROFILE"
  --disable-popup-blocking
)

if [[ -n "$APP_URL" ]]; then
  chrome_args+=("$APP_URL")
fi

open -na "Google Chrome" --args "${chrome_args[@]}"

if ! wait_for_chrome_debug; then
  echo "WARN: Chrome started, but debug port $CHROME_DEBUG_PORT was not detected yet." >&2
  exit 3
fi

echo "Chrome debug endpoint is ready at http://127.0.0.1:$CHROME_DEBUG_PORT."
