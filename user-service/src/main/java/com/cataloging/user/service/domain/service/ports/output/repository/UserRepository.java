package com.cataloging.user.service.domain.service.ports.output.repository;

import com.cataloging.user.service.domain.service.query.QueryUsersCommand;
import com.cataloging.user.service.domain.core.entity.User;

import java.util.List;

public interface UserRepository {
    User createUser(User user);

    User updateUser(User user);

    void deleteUser(String id);

    User findUserById(String id);

    List<User> findUsers(QueryUsersCommand command);

    long countUsers(QueryUsersCommand command);
}
