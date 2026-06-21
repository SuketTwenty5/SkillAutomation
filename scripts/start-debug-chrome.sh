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
CHROME_BIN="${CHROME_BIN:-/Applications/Google Chrome.app/Contents/MacOS/Google Chrome}"

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

if [[ "$(uname -s)" != "Darwin" ]]; then
  echo "ERROR: scripts/start-debug-chrome.sh currently supports macOS only." >&2
  exit 2
fi

mkdir -p "$CHROME_PROFILE"

# Reuse an already-running debug session instead of opening another Chrome.
# This keeps the consultant's existing, logged-in window intact (no duplicate
# windows, no extra tabs) so the persistent profile session is preserved.
if chrome_debug_ready; then
  echo "Reusing existing Chrome debug session on port $CHROME_DEBUG_PORT (already logged in if you signed in earlier)."
  echo "Chrome debug endpoint is ready at http://127.0.0.1:$CHROME_DEBUG_PORT."
  exit 0
fi

if [[ ! -x "$CHROME_BIN" ]]; then
  echo "ERROR: Google Chrome binary not found at: $CHROME_BIN" >&2
  echo "Set CHROME_BIN to the Chrome executable path and retry." >&2
  exit 2
fi

echo "Starting dedicated Chrome debug profile on port $CHROME_DEBUG_PORT."

# Launch the Chrome binary directly (NOT 'open -na'). Using a unique --user-data-dir
# makes Chrome reuse a single instance for this profile on subsequent launches instead
# of forcing a brand-new instance, which previously created duplicate windows.
chrome_args=(
  --remote-debugging-port="$CHROME_DEBUG_PORT"
  --user-data-dir="$CHROME_PROFILE"
  --disable-popup-blocking
  --no-first-run
  --no-default-browser-check
)

if [[ -n "$APP_URL" ]]; then
  chrome_args+=("$APP_URL")
fi

"$CHROME_BIN" "${chrome_args[@]}" >/dev/null 2>&1 &

if ! wait_for_chrome_debug; then
  echo "WARN: Chrome started, but debug port $CHROME_DEBUG_PORT was not detected yet." >&2
  exit 3
fi

echo "Chrome debug endpoint is ready at http://127.0.0.1:$CHROME_DEBUG_PORT."
echo "Sign in once in this window; the profile keeps you logged in for later runs."
