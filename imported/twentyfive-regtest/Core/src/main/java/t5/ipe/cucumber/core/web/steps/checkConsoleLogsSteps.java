package t5.ipe.cucumber.core.web.steps;

import com.codeborne.selenide.Selenide;
import io.cucumber.java.en.And;
import t5.ipe.cucumber.core.web.CucumberRuntime;
import t5.ipe.cucumber.core.web.LogCapture;
import t5.ipe.cucumber.core.web.interfaces.element_interfaces.Clickable;
import t5.ipe.cucumber.core.web.util.AllureUtils;
import t5.ipe.cucumber.core.web.util.web.SearchUtils;
import org.openqa.selenium.devtools.DevTools;
import t5.ipe.cucumber.core.web.util.web.WebDriverUtils;
import t5.ipe.cucumber.core.web.util.web.WebUtil;

import java.awt.*;


public class checkConsoleLogsSteps {
    private final DevTools devTools = WebUtil.devTools;
    private LogCapture stopLogCapture;
//    private LogCapture logCapture;

    @And("^(?:I |)check console logs when clicked on (.+)$")
    public void clickOn(String elementName) throws AWTException {
        devTools.createSession();
        LogCapture logCapture = new LogCapture(devTools);

        logCapture.startCapture();

        AllureUtils.logActionF("Enter Method clickOn with the elementName %s", elementName);
        SearchUtils.findElementAtCurrentPage(elementName, Clickable.class)
                .click();
        AllureUtils.logActionF("Performed click on %s", elementName);
        Selenide.sleep(7000); // Wait for potential console logs to appear
        logCapture.stopCapture();
        logCapture.printLogs();
        devTools.close();
    }
    @And("^(?:I |)start console log check session$")
    public void startConsoleLogCheckSession() throws AWTException {
        AllureUtils.logActionF("Logs before clearing:");
        CucumberRuntime.attachConsoleLogs();
//        CucumberRuntime.clearBrowserLogs();
    }

    @And("^(?:I |)stop console log check session$")
    public void stopConsoleLogCheckSession() throws AWTException {
        AllureUtils.saveScreenshot();
        CucumberRuntime.getConsoleLogsAsStringAndStopOnError();
    }

    @And("^(?:I |)stop console log check session_-Warning-_$")
    public void stopConsoleLogCheckSessionWarning() throws AWTException {
        AllureUtils.saveScreenshot();
        CucumberRuntime.getConsoleLogsAsStringAndStopOnErrorWarning();
    }
}
