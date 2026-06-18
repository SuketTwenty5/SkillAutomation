package t5.ipe.cucumber.core.web.util;

import io.cucumber.java.Scenario;
import org.apache.commons.io.FileUtils;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.concurrent.TimeUnit;

import t5.ipe.cucumber.core.web.StepCounter;

import java.io.ByteArrayInputStream;

/**
 * Created by: EKruze
 * Date: 20/10/2023
 */
public class AllureUtils {

    public static String sanitize(String name) {
        return name
                .replaceAll("[\\\\/:*?\"<>|]", "_") // replace invalid chars
                .replaceAll("\\s+", "_");          // replace spaces with underscores
    }
    public static String getScreenshotName() {
        Scenario scenario = StepCounter.getScenario(); // your method to get current scenario
        String name = "Test-";

        if (scenario != null) {
            // Get scenario name from ScenarioContext
            String scenarioName = scenario.getName();
            name += scenarioName;

            // Append current step number from StepCounter
            name += "-Step-" + StepCounter.get();
        } else {
            name += "UnknownScenario-Step-" + StepCounter.get();
        }

        // Sanitize for a valid filename
        name = AllureUtils.sanitize(name);

        System.out.println("[ScreenshotUtils] Screenshot name: " + name);
        return name;
    }

    public static void attachPageSource(String stepName) {
        String pageSource = WebDriverRunner.getWebDriver().getPageSource();

        Allure.addAttachment(
                "Page Source - " + stepName,
                "text/html",
                pageSource,
                ".html"
        );
    }

    public static void saveScreenshot() {
        try {
            System.out.println("[ScreenshotUtils] Capturing screenshot...");
            byte[] screenshotAsBytes = ((TakesScreenshot) WebDriverRunner.getWebDriver())
                    .getScreenshotAs(OutputType.BYTES);
            System.out.println("[ScreenshotUtils] Screenshot captured, bytes: " + screenshotAsBytes.length);
            String name = getScreenshotName();
            Allure.addAttachment(name, new ByteArrayInputStream(screenshotAsBytes));
            System.out.println("[ScreenshotUtils] Attached to Allure");
            // Get JENKINS_HOME, fallback to current dir if not set
            String jenkinsHome = System.getenv("JENKINS_HOME");
            if (jenkinsHome == null || jenkinsHome.isEmpty()) {
                jenkinsHome = ".";
            }
            // Folder: <JENKINS_HOME>/previousScreenshot/
            File folder = new File(jenkinsHome + "/previousScreenshot");
            // Create folder if it does not exist
            if (!folder.exists()) {
                boolean created = folder.mkdirs();
                if (created) {
                    System.out.println("[ScreenshotUtils] Created folder: " + folder.getAbsolutePath());
                }
            }
            // File to save
            File destFile = new File(folder, name + ".png");

            // Write file (overwrite if exists)
            FileUtils.writeByteArrayToFile(destFile, screenshotAsBytes);

            System.out.println("[ScreenshotUtils] Saved/Updated screenshot: " + destFile.getAbsolutePath());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void logAction(String text) {
        attachWithSystemOut("Action", text);
    }

    public static void logActionF(String text, Object... args) {
        logAction(String.format(text, args));
    }

    public static void logResult(String text) {
        attachWithSystemOut("Result", text);
    }

    public static void logResultF(String text, String ... args) {
        logResult(String.format(text, args));
    }

    private static void attachWithSystemOut(String attachmentName, String text) {
        System.out.println(text);
        Allure.addAttachment(attachmentName, text);
    }

    @Attachment(value = "{name}", type = "video/mp4", fileExtension = ".mp4")
    public static byte[] attachVideo(String name, String videoPath) throws IOException {
        File video = new File(videoPath);

        if (!video.exists() || video.length() == 0) {
            System.out.println("⚠️ Video file not found or empty: " + videoPath);
            return new byte[0]; // avoid exception in Allure
        }
        System.out.println("✅ Attaching video: " + videoPath);

        // Read and return bytes to attach in Allure
        return Files.readAllBytes(video.toPath());
    }

    public static void attachAllureVideo(String sessionId) {
        InputStream inputStream = null;
        String selenoidUrl = "https://autoregression.twenty5.com/selenoid";
        try {
            URL videoUrl = new URL(String.format("%s/video/%s.mp4", selenoidUrl, sessionId));
            URLConnection connection = videoUrl.openConnection();

            // Wait for video to be generated
            int attempts = 0;
            boolean videoAvailable = false;
            while (attempts < 5 && !videoAvailable) {
                System.out.println(String.format("Checking video availability, Attempt %d", attempts + 1));
                try {
                    // Check if the video stream is available
                    System.out.println("[Attempting to fetch video from:] " + videoUrl);
                    inputStream = videoUrl.openStream();
                    videoAvailable = true; // If no exception, the video is available
                } catch (FileNotFoundException e) {
                    // Video not yet available, retrying
                    System.out.println("Video not found, retrying...");
                    System.out.println("Error details: " + e.getMessage());
                    attempts++;
                    if (attempts < 5) {
                        TimeUnit.SECONDS.sleep(1);  // Wait for 1 second before retrying
                    }
                } catch (IOException e) {
                    // Handle other IO exceptions (e.g., network issues)
                    System.out.println("Error while accessing video URL: " + e.getMessage());
                    break;  // Exit the loop if a network issue occurs
                }
            }
            if(videoAvailable){
                String html = String.format(
                        "<html><body><a href='%s' target='_blank'>▶️ Click to View/Download Selenoid Video</a></body></html>",
                        videoUrl
                );
                Allure.addAttachment("Selenoid Video", "text/html",
                        new ByteArrayInputStream(html.getBytes(StandardCharsets.UTF_8)), ".html");
            }

            if (videoAvailable && inputStream != null) {
                System.out.println("Video available, attaching to Allure report");
                Allure.addAttachment("Video", "video/mp4", inputStream, "mp4");
            } else {
                System.out.println("Video not available after 5 attempts");
            }

        } catch (Exception e) {
            System.out.println("Error while saving video");
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
