package t5.ipe.cucumber.objects.pages.proposals;

import org.openqa.selenium.support.FindBy;
import t5.ipe.cucumber.core.web.elements.BaseWebElement;
import t5.ipe.cucumber.core.web.elements.LongWaitElement;
import t5.ipe.cucumber.core.web.interfaces.annotations.ElementName;
import t5.ipe.cucumber.core.web.interfaces.annotations.WebPage;
import t5.ipe.cucumber.objects.elements.IpeDropdownField;
import t5.ipe.cucumber.objects.elements.IpeField;
import t5.ipe.cucumber.objects.elements.IpeNotification;
import t5.ipe.cucumber.objects.elements.tables.BillingItemsTable;
import t5.ipe.cucumber.objects.pages.MainPage;

@WebPage(value = "Pricing page", urlTemplate = ".*quote:[0-9A-Fa-f]{8}(?:-[0-9A-Fa-f]{4}){3}-[0-9A-Fa-f]{12}|.*quote:[0-9A-Fa-f]{8}(?:-[0-9A-Fa-f]{4}){3}-[0-9A-Fa-f]{12}$", tabName = "Pricing")

public class PricingPage extends MainPage {

    public static final String PRICING_TAB = "'PHASES' tab";
    public static final String SETUP_TAB = "'Setup' tab";
    public static final String LINE_ITEMS_TAB = "'Line Items' tab";

    @ElementName(LINE_ITEMS_TAB)
    @FindBy(xpath = "//a//span[text()='Line Items' or text()='Deliverables']")
    BaseWebElement itemsTab;

    @ElementName("'Edit Price Validity' popUp")
    @FindBy(xpath = "//*[@role='dialog' and contains(@class,'x-window-default-resizable')]")
    BaseWebElement editPriceValidityPopUp;

    @ElementName("'Costing' table")
    @FindBy(xpath = "//div[@data-testid='costing-table']")
    BillingItemsTable costingTable;

    @ElementName(PRICING_TAB)
    @FindBy(xpath = "//a[@aria-selected='true']//span[contains(text(),'Pricing')]")
    BaseWebElement phasesTab;

    @ElementName("'Clear Dates' button")
    @FindBy(xpath = "//*[@role='dialog' and @aria-hidden='false']//*[text()='Clear Dates']/ancestor::a[@aria-hidden='false']")
    BaseWebElement clearDatesButton;

    @ElementName("'Cost Structure' tab")
    @FindBy(xpath = "//*[@role='tab' and @aria-hidden='false']//span[text()='Cost Structure']")
    BaseWebElement costStructureTab;

    @ElementName(SETUP_TAB)
    @FindBy(xpath = "//a[@aria-selected='false']//span[contains(text(),'Setup')]")
    BaseWebElement setupTab;

    @ElementName("'How are Labor/Item Rates Calculated' field")
    @FindBy(xpath = "//*[@placeholder='T&M Billing PriceBook']")
    IpeDropdownField howAreLaborItemRatesCalculatedField;

    @ElementName("'Data saved successfully' popUp")
    @FindBy(xpath = "//*[text()='Data saved successfully']/ancestor::*[@role='dialog' and @aria-hidden='false']")
    IpeNotification dataSavedSuccessfullyPopUp;

    @ElementName("'Costing' tab")
    @FindBy(xpath = "//a[@aria-selected='true']//span[contains(text(),'Costing') or contains(text(),'Pricing')]")
    BaseWebElement costingTab;

    @ElementName("'Confirm' button")
    @FindBy(xpath = "//*[@role='dialog' and @aria-hidden='false']//*[text()='Confirm']/ancestor::a[@aria-hidden='false']")
    BaseWebElement confirmButton;

    @ElementName("'Cancel' button")
    @FindBy(xpath = "//*[@role='dialog' and @aria-hidden='false']//*[text()='Cancel']/ancestor::a[@aria-hidden='false']")
    BaseWebElement cancelButton;

    @ElementName("'Valid From' field")
    @FindBy(xpath = "//*[@role='dialog' and @aria-hidden='false']//tr//td[1]//*[contains(@name,'iBEDateTime')]")
    IpeField validFromField;

    @ElementName("'Valid To' field")
    @FindBy(xpath = "//*[@role='dialog' and @aria-hidden='false']//tr//td[2]//*[contains(@name,'iBEDateTime')]")
    IpeField validToField;

    @Override
    public String getLoadTriggerName() {
        return PRICING_TAB;
    }
}
