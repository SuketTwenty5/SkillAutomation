package t5.ipe.cucumber.core.web.steps;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import com.epam.reportportal.service.ReportPortal;
import com.influxdb.client.InfluxDBClient;
import com.influxdb.client.InfluxDBClientFactory;
import com.influxdb.client.WriteApi;
import com.influxdb.client.WriteOptions;
import com.influxdb.client.write.Point;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import org.junit.Assert;
import t5.ipe.cucumber.core.web.AzureDeploymentChecker;
import t5.ipe.cucumber.core.web.CucumberRuntime;
import t5.ipe.cucumber.core.web.ScenarioMetrics;
import t5.ipe.cucumber.core.web.StepCounter;
import t5.ipe.cucumber.core.web.util.AllureUtils;
import t5.ipe.cucumber.core.web.util.web.SearchUtils;
import t5.ipe.cucumber.core.web.util.web.WebUtil;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static t5.ipe.cucumber.core.web.util.web.WebUtil.setCustomTimeoutSec;
import static t5.ipe.cucumber.core.web.util.web.WebUtil.setDefaultTimeout;

/**
 * Contains universal Cucumber-steps for interacting with web application
 * Created by: EKruze
 * Date: 20/10/2023
 */
public class UtilSteps {
    private static final String BUCKET = "Regression Performance";
    private static final String ORG = "Twenty5";
    private static final String URL = "http://20.127.232.15:8086"; // InfluxDB endpoint
    private static final String TOKEN = System.getProperty("integration.token", "");


    public static String baseDomainExtractor(String fullUrl) {
        try {
            URL url = new URL(fullUrl);
            return url.getProtocol() + "://" + url.getHost();
        } catch (Exception e) {
            return WebUtil.getSiteUrl();
        }
    }


    @And("^(?:I |)open site$")
    public void openUrl() {
        open(WebUtil.getSiteUrl());
        AllureUtils.saveScreenshot();
    }
    
    @Given("^(?:I |)open (.+) (.+)$")
    public void openQuote(String scenarioId, String type) {
        String url = WebUtil.getSiteUrl();
        String baseUrl = baseDomainExtractor(url);
        String line = "";
        String filePath = Paths.get(System.getProperty("user.dir"), scenarioId+"-"+type+".txt").toString();
        AllureUtils.logActionF("File Path for picking quote name: "+filePath);
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            while ((line = reader.readLine()) != null) {
                System.out.println("Read from file: " + line);
                open(baseUrl+"/#"+line);
                AllureUtils.saveScreenshot();
            }
        } catch (IOException e) {
            AllureUtils.logActionF("Error in opening quote :- '%s'",e.toString());
            Assert.fail("Not Able to open quote page");
        }

    }

    @And("^page with name '(.+)' is opened")
    public void checkIfPageOpened(String pageName) {
        AllureUtils.saveScreenshot();
        SearchUtils.getPageByName(pageName)
                .checkIfPageLoaded();
        AllureUtils.logActionF("page with name '%s' is opened", pageName);
    }

    @And("^URL of current page is equals '(.+)'$")
    public void checkIfCurrentPageUrlEquals(String expectedUrl) {
        String actualURL = getWebDriver().getCurrentUrl();
        String errorText = String.format("Actual URL [%s] does not equals to expected one [%s].", actualURL, expectedUrl);
        Assert.assertEquals(errorText, expectedUrl, actualURL);
        AllureUtils.logResultF("URL of current page is equals %s", expectedUrl);
    }

    // debug step, do not use in tests!
    @And("^(?:I |)wait for (\\d+) second[s]?$")
    public void waitFor(int seconds) {
        try {
            Thread.sleep(seconds * 1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        AllureUtils.saveScreenshot();
    }

    @And("^(?:I |)switch to the other browser tab$")
    public void switchToOtherBrowserTab() {
        sleep(2000);
        setCustomTimeoutSec(120);

        try {
            List<String> tabs = new ArrayList<>(getWebDriver().getWindowHandles());
            String currentTab = getWebDriver().getWindowHandle();
            String otherTab = tabs.stream().filter(tab -> !tab.equals(currentTab)).findFirst().orElseThrow(() -> new RuntimeException("No other tab found"));

            switchTo().window(otherTab);
            AllureUtils.logAction("Switched to the other tab");

        } catch (Exception e) {
            refresh();
            AllureUtils.logAction("Refreshing has been completed");

            try {
                List<String> tabs = new ArrayList<>(getWebDriver().getWindowHandles());
                String currentTab = getWebDriver().getWindowHandle();
                String otherTab = tabs.stream().filter(tab -> !tab.equals(currentTab)).findFirst().orElseThrow(() -> new RuntimeException("No other tab found after refresh"));

                switchTo().window(otherTab);
                AllureUtils.logAction("Switched to the other tab after refresh");

            } catch (Exception innerException) {
                AllureUtils.logResult("Failed to switch to the other tab after refresh" + innerException);
                throw innerException;
            }
        }

        setDefaultTimeout();
    }

    @And("^info:(.*)$")
    public void info(String info) {
//        ReportPortal.emitLog(info, "INFO", new Date());
        long endTime = System.currentTimeMillis();
        String status = "PASSED";
        // Build point and write to InfluxDB
        ScenarioMetrics metrics = CucumberRuntime.getInstance().getMetrics();
        InfluxDBClient influxDBClient = CucumberRuntime.getInstance().getInfluxDBClient();
        Point point = metrics.buildPoint(endTime, status);
        // Configure async write options
        WriteOptions writeOptions = WriteOptions.builder()
                .batchSize(500)         // Number of points per batch
                .flushInterval(5000)    // Flush every 5 seconds
                .retryInterval(5000)    // Retry interval for failed writes
                .build();

        // Create the WriteApi (async, non-blocking)
        try (WriteApi writeApi = influxDBClient.getWriteApi(writeOptions)) {
            writeApi.writePoint(BUCKET, ORG, point);
        } catch (Exception e) {
            System.err.println("InfluxDB write failed: " + e.getMessage());
        }
        CucumberRuntime.getInstance().setInfluxDBClient(influxDBClient);
        System.out.println("Previous Step ended at: " + endTime);
        System.out.println("Previous Step duration (ms): " + (endTime - metrics.getStartTime()));
//        InfluxDBClient client = InfluxDBClientFactory.create(URL, TOKEN.toCharArray(), ORG, BUCKET);

        String jobName = System.getenv("JOB_NAME") != null ? System.getenv("JOB_NAME") : "Local_Job";
        String scenarioName = metrics.getScenarioName();;
        String testName = info.replaceAll("^[-\\s]+|[-\\s]+$", "");
        String browser = System.getProperty("browser", "chrome").toLowerCase();
        String os = System.getProperty("os.name");
        ScenarioMetrics newMetrics = new ScenarioMetrics(jobName, scenarioName, testName, browser, os);
        CucumberRuntime.getInstance().setMetrics(newMetrics);
        System.out.println("Step started at: " + newMetrics.getStartTime());
        AllureUtils.logAction(info.trim());
        StepCounter.incrementStep();
        AzureDeploymentChecker.abortIfDeploymentInProgress();
    }

    @And("^(?:I |)refresh page$")
    public void refreshPage() {
        // Clear cache via JavaScript
        executeJavaScript("caches.keys().then(function(names) { for (let name of names) caches.delete(name); });");

        // Optionally, clear localStorage/sessionStorage
        executeJavaScript("localStorage.clear(); sessionStorage.clear();");
        refresh();
        Selenide.sleep(3500);// Wait for the page to reload and cache to clear
    }

}
