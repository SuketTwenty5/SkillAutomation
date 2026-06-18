package t5.ipe.cucumber.core.web;
import io.cucumber.plugin.EventListener;
import io.cucumber.plugin.event.EventPublisher;
import io.cucumber.plugin.event.TestStepStarted;
import io.cucumber.plugin.event.PickleStepTestStep;
import io.qameta.allure.Allure;
import t5.ipe.cucumber.core.web.util.AllureUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class ScreenshotStepListener implements EventListener {
    private static final String SCREENSHOT_FOLDER = System.getenv("JENKINS_HOME") + "/previousScreenshot";

    @Override
    public void setEventPublisher(EventPublisher publisher) {
        publisher.registerHandlerFor(TestStepStarted.class, this::onTestStepStarted);
    }

    private void onTestStepStarted(TestStepStarted event) {
//        long startTime = System.currentTimeMillis();
//        long endTime = 0; // End timer

        // Only handle actual Gherkin steps (skip hooks)
        if (event.getTestStep() instanceof PickleStepTestStep) {
            PickleStepTestStep step = (PickleStepTestStep) event.getTestStep();
            String stepText = step.getStep().getText();
            // Increment step counter
            StepCounter.increment();

            int infoStep = StepCounter.getStep();
            int currentStep = StepCounter.get();

//            AllureUtils.logActionF("Step %d, sub step %d started: %s", infoStep, currentStep, stepText);

            String fileName = AllureUtils.getScreenshotName();
            File file = new File(SCREENSHOT_FOLDER, fileName + ".png");

            if (file.exists()) {
                System.out.println("[ScreenshotStepListener] Found existing screenshot: " + file.getAbsolutePath());
                try (FileInputStream fis = new FileInputStream(file)) {
                    Allure.addAttachment("Previous Test Screenshot. Step:- " +infoStep+" Sub Step:- "+currentStep, "image/png", fis, ".png");
//                    endTime = System.currentTimeMillis();
//                    long timeTakenMs = endTime - startTime;
//                    System.out.println("[ScreenshotStepListener] Time taken to attach screenshot: " + timeTakenMs + " ms");
                } catch (IOException e) {
                    AllureUtils.logActionF("⚠️ Failed to attach screenshot: " + e.getMessage());
                }
            } else {
                System.out.println("[ScreenshotStepListener] Screenshot not found: " + file.getAbsolutePath());
            }
        }

    }

}

