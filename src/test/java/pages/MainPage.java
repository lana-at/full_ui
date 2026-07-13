package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.cssClass;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;


import com.codeborne.selenide.Selenide;

public class MainPage {

    // === ЛОКАТОРЫ ДЛЯ ФОРМЫ ОБРАТНОГО ЗВОНКА ===
    private final SelenideElement callbackButton = $("button[data-modal='#askModalMainPage']");
    private final SelenideElement callbackNameInput = $("input[name='NAME']");
    private final SelenideElement callbackSurnameInput = $("input[name='SURNAME']");
    private final SelenideElement callbackCompanyInput = $("input[name='COMPANY_NAME']");
    private final SelenideElement callbackInnInput = $("input[name='INN']");
    private final SelenideElement callbackEmailInput = $("input[name='EMAIL']");
    private final SelenideElement callbackPhoneInput = $("input[name='PHONE']");
    private final SelenideElement callbackPolicyCheckbox = $(".vacancy__send_checkbox");
    private final SelenideElement callbackSubmitButton = $("button[type='submit'].registration__subtitle");

    // === ЛОКАТОРЫ ДЛЯ ФОРМЫ ПОДПИСКИ ===
    private final SelenideElement subscribeButton = $("a[href='#subscribe']");
    private final SelenideElement subscribeNameInput = $(".subscribe-form input[name='NAME'], form[id*='subscribe'] input[name='NAME']");
    private final SelenideElement subscribeEmailInput = $(".subscribe-form input[name='EMAIL'], form[id*='subscribe'] input[name='EMAIL']");
    private final SelenideElement subscribePolicyCheckbox = $("#subscribe_policycheckbox");
    private final SelenideElement subscribeSubmitButton = $("#bx_subscribe_btn_sljzMT");
    private final SelenideElement successMessage = $x("//*[contains(text(), 'Спасибо за подписку')]");

    // === ЛОКАТОРЫ ДЛЯ ДРУГИХ ФУНКЦИЙ ===
    private final SelenideElement loginButton = $("a.header__top-button");

    // === ЛОКАТОРЫ ДЛЯ ПОИСКА ===
    private final SelenideElement searchOpenButton = $(".btn__search-show");
    private final SelenideElement searchInput = $("input[name='q']");
    private final SelenideElement searchSubmitButton = $(".btn__search:not([disabled])");



    // === ОБЩИЕ МЕТОДЫ ===

    @Step("Открыть главную страницу")
    public MainPage open() {
        Selenide.open("/");
        removeCookieBanner();
        return this;
    }

    // === МЕТОДЫ ДЛЯ ФОРМЫ ОБРАТНОГО ЗВОНКА ===

    @Step("Открыть форму обратного звонка")
    public MainPage openCallbackForm() {
        callbackButton.click();
        $("div#askModalMainPage").shouldBe(visible);
        return this;
    }

    @Step("Заполнить имя: {name}")
    public MainPage setName(String name) {
        callbackNameInput.setValue(name);
        return this;
    }

    @Step("Заполнить фамилию: {surname}")
    public MainPage setSurname(String surname) {
        callbackSurnameInput.setValue(surname);
        return this;
    }

    @Step("Заполнить компанию: {company}")
    public MainPage setCompany(String company) {
        callbackCompanyInput.setValue(company);
        return this;
    }

    @Step("Заполнить ИНН: {inn}")
    public MainPage setInn(String inn) {
        callbackInnInput.setValue(inn);
        callbackInnInput.pressEscape();
        return this;
    }

    @Step("Заполнить email: {email}")
    public MainPage setEmail(String email) {
        callbackEmailInput.setValue(email);
        return this;
    }

    @Step("Заполнить телефон: {phone}")
    public MainPage setPhone(String phone) {
        callbackPhoneInput.setValue(phone);
        return this;
    }

    @Step("Отметить согласие на обработку персональных данных (форма звонка)")
    public MainPage agreeToPolicy() {
        callbackPolicyCheckbox.click();
        return this;
    }

    @Step("Отправить форму обратного звонка")
    public MainPage submitForm() {
        callbackSubmitButton.click();
        return this;
    }

    // === МЕТОДЫ ДЛЯ ФОРМЫ ПОДПИСКИ ===

    @Step("Удалить cookie баннер")
    public MainPage removeCookieBanner() {
        executeJavaScript(
                "document.querySelectorAll('[class*=cookie]').forEach(el => el.remove());"
        );
        return this;
    }

    /*@Step("Открыть форму подписки на новости")
    public MainPage openSubscribeForm() {
        removeCookieBanner();
        subscribeButton.click();
        $("#subscribe_form").shouldBe(visible);
        return this;
    }
    */

    @Step("Открыть форму подписки на новости")
    public MainPage openSubscribeForm() {
        removeCookieBanner();
        subscribeButton.click();

        // 1. Скроллим к секции подписки
        $("#subscribe").scrollIntoView(true);

        // 2. Ищем форму по классу ИЛИ по части id (универсально для Битрикс)
        SelenideElement subscribeForm = $(".subscribe-form, form[id*='subscribe'], #subscribe_form");

        // 3. Ждем, пока она станет видимой
        subscribeForm.shouldBe(visible, java.time.Duration.ofSeconds(5));

        return this;
    }

    @Step("Ввести имя для подписки: {name}")
    public MainPage enterSubscribeName(String name) {
        subscribeNameInput.setValue(name);
        return this;
    }

    @Step("Ввести email для подписки: {email}")
    public MainPage enterSubscribeEmail(String email) {
        subscribeEmailInput.setValue(email);
        return this;
    }

   /* @Step("Отметить согласие на обработку персональных данных (подписка)")
    public MainPage agreeToSubscribePolicy() {
        subscribePolicyCheckbox.click();
        return this;
    } */



    @Step("Отметить согласие на обработку персональных данных (подписка)")
    public MainPage agreeToSubscribePolicy() {
        // Используем существующий локатор subscribePolicyCheckbox
        executeJavaScript(
                "arguments[0].click();",
                subscribePolicyCheckbox
        );
        return this;
    }

    @Step("Нажать кнопку подписки")
    public MainPage clickSubscribeButton() {
        subscribeSubmitButton.click();
        return this;
    }

    @Step("Проверить сообщение об успешной подписке")
    public MainPage checkSubscribeSuccess() {
        successMessage.shouldBe(visible);
        return this;
    }

    // === МЕТОДЫ ДЛЯ ЛОГИНА ===

    @Step("Открыть страницу входа")
    public LoginPage openLoginPage() {
        loginButton.click();
        Selenide.open("https://lk.softline.ru/");
        return new LoginPage();
    }

    // === МЕТОДЫ ДЛЯ ПОИСКА ===

    @Step("Открыть форму поиска")
    public MainPage openSearch() {
        searchOpenButton.click();  // ← Используем searchOpenButton
        return this;
    }

    @Step("Ввести поисковый запрос: {query}")
    public MainPage searchFor(String query) {
        searchInput.setValue(query);
        searchSubmitButton.shouldNotHave(cssClass("disabled"));
        searchSubmitButton.click();
        return this;
    }

    @Step("Проверить отсутствие сообщения об успешной подписке")
    public MainPage checkSubscribeNotSuccess() {
        successMessage.shouldNotBe(visible);
        return this;
    }
}