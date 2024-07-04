package io.appium.remote;

import com.codeborne.selenide.WebDriverProvider;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class AndroidDriverForApiDemos implements WebDriverProvider {


    public static String userName, accessKey;
    public static Map<String, Object> browserStackYamlMap;

    public AndroidDriverForApiDemos() {
        File file = new File("./browserstack.yml");
        browserStackYamlMap = convertYamlFileToMap(file, new HashMap<>());
        userName = (String) browserStackYamlMap.get("userName");
        accessKey = (String) browserStackYamlMap.get("accessKey");
    }

    private Map<String, Object> convertYamlFileToMap(File yamlFile, Map<String, Object> map) {
        try {
            InputStream inputStream = Files.newInputStream(yamlFile.toPath());
            Yaml yaml = new Yaml();
            Map<String, Object> config = yaml.load(inputStream);
            map.putAll(config);
        } catch (Exception e) {
            throw new RuntimeException(String.format("Malformed browserstack.yml file - %s.", e));
        }
        return map;
    }


    @Override
    public WebDriver createDriver(Capabilities capabilities) {
        UiAutomator2Options options = new UiAutomator2Options();
        options.merge(capabilities);
        options.setNewCommandTimeout(Duration.ofSeconds(10));

        try {
            return new AndroidDriver(new URL("https://" + userName + ":" + accessKey + "@hub-cloud.browserstack.com/wd/hub"), options);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }
}
