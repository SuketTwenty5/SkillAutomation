package t5.ipe.cucumber.objects.elements;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import org.junit.Assert;
import org.openqa.selenium.*;
import t5.ipe.cucumber.core.web.elements.TextElement;
import t5.ipe.cucumber.core.web.interfaces.element_interfaces.Selectable;
import t5.ipe.cucumber.core.web.util.AllureUtils;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

/**
 * 'View' dropdown on 'Estimates' pages
 */
public class IpeViewDropdownField extends TextElement implements Selectable {

    @Override
    public void select(String text) {
        String primaryXPath = "//*[@role='tabpanel' and @aria-hidden='false']" +
                "//*[(@role='grid' or @role='treegrid') and @aria-hidden='false']" +
                "//*[(@role='toolbar' or @role='group') and @aria-hidden='false']" +
                "//a[@aria-hidden='false' and contains(@class,'x-split-button')]" +
                "//span[@data-ref='btnWrap']";

        String fallbackXPath =
                "//*[(@role='grid' or @role='treegrid') and @aria-hidden='false']" +
                        "//*[(@role='toolbar' or @role='group') and @aria-hidden='false']" +
                        "//a[@aria-hidden='false' and contains(@class,'x-split-button')]" +
                        "//span[@data-ref='btnWrap']";

        String viewText = toSelenideElement().getText();
        if (viewText.contains(text)) {
            AllureUtils.logAction("✅ View text contains '"+viewText+"' || Hence skipping the step");
        } else {
            SelenideElement el;
            WebDriver driver = WebDriverRunner.getWebDriver();
            JavascriptExecutor js = (JavascriptExecutor) driver;
            try {
                AllureUtils.logActionF("Clicking on 'View' dropdown with primary XPath: %s", primaryXPath);
                el = $x(primaryXPath).shouldBe(visible, Duration.ofSeconds(3));
            } catch (Throwable e) {
                AllureUtils.logActionF("Clicking on 'View' dropdown with fallback XPath: %s", fallbackXPath);
                el = $x(fallbackXPath).shouldBe(visible, Duration.ofSeconds(3));
            }
            js.executeScript(
                    "const el = arguments[0];" +
                            "const rect = el.getBoundingClientRect();" +
                            "const clickX = rect.right - 2;" +  // near right edge
                            "const clickY = rect.top + rect.height / 2;" + // vertical center
                            "const clickEvent = new MouseEvent('click', {" +
                            "  bubbles: true, cancelable: true, clientX: clickX, clientY: clickY" +
                            "});" +
                            "el.dispatchEvent(clickEvent);",
                    el
            );
            Selenide.sleep(3000);
            $x("//*[@aria-hidden='false' and @role='menu']//a[@aria-hidden='false' and @class='x-menu-item-link']//*[contains(text(), '" + text + "')]").scrollIntoView(true).shouldBe(visible, Duration.ofSeconds(10)).hover().click();
            Selenide.sleep(1000);
            $(String.valueOf(toSelenideElement().getText().equals(text)));
        }
    }

}

