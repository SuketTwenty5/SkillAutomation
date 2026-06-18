package t5.ipe.cucumber.core.web.elements;

import com.codeborne.selenide.*;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.FindBy;
import t5.ipe.cucumber.core.web.interfaces.element_interfaces.Clickable;
import t5.ipe.cucumber.core.web.interfaces.element_interfaces.Visible;
import t5.ipe.cucumber.core.web.util.AllureUtils;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.File;
import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import org.sikuli.script.Screen;
import org.sikuli.script.Pattern;

/**
 * Base class element. It is required to specify it as a parent for the rest of the single element implementations,
 * since it contains the web-element initialization logic.
 * Created by: EKruze
 * Date: 20/10/2023
 */
public class BaseWebElement extends FindBy.FindByBuilder implements Clickable, Visible {

    private String title;

    private FindBy findBy;

    /**
     * Method returns the name of current element.
     *
     * @return name of cucumber-element.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Initializes the name of cucumber-element.
     *
     * @param title - name of cucumber-element.
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return element locator.
     */
    public FindBy getFindBy() {
        return findBy;
    }

    /**
     * @param findBy element locator.
     */
    public void setFindBy(FindBy findBy) {
        this.findBy = findBy;
    }

    /**
     * Obtains {@link By} corresponding to current element locator.<br>
     * Creates new locator.
     *
     * @return element locator.
     */
    public By toBy() {
        return buildByFromFindBy(getFindBy());
    }

    /**
     * Obtains {@link SelenideElement} corresponding to current element locator.
     * <br>Creates new element.
     *
     * @return corresponding {@link SelenideElement}.
     */
    public SelenideElement toSelenideElement() {
        return $(toBy());
    }

    public ElementsCollection toSelenideElements() {
        return $$(toBy());
    }
    public String toXpath(){
        By by = toBy();
        if (by instanceof By.ByXPath) {
            return by.toString().replaceFirst("By\\.xpath: ", "");
        }
        throw new UnsupportedOperationException("Locator is not an XPath");
    }
    private void highlightElement(SelenideElement element) {
        try {
            JavascriptExecutor js = (JavascriptExecutor) getWebDriver();
            js.executeScript("arguments[0].style.border='3px solid red'", element);
        } catch (Exception ignored) {
            // Highlighting is optional, so fail silently
        }
    }

    private void highlightElement(String xpath) {
        try {
            String script = ""
                    + "var xpath = \"" + xpath + "\";"
                    + "var result = document.evaluate(xpath, document, null, XPathResult.FIRST_ORDERED_NODE_TYPE, null);"
                    + "var element = result.singleNodeValue;"
                    + "if (element) {"
                    + "    element.style.border = '3px solid red';"
                    + "    element.style.backgroundColor = 'yellow';"
                    + "    console.log('✅ Element found and highlighted');"
                    + "} else {"
                    + "    console.warn('❌ Element NOT found');"
                    + "}";

            ((JavascriptExecutor) getWebDriver()).executeScript(script);
        } catch (Exception ignored) {
            // Highlighting is optional, so fail silently
        }
    }
    public void clickElementByXpathUsingJs(String xpath) {
        // STEP 1: Check if element exists using XPath
        String checkScript = ""
                + "var xpath = \"" + xpath + "\";"
                + "var result = document.evaluate(xpath, document, null, XPathResult.FIRST_ORDERED_NODE_TYPE, null);"
                + "var element = result.singleNodeValue;"
                + "return element !== null;";

        Boolean elementExists = (Boolean) ((JavascriptExecutor) getWebDriver()).executeScript(checkScript);

        if (Boolean.TRUE.equals(elementExists)) {
            // STEP 2: If found, scroll to and click the element
            String actionScript = ""
                    + "var xpath = \"" + xpath + "\";"
                    + "var result = document.evaluate(xpath, document, null, XPathResult.FIRST_ORDERED_NODE_TYPE, null);"
                    + "var element = result.singleNodeValue;"
                    + "element.scrollIntoView({block: 'center', inline: 'center'});"
                    + "element.click();"
                    + "console.log('🖱️ Element scrolled into view and clicked');";

            ((JavascriptExecutor) getWebDriver()).executeScript(actionScript);
        } else {
            throw new RuntimeException("❌ Element not found with XPath: " + xpath);
        }
    }

