package t5.ipe.cucumber.core.web.elements;

import com.codeborne.selenide.Condition;

import java.time.Duration;

/**
 * Base class element. It is required to specify it as a parent for the rest of the single element implementations,
 * since it contains the web-element initialization logic.
 * Created by: EKruze
 * Date:20/10/2023
 */
public class LongWaitElement extends BaseWebElement {

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
    public boolean isDisappeared() {
        try {
            waitUntilBecomesInvisible();
            return true;
        } catch (AssertionError e) {
            return false;
        }
    }

    @Override
    public void waitUntilBecomesVisible() {
        toSelenideElement().should(Condition.visible, Duration.ofSeconds(130));
    }

    @Override
    public void waitUntilBecomesInvisible() {
        toSelenideElement().shouldNot(Condition.exist, Duration.ofSeconds(100));
    }
}
