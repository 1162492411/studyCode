package com.study.jackson;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;


/**
 * <p>
 * 启动器
 * </p>
 */
@EnableAsync
@SpringBootApplication
public class JacksonApplication {

    public static void main(String[] args) {
        SpringApplication.run(JacksonApplication.class, args);
    }

}