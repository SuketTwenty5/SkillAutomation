package t5.ipe.cucumber.objects.pages;


import com.codeborne.selenide.Condition;
import org.openqa.selenium.support.FindBy;
import t5.ipe.cucumber.core.web.elements.*;
import t5.ipe.cucumber.core.web.interfaces.annotations.ElementName;
import t5.ipe.cucumber.core.web.interfaces.annotations.WebPage;
import t5.ipe.cucumber.core.web.util.AllureUtils;
import t5.ipe.cucumber.objects.elements.*;
import t5.ipe.cucumber.objects.elements.tables.*;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;
import static t5.ipe.cucumber.core.web.util.web.WebUtil.setCustomTimeoutSec;
import static t5.ipe.cucumber.core.web.util.web.WebUtil.setDefaultTimeout;

/**
 * Main page - The main page is accessible once the user is logged in, featuring a blue screen with toolbar tabs at the top.
 */
@WebPage(value = "Main page", urlTemplate = "\\/$|$|.*proposal[s]?$|.*proposal:[0-9A-Fa-f]{8}(?:-[0-9A-Fa-f]{4}){3}-[0-9A-Fa-f]{12}$|.*boe:[0-9A-Fa-f]{8}(?:-[0-9A-Fa-f]{4}){3}-[0-9A-Fa-f]{12}$|.*costing:[0-9A-Fa-f]{8}(?:-[0-9A-Fa-f]{4}){3}-[0-9A-Fa-f]{12}$")
public class MainPage extends BaseIpePage {
    public static final String TITLE_TOOLBAR = "'Title' toolbar";
    public static final String COST_STRUCTURE_TAB = "'Cost Structure' tab";

    @ElementName(COST_STRUCTURE_TAB)
    @FindBy(xpath = "//a//span[contains(text(),'Cost Structure')]")
    BaseWebElement costStructureTab;

    @ElementName("'Supplier quote warning' close")
    @FindBy(xpath = "//*[contains(text(),'Supplier Quote Type')]/ancestor::*[@role='dialog' and @aria-hidden='false']//*[@data-qtip='Close dialog']")
    BaseWebElement supplierQuoteWarningClose;

    @ElementName(TITLE_TOOLBAR)
    @FindBy(xpath = "//div[contains(@class, 'x-PricingAppNavTitleToolbar')]")
    LongWaitElement titleToolbar;

    @ElementName("'Total Price' widget")
    @FindBy(xpath = "//*[@role='tabpanel' and @aria-hidden='false']//*[@data-qtip='Total Price' or @data-qtip='Total Revenue']")
    TextElement totalPriceWidget;

    @ElementName("'First Add Filter' button")
    @FindBy(xpath = "//*[@role='grid' and @aria-hidden='false']//*[@role='toolbar' and @aria-hidden='false'][1]//*[contains(@class,'fa-plus')]")
    BaseWebElement firstAddFilterButton;

    @ElementName("'First Filter' dropdown")
    @FindBy(xpath = "//*[@role='grid' and @aria-hidden='false']//*[@role='toolbar' and @aria-hidden='false'][1]//*[contains(@class,'x-form-arrow-trigger-default')]")
    BaseWebElement firstFilterDropdown;

    @ElementName("'First Filter' dropbox")
    @FindBy(xpath = "//*[@role='listbox' and @aria-hidden='false']")
    BaseWebElement firstFilterDropbox;

    @ElementName("'First Filter' combobox")
    @FindBy(xpath = "//*[@role='combobox' and @aria-hidden='false']")
    IpeField firstFilterCombobox;

    @ElementName("'Regression Test Proposal for Copy Scenario - Manufacturing' proposal")
    @FindBy(xpath = "//*[@data-columnid='quoteSearchText']//*[contains(text(),'Regression Test Proposal for Copy Scenario - Manufacturing')]")
    BaseWebElement regressionTestProposalForCopyScenarioManufacturingProposal;

    @ElementName("'Proposal' option")
    @FindBy(xpath = "//li[@role='option' and text()='Proposal']")
    BaseWebElement firstFilterOption;

    @ElementName("'First Filter' input")
    @FindBy(xpath = "//input[@role='textbox' and @aria-hidden='false']")
    IpeField firstFilterInput;

    @ElementName("'Total Cost' widget")
    @FindBy(xpath = "//*[@role='tabpanel' and @aria-hidden='false']//*[@data-qtip='Total cost in the WBS']")
    TextElement totalCostWidget;

