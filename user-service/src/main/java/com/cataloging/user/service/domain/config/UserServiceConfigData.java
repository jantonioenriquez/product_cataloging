package com.cataloging.user.service.domain.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "user-service")
public class UserServiceConfigData {

    @Value("${service.topic.post.users.user}")
    private String userCreateTopicName;

    @Value("${service.topic.put.users.user}")
    private String userUpdateTopicName;

    @Value("${service.topic.delete.users.user}")
    private String userDeleteTopicName;
}
