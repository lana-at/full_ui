package helpers;

import com.codeborne.selenide.Selenide;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class Attach {
    @Attachment(value = "{name}", type = "image/png")
    public static byte[] screenshotAs(String name) {
        return ((TakesScreenshot) getWebDriver()).getScreenshotAs(OutputType.BYTES);
    }

    @Attachment(value = "Page source", type = "text/html")
    public static byte[] pageSource() {
        return getWebDriver().getPageSource().getBytes(StandardCharsets.UTF_8);
    }

    @Attachment(value = "{name}", type = "text/plain")
    public static String attachAsText(String name, String text) {
        return text;
    }

    public static void browserConsoleLogs() {
        attachAsText("Browser console logs",
                String.join("\n", Selenide.getWebDriverLogs("browser")));
    }

    @Attachment(value = "Video", type = "text/html", fileExtension = ".html")
    public static String addVideo() {
        if (System.getProperty("remote", "").isEmpty()) {
            return "";
        }

        String sessionId = ((RemoteWebDriver) getWebDriver()).getSessionId().toString();
        String videoUrl = String.format(
                "%s/video/%s.mp4",
                System.getProperty("remote").replace("/wd/hub", ""),
                sessionId
        );
        return "<html><body><video width='100%' height='100%' controls autoplay><source src='"
                + videoUrl + "' type='video/mp4'></video></body></html>";
    }
}