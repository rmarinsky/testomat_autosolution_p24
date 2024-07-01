package io.appium;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.appium.SelenideAppium;
import io.appium.java_client.AppiumBy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.appium.SelenideAppium.$;
import static io.appium.DriverProvider.Platform.IOS;
import static io.appium.DriverProvider.Platform.valueOf;

public class PoCTest {

    public void platform(DriverProvider.Platform platform) {
        System.setProperty("platform", platform.name());
    }

    public DriverProvider.Platform platform() {
        return valueOf(System.getProperty("platform"));
    }

    @BeforeEach
    public void setUp() {
        platform(IOS);

        Configuration.browser = DriverProvider.class.getName();
        SelenideAppium.launchApp();
    }


    @Test
    @DisplayName("first test for android")
    void firstTestForAndroid() {
        $(AppiumBy.id("search_bar")).shouldBe(visible).click();
        $(AppiumBy.id("com.google.android.settings.intelligence:id/open_search_view_edit_text"))
                .shouldBe(visible)
                .setValue("Система");
        $(AppiumBy.id("android:id/title")).shouldHave(Condition.text("Система"));
    }

    @Test
    void rawIos() {
        $(AppiumBy.iOSNsPredicateString("name == 'General'")).shouldBe(visible).click();
        WebDriverRunner.getWebDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(0));

        WebDriverRunner.getWebDriver().findElement(AppiumBy.iOSNsPredicateString("name=='Fonts'")).click();

        WebDriverRunner.getWebDriver().findElement(AppiumBy.iOSNsPredicateString("name=='No Fonts Installed'")).click();
    }


}
