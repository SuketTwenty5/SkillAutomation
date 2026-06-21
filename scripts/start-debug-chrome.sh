#!/usr/bin/env bash
set -euo pipefail

SCRIPT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"
REPO_ROOT="$(cd "$SCRIPT_DIR/.." && pwd)"
ENV_FILE="${ENV_FILE:-$REPO_ROOT/.skillautomation.env}"

# shellcheck source=scripts/lib/env.sh
source "$SCRIPT_DIR/lib/env.sh"
source_env_preserving_caller "$ENV_FILE" \
  APP_URL SELECTED_APP_URL CHROME_DEBUG_PORT CHROME_PROFILE CHROME_BIN

APP_URL="${1:-${APP_URL:-${SELECTED_APP_URL:-}}}"
CHROME_DEBUG_PORT="${CHROME_DEBUG_PORT:-9222}"
CHROME_PROFILE="${CHROME_PROFILE:-$HOME/.selenium-ai-chrome}"
CHROME_BIN="${CHROME_BIN:-/Applications/Google Chrome.app/Contents/MacOS/Google Chrome}"

# shellcheck source=scripts/lib/chrome-debug.sh
source "$SCRIPT_DIR/lib/chrome-debug.sh"

start_chrome_debug "$APP_URL"

echo "Chrome debug endpoint is ready at http://127.0.0.1:$CHROME_DEBUG_PORT."
echo "Sign in once in this window; the profile keeps you logged in for later runs."
