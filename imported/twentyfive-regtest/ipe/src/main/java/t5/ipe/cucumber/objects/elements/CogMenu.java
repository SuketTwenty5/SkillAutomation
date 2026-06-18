package t5.ipe.cucumber.objects.elements;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import t5.ipe.cucumber.core.web.elements.BaseWebElement;
import t5.ipe.cucumber.core.web.interfaces.element_interfaces.Tree;
import t5.ipe.cucumber.core.web.util.AllureUtils;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;

/**
 * Cog menu.
 */
public class CogMenu extends BaseWebElement implements Tree {
    private static final String XPATH_TEMPLATE = "//div[contains(@class, 'x-menu')][@aria-hidden='false']//a[@aria-hidden='false']//span[contains(text(),'%s')]";
    private static final String COG_XPATH = "//table[contains(@class, 'x-grid-item-selected')]//span[@class='x-tree-node-text ']//div[contains(@class, 'ibeCallOut')]";
    //private static final String COG_XPATH_SECOND = "//table[contains(@class, 'x-grid-item-selected')]//span[@class='x-tree-node-text']";
    private static final String COG_XPATH_SECOND = "//div[@class='x-grid-cell-inner x-grid-cell-inner-treecolumn']";
    @Override
    public void selectNode(String... nodeNames) {
        AllureUtils.logActionF("Enter method selectNode, searching nodeNames %s", nodeNames);
        Selenide.sleep(3000);

        // Use JavaScript to hover over the element
        try {
            SelenideElement element = $x(COG_XPATH);
            hoverByJavascript(element, COG_XPATH);
            // Check if element is visible after hover
            element.shouldBe(visible, Duration.ofMinutes(1));
            AllureUtils.logActionF("selectNode Method Selenide element %s is visible ", element);

            // Click the element
            element.click();
        } catch (Exception e) {
            AllureUtils.logActionF("Failed to hover over the element %s using JavaScript", COG_XPATH);
            SelenideElement element = $x(COG_XPATH_SECOND);
            element.hover();
            this.toSelenideElement().click();
        }

        for (String nodeName : nodeNames) {
            selectNode(nodeName);
        }
    }

    private static void hoverByJavascript(SelenideElement element, String xpathElement) {
        WebElement webElement = element.toWebElement();
        JavascriptExecutor js = (JavascriptExecutor) WebDriverRunner.getWebDriver();
        js.executeScript("var evt = document.createEvent('MouseEvents');"
                + "evt.initMouseEvent('mouseover', true, true, window, 1, 0, 0, 0, 0, false, false, false, false, 0, null);"
                + "arguments[0].dispatchEvent(evt);", webElement);
        AllureUtils.logActionF("Successfully hovered over the element %s using JavaScript", xpathElement);
    }

    public void selectNode(String nodeName) {
        String nodeXPath = getNodeXPath(nodeName);
        String nodeText =$x(nodeXPath).getText();
//        boolean alreadySet = nodeText.contains("(current)");
        if ($x(nodeXPath).isDisplayed()) {
            $x(nodeXPath).hover().click();
            AllureUtils.saveScreenshot();
        } else if (!$x(nodeXPath).isDisplayed()) {
            throw new RuntimeException(String.format("Not found item with name [%s]", nodeName));
        }
    }

    private String getNodeXPath(String nodeName) {
        return String.format(XPATH_TEMPLATE, nodeName);
    }
}
