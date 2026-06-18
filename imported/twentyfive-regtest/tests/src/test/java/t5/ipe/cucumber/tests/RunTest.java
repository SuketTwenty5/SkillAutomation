package t5.ipe.cucumber.tests;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.core.backend.DefaultObjectFactory;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import t5.ipe.cucumber.core.web.util.SSLUtils;

/**
 * Cucumber run configuration. Packages with step-def-s.
 * <p>
 * Created by: EKruze
 * Date: 20/10/2023
 */
@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {
                "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm",
                "pretty",
                "json:target/cucumber-reports/Cucumber.json",
                "t5.ipe.cucumber.core.web.ScreenshotStepListener"},
        glue = {
                "t5.ipe.cucumber"
        }, features = "src/test/resources/features",
        tags = "not @Ignore",
        objectFactory = DefaultObjectFactory.class)
public class RunTest {
}
