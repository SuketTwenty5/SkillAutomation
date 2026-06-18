package t5.ipe.cucumber.objects.elements;


import t5.ipe.cucumber.core.web.elements.BaseWebElement;
import t5.ipe.cucumber.core.web.interfaces.element_interfaces.Selectable;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;

/**
 * Ipe project button with dropdown list on Workbench page.
 */
public class WorkbenchDropButton extends BaseWebElement implements Selectable {

    @Override
    public void select(String text) {
        toSelenideElement().hover();
        $x("//a[contains (@class,'x-btn-over')]").shouldBe(visible, Duration.ofSeconds(15)).click();
        $x("//a[@data-qtip='" + text + "']").hover().click();
    }

    @Override
    public boolean isPresent(String text) {
        return $x("//*[text()='" + text + "']").shouldBe(visible).isDisplayed();
    }
}
