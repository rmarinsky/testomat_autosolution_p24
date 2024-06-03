package io.testomat.common;

import io.testomat.web.HomePage;
import io.testomat.web.LoginPage;
import io.testomat.web.ProjectPage;
import io.testomat.web.ProjectsListPage;

public class Application {

    public HomePage homePage() {
        return new HomePage();
    }

    public LoginPage loginPage() {
        return new LoginPage();
    }

    public ProjectsListPage projectsListPage() {
        return new ProjectsListPage();
    }

    public ProjectPage projectPage() {
        return new ProjectPage();
    }

}
