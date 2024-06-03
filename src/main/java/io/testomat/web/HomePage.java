package io.testomat.web;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class HomePage extends BasePage<HomePage> {

    public HomePage openHomePage() {
        open("/");
        return this;
    }

    public HomePage isLoaded() {
        $("#mainSlider-mw [src*='Real_time']").shouldBe(visible);
        //$("#mainSlider-mw").$(Selectors.by("src*", "Real_time")).shouldBe(visible);
        return this;
    }

    public void openSignInPage() {
        $("[href*='users/sign_in']").click();
    }
}
