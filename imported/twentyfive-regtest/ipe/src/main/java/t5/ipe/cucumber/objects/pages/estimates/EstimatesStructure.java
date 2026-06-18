package t5.ipe.cucumber.objects.pages.estimates;


import org.openqa.selenium.support.FindBy;
import t5.ipe.cucumber.core.web.elements.BaseWebElement;
import t5.ipe.cucumber.core.web.elements.LongWaitElement;
import t5.ipe.cucumber.core.web.elements.TextElement;
import t5.ipe.cucumber.core.web.interfaces.annotations.ElementName;
import t5.ipe.cucumber.core.web.interfaces.annotations.WebPage;
import t5.ipe.cucumber.objects.elements.*;
import t5.ipe.cucumber.objects.elements.tables.WBSTable;
import t5.ipe.cucumber.objects.pages.MainPage;

/**
 * Estimates - Structure page.
 */
@WebPage(value = "Estimate Structure", urlTemplate = ".*#boe:[0-9A-Fa-f]{32}$|.*boe:[0-9A-Fa-f]{8}(?:-[0-9A-Fa-f]{4}){3}-[0-9A-Fa-f]{12}$", tabName = "Structure")
public class EstimatesStructure extends MainPage {
    public static final String STRUCTURE_TAB = "'STRUCTURE' tab";

    @ElementName(STRUCTURE_TAB)
    @FindBy(xpath = "//a[@aria-selected='true']//span[text()='Structure']")
    BaseWebElement forecastTab;

    @ElementName("'Close' button")
    @FindBy(xpath = "//*[@role='dialog' and @aria-hidden='false']//*[text()='Close']")
    BaseWebElement closeButton;

    @ElementName("'Confirm & Open' button")
    @FindBy(xpath = "//span[text()='Confirm & Open']")
    BaseWebElement confirmOpenButton;

    @ElementName("'Estimate' popUp")
    @FindBy(xpath = "//*[text()='Estimate']/ancestor::*[@role='dialog' and @aria-hidden='false']")
    BaseWebElement estimatePopUp;

    @ElementName("'WBS Cost Structure' table")
    @FindBy(xpath = "//*[@role='treegrid' and @aria-hidden='false']//*[@class='x-grid-item-container']")
    WBSTable wbsCostStructureTable;

    @ElementName("'WBS' tree")
    @FindBy(xpath = "//div[contains(@class,'x-tree-view x-fit-item x-tree-view-default x-scroller')]")
    SimpleTree wbsTree;

    @ElementName("'Cog' menu")
    @FindBy(xpath = "//table[contains(@class, 'x-grid-item-selected')]//span[@class='x-tree-node-text ']//div[contains(@class, 'ibeCallOut')]")
    CogMenu cogMenu;

    @ElementName("'Owner' field")
    @FindBy(xpath = "//span[text()='Owner *']/ancestor::td//input")
    EstimatePopUpDropDown ownerField;

    @ElementName("'Confirm & Release' button")
    @FindBy(xpath = "//span[text()='Confirm & Release']")
    BaseWebElement confirmReleaseButton;

    @ElementName("'Gear' menuItems")
    @FindBy(xpath = "//*[@role='menu' and @aria-hidden='false' and @aria-expanded='true']")
    MenuItemField descriptionGearMenuItems;


    @ElementName("'Assign Existing SAP Project ID' popup")
    @FindBy(xpath = "//*[@role='dialog' and @aria-hidden='false']//*[@role='presentation' and text()='Assign Existing SAP Project ID']")
    BaseWebElement assignExistingSAPProjectIDPopup;

    @ElementName("'Select an SAP Project to assign to all WBS elements' field")
    @FindBy(xpath = "//*[@role='dialog' and @aria-hidden='false']//input[@placeholder='SAP Project' and @aria-hidden='false']")
    IpeDropdownField selectAnSAPProjectToAssignToAllWBSElementsField;

    @ElementName("'1 Overall Proposal' expander")
    @FindBy(xpath = "//*[@role='treegrid' and @aria-hidden='false']//*[@class='x-grid-item-container']//*[contains(text(),'Overall')]/ancestor::*[@role='gridcell']//*[contains(@class,'x-tree-expander')]")
    BaseWebElement overallProposalExpander;

    @ElementName("'Expand and Collapse' menuItem")
    @FindBy(xpath = "//*[@role='menu' and @aria-hidden='false' and @aria-expanded='true']//*[@role='menuitem' and @aria-hidden='false']//*[text()='Expand/Collapse']")
    BaseWebElement expandAndCollapseMenuItem;

    @ElementName("'Expand All' menuItem")
    @FindBy(xpath = "//*[@role='menu' and @aria-hidden='false' and @aria-expanded='true']//*[@role='menuitem' and @aria-hidden='false']//*[text()='Expand All']")
    BaseWebElement expandAllMenuItem;

    @ElementName("'Add Current Level' option")
    @FindBy(xpath = "//*[@role='menu' and @aria-hidden='false']//*[contains(text(),'Current Level')]")
    BaseWebElement addCurrentLevelOption;

    @ElementName("'Add Below' option")
    @FindBy(xpath = "//*[@role='menu' and @aria-hidden='false']//*[contains(text(),'Below')]")
    BaseWebElement addBelowOption;

