package com.lubeck.kafka_concepts_demo.adapters.messaging.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.Consumer;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumer {

    @KafkaListener(topics = "my-topic", containerFactory = "manualAckKafkaListenerContainerFactory")
    public void consume(ConsumerRecord<String, String> record, Acknowledgment ack, Consumer<?, ?> consumer) {
        try {
            System.out.println("Message received: " + record.value());

            processMessage(record.value());

            ack.acknowledge();
            System.out.println("Offset confirmed!");
        } catch (Exception e) {
            System.err.println("Error processing: " + e.getMessage());
        }
    }

    private void processMessage(String msg) {
        System.out.println("Processing: " + msg);
    }
}