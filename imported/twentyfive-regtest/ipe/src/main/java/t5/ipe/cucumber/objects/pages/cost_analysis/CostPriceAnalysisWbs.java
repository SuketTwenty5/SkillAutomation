package t5.ipe.cucumber.objects.pages.cost_analysis;


import org.openqa.selenium.support.FindBy;
import t5.ipe.cucumber.core.web.elements.BaseWebElement;
import t5.ipe.cucumber.core.web.interfaces.annotations.ElementName;
import t5.ipe.cucumber.core.web.interfaces.annotations.WebPage;
import t5.ipe.cucumber.objects.elements.WorkbenchDropButton;
import t5.ipe.cucumber.objects.pages.MainPage;

/**
 * Cost Price Analysis - Wbs page.
 */
@WebPage(value = "Cost Price Analysis Wbs page", urlTemplate = ".*#costing:[0-9A-Fa-f]{8}(?:-[0-9A-Fa-f]{4}){3}-[0-9A-Fa-f]{12}$", tabName = "WBS")
public class CostPriceAnalysisWbs extends MainPage {
    public static final String WBS_TAB = "'Cost Price Analysis Wbs' tab";

    @ElementName(WBS_TAB)
    @FindBy(xpath = "//a[@aria-selected='true']//span[contains(text(),'WBS')]")
    BaseWebElement wbsTab;

    @ElementName("'Workbench' tab")
    @FindBy(xpath = "//span[text()='Workbench' or text()='Analysis']")
    BaseWebElement workbenchTab;

    @ElementName("'Analysis' tab")
    @FindBy(xpath = "//span[text()='Analysis']")
    BaseWebElement analysisTab;

    @ElementName("'Cog Settings' button")
    @FindBy(xpath = "//div[not(contains(@class,'x-hidden-offsets')) and (contains (@class, 'x-tabpanel-child'))]//a[@data-qtip='Settings']")
    WorkbenchDropButton cogMenu;

    @Override
    public String getLoadTriggerName() {
        return WBS_TAB;
    }

}
