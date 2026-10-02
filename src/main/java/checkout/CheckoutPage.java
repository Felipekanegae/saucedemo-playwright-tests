package checkout;

import browser.BrowserManager;
import com.microsoft.playwright.Locator;
import testData.ExcelTestData;
import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;


public class CheckoutPage {

    private final BrowserManager browserManager;
    private ExcelTestData testData;

    public CheckoutPage(BrowserManager browserManager,
                        ExcelTestData testData) {
        this.browserManager = browserManager;
        this.testData = testData;

    }

    private Locator cartButton() {
        return browserManager.getPage().locator(
                "[data-test='shopping-cart-link']");

    }

    private Locator checkoutButton() {
        return browserManager.getPage().locator(
                "#checkout");
    }

    private Locator continueButton() {
        return browserManager.getPage().locator(
                "#continue");
    }

    private Locator firstNameField() {
        return browserManager.getPage().locator(
                "#first-name");
    }

    private Locator lastNameField() {
        return browserManager.getPage().locator(
                "#last-name");
    }

    private Locator zipPostalCodeField() {
        return browserManager.getPage().locator(
                "#postal-code");
    }

    private Locator checkoutContainer() {
        return browserManager.getPage().locator(
                "#checkout_summary_container");
    }

    private Locator errorMessage() {
        return browserManager.getPage().locator(
                "[data-test='error']");

    }

    //===ACTIONS===

    public void openCheckoutPage(){
        cartButton().click();
        checkoutButton().click();

    }

    public void checkout() {
        fillCheckoutForm();
        continueButton().click();

    }


    //===FORM FILLING===

    private  void fillCheckoutForm() {
        firstNameField().fill(testData.getStringOf("FIRST_NAME"));
        lastNameField().fill(testData.getStringOf("LAST_NAME"));
        zipPostalCodeField().fill(testData.getStringOf("ZIP_CODE"));

    }

    //===VALIDATIONS===

    public void validateCheckoutOverviewPage() {
        assertThat(checkoutContainer()).isVisible();

    }

    public void validateErrorMessage(String message) {
        assertThat(errorMessage()).hasText(message);

    }

}
