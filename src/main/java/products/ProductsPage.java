package products;

import browser.BrowserManager;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.options.AriaRole;
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

    private Locator cartButton() {
        return browserManager.getPage().locator(
                "[data-test='shopping-cart-link']");

    }

    private Locator productItem(String productName) {
        return browserManager.getPage()
                .locator("[data-test='inventory-item']")
                .filter(new Locator.FilterOptions().setHasText(productName));

    }

    private Locator addButton(String productName) {
        return productItem(productName)
                .getByRole(AriaRole.BUTTON,
                        new Locator.GetByRoleOptions().setName("Add to cart"));
    }

    private Locator removeButton(String productName) {
        return productItem(productName)
                .getByRole(AriaRole.BUTTON,
                        new Locator.GetByRoleOptions().setName("Remove"));
    }

    //===ACTIONS===

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

        addButton(product).click();


    }

    public void removeProductFromCart() {

        String product = testData.getStringOf("PRODUCT");
        removeButton(product).click();
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
        assertThat(productItem(product)).isVisible();

    }

    public void validateProductRemovedFromCart() {

        String product = testData.getStringOf("PRODUCT");
        assertThat(productItem(product)).isHidden();

    }

}