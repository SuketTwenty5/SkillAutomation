package t5.ipe.cucumber.objects.pages.proposals;


import org.openqa.selenium.support.FindBy;
import t5.ipe.cucumber.core.web.elements.BaseWebElement;
import t5.ipe.cucumber.core.web.elements.IpeCustomDropdownField;
import t5.ipe.cucumber.core.web.elements.IpeCustomInputField;
import t5.ipe.cucumber.core.web.elements.LongWaitElement;
import t5.ipe.cucumber.core.web.interfaces.annotations.ElementName;
import t5.ipe.cucumber.core.web.interfaces.annotations.WebPage;
import t5.ipe.cucumber.objects.elements.IpeCheckBox;
import t5.ipe.cucumber.objects.elements.IpeDropdownField;
import t5.ipe.cucumber.objects.elements.IpeField;
import t5.ipe.cucumber.objects.elements.MenuItemField;
import t5.ipe.cucumber.objects.elements.tables.*;
import t5.ipe.cucumber.objects.pages.MainPage;

/**
 * Procurement And Production page.
 */
@WebPage(value = "Procurement & Production page", urlTemplate = ".*quote:[0-9A-Fa-f]{8}(?:-[0-9A-Fa-f]{4}){3}-[0-9A-Fa-f]{12}$", tabName = "Procurement & Production")
public class ProcurementAndProductionPage extends MainPage {
    public static final String PROCUREMENTANDPRODUCTION_TAB = "'Procurement & Production' tab";

    @ElementName(PROCUREMENTANDPRODUCTION_TAB)
    @FindBy(xpath = "//a[@aria-selected='true']//span[text()='Procurement & Production']")
    BaseWebElement procurementProductionTab;

    @ElementName("'Cost Estimate' table")
    @FindBy(xpath = "(//div[not(contains(@class,'x-hidden-offsets')) and (contains (@class, 'x-panel x-tabpanel-child'))]//div[@class='x-grid-item-container'])")
    CostEstimateTable costEstimateTable;

    //*[@role='dialog' and @aria-hidden='false']//*[contains(text(),'Source Unit Cost')]/ancestor::*[contains(@class,'x-iBEAmountCls')]//*[contains(@data-componentid,'iBEAmount') and @role='textbox' and @aria-hidden='false']

    @ElementName("'Source Unit Cost' field")
    @FindBy(xpath = "//*[@role='dialog' and @aria-hidden='false']//*[contains(text(),'Source Unit Cost')]/ancestor::*[contains(@class,'x-iBEAmountCls')]//*[contains(@data-componentid,'iBEAmount') and @role='textbox' and @aria-hidden='false']")
    IpeField sourceUnitCostField;

    @ElementName("'Changed Adj Unit Cost' field")
    @FindBy(xpath = "//*[@role='dialog' and @aria-hidden='false']//*[contains(text(),'Changed Adj Unit Cost')]/ancestor::*[contains(@class,'x-iBEAmountCls')]//*[contains(@data-componentid,'iBEAmount') and @role='textbox' and @aria-hidden='false']")
    IpeField changedAdjUnitCostField;

    @ElementName("'Complexity Factor' field")
    @FindBy(xpath = "//*[@role='dialog' and @aria-hidden='false']//*[contains(text(),'Complexity Factor')]/ancestor::*[contains(@class,'x-iBENumberCls')]//*[contains(@data-componentid,'iBENumber') and @role='spinbutton' and @aria-hidden='false']")
    IpeCustomInputField complexityFactorField;

    @ElementName("'Decrement Factor' field")
    @FindBy(xpath = "//*[@role='dialog' and @aria-hidden='false']//*[contains(text(),'Decrement Factor')]/ancestor::*[contains(@class,'x-iBENumberCls')]//*[contains(@data-componentid,'iBENumber') and @role='spinbutton' and @aria-hidden='false']")
    IpeCustomInputField decrementFactorField;

    @ElementName("'Total Cost' field")
    @FindBy(xpath = "//*[@role='dialog' and @aria-hidden='false']//*[contains(text(),'Total Cost')]/ancestor::*[contains(@class,'x-iBEAmountCls')]//*[contains(@data-componentid,'iBEAmount') and @role='textbox' and @aria-hidden='false']")
    IpeField totalCostField;

    @ElementName("'Retain Changes' popup")
    @FindBy(xpath = "//*[text()='Retain Changes']/ancestor::*[@role='alertdialog' and @aria-hidden='false']")
    LongWaitElement retainChangesPopUp;

