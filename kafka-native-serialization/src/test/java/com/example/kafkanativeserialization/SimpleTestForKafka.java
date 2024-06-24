package com.example.kafkanativeserialization;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.awaitility.core.ConditionTimeoutException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicReference;

import static org.awaitility.Awaitility.await;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SimpleTestForKafka extends BaseTest {

    String topic = "demo";
    String key = UUID.randomUUID().toString();
    String value = "value" + UUID.randomUUID();

    @BeforeEach
    void beforeEach() {
        sharedKafkaTestResource.getKafkaTestUtils().createTopic(topic, 2, (short) 1);
    }

    @Test
    public void testSimpleProducer() throws ExecutionException, InterruptedException, TimeoutException {
        KafkaProducer<Object, Object> producer = new KafkaProducer<>(getProducerProperties());

        final RecordMetadata recordMetadata = producer.send(new ProducerRecord<>(topic, key, value))
                .get(3, TimeUnit.SECONDS);

        assertTrue(recordMetadata.hasOffset());
        assertTrue(recordMetadata.hasTimestamp());
    }

    @Test
    public void testSimpleConsumer() throws ExecutionException, InterruptedException, TimeoutException {
        final KafkaConsumer<String, String> consumer = new KafkaConsumer<>(getConsumerProperties());
        consumer.subscribe(Collections.singletonList(topic));

        final KafkaProducer<String, String> producer = new KafkaProducer<>(getProducerProperties());
        Future<RecordMetadata> future = producer.send(new ProducerRecord<>(topic, key, value));
        RecordMetadata metadata = future.get(1, TimeUnit.SECONDS); // Ensure the message is sent

        Assertions.assertNotNull(metadata, "Producer failed to send message");

        final ArrayList<String> values = new ArrayList<>();

        ConsumerRecord<String, String> record = waitForRecord(consumer, value);
        Assertions.assertNotNull(record);
        System.out.println("record = " + record);
    }

    private ConsumerRecord<String, String> waitForRecord(KafkaConsumer<String, String> consumer, String message) {
        AtomicReference<ConsumerRecord<String, String>> atomicRecord = new AtomicReference<>();
        await().ignoreException(ConditionTimeoutException.class).atMost(10, TimeUnit.SECONDS).until(() -> {
            ConsumerRecords<String, String> records = consumer.poll(Duration.ofSeconds(1));
            for (ConsumerRecord<String, String> record : records) {
                System.out.println(record);
                if (record.value().equals(message)) {
                    atomicRecord.set(record);
                    return true;
                }
            }
            return false;
        });
        return Optional.of(atomicRecord.get()).orElseThrow(NullPointerException::new);
    }

    private Properties getProducerProperties() {
        final Properties properties = new Properties();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, sharedKafkaTestResource.getKafkaConnectString());
        properties.put(ProducerConfig.CLIENT_ID_CONFIG, UUID.randomUUID().toString());
        properties.put(ProducerConfig.ACKS_CONFIG, "all");
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        return properties;
    }

    private Properties getConsumerProperties() {
        final Properties properties = new Properties();
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, sharedKafkaTestResource.getKafkaConnectString());
        properties.put(ConsumerConfig.CLIENT_ID_CONFIG, "client0");
        properties.put(ConsumerConfig.GROUP_ID_CONFIG, "group0");
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        properties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        return properties;
    }

}
