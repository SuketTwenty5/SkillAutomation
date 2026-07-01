#!/usr/bin/env node
// pom-verify.mjs — COMPUTED trust for locators.
//
// Reads Playwright traces from a verification run (run tests with POM_VERIFY=1 so green runs
// emit a trace) and records which selectors actually resolved on a successful action into
// tests/playwright/support/pom/.verified.json. That file is GENERATED, never hand-edited, and
// contains only selectors + run metadata (never new selector strings authored here), so it
// cannot drift from the code. A locator is "verified" iff it appears here from the latest run;
// trust lapses automatically when a run is red or absent.
//
// Usage: POM_VERIFY=1 scripts/run-playwright-test.sh <spec> ; then: node scripts/pom-verify.mjs

import fs from 'node:fs';
import path from 'node:path';
import { fileURLToPath } from 'node:url';
import { execFileSync } from 'node:child_process';

const here = path.dirname(fileURLToPath(import.meta.url));
const REPO = path.resolve(here, '..');
const RESULTS_DIR = path.join(REPO, 'test-results');
const OUT = path.join(REPO, 'tests/playwright/support/pom/.verified.json');

function findTraceZips(dir) {
  const found = [];
  if (!fs.existsSync(dir)) return found;
  for (const entry of fs.readdirSync(dir, { withFileTypes: true })) {
    const full = path.join(dir, entry.name);
    if (entry.isDirectory()) found.push(...findTraceZips(full));
    else if (entry.name === 'trace.zip') found.push(full);
  }
  return found;
}

function readTraceEvents(zip) {
  // Playwright trace.zip stores newline-delimited JSON in one or more *.trace entries
  // (e.g. test.trace, 0-trace.trace). Read them all via unzip -p.
  const events = [];
  try {
    const listing = execFileSync('unzip', ['-Z1', zip], { encoding: 'utf8' });
    const traceEntries = listing.split('\n').filter((name) => name.endsWith('.trace'));
    for (const entry of traceEntries) {
      const raw = execFileSync('unzip', ['-p', zip, entry], { maxBuffer: 512 * 1024 * 1024, encoding: 'utf8' });
      for (const line of raw.split('\n')) {
        if (!line) continue;
        try {
          events.push(JSON.parse(line));
        } catch {
          /* skip non-JSON lines */
        }
      }
    }
  } catch (error) {
    console.warn(`WARN: could not read ${zip}: ${error instanceof Error ? error.message : error}`);
  }
  return events;
}

function specNameFromPath(zip) {
  const rel = path.relative(RESULTS_DIR, zip);
  return rel.split(path.sep)[0] || rel;
}

function main() {
  const stampArg = process.argv.find((a) => a.startsWith('--at='));
  const generatedAt = stampArg ? stampArg.slice('--at='.length) : new Date().toISOString();

  const zips = findTraceZips(RESULTS_DIR);
  if (zips.length === 0) {
    console.warn(`No trace.zip found under ${RESULTS_DIR}.`);
    console.warn('Run a verification pass first:  POM_VERIFY=1 scripts/run-playwright-test.sh <spec> --retries=0');
    fs.writeFileSync(OUT, JSON.stringify({ generatedAt, traces: 0, selectorCount: 0, selectors: {} }, null, 2) + '\n');
    console.log(`Wrote empty ${OUT}.`);
    return;
  }

  const selectors = {};
  for (const zip of zips) {
    const spec = specNameFromPath(zip);
    const events = readTraceEvents(zip);
    const pending = new Map(); // callId -> selector

    for (const ev of events) {
      const selector = ev?.params?.selector;
      if ((ev.type === 'before' || ev.type === 'action') && ev.callId && typeof selector === 'string') {
        pending.set(ev.callId, selector);
      }
      if (ev.type === 'after' && ev.callId && pending.has(ev.callId)) {
        const selector2 = pending.get(ev.callId);
        pending.delete(ev.callId);
        if (!ev.error) {
          const rec = (selectors[selector2] ??= { count: 0, specs: [] });
          rec.count += 1;
          if (!rec.specs.includes(spec)) rec.specs.push(spec);
        }
      }
    }
  }

  const payload = { generatedAt, traces: zips.length, selectorCount: Object.keys(selectors).length, selectors };
  fs.writeFileSync(OUT, JSON.stringify(payload, null, 2) + '\n');
  console.log(`Wrote ${OUT}: ${payload.selectorCount} verified selectors from ${zips.length} trace(s).`);
}

main();
