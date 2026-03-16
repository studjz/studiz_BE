package com.example.studiz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.example.studiz.domain") // JPA 리포지토리가 있는 패키지
@EnableRedisRepositories(basePackages = "com.example.studiz.global") // Redis용 패키지 (만약 쓴다면)
public class StudizApplication {

    public static void main(String[] args) {
        SpringApplication.run(StudizApplication.class, args);
    }

}
