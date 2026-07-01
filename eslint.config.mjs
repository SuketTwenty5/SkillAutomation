// ESLint flat config — enforces the Page Object Model rule:
// specs must NOT contain inline locators; every locator comes from a page object under
// tests/playwright/support/pom/ (or an allowlisted builder helper in support/, e.g. textLocator).
//
// This bans the member-call forms `<recv>.locator(...)` and `<recv>.getBy*(...)` inside *.spec.ts
// (page.locator, x.getByRole, chained .getByText, etc.) — including calls hidden in spec-local
// helper functions, since those still live in the spec file. Calls to imported builders
// (textLocator(), tabLocator(), fastClick()) are Identifier callees, not member `.locator`, so
// they are allowed automatically.
// Known gaps (deliberate, not worth the noise): aliasing (`const l = page.locator; l(...)`) and
// computed access (`page['locator'](...)`) are NOT caught. They are not idiomatic and would be
// obvious in review; the guard targets realistic inline usage.
//
// RATCHET: the BASELINE list below are specs that were already dirty when the rule shipped.
// They are exempt until migrated. Do NOT add to this list. Remove a spec from it once it is
// migrated to the POM — that is how the baseline burns down to zero.

import tseslint from 'typescript-eslint';

const BANNED_LOCATOR_SYNTAX = [
  {
    selector: "CallExpression[callee.property.name='locator']",
    message:
      'Inline .locator() is banned in specs. Add the locator to a page object under tests/playwright/support/pom/ and call that method instead.',
  },
  {
    selector: "CallExpression[callee.property.name=/^getBy(Role|Text|Label|Placeholder|TestId|AltText|Title)$/]",
    message:
      'Inline getBy*() is banned in specs. Add the locator to a page object under tests/playwright/support/pom/ and call that method instead.',
  },
  {
    // Reloading over an unsaved ExtJS form discards edits (the USD-0 bug). Reloads belong inside POM
    // methods (e.g. updateCostsAndWaitForFinished, which self-guards by saving first). In a spec, poll
    // the coupled field to settle instead of forcing it with a reload.
    selector: "CallExpression[callee.property.name='reload']",
    message:
      'page.reload() is banned in specs — a reload over an unsaved ExtJS form discards edits. Reloads live inside POM methods; poll coupled fields to settle instead.',
  },
];

// Ratchet baseline — specs still containing inline locators when the rule shipped.
const BASELINE = [
  'tests/playwright/create-capital-project-from-prior-project-and-update-labor-fte-and-cost.spec.ts',
  'tests/playwright/create-capital-project-from-prior-project-and-update-labor-fte.spec.ts',
  'tests/playwright/create-capital-project-from-prior-project-and-update-labor-resource.spec.ts',
  'tests/playwright/create-engineering-services-proposal-from-prior-project.spec.ts',
  'tests/playwright/manufacturing-proposal.spec.ts',
  'tests/playwright/probe-labor-grid-addlink.spec.ts',
  'tests/playwright/recording-20260627-175641.spec.ts',
];

export default tseslint.config(
  {
    ignores: ['node_modules/**', 'test-results/**', 'playwright-report/**', 'playwrightRecording/**', 'imported/**'],
  },
  {
    files: ['tests/playwright/**/*.spec.ts'],
    languageOptions: { parser: tseslint.parser },
    rules: {
      'no-restricted-syntax': ['error', ...BANNED_LOCATOR_SYNTAX],
    },
  },
  {
    // RATCHET exemptions — burn this list down as each spec migrates to the POM.
    files: BASELINE,
    rules: {
      'no-restricted-syntax': 'off',
    },
  },
);
