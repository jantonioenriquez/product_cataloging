package com.cataloging.user.service.domain.service.delete;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class DeleteUserCommand {
    private String id;
}
