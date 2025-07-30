package com.cataloging.user.service.domain.service.create;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class CreateUserResponse {
    private String id;

    @NotNull
    private final String message;
}
