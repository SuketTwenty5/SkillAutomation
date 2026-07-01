import { defineConfig } from 'playwright/test';

export default defineConfig({
  testDir: './tests/playwright',
  timeout: 180_000,
  fullyParallel: false,
  workers: 1,
  reporter: [
    ['list'],
    ['json', { outputFile: 'test-results/playwright/results.json' }],
  ],
  use: {
    actionTimeout: 8_000,
    navigationTimeout: 45_000,
    screenshot: 'only-on-failure',
    // Verification runs (POM_VERIFY=1) force a trace on green runs so pom-verify.mjs can
    // record which locators actually resolved -> .verified.json (computed trust).
    trace: process.env.POM_VERIFY === '1' ? 'on' : 'retain-on-failure',
  },
  expect: {
    timeout: 5_000,
  },
});
