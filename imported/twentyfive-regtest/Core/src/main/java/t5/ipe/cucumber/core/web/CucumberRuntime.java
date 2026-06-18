package t5.ipe.cucumber.core.web;

import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.logevents.SelenideLogger;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.influxdb.client.InfluxDBClient;
import com.influxdb.client.InfluxDBClientFactory;
import com.influxdb.client.WriteApi;
import com.influxdb.client.WriteOptions;
import com.influxdb.client.write.Point;
import io.cucumber.java.*;
import io.qameta.allure.Allure;
import io.qameta.allure.selenide.AllureSelenide;
import kong.unirest.json.JSONObject;
import org.assertj.core.api.Assertions;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.SessionId;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import t5.ipe.cucumber.core.web.util.AllureUtils;
import t5.ipe.cucumber.core.web.util.SSLUtils;
import t5.ipe.cucumber.core.web.util.web.WebUtil;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import t5.ipe.cucumber.core.web.TestContext;

import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.*;
import io.qameta.allure.Allure;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.executeJavaScript;
import static t5.ipe.cucumber.core.web.util.AllureUtils.getScreenshotName;
import static t5.ipe.cucumber.core.web.util.web.WebDriverUtils.*;
import static t5.ipe.cucumber.core.web.util.web.WebUtil.*;

import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.assertj.core.api.SoftAssertions;
import static com.codeborne.selenide.Selenide.*;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;


/**
 * Class is responsible for interacting with test environment.
 */
@Configuration
@ComponentScan(
        value = {"t5.ipe.cucumber"}
)
public class CucumberRuntime {


    /**
     * Performs search/creation of Spring component object at Spring context by its name.
     *
     * @param beanName name of component (most often the page name).
     * @return instance of component.
     */

//    protected TestContext context = new TestContext();

//    public CucumberRuntime() {
//        this.context = new TestContext(); // Or some default value
//    }

//    public CucumberRuntime(TestContext context) {
//        this.context = context;
//    }

    public static String currentFeatureFile = "Unknown.feature";
    private static final String SCREENSHOT_FOLDER = System.getenv("JENKINS_HOME") + "/previousScreenshot";

    public static Object getBean(String beanName) {
        return appContext.getBean(beanName);
    }

    public static <T> T getBean(Class<T> aClass) {
        return appContext.getBean(aClass);
    }

    public static <T> T getBean(String beanName, Class<T> aClass) {
        return appContext.getBean(beanName, aClass);
    }

    public static ApplicationContext getAppContext() {
        return appContext;
    }

    private static ApplicationContext appContext;
    private static void setAppContext(ApplicationContext applicationContext) {
        if (appContext == null) {
            appContext = applicationContext;
            Runtime.getRuntime().addShutdownHook(new Thread(
                    () -> ((AnnotationConfigApplicationContext) CucumberRuntime.getAppContext()).close(), "Shutdown-thread"
            ));
        }
    }
    private static CucumberRuntime instance;

    private ScenarioMetrics metrics;
    private InfluxDBClient influxDBClient;

    private static final String BUCKET = "Regression Performance";
    private static final String ORG = "Twenty5";
    private static final String URL = "http://20.127.232.15:8086"; // InfluxDB endpoint
    private static final String TOKEN = System.getProperty("integration.token", "");

//    public CucumberRuntime() {
//        influxDBClient = InfluxDBClientFactory.create(URL, TOKEN.toCharArray(), ORG, BUCKET);
//    }

    public static CucumberRuntime getInstance(){
        if (instance == null) {
            instance = new CucumberRuntime();
        }
        return instance;
    }
    // getters and setters
    public ScenarioMetrics getMetrics() {
        return metrics;
    }

    public void setMetrics(ScenarioMetrics metrics) {
        this.metrics = metrics;
    }

    public InfluxDBClient getInfluxDBClient() {
        return influxDBClient;
    }

    public void setInfluxDBClient(InfluxDBClient influxDBClient) {
        this.influxDBClient = influxDBClient;
    }

