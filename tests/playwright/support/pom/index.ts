// Page Object Model barrel — the single import surface for specs.
// Locators live ONLY inside these page objects; specs must not declare inline locators.
export { BasePage } from './base-page';
export { EstimatePage } from './estimate-page';
export { QuoteListPage } from './quote-list-page';
export { ProposalSetupPage, proposalSetupFields, type SetupFieldExpectation } from './proposal-setup-page';
export { xpathLiteral, ciContainsText, normalizeText, escapeRegex } from './xpath';
