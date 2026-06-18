package t5.ipe.cucumber.objects.pages;


import org.openqa.selenium.support.FindBy;
import t5.ipe.cucumber.core.web.BasePage;
import t5.ipe.cucumber.core.web.elements.BaseWebElement;
import t5.ipe.cucumber.core.web.interfaces.annotations.ElementName;
import t5.ipe.cucumber.core.web.util.AllureUtils;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;
import static t5.ipe.cucumber.core.web.util.web.WebUtil.setCustomTimeoutSec;
import static t5.ipe.cucumber.core.web.util.web.WebUtil.setDefaultTimeout;

/**
 * Contains common elements for all Ipe pages.
 * <p/>
 * Created by: EKruze
 * Date: 20/10/2023
 */
public abstract class BaseIpePage extends BasePage {
    @ElementName("'PROPOSALS' tab")
    @FindBy(xpath = "//span[text()='PROPOSALS']")
    BaseWebElement bidsTab;
}
