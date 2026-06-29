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
    trace: 'retain-on-failure',
  },
  expect: {
    timeout: 5_000,
  },
});
