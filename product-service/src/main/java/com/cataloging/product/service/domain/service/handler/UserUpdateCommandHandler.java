package com.cataloging.product.service.domain.service.handler;

import com.cataloging.product.service.domain.service.mapper.UserDataMapper;
import com.cataloging.product.service.domain.service.ports.output.UserRepository;
import com.cataloging.product.service.domain.service.update.UpdateUserCommand;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * Handler class that implements functionality for update users
 */
@Slf4j
@Component
public class UserUpdateCommandHandler {
    private final UserRepository userRepository;
    private final UserDataMapper userDataMapper;

    public UserUpdateCommandHandler(UserRepository userRepository, UserDataMapper userDataMapper) {
        this.userRepository = userRepository;
        this.userDataMapper = userDataMapper;
    }

    /**
     * Executes validations and convert data user from the UpdateUserCommand object to User for to update it in the database
     *
     * @param updateUserCommand contains user attributes
     */
    @Transactional
    public void updateUser(UpdateUserCommand updateUserCommand) {
        Gson gson = new Gson();
        log.info("User update data in pubsub process : " + gson.toJson(updateUserCommand));

        var user = userDataMapper.updateUserCommandHandlerToUser(updateUserCommand);

        log.info("User update data final valid in pubsub process : " + gson.toJson(user));
        userRepository.updateUser(user);
        log.info("User {} updated successfully", user.getEmail());
    }
}