    @ElementName("'No Retain for Costing' button")
    @FindBy(xpath = "//*[contains(text(),'No - Retain for Costing')]/ancestor::*[@role='button' and @aria-hidden='false']")
    BaseWebElement noRetainForCostingButton;

    @ElementName("'Escalation Index' dropdown")
    @FindBy(xpath = "//*[@role='dialog' and @aria-hidden='false']//*[contains(text(),'Escalation Index')]/ancestor::*[contains(@class,'x-iBECombo')]//input[contains(@data-componentid,'iBEComboBox') and @role='combobox' and @aria-hidden='false']")
    IpeCustomDropdownField escalationIndexDropdown;

    @ElementName("'Cost Breakdown' table")
    @FindBy(xpath = "(//div[not(contains(@class,'x-hidden-offsets')) and (contains (@class, 'x-panel x-tabpanel-child'))]//div[@class='x-grid-item-container'])")
    CostBreakDownTable costBreakdownTable12;

    @ElementName("'Purchased Part' popup")
    @FindBy(xpath = "//*[contains(text(),'Purchased Part')]/ancestor::*[@role='dialog' and @aria-hidden='false']")
    BaseWebElement purchasedPartPopup;

    @ElementName("'Estimating Source' dropdown")
    @FindBy(xpath = "//*[@role='dialog' and @aria-hidden='false']//*[contains(text(),'Estimating Source')]/ancestor::*[contains(@class,'x-iBECombo')]//input[contains(@data-componentid,'iBEComboBox') and @role='combobox' and @aria-hidden='false']")
    IpeCustomDropdownField estimatingSourceDropdown;

    @ElementName("'Escalation Factor' field")
    @FindBy(xpath = "//*[@role='dialog' and @aria-hidden='false']//*[contains(text(),'Escalation Factor')]/ancestor::*[contains(@class,'x-iBENumberCls')]//*[contains(@data-componentid,'iBENumber') and @role='spinbutton' and @aria-hidden='false']")
    IpeCustomInputField escalationFactorField;

    @ElementName("'QAF Qty Adj Factor' field")
    @FindBy(xpath = "//*[@role='dialog' and @aria-hidden='false']//*[contains(text(),'QAF – Qty Adj Factor')]/ancestor::*[contains(@class,'x-iBENumberCls')]//*[contains(@data-componentid,'iBENumber') and @role='spinbutton' and @aria-hidden='false']")
    IpeCustomInputField qafQtyAdjFactorField;

    @ElementName("'Confirm Formula, Define Local Parameters' popUp")
    @FindBy(xpath = "//*[@role='dialog' and @aria-hidden='false']//*[text()='Confirm Formula, Define Local Parameters']")
    BaseWebElement confirmFormulaDefineLocalPopUp;

    @ElementName("'Regression - General & Admin Rate' parameter")
    @FindBy(xpath = "//*[@role='dialog' and @aria-hidden='false']//*[text()='Regression - General & Admin Rate']")
    BaseWebElement regressionGeneralAdminRateParameter;

    @ElementName("'Formula' table")
    @FindBy(xpath = "//*[@role='dialog' and @aria-hidden='false']//*[@role='tabpanel'] and @aria-hidden='false'")
    FormulaTable formulaTable;

    @ElementName("'Dialog Confirm' button")
    @FindBy(xpath = "//*[@role='dialog' and @aria-hidden='false']//*[text()='Confirm']")
    BaseWebElement dialogConfirm;

    @ElementName("'Dialog Result' input")
    @FindBy(xpath = "//*[@role='dialog' and @aria-hidden='false']//*[contains(text(),'Result')]/ancestor::label/following-sibling::*//input[@aria-hidden='false']")
    IpeField dialogResultInput;

    @ElementName("'Asset Depreciation' tab")
    @FindBy(xpath = "//*[@role='dialog' and @aria-hidden='false']//*[@role='tablist' and @aria-hidden='false']//*[@role='tab' and @aria-hidden='false']//*[text()='Asset Depreciation']/ancestor::*[@role='tab' and @aria-hidden='false']")
    BaseWebElement assetDepreciationTab;

    @ElementName("'Total Value' dropdown")
    @FindBy(xpath = "//*[@role='dialog' and @aria-hidden='false']//*[@role='tabpanel' and @aria-hidden='false']//input[contains(@id, 'iBEAmount') and contains(@class, 'x-form-field') and @aria-invalid='false']")
    IpeDropdownField totalValueDropdown;

