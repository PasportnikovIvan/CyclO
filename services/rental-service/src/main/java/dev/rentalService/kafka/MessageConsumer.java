package dev.rentalService.kafka;

import shared.CustomLoggerFactory;
import org.slf4j.Logger;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class MessageConsumer {
    private static final Logger logger = CustomLoggerFactory.getLogger(MessageConsumer.class);

    @KafkaListener(topics = "warehouse-topic", groupId = "rental-service-group")
    public void listenForWarehouseUpdates(String message) {
        logger.info("Received message from warehouse: {}", message);
    }
}
