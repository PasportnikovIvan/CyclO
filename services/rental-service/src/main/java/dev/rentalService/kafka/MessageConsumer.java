package dev.rentalService.kafka;

import shared.CustomLoggerFactory;
import org.slf4j.Logger;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * Kafka message consumer for the rental service application.
 * Listens for messages on specified Kafka topics and processes them.
 */
@Component
public class MessageConsumer {
    private static final Logger logger = CustomLoggerFactory.getLogger(MessageConsumer.class);
    
    /**
     * Listens for messages on the "warehouse-topic" Kafka topic and logs the received message.
     *
     * @param message the message received from the Kafka topic
     */
    @KafkaListener(topics = "warehouse-topic", groupId = "rental-service-group")
    public void listenForWarehouseUpdates(String message) {
        logger.info("Received message from warehouse: {}", message);
    }
}
