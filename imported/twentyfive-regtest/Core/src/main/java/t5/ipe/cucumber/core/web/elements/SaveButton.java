package t5.ipe.cucumber.core.web.elements;

import com.codeborne.selenide.*;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.FindBy;
import t5.ipe.cucumber.core.web.interfaces.element_interfaces.Clickable;
import t5.ipe.cucumber.core.web.interfaces.element_interfaces.Visible;
import t5.ipe.cucumber.core.web.util.AllureUtils;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class SaveButton extends BaseWebElement {

    private void highlightElement(String xpath) {
        try {
            String script = ""
                    + "var xpath = \"" + xpath + "\";"
                    + "var result = document.evaluate(xpath, document, null, XPathResult.FIRST_ORDERED_NODE_TYPE, null);"
                    + "var element = result.singleNodeValue;"
                    + "if (element) {"
                    + "    element.style.border = '3px solid red';"
                    + "    element.style.backgroundColor = 'yellow';"
                    + "    console.log('✅ Element found and highlighted');"
                    + "} else {"
                    + "    console.warn('❌ Element NOT found');"
                    + "}";

            ((JavascriptExecutor) WebDriverRunner.getWebDriver()).executeScript(script);
        } catch (Exception ignored) {
            // Highlighting is optional, so fail silently
        }
    }

    @Override
    public void click() {
        SelenideElement element = toSelenideElement();

        try {
            element.should(Condition.exist, Duration.ofSeconds(30))
                    .shouldBe(Condition.visible, Duration.ofSeconds(10));

            if (element.isDisplayed()) {
                element.scrollTo().hover();
                Selenide.sleep(700);
                $x("//*[@role='menu' and @aria-hidden='false']//*[@role='menuitem' and @aria-hidden='false']//*[text()='Save without Check']").hover().click();
                AllureUtils.saveScreenshot();
                AllureUtils.logActionF(String.format("User clicked on Element %s", element));
            } else {
                throw new Exception("Element exists but is not visible");
            }

        } catch (Throwable e) {
            String xpath = toXpath();
            AllureUtils.logActionF(String.format("Failed to click xpath %s, retrying with JavaScript", xpath));
            try {
                highlightElement(xpath);
                clickElementByXpathUsingJs(xpath);
                AllureUtils.saveScreenshot();
                AllureUtils.logActionF(String.format("JavaScript click succeeded on xpath: %s", xpath));
            } catch (Exception jsEx) {
                String msg = String.format("JavaScript click failed for element %s due to: %s", element, jsEx.getMessage());
                AllureUtils.logActionF(msg);
                throw new RuntimeException(msg, jsEx);
            }
        }
    }


}
