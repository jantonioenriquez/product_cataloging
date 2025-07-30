package com.cataloging.user.service.domain.service.create;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class CreateUserCommand {
    private String name;
    private String email;
}
