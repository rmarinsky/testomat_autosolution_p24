package io.appium;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.appium.SelenideAppium;
import org.junit.jupiter.api.BeforeEach;

import static io.appium.DriverProvider.Platform.IOS;
import static io.appium.DriverProvider.Platform.valueOf;

public class BaseTest {

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

}
