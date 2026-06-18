package t5.ipe.cucumber.objects.elements;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import t5.ipe.cucumber.core.web.elements.BaseWebElement;
import t5.ipe.cucumber.core.web.interfaces.element_interfaces.Clickable;
import t5.ipe.cucumber.core.web.interfaces.element_interfaces.Visible;
import t5.ipe.cucumber.core.web.util.AllureUtils;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$x;

public class localDataStorageReset extends BaseWebElement {

    @Override
    public void click() {
        SelenideElement element = toSelenideElement();

        try {
            element.should(Condition.exist, Duration.ofSeconds(30))
                    .shouldBe(Condition.visible, Duration.ofSeconds(10));

            if (element.isDisplayed()) {
                element.scrollTo().hover().click();
                AllureUtils.saveScreenshot();
                AllureUtils.logActionF(String.format("User clicked on Element %s", element));
            } else {
                throw new Exception("Element exists but is not visible");
            }

        } catch (Throwable e) {
            String xpath = toXpath();
            AllureUtils.logActionF(String.format("Failed to click xpath %s, retrying with JavaScript", xpath));
            try {
                clickElementByXpathUsingJs(xpath);
                AllureUtils.saveScreenshot();
                AllureUtils.logActionF(String.format("JavaScript click succeeded on xpath: %s", xpath));
            } catch (Exception jsEx) {
                String msg = String.format("JavaScript click failed for element %s due to: %s", element, jsEx.getMessage());
                AllureUtils.logActionF(msg);
                throw new RuntimeException(msg, jsEx);
            }
        }
//        Selenide.sleep(21500);
        $x("//*[contains(@class,'x-window-bodyWrap')]//*[text()= 'Loading']")
                .shouldNotBe(Condition.visible, Duration.ofSeconds(120));
        AllureUtils.attachPageSource("Local Data Storage Reset - Post Click");
    }
}
