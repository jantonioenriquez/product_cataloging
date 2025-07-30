package com.cataloging.product.service.domain.service.handler;

import com.cataloging.product.service.domain.service.delete.DeleteUserCommand;
import com.cataloging.product.service.domain.service.mapper.UserDataMapper;
import com.cataloging.product.service.domain.service.ports.output.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * Handler class that implements functionality for user deletion
 */
@Slf4j
@Component
public class UserDeleteCommandHandler {
    private final UserRepository userRepository;
    private final UserDataMapper userDataMapper;

    public UserDeleteCommandHandler(UserRepository userRepository, UserDataMapper userDataMapper) {
        this.userRepository = userRepository;
        this.userDataMapper = userDataMapper;
    }

    /**
     * Delete user from database with id received from object deleteUserCommand
     *
     * @param deleteUserCommand contains user attributes
     */
    @Transactional
    public void deleteUser(DeleteUserCommand deleteUserCommand) {
        var user = userDataMapper.deleteUserCommandHandlerToUser(deleteUserCommand);
        var foundUser = userRepository.findUserByAuth0Id(deleteUserCommand.getAuth0Id());

        if (foundUser.isPresent()) {
            userRepository.deleteUser(foundUser.get());
            log.info("User {} deleted successfully", user.getEmail());
        } else {
            log.info("User {} not found", user.getEmail());
        }
    }
}
