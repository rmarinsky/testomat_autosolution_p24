package com.handlers;

import com.sivalabs.demo.ProductPriceChangedEvent;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.test.utils.KafkaTestUtils;

import java.math.BigDecimal;
import java.util.Map;

public class ProductTopicHandler {

    private final KafkaTemplate<String, BigDecimal> kafkaTemplate;

    private ProductTopicHandler() {
        Map<String, Object> senderProps = KafkaTestUtils.producerProps("localhost:9092");
        senderProps.put("value.serializer", StringSerializer.class);
        DefaultKafkaProducerFactory<String, BigDecimal> pf = new DefaultKafkaProducerFactory<>(senderProps);
        kafkaTemplate = new KafkaTemplate<>(pf, true);
    }

    public static ProductTopicHandler get() {
        return LazyHolder.instance;
    }

    public void sendProductPriceChangeMessage(ProductPriceChangedEvent product) {
        kafkaTemplate.send("product-price-changes", product.getProductCode(), product.getPrice());
    }

    private static class LazyHolder {
        static final ProductTopicHandler instance = new ProductTopicHandler();
    }

}
