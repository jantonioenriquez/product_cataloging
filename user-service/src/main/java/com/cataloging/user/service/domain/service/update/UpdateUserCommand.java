package com.cataloging.user.service.domain.service.update;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class UpdateUserCommand {
    private String id;
    private String name;
    private String email;
    private String status;
}