    @ElementName("'View' dropdown")
    @FindBy(xpath = "//div[not(contains(@class,'x-hidden-offsets')) and (contains (@class, 'x-tabpanel-child'))]//a[contains(@class, 'x-split-button')][@aria-hidden='false']//span[contains(text(), 'View')]")
    IpeViewDropdownField viewDropdown;


    @ElementName("'Dialog box View' dropdown")
    @FindBy(xpath = "//*[@role='dialog' and @aria-hidden='false']//div[not(contains(@class,'x-hidden-offsets')) and (contains (@class, 'x-tabpanel-child'))]//a[contains(@class, 'x-split-button')][@aria-hidden='false']//span[contains(text(), 'View')]")
    IpeDialogBoxViewDropDownField dialogBoxViewDropdown;

    @ElementName("'Supplier RFP’s' notification")
    @FindBy(xpath = "//*[contains(text(),'RFP') and @data-ref='innerCt']/ancestor::*[@role='dialog' and @aria-hidden='false']")
    IpeNotification supplierRFPsNotification;

    @ElementName("'Mfg Part' popUp")
    @FindBy(xpath = "//*[@role='dialog' and @aria-hidden='false']//*[contains(text(),'Mfg')]")
    BaseWebElement mfgPartPopUp;

    @ElementName("'Estimate for Labor' popUp")
    @FindBy(xpath = "//*[@role='dialog' and @aria-hidden='false']//*[contains(text(),'Estimate for')]//*[@role='tab' and @aria-selected='true']")
    BaseWebElement estimateForLaborPopUp;

    @ElementName("'Product or Service Price Catalog' dropdown")
    @FindBy(xpath = "//*[@role='tabpanel' and @aria-hidden='false']//*[contains(text(),'Product/Service') or contains(text(),'Price Catalog')]/ancestor::label/following-sibling::*//input")
    IpeDropdownField productOrServicePriceCatalogDropdown;

    @ElementName("'Estimate for Labor' popup")
    @FindBy(xpath = "//*[contains(text(),'Estimate for')]/ancestor::*[@role='dialog' and @aria-hidden='false']//*[@role='tab' and @aria-selected='true']")
    BaseWebElement estimateForLaborPopup;

    @ElementName("'New' button")
    @FindBy(xpath = "//span[text()='New']/ancestor::a")
    BaseWebElement newButton;

    @ElementName("'Copy' button")
    @FindBy(xpath = "//span[text()='Copy']")
    BaseWebElement copyButton;

    @ElementName("'Next' button")
    @FindBy(xpath = "//span[text()='Next']")
    BaseWebElement nextButton;

    @ElementName("'Reload' button")
    @FindBy(xpath = "//a[@data-qtip='Reload']")
    BaseWebElement reloadButton;

    @ElementName("'WBS' tab")
    @FindBy(xpath = "//span[text()='WBS' or text()='Workstreams']")
    BaseWebElement wbsTab;

    @ElementName("'Pricing' tab")
    @FindBy(xpath = "//a//span[text()='Pricing']")
    BaseWebElement pricingTab;

    @ElementName("'WBS Structure' tab")
    @FindBy(xpath = "//a//span[text()='WBS Structure' or contains(text(),'Cost Structure') or contains(text(),'Workstreams')]")
    BaseWebElement wbsStructureTab;

    @ElementName("'Proposal BOM' tab")
    @FindBy(xpath = "//a[@role='tab' and @aria-hidden='false']//span[text()='Proposal BOM' or text()='Bom']")
    BaseWebElement proposalBOMTab;

    @ElementName("'Expand or Collapse' menuItem")
    @FindBy(xpath = "//*[@role='menu' and @aria-hidden='false']//*[@role='menuitem' and @aria-hidden='false']//*[contains(text(),'Expand/Collapse')]/ancestor::a")
    BaseWebElement expandOrCollapseMenuItem;

    @ElementName("'Expand All' menuItem")
    @FindBy(xpath = "//*[@role='menu' and @aria-hidden='false']//*[@role='menuitem' and @aria-hidden='false']//*[contains(text(),'Expand All')]/ancestor::a")
    BaseWebElement expandAllMenuItem;

    @ElementName("'Part Number Menu' options")
    @FindBy(xpath = "//*[@role='menu' and @aria-hidden='false']//*[contains(@class,'x-box-scroller-body-vertical')]")
    BaseWebElement partNumberMenuOptions;

    @ElementName("'Master Data' tab")
    @FindBy(xpath = "//*[@role='button' and @aria-hidden='false']//*[contains(text(),'Master Data') or contains(text(),'MASTER DATA')]")
    BaseWebElement masterDataTab;

