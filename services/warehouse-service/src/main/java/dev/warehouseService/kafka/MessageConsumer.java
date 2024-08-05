package dev.warehouseService.kafka;

import org.slf4j.Logger;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import shared.CustomLoggerFactory;

@Component
public class MessageConsumer {

    private static final Logger logger = CustomLoggerFactory.getLogger(MessageConsumer.class);

    @KafkaListener(topics = "rental-topic", groupId = "warehouse-service-group")
    public void listenForRentalUpdates(String message) {
        logger.info("Received message from rental service: {}", message);
    }
}