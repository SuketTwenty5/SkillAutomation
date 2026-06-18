package t5.ipe.cucumber.core.web.steps;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import t5.ipe.cucumber.core.web.TestContext;
import t5.ipe.cucumber.core.web.elements.TextElement;
import t5.ipe.cucumber.core.web.interfaces.element_interfaces.Readable;
import t5.ipe.cucumber.core.web.util.DataContainer;
import t5.ipe.cucumber.core.web.interfaces.element_interfaces.*;
import t5.ipe.cucumber.core.web.util.AllureUtils;
import t5.ipe.cucumber.core.web.util.web.SearchUtils;
import t5.ipe.cucumber.core.web.util.web.StepUtils;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;

import java.time.Duration;
import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

import static com.codeborne.selenide.Selenide.*;
import static java.lang.Thread.sleep;
import t5.ipe.cucumber.core.web.TestContext;


/**
 * Steps that implements actions aimed at interaction with GUI elements.
 * <p>
 * Created by: EKruze
 * Date: 20/10/2023
 */
public class ElementActionSteps {

    @And("^(?:I |)click on (.+)$")
    public void clickOn(String elementName) {
        AllureUtils.logActionF("Enter Method clickOn with the elementName %s", elementName);
        SearchUtils.findElementAtCurrentPage(elementName, Clickable.class)
                .click();
        AllureUtils.logActionF("Performed click on %s", elementName);
    }
    @And("^(?:I |)if visible click on (.+)$")
    public void clickOnIfVisible(String elementName) {
        try {
            AllureUtils.logActionF("Enter Method clickOn with the elementName %s", elementName);
            SearchUtils.findElementAtCurrentPage(elementName, Clickable.class)
                    .click();
            AllureUtils.logActionF("Performed click on %s", elementName);
        }catch (Exception e){
            AllureUtils.logActionF("%s is not visible or clickable", elementName);
        }
    }

    @And("^(?:I |)select '(.+)' in the (.+)$")
    public void selectItemFromList(String itemName, String elementName) {
        SearchUtils.findElementAtCurrentPage(elementName, Selectable.class)
                .select(itemName);
        AllureUtils.logActionF("Selected '%s' in the %s", itemName, elementName);
    }

    @And("^(?:I |)click on (.+) in (.+) with name '(.+)'$")
    public void clickOnItemInBlock(String elementName, String blockType, String blockName) {
        SearchUtils.findElementAtCurrentPage(blockType, TemplateElement.class)
                .focusOnElementByName(blockName)
                .findElementByTitle(elementName, Clickable.class)
                .click();
        AllureUtils.logActionF("Performed click on %s in %s with name '%s'", elementName, blockType, blockName);
    }

    @And("^(?:I |)select '(.+)' from (.+) in (.+)$")
    public void selectItemFromListInBlock(String itemName, String listElementName, String blockType) {
        SearchUtils.findElementAtCurrentPage(blockType, BlockElement.class)
                .findElementByTitle(listElementName, Selectable.class)
                .select(itemName);
        AllureUtils.logActionF("Selected %s", itemName);
    }

    @And("^(?:I |)select '(.+)' from (.+) in (.+) with name '(.+)'$")
    public void selectItemFromListInTemplateWithName(String itemName, String listElementName, String templateType,
                                                     String templateElementName) {
        SearchUtils.findElementAtCurrentPage(templateType, TemplateElement.class)
                .focusOnElementByName(templateElementName)
                .findElementByTitle(listElementName, Selectable.class)
                .select(itemName);
        AllureUtils.logActionF("Selected %s", itemName);
    }

    @And("^(?:I |)save the text of the (.+) to the variable '(.+)'$")
    public void saveElementTextToVariable(String textElementName, String varName) {
        String actualText = SearchUtils.findElementAtCurrentPage(textElementName, Readable.class)
                .readText();
        DataContainer.storeVariable(varName, actualText);
    }

    @And("^(?:I |)enter text '(.+)' in the (.+)$")
    public void enterTextInTheEditableElement(String text, String elementName) {
        String resolvedText = DataContainer.resolveVariablesInText(text);
        SearchUtils.findElementAtCurrentPage(elementName, Editable.class)
                .enterText(resolvedText);
        AllureUtils.logActionF("entered text [%s] into %s", resolvedText, elementName);
    }

