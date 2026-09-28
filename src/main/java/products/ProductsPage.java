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
}
