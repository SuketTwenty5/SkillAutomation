package t5.ipe.cucumber.objects.pages.proposals;


import org.openqa.selenium.support.FindBy;
import t5.ipe.cucumber.core.web.elements.BaseWebElement;
import t5.ipe.cucumber.core.web.interfaces.annotations.ElementName;
import t5.ipe.cucumber.core.web.interfaces.annotations.WebPage;
import t5.ipe.cucumber.objects.elements.IpeCogTree;
import t5.ipe.cucumber.objects.elements.tables.ClinsTable;
import t5.ipe.cucumber.objects.elements.tables.ContractLinesCostAllocationsTable;
import t5.ipe.cucumber.objects.pages.MainPage;

/**
 * CLINs page.
 */
@WebPage(value = "CLINs page", urlTemplate = ".*quote:[0-9A-Fa-f]{8}(?:-[0-9A-Fa-f]{4}){3}-[0-9A-Fa-f]{12}$", tabName = "CLINs")
public class ClinsPage extends MainPage {
    public static final String CLINS_TAB = "'CLINs' tab";

    @ElementName(CLINS_TAB)
    @FindBy(xpath = "//a[@aria-selected='true']//span[text()='CLINs' or text()='Contract Lines' or text()='Deliverables']")
    BaseWebElement clinsTab;

    @ElementName("'Click here to add' link")
    @FindBy(xpath = "//a[text()='click here to add']")
    BaseWebElement addLink;

    @ElementName("'Regression Finished Goods 1' link")
    @FindBy(xpath = "//div[normalize-space() = 'Regression Finished Goods 1']")
    BaseWebElement regressionClinsLink;

    @ElementName("'Update Cost & Prices' link")
    @FindBy(xpath = "//div[normalize-space()='Update Cost & Prices']")
    BaseWebElement updateCostAndPricesLink;

    @ElementName("'Clins' table")
    @FindBy(xpath = "//div[not(contains(@class,'x-hidden-offsets')) and (contains (@class, 'x-container x-tabpanel-child'))]//div[@class='x-grid-item-container']")
    ClinsTable clinsTable;

    @ElementName("'Contract Lines Allocations' table")
    @FindBy(xpath = "//div[not(contains(@class,'x-hidden-offsets')) and (contains (@class, 'x-panel x-tabpanel-child'))]//div[@class='x-grid-item-container']")
    ContractLinesCostAllocationsTable contractLinesAllocTable;

    @ElementName("'Import BOM' check")
    @FindBy(xpath = "//*[@role='row']//*[@data-columnid='itemsTabBomColumn']")
    BaseWebElement costBomCheck;

    @ElementName("'Reload' button")
    @FindBy(xpath = "//a[@data-qtip='Reload']")
    BaseWebElement reloadButton;

    @ElementName("'Line Items Details' table")
    @FindBy(xpath = "//div[not(contains(@class,'x-hidden-offsets')) and (contains (@class, 'x-panel x-tabpanel-child'))]//div[@class='x-grid-item-container']")
    ContractLinesCostAllocationsTable lineItemDetailsTable;

    @ElementName("'Re-price' button")
    @FindBy(xpath = "//span[normalize-space()='Re-price' and @data-ref='btnInnerEl']")
    BaseWebElement rePriceButton;

    @ElementName("'Workbench' tab")
    @FindBy(xpath = "//span[text()='Workbench']")
    BaseWebElement workbenchTab;

    @ElementName("'Import BOMs' button")
    @FindBy(xpath = "//*[@role='dialog' and not(contains(@class,'x-window-ghost')) and @aria-hidden='false']//*[text()='Import BOMs']")
    BaseWebElement importBOMsButton;

    @ElementName("'Wait for Importing BOMs to complete' message")
    @FindBy(xpath = "//*[contains(text(),'SAP')]/ancestor::*[@role='dialog' and @aria-hidden='false']")
    BaseWebElement waitForImportingBOMsToCompleteMessage;


    @ElementName("'Cell dropdown' arrow")
    @FindBy(xpath = "//*[@role='grid' and @aria-hidden='false']//*[@role='gridcell']//*[contains(@class,'x-form-trigger-default')]")
    BaseWebElement cellDropdownArrow;

    @ElementName("'Cell' dropdown")
    @FindBy(xpath = "//*[@role='listbox' and @aria-hidden='false']")
    BaseWebElement cellDropdown;

    @ElementName("'Group Gear' menu")
    @FindBy(xpath = "//*[@role='toolbar' and @aria-hidden='false']//*[@data-qtip='Settings' and @aria-hidden='false']")
    IpeCogTree groupGearMenu;

    @ElementName("'Group Gear' options")
    @FindBy(xpath = "//*[@role='menu' and @aria-hidden='false']")
    BaseWebElement groupGearOptions;

    @ElementName("'BOMs imported successfully' message")
    @FindBy(xpath = "//*[contains(text(),'Bom')]/ancestor::*[@role='dialog' and @aria-hidden='false']")
    BaseWebElement bOMsImportedSuccessfullyMessage;

    @ElementName("'Gear' icon")
    @FindBy(xpath = "//*[@role='toolbar' and @aria-hidden='false']//*[@data-qtip='Settings' and @aria-hidden='false']")
    BaseWebElement gearIcon;

    @ElementName("'Select Contract Lines to Import' popUp")
    @FindBy(xpath = "//*[@role='dialog' and not(contains(@class,'x-window-ghost')) and @aria-hidden='false']//*[text()='Select Contract Lines to Import']")
    BaseWebElement selectContractLinesToImportPopUp;

    @ElementName("'Top row' checkbox")
    @FindBy(xpath = "//*[@role='dialog' and not(contains(@class,'x-window-ghost')) and @aria-hidden='false']//*[@class='x-column-header-checkbox']")
    BaseWebElement topRowCheckbox;

    @ElementName("'Create New' button")
    @FindBy(xpath = "//*[@role='listbox' and @aria-hidden='false']/ancestor::*[contains(@class,'x-boundlist-default')]//*[text()='Create New']")
    BaseWebElement createNewButton;

    @ElementName("'Cost Allocation' popUp")
    @FindBy(xpath = "//*[@role='dialog' and @aria-hidden='false']//*[text()='Create new end item WBS']")
    BaseWebElement costAllocationPopUp;

    @Override
    public String getLoadTriggerName() {
        return CLINS_TAB;
    }


}


