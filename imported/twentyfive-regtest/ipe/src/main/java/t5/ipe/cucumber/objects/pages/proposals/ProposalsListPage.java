package t5.ipe.cucumber.objects.pages.proposals;


import com.codeborne.selenide.Condition;
import org.openqa.selenium.support.FindBy;
import t5.ipe.cucumber.core.web.elements.BaseWebElement;
import t5.ipe.cucumber.core.web.elements.LongWaitElement;
import t5.ipe.cucumber.core.web.interfaces.annotations.ElementName;
import t5.ipe.cucumber.core.web.interfaces.annotations.WebPage;
import t5.ipe.cucumber.core.web.util.AllureUtils;
import t5.ipe.cucumber.objects.pages.MainPage;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;
import static t5.ipe.cucumber.core.web.util.web.WebUtil.setCustomTimeoutSec;
import static t5.ipe.cucumber.core.web.util.web.WebUtil.setDefaultTimeout;

/**
 * Proposals list page - Access via Main Page's top toolbar's Proposals tab, this page presents a list of proposals.
 * Created by: Ekruze
 * Date: 07/11/2023
 */
@WebPage(value = "Proposals list page", urlTemplate = "\\/$|$|.*quote")
public class ProposalsListPage extends MainPage {
    public static final String PROPOSALS_LABEL = "'Title' toolbar";
    public static final String PROPOSALS_LIST = "'Proposals' list";

    @ElementName(PROPOSALS_LABEL)
    @FindBy(xpath = "//label[text()='Proposals ']")
    BaseWebElement proposalsLabel;

    @ElementName(PROPOSALS_LIST)
    @FindBy(xpath = "//*[@role='rowgroup' and not(contains(@class,'x-grid-header-ct')) and not(@aria-busy='true')]")
    LongWaitElement proposalsList;

    @ElementName("'first' proposal")
    @FindBy(xpath = "//*[(@role='grid' or @role='treegrid') and @aria-hidden='false']//*[@data-ref='body']//*[@role='rowgroup']//*[@class='x-grid-item-container']//table[1]//*[@data-columnid='quoteSearchText']//*[contains(@class,'x-grid-cell-inner') and text()]")
    BaseWebElement firstProposal;

    @Override
    public String getLoadTriggerName() {
        return PROPOSALS_LIST;
    }

    @Override
    public void checkIfPageLoaded() {
        setCustomTimeoutSec(15);

        if (isSpinnerDisplayed()) {
            handleVisibleSpinner();
        }

        setDefaultTimeout();
        super.checkIfPageLoaded();
    }

    private boolean isSpinnerDisplayed() {
        try {
            $x("//*[contains(text(), 'Loading') and @class='x-mask-msg-text']").shouldBe(visible);
            return true;
        } catch (AssertionError ae) {
            AllureUtils.logAction("Spinner is not displayed");
            return false;
        }
    }

    private void handleVisibleSpinner() {
        try {
            $x("//*[contains(text(), 'Loading') and @class='x-mask-msg-text']").shouldNotBe(Condition.visible, Duration.ofSeconds(100));
        } catch (AssertionError ae) {
            AllureUtils.logAction("Spinner is displayed");
            $x("//*[contains(text(), 'Loading') and @class='x-mask-msg-text']").shouldNotBe(Condition.visible, Duration.ofSeconds(100));
        }
    }

}
