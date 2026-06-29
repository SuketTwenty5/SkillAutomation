#!/usr/bin/env node
import fs from 'node:fs';
import path from 'node:path';
import { chromium } from 'playwright';

const DEFAULT_APP_URL = 'https://approuter-twenty5ipe-dev.cfapps.us10.hana.ondemand.com/#quote';
const appUrl = process.argv[2] || process.env.APP_URL || DEFAULT_APP_URL;
const workspaceDir = process.cwd();
const stateDir = path.resolve(workspaceDir, '.skillautomation');
const profileDir = process.env.PLAYWRIGHT_CODEGEN_PROFILE_DIR || path.join(stateDir, 'codegen-chrome');
const browserHomeDir = process.env.PLAYWRIGHT_BROWSER_HOME || path.join(stateDir, 'browser-home');
const crashDumpsDir = process.env.PLAYWRIGHT_CRASH_DUMPS_DIR || path.join(stateDir, 'crash-dumps');
const authStateFile = process.env.PLAYWRIGHT_AUTH_STATE_PATH || path.join(stateDir, 'auth-state.json');
const loginWaitMs = Number(process.env.PLAYWRIGHT_LOGIN_WAIT_MS || 120_000);

const appShellSelectors = [
  "xpath=//div[contains(@class, 'x-PricingAppNavTitleToolbar')]",
  "xpath=//a[@aria-hidden='false' and contains(@class,'ibeTopMenuButton')]",
  "xpath=//a//span[contains(translate(normalize-space(.), 'abcdefghijklmnopqrstuvwxyz', 'ABCDEFGHIJKLMNOPQRSTUVWXYZ'), 'PROPOSALS')]",
];

const loginSelectors = [
  '#j_username',
  "input[name='j_username']",
  "input[name='pf.username']",
  "input[type='email']",
  "input[type='password']",
  'text=/sign in|enter code|default identity provider/i',
];

const usernameSelectors = [
  '#j_username',
  "input[name='j_username']",
  "input[name='pf.username']",
  "input[type='email']",
  "input[autocomplete='username']",
];

const passwordSelectors = [
  '#j_password',
  "input[name='j_password']",
  "input[name='pf.pass']",
  "input[type='password']",
  "input[autocomplete='current-password']",
];

const submitSelectors = [
  '#logOnFormSubmit',
  "button[type='submit']",
  "input[type='submit']",
  'role=button[name=/sign in|log in|login|continue|next/i]',
];

fs.mkdirSync(profileDir, { recursive: true });
fs.mkdirSync(browserHomeDir, { recursive: true });
fs.mkdirSync(crashDumpsDir, { recursive: true });

const context = await chromium.launchPersistentContext(profileDir, {
  headless: process.env.HEADLESS === 'true',
  executablePath: process.env.CHROME_BIN || undefined,
  viewport: null,
  env: {
    ...process.env,
    HOME: browserHomeDir,
  },
  args: [
    '--start-maximized',
    '--disable-crash-reporter',
    '--disable-crashpad',
    `--crash-dumps-dir=${crashDumpsDir}`,
  ],
});

try {
  await restoreAuthStateIfFresh(context);
  const page = await choosePage(context);
  await page.bringToFront().catch(() => undefined);
  await page.goto(appUrl, { waitUntil: 'domcontentloaded', timeout: 45_000 }).catch((error) => {
    if (!String(error?.message || error).includes('ERR_ABORTED')) throw error;
  });
  await waitForSignedInApp(page);
  await saveAuthState(context, page);
  console.log(`Recording auth ready: ${new URL(appUrl).origin}`);
} finally {
  await context.close();
}

async function restoreAuthStateIfFresh(context) {
  const state = readFreshAuthState();
  if (!state) return;

  const appOrigin = new URL(appUrl).origin;
  await context.addCookies(state.storageState.cookies);
  await context.addInitScript(
    ({ origin, localStorageEntries, sessionStorageEntries }) => {
      if (window.location.origin !== origin) return;
      for (const [name, value] of localStorageEntries) window.localStorage.setItem(name, value);
      for (const [name, value] of sessionStorageEntries) window.sessionStorage.setItem(name, value);
    },
    {
      origin: appOrigin,
      localStorageEntries: state.storageState.origins.find((entry) => entry.origin === appOrigin)?.localStorage.map((entry) => [entry.name, entry.value]) || [],
      sessionStorageEntries: Object.entries(state.sessionStorage || {}),
    },
  );
}

async function choosePage(context) {
  const pages = context.pages().filter((page) => !page.isClosed());
  return pages.find((page) => /hana\.ondemand\.com|accounts\.sap\.com|login|signin|oauth/i.test(page.url())) || pages[0] || context.newPage();
}

async function waitForSignedInApp(page) {
  if (await anyVisible(page, appShellSelectors)) return;

  if (await anyVisible(page, loginSelectors)) {
    await loginWithTestUser(page);
    if (await anyVisible(page, appShellSelectors)) return;
  }

  await waitForAnyVisible(page, appShellSelectors, loginWaitMs, 'signed-in Twenty5 app shell');
}

