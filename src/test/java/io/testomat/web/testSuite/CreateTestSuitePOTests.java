package io.testomat.web.testSuite;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.junit5.TextReportExtension;
import com.github.javafaker.Faker;
import io.testomat.common.Roles;
import io.testomat.configs.TestomatEnvs;
import io.testomat.web.BaseTests;
import io.testomat.web.common.Application;
import io.testomat.web.common.data.UserDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;


@ExtendWith(TextReportExtension.class)
public class CreateTestSuitePOTests extends BaseTests {


    private final Faker faker = new Faker();
    private final Application application = new Application();

    static {
        Configuration.baseUrl = TestomatEnvs.BETA.domain;
    }

    @Test
    @DisplayName("Create test case test")
    void createFirstTestCaseTest() {
        var projectName = faker.company().name();
        var suiteName = faker.company().industry();

        var targetUser = new UserDto("newromka@gmail.com", "sLdKk28@RJ@eBPr", true);

        application.homePage()
                .openHomePage()
                .isLoaded()
                .openSignInPage();

        application.loginPage(targetUser)
                .loginUser();

        application.projectsListPage()
                .isLoaded()
                .openCreateProjectPage();

        application.projectPage()
                .isLoaded()
                .createProject(projectName)
                .onboardingPopUpShouldBeVisibleAndClose()
                .createTestSuite(suiteName);
    }

    @Test
    @DisplayName("Create test case test by role")
    void justForExmplaLoginByRole() {
        application.homePage()
                .openHomePage()
                .isLoaded()
                .openSignInPage();

        application.loginPage(null)
                .loginRole(Roles.ADMIN);
    }

}
