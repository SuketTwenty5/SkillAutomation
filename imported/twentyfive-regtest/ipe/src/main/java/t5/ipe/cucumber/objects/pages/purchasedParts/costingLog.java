package t5.ipe.cucumber.objects.pages.purchasedParts;

import org.openqa.selenium.support.FindBy;
import t5.ipe.cucumber.core.web.elements.BaseWebElement;
import t5.ipe.cucumber.core.web.interfaces.annotations.ElementName;
import t5.ipe.cucumber.core.web.interfaces.annotations.WebPage;
import t5.ipe.cucumber.objects.pages.estimates.EstimatesProposalBOM;

@WebPage(value = "Purchased Part: Estimate - Costing Log", urlTemplate = ".*#boe:[0-9A-Fa-f]{32}$|.*boe:[0-9A-Fa-f]{8}(?:-[0-9A-Fa-f]{4}){3}-[0-9A-Fa-f]{12}$", tabName = "Costing Log")

public class costingLog extends EstimatesProposalBOM {
    public static final String COSTING_LOG_TEXT_AREA = "'Costing Log' textArea";

    @ElementName(COSTING_LOG_TEXT_AREA)
    @FindBy(xpath = "//*[@role='tab' and @aria-hidden='false' and @aria-selected='true']//*[text()='Costing Log']/ancestor::*[@role='dialog' and @aria-hidden='false']//*[@role='tabpanel' and @aria-hidden='false']//*[@role='textbox' and @aria-hidden='false' and @aria-readonly='true']")
    BaseWebElement costingLogTextArea;

    @Override
    public String getLoadTriggerName() {
        return COSTING_LOG_TEXT_AREA;}
}
