package t5.ipe.cucumber.objects.pages.materialCostEstimates;

import org.openqa.selenium.support.FindBy;
import t5.ipe.cucumber.core.web.elements.BaseWebElement;
import t5.ipe.cucumber.core.web.interfaces.annotations.ElementName;
import t5.ipe.cucumber.core.web.interfaces.annotations.WebPage;
import t5.ipe.cucumber.objects.elements.tables.BillingItemsTable;
import t5.ipe.cucumber.objects.elements.tables.WBSTable;
import t5.ipe.cucumber.objects.pages.MainPage;

@WebPage(value = "Items page", urlTemplate = ".*#purchaseHistory:[0-9A-Fa-f]{8}(?:-[0-9A-Fa-f]{4}){3}-[0-9A-Fa-f]{12}$|", tabName = "Items")


public class itemsPage extends MainPage {

    @ElementName("'Items' tab")
    @FindBy(xpath = "//a[@aria-selected='true']//span[contains(text(),'Items')]")
    BaseWebElement itemsTab;

    @ElementName("'Items' table")
    @FindBy(xpath = "//div[@data-testid='items-table']")
    BillingItemsTable itemsTable;

    @ElementName("'Part Number' dropDown")
    @FindBy(xpath = "//*[@data-columnid='productColumn']//*[contains(@class,'x-form-trigger-focus')]")
    BaseWebElement partNumberDropDown;

    @ElementName("'Escalate to Commitment' chevron")
    @FindBy(xpath = "//*[text()='Escalate to Commitment' and @data-ref='textInnerEl']/ancestor::*[@role='columnheader' and @aria-hidden='false']//*[@data-ref='triggerEl']")
    BaseWebElement escalateToCommitmentChevron;

    @ElementName("'Escalate to Commitment' header")
    @FindBy(xpath = "//*[text()='Escalate to Commitment' and @data-ref='textInnerEl']/ancestor::*[@role='columnheader' and @aria-hidden='false']")
    BaseWebElement escalateToCommitmentHeader;

    @ElementName("'Columns' dropDown")
    @FindBy(xpath = "//*[@role='menu' and @aria-hidden='false'][1]")
    BaseWebElement columnsDropDown;

    @ElementName("'Columns' option")
    @FindBy(xpath = "//*[@role='menuitem' and @aria-hidden='false']//*[text()='Columns']")
    BaseWebElement partNumberColumnOption;

    @ElementName("'Columns' popUp")
    @FindBy(xpath = "//*[@role='menu' and @aria-hidden='false'][2]")
    BaseWebElement columnsPopUp;

    @ElementName("'Maximum Quantity' option")
    @FindBy(xpath = "//*[@role='menuitemcheckbox' and @aria-hidden='false']//*[text()='Maximum Quantity']/following-sibling::*[@data-ref='checkEl']")
    BaseWebElement maximumQuantityColumnOption;

    @ElementName("'Maximum Quantity' column")
    @FindBy(xpath = "//*[@data-qtip='Maximum quantity the pricing is up to']")
    BaseWebElement maximumQuantityColumn;

    @ElementName("'Costing' table")
    @FindBy(xpath = "//div[@data-testid='costing-table']")
    BillingItemsTable costingTable;

    @ElementName("'Edit Price Validity' popUp")
    @FindBy(xpath = "//*[@role='dialog' and contains(@class,'x-window-default-resizable')]")
    BaseWebElement editPriceValidityPopUp;

    @Override
    public String getLoadTriggerName() {
        return "'Items' tab";
    }
}
