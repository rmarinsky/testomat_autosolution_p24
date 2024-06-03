package io.testomat;

import com.github.javafaker.Faker;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class CreateTestCaseTests {

    private final Faker faker = new Faker();

    private static void createTestSuite(String suiteName) {
        $("[placeholder='First Suite']")
                .val(suiteName)
                .pressEnter();
    }

    private static void closeOnboardingPopUp() {
        $(".back").shouldBe(visible, Duration.ofSeconds(10000)).click();
    }

    private static void createProject(String companyName) {
        $("#project_title")
                .shouldBe(visible, Duration.ofSeconds(10000))
                .val(companyName);
        $("#project-create-btn input").click();
    }

    private static void projectsPageIsLoaded() {
        $("#user-menu-button img[src*=user_avatar]").shouldBe(visible, Duration.ofSeconds(10000));
    }

    private static void loginUser(String mail, String password) {
        $("#content-desktop #user_email").val(mail);
        $("#content-desktop #user_password").val(password);
        $("#content-desktop [name='commit']").click();
    }

    @Test
    @DisplayName("Create test case test")
    void createFirstTestCaseTest() {
        var projectName = faker.company().name();
        var suiteName = faker.company().industry();
        String mail = "newromka@gmail.com";
        String password = "sLdKk28@RJ@eBPr";

        open("https://testomat.io");
        $("[href*='users/sign_in']").click();
        loginUser(mail, password);
        projectsPageIsLoaded();
        $("[href='/projects/new']").click();
        createProject(projectName);
        closeOnboardingPopUp();

        createTestSuite(suiteName);

        $(byText(suiteName)).shouldBe(visible, Duration.ofSeconds(10000));
    }

}
