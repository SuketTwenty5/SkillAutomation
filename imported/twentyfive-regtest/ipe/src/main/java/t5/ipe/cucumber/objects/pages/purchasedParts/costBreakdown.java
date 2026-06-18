package t5.ipe.cucumber.objects.pages.purchasedParts;

import org.openqa.selenium.support.FindBy;
import t5.ipe.cucumber.core.web.interfaces.annotations.ElementName;
import t5.ipe.cucumber.core.web.interfaces.annotations.WebPage;
import t5.ipe.cucumber.objects.elements.tables.CostBreakDownTable;
import t5.ipe.cucumber.objects.elements.tables.CostEstimateTable;

@WebPage(value = "Purchased Part: Estimate - Cost Breakdown", urlTemplate = ".*#boe:[0-9A-Fa-f]{32}$|.*boe:[0-9A-Fa-f]{8}(?:-[0-9A-Fa-f]{4}){3}-[0-9A-Fa-f]{12}$", tabName = "Cost Breakdown")

public class costBreakdown extends costEstimate{
    public static final String COST_BREAKDOWN_TABLE = "'Cost Breakdown' table";

    @ElementName(COST_BREAKDOWN_TABLE)
    @FindBy(xpath = "(//div[not(contains(@class,'x-hidden-offsets')) and (contains (@class, 'x-panel x-tabpanel-child'))]//div[@class='x-grid-item-container'])")
    CostBreakDownTable costBreakdownTable23;

    @Override
    public String getLoadTriggerName() {
        return COST_BREAKDOWN_TABLE;
    }
}
