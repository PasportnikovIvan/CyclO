package dev.bikeService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
/**
 * Main class for the Bike Service application. Configures and starts the Spring Boot application.
 */
@SpringBootApplication
public class BikeServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(BikeServiceApplication.class, args);
    }
}