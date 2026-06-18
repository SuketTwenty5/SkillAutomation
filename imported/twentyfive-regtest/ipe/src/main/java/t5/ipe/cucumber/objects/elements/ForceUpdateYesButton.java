package t5.ipe.cucumber.objects.elements;

import com.codeborne.selenide.Condition;
import t5.ipe.cucumber.core.web.elements.BaseWebElement;
import t5.ipe.cucumber.core.web.util.AllureUtils;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.sleep;

public class ForceUpdateYesButton extends BaseWebElement {
    @Override
    public void click() {
        for (int attempt = 1; attempt <= 3; attempt++) {
            if(toSelenideElement().scrollIntoView(false).is(Condition.visible)){
                toSelenideElement().click();
                AllureUtils.logActionF("Clicked 'Yes' button to force update.");
                return;
            }
            $x("//span[text()='Confirm']/ancestor::a").click();
            $x("//*[@role='dialog' and @aria-disabled='false']//*[text()='Yes']").click();
            sleep(7000);
        }

        if(toSelenideElement().scrollIntoView(false).is(Condition.visible)){
            toSelenideElement().click();
        }else {
            throw new AssertionError("Force Update did not become visible after 3 retries");
        }
    }
}
