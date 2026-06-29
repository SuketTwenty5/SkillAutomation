import { chromium, type Browser, type BrowserContext, type Locator, type Page } from 'playwright';
import fs from 'node:fs';
import path from 'node:path';

export const DEFAULT_APP_URL = 'https://approuter-twenty5ipe-dev.cfapps.us10.hana.ondemand.com/#quote';

type BrowserStorageState = Awaited<ReturnType<BrowserContext['storageState']>>;

export interface CdpSession {
  browser: Browser;
  context: BrowserContext;
  page: Page;
}

export interface CdpOptions {
  appUrl?: string;
  loginWaitMs?: number;
}

interface LoginCredentials {
  username: string;
  password: string;
}

interface SavedAuthState {
  savedAt: string;
  appOrigin: string;
  storageState: BrowserStorageState;
  sessionStorage: Record<string, string>;
}

const appShellLocators = (page: Page): Locator[] => [
  page.locator("xpath=//div[contains(@class, 'x-PricingAppNavTitleToolbar')]").first(),
  page.locator("xpath=//a[@aria-hidden='false' and contains(@class,'ibeTopMenuButton')]").first(),
  page.locator("xpath=//a//span[contains(translate(normalize-space(.), 'abcdefghijklmnopqrstuvwxyz', 'ABCDEFGHIJKLMNOPQRSTUVWXYZ'), 'PROPOSALS')]").first(),
];

const loginLocators = (page: Page): Locator[] => [
  page.locator('#j_username').first(),
  page.locator("input[name='j_username']").first(),
  page.locator("xpath=//*[@name='pf.username']").first(),
  page.locator("input[type='email']").first(),
  page.locator("xpath=//*[@type='password']").first(),
  page.getByText(/sign in|enter code|default identity provider/i).first(),
];

const usernameLocators = (page: Page): Locator[] => [
  page.locator('#j_username').first(),
  page.locator("input[name='j_username']").first(),
  page.locator("input[name='pf.username']").first(),
  page.locator("input[type='email']").first(),
  page.locator("input[autocomplete='username']").first(),
];

const passwordLocators = (page: Page): Locator[] => [
  page.locator('#j_password').first(),
  page.locator("input[name='j_password']").first(),
  page.locator("input[name='pf.pass']").first(),
  page.locator("input[type='password']").first(),
  page.locator("input[autocomplete='current-password']").first(),
];

const submitLocators = (page: Page): Locator[] => [
  page.locator('#logOnFormSubmit').first(),
  page.locator("button[type='submit']").first(),
  page.getByRole('button', { name: /sign in|log in|login|continue|next/i }).first(),
  page.locator("input[type='submit']").first(),
];

export async function connectToTwentyFive(options: CdpOptions = {}): Promise<CdpSession> {
  const startedAt = Date.now();
  const appUrl = options.appUrl ?? process.env.APP_URL ?? DEFAULT_APP_URL;
  const loginWaitMs = options.loginWaitMs ?? Number(process.env.PLAYWRIGHT_LOGIN_WAIT_MS ?? 120_000);

  const { browser, context } = await launchManagedChromeContext();
  await restoreAuthStateIfFresh(context, appUrl);

  const page = await chooseUsefulPage(context);
  await page.bringToFront().catch(() => undefined);

  if (!(await alreadyInSignedInApp(page))) {
    await page.goto(appUrl, { waitUntil: 'domcontentloaded', timeout: 45_000 }).catch((error) => {
      const message = error instanceof Error ? error.message : String(error);
      if (!message.includes('ERR_ABORTED')) {
        throw error;
      }
    });
  }

  await waitForSignedInApp(page, loginWaitMs);
  await saveAuthState(context, page, appUrl).catch((error) => {
    console.warn(`Warning: unable to save Playwright auth state: ${error instanceof Error ? error.message : String(error)}`);
  });
  logTiming('connect app shell ready', startedAt);

  return { browser, context, page };
}

