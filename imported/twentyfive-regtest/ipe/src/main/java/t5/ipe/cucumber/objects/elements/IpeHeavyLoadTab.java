package t5.ipe.cucumber.objects.elements;

import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.JavascriptExecutor;
import t5.ipe.cucumber.core.web.elements.BaseWebElement;
import t5.ipe.cucumber.core.web.util.AllureUtils;

public class IpeHeavyLoadTab extends BaseWebElement {

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

            ((JavascriptExecutor) WebDriverRunner.getWebDriver()).executeScript(script);
        } catch (Exception ignored) {
            // Highlighting is optional, so fail silently
        }
    }

    @Override
    public void click(){
        String xpath = toXpath();
        SelenideElement element = toSelenideElement();
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
