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
public class IpeRadioButton extends AbstractCheckbox {
    @Override
    protected String getUnCheckedXPath() {
        return this.getFindBy().xpath() + "//..//input[@type='radio']//ancestor-or-self::div[not(contains(@class, 'x-form-cb-checked')) and (contains(@class, 'x-form-type-radio'))]";
    }

    @Override
    protected String getCheckedXPath() {
        return this.getFindBy().xpath() + "//..//input[@type='radio']//ancestor::div[contains(@class, 'x-form-type-radio') and contains(@class, 'x-form-cb-checked')]";
    }
}
