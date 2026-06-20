package t5.ipe.cucumber.objects.steps;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import static org.junit.Assert.assertFalse;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.eo.Se;
import io.qameta.allure.Allure;
import okhttp3.*;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Assert;
import java.io.IOException;
import javax.net.ssl.*;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;

import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import t5.ipe.cucumber.core.web.TestContext;
import t5.ipe.cucumber.core.web.elements.TextElement;
import t5.ipe.cucumber.core.web.interfaces.element_interfaces.Visible;
import t5.ipe.cucumber.core.web.util.AllureUtils;
import t5.ipe.cucumber.core.web.util.DataContainer;
import t5.ipe.cucumber.core.web.util.web.SearchUtils;
import t5.ipe.cucumber.core.web.util.web.WebUtil;
import t5.ipe.cucumber.objects.*;
import org.openqa.selenium.JavascriptExecutor;
import t5.ipe.cucumber.objects.elements.tables.WBSTable;
import t5.ipe.cucumber.objects.elements.tables.proposalTable;
import java.time.Duration;
import java.util.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static t5.ipe.cucumber.core.web.util.AllureUtils.logActionF;
import static t5.ipe.cucumber.core.web.util.AllureUtils.saveScreenshot;
import static t5.ipe.cucumber.core.web.util.web.WebUtil.setCustomTimeoutSec;
import static t5.ipe.cucumber.core.web.util.web.WebUtil.setDefaultTimeout;
import static com.codeborne.selenide.CollectionCondition.*;


public class setupPageSteps {

//    private TestContext context;

//    public setupPageSteps() {
//        // Provide default context or dummy fallback
//        this.context = new TestContext();
//    }

//    public setupPageSteps(TestContext context) {
//        this.context = context;
//    }