    @And("^(?:I |)set year '(.*)' month '(.*)' and day '(.*)' into (.+)$")
    public void setYearMonthAndDayInDateField(String year, String month, String day, String elementName) {
        SearchUtils.findElementAtCurrentPage(elementName, DatePickable.class).setYearMonthAndDay(year, month, day);
    }

    @And("^(?:I |)(?:check|select) (?!.*in)(?!.*with)(.*)$")
    public void checkElement(String elementName) {
        SearchUtils.findElementAtCurrentPage(elementName, Toggle.class).activate();
    }

    @And("^(?:I |)(?:uncheck|deselect) (.+)$")
    public void unCheckElement(String elementName) {
        SearchUtils.findElementAtCurrentPage(elementName, Toggle.class).deactivate();
    }

    @And("^(?:I |)enter data into the next fields:$")
    public void enterDataIntoTheFields(Map<String, String> fieldsAndValues) {
        AllureUtils.logActionF("enterDataIntoTheFields method with fieldsAndValues [%s]", fieldsAndValues);

        for (String elementName : fieldsAndValues.keySet()) {
            String valueToSet;
            if(fieldsAndValues.get(elementName) == null){
                valueToSet="";
            }
            else {
                valueToSet = DataContainer.resolveVariablesInText(fieldsAndValues.get(elementName));
            }
            AllureUtils.logActionF("Entering data [%s] in [%s]", valueToSet , elementName);
            Object elementInstance = SearchUtils.findElementAtCurrentPage(elementName, Visible.class);
            Class<?> elementClass = elementInstance.getClass();

            if (DatePickable.class.isAssignableFrom(elementClass)) {
                StepUtils.setDateInSimpleFormatAtDatePicker((DatePickable) elementInstance, valueToSet);
            } else if (Selectable.class.isAssignableFrom(elementClass)) {
                ((Selectable) elementInstance).select(valueToSet);
            } else if (Toggle.class.isAssignableFrom(elementClass)) {
                StepUtils.setValueToToggle((Toggle) elementInstance, valueToSet);
            } else if (Editable.class.isAssignableFrom(elementClass)) {
                ((Editable) elementInstance).enterText(valueToSet);
            } else {
                throw new IllegalArgumentException("Unsuitable type of element with name: " + elementName + System.lineSeparator()
                        + ". Suitable types are implementations of next interfaces: DatePickable, Selectable, Toggle and Editable");
            }
        }
    }

    @And("^(?:I |)select node with name '(.+)' in (.+)$")
    public void selectNodeInTree(String nodeName, String elementName) {
        String resolvedNodeName = DataContainer.resolveVariablesInText(nodeName);
        SearchUtils.findElementAtCurrentPage(elementName, Tree.class).selectNode(resolvedNodeName);
    }

    @And("^(?:I |)choose in (.+) the next menu chain:$")
    public void selectOptionInMenu(String menuName, DataTable menuChain) {
        String[] chain = menuChain.asList().toArray(new String[0]);
        SearchUtils.findElementAtCurrentPage(menuName, Tree.class).selectNode(chain);
    }

    @And("^(?:I |)choose in (.+) the next filter chains:$")
    public void selectFilters(String menuName, DataTable filterChains) {
        List<List<String>> rows = filterChains.asLists(String.class);
        Tree filter = SearchUtils.findElementAtCurrentPage(menuName, Tree.class);
        for (List<String> columns : rows) {
            String[] chain = columns.toArray(new String[0]);
            filter.selectNode(chain);
        }
    }

