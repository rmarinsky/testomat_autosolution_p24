package io.testomat.web;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class ProjectsListPage {


    public ProjectsListPage isLoaded() {
        $("#user-menu-button img[src*=user_avatar]").shouldBe(visible, Duration.ofSeconds(10000));
        return this;
    }

    public void openCreateProjectPage() {
        $("[href='/projects/new']").click();
    }
}