    @ElementName("'Collapse All' menuItem")
    @FindBy(xpath = "//*[@role='menu' and @aria-hidden='false' and @aria-expanded='true']//*[@role='menuitem' and @aria-hidden='false']//*[text()='Collapse All']")
    BaseWebElement collapseAllMenuItem;

    public static final String WBS = "'WBS' table";
    @ElementName(WBS)
    @FindBy(xpath = "//div[@class='x-grid-item-container' and not(contains(@style, 'transform'))]")
    WBSTable wbsTable;

    @ElementName("'Analysis' tab")
    @FindBy(xpath = "//span[text()='Analysis' or text()='Workbench']")
    BaseWebElement analysisTab;

    @ElementName("'Status' widget")
    @FindBy(xpath = "//*[@role='tabpanel' and @aria-hidden='false']//*[contains(@class,'x-ibeLinksCls data-tile-link')]/ancestor::*[contains(@class,'x-container-default x-scroller')]")
    BaseWebElement statusWidget;

    @ElementName("'Update Cost & Prices' link")
    @FindBy(xpath = "//*[@role='tabpanel' and @aria-hidden='false']//*[@titlelink='updateCostsWithFormula' or @titlelink='updateBoeCostsWithFormula']")
    BaseWebElement updateCostPriceLink;

    @ElementName("'Status & logs' link")
    @FindBy(xpath = "//*[@role='tabpanel' and @aria-hidden='false']//*[@subtitlelink='showJobStatus']")
    TextElement statusLogsLink;

    @ElementName("'Cost & Price' status")
    @FindBy(xpath = "//*[@role='tabpanel' and @aria-hidden='false']//*[contains(@class,'x-ibeLinksCls data-tile-link')]/parent::div/div[1]")
    TextElement costPriceStatus;

    @ElementName("'Setup' tab")
    @FindBy(xpath = "//span[text()='Setup']")
    BaseWebElement setupTab;

    @ElementName("'1 Prof Services Estimate' WP")
    @FindBy(xpath = "//*[text()='1 Prof Services Estimate ']/ancestor::table//*[@data-columnid='quoteWBSTreeWkPackageColumn']//span")
    BaseWebElement profServicesEstimateWP;

    @ElementName("'Perform Copy' window")
    @FindBy(xpath = "//div[contains(@class,'x-window-header')]//div[text()='Perform Copy?']")
    BaseWebElement performCopy;

    @ElementName("'Updated' status")
    @FindBy(xpath = "//*[text()='Updated']")
    LongWaitElement updatedStatus;

    @ElementName("'Needs Refresh' status")
    @FindBy(xpath = "//*[text()='Needs Refresh']")
    LongWaitElement needsRefreshStatus;

    @ElementName("'Running' status")
    @FindBy(xpath = "//*[contains(@id,'boedetails-dataTile') and @data-ref='innerCt']//*[text()='Running' or text()='running']")
    LongWaitElement runningStatus;

    @ElementName("'Refresh' button")
    @FindBy(xpath = "//*[@data-ref='btnIconEl' and contains(@class,'fa-sync-alt ')]")
    BaseWebElement refreshButton;

    @ElementName("'No' button")
    @FindBy(xpath = "//a//span[text()='No']")
    BaseWebElement noButton;

    @ElementName("'Yes' button")
    @FindBy(xpath = "//a//span[text()='Yes']")
    BaseWebElement yesButton;

    @ElementName("'Add Row' button")
    @FindBy(xpath = "//div[@data-qtip='Add Row']")
    BaseWebElement addRowButton;

    @ElementName("'Release Basis of Estimate' window")
    @FindBy(xpath = "//div[contains(@class, 'x-window')]//*[text()='Release Basis of Estimate']")
    BaseWebElement releaseEstimateWindow;

    @ElementName("'Workbench' tab")
    @FindBy(xpath = "//span[text()='Workbench']")
    BaseWebElement workbenchTab;

    @ElementName("'Update' window")
    @FindBy(xpath = "//div[contains(@class,'x-window-header')]//div[text()='Force Update of all Costs - Important']")
    BaseWebElement updateWindow;

    @ElementName("'Total Price' widget")
    @FindBy(xpath = "//*[@role='tabpanel' and @aria-hidden='false']//*[@data-qtip='Total Price' or @data-qtip='Total Revenue']")
    TextElement totalPriceWidget;

    @ElementName("'Total Revenue' widget")
    @FindBy(xpath = "//*[@role='tabpanel' and @aria-hidden='false']//*[@data-qtip='Total Revenue']")
    TextElement totalRevenueWidget;

    @ElementName("'Total Cost' widget")
    @FindBy(xpath = "//*[@role='tabpanel' and @aria-hidden='false']//*[@data-qtip='Total cost in the WBS']")
    TextElement totalCostWidget;

    @ElementName("'Margin as Percentage' widget")
    @FindBy(xpath = "//*[@role='tabpanel' and @aria-hidden='false']//*[@data-qtip='Margin as Percentage']")
    TextElement marginAsPercentageWidget;

    @ElementName("'Margin' widget")
    @FindBy(xpath = "//*[@role='tabpanel' and @aria-hidden='false']//*[@data-qtip='Margin as Percentage']")
    TextElement marginWidget;

    @Override
    public String getLoadTriggerName() {
        return STRUCTURE_TAB;
    }
}
