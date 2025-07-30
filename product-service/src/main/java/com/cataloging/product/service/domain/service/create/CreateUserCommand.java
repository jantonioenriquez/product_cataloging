package com.cataloging.product.service.domain.service.create;

import com.cataloging.valueObject.UserStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

/**
 * Object command for convert from request to user model
 */
@Getter
@Builder
@AllArgsConstructor
public class CreateUserCommand {
    private String id;
    private String auth0Id;
    private String email;
    private UserStatus status;
}
