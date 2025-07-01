package com.lubeck.kafka_concepts_demo.adapters.web.controller;

import com.lubeck.kafka_concepts_demo.adapters.web.dto.MessageRequestDTO;
import com.lubeck.kafka_concepts_demo.adapters.messaging.producer.MessageProducer;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/messages")
public class MessageController {

    private final MessageProducer producer;

    public MessageController(MessageProducer producer) {
        this.producer = producer;
    }

    @PostMapping
    public String send(@RequestBody MessageRequestDTO request) {
        producer.sendMessage(request.key(), request.message());
        return "Message sent to Kafka!";
    }
}