package com.cataloging.product.service.domain.service.delete;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class DeleteUserCommand {
    private String id;
    private String auth0Id;
    private String email;
}
