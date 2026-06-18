package t5.ipe.cucumber.core.web.steps;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.ex.ElementNotFound;
import io.cucumber.java.en.Then;
import io.qameta.allure.Allure;
import org.assertj.core.api.SoftAssertions;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import t5.ipe.cucumber.core.web.DateFormatConverter;
import t5.ipe.cucumber.core.web.TestContext;
import t5.ipe.cucumber.core.web.elements.BaseWebElement;
import t5.ipe.cucumber.core.web.interfaces.element_interfaces.Readable;
import t5.ipe.cucumber.core.web.interfaces.element_interfaces.*;
import t5.ipe.cucumber.core.web.util.AllureUtils;
import t5.ipe.cucumber.core.web.util.DataContainer;
import t5.ipe.cucumber.core.web.util.web.SearchUtils;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import org.junit.Assert;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Duration;
import java.util.List;
import java.util.Map;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static org.junit.Assert.assertEquals;

/**
 * Steps that implements checks of elements state.
 * <p>
 * Created by: EKruze
 * Date: 20/10/2023
 */
public class ElementCheckSteps {

//    private final TestContext context;
//
//    // ✅ Constructor injection again
//    public ElementCheckSteps(TestContext context) {
//        this.context = context;
//    }
//    TestContext context = new TestContext();

    @And("^text of (.+) equals '(.*)'$")
    public void checkIfTextInElementEquals(String textElementName, String text) {
        String expectedText = DataContainer.resolveVariablesInText(text);
        String actualText = SearchUtils.findElementAtCurrentPage(textElementName, Readable.class)
                .readText();
        if (actualText.matches(".* \\((shared|preferred|shared & preferred)\\)$")) {
            actualText = actualText.replaceAll(" \\((shared|preferred|shared & preferred)\\)$", "");
        }
        if(DateFormatConverter.parseDate(actualText) != null) {
            actualText = DateFormatConverter.convertDate(actualText);
        }
        String errorText = String.format("Actual text [%s] of element with name [%s] at page [%s] " +
                        "does not equals to expected [%s].",
                actualText, textElementName, SearchUtils.getCurrentPageName(), expectedText);
        assertEquals(errorText, expectedText, actualText);
        AllureUtils.logResult(String.format("Text of %s equals '%s'", textElementName, expectedText));
    }

    @Then("^(?:I |)verify (.*) is (.+)% of (.*)$")
    public void iVerifyFixedFeeAmountIsOfCostAmount(String resultElementName, int percentage, String baseElementName) {
        BigDecimal resultValue = parseCurrency(SearchUtils.findElementAtCurrentPage(resultElementName, Readable.class)
                .readText());
        BigDecimal baseValue = parseCurrency(SearchUtils.findElementAtCurrentPage(baseElementName, Readable.class)
                .readText());
        BigDecimal expected = baseValue.multiply(BigDecimal.valueOf(percentage)).divide(BigDecimal.valueOf(100), 2, RoundingMode.HALF_UP);
        AllureUtils.logActionF("Expected: %s, Actual: %s\n", expected, resultValue);
        AllureUtils.saveScreenshot();
        assertEquals("Result is not " + percentage + "% of base", expected, resultValue);
    }

    private BigDecimal parseCurrency(String text) {
        if (text == null || text.trim().isEmpty()) {
            return BigDecimal.ZERO;
        }
        String cleaned = text.replaceAll("[^\\d.-]", "").replace(",", "");
        return new BigDecimal(cleaned);
    }

