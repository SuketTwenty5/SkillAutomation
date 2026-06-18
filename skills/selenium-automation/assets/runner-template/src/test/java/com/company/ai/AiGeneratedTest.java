package com.company.ai;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

class AiGeneratedTest {
    @BeforeAll
    static void configureBrowser() {
        Configuration.browser = LocalChromeProvider.class.getName();
        Configuration.timeout = 15000;
        Configuration.screenshots = true;
        Configuration.savePageSource = true;
    }

    @Test
    void replaceWithMappedManualTest() {
        open(System.getProperty("app.url", "https://example.com"));
        $("body").shouldBe(visible);
    }
}
