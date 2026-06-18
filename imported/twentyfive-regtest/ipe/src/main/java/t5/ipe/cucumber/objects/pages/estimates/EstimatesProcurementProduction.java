package t5.ipe.cucumber.objects.pages.estimates;

import org.openqa.selenium.support.FindBy;
import t5.ipe.cucumber.core.web.elements.BaseWebElement;
import t5.ipe.cucumber.core.web.interfaces.annotations.ElementName;
import t5.ipe.cucumber.core.web.interfaces.annotations.WebPage;
import t5.ipe.cucumber.objects.elements.IpeCheckBox;
import t5.ipe.cucumber.objects.elements.IpeDropdownField;
import t5.ipe.cucumber.objects.elements.IpeField;
import t5.ipe.cucumber.objects.elements.MenuItemField;
import t5.ipe.cucumber.objects.elements.tables.*;
import t5.ipe.cucumber.objects.pages.MainPage;

@WebPage(value = "Estimate Procurement & Production", urlTemplate = ".*#boe:[0-9A-Fa-f]{32}$|.*boe:[0-9A-Fa-f]{8}(?:-[0-9A-Fa-f]{4}){3}-[0-9A-Fa-f]{12}$", tabName = "Procurement & Production")
public class EstimatesProcurementProduction extends MainPage {
    public static final String PROCUREMENT_PRODUCTION_TAB = "'Procurement & Production' tab";

    @ElementName(PROCUREMENT_PRODUCTION_TAB)
    @FindBy(xpath = "//a[@aria-selected='true']//span[text()='Procurement & Production' or text()='Material']")
    BaseWebElement costStructureTab;

    @ElementName("'Amortization Schedule' table")
    @FindBy(xpath = "(//div[not(contains(@class,'x-hidden-offsets')) and (contains (@class, 'x-panel x-tabpanel-child'))]//div[@class='x-grid-item-container'])[2]")
    LaborAmortizationTable laborAmortizationTable;

    @ElementName("'Cost Estimate' table")
    @FindBy(xpath = "(//div[not(contains(@class,'x-hidden-offsets')) and (contains (@class, 'x-panel x-tabpanel-child'))]//div[@class='x-grid-item-container'])")
    CostEstimateTable costEstimateTable;

    @ElementName("'Confirm Formula, Define Local Parameters' popUp")
    @FindBy(xpath = "//*[@role='dialog' and @aria-hidden='false']//*[text()='Confirm Formula, Define Local Parameters']")
    BaseWebElement confirmFormulaDefineLocalPopUp;

    @ElementName("'End of Depreciation' checkBox")
    @FindBy(xpath = "//*[@role='dialog' and @aria-hidden='false']//*[@role='tabpanel' and @aria-hidden='false']//*[contains(text(),'end') and @data-ref='boxLabelEl']/ancestor::div[@class='x-form-cb-wrap-inner']//span[contains(@class, 'x-form-checkbox')]")
    IpeCheckBox endOfDepreciationCheckBox;

    @ElementName("'End of Depreciation' datePicker")
    @FindBy(xpath = "//*[@role='dialog' and @aria-hidden='false']//*[@role='tabpanel' and @aria-hidden='false']//*[contains(text(),'End') and @data-ref='labelTextEl']/ancestor::div[contains(@class,'iBEDateTimeCls')]//input")
    IpeField endOfDepreciationDatePicker;

    @ElementName("'Total Value' dropdown")
    @FindBy(xpath = "//*[@role='dialog' and @aria-hidden='false']//*[@role='tabpanel' and @aria-hidden='false']//input[contains(@id, 'iBEAmount') and contains(@class, 'x-form-field') and @aria-invalid='false']")
    IpeDropdownField totalValueDropdown;

    @ElementName("'Start of Depreciation' datePicker")
    @FindBy(xpath = "//*[@role='dialog' and @aria-hidden='false']//*[@role='tabpanel' and @aria-hidden='false']//*[(contains(text(),'start') or contains(text(),'Start')) and @data-ref='labelTextEl']/ancestor::div[contains(@class,'iBEDateTimeCls')]//input")
    IpeField startOfDepreciationDatePicker;

