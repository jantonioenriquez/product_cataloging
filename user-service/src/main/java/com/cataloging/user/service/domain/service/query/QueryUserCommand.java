package com.cataloging.user.service.domain.service.query;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class QueryUserCommand {
    private String id;
}
