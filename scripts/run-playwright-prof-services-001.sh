#!/usr/bin/env bash
set -euo pipefail

SCRIPT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"
REPO_ROOT="$(cd "$SCRIPT_DIR/.." && pwd)"
ENV_FILE="${ENV_FILE:-$REPO_ROOT/.skillautomation.env}"

# shellcheck source=scripts/lib/env.sh
source "$SCRIPT_DIR/lib/env.sh"
source_env_preserving_caller "$ENV_FILE" \
  APP_URL SELECTED_APP_URL CHROME_PROFILE CHROME_BIN PLAYWRIGHT_PROFILE_DIR

DEFAULT_APP_URL="https://approuter-twenty5ipe-dev.cfapps.us10.hana.ondemand.com/#quote"
APP_URL="${APP_URL:-${SELECTED_APP_URL:-$DEFAULT_APP_URL}}"

if [[ ! -d "$REPO_ROOT/node_modules" ]]; then
  echo "Installing Node dependencies without downloading browser binaries..."
  (cd "$REPO_ROOT" && PLAYWRIGHT_SKIP_BROWSER_DOWNLOAD=1 npm install)
fi

cd "$REPO_ROOT"
APP_URL="$APP_URL" \
"$SCRIPT_DIR/run-playwright-test.sh" tests/playwright/prof-services-001.spec.ts "$@"