    @ElementName("'SETUP' options")
    @FindBy(xpath = "//*[@role='menu' and @aria-hidden='false']")
    MenuItemField setupOptions;

    @ElementName("top menu 'Setup'")
    @FindBy(xpath = "//*[@role='menu' and @aria-hidden='false' and @aria-expanded='true']//*[@role='menuitem' and @aria-hidden='false']")
    MenuItemField setupTopMenu;

    @ElementName("'Users' tab")
    @FindBy(xpath = "//*[@role='button' and @aria-hidden='false']//span[contains(text(),'tech twenty5') or contains(text(),'Suket Suman')]")
    BaseWebElement usersTab;

    @ElementName("'Users' options")
    @FindBy(xpath = "//*[@role='menu' and @aria-hidden='false']")
    MenuItemField usersOptions;

    @ElementName("top menu 'Users'")
    @FindBy(xpath = "//*[@role='menu' and @aria-hidden='false' and @aria-expanded='true']//*[@role='menuitem' and @aria-hidden='false']")
    MenuItemField usersTopMenu;

    @ElementName("'SETUP' tab")
    @FindBy(xpath = "//a//span[contains(text(),'SETUP')]")
    BaseWebElement SETUPTab;

    @ElementName("'Proposals' tab")
    @FindBy(xpath = "//a//span[contains(text(),'Proposals') or contains(text(),'PROPOSALS')]")
    BaseWebElement proposalsTab;

     @ElementName("'GA Cost' option")
     @FindBy(xpath = "//*[contains(@class,'x-menu-item-unchecked')]//*[@class='x-menu-item-link']//*[@data-ref='textEl' and text()='G&A Cost']/following-sibling::*")
     BaseWebElement gaCostOption;

    @ElementName("'Master Data' options")
    @FindBy(xpath = "//*[@role='menu' and @aria-hidden='false']")
    BaseWebElement masterDataOptions;

    @ElementName("'Create from Type' option")
    @FindBy(xpath = "//*[@role='menu' and @aria-expanded='true' and @aria-hidden='false']//*[@class='x-menu-item-link']//*[contains(text(),'Create from Type')]/ancestor::a[@role='menuitem' and @aria-hidden='false']")
    BaseWebElement createFromTypeOption;

    @ElementName("'Create from Type' dropDown")
    @FindBy(xpath = "//*[@role='menu' and @aria-hidden='false'][2]")
    BaseWebElement createFromTypeDropDown;

    @ElementName("'Purchased Part' option")
    @FindBy(xpath = "//*[@role='menuitem' and @aria-hidden='false']//*[contains(text(),'Purchased Part')]/ancestor::a")
    BaseWebElement purchasedPartOption;

    @ElementName("'Material Cost Estimates' tab")
    @FindBy(xpath = "//*[@role='button' and @aria-hidden='false']//*[contains(text(),'MATERIAL COST ESTIMATES') or contains(text(),'SOURCING & COST HISTORY')]/ancestor::*[@role='button' and @aria-hidden='false']")
    BaseWebElement materialCostEstimatesTab;

    @ElementName("'Material Cost Estimates' options")
    @FindBy(xpath = "//*[@role='menu' and @aria-hidden='false']")
    MenuItemField materialCostEstimatesOptions;

    @ElementName("'Cost Source History' sub-tab")
    @FindBy(xpath = "//*[@role='menu' and @aria-hidden='false']//span[text()='Cost Source History' or text()='Purchase History']")
    BaseWebElement costSourceHistorySubTab;

    @ElementName("'Supplier Quote' option")
    @FindBy(xpath = "//*[@role='menu' and @aria-hidden='false']//*[@role='menuitem' and @aria-hidden='false']//*[contains(text(),'Supplier Quote')]/ancestor::a")
    BaseWebElement supplierQuoteOption;

    @ElementName("'Create from Category' option")
    @FindBy(xpath = "//*[@role='menu' and @aria-expanded='true' and @aria-hidden='false']//*[@class='x-menu-item-link']//*[contains(text(),'Create from Category')]/ancestor::a[@role='menuitem' and @aria-hidden='false']")
    BaseWebElement createFromCategoryOption;

    @ElementName("'Create from Category' dropDown")
    @FindBy(xpath = "//*[@role='menu' and @aria-hidden='false'][2]")
    BaseWebElement createFromCategoryDropDown;

