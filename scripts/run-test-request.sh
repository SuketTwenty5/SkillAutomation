#!/usr/bin/env bash
set -euo pipefail

SCRIPT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"
REPO_ROOT="$(cd "$SCRIPT_DIR/.." && pwd)"
ENV_FILE="${ENV_FILE:-$REPO_ROOT/.skillautomation.env}"
TEST_ROOT="${TEST_ROOT:-$REPO_ROOT/imported/twentyfive-regtest}"
RESOLVER="$SCRIPT_DIR/resolve-test-request.py"

CALLER_APP_URL_IS_SET="false"
CALLER_APP_URL="${APP_URL:-}"
if [[ "${APP_URL+x}" == "x" ]]; then
  CALLER_APP_URL_IS_SET="true"
fi

# shellcheck source=scripts/lib/env.sh
source "$SCRIPT_DIR/lib/env.sh"
source_env_preserving_caller "$ENV_FILE" \
  APP_URL CHROME_DEBUG_PORT CHROME_PROFILE CHROME_BIN AUTO_START_CHROME \
  LOGIN_WAIT_SECONDS CLIN_UPLOAD_FILE

# shellcheck source=scripts/lib/chrome-debug.sh
source "$SCRIPT_DIR/lib/chrome-debug.sh"

ASSUME_YES="false"
DRY_RUN_REQUEST="false"
NO_BROWSER="false"
URL_OVERRIDE=""
REQUEST_PARTS=()

usage() {
  cat <<EOF
Usage:
  scripts/run-test-request.sh "run TC-Prof-Services-001: Verify Proposal Setup Layout"
  scripts/run-test-request.sh "Manufacturing Proposal"
  scripts/run-test-request.sh --url "https://app-twenty5ipe-lm-dev.cfapps.us10.hana.ondemand.com/" "TC-CLIN-UPLOAD-IMPORT"
  scripts/run-test-request.sh --list

Options:
  --url <url>     Use an alternate app URL for this run.
  -y, --yes       Accept the resolved default URL and launch Chrome if needed.
  --dry-run       Resolve and print the underlying command without running Maven/Node.
  --no-browser    Do not launch Chrome; fail if the debug endpoint is not already ready.
  --list          List mapped tests and suites.
EOF
}

expand_path() {
  local value="$1"
  if [[ "$value" == "~/"* ]]; then
    printf '%s/%s' "$HOME" "${value:2}"
  else
    printf '%s' "$value"
  fi
}

