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
CHROME_PROFILE="${CHROME_PROFILE:-$HOME/.selenium-ai-chrome}"
METRICS_ENABLED="${METRICS_ENABLED:-false}"

running_inside_codex_sandbox() {
  [[ -n "${CODEX_SANDBOX:-}" || -n "${CODEX_SHELL:-}" ]]
}

if [[ -z "${AUTO_START_CHROME+x}" ]]; then
  AUTO_START_CHROME="true"
fi

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
  AUTO_START_CHROME   Start Chrome debug profile if port is not listening. Default true.
                      Set to false to require a manually started debug browser.
  CHROME_PROFILE      Chrome profile used for Selenium attachment.
  METRICS_ENABLED     Enable InfluxDB writes. Default false for local runner.
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
  echo "Starting Chrome debug profile on port $CHROME_DEBUG_PORT"
  open -na "Google Chrome" --args \
    --remote-debugging-port="$CHROME_DEBUG_PORT" \
    --user-data-dir="$CHROME_PROFILE" \
    "$APP_URL"
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
  if ! chrome_debug_ready && [[ "$AUTO_START_CHROME" == "true" ]]; then
    start_chrome_debug || true
    wait_for_chrome_debug || true
  fi

  if ! chrome_debug_ready; then
    cat >&2 <<EOF
ERROR: Chrome debug endpoint is not listening on port $CHROME_DEBUG_PORT.

Start the dedicated Chrome debug browser before running tests from Codex:

  scripts/start-debug-chrome.sh "$APP_URL"

Then log in to the target app in that Chrome window and rerun this command.

If you are running from Claude Code or a normal Terminal, the wrapper can open Chrome for you with:

  AUTO_START_CHROME=true
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
  "-Dmetrics.enabled=$METRICS_ENABLED"
)

if [[ "$USE_DEBUG_CHROME" == "true" && "$DEBUG_HOLD_BROWSER" == "true" ]]; then
  MVN_ARGS+=("-Ddebug=true")
fi

LOGS_DIR="$REPO_ROOT/reports/logs"
mkdir -p "$LOGS_DIR"
LOG_FILE="$LOGS_DIR/mvn_$(date +%Y%m%d_%H%M%S).log"

echo "Running Twenty5 test"
echo "  root: $TEST_ROOT"
echo "  url:  $APP_URL"
echo "  tags: $CUCUMBER_TAGS"
echo "  log:  $LOG_FILE"
echo

mvn "${MVN_ARGS[@]}" >"$LOG_FILE" 2>&1
MVN_EXIT=$?

python3 "$SCRIPT_DIR/generate-report.py" \
  "$TEST_ROOT/tests/target/cucumber-reports/Cucumber.json"

exit $MVN_EXIT
