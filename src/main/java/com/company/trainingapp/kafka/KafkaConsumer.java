package com.company.trainingapp.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {

    @KafkaListener(topics = "training_topic", groupId = "myGroup")
    public void consume(String message) {
        System.out.println("Consumed message: " + message);
    }
}