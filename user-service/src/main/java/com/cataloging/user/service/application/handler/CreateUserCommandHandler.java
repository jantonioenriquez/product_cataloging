package com.cataloging.user.service.application.handler;

import com.cataloging.user.service.domain.service.create.CreateUserCommand;
import com.cataloging.user.service.domain.service.create.CreateUserResponse;
import com.cataloging.user.service.dataaccess.mapper.UserDataAccessMapper;
import com.cataloging.user.service.domain.service.ports.output.repository.UserRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class CreateUserCommandHandler {
    private final UserRepository userRepository;
    private final UserDataAccessMapper userDataAccessMapper;

    public CreateUserCommandHandler(UserRepository userRepository, UserDataAccessMapper userDataAccessMapper) {
        this.userRepository = userRepository;
        this.userDataAccessMapper = userDataAccessMapper;
    }

    @Transactional
    public CreateUserResponse createUser(CreateUserCommand createUserCommand) {
        var user = userDataAccessMapper.createUserCommandToUser(createUserCommand);
        return new CreateUserResponse(userRepository.createUser(user).getId().getValue(), "OK");
    }



}
