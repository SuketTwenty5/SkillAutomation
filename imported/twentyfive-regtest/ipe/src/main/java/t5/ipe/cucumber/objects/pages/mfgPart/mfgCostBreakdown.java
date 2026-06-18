package t5.ipe.cucumber.objects.pages.mfgPart;

import t5.ipe.cucumber.core.web.interfaces.annotations.WebPage;
import t5.ipe.cucumber.objects.pages.purchasedParts.costBreakdown;

@WebPage(value = "Mfg Part: Estimate - Cost Breakdown", urlTemplate = ".*#boe:[0-9A-Fa-f]{32}$|.*boe:[0-9A-Fa-f]{8}(?:-[0-9A-Fa-f]{4}){3}-[0-9A-Fa-f]{12}$", tabName = "Cost Breakdown")

public class mfgCostBreakdown extends costBreakdown {
}
