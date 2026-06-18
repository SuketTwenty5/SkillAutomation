package t5.ipe.cucumber.objects.pages.estimates;


import com.codeborne.selenide.Condition;
import org.openqa.selenium.support.FindBy;
import t5.ipe.cucumber.core.web.elements.BaseWebElement;
import t5.ipe.cucumber.core.web.elements.LongWaitElement;
import t5.ipe.cucumber.core.web.elements.TextElement;
import t5.ipe.cucumber.core.web.interfaces.annotations.ElementName;
import t5.ipe.cucumber.core.web.interfaces.annotations.WebPage;
import t5.ipe.cucumber.core.web.util.AllureUtils;
import t5.ipe.cucumber.objects.elements.*;
import t5.ipe.cucumber.objects.elements.tables.*;
import t5.ipe.cucumber.objects.pages.MainPage;

import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.sleep;
import static t5.ipe.cucumber.core.web.util.web.WebUtil.setCustomTimeoutSec;
import static t5.ipe.cucumber.core.web.util.web.WebUtil.setDefaultTimeout;

/**
 * Estimates - Labor page.
 */
@WebPage(value = "Estimate Labor", urlTemplate = ".*#boe:[0-9A-Fa-f]{32}$|.*boe:[0-9A-Fa-f]{8}(?:-[0-9A-Fa-f]{4}){3}-[0-9A-Fa-f]{12}$", tabName = "Labor")
public class EstimatesLabor extends MainPage {
    public static final String LABOR_TAB = "'LABOR' tab";
    public static final String RIGHT_TABLE = "'Right' table";
    private static final String RIGHT_HEADER_COLUMN_XPATH = "'Right' header";
    private static final String RIGHT_TABLE_XPATH = "'Right table' xpath";


    @ElementName(LABOR_TAB)
    @FindBy(xpath = "//a[@aria-selected='true']//span[text()='Labor' or text()='Engineering']")
    LongWaitElement costStructureTab;

    @ElementName("'FTE' column header")
    @FindBy(xpath = "//div[not(contains(@class,'x-hidden-offsets')) and (contains (@class, 'x-panel x-tabpanel-child')) and @aria-hidden='false']//*[@role='columnheader' and @aria-hidden='false']//*[@class='x-column-header-text-inner' and contains(text(),'FTE')]/ancestor::*[@role='columnheader' and @aria-hidden='false']")
    BaseWebElement fteColumnHeader;

        @ElementName("'Overhead Cost' column header")
    @FindBy(xpath = "//*[@role='columnheader' and @aria-hidden='false']//*[text()='Overhead Cost']/ancestor::*[@role='columnheader' and @aria-hidden='false']")
    BaseWebElement overheadColumnHeader;

    @ElementName("'Overhead Cost' column chevron")
    @FindBy(xpath = "//*[@role='columnheader' and @aria-hidden='false']//*[text()='Overhead Cost']/ancestor::*[@role='columnheader' and @aria-hidden='false']//*[@data-ref='triggerEl']")
    BaseWebElement overheadColumnChevron;

    @ElementName("'FTE' column chevron")
    @FindBy(xpath = "//div[not(contains(@class,'x-hidden-offsets')) and (contains (@class, 'x-panel x-tabpanel-child')) and @aria-hidden='false']//*[@role='columnheader' and @aria-hidden='false']//*[@class='x-column-header-text-inner' and contains(text(),'FTE')]/ancestor::*[@role='columnheader' and @aria-hidden='false']//*[@class='x-column-header-trigger']")
    BaseWebElement fteColumnChevron;

    @ElementName("'Proposals' tab")
    @FindBy(xpath = "//a//span[contains(text(),'Proposals') or contains(text(),'PROPOSALS')]")
    BaseWebElement proposalsTab;

    @ElementName("top menu")
    @FindBy(xpath = "//*[(@role='toolbar' and @aria-hidden='false') or contains(@class,'PricingAppSearchContainer')]//*[contains(@class,'TopMenuButton') and @aria-hidden='false']")
    MenuItemField topMenu;