    public static BigDecimal extractAndParseNumber(String value) {
        if (value == null || value.isEmpty()) return BigDecimal.ZERO;

        String cleaned = value.trim().replaceAll(",", "");
        String[] parts = cleaned.split("[^\\d.-]+"); // split on non-numeric parts
        for (String part : parts) {
            if (!part.isEmpty()) {
                return new BigDecimal(part);
            }
        }
        throw new NumberFormatException("No numeric part found in: " + value);
    }
    @And("^text of (.+) equals '(.*)'_-ROUNDOFF CHECK-_$")
    public void checkIfTextInElementEqualsRoundoffCheck(String textElementName, String text) {
        String expectedText = DataContainer.resolveVariablesInText(text);
        String actualText = SearchUtils.findElementAtCurrentPage(textElementName, Readable.class)
                .readText();
        if (actualText.matches(".* \\((shared|preferred|shared & preferred)\\)$")) {
            actualText = actualText.replaceAll(" \\((shared|preferred|shared & preferred)\\)$", "");
        }
        String errorText = String.format("Actual text [%s] of element with name [%s] at page [%s] " +
                        "does not equals to expected [%s].",
                actualText, textElementName, SearchUtils.getCurrentPageName(), expectedText);
        BigDecimal expected = extractAndParseNumber(expectedText).setScale(0, RoundingMode.HALF_UP);
        BigDecimal actual = extractAndParseNumber(actualText).setScale(0, RoundingMode.HALF_UP);

        System.out.println("Rounded Expected: " + expected);
        System.out.println("Rounded Actual: " + actual);

        assertEquals(errorText, expected, actual);
        AllureUtils.logActionF("Rounded value matched for %s", actualText);
        System.out.printf("DEBUG: Comparing expected='%s' with actual='%s'%n", expectedText, actualText);
        TestContext.getInstance().softly.assertThat(actualText)
                .as("Check Exact Values")
                .isEqualTo(expectedText);
    }

    @And("^(.+) contains (\\d+) items_-Warning-_$")
    public void openedMenuContainsItems_Warning_(String elementName, int itemCount) {
        AllureUtils.saveScreenshot();
        Selectable elementWithItems = SearchUtils.findElementAtCurrentPage(elementName, Selectable.class);
        int count = elementWithItems.getCount();
        System.out.println("Count : "+count);
        TestContext.getInstance().softly.assertThat(count).as(String.format("%d element(s) present in %s", count, elementName)).isEqualTo(itemCount);
    }

    @And("^(.+) contains items_-Warning-_:$")
    public void elementContainsItemsWarning(String elementName, DataTable itemNames) {
        List<String> itemNameList = itemNames.asList();
        Selectable elementWithItems = SearchUtils.findElementAtCurrentPage(elementName, Selectable.class);

        SoftAssertions softly = TestContext.getInstance().softly;

        for (String itemName : itemNameList) {
            String item = DataContainer.resolveVariablesInText(itemName);
            boolean isItemPresent;
            try {
                isItemPresent = elementWithItems.isPresent(item);
            } catch (AssertionError e) {
                isItemPresent = false;
            }
            softly.assertThat(isItemPresent)
                    .as(String.format("%s is not present in %s", item, elementName))
                    .isTrue();
        }
    }

    @And("^text of (.+) equals '(.*)'_-Warning-_$")
    public void checkWarningIfTextInElementEquals(String textElementName, String text) {
        try {
            String expectedText = DataContainer.resolveVariablesInText(text);
            String actualText = SearchUtils.findElementAtCurrentPage(textElementName, Readable.class)
                    .readText();

            String errorText = String.format(
                    "Actual text [%s] of element [%s] at page [%s] does not equal expected [%s].",
                    actualText, textElementName, SearchUtils.getCurrentPageName(), expectedText
            );

            TestContext.getInstance().softly.assertThat(actualText).as(errorText).isEqualTo(expectedText);

        } catch (Exception e) {
            TestContext.getInstance().softly.fail(
                    String.format("❗ Exception while checking text of [%s]: %s", textElementName, e.getMessage())
            );
            Allure.step("Exception occurred: " + e.getMessage());
        }
    }


    @And("^text of (.+) equals_-Warning-_:$")
    public void textOfDialogEqualsWarning(String textElementName, String text) {
        try  {
            String expectedText = DataContainer.resolveVariablesInText(text).trim(); // Trim any whitespace from expected text
            String actualText = SearchUtils.findElementAtCurrentPage(textElementName, Readable.class)
                    .readText().trim(); // Trim any whitespace from actual text
            String errorText = String.format("Actual text [%s] of element with name [%s] at page [%s] " +
                            "does not equals to expected [%s].",
                    actualText, textElementName, SearchUtils.getCurrentPageName(), expectedText);
            TestContext.getInstance().softly.assertThat(actualText).as(errorText).isEqualTo(expectedText);
            AllureUtils.logResult(String.format("Text of %s equals '%s'", textElementName, expectedText));
        }catch (Exception e) {
            TestContext.getInstance().softly.fail(
                    String.format("❗ Exception while checking text of [%s]: %s", textElementName, e.getMessage())
            );
            Allure.step("Exception occurred: " + e.getMessage());
        }
    }

