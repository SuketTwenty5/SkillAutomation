package t5.ipe.cucumber.core.web.util.web;


import com.codeborne.selenide.Configuration;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.Scenario;
import io.cucumber.plugin.event.TestCaseStarted;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.HasDevTools;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.remote.RemoteWebDriver;
import t5.ipe.cucumber.core.web.VideoRecorderUtil;
import t5.ipe.cucumber.core.web.util.AllureUtils;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.safari.SafariOptions;


import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.nio.file.Path;
import java.time.Duration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.lang.reflect.Field;
import java.util.Scanner;
import java.util.logging.Level;

import static t5.ipe.cucumber.core.web.CucumberRuntime.getTimestamp;
import io.github.bonigarcia.wdm.WebDriverManager;
import java.nio.file.*;
import org.openqa.selenium.remote.Augmenter;


/**
 * Class responsible for driver instance preparation.
 * <p>
 * Created by: EKruze
 * Date: 20/10/2023
 */
public class WebDriverUtils {
    public static final String OS = System.getProperty("os.name").toLowerCase();
    public static final String WINDOWS_TEST_NAME = "WindowsTest_" + getTimestamp();
    public static final String WINDOWS_EDGE_DRIVER_PATH = "C:\\Tools\\edgedriver\\edgedriver_win64\\msedgedriver.exe";

//    static {
//        System.setProperty("webdriver.http.factory", "jdk-http-client");
//    }

