package t5.ipe.cucumber.objects.pages.proposals;


import org.openqa.selenium.support.FindBy;
import t5.ipe.cucumber.core.web.elements.*;
import t5.ipe.cucumber.core.web.interfaces.annotations.ElementName;
import t5.ipe.cucumber.core.web.interfaces.annotations.WebPage;
import t5.ipe.cucumber.objects.elements.*;
import t5.ipe.cucumber.objects.elements.tables.*;
import t5.ipe.cucumber.objects.pages.MainPage;

/**
 * CLINs page.
 */
@WebPage(value = "Billing Items page", urlTemplate = ".*quote:[0-9A-Fa-f]{8}(?:-[0-9A-Fa-f]{4}){3}-[0-9A-Fa-f]{12}$", tabName = "Billing Items")
public class BillingItemsPage extends MainPage {
    public static final String BILLING_ITEMS_TAB = "'Billing Items' tab";

    @ElementName(BILLING_ITEMS_TAB)
    @FindBy(xpath = "//a[@aria-selected='true']//span[text()='Billing Items']")
    BaseWebElement billingItemsTab;

    @ElementName("'Billing Items Pricing' tab")
    @FindBy(xpath = "//*[@role='dialog' and @aria-hidden='false']//a[@aria-hidden='false' and @role='tab']//span[text()='Pricing']")
    BaseWebElement popUpPricingTab;

    @ElementName("'PTW 0' hyperlink")
    @FindBy(xpath = "//a[text()='PTW $ 0.00']")
    BaseWebElement ptw$0hyperlink;

    @ElementName("'Billing Items' table")
    @FindBy(xpath = "//div[@class='x-grid-item-container' and not(contains(@style, 'transform'))]")
    BillingItemsTable billingItemsTable;

    @ElementName("'0 Allocated' hyperlink")
    @FindBy(xpath = "//a[text()='0% Allocated']")
    BaseWebElement allocated0hyperlink;

    @ElementName("'Re-price' button")
    @FindBy(xpath = "//span[normalize-space()='Re-price' and @data-ref='btnInnerEl']")
    BaseWebElement rePriceButton;

    @ElementName("'Billing Items' popUp")
    @FindBy(xpath = "//*[@role='dialog' and @aria-hidden='false']")
    BaseWebElement billingItemPopUp;

    @ElementName("'Billing Items Cost Allocation' table")
    @FindBy(xpath = "//*[@role='dialog' and @aria-hidden='false']")
    LaborAmortizationTable billingItemCostAllocationTable;

    @ElementName("'Billing Items Pricing' table")
    @FindBy(xpath = "//*[@data-ref='btnInnerEl' and text()='Pricing']/ancestor::*[@role='dialog' and @aria-hidden='false']//*[@role='tabpanel' and @aria-hidden='false']//*[@class='x-grid-item-container']")
    BillingItemsPricingTable billingItemPricingTable;

    @ElementName("'Fixed fee' Amount")
    @FindBy(xpath = "//*[@role='dialog' and @aria-hidden='false']//*[@data-columnid='quoteItemsPriceGridEstimatingSource']//*[text()='Fixed fee']/ancestor::*[@class='  x-grid-row']//*[@data-columnid='quoteItemsPriceGridUnitPrice']//div")
    TextElement fixedFeeAmountCellValue;

    @ElementName("'Cost' Amount")
    @FindBy(xpath = "//*[@role='dialog' and @aria-hidden='false']//*[@data-columnid='quoteItemsPriceGridEstimatingSource']//*[text()='Cost']/ancestor::*[@class='  x-grid-row']//*[@data-columnid='quoteItemsPriceGridUnitPrice']//div")
    TextElement costAmountCellValue;

    @ElementName("'Discount or Surcharge' Amount")
    @FindBy(xpath = "//*[@role='dialog' and @aria-hidden='false']//*[@data-columnid='quoteItemsPriceGridEstimatingSource']//*[text()='Discount or Surcharge']/ancestor::*[@class='  x-grid-row']//*[@data-columnid='quoteItemsPriceGridUnitPrice']//div")
    TextElement discountAndSurchargeAmountCellValue;

