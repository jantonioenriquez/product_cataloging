package com.cataloging.product.service.dataaccess.adapter;

import com.cataloging.product.service.dataaccess.mapper.UserDataAccessMapper;
import com.cataloging.product.service.dataaccess.repository.UserJpaRepository;
import com.cataloging.product.service.domain.core.entity.User;
import com.cataloging.product.service.domain.service.ports.output.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;


@Component
@Slf4j
public class UserRepositoryImpl implements UserRepository {

    private final UserJpaRepository userJpaRepository;
    private final UserDataAccessMapper userDataAccessMapper;

    public UserRepositoryImpl(UserJpaRepository userJpaRepository,
                              UserDataAccessMapper userDataAccessMapper) {
        this.userJpaRepository = userJpaRepository;
        this.userDataAccessMapper = userDataAccessMapper;
    }

    /**
     * search for the user of the object in mongodb
     *
     * @param auth0Id data user
     * @return object user with id mongodb
     */
    @Override
    public Optional<User> findUserByAuth0Id(String auth0Id) {
        return userJpaRepository.findUserByAuth0Id(auth0Id).map(userDataAccessMapper::userEntityToUser);
    }

    /**
     * Save object user into mongodb on event create user
     *
     * @param user - data user
     * @return object user with id mongodb
     */
    @Override
    public User createUser(User user) {
        var userEntity = userDataAccessMapper.userToUserEntity(user);
        var userSaved = userJpaRepository.save(userEntity);

        return userDataAccessMapper.userEntityToUser(userSaved);
    }

    /**
     * Update object user into mongodb on event update user
     *
     * @param user - data user
     * @return object user with id mongodb
     */
    @Override
    public User updateUser(User user) {
        var userEntity = userDataAccessMapper.userToUserEntity(user);
        var userSaved = userJpaRepository.save(userEntity);

        return userDataAccessMapper.userEntityToUser(userSaved);
    }

    /**
     * Delete object user into mongodb on event delete user
     *
     * @param user - data user
     */
    @Override
    public void deleteUser(User user) {
        var userEntity = userDataAccessMapper.userToUserEntity(user);
        userJpaRepository.delete(userEntity);
    }

    @Override
    public List<User> findUsersByIdProvider(String providerId) {
        return userJpaRepository.findByProviderId(providerId).stream().map(userDataAccessMapper::userEntityToUser).toList();
    }
}
