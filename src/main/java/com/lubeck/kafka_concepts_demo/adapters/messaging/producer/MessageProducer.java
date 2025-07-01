package com.lubeck.kafka_concepts_demo.adapters.messaging.producer;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class MessageProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private static final String TOPIC = "my-topic";

    public MessageProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(String key, String message) {
        CompletableFuture<SendResult<String, String>> future = kafkaTemplate.send(TOPIC, key, message);

        future.thenAccept(result -> {
            System.out.println("Message successfully sent to partition: " +
                    result.getRecordMetadata().partition());
        }).exceptionally(ex -> {
            System.err.println("Failed to send message: " + ex.getMessage());
            return null;
        });
    }
}