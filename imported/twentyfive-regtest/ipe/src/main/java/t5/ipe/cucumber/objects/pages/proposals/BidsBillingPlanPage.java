package t5.ipe.cucumber.objects.pages.proposals;


import org.openqa.selenium.support.FindBy;
import t5.ipe.cucumber.core.web.elements.BaseWebElement;
import t5.ipe.cucumber.core.web.interfaces.annotations.ElementName;
import t5.ipe.cucumber.core.web.interfaces.annotations.WebPage;
import t5.ipe.cucumber.objects.elements.tables.ContractLinesTable;
import t5.ipe.cucumber.objects.pages.MainPage;

/**
 * Bids Billings Plan page.
 */
@WebPage(value = "Billing page", urlTemplate = ".*quote:[0-9A-Fa-f]{8}(?:-[0-9A-Fa-f]{4}){3}-[0-9A-Fa-f]{12}$", tabName = "Billing")
public class BidsBillingPlanPage extends MainPage {
    public static final String BILLING_PLAN = "'Billing' tab";

    @ElementName(BILLING_PLAN)
    @FindBy(xpath = "//a[@aria-selected='true']//span[contains(text(),'Billing')]")
    BaseWebElement billingPlanTab;

    @ElementName("'Billing Plan' table")
    @FindBy(xpath = "//div[not(contains(@class,'x-hidden-offsets')) and (contains (@class, 'x-panel x-box-item'))]//div[@class='x-grid-item-container']")
    ContractLinesTable billingPlanTable;

    @ElementName("'Reprice' button")
    @FindBy(xpath = "//span[text()='Re-price']")
    BaseWebElement repriceButton;

    @Override
    public String getLoadTriggerName() {
        return BILLING_PLAN;
    }

}
