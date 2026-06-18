package t5.ipe.cucumber.objects.pages.proposals;

import org.openqa.selenium.support.FindBy;
import t5.ipe.cucumber.core.web.elements.BaseWebElement;
import t5.ipe.cucumber.core.web.interfaces.annotations.ElementName;
import t5.ipe.cucumber.core.web.interfaces.annotations.WebPage;
import t5.ipe.cucumber.objects.elements.MenuItemField;
import t5.ipe.cucumber.objects.elements.tables.PhasesTable;
import t5.ipe.cucumber.objects.elements.tables.WBSTable;
import t5.ipe.cucumber.objects.pages.MainPage;
import t5.ipe.cucumber.objects.elements.tables.setsTable;

@WebPage(value = "Sets page", urlTemplate = ".*proposal:[0-9A-Fa-f]{8}(?:-[0-9A-Fa-f]{4}){3}-[0-9A-Fa-f]{12}$", tabName = "Sets")

public class setsPage extends MainPage {
    public static final String SETS_TAB = "'Sets' tab";

    @ElementName(SETS_TAB)
    @FindBy(xpath = "//a[@aria-selected='true']//span[text()='Sets' or text()='Sets/Phases']")
    BaseWebElement setsTab;

    @ElementName("'Confirm & Release' button")
    @FindBy(xpath = "//span[text()='Confirm & Release']")
    BaseWebElement confirmReleaseButton;

    @ElementName("'Confirm & Open' button")
    @FindBy(xpath = "//span[text()='Confirm & Open']")
    BaseWebElement confirmOpenButton;

    @ElementName("'Close' button")
    @FindBy(xpath = "//span[text()='Close']/ancestor::a")
    BaseWebElement closeButton;

    @ElementName("'Estimate' popUp")
    @FindBy(xpath = "//*[text()='Estimate']/ancestor::*[@role='dialog' and @aria-hidden='false']")
    BaseWebElement estimatePopUp;

    @ElementName("'Phases' table")
    @FindBy(xpath = "//div[@class='x-grid-item-container']")
    PhasesTable phasesTable;

    @ElementName("'Column' header")
    @FindBy(xpath = "//*[@role='columnheader' and @aria-hidden='false']")
    MenuItemField columnHeader;

    @ElementName("'Sets' table")
    @FindBy(xpath = "//*[@role='rowgroup' and not(contains(@class,'x-grid-header-ct')) and not(@aria-busy='true')]")
    setsTable setsTable;

    @Override
    public String getLoadTriggerName() {
        return SETS_TAB;
    }
}
