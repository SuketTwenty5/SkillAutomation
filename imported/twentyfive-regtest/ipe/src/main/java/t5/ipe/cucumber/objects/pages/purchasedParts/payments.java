package t5.ipe.cucumber.objects.pages.purchasedParts;

import org.openqa.selenium.support.FindBy;
import t5.ipe.cucumber.core.web.elements.BaseWebElement;
import t5.ipe.cucumber.core.web.interfaces.annotations.ElementName;
import t5.ipe.cucumber.core.web.interfaces.annotations.WebPage;
import t5.ipe.cucumber.objects.pages.estimates.EstimatesProposalBOM;

@WebPage(value = "Purchased Part: Estimate - Payments", urlTemplate = ".*#boe:[0-9A-Fa-f]{32}$|.*boe:[0-9A-Fa-f]{8}(?:-[0-9A-Fa-f]{4}){3}-[0-9A-Fa-f]{12}$", tabName = "Payments")


public class payments extends EstimatesProposalBOM {
    public static final String PAYMENTS_TABLE = "'Payments' grid";

    @ElementName(PAYMENTS_TABLE)
    @FindBy(xpath = "//*[@role='tab' and @aria-hidden='false' and @aria-selected='true']//*[text()='Payments']/ancestor::*[@role='dialog' and @aria-hidden='false']//*[@role='tabpanel' and @aria-hidden='false']//*[@role='grid' and @aria-hidden='false']")
    BaseWebElement quantitiesFieldSet;

    @Override
    public String getLoadTriggerName() {
        return PAYMENTS_TABLE;
    }
}
