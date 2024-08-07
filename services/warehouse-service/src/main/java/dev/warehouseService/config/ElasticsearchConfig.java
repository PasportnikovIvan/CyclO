package dev.warehouseService.config;

import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.RestClients;

/**
 * Configuration class for Elasticsearch.
 * This class is responsible for setting up the Elasticsearch client.
 */
@Configuration
public class ElasticsearchConfig {

    /**
     * Creates a RestHighLevelClient bean used to interact with Elasticsearch.
     *
     * @return a RestHighLevelClient instance
     */
    @Bean
    public RestHighLevelClient client() {
        ClientConfiguration clientConfiguration = ClientConfiguration.builder()
                .connectedTo("localhost:9200")
                .build();
        return RestClients.create(clientConfiguration).rest();
    }
}