package io.appium.screens.setting;

import com.codeborne.selenide.WebDriverRunner;
import io.appium.java_client.android.AndroidDriver;

public interface ISettingScreen {

    ISettingScreen searchFor(String text);

    ISettingScreen searchResultHas(String text);

    class SettingScreen implements ISettingScreen {

        private final ISettingScreen strategy;

        public SettingScreen() {
            var webDriver = WebDriverRunner.getWebDriver();
            strategy = (webDriver instanceof AndroidDriver) ? new AndroidSettings() : new IosSetting();
        }

        @Override
        public ISettingScreen searchFor(String text) {
            return strategy.searchFor(text);
        }

        @Override
        public ISettingScreen searchResultHas(String text) {
            return strategy.searchResultHas(text);
        }
    }

}
