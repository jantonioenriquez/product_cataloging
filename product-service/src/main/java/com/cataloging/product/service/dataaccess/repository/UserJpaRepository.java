package com.cataloging.product.service.dataaccess.repository;

import com.cataloging.product.service.dataaccess.entity.UserEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserJpaRepository extends MongoRepository<UserEntity, String> {
    @Query("{'auth0Id': :#{#auth0Id}}")
    Optional<UserEntity> findUserByAuth0Id(String auth0Id);

    @Query("{ 'providers.idProvider': ?0 }")
    List<UserEntity> findByProviderId(String providerId);
}