require_url() {
  local url="$1"
  if [[ ! "$url" =~ ^https?:// ]]; then
    echo "ERROR: URL must start with http:// or https://: $url" >&2
    exit 2
  fi
}

while [[ $# -gt 0 ]]; do
  case "$1" in
    -h|--help)
      usage
      exit 0
      ;;
    --list)
      "$RESOLVER" --list
      exit 0
      ;;
    -y|--yes)
      ASSUME_YES="true"
      shift
      ;;
    --dry-run)
      DRY_RUN_REQUEST="true"
      shift
      ;;
    --no-browser)
      NO_BROWSER="true"
      shift
      ;;
    --url=*)
      URL_OVERRIDE="${1#--url=}"
      shift
      ;;
    --url)
      if [[ $# -lt 2 ]]; then
        echo "ERROR: --url requires a value." >&2
        exit 2
      fi
      URL_OVERRIDE="$2"
      shift 2
      ;;
    *)
      REQUEST_PARTS+=("$1")
      shift
      ;;
  esac
done

REQUEST="${REQUEST_PARTS[*]:-}"
if [[ -z "$REQUEST" ]]; then
  usage >&2
  exit 2
fi

if RESOLVED_SHELL="$("$RESOLVER" --shell "$REQUEST")"; then
  :
else
  exit $?
fi
eval "$RESOLVED_SHELL"

RUN_URL="$RESOLVED_DEFAULT_URL"
if [[ -n "$URL_OVERRIDE" ]]; then
  RUN_URL="$URL_OVERRIDE"
elif [[ "$CALLER_APP_URL_IS_SET" == "true" && -n "$CALLER_APP_URL" ]]; then
  RUN_URL="$CALLER_APP_URL"
fi
require_url "$RUN_URL"

if [[ "$ASSUME_YES" != "true" && "$DRY_RUN_REQUEST" != "true" && -t 0 && -z "$URL_OVERRIDE" && "$CALLER_APP_URL_IS_SET" != "true" ]]; then
  echo "Resolved request: $RESOLVED_ID - $RESOLVED_TITLE"
  echo "Default URL: $RUN_URL"
  read -r -p "Press Enter to use this URL, or paste an alternate URL: " alternate_url
  if [[ -n "$alternate_url" ]]; then
    RUN_URL="$alternate_url"
    require_url "$RUN_URL"
  fi
fi

echo "Resolved request:"
echo "  id:      $RESOLVED_ID"
echo "  title:   $RESOLVED_TITLE"
echo "  runner:  $RESOLVED_RUNNER"
echo "  url:     $RUN_URL"
if [[ -n "$RESOLVED_GUIDE" ]]; then
  echo "  guide:   $RESOLVED_GUIDE"
fi
if [[ "${#RESOLVED_TAGS[@]}" -gt 0 ]]; then
  echo "  tags:    ${RESOLVED_TAGS[*]}"
fi
echo

for ((i = 0; i < RESOLVED_REQUIRED_FILE_COUNT; i += 1)); do
  name_var="RESOLVED_REQUIRED_FILE_${i}_NAME"
  env_var="RESOLVED_REQUIRED_FILE_${i}_ENV"
  default_var="RESOLVED_REQUIRED_FILE_${i}_DEFAULT"
  file_name="${!name_var}"
  env_name="${!env_var}"
  default_path="$(expand_path "${!default_var}")"
  candidate="$default_path"
  if [[ -n "$env_name" ]]; then
    env_value="${!env_name:-}"
    if [[ -n "$env_value" ]]; then
      candidate="$(expand_path "$env_value")"
    fi
  fi
  if [[ ! -f "$candidate" ]]; then
    cat >&2 <<EOF
ERROR: Missing required file for $RESOLVED_ID: $file_name

Expected:
  $candidate

Set it for this run with:
  $env_name="/path/to/file.xlsx" scripts/run-test-request.sh "$REQUEST"
EOF
    exit 4
  fi
  if [[ -n "$env_name" ]]; then
    export "$env_name=$candidate"
  fi
done

if [[ "$RESOLVED_RUNNER" == "cucumber" && "${#RESOLVED_TAGS[@]}" -gt 0 ]]; then
  FEATURE_ROOT="$TEST_ROOT/tests/src/test/resources/features"
  for tag in "${RESOLVED_TAGS[@]}"; do
    if [[ "$tag" == @* ]] && ! rg -Fq "$tag" "$FEATURE_ROOT"; then
      echo "ERROR: Cucumber tag was not found in feature files: $tag" >&2
      exit 5
    fi
  done
fi

if [[ "$DRY_RUN_REQUEST" != "true" && "$NO_BROWSER" != "true" ]]; then
  if ! chrome_debug_ready; then
    launch="true"
    if [[ "$ASSUME_YES" != "true" && -t 0 ]]; then
      read -r -p "Chrome debug is not running. Launch it with the URL above? [Y/n] " answer
      case "$answer" in
        n|N|no|NO|No) launch="false" ;;
      esac
    fi
    ensure_chrome_debug "$RUN_URL" "$launch" "$ASSUME_YES"
  else
    echo "Chrome debug endpoint is already ready at http://127.0.0.1:$CHROME_DEBUG_PORT."
  fi
fi

if [[ "$RESOLVED_RUNNER" == "cucumber" ]]; then
  if [[ "$RESOLVED_KIND" == "suite" ]]; then
    cmd=( "$SCRIPT_DIR/run-twentyfive-test.sh" --suite "$RESOLVED_NAME" )
  else
    cmd=( "$SCRIPT_DIR/run-twentyfive-test.sh" --tag "${RESOLVED_TAGS[0]}" )
  fi

  if [[ "$DRY_RUN_REQUEST" == "true" ]]; then
    echo "Command:"
    printf '  APP_URL=%q DRY_RUN=true AUTO_START_CHROME=false' "$RUN_URL"
    printf ' %q' "${cmd[@]}"
    printf '\n'
    APP_URL="$RUN_URL" DRY_RUN=true AUTO_START_CHROME=false "${cmd[@]}"
  else
    APP_URL="$RUN_URL" AUTO_START_CHROME=false "${cmd[@]}"
  fi
elif [[ "$RESOLVED_RUNNER" == "master-data" ]]; then
  cmd=( "$SCRIPT_DIR/run-master-data-test.sh" "${RESOLVED_ARGS[@]}" )
  if [[ "$DRY_RUN_REQUEST" == "true" ]]; then
    echo "Command:"
    printf '  MASTER_DATA_APP_URL=%q AUTO_START_CHROME=false' "$RUN_URL"
    printf ' %q' "${cmd[@]}"
    printf '\n'
  else
    MASTER_DATA_APP_URL="$RUN_URL" AUTO_START_CHROME=false "${cmd[@]}"
  fi
else
  echo "ERROR: Unsupported runner: $RESOLVED_RUNNER" >&2
  exit 2
fi
