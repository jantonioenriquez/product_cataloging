package com.cataloging.product.service.domain.service.mapper;


import com.cataloging.product.service.domain.core.entity.User;
import com.cataloging.product.service.domain.service.create.CreateUserCommand;
import com.cataloging.product.service.domain.service.delete.DeleteUserCommand;
import com.cataloging.product.service.domain.service.dto.message.UserModel;
import com.cataloging.product.service.domain.service.update.UpdateUserCommand;
import com.cataloging.valueObject.UserId;
import org.springframework.stereotype.Component;

/**
 * Class for mapping user attributes to domain user objects.
 */
@Component
public class UserDataMapper {
    /**
     * This method convert object CreateUserCommand to User
     *
     * @param createUserCommand - object with attributes for create users
     * @return Entity User
     */
    public User createUserCommandHandlerToUser(CreateUserCommand createUserCommand) {
        UserId id = createUserCommand.getId() == null ? null : new UserId(createUserCommand.getId());


        return new User(
                id,
                createUserCommand.getAuth0Id(),
                createUserCommand.getEmail(),
                createUserCommand.getStatus()
        );
    }

    /**
     * Convert object UserModel to CreateUserCommand
     *
     * @param userModel - message from pubsub
     * @return Object command
     */
    public CreateUserCommand userModelToCreateUserCommand(UserModel userModel) {
        return new CreateUserCommand(
                userModel.getId(),
                userModel.getAuth0Id(),
                userModel.getEmail(),
                userModel.getStatus()
        );
    }

    /**
     * Convert object UserModel to CreateUserCommand
     *
     * @param userModel - message from pubsub
     * @return Object
     */
    public UpdateUserCommand userModelToUpdateUserCommand(UserModel userModel) {
        return new UpdateUserCommand(
                userModel.getId(),
                userModel.getAuth0Id(),
                userModel.getEmail(),
                userModel.getProfiles(),
                userModel.getStatus()
        );
    }

    /**
     * Convert object UserModel to CreateUserCommand
     *
     * @param userModel - message from pubsub
     * @return Object
     */
    public DeleteUserCommand userModelToDeleteUserCommand(UserModel userModel) {
        return new DeleteUserCommand(
                userModel.getId(),
                userModel.getAuth0Id(),
                userModel.getEmail()
        );
    }

    /**
     * This method convert object UpdateUserCommand to User
     *
     * @param updateUserCommand - object with attributes for update users
     * @return Entity User
     */
    public User updateUserCommandHandlerToUser(UpdateUserCommand updateUserCommand) {
        UserId id = updateUserCommand.getId() == null ? null : new UserId(updateUserCommand.getId());


        return new User(
                id,
                updateUserCommand.getAuth0Id(),
                updateUserCommand.getEmail(),
                updateUserCommand.getStatus()
        );
    }

    /**
     * This method convert object DeleteUserCommand to User
     *
     * @param deleteUserCommand - object with attributes for delete users
     * @return Entity User
     */
    public User deleteUserCommandHandlerToUser(DeleteUserCommand deleteUserCommand) {
        UserId id = deleteUserCommand.getId() == null ? null : new UserId(deleteUserCommand.getId());
        return new User(
                id,
                deleteUserCommand.getAuth0Id(),
                deleteUserCommand.getEmail()
        );
    }

}
