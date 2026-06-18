package t5.ipe.cucumber.core.web.util.web;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.ex.UIAssertionError;
import org.springframework.aop.support.AopUtils;
import t5.ipe.cucumber.core.web.BasePage;
import t5.ipe.cucumber.core.web.CucumberRuntime;
import t5.ipe.cucumber.core.web.interfaces.annotations.WebPage;
import t5.ipe.cucumber.core.web.util.AllureUtils;

import java.time.Duration;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static com.codeborne.selenide.Selenide.*;

/**
 * Class is responding for page and element search.
 * Created by: EKruze
 * Date: 20/10/2023
 */
public class SearchUtils {
    private static LinkedList<String> pageNames = new LinkedList<>();

    static {
        //sorting elements - from detailed to common
        Map<String, Object> beansMap = CucumberRuntime.getAppContext().getBeansWithAnnotation(WebPage.class);

        for (Map.Entry<String, Object> bean : beansMap.entrySet()) {
            if (bean.getValue().getClass().getAnnotation(WebPage.class).tabName().isEmpty()) {
                pageNames.addLast(bean.getKey());
            } else {
                pageNames.addFirst(bean.getKey());
            }
        }
    }

    private SearchUtils() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * Method defines current page mapping class name, then performs search for element of required type
     * (UI element interface) by element name.
     *
     * @param elementTitle cucumber-name of element.
     * @param requiredType type of required interface of element.
     * @return found implementation of UI element interface.
     */
    public static <T> T findElementAtCurrentPage(String elementTitle, Class<T> requiredType) {
        return getCurrentPage().findElementByTitle(elementTitle, requiredType);
    }

    /**
     * Performs search for instance of current (opened) page-mapping class.
     *
     * @return instance of current page.
     */
    private static BasePage getCurrentPage() {
        return (BasePage) CucumberRuntime.getAppContext().getBean(getCurrentPageName());
    }

    private static String currentPage;

    private static String CheckCurrentPage(String pageName, String resourcePath, String tabName, List<String> lstMainTabs) {
        try {
            if (pageName == null) {
                System.out.print("CheckCurrentPage: pageName is null, returning empty string.\n");
                return "";
            }

            // Try getting the WebPage annotation
            Object bean = CucumberRuntime.getAppContext().getBean(pageName);
            if (bean == null) {
                System.out.printf("CheckCurrentPage: Bean for '%s' not found, returning empty string.\n", pageName);
                return "";
            }

            Class<?> targetClass = AopUtils.getTargetClass(bean);
            WebPage webPage = targetClass.getAnnotation(WebPage.class);

            if (webPage == null) {
                System.out.printf("CheckCurrentPage: No @WebPage annotation found on '%s', returning empty string.\n", pageName);
                return "";
            }

            System.out.printf("CheckCurrentPage: Checking for page '%s' | tabName='%s' | resourcePath='%s'\n",
                    pageName, tabName, resourcePath);

            // --- 1️⃣ Try matching with old tabName ---
            if (tabName != null && !tabName.trim().isEmpty()) {
                System.out.printf("Trying tabName match: tabName='%s' vs annotation.tabName='%s'\n",
                        tabName, webPage.tabName());
                if (!webPage.tabName().isEmpty() &&
                        tabName.toLowerCase().contains(webPage.tabName().toLowerCase())) {
                    currentPage = pageName;
                    System.out.printf("✅ Matched by old tabName: '%s'\n", pageName);
                    return pageName;
                } else {
                    System.out.printf("❌ No old tabName match for page '%s'. Page has changed\n", pageName);
                }
            }

            // --- 2️⃣ Try matching with resourcePath ---
            if (resourcePath != null && !resourcePath.trim().isEmpty()) {
                System.out.printf("Trying resourcePath match: '%s' vs '%s'\n", resourcePath, webPage.urlTemplate());
                if (resourcePath.matches(webPage.urlTemplate())) {
                    currentPage = pageName;
                    System.out.printf("✅ Matched by resourcePath: '%s'\n", pageName);
                    return pageName;
                } else {
                    System.out.printf("❌ No resourcePath match for page '%s'\n", pageName);
                }
            }

            // --- 3️⃣ Try matching with main tabs ---
            if (lstMainTabs != null && !lstMainTabs.isEmpty()) {
                System.out.printf("Trying tab matches for page '%s'\n", pageName);
                for (String mainTabText : lstMainTabs) {
                    System.out.printf("Comparing mainTab='%s' vs urlTemplate='%s'\n", mainTabText, webPage.urlTemplate());
                    if (mainTabText.toLowerCase().matches(webPage.urlTemplate())) {
                        currentPage = pageName;
                        System.out.printf("✅ Matched by mainTab: '%s'\n", pageName);
                        return pageName;
                    }
                }
                System.out.printf("❌ No mainTab match for page '%s'\n", pageName);
            }

            // --- Nothing matched ---
            System.out.printf("⚠️ No match found for page '%s'.\n", pageName);
            return "";

        } catch (Exception e) {
            System.out.printf("❌ Exception in CheckCurrentPage for page '%s': %s\n", pageName, e.getMessage());
            e.printStackTrace();
            return "";
        }
    }

