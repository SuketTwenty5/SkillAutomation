package t5.ipe.cucumber.objects.elements;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;


/**
 * Ipe project field with dropdown list (search list).
 * <p>
 * Created by: EKruze
 * Date: 23/112022
 */
public class IpeDropdownField extends TextElement implements Editable, Selectable {
    protected static final String VISIBLE_OPTIONS_XPATH = "//ul[contains(@class,'x-list-plain') and @aria-hidden='false']//*[@role='option']";
    private static final Duration OPTION_TIMEOUT = Duration.ofSeconds(20);
    private static final Duration VALUE_TIMEOUT = Duration.ofSeconds(10);


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
            SelenideElement firstOption = $x("(" + VISIBLE_OPTIONS_XPATH + ")[1]").shouldBe(visible, Duration.ofSeconds(15));
            hasChild = !firstOption.$$x("./*").isEmpty();
        }catch (ElementNotFound | NoSuchElementException | StaleElementReferenceException e) {
            return false;
        }
        return hasChild;
    }

    protected boolean openDropdownPicker() {
        SelenideElement input = $(toSelenideElement()).shouldBe(visible, Duration.ofSeconds(10));
        String[] triggerXpaths = {
                "./ancestor::*[@data-ref='triggerWrap'][1]//*[contains(@class,'x-form-arrow-trigger')]",
                "./parent::div/following-sibling::div[contains(@class,'x-form-arrow-trigger')]",
                "./ancestor::*[@data-ref='triggerWrap'][1]//*[contains(@class,'x-form-trigger') and not(contains(@class,'x-form-clear-trigger'))]"
        };

        for (String triggerXpath : triggerXpaths) {
            try {
                SelenideElement trigger = input.$x(triggerXpath).shouldBe(visible, Duration.ofSeconds(3));
                trigger.scrollIntoView(false).click();
                Selenide.sleep(700);
                return true;
            } catch (RuntimeException ignored) {
                // Try the next trigger shape used by ExtJS combo boxes.
            }
        }

        AllureUtils.logAction("Dropdown trigger was not found for element: " + toXpath());
        return false;
    }

    protected void loadOptionsThenEnterText(String text) {
        openDropdownPicker();
        enterText(text);
        Selenide.sleep(700);

        if (visibleOptionTexts().isEmpty()) {
            AllureUtils.logAction("No dropdown options were visible after typing '" + text + "'. Clicking the arrow trigger to load options.");
            openDropdownPicker();
            Selenide.sleep(1000);
        }
    }

    protected String currentInputText(SelenideElement el) {
        String tag = el.getTagName().toLowerCase(Locale.ROOT);
        if (tag.equals("input") || tag.equals("textarea") || tag.equals("select")) {
            return el.getValue() == null ? "" : el.getValue();
        }
        return el.getText() == null ? "" : el.getText();
    }

    protected String normalizeDropdownText(String value) {
        if (value == null) {
            return "";
        }
        return value
                .replace('\u00A0', ' ')
                .replaceAll("\\s*\\|\\s*", "|")
                .replaceAll("\\s+", " ")
                .trim()
                .toLowerCase(Locale.ROOT);
    }

    protected String compactDropdownText(String value) {
        return normalizeDropdownText(value).replaceAll("[^a-z0-9]", "");
    }

    protected boolean matchesRequestedOption(String optionText, String requestedText) {
        String option = normalizeDropdownText(optionText);
        String requested = normalizeDropdownText(requestedText);
        if (option.isEmpty() || requested.isEmpty()) {
            return false;
        }
        if (option.equals(requested) || option.contains(requested)) {
            return true;
        }

        String compactOption = compactDropdownText(optionText);
        String compactRequested = compactDropdownText(requestedText);
        if (compactOption.equals(compactRequested) || compactOption.contains(compactRequested)) {
            return true;
        }

        return compactRequested.endsWith("proposal")
                && compactRequested.substring(0, compactRequested.length() - "proposal".length()).equals(compactOption);
    }

    protected SelenideElement findVisibleOptionMatching(String requestedText) {
        long deadline = System.nanoTime() + OPTION_TIMEOUT.toNanos();
        List<String> lastVisibleOptions = new ArrayList<>();

        while (System.nanoTime() < deadline) {
            lastVisibleOptions = visibleOptionTexts();
            ElementsCollection options = $$x(VISIBLE_OPTIONS_XPATH).filterBy(visible);
            for (SelenideElement option : options) {
                try {
                    if (matchesRequestedOption(option.getText(), requestedText)) {
                        return option;
                    }
                } catch (StaleElementReferenceException ignored) {
                    break;
                }
            }
            Selenide.sleep(500);
        }

        throw new AssertionError("No visible dropdown option matched '" + requestedText
                + "'. Visible options: " + lastVisibleOptions);
    }

    protected List<String> visibleOptionTexts() {
        List<String> optionTexts = new ArrayList<>();
        ElementsCollection options = $$x(VISIBLE_OPTIONS_XPATH).filterBy(visible);
        for (SelenideElement option : options) {
            try {
                String optionText = normalizeDropdownText(option.getText());
                if (!optionText.isEmpty()) {
                    optionTexts.add(option.getText().replaceAll("\\s+", " ").trim());
                }
            } catch (StaleElementReferenceException ignored) {
                return optionTexts;
            }
        }
        return optionTexts;
    }

    protected boolean valueMatchesSelection(String actualText, String requestedText, String selectedOptionText) {
        if (matchesRequestedOption(actualText, requestedText)) {
            return true;
        }
        return selectedOptionText != null
                && matchesRequestedOption(selectedOptionText, requestedText)
                && matchesRequestedOption(selectedOptionText, actualText);
    }

    protected void waitUntilValueMatchesSelection(String requestedText, String selectedOptionText) {
        long deadline = System.nanoTime() + VALUE_TIMEOUT.toNanos();
        String actualText = "";

        while (System.nanoTime() < deadline) {
            actualText = currentInputText($(toSelenideElement()).shouldBe(visible, Duration.ofSeconds(5)));
            if (valueMatchesSelection(actualText, requestedText, selectedOptionText)) {
                return;
            }
            Selenide.sleep(250);
        }

        throw new AssertionError("Dropdown value did not match requested value. Requested: '"
                + requestedText + "', selected option: '" + selectedOptionText
                + "', actual field value: '" + actualText + "'");
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

        String actualText = currentInputText(el);
        if(valueMatchesSelection(actualText, text, null)){
            AllureUtils.logAction("The value '" + text + "' is already selected in the dropdown");
        } else {
            loadOptionsThenEnterText(text);
            String selectedOptionText = null;
            boolean optionVisible = true;
            try {
                SelenideElement option = findVisibleOptionMatching(text);
                selectedOptionText = option.getText().replaceAll("\\s+", " ").trim();
                option.scrollIntoView(false).hover().click();
            } catch (AssertionError error) {
                optionVisible = false;
                AllureUtils.logAction(error.getMessage());
                toSelenideElement().clear();
                if(warningTestData.contains(text)){
                    TestContext.getInstance().softly
                            .assertThat(false)
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
            }
            waitUntilValueMatchesSelection(text, selectedOptionText);
        }
    }

    @Override
    public String readText() {
        return toSelenideElement().getValue();
    }
}