async function launchManagedChromeContext(): Promise<{ browser: Browser; context: BrowserContext }> {
  const workspaceStateDir = workspaceAutomationDir();
  const browserHomeDir = process.env.PLAYWRIGHT_BROWSER_HOME ?? path.join(workspaceStateDir, 'browser-home');
  const crashDumpsDir = process.env.PLAYWRIGHT_CRASH_DUMPS_DIR ?? path.join(workspaceStateDir, 'crash-dumps');
  const profileDir =
    process.env.PLAYWRIGHT_PROFILE_DIR ??
    process.env.CHROME_PROFILE ??
    path.join(workspaceStateDir, 'playwright-chrome');
  fs.mkdirSync(profileDir, { recursive: true });
  fs.mkdirSync(browserHomeDir, { recursive: true });
  fs.mkdirSync(crashDumpsDir, { recursive: true });

  const headless = process.env.HEADLESS === 'true';
  const executablePath = process.env.CHROME_BIN || undefined;
  const context = await chromium.launchPersistentContext(profileDir, {
    headless,
    executablePath,
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
  const browser = context.browser();
  if (!browser) {
    throw new Error('Managed Playwright Chrome did not expose a browser instance.');
  }
  return { browser, context };
}

async function restoreAuthStateIfFresh(context: BrowserContext, appUrl: string): Promise<void> {
  const state = readFreshAuthState(appUrl);
  if (!state) return;

  const appOrigin = new URL(appUrl).origin;
  await context.addCookies(state.storageState.cookies);
  await context.addInitScript(
    ({ origin, localStorageEntries, sessionStorageEntries }) => {
      if (window.location.origin !== origin) return;
      for (const [name, value] of localStorageEntries) {
        window.localStorage.setItem(name, value);
      }
      for (const [name, value] of sessionStorageEntries) {
        window.sessionStorage.setItem(name, value);
      }
    },
    {
      origin: appOrigin,
      localStorageEntries: state.storageState.origins.find((entry) => entry.origin === appOrigin)?.localStorage.map((entry) => [entry.name, entry.value]) ?? [],
      sessionStorageEntries: Object.entries(state.sessionStorage),
    },
  );
  console.log(`Reusing fresh Playwright auth state from ${authStatePath()}`);
}

async function saveAuthState(context: BrowserContext, page: Page, appUrl: string): Promise<void> {
  if (!(await alreadyInSignedInApp(page))) return;

  const appOrigin = new URL(appUrl).origin;
  const storageState = await context.storageState();
  const sessionStorage =
    page.url().startsWith(appOrigin)
      ? await page.evaluate(() => Object.fromEntries(Array.from({ length: window.sessionStorage.length }, (_, index) => {
          const key = window.sessionStorage.key(index) ?? '';
          return [key, window.sessionStorage.getItem(key) ?? ''];
        }).filter(([key]) => key)))
      : {};

  const state: SavedAuthState = {
    savedAt: new Date().toISOString(),
    appOrigin,
    storageState,
    sessionStorage,
  };
  fs.mkdirSync(workspaceAutomationDir(), { recursive: true });
  fs.writeFileSync(authStatePath(), JSON.stringify(state, null, 2));
}

function readFreshAuthState(appUrl: string): SavedAuthState | undefined {
  const filePath = authStatePath();
  if (!fs.existsSync(filePath)) return undefined;

  const state = JSON.parse(fs.readFileSync(filePath, 'utf8')) as SavedAuthState;
  const appOrigin = new URL(appUrl).origin;
  if (state.appOrigin !== appOrigin) return undefined;

  const savedAt = new Date(state.savedAt).getTime();
  const maxAgeMs = Number(process.env.PLAYWRIGHT_AUTH_STATE_MAX_AGE_MS ?? 8 * 60 * 60 * 1000);
  if (!Number.isFinite(savedAt) || Date.now() - savedAt > maxAgeMs) return undefined;

  const nowSeconds = Date.now() / 1000;
  const originCookies = state.storageState.cookies.filter((cookie) => cookie.domain === new URL(appUrl).hostname || cookie.domain.endsWith(`.${new URL(appUrl).hostname}`));
  if (originCookies.some((cookie) => cookie.expires > 0 && cookie.expires <= nowSeconds)) return undefined;

  return state;
}

function workspaceAutomationDir(): string {
  return path.resolve(process.cwd(), '.skillautomation');
}

function authStatePath(): string {
  return process.env.PLAYWRIGHT_AUTH_STATE_PATH ?? path.join(workspaceAutomationDir(), 'auth-state.json');
}

async function chooseUsefulPage(context: BrowserContext): Promise<Page> {
  const openPages = context.pages().filter((page) => !page.isClosed());
  const appPage = openPages.find((page) => /hana\.ondemand\.com|accounts\.sap\.com|login|signin|oauth/i.test(page.url()));
  if (appPage) return appPage;
  if (openPages[0]) return openPages[0];
  return context.newPage();
}

export async function waitForSignedInApp(page: Page, timeoutMs: number): Promise<void> {
  if (await anyVisible(appShellLocators(page))) {
    return;
  }

  if (await anyVisible(loginLocators(page))) {
    await loginWithTestUser(page, timeoutMs);
    if (await anyVisible(appShellLocators(page))) {
      return;
    }
  }

  await waitForAppShellWithOneRefresh(page, timeoutMs, 'signed-in Twenty5 app shell');
}

async function alreadyInSignedInApp(page: Page): Promise<boolean> {
  if (!/hana\.ondemand\.com/i.test(page.url())) return false;
  return anyVisible(appShellLocators(page));
}

export async function waitForNoLoading(page: Page, timeoutMs = 15_000): Promise<void> {
  const loadingMask = page.locator(
    "xpath=//*[contains(normalize-space(.), 'Loading Twenty5') or contains(normalize-space(.), 'Loading document') or (contains(@class,'x-mask-msg-text') and contains(normalize-space(.), 'Loading'))]",
  );

  if ((await loadingMask.count()) === 0) return;
  await loadingMask.first().waitFor({ state: 'hidden', timeout: timeoutMs }).catch(() => undefined);
}

export async function waitForAnyVisible(
  page: Page,
  locators: Locator[],
  timeoutMs: number,
  description: string,
): Promise<Locator> {
  const deadline = Date.now() + timeoutMs;
  while (Date.now() < deadline) {
    for (const locator of locators) {
      if (await isVisible(locator)) {
        return locator;
      }
    }
    await page.waitForTimeout(250);
  }

  throw new Error(`Timed out after ${timeoutMs}ms waiting for ${description}. Current URL: ${page.url()}`);
}

async function anyVisible(locators: Locator[]): Promise<boolean> {
  for (const locator of locators) {
    if (await isVisible(locator)) return true;
  }
  return false;
}

async function loginWithTestUser(page: Page, timeoutMs: number): Promise<void> {
  const credentials = readLoginCredentials();
  if (!credentials) {
    throw new Error(
      'Twenty5 login is required, but IPE_USERNAME/IPE_PASSWORD were not found in environment or /Users/suketsuman/Documents/LocalTestAutomation/.env.local.',
    );
  }

  console.log('Chrome is on a login page. Attempting automatic login with the configured test user.');
  await clickIfVisible(page.getByRole('button', { name: /default identity provider/i }).first(), 5_000);

  const usernameInput = await waitForAnyVisible(page, usernameLocators(page), 20_000, 'username field');
  await usernameInput.fill(credentials.username);
  if (!(await anyVisible(passwordLocators(page)))) {
    await clickFirstVisible(page, submitLocators(page), 5_000).catch(async () => {
      await page.keyboard.press('Enter');
    });
  }

  const passwordInput = await waitForAnyVisible(page, passwordLocators(page), 20_000, 'password field');
  await passwordInput.fill(credentials.password);
  await clickFirstVisible(page, submitLocators(page), 5_000).catch(async () => {
    await page.keyboard.press('Enter');
  });

  const mfaChallenge = page.getByText(/enter code|verification code|multi-factor|authenticator|approve sign in/i).first();
  await waitForAppShellWithOneRefresh(page, timeoutMs, 'signed-in Twenty5 app shell after automatic login', [mfaChallenge]);

  if (!(await anyVisible(appShellLocators(page)))) {
    throw new Error('Automatic login did not reach the Twenty5 app shell. MFA/SSO approval may be required.');
  }
}

async function waitForAppShellWithOneRefresh(
  page: Page,
  timeoutMs: number,
  description: string,
  extraLocators: Locator[] = [],
): Promise<void> {
  const firstWaitMs = Math.min(timeoutMs, 45_000);
  const startedAt = Date.now();

  try {
    await waitForAnyVisible(page, [...appShellLocators(page), ...extraLocators], firstWaitMs, description);
    return;
  } catch (error) {
    if (!(await refreshOnceAfterAppNavigation(page, description))) {
      throw error;
    }
  }

  const elapsed = Date.now() - startedAt;
  const remainingMs = Math.max(15_000, timeoutMs - elapsed);
  await waitForAnyVisible(page, [...appShellLocators(page), ...extraLocators], remainingMs, description);
}

async function refreshOnceAfterAppNavigation(page: Page, description: string): Promise<boolean> {
  if (!/hana\.ondemand\.com/i.test(page.url()) && !/^(about:blank|chrome-error:)/i.test(page.url())) return false;

  console.warn(`[${description}] app shell did not appear after navigation; refreshing once before retrying.`);
  await page.reload({ waitUntil: 'domcontentloaded', timeout: 45_000 }).catch((error) => {
    const message = error instanceof Error ? error.message : String(error);
    if (!message.includes('ERR_ABORTED')) throw error;
  });
  await waitForNoLoading(page, 30_000);
  return true;
}

function readLoginCredentials(): LoginCredentials | undefined {
  const username = process.env.IPE_USERNAME;
  const password = process.env.IPE_PASSWORD;
  if (username && password) return { username, password };

  const envPath = process.env.IPE_ENV_FILE ?? '/Users/suketsuman/Documents/LocalTestAutomation/.env.local';
  if (!fs.existsSync(envPath)) return undefined;

  const parsed = parseEnvFile(fs.readFileSync(envPath, 'utf8'));
  if (!parsed.IPE_USERNAME || !parsed.IPE_PASSWORD) return undefined;
  return {
    username: parsed.IPE_USERNAME,
    password: parsed.IPE_PASSWORD,
  };
}

function parseEnvFile(contents: string): Record<string, string> {
  const values: Record<string, string> = {};
  for (const rawLine of contents.split(/\r?\n/)) {
    const line = rawLine.trim();
    if (!line || line.startsWith('#')) continue;
    const match = /^(?:export\s+)?([A-Za-z_][A-Za-z0-9_]*)=(.*)$/.exec(line);
    if (!match) continue;
    values[match[1]] = unquoteEnvValue(match[2].trim());
  }
  return values;
}

function unquoteEnvValue(value: string): string {
  if ((value.startsWith('"') && value.endsWith('"')) || (value.startsWith("'") && value.endsWith("'"))) {
    return value.slice(1, -1);
  }
  return value;
}

async function clickFirstVisible(page: Page, locators: Locator[], timeoutMs: number): Promise<void> {
  const locator = await waitForAnyVisible(page, locators, timeoutMs, 'login submit button');
  await locator.click({ force: true, noWaitAfter: true });
}

async function clickIfVisible(locator: Locator, timeoutMs: number): Promise<void> {
  await locator.waitFor({ state: 'visible', timeout: timeoutMs }).catch(() => undefined);
  if (await isVisible(locator)) {
    await locator.click({ force: true, noWaitAfter: true });
  }
}

async function isVisible(locator: Locator): Promise<boolean> {
  return (await locator.count()) > 0 && (await locator.isVisible().catch(() => false));
}

function logTiming(label: string, startedAt: number): void {
  if (process.env.PLAYWRIGHT_TIMING === 'false') return;
  console.log(`[timing] ${label}: ${Date.now() - startedAt}ms`);
}