    private String getFeatureFileNameFromScenarioId(Scenario scenario) {
        String uri = scenario.getUri().toString();
        String featureFileNameWithExtension = uri.substring(uri.lastIndexOf("/") + 1);
        System.out.println("Feature File Name with Extension: " + featureFileNameWithExtension);

        // To get only the name without the extension:
        String featureFileName = featureFileNameWithExtension.substring(0, featureFileNameWithExtension.lastIndexOf("."));
        System.out.println("Feature File Name: " + featureFileName);

        return featureFileName;
    }
//    private void killAnyOpenBrowserProcesses() {
//        try {
//            Runtime.getRuntime().exec("pkill -f 'chrome'");
//            // Add delays or logs if necessary
//        } catch (IOException e) {
//            System.err.println("Failed to kill Chrome processes: " + e.getMessage());
//        }
//    }

//    @BeforeStep
//    public void beforeStep(Scenario scenario) {
//        Allure.step("Step:- " + (StepCounter.get() + 1) + " Previous Screenshot in Scenario: " + scenario.getName(),() -> {
//            StepCounter.increment();
//            System.out.println("[Hooks] Step " + StepCounter.get() + " starting in scenario: " + scenario.getName());
//            String fileName = AllureUtils.getScreenshotName();
//            File file = new File(SCREENSHOT_FOLDER, fileName + ".png");
//            if (file.exists()) {
//                System.out.println("[ScreenshotUtils] Found existing screenshot: " + file.getAbsolutePath());
//                // 3. Attach to Allure
//                try (FileInputStream fis = new FileInputStream(file)) {
//                    Allure.addAttachment(fileName, "image/png", fis, ".png");
//                } catch (IOException e) {
//                    AllureUtils.logActionF("⚠️ Failed to attach screenshot: " + e.getMessage());
//                }
//            } else {
//                AllureUtils.logActionF("[ScreenshotUtils] Screenshot not found: %s", file.getAbsolutePath());
//            }
//        });
        // Hide this hook from Allure
//        Allure.getLifecycle().updateStep(step -> step.setHidden(true));
//    }

    @Before
    public void setUp(Scenario scenario) throws Exception {
        System.setProperty("webdriver.http.factory", "jdk-http-client");
        AzureDeploymentChecker.abortIfDeploymentInProgress();
        System.out.println("[Hooks] Before scenario: " + scenario.getName());
        StepCounter.reset();
        StepCounter.setScenario(scenario);
        setAppContext(new AnnotationConfigApplicationContext(CucumberRuntime.class));

        boolean hasStartTag = scenario.getSourceTagNames().contains("@START");

        boolean hasRunTag = scenario.getSourceTagNames().contains("@RUN");

        boolean hasEndTag = scenario.getSourceTagNames().contains("@END");

        boolean hasDebugTag = scenario.getSourceTagNames().contains("@DEBUG");

        if (isDriverInitialized() && hasStartTag) {
            AllureUtils.logActionF("Driver is already initialized, quitting it.");
            WebDriverRunner.getWebDriver().quit();
            WebDriverRunner.closeWebDriver(); // Ensures Selenide's thread-local reference is cleared
            AllureUtils.logActionF("Browser and driver closed");

            currentFeatureFile =  getFeatureFileNameFromScenarioId(scenario);

            prepareChromeDriver(scenario, currentFeatureFile);
            prepareEnvPropertiesForReport();
            SelenideLogger.addListener("AllureSelenide", new AllureSelenide()
                    .screenshots(true)
            );

        }else{
            if(hasRunTag || hasEndTag){
                AllureUtils.logActionF("Using Previous test Driver");

            } else if (hasDebugTag) {
                AllureUtils.logActionF("Debug mode - Using Previous test Driver and skipping driver initialization");
                String currentUrl = getLocalDriver().getCurrentUrl();
                System.out.println("Current URL in Debug mode: " + currentUrl);
//                open(currentUrl);
            } else {
                AllureUtils.logActionF("Driver was never initialized, doing now.");

                currentFeatureFile =  getFeatureFileNameFromScenarioId(scenario);

                prepareChromeDriver(scenario, currentFeatureFile);
                prepareEnvPropertiesForReport();
                SelenideLogger.addListener("AllureSelenide", new AllureSelenide()
                        .screenshots(true)
                );

            }
        }

        String jobName = System.getenv("JOB_NAME") != null ? System.getenv("JOB_NAME") : "Local_Job";
        System.out.println("[INFLUX DB] Job Name: " + jobName);
        String scenarioName = scenario.getName();
        String testName = "Test Initialization";
        String browser = System.getProperty("browser", "chrome").toLowerCase();
        String os = System.getProperty("os.name");
        InfluxDBClient client = InfluxDBClientFactory.create(URL, TOKEN.toCharArray(), ORG, BUCKET);
        CucumberRuntime.getInstance().setInfluxDBClient(client);
        ScenarioMetrics metrics = new ScenarioMetrics(jobName, scenarioName, testName, browser, os);
        CucumberRuntime.getInstance().setMetrics(metrics);
        System.out.println("Test Initialization started at: " + metrics.getStartTime());
    }