    @Override
    public void click() {
        SelenideElement element = toSelenideElement();

        try {
            element.should(Condition.exist, Duration.ofSeconds(30))
                    .shouldBe(Condition.visible, Duration.ofSeconds(10));

            if (element.isDisplayed()) {
                element.scrollTo().hover().click();
                AllureUtils.saveScreenshot();
                AllureUtils.logActionF(String.format("User clicked on Element %s", element));
            } else {
                throw new Exception("Element exists but is not visible");
            }

        } catch (Throwable e) {
            String xpath = toXpath();
            AllureUtils.logActionF(String.format("Failed to click xpath %s, retrying with JavaScript", xpath));
                try {
                    highlightElement(xpath);
                    clickElementByXpathUsingJs(xpath);
                    AllureUtils.saveScreenshot();
                    AllureUtils.logActionF(String.format("JavaScript click succeeded on xpath: %s", xpath));
                } catch (Exception jsEx) {
                    String msg = String.format("JavaScript click failed for element %s due to: %s", element, jsEx.getMessage());
                    AllureUtils.logActionF(msg);
                    throw new RuntimeException(msg, jsEx);
                }
        }
    }


    public void hover() {
        try {

            toSelenideElement().should(Condition.exist, Duration.ofSeconds(30)).shouldBe(Condition.visible, Duration.ofSeconds(10));


            if (toSelenideElement().isDisplayed()) {
                toSelenideElement().scrollIntoView(true).hover();
                AllureUtils.saveScreenshot();
                AllureUtils.logActionF(String.format("User clicked on Element %s ", toSelenideElement()));
            } else {
                throw new Exception("Element exists but is not visible");
            }
        } catch (Throwable e) {
            AllureUtils.logActionF("Failed to click the element %s, entering catch block to try using JavaScript", toSelenideElement());
//            Selenide.sleep(4000);

            if (toSelenideElement().exists()) {
                ((JavascriptExecutor) Selenide.webdriver().object()).executeScript("arguments[0].scrollIntoView(true);", toSelenideElement()); // Desplázate al elemento
                AllureUtils.saveScreenshot();
            } else {
                AllureUtils.logActionF("Element %s does not exist in the DOM, unable to click", toSelenideElement());
            }
        }
    }

    public String getExtComponentId(SelenideElement element) {
        return element.getAttribute("data-componentid").replace("-button", "");
    }

    @Override
    public void uploadFile(String filePath) {
        try {

            // Locate element once
            SelenideElement inputElement = toSelenideElement();

            File file = new File(filePath);

            System.out.println("------ EXTJS FILE UPLOAD START ------");
            System.out.println("File: " + file.getAbsolutePath());
            // Upload file
            inputElement.uploadFile(file);
            File cancelPngfile = new File("cancel.png");
            System.out.println("Cancel button image path: " + cancelPngfile.getAbsolutePath());
            Screen screen = new Screen();
            Pattern cancelBtn = new Pattern(cancelPngfile.getAbsolutePath());
            screen.wait(cancelBtn, 5);
            screen.click(cancelBtn);
            System.out.println("File dialog closed via SikuliX.");
//            Selenide.sleep(5000);
//            String url = WebDriverRunner.getWebDriver().getCurrentUrl();
//            String currentTab = WebDriverRunner.getWebDriver().getWindowHandle();
//            executeJavaScript("window.open(arguments[0], '_blank');", url);
//            Wait().until(driver -> driver.getWindowHandles().size() > 1);
//            // Switch to the new tab (last in the set)
//            String newTab = WebDriverRunner.getWebDriver().getWindowHandles()
//                    .stream()
//                    .filter(handle -> !handle.equals(currentTab))
//                    .findFirst()
//                    .orElseThrow(() -> new RuntimeException("New tab not found"));
//
//            switchTo().window(newTab);
//            $x("//*[@role='tabpanel' and @aria-hidden='false']").shouldBe(visible, Duration.ofSeconds(15));
//
//            // Close old tab
//            switchTo().window(currentTab);
//            closeWindow();
//
//            // Switch back to new tab
//            switchTo().window(newTab);
            System.out.println("------ EXTJS FILE UPLOAD END ------");
        } catch (Exception e) {

            String msg = String.format(
                    "Failed to upload file %s to element %s due to: %s",
                    filePath, toSelenideElement(), e.getMessage()
            );

            System.out.println("UPLOAD ERROR: " + msg);

            AllureUtils.logActionF(msg);

            throw new RuntimeException(msg, e);
        }
    }

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
    public boolean isEnabled() {
        try {
            waitUntilBecomesVisible();
            return toSelenideElement().isEnabled() && !("true".equals(toSelenideElement().getAttribute("aria-disabled")));
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
        toSelenideElement().should(visible, Duration.ofSeconds(15));
    }

    @Override
    public void waitUntilBecomesInvisible() {
        toSelenideElement().shouldNot(Condition.exist);
    }
}
