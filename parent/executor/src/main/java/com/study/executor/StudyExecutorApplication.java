package com.study.executor;

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
public class StudyExecutorApplication {

    public static void main(String[] args) {
        SpringApplication.run(StudyExecutorApplication.class, args);
    }

}

