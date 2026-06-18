package t5.ipe.cucumber.objects.pages.proposals;


import com.codeborne.selenide.Condition;
import org.openqa.selenium.support.FindBy;
import t5.ipe.cucumber.core.web.elements.BaseWebElement;
import t5.ipe.cucumber.core.web.elements.LongWaitElement;
import t5.ipe.cucumber.core.web.elements.TextElement;
import t5.ipe.cucumber.core.web.interfaces.annotations.ElementName;
import t5.ipe.cucumber.core.web.interfaces.annotations.WebPage;
import t5.ipe.cucumber.core.web.util.AllureUtils;
import t5.ipe.cucumber.objects.elements.*;
import t5.ipe.cucumber.objects.pages.MainPage;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;
import static t5.ipe.cucumber.core.web.util.web.WebUtil.setCustomTimeoutSec;
import static t5.ipe.cucumber.core.web.util.web.WebUtil.setDefaultTimeout;

/**
 * Setup page - Accessed through the "Proposals" tab by the "New" button, this page contains the essential fields for initiating proposal creation.
 */
@WebPage(value = "Escalation or Inflation Rates page", urlTemplate = ".*#quote:new:type=PS_consulting$|.*#proposal:new:type=DI_service$|.*proposal:[0-9A-Fa-f]{8}(?:-[0-9A-Fa-f]{4}){3}-[0-9A-Fa-f]{12}$", tabName = "Escalation or Inflation Rates")
public class SetUpEscalationOrInflationRatesPopUp extends MainPage {
    public static final String ESCALATION_OR_INFLATION_TAB = "'Escalation or Inflation Rates' tab";

    @ElementName(ESCALATION_OR_INFLATION_TAB)
    @FindBy(xpath = "//a[@aria-selected='true']//span[contains(text(),'Escalation or Inflation Rates')]")
    BaseWebElement setupTab;

    @Override
    public String getLoadTriggerName() {
        return ESCALATION_OR_INFLATION_TAB;
    }
}
