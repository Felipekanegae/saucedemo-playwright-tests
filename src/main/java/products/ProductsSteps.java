package products;

import com.microsoft.playwright.Page;
import io.cucumber.java.Before;
import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.Scenario;
import browser.BrowserManager;
import login.LoginPage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import testData.ExcelTestData;

public class ProductsSteps {

    private static final Logger log = LogManager.getLogger(ProductsSteps.class);
    private LoginPage login;
    private ProductsPage products;
    private final BrowserManager browserManager;
    private final ExcelTestData testData;

    public ProductsSteps(BrowserManager browserManager,
                         ExcelTestData testData,
                         LoginPage login,
                         ProductsPage products) {
        this.browserManager = browserManager;
        this.testData = testData;
        this.login = login;
        this.products = products;

    }

    @Before
    public void beforeScenario(Scenario scenario) {

        String ct = scenario.getSourceTagNames()
                .stream()
                .filter(tag -> tag.startsWith("@CT"))
                .findFirst()
                .orElse("@CT001")
                .replace("@CT", "");

        browserManager.startBrowser();

        testData.loadTestData(
                "src/test/resources/testData/testData.xlsx",
                "saucedemo", ct);

        Page page = browserManager.getPage();
        page.navigate("https://www.saucedemo.com/");

    }

    @Given("I am on the products page")
    public void i_am_on_the_products_page() {
        login.login();

    }

    @When("I sort the products by name in ascending order")
    public void i_sort_the_products_by_name_in_ascending_order() {
        products.sortByProducts();
    }

    @Then("the name ascending sort option should be selected")
    public void the_name_ascending_sort_option_should_be_selected() {
        products.validateSortIsSelected();

    }

    @When("I sort the products by name in descending order")
    public void i_sort_the_products_by_name_in_descending_order() {
        products.sortByProducts();
    }

    @Then("the name descending sort option should be selected")
    public void the_name_descending_sort_option_should_be_selected() {
        products.validateSortIsSelected();

    }


    @After
    public void afterScenario() {
        browserManager.closeBrowser();

    }
}
