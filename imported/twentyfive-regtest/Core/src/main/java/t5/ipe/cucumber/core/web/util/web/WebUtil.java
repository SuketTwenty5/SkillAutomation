package t5.ipe.cucumber.core.web.util.web;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.cucumber.java.Scenario;
import org.assertj.core.api.SoftAssertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import t5.ipe.cucumber.core.web.CucumberRuntime;
import t5.ipe.cucumber.core.web.TestContext;
import t5.ipe.cucumber.core.web.interfaces.core.EnvProvider;
import t5.ipe.cucumber.core.web.interfaces.core.SiteMap;
import t5.ipe.cucumber.core.web.PreviousWindow;


/**
 * Util class for webapp interaction functionality.
 * Created by: EKruze
 * Date: 20/10/2023
 */
public class WebUtil {
//    static WebDriver driver;
public static DevTools devTools;
    private static final ThreadLocal<WebDriver> threadLocalDriver = new ThreadLocal<>();

    public static WebDriver getDriver() {
        return threadLocalDriver.get();
    }

    private static String currentSiteUrl;

    private WebUtil() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * Method returns URL of current web-site by its name.
     *
     * @return site URL.
     */
    public static String getSiteUrl() {
        currentSiteUrl = getCurrentEnv();
        if (currentSiteUrl == null) {
            throw new IllegalArgumentException("Unknown URL for site with name [" + currentSiteUrl + "].");
        }
        return currentSiteUrl;
    }

    public static String getCurrentSiteUrl() {
        return currentSiteUrl;
    }

    /**
     * Method initiates Selenide by ChromeDriver object, opens browser at full-screen mode and sets up Selenide timeout
     * in 10 seconds.
     */
    public static void prepareChromeDriver(Scenario scenario, String featureName) throws Exception {
        if (WebUtil.getDriver() == null) {
            String localRun = System.getProperty("local.run");
            System.out.println("Local run property: " + localRun);
            if ("true".equals(localRun)) {
                WebDriver driverLocal = WebDriverUtils.getLocalDriver();
                threadLocalDriver.set(driverLocal);
                WebDriverRunner.setWebDriver(driverLocal);
            } else {
                WebDriver driver = WebDriverUtils.constructSeleniumDriver(scenario, featureName);
                threadLocalDriver.set(driver);
                WebDriverRunner.setWebDriver(driver);
            }
            setDefaultTimeout();
            TestContext.getInstance().softly = new SoftAssertions();
        }
    }

    public static boolean isDriverQuit() {
        try {
            // Accessing a harmless method to check if session is alive
            WebUtil.getDriver().getTitle();  // Will throw if driver is quit
            return false;
        } catch (Exception e) {
            return true;
        }
    }

    public static boolean isBrowserOnValidPage() {
        if (WebUtil.getDriver() == null) return false;

        String currentUrl = WebUtil.getDriver().getCurrentUrl();
        return currentUrl != null && !currentUrl.trim().isEmpty() && !currentUrl.equals("about:blank");
    }

    public static boolean isDriverInitialized() {
        return WebUtil.getDriver() != null;
    }

    public static void setDefaultTimeout() {
        // Selenide timeouts (for element waits)
        Configuration.timeout = 60_000;            // 60 seconds for element presence
        Configuration.pageLoadTimeout = 120_000;   // 120 seconds for page loads

        // Remote WebDriver timeouts (via system properties)
        System.setProperty("webdriver.chrome.timeout", "120000");     // ChromeDriver timeout
//        System.setProperty("webdriver.http.factory", "jdk-http-client"); // Better HTTP client
    }

    public static void setCustomTimeoutSec(long sec) {
        Configuration.timeout = sec * 1000;
    }

    public static void setCustomTimeoutMs(long ms) {
        Configuration.timeout = ms;
    }

    public static void quitDriver() {
        String debug = System.getProperty("debug");
        if ("true".equalsIgnoreCase(debug)) {
            System.out.println("Debug mode is ON. Skipping WebDriver quit to allow debugging.");
        }else {
            try {
                PreviousWindow.clear();
                WebDriverRunner.getWebDriver().quit();
                WebDriverRunner.closeWebDriver(); // closes the thread-local driver properly
            } catch (Exception e) {
                System.err.println("Error while closing WebDriver: " + e.getMessage());
            } finally {
                threadLocalDriver.remove(); // remove your ThreadLocal reference
            }
        }
    }

    public static String getCurrentEnv() {
        return getImplementation(EnvProvider.class).getCurrentEnv();
    }

    public static String getCurrentSite() {
        String siteName = System.getProperty("site");
        return siteName;
    }


    private static SiteMap getSiteMap() {
        return getImplementation(SiteMap.class);
    }

    /**
     * Method performs search of suitable framework interface implementation and will throw an exception with
     * implementation advice if none is found.
     *
     * @param interfaceClass type of sought implementation
     * @return implementation.
     */
    private static <T> T getImplementation(Class<T> interfaceClass) {
        try {
            return CucumberRuntime.getBean(interfaceClass);
        } catch (NoSuchBeanDefinitionException nsbde) {
            throw new RuntimeException("You should implement interface " + interfaceClass.getName()
                    + " and mark it with Spring @Component annotation.");
        }
    }
}
