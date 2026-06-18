package t5.ipe.cucumber.objects.pages.purchasedParts;

import org.openqa.selenium.support.FindBy;
import t5.ipe.cucumber.core.web.elements.BaseWebElement;
import t5.ipe.cucumber.core.web.interfaces.annotations.ElementName;
import t5.ipe.cucumber.core.web.interfaces.annotations.WebPage;
import t5.ipe.cucumber.objects.elements.tables.WBSTable;
import t5.ipe.cucumber.objects.pages.estimates.EstimatesProposalBOM;

@WebPage(value = "Purchased Part: Estimate - Proposal Requirements", urlTemplate = ".*#boe:[0-9A-Fa-f]{32}$|.*boe:[0-9A-Fa-f]{8}(?:-[0-9A-Fa-f]{4}){3}-[0-9A-Fa-f]{12}$", tabName = "Proposal Requirements")


public class proposalRequirements extends EstimatesProposalBOM {
    public static final String PROPOSAL_REQUIREMENTS_TABLE = "'Proposal Requirements' table";

    @ElementName(PROPOSAL_REQUIREMENTS_TABLE)
    @FindBy(xpath = "//*[@role='tab' and @aria-hidden='false' and @aria-selected='true']//*[text()='Proposal Requirements']/ancestor::*[@role='dialog' and @aria-hidden='false']//*[@role='tabpanel' and @aria-hidden='false']//*[@role='grid' and @aria-hidden='false']")
    WBSTable quantitiesFieldSet;

    @Override
    public String getLoadTriggerName() {
        return PROPOSAL_REQUIREMENTS_TABLE;
    }
}
