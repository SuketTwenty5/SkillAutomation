package t5.ipe.cucumber.objects.pages.proposals;

import org.openqa.selenium.support.FindBy;
import t5.ipe.cucumber.core.web.elements.BaseWebElement;
import t5.ipe.cucumber.core.web.interfaces.annotations.ElementName;
import t5.ipe.cucumber.core.web.interfaces.annotations.WebPage;
import t5.ipe.cucumber.objects.elements.IpeField;
import t5.ipe.cucumber.objects.elements.IpeViewDropdownField;
import t5.ipe.cucumber.objects.elements.tables.PhasesTable;
import t5.ipe.cucumber.objects.pages.MainPage;

/**
 * Bids Phases page.
 * Created by: Ekruze
 * Date: 11/01/2023
 */
@WebPage(value = "Phases page", urlTemplate = ".*quote:[0-9A-Fa-f]{8}(?:-[0-9A-Fa-f]{4}){3}-[0-9A-Fa-f]{12}|.*quote:[0-9A-Fa-f]{8}(?:-[0-9A-Fa-f]{4}){3}-[0-9A-Fa-f]{12}$", tabName = "Phases")
public class PhasesPage extends WBSPage {
    public static final String PHASES_TAB = "'PHASES' tab";
    public static final String COST_STRUCTURE_TAB = "'Cost Structure' tab";

    @ElementName(COST_STRUCTURE_TAB)
    @FindBy(xpath = "//a[@aria-selected='false']//span[contains(text(),'Cost Structure')]")
    BaseWebElement costStructureTab;

    @ElementName(PHASES_TAB)
    @FindBy(xpath = "//a[@aria-selected='true']//span[contains(text(),'Phases')]")
    BaseWebElement phasesTab;

    @ElementName("'No records found, click here to add' button")
    @FindBy(xpath = "//*[@role='tabpanel' and @aria-hidden='false']//*[@aria-hidden='false' and (@role='grid' or @role='treegrid')]//*[@data-grigaddlink='true']//div[text()]")
    BaseWebElement noRecordFind;

    @ElementName("'Setup' tab")
    @FindBy(xpath = "//span[text()='Setup']")
    BaseWebElement setupTab;

    @ElementName("'View' dropdown")
    @FindBy(xpath = "//div[not(contains(@class,'x-hidden-offsets')) and (contains (@class, 'x-tabpanel-child'))]//a[contains(@class, 'x-split-button')][@aria-hidden='false']//span[contains(text(), 'View')]")
    IpeViewDropdownField viewDropdown;

    @ElementName("'Click here to add' link")
    @FindBy(xpath = "//div[contains(@class, 'x-panel-body')]//a[text()='click here to add']")
    BaseWebElement addLink;

    @ElementName("'Phases' table")
    @FindBy(xpath = "//div[@class='x-grid-item-container']")
    PhasesTable phasesTable;

    @ElementName("'Edit Phase Information' window")
    @FindBy(xpath = "//div[contains(@class, 'x-window')]//*[text()='Edit Phase Information']")
    BaseWebElement editPhaseInformationWindow;

    @ElementName("'Reschedule Project' window")
    @FindBy(xpath = "//div[contains(@class, 'x-window')]//*[text()='Reschedule Project']")
    BaseWebElement rescheduleProjectWindow;

    @ElementName("'Cancel' button")
    @FindBy(xpath = "//div[@role='toolbar']//a[@role='button']//span[text()='Cancel']")
    BaseWebElement cancelButton;

    @ElementName("'Start' field")
    @FindBy(xpath = "(//div[contains(@class, 'x-window-body')]//input[@title='Expected date format n/j/y.'])[1]")
    IpeField selectStartField;

    @ElementName("'End' field")
    @FindBy(xpath = "(//div[contains(@class, 'x-window-body')]//input[@title='Expected date format n/j/y.'])[2]")
    IpeField selectEndField;

    @ElementName("'Close' button")
    @FindBy(xpath = "//span[text()='Close']")
    BaseWebElement closeButton;

    @ElementName("'WBS' tab")
    @FindBy(xpath = "//span[text()='WBS' or text()='Workstreams']")
    BaseWebElement wbsTab;

    @Override
    public String getLoadTriggerName() {
        return PHASES_TAB;
    }
}
