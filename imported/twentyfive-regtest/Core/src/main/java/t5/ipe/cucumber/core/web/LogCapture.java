package t5.ipe.cucumber.core.web;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v148.log.Log;
import t5.ipe.cucumber.core.web.util.AllureUtils;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.stream.Collectors;
import org.openqa.selenium.devtools.DevTools;


public class LogCapture {

    private static DevTools devTools;
    private final List<String> logBuffer = new ArrayList<>();

    public LogCapture(DevTools devTools) {
        this.devTools = devTools;
    }

    public void startCapture() throws AWTException {
        logBuffer.clear();
        devTools.send(Log.enable());
        devTools.addListener(Log.entryAdded(), entry -> {
            String logRecord = entry.getLevel() + " | " + entry.getText();
            logBuffer.add(logRecord);

            // Auto-detect SEVERE logs immediately
            if (entry.getLevel().equals(Level.SEVERE) || entry.getText().toUpperCase().contains("SEVERE") || entry.getText().contains("TypeError") || entry.getText().contains("Uncaught")) {
//                Selenide.sleep(300);
                AllureUtils.saveScreenshot();
                throw new AssertionError("❌ SEVERE Console Error Detected: " + entry.getText());
            }
        });
    }

    public List<String> stopCapture()  throws AWTException {
        AllureUtils.saveScreenshot();
        Robot robot = new Robot();
        robot.keyPress(KeyEvent.VK_F12);
        robot.keyRelease(KeyEvent.VK_F12);
        Selenide.sleep(1000); // Wait for DevTools to close
        return new ArrayList<>(logBuffer);
    }

    public void printLogs() {
        AllureUtils.logActionF("============ Console Logs ============");
        String result = logBuffer.stream().map(s ->"**" + s + "**").collect(Collectors.joining("\n"));
        AllureUtils.logActionF(result);
        AllureUtils.logActionF("==================END=================");
    }
}
