package io.testomat.web.pw;

import com.github.javafaker.Faker;
import com.microsoft.playwright.*;
import com.microsoft.playwright.assertions.LocatorAssertions;
import org.junit.jupiter.api.*;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class CreateTestSuiteTestsPW {

    static Playwright playwright;
    static Browser browser;

    Faker faker = new Faker();

    // New instance for each test method.
    BrowserContext context;
    Page page;

    @BeforeAll
    static void launchBrowser() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions()
                .setHeadless(false)
                .setSlowMo(0)
        );
    }

    @AfterAll
    static void closeBrowser() {
        playwright.close();
    }

    @Test
    @DisplayName("first test on playwright")
    void firstTestOnPlaywrigh() {
        context = browser.newContext();
        page = context.newPage();

        page.navigate("https://app.testomat.io/users/sign_in");
        assertThat(page.locator("#content-desktop #new_user")).isVisible(
                new LocatorAssertions.IsVisibleOptions().setTimeout(20000));

        page.locator("#content-desktop #user_email").first().fill("newromka@gmail.com");
        var passwordField = page.locator("#content-desktop #user_password");
        passwordField.fill("sLdKk28@RJ@eBPr");
        passwordField.press("Enter");

        isHidden(passwordField);

        page.navigate("https://app.testomat.io/projects/new");

        page.locator("#project_title").fill(faker.book().title());

        page.locator("[name='commit']").click();

        page.locator(".back").click();

        var targetTestSuiteName = faker.commerce().department();
        var firstTestSuiteFiled = page.locator("[placeholder='First Suite']");
        firstTestSuiteFiled.fill(targetTestSuiteName);
        firstTestSuiteFiled.press("Enter");

        assertThat(page.locator((".list-group-wrapper .dragSortItem a span"))).hasText(targetTestSuiteName);
    }

    public void isHidden(Locator locator) {
        assertThat(locator).isHidden(new LocatorAssertions.IsHiddenOptions().setTimeout(10000));
    }


    @AfterEach
    void closeContext() {
        context.close();
    }

}
