package t5.ipe.cucumber.core.web.elements;

import t5.ipe.cucumber.core.web.interfaces.element_interfaces.Toggle;
import t5.ipe.cucumber.core.web.util.AllureUtils;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;

/**
 * Default checkbox implementation (based on XPath locators).
 * <p>
 * Created by: EKruze
 * Date: 20/10/2023
 */
public abstract class AbstractCheckbox extends TextElement implements Toggle {

    @Override
    public void activate() {
        if (!isActivated()) {
            this.toSelenideElement().scrollIntoView(true).click();
            $x(getCheckedXPath()).shouldBe(visible);
            AllureUtils.logActionF("Checked %s.", this.getTitle());
        } else {
            AllureUtils.logActionF("%s is already checked. Action is not performed.", this.getTitle());
        }
    }

    @Override
    public void deactivate() {
        if (isActivated()) {
            this.toSelenideElement().scrollIntoView(false).click();
            $x(getUnCheckedXPath()).shouldBe(visible);
            AllureUtils.logActionF("Unchecked %s.", this.getTitle());
        } else {
            AllureUtils.logActionF("%s is already unchecked. Action is not performed.", this.getTitle());
        }
    }

    @Override
    public boolean isActivated() {
        this.toSelenideElement().scrollIntoView(true).shouldBe(visible);
        if ($x(getCheckedXPath()).is(visible)) {
            return true;
        } else if ($x(getUnCheckedXPath()).is(visible)) {
            return false;
        } else {
            throw new RuntimeException("Unknown state of " + this.getTitle()
                    + " or invalid check|uncheck xpaths are used:" + System.lineSeparator() + getCheckedXPath()
                    + System.lineSeparator() + getUnCheckedXPath());
        }
    }

    protected abstract String getUnCheckedXPath();

    protected abstract String getCheckedXPath();
}
