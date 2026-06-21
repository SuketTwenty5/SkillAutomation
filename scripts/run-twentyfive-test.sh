#!/usr/bin/env bash
set -euo pipefail

SCRIPT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"
REPO_ROOT="$(cd "$SCRIPT_DIR/.." && pwd)"
TEST_ROOT="${TEST_ROOT:-$REPO_ROOT/imported/twentyfive-regtest}"
ENV_FILE="${ENV_FILE:-$REPO_ROOT/.skillautomation.env}"

# shellcheck source=scripts/lib/env.sh
source "$SCRIPT_DIR/lib/env.sh"
source_env_preserving_caller "$ENV_FILE" \
  APP_URL SELECTED_APP_URL CHROME_DEBUG_PORT USE_DEBUG_CHROME LOCAL_RUN \
  DEBUG_HOLD_BROWSER CHROME_PROFILE CHROME_BIN METRICS_ENABLED CLEAN_FIRST \
  STOP_ON_FAILURE DRY_RUN AUTO_START_CHROME TEST_SUITE Test_Suite SUITE \
  CUCUMBER_TAGS

DEFAULT_APP_URL="https://approuter-twenty5ipe-dev.cfapps.us10.hana.ondemand.com/#quote"
APP_URL="${APP_URL:-${SELECTED_APP_URL:-$DEFAULT_APP_URL}}"
CHROME_DEBUG_PORT="${CHROME_DEBUG_PORT:-9222}"
USE_DEBUG_CHROME="${USE_DEBUG_CHROME:-true}"
LOCAL_RUN="${LOCAL_RUN:-true}"
DEBUG_HOLD_BROWSER="${DEBUG_HOLD_BROWSER:-true}"
CHROME_PROFILE="${CHROME_PROFILE:-$HOME/.selenium-ai-chrome}"
CHROME_BIN="${CHROME_BIN:-/Applications/Google Chrome.app/Contents/MacOS/Google Chrome}"
METRICS_ENABLED="${METRICS_ENABLED:-false}"
CLEAN_FIRST="${CLEAN_FIRST:-}"
STOP_ON_FAILURE="${STOP_ON_FAILURE:-false}"
DRY_RUN="${DRY_RUN:-false}"

if [[ -z "${AUTO_START_CHROME+x}" ]]; then
  AUTO_START_CHROME="false"
fi

# shellcheck source=scripts/lib/chrome-debug.sh
source "$SCRIPT_DIR/lib/chrome-debug.sh"

usage() {
  cat <<EOF
Usage:
  APP_URL=<url> [CUCUMBER_TAGS=@tag] scripts/run-twentyfive-test.sh
  scripts/run-twentyfive-test.sh @TC-001
  scripts/run-twentyfive-test.sh "Manufacturing Proposal"
  scripts/run-twentyfive-test.sh --suite "Manufacturing Proposal"
  scripts/run-twentyfive-test.sh --list-suites

Environment:
  APP_URL             Target base URL. Defaults to $DEFAULT_APP_URL.
  CUCUMBER_TAGS       Cucumber tag filter. Defaults to @TC-001.
  TEST_SUITE          Suite name to run, for example "Manufacturing Proposal".
  USE_DEBUG_CHROME    true attaches to Chrome on localhost:9222. Default true.
  LOCAL_RUN           Pass -Dlocal.run=true. Default true.
  DEBUG_HOLD_BROWSER  Pass -Ddebug=true. Default true when USE_DEBUG_CHROME=true.
  AUTO_START_CHROME   Start Chrome debug profile if port is not listening. Default false.
                      Set to false to require a manually started debug browser.
  CHROME_PROFILE      Chrome profile used for Selenium attachment.
  METRICS_ENABLED     Enable InfluxDB writes. Default false for local runner.
  CLEAN_FIRST         Run "mvn clean test" for the first tag. Default true for suites.
  STOP_ON_FAILURE     Stop a suite after the first failing tag. Default false.
  DRY_RUN             Print the resolved Maven runs without launching Chrome or Maven.
EOF
}

normalize_suite_name() {
  local value="$1"
  value="${value//&amp;/&}"
  value="${value//&quot;/\"}"
  value="${value//&#39;/\'}"
  value="${value//&apos;/\'}"
  printf '%s' "$value" | tr '[:upper:]' '[:lower:]' | sed 's/[[:space:]]\+/ /g; s/^ //; s/ $//'
}

canonical_suite_name() {
  case "$(normalize_suite_name "$1")" in
    "smoke tests") echo "Smoke Tests" ;;
    "professional services proposal") echo "Professional Services Proposal" ;;
    "manufacturing proposal") echo "Manufacturing Proposal" ;;
    "copy prior manufacturing proposal") echo "Copy Prior Manufacturing Proposal" ;;
    "distribute labor costs using manual effort input") echo "Distribute Labor Costs using Manual Effort input" ;;
    "check that labor inflation is calculated correctly over the length of the proposal") echo "Check that Labor Inflation is calculated correctly over the length of the proposal" ;;
    "bom import") echo "BOM Import" ;;
    "excel bom import") echo "Excel BOM Import" ;;
    "performance") echo "Performance" ;;
    *) return 1 ;;
  esac
}

