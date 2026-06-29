#!/usr/bin/env node
import { existsSync, mkdirSync, writeFileSync } from 'node:fs';
import { basename, join, resolve } from 'node:path';
import { chromium } from 'playwright';

const args = parseArgs(process.argv.slice(2));

if (args.help || args.h) {
  printHelp();
  process.exit(0);
}

const url = stringArg('url');
const cdp = stringArg('cdp');
const name = slug(stringArg('name') || deriveName(url) || 'page');
const outDir = resolve(stringArg('out-dir') || 'skills/skill-playwright-automation/references/page-locators');
const storageState = stringArg('storage-state');
const headed = booleanArg('headed');
const waitText = stringArg('wait-text');
const timeoutMs = numberArg('timeout', 45_000);

mkdirSync(outDir, { recursive: true });

const launched = !cdp;
const browser = cdp
  ? await chromium.connectOverCDP(cdp)
  : await chromium.launch({ headless: !headed });

const context = cdp
  ? browser.contexts()[0] || await browser.newContext()
  : await browser.newContext(storageState && existsSync(storageState) ? { storageState } : {});

const page = context.pages()[0] || await context.newPage();

if (url) {
  await page.goto(url, { waitUntil: 'domcontentloaded', timeout: timeoutMs }).catch((error) => {
    const message = error instanceof Error ? error.message : String(error);
    if (!message.includes('ERR_ABORTED')) throw error;
  });
}

if (waitText) {
  await waitForText(page, waitText, timeoutMs);
} else {
  await page.waitForLoadState('domcontentloaded', { timeout: timeoutMs }).catch(() => undefined);
  await page.waitForTimeout(1000);
}

const scan = await page.evaluate(scanVisibleLocators);
const result = {
  name,
  scannedAt: new Date().toISOString(),
  url: page.url(),
  title: await page.title().catch(() => ''),
  count: scan.length,
  locators: scan,
};

const jsonPath = join(outDir, `${name}.json`);
const mdPath = join(outDir, `${name}.md`);
writeFileSync(jsonPath, `${JSON.stringify(result, null, 2)}\n`);
writeFileSync(mdPath, renderMarkdown(result));

console.log(`Scanned ${scan.length} visible locators`);
console.log(`JSON: ${jsonPath}`);
console.log(`Markdown: ${mdPath}`);

if (launched) {
  await browser.close();
}

function parseArgs(argv) {
  const parsed = {};
  for (let index = 0; index < argv.length; index += 1) {
    const arg = argv[index];
    if (!arg.startsWith('--')) continue;
    const key = arg.slice(2);
    if (key.includes('=')) {
      const [namePart, ...valueParts] = key.split('=');
      parsed[namePart] = valueParts.join('=');
    } else if (index + 1 < argv.length && !argv[index + 1].startsWith('--')) {
      parsed[key] = argv[index + 1];
      index += 1;
    } else {
      parsed[key] = true;
    }
  }
  return parsed;
}

function stringArg(key) {
  const value = args[key];
  return typeof value === 'string' && value.trim() ? value.trim() : '';
}

function booleanArg(key) {
  return args[key] === true || args[key] === 'true';
}

function numberArg(key, fallback) {
  const value = Number(args[key]);
  return Number.isFinite(value) && value > 0 ? value : fallback;
}

