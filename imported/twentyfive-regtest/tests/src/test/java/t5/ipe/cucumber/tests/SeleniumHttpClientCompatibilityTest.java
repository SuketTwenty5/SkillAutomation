package t5.ipe.cucumber.tests;

import org.junit.Test;
import org.openqa.selenium.remote.http.HttpClient;

import static org.junit.Assert.assertNotNull;

public class SeleniumHttpClientCompatibilityTest {
    @Test
    public void seleniumHttpClientFactoryLoadsFromRuntimeClasspath() {
        assertNotNull(HttpClient.Factory.createDefault());
    }
}
