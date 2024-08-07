package dev.rentalService.config;

import dev.rentalService.kafka.MessageConsumer;
import dev.rentalService.kafka.MessageProducer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.annotation.EnableKafka;
/**
 * Configuration class for the rental service application.
 * This class is responsible for setting up the Kafka template and
 * configuring the message producer and consumer beans.
 */
@Configuration
@EnableKafka
public class AppConfig {
    /**
     * Creates a KafkaTemplate bean used to send messages to Kafka topics.
     *
     * @param producerFactory the producer factory used to create Kafka producers
     * @return a KafkaTemplate instance
     */
    @Bean
    public KafkaTemplate<String, String> kafkaTemplate(ProducerFactory<String, String> producerFactory) {
        return new KafkaTemplate<>(producerFactory);
    }

    /**
     * Creates a MessageProducer bean used to produce messages to Kafka topics.
     *
     * @param kafkaTemplate the KafkaTemplate used to send messages
     * @return a MessageProducer instance
     */
    @Bean
    public MessageProducer messageProducer(KafkaTemplate<String, String> kafkaTemplate) {
        return new MessageProducer(kafkaTemplate);
    }

    /**
     * Creates a MessageConsumer bean used to consume messages from Kafka topics.
     *
     * @return a MessageConsumer instance
     */
    @Bean
    public MessageConsumer messageConsumer() {
        return new MessageConsumer();
    }
}