function deriveName(value) {
  if (!value) return '';
  try {
    const parsed = new URL(value);
    return parsed.hash ? parsed.hash.replace(/^#/, '') : basename(parsed.pathname) || parsed.hostname;
  } catch {
    return value;
  }
}

function slug(value) {
  return value
    .toLowerCase()
    .replace(/[^a-z0-9]+/g, '-')
    .replace(/^-+|-+$/g, '')
    .slice(0, 80) || 'page';
}

async function waitForText(page, text, timeout) {
  const deadline = Date.now() + timeout;
  while (Date.now() < deadline) {
    const found = await page.evaluate((expected) => {
      return (document.body?.innerText || '').includes(expected);
    }, text).catch(() => false);
    if (found) return;
    await page.waitForTimeout(250);
  }
  throw new Error(`Timed out waiting for text: ${text}`);
}

function renderMarkdown(result) {
  const lines = [
    `# ${result.name} Locator Scan`,
    '',
    `Scanned: ${result.scannedAt}`,
    `URL: \`${result.url}\``,
    `Title: ${result.title || ''}`,
    `Total visible locators: ${result.count}`,
    '',
    '| Kind | Name/Text | Recommended | Fallback XPath |',
    '|---|---|---|---|',
  ];

  for (const entry of result.locators) {
    lines.push([
      entry.kind,
      escapeCell(entry.name || entry.text || entry.placeholder || entry.ariaLabel || entry.id || ''),
      codeCell(entry.recommended || ''),
      codeCell(entry.xpath || ''),
    ].join(' | ').replace(/^/, '| ').replace(/$/, ' |'));
  }

  return `${lines.join('\n')}\n`;
}

function escapeCell(value) {
  return String(value).replace(/\|/g, '\\|').replace(/\s+/g, ' ').trim();
}

function codeCell(value) {
  const safe = escapeCell(value).replace(/`/g, '\\`');
  return safe ? `\`${safe}\`` : '';
}

function printHelp() {
  console.log(`Usage:
  node skills/skill-playwright-automation/scripts/scan-page-locators.mjs --url <url> --name <page-name> [--storage-state auth.json] [--headed]
  node skills/skill-playwright-automation/scripts/scan-page-locators.mjs --cdp http://127.0.0.1:9222 --name <page-name>

Options:
  --url <url>              Navigate before scanning.
  --cdp <endpoint>         Attach to an existing Chrome debug session.
  --name <name>            Output basename.
  --out-dir <dir>          Output directory. Defaults to skill page-locators reference dir.
  --storage-state <file>   Playwright storage state file such as auth.json.
  --headed                 Launch visible Chromium when not using --cdp.
  --wait-text <text>       Wait until visible page text exists before scanning.
  --timeout <ms>           Navigation/wait timeout. Default 45000.
`);
}

function scanVisibleLocators() {
  const selector = [
    'button',
    'a',
    'input',
    'textarea',
    'select',
    '[role="button"]',
    '[role="tab"]',
    '[role="menuitem"]',
    '[role="gridcell"]',
    '[role="columnheader"]',
    '[aria-label]',
    '[placeholder]',
    '[data-testid]',
    '[data-qtip]',
    '[title]',
  ].join(',');

  const elements = Array.from(document.querySelectorAll(selector))
    .filter(isVisible)
    .slice(0, 700);

  return elements.map((element, index) => {
    const tag = element.tagName.toLowerCase();
    const role = element.getAttribute('role') || inferRole(element);
    const text = clean(element.innerText || element.textContent || '');
    const ariaLabel = clean(element.getAttribute('aria-label') || '');
    const placeholder = clean(element.getAttribute('placeholder') || '');
    const title = clean(element.getAttribute('title') || '');
    const dataTestId = clean(element.getAttribute('data-testid') || '');
    const dataQtip = clean(element.getAttribute('data-qtip') || '');
    const id = clean(element.id || '');
    const name = clean(element.getAttribute('name') || '');
    const candidateName = ariaLabel || placeholder || dataQtip || title || text || name || id;

    return {
      index,
      kind: role || tag,
      tag,
      name: candidateName,
      text,
      ariaLabel,
      placeholder,
      title,
      dataTestId,
      dataQtip,
      id,
      inputName: name,
      recommended: recommendedLocator({ role, tag, candidateName, ariaLabel, placeholder, dataTestId, dataQtip, id, name, text }),
      xpath: xpathFor(element),
    };
  });

  function isVisible(element) {
    const style = window.getComputedStyle(element);
    const rect = element.getBoundingClientRect();
    return style.visibility !== 'hidden' &&
      style.display !== 'none' &&
      element.getAttribute('aria-hidden') !== 'true' &&
      rect.width > 0 &&
      rect.height > 0;
  }

  function inferRole(element) {
    const tag = element.tagName.toLowerCase();
    if (tag === 'button') return 'button';
    if (tag === 'a') return 'link';
    if (tag === 'select') return 'combobox';
    if (tag === 'textarea') return 'textbox';
    if (tag === 'input') {
      const type = (element.getAttribute('type') || 'text').toLowerCase();
      if (['button', 'submit', 'reset'].includes(type)) return 'button';
      if (type === 'checkbox') return 'checkbox';
      if (type === 'radio') return 'radio';
      return 'textbox';
    }
    return '';
  }

  function recommendedLocator(info) {
    if (info.dataTestId) return `[data-testid="${cssEscape(info.dataTestId)}"]`;
    if (info.role && info.candidateName) return `getByRole(${quote(info.role)}, { name: ${regex(info.candidateName)} })`;
    if (info.placeholder) return `getByPlaceholder(${quote(info.placeholder)})`;
    if (info.ariaLabel) return `[aria-label="${cssEscape(info.ariaLabel)}"]`;
    if (info.dataQtip) return `[data-qtip="${cssEscape(info.dataQtip)}"]`;
    if (info.id && /^[A-Za-z][\w:-]*$/.test(info.id)) return `#${cssEscape(info.id)}`;
    if (info.name) return `[name="${cssEscape(info.name)}"]`;
    if (info.text) return `text=${info.text}`;
    return '';
  }

  function xpathFor(element) {
    const dataTestId = element.getAttribute('data-testid');
    if (dataTestId) return `//*[@data-testid=${xpathLiteral(dataTestId)}]`;

    const placeholder = element.getAttribute('placeholder');
    if (placeholder) return `//${element.tagName.toLowerCase()}[@placeholder=${xpathLiteral(placeholder)}]`;

    const ariaLabel = element.getAttribute('aria-label');
    if (ariaLabel) return `//*[@aria-label=${xpathLiteral(ariaLabel)}]`;

    const dataQtip = element.getAttribute('data-qtip');
    if (dataQtip) return `//*[@data-qtip=${xpathLiteral(dataQtip)}]`;

    const title = element.getAttribute('title');
    if (title) return `//*[@title=${xpathLiteral(title)}]`;

    const text = clean(element.innerText || element.textContent || '');
    const role = element.getAttribute('role') || inferRole(element);
    if (text && role) return `//*[@role=${xpathLiteral(role)} and contains(normalize-space(.), ${xpathLiteral(text)})]`;
    if (text && ['button', 'a'].includes(element.tagName.toLowerCase())) {
      return `//${element.tagName.toLowerCase()}[contains(normalize-space(.), ${xpathLiteral(text)})]`;
    }

    return absoluteXPath(element);
  }

  function absoluteXPath(element) {
    const parts = [];
    let current = element;
    while (current && current.nodeType === Node.ELEMENT_NODE && current !== document.body) {
      const tag = current.tagName.toLowerCase();
      const siblings = Array.from(current.parentElement?.children || []).filter((sibling) => sibling.tagName === current.tagName);
      const suffix = siblings.length > 1 ? `[${siblings.indexOf(current) + 1}]` : '';
      parts.unshift(`${tag}${suffix}`);
      current = current.parentElement;
    }
    return `/html/body/${parts.join('/')}`;
  }

  function clean(value) {
    return String(value || '').replace(/\s+/g, ' ').trim().slice(0, 160);
  }

  function cssEscape(value) {
    return String(value).replace(/\\/g, '\\\\').replace(/"/g, '\\"');
  }

  function quote(value) {
    return `'${String(value).replace(/\\/g, '\\\\').replace(/'/g, "\\'")}'`;
  }

  function regex(value) {
    const escaped = String(value).replace(/[.*+?^${}()|[\]\\]/g, '\\$&');
    return `/${escaped}/i`;
  }

  function xpathLiteral(value) {
    if (!String(value).includes("'")) return `'${value}'`;
    if (!String(value).includes('"')) return `"${value}"`;
    return `concat(${String(value).split("'").map((part) => `'${part}'`).join(', "\'", ')})`;
  }
}