    @ElementName("'Start of Depreciation' checkBox")
    @FindBy(xpath = "//label[@data-ref='boxLabelEl' and contains(text(), 'start')]/preceding-sibling::span")
    IpeCheckBox startOfDepreciationCheckBox;

    @ElementName("'Amortization Schedule' table")
    @FindBy(xpath = "(//div[not(contains(@class,'x-hidden-offsets')) and (contains (@class, 'x-panel x-tabpanel-child'))]//div[@class='x-grid-item-container'])[2]")
    LaborAmortizationTable laborAmortizationTable;

    @ElementName("'Start of Depreciation' datePicker")
    @FindBy(xpath = "//*[@role='dialog' and @aria-hidden='false']//*[@role='tabpanel' and @aria-hidden='false']//*[(contains(text(),'start') or contains(text(),'Start')) and @data-ref='labelTextEl']/ancestor::div[contains(@class,'iBEDateTimeCls')]//input")
    IpeField startOfDepreciationDatePicker;

    @ElementName("'Depreciation Method' field")
    @FindBy(xpath = "//*[@role='dialog' and @aria-hidden='false']//*[@role='tabpanel' and @aria-hidden='false']//input[starts-with(@data-componentid, 'iBEComboBox')]")
    IpeDropdownField amortizationMethodField;

    @ElementName("'Estimate for Labor' popup")
    @FindBy(xpath = "//*[contains(text(),'Estimate for')]/ancestor::*[@role='dialog' and @aria-hidden='false']//*[@role='tab' and @aria-selected='true']")
    BaseWebElement estimateForLaborPopup;

    @ElementName("'End of Depreciation' datePicker")
    @FindBy(xpath = "//*[@role='dialog' and @aria-hidden='false']//*[@role='tabpanel' and @aria-hidden='false']//*[contains(text(),'End') and @data-ref='labelTextEl']/ancestor::div[contains(@class,'iBEDateTimeCls')]//input")
    IpeField endOfDepreciationDatePicker;

    @ElementName("'End of Depreciation' checkBox")
    @FindBy(xpath = "//*[@role='dialog' and @aria-hidden='false']//*[@role='tabpanel' and @aria-hidden='false']//*[contains(text(),'end') and @data-ref='boxLabelEl']/ancestor::div[@class='x-form-cb-wrap-inner']//span[contains(@class, 'x-form-checkbox')]")
    IpeCheckBox endOfDepreciationCheckBox;

    @ElementName("'Purchased Parts Base' parameter")
    @FindBy(xpath = "//*[@role='dialog' and @aria-hidden='false']//*[text()='Purchased Parts Base (USD)']")
    BaseWebElement purchasedPartsBaseParameter;

    @ElementName("'Material & Equipment Burden Rate' parameter")
    @FindBy(xpath = "//*[@role='dialog' and @aria-hidden='false']//*[text()='Material & Equipment Burden Rate']")
    BaseWebElement materialEquipmentBurdenRateParameter;

    @ElementName("'Mfg' table")
    @FindBy(xpath = "(//div[not(contains(@class,'x-hidden-offsets')) and (contains (@class, 'x-panel x-tabpanel-child'))]//div[@class='x-grid-item-container'])[2]")
    CostBreakDownTable mfgTable;

    @ElementName("'Edit Details' menuItem")
    @FindBy(xpath = "//div[contains(@class, 'x-menu') and @aria-hidden='false']//a[span[text()='Edit Details']]")
    BaseWebElement editDetailsMenuItem;

    @ElementName("'Opened' menu")
    @FindBy(xpath = "//*[@data-ref='listEl' and @role='listbox' and @aria-hidden='false']")
    MenuItemField openedMenu;

    @ElementName("'Test WBS 1' menuItem")
    @FindBy(xpath = "//*[text()='Test WBS 1']/ancestor::li[@role='option']")
    MenuItemField testWBS1Menu;

    @ElementName("'Test WBS 2' menuItem")
    @FindBy(xpath = "//*[text()='Test WBS 2']/ancestor::li[@role='option']")
    MenuItemField testWBS2Menu;

    @ElementName("'Procurement & Production' table")
    @FindBy(xpath = "//div[not(contains(@class,'x-hidden-offsets')) and (contains (@class, 'x-container x-tabpanel-child'))]//div[@class='x-grid-item-container']")
    CommonDualTable procurementProductionTable;

    @ElementName("'Click here to add' link")
    @FindBy(xpath = "//a[text()='click here to add']")
    BaseWebElement addLink;

    @Override
    public String getLoadTriggerName() {
        return PROCUREMENTANDPRODUCTION_TAB;
    }


}


