package t5.ipe.cucumber.core.web.elements;

import t5.ipe.cucumber.core.web.interfaces.element_interfaces.Editable;
import t5.ipe.cucumber.core.web.interfaces.element_interfaces.Selectable;

import static com.codeborne.selenide.Selectors.byAttribute;
import static com.codeborne.selenide.Selenide.*;

public class IPEIFrameElement  extends TextElement implements Editable, Selectable {
    @Override
    public void select(String text) {
        toSelenideElement().scrollIntoView(true).click();
        switchTo().frame($x("//iframe"));
        $x("//body[@id='tinymce']").click();
        actions().sendKeys(text).perform();
        switchTo().defaultContent();
    }

    @Override
    public void enterText(String text) {
        toSelenideElement().scrollIntoView(true).click();
        switchTo().frame($x("//iframe"));
        $x("//body[@id='tinymce']").click();
        actions().sendKeys(text).perform();
        switchTo().defaultContent();
    }
}
