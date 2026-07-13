package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class CloudSolutionsPage {

    // === ЛОКАТОРЫ ===

    // Заголовок страницы (проверка, что мы на нужной странице)
    private final SelenideElement pageTitle = $("h1, .page-title");

    // Список продуктов/услуг на странице облачных решений
    private final SelenideElement cloudProducts = $(".products-list, .catalog-items, .solutions-grid");

    // Конкретные облачные продукты (Azure, AWS, Google Cloud и т.д.)
    private final SelenideElement azureProduct = $("a[href*='azure'], .product-azure");
    private final SelenideElement awsProduct = $("a[href*='aws'], .product-aws");

    // === МЕТОДЫ ===

    @Step("Проверить заголовок страницы")
    public CloudSolutionsPage checkPageTitle() {
        pageTitle.shouldBe(visible);
        return this;
    }

    @Step("Проверить наличие облачных продуктов")
    public CloudSolutionsPage checkProductsDisplayed() {
        cloudProducts.shouldBe(visible);
        return this;
    }

    @Step("Проверить наличие Microsoft Azure")
    public CloudSolutionsPage checkAzureProduct() {
        azureProduct.shouldBe(visible);
        return this;
    }

    @Step("Проверить наличие Amazon AWS")
    public CloudSolutionsPage checkAwsProduct() {
        awsProduct.shouldBe(visible);
        return this;
    }
}