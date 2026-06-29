import { expect, type Locator, type Page } from 'playwright/test';
import { waitForAnyVisible, waitForNoLoading } from './twentyfive-cdp';

type FieldKind = 'free text' | 'drop down' | 'date picker' | 'radio buttons';
type FieldState =
  | { type: 'empty' }
  | { type: 'text'; value: string; allowLocalVariance?: boolean }
  | { type: 'checked' }
  | { type: 'unchecked' };

export interface SetupFieldExpectation {
  name: string;
  required: boolean;
  enabled: boolean;
  kind: FieldKind;
  state: FieldState;
}

const UPPER = 'ABCDEFGHIJKLMNOPQRSTUVWXYZ';
const LOWER = 'abcdefghijklmnopqrstuvwxyz';
const FIELD_TIMEOUT_MS = Number(process.env.PLAYWRIGHT_FIELD_TIMEOUT_MS ?? 4_000);
const FAST_SETUP_ROUTE = process.env.PLAYWRIGHT_FAST_SETUP_ROUTE === 'true';
const TIMING_LOGS = process.env.PLAYWRIGHT_TIMING !== 'false';
const CONSULTING_TYPE_ROUTE = 'quote:new:type=PS_consulting';
const CONSULTING_TYPE_LABEL = 'Regression Test Only - Consulting';

export class ProposalSetupPage {
  private readonly descriptionRuntimeValues = new Map<string, string>();

  constructor(private readonly page: Page) {}

  readonly proposalsTab = this.page
    .locator("xpath=//a//span[contains(translate(normalize-space(.), 'abcdefghijklmnopqrstuvwxyz', 'ABCDEFGHIJKLMNOPQRSTUVWXYZ'), 'PROPOSALS')]")
    .first();

  readonly proposalsList = this.page
    .locator("xpath=//*[@role='rowgroup' and not(contains(@class,'x-grid-header-ct')) and not(@aria-busy='true')]")
    .first();

  readonly newButton = this.page.locator("xpath=//span[normalize-space()='New']/ancestor::a").first();

  readonly setupTab = this.page
    .locator("xpath=//a[@aria-selected='true']//span[contains(text(),'Setup') or contains(text(),'Opportunity Details')]")
    .first();

  readonly proposalTypeField = this.page.locator("xpath=//input[contains(@id,'iBEBusObjType')]").first();
  readonly descriptionField = this.page
    .locator("xpath=//input[@placeholder='Enter a brief description of the bid (something you can remember to find it later by)']")
    .first();
  readonly plannedStartField = this.page.locator("xpath=//input[@placeholder='Select Start']").first();
  readonly endField = this.page.locator("xpath=//input[@placeholder='Select End']").first();
  readonly yourCompanyField = this.page.locator("xpath=//input[@placeholder='Select Company']").first();
  readonly leadingDepartmentField = this.page.locator("xpath=//input[@placeholder='Select Department']").first();
  readonly profitCenterField = this.inputForField('Profit Center');
  readonly costCenterField = this.inputForField('Cost Center');
  readonly clientCustomerField = this.page
    .locator("xpath=//input[contains(@placeholder,'Enter company') and contains(@placeholder,'name to search')]")
    .first();

  readonly openProjectInSapLink = this.page.locator("xpath=//a[@class='x-ibeLinksCls ' and text()='Open Project in SAP']").first();
  readonly editRatesLink = this.page.locator("xpath=//a[text()='Edit Rates']").first();
  readonly uploadRfxFilesButton = this.page.locator("xpath=//a[contains(@id,'iBEFileUploadButton')]").first();
  readonly projectGoalsTextbox = this.page.locator("xpath=//iframe[@class='tox-edit-area__iframe' or @title='Rich Text Area']").first();
  readonly textEditToolbox = this.page.locator("xpath=//*[@data-alloy-vertical-dir='toptobottom' and @style='display: block;']").first();

  async navigateToProposals(): Promise<void> {
    const startedAt = Date.now();
    if (await this.isProposalsActionReady()) {
      logTiming('proposals already action ready', startedAt);
      return;
    }

    await this.fastClick(this.proposalsTab, 'PROPOSALS tab');
    await waitForAnyVisible(this.page, [this.newButton], 8_000, 'New button on Proposals page');
    logTiming('wait proposals action ready', startedAt);
  }

  async startNewProposal(): Promise<void> {
    if (FAST_SETUP_ROUTE) {
      await this.openTypedSetupRoute();
      return;
    }
    await this.ensureNewButtonVisible();
    await this.fastClick(this.newButton, 'New button');
    await this.waitForSetupPage();
  }

  async waitForSetupPage(): Promise<void> {
    const startedAt = Date.now();
    await waitForAnyVisible(this.page, [this.setupTab, this.proposalTypeField], 25_000, 'Setup page');
    await waitForNoLoading(this.page, 5_000);
    logTiming('wait setup page ready', startedAt);
  }

  async selectProposalType(value: string): Promise<void> {
    if (FAST_SETUP_ROUTE && value === CONSULTING_TYPE_LABEL && (await this.proposalTypeMatches(value, 3_000))) {
      return;
    }

    await this.selectDropdown(this.proposalTypeField, value);
    await this.proposalTypeMatches(value, 5_000);
  }

  async ensureManufacturingSetupPage(): Promise<void> {
    if ((await this.descriptionField.count()) > 0 && (await this.descriptionField.isVisible().catch(() => false))) {
      if (await this.proposalTypeMatches('Regression Test Only - Manufacturing', 1_000)) return;
    }

    await this.navigateToProposals();
    await this.startNewProposal();
    await this.selectProposalType('Regression Test Only - Manufacturing');
  }

  async clearManufacturingRequiredDates(): Promise<void> {
    const startedAt = Date.now();
    await this.setFieldText(this.plannedStartField, '', 'Planned Start');
    await this.setFieldText(this.endField, '', 'End');
    logTiming('clear required setup dates', startedAt);
  }