    @ElementName("'Error' dialog")
    @FindBy(xpath = "//*[@role='dialog' and @aria-hidden='false']")
    TextElement errorDialog;

    @ElementName("'New' options")
    @FindBy(xpath = "//*[@role='menu' and @aria-hidden='false']")
    MenuItemField newOptions;

    @ElementName("'Products & Services' sub-tab")
    @FindBy(xpath = "//*[@role='menu' and @aria-hidden='false']//span[text()='Products & Services']")
    BaseWebElement productsAndServicesSubTab;

    @ElementName("'Margin as Percentage' widget")
    @FindBy(xpath = "//*[@role='tabpanel' and @aria-hidden='false']//*[@data-qtip='Margin as Percentage']")
    TextElement marginAsPercentageWidget;

    @ElementName("'Phases' tab")
    @FindBy(xpath = "//span[contains(text(),'Phases')]")
    BaseWebElement phasesTab;

    @ElementName("'CLINs' tab")
    @FindBy(xpath = "//span[text()='CLINs' or text()='Line Items' or text() = 'Deliverables']")
    BaseWebElement clinsTab;

    @ElementName("'Clins' table")
    @FindBy(xpath = "//div[not(contains(@class,'x-hidden-offsets')) and (contains (@class, 'x-container x-tabpanel-child'))]//div[@class='x-grid-item-container']")
    ClinsTable clinsTable;

    @ElementName("'CLINs' table")
    @FindBy(xpath = "//div[not(contains(@class,'x-hidden-offsets')) and (contains (@class, 'x-container x-tabpanel-child'))]//div[@class='x-grid-item-container']")
    ClinsTable cLINSTable;

    @ElementName("'Cell dropdown' arrow")
    @FindBy(xpath = "//*[@role='tabpanel' and @aria-hidden='false']//*[@role='grid' and @aria-hidden='false']//*[contains(@class,'x-form-arrow-trigger')]")
    BaseWebElement cellDropdownArrow;

    @ElementName("'Cell' dropdown")
    @FindBy(xpath = "//*[@role='listbox' and @aria-hidden='false']")
    BaseWebElement cellDropdown;

    @ElementName("'Create New' button")
    @FindBy(xpath = "//*[contains(@class,'x-boundlist')]//*[@role='toolbar' and @aria-hidden='false']//*[contains(text(),'New')]/ancestor::*[@role='button' and @aria-hidden='false']")
    BaseWebElement createNewButtonListBox;

    @ElementName("'Cost Allocation' popUp")
    @FindBy(xpath = "//*[@role='dialog' and @aria-hidden='false']")
    BaseWebElement costAllocationPopUp;

    @ElementName("'Bill of Material' tab")
    @FindBy(xpath = "//span[text()='Bill of Material']")
    BaseWebElement billOfMaterialTab;

    @ElementName("'Procurement & Production' table")
    @FindBy(xpath = "//div[not(contains(@class,'x-hidden-offsets')) and (contains (@class, 'x-container x-tabpanel-child'))]//div[@class='x-grid-item-container']")
    CommonDualTable procurementProductionTable;

    @ElementName("'Procurement & Production' tab")
    @FindBy(xpath = "//span[text()='Procurement & Production' or text()='Material']")
    BaseWebElement procurementProductionTab;

    @ElementName("'Labor' tab")
    @FindBy(xpath = "//span[text()='Labor' or text()='Engineering' or text()='Labor Resources']")
    BaseWebElement laborTab;

    @ElementName("'Hardware & Software' tab")
    @FindBy(xpath = "//span[text()='Hardware & Software']")
    BaseWebElement hwSwTab;

    @ElementName("'Billing Plan' tab")
    @FindBy(xpath = "//a[@aria-hidden='false']//span[contains(text(),'Billing')]")
    BaseWebElement billingPlanTab;

    @ElementName("'Billing Items' tab")
    @FindBy(xpath = "//a[@aria-hidden='false']//span[contains(text(),'Billing Items')]")
    BaseWebElement billingItemsTab;

    @ElementName("'Sets' tab")
    @FindBy(xpath = "//a[@aria-hidden='false']//span[contains(text(),'Sets')]")
    BaseWebElement setsTab;

    @ElementName("'Proposal BOM' table")
    @FindBy(xpath = "(//div[not(contains(@class,'x-hidden-offsets')) and (contains (@class, 'x-panel x-tabpanel-child'))]//div[@class='x-grid-item-container'])")
    proposalBOMTable proposalBOMTable;

