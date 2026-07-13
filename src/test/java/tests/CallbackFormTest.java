package tests;

import io.qameta.allure.Description;
import org.junit.jupiter.api.Test;
import testdata.TestData;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class CallbackFormTest extends BaseTest {

    @Test
    @Description("Отправка формы обратного звонка")
    void callbackFormTest() {
        mainPage.open()
                .openCallbackForm()
                .setName(TestData.CALLBACK_NAME)
                .setSurname(TestData.CALLBACK_SURNAME)
                .setCompany(TestData.CALLBACK_COMPANY)
                .setInn(TestData.CALLBACK_INN)
                .setEmail(TestData.VALID_EMAIL)
                .setPhone(TestData.CALLBACK_PHONE)
                .agreeToPolicy();
        $("input[name='NAME']").shouldHave(attribute("value", TestData.CALLBACK_NAME));
        $("input[name='SURNAME']").shouldHave(attribute("value", TestData.CALLBACK_SURNAME));
        $("input[name='EMAIL']").shouldHave(attribute("value", TestData.VALID_EMAIL));
        $("input[name='PHONE']").shouldHave(attribute("value", "+7(999)123-45-67"));

    }

}