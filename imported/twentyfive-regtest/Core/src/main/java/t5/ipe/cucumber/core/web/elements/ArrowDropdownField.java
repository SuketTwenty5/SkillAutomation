package t5.ipe.cucumber.core.web.elements;

import org.openqa.selenium.Keys;
import t5.ipe.cucumber.core.web.interfaces.element_interfaces.Selectable;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

/**
 * Ipe view field with dropdown list (search list).
 * <p>
 * Created by: EKruze
 * Date: 01/03/2023
 */
public class ArrowDropdownField extends TextElement implements Selectable {

    @Override
    public void select(String text) {
        $(toSelenideElement()).scrollIntoView(true).hover();
        $(toSelenideElement()).scrollIntoView(true).click();
        $(toSelenideElement()).sendKeys(Keys.ARROW_DOWN);
        $x("//li[contains(text(), '" + text + "')]").shouldBe(visible).click();
        $(String.valueOf(toSelenideElement().getText().equals(text)));
    }

    @Override
    public String readText() {
        return toSelenideElement().getText();
    }
}
