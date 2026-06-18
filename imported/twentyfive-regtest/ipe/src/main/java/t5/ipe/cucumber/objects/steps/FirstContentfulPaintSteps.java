package t5.ipe.cucumber.objects.steps;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.qameta.allure.Allure;
import org.junit.Assert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v148.network.Network;
import org.openqa.selenium.devtools.v148.performance.Performance;
import org.openqa.selenium.devtools.v148.performance.model.Metric;

import java.time.Duration;
import java.util.*;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.Wait;
import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.executeJavaScript;
import static com.codeborne.selenide.Selenide.open;
import static t5.ipe.cucumber.core.web.util.AllureUtils.logActionF;

public class FirstContentfulPaintSteps {
    private String targetUrl;

    private static final Map<String, Map<String, String>> PAGE_URLS = new HashMap<>();

    static {
        Map<String, String> quoteUrls = new HashMap<>();
        quoteUrls.put("qa",  "https://approuter-twenty5ipe-qa.cfapps.us20.hana.ondemand.com/#quotes:32dd18ea-3fb7-3853-81fb-f3db34e02736");
        quoteUrls.put("dev", "https://approuter-twenty5ipe.cfapps.us20.hana.ondemand.com/#quotes:0a5b16ea-a18b-4721-a186-3f3d8d75b4ac");
        PAGE_URLS.put("quote", quoteUrls);

        Map<String, String> estimateUrls = new HashMap<>();
        estimateUrls.put("qa",  "https://approuter-twenty5ipe-qa.cfapps.us20.hana.ondemand.com/#boe:8c3af1fc-5d90-11f1-aab7-69d6a5ead624");
        estimateUrls.put("dev", "https://approuter-twenty5ipe.cfapps.us20.hana.ondemand.com/#boe:ba32f947-5ace-11f1-b66f-a3a7627e2719");
        PAGE_URLS.put("estimate", estimateUrls);

        Map<String, String> ldcUrls = new HashMap<>();
        ldcUrls.put("qa",  "https://approuter-twenty5ipe-qa.cfapps.us20.hana.ondemand.com/#loadedDirectCosts:some-qa-uuid");
        ldcUrls.put("dev", "https://approuter-twenty5ipe.cfapps.us20.hana.ondemand.com/#loadedDirectCosts:some-dev-uuid");
        PAGE_URLS.put("loaded direct cost", ldcUrls);
    }

    private String appReadySelector;
    private static final Map<String, String> PAGE_READY_SELECTORS = new HashMap<>();

    static {
        PAGE_READY_SELECTORS.put("quote",
                "//*[@placeholder='Enter a brief description of the bid (something you can remember to find it later by)' and @aria-hidden='false']" +
                        "[not(//*[@role='alertdialog' and @aria-hidden='false'])]");

        PAGE_READY_SELECTORS.put("estimate",
                "//*[@role='tabpanel' and @aria-hidden='false']//*[@role='treegrid' and @aria-hidden='false']" +
                        "[not(//*[@role='alertdialog' and @aria-hidden='false'])]");

        PAGE_READY_SELECTORS.put("loaded direct cost",
                "//*[@placeholder='your ldc placeholder here' and @aria-hidden='false']" +
                        "[not(//*[@role='alertdialog' and @aria-hidden='false'])]");
    }
    private final List<Double> fcpResultsMs = new ArrayList<>();

    @When("I measure first contentful paint for {string} details page over {int} runs")
    public void measureFirstContentfulPaintForQuoteDetailsPage(String page, int runs) {
        String currentUrl = WebDriverRunner.url();

        String env;
        if (currentUrl.contains("twenty5ipe-qa.cfapps")) {
            env = "qa";
        } else if (currentUrl.contains("twenty5ipe.cfapps")) {
            env = "dev";
        } else {
            throw new RuntimeException("Unknown environment URL: " + currentUrl);
        }

        Map<String, String> envUrls = PAGE_URLS.get(page.toLowerCase());
        if (envUrls == null) {
            throw new IllegalArgumentException(
                    "Unknown page: '" + page + "'. Valid options: " + PAGE_URLS.keySet()
            );
        }

        targetUrl = envUrls.get(env);
        if (targetUrl == null) {
            throw new IllegalArgumentException(
                    "No URL configured for page='" + page + "' env='" + env + "'"
            );
        }
        appReadySelector = PAGE_READY_SELECTORS.get(page.toLowerCase());
        if (appReadySelector == null) {
            throw new IllegalArgumentException(
                    "No ready selector configured for page: '" + page + "'"
            );
        }
        Assert.assertTrue("Use at least 5 runs for stable FCP data", runs >= 5);

        // NO DevTools session needed
        for (int run = 1; run <= runs; run++) {
            if (run > 1) {
                blankScreenAndConfirmWhite();
                executeJavaScript("location.reload()");
            } else {
                open(targetUrl);
            }
            long startedAt = System.currentTimeMillis();

            waitForTargetPageOnly();

            double fcpMs = measureFCPWithJavaScript(); // JS-based FCP

            Assert.assertTrue(
                    "FirstContentfulPaint was not reported for run " + run,
                    fcpMs > 0
            );

            fcpResultsMs.add(fcpMs);

            logActionF("FCP run %d/%d: %.2f ms (wall clock: %d ms) [%s]",
                    run,
                    runs,
                    fcpMs,
                    System.currentTimeMillis() - startedAt,
                    run == 1 ? "COLD" : "CACHED run " + (run - 1));

            Allure.addAttachment("FCP run " + run,
                    String.format("url=%s%ntype=%s%nfcpMs=%.2f",
                            targetUrl,
                            run == 1 ? "COLD" : "CACHED",
                            fcpMs));
            Selenide.sleep(10000);
        }

        logSummary();
    }

