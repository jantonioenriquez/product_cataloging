package com.cataloging.user.service.domain.service.query;

import com.cataloging.user.service.domain.core.entity.User;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class QueryUserResponse {
    private User user;

    @NotNull
    private final String message;
}
