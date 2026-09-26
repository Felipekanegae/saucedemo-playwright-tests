package login;

import com.microsoft.playwright.Page;
import io.cucumber.java.Before;
import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.Scenario;

import browser.BrowserManager;

public class LoginSteps {

    private final BrowserManager browserManager;

    public LoginSteps(BrowserManager browserManager) {
        this.browserManager = browserManager;

    }

    @Before
    public void beforeScenario(Scenario scenario) {

        browserManager.startBrowser();

        Page page = browserManager.getPage();
        page.navigate("https://www.saucedemo.com/");
    }

    @Given("I am on the login page")
    public void i_am_on_the_login_page() {

    }

    @When("I enter a valid user name and password")
    public void i_enter_a_valid_user_name_and_password() {

    }

    @Then("the user should be logged in successfully")
    public void the_user_should_be_logged_in_successfully() {

    }

    @After
    public void afterScenario() {
        browserManager.closeBrowser();

    }
}