    @ElementName("'Owner' column header")
    @FindBy(xpath = "//*[@role='columnheader' and @aria-hidden='false']//*[text()='Owner']/ancestor::*[@role='columnheader' and @aria-hidden='false']")
    BaseWebElement ownerColumnHeader;

    @ElementName("'Owner' column chevron")
    @FindBy(xpath = "//*[@role='columnheader' and @aria-hidden='false']//*[text()='Owner']/ancestor::*[@role='columnheader' and @aria-hidden='false']//*[@data-ref='triggerEl']")
    BaseWebElement ownerColumnChevron;

    @ElementName("'Column' header options")
    @FindBy(xpath = "//*[@role='menu' and @aria-hidden='false' and @aria-expanded='true']")
    BaseWebElement columnHeaderOptions;

    @ElementName("top menu")
    @FindBy(xpath = "//*[(@role='toolbar' and @aria-hidden='false') or contains(@class,'PricingAppSearchContainer')]//*[contains(@class,'TopMenuButton') and @aria-hidden='false']")
    MenuItemField topMenu;

    @ElementName("'Search' icon")
    @FindBy(xpath = "//*[contains(@class,'PricingAppTitleToolbar')]//*[@role='toolbar' and @aria-hidden='false']//*[@data-qtip='Search' and @aria-hidden='false']//*[@data-ref='btnEl']")
    BaseWebElement searchIcon;

    @ElementName("'Refresh' icon")
        @FindBy(xpath = "//*[contains(@class,'PricingAppTitleToolbar')]//*[@role='toolbar' and @aria-hidden='false']//*[@data-qtip='Reload' and @aria-hidden='false']//*[@data-ref='btnEl']")
    BaseWebElement refreshIcon;


    @ElementName("'Columns' option")
    @FindBy(xpath = "//*[@role='menuitem' and .//*[text()='Columns']]")
    BaseWebElement columnsOption;

    @ElementName("'Columns' options")
    @FindBy(xpath = "//*[@role='menu' and @aria-hidden='false' and @aria-expanded='true'][2]")
    BaseWebElement columnsOptions;

    @ElementName("'plus' searchIcon")
    @FindBy(xpath = "//*[@role='grid' and @aria-hidden='false']//*[@role='toolbar' and @aria-hidden='false']//*[contains(@class,'fa-plus')]")
    BaseWebElement plusSearchIcon;

    @ElementName("'Created On' option")
    @FindBy(xpath = "//*[@role='menuitemcheckbox' and .//*[text()='Created On']]")
    BaseWebElement createdOnOption;

    @ElementName("'Created On' column")
    @FindBy(xpath = "//*[@role='columnheader' and @aria-hidden='false']//*[text()='Created On']/ancestor::*[@role='columnheader' and @aria-hidden='false']")
    BaseWebElement createdOnColumn;

    @ElementName("'Lines Items' tab")
    @FindBy(xpath = "//span[text()='Line Items']")
    BaseWebElement lineItemsTab;

    @ElementName("'Save' button")
    @FindBy(xpath = "//a[@data-qtip='Save']")
    SaveButton saveButton;

    @ElementName("'Confirm' button")
    @FindBy(xpath = "//*[@role='dialog' and @aria-hidden='false' and contains(@class,'x-window x-layer')]//*[@role='toolbar' and @aria-hidden='false' and contains(@class,'x-toolbar-default-docked-bottom')]//*[@role='button' and @aria-hidden='false']//*[text()='Confirm']/ancestor::a")
    BaseWebElement confirmButton;

    @ElementName("'Material Cost' check")
    @FindBy(xpath = "//span[@aria-label='Material Cost']")
    BaseWebElement materialCostCheck;

    @ElementName("'Reschedule Proposal' menuItem")
    @FindBy(xpath = "//*[text()='Reschedule Proposal']/ancestor::*[@role='menuitem' and @aria-hidden='false']")
    BaseWebElement rescheduleProposalMenuItem;

    @ElementName("'Reload' page")
    @FindBy(xpath = "//*[@data-qtip='Reload']")
    BaseWebElement reloadSymbol;

    @ElementName("'Items' tab")
    @FindBy(xpath = "//a//span[contains(text(),'Items')]")
    BaseWebElement itemsTab;

    @ElementName("'View' dropArrow")
    @FindBy(xpath = "//*[contains(@class, 'x-toolbar') and @aria-hidden='false' and @role='group']//a[@aria-hidden='false' and contains(@class, 'x-split-button')]//span[@data-ref='arrowEl']")
    BaseWebElement viewDropArrow;

