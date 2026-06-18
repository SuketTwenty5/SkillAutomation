package t5.ipe.cucumber.core.web.elements;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import t5.ipe.cucumber.core.web.interfaces.element_interfaces.Editable;
import t5.ipe.cucumber.core.web.interfaces.element_interfaces.Selectable;
import t5.ipe.cucumber.core.web.util.AllureUtils;

import static com.codeborne.selenide.Selenide.*;

/**
 * Ipe project field with custom dropdown list (search list e.g. filter field).
 * <p>
 * Created by: EKruze
 * Date: 23/112022
 */
public class IpeCustomDropdownField extends TextElement implements Editable, Selectable {


//    @Override
//    public void enterText(String text) {
//        toSelenideElement().scrollIntoView(false).clear();
//        AllureUtils.saveScreenshot();
//        toSelenideElement().sendKeys(text);
//        AllureUtils.saveScreenshot();
//    }
    @Override
    public void enterText(String text) {
        AllureUtils.logActionF("Value before clearing: '%s'", toSelenideElement().getValue());
        toSelenideElement().scrollIntoView(false).clear();
        executeJavaScript("arguments[0].value=''; arguments[0].dispatchEvent(new Event('input')); arguments[0].dispatchEvent(new Event('change'));", toSelenideElement());
        String textAfterClear = toSelenideElement().getValue();
        AllureUtils.logActionF("Value after clearing: '%s'", textAfterClear);
        if(textAfterClear != null && !textAfterClear.isEmpty()){
            AllureUtils.saveScreenshot();
            throw new RuntimeException(String.format("❌ Unable to clear field '%s'. Value after clearing is: '%s'", toSelenideElement().toString(), textAfterClear));
        }
        toSelenideElement().sendKeys(text);
        AllureUtils.saveScreenshot();
    }

    @Override
    public void select(String text) {
        enterText(text);
        Selenide.sleep(1500);
        AllureUtils.saveScreenshot();
        if(!text.isEmpty()) {
            $$x("//li[contains(text(), '" + text + "')]").first().click();
        }
        $(toSelenideElement()).shouldBe(Condition.value(text));
    }

    @Override
    public String readText() {
        return toSelenideElement().getValue();
    }
}
