package t5.ipe.cucumber.objects.pages.cost_analysis;


import com.codeborne.selenide.Condition;
import t5.ipe.cucumber.core.web.elements.BaseWebElement;
import t5.ipe.cucumber.core.web.interfaces.annotations.ElementName;
import t5.ipe.cucumber.core.web.interfaces.annotations.WebPage;
import t5.ipe.cucumber.core.web.util.AllureUtils;
import t5.ipe.cucumber.objects.elements.*;
import t5.ipe.cucumber.objects.pages.MainPage;
import org.openqa.selenium.support.FindBy;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;
import static t5.ipe.cucumber.core.web.util.web.WebUtil.setCustomTimeoutSec;
import static t5.ipe.cucumber.core.web.util.web.WebUtil.setDefaultTimeout;

/**
 * Cost Price Analysis - Workbench page.
 * Created by: Ekruze
 * Date: 10/02/2023
 */
@WebPage(value = "Workbench page", urlTemplate = ".*#costing:.*?", tabName = "Workbench")
public class CostPriceAnalysisWorkbench extends MainPage {
    public static final String WORKBENCH_TAB = "'Workbench' tab";

    @ElementName(WORKBENCH_TAB)
    @FindBy(xpath = "//a//span[text()='Workbench' or text()='Analysis']")
    BaseWebElement workbenchTab;

    @ElementName("'Description' filter")
    @FindBy(xpath = "//*[@data-ref='inputEl' and @aria-hidden='false' and @role='textbox']")
    IpeField descriptionFilter;

    @ElementName("'View' dropdown")
    @FindBy(xpath = "//div[not(contains(@class,'x-hidden-offsets')) and (contains (@class, 'x-tabpanel-child'))]//a[contains(@class, 'x-split-button')][@aria-hidden='false']//span[contains(text(), 'View')]")
    IpeViewDropdownField viewDropdown;

    @ElementName("'Workbench' table")
    @FindBy(xpath = "//div[not(contains(@class,'x-hidden-offsets')) and (contains (@class, 'x-tabpanel-child'))]//div[@class='x-grid-item-container']")
    WorkbenchTable workbenchTable;

    @ElementName("'Change Rows & Columns' window")
    @FindBy(xpath = "//div[@class='x-panel x-docked x-panel-default x-docked-right x-panel-docked-right x-panel-default-docked-right x-unselectable']//*[text()='Change Rows & Columns']")
    BaseWebElement changeWindow;

    @ElementName("'Hours' menu item")
    @FindBy(xpath = "//div[contains(@class,'x-pivot-grid-config-column x-unselectable x-box-item x-pivot-grid-config-column-default')]//span[@data-qtip='Hours']")
    MenuItemField hoursMenuItem;

    @ElementName("'Cost:Company' menu item")
    @FindBy(xpath = "//div[contains(@class,'x-pivot-grid-config-column x-unselectable x-box-item x-pivot-grid-config-column-default')]//span[@data-qtip='Cost: (Company)']")
    MenuItemField costMenuItem;

    @ElementName("'Cog Settings' button")
    @FindBy(xpath = "//div[not(contains(@class,'x-hidden-offsets')) and (contains (@class, 'x-tabpanel-child'))]//a[@data-qtip='Settings']")
    WorkbenchDropButton cogMenu;

    @ElementName("'Apply' button")
    @FindBy(xpath = "//span[text()='Apply']")
    BaseWebElement applyButton;

    @ElementName("'Apply & Close' button")
    @FindBy(xpath = "//span[text()='Apply & Close']")
    BaseWebElement applyAndCloseButton;

    @ElementName("'Pricing Revenue only' delete button")
    @FindBy(xpath = "//div[text()='Pricing Revenue only']//following-sibling::div[@class='x-tagfield-item-close']")
    BaseWebElement deleteRevenueButton;

    @ElementName("'CashFlow' tab")
    @FindBy(xpath = "//span[text()='CashFlow']")
    BaseWebElement cashFlowTab;

    @Override
    public String getLoadTriggerName() {
        return WORKBENCH_TAB;
    }
}
