package com.cataloging.product.service.domain.service.dto.message;

import com.cataloging.valueObject.UserStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

/**
 * Class model for process messages from microservice user management
 */
@Getter
@Builder
@AllArgsConstructor
public class UserModel {
    private String id;
    private String auth0Id;
    private String email;
    private List<String> profiles;
    private UserStatus status;
}