    private double measureFCPWithJavaScript() {
        WebDriver driver = WebDriverRunner.getWebDriver();
        JavascriptExecutor js = (JavascriptExecutor) driver;

        long startTime = System.currentTimeMillis();
        while (System.currentTimeMillis() - startTime < 10000) {
            Double fcp = (Double) js.executeScript(
                    "var entries = performance.getEntriesByName('first-contentful-paint');" +
                            "return entries.length > 0 ? entries[0].startTime : null;"
            );
            if (fcp != null && fcp > 0) {
                return fcp;
            }
            try { Thread.sleep(500); } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        return 0.0;
    }
    private void blankScreenAndConfirmWhite() {
        // Overlay a white cover on top — don't touch existing DOM/HTML
        executeJavaScript(
                "var overlay = document.createElement('div');" +
                        "overlay.id = '__white_overlay__';" +
                        "overlay.style.cssText = 'position:fixed;top:0;left:0;width:100vw;height:100vh;" +
                        "background:#ffffff;z-index:2147483647;margin:0;padding:0;';" +
                        "document.documentElement.appendChild(overlay);"
        );

        // Confirm overlay is present and visible
        Wait()
                .withTimeout(Duration.ofSeconds(5))
                .until(driver -> {
                    Object exists = executeJavaScript(
                            "var el = document.getElementById('__white_overlay__');" +
                                    "return el !== null && el.offsetWidth > 0;"
                    );
                    return Boolean.TRUE.equals(exists);
                });

        logActionF("Screen confirmed white (overlay applied) before next navigation");
    }

    // Remove readMetricMs() entirely, add this instead:
//    private double readFcpFromPaintTimingApi() {
//        // Give the browser up to 3 s to record the paint entry
//        long deadline = System.currentTimeMillis() + 3_000;
//        while (System.currentTimeMillis() < deadline) {
//            Object result = executeJavaScript(
//                    "var e = performance.getEntriesByName('first-contentful-paint');" +
//                            "return e.length > 0 ? e[0].startTime : -1;"
//            );
//            if (result != null) {
//                double ms = ((Number) result).doubleValue();
//                if (ms > 0) return ms;
//            }
//            try { Thread.sleep(200); } catch (InterruptedException ignored) {}
//        }
//        return -1.0;
//    }

    @Then("median first contentful paint should be less than {int} ms")
    public void medianFirstContentfulPaintShouldBeLessThanMs(int thresholdMs) {
        Assert.assertFalse("No FCP measurements were captured", fcpResultsMs.isEmpty());

        double median = percentile(fcpResultsMs, 50);
        double p75 = percentile(fcpResultsMs, 75);
        double p95 = percentile(fcpResultsMs, 95);

        String summary = String.format(
                "FCP summary for measured page only:%n" +
                        "runs=%d%nmedian=%.2f ms%np75=%.2f ms%np95=%.2f ms%nvalues=%s",
                fcpResultsMs.size(),
                median,
                p75,
                p95,
                fcpResultsMs
        );

        Allure.addAttachment("FCP summary", summary);
        logActionF(summary);

        Assert.assertTrue(
                String.format("Median FCP too slow. Expected < %d ms, actual %.2f ms", thresholdMs, median),
                median < thresholdMs
        );
    }

    private void resetMeasurementState(DevTools devTools) {
        devTools.send(Network.clearBrowserCache());
        devTools.send(Performance.disable());
        devTools.send(Performance.enable(Optional.empty()));
    }

    private void waitForTargetPageOnly() {
//        Wait()
//                .withTimeout(Duration.ofSeconds(90))
//                .until(driver -> driver.getCurrentUrl().equals(targetUrl));

        $x(appReadySelector).shouldBe(visible, Duration.ofSeconds(90));

//        Wait()
//                .withTimeout(Duration.ofSeconds(30))
//                .until(driver -> "complete".equals(executeJavaScript("return document.readyState")));
    }

    private double readMetricMs(DevTools devTools, String metricName) {
        List<Metric> metrics = devTools.send(Performance.getMetrics());

        return metrics.stream()
                .filter(metric -> metricName.equals(metric.getName()))
                .findFirst()
                .map(metric -> metric.getValue().doubleValue() * 1000)
                .orElse(-1.0);
    }

    private void logSummary() {
        double coldFcp = fcpResultsMs.get(0);

        List<Double> cachedRuns = fcpResultsMs.subList(1, fcpResultsMs.size());
        double cachedMedian = percentile(cachedRuns, 50);
        double cachedP75    = percentile(cachedRuns, 75);
        double cachedP95    = percentile(cachedRuns, 95);

        double improvement = ((coldFcp - cachedMedian) / coldFcp) * 100;

        String summary = String.format(
                "FCP Cache Impact Summary:\n" +          // <-- \n not %n
                        "cold (run 1)       = %.2f ms\n" +
                        "cached median      = %.2f ms\n" +
                        "cached p75         = %.2f ms\n" +
                        "cached p95         = %.2f ms\n" +
                        "improvement        = %.1f%%\n" +
                        "all values         = %s",
                coldFcp,
                cachedMedian,
                cachedP75,
                cachedP95,
                improvement,
                fcpResultsMs
        );

        Allure.addAttachment("FCP Cache Impact Summary", summary);
        logActionF("%s",summary);
    }

    private double percentile(List<Double> values, int percentile) {
        List<Double> sorted = new ArrayList<>(values);
        Collections.sort(sorted);

        int index = (int) Math.ceil((percentile / 100.0) * sorted.size()) - 1;
        index = Math.max(0, Math.min(index, sorted.size() - 1));

        return sorted.get(index);
    }
}