    @ElementName("'Fringe Cost' option")
    @FindBy(xpath = "//*[contains(@class,'x-menu-item-unchecked')]//*[@class='x-menu-item-link']//*[@data-ref='textEl' and text()='Fringe Cost']/following-sibling::*")
    BaseWebElement fringeCostOption;


     @ElementName("'GA Cost' column header")
    @FindBy(xpath = "//*[@role='columnheader' and @aria-hidden='false']//*[text()='G&A Cost']/ancestor::*[@role='columnheader' and @aria-hidden='false']")
    BaseWebElement gaColumnHeader;

     @ElementName("'GA Cost' column chevron")
    @FindBy(xpath = "//*[@role='columnheader' and @aria-hidden='false']//*[text()='G&A Cost']/ancestor::*[@role='columnheader' and @aria-hidden='false']//*[@data-ref='triggerEl']")
    BaseWebElement gaColumnChevron;

    @ElementName("'Overhead Cost' option")
    @FindBy(xpath = "//*[contains(@class,'x-menu-item-unchecked')]//*[@class='x-menu-item-link']//*[@data-ref='textEl' and text()='Overhead Cost']/following-sibling::*")
    BaseWebElement overheadCostOption;




    @ElementName("'GA Cost' option")
    @FindBy(xpath = "//*[contains(@class,'x-menu-item-unchecked')]//*[@class='x-menu-item-link']//*[@data-ref='textEl' and text()='G&A Cost']/following-sibling::*")
    BaseWebElement gaCostOption;

    @ElementName("'plus' searchIcon")
    @FindBy(xpath = "//*[@role='grid' and @aria-hidden='false']//*[@role='toolbar' and @aria-hidden='false']//*[contains(@class,'fa-plus')]")
    BaseWebElement plusSearchIcon;

    @ElementName("'Proposal BOM' tab")
    @FindBy(xpath = "//a[@role='tab' and @aria-hidden='false']//span[text()='Proposal BOM' or text()='Bom']")
    BaseWebElement proposalBOMTab;

    @ElementName("'Confirm Formula, Define Local Parameters' popUp")
    @FindBy(xpath = "//*[@role='dialog' and @aria-hidden='false']//*[text()='Confirm Formula, Define Local Parameters']")
    BaseWebElement confirmFormulaDefineLocalPopUp;

    @ElementName(RIGHT_HEADER_COLUMN_XPATH)
    @FindBy(xpath = "//div[not(contains(@class,'x-hidden-offsets')) and (contains (@class, 'x-panel x-tabpanel-child')) and @aria-hidden='false']//*[contains(@class,'x-panel x-grid-inner-normal') and @aria-hidden='false']//div[@aria-hidden='false' and @role='columnheader']")
    LongWaitElement rightHeaderXpath;

    @ElementName(RIGHT_TABLE_XPATH)
    @FindBy(xpath = "//div[not(contains(@class,'x-hidden-offsets')) and (contains (@class, 'x-panel x-tabpanel-child')) and @aria-hidden='false']//*[contains(@class,'x-panel x-grid-inner-normal') and @aria-hidden='false']")
    LongWaitElement rightTableXpath;

    @ElementName("'Description Gear' menuItems")
    @FindBy(xpath = "//*[@role='menu' and @aria-hidden='false' and @aria-expanded='true']")
    MenuItemField descriptionGearMenuItems;

    @ElementName("'Edit Details' menuItem")
    @FindBy(xpath = "//div[contains(@class, 'x-menu') and @aria-hidden='false']//a[span[text()='Edit Details']]")
    BaseWebElement editDetailsMenuItem;

    @ElementName("'Formula' table")
    @FindBy(xpath = "//*[@role='dialog' and @aria-hidden='false']//*[@role='tabpanel'] and @aria-hidden='false'")
    FormulaTable formulaTable;

