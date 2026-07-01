import { type Locator } from 'playwright/test';
import { fastClick } from '../twentyfive-ui';
import { BasePage } from './base-page';

/** The Proposals/Quote list: the New button and the list rowgroup. */
export class QuoteListPage extends BasePage {
  /** @label New */
  readonly newButton: Locator = this.page.locator("xpath=//span[normalize-space()='New']/ancestor::a").first();

  readonly listRowGroup: Locator = this.page
    .locator("xpath=//*[@role='rowgroup' and not(contains(@class,'x-grid-header-ct')) and not(@aria-busy='true')]")
    .first();

  async clickNew(): Promise<void> {
    await fastClick(this.newButton, 'New proposal button', 20_000);
  }
}
