#!/usr/bin/env bash
set -euo pipefail

APP_URL="${1:-https://approuter-twenty5ipe-dev.cfapps.us10.hana.ondemand.com/#quote}"
STAMP="$(date +%Y%m%d_%H%M%S)"
OUT_DIR="playwrightRecording/${STAMP}"
OUT_FILE="${OUT_DIR}/recording.spec.js"
CODEGEN_PROFILE_DIR="${PLAYWRIGHT_CODEGEN_PROFILE_DIR:-.skillautomation/codegen-chrome}"

mkdir -p "${OUT_DIR}" "${CODEGEN_PROFILE_DIR}"

echo "Recording folder: ${OUT_DIR}"
echo "Recording file: ${OUT_FILE}"

# Default: launch codegen directly and let the consultant log in manually in the
# codegen window. The persistent profile (.skillautomation/codegen-chrome) keeps
# the session, so a previously signed-in profile opens already logged in.
# Set RECORD_AUTO_LOGIN=1 to run the auto-login preflight instead.
if [[ "${RECORD_AUTO_LOGIN:-0}" == "1" ]]; then
  echo "Preparing recording browser with the configured test-user login."
  node scripts/prepare-playwright-recording-auth.mjs "${APP_URL}"
fi

echo "Log in manually if prompted, then begin the steps. Close the codegen browser window when finished."

npx playwright codegen \
  --user-data-dir="${CODEGEN_PROFILE_DIR}" \
  --target=javascript \
  --output="${OUT_FILE}" \
  "${APP_URL}"