    @ElementName("'Regression Service Labor Base' parameter")
    @FindBy(xpath = "//*[@role='dialog' and @aria-hidden='false']//*[text()='Regression Service Labor Base (USD)']")
    BaseWebElement regressionServiceLaborBaseUSDParameter;

    @ElementName("'Regression Normal Labor Fringe Rate' parameter")
    @FindBy(xpath = "//*[@role='dialog' and @aria-hidden='false']//*[text()='Regression Normal Labor Fringe Rate']")
    BaseWebElement regressionNormalLaborFringeRate;

    @ElementName("'Dialog Confirm' button")
    @FindBy(xpath = "//*[@role='dialog' and @aria-hidden='false']//*[text()='Confirm']")
    BaseWebElement dialogConfirm;

    @ElementName("'Dialog Result' input")
    @FindBy(xpath = "//*[@role='dialog' and @aria-hidden='false']//*[contains(text(),'Result')]/ancestor::label/following-sibling::*//input[@aria-hidden='false']")
    IpeField dialogResultInput;

    @ElementName(RIGHT_TABLE)
    @FindBy(xpath = "//*[contains(@class,'x-grid-scrollbar-clipper') and not(contains(@class,'x-grid-scrollbar-clipper-locked'))]")
    LongWaitElement rightTable;

    @ElementName("'Filter' button")
    @FindBy(xpath = "//*[@data-qtip='Filters' and @aria-hidden='false']")
    BaseWebElement filterButton;

    @ElementName("'Update Cost' link")
    @FindBy(xpath = "//*[@role='dialog' and @aria-hidden='false']//*[contains(text(),'Update Cost')]")
    BaseWebElement updateCostLink;

    @ElementName("'Amortization Schedule' table")
    @FindBy(xpath = "(//div[not(contains(@class,'x-hidden-offsets')) and (contains (@class, 'x-panel x-tabpanel-child'))]//div[@class='x-grid-item-container'])[2]")
    LaborAmortizationTable laborAmortizationTable;

    @ElementName("'Close' button")
    @FindBy(xpath = "//*[@role='dialog' and @aria-hidden='false']//*[text()='Close']")
    BaseWebElement closeButton;

    @ElementName("'Start of Amortization' checkBox")
    @FindBy(xpath = "//label[@data-ref='boxLabelEl' and contains(text(), 'start')]/preceding-sibling::span")
    IpeCheckBox startOfAmortizationCheckBox;

    @ElementName("'End of Amortization' checkBox")
    @FindBy(xpath = "//*[@role='dialog' and @aria-hidden='false']//*[@role='tabpanel' and @aria-hidden='false']//*[contains(text(),'end') and @data-ref='boxLabelEl']/ancestor::div[@class='x-form-cb-wrap-inner']//span[contains(@class, 'x-form-checkbox')]")
    IpeCheckBox endOfAmortizationCheckBox;

    @ElementName("'Start of Amortization' datePicker")
    @FindBy(xpath = "//*[@role='dialog' and @aria-hidden='false']//*[@role='tabpanel' and @aria-hidden='false']//*[(contains(text(),'start') or contains(text(),'Start')) and @data-ref='labelTextEl']/ancestor::div[contains(@class,'iBEDateTimeCls')]//input")
    IpeField startOfAmortizationDatePicker;

    @ElementName("'Start of Depreciation' datePicker")
    @FindBy(xpath = "//*[@role='dialog' and @aria-hidden='false']//*[@role='tabpanel' and @aria-hidden='false']//*[(contains(text(),'start') or contains(text(),'Start')) and @data-ref='labelTextEl']/ancestor::div[contains(@class,'iBEDateTimeCls')]//input")
    IpeField startOfDepreciationDatePicker;

