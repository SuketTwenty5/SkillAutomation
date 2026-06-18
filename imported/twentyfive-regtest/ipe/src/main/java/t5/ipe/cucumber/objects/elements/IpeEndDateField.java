package t5.ipe.cucumber.objects.elements;

import com.codeborne.selenide.Selenide;
import t5.ipe.cucumber.core.web.elements.TextElement;
import t5.ipe.cucumber.core.web.interfaces.element_interfaces.Editable;

public class IpeEndDateField extends TextElement implements Editable {
    @Override
    public void enterText(String text) {
        toSelenideElement().scrollIntoView(true).click();
        Selenide.sleep(2000);
        toSelenideElement().scrollIntoView(false).clear();
        toSelenideElement().sendKeys(text);
    }

    @Override
    public String readText() {
        return toSelenideElement().getValue();
    }
}
