package com.sivalabs.demo;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.TestPropertySource;
import org.testcontainers.containers.KafkaContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import java.math.BigDecimal;
import java.util.Optional;

import static com.sivalabs.demo.Awaits.await10SecondPollEverySecond;
import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles("it")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(properties = {
        "spring.datasource.url=jdbc:tc:postgresql:15.2-alpine:///db?TC_INITSCRIPT=sql/schema.sql"
})
@Testcontainers
@Slf4j
class ProductPriceChangedEventHandlerTest {

    @Container
    static final KafkaContainer kafka =
            new KafkaContainer(DockerImageName.parse("confluentinc/cp-kafka:7.2.1"));
    String targetProductCode = "P100";
    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    @Autowired
    private ProductRepository productRepository;

    @DynamicPropertySource
    static void overridePropertiesInternal(DynamicPropertyRegistry registry) {
        registry.add("spring.kafka.bootstrap-servers", kafka::getBootstrapServers);
    }

    @BeforeEach
    void setUp() {
        Product product = new Product(null, targetProductCode, "Product One", BigDecimal.TEN);
        productRepository.save(product);
    }

    @Test
    void shouldHandleProductPriceChangedEvent() {
        var targetPrice = new BigDecimal("14.50");
        ProductPriceChangedEvent event =
                new ProductPriceChangedEvent(targetProductCode, targetPrice);

        log.info("Publishing ProductPriceChangedEvent with ProductCode: {}", event.getProductCode());
        kafkaTemplate.send("product-price-changes", event.getProductCode(), event);

        await10SecondPollEverySecond.untilAsserted(() -> {
            Optional<Product> optionalProduct = productRepository.findByCode(targetProductCode);
            assertThat(optionalProduct).isPresent();
            assertThat(optionalProduct.get().getCode()).isEqualTo(targetProductCode);
            assertThat(optionalProduct.get().getPrice()).isEqualTo(targetPrice);
        });
    }
}
