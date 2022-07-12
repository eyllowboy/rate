package com.example.rate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class RateApplication {

    public static void main(String[] args) {
        SpringApplication.run(RateApplication.class, args);
    }

}
