#!/usr/bin/env node
import fs from 'node:fs';
import path from 'node:path';

const query = process.argv.slice(2).join(' ').trim();

if (!query) {
  console.error('Usage: node skills/skill-playwright-automation/scripts/find-source-artifact.mjs "<source URL, path, or title>"');
  process.exit(2);
}

const workspace = process.cwd();
const indexPath = path.join(workspace, 'skills/skill-playwright-automation/references/source-artifacts.json');

function normalize(value) {
  return String(value || '')
    .toLowerCase()
    .replace(/^file:\/\//, '')
    .replace(/[?#].*$/, '')
    .replace(/[^a-z0-9]+/g, ' ')
    .trim();
}

function pathExists(relativePath) {
  return fs.existsSync(path.join(workspace, relativePath));
}

function scoreEntry(entry) {
  const normalizedQuery = normalize(query);
  const normalizedSource = normalize(entry.source);
  const normalizedTitle = normalize(entry.title);
  const normalizedId = normalize(entry.id);

  if (String(entry.source) === query) return { confidence: 'exact', score: 100 };
  if (normalizedSource && normalizedSource === normalizedQuery) return { confidence: 'url', score: 95 };
  if (normalizedTitle && normalizedTitle === normalizedQuery) return { confidence: 'title', score: 90 };
  if (normalizedId && normalizedId === normalizedQuery) return { confidence: 'title', score: 85 };
  if (normalizedQuery && normalizedSource.includes(normalizedQuery)) return { confidence: 'partial', score: 70 };
  if (normalizedQuery && normalizedTitle.includes(normalizedQuery)) return { confidence: 'partial', score: 65 };
  if (normalizedQuery && normalizedId.includes(normalizedQuery)) return { confidence: 'partial', score: 60 };
  return { confidence: 'none', score: 0 };
}

const index = fs.existsSync(indexPath)
  ? JSON.parse(fs.readFileSync(indexPath, 'utf8'))
  : [];

const matches = index
  .map((entry) => {
    const match = scoreEntry(entry);
    return {
      ...entry,
      confidence: match.confidence,
      score: match.score,
      artifacts: (entry.artifacts || []).map((artifact) => ({
        path: artifact,
        exists: pathExists(artifact)
      }))
    };
  })
  .filter((entry) => entry.score > 0)
  .sort((a, b) => b.score - a.score);

if (matches.length) {
  console.log(JSON.stringify({ query, matches }, null, 2));
  process.exit(0);
}

console.log(JSON.stringify({
  query,
  matches: [],
  nextSearchLocations: [
    'tests/playwright/*.steps.json',
    'reports/*.normalized.yaml',
    'reports/*.steps.json',
    'reports/*.mapping.json',
    'reports/*.txt'
  ]
}, null, 2));
