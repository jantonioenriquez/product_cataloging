package com.cataloging.user.service.application.handler;

import com.cataloging.user.service.domain.service.query.QueryUserCommand;
import com.cataloging.user.service.domain.service.query.QueryUsersCommand;
import com.cataloging.user.service.domain.service.query.QueryUsersResponse;
import com.cataloging.user.service.domain.service.query.QueryUserResponse;
import com.cataloging.user.service.domain.service.ports.output.repository.UserRepository;
import org.springframework.stereotype.Component;

@Component
public class QueryUserCommandHandler {
    private final UserRepository userRepository;

    public QueryUserCommandHandler(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public QueryUserResponse findUserById(QueryUserCommand command) {
        var user = userRepository.findUserById(command.getId());
        return new QueryUserResponse(user, "Ok");
    }

    public QueryUsersResponse getUsers(QueryUsersCommand command) {
        long total = userRepository.countUsers(command);
        var users = userRepository.findUsers(command);
        return new QueryUsersResponse(users, command.getPage(), command.getSize(), total);
    }
}