    @Then("^(?:I |)verify '(.+)' of (.+) page is (.+), (.+), (.+) and (.+)$")
    public void iVerifyContentsOfProjectDetailsSection(String field,String page, String r1, String r2, String r3, String r4){
        String tz = executeJavaScript(
                "return Intl.DateTimeFormat().resolvedOptions().timeZone;"
        );
        System.out.println("Browser Timezone: " + tz);
        if (tz == null) {
            throw new RuntimeException("Browser timezone is null");
        }
        ZoneId zoneId = ZoneId.of(tz);
        ZonedDateTime now = ZonedDateTime.now(zoneId);
        LocalDate today = now.toLocalDate();
        System.out.println("Browser TZ: " + tz);
        System.out.println("Browser Now: " + now);
        System.out.println("Computed Today: " + today);
        LocalDate oneYearLaterMinusOneDay = today.plusYears(1).minusDays(1);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/yy");
        DateTimeFormatter formatterLong = DateTimeFormatter.ofPattern("M/d/yyyy");

//        saveScreenshot();

        String labelXPath;
        if(Objects.equals(field,"Margin")) {
            labelXPath = "//*[text()='" + field + "']";
        } else {
            labelXPath = "//*[contains(text(),'" + field + "')]";
        }
        if((Objects.equals(page,"WBS Dialog") || page.contains("Dialog")|| page.contains("dialog")) && !(page.contains("table") || page.contains("Table"))) {
            labelXPath = "//*[@role='dialog' and @aria-hidden='false']//*[contains(text(),'"+field+"')]";
        } else if ((page.contains("table") || page.contains("Table")) && (page.contains("Dialog") || page.contains("dialog"))) {
            labelXPath = "//*[@role='dialog' and @aria-hidden='false']//*[@role='tabpanel' and @aria-hidden='false']//*[contains(text(),'"+field+"')]";
        }
        String xpath = labelXPath +"/ancestor::div[contains(@class,'x-field ')]//*[self::input or self::textarea][@aria-hidden='false' or @aria-readonly='false']";
        ElementsCollection inputElements = $$x(xpath);
        SelenideElement inputField;
        if(inputElements.size()>1) {
            inputField = inputElements.first();
        }else{
            inputField =$x(xpath);
        }
        if(Objects.equals(field, "Select Tag to Add")){
            labelXPath ="//label[text()='Use Tags to add New Dimensions to your Cost & Revenue Model']";
            xpath = "//input[@placeholder='"+field+"']";
            inputField= $x(xpath);
        } else if(Objects.equals(field, "WBS managed in SAP") || Objects.equals(field, "multiple phases")){
            inputElements = $$x("//*[contains(text(),'"+field+"')]/ancestor::div[contains(@class,'x-form-checkboxgroup')]//input");
            inputField = inputElements.first();
        }
        if(!r4.contains("has tags")) {
            inputField.scrollIntoView(true);
        }

        try {
            Allure.step("Checking " + field);
            if(Objects.equals(r1, "enabled")) {
                Allure.step("Locating the field using XPath: " + xpath);
                // Verify field is enabled
                boolean isEnabled = inputField.isEnabled();
                Allure.step("Field enabled: " + isEnabled);
                assertTrue("❌ Field is not enabled", isEnabled);
            } else if (Objects.equals(r1, "not enabled") || Objects.equals(r1, "disabled")) {
                Allure.step("Locating the field using XPath: " + xpath);
                // Verify field is enabled
                boolean isEnabled = inputField.isEnabled();
                Allure.step("Field enabled: " + isEnabled);
                assertFalse("❌ Field is enabled", isEnabled);
            }else {
                assertEquals("Element is enabled or not should be specified", "Element is enabled or not, is not specified");
            }

            if(Objects.equals(r3, "free text")) {
                // Verify field is input or textarea
                String tagName = inputField.getTagName();
                Allure.step("Field tag: " + tagName);
                assertTrue("❌ Field is not a free-text input (input or textarea)",
                        tagName.equalsIgnoreCase("input") || tagName.equalsIgnoreCase("textarea"));
            } else if (Objects.equals(r3, "drop down")){
                String dropDownXpath = xpath +"/ancestor::div[@data-ref='inputWrap']/following-sibling::div[contains(@class,'x-form-arrow-trigger')]";
                String text = "";
                if($x(dropDownXpath).isDisplayed()){
                    text = "displayed";
                }else {
                    text = "not displayed";
                }
                Allure.step("Dropdown is" + text);
                assertTrue("❌ Field is not a dropdown", $x(dropDownXpath).isDisplayed());
            } else if (Objects.equals(r3, "date picker")){
                String datePickerXpath = xpath +"/parent::div/following-sibling::div[contains(@class,'x-form-date-trigger')]";
                String text = "";
                if($x(datePickerXpath).isDisplayed()){
                    text = "displayed";
                }else {
                    text = "not displayed";
                }
                Allure.step("Date picker is" + text);
                assertTrue("❌ Field is not a date picker", $x(datePickerXpath).isDisplayed());
            }else if (Objects.equals(r3, "radio buttons")){
                for (SelenideElement input : inputElements) {
                    String type = input.getAttribute("type");
                    AllureUtils.logActionF("Element Name: "+input+" | Input type: " + type);
                    assertEquals("❌ Expected input type to be 'radio'", "radio", type);
                }
            }else if (Objects.equals(r3, "check box")){
                for (SelenideElement input : inputElements) {
                    String type = input.getAttribute("type");
                    AllureUtils.logActionF("Element Name: "+input+" | Input type: " + type);
                    assertEquals("❌ Expected input type to be 'radio'", "checkbox", type);
                }
            }else if (Objects.equals(r3, "numeric selection")){
                String numericSelectionXpath = labelXPath+"/ancestor::div[contains(@class,'x-field ')]//*[contains(@class, 'x-form-spinner-up') or contains(@class, 'x-form-spinner-down')]";
                ElementsCollection spinners = $$x(numericSelectionXpath);
                int expectedCount = 2;
                int actualCount = spinners.size();
                assertEquals("Expected 2 spinner elements, but found: " + actualCount, expectedCount, actualCount);
            } else {
                assertEquals("Type of Element", "No type of Element Found");
            }

            if(Objects.equals(r4, "is empty")){
                // Verify field is empty
                String value = inputField.getValue();
                Allure.step("Field value: '" + value + "'");
                assertTrue("❌ Field is not empty", value == null || value.trim().isEmpty());
            } else if(r4.contains("has text")){
                String extracted = r4.replaceAll(".*has text '(.*?)'.*", "$1");
                String value = inputField.getValue();
                assert value != null;
//                if (value.equals("$")){
//
//                }
                if(!extracted.contains("$")) {
                    if(NumberStringDetector.isNumber(extracted)) {
//                        extracted = extracted.replaceAll("\\s*hours\\s*", "").trim();
//                        extracted = extracted.replace(",", "");
                        AllureUtils.logAction("Both values are detected as numbers, proceeding with numeric comparison.");
                        double extractedRounded = Math.round(NumberStringDetector.extractDouble(extracted) * 10.0) / 10.0;
                        double valueRounded = Math.round(NumberStringDetector.extractDouble(value) * 10.0) / 10.0;
                        Allure.step("Checking extracted value: " + extracted + " exists");
                        Assert.assertEquals(
                                "Actual value is " + value + " and expected value is " + extracted + " up to one decimal place",
                                extractedRounded, valueRounded, 0.0 // no tolerance since rounded already
                        );
                    } else if (DateDetector.isDate(extracted) || DateDetector.isDate(value)) {
                        AllureUtils.logAction("Both values are detected as dates, proceeding with date comparison.");
                        Set<String> extractedDates = DateDetector.convertToAllFormats(extracted);
                        String finalValue = value;
                        boolean matchFound = extractedDates.stream().anyMatch(date -> date.equals(finalValue));
                        Allure.step("Checking extracted value: " + extracted + " exists in any of the formats: " + extractedDates);
                        Assert.assertTrue(
                                "Actual value is " + value + " and expected value is " + extracted + " in any recognized date format",
                                matchFound
                        );
                    } else {
                        AllureUtils.logActionF("At least one value is not a number, proceeding with string comparison.");
                        Allure.step("Checking extracted value:- " + extracted + " exists");
                        if(extracted.equals("QT-#####")){
                            AllureUtils.logActionF("Actual Document Number PI value before masking: " + value);
                            value = value.replaceAll("QT-\\d{5}", "QT-#####");
                        }
                        if (isLocalRun() && field.equalsIgnoreCase("Project Manager") && !extracted.equals(value)) {
                            AllureUtils.logActionF("WARNING: Project Manager value differs in local run. Expected '%s', actual '%s'. Continuing.", extracted, value);
                            return;
                        }
                        Assert.assertEquals("Actual value is " + value + " and expected value is " + extracted, extracted, value);
                    }
                } else if (extracted.equals("$todayDate")) {
                    extracted =today.format(formatter);
                    boolean isDateShortMatch = extracted.equals(value);
                    AllureUtils.logActionF("Comparing today's date in short format: extracted='%s', actual='%s'", extracted, value);
                    extracted = today.format(formatterLong);
                    boolean isDateLongMatch = extracted.equals(value);
                    AllureUtils.logActionF("Comparing today's date in long format: extracted='%s', actual='%s'", extracted, value);
                    Allure.step("Checking extracted value:- "+extracted+" exists");
                    Assert.assertTrue("Actual value is " + value + " and expected value is " + extracted, isDateShortMatch || isDateLongMatch);
                } else if (extracted.equals("$oneYearFromToday")){
                    extracted = oneYearLaterMinusOneDay.format(formatter);
                    boolean isDateShortMatch = extracted.equals(value);
                    AllureUtils.logActionF("Comparing one year from today's date in short format: extracted='%s', actual='%s'", extracted, value);
                    extracted = oneYearLaterMinusOneDay.format(formatterLong);
                    boolean isDateLongMatch = extracted.equals(value);
                    AllureUtils.logActionF("Comparing one year from today's date in long format: extracted='%s', actual='%s'", extracted, value);
                    Allure.step("Checking extracted value:- "+extracted+" exists");
                    Assert.assertTrue("Actual value is " + value + " and expected value is " + extracted, isDateShortMatch || isDateLongMatch);
                }
            } else if(r4.contains("is unchecked")){
                for (int i = 0; i < inputElements.size(); i++) {
                    SelenideElement input = inputElements.get(i);
                    boolean isSelected = input.isSelected();
                    Allure.step("Checking element " + (i + 1) + ": isSelected = " + isSelected);
                    assertFalse("❌ Element " + (i + 1) + " is checked, expected to be unchecked", isSelected);
                }
            }else if(r4.contains("is checked")){
                boolean anySelected = false;
                for (int i = 0; i < inputElements.size(); i++) {
                    SelenideElement radio = inputElements.get(i);
                    boolean selected = radio.isSelected();
                    Allure.step("Radio " + (i + 1) + " selected: " + selected);
                    if (selected) {
                        anySelected = true;
                        break;
                    }
                }
                assertTrue("❌ None of the radio buttons are selected", anySelected);
            }else if(r4.contains("has tags")){

                int start = r4.indexOf('\'') + 1;
                int end = r4.lastIndexOf('\'');
                String insideQuotes = r4.substring(start, end);

                String[] expectedTags = insideQuotes.split(",");
                for (int i = 0; i < expectedTags.length; i++) {
                    expectedTags[i] = expectedTags[i].trim();
                }

                String tagXpath = labelXPath+"/ancestor::div[contains(@class,'x-field ')]//*[@class='x-tagfield-item-text']";
                ElementsCollection tagElements = $$x(tagXpath)
                        .shouldBe(sizeGreaterThan(0), Duration.ofSeconds(15));

                String[] actualText = new String[tagElements.size()];
                for (int i = 0; i < tagElements.size(); i++) {
                    actualText[i] = tagElements.get(i).shouldBe(visible, Duration.ofSeconds(15)).getText().trim();
                }
                Assert.assertArrayEquals("Expected Tags are not found",expectedTags,actualText);
            }else {
                assertEquals("Behaviour of Element", "No Behaviour Found");
            }

            if(Objects.equals(r2, "required")){
                // Locate and verify the label for required marker
                SelenideElement labelElement = $x(labelXPath);
                String labelText = labelElement.getText().trim();
                Allure.step("Field label text: '" + labelText + "'");

                boolean isRequired = labelText.contains("*");
                Allure.step("Is field required (based on '*'): " + isRequired);

                // Example assertion — can be assertTrue or assertFalse based on your use case
                assertTrue("❌ " + field + " field is not marked with *", isRequired);
            } else if (Objects.equals(r2, "not required")) {
                SelenideElement labelElement = $x(labelXPath);
                String labelText = labelElement.getText().trim();
                Allure.step("Field label text: '" + labelText + "'");

                boolean isRequired = labelText.contains("*");
                Allure.step("Is field required (based on '*'): " + isRequired);

                // Example assertion — can be assertTrue or assertFalse based on your use case
                assertFalse("❌ " + field + " field is marked with *", isRequired);
            }else {
                assertEquals("Requirement to be specified", "No requirement specified");
            }

//            Allure.step("✅ "+field+ "field is enabled, empty, free-text, and marked as required.");
        } catch (AssertionError | Exception e) {
            Allure.step("❌ "+field+" verification failed: " + e.getMessage());
            throw e;
        }



    }

//    @Then("^(?:I |)note '(.+)' of (.+) page is (.+), (.+), (.+) and (.+)$")
//    public void iNoteContentsOfProjectDetailsSection(String field, String page, String r1, String r2, String r3, String r4) {
//        LocalDate today = LocalDate.now();
//        LocalDate oneYearLaterMinusOneDay = today.plusYears(1).minusDays(1);
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/yy");
//
//        String labelXPath = "//*[contains(text(),'" + field + "')]";
//        if (Objects.equals(page, "WBS Dialog")) {
//            labelXPath = "//*[@role='dialog' and @aria-hidden='false']//*[contains(text(),'" + field + "')]";
//        }
//        String xpath = labelXPath + "/ancestor::div[contains(@class,'x-field ')]//input";
//        ElementsCollection inputElements = $$x(xpath);
//        SelenideElement inputField;
//        if (inputElements.size() > 1) {
//            inputField = inputElements.first();
//        } else {
//            inputField = $x(xpath);
//        }
//        if (Objects.equals(field, "Select Tag to Add")) {
//            labelXPath = "//label[text()='Use Tags to add New Dimensions to your Cost & Revenue Model']";
//            xpath = "//input[@placeholder='" + field + "']";
//            inputField = $x(xpath);
//        } else if (Objects.equals(field, "WBS managed in SAP") || Objects.equals(field, "multiple phases")) {
//            inputElements = $$x("//*[contains(text(),'" + field + "')]/ancestor::div[contains(@class,'x-form-checkboxgroup')]//input");
//            inputField = inputElements.first();
//        }
//        if (!r4.contains("has tags")) {
//            inputField.scrollIntoView(true);
//        }
//
//        try {
//            Allure.step("Checking " + field);
//            if (Objects.equals(r1, "enabled")) {
//                Allure.step("Locating the field using XPath: " + xpath);
//                boolean isEnabled = inputField.isEnabled();
//                Allure.step("Field enabled: " + isEnabled);
//                context.softly
//                        .assertThat(isEnabled)
//                        .overridingErrorMessage("❌ Field is not enabled")
//                        .isTrue();
//            } else if (Objects.equals(r1, "not enabled")) {
//                Allure.step("Locating the field using XPath: " + xpath);
//                boolean isEnabled = inputField.isEnabled();
//                Allure.step("Field enabled: " + isEnabled);
//                context.softly
//                        .assertThat(isEnabled)
//                        .overridingErrorMessage("❌ Field is enabled")
//                        .isFalse();
//            } else {
//                context.softly.fail("Element is enabled or not should be specified");
//            }
//
//            if (Objects.equals(r3, "free text")) {
//                String tagName = inputField.getTagName();
//                Allure.step("Field tag: " + tagName);
//                context.softly
//                        .assertThat(tagName.equalsIgnoreCase("input") || tagName.equalsIgnoreCase("textarea"))
//                        .overridingErrorMessage("❌ Field is not a free-text input (input or textarea)")
//                        .isTrue();
//            } else if (Objects.equals(r3, "drop down")) {
//                String dropDownXpath = xpath + "/ancestor::div[@data-ref='inputWrap']/following-sibling::div[contains(@class,'x-form-arrow-trigger')]";
//                boolean displayed = $x(dropDownXpath).isDisplayed();
//                Allure.step("Dropdown is " + (displayed ? "displayed" : "not displayed"));
//                context.softly
//                        .assertThat(displayed)
//                        .overridingErrorMessage("❌ Field is not a dropdown")
//                        .isTrue();
//            } else if (Objects.equals(r3, "date picker")) {
//                String datePickerXpath = xpath + "/parent::div/following-sibling::div[contains(@class,'x-form-date-trigger')]";
//                boolean displayed = $x(datePickerXpath).isDisplayed();
//                Allure.step("Date picker is " + (displayed ? "displayed" : "not displayed"));
//                context.softly
//                        .assertThat(displayed)
//                        .overridingErrorMessage("❌ Field is not a date picker")
//                        .isTrue();
//            } else if (Objects.equals(r3, "radio buttons")) {
//                for (SelenideElement input : inputElements) {
//                    String type = input.getAttribute("type");
//                    AllureUtils.logActionF("Element Name: " + input + " | Input type: " + type);
//                    context.softly
//                            .assertThat(type)
//                            .overridingErrorMessage("❌ Expected input type to be 'radio'")
//                            .isEqualTo("radio");
//                }
//            } else if (Objects.equals(r3, "check box")) {
//                for (SelenideElement input : inputElements) {
//                    String type = input.getAttribute("type");
//                    AllureUtils.logActionF("Element Name: " + input + " | Input type: " + type);
//                    context.softly
//                            .assertThat(type)
//                            .overridingErrorMessage("❌ Expected input type to be 'checkbox'")
//                            .isEqualTo("checkbox");
//                }
//            } else if (Objects.equals(r3, "numeric selection")) {
//                String numericSelectionXpath = labelXPath + "/ancestor::div[contains(@class,'x-field ')]//*[contains(@class, 'x-form-spinner-up') or contains(@class, 'x-form-spinner-down')]";
//                ElementsCollection spinners = $$x(numericSelectionXpath);
//                int expectedCount = 2;
//                int actualCount = spinners.size();
//                context.softly
//                        .assertThat(actualCount)
//                        .overridingErrorMessage("Expected 2 spinner elements, but found: " + actualCount)
//                        .isEqualTo(expectedCount);
//            } else {
//                context.softly.fail("Type of Element: No type of Element Found");
//            }
//
//            if (Objects.equals(r4, "is empty")) {
//                String value = inputField.getValue();
//                Allure.step("Field value: '" + value + "'");
//                context.softly
//                        .assertThat(value == null || value.trim().isEmpty())
//                        .overridingErrorMessage("❌ Field is not empty")
//                        .isTrue();
//            } else if (r4.contains("has text")) {
//                String extracted = r4.replaceAll(".*has text '(.*?)'.*", "$1");
//                String value = inputField.getValue();
//                if (!extracted.contains("$")) {
//                    Allure.step("Checking extracted value:- " + extracted + " exists");
//                    context.softly
//                            .assertThat(value)
//                            .overridingErrorMessage("Actual value is " + value + " and expected value is " + extracted)
//                            .isEqualTo(extracted);
//                } else if (extracted.equals("$todayDate")) {
//                    extracted = today.format(formatter);
//                    Allure.step("Checking extracted value:- " + extracted + " exists");
//                    context.softly
//                            .assertThat(value)
//                            .overridingErrorMessage("Actual value is " + value + " and expected value is " + extracted)
//                            .isEqualTo(extracted);
//                } else if (extracted.equals("$oneYearFromToday")) {
//                    extracted = oneYearLaterMinusOneDay.format(formatter);
//                    Allure.step("Checking extracted value:- " + extracted + " exists");
//                    context.softly
//                            .assertThat(value)
//                            .overridingErrorMessage("Actual value is " + value + " and expected value is " + extracted)
//                            .isEqualTo(extracted);
//                }
//            } else if (r4.contains("is unchecked")) {
//                for (int i = 0; i < inputElements.size(); i++) {
//                    SelenideElement input = inputElements.get(i);
//                    boolean isSelected = input.isSelected();
//                    Allure.step("Checking element " + (i + 1) + ": isSelected = " + isSelected);
//                    context.softly
//                            .assertThat(isSelected)
//                            .overridingErrorMessage("❌ Element " + (i + 1) + " is checked, expected to be unchecked")
//                            .isFalse();
//                }
//            } else if (r4.contains("is checked")) {
//                boolean anySelected = false;
//                for (int i = 0; i < inputElements.size(); i++) {
//                    SelenideElement radio = inputElements.get(i);
//                    boolean selected = radio.isSelected();
//                    Allure.step("Radio " + (i + 1) + " selected: " + selected);
//                    if (selected) {
//                        anySelected = true;
//                        break;
//                    }
//                }
//                context.softly
//                        .assertThat(anySelected)
//                        .overridingErrorMessage("❌ None of the radio buttons are selected")
//                        .isTrue();
//            } else if (r4.contains("has tags")) {
//                int start = r4.indexOf('\'') + 1;
//                int end = r4.lastIndexOf('\'');
//                String insideQuotes = r4.substring(start, end);
//
//                String[] expectedTags = insideQuotes.split(",");
//                for (int i = 0; i < expectedTags.length; i++) {
//                    expectedTags[i] = expectedTags[i].trim();
//                }
//
//                String tagXpath = labelXPath + "/ancestor::div[contains(@class,'x-field ')]//*[@class='x-tagfield-item-text']";
//                ElementsCollection tagElements = $$x(tagXpath)
//                        .shouldBe(sizeGreaterThan(0), Duration.ofSeconds(15));
//
//                String[] actualText = new String[tagElements.size()];
//                for (int i = 0; i < tagElements.size(); i++) {
//                    actualText[i] = tagElements.get(i).getText().trim();
//                }
//
//                for (String tag : expectedTags) {
//                    boolean found = Arrays.asList(actualText).contains(tag);
//                    context.softly
//                            .assertThat(found)
//                            .overridingErrorMessage("❌ Expected tag '" + tag + "' not found")
//                            .isTrue();
//                }
//            }
//
//        } catch (Exception e) {
//            Allure.step("Exception thrown in iNoteContentsOfProjectDetailsSection: " + e.getMessage());
//            context.softly.fail(e.getMessage());
//        }
//    }


