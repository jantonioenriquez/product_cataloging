package com.cataloging.product.service.domain.service.handler;

import com.cataloging.product.service.domain.service.create.CreateUserCommand;
import com.cataloging.product.service.domain.service.mapper.UserDataMapper;
import com.cataloging.product.service.domain.service.ports.output.UserRepository;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


/**
 * Handler class that implements functionality for create users
 */
@Slf4j
@Component
public class UserCreateCommandHandler {
    private final UserRepository userRepository;
    private final UserDataMapper userDataMapper;

    public UserCreateCommandHandler(UserRepository userRepository, UserDataMapper userDataMapper) {
        this.userRepository = userRepository;
        this.userDataMapper = userDataMapper;
    }

    /**
     * Executes validations and convert data user from the createUserCommand object to User for to save it in the database
     *
     * @param createUserCommand contains user attributes
     */
    @Transactional
    public void createUser(CreateUserCommand createUserCommand) {

        Gson gson = new Gson();
        log.info("User create data in pubsub process : " + gson.toJson(createUserCommand));

        var user = userDataMapper.createUserCommandHandlerToUser(createUserCommand);

        log.info("User create data final valid in pubsub process : " + gson.toJson(user));
        userRepository.createUser(user);
        log.info("User {} saved successfully", user.getEmail());
    }

}