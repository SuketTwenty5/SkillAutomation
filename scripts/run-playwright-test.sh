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
"$NODE_BIN" "$REPO_ROOT/node_modules/playwright/cli.js" test "$@"
