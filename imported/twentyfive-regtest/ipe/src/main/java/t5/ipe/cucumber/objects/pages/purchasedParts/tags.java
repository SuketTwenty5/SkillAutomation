package t5.ipe.cucumber.objects.pages.purchasedParts;

import org.openqa.selenium.support.FindBy;
import t5.ipe.cucumber.core.web.elements.BaseWebElement;
import t5.ipe.cucumber.core.web.interfaces.annotations.ElementName;
import t5.ipe.cucumber.core.web.interfaces.annotations.WebPage;
import t5.ipe.cucumber.objects.pages.estimates.EstimatesProposalBOM;

@WebPage(value = "Purchased Part: Estimate - Tags", urlTemplate = ".*#boe:[0-9A-Fa-f]{32}$|.*boe:[0-9A-Fa-f]{8}(?:-[0-9A-Fa-f]{4}){3}-[0-9A-Fa-f]{12}$", tabName = "Tags")

public class tags extends EstimatesProposalBOM {
    public static final String TAGS_FIELD_SET = "'Tags' fieldSet";

    @ElementName(TAGS_FIELD_SET)
    @FindBy(xpath = "//*[@role='tab' and @aria-hidden='false' and @aria-selected='true']//*[text()='Tags']/ancestor::*[@role='dialog' and @aria-hidden='false']//*[@role='tabpanel' and @aria-hidden='false']//*[@role='group' and @aria-label='Tags field set']")
    BaseWebElement quantitiesFieldSet;

    @Override
    public String getLoadTriggerName() {
        return TAGS_FIELD_SET;
    }
}