    public static void saveUrlInfoIfMatched(String url, String scenarioId) {
        String quoteRegex = ".*quote:([0-9A-Fa-f]{32}|[0-9A-Fa-f]{8}(?:-[0-9A-Fa-f]{4}){3}-[0-9A-Fa-f]{12})$";
        String boeRegex = ".*boe:([0-9A-Fa-f]{32}|[0-9A-Fa-f]{8}(?:-[0-9A-Fa-f]{4}){3}-[0-9A-Fa-f]{12})$";

        Pattern quotePattern = Pattern.compile(quoteRegex);
        Pattern boePattern = Pattern.compile(boeRegex);

        Matcher quoteMatcher = quotePattern.matcher(url);
        Matcher boeMatcher = boePattern.matcher(url);

        String type = null;
        String id = null;

        if (quoteMatcher.find()) {
            type = "quote";
            id = quoteMatcher.group(1);
        } else if (boeMatcher.find()) {
            type = "boe";
            id = boeMatcher.group(1);
        }

        if (type != null && id != null) {
            String content = type + ":" + id;

            try {
                String filePath = Paths.get(System.getProperty("user.dir"), scenarioId+"-"+type+".txt").toString();
                try (FileWriter writer = new FileWriter(filePath, false)) {  // false = overwrite
                    writer.write(content);
                }
                System.out.println("Saved "+content+" to workspace: " + scenarioId+"-"+type+".txt");
            } catch (IOException e) {
                System.err.println("Error writing URL to file: " + e.getMessage());
            }
        }
    }

    public static List<String> getAllOpenUrls() {
        WebDriver driver = WebDriverRunner.getWebDriver();
        String originalWindow = driver.getWindowHandle();

        Set<String> allWindows = driver.getWindowHandles();
        List<String> urls = new ArrayList<>();

        for (String windowHandle : allWindows) {
            driver.switchTo().window(windowHandle);
            urls.add(driver.getCurrentUrl());
        }

        // Optional: switch back to original window
        driver.switchTo().window(originalWindow);

        return urls;
    }
    public static String getTimestamp() {
        SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyy_HHmmss");
        return sdf.format(new Date());
    }

