package t5.ipe.cucumber.objects.elements;

import t5.ipe.cucumber.core.web.elements.TextElement;
import t5.ipe.cucumber.core.web.interfaces.element_interfaces.Editable;

/**
 * Standard Ipe text field element.
 * <p>
 * Created by: EKruze
 * Date: 07/11/2023
 */
public class IpeField extends TextElement implements Editable {
    @Override
    public void enterText(String text) {
        toSelenideElement().scrollIntoView(false).clear();
        toSelenideElement().sendKeys(text);
    }

    @Override
    public String readText() {
        return toSelenideElement().getValue();
    }
}