    @ElementName("'End of Amortization' datePicker")
    @FindBy(xpath = "//*[@role='dialog' and @aria-hidden='false']//*[@role='tabpanel' and @aria-hidden='false']//*[contains(text(),'End') and @data-ref='labelTextEl']/ancestor::div[contains(@class,'iBEDateTimeCls')]//input")
    IpeField endOfAmortizationDatePicker;

    @ElementName("'End of Depreciation' datePicker")
    @FindBy(xpath = "//*[@role='dialog' and @aria-hidden='false']//*[@role='tabpanel' and @aria-hidden='false']//*[contains(text(),'End') and @data-ref='labelTextEl']/ancestor::div[contains(@class,'iBEDateTimeCls')]//input")
    IpeField endOfDepreciationDatePicker;

    @ElementName("'Start of Depreciation' checkBox")
    @FindBy(xpath = "//label[@data-ref='boxLabelEl' and contains(text(), 'start')]/preceding-sibling::span")
    IpeCheckBox startOfDepreciationCheckBox;

    @ElementName("'End of Depreciation' checkBox")
    @FindBy(xpath = "//*[@role='dialog' and @aria-hidden='false']//*[@role='tabpanel' and @aria-hidden='false']//*[contains(text(),'end') and @data-ref='boxLabelEl']/ancestor::div[@class='x-form-cb-wrap-inner']//span[contains(@class, 'x-form-checkbox')]")
    IpeCheckBox endOfDepreciationCheckBox;

    @ElementName("'Amortization Method' field")
    @FindBy(xpath = "//*[@role='dialog' and @aria-hidden='false']//*[@role='tabpanel' and @aria-hidden='false']//input[starts-with(@data-componentid, 'iBEComboBox')]")
    IpeDropdownField amortizationMethodField;

    @ElementName("'Total Value' dropdown")
    @FindBy(xpath = "//*[@role='dialog' and @aria-hidden='false']//*[@role='tabpanel' and @aria-hidden='false']//input[contains(@id, 'iBEAmount') and contains(@class, 'x-form-field') and @aria-invalid='false']")
    IpeDropdownField totalValueDropdown;

    @ElementName("'Estimate for Labor' popup")
    @FindBy(xpath = "//*[contains(text(),'Labor') and contains(text(),'Estimate')]/ancestor::*[@role='dialog' and @aria-hidden='false']//*[@role='tab' and @aria-selected='true']")
    BaseWebElement estimateForLaborPopup;

    @ElementName("'Labor Revenue' widget")
    @FindBy(xpath = "//*[@role='tabpanel' and @aria-hidden='false']//*[@data-qtip='Labor revenue']")
    TextElement laborRevenueWidget;

    @ElementName("'Labor Cost' widget")
    @FindBy(xpath = "//*[@role='tabpanel' and @aria-hidden='false']//*[@data-qtip='Labor cost']")
    TextElement laborCostWidget;

    @ElementName("'Labor Amortization' tab")
    @FindBy(xpath = "//*[@role='dialog' and @aria-hidden='false']//*[@role='tab' and @aria-hidden='false']//span[text()='Labor Amortization']")
    LongWaitElement laborAmortizationTab;

    @ElementName("'Distribute Total Effort' field")
    @FindBy(xpath = "//*[@role='dialog' and @aria-hidden='false']//label//*[contains(text(),'effort')]/ancestor::td//input[@aria-hidden='false']")
    IpeDropdownField distributeTotalEffortField;

    @ElementName("'Total Effort' checkBox")
    @FindBy(xpath = "//*[@role='dialog' and @aria-hidden='false']//label[contains(text(),'Total effort')]/ancestor::td[@class='x-form-radio-group']//input")
    BaseWebElement totalEffortCheckBox;

    @ElementName("'Download' button")
    @FindBy(xpath = "//*[@role='tabpanel' and @aria-hidden='false']//*[@role='grid' and @aria-hidden='false']//*[@data-qtip='Download' and @aria-hidden='false']")
    BaseWebElement downloadButton;