    @After
    public void shutDown(Scenario scenario) throws Exception {
        AzureDeploymentChecker.abortIfDeploymentInProgress();
        ScenarioMetrics metrics = CucumberRuntime.getInstance().getMetrics();
        InfluxDBClient client = CucumberRuntime.getInstance().getInfluxDBClient();

        long endTime = System.currentTimeMillis();
        String status = scenario.getStatus().name();

        // Build point
        Point point = metrics.buildPoint(endTime, status);

        // Configure async write options
        WriteOptions writeOptions = WriteOptions.builder()
                .batchSize(500)         // Number of points per batch
                .flushInterval(5000)    // Flush every 5 seconds
                .retryInterval(5000)    // Retry interval for failed writes
                .build();

        // Create the WriteApi (async, non-blocking)
        try (WriteApi writeApi = client.getWriteApi(writeOptions)) {
            writeApi.writePoint(BUCKET, ORG, point);
            // Optional: flush immediately if needed
            writeApi.flush();
        } catch (Exception e) {
            System.err.println("InfluxDB write failed: " + e.getMessage());
        }
        if (client != null) {
            client.close();
        }
        System.out.println("Scenario ended at: " + endTime);
        System.out.println("Previous test duration (ms): " + (endTime - metrics.getStartTime()));
        try {
            AllureUtils.saveScreenshot();
        }
        catch (Exception e) {
            System.out.println("⚠️ Failed to capture screenshot: " + e.getMessage());
        }
        System.out.println("[Hooks] After scenario, clearing context");
        attachConsoleLogs();
        attachPerformanceLogs();
        String scenarioID = scenario.getName().split(":")[0];
        List<String> openUrls = CucumberRuntime.getAllOpenUrls();
        int i =1;
        for (String url : openUrls) {
            System.out.println("Found URL "+i+" : " + url);
            i=i+1;
            CucumberRuntime.saveUrlInfoIfMatched(url, scenarioID);
        }
        boolean hasRunTag = scenario.getSourceTagNames().contains("@RUN");
        boolean hasEndTag = scenario.getSourceTagNames().contains("@END");
        boolean hasStartTag = scenario.getSourceTagNames().contains("@START");
        boolean scenarioPassed = !scenario.isFailed();
        String scenarioName = scenario.getName();
        SessionId sessionId = ((RemoteWebDriver) WebDriverRunner.getWebDriver()).getSessionId();
        if ((hasStartTag || hasRunTag) && scenarioPassed && !hasEndTag) {
//            saveScreenIfNeeded(scenario);
//            try {
//                TestContext.getInstance().assertAll();
//            } finally {
                AllureUtils.logActionF("Continuing.....Scenario passed | Scenario Name: " + scenarioName);

//            }
        } else {
            String currentUrl = WebDriverRunner.url();
            AllureUtils.logActionF("%s Url is '%s'", scenario.getName(), currentUrl);
            AllureUtils.logActionF("%s Soft Assertion Start", scenario.getName());
            try {
                TestContext.getInstance().assertAll();
            }
            finally {
                AllureUtils.logActionF("%s Soft Assertion End", scenario.getName());
                boolean windowsRecord = Boolean.parseBoolean(System.getProperty("windowsRecord", "false"));

                // options.setBrowserVersion("120"); --select your local version
                if (windowsRecord && OS.contains("win")) {
                     handleWindowsVideoRecording(scenario, sessionId.toString(), WINDOWS_TEST_NAME);
                }
                TicketHandler.handleTicketCreation(scenarioPassed, scenario);
                quitDriver();
                AllureUtils.logActionF("%s Driver Qit", scenario.getName());
                String localRun = System.getProperty("local.run");
                if ("true".equals(localRun)) {
                        AllureUtils.logActionF("Local Run Complete. Video Generated in Windows Run Only");
                }else {
                    AllureUtils.logActionF("%s SAVING SELENOID VIDEO", scenario.getName());
                    saveSelenoidVideo(sessionId);
                }
            }
        }
    }

    private static String readableFileSize(long size) {
        if(size <= 0) return "0 B";
        final String[] units = new String[]{"B","KB","MB","GB","TB"};
        int digitGroups = (int) (Math.log10(size)/Math.log10(1024));
        return String.format("%.1f %s", size/Math.pow(1024, digitGroups), units[digitGroups]);
    }

