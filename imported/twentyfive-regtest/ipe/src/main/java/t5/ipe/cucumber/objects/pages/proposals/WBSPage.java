package t5.ipe.cucumber.objects.pages.proposals;


import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import org.openqa.selenium.support.FindBy;
import t5.ipe.cucumber.core.web.elements.BaseWebElement;
import t5.ipe.cucumber.core.web.elements.LongWaitElement;
import t5.ipe.cucumber.core.web.elements.TextElement;
import t5.ipe.cucumber.core.web.interfaces.annotations.ElementName;
import t5.ipe.cucumber.core.web.interfaces.annotations.WebPage;
import t5.ipe.cucumber.core.web.util.AllureUtils;
import t5.ipe.cucumber.objects.elements.CogMenu;
import t5.ipe.cucumber.objects.elements.IpeDropButton;
import t5.ipe.cucumber.objects.elements.SimpleTree;
import t5.ipe.cucumber.objects.elements.tables.WBSTable;
import t5.ipe.cucumber.objects.pages.MainPage;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;
import static t5.ipe.cucumber.core.web.util.web.WebUtil.setCustomTimeoutSec;
import static t5.ipe.cucumber.core.web.util.web.WebUtil.setDefaultTimeout;

/**
 * Proposal WBS page.
 */
@WebPage(value = "WBS page", urlTemplate = ".*quote:[0-9A-Fa-f]{8}(?:-[0-9A-Fa-f]{4}){3}-[0-9A-Fa-f]{12}$", tabName = "Workstreams")

public class WBSPage extends MainPage {
    public static final String WBS_TAB = "'WBS' tab";

    @ElementName(WBS_TAB)
    @FindBy(xpath = "//a//span[text()='WBS Structure' or contains(text(),'Cost Structure') or contains(text(),'Workstreams')]")
    BaseWebElement wbsTab;

    @ElementName("'Analysis' tab")
    @FindBy(xpath = "//span[text()='Analysis' or text()='Workbench']")
    BaseWebElement analysisTab;

    @ElementName("'Status' widget")
    @FindBy(xpath = "//*[@role='tabpanel' and @aria-hidden='false']//*[contains(@class,'x-ibeLinksCls data-tile-link')]/ancestor::*[contains(@class,'x-container-default x-scroller')]")
    BaseWebElement statusWidget;

    @ElementName("'Update Cost & Prices' link")
    @FindBy(xpath = "//*[@role='tabpanel' and @aria-hidden='false']//*[@titlelink='updateCostsWithFormula' or @titlelink='updateBoeCostsWithFormula']")
    BaseWebElement updateCostPriceLink;

    @ElementName("'Status & logs' link")
    @FindBy(xpath = "//*[@role='tabpanel' and @aria-hidden='false']//*[@subtitlelink='showJobStatus']")
    TextElement statusLogsLink;

    @ElementName("'Cost & Price' status")
    @FindBy(xpath = "//*[@role='tabpanel' and @aria-hidden='false']//*[contains(@class,'x-ibeLinksCls data-tile-link')]/parent::div/div[1]")
    TextElement costPriceStatus;

    @ElementName("'Setup' tab")
    @FindBy(xpath = "//span[text()='Setup']")
    BaseWebElement setupTab;

    @ElementName("'1 Prof Services Estimate' WP")
    @FindBy(xpath = "//*[text()='1 Prof Services Estimate ']/ancestor::table//*[@data-columnid='quoteWBSTreeWkPackageColumn']//span")
    BaseWebElement profServicesEstimateWP;

    @ElementName("'Perform Copy' window")
    @FindBy(xpath = "//div[contains(@class,'x-window-header')]//div[text()='Perform Copy?']")
    BaseWebElement performCopy;

    @ElementName("'Updated' status")
    @FindBy(xpath = "//*[text()='Updated']")
    LongWaitElement updatedStatus;

    @ElementName("'Needs Refresh' status")
    @FindBy(xpath = "//*[text()='Needs Refresh']")
    LongWaitElement needsRefreshStatus;

    @ElementName("'Running' status")
    @FindBy(xpath = "//*[contains(@id,'boedetails-dataTile') and @data-ref='innerCt']//*[text()='Running' or text()='running']")
    LongWaitElement runningStatus;

    @ElementName("'Refresh' button")
    @FindBy(xpath = "//*[@data-ref='btnIconEl' and contains(@class,'fa-sync-alt ')]")
    BaseWebElement refreshButton;

    @ElementName("'Add Below' option")
    @FindBy(xpath = "//*[@role='menu' and @aria-hidden='false']//*[contains(text(),'Below')]")
    BaseWebElement addBelowOption;

    @ElementName("'Add Current Level' option")
    @FindBy(xpath = "//*[@role='menu' and @aria-hidden='false']//*[contains(text(),'Current Level')]")
    BaseWebElement addCurrentLevelOption;

    @ElementName("'No' button")
    @FindBy(xpath = "//a//span[text()='No']")
    BaseWebElement noButton;

    @ElementName("'Yes' button")
    @FindBy(xpath = "//a//span[text()='Yes']")
    BaseWebElement yesButton;

    @ElementName("'Add Row' button")
    @FindBy(xpath = "//div[@data-qtip='Add Row']")
    BaseWebElement addRowButton;