    @ElementName("'Upload' button")
    @FindBy(xpath = "//*[@role='tabpanel' and @aria-hidden='false']//*[@role='grid' and @aria-hidden='false']//*[@data-qtip='Upload' and @aria-hidden='false']")
    BaseWebElement uploadButton;

    @ElementName("'Labor Hours' widget")
    @FindBy(xpath = "//*[@role='tabpanel' and @aria-hidden='false']//*[@data-qtip='Planned total effort']")
    TextElement laborHoursWidget;

    @ElementName("'Labor Days' widget")
    @FindBy(xpath = "//*[@role='tabpanel' and @aria-hidden='false']//*[@data-qtip='Planned total effort in days']")
    TextElement laborDaysWidget;

    @ElementName("'Escalation and Inflammation Rates' table")
    @FindBy(xpath = "(//div[not(contains(@class,'x-hidden-offsets')) and (contains (@class, 'x-window-item x-panel-default'))]//div[@class='x-grid-item-container'])[2]")
    EscalationInflationRatesTable escalationAndInflammationRatesTable;

    @ElementName("'No records found, click here to add' button")
    @FindBy(xpath = "//*[@role='tabpanel' and @aria-hidden='false']//*[@aria-hidden='false' and (@role='grid' or @role='treegrid')]//*[@data-grigaddlink='true']//div[text()]")
    LongWaitElement noRecordFind;

    @ElementName("'Labor Dual' table")
    @FindBy(xpath = "(//div[not(contains(@class,'x-hidden-offsets')) and (contains (@class, 'x-panel x-tabpanel-child'))]//div[@class='x-grid-item-container'])[2]")
    CommonDualTable laborDualTable;

    @ElementName("'Opened' menu")
    @FindBy(xpath = "//*[@data-ref='listEl' and @role='listbox' and @aria-hidden='false']")
    MenuItemField openedMenu;

    @ElementName("'Test WBS 1' menuItem")
    @FindBy(xpath = "//*[text()='Test WBS 1']/ancestor::li[@role='option']")
    MenuItemField testWBS1Menu;

    @ElementName("'Test WBS 2' menuItem")
    @FindBy(xpath = "//*[text()='Test WBS 2']/ancestor::li[@role='option']")
    MenuItemField testWBS2Menu;

    @ElementName("'COPY LABOR' button")
    @FindBy(xpath = "//*[text()='COPY LABOR']/ancestor::*[@role='button' and @aria-hidden='false']")
    BaseWebElement copyLaborButton;

    @ElementName("'Learn more' link")
    @FindBy(xpath = "//a[@class='x-ibeLinksCls ' and text()='Learn more']")
    BaseWebElement learnMoreLink;

    @ElementName("'View' dropdown")
    @FindBy(xpath = "//div[not(contains(@class,'x-hidden-offsets')) and (contains (@class, 'x-tabpanel-child'))]//a[contains(@class, 'x-split-button')][@aria-hidden='false']//span[contains(text(), 'View')]")
    IpeViewDropdownField viewDropdown;

    @ElementName("'Click here to add' link")
    @FindBy(xpath = "//*[@class='x-grid-scrollbar-clipper ']//a/div")
    BaseWebElement addLink;

    @ElementName("'Labor' widget")
    @FindBy(xpath = "//*[@role='tabpanel' and @aria-hidden='false']//*[contains(@data-qtip,'Labor')]")
    TextElement laborWidget;

    @ElementName("'Total Effort' widget")
    @FindBy(xpath = "//*[@role='tabpanel' and @aria-hidden='false']//*[@data-qtip='Planned total effort']")
    TextElement totalEffortWidget;

    @ElementName("'Travel' widget")
    @FindBy(xpath = "//*[@role='tabpanel' and @aria-hidden='false']//*[contains(@data-qtip,'Travel')]")
    TextElement travelWidget;

