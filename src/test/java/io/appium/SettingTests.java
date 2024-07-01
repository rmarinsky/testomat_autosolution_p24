package io.appium;

import io.appium.screens.setting.ISettingScreen.SettingScreen;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class SettingTests extends BaseTest {


    @Test
    @DisplayName("open setting and check about screen test")
    void openSettingAndCheckAboutScreenTest() {
        var targetQuery = platform() == DriverProvider.Platform.ANDROID ? "General" : "Про пристрій";

        new SettingScreen()
                .searchFor(targetQuery)
                .searchResultHas(targetQuery);
    }

}
