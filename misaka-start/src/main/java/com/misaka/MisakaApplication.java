package com.misaka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class MisakaApplication {

    public static void main(String[] args) {
        SpringApplication.run(MisakaApplication.class, args);
    }

}