    @And("^text of (.+) equals:$")
    public void textOfDialogEquals(String textElementName, String text) {
        String expectedText = DataContainer.resolveVariablesInText(text).trim(); // Trim any whitespace from expected text
        String actualText = SearchUtils.findElementAtCurrentPage(textElementName, Readable.class)
                .readText().trim(); // Trim any whitespace from actual text
        String errorText = String.format("Actual text [%s] of element with name [%s] at page [%s] " +
                        "does not equals to expected [%s].",
                actualText, textElementName, SearchUtils.getCurrentPageName(), expectedText);
        assertEquals(errorText, expectedText, actualText);
        AllureUtils.logResult(String.format("Text of %s equals '%s'", textElementName, expectedText));
    }

    @And("^text in the next fields is equal:$")
    public void enterDataIntoTheFields(Map<String, String> fieldsAndValues) {
        for (Map.Entry<String, String> entry : fieldsAndValues.entrySet()) {
            checkIfTextInElementEquals(entry.getKey(), entry.getValue());
        }
    }

    @And("^text of (.+) in (.+) with name '(.+)' equals '(.+)'$")
    public void checkIfTextInElementOfBlockEquals(String textElementName, String blockType, String blockName,
                                                  String expectedText) {
        String actualText = SearchUtils.findElementAtCurrentPage(blockType, TemplateElement.class)
                .focusOnElementByName(blockName)
                .findElementByTitle(textElementName, Readable.class)
                .readText();

        String errorText = String.format("Actual text [%s] of [%s] in [%s] with name [%s] at page [%s] " +
                        "does not equals to expected text [%s].",
                actualText, textElementName, blockType, blockName, SearchUtils.getCurrentPageName(), expectedText);
        assertEquals(errorText, expectedText, actualText);
        AllureUtils.logResultF("Text of %s in %s %s equals '%s'", textElementName, blockType, blockName, expectedText);
    }

    @And("^(.+) is displayed_-Warning-_$")
    public void elementIsDisplayedWarning(String elementName) {
        boolean isElementVisible = SearchUtils.findElementAtCurrentPage(elementName, Visible.class).isVisible();
        AllureUtils.logActionF(elementName + " is " + (isElementVisible ? "DISPLAYED" : "NOT DISPLAYED"));
        AllureUtils.saveScreenshot();
        TestContext.getInstance().softly.assertThat(isElementVisible)
                .as(String.format("%s is not displayed", elementName))
                .isTrue();
    }

    @And("^(.+) is displayed$")
    public void elementIsDisplayed(String elementName) {
        boolean isElementVisible = SearchUtils.findElementAtCurrentPage(elementName, Visible.class).isVisible();
        AllureUtils.saveScreenshot();
        Assert.assertTrue(String.format("%s is not displayed", elementName), isElementVisible);
        AllureUtils.logActionF("%s is displayed", elementName);
    }

    @And("^if (.+) displayed then click (.+)$")
    public void ifNeedsRefreshStatusDisplayedThenClickUpdateCostButton(String displayElement, String clickElement) {
        boolean isElementVisible = SearchUtils.findElementAtCurrentPage(displayElement, Visible.class).isVisible();
        if(isElementVisible){
            AllureUtils.logActionF("Enter Method clickOn with the elementName %s", clickElement);
            SearchUtils.findElementAtCurrentPage(clickElement, Clickable.class)
                    .click();
            AllureUtils.logActionF("Performed click on %s", clickElement);
        }else{
            AllureUtils.logActionF("%s is not visible", displayElement);
        }
    }

    public void closeDialogIfVisible() {
        SelenideElement closeButton = $x("//*[@role='dialog' and @aria-hidden='false']//*[@data-qtip='Close dialog']//*[@data-ref='toolEl']");

        // Wait up to 7 seconds for the button to be visible, if it exists
        if (closeButton.should(appear, Duration.ofSeconds(7)).isDisplayed()) {
            closeButton.click();
        }
    }

