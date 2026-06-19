package t5.ipe.cucumber.objects.steps;
import java.io.File;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import static org.junit.Assert.assertFalse;

import com.codeborne.selenide.*;
import com.codeborne.selenide.ex.ElementNotFound;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.When;
import io.qameta.allure.Allure;
import okhttp3.*;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Assert;
import java.io.IOException;
import javax.net.ssl.*;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;

import org.openqa.selenium.*;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import t5.ipe.cucumber.core.web.PreviousWindow;
import t5.ipe.cucumber.core.web.TestContext;
import t5.ipe.cucumber.core.web.WindowLogger;
import t5.ipe.cucumber.core.web.util.AllureUtils;
import t5.ipe.cucumber.core.web.util.DataContainer;
import t5.ipe.cucumber.core.web.util.web.WebDriverUtils;
import t5.ipe.cucumber.core.web.util.web.WebUtil;
import t5.ipe.cucumber.objects.IpeLoginFormType;
import t5.ipe.cucumber.objects.IpeUser;
import t5.ipe.cucumber.objects.IpeUserRepository;
import t5.ipe.cucumber.objects.elements.tables.WBSTable;
import t5.ipe.cucumber.objects.elements.tables.proposalTable;
import java.time.Duration;
import java.util.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;
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

/**
 * Created by: EKruze
 */
public class IpeSteps {

    String LOGIN_WIDGET_T5 = "//div[@data-iam-selected-section='login-standard']";
    String USERNAME_INPUT_T5 = "//input[@id='j_username']";
    String PASSWORD_INPUT_T5 = "//input[@id='j_password' or @id='password']";
    String SIGN_IN_BUTTON_T5 = "//button[@type='submit' or text()='Sign in']";
    String Continue_Button_T5 = "//*[text()='Continue']";
    String DEFAULT_IDENTITY_PROVIDER = "//a[text()='Default Identity Provider']";
    String UNDERSTOOD_COOKIE_WARNING ="//*[contains(text(),'Understood')]";
    String tabs = "//a[@role='tab' and @aria-hidden='false']//span[contains(@class,'x-tab-inner')]";
    String subTabs = "";
    String UPLOAD_FILE_WINDOW = "//div[not(contains(@class,'x-hidden-offsets')) and (contains (@class, 'x-window x-layer'))]//div[contains(@class,'x-window-header')]//div[text()='Upload a List of Objects']";
    String HAMBURGER_MENU = "//a[@data-qtip='More']";
    String HAMBURGER_MENU_DROPDOWN = "//div[@class='x-menu x-layer x-menu-default x-border-box'][@aria-hidden='false']";
    String LAST_FINISHED_MSG = "//span//small[contains(text(), 'Last Finished')]";
    String VIEW_LOGS_MSG = "//span[text()='View Logs']";
    String USER_PILOT_CONTENT = "//div[@id='userpilotContent']";
    String CLOSE_USER_PILOT = "//div[@aria-label='Close Modal']";

    String ENTER_CODE_TEXT = "//*[text()='Enter code']";
    String SIGN_IN_TEXT = "//title[text()='Sign In']";
    String ENTER_PASSWORD_TEXT = "//*[@id='j_password-label']";
    String APP_TITLE_TOOLBAR = "//div[contains(@class, 'x-PricingAppNavTitleToolbar')]";
    String APP_TOP_MENU_BUTTON = "//a[@aria-hidden='false' and contains(@class,'ibeTopMenuButton')]";
    String PROPOSALS_TAB = "//a//span[contains(translate(normalize-space(.), 'abcdefghijklmnopqrstuvwxyz', 'ABCDEFGHIJKLMNOPQRSTUVWXYZ'), 'PROPOSALS')]";
    String PG_USERNAME_INPUT = "//*[@name='pf.username']";
    String PG_PASSWORD_INPUT = "//*[@type='password']";
    String PG_SIGN_ON_BUTTON = "//*[@class='ping-button normal allow']";

    public static boolean isWindows() {
        return System.getProperty("os.name").toLowerCase().contains("win");
    }

//    private final PreviousWindow originalWindow;

//    public IpeSteps(PreviousWindow originalWindow) {
//        this.originalWindow = originalWindow;
//    }

    private Set<String> existingHandles = new HashSet<>();

    private enum LoginReadiness {
        SIGNED_IN,
        LOGIN_FORM,
        UNKNOWN
    }

    public void performOnlyOnWindows(Runnable action) {
        if (isWindows()) {
            action.run();
        } else {
            System.out.println("Skipping action — not Windows environment");
        }
    }



    @And("^(?:I |)perform login$")
    public void performLogin() {
        setCustomTimeoutSec(60);
        if (skipLoginIfAlreadySignedIn()) {
            return;
        }

        //User information is retrieved for the specified login from the IpeUserRepository repository.
        IpeUser user = IpeUserRepository.getUserInfo();
        if (System.getProperty("auth.method") == null || System.getProperty("auth.method").equalsIgnoreCase(IpeLoginFormType.SAPID.toString())) {
//            if ($x(DEFAULT_IDENTITY_PROVIDER).shouldBe(visible, Duration.ofSeconds(30)).exists()) {
//                $x(DEFAULT_IDENTITY_PROVIDER).click();
//            }
            clickIfVisible(DEFAULT_IDENTITY_PROVIDER, "Default Identity Provider");
            $x(USERNAME_INPUT_T5).should(Condition.visible, Duration.ofSeconds(45)).setValue(user.getLogin());
            Selenide.sleep(700);
            $x(Continue_Button_T5).should(Condition.visible, Duration.ofSeconds(5)).click();
            Selenide.sleep(700);
            try {
                if ($x(UNDERSTOOD_COOKIE_WARNING).shouldBe(visible, Duration.ofSeconds(7)).exists()) {
                    $x(UNDERSTOOD_COOKIE_WARNING).click();
                    Selenide.sleep(2000);
                }
            } catch (ElementNotFound e) {
                AllureUtils.logActionF("Understood Button Not Found");
            }
            $x(PASSWORD_INPUT_T5).should(Condition.visible, Duration.ofSeconds(45)).setValue(user.getPassword());
            Selenide.sleep(700);
            $x(SIGN_IN_BUTTON_T5).shouldBe(enabled).click();
            Selenide.sleep(700);
            waitForSignedInApp(Duration.ofSeconds(60), "after login");
            saveScreenshot();
        } else {
            System.out.println("Authentication method not found: " + System.getProperty("auth.method"));
        }
    }

    @And("^(?:I |)perform Mfg 2.4 login$")
    public void mfg24PerformLogin() {
        setCustomTimeoutSec(60);
        if (skipLoginIfAlreadySignedIn()) {
            return;
        }

        //User information is retrieved for the specified login from the IpeUserRepository repository.
        IpeUser user = IpeUserRepository.getUserInfo();
        if (System.getProperty("auth.method") == null || System.getProperty("auth.method").equalsIgnoreCase(IpeLoginFormType.SAPID.toString())) {
            clickIfVisible(DEFAULT_IDENTITY_PROVIDER, "Default Identity Provider");
            $x(USERNAME_INPUT_T5).should(Condition.visible, Duration.ofSeconds(45)).setValue(user.getLogin());
            Selenide.sleep(700);
            $x(Continue_Button_T5).should(Condition.visible, Duration.ofSeconds(5)).click();
            Selenide.sleep(700);
            try {
                if ($x(UNDERSTOOD_COOKIE_WARNING).shouldBe(visible, Duration.ofSeconds(7)).exists()) {
                    $x(UNDERSTOOD_COOKIE_WARNING).click();
                    Selenide.sleep(2000);
                }
            } catch (ElementNotFound e) {
                AllureUtils.logActionF("Understood Button Not Found");
            }
            $x(PASSWORD_INPUT_T5).should(Condition.visible, Duration.ofSeconds(45)).setValue(user.getPassword());
            Selenide.sleep(700);
            $x(SIGN_IN_BUTTON_T5).shouldBe(enabled).click();
            Selenide.sleep(700);
            waitForSignedInApp(Duration.ofSeconds(60), "after Mfg 2.4 login");
            saveScreenshot();
        } else {
            System.out.println("Authentication method not found: " + System.getProperty("auth.method"));
        }
    }

