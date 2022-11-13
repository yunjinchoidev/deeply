package com.server.deeply;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class DeeplyApplication {

    public static void main(String[] args) {
        SpringApplication.run(DeeplyApplication.class, args);
    }

}
