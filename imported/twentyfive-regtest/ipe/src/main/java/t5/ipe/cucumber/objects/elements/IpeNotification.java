package t5.ipe.cucumber.objects.elements;

import org.openqa.selenium.StaleElementReferenceException;
import t5.ipe.cucumber.core.web.elements.BaseWebElement;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;

public class IpeNotification extends BaseWebElement {
    @Override
    public boolean isVisible() {
        try {
            waitUntilBecomesVisible();
            return true;
        } catch (AssertionError e) {
            return false;
        }
    }

    @Override
    public void waitUntilBecomesVisible() {
        try {
            $x(toXpath()).should(visible, Duration.ofSeconds(20));
        }
        catch (StaleElementReferenceException e){
            $x(toXpath()).should(visible, Duration.ofSeconds(30));
        }
    }
}
