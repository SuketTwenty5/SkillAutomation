package t5.ipe.cucumber.objects.pages.estimates;


import org.openqa.selenium.support.FindBy;
import t5.ipe.cucumber.core.web.elements.BaseWebElement;
import t5.ipe.cucumber.core.web.interfaces.annotations.ElementName;
import t5.ipe.cucumber.core.web.interfaces.annotations.WebPage;
import t5.ipe.cucumber.objects.elements.IpeViewDropdownField;
import t5.ipe.cucumber.objects.elements.tables.ServiceTable;
import t5.ipe.cucumber.objects.pages.MainPage;

/**
 * Estimates - Hardware Software page.
 */
@WebPage(value = "Estimate Hardware Software", urlTemplate = ".*#boe:[0-9A-Fa-f]{32}$|.*boe:[0-9A-Fa-f]{8}(?:-[0-9A-Fa-f]{4}){3}-[0-9A-Fa-f]{12}$", tabName = "Hardware & Software")
public class EstimatesHardwareSoftware extends MainPage {
    public static final String SERVICE_TAB = "'Service' tab";

    @ElementName(SERVICE_TAB)
    @FindBy(xpath = "//a[@aria-selected='true']//span[text()='Hardware & Software']")
    BaseWebElement hwSwTab;

    @ElementName("'View' dropdown")
    @FindBy(xpath = "//div[not(contains(@class,'x-hidden-offsets')) and (contains (@class, 'x-tabpanel-child'))]//a[contains(@class, 'x-split-button')][@aria-hidden='false']//span[contains(text(), 'View')]")
    IpeViewDropdownField viewDropdown;

    @ElementName("'Click here to add' link")
    @FindBy(xpath = "(//div[contains(@class, 'x-panel x-tabpanel-child')]//a[text()='click here to add'])[last()]")
    BaseWebElement addLink;

    @ElementName("'Service' table")
    @FindBy(xpath = "(//div[not(contains(@class,'x-hidden-offsets')) and (contains (@class, 'x-panel x-tabpanel-child'))]//div[@class='x-grid-item-container'])[2]")
    ServiceTable laborTable;

    @Override
    public String getLoadTriggerName() {
        return SERVICE_TAB;
    }
}
