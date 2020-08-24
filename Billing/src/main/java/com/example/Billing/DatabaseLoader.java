package com.example.Billing;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class DatabaseLoader {

    private static final Logger log = LoggerFactory.getLogger(DatabaseLoader.class);

    @Bean
    CommandLineRunner initDatabase(BillRepository billRepository) {

        return args -> {
            log.info("Preloading " + billRepository.save(new Bill("11111111-22222222", "HUF", 150000.0)));
            log.info("Preloading " + billRepository.save(new Bill("22222222-33333333", "USD", 1230.0)));
            log.info("Preloading " + billRepository.save(new Bill("33333333-44444444", "USD", 0.0)));
            log.info("Preloading " + billRepository.save(new Bill("44444444-55555555", "HUF", 0.0)));
            log.info("Preloading " + billRepository.save(new Bill("55555555-66666666", "CAD", 0.0)));
        };
    }
}