    public static String getInstalledEdgeVersion() throws Exception {
        Process process = Runtime.getRuntime().exec(
                "reg query \"HKCU\\Software\\Microsoft\\Edge\\BLBeacon\" /v version"
        );
        try (Scanner scanner = new Scanner(process.getInputStream())) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line.contains("version")) {
                    return line.replaceAll(".*REG_SZ\\s+", "").trim();
                }
            }
        }
        return null;
    }
    public static String getEdgeDriverVersion(String driverPath) throws Exception {
        Process process = Runtime.getRuntime().exec(driverPath + " --version");
        try (Scanner scanner = new Scanner(process.getInputStream())) {
            return scanner.hasNextLine() ? scanner.nextLine() : null;
        }
    }

    public static boolean isUpdateRequired(String edgeVersion, String driverVersion) {
        String edgeMajor = edgeVersion.split("\\.")[0];
        String driverMajor = driverVersion.replaceAll("[^0-9.]", "").split("\\.")[0];
        return !edgeMajor.equals(driverMajor);
    }



    public static WebDriver getLocalDriver() throws Exception {
            String driverPath = null;
            System.out.println("Operating System: [" + OS + "]");
            System.out.println("Length: " + OS.length());
            String browser = System.getProperty("browser");
            System.out.println("*****Browser specified: " + browser+" *****");

            if (OS.contains("win") && (browser == null || browser.equalsIgnoreCase("chrome"))) {
                String path1 = "C:\\Users\\RegressionTest\\AppData\\Local\\Microsoft\\WinGet\\Links\\chromedriver.exe";;
                String path2 = "C:\\Tools\\chromedriver\\chromedriver-win64\\chromedriver.exe";

                // Check which one exists
                if (new File(path1).exists()) {
                    driverPath = path1;
                } else if (new File(path2).exists()) {
                    driverPath = path2;
                } else {
                    System.err.println("ChromeDriver not found in either path!");
                    System.exit(1);
                }

                System.setProperty("webdriver.chrome.driver", driverPath);
//                WebDriverManager.chromedriver()
//                        .browserVersion("142.0.7444.60")  // your exact Chrome version
//                        .setup();
            }else if(OS.contains("win") && "edge".equalsIgnoreCase(browser)){
                String edgeDriverPath1 = WINDOWS_EDGE_DRIVER_PATH;
//                String edgeVersion = getInstalledEdgeVersion();
//
//                String currentDriverVersion = getEdgeDriverVersion(edgeDriverPath1);
//
//                assert edgeVersion != null;
//                assert currentDriverVersion != null;
//                if (isUpdateRequired(edgeVersion, currentDriverVersion)) {
//
//                    Path downloadedDriver = downloadLatestEdgeDriver();
//                    replaceEdgeDriver(downloadedDriver);
//
//                } else {
//                   AllureUtils.logActionF("✅ EdgeDriver already up to date");
//                }

                if (new File(edgeDriverPath1).exists()) {
                    driverPath = edgeDriverPath1;
                } else {
                    AllureUtils.logActionF("EdgeDriver not found in the specified path!");
                    System.exit(1);
                }
                System.setProperty("webdriver.edge.driver", driverPath);
            }
            boolean windowsRecord = Boolean.parseBoolean(System.getProperty("windowsRecord", "false"));

            // options.setBrowserVersion("120"); --select your local version
            if (windowsRecord && OS.contains("win")) {
                VideoRecorderUtil.startRecording(WINDOWS_TEST_NAME);
            }
            WebDriver driver = null;
            if(browser == null || browser.equalsIgnoreCase("chrome")) {
                String downloadFilepath = System.getProperty("user.dir") + "\\target\\uploads";
                System.setProperty("selenide.downloadsFolder", downloadFilepath);
                HashMap<String, Object> chromePrefs = new HashMap<>();
                chromePrefs.put("profile.default_content_settings.popups", 0);
                chromePrefs.put("download.default_directory", downloadFilepath);
                final ChromeOptions options = new ChromeOptions();
                options.setExperimentalOption("prefs", chromePrefs);
                options.addArguments("--ignore-ssl-errors=yes", "--ignore-certificate-errors");
                String debug = System.getProperty("debug");
//                System.setProperty("webdriver.http.factory", "jdk-http-client");
                if ("true".equalsIgnoreCase(debug)) {
                    System.out.println("DEBUG MODE: Attaching to existing Edge browser");
//                    "/Applications/Google\ Chrome.app/Contents/MacOS/Google\ Chrome \
//                    --remote-debugging-port=9222 \
//                    --user-data-dir=/tmp/LMDevProfile"
//                    try {

                        // Try attaching to existing browser
                        options.setExperimentalOption("debuggerAddress", "localhost:9222");
                        System.out.println("Trying to attach to existing Chrome...");
                        Configuration.holdBrowserOpen = true;
                        driver = new ChromeDriver(options);

//                    } catch (Exception e) {
//
//                        System.out.println("No debug browser found. Starting Chrome...");
//
//                        try {
//
//                            // Start Chrome from Java
//                            String chromePath = "/Applications/Google Chrome.app/Contents/MacOS/Google Chrome";
//
//                            ProcessBuilder pb = new ProcessBuilder(
//                                    chromePath,
//                                    "--remote-debugging-port=9222",
//                                    "--remote-allow-origins=*",
//                                    "--user-data-dir=/tmp/chrome-debug-profile"
//                            );
//
//                            pb.inheritIO();
//                            Process process = pb.start();
//
//                            // Wait for browser to start
//                            Thread.sleep(4000);
//
//                        } catch (IOException | InterruptedException ex) {
//                            throw new RuntimeException("Failed to start Chrome debug mode", ex);
//                        }
//
//                        // Attach after starting
//                        options.setExperimentalOption("debuggerAddress", "localhost:9222");
//                        driver = new ChromeDriver(options);
//                    }
                } else {
                    driver = new ChromeDriver(options);
                }
            } else if (browser.equalsIgnoreCase("edge")) {
                if(OS.contains("win")) {
                    String downloadFilepath = "C:\\downloads";
                    System.setProperty("selenide.downloadsFolder", downloadFilepath);
                }else{
                    String downloadFilepath = System.getProperty("user.dir") + "\\target\\uploads";
                    System.setProperty("selenide.downloadsFolder", downloadFilepath);
                }
                EdgeOptions edgeOptions = getEdgeOptions();
                driver = new EdgeDriver(edgeOptions);
            }
            // Set a fixed size first
            driver.manage().window().setSize(new Dimension(1920, 1080));

            // Then maximize (optional, may stretch)
            driver.manage().window().maximize();
            return driver;
        }

    private static EdgeOptions getEdgeOptions() {
        EdgeOptions edgeOptions = new EdgeOptions();
        edgeOptions.addArguments("--ignore-ssl-errors=yes", "--ignore-certificate-errors");
        edgeOptions.addArguments("--disable-features=EdgeNurturingService");
        edgeOptions.addArguments("--disable-features=msEdgeSidePanel");
        edgeOptions.addArguments("--disable-notifications");  // disables notifications
        edgeOptions.addArguments("--no-first-run");          // disables first-run dialogs
        return edgeOptions;
    }

    public static Path downloadLatestEdgeDriver() {
        WebDriverManager.edgedriver()
                .clearDriverCache()
                .forceDownload()
                .setup();

        return Paths.get(System.getProperty("webdriver.edge.driver"));
    }


    public static void replaceEdgeDriver(Path downloadedDriver) throws Exception {
        Path target = Paths.get(WINDOWS_EDGE_DRIVER_PATH);

        Files.createDirectories(target.getParent());

        Files.copy(
                downloadedDriver,
                target,
                StandardCopyOption.REPLACE_EXISTING
        );

        AllureUtils.logActionF("✅ EdgeDriver updated at: " + target);

        String command = String.format(
                "Unblock-File \"%s\"",
                WINDOWS_EDGE_DRIVER_PATH
        );

        Process process = new ProcessBuilder(
                "powershell",
                "-Command",
                command
        ).start();

        process.waitFor();
        AllureUtils.logActionF("✅ EdgeDriver unblocked successfully.");
    }



