#!/usr/bin/env node
// find-historical-locator.mjs — PULL-ONLY lookup into the historical locator catalog.
//
// The catalog (references/ipe-auto-tests-locators.json, 709 entries flattened from the OLD
// Java "Golden Dev" build) is a NAMING + INTENT seed, NOT a source of ready-to-run locators.
// This tool PRINTS suggestions (className, field, sanitized label, xpath, brittleness) so an
// author can name a POM getter and understand intent. It NEVER writes to the POM.
//
// EVERY suggested xpath is UNVERIFIED against the live app and MUST be re-anchored with
// scan-page-locators.mjs (preferring visible label / role) before it enters a page object.
//
// Usage:
//   node scripts/find-historical-locator.mjs "<query>"          # match class/field/label
//   node scripts/find-historical-locator.mjs --class SetUpPage  # filter by class
//   node scripts/find-historical-locator.mjs --label "New" --limit 20
//
// Options: --class <c>  --field <f>  --label <l>  --limit <n>  --json

import fs from 'node:fs';
import path from 'node:path';
import { fileURLToPath } from 'node:url';

const here = path.dirname(fileURLToPath(import.meta.url));
const CATALOG = path.resolve(here, '../references/ipe-auto-tests-locators.json');

function parseArgs(argv) {
  const opts = { limit: 25, json: false, query: '', class: '', field: '', label: '' };
  const rest = [];
  for (let i = 0; i < argv.length; i += 1) {
    const a = argv[i];
    if (a === '--json') opts.json = true;
    else if (a === '--limit') opts.limit = Number(argv[++i]) || opts.limit;
    else if (a === '--class') opts.class = argv[++i] ?? '';
    else if (a === '--field') opts.field = argv[++i] ?? '';
    else if (a === '--label') opts.label = argv[++i] ?? '';
    else rest.push(a);
  }
  opts.query = rest.join(' ').trim();
  return opts;
}

/** Strip the stray trailing apostrophe seen in ~658 Java-sourced elementName values. */
function sanitizeLabel(name) {
  return String(name ?? '').replace(/['"]\s*$/, '').trim();
}

/** Classify an xpath's robustness per the SKILL locator ladder. */
function classify(xpath = '') {
  const x = String(xpath);
  if (/\bcontains\(@id,\s*'iBE/i.test(x) || /@id='iBE/i.test(x)) return { tier: 'stable-id', note: 'app-authored iBE* id (acceptable stable attribute)' };
  if (/text\(\)\s*=/.test(x) || /contains\(\s*text\(\)/.test(x) || /normalize-space\(\)\s*=/.test(x)) return { tier: 'robust', note: 'visible-text anchored' };
  if (/@placeholder|@aria-label/.test(x)) return { tier: 'robust', note: 'placeholder/aria-label anchored' };
  if (/@role='dialog'\s+and\s+@aria-hidden='false'\]\s*$/.test(x)) return { tier: 'brittle', note: 'generic dialog scope — NOT a unique element' };
  if (/\[\s*[2-9]\d*\s*\]/.test(x)) return { tier: 'brittle', note: 'positional index (fragile)' };
  if (/contains\(@class,\s*'x-/.test(x)) return { tier: 'brittle', note: 'ExtJS x-* class (build-coupled)' };
  if (/@role=|@aria-/.test(x)) return { tier: 'brittle', note: 'role/aria scope only, no text anchor' };
  return { tier: 'review', note: 'unclassified — inspect manually' };
}

function main() {
  if (!fs.existsSync(CATALOG)) {
    console.error(`Catalog not found: ${CATALOG}`);
    process.exit(1);
  }
  const opts = parseArgs(process.argv.slice(2));
  if (!opts.query && !opts.class && !opts.field && !opts.label) {
    console.error('Provide a query or one of --class/--field/--label. See header for usage.');
    process.exit(2);
  }

  const entries = JSON.parse(fs.readFileSync(CATALOG, 'utf8'));
  const q = opts.query.toLowerCase();

  const matches = entries.filter((e) => {
    if (opts.class && !String(e.className ?? '').toLowerCase().includes(opts.class.toLowerCase())) return false;
    if (opts.field && !String(e.field ?? '').toLowerCase().includes(opts.field.toLowerCase())) return false;
    if (opts.label && !sanitizeLabel(e.elementName).toLowerCase().includes(opts.label.toLowerCase())) return false;
    if (!q) return true;
    const hay = `${e.className} ${e.field} ${sanitizeLabel(e.elementName)}`.toLowerCase();
    return hay.includes(q);
  });

  // Robust suggestions first, brittle last.
  const order = { 'robust': 0, 'stable-id': 1, 'review': 2, 'brittle': 3 };
  const enriched = matches.map((e) => ({ ...e, label: sanitizeLabel(e.elementName), ...classify(e.xpath) }));
  enriched.sort((a, b) => (order[a.tier] ?? 9) - (order[b.tier] ?? 9));
  const shown = enriched.slice(0, opts.limit);

  if (opts.json) {
    console.log(JSON.stringify(shown, null, 2));
    return;
  }

  console.log(`\n${matches.length} match(es) for "${opts.query || [opts.class, opts.field, opts.label].filter(Boolean).join(' ')}" — showing ${shown.length}\n`);
  for (const e of shown) {
    console.log(`• ${e.className}.${e.field}  [${e.tier}: ${e.note}]`);
    console.log(`    label:  ${e.label}`);
    console.log(`    xpath:  ${e.xpath}`);
    console.log(`    suggest getter: /** @label ${e.label} */  (id: '${e.className}.${e.field}')`);
    console.log('');
  }
  if (matches.length > shown.length) console.log(`… ${matches.length - shown.length} more (raise --limit).\n`);
  console.warn('REMINDER: these xpaths are UNVERIFIED against the live app. Re-anchor via scan-page-locators.mjs');
  console.warn('(prefer visible label / role) before adding any of these to a page object.');
}

main();
