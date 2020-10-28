package com.yogurt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication//(exclude = { SecurityAutoConfiguration.class })
public class YogurtApplication {

    public static void main(String[] args) {
        SpringApplication.run(YogurtApplication.class, args);
    }
}