    @Then("^(.+) and (.+) are aligned$")
    public void field1AndField2TypeAreAligned(String field1, String field2) {
        Object elementInstance1 = SearchUtils.findElementAtCurrentPage(field1, Visible.class);
        TextElement textElement1 = (TextElement) elementInstance1;
        String xpath1 = textElement1.toXpath();
        Object elementInstance2 = SearchUtils.findElementAtCurrentPage(field2, Visible.class);
        TextElement textElement2 = (TextElement) elementInstance2;
        String xpath2 = textElement2.toXpath();
        SelenideElement tr1 = $x(xpath1+"/ancestor::tr[1]");
        SelenideElement tr2 = $x(xpath2+"/ancestor::tr[1]");
        assertEquals("The "+field1+" element and "+field2+" are not in same line",tr1, tr2);
    }

    private boolean isLocalRun() {
        return Boolean.parseBoolean(System.getProperty("local.run", "false"));
    }

    @And("Fill Project Goals or Remarks with rich text, bullets, and table")
    public void fillProjectGoalsOrRemarksWithRichTextBulletsAndTable() {
// Switch to the iframe
        switchTo().frame($x("//iframe[@title='Rich Text Area']"));

        // Target the editor's body (most rich text editors use <body contenteditable="true">)
        SelenideElement editorBody = $x("//body");

        // Clear any existing content
        editorBody.scrollIntoView(true).shouldBe(visible).click();
        editorBody.scrollIntoView(true).shouldBe(visible).clear();

        String html = ""
                + "<p><strong>Project</strong> <em><strong>Goals</em></strong></p>"
                + "<ul><li><p>Enhance production efficiency</p></li><li><p>Ensure product quality and consistency</p></li><li><p>Minimize costs and lead times</p></li></ul>"
                + "<p><strong><u>Project</u></strong> <em><strong><u>Remarks</u></strong></em></p>"
                + "<table style='border-collapse: collapse; width: 100%;' border='1'>"
                + "  <colgroup>"
                + "    <col style='width: 49.9421%;'>"
                + "    <col style='width: 49.9421%;'>"
                + "  </colgroup>"
                + "  <tbody>"
                + "    <tr>"
                + "      <td>Remark 1</td>"
                + "      <td>Project timeline and deliverables are subject to adjustment based on material availability and client approvals</td>"
                + "    </tr>"
                + "    <tr>"
                + "      <td>Remark 2</td>"
                + "      <td>All processes will comply with relevant safety, environmental, and regulatory standards</td>"
                + "    </tr>"
                + "    <tr>"
                + "      <td>Remark 3</td>"
                + "      <td>Continuous communication will be maintained to ensure alignment with client expectations and project milestones</td>"
                + "    </tr>"
                + "  </tbody>"
                + "</table>";

        executeJavaScript("arguments[0].innerHTML = arguments[1];", editorBody, html);

//        // Switch back to main content
        switchTo().defaultContent();
    }

    @And("I {string} row {int} in the table inside the Rich Text Editor")
    public void iInsertRowAfterRowInTheTableInsideTheRichTextEdito(String action, int rowNumber) {
        // Switch to the iframe
        switchTo().frame($x("//iframe[@title='Rich Text Area']"));

        SelenideElement firstCell = $x("//body[@id='tinymce']//table//tr[" + rowNumber + "]//td[1]");

        // Use JS to focus and then click
        executeJavaScript(
                "var range = document.createRange();" +
                        "range.selectNodeContents(arguments[0]);" +
                        "range.collapse(true);" + // put caret inside td
                        "var sel = window.getSelection();" +
                        "sel.removeAllRanges();" +
                        "sel.addRange(range);" +
                        // dispatch a click event so TinyMCE also gets a 'click'
                        "arguments[0].dispatchEvent(new MouseEvent('click', {bubbles: true}));",
                firstCell
        );

        switchTo().defaultContent();
        AllureUtils.saveScreenshot();

        $x("//*[@role='group' and @class='tox-toolbar' and @aria-disabled='false']//*[@aria-label='" + action +"']").shouldBe(visible, Duration.ofSeconds(10)).click();
    }
}