    @ElementName("'View' dropArrow2")
    @FindBy(xpath = "//*[contains(@class, 'x-toolbar')]//a[contains(@class, 'x-split-button')]")
    BaseWebElement viewDropArrow2;

    @ElementName("'Default' view")
    @FindBy(xpath = "//a[@role='menuitem']//*[text()='Default (shared)']")
    BaseWebElement defaultView;

    @ElementName("'Hamburger' menu")
    @FindBy(xpath = "//a[@data-qtip='More']")
    IpeHumburgerTree hamburgerMenu;

    @ElementName("top menu 'Material Cost Estimates'")
    @FindBy(xpath = "//*[@role='menu' and @aria-hidden='false' and @aria-expanded='true']//*[@role='menuitem' and @aria-hidden='false']")
    MenuItemField materialCostEstimatesTopMenu;

    @ElementName("top menu 'Master Data'")
    @FindBy(xpath = "//*[@role='menu' and @aria-hidden='false' and @aria-expanded='true']//*[@role='menuitem' and @aria-hidden='false']")
    MenuItemField masterDataTopMenu;

    @ElementName("'Status' widget")
    @FindBy(xpath = "//*[@role='tabpanel' and @aria-hidden='false']//*[@class='x-ibeLinksCls data-tile-link']/ancestor::*[contains(@class,'x-container-default x-scroller')]")
    BaseWebElement statusWidget;

    @ElementName("'Status & logs' link")
    @FindBy(xpath = "//*[@role='tabpanel' and @aria-hidden='false']//*[@subtitlelink='showJobStatus']")
    TextElement statusLogsLink;

    @ElementName("'Cost & Price' status")
    @FindBy(xpath = "//*[@role='tabpanel' and @aria-hidden='false']//*[@class='x-ibeLinksCls data-tile-link']/parent::div/div[1]")
    TextElement costPriceStatus;

    @ElementName("'Dialog View' dropdown")
    @FindBy(xpath = "//*[@role='dialog' and @aria-hidden='false']//div[not(contains(@class,'x-hidden-offsets')) and (contains (@class, 'x-tabpanel-child'))]//a[contains(@class, 'x-split-button')][@aria-hidden='false']//span[contains(text(), 'View')]")
    IpeDialogViewDropdownField dialogViewDropdown;

    @ElementName("'Reset Now' button")
    @FindBy(xpath = "//*[(@role='dialog' or @role='alertdialog') and @aria-hidden='false']//*[contains(@class,'x-btn') and @role='button' and .//*[text()='Reset Now']]")
    localDataStorageReset resetNowButton;

    @ElementName("'Other' tab")
    @FindBy(xpath = "//a[@role='tab' and @aria-hidden='false']//span[text()='Other']/ancestor::*[@role='tab' and @aria-hidden='false']")
    BaseWebElement otherTab;

    @ElementName("'Engineering or Functional Labor' tab")
    @FindBy(xpath = "//*[@role='tablist' and @aria-hidden='false']//*[contains(text(),'Engineering') or contains(text(),'Labor')]/ancestor::*[@role='tab' and @aria-hidden='false']")
    BaseWebElement engineeringOrFunctionalLaborTab;

    @ElementName("'Default Pricing Strategy' dropdown")
    @FindBy(xpath = "//*[@role='tabpanel' and @aria-hidden='false']//*[contains(text(),'Default Pricing Strategy')]/ancestor::label/following-sibling::*//input")
    IpeDropdownField defaultPricingStrategyDropdown;

    @ElementName("'Dialog Pricing Strategy' dropdown")
    @FindBy(xpath = "//*[@role='dialog' and @aria-hidden='false']//*[@role='tabpanel' and @aria-hidden='false']//*[contains(text(),'Pricing Strategy')]/ancestor::label/following-sibling::*//input")
    IpeDropdownField dialogPricingStrategyDropdown;


    @ElementName("'Update Cost & Prices' link")
    @FindBy(xpath = "//*[@role='tabpanel' and @aria-hidden='false']//*[@titlelink='updateCostsWithFormula' or @titlelink='updateBoeCostsWithFormula']")
    BaseWebElement updateCostPriceLink;

    @ElementName("'Proposal View' dropdown")
    @FindBy(xpath = "//*[@role='toolbar' and @aria-hidden='false']//a[contains(@class, 'x-split-button')][@aria-hidden='false']//span[contains(text(), 'View:') or contains(text(), 'Select or Create View')]")
    IpeViewDropdownField proposalViewDropdown;