    @And("^(?:I |)perform PG login$")
    public void performPgLogin() {
        setCustomTimeoutSec(60);
        if (skipLoginIfAlreadySignedIn()) {
            return;
        }

        IpeUser user = IpeUserRepository.getUserInfo();

        $x(PG_USERNAME_INPUT).shouldBe(visible, Duration.ofSeconds(45)).setValue(user.getLogin());
        Selenide.sleep(700);
        $x(PG_PASSWORD_INPUT).shouldBe(visible, Duration.ofSeconds(45)).setValue(user.getPassword());
        Selenide.sleep(700);
        $x(PG_SIGN_ON_BUTTON).shouldBe(enabled, Duration.ofSeconds(20)).click();
        Selenide.sleep(30000);
        waitForSignedInApp(Duration.ofSeconds(60), "after PG login");
        saveScreenshot();
    }

    private boolean skipLoginIfAlreadySignedIn() {
        String currentUrl = WebDriverRunner.url();
        AllureUtils.logActionF("Checking existing signed-in session. Current URL: %s", currentUrl);

        if (waitForLoginReadiness(Duration.ofSeconds(60), "before login") == LoginReadiness.SIGNED_IN) {
            AllureUtils.logActionF("Existing signed-in session detected. Skipping login.");
            saveScreenshot();
            return true;
        }

        AllureUtils.logActionF("Existing signed-in session not detected. Login will continue. Current URL: %s", WebDriverRunner.url());
        return false;
    }

    private void waitForSignedInApp(Duration timeout, String phase) {
        LoginReadiness readiness = waitForLoginReadiness(timeout, phase);
        if (readiness == LoginReadiness.SIGNED_IN) {
            AllureUtils.logActionF("Signed-in app shell detected %s. Current URL: %s", phase, WebDriverRunner.url());
            return;
        }

        AllureUtils.logActionF("Signed-in app shell was not detected %s. Current URL: %s", phase, WebDriverRunner.url());
    }

    private LoginReadiness waitForLoginReadiness(Duration timeout, String phase) {
        long deadline = System.currentTimeMillis() + timeout.toMillis();
        while (System.currentTimeMillis() <= deadline) {
            try {
                if (isSignedInAppVisible()) {
                    AllureUtils.logActionF("Signed-in app shell detected %s. Current URL: %s", phase, WebDriverRunner.url());
                    return LoginReadiness.SIGNED_IN;
                }

                if (isLoginPageVisible()) {
                    AllureUtils.logActionF("Login page detected %s. Current URL: %s", phase, WebDriverRunner.url());
                    return LoginReadiness.LOGIN_FORM;
                }
            } catch (RuntimeException e) {
                AllureUtils.logActionF("Login readiness check still waiting %s: %s", phase, e.getMessage());
            }

            Selenide.sleep(500);
        }

        return LoginReadiness.UNKNOWN;
    }

    private boolean isSignedInAppVisible() {
        return firstVisibleElement(APP_TITLE_TOOLBAR) != null
                || firstVisibleElement(APP_TOP_MENU_BUTTON) != null
                || firstVisibleElement(PROPOSALS_TAB) != null;
    }

    private boolean clickIfVisible(String xpath, String label) {
        SelenideElement element = firstVisibleElement(xpath);
        if (element == null) {
            return false;
        }

        AllureUtils.logActionF("%s is visible. Clicking it before continuing.", label);
        element.shouldBe(enabled, Duration.ofSeconds(5)).click();
        Selenide.sleep(700);
        return true;
    }

    private boolean isLoginPageVisible() {
        return firstVisibleElement(LOGIN_WIDGET_T5) != null
                || firstVisibleElement(USERNAME_INPUT_T5) != null
                || firstVisibleElement(PASSWORD_INPUT_T5) != null
                || firstVisibleElement(PG_USERNAME_INPUT) != null
                || firstVisibleElement(PG_PASSWORD_INPUT) != null
                || firstVisibleElement(DEFAULT_IDENTITY_PROVIDER) != null;
    }

    private SelenideElement firstVisibleElement(String xpath) {
        ElementsCollection visibleElements = $$x(xpath).filter(visible);
        if (visibleElements.isEmpty()) {
            return null;
        }
        return visibleElements.first();
    }

    @And("I verify current page URL contains {string}")
    public void iVerifyCurrentPageURLContainsProposalID(String expectedRegex) {
        String currentUrl = WebDriverRunner.url();
        AllureUtils.logActionF("Current URL: " + currentUrl);

        try {
            Pattern pattern = Pattern.compile(expectedRegex);
            Matcher matcher = pattern.matcher(currentUrl);

            if (!matcher.find()) {
                TestContext.getInstance().softly.fail(
                        "❌ URL does not match expected pattern.\n" +
                                "Expected pattern: " + expectedRegex + "\n" +
                                "Actual URL: " + currentUrl
                );
            } else {
                AllureUtils.logActionF("✅ URL matches expected pattern: " + expectedRegex);
            }

        } catch (PatternSyntaxException e) {
            TestContext.getInstance().softly.fail(
                    "❌ Invalid regex pattern: " + expectedRegex + "\nError: " + e.getMessage()
            );
        }
    }

    @And("^(?:I |)expand collapsed items")
    public void expandCollapsedItems() {
        String COLLAPSED_ITEM = "//*[@role='grid' and @aria-hidden='false']//*[contains(@class, 'x-pivot-grid-group-header-collapsed') and not(. = //*[(contains(text(),'Qtr: '))]/ancestor::*[contains(@class, 'x-pivot-grid-group-header-collapsed')][1])]";
        int maxAttempts = 15; // safety limit to prevent infinite loop
        int attempts = 0;
        while (!$$x(COLLAPSED_ITEM).isEmpty() && attempts < maxAttempts) {
            SelenideElement item = $$x(COLLAPSED_ITEM).first();

            if (item.isDisplayed()) {
                item.click();
                Selenide.sleep(1000); // wait for UI to update after click
            } else {
                logActionF("Collapsed item not visible or hidden, skipping click. All items might be expanded.");
                return; // exit the whole method if item is not visible
            }

            attempts++;
        }

        if (attempts >= maxAttempts) {
            logActionF("Stopped loop after max attempts to avoid infinite loop.");
        }

        logActionF("All items are expanded");
    }

    @And("^(?:I |)switch to browser tab '(.+)'$")
    public void switchToBrowserTab(String tabName) {
        setCustomTimeoutSec(60);
        try {
            Duration timeout = Duration.ofMinutes(3);
            switchTo().window(tabName, timeout);

        } catch (AssertionError ae) {
            Selenide.refresh();
            AllureUtils.logAction("refreshing has been completed");
            Duration timeout = Duration.ofMinutes(2);
            switchTo().window(tabName, timeout);
        }
        setDefaultTimeout();
    }

    @And("^(?:I |)switch to previous tab")
    public void switchToPreviousTab() {
        Set<String> allWindowHandles = Selenide.webdriver().driver().getWebDriver().getWindowHandles();
        List<String> windowHandlesList = new ArrayList<>(allWindowHandles);

        String currentWindowHandle = Selenide.webdriver().driver().getWebDriver().getWindowHandle();
        int currentIndex = windowHandlesList.indexOf(currentWindowHandle);

        if (currentIndex > 0) {
            String previousWindowHandle = windowHandlesList.get(currentIndex - 1);
            Selenide.switchTo().window(previousWindowHandle);
            System.out.println("Switched to the previous tab successfully.");
        } else {
            System.out.println("No previous tab available.");
        }
    }

    @And("^(?:I |)switch to one of the browser tabs '(.+)' or '(.+)'$")
    public void switchToOneOfTheBrowserTabs(String tabName, String secondTabName) {
        setCustomTimeoutSec(60);

        if (!switchToTab(tabName, Duration.ofSeconds(130))) {
            switchToTab(secondTabName, Duration.ofSeconds(60));
        }


    }

    private boolean switchToTab(String tabName, Duration timeout) {
        try {
            switchTo().window(tabName, timeout);
            return true;
        } catch (AssertionError ae) {
            return false;
        }
    }

    @And("^(?:I |)close current browser tab$")
    public void closeCurrentBrowserTab() {
        closeWindow();
    }


    @And("table row is displayed with the name (.+)$")
    public void displayedTableRowWithName(String text) {
        String resolvedText = DataContainer.resolveVariablesInText(text);
        boolean isElementVisible;
        try {
            $x("//*[text()='" + resolvedText + "']").should(Condition.visible);
            isElementVisible = true;
        } catch (AssertionError e) {
            isElementVisible = false;
        }
        assertTrue(String.format("%s is not displayed", resolvedText), isElementVisible);
        logActionF("%s is displayed", resolvedText);
    }

