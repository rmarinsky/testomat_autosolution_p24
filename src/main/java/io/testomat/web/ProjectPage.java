package io.testomat.web;

import com.codeborne.selenide.Selectors;
import com.codeborne.selenide.SelenideElement;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class ProjectPage {

    private final SelenideElement projectTitleInput = $("#project_title");

    public ProjectPage isLoaded() {
        projectTitleInput.shouldBe(visible, Duration.ofSeconds(10));
        $(Selectors.by("src*", "user_avatar")).shouldBe(visible);
        return this;
    }

    public ProjectPage createProject(String projectName) {
        projectTitleInput.val(projectName);
        $("#project-create-btn input").click();
        return this;
    }

    public ProjectPage onboardingPopUpShouldBeVisibleAndClose() {
        $(".back").shouldBe(visible, Duration.ofSeconds(10)).click();
        return this;
    }

    public ProjectPage createTestSuite(String suiteName) {
        $("[placeholder='First Suite']")
                .val(suiteName)
                .pressEnter();

        $(byText(suiteName)).shouldBe(visible, Duration.ofSeconds(10));
        return this;
    }

}
