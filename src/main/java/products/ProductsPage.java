package products;

import browser.BrowserManager;
import com.microsoft.playwright.Locator;
import testData.ExcelTestData;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;


public class ProductsPage {

    private final BrowserManager browserManager;
    private ExcelTestData testData;

    public ProductsPage(BrowserManager browserManager, ExcelTestData testData) {
        this.browserManager = browserManager;
        this.testData = testData;

    }

    //===LOCATORS===
    private Locator sortContainer() {
        return browserManager.getPage().locator(
                "[data-test='product-sort-container']");

    }

    private Locator addBackpackToCartButton() {
        return browserManager.getPage().locator(
                "#add-to-cart-sauce-labs-backpack");

    }

    private Locator removeBackpackToCartButton() {
        return browserManager.getPage().locator(
                "#remove-sauce-labs-backpack");

    }


    private Locator addBikeLightToCartButton() {
        return browserManager.getPage().locator(
                "#add-to-cart-sauce-labs-bike-light");

    }

    private Locator removeBikeLightToCartButton() {
        return browserManager.getPage().locator(
                "#remove-sauce-labs-bike-light");

    }

    private Locator addFleeceJacketToCartButton() {
        return browserManager.getPage().locator(
                "#add-to-cart-sauce-labs-fleece-jacket");

    }

    private Locator removeFleeceJacketToCartButton() {
        return browserManager.getPage().locator(
                "#remove-sauce-labs-fleece-jacket");

    }

    private Locator addTShirtToCartButton() {
        return browserManager.getPage().locator(
                "#add-to-cart-sauce-labs-bolt-t-shirt");

    }

    private Locator removeTShirtToCartButton() {
        return browserManager.getPage().locator(
                "#remove-sauce-labs-bolt-t-shirt");

    }

    private Locator addOnesieToCartButton() {
        return browserManager.getPage().locator(
                "#add-to-cart-sauce-labs-onesie");

    }


    private Locator removeOnesieToCartButton() {
        return browserManager.getPage().locator(
                "#remove-sauce-labs-onesie");

    }

    private Locator addclassicTshirtToCartButton() {
        return browserManager.getPage().locator(
                "#add-to-cart-test.allthethings()-t-shirt-(red)");

    }

    private Locator removeClassicTshirtToCartButton() {
        return browserManager.getPage().locator(
                "#remove-test.allthethings()-t-shirt-(red)");

    }

    private Locator cartButton() {
        return browserManager.getPage().locator(
                "[data-test='shopping-cart-link']");

    }

    private Locator cartList() {
        return browserManager.getPage().locator(
                "[class='cart_item_label']");

    }

    private Locator productName(String productName) {
        return browserManager.getPage()
                .locator("[data-test='inventory-item-name']")
                .filter(new Locator.FilterOptions().setHasText(productName));
    }

    //===ACTIONS==

    public void sortByProducts() {

        String sort = testData.getStringOf("SORT");

        switch (sort) {
            case "NAME_ASCENDING":
                sortContainer().selectOption("az");
                break;

            case "NAME_DESCENDING":
                sortContainer().selectOption("za");
                break;

            case "PRICE_ASCENDING":
                sortContainer().selectOption("lohi");
                break;

            case "PRICE_DESCENDING":
                sortContainer().selectOption("hilo");
                break;

            default:
                throw new IllegalArgumentException("Invalid sort option: " + sort);

        }

    }

    public void addProductToCart() {

        String product = testData.getStringOf("PRODUCT");

        switch (product) {
            case "Backpack":
                addBackpackToCartButton().click();
                break;

            case "Bike_Light":
                addBikeLightToCartButton().click();
                break;

            case "T-Shirt":
                addTShirtToCartButton().click();
                break;

            case "Classic_T-Shirt":
                addclassicTshirtToCartButton().click();
                break;

            case "Fleece_Jacket":
                addFleeceJacketToCartButton().click();
                break;

            case "Onesie":
                addOnesieToCartButton().click();
                break;

            default:
                throw new IllegalArgumentException("Invalid sort option: " + product);

        }

    }

    //===VALIDATIONS===

    public void validateSortIsSelected() {

        String sort = testData.getStringOf("SORT");

        switch (sort) {
            case "NAME_ASCENDING":
                assertThat(sortContainer()).hasValue("az");
                break;

            case "NAME_DESCENDING":
                assertThat(sortContainer()).hasValue("za");
                break;

            case "PRICE_ASCENDING":
                assertThat(sortContainer()).hasValue("lohi");
                break;

            case "PRICE_DESCENDING":
                assertThat(sortContainer()).hasValue("hilo");
                break;

            default:
                throw new IllegalArgumentException("Invalid sort option: " + sort);

        }

    }

    public void validateAddProductToCart() {
        cartButton().click();
        validateProductInTheCart();

    }

    private void validateProductInTheCart() {

        String product = testData.getStringOf("PRODUCT");

        assertThat(productName(product)).isVisible();

    }

}