    @And("table row is not displayed with the name (.+)$")
    public void notDisplayedTableRowWithName(String text) {
        String resolvedText = DataContainer.resolveVariablesInText(text);
        boolean isElementVisible;
        try {
            $x("//*[text()='" + resolvedText + "']").should(Condition.visible);
            isElementVisible = true;
        } catch (AssertionError e) {
            isElementVisible = false;
        }
        Assert.assertFalse(String.format("%s is displayed", resolvedText), isElementVisible);
        logActionF("%s is deleted", resolvedText);
    }

    // todo - temporary step, swap current implementation on table interfaces
    @And("I select table row with name (.+)$")
    public void selectTableRowWithName(String text) {
        String resolvedText = DataContainer.resolveVariablesInText(text);
        $x("//*[text()='" + resolvedText + "']").should(Condition.visible).click();
        logActionF("%s is selected", resolvedText);
    }

    public void debugPageLoadState() {
        WebDriver driver = WebDriverRunner.getWebDriver();

        try {
            String url = driver.getCurrentUrl();
            System.out.println("🌐 Current URL: " + url);

            if (url == null || url.trim().isEmpty()) {
                System.out.println("⚠️ URL not available. Possibly a blank or non-HTML tab.");
                return;
            }

            if (url.startsWith("data:") || url.startsWith("chrome") || url.equals("about:blank")) {
                System.out.println("⚠️ Unsupported tab type — skipping JavaScript checks.");
                return;
            }

            JavascriptExecutor js = (JavascriptExecutor) driver;
            String readyState = (String) js.executeScript("return document.readyState");
            System.out.println("🧪 Document readyState: " + readyState);

            String title = driver.getTitle();
            System.out.println("📄 Page Title: " + title);

            String bodyText = driver.findElement(By.tagName("body")).getText();
            System.out.println("📜 Body preview: " + bodyText.substring(0, Math.min(bodyText.length(), 200)));

        } catch (Exception e) {
            System.out.println("❌ Error during page debug: " + e.getMessage());
        }
    }
    private boolean isBlankUrl(String url) {
        return url == null ||
                url.trim().isEmpty() ||
                url.equals("about:blank") ||
                url.startsWith("data:") ||
                url.startsWith("chrome");
    }

    private boolean isPageLoaded() {
        try {
            String readyState = executeJavaScript("return document.readyState");
            System.out.println("📄 document.readyState = " + readyState);
            return "complete".equals(readyState);
        } catch (Exception e) {
            System.err.println("⚠️ Exception during readyState check: " + e.getMessage());
            return false;
        }
    }

    public void waitForPageToFullyLoad(int totalTimeoutSeconds) {
        WebDriver driver = WebDriverRunner.getWebDriver();
        int refreshInterval = 15;
        int checkInterval = 5;
        int elapsed = 0;
        int timeSinceLastRefresh = 0;

        System.out.println("🚦 Waiting for page to fully load (Timeout = " + totalTimeoutSeconds + " seconds)");

        while (elapsed < totalTimeoutSeconds) {
            try {
                if (isPageLoaded()) {
                    System.out.println("✅ Page reported fully loaded at " + elapsed + " seconds");
                    return;
                }
            } catch (NoSuchSessionException e) {
                System.err.println("❌ Session invalid during page load check: " + e.getMessage());
                throw new RuntimeException("WebDriver session expired during load check", e);
            } catch (Exception e) {
                System.err.println("⚠️ Error while checking page load: " + e.getMessage());
            }

            try {
                Thread.sleep(checkInterval * 1000L);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.err.println("❌ Thread interrupted during wait");
                return;
            }

            elapsed += checkInterval;
            timeSinceLastRefresh += checkInterval;

            if (timeSinceLastRefresh >= refreshInterval) {
                try {
                    System.out.println("🔄 Refreshing page after " + elapsed + " seconds of waiting...");
                    // Confirm session is still active before refresh
                    driver.getTitle(); // trigger a harmless call to ensure session is alive
                    driver.navigate().refresh();
                    timeSinceLastRefresh = 0;
                } catch (NoSuchSessionException e) {
                    System.err.println("❌ Session lost during refresh: " + e.getMessage());
                    throw new RuntimeException("Page refresh failed - session is invalid", e);
                } catch (Exception e) {
                    System.err.println("❌ Error during page refresh: " + e.getMessage());
                    throw new RuntimeException("Page refresh failed", e);
                }
            }
        }

        throw new RuntimeException("❌ Timeout: Page did not load in " + totalTimeoutSeconds + " seconds.");
    }


    public static void checkTabBeforeSwitch(int i) {
        WebDriver driver = WebDriverRunner.getWebDriver();
        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());

        if (i <= 0 || i > tabs.size()) {
            throw new IllegalArgumentException("Tab index " + i + " is out of bounds. Opened tabs: " + tabs.size());
        }

        String currentTab = driver.getWindowHandle();

