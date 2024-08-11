package dev.rentalService.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
/**
 * Kafka message producer for the rental service application.
 * Provides methods to send messages to specified Kafka topics.
 */
@Service
public class MessageProducer {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    /**
     * Constructs a new MessageProducer with the specified KafkaTemplate.
     *
     * @param kafkaTemplate the KafkaTemplate to be used for sending messages
     */
    public MessageProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }
    /**
     * Sends a message to the specified Kafka topic.
     *
     * @param topic the topic to which the message will be sent
     * @param message the message to be sent
     */
    public void sendMessage(String topic, String message) {
        kafkaTemplate.send(topic, message);
    }
}
