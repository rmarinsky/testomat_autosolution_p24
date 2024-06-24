package com.sivalabs.demo;

import com.handlers.ProductTopicHandler;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Random;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.fail;

public class E2ETest {

    @Test
    @DisplayName("E2E test for topic handler")
    void e2ETestForTopicHandler() {

        var productPriceChangedEvent = new ProductPriceChangedEvent(
                UUID.randomUUID().toString(), BigDecimal.valueOf(new Random().nextDouble()));

        ProductTopicHandler.get()
                .sendProductPriceChangeMessage(productPriceChangedEvent);

        fail("Not implemented yet");
    }

}
