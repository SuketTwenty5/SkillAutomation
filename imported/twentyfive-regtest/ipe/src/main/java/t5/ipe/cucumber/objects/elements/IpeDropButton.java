package t5.ipe.cucumber.objects.elements;


import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Allure;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.InvalidElementStateException;
import t5.ipe.cucumber.core.web.TestContext;
import t5.ipe.cucumber.core.web.elements.BaseWebElement;
import t5.ipe.cucumber.core.web.interfaces.element_interfaces.Selectable;
import t5.ipe.cucumber.core.web.util.AllureUtils;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.executeJavaScript;

/**
 * Ipe project button with dropdown list (example - User menu button).
 * <p>
 * Created by: EKruze
 * Date: 07/11/2023
 */
public class IpeDropButton extends BaseWebElement implements Selectable {

    @Override
    public void select(String text) {
        Allure.step(String.format("Enter into select Method, searching text %s in the list", text));
        toSelenideElement().shouldBe(visible, Duration.ofSeconds(6));
        toSelenideElement().click();
        AllureUtils.saveScreenshot();
        boolean isOptionVisible = false;
        try {
            $x("//*[@role='listbox' and @aria-hidden='false']//*[@role='option']//*[text()='" + text + "']")
                    .shouldBe(visible, Duration.ofSeconds(5));
            isOptionVisible = true;
        } catch (Exception e) {
            AllureUtils.logActionF("Option with text '%s' is not visible in the dropdown list.", text);
        }
        if (!isOptionVisible) {
            TestContext.getInstance().softly
                    .assertThat(isOptionVisible)
                    .as("Option '" + text + "' should be visible. Config not found in first attempt")
                    .isTrue();
            executeJavaScript("arguments[0].value = arguments[1];", toSelenideElement(), text);
        }else {
            $x("//*[@role='listbox' and @aria-hidden='false']//*[@role='option']//*[text()='" + text + "']").scrollIntoView(true).hover().shouldBe(visible, Duration.ofSeconds(25)).click();
        }
        AllureUtils.saveScreenshot();
    }

    @Override
    public boolean isPresent(String text) {
        return $x("//*[text()='" + text + "']").shouldBe(visible).isDisplayed();
    }
}
