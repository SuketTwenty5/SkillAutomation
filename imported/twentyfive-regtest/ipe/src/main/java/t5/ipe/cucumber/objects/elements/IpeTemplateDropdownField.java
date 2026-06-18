package t5.ipe.cucumber.objects.elements;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.Keys;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static org.assertj.core.error.ShouldBe.shouldBe;

/**
 * Ipe project field with dropdown list (search list).
 */
public class IpeTemplateDropdownField extends IpeDropdownField {


    @Override
    public void select(String text) {
        SelenideElement combo = $(toSelenideElement()).shouldBe(visible);
        combo.click();
        combo.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        combo.sendKeys(text);
        executeJavaScript(
                "var cmp = Ext.Component.fromElement(arguments[0]);" +
                        "cmp.lastQuery = null;" +
                        "cmp.doQuery(arguments[1], false, true);",
                combo,
                text
        );
        $$x("//li[@role='option' and contains(@class,'x-boundlist-item')]")
                .filter(Condition.visible)
                .shouldHave(CollectionCondition.sizeGreaterThan(0), Duration.ofSeconds(20));
//        Selenide.sleep(5000);
//        $$x("//li[@role='option' and contains(@class,'x-boundlist-item')]")
//                .filter(Condition.visible)
//                .findBy(Condition.matchText(text)).scrollIntoView(true)
//                .shouldBe(visible)
//                .hover()
//                .click();
//        combo.$x("../following-sibling::*[contains(@class,'x-form-arrow-trigger')]").scrollIntoView(true).shouldBe(visible).hover().click();
//        Selenide.sleep(5000);
//        executeJavaScript(
//                "var cmp = Ext.Component.fromElement(arguments[0]);" +
//                        "var text = arguments[1];" +
//                        "cmp.focus();" +
//                        "cmp.expand();" +
//                        "cmp.inputEl.dom.value = text;" +
//                        "cmp.inputEl.dom.dispatchEvent(new Event('input', { bubbles: true }));" +
//                        "cmp.lastQuery = null;" +
//                        "cmp.doQuery(text, false, true);",
//                combo,
//                text
//        );
//        Selenide.sleep(5000);
        $x("//li[@role='option' and contains(@class,'x-boundlist-item') and contains(.,  '" + text + "')]").shouldBe(visible, Duration.ofSeconds(25)).hover().click();
//        $x("//li[contains(.,  '" + text + "')]").click();
        $(toSelenideElement()).shouldBe(Condition.value(text));
    }

}
