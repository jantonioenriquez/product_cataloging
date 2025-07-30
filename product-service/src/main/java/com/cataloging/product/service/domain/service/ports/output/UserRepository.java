package com.cataloging.product.service.domain.service.ports.output;


import com.cataloging.product.service.domain.core.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    Optional<User> findUserByAuth0Id(String auth0Id);
    User createUser(User user);
    User updateUser(User user);
    void deleteUser(User user);
    List<User> findUsersByIdProvider(String providerId);

}
