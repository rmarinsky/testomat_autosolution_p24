package io.appium;

import com.codeborne.selenide.WebDriverProvider;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;
import lombok.SneakyThrows;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class DriverProvider implements WebDriverProvider {

    @SneakyThrows
    public WebDriver createDriver(Capabilities capabilities) {
        String platform = System.getProperty("platform");
        if (Platform.ANDROID.name().equals(platform)) {
            return getAndroidDriver();
        } else if (Platform.IOS.name().equals(platform)) {
            return getIOSDriver();
        } else {
            throw new IllegalArgumentException("Unknown platform: " + platform);
        }
    }

    private AndroidDriver getAndroidDriver() throws MalformedURLException {
        var options = new UiAutomator2Options()
                .setPlatformName("Android")
                .setPlatformVersion("13")
                .setDeviceName("emulator-5554")
                .setAppPackage("com.android.settings") // Set the package name of the Clock app
                .setAppActivity("com.android.settings.Settings") // Set the main activity of the Clock app
                .setNoReset(false);

        var driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), options);

        driver.activateApp("com.android.settings");

        return driver;
    }

    private IOSDriver getIOSDriver() throws MalformedURLException {
        XCUITestOptions options = new XCUITestOptions()
                .setPlatformName("iOS")
                .setPlatformVersion("17.2")
                .setDeviceName("iPhone 15 Pro")
                .setApp("com.apple.Preferences") // Set the bundle ID of the Clock app
                .setNoReset(false);

        IOSDriver iosDriver = new IOSDriver(new URL("http://127.0.0.1:4723/"), options);
        iosDriver.activateApp("com.apple.Preferences"); // Activate the Clock app
        return iosDriver;
    }

    public enum Platform {
        ANDROID,
        IOS
    }

}