    @Then("^(?:I |)hover on (.+)$")
    public void iHoverOnProposalColumnHeader(String elementName) {

        AllureUtils.logActionF("Enter Method hoverOn with the elementName %s", elementName);
        SearchUtils.findElementAtCurrentPage(elementName, Clickable.class)
                .hover();
        AllureUtils.logActionF("Performed hover on %s", elementName);
    }
    public static String getColorName(String rgb) {
        // Extract the RGB values from the input string
        rgb = rgb.replaceAll("[^0-9,]", ""); // Remove non-numeric and non-comma characters
        String[] rgbValues = rgb.split(",");

        // Parse the RGB values
        int red = Integer.parseInt(rgbValues[0].trim());
        int green = Integer.parseInt(rgbValues[1].trim());
        int blue = Integer.parseInt(rgbValues[2].trim());

        // Logic to return the corresponding color name
        if (red > green && red > blue) {
            return "red";
        } else if (green > red && green > blue) {
            return "green";
        } else if (blue > red && blue > green) {
            return "blue";
        } else {
            System.out.println("Unknown Color RGB value");
            System.out.printf("Red: %d || Green: %d || Blue: %d%n", red, green, blue);
            return "unknown color"; // Add more color cases as needed
        }
    }

    @And("^(?:I |)verify (.+) css of (.+) is '(.+)'")
    public void colorOfErrorDialogIsRedImportant(String css, String elementName, String color) {
        Object elementInstance = SearchUtils.findElementAtCurrentPage(elementName, Visible.class);
        TextElement textElement = (TextElement) elementInstance;
        SelenideElement element = textElement.toSelenideElement();
        String xpath = textElement.toXpath();
        SelenideElement selenideElement;
        if (element.getTagName().equalsIgnoreCase("input")) {
            selenideElement = $x(xpath+"/ancestor::div[@data-ref='triggerWrap'][1]");
        }else {
            selenideElement=element;
        }
        String borderColor = selenideElement.getCssValue(css);
        String borderColorValue = getColorName(borderColor);
        AllureUtils.logActionF("The css value"+css+" of "+elementName+" is "+borderColorValue);
        Assert.assertEquals("Actual Color is: "+borderColorValue+ " and "+".Expected is: "+color,color,borderColorValue);
    }

    @And("^(?:I |)verify (.+) css of (.+) is '(.+)'_-Warning-_")
    public void colorOfErrorDialogIsRedImportantWarning(String css, String elementName, String color) {
        Object elementInstance = SearchUtils.findElementAtCurrentPage(elementName, Visible.class);
        TextElement textElement = (TextElement) elementInstance;
        SelenideElement element = textElement.toSelenideElement();
        String xpath = textElement.toXpath();
        SelenideElement selenideElement;
        if (element.getTagName().equalsIgnoreCase("input")) {
            selenideElement = $x(xpath+"/ancestor::div[@data-ref='triggerWrap'][1]");
        }else {
            selenideElement=element;
        }
        String borderColor = selenideElement.getCssValue(css);
        String borderColorValue = getColorName(borderColor);
        TestContext.getInstance().softly.assertThat(borderColorValue).as("Actual Color is: "+borderColorValue+ " and "+".Expected is: "+color).isEqualTo(color);
        AllureUtils.logActionF("The css value"+css+" of "+elementName+" is "+borderColorValue);
    }

    public static boolean isMultiline(String text) {
        return text.contains("\n") || text.contains("\r");
    }

    public static String removeNewlines(String text) {
        return text.replaceAll("\\r?\\n", " ");  // replaces both \n and \r\n with space
    }

