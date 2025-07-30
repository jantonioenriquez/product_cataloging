package com.cataloging.product.service.domain.service;

import com.cataloging.product.service.domain.service.dto.message.UserModel;
import com.cataloging.product.service.domain.service.handler.UserCreateCommandHandler;
import com.cataloging.product.service.domain.service.handler.UserDeleteCommandHandler;
import com.cataloging.product.service.domain.service.handler.UserUpdateCommandHandler;
import com.cataloging.product.service.domain.service.mapper.UserDataMapper;
import com.cataloging.product.service.domain.service.ports.message.listener.UserMessageListener;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

/**
 * Service class contains the functionality of CRUD operations for users
 */
@Validated
@Service
public class UserMessageListenerImpl implements UserMessageListener {

    private final UserCreateCommandHandler userCreateCommandHandler;
    private final UserUpdateCommandHandler userUpdateCommandHandler;
    private final UserDeleteCommandHandler userDeleteCommandHandler;
    private final UserDataMapper userDataMapper;

    public UserMessageListenerImpl(UserCreateCommandHandler userCreateCommandHandler,
                                   UserUpdateCommandHandler userUpdateCommandHandler,
                                   UserDeleteCommandHandler userDeleteCommandHandler,
                                   UserDataMapper userDataMapper) {
        this.userCreateCommandHandler = userCreateCommandHandler;
        this.userUpdateCommandHandler = userUpdateCommandHandler;
        this.userDeleteCommandHandler = userDeleteCommandHandler;
        this.userDataMapper = userDataMapper;
    }

    /**
     * Method that processes messages received by post user
     * @param userModel user data received by pubsub on event create user
     */
    @Override
    public void userCreated(UserModel userModel){
        var createUserCommand = userDataMapper.userModelToCreateUserCommand(userModel);

        userCreateCommandHandler.createUser(createUserCommand);
    }

    /**
     * Method that processes messages received by put user
     * @param userModel user data received by pubsub on event update user
     */
    @Override
    public void userUpdated(UserModel userModel){
        var updateUserCommand = userDataMapper.userModelToUpdateUserCommand(userModel);
        userUpdateCommandHandler.updateUser(updateUserCommand);
    }

    /**
     * Method that processes messages received by delete user
     * @param userModel user data received by pubsub on event delete user
     */
    @Override
    public void userDeleted(UserModel userModel){
        var updateUserCommand = userDataMapper.userModelToDeleteUserCommand(userModel);
        userDeleteCommandHandler.deleteUser(updateUserCommand);
    }
}