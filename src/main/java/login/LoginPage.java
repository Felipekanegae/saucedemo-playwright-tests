package login;

import browser.BrowserManager;
import com.microsoft.playwright.Page;

public class LoginPage {

    private final BrowserManager browserManager;

    public LoginPage(BrowserManager browserManager) {
        this.browserManager = browserManager;
    }

    public void openLoginPage() {
        Page page = browserManager.getPage();
        page.navigate("https://www.saucedemo.com/");
    }


}