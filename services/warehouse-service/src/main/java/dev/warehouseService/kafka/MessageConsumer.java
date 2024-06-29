package dev.warehouseService.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class MessageConsumer {

    @KafkaListener(topics = "rental-topic", groupId = "warehouse-service-group")
    public void listenForRentalUpdates(String message) {
        System.out.println("Received message from rental service: " + message);
    }
}