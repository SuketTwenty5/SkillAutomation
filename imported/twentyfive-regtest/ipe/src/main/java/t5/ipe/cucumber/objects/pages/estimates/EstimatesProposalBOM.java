package t5.ipe.cucumber.objects.pages.estimates;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import org.openqa.selenium.support.FindBy;
import t5.ipe.cucumber.core.web.elements.BaseWebElement;
import t5.ipe.cucumber.core.web.elements.LongWaitElement;
import t5.ipe.cucumber.core.web.elements.TextElement;
import t5.ipe.cucumber.core.web.interfaces.annotations.ElementName;
import t5.ipe.cucumber.core.web.interfaces.annotations.WebPage;
import t5.ipe.cucumber.core.web.interfaces.element_interfaces.Visible;
import t5.ipe.cucumber.core.web.util.AllureUtils;
import t5.ipe.cucumber.core.web.util.web.SearchUtils;
import t5.ipe.cucumber.objects.elements.*;
import t5.ipe.cucumber.objects.elements.tables.LaborTable;
import t5.ipe.cucumber.objects.elements.tables.WBSTable;
import t5.ipe.cucumber.objects.pages.MainPage;
import t5.ipe.cucumber.objects.elements.tables.proposalBOMTable;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;
import static t5.ipe.cucumber.core.web.util.web.WebUtil.setCustomTimeoutSec;
import static t5.ipe.cucumber.core.web.util.web.WebUtil.setDefaultTimeout;

@WebPage(value = "Estimate Proposal BOM", urlTemplate = ".*#boe:[0-9A-Fa-f]{32}$|.*boe:[0-9A-Fa-f]{8}(?:-[0-9A-Fa-f]{4}){3}-[0-9A-Fa-f]{12}$", tabName = "Proposal BOM")
public class EstimatesProposalBOM extends MainPage {
    public static final String PROPOSAL_BOM_TAB = "'Proposal BOM' tab";

    @ElementName(PROPOSAL_BOM_TAB)
    @FindBy(xpath = "//a[@aria-selected='true']//span[text()='Proposal BOM' or text()='Bom']")
    BaseWebElement costStructureTab;

    @ElementName("'BOM Upload' button")
    @FindBy(xpath = "//*[@role='tabpanel' and @aria-hidden='false']//*[@role='button' and @aria-hidden='false' and @data-qtip='Upload']")
    BaseWebElement bomUploadButton;

    @ElementName("'Upload a List of Objects' dialog")
    @FindBy(xpath = "//*[contains(text(),'Upload a List of Objects')]/ancestor::*[@role='dialog' and @aria-hidden='false']")
    BaseWebElement uploadAListOfObjectsDialog;

    @ElementName("'Select file' button")
    @FindBy(xpath = "//*[@role='dialog' and @aria-hidden='false']//*[text()='Select File']/ancestor::*[@role='button' and @aria-hidden='false']")
    BaseWebElement selectFileButton;

    @ElementName("'Upload' photo")
    @FindBy(xpath = "//*[@role='dialog']//input[@name='photo' and @type ='file']")
    BaseWebElement uploadPhotoInput;

    @ElementName("'Run All Parts' button")
    @FindBy(xpath = "//*[@role='dialog' and @aria-hidden='false']//*[@role='button' and @aria-hidden='false']//*[text()='Run - All Parts']")
    BaseWebElement runAllPartsButton;

    @ElementName("'Rolled-up the costed BOM' notification")
    @FindBy(xpath = "//div[contains(normalize-space(.), 'rolled-up the costed BOM')]/ancestor::*[@role='dialog' and @aria-hidden='false']")
    IpeNotification rolledUpTheCostedBOMNotification;

    @ElementName("'Cost consolidated bill of material has completed and material estimates will be reloaded' notification")
    @FindBy(xpath = "//*[text()='Cost consolidated bill of material has completed and material estimates will be reloaded']/ancestor::*[@role='dialog' and @aria-hidden='false']")
    IpeNotification costConsolidatedBillOfMaterialNotification;

    @ElementName("'Bill of Material was updated successfully' notification")
    @FindBy(xpath = "//*[contains(normalize-space(.), 'Bill of Material was updated successfully')]/ancestor::*[@role='dialog' and @aria-hidden='false']")
    IpeNotification billOfMaterialWasUpdatedSuccessfullyNotification;

    @ElementName("'Purchase History' tab")
    @FindBy(xpath = "//*[@role='dialog' and @aria-hidden='false']//*[@role='tab' and @aria-hidden='false']//*[text()='Purchase History']")
    BaseWebElement purchaseHistoryTab;


    @ElementName("'Production History' tab")
    @FindBy(xpath = "//*[@role='dialog' and @aria-hidden='false']//*[@role='tab' and @aria-hidden='false']//*[text()='Production History']")
    BaseWebElement productionHistoryTab;

