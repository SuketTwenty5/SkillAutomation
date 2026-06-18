package t5.ipe.cucumber.objects.pages.estimates;

import org.openqa.selenium.support.FindBy;
import t5.ipe.cucumber.core.web.elements.BaseWebElement;
import t5.ipe.cucumber.core.web.interfaces.annotations.ElementName;
import t5.ipe.cucumber.core.web.interfaces.annotations.WebPage;
import t5.ipe.cucumber.objects.elements.MenuItemField;
import t5.ipe.cucumber.objects.elements.tables.CommonDualTable;
import t5.ipe.cucumber.objects.elements.tables.LaborTable;
import t5.ipe.cucumber.objects.pages.MainPage;

@WebPage(value = "Estimate Other", urlTemplate = ".*#boe:[0-9A-Fa-f]{32}$|.*boe:[0-9A-Fa-f]{8}(?:-[0-9A-Fa-f]{4}){3}-[0-9A-Fa-f]{12}$", tabName = "Other")
public class EstimatesOther extends MainPage {
    public static final String OTHER_TAB = "'Other' tab";

    @ElementName(OTHER_TAB)
    @FindBy(xpath = "//a[@aria-selected='true']//span[text()='Other']")
    BaseWebElement costStructureTab;

    @ElementName("'Proposal BOM' tab")
    @FindBy(xpath = "//a[@role='tab' and @aria-hidden='false']//span[text()='Proposal BOM' or text()='Bom']")
    BaseWebElement proposalBOMTab;

    @ElementName("'Other' table")
    @FindBy(xpath = "(//div[not(contains(@class,'x-hidden-offsets')) and (contains (@class, 'x-panel x-tabpanel-child'))]//div[@class='x-grid-item-container'])[2]")
    CommonDualTable otherTable;

    @ElementName("'Opened' menu")
    @FindBy(xpath = "//*[@data-ref='listEl' and @role='listbox' and @aria-hidden='false']")
    MenuItemField openedMenu;

    @Override
    public String getLoadTriggerName() {
        return OTHER_TAB;
    }
}