    @ElementName("'List price' Amount")
    @FindBy(xpath = "//*[@role='dialog' and @aria-hidden='false']//*[@data-columnid='quoteItemsPriceGridEstimatingSource']//*[text()='List price']/ancestor::*[@class='  x-grid-row']//*[@data-columnid='quoteItemsPriceGridUnitPrice']//div")
    TextElement listPriceAmountCellValue;


    @ElementName("'Confirm' button")
    @FindBy(xpath = "//*[@role='dialog' and @aria-hidden='false']//*[text()='Confirm']")
    BaseWebElement confirmButton;

    @ElementName("'Cost Allocations' tab")
    @FindBy(xpath = "//span[text()='Cost Allocations']")
    BaseWebElement costAllocTab;

    @ElementName("'Cancel' button")
    @FindBy(xpath = "//*[@role='dialog' and @aria-hidden='false']//span[text()='Cancel']")
    BaseWebElement cancelButton;

    @ElementName("'Contract Lines Allocations' table")
    @FindBy(xpath = "//div[not(contains(@class,'x-hidden-offsets')) and (contains (@class, 'x-tabpanel-child'))]//div[@class='x-grid-item-container']")
    ContractLinesCostAllocationsTable contractLinesAllocTable;

    @ElementName("'Click here to add' link")
    @FindBy(xpath = "//div[@aria-hidden='false' and @role='tabpanel']//a[@data-grigaddlink='true']//*[text()]")
    BaseWebElement addLink;

    @ElementName("'Estimated Cost' textBox")
    @FindBy(xpath = "//*[text()='Estimated Cost (from WBS)']/ancestor::label/following-sibling::div//input[@role='textbox']")
    IpeCustomInputField estimatedCostTextbox;

//    @ElementName("'Billing Items' table")
//    @FindBy(xpath = "//div[not(contains(@class,'x-hidden-offsets')) and (contains (@class, 'x-tabpanel-child'))]//div[@class='x-grid-item-container']")
//    BillingItemsTable billingItemsTable;
//*[@role="dialog" and @aria-hidden="false"]//*[@role="tab" and @aria-hidden="false"]//*[text()='Pricing']

    @ElementName("'Dialog Pricing' tab")
    @FindBy(xpath = "//*[@role='dialog' and @aria-hidden='false']//*[@role='tab' and @aria-hidden='false']//*[text()='Pricing']")
    BillingItemsTable dialogPricingTab;

    @ElementName("'Revenue Recognition Method' field")
    @FindBy(xpath = "(//div//label//span[text()='Revenue Recognition Method ']//following::input[1])")
    ArrowDropdownField revenueRecognitionField;

    @ElementName("'Pricing Strategy' field")
    @FindBy(xpath = "(//div//label//span[text()='Pricing Strategy *']//following::input[1])")
    IpeCustomDropdownField pricingStrategyField;

    @ElementName("'Description' column")
    @FindBy(xpath = "//*[@data-columnid='quoteItemsPriceGridEstimatingSource']")
    TableColumn descriptionColumn;

    @ElementName("'Bill of Material' table")
    @FindBy(xpath = "//div[not(contains(@class,'x-hidden-offsets')) and (contains (@class, 'x-panel x-tabpanel-child'))]//div[@class='x-grid-item-container']")
    BillMaterialTable billMaterialTable;

    @ElementName("'Cog' menu")
    @FindBy(xpath = "//table[contains(@class, 'x-grid-item')]//span[@class='x-tree-node-text ']//div[contains(@class, 'ibeCallOut')]")
    CogMenu cogMenu;

    @ElementName("'Yes' button")
    @FindBy(xpath = "//a//span[text()='Yes']")
    BaseWebElement yesButton;

    @ElementName("'Bill Of Material' tree")
    @FindBy(xpath = "//div[contains(@class,'x-tree-view x-fit-item x-tree-view-default x-scroller')]")
    SimpleTree billMaterialTree;

    @ElementName("'Run All Parts' button")
    @FindBy(xpath = "//span//span[text()='Run - All Parts']")
    BaseWebElement runAllPartsButton;

    @ElementName("'Hamburger' menu")
    @FindBy(xpath = "//a[@data-qtip='More']")
    IpeHumburgerTree hamburgerMenu;


    @Override
    public String getLoadTriggerName() {
        return BILLING_ITEMS_TAB;
    }


}


