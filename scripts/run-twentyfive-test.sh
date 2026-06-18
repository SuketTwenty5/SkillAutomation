#!/usr/bin/env bash
set -euo pipefail

SCRIPT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"
REPO_ROOT="$(cd "$SCRIPT_DIR/.." && pwd)"
TEST_ROOT="${TEST_ROOT:-$REPO_ROOT/imported/twentyfive-regtest}"
ENV_FILE="${ENV_FILE:-$REPO_ROOT/.skillautomation.env}"

if [[ -f "$ENV_FILE" ]]; then
  # shellcheck disable=SC1090
  source "$ENV_FILE"
fi

APP_URL="${APP_URL:-${SELECTED_APP_URL:-}}"
CUCUMBER_TAGS="${CUCUMBER_TAGS:-${1:-@TC-001}}"
CHROME_DEBUG_PORT="${CHROME_DEBUG_PORT:-9222}"
USE_DEBUG_CHROME="${USE_DEBUG_CHROME:-true}"
LOCAL_RUN="${LOCAL_RUN:-true}"
DEBUG_HOLD_BROWSER="${DEBUG_HOLD_BROWSER:-true}"

usage() {
  cat <<EOF
Usage:
  APP_URL=<url> [CUCUMBER_TAGS=@tag] scripts/run-twentyfive-test.sh
  scripts/run-twentyfive-test.sh @TC-001

Environment:
  APP_URL             Target base URL. Defaults to .skillautomation.env from setup.
  CUCUMBER_TAGS       Cucumber tag filter. Defaults to @TC-001.
  USE_DEBUG_CHROME    true attaches to Chrome on localhost:9222. Default true.
  LOCAL_RUN           Pass -Dlocal.run=true. Default true.
  DEBUG_HOLD_BROWSER  Pass -Ddebug=true. Default true when USE_DEBUG_CHROME=true.
EOF
}

if [[ "${1:-}" == "-h" || "${1:-}" == "--help" ]]; then
  usage
  exit 0
fi

if [[ -z "$APP_URL" ]]; then
  usage
  echo
  echo "ERROR: APP_URL is not set and $ENV_FILE does not contain a selected URL." >&2
  exit 2
fi

if [[ ! -d "$TEST_ROOT" ]]; then
  echo "ERROR: Test root not found: $TEST_ROOT" >&2
  exit 2
fi

if [[ "$USE_DEBUG_CHROME" == "true" ]]; then
  if ! curl -fsS "http://127.0.0.1:${CHROME_DEBUG_PORT}/json/version" >/dev/null 2>&1; then
    cat >&2 <<EOF
ERROR: Chrome debug endpoint is not listening on port $CHROME_DEBUG_PORT.

Run setup first from a normal macOS Terminal:
  /bin/bash -c "\$(curl -fsSL https://raw.githubusercontent.com/SuketTwenty5/SkillAutomation/main/scripts/macos-consultant-setup.sh)"

Or start Chrome manually:
  open -na "Google Chrome" --args --remote-debugging-port=$CHROME_DEBUG_PORT --user-data-dir="\$HOME/.selenium-ai-chrome"
EOF
    exit 3
  fi
fi

cd "$TEST_ROOT"

MVN_ARGS=(
  -pl tests
  -am
  test
  -Dtest=RunTest
  -Dsurefire.failIfNoSpecifiedTests=false
  "-Dcucumber.filter.tags=$CUCUMBER_TAGS"
  "-Denv=$APP_URL"
  "-Dlocal.run=$LOCAL_RUN"
)

if [[ "$USE_DEBUG_CHROME" == "true" && "$DEBUG_HOLD_BROWSER" == "true" ]]; then
  MVN_ARGS+=("-Ddebug=true")
fi

echo "Running Twenty5 test"
echo "  root: $TEST_ROOT"
echo "  url:  $APP_URL"
echo "  tags: $CUCUMBER_TAGS"
echo

mvn "${MVN_ARGS[@]}"