    @And("^if (.+) is visible then refresh page$")
    public void ifNeedsRefreshStatusDisplayedThenRefreshPage(String displayElement) throws InterruptedException {
        //------------------------------------PLEASE NOTE:-------------------------------------
        //only for Regression Test Only - Consulting and Regression Test - Customer USD proposal
        boolean isElementVisibleInitial = SearchUtils.findElementAtCurrentPage(displayElement, Visible.class).isVisible();
        if (isElementVisibleInitial) {
            for (int i = 0; i < 5; i++) {
                AllureUtils.logActionF("In %s round", Integer.toString(i));
                boolean isElementVisible = SearchUtils.findElementAtCurrentPage(displayElement, Visible.class).isVisible();
                if (isElementVisible) {
//
//                    AllureUtils.logActionF("Sleeping for 15 before clicking update cost");
                    Selenide.sleep(15000);
//                    $x("//*[@role='tabpanel' and @aria-hidden='false']//*[@titlelink='updateCostsWithFormula']").shouldBe(Condition.visible, Duration.ofSeconds(15)).click();
//                    AllureUtils.logActionF("Clicked on Update Cost. Sleeping for 20 before refresh");
//                    Selenide.sleep(40000);
//                     Clear cache via JavaScript
                    executeJavaScript("caches.keys().then(function(names) { for (let name of names) caches.delete(name); });");

                    // Optionally, clear localStorage/sessionStorage
                    executeJavaScript("localStorage.clear(); sessionStorage.clear();");
                    Selenide.refresh();
                    Selenide.sleep(30000);
//                    $x("//*[@role='tab' and @aria-selected='true']//*[text()='Setup']").shouldBe(Condition.visible, Duration.ofSeconds(60));
                    $x("//*[@role='tab']//*[text()='Workstreams']").shouldBe(Condition.visible, Duration.ofSeconds(15)).click();
                    try{
                        $x("//*[@role='tab' and @aria-selected='true']//*[text()='Workstreams']").shouldBe(Condition.visible, Duration.ofSeconds(15));
                    }catch (ElementNotFound e){
//                        closeDialogIfVisible();
                        String actualCompanyText = $x("//input[@placeholder='Enter company  name to search or click arrow for list']").shouldBe(visible, Duration.ofSeconds(15)).getValue();
                        AllureUtils.logActionF("Company Name: %s",actualCompanyText);
                        if(actualCompanyText != null && !actualCompanyText.trim().isEmpty()){
                            AllureUtils.logAction("The value Company is already selected in the dropdown");
                        } else {
                            $x("//input[@placeholder='Enter company  name to search or click arrow for list']").setValue("Regression Test - Customer USD");
                            Selenide.sleep(3000);
                            $x("//*[@role='listbox'  and @aria-hidden='false']//*[@role='option']//*[contains(text(),'Regression Test - Customer USD')]").shouldBe(visible, Duration.ofSeconds(15)).click();
                            $x("//input[@placeholder='Enter company  name to search or click arrow for list']").sendKeys(Keys.ENTER);
                            $x("//a[@data-qtip='Save']").shouldBe(visible, Duration.ofSeconds(15)).click();
                            Selenide.sleep(7000);
                        }
                        SelenideElement typeCell = $x("//*[text()='Select the Best-fit Type *']/ancestor::td[1]");
                        String actualTypeText = "";

                        try {
                            // Try normal input value first
                            actualTypeText = typeCell.$("input").getValue();
                        } catch (Exception e1) {
                            // Ignore if not found
                            AllureUtils.logActionF("Exception while getting Type input value: %s", e1.getMessage());
                        }

//                        if (actualTypeText == null || actualTypeText.trim().isEmpty()) {
//                            // Try visible text div/span if input is hidden
//                            actualTypeText = typeCell.$x(".//*[contains(@class,'x-form-display-field') or contains(@class,'x-form-field') or contains(@class,'x-form-text')]")
//                                    .getText();
//                        }

                        AllureUtils.logActionF("Type Name: %s", actualTypeText);

                        if(!actualTypeText.trim().isEmpty()){
                            AllureUtils.logAction("The value Type is already selected in the dropdown");
                        } else {
                            $x("//*[text()='Select the Best-fit Type *']/ancestor::td[1]//input").setValue("Regression Test Only - Consulting");
                            Selenide.sleep(3000);
                            $x("//*[@role='listbox'  and @aria-hidden='false']//*[@role='option'][contains(text(),'Regression Test Only - Consulting')]").shouldBe(visible, Duration.ofSeconds(15)).click();
                            $x("//*[text()='Select the Best-fit Type *']/ancestor::td[1]//input").sendKeys(Keys.ENTER);
                            $x("//a[@data-qtip='Save']").shouldBe(visible, Duration.ofSeconds(15)).click();
                            Selenide.sleep(7000);
                        }
                        $x("//*[@role='tab']//*[text()='Workstreams']").shouldBe(Condition.visible, Duration.ofSeconds(30)).click();
                        $x("//*[@role='tab' and @aria-selected='true']//*[text()='Workstreams']").shouldBe(Condition.visible, Duration.ofSeconds(30));
                    }
                } else {
                    AllureUtils.logActionF("%s is not visible in %s round", displayElement, Integer.toString(i));
                    break;
                }
            }
        }else {
            AllureUtils.logActionF("%s is not visible", displayElement);
        }
    }