//    public static RemoteWebDriver constructSeleniumDriver() throws JsonProcessingException {
//        HashMap<String, Object> chromePrefs = new HashMap<>();
//        chromePrefs.put("download.default_directory", "/opt/selenium");
//        ChromeOptions options = new ChromeOptions();
//        options.setExperimentalOption("prefs", chromePrefs);
//
//        String browserOptions = System.getProperty("browser.options");
//        String[] inputOptions = browserOptions.split(";");
////      The system property browser.options - a string with commas(, ) as separators, used to define browser options. For example:headless,no-sandbox,disable-dev-shm-usage
//        if (browserOptions == null || browserOptions.isEmpty()) {
//            System.out.println("Auotests launched without any Chrome Options");
//        }
//        for (String opt : inputOptions) {
//            System.out.println("Added Chrome Option: " + opt);
//            options.addArguments("--" + opt);
//        }
////        The system property 'selenoid.capabilities' is used to define capabilities for the Selenoid browser automation tool.
////        * It should be provided as a JSON representation with escaped symbols since, as a method parameter, it must be pre-escaped.
////        JSON string representation (already escaped for use as a parameter): * ' "{\"name\":\"ipe test\",\"enableVideo\":true,\"enableVNC\":true,\"env\":[\"TZ=UTC\"]}"'
//        String browserCapabilities = System.getProperty("selenoid.capabilities");
//        System.out.println("Selenoid Capabilities: " + browserCapabilities);  // Debug log
//
//
//        ObjectMapper objectMapper = new ObjectMapper();
//        JsonNode jsonNode = objectMapper.readTree(browserCapabilities);
//        System.out.println("Parsed Capabilities: " + jsonNode.toString());  // Debug log
//
//        MutableCapabilities capabilities = new MutableCapabilities();
//        AllureUtils.logResult("User Dir: " + System.getProperty("user.true"));
//
//        capabilities.setCapability(ChromeOptions.CAPABILITY, options);
//        capabilities.setCapability("selenoid:options", jsonNode);
//
//        try {
//            String remoteWebDriverurl = "http://localhost:4444/wd/hub";
//            String selenoidRemoteUrl = "https://autoregression.twenty5.com/selenoid";
//            System.out.println("[Final WebDriver Capabilities: ]" + capabilities);
//            RemoteWebDriver driver = new RemoteWebDriver(
//                    URI.create(remoteWebDriverurl).toURL(),
//                    capabilities);
//            driver.setFileDetector(new LocalFileDetector());
//            System.out.println(String.format("[SELENOID VNC SESSION]: %s/#/sessions/%s%n",
//                    selenoidRemoteUrl,
//                    driver.getSessionId()));
//
//
//            // Log the entire selenoid:options
//            if (jsonNode != null && jsonNode.has("selenoid:options")) {
//                JsonNode selenoidOptions = jsonNode.get("selenoid:options");
//                System.out.println("Selenoid Options: ");
//                System.out.println(selenoidOptions.toPrettyString());
//            } else {
//                System.out.println("No selenoid:options found in capabilities.");
//            }
//
//            // Log the video directory
//            if (jsonNode != null && jsonNode.has("selenoid:options")) {
//                JsonNode selenoidOptions = jsonNode.get("selenoid:options");
//                if (selenoidOptions.has("video-output-dir")) {
//                    String videoDir = selenoidOptions.get("video-output-dir").asText();
//                    System.out.println("Selenoid Video Directory: " + videoDir);
//                } else {
//                    System.out.println("Selenoid video directory not found in capabilities.");
//                }
//            }
//
//            // Check if video recording is enabled
//            boolean isVideoEnabled = jsonNode != null && jsonNode.has("selenoid:options") &&
//                    jsonNode.get("selenoid:options").has("video") &&
//                    jsonNode.get("selenoid:options").get("video").asBoolean();
//            if (isVideoEnabled) {
//                System.out.println("Video recording is enabled for this session.");
//            } else {
//                System.out.println("Video recording is NOT enabled for this session.");
//            }
//            driver.manage().window().maximize();
//            return driver;
//        } catch (MalformedURLException e) {
//            System.err.println("Incorrect remote driver hub url");
//            throw new RuntimeException("unable construct remote driver");
//        }
//    }

