package io.testomat.web.common;

import io.testomat.web.HomePage;
import io.testomat.web.LoginPage;
import io.testomat.web.ProjectPage;
import io.testomat.web.ProjectsListPage;
import io.testomat.web.common.data.UserDto;

public class Application {

    public HomePage homePage() {
        return new HomePage();
    }

    public LoginPage loginPage(UserDto userDto) {
        return new LoginPage(userDto);
    }

    public ProjectsListPage projectsListPage() {
        return new ProjectsListPage();
    }

    public ProjectPage projectPage() {
        return new ProjectPage();
    }

}
