package io.appium.screens.setting;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.appium.AppiumScrollOptions;
import io.appium.java_client.AppiumBy;

import static com.codeborne.selenide.appium.SelenideAppium.$;
import static io.appium.java_client.AppiumBy.iOSClassChain;

public class IosSetting implements ISettingScreen {

    public IosSetting swipeToSearch() {
        $(AppiumBy.iOSNsPredicateString("name == \"Пошук\"")).scroll(AppiumScrollOptions.up());
        return this;
    }

    public IosSetting searchFor(String text) {
        swipeToSearch();
        $(AppiumBy.iOSNsPredicateString("name == \"Пошук\"")).val(text);
        return this;
    }

    public IosSetting searchResultHas(String text) {
        $(iOSClassChain("**/XCUIElementTypeCollectionView/XCUIElementTypeCell"))
                .shouldHave(Condition.partialText(text));
        return this;
    }

}
