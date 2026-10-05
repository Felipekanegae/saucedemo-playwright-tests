package checkout;

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
import products.ProductsPage;
import testData.ExcelTestData;


public class CheckoutSteps {
    private static final Logger log = LogManager.getLogger(CheckoutSteps.class);
    private CheckoutPage checkout;
    private LoginPage login;
    private ProductsPage products;
    private final BrowserManager browserManager;
    private final ExcelTestData testData;

    public CheckoutSteps(BrowserManager browserManager,
                         ExcelTestData testData,
                         CheckoutPage checkout,
                         LoginPage login,
                         ProductsPage products) {
        this.browserManager = browserManager;
        this.testData = testData;
        this.checkout = checkout;
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

    @Given("I added a product to cart")
    public void i_added_a_product_to_cart() {
        login.login();
        products.addProductToCart();
    }

    @Given("I am on the checkout information page")
    public void i_am_on_the_checkout_information_page() {
        checkout.openCheckoutPage();

    }

    @When("I fill in the required checkout information")
    public void i_fill_in_the_required_checkout_information() {
        checkout.checkout();

    }

    @Then("I should be on the checkout overview page")
    public void i_should_be_on_the_checkout_overview_page() {
        checkout.validateCheckoutOverviewPage();

    }

    @When("I try to continue without filling in the required information")
    public void i_try_to_continue_without_filling_in_the_required_information() {
        checkout.checkout();

    }

    @Then("The message {string} should be displayed")
    public void the_message_should_be_displayed(String message) {
        checkout.validateErrorMessage(message);

    }

    @When("I go to the checkout overview page")
    public void i_go_to_the_checkout_overview_page() {
       checkout.openCheckoutPage();
       checkout.checkout();

    }

    @Then("the added product should be displayed on the overview page")
    public void the_added_product_should_be_displayed_on_the_overview_page() {
        checkout.validateCheckoutOverviewPage();
        checkout.validateCheckoutOverviewItem();

    }

    @After
    public void afterScenario() {
        browserManager.closeBrowser();

    }

}
