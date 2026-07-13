package tests;

import io.qameta.allure.Description;
import org.junit.jupiter.api.Test;
import testdata.TestData;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class NewsletterTest extends BaseTest {

    @Test
    @Description("Подписка на новости с валидным email")
    void newsletterValidEmailTest() {
        mainPage.open()
                .openSubscribeForm()
                .enterSubscribeName(TestData.USER)
                .enterSubscribeEmail(TestData.VALID_EMAIL)
                .agreeToSubscribePolicy()
                .clickSubscribeButton()
                .checkSubscribeSuccess();  // ✅ Проверяем "Спасибо за подписку"
    }

    @Test
    @Description("Подписка с невалидным email")
    void newsletterInvalidEmailTest() {
        mainPage.open()
                .openSubscribeForm()
                .enterSubscribeName(TestData.USER)
                .enterSubscribeEmail(TestData.INVALID_EMAIL)
                .agreeToSubscribePolicy()
                .clickSubscribeButton()
                .checkSubscribeNotSuccess();
    }
}