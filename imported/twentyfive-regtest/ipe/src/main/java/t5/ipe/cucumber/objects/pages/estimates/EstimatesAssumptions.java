package t5.ipe.cucumber.objects.pages.estimates;


import org.openqa.selenium.support.FindBy;
import t5.ipe.cucumber.core.web.elements.BaseWebElement;
import t5.ipe.cucumber.core.web.interfaces.annotations.ElementName;
import t5.ipe.cucumber.core.web.interfaces.annotations.WebPage;
import t5.ipe.cucumber.objects.elements.IpeCogTree;
import t5.ipe.cucumber.objects.elements.IpeViewDropdownField;
import t5.ipe.cucumber.objects.elements.tables.LaborTable;
import t5.ipe.cucumber.objects.pages.MainPage;

/**
 * Estimates - Assumptions page.
 */
@WebPage(value = "Estimate Assumptions", urlTemplate = ".*#boe:[0-9A-Fa-f]{32}$|.*boe:[0-9A-Fa-f]{8}(?:-[0-9A-Fa-f]{4}){3}-[0-9A-Fa-f]{12}$", tabName = "Assumptions")
public class EstimatesAssumptions extends MainPage {
    public static final String ASSUMPTIONS_TAB = "'Assumptions' tab";

    @ElementName(ASSUMPTIONS_TAB)
    @FindBy(xpath = "//a[@aria-selected='true']//span[text()='Assumptions']")
    BaseWebElement AssumptionsTab;

    @Override
    public String getLoadTriggerName() {
        return ASSUMPTIONS_TAB;
    }
}
