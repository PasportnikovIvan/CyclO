package dev.rentalService.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class MessageConsumer {

    @KafkaListener(topics = "warehouse-topic", groupId = "rental-service-group")
    public void listenForWarehouseUpdates(String message) {
        System.out.println("Received message from warehouse: " + message);
    }
}