    @And("warning messages displayed in {int} seconds are:")
    public void warningMessagesDisplayedAre(int timeSeconds, String docString) throws InterruptedException {
        // Use innerCt to get each message separately
        String xpath = "//*[@role='dialog' and @aria-hidden='false']//*[@class='x-autocontainer-innerCt']";

        // Prepare expected messages
        String[] messageArray = docString.split("---");
        Set<String> expectedMessages = new HashSet<>();
        for (String msg : messageArray) {
            if (isMultiline(msg)) {
                msg = removeNewlines(msg);
            }
            expectedMessages.add(msg.trim());
        }
        AllureUtils.logAction("📝 Expected messages: " + expectedMessages);

        Set<String> uniqueMessages = new HashSet<>();
        Instant start = Instant.now();

        while (Duration.between(start, Instant.now()).getSeconds() < timeSeconds) {
            try {
                List<String> currentTexts = executeJavaScript(
                        "var result = document.evaluate(arguments[0], document, null, XPathResult.ORDERED_NODE_SNAPSHOT_TYPE, null);" +
                                "var list = [];" +
                                "for (var i = 0; i < result.snapshotLength; i++) {" +
                                "  var el = result.snapshotItem(i);" +
                                "  for (var j = 0; j < el.childNodes.length; j++) {" +
                                "    if (el.childNodes[j].nodeType === Node.TEXT_NODE) {" +
                                "      var text = el.childNodes[j].textContent.trim();" +
                                "      if (text.length > 0) list.push(text);" +
                                "    }" +
                                "  }" +
                                "}" +
                                "return list;",
                        xpath
                );

                AllureUtils.logActionF(
                        "Found %d message elements in this iteration: %s",
                        currentTexts.size(),
                        currentTexts
                );
                if (!currentTexts.isEmpty()) {
                    for (String text : currentTexts) {
                        if (text.toLowerCase().contains("exception") ||
                                text.toLowerCase().contains("org.openqa.selenium") ||
                                text.toLowerCase().contains("build info") ||
                                text.toLowerCase().contains("command: [") ||
                                text.toLowerCase().contains("element: [[")) {
                            AllureUtils.logAction("⚠️ Skipping exception-like message: " + text);
                            continue;
                        }

                        if (uniqueMessages.add(text)) {
                            AllureUtils.logAction("🔔 Captured message: " + text);
                        } else {
                            AllureUtils.logAction("ℹ️ Duplicate message ignored: " + text);
                        }
                    }
                } else {
                    AllureUtils.logAction("ℹ️ No messages found in this iteration.");
                }

            } catch (org.openqa.selenium.StaleElementReferenceException e) {
                AllureUtils.logAction("⚠️ Stale element skipped.");
            } catch (org.openqa.selenium.NoSuchElementException e) {
                AllureUtils.logAction("⚠️ No element skipped.");
            } catch (org.openqa.selenium.WebDriverException e) {
                AllureUtils.logAction("⚠️ WebDriver exception: " + e.getMessage());
            }

            Thread.sleep(300);
        }

        AllureUtils.logAction("✅ Collected messages: " + uniqueMessages);

        Set<String> missingMessages = new HashSet<>(expectedMessages);
        missingMessages.removeAll(uniqueMessages);

        if (!missingMessages.isEmpty()) {
            AllureUtils.logAction("❌ Some expected warning messages are missing in the actual output.");
            AllureUtils.logAction("❗ Missing Warning Messages: " + missingMessages);
        } else {
            AllureUtils.logAction("✅ All expected warning messages were captured.");
        }

        Assert.assertTrue("Actual warning messages do not contain all expected ones",
                uniqueMessages.containsAll(expectedMessages));
    }

    @And("^(?:I |)upload file '(.+)' from (.+)")
    public void iUploadFileTCBOMImportExcelFileXlsxFromSelectFileButton(String filePath, String buttonName) {
        SearchUtils.findElementAtCurrentPage(buttonName, Clickable.class)
                .uploadFile(filePath);
    }
}

//            ElementsCollection boxes = $$x("//*[@role='dialog' and @aria-hidden='false']//*[@class='x-autocontainer-outerCt']");
//            String text = "";
//            try {
//                if (i < newBoxes.size()) {
//                    // Try to safely access and use the element
//                    SelenideElement element = newBoxes.get(i);
//                    if (element.exists() && element.isDisplayed()) {
//                        text = element.getText().trim();
//                    } else {
//                        AllureUtils.logAction(String.format("⚠️ Element at index %d does not exist or is not displayed.", i));
//                    }
//                } else {
//                    AllureUtils.logAction(String.format("⚠️ Index %d out of bounds. List size: %d", i, newBoxes.size()));
//                }
//            } catch (org.openqa.selenium.StaleElementReferenceException e) {
//                AllureUtils.logAction(String.format("⚠️ Stale element at index %d skipped.", i));
//            } catch (org.openqa.selenium.NoSuchElementException e) {
//                AllureUtils.logAction(String.format("⚠️ No element at index %d skipped.", i));
//            } catch (org.openqa.selenium.WebDriverException e) {
//                AllureUtils.logAction(String.format("⚠️ WebDriver exception at index %d: %s", i, e.getMessage()));
//            }
//            if (!text.isEmpty()) {
//                uniqueMessages.add(text);
//            }
//
//
//            sleep(200); // check every 200ms
//        }
