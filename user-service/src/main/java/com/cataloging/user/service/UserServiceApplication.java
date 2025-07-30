package com.cataloging.user.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@EnableMongoRepositories(basePackages = {"com.cataloging.user.service.dataaccess", "com.cataloging.dataaccess"})
@EnableMongoAuditing
@EntityScan(basePackages = {"com.cataloging.user.service.dataaccess", "com.cataloging.dataaccess"})
@SpringBootApplication(scanBasePackages = "com.cataloging", exclude = {DataSourceAutoConfiguration.class})
@Slf4j
public class UserServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(UserServiceApplication.class, args);
    }

}