    @ElementName("'Advanced Options' tab")
    @FindBy(xpath = "//*[@role='dialog' and @aria-hidden='false']//*[@role='tab' and @aria-hidden='false']//*[text()='Advanced Options']")
    BaseWebElement advancedOptionsTab;

    @ElementName("'Cost purchased and subcontract parts' radio")
    @FindBy(xpath = "//*[@role='dialog' and @aria-hidden='false']//*[text()='Cost purchased and subcontract parts']/ancestor::*[@class='x-table-layout-cell ']")
    IpeCheckBox costPurchasedAndSubcontractPartsRadio;

    @ElementName("'Exclude manual purchase history' radio")
    @FindBy(xpath = "//*[@role='dialog' and @aria-hidden='false']//*[text()='Exclude manual (non-SAP) purchase history']/ancestor::*[@class='x-table-layout-cell ']")
    IpeCheckBox excludeManualNonSAPPurchaseHistoryRadio;

    @ElementName("'Cost make parts' radio")
    @FindBy(xpath = "//*[@role='dialog' and @aria-hidden='false']//*[text()='Cost make parts']/ancestor::*[@class='x-table-layout-cell ']")
    IpeCheckBox costMakePartsRadio;

    @ElementName("'Only consider current valid quotes or contracts' radio")
    @FindBy(xpath = "//*[@role='dialog' and @aria-hidden='false']//*[text()='Only consider current valid quotes or contracts']/ancestor::*[@class='x-table-layout-cell ']")
    IpeCheckBox onlyConsiderCurrentValidQuotesOrContractsRadio;

    @ElementName("'Escalate based on commitment' radio")
    @FindBy(xpath = "//*[@role='dialog' and @aria-hidden='false']//*[text()='Escalate based on commitment']/ancestor::*[@class='x-table-layout-cell ']")
    IpeCheckBox escalateBasedOnCommitmentRadio;

    @ElementName("'Disable all quantity curving' radio")
    @FindBy(xpath = "//*[@role='dialog' and @aria-hidden='false']//*[text()='Disable all quantity curving']/ancestor::*[@class='x-table-layout-cell ']")
    IpeCheckBox disableAllQuantityCurvingRadio;

    @ElementName("'Consolidate and Cost Bill of Material' dialog")
    @FindBy(xpath = "//*[contains(text(),'Consolidate and Cost Bill of Material')]/ancestor::*[@role='dialog' and @aria-hidden='false']")
    BaseWebElement consolidateAndCostBillOfMaterialDialog;

    @ElementName("'Procurement & Production' tab")
    @FindBy(xpath = "//a[@role='tab' and @aria-hidden='false']//span[text()='Procurement & Production' or text()='Material']")
    BaseWebElement proposalBOMTab;

    @ElementName("'Proposal BOM' table")
    @FindBy(xpath = "(//div[not(contains(@class,'x-hidden-offsets')) and (contains (@class, 'x-panel x-tabpanel-child'))]//div[@class='x-grid-item-container'])")
    proposalBOMTable proposalBOMTable;

    @ElementName("'Remarks' dialog")
    @FindBy(xpath = "//*[@role='application' and @aria-disabled='false' and contains(@class,'tox')]")
    BaseWebElement remarksDialog;

    @ElementName("'Remarks' iframe")
    @FindBy(xpath = "//body[@id='tinymce']//*[text()][1]")
    IpeIframe remarksDialogIframe;

    @ElementName("'Close' button")
    @FindBy(xpath = "//*[@data-ref='btnInnerEl' and text()='Close']/ancestor::*[@role='button' and @aria-hidden='false']")
    BaseWebElement remarksDialogCloseButton;

    @ElementName("'Additional Data' tab")
    @FindBy(xpath = "//*[@role='dialog' and @aria-hidden='false']//*[@role='tab' and @aria-hidden='false']//span[text()='Additional Data']")
    BaseWebElement additionalDataTab;

    @ElementName("'Payments' tab")
    @FindBy(xpath = "//*[@role='dialog' and @aria-hidden='false']//*[@role='tab' and @aria-hidden='false']//span[text()='Payments']")
    BaseWebElement paymentsTab;

    @ElementName("'Proposal Requirements' tab")
    @FindBy(xpath = "//*[@role='dialog' and @aria-hidden='false']//a[@role='tab' and @aria-hidden='false']//span[text()='Proposal Requirements']")
    BaseWebElement proposalRequirementsTab;

    @ElementName("'Tags' tab")
    @FindBy(xpath = "//*[@role='dialog' and @aria-hidden='false']//*[@role='tab' and @aria-hidden='false']//span[text()='Tags']")
    BaseWebElement tagsTab;

    @ElementName("'Remarks' tab")
    @FindBy(xpath = "//*[@role='dialog' and @aria-hidden='false']//*[@role='tab' and @aria-hidden='false']//span[text()='Remarks']")
    BaseWebElement remarksTab;

