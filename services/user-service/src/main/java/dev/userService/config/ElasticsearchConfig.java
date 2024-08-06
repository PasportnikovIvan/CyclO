package dev.userService.config;

import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.RestClients;
/**
 * Configuration class for setting up Elasticsearch.
 */
@Configuration
public class ElasticsearchConfig {

    /**
     * Creates the {@link RestHighLevelClient} bean.
     *
     * @return the {@link RestHighLevelClient}
     */
    @Bean
    public RestHighLevelClient client() {
        ClientConfiguration clientConfiguration = ClientConfiguration.builder()
                .connectedTo("localhost:9200")
                .build();
        return RestClients.create(clientConfiguration).rest();
    }
}
