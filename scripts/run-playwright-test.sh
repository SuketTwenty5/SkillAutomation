#!/usr/bin/env bash
set -euo pipefail

SCRIPT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"
REPO_ROOT="$(cd "$SCRIPT_DIR/.." && pwd)"

choose_node() {
  local candidate
  local major

  if [[ -n "${PLAYWRIGHT_NODE:-}" ]]; then
    printf '%s\n' "$PLAYWRIGHT_NODE"
    return 0
  fi

  for candidate in /usr/local/bin/node /opt/homebrew/bin/node "$(command -v node)"; do
    [[ -x "$candidate" ]] || continue
    major="$("$candidate" -p "process.versions.node.split('.')[0]" 2>/dev/null || true)"
    [[ "$major" =~ ^[0-9]+$ ]] || continue
    if [[ "$major" -ge 18 && "$major" -ne 23 ]]; then
      printf '%s\n' "$candidate"
      return 0
    fi
  done

  command -v node
}

NODE_BIN="$(choose_node)"
NODE_MAJOR="$("$NODE_BIN" -p "process.versions.node.split('.')[0]")"
if [[ "$NODE_MAJOR" == "23" ]]; then
  echo "WARN: Running Playwright with Node 23. Playwright 1.61 may fail loading TypeScript tests on Node 23." >&2
fi

cd "$REPO_ROOT"

# POM guard: fail fast if any enforced spec contains inline locators (bypass with POM_GUARD=off).
if [[ "${POM_GUARD:-on}" != "off" && -x "$REPO_ROOT/node_modules/.bin/eslint" ]]; then
  echo "==> POM guard: checking specs for inline locators (set POM_GUARD=off to bypass)"
  if ! "$REPO_ROOT/node_modules/.bin/eslint" "tests/playwright/**/*.spec.ts"; then
    echo "ERROR: POM guard failed — a spec contains inline locators. Move them into tests/playwright/support/pom/ and call the page-object method." >&2
    exit 1
  fi
fi

# Type-check gate: catch build-time errors before launching the browser (bypass with TYPECHECK=off).
# Notably turns "a BasePage method called on ProposalSetupPage (which does not extend BasePage)" from a
# runtime "is not a function" crash into a fast build failure.
if [[ "${TYPECHECK:-on}" != "off" && -f "$REPO_ROOT/node_modules/typescript/bin/tsc" ]]; then
  echo "==> Type-check: tsc --noEmit (set TYPECHECK=off to bypass)"
  if ! "$NODE_BIN" "$REPO_ROOT/node_modules/typescript/bin/tsc" --noEmit -p "$REPO_ROOT/tsconfig.json"; then
    echo "ERROR: Type-check failed — fix the TypeScript errors above before running." >&2
    exit 1
  fi
fi

"$NODE_BIN" "$REPO_ROOT/node_modules/playwright/cli.js" test "$@"
