package login;

import com.microsoft.playwright.Page;
import io.cucumber.java.Before;
import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.Scenario;

import browser.BrowserManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import testData.ExcelTestData;

public class LoginSteps {

    private static final Logger log = LogManager.getLogger(LoginSteps.class);
    private LoginPage login;
    private final BrowserManager browserManager;
    private final ExcelTestData testData;

    public LoginSteps(BrowserManager browserManager,
                      ExcelTestData testData,
                      LoginPage login) {
        this.browserManager = browserManager;
        this.testData = testData;
        this.login = login;

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

    @Given("I am on the login page")
    public void i_am_on_the_login_page() {
        login.validateLoginPage();

    }

    @When("I enter a valid user name and password")
    public void i_enter_a_valid_user_name_and_password() {
        login.login();

    }

    @Then("the user should be logged in successfully")
    public void the_user_should_be_logged_in_successfully() {
        login.validateLogin();

    }

    @When("I enter a locked out user")
    public void i_enter_a_locked_out_user() {
        login.login();

    }

    @Then("the message {string} should be displayed")
    public void the_message_should_be_displayed(String message) {
        login.validateErrorMessage(message);

    }


    @When("I try to login without credentials")
    public void i_try_to_login_without_credentials() {
        login.login();

    }

    @Given("I am logged in")
    public void i_am_logged_in() {
       login.login();

    }

    @When("I log out")
    public void i_log_out() {
        login.logout();

    }

    @Then("I should be logged out successfully")
    public void i_should_be_logged_out_successfully() {
        login.validateLoginPage();

    }

    @After
    public void afterScenario() {
        browserManager.closeBrowser();

    }
}
