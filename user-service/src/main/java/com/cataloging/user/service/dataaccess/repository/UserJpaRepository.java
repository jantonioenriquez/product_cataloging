package com.cataloging.user.service.dataaccess.repository;

import com.cataloging.user.service.dataaccess.entity.UserEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserJpaRepository extends MongoRepository<UserEntity, String> {

}
