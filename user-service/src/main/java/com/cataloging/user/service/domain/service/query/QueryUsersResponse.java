package com.cataloging.user.service.domain.service.query;

import com.cataloging.user.service.domain.core.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class QueryUsersResponse {
    private List<User> users;
    private final int page;
    private final int size;
    private final long total;
}
