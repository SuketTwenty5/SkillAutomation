package t5.ipe.cucumber.objects.elements;

import t5.ipe.cucumber.core.web.elements.TextElement;
import t5.ipe.cucumber.core.web.interfaces.element_interfaces.Selectable;
import t5.ipe.cucumber.core.web.util.AllureUtils;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class TableColumn  extends TextElement implements Selectable {
    @Override
    public void select(String text) {
        int maxTries = 5;
        int currentTry = 0;
        while (currentTry < maxTries && !($x("//span[contains(text(), '" + text + "')]").isDisplayed())) {
            $(toSelenideElement().scrollIntoView(true)).click();
            currentTry++;
        }
        if ($x("//span[contains(text(), '" + text + "')]").isDisplayed()) {
            $x("//span[contains(text(), '" + text + "')]").shouldBe(visible).click();
            AllureUtils.saveScreenshot();
        }
    }

    @Override
    public String readText() {
        return toSelenideElement().getValue();
    }

    @Override
    public boolean isPresent(String text) {
        String xpath = toXpath() +"//*[text()='" + text + "']";
        return $x(xpath).shouldBe(visible).isDisplayed();
    }

    @Override
    public int getCount() {
        String childXpath = toXpath() +"/*";
        return $$x(childXpath).size();
    }
}