suite_tags() {
  case "$(normalize_suite_name "$1")" in
    "smoke tests")
      printf '%s\n' "@SMOKE-TEST"
      ;;
    "professional services proposal")
      printf '%s\n' \
        "@SETUP-PROFF-SERVICES" \
        "@COST-CREATION-PROFF-SERVICES" \
        "@PRICING-ASSIGNMENT-PROFF-SERVICES"
      ;;
    "manufacturing proposal")
      printf '%s\n' \
        "@SETUP-MANUFACTURING" \
        "@COST-CREATION-MANUFACTURING_PROPOSAL" \
        "@OVERHEAD-COST-CREATION-MANUFACTURING_PROPOSAL" \
        "@AMORTIZATION-DEPRECIATION-MANUFACTURING-PROPOSAL"
      ;;
    "copy prior manufacturing proposal")
      printf '%s\n' "@copyManufacturingPriorProposal"
      ;;
    "distribute labor costs using manual effort input")
      printf '%s\n' "@DistributeLaborCosts"
      ;;
    "check that labor inflation is calculated correctly over the length of the proposal")
      printf '%s\n' "@laborInflationProposal"
      ;;
    "bom import")
      printf '%s\n' "@basicImportBOM"
      ;;
    "excel bom import")
      printf '%s\n' "@excelBOMImport"
      ;;
    "performance")
      printf '%s\n' "@FCP"
      ;;
    *)
      return 1
      ;;
  esac
}

print_suites() {
  cat <<EOF
Available Twenty5 test suites:
  Smoke Tests
    @SMOKE-TEST
  Professional Services Proposal
    @SETUP-PROFF-SERVICES
    @COST-CREATION-PROFF-SERVICES
    @PRICING-ASSIGNMENT-PROFF-SERVICES
  Manufacturing Proposal
    @SETUP-MANUFACTURING
    @COST-CREATION-MANUFACTURING_PROPOSAL
    @OVERHEAD-COST-CREATION-MANUFACTURING_PROPOSAL
    @AMORTIZATION-DEPRECIATION-MANUFACTURING-PROPOSAL
  Copy Prior Manufacturing Proposal
    @copyManufacturingPriorProposal
  Distribute Labor Costs using Manual Effort input
    @DistributeLaborCosts
  Check that Labor Inflation is calculated correctly over the length of the proposal
    @laborInflationProposal
  BOM Import
    @basicImportBOM
  Excel BOM Import
    @excelBOMImport
  Performance
    @FCP
EOF
}

safe_log_token() {
  printf '%s' "$1" | tr -c 'A-Za-z0-9_.-' '_'
}

REQUEST_MODE=""
REQUEST_VALUE=""

while [[ $# -gt 0 ]]; do
  case "$1" in
    -h|--help)
      usage
      exit 0
      ;;
    --list-suites)
      print_suites
      exit 0
      ;;
    --suite)
      if [[ $# -lt 2 ]]; then
        echo "ERROR: --suite requires a suite name." >&2
        exit 2
      fi
      REQUEST_MODE="suite"
      REQUEST_VALUE="$2"
      shift 2
      ;;
    --tag|--tags)
      if [[ $# -lt 2 ]]; then
        echo "ERROR: $1 requires a Cucumber tag/filter." >&2
        exit 2
      fi
      REQUEST_MODE="tags"
      REQUEST_VALUE="$2"
      shift 2
      ;;
    *)
      if [[ -n "$REQUEST_VALUE" ]]; then
        echo "ERROR: Unexpected extra argument: $1" >&2
        usage >&2
        exit 2
      fi
      REQUEST_MODE="auto"
      REQUEST_VALUE="$1"
      shift
      ;;
  esac
done

REQUESTED_SUITE="${TEST_SUITE:-${Test_Suite:-${SUITE:-}}}"
REQUESTED_TAGS="${CUCUMBER_TAGS:-}"

if [[ -n "$REQUEST_VALUE" ]]; then
  case "$REQUEST_MODE" in
    suite)
      REQUESTED_SUITE="$REQUEST_VALUE"
      REQUESTED_TAGS=""
      ;;
    tags)
      REQUESTED_TAGS="$REQUEST_VALUE"
      REQUESTED_SUITE=""
      ;;
    auto)
      if canonical_suite_name "$REQUEST_VALUE" >/dev/null 2>&1; then
        REQUESTED_SUITE="$REQUEST_VALUE"
        REQUESTED_TAGS=""
      else
        REQUESTED_TAGS="$REQUEST_VALUE"
        REQUESTED_SUITE=""
      fi
      ;;
  esac
