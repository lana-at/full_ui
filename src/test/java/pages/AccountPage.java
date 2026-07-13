package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class AccountPage {

    private final SelenideElement accountLabel = $(".nav-logo__label");

    @Step("Проверить успешный вход в личный кабинет")
    public AccountPage checkWelcomeMessage() {
        accountLabel.shouldBe(visible);
        return this;
    }
}