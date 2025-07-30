package com.cataloging.user.service.domain.service;

import com.cataloging.user.service.domain.service.create.CreateUserCommand;
import com.cataloging.user.service.domain.service.create.CreateUserResponse;
import com.cataloging.user.service.domain.service.delete.DeleteUserCommand;
import com.cataloging.user.service.domain.service.delete.DeleteUserResponse;
import com.cataloging.user.service.domain.service.ports.input.service.UserApplicationService;
import com.cataloging.user.service.domain.service.query.QueryUserCommand;
import com.cataloging.user.service.domain.service.query.QueryUsersCommand;
import com.cataloging.user.service.domain.service.query.QueryUsersResponse;
import com.cataloging.user.service.domain.service.query.QueryUserResponse;
import com.cataloging.user.service.domain.service.update.UpdateUserCommand;
import com.cataloging.user.service.domain.service.update.UpdateUserResponse;
import com.cataloging.user.service.application.handler.CreateUserCommandHandler;
import com.cataloging.user.service.application.handler.DeleteUserCommandHandler;
import com.cataloging.user.service.application.handler.QueryUserCommandHandler;
import com.cataloging.user.service.application.handler.UpdateUserCommandHandler;
import org.springframework.stereotype.Service;

@Service
public class UserApplicationServiceImpl implements UserApplicationService {
    private final CreateUserCommandHandler createUserCommandHandler;
    private final UpdateUserCommandHandler updateUserCommandHandler;
    private final DeleteUserCommandHandler deleteUserCommandHandler;
    private final QueryUserCommandHandler queryUserCommandHandler;

    public UserApplicationServiceImpl(CreateUserCommandHandler createUserCommandHandler, UpdateUserCommandHandler updateUserCommandHandler,
                                      DeleteUserCommandHandler deleteUserCommandHandler, QueryUserCommandHandler queryUserCommandHandler) {
        this.createUserCommandHandler = createUserCommandHandler;
        this.updateUserCommandHandler = updateUserCommandHandler;
        this.deleteUserCommandHandler = deleteUserCommandHandler;
        this.queryUserCommandHandler = queryUserCommandHandler;
    }

    public QueryUsersResponse findAll(QueryUsersCommand command) {
        return queryUserCommandHandler.getUsers(command);
    }

    public QueryUserResponse findById(QueryUserCommand queryUserCommand) {
        return queryUserCommandHandler.findUserById(queryUserCommand);
    }

    public CreateUserResponse createUser(CreateUserCommand createUserCommand) {
        return createUserCommandHandler.createUser(createUserCommand);
    }

    public UpdateUserResponse updateUser(UpdateUserCommand updateUserCommand) {
        return updateUserCommandHandler.updateUser(updateUserCommand);
    }

    public DeleteUserResponse deleteUser(DeleteUserCommand deleteUserCommand) {
        return deleteUserCommandHandler.deleteUser(deleteUserCommand);
    }
}