fi

RUNNING_SUITE="false"
SUITE_NAME=""
TAGS=()

if [[ -n "$REQUESTED_SUITE" ]]; then
  if ! SUITE_NAME="$(canonical_suite_name "$REQUESTED_SUITE")"; then
    echo "ERROR: Unknown test suite: $REQUESTED_SUITE" >&2
    echo >&2
    print_suites >&2
    exit 2
  fi
  while IFS= read -r tag; do
    TAGS+=("$tag")
  done < <(suite_tags "$SUITE_NAME")
  RUNNING_SUITE="true"
elif [[ -n "$REQUESTED_TAGS" ]]; then
  TAGS=("$REQUESTED_TAGS")
else
  TAGS=("@TC-001")
fi

if [[ -z "$CLEAN_FIRST" ]]; then
  if [[ "$RUNNING_SUITE" == "true" ]]; then
    CLEAN_FIRST="true"
  else
    CLEAN_FIRST="false"
  fi
fi

if [[ ! -d "$TEST_ROOT" ]]; then
  echo "ERROR: Test root not found: $TEST_ROOT" >&2
  exit 2
fi

if [[ "$USE_DEBUG_CHROME" == "true" && "$DRY_RUN" != "true" ]]; then
  ensure_chrome_debug "$APP_URL" "$AUTO_START_CHROME" "${ASSUME_YES:-false}"
fi

cd "$TEST_ROOT"

LOGS_DIR="$REPO_ROOT/reports/logs"
mkdir -p "$LOGS_DIR"

run_tag() {
  local tag="$1"
  local index="$2"
  local total="$3"
  local goals=(test)
  local mvn_exit
  local safe_tag
  local log_file
  local mvn_args

  if [[ "$CLEAN_FIRST" == "true" && "$index" -eq 0 ]]; then
    goals=(clean test)
  fi

  safe_tag="$(safe_log_token "$tag")"
  log_file="$LOGS_DIR/mvn_$(date +%Y%m%d_%H%M%S)_$((index + 1))_${safe_tag}.log"

  mvn_args=(
    -pl tests
    -am
    "${goals[@]}"
    -Dtest=RunTest
    -Dsurefire.failIfNoSpecifiedTests=false
    "-Dcucumber.filter.tags=$tag"
    "-Denv=$APP_URL"
    "-Dlocal.run=$LOCAL_RUN"
    "-Dmetrics.enabled=$METRICS_ENABLED"
  )

  if [[ "$USE_DEBUG_CHROME" == "true" && "$DEBUG_HOLD_BROWSER" == "true" ]]; then
    mvn_args+=("-Ddebug=true")
  fi

  echo "Running Twenty5 test $((index + 1))/$total"
  if [[ "$RUNNING_SUITE" == "true" ]]; then
    echo "  suite: $SUITE_NAME"
  fi
  echo "  root:  $TEST_ROOT"
  echo "  url:   $APP_URL"
  if [[ "$APP_URL" == "$DEFAULT_APP_URL" ]]; then
    echo "  url source: default; override with APP_URL=<url> if needed"
  fi
  echo "  tags:  $tag"
  echo "  goals: ${goals[*]}"
  echo "  log:   $log_file"
  echo

  if [[ "$DRY_RUN" == "true" ]]; then
    printf '  command: mvn'
    printf ' %q' "${mvn_args[@]}"
    printf '\n\n'
    return 0
  fi

  if mvn "${mvn_args[@]}" >"$log_file" 2>&1; then
    mvn_exit=0
  else
    mvn_exit=$?
  fi

  if [[ -f "$TEST_ROOT/tests/target/cucumber-reports/Cucumber.json" ]]; then
    python3 "$SCRIPT_DIR/generate-report.py" \
      "$TEST_ROOT/tests/target/cucumber-reports/Cucumber.json" || true
  else
    echo "WARN: Cucumber JSON report not found after tag $tag"
  fi

  return "$mvn_exit"
}

if [[ "$RUNNING_SUITE" == "true" ]]; then
  echo "Running Twenty5 test suite: $SUITE_NAME"
  echo "Suite tags will run one by one:"
  for tag in "${TAGS[@]}"; do
    echo "  $tag"
  done
  echo
fi

OVERALL_EXIT=0
for i in "${!TAGS[@]}"; do
  if run_tag "${TAGS[$i]}" "$i" "${#TAGS[@]}"; then
    :
  else
    TAG_EXIT=$?
    [[ "$OVERALL_EXIT" -eq 0 ]] && OVERALL_EXIT="$TAG_EXIT"
    if [[ "$STOP_ON_FAILURE" == "true" ]]; then
      exit "$OVERALL_EXIT"
    fi
  fi
done

exit "$OVERALL_EXIT"
