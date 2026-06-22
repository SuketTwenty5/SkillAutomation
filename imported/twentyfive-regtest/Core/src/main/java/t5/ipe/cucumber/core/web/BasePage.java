package t5.ipe.cucumber.core.web;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import t5.ipe.cucumber.core.web.elements.BaseWebElement;
import t5.ipe.cucumber.core.web.interfaces.core.ElementMap;
import t5.ipe.cucumber.core.web.interfaces.core.Page;
import t5.ipe.cucumber.core.web.interfaces.annotations.WebPage;
import t5.ipe.cucumber.core.web.interfaces.element_interfaces.Visible;
import t5.ipe.cucumber.core.web.util.AllureUtils;
import t5.ipe.cucumber.core.web.util.web.SearchUtils;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Condition.hidden;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

import com.codeborne.selenide.SelenideElement;

import java.time.Duration;

/**
 * Base class of web-page which implements default logic.
 *
 * Created by: EKruze
 * Date: 20/10/2023
 */
public abstract class BasePage implements Page, ElementMap {

    public void waitForElementsToDisappearAndStayGone() {
        String[] xpaths = {
                "//*[@class='x-mask-msg-text']",
                "//*[@role='alertdialog']",
                "//*[@class='x-mask-msg-text']"
        };

        // Now confirm that all elements are gone for 4 full seconds
        long stableStart = System.currentTimeMillis();

        while (true) {
            boolean anyVisible = false;

            for (String xpath : xpaths) {
                ElementsCollection elements = $$x(xpath);
                if (!elements.isEmpty() && elements.get(0).isDisplayed()) {
                    System.out.println("🔄 Element reappeared: " + xpath);
                    stableStart = System.currentTimeMillis(); // Reset timer
                    anyVisible = true;
                    break;
                }
            }

            if (!anyVisible) {
                long stableDuration = System.currentTimeMillis() - stableStart;
                if (stableDuration >= 4000) {
                    System.out.println("✅ All elements hidden for 4 consecutive seconds. Proceeding...");
                    break;
                }
            }

            try {
                Thread.sleep(250); // Polling interval
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    @Override
    public String getPageName() {
        return this.getClass().getAnnotation(WebPage.class).value();
    }



    @Override
    public void checkIfPageLoaded() {
        String pageName = getPageName();
        String elementName = getLoadTriggerName();
        Visible element = SearchUtils.getPageByName(pageName).findElementByTitle(elementName, Visible.class);
        try {
            element.waitUntilBecomesVisible();
        } catch (AssertionError nse) {
            Selenide.refresh();
            try {
                element.waitUntilBecomesVisible();
            } catch (AssertionError nse2){
                String message = String.format(
                        "❌ Page [%s] was not loaded. Unable to find [%s] by locator: %s",
                        pageName,
                        elementName,
                        ((BaseWebElement) element).getFindBy().xpath()
                );
                throw new RuntimeException(message, nse2);
            }
        }

    }
}