    @ElementName("'WBS View' dropdown")
    @FindBy(xpath = "//*[contains(@class, 'x-toolbar') and @aria-hidden='false' and @role='group']//a[contains(@class, 'x-split-button')]")
    IpeViewDropdownField wbsViewDropdown;

    @ElementName("'first' proposal")
    @FindBy(xpath = "//table[1]//tr/td[@data-columnid='quoteSearchText']/div")
    BaseWebElement firstProposal;

    @ElementName("'UNLOCK FOR OTHERS' link")
    @FindBy(xpath = "//*[@data-qtip='Edit mode - Click to display (unlocks for other users)' and @aria-disabled='false']")
    BaseWebElement unlockForOthersLink;

    @ElementName("'Line Items' table")
    @FindBy(xpath = "//div[not(contains(@class,'x-hidden-offsets')) and (contains (@class, 'x-container x-tabpanel-child'))]//div[@class='x-grid-item-container']")
    LineItemsTable lineItemsTable;

    @ElementName("'No records found, click here to add' button")
    @FindBy(xpath = "//*[@role='tabpanel' and @aria-hidden='false']//*[@aria-hidden='false' and (@role='grid' or @role='treegrid')]//*[@data-grigaddlink='true']//div[text()]")
    LongWaitElement noRecordFind;

    @ElementName("'List View' dropdown")
    @FindBy(xpath = "//*[@role='toolbar' and @aria-hidden='false']//a[contains(@class, 'x-split-button')][@aria-hidden='false']//span[contains(text(), 'View')]")
    IpeViewDropdownField listViewDropdown;

    @ElementName("'Filter' icon")
    @FindBy(xpath = "//*[@role='toolbar' and @aria-hidden='false']//*[@data-qtip='Filters']")
    BaseWebElement filterIcon;

    @ElementName("'Download' icon")
    @FindBy(xpath = "//*[@role='toolbar' and @aria-hidden='false']//*[@data-qtip='Download']")
    BaseWebElement downloadIcon;

    @ElementName("'Download' options")
    @FindBy(xpath = "//*[@role='menu' and @aria-hidden='false' and @aria-expanded='true']")
    MenuItemField downloadOptions;

    @ElementName("'Update Estimate totals' link")
    @FindBy(xpath = "//*[@role='tabpanel' and @aria-hidden='false']//*[@titlelink='updateBoeCostsWithFormula']")
    TextElement UpdateEstimateTotalsLink;

    @ElementName("'Other Revenue' widget")
    @FindBy(xpath = "//*[@role='tabpanel' and @aria-hidden='false']//*[@data-qtip='Other revenue']")
    TextElement otherRevenueWidget;

    @ElementName("'Owner' EstimatePopup")
    @FindBy(xpath = "//*[@role='dialog' and @aria-hidden='false']//*[text()='Owner *']/ancestor::label/following-sibling::*//input")
    IpeDropdownField ownerEstimatePopUp;

    @ElementName("'Other Cost' widget")
    @FindBy(xpath = "//*[@role='tabpanel' and @aria-hidden='false']//*[@data-qtip='Other cost']")
    TextElement otherCostWidget;

    @ElementName("'Travel Revenue' widget")
    @FindBy(xpath = "//*[@role='tabpanel' and @aria-hidden='false']//*[@data-qtip='Travel & Other revenue']")
    TextElement travelRevenueWidget;

    @ElementName("'Travel Expenses' widget")
    @FindBy(xpath = "//*[@role='tabpanel' and @aria-hidden='false']//*[@data-qtip='Travel & Other expenses']")
    TextElement travelCostWidget;

    @ElementName("'Update Cost' button")
    @FindBy(xpath = "//*[@titlelink='updateCostsWithFormula']")
    BaseWebElement updCostButton;

    @ElementName("'Consolidated BOM' tab")
    @FindBy(xpath = "//*[@role='tab' and @aria-hidden='false']//*[contains(text(),'Consolidated BOM')]")
    IpeHeavyLoadTab consolidatedBOMTab;

    @ElementName("'Data saved successfully' popUp")
    @FindBy(xpath = "//*[text()='Data saved successfully']/ancestor::*[@role='dialog' and @aria-hidden='false']")
    LongWaitElement dataSavedSuccessfullyPopUp;

    @ElementName("'WBS Cost Structure' tab")
    @FindBy(xpath = "//a[@role='tab' and @aria-hidden='false']//span[contains(text(),'WBS Cost Structure') or contains(text(),'Cost Structure')]")
    BaseWebElement wbsCostStructureTab;

