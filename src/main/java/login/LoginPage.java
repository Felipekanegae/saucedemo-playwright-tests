package login;

import browser.BrowserManager;
import com.microsoft.playwright.Locator;
import testData.ExcelTestData;
import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class LoginPage {

    private final BrowserManager browserManager;
    private ExcelTestData testData;

    public LoginPage(BrowserManager browserManager, ExcelTestData testData) {
        this.browserManager = browserManager;
        this.testData = testData;

    }

    //===LOCATORS===
    private Locator usernameField() {
        return browserManager.getPage().locator(
                "[data-test='username']");
    }

    private Locator passwordField() {
        return browserManager.getPage().locator(
                "[data-test='password']");
    }

    private Locator loginButton() {
        return browserManager.getPage().locator(
                "[data-test='login-button']");
    }

    private Locator menuButton() {
        return browserManager.getPage().locator(
                "#react-burger-menu-btn");

    }

    private Locator logoutButton() {
        return browserManager.getPage().locator(
                "#logout_sidebar_link");

    }

    private Locator errorMessage() {
        return browserManager.getPage().locator(
                "[class='error-message-container error']");

    }

    //===ACTIONS===
    public void login(){
        fillLoginForm();
        loginButton().click();

    }

    public void logout(){
        menuButton().click();
        logoutButton().click();

    }

    //===FORM FILLING===
    private void fillLoginForm(){
        usernameField().fill(testData.getStringOf("USER_NAME"));
        passwordField().fill(testData.getStringOf("PASSWORD"));

    }

    //===VALIDATIONS===
    public void validateLoginPage(){
        assertThat(usernameField()).isVisible();
        assertThat(passwordField()).isVisible();
        assertThat(loginButton()).isVisible();

    }

    public void validateLogin(){
        menuButton().click();
        assertThat(logoutButton()).isVisible();

    }

    public void validateErrorMessage(String message){
        assertThat(errorMessage()).hasText(message);

    }

}