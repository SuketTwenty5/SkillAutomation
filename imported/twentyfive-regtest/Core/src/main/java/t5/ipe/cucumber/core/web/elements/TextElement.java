package t5.ipe.cucumber.core.web.elements;

import t5.ipe.cucumber.core.web.interfaces.element_interfaces.Readable;

/**
 * Base implementation of element with text.
 *
 * Created by: EKruze
 * Date: 20/10/2023
 */
public class TextElement extends BaseWebElement implements Readable {

    @Override
    public String readText() {
        return toSelenideElement().getText();
    }
}