    @And("^if (.+) not displayed then refresh page$")
    public void ifNeedsRefreshStatusNotDisplayedThenRefreshPage(String displayElement) throws InterruptedException {
        boolean isElementVisibleInitial = SearchUtils.findElementAtCurrentPage(displayElement, Visible.class).isVisible();
        if (!isElementVisibleInitial) {
            for (int i = 0; i < 5; i++) {
                boolean isElementVisible = SearchUtils.findElementAtCurrentPage(displayElement, Visible.class).isVisible();
                if (isElementVisible) {
                    // Clear cache via JavaScript
                    executeJavaScript("caches.keys().then(function(names) { for (let name of names) caches.delete(name); });");

                    // Optionally, clear localStorage/sessionStorage
                    executeJavaScript("localStorage.clear(); sessionStorage.clear();");
                    Selenide.refresh();
                    $x("//*[@role='tab' and @aria-selected='true']//*[text()='Setup']").shouldBe(Condition.visible, Duration.ofSeconds(30));
                    $x("//*[@role='tab' and @aria-selected='false']//*[text()='Workstreams']").shouldBe(Condition.visible, Duration.ofSeconds(30)).click();
                    $x("//*[@role='tab' and @aria-selected='true']//*[text()='Workstreams']").shouldBe(Condition.visible, Duration.ofSeconds(30));
                } else {
                    AllureUtils.logActionF("%s is not visible in %s round", displayElement, Integer.toString(i));
                    break;
                }
            }
        }else {
            AllureUtils.logActionF("%s is visible", displayElement);
        }
    }

    @And("^(.+) is not displayed$")
    public void elementIsNotDisplayed(String elementName) {
        boolean isElementInvisible = SearchUtils.findElementAtCurrentPage(elementName, Visible.class).isDisappeared();
        Assert.assertTrue(String.format("%s is displayed", elementName), isElementInvisible);
        AllureUtils.logActionF("%s is not displayed", elementName);
    }

    @And("^(.+) is selected$")
    public void elementIsSelected(String elementName) {
        boolean isElementSelected = SearchUtils.findElementAtCurrentPage(elementName, Toggle.class).isActivated();
        Assert.assertTrue(String.format("%s is not selected", elementName), isElementSelected);
        AllureUtils.logActionF("%s is selected", elementName);
    }

    @And("^(.+) is not selected$")
    public void elementIsNotSelected(String elementName) {
        boolean isElementSelected = SearchUtils.findElementAtCurrentPage(elementName, Toggle.class).isActivated();
        Assert.assertFalse(String.format("%s is selected", elementName), isElementSelected);
        AllureUtils.logActionF("%s is not selected", elementName);
    }

