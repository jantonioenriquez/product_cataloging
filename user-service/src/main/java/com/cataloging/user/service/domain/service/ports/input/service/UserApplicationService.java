package com.cataloging.user.service.domain.service.ports.input.service;

import com.cataloging.user.service.domain.service.create.CreateUserCommand;
import com.cataloging.user.service.domain.service.create.CreateUserResponse;
import com.cataloging.user.service.domain.service.delete.DeleteUserCommand;
import com.cataloging.user.service.domain.service.delete.DeleteUserResponse;
import com.cataloging.user.service.domain.service.query.QueryUserCommand;
import com.cataloging.user.service.domain.service.query.QueryUsersCommand;
import com.cataloging.user.service.domain.service.query.QueryUsersResponse;
import com.cataloging.user.service.domain.service.query.QueryUserResponse;
import com.cataloging.user.service.domain.service.update.UpdateUserCommand;
import com.cataloging.user.service.domain.service.update.UpdateUserResponse;

public interface UserApplicationService {
    QueryUsersResponse findAll(QueryUsersCommand command);

    QueryUserResponse findById(QueryUserCommand queryUserCommand);

    CreateUserResponse createUser(CreateUserCommand createUserCommand);

    UpdateUserResponse updateUser(UpdateUserCommand updateUserCommand);

    DeleteUserResponse deleteUser(DeleteUserCommand deleteUserCommand);
}
