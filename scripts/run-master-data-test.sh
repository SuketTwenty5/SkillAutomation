#!/usr/bin/env bash
set -euo pipefail

SCRIPT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"
REPO_ROOT="$(cd "$SCRIPT_DIR/.." && pwd)"
ENV_FILE="${ENV_FILE:-$REPO_ROOT/.skillautomation.env}"

# shellcheck source=scripts/lib/env.sh
source "$SCRIPT_DIR/lib/env.sh"
source_env_preserving_caller "$ENV_FILE" \
  MASTER_DATA_APP_URL APP_URL CDP_ENDPOINT CHROME_DEBUG_PORT CHROME_PROFILE \
  CHROME_BIN AUTO_START_CHROME

DEFAULT_MASTER_DATA_APP_URL="https://app-twenty5ipe-lm-dev.cfapps.us10.hana.ondemand.com/"
MASTER_DATA_APP_URL="${MASTER_DATA_APP_URL:-$DEFAULT_MASTER_DATA_APP_URL}"
CHROME_DEBUG_PORT="${CHROME_DEBUG_PORT:-9222}"
CDP_ENDPOINT="${CDP_ENDPOINT:-http://127.0.0.1:${CHROME_DEBUG_PORT}}"
CHROME_PROFILE="${CHROME_PROFILE:-$HOME/.selenium-ai-chrome}"
AUTO_START_CHROME="${AUTO_START_CHROME:-true}"

# shellcheck source=scripts/lib/chrome-debug.sh
source "$SCRIPT_DIR/lib/chrome-debug.sh"

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

if [[ "${1:-}" == "-h" || "${1:-}" == "--help" ]]; then
  usage
  node "$REPO_ROOT/test_automation_cdp.js" --help
  exit 0
fi

if [[ ! -d "$REPO_ROOT/node_modules" ]]; then
  echo "Installing Node dependencies without downloading browser binaries..."
  (cd "$REPO_ROOT" && PLAYWRIGHT_SKIP_BROWSER_DOWNLOAD=1 npm install)
fi

ensure_chrome_debug "$MASTER_DATA_APP_URL" "$AUTO_START_CHROME" "${ASSUME_YES:-false}"

cd "$REPO_ROOT"
MASTER_DATA_APP_URL="$MASTER_DATA_APP_URL" \
CDP_ENDPOINT="$CDP_ENDPOINT" \
node test_automation_cdp.js "$@"
