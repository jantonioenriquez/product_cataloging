package com.cataloging.user.service.dataaccess.mapper;

import com.cataloging.user.service.domain.service.create.CreateUserCommand;
import com.cataloging.user.service.domain.service.update.UpdateUserCommand;
import com.cataloging.user.service.domain.core.entity.User;
import com.cataloging.user.service.dataaccess.entity.UserEntity;
import com.cataloging.valueObject.UserId;
import org.springframework.stereotype.Component;

@Component
public class UserDataAccessMapper {

    public User createUserCommandToUser(CreateUserCommand createUserCommand) {
        return new User(createUserCommand.getName(), createUserCommand.getEmail());
    }


    public User updateUserCommandToUser(UpdateUserCommand updateUserCommand) {
        UserId id = new UserId(updateUserCommand.getId());
        return new User(id, updateUserCommand.getName(), updateUserCommand.getEmail(), updateUserCommand.getStatus());
    }

    public UserEntity userToUserEntity(User user) {
        return UserEntity
                .builder()
                .id(user.getId().getValue())
                .name(user.getName())
                .email(user.getEmail())
                .build();
    }

    public User userEntityToUser(UserEntity userEntity) {
        UserId id = new UserId(userEntity.getId());
        return new User(id, userEntity.getAuth0Id(), userEntity.getName(), userEntity.getEmail(), userEntity.getStatus(),
                userEntity.getCreatedAt());
    }


}
