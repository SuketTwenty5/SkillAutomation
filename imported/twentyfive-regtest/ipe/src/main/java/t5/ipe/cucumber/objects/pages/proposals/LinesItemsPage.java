package t5.ipe.cucumber.objects.pages.proposals;


import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import org.openqa.selenium.support.FindBy;
import t5.ipe.cucumber.core.web.elements.ArrowDropdownField;
import t5.ipe.cucumber.core.web.elements.BaseWebElement;
import t5.ipe.cucumber.core.web.elements.IpeCustomDropdownField;
import t5.ipe.cucumber.core.web.interfaces.annotations.ElementName;
import t5.ipe.cucumber.core.web.interfaces.annotations.WebPage;
import t5.ipe.cucumber.core.web.util.AllureUtils;
import t5.ipe.cucumber.objects.elements.IpeViewDropdownField;
import t5.ipe.cucumber.objects.elements.tables.ContractLinesCostAllocationsTable;
import t5.ipe.cucumber.objects.elements.tables.ContractLinesTable;
import t5.ipe.cucumber.objects.elements.tables.LineItemsTable;
import t5.ipe.cucumber.objects.elements.tables.WBSTable;
import t5.ipe.cucumber.objects.pages.MainPage;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;
import static t5.ipe.cucumber.core.web.util.web.WebUtil.setCustomTimeoutSec;
import static t5.ipe.cucumber.core.web.util.web.WebUtil.setDefaultTimeout;

/**
 * Proposal Lines Items page.
 */
@WebPage(value = "Line Items page", urlTemplate = ".*quote:[0-9A-Fa-f]{8}(?:-[0-9A-Fa-f]{4}){3}-[0-9A-Fa-f]{12}$", tabName = "Line Items")
public class LinesItemsPage extends MainPage {
    public static final String COST_STRUCTURE_TAB = "'Cost Structure' tab";
    public static final String LINE_ITEMS_TAB = "'Line Items' tab";

    @ElementName(LINE_ITEMS_TAB)
    @FindBy(xpath = "//a[@aria-selected='true']//span[text()='Line Items' or text()='Deliverables']")
    BaseWebElement itemsTab;

    @ElementName(COST_STRUCTURE_TAB)
    @FindBy(xpath = "//a[@aria-selected='false']//span[contains(text(),'Cost Structure')]")
    BaseWebElement costStructureTab;

    @ElementName("'Line Item Details' window")
    @FindBy(xpath = "//*[@role='dialog' and contains(@class,'x-window-default-resizable')]")
    BaseWebElement editPhaseInformationWindow;

    @ElementName("'Line Items' table")
    @FindBy(xpath = "//div[not(contains(@class,'x-hidden-offsets')) and (contains (@class, 'x-container x-tabpanel-child'))]//div[@class='x-grid-item-container']")
    LineItemsTable lineItemsTable;

    @ElementName("'Cost Allocations' tab")
    @FindBy(xpath = "//span[text()='Cost Allocations']")
    BaseWebElement costAllocTab;

    @ElementName("'Contract Lines Allocations' table")
    @FindBy(xpath = "//div[not(contains(@class,'x-hidden-offsets')) and (contains (@class, 'x-panel x-tabpanel-child'))]//div[@class='x-grid-item-container']")
    ContractLinesCostAllocationsTable contractLinesAllocTable;

    @ElementName("'Pricing & Qty' tab")
    @FindBy(xpath = "//*[@role='dialog']//span[text()='Pricing & Qty' or text()='Pricing']")
    BaseWebElement pricingTab;

    @ElementName("'View' dropdown")
    @FindBy(xpath = "//*[@role='tabpanel' and @aria-hidden='false']//*[contains(@class, 'x-toolbar') and @aria-hidden='false' and @role='toolbar']//a[contains(@class, 'x-split-button')]")
    IpeViewDropdownField viewDropdown;

    @ElementName("'Pricing Strategy' field")
    @FindBy(xpath = "(//div//label//span[text()='Pricing Strategy *']//following::input[1])")
    IpeCustomDropdownField pricingStrategyField;

    @ElementName("'Revenue Recognition Method' field")
    @FindBy(xpath = "(//div//label//span[text()='Revenue Recognition Method ']//following::input[1])")
    ArrowDropdownField revenueRecognitionField;

    @ElementName("'Pricing&Qty' table")
    @FindBy(xpath = "//div[not(contains(@class,'x-hidden-offsets')) and (contains (@class, 'x-panel x-tabpanel-child'))]//div[@class='x-grid-item-container']")
    ContractLinesCostAllocationsTable pricingQtyTable;

    @ElementName("'Click here to add' link")
    @FindBy(xpath = "//div[@aria-hidden='false' and @role='tabpanel']//a[@data-grigaddlink='true']//*[text()]")
    BaseWebElement addLink;

    @ElementName("'Reprice' button")
    @FindBy(xpath = "//span[text()='Re-price']")
    BaseWebElement repriceButton;

    @ElementName("'Delivery Schedule' tab")
    @FindBy(xpath = "//span[text()='Delivery Schedule']")
    BaseWebElement deliveryScheduleTab;

    @ElementName("'Delivery Schedule' table")
    @FindBy(xpath = "//div[not(contains(@class,'x-hidden-offsets')) and (contains (@class, 'x-panel x-tabpanel-child'))]//div[@class='x-grid-item-container']")
    ContractLinesCostAllocationsTable deliveryScheduleTable;

    @Override
    public String getLoadTriggerName() {
        return LINE_ITEMS_TAB;
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

    private void handleReload() {
        $x("//a[@data-qtip='Reload']").shouldBe(visible, Duration.ofSeconds(25)).click();
    }


}


