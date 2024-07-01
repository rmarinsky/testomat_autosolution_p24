package io.appium.screens.setting;

import com.codeborne.selenide.Condition;

import static com.codeborne.selenide.appium.SelenideAppium.$;
import static io.appium.java_client.AppiumBy.id;

public class AndroidSettings implements ISettingScreen {
    @Override
    public ISettingScreen searchFor(String text) {
        $(id("search_bar")).click();
        $(id("com.google.android.settings.intelligence:id/open_search_view_edit_text"))
                .shouldBe(Condition.visible)
                .val(text);
        return this;
    }

    @Override
    public ISettingScreen searchResultHas(String expectedText) {
        $(id("android:id/title")).shouldHave(Condition.text(expectedText));
        return null;
    }
}