    public static void getConsoleLogsAsStringAndStopOnError() {
        LogEntries logs = WebDriverRunner.getWebDriver().manage().logs().get(LogType.BROWSER);
        StringBuilder sb = new StringBuilder();

        for (LogEntry entry : logs) {

            String message = entry.getMessage();

            AllureUtils.logActionF("Console Log: [%s] [%s] %s",
                    entry.getLevel(),
                    entry.getTimestamp(),
                    message
            );

            // ❌ Ignore TinyMCE plugin load noise (even if SEVERE)
            if (isIgnorableTinyMcePluginError(message)) {
                AllureUtils.logActionF(
                        "ℹ️ Ignored TinyMCE plugin error: %s",
                        message
                );
                continue;
            }

            // ❌ Skip TrackJS CDN errors
            if (message != null &&
                    (message.contains("cdn.trackjs.com") ||
                            message.contains("tracker.js"))) {
                continue;
            }

            if (entry.getLevel().equals(Level.SEVERE)) {
                sb.append(String.format("[%s] [%s] %s%n",
                        entry.getLevel(),
                        entry.getTimestamp(),
                        message
                ));
            }
        }

        // ❌ Fail test if any SEVERE error exists
        if (sb.length() > 0) {
            Assertions.fail("❌ Browser console errors detected:\n\n" + sb);
        }
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    public static void getConsoleLogsAsStringAndStopOnErrorWarning() {
        LogEntries logs = WebDriverRunner.getWebDriver().manage().logs().get(LogType.BROWSER);
        StringBuilder sb = new StringBuilder();

        for (LogEntry entry : logs) {

            String message = entry.getMessage();

            AllureUtils.logActionF("Console Log: [%s] [%s] %s",
                    entry.getLevel(),
                    entry.getTimestamp(),
                    message
            );

            // ❌ Ignore TinyMCE plugin load noise (even if SEVERE)
            if (isIgnorableTinyMcePluginError(message)) {
                AllureUtils.logActionF(
                        "ℹ️ Ignored TinyMCE plugin error: %s",
                        message
                );
                continue;
            }

            // ❌ Skip TrackJS CDN errors
            if (message != null &&
                    (message.contains("cdn.trackjs.com") ||
                            message.contains("tracker.js"))) {
                continue;
            }

            if (entry.getLevel().equals(Level.SEVERE)) {
                sb.append(String.format("[%s] [%s] %s%n",
                        entry.getLevel(),
                        entry.getTimestamp(),
                        message
                ));
            }
        }

        // ❌ Fail test if any SEVERE error exists
        if (sb.length() > 0) {
            TestContext.getInstance().softly.fail("❌ Browser console errors detected:\n\n" + sb);
        }
    }

    private static boolean isIgnorableTinyMcePluginError(String message) {
        return message != null
                && message.matches(".*tinymce/plugins/(checklist|spellchecker)/.*\\.js.*");
    }



    public static void attachConsoleLogs() {
        try {
            LogEntries logs = WebDriverRunner.getWebDriver().manage().logs().get(LogType.BROWSER);
            StringBuilder sb = new StringBuilder();
            for (LogEntry entry : logs) {
                sb.append(String.format("[%s] [%s] %s%n", entry.getLevel(), entry.getTimestamp(), entry.getMessage()));
            }
            System.out.println("Attaching Console Logs to Allure");
            System.out.println(sb);
            Allure.addAttachment("Console Logs", sb.toString());
        } catch (Exception e) {
            Allure.addAttachment("Console Logs Error", e.getMessage());
        }
    }

    public static void handleWindowsVideoRecording(Scenario scenario, String sessionId, String testName) throws Exception {
        String OS = System.getProperty("os.name").toLowerCase();

        if (!OS.contains("win")) {
            AllureUtils.logActionF("Non-Windows OS detected (%s).", OS);
            return;
        }

        // 1️⃣ Stop Recording
        VideoRecorderUtil.stopRecording();
        AllureUtils.logActionF("Windows Run Complete. Video Recording stopped");

        // 2️⃣ Generate video file names
        String aviPath = "videos/" + testName + ".avi";
        String mp4Path = "videos/" + testName + ".mp4";

        // 3️⃣ Convert AVI → MP4
        System.out.println("Converting video to MP4 format: " + aviPath + " → " + mp4Path);
        VideoRecorderUtil.convertAviToMp4(aviPath, mp4Path);
        System.out.println("Video conversion complete.");

        File videoFile = new File(mp4Path);
        File aviVideoFile = new File(aviPath);

        // 4️⃣ Attach video to Allure if exists
        if (videoFile.exists() && videoFile.length() > 0) {
            try (InputStream is = Files.newInputStream(videoFile.toPath())) {
                Allure.addAttachment(
                        "Execution video - " + scenario.getName().replaceAll(" ", "_"),
                        "video/mp4",
                        is,
                        ".mp4"
                );
                System.out.println("✅ Video attached to Allure: " + mp4Path);
            } catch (Exception e) {
                e.printStackTrace();
            }

            AllureUtils.logActionF("Test Session ID: %s", sessionId);

            // 5️⃣ Create clickable HTML link
            String attachmentHtml = String.format(
                    "<a class=\"link\" href=\"%s\" target=\"_blank\" type=\"video/mp4\" title=\"Open video in new tab\">" +
                            "<span class=\"fa fa-save\"></span> %s</a>",
                    videoFile.getName(),
                    readableFileSize(videoFile.length())
            );

            Allure.step("Open execution video", () ->
                    Allure.addAttachment("Video Link", "text/html", attachmentHtml, ".html")
            );

            System.out.println("✅ Video attached and link added to Allure report: " + videoFile.getAbsolutePath());
        } else {
            System.out.println("⚠️ Video not found or empty: " + mp4Path);
        }

        // 6️⃣ Cleanup AVI file
        if (aviVideoFile.exists()) {
            if (aviVideoFile.delete()) {
                System.out.println("AVI duplicate Video deleted successfully: " + aviPath);
            } else {
                System.out.println("Failed to delete AVI duplicate Video: " + aviPath);
            }
        }

        // 7️⃣ Cleanup MP4 file
        if (videoFile.exists()) {
            if (videoFile.delete()) {
                System.out.println("MP4 Video deleted successfully: " + mp4Path);
            } else {
                System.out.println("Failed to delete MP4 Video: " + mp4Path);
            }
        }
    }

    public static void attachPerformanceLogs() {
        try {
            LogEntries logs = WebDriverRunner.getWebDriver().manage().logs().get(LogType.PERFORMANCE);
            StringBuilder summaryLog = new StringBuilder();
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            Map<String, String> requestUrlMap = new HashMap<>();

            summaryLog.append("====== 🌐 API Request/Response Summary ======\n\n");

            for (LogEntry entry : logs) {
                String message = entry.getMessage();

                JsonObject json;
                try {
                    json = JsonParser.parseString(message).getAsJsonObject();
                } catch (Exception e) {
                    continue; // skip invalid JSON lines
                }

                JsonObject messageObj = json.getAsJsonObject("message");
                if (!messageObj.has("method")) continue;

                String method = messageObj.get("method").getAsString();
                JsonObject params = messageObj.getAsJsonObject("params");

                // ➡️ Request
                if ("Network.requestWillBeSent".equals(method)) {
                    JsonObject request = params.getAsJsonObject("request");
                    String url = request.get("url").getAsString();
                    String requestMethod = request.get("method").getAsString();
                    String requestId = params.get("requestId").getAsString();

                    requestUrlMap.put(requestId, url); // save for pairing

                    summaryLog.append("➡️ **Request**\n");
                    summaryLog.append("🔗 URL: ").append(url).append("\n");
                    summaryLog.append("📬 Method: ").append(requestMethod).append("\n");

                    if (request.has("headers")) {
                        JsonObject headers = request.getAsJsonObject("headers");
                        summaryLog.append("📦 Headers:\n")
                                .append(gson.toJson(headers)).append("\n");
                    }

                    if (request.has("postData")) {
                        summaryLog.append("📝 Body:\n")
                                .append(request.get("postData").getAsString()).append("\n");
                    }

                    summaryLog.append("\n");
                }

                // ⬅️ Response
                if ("Network.responseReceived".equals(method)) {
                    JsonObject response = params.getAsJsonObject("response");
                    String url = response.get("url").getAsString();
                    int status = response.get("status").getAsInt();
                    String mimeType = response.has("mimeType") ? response.get("mimeType").getAsString() : "unknown";
                    String requestId = params.get("requestId").getAsString();

                    summaryLog.append("⬅️ **Response**\n");
                    summaryLog.append("🔗 URL: ").append(url).append("\n");
                    summaryLog.append("📊 Status: ").append(status).append("\n");
                    summaryLog.append("🧾 MIME: ").append(mimeType).append("\n");

                    // Body is not included in performance logs — mention that
                    summaryLog.append("📄 Body: (not available via performance log)\n");

                    summaryLog.append("\n");
                }
            }

            Allure.addAttachment("📊 API Logs (Formatted)", "text/plain", summaryLog.toString());

        } catch (Exception e) {
            Allure.addAttachment("❌ Performance Logs Error", e.getMessage());
        }
    }

//    @AfterAll
//    public void shutDownIfNot(Scenario scenario){
//        if(isDriverQuit()){
//            AllureUtils.logActionF("Driver Already Quit");
//        } else{
//            saveScreenIfNeeded(scenario);
//            SessionId sessionId = ((RemoteWebDriver) WebDriverRunner.getWebDriver()).getSessionId();
//            String currentUrl = WebDriverRunner.url();
//            AllureUtils.logActionF("%s Url is '%s'", scenario, currentUrl);
//            quitDriver();
//            String localRun = System.getProperty("local.run");
//            if ("true".equals(localRun)) {
//                AllureUtils.logActionF("Local Run Complete. Video Not Generated in Local Run");
//            }else {
//                saveSelenoidVideo(sessionId);
//            }
//        }
//    }


    private void prepareEnvPropertiesForReport() {
        String userDirPath = System.getProperty("user.dir");
        File file = new File(userDirPath + "/target/allure-results/environment.properties");
        File parent = file.getParentFile();
        if (parent != null && !parent.exists() && !parent.mkdirs()) {
            throw new IllegalStateException("Couldn't create dir: " + parent);
        }
        Capabilities capabilities = ((RemoteWebDriver) WebDriverRunner.getWebDriver()).getCapabilities();
        try (FileOutputStream fos = new FileOutputStream(file)) {
            Properties props = new Properties();
            props.setProperty("Environment: ", WebUtil.getCurrentEnv());
            props.setProperty("Browser and version:", capabilities.getBrowserName() + " " + capabilities.getBrowserVersion());
            props.store(fos, "comments");
        } catch (Exception e) {
            System.out.println("IO problem when writing allure properties file");
            e.printStackTrace();
        }
    }


    private void saveScreenIfNeeded(Scenario scenario) {
        Status scenarioStatus = scenario.getStatus();
        AllureUtils.logActionF("Scenario Status is %s", scenarioStatus);
        if (scenarioStatus == Status.FAILED
                || scenarioStatus == Status.UNDEFINED
                || scenarioStatus == Status.SKIPPED
                || scenarioStatus == Status.PENDING
                || scenarioStatus == Status.UNUSED
                || scenarioStatus == Status.AMBIGUOUS) {
            AllureUtils.saveScreenshot();
        }
    }

    private void saveSelenoidVideo(SessionId sessionId) {
        // Retrieve SELENOID_UI_URL from environment variables
        String selenoidRemoteUrl = "https://autoregression.twenty5.com/selenoid";
        AllureUtils.attachAllureVideo(sessionId.toString());
        // Log the video link
        System.out.println(String.format("[SELENOID_VIDEO]: %s/video/%s.mp4%n", selenoidRemoteUrl, sessionId));
    }

}
//if (OS.contains("win")) {
//                VideoRecorderUtil.stopRecording();
//                AllureUtils.logActionF("Windows Run Complete. Video Recording stopped");
//                // Generate video file names
//
//                String aviPath = "videos/" + WINDOWS_TEST_NAME + ".avi";
//                String mp4Path = "videos/" + WINDOWS_TEST_NAME + ".mp4";
//
//                // Convert AVI → MP4
//                System.out.println("Converting video to MP4 format: " + aviPath + " → " + mp4Path);
//                VideoRecorderUtil.convertAviToMp4(aviPath, mp4Path);
//                System.out.println("Video conversion complete.");
//                System.out.println("Attaching video to Allure report: " + mp4Path);
////                AllureUtils.attachVideo(WINDOWS_TEST_NAME, mp4Path);
//                File videoFile = new File(mp4Path);
//                File aviVideoFile = new File(aviPath);
//                if (videoFile.exists() && videoFile.length() > 0) {
//                    // 1️⃣ Attach video file to Allure
//                    try (InputStream is = Files.newInputStream(videoFile.toPath())) {
//                        Allure.addAttachment("Execution video - " + scenario.getName().replaceAll(" ", "_"), "video/mp4", is, ".mp4");
//                        System.out.println("✅ Video attached to Allure: " + mp4Path);
//                    }
//                    AllureUtils.logActionF("Test Session ID: %s",sessionId);
//                    // 2️⃣ Create a clickable HTML link that opens in a new tab
//                    String attachmentHtml = String.format(
//                            "<a class=\"link\" href=\"%s\" target=\"_blank\" type=\"video/mp4\" title=\"Open video in new tab\">" +
//                                    "<span class=\"fa fa-save\"></span> %s</a>",
//                            videoFile.getName(),
//                            readableFileSize(videoFile.length())
//                    );
//
//                    // 3️⃣ Inject HTML into Allure report
//                    Allure.step("Open execution video", () -> {
//                        Allure.addAttachment("Video Link", "text/html", attachmentHtml, ".html");
//                    });
//
//                    System.out.println("✅ Video attached and link added to Allure report: " + videoFile.getAbsolutePath());
//                } else {
//                    System.out.println("⚠️ Video not found or empty: " + mp4Path);
//                }
//                if (aviVideoFile.exists()) {
//                    if (aviVideoFile.delete()) {
//                        System.out.println("AVI duplicate Video deleted successfully: " + aviPath);
//                    } else {
//                        System.out.println("Failed to delete AVI duplicate Video: " + aviPath);
//                    }
//                } else {
//                    System.out.println("AVI duplicate Video file does not exist: " + aviPath);
//                }
//                if(videoFile.exists()) {
//                    if (videoFile.delete()) {
//                        System.out.println("MP4 Video deleted successfully: " + mp4Path);
//                    } else {
//                        System.out.println("Failed to delete MP4 Video: " + mp4Path);
//                    }
//                } else {
//                    System.out.println("MP4 Video file does not exist: " + mp4Path);
//                }
//            }