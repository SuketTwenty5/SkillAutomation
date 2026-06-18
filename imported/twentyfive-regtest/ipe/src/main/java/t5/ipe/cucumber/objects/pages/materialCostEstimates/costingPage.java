package t5.ipe.cucumber.objects.pages.materialCostEstimates;

import org.openqa.selenium.support.FindBy;
import t5.ipe.cucumber.core.web.elements.BaseWebElement;
import t5.ipe.cucumber.core.web.interfaces.annotations.ElementName;
import t5.ipe.cucumber.core.web.interfaces.annotations.WebPage;
import t5.ipe.cucumber.objects.elements.IpeField;
import t5.ipe.cucumber.objects.elements.tables.BillingItemsTable;
import t5.ipe.cucumber.objects.pages.MainPage;

@WebPage(value = "Costing page", urlTemplate = ".*#purchaseHistory:[0-9A-Fa-f]{8}(?:-[0-9A-Fa-f]{4}){3}-[0-9A-Fa-f]{12}$|", tabName = "Costing")

public class costingPage extends MainPage {

    @ElementName("'Costing' tab")
    @FindBy(xpath = "//a[@aria-selected='true']//span[contains(text(),'Costing') or contains(text(),'Pricing')]")
    BaseWebElement costingTab;

    @ElementName("'Costing' table")
    @FindBy(xpath = "//div[@data-testid='costing-table']")
    BillingItemsTable costingTable;

    @ElementName("'Edit Price Validity' popUp")
    @FindBy(xpath = "//*[@role='dialog' and contains(@class,'x-window-default-resizable')]")
    BaseWebElement editPriceValidityPopUp;

    @ElementName("'Clear Dates' button")
    @FindBy(xpath = "//*[@role='dialog' and @aria-hidden='false']//*[text()='Clear Dates']/ancestor::a[@aria-hidden='false']")
    BaseWebElement clearDatesButton;

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
        return "'Costing' tab";
    }
}