async function loginWithTestUser(page) {
  const credentials = readLoginCredentials();
  if (!credentials) {
    throw new Error('Twenty5 login is required, but IPE_USERNAME/IPE_PASSWORD were not found in environment or /Users/suketsuman/Documents/LocalTestAutomation/.env.local.');
  }

  console.log('Recording browser is on a login page. Attempting automatic login with the configured test user.');
  await clickIfVisible(page, 'role=button[name=/default identity provider/i]', 5_000);

  const usernameInput = await waitForAnyVisible(page, usernameSelectors, 20_000, 'username field');
  await usernameInput.fill(credentials.username);
  if (!(await anyVisible(page, passwordSelectors))) {
    await clickFirstVisible(page, submitSelectors, 5_000).catch(async () => page.keyboard.press('Enter'));
  }

  const passwordInput = await waitForAnyVisible(page, passwordSelectors, 20_000, 'password field');
  await passwordInput.fill(credentials.password);
  await clickFirstVisible(page, submitSelectors, 5_000).catch(async () => page.keyboard.press('Enter'));

  const mfaChallenge = 'text=/enter code|verification code|multi-factor|authenticator|approve sign in/i';
  await waitForAnyVisible(page, [...appShellSelectors, mfaChallenge], loginWaitMs, 'signed-in Twenty5 app shell after automatic login');
  if (!(await anyVisible(page, appShellSelectors))) {
    throw new Error('Automatic login did not reach the Twenty5 app shell. MFA/SSO approval may be required.');
  }
}

async function saveAuthState(context, page) {
  if (!(await anyVisible(page, appShellSelectors))) return;

  const appOrigin = new URL(appUrl).origin;
  const storageState = await context.storageState();
  const sessionStorage = page.url().startsWith(appOrigin)
    ? await page.evaluate(() => Object.fromEntries(Array.from({ length: window.sessionStorage.length }, (_, index) => {
        const key = window.sessionStorage.key(index) || '';
        return [key, window.sessionStorage.getItem(key) || ''];
      }).filter(([key]) => key)))
    : {};

  fs.mkdirSync(stateDir, { recursive: true });
  fs.writeFileSync(authStateFile, JSON.stringify({
    savedAt: new Date().toISOString(),
    appOrigin,
    storageState,
    sessionStorage,
  }, null, 2));
}

function readFreshAuthState() {
  if (!fs.existsSync(authStateFile)) return undefined;

  const state = JSON.parse(fs.readFileSync(authStateFile, 'utf8'));
  const appOrigin = new URL(appUrl).origin;
  if (state.appOrigin !== appOrigin) return undefined;

  const savedAt = new Date(state.savedAt).getTime();
  const maxAgeMs = Number(process.env.PLAYWRIGHT_AUTH_STATE_MAX_AGE_MS || 8 * 60 * 60 * 1000);
  if (!Number.isFinite(savedAt) || Date.now() - savedAt > maxAgeMs) return undefined;

  const nowSeconds = Date.now() / 1000;
  const hostname = new URL(appUrl).hostname;
  const originCookies = state.storageState.cookies.filter((cookie) => cookie.domain === hostname || cookie.domain.endsWith(`.${hostname}`));
  if (originCookies.some((cookie) => cookie.expires > 0 && cookie.expires <= nowSeconds)) return undefined;

  return state;
}

function readLoginCredentials() {
  if (process.env.IPE_USERNAME && process.env.IPE_PASSWORD) {
    return { username: process.env.IPE_USERNAME, password: process.env.IPE_PASSWORD };
  }

  const envPath = process.env.IPE_ENV_FILE || '/Users/suketsuman/Documents/LocalTestAutomation/.env.local';
  if (!fs.existsSync(envPath)) return undefined;

  const parsed = parseEnvFile(fs.readFileSync(envPath, 'utf8'));
  if (!parsed.IPE_USERNAME || !parsed.IPE_PASSWORD) return undefined;
  return { username: parsed.IPE_USERNAME, password: parsed.IPE_PASSWORD };
}

function parseEnvFile(contents) {
  const values = {};
  for (const rawLine of contents.split(/\r?\n/)) {
    const line = rawLine.trim();
    if (!line || line.startsWith('#')) continue;
    const match = /^(?:export\s+)?([A-Za-z_][A-Za-z0-9_]*)=(.*)$/.exec(line);
    if (match) values[match[1]] = unquoteEnvValue(match[2].trim());
  }
  return values;
}

function unquoteEnvValue(value) {
  if ((value.startsWith('"') && value.endsWith('"')) || (value.startsWith("'") && value.endsWith("'"))) {
    return value.slice(1, -1);
  }
  return value;
}

async function clickFirstVisible(page, selectors, timeoutMs) {
  const locator = await waitForAnyVisible(page, selectors, timeoutMs, 'login submit button');
  await locator.click({ force: true, noWaitAfter: true });
}

async function clickIfVisible(page, selector, timeoutMs) {
  const locator = page.locator(selector).first();
  await locator.waitFor({ state: 'visible', timeout: timeoutMs }).catch(() => undefined);
  if (await isVisible(locator)) await locator.click({ force: true, noWaitAfter: true });
}

async function waitForAnyVisible(page, selectors, timeoutMs, description) {
  const deadline = Date.now() + timeoutMs;
  while (Date.now() < deadline) {
    for (const selector of selectors) {
      const locator = page.locator(selector).first();
      if (await isVisible(locator)) return locator;
    }
    await page.waitForTimeout(250);
  }
  throw new Error(`Timed out after ${timeoutMs}ms waiting for ${description}. Current URL: ${page.url()}`);
}

async function anyVisible(page, selectors) {
  for (const selector of selectors) {
    if (await isVisible(page.locator(selector).first())) return true;
  }
  return false;
}

async function isVisible(locator) {
  return (await locator.count()) > 0 && (await locator.isVisible().catch(() => false));
}