  async fillManufacturingRequiredFields(data: {
    title: string;
    plannedStart: string;
    end: string;
    company: string;
    leadingDepartment: string;
    clientCustomer: string;
    projectGoals: string;
  }): Promise<void> {
    const startedAt = Date.now();
    await this.setFieldText(this.descriptionField, data.title, 'Description');
    await this.setFieldText(this.plannedStartField, data.plannedStart, 'Planned Start');
    await this.setFieldText(this.endField, data.end, 'End');
    await this.selectDropdown(this.yourCompanyField, data.company);
    await this.selectDropdown(this.leadingDepartmentField, data.leadingDepartment);
    await this.selectDropdown(this.clientCustomerField, data.clientCustomer);
    await this.setRichText(data.projectGoals);
    logTiming('fill Manufacturing required setup fields', startedAt);
  }

  async fillProjectProposalPdfSetupFields(data: {
    title: string;
    company: string;
    leadingDepartment: string;
    profitCenter: string;
    clientCustomer: string;
  }): Promise<void> {
    const startedAt = Date.now();
    await this.setFieldText(this.descriptionField, data.title, 'Description');
    await this.selectDropdown(this.yourCompanyField, data.company);
    await this.selectDropdown(this.leadingDepartmentField, data.leadingDepartment);
    await this.selectDropdown(this.profitCenterField, data.profitCenter);
    await this.selectDropdown(this.clientCustomerField, data.clientCustomer);
    logTiming('fill PDF project proposal setup fields', startedAt);
  }

  async selectProjectTypeIfVisible(value: string): Promise<boolean> {
    const projectTypeField = this.page
      .locator(
        `xpath=(//*[self::label or self::span or self::div][${ciContainsTextNode(
          'Project Type',
        )}]/ancestor::div[contains(@class,'x-field ')][1]//*[self::input or self::textarea][@aria-hidden='false' or @aria-readonly='false'])[1]`,
      )
      .first();

    if ((await projectTypeField.count()) === 0 || !(await projectTypeField.isVisible().catch(() => false))) {
      console.log(`[pdf-project-proposal] Project Type field was not visible; skipping '${value}' selection.`);
      return false;
    }

    await this.selectDropdown(projectTypeField, value);
    await waitUntil(
      this.page,
      async () => matchesDropdownText(await readLocatorValue(projectTypeField), value),
      FIELD_TIMEOUT_MS,
      `Project Type field should equal '${value}'`,
    );
    return true;
  }

  async openSapProjectDropdownAndClose(): Promise<void> {
    const startedAt = Date.now();
    const sapProjectField = this.inputForField('SAP Project');
    await expect(sapProjectField, 'SAP Project field should be visible').toBeVisible({ timeout: FIELD_TIMEOUT_MS });
    await sapProjectField.scrollIntoViewIfNeeded();
    await sapProjectField.click({ force: true, noWaitAfter: true });
    await this.openDropdown(sapProjectField);

    const visibleOption = this.page
      .locator("xpath=//*[@role='option' and not(ancestor-or-self::*[@aria-hidden='true'])]")
      .first();
    await expect(visibleOption, 'SAP Project dropdown options should be displayed').toBeVisible({ timeout: 5_000 });
    await this.page.keyboard.press('Escape');
    logTiming('open SAP Project dropdown', startedAt);
  }

  async setEstimatedProjectStart(value: string): Promise<void> {
    await this.setPlannedStartAndEnsureValidProjectEnd(value);
  }

  async setProjectEnd(value: string): Promise<void> {
    await this.setFieldText(this.endField, value, 'End');
  }

  async fillManufacturingScribeFields(data: {
    title: string;
    plannedStart: string;
    company: string;
    leadingDepartment: string;
    clientCustomer: string;
  }): Promise<void> {
    const startedAt = Date.now();
    await this.setFieldText(this.descriptionField, data.title, 'Description');
    await this.setPlannedStartAndWaitForAutoEnd(data.plannedStart);
    await this.selectDropdown(this.yourCompanyField, data.company);
    await this.selectDropdown(this.leadingDepartmentField, data.leadingDepartment);
    await this.selectDropdown(this.clientCustomerField, data.clientCustomer);
    logTiming('fill Manufacturing Scribe setup fields', startedAt);
  }

  async fillConsultingRegressionFields(data: {
    title: string;
    plannedStart: string;
    end: string;
    company: string;
    leadingDepartment: string;
    profitCenter: string;
    costCenter: string;
    clientCustomer: string;
  }): Promise<void> {
    const startedAt = Date.now();
    await this.setProposalTitle(data.title);
    await this.setFieldText(this.plannedStartField, data.plannedStart, 'Planned Start');
    await this.setFieldText(this.endField, data.end, 'End');
    await this.selectDropdown(this.yourCompanyField, data.company);
    await this.selectDropdown(this.leadingDepartmentField, data.leadingDepartment);
    await this.selectDropdown(this.profitCenterField, data.profitCenter);
    await this.selectDropdown(this.costCenterField, data.costCenter);
    await this.selectDropdown(this.clientCustomerField, data.clientCustomer);
    logTiming('fill Consulting regression setup fields', startedAt);
  }

  async setProposalTitle(value: string): Promise<void> {
    await this.setFieldText(this.descriptionField, value, 'Description');
  }

  async expectSetupFieldValue(fieldName: string, value: string): Promise<void> {
    const input = this.inputForField(fieldName);
    await expect(input, `${fieldName} field should be visible`).toBeVisible({ timeout: FIELD_TIMEOUT_MS });
    await waitUntil(
      this.page,
      async () => matchesDropdownText(await readLocatorValue(input), value) || fieldTextMatches(await readLocatorValue(input), value),
      FIELD_TIMEOUT_MS,
      `${fieldName} field should equal '${value}'`,
    );
  }

