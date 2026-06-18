package t5.ipe.cucumber.core.web;

import org.assertj.core.api.SoftAssertions;
import t5.ipe.cucumber.core.web.util.AllureUtils;

public class TestContext {
    public SoftAssertions softly;

    private int softAssertionFailureCount = 0;

    private static final TestContext instance = new TestContext();

    public TestContext() {
        // private constructor to prevent direct instantiation
    }

    public static TestContext getInstance() {
        return instance;
    }

    public void assertAll() {
        if (softly != null) {
//            try {
                softly.assertAll();
//            } catch (AssertionError e) {
//                softAssertionFailureCount++;
//                AllureUtils.logActionF(e.getMessage());
//            }
        }
    }
}
