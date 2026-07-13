package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class LoginPage {

    // === ЛОКАТОРЫ ===

    // Поле ввода email/логина
    private final SelenideElement emailInput = $("#email");

    // Поле ввода пароля
    private final SelenideElement passwordInput = $("#password");

    // Кнопка "Войти"
    private final SelenideElement submitButton = $("button[type='submit']");

    // Сообщение об ошибке (если логин не удался)
    private final SelenideElement errorMessage = $(".error-message, .alert-danger, .login-error");

    // Элемент личного кабинета (после успешного входа)
    private final SelenideElement accountWelcome = $(".welcome-message, .account-name, .user-profile");

    // === МЕТОДЫ ===

    @Step("Ввести email: {email}")
    public LoginPage enterEmail(String email) {
        emailInput.setValue(email);
        return this;
    }

    @Step("Ввести пароль")
    public LoginPage enterPassword(String password) {
        passwordInput.setValue(password);
        return this;
    }

    @Step("Нажать кнопку входа")
    public AccountPage clickLogin() {
        submitButton.click();
        return new AccountPage();
    }

    @Step("Выполнить вход с email: {email} и паролем")
    public AccountPage login(String email, String password) {
        enterEmail(email);
        enterPassword(password);
        return clickLogin();
    }

    @Step("Проверить сообщение об ошибке")
    public LoginPage checkErrorMessage() {
        errorMessage.shouldBe(visible);
        return this;
    }

    @Step("Проверить успешный вход")
    public AccountPage checkWelcomeMessage() {
        accountWelcome.shouldBe(visible);
        return new AccountPage();
    }
}