package com.ogawalucas.activity04client;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

@EnableFeignClients
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public CommandLineRunner sum(SumService sumService) {
        return arg -> {
            System.out.println("\n========== Activity 04 ==========");

            System.out.println("2 + 2 = " + sumService.sum(2, 2));

            System.out.println("=================================\n");
        };
    }
}
