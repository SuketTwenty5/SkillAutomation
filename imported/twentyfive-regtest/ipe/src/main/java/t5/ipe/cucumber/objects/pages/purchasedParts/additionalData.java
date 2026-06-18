package t5.ipe.cucumber.objects.pages.purchasedParts;
import org.openqa.selenium.support.FindBy;
import t5.ipe.cucumber.core.web.elements.BaseWebElement;
import t5.ipe.cucumber.core.web.interfaces.annotations.ElementName;
import t5.ipe.cucumber.core.web.interfaces.annotations.WebPage;
import t5.ipe.cucumber.objects.pages.MainPage;
import t5.ipe.cucumber.objects.pages.estimates.EstimatesProposalBOM;

@WebPage(value = "Purchased Part: Estimate - Additional Data", urlTemplate = ".*#boe:[0-9A-Fa-f]{32}$|.*boe:[0-9A-Fa-f]{8}(?:-[0-9A-Fa-f]{4}){3}-[0-9A-Fa-f]{12}$", tabName = "Additional Data")

public class additionalData extends EstimatesProposalBOM {
    public static final String QUANTITIES_FIELD_SET = "'Quantities' fieldSet";

    @ElementName(QUANTITIES_FIELD_SET)
    @FindBy(xpath = "//*[@role='dialog' and @aria-hidden='false']//*[@role='tabpanel' and @aria-hidden='false']//*[@aria-label='Quantities field set' and @aria-hidden='false']")
    BaseWebElement quantitiesFieldSet;

    @Override
    public String getLoadTriggerName() {
        return QUANTITIES_FIELD_SET;
    }
}
