package com.example.kafkanativeserialization;

import com.salesforce.kafka.test.junit5.SharedKafkaTestResource;
import org.junit.jupiter.api.extension.RegisterExtension;

public class BaseTest {

    @RegisterExtension
    public static final SharedKafkaTestResource sharedKafkaTestResource = new SharedKafkaTestResource()
            .withBrokerProperty("auto.create.topics.enable", "false")
            .withBrokerProperty("message.max.bytes", "512000");
}