    @ElementName("'Costing Log' tab")
    @FindBy(xpath = "//*[@role='dialog' and @aria-hidden='false']//*[@role='tab' and @aria-hidden='false']//span[text()='Costing Log']")
    BaseWebElement costingLogTab;

    @ElementName("'History' tab")
    @FindBy(xpath = "//*[@role='dialog' and @aria-hidden='false']//*[@role='tab' and @aria-hidden='false']//span[text()='History']")
    BaseWebElement historyTab;

    @ElementName("'Hamburger' menu options")
    @FindBy(xpath = "//*[@role='menu' and @aria-hidden='false']//*[@role='menuitem' and @aria-hidden='false']")
    MenuItemField hamburgerMenuOptions;

    @ElementName("'Update & Cost Consolidated BOM' menuItem")
    @FindBy(xpath = "//*[@role='menu' and @aria-hidden='false']//*[@role='menuitem' and @aria-hidden='false']//*[contains(text(),'Update & Cost Consolidated BOM')]/ancestor::a")
    BaseWebElement updateAndCostConsolidatedBOMMenuItem;

    @ElementName("'Generate Sourcing Plan' menuItem")
    @FindBy(xpath = "//*[@role='menu' and @aria-hidden='false']//*[@role='menuitem' and @aria-hidden='false']//*[contains(text(),'Generate Sourcing Plan')]/ancestor::a")
    BaseWebElement generateSourcingPlanMenuItem;

    @ElementName("'All Parts' menuItem")
    @FindBy(xpath = "//*[@role='menu' and @aria-hidden='false']//*[@role='menuitem' and @aria-hidden='false']//*[contains(text(),'All Parts') or contains(text(),'All parts')]/ancestor::a")
    BaseWebElement allPartsMenuItem;

    @ElementName("'Consolidate and Cost Bill of Material' popup")
    @FindBy(xpath = "//*[@role='dialog' and not(contains(@class,'x-window-ghost')) and @aria-hidden='false']//*[text()='Consolidate and Cost Bill of Material']")
    BaseWebElement consolidateAndCostBillOfMaterialPopup;

    @ElementName("'Consolidate and Cost Bill of Material' headers")
    @FindBy(xpath = "//*[@role='dialog' and not(contains(@class,'x-window-ghost')) and @aria-hidden='false']//*[@role='tab' and @aria-hidden='false']")
    MenuItemField consolidateAndCostBillOfMaterialHeader;

    @ElementName("'Set Default' button")
    @FindBy(xpath = "//*[@role='dialog' and not(contains(@class,'x-window-ghost')) and @aria-hidden='false']//*[@role='toolbar' and @aria-hidden='false']//*[@role='button' and @aria-hidden='false'][1]")
    TextElement setDefaultButton;

    @ElementName("'MFC defaults shared' dropdown")
    @FindBy(xpath = "//*[@role='menu' and @aria-hidden='false']//*[@role='menuitem' and @aria-hidden='false']//*[contains(text(),'MFC/M510')]")
    BaseWebElement mceDefaultsSharedDropdown;

    @ElementName("'Regression Test BOM Costing Profile' dropdown")
    @FindBy(xpath = "//*[@role='menu' and @aria-hidden='false']//*[@role='menuitem' and @aria-hidden='false']//*[contains(text(),'Regression Test BOM Costing Profile')]")
    BaseWebElement regressionTestBOMCostingProfileDropdown;

    @ElementName("'Bill of material is being consolidated' notification")
    @FindBy(xpath = "//div[@data-ref='innerCt' and contains(translate(normalize-space(.), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'material is being consolidated')]/ancestor::*[@role='dialog' and @aria-hidden='false']")
    BaseWebElement consolidatedBOMUpdateNotification;

    @ElementName("'Expand or Collapse' menuItem")
    @FindBy(xpath = "//*[@role='menu' and @aria-hidden='false']//*[@role='menuitem' and @aria-hidden='false']//*[contains(text(),'Expand/Collapse')]/ancestor::a")
    BaseWebElement expandOrCollapseMenuItem;

    @ElementName("'Expand All' menuItem")
    @FindBy(xpath = "//*[@role='menu' and @aria-hidden='false']//*[@role='menuitem' and @aria-hidden='false']//*[contains(text(),'Expand All')]/ancestor::a")
    BaseWebElement expandAllMenuItem;

    @ElementName("'Part Number Menu' options")
    @FindBy(xpath = "//*[@role='menu' and @aria-hidden='false']//*[contains(@class,'x-box-scroller-body-vertical')]")
    BaseWebElement partNumberMenuOptions;

    @Override
    public String getLoadTriggerName() {
        return PROPOSAL_BOM_TAB;
    }

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
            $x("//*[@role='progressbar' and @aria-hidden='false']//*[@class='x-mask-msg-text']").shouldBe(visible, Duration.ofSeconds(7));
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

}
