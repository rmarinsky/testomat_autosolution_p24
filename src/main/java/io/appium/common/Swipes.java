package io.appium.common;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.interactions.Pause;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.time.Duration;
import java.util.List;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class Swipes {

    public void swipe(Direction direction) {
        RemoteWebDriver webDriver = (RemoteWebDriver) getWebDriver();
        Dimension size = webDriver.manage().window().getSize();
        int startX = size.getWidth() / 2;
        int startY = size.getHeight() / 2;
        int endX = startX;

        int endY = switch (direction) {
            case UP -> (int) (size.getHeight() * 0.25);
            case DOWN -> size.getHeight();
            default -> throw new IllegalStateException("Unexpected value: " + direction);
        };

        PointerInput finger1 = new PointerInput(PointerInput.Kind.TOUCH, "finger1");
        Sequence sequence = new Sequence(finger1, 1)
                .addAction(finger1.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), startX, startY))
                .addAction(finger1.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
                .addAction(new Pause(finger1, Duration.ofMillis(200)))
                .addAction(
                        finger1.createPointerMove(
                                Duration.ofMillis(100), PointerInput.Origin.viewport(), endX, endY
                        )
                )
                .addAction(finger1.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        webDriver.perform(List.of(sequence));
    }

    public enum Direction {
        UP, DOWN
    }

}