    @ElementName("'WBS' tree")
    @FindBy(xpath = "//div[contains(@class,'x-tree-view x-fit-item x-tree-view-default x-scroller')]")
    SimpleTree wbsTree;

    @ElementName("'Cog' menu")
    @FindBy(xpath = "//table[contains(@class, 'x-grid-item-selected')]//span[@class='x-tree-node-text ']//div[contains(@class, 'ibeCallOut')]")
    CogMenu cogMenu;

    @ElementName("'Release Basis of Estimate' window")
    @FindBy(xpath = "//div[contains(@class, 'x-window')]//*[text()='Release Basis of Estimate']")
    BaseWebElement releaseEstimateWindow;

    @ElementName("'Workbench' tab")
    @FindBy(xpath = "//span[text()='Workbench']")
    BaseWebElement workbenchTab;

    @ElementName("'Update' window")
    @FindBy(xpath = "//div[contains(@class,'x-window-header')]//div[text()='Force Update of all Costs - Important']")
    BaseWebElement updateWindow;

    public static final String WBS = "'WBS' table";
    @ElementName(WBS)
    @FindBy(xpath = "//div[@class='x-grid-item-container' and not(contains(@style, 'transform'))]")
    WBSTable wbsTable;

    @ElementName("'Owner' field")
    @FindBy(xpath = "//span[text()='Owner *']//following::div[4]")
    IpeDropButton ownerField;

    @ElementName("'Confirm & Open' button")
    @FindBy(xpath = "//span[text()='Confirm & Open']")
    BaseWebElement confirmOpenButton;

    @ElementName("'Estimate' popUp")
    @FindBy(xpath = "//*[text()='Estimate']/ancestor::*[@role='dialog' and @aria-hidden='false']")
    BaseWebElement estimatePopUp;

    @ElementName("'Confirm & Release' button")
    @FindBy(xpath = "//span[text()='Confirm & Release']")
    BaseWebElement confirmReleaseButton;

    @ElementName("'Total Price' widget")
    @FindBy(xpath = "//*[@role='tabpanel' and @aria-hidden='false']//*[@data-qtip='Total Price' or @data-qtip='Total Revenue']")
    TextElement totalPriceWidget;

    @ElementName("'Total Revenue' widget")
    @FindBy(xpath = "//*[@role='tabpanel' and @aria-hidden='false']//*[@data-qtip='Total Revenue']")
    TextElement totalRevenueWidget;

    @ElementName("'Total Cost' widget")
    @FindBy(xpath = "//*[@role='tabpanel' and @aria-hidden='false']//*[@data-qtip='Total cost in the WBS']")
    TextElement totalCostWidget;

    @ElementName("'Margin as Percentage' widget")
    @FindBy(xpath = "//*[@role='tabpanel' and @aria-hidden='false']//*[@data-qtip='Margin as Percentage']")
    TextElement marginAsPercentageWidget;

    @ElementName("'Margin' widget")
    @FindBy(xpath = "//*[@role='tabpanel' and @aria-hidden='false']//*[@data-qtip='Margin as Percentage']")
    TextElement marginWidget;

    @Override
    public String getLoadTriggerName() {
        return WBS_TAB;
    }

    @Override
    public void checkIfPageLoaded() {
//        setCustomTimeoutSec(15);

        if (isSpinnerDisplayed()) {
            handleVisibleSpinner();
            if (isNoLoadingDisplayed()){
                handleNoLoading();
            }
        }

//        setDefaultTimeout();
        super.checkIfPageLoaded();
    }

    private boolean isNoLoadingDisplayed() {
        try {
            $x("//*[@role='alertdialog' and @aria-hidden='false']//*[@data-ref='textEl']").shouldBe(visible, Duration.ofSeconds(10));
            return true;
        } catch (AssertionError ae) {
            AllureUtils.logAction("No Loading not displayed");
            return false;
        }
    }

    private void handleNoLoading() {
        try {
            $$x("//*[@role='alertdialog']")
                    .filter(Condition.visible)
                    .shouldBe(CollectionCondition.size(0), Duration.ofSeconds(150));
        } catch (AssertionError ae) {
            AllureUtils.logAction("No Loading is displayed for more than 150 seconds");
//            $x("//*[@role='alertdialog' and @aria-hidden='false']//*[@data-ref='textEl']").shouldNotBe(Condition.visible, Duration.ofSeconds(100));
        }
    }


    private boolean isSpinnerDisplayed() {
        try {
            $x("//*[@role='progressbar' and @aria-hidden='false' and contains(@class,'x-component')]//*[@data-ref='msgTextEl']").shouldBe(visible, Duration.ofSeconds(10));
            return true;
        } catch (AssertionError ae) {
            AllureUtils.logAction("Spinner is not displayed");
            return false;
        }
    }

    private void handleVisibleSpinner() {
        try {
            $$x("//*[@role='progressbar' and contains(@class,'x-component')]")
                    .filter(Condition.visible)
                    .shouldBe(CollectionCondition.size(0), Duration.ofSeconds(150));
        } catch (AssertionError ae) {
            AllureUtils.logAction("Spinner is displayedEcen After 150 Seconds");
//            $x("//*[@role='progressbar' and @aria-hidden='false' and contains(@class,'x-component')]//*[@data-ref='msgTextEl']").shouldNotBe(Condition.visible, Duration.ofSeconds(100));
        }
    }
}


