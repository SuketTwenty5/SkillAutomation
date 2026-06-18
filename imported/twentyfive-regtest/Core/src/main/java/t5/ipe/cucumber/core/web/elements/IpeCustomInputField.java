package t5.ipe.cucumber.core.web.elements;

import org.junit.Assert;
import t5.ipe.cucumber.core.web.interfaces.element_interfaces.Editable;
import t5.ipe.cucumber.core.web.interfaces.element_interfaces.Selectable;
import t5.ipe.cucumber.core.web.util.AllureUtils;

import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.executeJavaScript;

public class IpeCustomInputField extends TextElement implements Editable {

    @Override
    public void enterText(String text) {
        AllureUtils.logActionF("Value before clearing: '%s'", toSelenideElement().getValue());
        toSelenideElement().scrollIntoView(false).clear();
        executeJavaScript("arguments[0].value=''; arguments[0].dispatchEvent(new Event('input')); arguments[0].dispatchEvent(new Event('change'));", toSelenideElement());
        String textAfterClear = toSelenideElement().getValue();
        AllureUtils.logActionF("Value after clearing: '%s'", textAfterClear);
        if(textAfterClear != null && !textAfterClear.isEmpty() && !textAfterClear.equals("0.00")){
            AllureUtils.saveScreenshot();
            throw new RuntimeException(String.format("❌ Unable to clear field '%s'. Value after clearing is: '%s'", toSelenideElement().toString(), textAfterClear));
        }
        toSelenideElement().setValue(text);
        $x("//body[@role='application']").click();
        if(!toSelenideElement().getValue().equals(text)){
            executeJavaScript("arguments[0].value=''; arguments[0].dispatchEvent(new Event('input')); arguments[0].dispatchEvent(new Event('change'));", toSelenideElement());
            toSelenideElement().sendKeys(text);
            $x("//body[@role='application']").click();
        }
        Assert.assertEquals("❌ Text entry failed for field.", text, toSelenideElement().getValue());
    }

    @Override
    public String readText() {
        toSelenideElement().scrollIntoView(true);
        return toSelenideElement().val();
    }
}