    @And("^'(.+)' is present in (.+)$")
    public void itemIsPresentInListElement(String itemName, String elementName) {
        String item = DataContainer.resolveVariablesInText(itemName);
        boolean isElementPresent = SearchUtils.findElementAtCurrentPage(elementName, Selectable.class).isPresent(item);
        Assert.assertTrue(String.format("%s is not displayed in %s", itemName, elementName), isElementPresent);
    }

    @And("^In (.+) all contains text '(.+)'$")
    public void elementContainsText(String elementName, String text) {
        String expectedText = DataContainer.resolveVariablesInText(text);
        Selectable elementWithItems = SearchUtils.findElementAtCurrentPage(elementName, Selectable.class);
        int itemCount = elementWithItems.getCount();
        for (int i = 0; i < itemCount; i++) {
            String item = elementWithItems.getElements().get(i);
            Assert.assertTrue(String.format("%s does not contain text %s", item, expectedText), item.contains(expectedText));
        }
    }

    @And("^(.+) contains items:$")
    public void elementContainsItems(String elementName, DataTable itemNames) {
        List<String> itemNameList = itemNames.asList();
        Selectable elementWithItems = SearchUtils.findElementAtCurrentPage(elementName, Selectable.class);
        for (String itemName : itemNameList) {
            String item = DataContainer.resolveVariablesInText(itemName);
            boolean isItemPresent = elementWithItems.isPresent(item);
            Assert.assertTrue(String.format("%s is not present in %s", item, elementName), isItemPresent);
        }
    }

    @And("^(.+) does not contain items:$")
    public void elementDoesNotContainsItems(String elementName, DataTable itemNames) {
        List<String> itemNameList = itemNames.asList();
        Selectable elementWithItems = SearchUtils.findElementAtCurrentPage(elementName, Selectable.class);
        for (String itemName : itemNameList) {
            String item = DataContainer.resolveVariablesInText(itemName);
            boolean isItemPresent = elementWithItems.isPresent(item);
            Assert.assertFalse(String.format("%s is present in %s", item, elementName), isItemPresent);
        }
    }

    @And("^'(.+)' is absent in (.+)$")
    public void itemIsAbsentInListElement(String itemName, String elementName) {
        boolean isElementPresent = SearchUtils.findElementAtCurrentPage(elementName, Selectable.class).isPresent(itemName);
        Assert.assertFalse(String.format("is displayed in %s", elementName), isElementPresent);
    }

    @And("^(.+) is enabled$")
    public void elementIsEnabled(String elementName) {
        boolean isElementEnabled = SearchUtils.findElementAtCurrentPage(elementName, Visible.class).isEnabled();
        AllureUtils.saveScreenshot();
        Assert.assertTrue(String.format("%s is not enabled", elementName), isElementEnabled);
        AllureUtils.logActionF("%s is enabled", elementName);
    }

    @And("^(.+) is disabled$")
    public void elementIsNotEnabled(String elementName) {
        boolean isElementEnabled = SearchUtils.findElementAtCurrentPage(elementName, Visible.class).isEnabled();
        AllureUtils.saveScreenshot();
        Assert.assertFalse(String.format("%s is enabled", elementName), isElementEnabled);
        AllureUtils.logActionF("%s is disabled", elementName);
    }


    @And("^(.+) is dropdown$")
    public void settingsIconIsDropdown(String elementName) {
        SelenideElement element = SearchUtils.findElementAtCurrentPage(elementName, BaseWebElement.class).toSelenideElement();
        SelenideElement anchor = element.ancestor("a");
        boolean isDropDown = anchor.$x("..//*[contains(@class,'x-btn-arrow')]").exists();
        Assert.assertTrue(String.format("%s is not dropdown", elementName), isDropDown);
        AllureUtils.logActionF("%s is dropdown", elementName);
    }


    @And("{string} warning message is displayed in {int} seconds")
    public void warningMessageDisplayed(String message, int seconds) {
        waitForMessageInDialog(message, seconds);
    }

    public void waitForMessageInDialog(String message, int seconds) {
        String xpath = "//*[text()='" + message + "']" +
                "/ancestor::*[@role='dialog' and @aria-hidden='false']";
        try{$x(xpath).shouldBe(visible, Duration.ofSeconds(seconds));}
        catch (StaleElementReferenceException e) {$x(xpath).should(visible, Duration.ofSeconds(5));}
    }
}
