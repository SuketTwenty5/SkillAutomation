package t5.ipe.cucumber.core.web;
import com.codeborne.selenide.WebDriverRunner;
import t5.ipe.cucumber.core.web.util.AllureUtils;

import java.util.Set;

public class WindowLogger {
    public static void logCurrentWindows(String message) {
        Set<String> handles = WebDriverRunner.getWebDriver().getWindowHandles();

        StringBuilder sb = new StringBuilder();
        sb.append(message).append("\n");
        sb.append("Total windows: ").append(handles.size()).append("\n");

        int i = 1;
        for (String handle : handles) {
            sb.append("[").append(i++).append("] ").append(handle).append("\n");
        }

        AllureUtils.logActionF(sb.toString());
    }
}
