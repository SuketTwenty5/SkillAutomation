package t5.ipe.cucumber.objects.elements;


import com.codeborne.selenide.Condition;
import org.openqa.selenium.Keys;
import t5.ipe.cucumber.core.web.util.AllureUtils;

import static com.codeborne.selenide.Selenide.*;

/**
 * CogTree
 */

public class IpeCogTree extends IpeHumburgerTree {
    private static final String XPATH_TEMPLATE = "(//div[@class='x-menu x-layer x-menu-default x-border-box']//*[contains(text(),'%s')])[last()]";

    @Override
    public void selectNode(String... nodeNames) {
        this.toSelenideElement().hover().click();
        $x("//div[@class='x-menu x-layer x-menu-default x-border-box'][@aria-hidden='false']").shouldBe(Condition.visible);
        for (String nodeName : nodeNames) {
            selectNode(nodeName);
        }
    }

    public void selectNode(String nodeName) {
        String nodeXPath = getNodeXPath(nodeName);
        boolean alreadySet = $x(nodeXPath).getText().contains("(current)");
        if (alreadySet) {
            AllureUtils.logActionF("%s already selected. Not making any selection",nodeName);
            actions().sendKeys(Keys.ESCAPE).perform();
        } else if ($x(nodeXPath).isDisplayed()) {
            $x(nodeXPath).hover();
            $x(nodeXPath).click();
            AllureUtils.saveScreenshot();
        } else if (!($x(nodeXPath).isDisplayed())) {
            throw new RuntimeException(String.format("Not found item with name [%s]", nodeName));
        }
    }

    private String getNodeXPath(String nodeName) {
        return String.format(XPATH_TEMPLATE, nodeName);
    }
}