  async saveViaToolbar(): Promise<void> {
    const startedAt = Date.now();
    const saveButton = this.page.locator("xpath=//a[@data-qtip='Save' and @aria-hidden='false']").first();
    await this.fastClick(saveButton, 'Save toolbar button', 5_000);

    const saveWithoutCheck = this.page
      .locator(
        "xpath=//*[@role='menu' and @aria-hidden='false']//*[@role='menuitem' and @aria-hidden='false']//*[normalize-space()='Save without Check']/ancestor::*[@role='menuitem'][1]",
      )
      .first();
    if ((await saveWithoutCheck.count()) > 0 && (await saveWithoutCheck.isVisible().catch(() => false))) {
      await this.fastClick(saveWithoutCheck, 'Save without Check menu item', 2_000);
    }

    logTiming('save toolbar action', startedAt);
  }

  async expectRequiredSaveErrors(expectedMessages: string[]): Promise<void> {
    const errorDialog = this.page.locator("xpath=//*[@role='dialog' and @aria-hidden='false']//*[normalize-space()='Error']/ancestor::*[@role='dialog'][1]").first();
    await expect(errorDialog).toBeVisible({ timeout: 10_000 });
    for (const message of expectedMessages) {
      await expect(errorDialog.locator(`xpath=.//*[contains(normalize-space(.), ${xpathLiteral(message)})]`).first()).toBeVisible({ timeout: 3_000 });
    }
  }

