package t5.ipe.cucumber.objects.elements;

import com.codeborne.selenide.SelenideElement;
import t5.ipe.cucumber.core.web.elements.TextElement;
import t5.ipe.cucumber.core.web.interfaces.element_interfaces.Editable;
import t5.ipe.cucumber.core.web.util.AllureUtils;

import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.switchTo;

public class IpeIframe extends TextElement implements Editable {
    @Override
    public void enterText(String text) {
        toSelenideElement().scrollIntoView(false).clear();
        toSelenideElement().sendKeys(text);
    }

     @Override
    public String readText() {
         SelenideElement iframe = $x("//*[@role='application' and @aria-disabled='false']//iframe");
         switchTo().frame(iframe);
         AllureUtils.saveScreenshot();
         String text =  toSelenideElement().getText();
         AllureUtils.logActionF("Read text from iframe: {0}", text);
         switchTo().defaultContent();
         return text;
    }
}
