package t5.ipe.cucumber.objects.elements;

import com.codeborne.selenide.SelenideElement;
import t5.ipe.cucumber.core.web.util.AllureUtils;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class IpeIdDropdownField extends IpeDropdownField{
    public void select(String text) {
        SelenideElement element = $(toSelenideElement()).shouldBe(visible, Duration.ofSeconds(15));
        String actualText = currentInputText(element);
        System.out.println("Actual text in dropdown: '" + actualText + "'");
        if(valueMatchesSelection(actualText, text, null)){
            AllureUtils.logAction("The value '" + text + "' is already selected in the dropdown");
        } else {
            loadOptionsThenEnterText(text);
            SelenideElement option = findVisibleOptionMatching(text);
            String selectedOptionText = option.getText().replaceAll("\\s+", " ").trim();
            AllureUtils.logAction("Selecting dropdown option '" + selectedOptionText + "' for requested value '" + text + "'");
            option.scrollIntoView(false).hover().click();
            waitUntilValueMatchesSelection(text, selectedOptionText);
        }
    }

}