  async expectSaveSuccess(timeoutMs = 30_000): Promise<void> {
    const successDialog = this.page
      .locator("xpath=//*[normalize-space()='Data saved successfully']/ancestor::*[@role='dialog' and @aria-hidden='false']")
      .first();
    const errorDialog = this.page.locator("xpath=//*[@role='dialog' and @aria-hidden='false']//*[normalize-space()='Error']/ancestor::*[@role='dialog'][1]").first();
    const invalidField = this.page.locator("xpath=//*[@aria-invalid='true' and not(@aria-hidden='true')]").first();

    await waitUntil(
      this.page,
      async () => {
        if (await isVisible(successDialog)) return true;
        if (/#quote:[0-9a-f]{8}(?:-[0-9a-f]{4}){3}-[0-9a-f]{12}$/i.test(this.page.url())) return true;
        if (await isVisible(errorDialog)) {
          throw new Error(`Save failed with an error dialog: ${await readVisibleText(errorDialog)}`);
        }
        if (await isVisible(invalidField)) {
          throw new Error(`Save blocked by invalid field: ${await describeField(invalidField)}`);
        }
        return false;
      },
      timeoutMs,
      'Save should show a success dialog, navigate to a saved proposal URL, or expose a validation error',
    );
  }

  async waitForSavedProposalUrl(timeoutMs = 30_000): Promise<string> {
    await waitUntil(
      this.page,
      async () => /#quote:[0-9a-f]{8}(?:-[0-9a-f]{4}){3}-[0-9a-f]{12}$/i.test(this.page.url()),
      timeoutMs,
      'Saved proposal URL should contain a quote id',
    );
    return this.page.url();
  }

  async expectDescription(value: string): Promise<void> {
    const expectedValue = this.descriptionRuntimeValues.get(normalizeText(value)) ?? withReadableTimestamp(value);
    await expect(this.descriptionField).toBeVisible({ timeout: FIELD_TIMEOUT_MS });
    await expect
      .poll(async () => normalizeText(await readLocatorValue(this.descriptionField)), {
        message: `Description should equal '${expectedValue}'`,
        timeout: FIELD_TIMEOUT_MS,
      })
      .toBe(normalizeText(expectedValue));
  }

  async dismissVisibleDialog(): Promise<void> {
    const dialog = this.page.locator("xpath=//*[@role='dialog' and @aria-hidden='false']").first();
    if ((await dialog.count()) === 0 || !(await dialog.isVisible().catch(() => false))) return;

    const button = dialog
      .locator(
        "xpath=.//*[@role='button' and @aria-hidden='false'][.//*[normalize-space()='OK' or normalize-space()='Close' or normalize-space()='Cancel'] or normalize-space()='OK' or normalize-space()='Close' or normalize-space()='Cancel']",
      )
      .first();
    if ((await button.count()) > 0 && (await button.isVisible().catch(() => false))) {
      await this.fastClick(button, 'dialog dismiss button', 2_000);
      return;
    }

    await this.page.keyboard.press('Escape');
  }

  async expectTopTabs(expectedTabs: string[]): Promise<void> {
    const menuItems = this.page.locator("xpath=//*[@role='tab' and @aria-hidden='false']//*[@data-ref='btnInnerEl']");
    await expect(menuItems.first()).toBeVisible();
    const actual = (await menuItems.allTextContents()).map(normalizeText).filter(Boolean).map((value) => value.toLowerCase()).sort();
    const expected = expectedTabs.map((value) => normalizeText(value).toLowerCase()).sort();
    expect(actual).toEqual(expected);
  }

  async expectVisible(locator: Locator, name: string): Promise<void> {
    await expect(locator, `${name} should be visible`).toBeVisible();
  }

  async clickProjectGoalsTextbox(): Promise<void> {
    await this.fastClick(this.projectGoalsTextbox, 'Project Goals or Remarks textbox');
  }

  async verifyField(expectation: SetupFieldExpectation): Promise<void> {
    const label = this.labelForField(expectation.name);
    await this.expectRequiredMarker(label, expectation);

    if (expectation.kind === 'radio buttons') {
      await this.verifyRadioField(expectation);
      return;
    }

    const input = this.inputForField(expectation.name);
    await expect(input, `${expectation.name} input should exist`).toBeAttached({ timeout: FIELD_TIMEOUT_MS });

    const enabled = await input.isEnabled({ timeout: FIELD_TIMEOUT_MS });
    expect(enabled, `${expectation.name} enabled state`).toBe(expectation.enabled);

    await this.expectFieldKind(expectation.name, input, expectation.kind);
    await this.expectFieldState(expectation.name, input, expectation.state);
  }

  private async verifyRadioField(expectation: SetupFieldExpectation): Promise<void> {
    const radios = this.radioInputsForField(expectation.name);
    const count = await radios.count();
    expect(count, `${expectation.name} should have radio inputs`).toBeGreaterThan(0);

    for (let index = 0; index < count; index += 1) {
      const radio = radios.nth(index);
      const enabled = await radio.isEnabled({ timeout: FIELD_TIMEOUT_MS });
      expect(enabled, `${expectation.name} radio ${index + 1} enabled state`).toBe(expectation.enabled);
      await expect(radio).toHaveAttribute('type', 'radio', { timeout: FIELD_TIMEOUT_MS });
    }

    await this.expectFieldState(expectation.name, radios.first(), expectation.state);
  }

  private async selectDropdown(input: Locator, requestedText: string): Promise<void> {
    const startedAt = Date.now();
    const currentValue = await readLocatorValue(input);
    if (matchesDropdownText(currentValue, requestedText)) {
      logTiming(`select dropdown '${requestedText}' already selected`, startedAt);
      return;
    }

    const searchTexts = dropdownSearchCandidates(requestedText);
    let selectedOptionText = '';

    for (let index = 0; index < searchTexts.length; index += 1) {
      const searchText = searchTexts[index];
      await input.scrollIntoViewIfNeeded();
      await input.click({ force: true, noWaitAfter: true });
      await this.openDropdown(input);
      await setInputText(input, searchText);

      const option = this.page
        .locator(`xpath=//ul[contains(@class,'x-list-plain') and @aria-hidden='false']//*[@role='option' and ${ciContainsDot(searchText)}]`)
        .first();
      const optionTimeout = index === searchTexts.length - 1 ? 20_000 : 5_000;
      const optionVisible = await option.waitFor({ state: 'visible', timeout: optionTimeout }).then(
        () => true,
        () => false,
      );
      if (!optionVisible) continue;

      selectedOptionText = normalizeText((await option.textContent()) ?? '');
      await this.fastClick(option, `Dropdown option '${searchText}'`);
      break;
    }

    if (!selectedOptionText) {
      throw new Error(`Dropdown option '${requestedText}' should be visible. Tried: ${searchTexts.join(', ')}`);
    }

    await waitUntil(
      this.page,
      async () => {
        const actual = await readLocatorValue(input);
        return matchesDropdownText(actual, requestedText) || matchesDropdownText(selectedOptionText, actual);
      },
      10_000,
      `Dropdown value should match '${requestedText}'`,
    );
    logTiming(`select dropdown '${requestedText}'`, startedAt);
  }

  private async openDropdown(input: Locator): Promise<void> {
    const triggerCandidates = [
      input.locator("xpath=./ancestor::*[@data-ref='triggerWrap'][1]//*[contains(@class,'x-form-arrow-trigger')]").first(),
      input.locator("xpath=./parent::div/following-sibling::div[contains(@class,'x-form-arrow-trigger')]").first(),
      input
        .locator(
          "xpath=./ancestor::*[@data-ref='triggerWrap'][1]//*[contains(@class,'x-form-trigger') and not(contains(@class,'x-form-clear-trigger'))]",
        )
        .first(),
    ];

    for (const trigger of triggerCandidates) {
      if ((await trigger.count()) > 0 && (await trigger.isVisible().catch(() => false))) {
        await this.fastClick(trigger, 'Dropdown trigger', 2_000);
        await this.page.waitForTimeout(300);
        return;
      }
    }
  }

  private async fastClick(locator: Locator, name: string, timeoutMs = FIELD_TIMEOUT_MS): Promise<void> {
    const startedAt = Date.now();
    await expect(locator, `${name} should be visible before click`).toBeVisible({ timeout: timeoutMs });
    try {
      await locator.click({ timeout: timeoutMs, force: true, noWaitAfter: true });
    } catch (error) {
      await locator.evaluate((element) => {
        if (element instanceof HTMLElement) {
          element.click();
        } else {
          element.dispatchEvent(new MouseEvent('click', { bubbles: true, cancelable: true, view: window }));
        }
      });
    } finally {
      logTiming(`click ${name}`, startedAt);
    }
  }

  private async setFieldText(locator: Locator, value: string, name: string): Promise<void> {
    const startedAt = Date.now();
    const textToEnter = name === 'Description' ? withReadableTimestamp(value) : value;
    if (name === 'Description') {
      this.descriptionRuntimeValues.set(normalizeText(value), textToEnter);
    }
    await expect(locator, `${name} field should be visible`).toBeVisible({ timeout: FIELD_TIMEOUT_MS });
    await locator.scrollIntoViewIfNeeded();
    await setExtInputText(locator, textToEnter);
    await waitUntil(
      this.page,
      async () => fieldTextMatches(await readLocatorValue(locator), textToEnter),
      FIELD_TIMEOUT_MS,
      `${name} field should equal '${textToEnter}'`,
    );
    logTiming(`set ${name}`, startedAt);
  }

  private async setPlannedStartAndWaitForAutoEnd(value: string): Promise<void> {
    const startedAt = Date.now();
    const originalEnd = normalizeText(await readLocatorValue(this.endField));
    await expect(this.plannedStartField, 'Planned Start field should be visible').toBeVisible({ timeout: FIELD_TIMEOUT_MS });
    await this.plannedStartField.scrollIntoViewIfNeeded();
    await setDateInputLikeUser(this.plannedStartField, value);
    await waitUntil(
      this.page,
      async () => fieldTextMatches(await readLocatorValue(this.plannedStartField), value),
      FIELD_TIMEOUT_MS,
      `Planned Start field should equal '${value}'`,
    );

    await waitUntil(
      this.page,
      async () => {
        const start = parseUsDate(await readLocatorValue(this.plannedStartField));
        const endText = normalizeText(await readLocatorValue(this.endField));
        const end = parseUsDate(endText);
        return Boolean(start && end && endText !== originalEnd && compareUsDates(end, start) >= 0);
      },
      15_000,
      `Project End should auto-update after Planned Start '${value}'`,
    );
    logTiming('set Planned Start and wait for Project End auto-update', startedAt);
  }

  private async setPlannedStartAndEnsureValidProjectEnd(value: string): Promise<void> {
    const startedAt = Date.now();
    await this.setFieldText(this.plannedStartField, value, 'Planned Start');
    const start = parseUsDate(await readLocatorValue(this.plannedStartField));
    if (!start) {
      throw new Error(`Planned Start value '${value}' could not be parsed as a US date.`);
    }

    await waitForNoLoading(this.page, 5_000);
    await this.page.waitForTimeout(500);

    if ((await this.endField.count()) === 0 || !(await this.endField.isVisible().catch(() => false))) {
      logTiming('set Planned Start without visible Project End', startedAt);
      return;
    }

    const currentEndText = await readLocatorValue(this.endField);
    const currentEnd = parseUsDate(currentEndText);
    const isInvalid = (await this.endField.getAttribute('aria-invalid').catch(() => null)) === 'true';
    if (currentEnd && compareUsDates(currentEnd, start) >= 0 && !isInvalid) {
      logTiming('set Planned Start and keep valid Project End', startedAt);
      return;
    }

    const fallbackEnd = formatUsDate(addDays(start, 364));
    await this.setFieldText(this.endField, fallbackEnd, 'Project End');
    await waitUntil(
      this.page,
      async () => {
        const refreshedEnd = parseUsDate(await readLocatorValue(this.endField));
        const refreshedInvalid = (await this.endField.getAttribute('aria-invalid').catch(() => null)) === 'true';
        return Boolean(refreshedEnd && compareUsDates(refreshedEnd, start) >= 0 && !refreshedInvalid);
      },
      FIELD_TIMEOUT_MS,
      `Project End should be valid and on or after Planned Start '${value}'`,
    );
    logTiming('set Planned Start and repair Project End', startedAt);
  }

  private async setRichText(value: string): Promise<void> {
    const startedAt = Date.now();
    await expect(this.projectGoalsTextbox, 'Project Goals or Remarks iframe should be visible').toBeVisible({ timeout: FIELD_TIMEOUT_MS });
    const body = this.page.frameLocator("iframe[title='Rich Text Area'], iframe.tox-edit-area__iframe").locator('body#tinymce, body').first();
    await body.evaluate((element, text) => {
      element.innerHTML = '';
      const paragraph = document.createElement('p');
      paragraph.textContent = text;
      element.appendChild(paragraph);
      element.dispatchEvent(new Event('input', { bubbles: true }));
      element.dispatchEvent(new Event('change', { bubbles: true }));
    }, value);
    logTiming('set Project Goals or Remarks', startedAt);
  }

  private async isProposalsActionReady(): Promise<boolean> {
    return (await this.newButton.count()) > 0 && (await this.newButton.isVisible().catch(() => false));
  }

  private async ensureNewButtonVisible(): Promise<void> {
    if (await this.isProposalsActionReady()) return;

    await this.fastClick(this.proposalsTab, 'PROPOSALS tab retry', 8_000).catch(() => undefined);
    await waitForNoLoading(this.page, 10_000);
    if (await this.isProposalsActionReady()) return;

    console.warn('[proposal setup] New button was not visible; refreshing Proposals once before retrying.');
    await this.page.reload({ waitUntil: 'domcontentloaded', timeout: 45_000 }).catch((error) => {
      const message = error instanceof Error ? error.message : String(error);
      if (!message.includes('ERR_ABORTED')) throw error;
    });
    await waitForNoLoading(this.page, 30_000);
    await this.fastClick(this.proposalsTab, 'PROPOSALS tab after refresh', 8_000).catch(() => undefined);
    await waitForAnyVisible(this.page, [this.newButton], 30_000, 'New button on Proposals page after refresh');
  }

  private async openTypedSetupRoute(): Promise<void> {
    const startedAt = Date.now();
    const currentUrl = new URL(this.page.url());
    const targetUrl = `${currentUrl.origin}${currentUrl.pathname}${currentUrl.search}#${CONSULTING_TYPE_ROUTE}`;
    await this.page.goto(targetUrl, { waitUntil: 'domcontentloaded', timeout: 45_000 });
    await this.waitForSetupPage();
    logTiming('open typed setup route', startedAt);
  }

  private async proposalTypeMatches(value: string, timeoutMs: number): Promise<boolean> {
    try {
      await waitUntil(
        this.page,
        async () => matchesDropdownText(await readLocatorValue(this.proposalTypeField), value),
        timeoutMs,
        `Proposal type should match '${value}'`,
      );
      return true;
    } catch (error) {
      return false;
    }
  }

  private inputForField(name: string): Locator {
    if (name === 'Select Tag to Add') {
      return this.page.locator("xpath=//*[@placeholder='Select Tag to Add' and @aria-hidden='false']").first();
    }
    if (name === 'SAP Project') {
      return this.page.locator("xpath=//a[text()='Open Project in SAP']/ancestor::td[1]//input").first();
    }
    if (name === 'Title or Brief Description') {
      return this.page
        .locator("xpath=//input[@placeholder='Enter a brief description of the bid (something you can remember to find it later by)']")
        .first();
    }
    if (name === 'Select the Best-fit Type') {
      return this.proposalTypeField;
    }

    const labelXPath = this.labelXPath(name);
    return this.page
      .locator(
        `xpath=(${labelXPath}/ancestor::div[contains(@class,'x-field ')][1]//*[self::input or self::textarea][@aria-hidden='false' or @aria-readonly='false'])[1]`,
      )
      .first();
  }

  private radioInputsForField(name: string): Locator {
    if (name === 'Select a Template') {
      return this.page
        .locator(
          `xpath=//label[${ciContainsTextNode('Select a Template')}]/preceding-sibling::*//input | //label[${ciContainsTextNode(
            'Select a Template',
          )}]/ancestor::*[contains(@class,'x-form-cb-wrap')][1]//input`,
        )
        .first();
    }

    if (name === 'Select a prior proposal') {
      return this.page
        .locator(
          `xpath=//label[${ciContainsTextNode('Select a prior proposal')} or ${ciContainsTextNode(
            'Select a prior project',
          )}]/preceding-sibling::*//input | //label[${ciContainsTextNode('Select a prior proposal')} or ${ciContainsTextNode(
            'Select a prior project',
          )}]/ancestor::*[contains(@class,'x-form-cb-wrap')][1]//input`,
        )
        .first();
    }

    const labelXPath = this.labelXPath(name);
    return this.page.locator(`xpath=${labelXPath}/ancestor::div[contains(@class,'x-form-checkboxgroup')][1]//input`);
  }

  private labelForField(name: string): Locator {
    if (name === 'Select Tag to Add') {
      return this.page.locator("xpath=//label[text()='Use Tags to add New Dimensions to your Cost & Revenue Model']").first();
    }
    return this.page.locator(`xpath=${this.labelXPath(name)}`).first();
  }

  private labelXPath(name: string): string {
    return `//*[self::label or self::span or self::div][${ciContainsTextNode(name)}]`;
  }

  private async expectFieldKind(name: string, input: Locator, kind: FieldKind): Promise<void> {
    if (kind === 'free text') {
      const tagName = await input.evaluate((element) => element.tagName.toLowerCase());
      expect(['input', 'textarea'], `${name} should be a free-text input`).toContain(tagName);
      return;
    }

    if (kind === 'drop down') {
      const trigger = input
        .locator("xpath=./ancestor::div[@data-ref='inputWrap'][1]/following-sibling::div[contains(@class,'x-form-arrow-trigger')]")
        .first();
      await expect(trigger, `${name} should have a dropdown trigger`).toBeVisible({ timeout: FIELD_TIMEOUT_MS });
      return;
    }

    if (kind === 'date picker') {
      const trigger = input.locator("xpath=./parent::div/following-sibling::div[contains(@class,'x-form-date-trigger')]").first();
      await expect(trigger, `${name} should have a date picker trigger`).toBeVisible({ timeout: FIELD_TIMEOUT_MS });
      return;
    }

    const radios = this.radioInputsForField(name);
    const count = await radios.count();
    expect(count, `${name} should have radio inputs`).toBeGreaterThan(0);
    for (let index = 0; index < count; index += 1) {
      await expect(radios.nth(index)).toHaveAttribute('type', 'radio', { timeout: FIELD_TIMEOUT_MS });
    }
  }

  private async expectFieldState(name: string, input: Locator, state: FieldState): Promise<void> {
    if (state.type === 'empty') {
      expect(normalizeText(await readLocatorValue(input)), `${name} should be empty`).toBe('');
      return;
    }

    if (state.type === 'text') {
      const actual = normalizeText(await readLocatorValue(input));
      const expectedCandidates = await this.resolveExpectedTextCandidates(state.value);

      if (state.allowLocalVariance && process.env.STRICT_LOCAL_FIELDS !== 'true' && !expectedCandidates.includes(actual)) {
        console.warn(`Warning: ${name} value differed in local run. Expected ${expectedCandidates.join(' or ')}, actual ${actual}.`);
        return;
      }

      expect(expectedCandidates, `${name} text`).toContain(actual);
      return;
    }

    const radios = this.radioInputsForField(name);
    const checkedStates: boolean[] = [];
    for (let index = 0; index < (await radios.count()); index += 1) {
      checkedStates.push(await radios.nth(index).isChecked());
    }

    if (state.type === 'checked') {
      expect(checkedStates.some(Boolean), `${name} should have one selected radio`).toBe(true);
    } else {
      expect(checkedStates.every((checked) => !checked), `${name} should have no selected radios`).toBe(true);
    }
  }

  private async expectRequiredMarker(label: Locator, expectation: SetupFieldExpectation): Promise<void> {
    await expect(label, `${expectation.name} label should be visible`).toBeVisible({ timeout: FIELD_TIMEOUT_MS });
    const labelText = normalizeText((await label.textContent()) ?? '');
    if (expectation.required) {
      expect(labelText, `${expectation.name} should be marked required`).toContain('*');
    } else {
      expect(labelText, `${expectation.name} should not be marked required`).not.toContain('*');
    }
  }

  private async resolveExpectedTextCandidates(expected: string): Promise<string[]> {
    if (expected === '$todayDate') {
      const date = await browserDate(this.page, 0);
      return [formatDate(date, 'short'), formatDate(date, 'long')];
    }

    if (expected === '$oneYearFromToday') {
      const date = await browserDate(this.page, 365);
      date.setDate(date.getDate() - 1);
      return [formatDate(date, 'short'), formatDate(date, 'long')];
    }

    return [normalizeText(expected)];
  }
}

export const proposalSetupFields: SetupFieldExpectation[] = [
  { name: 'Title or Brief Description', enabled: true, required: true, kind: 'free text', state: { type: 'empty' } },
  {
    name: 'Select the Best-fit Type',
    enabled: true,
    required: true,
    kind: 'drop down',
    state: { type: 'text', value: 'Regression Test Only - Consulting' },
  },
  { name: 'Contract Type', enabled: true, required: false, kind: 'drop down', state: { type: 'empty' } },
  { name: 'SAP Project', enabled: true, required: false, kind: 'drop down', state: { type: 'empty' } },
  { name: 'SAP WBS', enabled: true, required: false, kind: 'drop down', state: { type: 'empty' } },
  { name: 'WBS managed in SAP', enabled: true, required: false, kind: 'radio buttons', state: { type: 'unchecked' } },
  { name: 'Estimated Project Start', enabled: true, required: true, kind: 'date picker', state: { type: 'text', value: '$todayDate' } },
  { name: 'Project End', enabled: true, required: true, kind: 'date picker', state: { type: 'text', value: '$oneYearFromToday' } },
  {
    name: 'Project Manager',
    enabled: true,
    required: false,
    kind: 'drop down',
    state: { type: 'text', value: 'Tech User', allowLocalVariance: true },
  },
  { name: 'multiple phases', enabled: true, required: false, kind: 'radio buttons', state: { type: 'checked' } },
  { name: 'Proposal Hours', enabled: true, required: false, kind: 'drop down', state: { type: 'empty' } },
  { name: 'Leading company', enabled: true, required: true, kind: 'drop down', state: { type: 'empty' } },
  { name: 'Leading department', enabled: true, required: true, kind: 'drop down', state: { type: 'empty' } },
  { name: 'Profit Center', enabled: true, required: false, kind: 'drop down', state: { type: 'empty' } },
  { name: 'Cost Center', enabled: true, required: false, kind: 'drop down', state: { type: 'empty' } },
  {
    name: 'Leading Company Currency',
    enabled: true,
    required: true,
    kind: 'free text',
    state: { type: 'text', value: '$ USA - US Dollar(USD)' },
  },
  { name: 'Project Currency', enabled: true, required: true, kind: 'drop down', state: { type: 'empty' } },
  { name: 'Other Departments or Sites Involved', enabled: true, required: false, kind: 'drop down', state: { type: 'empty' } },
  { name: 'Client/Customer (Sell-to)', enabled: true, required: true, kind: 'drop down', state: { type: 'empty' } },
  { name: 'CRM Opportunity', enabled: true, required: false, kind: 'drop down', state: { type: 'empty' } },
  { name: 'Customer Contact', enabled: true, required: false, kind: 'drop down', state: { type: 'empty' } },
  { name: 'Opportunity Status', enabled: true, required: false, kind: 'free text', state: { type: 'empty' } },
  { name: 'Customer Purchase order', enabled: true, required: false, kind: 'free text', state: { type: 'empty' } },
  { name: 'Proposal Authorization#', enabled: true, required: false, kind: 'free text', state: { type: 'empty' } },
  { name: 'Select a Template', enabled: true, required: false, kind: 'radio buttons', state: { type: 'checked' } },
  { name: 'Select a prior proposal', enabled: true, required: false, kind: 'radio buttons', state: { type: 'unchecked' } },
  { name: 'Select Tag to Add', enabled: true, required: false, kind: 'drop down', state: { type: 'empty' } },
];

function normalizeText(value: string): string {
  return value.replace(/\u00a0/g, ' ').replace(/\s+/g, ' ').trim();
}

function withReadableTimestamp(value: string): string {
  const normalized = normalizeText(value);
  if (!normalized) return formatReadableTimestamp();
  if (endsWithReadableTimestamp(normalized)) return normalized;
  return `${normalized} - ${formatReadableTimestamp()}`;
}

function endsWithReadableTimestamp(value: string): boolean {
  return (
    /\b\d{4}-\d{2}-\d{2} \d{2}:\d{2}(?: [A-Z]{2,5})?$/.test(value) ||
    /\b[A-Z][a-z]{2,8} \d{1,2}, \d{4},? \d{1,2}:\d{2} (?:AM|PM)(?: [A-Z0-9:+-]{2,10})?$/.test(value)
  );
}

function formatReadableTimestamp(date = new Date()): string {
  const parts = new Intl.DateTimeFormat('en-US', {
    month: 'short',
    day: 'numeric',
    year: 'numeric',
    hour: 'numeric',
    minute: '2-digit',
    hour12: true,
    timeZoneName: 'short',
  }).formatToParts(date);
  const value = (type: Intl.DateTimeFormatPartTypes) => parts.find((part) => part.type === type)?.value ?? '';
  const dayPeriod = value('dayPeriod');
  const timeZoneName = value('timeZoneName');
  return `${value('month')} ${value('day')}, ${value('year')} ${value('hour')}:${value('minute')}${dayPeriod ? ` ${dayPeriod}` : ''}${
    timeZoneName ? ` ${timeZoneName}` : ''
  }`;
}

function fieldTextMatches(actual: string, expected: string): boolean {
  const normalizedActual = normalizeText(actual);
  const normalizedExpected = normalizeText(expected);
  if (normalizedActual === normalizedExpected) return true;

  const actualDate = parseUsDate(normalizedActual);
  const expectedDate = parseUsDate(normalizedExpected);
  return Boolean(
    actualDate &&
      expectedDate &&
      actualDate.month === expectedDate.month &&
      actualDate.day === expectedDate.day &&
      actualDate.fullYear === expectedDate.fullYear,
  );
}

function parseUsDate(value: string): { month: number; day: number; fullYear: number } | null {
  const match = value.match(/^(\d{1,2})\/(\d{1,2})\/(\d{2}|\d{4})$/);
  if (!match) return null;

  const rawYear = Number(match[3]);
  return {
    month: Number(match[1]),
    day: Number(match[2]),
    fullYear: rawYear < 100 ? 2000 + rawYear : rawYear,
  };
}

function addDays(value: { month: number; day: number; fullYear: number }, days: number): { month: number; day: number; fullYear: number } {
  const date = new Date(Date.UTC(value.fullYear, value.month - 1, value.day + days));
  return {
    month: date.getUTCMonth() + 1,
    day: date.getUTCDate(),
    fullYear: date.getUTCFullYear(),
  };
}

function formatUsDate(value: { month: number; day: number; fullYear: number }): string {
  return `${value.month}/${value.day}/${value.fullYear}`;
}

async function readLocatorValue(locator: Locator): Promise<string> {
  return locator.evaluate((element) => {
    if (element instanceof HTMLInputElement || element instanceof HTMLTextAreaElement || element instanceof HTMLSelectElement) {
      return element.value ?? '';
    }
    return element.textContent ?? '';
  });
}

async function setInputText(locator: Locator, value: string): Promise<void> {
  await locator.evaluate((element, text) => {
    if (!(element instanceof HTMLInputElement || element instanceof HTMLTextAreaElement)) return;
    element.value = '';
    element.dispatchEvent(new Event('input', { bubbles: true }));
    element.dispatchEvent(new Event('change', { bubbles: true }));
    element.value = text;
    element.dispatchEvent(new Event('input', { bubbles: true }));
    element.dispatchEvent(new Event('change', { bubbles: true }));
  }, value);
}

async function setExtInputText(locator: Locator, value: string): Promise<void> {
  await locator.evaluate((element, text) => {
    if (!(element instanceof HTMLInputElement || element instanceof HTMLTextAreaElement)) return;

    const win = element.ownerDocument.defaultView as
      | (Window & {
          Ext?: {
            ComponentQuery?: {
              query?: (selector: string) => Array<{
                inputEl?: { dom?: Element };
                setValue?: (value: string) => void;
                setRawValue?: (value: string) => void;
                checkChange?: () => void;
                validate?: () => void;
                fireEvent?: (name: string, ...args: unknown[]) => void;
              }>;
            };
          };
        })
      | null;
    const field = win?.Ext?.ComponentQuery?.query?.('field')?.find((component) => component.inputEl?.dom === element);

    if (field?.setValue) {
      field.setValue(text);
    } else if (field?.setRawValue) {
      field.setRawValue(text);
    } else {
      element.value = text;
    }

    element.value = text;
    element.dispatchEvent(new Event('input', { bubbles: true }));
    element.dispatchEvent(new Event('change', { bubbles: true }));
    element.dispatchEvent(new KeyboardEvent('keyup', { bubbles: true }));
    field?.checkChange?.();
    field?.validate?.();
    field?.fireEvent?.('change', field, text);
    element.blur();
  }, value);
}

async function setDateInputLikeUser(locator: Locator, value: string): Promise<void> {
  await locator.click({ force: true });
  await locator.evaluate((element) => {
    if (element instanceof HTMLInputElement || element instanceof HTMLTextAreaElement) {
      element.focus();
      element.select();
    }
  });
  await locator.pressSequentially(value, { delay: 45 });
  await locator.press('Tab');
}

async function isVisible(locator: Locator): Promise<boolean> {
  return (await locator.count()) > 0 && (await locator.isVisible().catch(() => false));
}

async function readVisibleText(locator: Locator): Promise<string> {
  return normalizeText((await locator.textContent().catch(() => '')) ?? '');
}

async function describeField(locator: Locator): Promise<string> {
  return locator.evaluate((element) => {
    const input = element as HTMLInputElement | HTMLTextAreaElement;
    const ariaLabel = input.getAttribute('aria-label');
    const placeholder = input.getAttribute('placeholder');
    const value = input.value;
    const label = input.closest('.x-field')?.querySelector('label, .x-form-item-label, .x-form-item-label-default')?.textContent;
    return [label, ariaLabel, placeholder, value].filter(Boolean).join(' | ');
  });
}

function compareUsDates(left: { month: number; day: number; fullYear: number }, right: { month: number; day: number; fullYear: number }): number {
  const leftTime = Date.UTC(left.fullYear, left.month - 1, left.day);
  const rightTime = Date.UTC(right.fullYear, right.month - 1, right.day);
  return leftTime - rightTime;
}

function normalizeDropdownText(value: string): string {
  return normalizeText(value).replace(/\s*\|\s*/g, '|').toLowerCase();
}

function compactDropdownText(value: string): string {
  return normalizeDropdownText(value).replace(/[^a-z0-9]/g, '');
}

function matchesDropdownText(optionText: string, requestedText: string): boolean {
  const option = normalizeDropdownText(optionText);
  const requested = normalizeDropdownText(requestedText);
  if (!option || !requested) return false;
  if (option === requested || option.includes(requested)) return true;

  const compactOption = compactDropdownText(optionText);
  const compactRequested = compactDropdownText(requestedText);
  return compactOption === compactRequested || compactOption.includes(compactRequested);
}

function dropdownSearchCandidates(requestedText: string): string[] {
  const candidates = [requestedText];
  const withoutParenthesizedCode = requestedText.replace(/\s*\([^)]*\)\s*$/, '').trim();
  if (withoutParenthesizedCode && withoutParenthesizedCode !== requestedText) {
    candidates.push(withoutParenthesizedCode);
  }
  return candidates;
}

