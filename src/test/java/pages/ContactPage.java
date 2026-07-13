package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class ContactPage {
    private final SelenideElement phoneElement = $("a[href^='tel:']");
    private final SelenideElement addressElement = $x("//td[contains(text(), 'E-mail:')]/following-sibling::td//a[contains(@href, 'mailto:')]");

    @Step("Удалить cookie баннер")
    public ContactPage removeCookieBanner() {
        executeJavaScript(
                "document.querySelectorAll('[class*=cookie]').forEach(el => el.remove());"
        );
        System.out.println("✅ Cookie баннер удалён из DOM");
        return this;
    }

    @Step("Проверить наличие телефона")
    public ContactPage checkPhoneDisplayed() {
        phoneElement.shouldBe(visible);
        return this;
    }

    @Step("Проверить наличие почтового адреса")
    public ContactPage checkEmailDisplayed() {
        addressElement.shouldBe(visible);
        return this;
    }
}