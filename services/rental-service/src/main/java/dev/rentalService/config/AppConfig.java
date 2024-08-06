package dev.rentalService.config;

import dev.rentalService.kafka.MessageConsumer;
import dev.rentalService.kafka.MessageProducer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.annotation.EnableKafka;

@Configuration
@EnableKafka
public class AppConfig {

    @Bean
    public KafkaTemplate<String, String> kafkaTemplate(ProducerFactory<String, String> producerFactory) {
        return new KafkaTemplate<>(producerFactory);
    }

    @Bean
    public MessageProducer messageProducer(KafkaTemplate<String, String> kafkaTemplate) {
        return new MessageProducer(kafkaTemplate);
    }

    @Bean
    public MessageConsumer messageConsumer() {
        return new MessageConsumer();
    }
}

