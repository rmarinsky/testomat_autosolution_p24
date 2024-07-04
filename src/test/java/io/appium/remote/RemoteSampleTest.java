package io.appium.remote;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.appium.SelenideAppium;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.hidden;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class RemoteSampleTest {


    @Test
    @DisplayName("Open testomat.io")
    void testName() {
        Configuration.baseUrl = "https://testomat.io";
        Configuration.remoteConnectionTimeout = 30000;
        Configuration.remoteReadTimeout = 60000;
        Configuration.pollingInterval = 500;
        SelenideAppium.launchApp();
        open("https://testomat.io/");
        $("[href*='users/sign_in']").click();
        $("[href*='users/sign_in']").shouldBe(hidden);
    }

}
