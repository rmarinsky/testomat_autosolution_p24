package io.testomat.testSuite;

import com.github.javafaker.Faker;
import io.testomat.BaseTests;
import io.testomat.common.Application;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CreateTestSuitePOTests extends BaseTests {

    private final Faker faker = new Faker();
    private final Application application = new Application();

    @Test
    @DisplayName("Create test case test")
    void createFirstTestCaseTest() {
        var projectName = faker.company().name();
        var suiteName = faker.company().industry();
        String mail = "newromka@gmail.com";
        String password = "sLdKk28@RJ@eBPr";

        application.homePage()
                .openHomePage()
                .isLoaded()
                .openSignInPage();

        application.loginPage()
                .loginUser(mail, password);

        application.projectsListPage()
                .isLoaded()
                .openCreateProjectPage();

        application.projectPage()
                .isLoaded()
                .createProject(projectName)
                .onboardingPopUpShouldBeVisibleAndClose()
                .createTestSuite(suiteName);
    }

}
