package t5.ipe.cucumber.objects.elements;


import t5.ipe.cucumber.core.web.elements.AbstractCheckbox;
import t5.ipe.cucumber.core.web.util.AllureUtils;

import java.time.Duration;

import static com.codeborne.selenide.Condition.selected;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;

/**
 * RadioButton implementation for Ipe project.
 * <p>
 */
public class IpeCheckBox extends AbstractCheckbox {
    @Override
    public void activate() {
        if (!isActivated()) {
            this.toSelenideElement().scrollIntoView(true).shouldBe(visible, Duration.ofSeconds(15)).click();
            String input = this.toXpath() +"//input[@type='checkbox']";
            $x(input).shouldBe(selected, Duration.ofSeconds(7));
            AllureUtils.logActionF("Checked %s.", this.getTitle());
        } else {
            AllureUtils.logActionF("%s is already checked. Action is not performed.", this.getTitle());
        }
    }

    @Override
    public boolean isActivated() {
        this.toSelenideElement().scrollIntoView(true).shouldBe(visible, Duration.ofSeconds(15));
        String input = this.toXpath() +"//input[@type='checkbox']";
        if ($x(input).isSelected()) {
            return true;
        } else if (!$x(input).isSelected()) {
            return false;
        } else {
            throw new RuntimeException("Unknown state of " + this.getTitle()
                    + " or invalid check|uncheck xpaths are used:" + System.lineSeparator() + getCheckedXPath()
                    + System.lineSeparator() + getUnCheckedXPath());
        }
    }

    @Override
    protected String getUnCheckedXPath() {
        return this.getFindBy().xpath() + "//..//input[@type='radio' or @type='checkbox']//ancestor-or-self::div[not(contains(@class, 'x-form-cb-checked')) and (contains(@class, 'x-form-type-radio') or contains(@class,'x-form-type-checkbox'))]";
    }

    @Override
    protected String getCheckedXPath() {
        return this.getFindBy().xpath() + "//..//input[@type='radio' or @type='checkbox']//ancestor::div[(contains(@class, 'x-form-type-radio') or contains(@class,'x-form-type-checkbox')) and contains(@class, 'x-form-cb-checked')]";
    }
}
