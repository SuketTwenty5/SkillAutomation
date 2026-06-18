package t5.ipe.cucumber.objects.elements;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.JavascriptExecutor;
import t5.ipe.cucumber.core.web.elements.BaseBlockElement;
import t5.ipe.cucumber.core.web.interfaces.element_interfaces.Tree;
import t5.ipe.cucumber.core.web.util.AllureUtils;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;

/**
 * Humburger
 */

public class IpeHumburgerTree extends BaseBlockElement implements Tree {
    private static final String XPATH_TEMPLATE = "(//div[@class='x-menu x-layer x-menu-default x-border-box']//*[text()='%s'])[last()]/ancestor::*[@role='menuitem' and @aria-hidden='false']";

    @Override
    public void selectNode(String... nodeNames) {
        this.toSelenideElement().shouldBe(visible, Duration.ofMinutes(1));
        this.toSelenideElement().hover();
        //$x("//div[@class='x-menu x-layer x-menu-default x-border-box'][@aria-hidden='false']").shouldBe(visible);
        for (String nodeName : nodeNames) {
            selectNode(nodeName);
        }
    }

    public void hoverAndClickUsingJS(String nodeXPath) {
        System.out.println("Finding element using XPath: " + nodeXPath);

        SelenideElement element = $x(nodeXPath).shouldBe(visible, Duration.ofSeconds(15));

        JavascriptExecutor js = (JavascriptExecutor) WebDriverRunner.getWebDriver();

        System.out.println("Hovering over the element using JavaScript...");

        String hoverScript =
                "['mouseover','mouseenter','mousemove'].forEach(function(e) {" +
                        " var evt = new MouseEvent(e, { bubbles: true, cancelable: true, view: window });" +
                        " arguments[0].dispatchEvent(evt);" +
                        "});";

        try {
            js.executeScript(hoverScript, element.getWrappedElement());
            System.out.println("Hover script executed.");
        } catch (Exception e) {
            System.out.println("Error while hovering: " + e.getMessage());
        }

        Selenide.sleep(2000); // Optional sleep for visual confirmation

        System.out.println("Clicking the element using JavaScript...");

        try {
            js.executeScript("arguments[0].click();", element);
            System.out.println("Click script executed.");
        } catch (Exception e) {
            System.out.println("Error while clicking: " + e.getMessage());
        }

        Selenide.sleep(1000); // Optional sleep after click
    }


    public void selectNode(String nodeName) {
        String nodeXPath = getNodeXPath(nodeName);
        if ($x(nodeXPath).isDisplayed()) {
//            hoverAndClickUsingJS(nodeXPath);
            $x(nodeXPath).hover().click();
            AllureUtils.saveScreenshot();
        } else if (!($x(nodeXPath).isDisplayed())) {
            throw new RuntimeException(String.format("Not found item with name [%s]", nodeName));
        }
    }

    private String getNodeXPath(String nodeName) {
        return String.format(XPATH_TEMPLATE, nodeName);
    }
}