    @ElementName("'Update Estimate totals' link")
    @FindBy(xpath = "//*[@role='tabpanel' and @aria-hidden='false']//*[@titlelink='updateBoeCostsWithFormula']")
    TextElement UpdateEstimateTotalsLink;

    @ElementName("'Update Estimate Totals' link")
    @FindBy(xpath = "//*[@role='tabpanel' and @aria-hidden='false']//*[@titlelink='updateBoeCostsWithFormula']")
    TextElement UpdateEstimatetotalsLink;

    @ElementName("'Other' widget")
    @FindBy(xpath = "//*[@role='tabpanel' and @aria-hidden='false']//*[not(contains(@data-qtip,'Travel')) and contains(@data-qtip,'Other')]")
    TextElement otherWidget;

    @ElementName("'Other' tab")
    @FindBy(xpath = "//a[@role='tab' and @aria-hidden='false']//span[text()='Other']/ancestor::*[@role='tab' and @aria-hidden='false']")
    BaseWebElement otherTab;

    @ElementName("'Labor' table")
    @FindBy(xpath = "(//div[not(contains(@class,'x-hidden-offsets')) and (contains (@class, 'x-panel x-tabpanel-child'))]//div[@class='x-grid-item-container'])[2]")
    LaborTable laborTable;

    @ElementName("'Update Cost' button")
    @FindBy(xpath = "(//div[not(contains(@class,'x-hidden-offsets'))]//div[@data-qtip and @titlelink='updateBoeCostsWithFormula'])[last()]")
    BaseWebElement updCostButton;

    @ElementName("'Refresh' button")
    @FindBy(xpath = "//*[@data-ref='btnIconEl' and contains(@class,'fa-sync-alt ')]")
    BaseWebElement refreshButton;

    @ElementName("'Cog Settings' menu")
    @FindBy(xpath = "//div[(contains (@class, 'x-fit-item x-panel-default x-grid')) and contains(@aria-hidden,'false')]//span[contains(@class, 'x-fa fa-cog')]")
    IpeCogTree cogMenu;

    @ElementName("'Change Planning Mode' window")
    @FindBy(xpath = "//*[@role='alertdialog' and @aria-hidden='false']//div[contains(@class,'x-window-header')]//div[text()='Change Planning Mode in Other Estimates']")
    BaseWebElement changeWindow;

    @ElementName("'Yes' button")
    @FindBy(xpath = "//a//span[text()='Yes']")
    BaseWebElement yesButton;

    @Override
    public String getLoadTriggerName() {
        return RIGHT_TABLE_XPATH;
    }

    public void waitForProgressbarLifecycle() {
        String xpath = "//*[@role='alertdialog' and @aria-hidden='false']//*[@role='progressbar' and @aria-hidden='false']";
        long timeoutMs = 45000;
        long pollingIntervalMs = 700;
        long start = System.currentTimeMillis();
        boolean appeared = false;
        boolean disappeared = false;

        while (System.currentTimeMillis() - start < timeoutMs) {
            boolean visible = !$$x(xpath).filter(Condition.visible).isEmpty();

            if (visible) {
                appeared = true; // It showed up
            } else if (appeared) {
                AllureUtils.logActionF("✅ Progress bar appeared and disappeared in " +
                        (System.currentTimeMillis() - start) + " ms.");
                disappeared = true; // It was visible before and now gone
                break;
            }
            sleep(pollingIntervalMs);
        }

        if (!appeared) {
            AllureUtils.logActionF("✅ Progress bar never appeared within 45 seconds.");
        } else if (disappeared) {
            AllureUtils.logActionF("✅ Progress bar appeared and disappeared within 45 seconds.");
        } else {
            AllureUtils.logActionF("Progress bar appeared but did NOT disappear within 45 seconds.");
        }
    }


    @Override
    public void checkIfPageLoaded() {
        setCustomTimeoutSec(15);
        waitForProgressbarLifecycle();
        setDefaultTimeout();
        super.checkIfPageLoaded();
    }

}
