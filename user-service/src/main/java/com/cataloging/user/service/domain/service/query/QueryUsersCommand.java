package com.cataloging.user.service.domain.service.query;

import com.cataloging.user.service.domain.core.entity.User;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class QueryUsersCommand {
    private List<User> users;
    private int page;
    private int size;
    private String sort;
    private String order;
    private long total;
}
