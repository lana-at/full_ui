package tests;

import io.qameta.allure.Description;
import org.junit.jupiter.api.Test;
import testdata.TestData;
import pages.AccountPage;

public class LoginTest extends BaseTest {

    @Test
    @Description("Авторизация на сайте")
    void loginTest() {
        mainPage.open()
                .openLoginPage()
                .login(TestData.LOGIN, TestData.PASSWORD)
                .checkWelcomeMessage();
    }
}
