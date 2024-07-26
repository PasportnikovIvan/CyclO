package dev.warehouseService.kafka;

import dev.shared.CustomLoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class MessageProducer {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    private static final Logger logger = CustomLoggerFactory.getLogger(MessageProducer.class);

    public void sendMessage(String topic, String message) {
        kafkaTemplate.send(topic, message);
        logger.info("Sent message to Kafka topic {}: {}", topic, message);
    }
}