//    public static RemoteWebDriver constructSeleniumDriver(Scenario scenario, String currentFeatureFile) {
//        // Chrome preferences
//        HashMap<String, Object> chromePrefs = new HashMap<>();
//        chromePrefs.put("download.default_directory", "/opt/selenium");
//
//        // Setting Chrome options
//        ChromeOptions options = new ChromeOptions();
//        LoggingPreferences logPrefs = new LoggingPreferences();
//        logPrefs.enable(LogType.BROWSER, Level.ALL);        // Console logs
//        logPrefs.enable(LogType.PERFORMANCE, Level.ALL);    // Network logs (XHR)
//        options.setCapability("goog:loggingPrefs", logPrefs);
//
//        options.setExperimentalOption("prefs", chromePrefs);
//
//        options.addArguments("--no-sandbox");// Disable the sandbox for stability
//        options.addArguments("--disable-gpu");
//        options.addArguments("--disable-renderer-backgrounding");
//        options.addArguments("--disable-software-rasterizer");
//        options.addArguments("--disable-background-timer-throttling");
//        options.addArguments("--disable-backgrounding-occluded-windows");
//        options.addArguments("--disable-breakpad");
//        options.addArguments("--memory-pressure-off");
//        options.addArguments("--disable-dev-shm-usage");// Overcome limited resources in containers
//        options.addArguments("--incognito");
//        options.addArguments("--disable-extensions");  // Disable browser extensions
//        options.addArguments("--remote-allow-origins=*");  // Prevent CORS issues
//        options.addArguments("--disable-popup-blocking");  // Allow pop-ups
//        options.addArguments("--disable-infobars");
//        options.addArguments("--ignore-certificate-errors");
////        options.addArguments("--disable-blink-features=AutomationControlled");
//        options.addArguments("--lang=en-US");  // Set browser language
//        options.addArguments("--disable-notifications");  // Block notification popups
//        options.addArguments("--user-agent=Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36");  // Set custom User-Agent
//
////        // Critical Timeout Configurations (NEW)
////        Map<String, Integer> value = new HashMap<>();
////        value.put("script", 180000);
////        value.put("pageLoad", 120000);
////        value.put("implicit", 0);
////        options.setCapability("se:timeouts", value);
//        options.setPageLoadStrategy(PageLoadStrategy.NORMAL); // Wait for full page load
//
//        // Hardcoding selenoid:options with video recording enabled
//        MutableCapabilities selenoidOptions = new MutableCapabilities();
//        selenoidOptions.setCapability("name", "BTP-Gold-Dev-"+currentFeatureFile);  // Customize as needed
//        selenoidOptions.setCapability("enableVideo", true);  // Enable video recording
//        selenoidOptions.setCapability("video-output-dir", "/opt/selenoid/video");  // Specify video directory
//        selenoidOptions.setCapability("enableVNC", true);  // Enable VNC if needed
//        selenoidOptions.setCapability("env", new String[] { "TZ=UTC" });
//        selenoidOptions.setCapability("enableLog", true);
//        selenoidOptions.setCapability("sessionTimeout", "15m");
//
//        // Add selenoid options to Chrome capabilities
//        MutableCapabilities capabilities = new MutableCapabilities();
//        capabilities.setCapability(ChromeOptions.CAPABILITY, options);
//        capabilities.setCapability("selenoid:options", selenoidOptions);
//
//
//
//        try {
//            // Selenoid hub URL (adjust if needed)
//            String remoteWebDriverurl = "http://localhost:4444/wd/hub";
//            String selenoidRemoteUrl = "https://autoregression.twenty5.com/selenoid";
//
//            // Log capabilities
//            System.out.println("[Final WebDriver Capabilities: ]" + capabilities);
//
//            // Initialize RemoteWebDriver with the hardcoded capabilities
//            RemoteWebDriver driver = new RemoteWebDriver(
//                    URI.create(remoteWebDriverurl).toURL(),
//                    capabilities);
//            driver.setFileDetector(new LocalFileDetector());
//
//            // Additional Timeout Settings (NEW)
//            driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(120));
//            driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(90));
//
//            // Print Selenoid VNC session URL
//            System.out.println(String.format("[SELENOID VNC SESSION]: %s/#/sessions/%s%n",
//                    selenoidRemoteUrl,
//                    driver.getSessionId()));
//
//            driver.manage().window().maximize();
//            AllureUtils.logActionF("Driver is Initialized");
//            return driver;
//        } catch (MalformedURLException e) {
//            System.err.println("Incorrect remote driver hub URL");
//            throw new RuntimeException("Unable to construct remote driver");
//        }
//    }

    public static RemoteWebDriver constructSeleniumDriver(Scenario scenario, String currentFeatureFile) {
        String browser = System.getProperty("browser", "chrome").toLowerCase(); // Default: chrome
        String resolution = System.getProperty("resolution", "1920x1080");
        RemoteWebDriver driver;

        // Parse width and height
        String[] res = resolution.split("x");
        int width = Integer.parseInt(res[0]);
        int height = Integer.parseInt(res[1]);


        try {
            // Selenoid hub URLs
            String remoteWebDriverUrl = "http://localhost:4444/wd/hub";
            String selenoidRemoteUrl = "https://autoregression.twenty5.com/selenoid";

            // ---- Common Selenoid Capabilities ----
            MutableCapabilities selenoidOptions = new MutableCapabilities();
            selenoidOptions.setCapability("name", "BTP-Gold-Dev-" + currentFeatureFile);
            selenoidOptions.setCapability("enableVideo", true);
            selenoidOptions.setCapability("videoOutputDir", "/opt/selenoid/video");
            selenoidOptions.setCapability("enableVNC", true);
            selenoidOptions.setCapability("env", new String[]{"TZ=UTC"});
            selenoidOptions.setCapability("enableLog", true);
            selenoidOptions.setCapability("sessionTimeout", "15m");
            selenoidOptions.setCapability("screenResolution", width + "x" + height + "x24");

            MutableCapabilities capabilities = new MutableCapabilities();

            switch (browser) {
                // ---------------- CHROME ----------------
                case "chrome":
                    HashMap<String, Object> chromePrefs = new HashMap<>();
                    chromePrefs.put("download.default_directory", "/opt/selenium");

                    ChromeOptions chromeOptions = new ChromeOptions();
                    chromeOptions.setExperimentalOption("prefs", chromePrefs);

                    LoggingPreferences chromeLogPrefs = new LoggingPreferences();
                    chromeLogPrefs.enable(LogType.BROWSER, Level.ALL);
                    chromeLogPrefs.enable(LogType.PERFORMANCE, Level.ALL);
                    chromeOptions.setCapability("goog:loggingPrefs", chromeLogPrefs);

                    // ✅ all your original arguments preserved
                    chromeOptions.addArguments("--no-sandbox");
                    chromeOptions.addArguments("--disable-gpu");
                    chromeOptions.addArguments("--disable-renderer-backgrounding");
                    chromeOptions.addArguments("--disable-software-rasterizer");
                    chromeOptions.addArguments("--disable-background-timer-throttling");
                    chromeOptions.addArguments("--disable-backgrounding-occluded-windows");
                    chromeOptions.addArguments("--disable-breakpad");
                    chromeOptions.addArguments("--memory-pressure-off");
                    chromeOptions.addArguments("--disable-dev-shm-usage");
                    chromeOptions.addArguments("--incognito");
                    chromeOptions.addArguments("--disable-extensions");
                    chromeOptions.addArguments("--remote-allow-origins=*");
                    chromeOptions.addArguments("--disable-popup-blocking");
                    chromeOptions.addArguments("--disable-infobars");
                    chromeOptions.addArguments("--ignore-certificate-errors");
                    chromeOptions.addArguments("--lang=en-US");
                    chromeOptions.addArguments("--disable-notifications");
                    chromeOptions.addArguments("--user-agent=Mozilla/5.0 (Windows NT 10.0; Win64; x64) " +
                            "AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36");

                    chromeOptions.setPageLoadStrategy(PageLoadStrategy.NORMAL);
//                    chromeOptions.setBrowserVersion("121.0");
                    chromeOptions.setCapability("selenoid:options", selenoidOptions);
                    capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
                    break;

                // ---------------- FIREFOX ----------------
                case "firefox":
                    //                    FirefoxOptions firefoxOptions = getFirefoxOptions();

                    // Attach Selenoid options
                    //                    firefoxOptions.setCapability("selenoid:options", selenoidOptions);
                    //                    capabilities.setCapability(FirefoxOptions.FIREFOX_OPTIONS, firefoxOptions);
                    // ======================================
                    // 2. FirefoxOptions
                    // ======================================
                    FirefoxOptions firefoxOptions = new FirefoxOptions();

                    // --- Download settings ---
                    firefoxOptions.addPreference("browser.download.dir", "/opt/selenium");
                    firefoxOptions.addPreference("browser.download.folderList", 2);
//                    firefoxOptions.addPreference("browser.helperApps.neverAsk.saveToDisk",
//                            "application/pdf,application/octet-stream,application/vnd.ms-excel");
                    firefoxOptions.addPreference("pdfjs.disabled", true);

                    // --       - User agent ---
                    firefoxOptions.addPreference("general.useragent.override",
                            "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 "
                                    + "(KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36");

                    // --- Firefox arguments (Chrome equivalents) ---
                    firefoxOptions.addArguments("-private");  // Incognito equivalent
//                    firefoxOptions.addArguments("--headless");
                    firefoxOptions.setPageLoadStrategy(PageLoadStrategy.NORMAL);

                    // --- Page load strategy ---
//                    firefoxOptions.setPageLoadStrategy(PageLoadStrategy.NORMAL);

                    // ======================================
                    // 3. Logging prefs (Chrome equivalent)
                    // ======================================
//                    LoggingPreferences logPrefs = new LoggingPreferences();
//                    logPrefs.enable(LogType.BROWSER, Level.ALL);
//                    logPrefs.enable(LogType.CLIENT, Level.ALL);
//                    logPrefs.enable(LogType.DRIVER, Level.ALL);

//                    capabilities.setCapability("loggingPrefs", logPrefs);

                    // ======================================
                    // 4. Attach FirefoxOptions to capabilities
                    // ======================================
                    capabilities.setCapability(FirefoxOptions.FIREFOX_OPTIONS, firefoxOptions);
                    // ---- REQUIRED ----
                    capabilities.setCapability("browserName", "firefox");
                    capabilities.setCapability("acceptInsecureCerts", true);
//                    capabilities.setCapability("pageLoadStrategy", "normal");
                    break;

                // ---------------- EDGE ----------------
                case "edge":
                    EdgeOptions edgeOptions = new EdgeOptions();
                    edgeOptions.addArguments(
                            "--no-sandbox",
                            "--disable-gpu",
                            "--disable-dev-shm-usage",
                            "--disable-extensions",
                            "--incognito",
                            "--disable-popup-blocking",
                            "--ignore-certificate-errors"
                    );
                    edgeOptions.setPageLoadStrategy(PageLoadStrategy.NORMAL);
                    capabilities.setCapability(EdgeOptions.CAPABILITY, edgeOptions);
                    break;

                // ---------------- SAFARI ----------------
                case "safari":
                    SafariOptions safariOptions = new SafariOptions();
                    safariOptions.setCapability("safari:useTechnologyPreview", false);
                    safariOptions.setPageLoadStrategy(PageLoadStrategy.NORMAL);
                    capabilities.merge(safariOptions);
                    break;

                default:
                    throw new IllegalArgumentException("Unsupported browser: " + browser);
            }


            capabilities.setCapability("selenoid:options", selenoidOptions);

            System.out.println("Creating session with capabilities: " + capabilities);
            System.out.println("User Dir: " + System.getProperty("user.dir"));

            // ---- Initialize driver ----
            driver = new RemoteWebDriver(URI.create(remoteWebDriverUrl).toURL(), capabilities);
            driver.setFileDetector(new LocalFileDetector());
            driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(120));
            driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(90));
            driver.manage().window().maximize();
