package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import helpers.Attach;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.remote.DesiredCapabilities;
import pages.MainPage;
import java.time.Duration;
import static com.codeborne.selenide.Condition.visible;

import java.util.Map;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class BaseTest {
    protected static MainPage mainPage = new MainPage();

    @BeforeAll
    static void configure() {
        Configuration.baseUrl = "https://softline.ru";
        Configuration.browserSize = "1920x1080";
        Configuration.timeout = 10000;
        Configuration.pageLoadStrategy = "normal";

        // Настройки для Selenoid (удалённый запуск)
        String remote = System.getProperty("remote", "");
        if (!remote.isEmpty()) {
            Configuration.remote = remote;
            Configuration.browser = System.getProperty("browser", "chrome");
            Configuration.browserVersion = System.getProperty("browserVersion", "120.0");

            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability("selenoid:options", Map.of(
                    "enableVNC", true,
                    "enableVideo", true
            ));
            Configuration.browserCapabilities = capabilities;
        }

        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
    }

    @AfterEach
    void addAttachments() {
        Attach.screenshotAs("Last screenshot");
        Attach.pageSource();
        Attach.browserConsoleLogs();
        Attach.addVideo();
        closeWebDriver();
    }

}