package com.user_order_management_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class Main {
    public static void main(String[] args) {

        SpringApplication.run(Main.class, args);
        System.out.println("Spring boot App Started Successfully.....");
    }
}