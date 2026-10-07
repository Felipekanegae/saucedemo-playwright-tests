package browser;

import com.microsoft.playwright.*;

public class BrowserManager {

    private Playwright playwright;
    private Browser browser;
    private BrowserContext context;
    private Page page;

    public void startBrowser() {
        playwright = Playwright.create();

        boolean headless = Boolean.getBoolean("headless");

        browser = playwright.chromium().launch(
                new BrowserType.LaunchOptions().setHeadless(headless)
        );

        context = browser.newContext();
        page = context.newPage();
    }

    public Page getPage() {
        return page;

    }

    public void closeBrowser() {
        if (page != null) {
            page.close();
        }

        if (context != null) {
            context.close();
        }

        if (browser != null) {
            browser.close();
        }

        if (playwright != null) {
            playwright.close();
        }
    }

}