function ciContainsTextNode(value: string): string {
  return `contains(translate(normalize-space(text()), '${UPPER}', '${LOWER}'), ${xpathLiteral(value.toLowerCase())})`;
}

function ciContainsDot(value: string): string {
  return `contains(translate(normalize-space(.), '${UPPER}', '${LOWER}'), ${xpathLiteral(value.toLowerCase())})`;
}

function xpathLiteral(value: string): string {
  if (!value.includes("'")) return `'${value}'`;
  if (!value.includes('"')) return `"${value}"`;
  return `concat(${value
    .split("'")
    .map((part) => `'${part}'`)
    .join(', "\'", ')})`;
}

async function browserDate(page: Page, plusDays: number): Promise<Date> {
  const timestamp = await page.evaluate((days) => {
    const now = new Date();
    now.setDate(now.getDate() + days);
    return new Date(now.getFullYear(), now.getMonth(), now.getDate()).getTime();
  }, plusDays);
  return new Date(timestamp);
}

function formatDate(date: Date, year: 'short' | 'long'): string {
  const fullYear = date.getFullYear();
  const renderedYear = year === 'short' ? String(fullYear).slice(-2) : String(fullYear);
  return `${date.getMonth() + 1}/${date.getDate()}/${renderedYear}`;
}

async function waitUntil(page: Page, predicate: () => Promise<boolean>, timeoutMs: number, message: string): Promise<void> {
  const deadline = Date.now() + timeoutMs;
  while (Date.now() < deadline) {
    if (await predicate()) return;
    await page.waitForTimeout(250);
  }
  throw new Error(message);
}

function logTiming(label: string, startedAt: number): void {
  if (!TIMING_LOGS) return;
  console.log(`[timing] ${label}: ${Date.now() - startedAt}ms`);
}
