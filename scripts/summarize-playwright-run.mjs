#!/usr/bin/env node
import fs from 'node:fs';
import path from 'node:path';

const resultPath = process.argv[2] || 'test-results/playwright/results.json';
const readmePath = process.argv[3] || '';
const cwd = process.cwd();

if (!fs.existsSync(resultPath)) {
  console.error(`Playwright result not found: ${resultPath}`);
  process.exit(1);
}

const result = JSON.parse(fs.readFileSync(resultPath, 'utf8'));
const tests = [];
collectTests(result.suites || [], tests);

const lines = ['# Playwright Run Report', ''];
lines.push(`- Result JSON: \`${resultPath}\``);
if (readmePath) lines.push(`- README: \`${readmePath}\``);
lines.push(`- Overall: ${result.stats?.unexpected ? 'failed' : 'passed'}`);
lines.push(`- Expected passed: ${result.stats?.expected ?? 0}`);
lines.push(`- Failed: ${result.stats?.unexpected ?? 0}`);
lines.push(`- Skipped: ${result.stats?.skipped ?? 0}`);
if (result.stats?.duration) lines.push(`- Duration: ${Math.round(result.stats.duration)} ms`);
lines.push('');

for (const item of tests) {
  if (!item.results.length) continue;
  const latest = item.results[item.results.length - 1];
  if (!latest) continue;
  lines.push(`## ${item.title}`);
  lines.push('');
  lines.push(`- Status: ${latest.status}`);
  lines.push(`- Duration: ${latest.duration} ms`);
  const stdout = (latest.stdout || []).map((entry) => entry.text || '').join('');
  const estimateUrl = stdout.match(/estimate url: (https?:\/\/\S+)/)?.[1];
  if (estimateUrl) lines.push(`- Estimate URL: \`${estimateUrl}\``);
  lines.push('');
  if (latest.steps?.length) {
    const attachments = collectAttachments(latest, readmePath);
    lines.push('| # | Step | Status | Duration | Screenshot |');
    lines.push('| ---: | --- | --- | ---: | --- |');
    latest.steps.forEach((step, index) => {
      const screenshot = attachments[index]?.path ? `\`${attachments[index].path}\`` : '';
      lines.push(`| ${index + 1} | ${escapeTable(step.title)} | ${latest.status} | ${step.duration} ms | ${screenshot} |`);
    });
    lines.push('');
  }
  const attachments = collectAttachments(latest, readmePath);
  if (attachments.length) {
    lines.push('### Screenshot Files');
    lines.push('');
    for (const attachment of attachments) lines.push(`- ${attachment.name}: \`${attachment.path}\``);
    lines.push('');
  }
  if (latest.errors?.length) {
    lines.push('### Errors');
    lines.push('');
    for (const error of latest.errors) lines.push(`- ${String(error.message || error).split('\n')[0]}`);
    lines.push('');
  }
}

const outputPath = readmePath
  ? path.join(path.dirname(readmePath), 'latest-chat-report.md')
  : 'test-results/playwright/latest-chat-report.md';
fs.mkdirSync(path.dirname(outputPath), { recursive: true });
fs.writeFileSync(outputPath, `${lines.join('\n').trim()}\n`);
console.log(lines.join('\n').trim());
console.log(`\nReport written: ${path.relative(cwd, outputPath)}`);

function collectTests(suites, out) {
  for (const suite of suites) {
    for (const spec of suite.specs || []) {
      for (const test of spec.tests || []) out.push({ title: spec.title, results: test.results || [] });
    }
    collectTests(suite.suites || [], out);
  }
}

function collectAttachments(result, readmePathForLinks) {
  const attachments = [];
  for (const attachment of result.attachments || []) {
    if (attachment.contentType !== 'image/png') continue;
    if (attachment.path) {
      attachments.push({ name: attachment.name, path: attachment.path });
    } else if (readmePathForLinks) {
      const evidenceDir = path.join(path.dirname(readmePathForLinks), 'evidence');
      if (fs.existsSync(evidenceDir)) {
        const files = findFiles(evidenceDir).filter((file) => file.endsWith('.png'));
        for (const file of files) attachments.push({ name: path.basename(file), path: path.relative(cwd, file) });
      }
    }
  }
  return uniqueByPath(attachments);
}

function findFiles(dir) {
  return fs.readdirSync(dir, { withFileTypes: true }).flatMap((entry) => {
    const fullPath = path.join(dir, entry.name);
    return entry.isDirectory() ? findFiles(fullPath) : [fullPath];
  });
}

function uniqueByPath(items) {
  const seen = new Set();
  return items.filter((item) => {
    if (seen.has(item.path)) return false;
    seen.add(item.path);
    return true;
  });
}

function escapeTable(value) {
  return String(value).replace(/\|/g, '\\|').replace(/\r?\n/g, ' ');
}