    @ElementName("'Filter by' dropdown")
    @FindBy(xpath = "//input[@placeholder='Choose Field']")
    IpeCustomDropdownField filterByDropdown;

    @ElementName("'Cog Settings' menu")
    @FindBy(xpath = "//div[((contains(@class, 'x-fit-item x-panel-default x-grid') or contains(@class,'x-tree-panel x-tree-lines x-grid'))) and contains(@aria-hidden,'false')]//span[contains(@class, 'x-fa fa-cog')]")
    IpeCogTree cogMenu;

    @ElementName("'Filter by' dropDownArrow")
    @FindBy(xpath="//input[@placeholder='Choose Field']/parent::*/following-sibling::*")
    BaseWebElement filterByDropDownArrow;

    @ElementName("'Filter by' options")
    @FindBy(xpath = "//*[@role='listbox' and @aria-hidden='false']")
    MenuItemField filterByOptions;

    @ElementName("'Owner' column")
    @FindBy(xpath = "//*[@data-columnid='quoteSearchResponsibleID']")
    ListColumn ownerColumn;

    @ElementName("'Owner' dropDownArrow")
    @FindBy(xpath="//*[@data-ref='placeholderLabel' and text()='All Owner']/ancestor::*[@data-ref='triggerWrap']//*[contains(@class,'x-form-arrow-trigger')]")
    BaseWebElement ownerDropDownArrow;

    @ElementName("'Allen Azar' option")
    @FindBy(xpath = "//*[@role='option' and text()='Allen Azar']")
    BaseWebElement allenAzarOption;

    @ElementName("'Filter' field")
    @FindBy(xpath = "//input[@role='textbox']")
    IpeField filterField;

    @ElementName("'Filter' selectedTags")
    @FindBy(xpath = "//input[@role='textbox']/ancestor::*[@role='combobox' and @aria-hidden='false' and @aria-autocomplete='list']//*[@data-ref='itemList']")
    TextElement filterSelectedTags;

    @ElementName("'Update Cost & Prices' link")
    @FindBy(xpath = "//div[normalize-space()='Update Cost & Prices']")
    BaseWebElement updateCostAndPricesLink;

    @ElementName("'Open' menuItem")
    @FindBy(xpath = "//*[@role='menu' and @aria-hidden='false']//*[contains(@class,'x-menu-item x-menu-item-default x-box-item')]//*[text()='Open']")
    BaseWebElement openMenuItem;

    @ElementName("'Open' menuItems")
    @FindBy(xpath = "//*[@role='menu' and @aria-hidden='false' and @aria-expanded='true'][last()]//*[@role='menuitem' and @aria-hidden='false'] ")
    MenuItemField openMenuItems;

    @ElementName("'Open Proposal' menuItem")
    @FindBy(xpath = "//*[@role='menu' and @aria-hidden='false']//*[contains(@class,'x-menu-item x-menu-item-default x-box-item')]//*[text()='Open Proposal']")
    MenuItemField openProposalMenuItem;

    @ElementName("'Setup' tab")
    @FindBy(xpath = "//a//span[contains(text(),'Setup')]")
    BaseWebElement setupTab;

    @ElementName("'Yes' dialogButton")
    @FindBy(xpath = "//*[@role='alertdialog' and @aria-hidden='false']//*[text()='Yes']")
    BaseWebElement yesDialogButton;

    @ElementName("'Confirmation Yes' button")
    @FindBy(xpath = "//*[@role='alertdialog' and @aria-hidden='false']//*[text()='Yes']")
    BaseWebElement confirmationYesDialogButton;

    @ElementName("'Click here to add' link")
    @FindBy(xpath = "//a[text()='click here to add']")
    BaseWebElement addLink;

    @ElementName("'Regression Finished Goods 1' link")
    @FindBy(xpath = "//div[normalize-space() = 'Regression Finished Goods 1']")
    BaseWebElement regressionClinsLink;

    @ElementName("'Contract Lines Allocations' table")
    @FindBy(xpath = "//div[not(contains(@class,'x-hidden-offsets')) and (contains (@class, 'x-panel x-tabpanel-child'))]//div[@class='x-grid-item-container']")
    ContractLinesCostAllocationsTable contractLinesAllocTable;

    @ElementName("'Import BOM' check")
    @FindBy(xpath = "//*[@role='row']//*[@data-columnid='itemsTabBomColumn']")
    BaseWebElement costBomCheck;

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


    @Override
    public String getLoadTriggerName() {
        return TITLE_TOOLBAR;
    }

}
