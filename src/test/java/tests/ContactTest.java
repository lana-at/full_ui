package tests;

import io.qameta.allure.Description;
import org.junit.jupiter.api.Test;
import pages.ContactPage;

import static com.codeborne.selenide.Selenide.open;

public class ContactTest extends BaseTest {
    @Test
    @Description("Проверка наличия контактной информации")
    void contactInfoTest() {
        open("/about/contact");
        ContactPage contactPage = new ContactPage();
        contactPage.removeCookieBanner()
                .checkEmailDisplayed()
                .checkPhoneDisplayed();

    }
}