package t5.ipe.cucumber.objects.elements;

import com.codeborne.selenide.Selenide;
import org.openqa.selenium.Keys;
import t5.ipe.cucumber.core.web.elements.TextElement;
import t5.ipe.cucumber.core.web.interfaces.element_interfaces.Selectable;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class EstimatePopUpDropDown extends TextElement implements Selectable {

    @Override
    public void select(String text) {
        $(toSelenideElement()).scrollIntoView(true).hover();
        $(toSelenideElement()).click();
        Selenide.executeJavaScript("arguments[0].value=''", $(toSelenideElement()));
        $(toSelenideElement()).shouldBe(visible).sendKeys(text);
//        $x("//*[text()='" + text + "']").shouldBe(visible).click();
        $x("//li[contains(text(), '" + text + "')]").shouldBe(visible, Duration.ofSeconds(30)).click();
        $(String.valueOf(toSelenideElement().getText().equals(text)));
    }
}
