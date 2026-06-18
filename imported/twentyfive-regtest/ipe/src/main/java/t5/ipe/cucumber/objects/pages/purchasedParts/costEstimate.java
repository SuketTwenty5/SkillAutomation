package t5.ipe.cucumber.objects.pages.purchasedParts;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import org.openqa.selenium.support.FindBy;
import t5.ipe.cucumber.core.web.elements.BaseWebElement;
import t5.ipe.cucumber.core.web.interfaces.annotations.ElementName;
import t5.ipe.cucumber.core.web.interfaces.annotations.WebPage;
import t5.ipe.cucumber.core.web.interfaces.element_interfaces.Visible;
import t5.ipe.cucumber.core.web.util.AllureUtils;
import t5.ipe.cucumber.core.web.util.web.SearchUtils;
import t5.ipe.cucumber.objects.elements.tables.CommonDualTable;
import t5.ipe.cucumber.objects.elements.tables.CostEstimateTable;
import t5.ipe.cucumber.objects.elements.tables.LaborAmortizationTable;
import t5.ipe.cucumber.objects.elements.tables.WBSTable;
import t5.ipe.cucumber.objects.pages.MainPage;
import t5.ipe.cucumber.objects.pages.estimates.EstimatesProposalBOM;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;
import static t5.ipe.cucumber.core.web.util.web.WebUtil.setCustomTimeoutSec;
import static t5.ipe.cucumber.core.web.util.web.WebUtil.setDefaultTimeout;

@WebPage(value = "Purchased Part: Estimate - Cost Estimate", urlTemplate = ".*#boe:[0-9A-Fa-f]{32}$|.*boe:[0-9A-Fa-f]{8}(?:-[0-9A-Fa-f]{4}){3}-[0-9A-Fa-f]{12}$", tabName = "Cost Estimate")

public class costEstimate extends EstimatesProposalBOM {

    public static final String COST_ESTIMATE_TABLE = "'Cost Estimate' table";

    @ElementName(COST_ESTIMATE_TABLE)
    @FindBy(xpath = "(//div[not(contains(@class,'x-hidden-offsets')) and (contains (@class, 'x-panel x-tabpanel-child'))]//div[@class='x-grid-item-container'])")
    CostEstimateTable costEstimateTable;

    @Override
    public void checkIfPageLoaded() {
        String pageName = getPageName();
        String elementName = getLoadTriggerName();
        Visible element = SearchUtils.getPageByName(pageName).findElementByTitle(elementName, Visible.class);
        try {
            element.waitUntilBecomesVisible();
        } catch (AssertionError nse) {
            Selenide.refresh();
            try {
                element.waitUntilBecomesVisible();
            } catch (AssertionError nse2){
                String message = String.format(
                        "❌ Page [%s] was not loaded. Unable to find [%s] by locator: %s",
                        pageName,
                        elementName,
                        ((BaseWebElement) element).getFindBy().xpath()
                );
                throw new RuntimeException(message, nse2);
            }
        }
        setCustomTimeoutSec(30);
        if (isSpinnerDisplayed()) {
            handleVisibleSpinner();
        }
        setDefaultTimeout();
    }

    private boolean isSpinnerDisplayed() {
        try {
            $x("//*[@role='progressbar' and @aria-hidden='false']//*[@class='x-mask-msg-text']").shouldBe(visible,Duration.ofSeconds(7));
            return true;
        } catch (AssertionError ae) {
            AllureUtils.logAction("Spinner is not displayed");
            return false;
        }
    }

    private void handleVisibleSpinner() {
        try {
            $x("//*[@role='progressbar' and @aria-hidden='false']//*[@class='x-mask-msg-text']").shouldNotBe(Condition.visible, Duration.ofSeconds(30));
        } catch (AssertionError ae) {
            AllureUtils.logAction("Spinner is displayed");
            handleReload();
            $x("//*[@role='progressbar' and @aria-hidden='false']//*[@class='x-mask-msg-text']").shouldNotBe(Condition.visible, Duration.ofSeconds(30));
        }
    }
    private void handleReload() {
        $x("//a[@data-qtip='Reload']").shouldBe(visible, Duration.ofSeconds(25)).click();
    }

    @Override
    public String getLoadTriggerName() {
        return COST_ESTIMATE_TABLE;
    }
}
