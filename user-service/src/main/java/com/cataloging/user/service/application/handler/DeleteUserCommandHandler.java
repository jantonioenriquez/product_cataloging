package com.cataloging.user.service.application.handler;

import com.cataloging.user.service.domain.service.delete.DeleteUserCommand;
import com.cataloging.user.service.domain.service.delete.DeleteUserResponse;
import com.cataloging.user.service.domain.service.ports.output.repository.UserRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class DeleteUserCommandHandler {
    private final UserRepository userRepository;

    public DeleteUserCommandHandler(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public DeleteUserResponse deleteUser(DeleteUserCommand deleteUserCommand) {
        userRepository.deleteUser(deleteUserCommand.getId());
        return new DeleteUserResponse("OK");
    }
}
