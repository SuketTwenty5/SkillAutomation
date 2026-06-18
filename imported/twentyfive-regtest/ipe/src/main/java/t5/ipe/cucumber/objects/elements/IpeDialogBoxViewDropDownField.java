package t5.ipe.cucumber.objects.elements;

import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import t5.ipe.cucumber.core.web.elements.TextElement;
import t5.ipe.cucumber.core.web.interfaces.element_interfaces.Selectable;
import t5.ipe.cucumber.core.web.util.AllureUtils;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class IpeDialogBoxViewDropDownField extends TextElement implements Selectable {
    @Override
    public void select(String text) {
        String viewText = toSelenideElement().getText();
        if (viewText.contains(text)) {
            AllureUtils.logAction("✅ View text contains '"+viewText+"' || Hence skipping the step");
        } else {
            SelenideElement el = $x("//*[@role='dialog' and @aria-hidden='false']"+
                    "//*[(@role='grid' or @role='treegrid') and @aria-hidden='false']" +
                    "//*[(@role='toolbar' or @role='group') and @aria-hidden='false']" +
                    "//a[@aria-hidden='false' and contains(@class,'x-split-button')]" +
                    "//span[@data-ref='btnWrap']");

            WebDriver driver = WebDriverRunner.getWebDriver();
            JavascriptExecutor js = (JavascriptExecutor) driver;

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
            $x("//*[@role='menu' and @aria-hidden='false']//*[@class='x-menu-item-link' and @aria-hidden='false']//*[contains(text(), '" + text + "')]").shouldBe(visible, Duration.ofSeconds(10)).click();
            $(String.valueOf(toSelenideElement().getText().equals(text)));
        }
    }
}
