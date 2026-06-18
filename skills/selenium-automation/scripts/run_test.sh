#!/usr/bin/env bash
set -euo pipefail

ROOT="${1:-.}"
TEST_NAME="${2:-}"
STAMP="$(date +%Y%m%d-%H%M%S)"
EVIDENCE_DIR="$ROOT/test-evidence/$STAMP"

mkdir -p "$EVIDENCE_DIR"

if [[ -z "$TEST_NAME" ]]; then
  echo "Usage: run_test.sh <repo-root> <test-class-or-pattern>"
  exit 2
fi

cd "$ROOT"

if [[ -x "./gradlew" ]]; then
  ./gradlew test --tests "$TEST_NAME" 2>&1 | tee "$EVIDENCE_DIR/test.log"
elif [[ -f "pom.xml" ]]; then
  mvn test -Dtest="$TEST_NAME" 2>&1 | tee "$EVIDENCE_DIR/test.log"
else
  echo "No Gradle wrapper or Maven pom.xml found" | tee "$EVIDENCE_DIR/test.log"
  exit 3
fi

echo "Evidence: $EVIDENCE_DIR"