    @ElementName("'Start of Depreciation' checkBox")
    @FindBy(xpath = "//label[@data-ref='boxLabelEl' and contains(text(), 'start')]/preceding-sibling::span")
    IpeCheckBox startOfDepreciationCheckBox;

    @ElementName("'Estimate for Labor' popup")
    @FindBy(xpath = "//*[contains(text(),'Estimate for')]/ancestor::*[@role='dialog' and @aria-hidden='false']//*[@role='tab' and @aria-selected='true']")
    BaseWebElement estimateForLaborPopup;

    @ElementName("'Asset Depreciation' tab")
    @FindBy(xpath = "//*[@role='dialog' and @aria-hidden='false']//*[@role='tablist' and @aria-hidden='false']//*[@role='tab' and @aria-hidden='false']//*[text()='Asset Depreciation']/ancestor::*[@role='tab' and @aria-hidden='false']")
    BaseWebElement assetDepreciationTab;

    @ElementName("'Depreciation Method' field")
    @FindBy(xpath = "//*[@role='dialog' and @aria-hidden='false']//*[@role='tabpanel' and @aria-hidden='false']//input[starts-with(@data-componentid, 'iBEComboBox')]")
    IpeDropdownField amortizationMethodField;

    @ElementName("'Dialog Result' input")
    @FindBy(xpath = "//*[@role='dialog' and @aria-hidden='false']//*[contains(text(),'Result')]/ancestor::label/following-sibling::*//input[@aria-hidden='false']")
    IpeField dialogResultInput;

    @ElementName("'Dialog Confirm' button")
    @FindBy(xpath = "//*[@role='dialog' and @aria-hidden='false']//*[text()='Confirm']")
    BaseWebElement dialogConfirm;

    @ElementName("'Purchased Parts Base' parameter")
    @FindBy(xpath = "//*[@role='dialog' and @aria-hidden='false']//*[text()='Purchased Parts Base (USD)']")
    BaseWebElement purchasedPartsBaseParameter;

    @ElementName("'Regression - General & Admin Rate' parameter")
    @FindBy(xpath = "//*[@role='dialog' and @aria-hidden='false']//*[text()='Regression - General & Admin Rate']")
    BaseWebElement regressionGeneralAdminRateParameter;

    @ElementName("'Material & Equipment Burden Rate' parameter")
    @FindBy(xpath = "//*[@role='dialog' and @aria-hidden='false']//*[text()='Material & Equipment Burden Rate']")
    BaseWebElement materialEquipmentBurdenRateParameter;

    @ElementName("'Procurement & Production' tab")
    @FindBy(xpath = "//a[@role='tab' and @aria-hidden='false']//span[text()='Procurement & Production' or text()='Material']")
    BaseWebElement proposalBOMTab;

    @ElementName("'Edit Details' menuItem")
    @FindBy(xpath = "//div[contains(@class, 'x-menu') and @aria-hidden='false']//a[span[text()='Edit Details']]")
    BaseWebElement editDetailsMenuItem;

    @ElementName("'Procurement & Production' table")
    @FindBy(xpath = "(//div[not(contains(@class,'x-hidden-offsets')) and (contains (@class, 'x-panel x-tabpanel-child'))]//div[@class='x-grid-item-container'])[2]")
    CommonDualTable procurementProductionTable;

    @ElementName("'Mfg' table")
    @FindBy(xpath = "(//div[not(contains(@class,'x-hidden-offsets')) and (contains (@class, 'x-panel x-tabpanel-child'))]//div[@class='x-grid-item-container'])[2]")
    CostBreakDownTable mfgTable;

    @ElementName("'Opened' menu")
    @FindBy(xpath = "//*[@data-ref='listEl' and @role='listbox' and @aria-hidden='false']")
    MenuItemField openedMenu;

    @Override
    public String getLoadTriggerName() {
        return PROCUREMENT_PRODUCTION_TAB;
    }
}