//            WebDriver augmentedDriver = new Augmenter().augment(driver);
//            DevTools devTools = ((HasDevTools) augmentedDriver).getDevTools();
//            devTools.createSession();
            AllureUtils.logActionF("Driver initialized for " + browser + " at " + width + "x" + height);
            String sessionId = driver.getSessionId().toString();
            String videoUrl = "http://localhost:4444/video/" + sessionId + ".mp4";

            System.out.println("Session ID: " + sessionId);
            System.out.println("Expected video URL: " + videoUrl);
            System.out.println("Video recording enabled: " + capabilities.getCapability("selenoid:options"));

            System.out.printf("[SELENOID VNC SESSION]: %s/#/sessions/%s%n",
                    selenoidRemoteUrl, driver.getSessionId());
            AllureUtils.logActionF("Driver initialized for browser: " + browser);
            return driver;

        } catch (MalformedURLException e) {
            throw new RuntimeException("Incorrect Selenoid hub URL", e);
        }
    }

    private static FirefoxOptions getFirefoxOptions() {

        FirefoxOptions firefoxOptions = new FirefoxOptions();
        firefoxOptions.addPreference("browser.download.folderList", 2);
        firefoxOptions.addPreference("browser.download.dir", "/opt/selenium");
        firefoxOptions.addPreference("browser.helperApps.neverAsk.saveToDisk", "application/pdf");
        firefoxOptions.addPreference("browser.download.useDownloadDir", true);
        firefoxOptions.addPreference("browser.privatebrowsing.autostart", true);
        firefoxOptions.addPreference("pdfjs.disabled", true); // disable built-in PDF viewer
        firefoxOptions.addArguments("--disable-popup-blocking");
        firefoxOptions.setPageLoadStrategy(PageLoadStrategy.NORMAL);
        return firefoxOptions;
    }


    private static Map<String, Object> convertJsonNodeToMap(JsonNode jsonNode) {
        Map<String, Object> resultMap = new HashMap<>();

        Iterator<Map.Entry<String, JsonNode>> fields = jsonNode.fields();
        while (fields.hasNext()) {
            Map.Entry<String, JsonNode> entry = fields.next();
            String key = entry.getKey();
            JsonNode valueNode = entry.getValue();

            // Convert JSON values to Java types
            Object value = convertJsonNodeToObject(valueNode);

            // Put the key-value pair in the result map
            resultMap.put(key, value);
        }

        return resultMap;
    }

    private static Object convertJsonNodeToObject(JsonNode jsonNode) {
        if (jsonNode.isObject()) {
            return convertJsonNodeToMap(jsonNode);
        } else if (jsonNode.isArray()) {
            return convertJsonNodeToList(jsonNode);
        } else if (jsonNode.isBoolean()) {
            return jsonNode.asBoolean();
        } else if (jsonNode.isDouble()) {
            return jsonNode.asDouble();
        } else if (jsonNode.isLong()) {
            return jsonNode.asLong();
        } else if (jsonNode.isTextual()) {
            return jsonNode.asText();
        }

        // Return null if the node is of unsupported type
        return null;
    }

    private static Object convertJsonNodeToList(JsonNode jsonNode) {
        if (jsonNode.isArray()) {
            Iterator<JsonNode> elements = jsonNode.elements();
            while (elements.hasNext()) {
                JsonNode element = elements.next();
                return convertJsonNodeToObject(element);
            }
        }
        // Return null if the node is not an array
        return null;
    }
}
