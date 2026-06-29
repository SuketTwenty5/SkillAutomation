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
echo "Preparing recording browser with the configured test-user login."
node scripts/prepare-playwright-recording-auth.mjs "${APP_URL}"
echo "Close the Playwright/codegen browser window when finished."

npx playwright codegen \
  --user-data-dir="${CODEGEN_PROFILE_DIR}" \
  --target=javascript \
  --output="${OUT_FILE}" \
  "${APP_URL}"
