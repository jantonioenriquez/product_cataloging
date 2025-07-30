package com.cataloging.product.service.domain.service.update;

import com.cataloging.valueObject.UserStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class UpdateUserCommand {
    private String id;
    private String auth0Id;
    private String email;
    private List<String> profiles;
    private UserStatus status;
}
