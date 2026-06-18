package t5.ipe.cucumber.objects.elements;


import org.openqa.selenium.support.FindBy;
import t5.ipe.cucumber.core.web.elements.BaseBlockElement;
import t5.ipe.cucumber.core.web.interfaces.annotations.ElementName;
import t5.ipe.cucumber.core.web.interfaces.element_interfaces.Selectable;
import t5.ipe.cucumber.core.web.interfaces.element_interfaces.Tree;
import t5.ipe.cucumber.core.web.util.AllureUtils;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;

/**
 * Standard tree (for example WBS tree).
 */
public class SimpleTree extends BaseBlockElement implements Tree, Selectable {

    @ElementName("'Cog' menu")
    @FindBy(xpath = "//table[contains(@class, 'x-grid-item-selected')]//div[@class='ibeCallOut']")
    CogMenu cogMenu;

    private static final String NODE_XPATH_PATTERN = "//span[@class='x-tree-node-text '][contains(text(),'%s')]";

    @Override
    public void selectNode(String... nodeNames) {
        if (nodeNames.length >= 1) {
            selectNode(nodeNames[0]);
        }
    }

    @Override
    public boolean isPresent(String text) {
        return $x(getNodeXPath(text)).shouldBe(visible).isDisplayed();
    }

    public void selectNode(String nodeName) {
        String nodeXPath = getNodeXPath(nodeName);
        if ($x(nodeXPath).isDisplayed()) {
            $x(nodeXPath).hover();
            $x(nodeXPath + "//div[contains(@class, 'ibeCallOut')]").shouldBe(visible).click();
            AllureUtils.saveScreenshot();
        } else {
            throw new RuntimeException(String.format("Not found node with name [%s]", nodeName));

        }
    }

    private String getNodeXPath(String nodeName) {
        return elementXpath() + String.format(NODE_XPATH_PATTERN, nodeName);
    }

    private String elementXpath() {
        return this.getFindBy().xpath();
    }

}
