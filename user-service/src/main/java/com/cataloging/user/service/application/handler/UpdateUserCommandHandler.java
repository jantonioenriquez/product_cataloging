package com.cataloging.user.service.application.handler;

import com.cataloging.user.service.domain.service.update.UpdateUserCommand;
import com.cataloging.user.service.domain.service.update.UpdateUserResponse;
import com.cataloging.user.service.dataaccess.mapper.UserDataAccessMapper;
import com.cataloging.user.service.domain.service.ports.output.repository.UserRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class UpdateUserCommandHandler {
    private final UserRepository userRepository;
    private final UserDataAccessMapper userDataAccessMapper;

    public UpdateUserCommandHandler(UserRepository userRepository, UserDataAccessMapper userDataAccessMapper) {
        this.userRepository = userRepository;
        this.userDataAccessMapper = userDataAccessMapper;
    }

    @Transactional
    public UpdateUserResponse updateUser(UpdateUserCommand updateUserCommand) {
        var user = userDataAccessMapper.updateUserCommandToUser(updateUserCommand);
        return new UpdateUserResponse(userRepository.updateUser(user).getId().getValue(), "OK");
    }
}
