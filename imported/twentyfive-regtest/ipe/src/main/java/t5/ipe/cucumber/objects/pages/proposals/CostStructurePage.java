package t5.ipe.cucumber.objects.pages.proposals;

import org.openqa.selenium.support.FindBy;
import t5.ipe.cucumber.core.web.elements.BaseWebElement;
import t5.ipe.cucumber.core.web.interfaces.annotations.ElementName;
import t5.ipe.cucumber.core.web.interfaces.annotations.WebPage;
import t5.ipe.cucumber.objects.elements.IpeDropdownField;
import t5.ipe.cucumber.objects.elements.MenuItemField;
import t5.ipe.cucumber.objects.elements.tables.WBSTable;

@WebPage(value = "Cost Structure page", urlTemplate = ".*#quote:new:type=PS_consulting$|.*#quote:new:type=PS_consulting_simple$|.*#proposal:new:type=PS_consulting_simple$|.*#proposal:new:type=DI_service$|.*proposal:[0-9A-Fa-f]{8}(?:-[0-9A-Fa-f]{4}){3}-[0-9A-Fa-f]{12}$|.*quote:[0-9A-Fa-f]{8}(?:-[0-9A-Fa-f]{4}){3}-[0-9A-Fa-f]{12}$", tabName = "WBS Cost Structure")

public class CostStructurePage extends WBSPage{
    public static final String COST_STRUCTURE_TAB = "'Cost Structure' tab";

    @ElementName(COST_STRUCTURE_TAB)
    @FindBy(xpath = "//a[@aria-selected='true']//span[contains(text(),'Cost Structure')]")
    BaseWebElement costStructureTab;

    @ElementName("'Gear' menuItems")
    @FindBy(xpath = "//*[@role='menu' and @aria-hidden='false' and @aria-expanded='true']")
    MenuItemField descriptionGearMenuItems;

    @ElementName("'Close' button")
    @FindBy(xpath = "//*[@role='dialog' and not(contains(@class,'x-window-ghost')) and @aria-hidden='false']//*[text()='Close']/ancestor::*[@role='button' and @aria-hidden='false']")
    BaseWebElement closeButton;

    @ElementName("'Assign Existing SAP Project ID' popup")
    @FindBy(xpath = "//*[@role='dialog' and @aria-hidden='false']//*[@role='presentation' and text()='Assign Existing SAP Project ID']")
    BaseWebElement assignExistingSAPProjectIDPopup;

    @ElementName("'WBS Cost Structure' table")
    @FindBy(xpath = "//*[@role='treegrid' and @aria-hidden='false']//*[@class='x-grid-item-container']")
    WBSTable wbsCostStructureTable;

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

    @ElementName("'Collapse All' menuItem")
    @FindBy(xpath = "//*[@role='menu' and @aria-hidden='false' and @aria-expanded='true']//*[@role='menuitem' and @aria-hidden='false']//*[text()='Collapse All']")
    BaseWebElement collapseAllMenuItem;

//    @ElementName("'Add (Below)' option")
//    @FindBy(xpath = "//*[@role='menu' and @aria-hidden='false']//*[contains(text(),'Below')]")
//    BaseWebElement addBelowOption;

    @Override
    public String getLoadTriggerName() {
        return COST_STRUCTURE_TAB;
    }
}
