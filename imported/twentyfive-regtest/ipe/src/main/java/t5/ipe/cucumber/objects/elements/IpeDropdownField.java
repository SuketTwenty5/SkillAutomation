package t5.ipe.cucumber.objects.elements;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.ex.ElementNotFound;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import t5.ipe.cucumber.core.web.TestContext;
import t5.ipe.cucumber.core.web.elements.TextElement;
import t5.ipe.cucumber.core.web.interfaces.element_interfaces.Editable;
import t5.ipe.cucumber.core.web.interfaces.element_interfaces.Selectable;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import org.openqa.selenium.Keys;
import t5.ipe.cucumber.core.web.util.AllureUtils;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;


/**
 * Ipe project field with dropdown list (search list).
 * <p>
 * Created by: EKruze
 * Date: 23/112022
 */
public class IpeDropdownField extends TextElement implements Editable, Selectable {

    public void clearInputWithJS(SelenideElement input) {
        executeJavaScript("arguments[0].value = '';", input);
        executeJavaScript("arguments[0].dispatchEvent(new Event('input', { bubbles: true }));", input);
    }

    @Override
    public void enterText(String text) {

        if ("input".equalsIgnoreCase(toSelenideElement().getTagName())) {
            System.out.println("Element is an <input> tag.");
            clearInputWithJS(toSelenideElement());
        } else {
            System.out.println("Element is NOT an <input> tag. It's <" + toSelenideElement().getTagName() + ">.");
            toSelenideElement().scrollIntoView(false).clear();
        }
        toSelenideElement().sendKeys(text);
    }

    public boolean isFirstOptionVisibleAndHasChild() {
        boolean hasChild;
        try {
            SelenideElement firstOption = $x("//ul[@class='x-list-plain' and @aria-hidden='false']//*[@role='option'][1]").shouldBe(visible, Duration.ofSeconds(15));
            hasChild = !firstOption.$$x("./*").isEmpty();
        }catch (ElementNotFound | NoSuchElementException | StaleElementReferenceException e) {
            return false;
        }
        return hasChild;
    }

    List<String> warningTestData = Arrays.asList(
            "Regression Test",
            "US - New York",
            "Regression Test - Customer USD",
            "Services"
    );

    @Override
    public void select(String text) {
        SelenideElement el = $(toSelenideElement()).shouldBe(visible, Duration.ofSeconds(15));

        String actualText;

        String tag = el.getTagName().toLowerCase();

        if (tag.equals("input") || tag.equals("textarea") || tag.equals("select")) {
            actualText = el.getValue();    // for input-like elements
        } else {
            actualText = el.getText();     // for other DOM elements
        }
        if(actualText.contains(text)){
            AllureUtils.logAction("The value '" + text + "' is already selected in the dropdown");
        } else {
            enterText(text);
            Selenide.sleep(4000);
            if (isFirstOptionVisibleAndHasChild()) {
                $$x("//ul[@class='x-list-plain' and @aria-hidden='false']//*[@role='option']//*[contains(text(), '" + text + "')]").first().click();
            } else {
                boolean optionVisible = $$x("//ul[@class='x-list-plain' and @aria-hidden='false']//*[@role='option'][contains(text(), '" + text + "')]").first().is(visible);
                if(!optionVisible){
                    AllureUtils.logActionF("Option with text '{0}' visibility: {1}", text, false);
                    toSelenideElement().clear();
                    if(warningTestData.contains(text)){
                        TestContext.getInstance().softly
                                .assertThat(optionVisible)
                                .as("Option '" + text + "' should be visible. Config not found in first attempt")
                                .isTrue();
                        executeJavaScript("arguments[0].value = arguments[1];", toSelenideElement(), text);
                        if("US - New York".equals(text)){
                            executeJavaScript("arguments[0].value = arguments[1];", $x("//*[@role='tabpanel' and @aria-hidden='false']//*[@placeholder='Default=USD' and @aria-hidden='false']"), "$ USA - US Dollar(USD)");
                        }
                    } else {
                        TestContext.getInstance().softly
                                .assertThat(optionVisible)
                                .as("Option '" + text + "' should be visible")
                                .isTrue();
                    }
                } else {
                    $$x("//ul[@class='x-list-plain' and @aria-hidden='false']//*[@role='option'][contains(text(), '" + text + "')]").first().click();
                }
            }
            $(toSelenideElement()).shouldBe(Condition.value(text));
        }
    }

    @Override
    public String readText() {
        return toSelenideElement().getValue();
    }
}