        try {
            // Temporarily switch to the target tab
            driver.switchTo().window(tabs.get(i - 1));

            // Wait up to 30 seconds for a non-empty title
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
            wait.until((ExpectedCondition<Boolean>) d ->
                    d != null && d.getTitle() != null && !d.getTitle().trim().isEmpty()
            );

            // Once ready, get title and URL
            String tabTitle = driver.getTitle();
            String tabUrl = driver.getCurrentUrl();

            System.out.println("Tab[" + i + "] Title: " + tabTitle);
            System.out.println("Tab[" + i + "] URL: " + tabUrl);

        } finally {
            // Switch back to original tab
            driver.switchTo().window(currentTab);
        }
    }

    public void waitUntilUrlIsNotBlank() {
        WebDriver driver = WebDriverRunner.getWebDriver();
        long end = System.currentTimeMillis() + 20_000; // 20 seconds

        while (System.currentTimeMillis() < end) {
            String url = driver.getCurrentUrl();
            if (url != null && !url.trim().isEmpty() && !url.equals("about:blank")) {
                System.out.println("✅ URL is loaded: " + url);
                return;
            }
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        throw new RuntimeException("❌ URL did not load within 20 seconds.");
    }


    public static String getCurrentUrlSafely(WebDriver driver, int timeoutMillis) {
        long end = System.currentTimeMillis() + timeoutMillis;
        String url = "about:blank";

        while (System.currentTimeMillis() < end) {
            try {
                url = driver.getCurrentUrl();
                if (url != null && !url.equals("about:blank")) {
                    System.out.println("✅ URL loaded: " + url);
                    return url;
                }
            } catch (Exception e) {
                System.out.println("❌ Error getting URL: " + e.getMessage());
            }

            try {
                Thread.sleep(500); // wait before next retry
            } catch (InterruptedException ignored) {}
        }

        System.out.println("❌ Timeout. Final URL: " + url);
        return url;
    }



    @Then("^I switch to tab (.+)")
    public void iSwitchToNthBrowser(int in) throws InterruptedException {
        WebDriver driver = WebDriverRunner.getWebDriver();
        Set<String> handles = driver.getWindowHandles();
        List<String> handleList = new ArrayList<>(handles);

        int index = in - 1;

        AllureUtils.logActionF("🔁 Attempting to switch to tab index: " + index + " (1-based input: " + in + ")");
        AllureUtils.logActionF("🔍 Available tabs: " + handleList.size());

        if (index < 0 || index >= handleList.size()) {
            throw new RuntimeException("❌ Invalid tab index: " + index + ". Total available tabs: " + handleList.size());
        }

        try {
//            Selenide.sleep(3000);
            String targetHandle = handleList.get(in - 1); // converting to 0-based
            driver.switchTo().window(targetHandle);
            String url = driver.getCurrentUrl();
            if (url != null && url.startsWith("edge://nurturing")) {
                AllureUtils.logActionF("⚠️ Detected 'edge://nurturing' tab. Switching back to tab index: " + index);
                targetHandle = handleList.get(in);
                driver.switchTo().window(targetHandle);
            }
            System.out.printf("✅ Switched to tab: %s", targetHandle);
            Selenide.sleep(3000);
            String finalUrl = getCurrentUrlSafely(driver, 7000);
            System.out.println("URL final tab" + finalUrl);
            boolean becameBlank = false;
            for (int i = 0; i < 9; i++) {
                String currentUrl = getCurrentUrlSafely(driver, 3000); // 3 second timeout
                System.out.println("🔍 Checking URL: " + currentUrl);

                if ("about:blank".equalsIgnoreCase(currentUrl)) {
                    becameBlank = true;
                    break;
                }
                Selenide.sleep(1000); // Check every second
            }
            if (becameBlank) {
                System.out.println("⚠️ URL became 'about:blank'. Opening finalUrl...");
                Selenide.open(finalUrl);
            } else {
                System.out.println("✅ URL did not become 'about:blank'. No action needed.");
            }
        } catch (NoSuchSessionException e) {
            System.err.println("❌ Session not found while switching to tab or loading the page: " + e.getMessage());
            throw new RuntimeException("Session expired or not found", e);
        } catch (Exception e) {
            System.err.println("❌ Unexpected error while switching tab: " + e.getMessage());
            throw e;
        }
    }
//        System.out.println("URL initial tab before wait" + WebDriverRunner.url());
//        Selenide.sleep(15000);
//        System.out.println("URL initial tab after wait" + WebDriverRunner.url());
    //            waitUntilUrlIsNotBlank();
//            saveScreenshot();
//            SelenideElement container = $x("//*[@class='x-grid-scrollbar-clipper ']//*[@class='x-grid-item-container']");
//            try {
//                container.should(Condition.appear, Duration.ofSeconds(50));
//                System.out.println("Element appeared and is visible.");
//            } catch (Exception e) {
//                System.out.println("Element did not appear within timeout.");
//            }
//            saveScreenshot();
//            Selenide.refresh();
//            Selenide.sleep(20000);
//            saveScreenshot();

//            SelenideElement loadingMask = $x("//*[@class='x-mask-msg-text']");
//            loadingMask.should(Condition.appear, Duration.ofSeconds(10));
//            loadingMask.shouldNot(Condition.visible, Duration.ofSeconds(60));
//            saveScreenshot();
//            SelenideElement loadingDocumentBox = $x("//*[contains(text(),'Loading document') and @data-ref='textEl']");
//            loadingDocumentBox.should(Condition.appear, Duration.ofSeconds(10));
//            loadingDocumentBox.shouldNot(Condition.visible, Duration.ofSeconds(60));
//            saveScreenshot();
//            Thread.sleep(30000);
//            saveScreenshot();
//            System.out.println("✅ Switched to tab index: " + index);
//            waitForPageToFullyLoad(90);
//            $x("//body[@role='application']").shouldBe(visible, Duration.ofSeconds(90));



    @And("I make a new proposal of type (.+)$")
    public void iMakeANewProposalOfType(String proposalType) {
        $x("//*[contains(@class,'x-PricingAppTitleToolbar')]//*[contains(text(),'New')]/ancestor::a").shouldBe(visible, Duration.ofSeconds(7)).hover();
        $x("//*[@role='menu' and @aria-expanded='true']//*[contains(text(),'Type')]/parent::a[@aria-hidden='false']/div").shouldBe(visible, Duration.ofSeconds(7)).hover().click();
        $x("//*[@role='menu' and @aria-expanded='true']//*[contains(text(),'"+proposalType+"')]").shouldBe(visible, Duration.ofSeconds(7)).hover().click();
        saveScreenshot();

    }

    @And("I verify wbs page dates")
    public void iVerifyWbsPageDates() {
//        Selenide.sleep(2000);
        // XPath to select both td[4] and td[5]
        WBSTable table = new WBSTable();
        int startRow = table.getColumnIndexByName("Start")+1;
        int endRow = table.getColumnIndexByName("End")+1;
        String xpath = "//table[contains(@class,'x-grid-item') and @data-recordindex!='0']//tr/td[(contains(@data-columnid,'iBEDateTimeColumn')) and (position()="+startRow+" or position()="+endRow+")]";
        // Find all matching elements
        ElementsCollection cells = $$x(xpath);
        AllureUtils.logActionF("Total date elements found: " + cells.size());
        // Extract text and store in a List<String>
        List<String> actualTexts = cells.texts();

        List<String> expectedTexts = new ArrayList<>();
        expectedTexts.add("1/1/26");
        expectedTexts.add("12/31/31");
        expectedTexts.add("1/1/26");
        expectedTexts.add("6/30/27");
        expectedTexts.add("1/1/26");
        expectedTexts.add("6/30/27");
        expectedTexts.add("1/1/26");
        expectedTexts.add("6/30/27");
        expectedTexts.add("7/1/28");
        expectedTexts.add("12/31/29");
        expectedTexts.add("7/1/28");
        expectedTexts.add("12/31/29");
        expectedTexts.add("7/1/28");
        expectedTexts.add("12/31/29");
        expectedTexts.add("1/1/30");
        expectedTexts.add("12/31/31");
        expectedTexts.add("1/1/30");
        expectedTexts.add("12/31/31");
        expectedTexts.add("1/1/30");
        expectedTexts.add("12/31/31");

        // Normalize only the date values
        List<String> normalizedActual = normalizeDates(actualTexts);
        List<String> normalizedExpected = normalizeDates(expectedTexts);

        // Log values for debugging
        AllureUtils.logActionF("Expected Data (Normalized): " + normalizedExpected);
        AllureUtils.logActionF("Actual Data (Normalized): " + normalizedActual);

        if (normalizedActual.size() != normalizedExpected.size()) {
            AllureUtils.logActionF("WARNING: Size mismatch! Expected: " + normalizedExpected.size() + ", Actual: " + normalizedActual.size());
        }
        saveScreenshot();
        Collections.sort(normalizedExpected);
        Collections.sort(normalizedActual);
        Assert.assertEquals("Mismatch between actual and expected table data.\nExpected: " + normalizedExpected + "\nActual: " + normalizedActual,
                normalizedExpected, normalizedActual);
    }

    @And("I verify phases page dates")
    public void iVerifyPhasesPageDates() {
        String xpath = "//*[@data-columnid=\"phaseTabDescription\" or @data-columnid=\"phaseTabStartColumn\" or @data-columnid='phaseTabEndColumn']//*[text()]";

        ElementsCollection cells = $$x(xpath);
        AllureUtils.logActionF("Total elements found: " + cells.size());

        List<String> actualTexts = cells.texts();

        List<String> expectedTexts = Arrays.asList(
                "Design & Build Phase", "01/01/2026", "06/30/2027",
                "Transition Phase", "07/01/2028", "12/31/2029",
                "Run & Maintain Phase", "01/01/2030", "12/31/2031"
        );

        // Normalize only the date values
        List<String> normalizedActual = normalizeDates(actualTexts);
        List<String> normalizedExpected = normalizeDates(expectedTexts);

        // Log values for debugging
        AllureUtils.logActionF("Expected Data (Normalized): " + normalizedExpected);
        AllureUtils.logActionF("Actual Data (Normalized): " + normalizedActual);

        if (normalizedActual.size() != normalizedExpected.size()) {
            AllureUtils.logActionF("WARNING: Size mismatch! Expected: " + normalizedExpected.size() + ", Actual: " + normalizedActual.size());
        }
        saveScreenshot();
        Assert.assertEquals("Mismatch between actual and expected table data.\nExpected: " + normalizedExpected + "\nActual: " + normalizedActual,
                normalizedExpected, normalizedActual);

    }

    private List<String> normalizeDates(List<String> inputs) {
        SimpleDateFormat inputFormat1 = new SimpleDateFormat("M/d/yy", Locale.US);
        SimpleDateFormat inputFormat2 = new SimpleDateFormat("MM/dd/yyyy", Locale.US);
        SimpleDateFormat outputFormat = new SimpleDateFormat("MM/dd/yyyy", Locale.US);

        List<String> normalizedList = new ArrayList<>();
        for (String value : inputs) {
            try {
                if (value.matches("\\d{1,2}/\\d{1,2}/\\d{2,4}")) { // Check if it's a date format
                    Date date;
                    if (value.length() <= 8) {
                        date = inputFormat1.parse(value);  // Handles 1/1/26
                    } else {
                        date = inputFormat2.parse(value);  // Handles 01/01/2026
                    }
                    normalizedList.add(outputFormat.format(date)); // Normalize to MM/dd/yyyy
                } else {
                    normalizedList.add(value); // Keep non-date values as they are
                }
            } catch (ParseException e) {
                normalizedList.add(value); // If parsing fails, retain original value
            }
        }
        return normalizedList;
    }


    @And("stop")
    public void stop() {
//        System.out.println($x("//tr[contains(@class,'x-grid-row') and (@data-qtip) ][..//td[2]//*[text()='TC-Manufacturing-Proposal-002 2025-06-05 01:18 ']]//td[3]/div").getText());
//        System.out.println((int) $x("//tr[contains(@class,'x-grid-row') and (@data-qtip) ][..//td[2]//*[text()='TC-Manufacturing-Proposal-002 2025-06-05 01:18 ']]//td[3]/div").getText().charAt(0)); // to see the Unicode
//        SelenideElement confirmButton = $x("//span[text()='Confirm']");
        Selenide.sleep(30000);
Selenide.sleep(3000);
// Check if it's displayed and enabled
//        AllureUtils.logActionF("Displayed: " + confirmButton.isDisplayed());
//        AllureUtils.logActionF("Enabled: " + confirmButton.isEnabled());
//        AllureUtils.logActionF("Exists: " + confirmButton.exists());
//
//        ElementsCollection buttons = $$x("//*[@role='dialog' and @aria-hidden='false' and contains(@class,'x-window x-layer')]//*[@role='toolbar' and @aria-hidden='false' and contains(@class,'x-toolbar-default-docked-bottom')]//*[@role='button' and @aria-hidden='false']//*[text()='Confirm']/ancestor::a");
//        AllureUtils.logActionF("Total buttons found: " + buttons.size());
//        for (SelenideElement element : buttons) {
//            AllureUtils.logActionF("Text: " + element.getText());
//            AllureUtils.logActionF("ID: " + element.getAttribute("id"));
//            AllureUtils.logActionF("Class: " + element.getAttribute("class"));

            // Scroll into view
//            element.scrollIntoView("{behavior: 'smooth', block: 'center'}");

            // Highlight with border and background color
//            Selenide.executeJavaScript("arguments[0].style.border='3px solid red'", element);
//            Selenide.executeJavaScript("arguments[0].style.backgroundColor='yellow'", element);
//        }
//        ElementsCollection buttons2 = $$x("//*[@role='dialog' and @aria-hidden='false' and contains(@class,'x-window x-layer')]//*[@role='toolbar' and @aria-hidden='false' and contains(@class,'x-toolbar-default-docked-bottom')]//*[@role='button' and @aria-hidden='false']//*[text()='Confirm']");
//        AllureUtils.logActionF("Total buttons found: " + buttons2.size());
//        for (SelenideElement element : buttons2) {
//            AllureUtils.logActionF("Text: " + element.getText());
//            AllureUtils.logActionF("ID: " + element.getAttribute("id"));
//            AllureUtils.logActionF("Class: " + element.getAttribute("class"));
//
//            // Scroll into view
//            element.scrollIntoView("{behavior: 'smooth', block: 'center'}");
//
//            // Highlight with border and background color
//            Selenide.executeJavaScript("arguments[0].style.border='3px solid red'", element);
//            Selenide.executeJavaScript("arguments[0].style.backgroundColor='yellow'", element);
//        }

    }

    @And("I store relative url of app in '(.+)'$")
    public void iStoreRelativeUrlOfAppIn$uniqueProposalSetupRelative(String var) {
        String relativeUrl = Selenide.executeJavaScript("return window.location.pathname + window.location.search + window.location.hash;");
        AllureUtils.logActionF("Current Relative URL: " + relativeUrl);
        DataContainer.storeVariable(var,relativeUrl);
        AllureUtils.logActionF("Relative URL :"+relativeUrl+" stored in :"+var);
    }

    @Then("I navigate to relative url '(.+)'$")
    public void iNavigateToRelativeUrl$uniqueProposalSetupRelative(String var) {
        String baseUrl = WebUtil.getSiteUrl();
        // Trim everything after .com (including slashes or query parameters)
        baseUrl = baseUrl.replaceAll("(?<=\\.com).*", "");

        String url = baseUrl+ DataContainer.resolveVariablesInText(var);
        AllureUtils.logActionF("Navigating to url: "+ url);
        open(
                url
        );

    }

    @Then("Turn off Escalation")
    public void turnOffEscalation() {

        $x("//a[text()='Edit Rates']").scrollIntoView(true).click();
        Selenide.sleep(1500);
        $x("//a//span[text()='Escalation or Inflation Rates']").click();
        Selenide.sleep(10000);
        $x("//*[@role='dialog']//*[@class='x-grid-checkcolumn']/ancestor::td[@role='gridcell']").click();


//        JavascriptExecutor js = (JavascriptExecutor) Selenide.webdriver().object();
//        js.executeScript("arguments[0].scrollIntoView({block: 'center'});", element);
//        Selenide.sleep(500); // Small delay before clicking
//        js.executeScript("arguments[0].click();", element);

//        System.out.println("Element displayed: " + element.isDisplayed());
//        System.out.println("Element enabled: " + element.isEnabled());
//        System.out.println("Element location: " + element.getLocation());

//        $x("//*[contains(@class,'x-grid-checkcolumn-cell-inner')]").shouldBe(visible).click();
        Selenide.sleep(300);
        $x("//a[@aria-hidden='false']//*[text()='Update Rates']/ancestor::a").click();
        Selenide.sleep(700);
        $x("//*[text()='Turn-off Escalation (Reset all Rates to 1)']/ancestor::a").click();
        Selenide.sleep(2000);
        $x("//span[text()='Close']/ancestor::a").click();
        Selenide.sleep(700);
        $x("//span[text()='Yes and Update Costs']/ancestor::a").click();
        Selenide.sleep(5000);
        try {
            SelenideElement yesBtn = $$x("//span[text()='Yes']/ancestor::a")
                    .findBy(visible);
            yesBtn.shouldBe(visible, Duration.ofSeconds(15))
                    .click();
        } catch (Exception e) {
            AllureUtils.logActionF("'Yes' button not clickable or not visible: " + e.getMessage());
            $x("//*[@role='dialog' and @aria-hidden='false']//*[text()='Close']").shouldBe(visible, Duration.ofSeconds(10)).click();
        }
        Selenide.sleep(7000);
        try {
            $x("//*[@data-qtip='Save']").click();
        } catch (Exception e){
            $x("//*[@role='dialog' and @aria-hidden='false']//*[text()='Close']").shouldBe(visible, Duration.ofSeconds(10)).click();
        }
        Selenide.sleep(2000);
    }

    public static boolean isCurrency(String str) {
        // Known currency symbols and codes
        List<String> currencySymbols = Arrays.asList("$", "€", "£", "¥", "₹");
        List<String> currencyCodes = Arrays.asList("USD", "EUR", "GBP", "JPY", "INR", "AUD", "CAD", "CHF", "CNY", "SEK", "NZD");

        str = str.trim();
        if (str.isEmpty()) return false;

        String[] tokens = str.split("\\s+");
        if (tokens.length == 0) return false;

        String prefix = tokens[0];
        return currencySymbols.contains(prefix) || currencyCodes.contains(prefix);
    }

    // Normalize currency string to BigDecimal
    public static BigDecimal parseCurrency(String text) {
        if (text == null || text.trim().isEmpty()) {
            return BigDecimal.ZERO;
        }
        try {
            // Remove all characters except digits, minus sign, and dot
            String numberOnly = text.replaceAll("[^\\d.-]", "");

            // Check if result is still valid
            if (numberOnly.isEmpty() || numberOnly.equals("-") || numberOnly.equals(".")) {
                return BigDecimal.ZERO;
            }

            return new BigDecimal(numberOnly);
        } catch (NumberFormatException e) {
            // Optional: log error
            System.err.println("Invalid currency format: " + text);
            return BigDecimal.ZERO;
        }
    }

    public static boolean isSortedAscending(List<String> inputList) {

        for (int i = 0; i < inputList.size() - 1; i++) {
            String current = inputList.get(i);
            String next = inputList.get(i + 1);

            if (current == null || next == null) continue; // or return false if nulls are invalid

            if (current.compareTo(next) > 0) {
                return false;
            }
        }
        return true;
    }


    @Then("^(?:I |)verify sorting in '(.+)' column of proposal table$")
    public void iVerifySortingInColumn(String column) throws InterruptedException {

        $x("//span[text()='"+column+"']/ancestor::*[@data-ref='titleEl']").scrollIntoView(true).shouldBe(Condition.visible, Duration.ofSeconds(30)).hover();
        $x("//span[text()='"+column+"']/ancestor::*[@data-ref='titleEl']//*[@class='x-column-header-trigger']").shouldBe(Condition.visible, Duration.ofSeconds(10)).click();
        $x("//*[text()='Sort Ascending']/ancestor::*[@role='menuitem' and @aria-hidden='false']").shouldBe(Condition.visible, Duration.ofSeconds(10)).scrollIntoView(true).hover().click();
        if($x("//*[@role='progressbar']//*[@data-ref='msgTextEl']").shouldBe(Condition.visible, Duration.ofSeconds(10)).isDisplayed()){
            AllureUtils.saveScreenshot();
            AllureUtils.logActionF("Loading icon detected by script");
        } else {
            AllureUtils.saveScreenshot();
            AllureUtils.logActionF("Loading icon not detected by script");
        }
        Thread.sleep(7000);
        proposalTable table = new proposalTable();
        String columnIndex = Integer.toString(table.getColumnIndexByName(column)+1);
        ElementsCollection elements = $$x("//*[@data-ref='body']//table//tr/td["+columnIndex+"]/div");
        List<String> actualTexts = new ArrayList<>();
        for (SelenideElement el : elements) {
            actualTexts.add(el.getAttribute("textContent"));
        }
        AllureUtils.logActionF("Found texts: "+ actualTexts);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/yy", Locale.ENGLISH);
        DateTimeFormatter yearFormatter = DateTimeFormatter.ofPattern("M/d/yyyy", Locale.ENGLISH);

        // Check if all values are valid dates
        boolean allAreDates = actualTexts.stream().allMatch(text -> {
            try {
                LocalDate.parse(text, formatter);
                return true;
            } catch (DateTimeParseException e) {
                try {
                    LocalDate.parse(text, yearFormatter);
                    return true;
                } catch (DateTimeParseException e2) {
                    return false;
                }
            }
        });

        boolean allAreCurrencies = actualTexts.stream().allMatch(IpeSteps::isCurrency);
        boolean mostAreCurrencies = actualTexts.stream()
                .filter(IpeSteps::isCurrency)
                .count() > actualTexts.size() / 2;

        if (allAreDates) {
            // 👉 Handle as dates
            List<LocalDate> actualDates = actualTexts.stream()
                    .map((dateStr) -> {
                        try {
                            return LocalDate.parse(dateStr, formatter);
                        } catch (DateTimeParseException e) {
                            return LocalDate.parse(dateStr, yearFormatter);
                        }
                    })
                    .collect(Collectors.toList());

            List<LocalDate> sortedDates = new ArrayList<>(actualDates);
            Collections.sort(sortedDates);

            if (!actualDates.equals(sortedDates)) {
                throw new AssertionError("❌ Dates not sorted.\nActual: " + actualDates + "\nExpected: " + sortedDates);
            } else {
                AllureUtils.logActionF("✅ Dates are sorted. ");
            }

        } else if (mostAreCurrencies) {
            List<BigDecimal> numbers = actualTexts.stream()
                    .map(IpeSteps::parseCurrency)
                    .collect(Collectors.toList());
            List<BigDecimal> sorted = numbers.stream()
                    .sorted()
                    .collect(Collectors.toList());
            if (!numbers.equals(sorted)) {
                throw new AssertionError("❌ Currencies not sorted.\nActual: " + numbers + "\nExpected: " + sorted);
            } else {
                AllureUtils.logActionF("✅ Currencies are sorted.");
            }
        } else {
            boolean isAscending = isSortedAscending(actualTexts);
            if (!isAscending) {
                List<String> sorted = new ArrayList<>(actualTexts);
                sorted.sort(String::compareToIgnoreCase);
                throw new AssertionError("❌ Strings not sorted.\nActual: " + actualTexts + "\nExpected: " + sorted);
            } else {
                AllureUtils.logActionF("✅ Strings are sorted. %s", actualTexts);
            }
        }
        AllureUtils.saveScreenshot();
        AllureUtils.logActionF("Sorting in Ascending order Complete");
        $x("//span[text()='"+column+"']/ancestor::*[@data-ref='titleEl']").shouldBe(Condition.visible, Duration.ofSeconds(30)).scrollIntoView(true).click();
        if($x("//*[@role='progressbar']//*[@data-ref='msgTextEl']").shouldBe(Condition.visible, Duration.ofSeconds(10)).isDisplayed()){
            AllureUtils.saveScreenshot();
            AllureUtils.logActionF("Loading icon detected by script");
        } else {
            AllureUtils.saveScreenshot();
            AllureUtils.logActionF("Loading icon not detected by script");
        }
        Thread.sleep(7000);
        ElementsCollection elementsDesc = $$x("//*[@data-ref='body']//table//tr/td["+columnIndex+"]/div");
        List<String> actualTextsDesc = elementsDesc.texts();
        boolean isDescending = true; // set to false for ascending

        if (allAreDates) {
            // 📆 Handle as dates
            List<LocalDate> actualDates = actualTextsDesc.stream()
                    .map((dateStr) -> {
                        try {
                            return LocalDate.parse(dateStr, formatter);
                        } catch (DateTimeParseException e) {
                            return LocalDate.parse(dateStr, yearFormatter);
                        }
                    })
                    .collect(Collectors.toList());

            List<LocalDate> sortedDates = new ArrayList<>(actualDates);
            sortedDates.sort(isDescending ? Comparator.reverseOrder() : Comparator.naturalOrder());

            if (!actualDates.equals(sortedDates)) {
                throw new AssertionError("❌ Dates not sorted in " + (isDescending ? "descending" : "ascending") + " order.\nActual: " + actualDates + "\nExpected: " + sortedDates);
            } else {
                AllureUtils.logActionF("✅ Dates are sorted in " + (isDescending ? "descending" : "ascending") + " order.");
            }

        } else if (mostAreCurrencies) {
            List<BigDecimal> numbers = actualTextsDesc.stream()
                    .map(IpeSteps::parseCurrency)
                    .collect(Collectors.toList());
            List<BigDecimal> sorted = numbers.stream()
                    .sorted((a, b) -> b.compareTo(a))
                    .collect(Collectors.toList());
            if (!numbers.equals(sorted)) {
                throw new AssertionError("❌ Currencies not sorted in Descending.\nActual: " + numbers + "\nExpected: " + sorted);
            } else {
                AllureUtils.logActionF("✅ Currencies are sorted in Descending.");
            }
        } else {
            List<String> sortedDesc = new ArrayList<>(actualTextsDesc);
            sortedDesc.sort(Comparator.reverseOrder());
            boolean listIsDescending = actualTextsDesc.equals(sortedDesc);
            if (listIsDescending) {
                AllureUtils.logActionF("✅ Strings are sorted in " + (isDescending ? "descending" : "ascending") + " order.");
            } else {
                throw new AssertionError("❌ Strings not sorted in " + (isDescending ? "descending" : "ascending") + " order.\nActual: " + actualTextsDesc + "\nExpected: " + sortedDesc);
            }
        }
        AllureUtils.saveScreenshot();
        AllureUtils.logActionF("Sorting in Descending order Complete");
    }

    @Then("Debug test")
    public void debugTest() {

        $x("//div[contains(@class, 'wbsTree') and @aria-hidden='false']").shouldBe(visible, Duration.ofSeconds(10));
        $x("//*[contains(@class, 'x-toolbar') and @aria-hidden='false' and @role='group']//a[contains(@class, 'x-split-button')]").shouldBe(visible, Duration.ofSeconds(10));
        System.out.println($$x("//*[contains(@class, 'x-toolbar') and @aria-hidden='false' and @role='group']//a[contains(@class, 'x-split-button')]").size());

    }

    private static OkHttpClient getUnsafeOkHttpClient() {
        try {
            TrustManager[] trustAllCerts = new TrustManager[]{
                    new X509TrustManager() {
                        public void checkClientTrusted(X509Certificate[] chain, String authType) {}
                        public void checkServerTrusted(X509Certificate[] chain, String authType) {}
                        public X509Certificate[] getAcceptedIssuers() { return new X509Certificate[]{}; }
                    }
            };

            SSLContext sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, trustAllCerts, new SecureRandom());

            return new OkHttpClient.Builder()
                    .sslSocketFactory(sslContext.getSocketFactory(), (X509TrustManager) trustAllCerts[0])
                    .hostnameVerifier((hostname, session) -> true)
                    .build();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public static String prettyJson(JSONObject json) {
        return json.toString(2);  // Indentation level of 2 spaces for better readability
    }

    @Then("I verify list of all customers")
    public void iVerifyListOfAllCustomers() throws IOException {
        Set<String> initialCustomerKeys = new HashSet<>();
        List<JSONObject> initialCustomerList = new ArrayList<>();
        // --- 1. Extract customer names from UI ---
        ElementsCollection listItems = $$x("//*[@data-ref='listEl' and @aria-hidden='false']/ul/li");
//        System.out.printf("/n Size of initial List = %s ", listItems.size());
        assertFalse("Customer list is empty!", listItems.isEmpty());
        for (int i = 1; i <= listItems.size(); i++) {
            String extid = $x("//*[@data-ref='listEl' and @aria-hidden='false']/ul/li["+i+"]/div/div[1]").scrollIntoView(true).getText();
            String text = $x("//*[@data-ref='listEl' and @aria-hidden='false']/ul/li["+i+"]/div/div[last()]").scrollIntoView(true).getText();
//            System.out.printf("/n extId: %s, text: %s",extid,text);
            JSONObject customer = new JSONObject();
            customer.put("EXTID", extid);
            customer.put("TEXT", text);
            initialCustomerList.add(customer);
            initialCustomerKeys.add(extid + "|" + text); // Unique key
        }
        AllureUtils.logActionF("Scrolled to last customer");
        saveScreenshot();
        JSONObject allCustomers = new JSONObject();
        allCustomers.put("count", initialCustomerList.size());
        allCustomers.put("customers", new JSONArray(initialCustomerList));
        AllureUtils.logActionF("✅ Actual customer list:\n" + prettyJson(allCustomers));

        JSONArray uiCustomerArray = (JSONArray) allCustomers.get("customers");
        System.out.println("Actual UI Customers: " + uiCustomerArray);

        List<JSONObject> uiCustomers = new ArrayList<>();
        for (int i = 0; i < uiCustomerArray.length(); i++) {
            JSONObject customer = uiCustomerArray.getJSONObject(i);
            JSONObject uiCustomer = new JSONObject();
            uiCustomer.put("EXTID", customer.optString("EXTID", ""));
            uiCustomer.put("TEXT", customer.optString("TEXT", ""));
            uiCustomers.add(uiCustomer);
        }

//        // --- 2. Call the API to get expected customer names ---
        OkHttpClient client = getUnsafeOkHttpClient();
        MediaType mediaType = MediaType.parse("text/plain");
        RequestBody body = RequestBody.create(mediaType, "");

        Request request = new Request.Builder()
                .url("https://20.127.232.15:3000/customerCount")
                .get()
                .build();

        String responseBody;

        // Try-with-resources to ensure response is closed after use
        try (Response response = client.newCall(request).execute()) {
            ResponseBody bodyResponse = response.body();
            if (bodyResponse == null) {
                throw new IOException("API response body is null");
            }
            responseBody = bodyResponse.string();
        }

        JSONObject json = new JSONObject(responseBody);
        JSONArray apiCustomerArray = json.getJSONArray("customers");

        List<JSONObject> apiCustomers = new ArrayList<>();
        for (int i = 0; i < apiCustomerArray.length(); i++) {
            JSONObject customer = apiCustomerArray.getJSONObject(i);
            JSONObject apiCustomer = new JSONObject();
            apiCustomer.put("EXTID", customer.optString("EXTID", ""));
            String text = customer.optString("TEXT", "");
            // Check if the string contains <script> tag or any HTML tag
            if (text.matches("(?i).*<script.*?>.*?</script>.*") || text.matches(".*<[^>]+>.*")) {
                text = "";
            }
            apiCustomer.put("TEXT", text);
            apiCustomers.add(apiCustomer);
        }

        AllureUtils.logActionF("Expected customer list from API:\n" + apiCustomers.toString());
//        // --- 4. Sort both UI and API customer lists by EXTID ---
        uiCustomers.sort(Comparator.comparing(o -> o.getString("EXTID")));
        apiCustomers.sort(Comparator.comparing(o -> o.getString("EXTID")));
//        // --- 4. Compare both lists ---
        for (int i = 0; i < apiCustomers.size(); i++) {
            JSONObject expected = apiCustomers.get(i);
            JSONObject actual = uiCustomers.get(i);

            Assert.assertEquals("EXTID mismatch at index " + i,
                    expected.getString("EXTID"),
                    actual.getString("EXTID"));

            // TEXT comparison with normalized whitespace
            String expectedTextRaw = expected.getString("TEXT");
            String actualTextRaw = actual.getString("TEXT");

            String expectedText = expectedTextRaw.replaceAll("\\s+", "").trim();
            String actualText = actualTextRaw.replaceAll("\\s+", "").trim();

            // Debug prints
            System.out.println("Index " + i + " TEXT comparison:");
            System.out.println("  Expected (raw)   : [" + expectedTextRaw + "]");
            System.out.println("  Actual   (raw)   : [" + actualTextRaw + "]");
            System.out.println("  Expected (clean) : [" + expectedText + "]");
            System.out.println("  Actual   (clean) : [" + actualText + "]");

            Assert.assertEquals("TEXT mismatch at index " + i, expectedText, actualText);
        }
    }

    @Then("update cost and price")
    public void updateCostAndPrice() throws InterruptedException {
        int retries = 0;
        int maxRetries = 7;

        while (retries < maxRetries && !$x("//*[text()='Updated']").isDisplayed()) {
            $x("//*[@role='tabpanel' and @aria-hidden='false']//*[@titlelink='updateCostsWithFormula' or @titlelink='updateBoeCostsWithFormula']").shouldBe(visible, Duration.ofSeconds(50));
            Thread.sleep(10000);
            if($x("//*[text()='Needs Refresh']").isDisplayed()){
                AllureUtils.logActionF("Clicking on 'Update Costs With Formula' tab");
                $x("//*[@role='tabpanel' and @aria-hidden='false']//*[@titlelink='updateCostsWithFormula' or @titlelink='updateBoeCostsWithFormula']").shouldBe(enabled).click();
            }

            AllureUtils.logActionF("Waiting for 'Running' to disappear");

            try {
                $x("//*[text()='Running']").shouldBe(Condition.visible);
                $x("//*[text()='Running']").shouldBe(Condition.disappear, Duration.ofSeconds(90));
            } catch (Exception e) {
                AllureUtils.logActionF("Timeout or error while waiting for 'Running' to disappear: " + e.getMessage());
            }

            retries++;
        }

        saveScreenshot();
        if (retries == maxRetries) {
            AllureUtils.logActionF("Max retries reached. 'Updated' was not found.");
            throw new RuntimeException("'Updated' status not found after maximum retries");
        } else {
            AllureUtils.logActionF("'Updated' status found. Process completed successfully.");
        }
    }

    @Then("^I search (.+) in Proposal List page$")
    public void iSearchColumnValue(String filterText) {
        String value = filterText.substring(1, filterText.indexOf("'", 1));
        String valueToSet = DataContainer.resolveVariablesInText(value);
        String[] parts = filterText.split("' ");
        String column = parts[1];
        AllureUtils.logActionF("Searching for "+valueToSet+" in " +column+" field.");
        $x("//*[contains(@class,'fa-plus')]/ancestor::a").shouldBe(visible, Duration.ofSeconds(30));
        if(!$x("//input[@placeholder='Choose Field' and contains(@class,'x-form-empty-field')]").isDisplayed()){
            $x("//*[contains(@class,'fa-plus')]/ancestor::a").shouldBe(visible, Duration.ofSeconds(10)).click();
        }
        $x("//input[@placeholder='Choose Field' and contains(@class,'x-form-empty-field')]").shouldBe(visible, Duration.ofSeconds(15)).setValue(column);
        $x("//li[@role='option' and text()='"+column+"']").shouldBe(visible, Duration.ofSeconds(15)).click();
        $x("//input[@role='textbox' and @aria-hidden='false']").shouldBe(visible, Duration.ofSeconds(15)).setValue(valueToSet);
        $x("//input[@role='textbox' and @aria-hidden='false']").sendKeys(Keys.ENTER);
    }

    @Then("^(?:I |)verify (.+) available in the (.+) are:$")
    public void iVerifyTabsAvailableInThePageAre(String element,String screen,DataTable tabNames) {
        String xpath = "";
        if (Objects.equals(element, "tabs")){
            xpath = tabs;
        }
        if (Objects.equals(screen, "popup")){
            xpath = "//*[@role='dialog' and @aria-hidden='false']"+tabs;
        }
        saveScreenshot();
        try {
            // Log expected tab names
            List<String> expectedTabs = new ArrayList<>();
            for (String s : tabNames.asList()) {
                String trim = s.trim();
                expectedTabs.add(trim);
            }
            Allure.step("Expected tabs: " + expectedTabs);

            // Find all visible tab texts
            ElementsCollection tabElements = $$x(xpath);
            List<String> actualTabs = new ArrayList<>();
            for (SelenideElement tabElement : tabElements) {
                actualTabs.add(tabElement.getText().trim().toLowerCase());
            }
            Allure.step("Actual tabs found on the page: " + actualTabs);
            // Normalize expected list as well
            List<String> normalizedExpectedTabs = expectedTabs.stream()
                    .map(s -> s.trim().toLowerCase())
                    .collect(Collectors.toList());

            Collections.sort(normalizedExpectedTabs);
            Collections.sort(actualTabs);
            Assert.assertEquals("Tab mismatch! (case-insensitive)", normalizedExpectedTabs, actualTabs);
            Allure.step("Tab verification passed: All expected tabs are present and match.");
        } catch (AssertionError | Exception e) {
            Allure.step("❌ Tab verification failed: " + e.getMessage());
            throw e; // rethrow to fail the test
        }
    }

    @And("I exit iframe")
    public void iExitIframe() {
        switchTo().defaultContent();
    }

    @Then("I see the following TABS in the {string} Top Menu:")
    public void iSeeTheFollowingTABSInTheEstimatesTopMenu(String topMenu, DataTable dataTable) {
        List<String> expectedTexts = new ArrayList<>(dataTable.asList());

        ElementsCollection menusElements = $$x("//*[@role='tab' and @aria-hidden='false']//*[@data-ref='btnInnerEl']");
        List<String> menuTexts = new ArrayList<>();
        for(SelenideElement menusElement:menusElements){
            menuTexts.add(menusElement.getText());
        }
        Collections.sort(menuTexts);
        Collections.sort(expectedTexts);

        AllureUtils.logActionF("Visible Menu Items are:- "+menuTexts);
        AllureUtils.logActionF("Required Menu Items are:- "+expectedTexts);

        Assert.assertEquals("Mismatch in menu Items of "+topMenu+" page",expectedTexts,menuTexts);
    }
    @Then("I see the following TABS in the {string} Top Menu_-Warning-_:")
    public void iSeeTheFollowingTABSInTheEstimatesTopMenuWarning(String topMenu, DataTable dataTable) {
        List<String> expectedTexts = new ArrayList<>(dataTable.asList());

        ElementsCollection menusElements = $$x("//*[@role='tab' and @aria-hidden='false']//*[@data-ref='btnInnerEl']");
        List<String> menuTexts = new ArrayList<>();
        for(SelenideElement menusElement:menusElements){
            menuTexts.add(menusElement.getText());
        }
        menuTexts = menuTexts.stream()
                .map(String::toLowerCase)
                .collect(Collectors.toList());

        expectedTexts = expectedTexts.stream()
                .map(String::toLowerCase)
                .collect(Collectors.toList());
        Collections.sort(menuTexts);
        Collections.sort(expectedTexts);

        AllureUtils.logActionF("Visible Menu Items are:- "+menuTexts);
        AllureUtils.logActionF("Required Menu Items are:- "+expectedTexts);

        TestContext.getInstance().softly.assertThat(menuTexts)
                .as("Mismatch in menu Items of " + topMenu + " page")
                .isEqualTo(expectedTexts);
    }

    @When("I retrieve the number of open browser windows")
    public void iRetrieveTheNumberOfOpenBrowserWindows() {
        WindowLogger.logCurrentWindows("Before switching tab");
        PreviousWindow.setValue(WebDriverRunner.getWebDriver().getWindowHandle());
        existingHandles = WebDriverRunner.getWebDriver().getWindowHandles();
    }

    /** Switch to newly opened window */
    public void switchToNewWindow(Duration timeout) {
        long end = System.currentTimeMillis() + timeout.toMillis();


        while (System.currentTimeMillis() < end) {
            Set<String> current = WebDriverRunner.getWebDriver().getWindowHandles();

            Set<String> diff = new HashSet<>(current);
            Set<String> toRemove = new HashSet<>();
            diff.removeAll(existingHandles);

            if (!diff.isEmpty()) {
                if(diff.size() > 2){
                    throw new RuntimeException("Multiple new windows detected: " + diff);
                }
                if(diff.size() == 2){
                    for (String handle : diff) {
                        switchTo().window(handle);
                        String url = WebDriverRunner.getWebDriver().getCurrentUrl();
                        if (url != null && url.startsWith("edge://nurturing")) {
                            AllureUtils.logActionF("Skipping Edge internal tab: " + url);
                            toRemove.add(handle);
                        }
                    }
                }
                diff.removeAll(toRemove);
                if(diff.size() != 1){
                    throw new RuntimeException("Unable to determine the new window handle. Remaining handles: " + diff);
                }
                String newHandle = diff.iterator().next();
                switchTo().window(newHandle);
                return;
            }

            Selenide.sleep(300);
        }

        throw new RuntimeException("No new window appeared within " + timeout.getSeconds() + " seconds");
    }

    @Then("I switch to new tab")
    public void iSwitchToNewTab() {
        //Runs only after iRetrieveTheNumberOfOpenBrowserWindows is called and action that opens new tab is performed
        WindowLogger.logCurrentWindows("After switching action that opens new tab");
        switchToNewWindow(Duration.ofSeconds(30));
    }

    @Then("I switch back to previous tab")
    public void iSwitchBackToOriginalTab() {
        String beforeSwitch = WebDriverRunner.getWebDriver().getWindowHandle();
        String text = PreviousWindow.getValue();
        if (text != null) {
            switchTo().window(text);
        }else{
            throw new RuntimeException("Original window handle is null. Cannot switch back.");
        }
        PreviousWindow.setValue(beforeSwitch);
    }

    @And("I import BOM file '(.+)'$")
    public void importExcelFile(String filePath) {
        executeJavaScript("let id = document.querySelector(\"[id^='iBEImportExcelWindow']\").id;let cmp = Ext.getCmp(id);cmp.down('filefield').fileInputEl.on('change', cmp.handleFileSelection, cmp);");
        executeJavaScript("document.querySelector(\"[class='x-field x-form-item x-form-item-default x-form-readonly x-form-type-text x-box-item x-field-default x-vbox-form-item']\").style.display ='block'");
        $x("//input[@type='file']").uploadFile(new File(filePath));

    }

//    @And("I wait until uploading completed")
//    public void waitUntilLroCompleted() throws InterruptedException {
//        //sleep to wait for loading
//        com.google.common.base.Stopwatch stopwatch = com.google.common.base.Stopwatch.createStarted();
//        Thread.sleep(350 * 1000L);
//        try {
//            $x(UPLOAD_FILE_WINDOW).shouldNotBe(Condition.visible, Duration.ofSeconds(60));
//            $x(HAMBURGER_MENU).click();
//            $x(HAMBURGER_MENU_DROPDOWN).shouldBe(Condition.visible, Duration.ofSeconds(60));
//            int attempts = 0;
//            while (!$x(LAST_FINISHED_MSG).isDisplayed() | attempts < 5) {
//                $x(HAMBURGER_MENU).click();
//                $x(HAMBURGER_MENU_DROPDOWN).shouldNotBe(Condition.visible, Duration.ofSeconds(10));
//                Thread.sleep(60 * 1000L);
//                $x("//a[@data-qtip='Reload']").click();
//                Thread.sleep(60 * 1000L);
//                $x(HAMBURGER_MENU).click();
//                $x(HAMBURGER_MENU_DROPDOWN).shouldBe(Condition.visible, Duration.ofSeconds(10));
//                $x(LAST_FINISHED_MSG).shouldBe(Condition.visible, Duration.ofSeconds(10));
//                attempts++;
//            }
//            if (attempts < 5) {
//                AllureUtils.logAction("GEMS file uploading has been completed");
//                stopwatch.stop();
//                long executionTime = stopwatch.elapsed().toMinutes();
//                System.out.println("Time to GEMS importing " + executionTime + " min");
//                System.setProperty("GEMS_IMPORT_EXECUTION_TIME_IN_MINUTES", String.valueOf(executionTime));
//            } else {
//                AllureUtils.logAction("GEMS file uploading has not been completed within 5 attempts");
//                stopwatch.stop();
//                long executionTime = stopwatch.elapsed().toMinutes();
//                System.setProperty("GEMS_IMPORT_EXECUTION_TIME_IN_MINUTES", String.valueOf(executionTime));
//                System.out.println("GEMS message about successful import has not been appeared for: " + executionTime + " min");
//                AllureUtils.logAction("GEMS message about successful import has not been appeared for: " + executionTime + " min");
//            }
//        } catch (AssertionError ae) {
//            AllureUtils.logAction("GEMS file uploading has not been completed");
//            // throw ae;
//        }
//    }
}
