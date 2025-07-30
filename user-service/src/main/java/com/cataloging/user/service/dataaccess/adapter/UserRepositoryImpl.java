package com.cataloging.user.service.dataaccess.adapter;

import com.cataloging.user.service.dataaccess.entity.UserEntity;
import com.cataloging.user.service.domain.service.query.QueryUsersCommand;
import com.cataloging.user.service.domain.core.entity.User;
import com.cataloging.user.service.dataaccess.mapper.UserDataAccessMapper;
import com.cataloging.user.service.dataaccess.repository.UserJpaRepository;
import com.cataloging.user.service.domain.service.ports.output.repository.UserRepository;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.*;
import org.springframework.data.mongodb.core.query.Collation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class UserRepositoryImpl implements UserRepository {
    private final UserJpaRepository userJpaRepository;
    private final UserDataAccessMapper userDataAccessMapper;
    private final MongoTemplate mongoTemplate;

    public UserRepositoryImpl(UserJpaRepository userJpaRepository, UserDataAccessMapper userDataAccessMapper, MongoTemplate mongoTemplate) {
        this.userJpaRepository = userJpaRepository;
        this.userDataAccessMapper = userDataAccessMapper;
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public User createUser(User user) {
        var userEntity = userDataAccessMapper.userToUserEntity(user);
        var savedUser = userJpaRepository.save(userEntity);
        return userDataAccessMapper.userEntityToUser(savedUser);
    }

    @Override
    public User updateUser(User user) {
        var userEntity = userDataAccessMapper.userToUserEntity(user);
        var savedUser = userJpaRepository.save(userEntity);
        return userDataAccessMapper.userEntityToUser(savedUser);
    }

    @Override
    public void deleteUser(String id) {
        userJpaRepository.deleteById(id);
    }

    @Override
    public User findUserById(String id) {
        var userEntity = userJpaRepository.findById(id);
        return userDataAccessMapper.userEntityToUser(userEntity.get());
    }

    @Override
    public List<User> findUsers(QueryUsersCommand command) {
        Aggregation aggregation = findUsers(true, command);
        List<UserEntity> result = mongoTemplate.aggregate(aggregation, "users",
                UserEntity.class).getMappedResults();
        return result.stream().map(userDataAccessMapper::userEntityToUser).toList();
    }

    @Override
    public long countUsers(QueryUsersCommand command) {
        Aggregation aggregation = findUsers(false, command);
        var result = mongoTemplate.aggregate(aggregation, "users", Map.class).getMappedResults();
        return result.isEmpty() ? 0 : Long.parseLong(result.get(0).get("count").toString());
    }

    private Aggregation findUsers(Boolean isPageable, QueryUsersCommand command) {
        Criteria criteria = new Criteria();
        var sort = command.getSort();
        var order = command.getOrder();
        var page = command.getPage();
        var size = command.getSize();

        MatchOperation match = Aggregation.match(criteria);

        ProjectionOperation project = Aggregation.project("_id", "name",
                "status", "email", "createdAt");

        var sortOperation = sort != null ? order.equalsIgnoreCase(Sort.Direction.ASC.name())
                ? Aggregation.sort(Sort.Direction.ASC, sort)
                : Aggregation.sort(Sort.Direction.DESC, sort) : null;

        if (isPageable) {
            Collation collation = Collation.of("en");
            AggregationOptions options = AggregationOptions.builder().collation(collation).build();

            long skip = (long) page * size;
            AggregationOperation skipOperation = Aggregation.skip(skip);
            AggregationOperation limitOperation = Aggregation.limit(size);

            return Aggregation.newAggregation(match, project, sortOperation, skipOperation,
                    limitOperation).withOptions(options);
        } else {
            GroupOperation count = Aggregation.group().count().as("count");
            return Aggregation.newAggregation(match, count);
        }
    }
}
