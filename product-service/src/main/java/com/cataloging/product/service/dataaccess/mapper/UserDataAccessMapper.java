package com.cataloging.product.service.dataaccess.mapper;

import com.cataloging.product.service.dataaccess.entity.UserEntity;
import com.cataloging.product.service.domain.core.entity.User;
import com.cataloging.valueObject.UserId;
import com.cataloging.valueObject.UserStatus;
import org.springframework.stereotype.Component;

@Component
public class UserDataAccessMapper {

    /**
     * Convert object UserEntity to User
     *
     * @param user contain data user saved in database
     * @return Object user
     */
    public User userEntityToUser(UserEntity user) {
          var status = user.getStatus() != null ? UserStatus.valueOf(user.getStatus()) : null;

        return new User(new UserId(String.valueOf(user.getId())),
                user.getName(),
                user.getAuth0Id(),
                user.getEmail(),
                status,
                user.getCreatedAt()
        );
    }

    /**
     * Convert object User to UserEntity for save in database
     *
     * @param user - object domain that contains user attributes
     * @return object entity
     */
    public UserEntity userToUserEntity(User user) {
        String id = user.getId() == null ? null : user.getId().getValue();
        String status = user.getStatus() != null ? user.getStatus().name() : null;

        return UserEntity.builder()
                .id(id)
                .name(user.getName())
                .auth0Id(user.getAuth0Id())
                .email(user.getEmail())
                .status(status)
                .build();
    }
}