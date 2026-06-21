#!/usr/bin/env bash
set -euo pipefail

SCRIPT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"
REPO_ROOT="$(cd "$SCRIPT_DIR/.." && pwd)"
ENV_FILE="${ENV_FILE:-$REPO_ROOT/.skillautomation.env}"

if [[ -f "$ENV_FILE" ]]; then
  # shellcheck disable=SC1090
  source "$ENV_FILE"
fi

DEFAULT_MASTER_DATA_APP_URL="https://app-twenty5ipe-lm-dev.cfapps.us10.hana.ondemand.com/"
MASTER_DATA_APP_URL="${MASTER_DATA_APP_URL:-$DEFAULT_MASTER_DATA_APP_URL}"
CHROME_DEBUG_PORT="${CHROME_DEBUG_PORT:-9222}"
CDP_ENDPOINT="${CDP_ENDPOINT:-http://127.0.0.1:${CHROME_DEBUG_PORT}}"
CHROME_PROFILE="${CHROME_PROFILE:-$HOME/.selenium-ai-chrome}"
AUTO_START_CHROME="${AUTO_START_CHROME:-true}"

usage() {
  cat <<EOF
Usage:
  scripts/run-master-data-test.sh [--to TC5]
  scripts/run-master-data-test.sh --to TC2
  scripts/run-master-data-test.sh --only TC4

Environment:
  MASTER_DATA_APP_URL  Target Master Data app URL. Defaults to $DEFAULT_MASTER_DATA_APP_URL.
  CDP_ENDPOINT         Chrome CDP endpoint. Defaults to http://127.0.0.1:$CHROME_DEBUG_PORT.
  AUTO_START_CHROME   Start Chrome debug profile if port is not listening. Default true.
EOF
}

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
  if [[ "$(uname -s)" != "Darwin" ]]; then
    return 1
  fi
  mkdir -p "$CHROME_PROFILE"
  open -na "Google Chrome" --args \
    --remote-debugging-port="$CHROME_DEBUG_PORT" \
    --user-data-dir="$CHROME_PROFILE" \
    "$MASTER_DATA_APP_URL"
}

if [[ "${1:-}" == "-h" || "${1:-}" == "--help" ]]; then
  usage
  node "$REPO_ROOT/test_automation_cdp.js" --help
  exit 0
fi

if [[ ! -d "$REPO_ROOT/node_modules" ]]; then
  echo "Installing Node dependencies without downloading browser binaries..."
  (cd "$REPO_ROOT" && PLAYWRIGHT_SKIP_BROWSER_DOWNLOAD=1 npm install)
fi

if ! chrome_debug_ready && [[ "$AUTO_START_CHROME" == "true" ]]; then
  echo "Starting Chrome debug profile on port $CHROME_DEBUG_PORT"
  start_chrome_debug || true
  wait_for_chrome_debug || true
fi

if ! chrome_debug_ready; then
  cat >&2 <<EOF
ERROR: Chrome debug endpoint is not listening on port $CHROME_DEBUG_PORT.

Start the dedicated Chrome browser first:

  scripts/start-debug-chrome.sh "$MASTER_DATA_APP_URL"

Then log in and rerun this command.
EOF
  exit 3
fi

cd "$REPO_ROOT"
MASTER_DATA_APP_URL="$MASTER_DATA_APP_URL" \
CDP_ENDPOINT="$CDP_ENDPOINT" \
node test_automation_cdp.js "$@"
