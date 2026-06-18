package t5.ipe.cucumber.core.web.steps;

import io.cucumber.java.en.And;

import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * Contains steps responsible for keyboard events.
 * <p>
 * Created by: EKruze
 * Date: 20/10/2023
 */
public class KeyboardSteps {

    @And("^(?:I |)press enter$")
    public void pressEnter() {
        try {
            Robot robot = new Robot();
            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }

    @And("^(?:I |)press escape$")
    public void pressEscape() {
        try {
            Robot robot = new Robot();
            robot.keyPress(KeyEvent.VK_ESCAPE);
            robot.keyRelease(KeyEvent.VK_ESCAPE);
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }

    @And("^(?:I |)open new browser tab$")
    public void openNewBrowserTab() {
        try {
            Robot robot = new Robot();
            robot.keyPress(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_T);
            robot.keyRelease(KeyEvent.VK_T);
            robot.keyRelease(KeyEvent.VK_CONTROL);
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }

    @And("^(?:I |)switch to next browser tab$")
    public void switchToPreviousBrowserTab() {
        try {
            Robot robot = new Robot();
            robot.keyPress(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_TAB);
            robot.keyRelease(KeyEvent.VK_TAB);
            robot.keyRelease(KeyEvent.VK_CONTROL);
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }

    @And("^(?:I |)press TAB key$")
    public void pressTab() {
        try {
            Robot robot = new Robot();
            robot.keyPress(KeyEvent.VK_TAB);
            robot.keyRelease(KeyEvent.VK_TAB);
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }
}
