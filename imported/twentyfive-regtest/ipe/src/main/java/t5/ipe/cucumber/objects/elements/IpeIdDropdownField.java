package t5.ipe.cucumber.objects.elements;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import t5.ipe.cucumber.core.web.util.AllureUtils;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$x;

public class IpeIdDropdownField extends IpeDropdownField{
    public void select(String text) {
        SelenideElement element = $(toSelenideElement()).shouldBe(visible, Duration.ofSeconds(15));
        String actualText;
        if (element.getTagName().equalsIgnoreCase("input") || element.getTagName().equalsIgnoreCase("textarea")) {
            actualText = element.getValue();
        } else {
            actualText = element.getText();
        }
        System.out.println("Actual text in dropdown: '" + actualText + "'");
        if(actualText.contains(text)){
            AllureUtils.logAction("The value '" + text + "' is already selected in the dropdown");
        } else {
            enterText(text);
//            if (isFirstOptionVisibleAndHasChild()) {
            $$x("//ul[@class='x-list-plain' and @aria-hidden='false']//*[@role='option']//*[contains(text(), '" + text + "')]").first().shouldBe(visible, Duration.ofSeconds(15)).hover().click();
//            } else {
//            try {
//                $$x("//ul[@class='x-list-plain' and @aria-hidden='false']//*[@role='option'][contains(text(), '" + text + "')]").first().click();
//            }
//            catch (ElementNotFound | NoSuchElementException | StaleElementReferenceException e) {
//                AllureUtils.logAction("❌ No options found in the dropdown to select '" + text + "'");
//            }
//            }
            $(toSelenideElement()).shouldBe(Condition.value(text));
        }
    }

}