    /**
     * Defines name of current (opened) page by browser URL.
     *
     * @return current page name.
     */
    public static String getCurrentPageName() {
        String currentURL = WebDriverRunner.getWebDriver().getCurrentUrl();
        String currentSiteUrl = WebUtil.getCurrentSiteUrl();
        // extract resource path from URL
        String resourcePath = currentURL.replace(currentSiteUrl, "");
        System.out.printf("getCurrentPageName Method getting resourcePath value %s\n", resourcePath);
        String tabName = "";
        String xpathExpression = "//a[@aria-hidden='false' and contains(@class,'ibeTopMenuButton')]//span[contains(@id,'button') and @data-ref='btnInnerEl']";

        // get list elements main tab
        List<String> lstMainTabs = $$x(xpathExpression).texts();

        SelenideElement dialogBox = $x("//*[@role='dialog' and @aria-hidden='false' and not(contains(@class,'x-window-ghost'))]//*[@role='tabpanel' and @aria-hidden='false']");
        SelenideElement dialogTab = $x("//*[@role='dialog' and @aria-hidden='false' and not(contains(@class,'x-window-ghost'))]//a[@aria-selected='true']");
        String dialogTabName = "";
        try {
            if(dialogTab.isDisplayed() && dialogTab.exists()){
                try {
                    dialogTabName = dialogTab.getText();
                } catch (Throwable e) {
                    AllureUtils.logActionF("No selected tab in dialog. Exception: %s\n", e.getMessage());
                }
            }else{
                AllureUtils.logActionF("No selected tab in dialog.");
            }
        }catch (Throwable e){
            AllureUtils.logActionF("No selected tab in dialog. Exception: %s\n", e.getMessage());
        }
        if(dialogBox.exists() && !dialogTabName.isEmpty()) {
            AllureUtils.logActionF("Dialog is opened. Looking for selected tab inside the dialog.");
            String result = CheckCurrentPage(currentPage, "", dialogTabName, lstMainTabs);
            if (!result.isEmpty() && !result.equals("Main page")) {
                System.out.printf("Result Current Page in dialog: %s\n", result);
                return result;
            }
        }
        SelenideElement element = $x("//a[@aria-selected='true']");
        if (element.exists())
            tabName = element.getText();
        System.out.printf("Main Head Tabs are: %s\n", String.join(", ", lstMainTabs));
        System.out.printf("Current Sub Tab is: %s\n", tabName);
        System.out.printf("Previous page is %s\n", currentPage);

        String result = CheckCurrentPage(currentPage, "", tabName, lstMainTabs);
        if (!result.isEmpty() && !result.equals("Main page")) {
            System.out.printf("Result Current Page: %s\n", result);
            return result;
        }

        System.out.printf("Result not found on current page. Page has changed\n");
        for (String pageName : pageNames) {
//            String oldResult = result;
            result = CheckCurrentPage(pageName, "", tabName, lstMainTabs);
            if (!result.isEmpty()) {
                System.out.printf("Result of Page- %s is %s\n",pageName, result);
                return result;
            }
        }
        System.out.print("Result not found by Tab name. Checked all available tabs\n");
        for (String pageName : pageNames) {
            result = CheckCurrentPage(pageName, resourcePath, tabName, lstMainTabs);
            if (!result.isEmpty()) {
                System.out.printf("Result of Page- %s is %s\n",pageName, result);
                return result;
            }
        }
        System.out.print("Result not found by Resource Path. Fix the resource path\n");
        if (!result.isEmpty()) {
            System.out.printf("Result Current Page: %s\n", result);
            return result;
        }
        throw new IllegalArgumentException("Not found page name for URL: " + currentURL + System.lineSeparator()
                + resourcePath);
    }

    /**
     * Performs request for page object from Spring context by page name.
     *
     * @param pageName name of page.
     * @return instance of page object.
     */
    public static BasePage getPageByName(String pageName) {
        Map<String, Object> beansMap = CucumberRuntime.getAppContext().getBeansWithAnnotation(WebPage.class);
        if (pageName.equals("WBS Cost Structure page")) {
            pageName = "Cost Structure page";
        }
        BasePage searchingPage = (BasePage) beansMap.get(pageName);
        if (searchingPage == null) {
            throw new IllegalArgumentException(
                    String.format(
                            "Not found page with name [%s].%nKnown pages:%n%s",
                            pageName,
                            String.join(System.lineSeparator(), beansMap.keySet())

                    ));
        }
        return searchingPage;
    }
}
