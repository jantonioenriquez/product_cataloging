package com.cataloging.product.service.domain.core.entity;


import com.cataloging.entity.AggregateRoot;
import com.cataloging.valueObject.UserId;
import com.cataloging.valueObject.UserStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@AllArgsConstructor
@Builder
@Getter
@Setter
public class User extends AggregateRoot<UserId> {
    private String name;
    private String auth0Id;
    private String email;
    private UserStatus status;
    private Date createdAt;

    public User(UserId id, String auth0Id, String name, String email, UserStatus status, Date createdAt) {
        super.setId(id);
        this.auth0Id = auth0Id;
        this.name = name;
        this.email = email;
        this.status = status;
        this.createdAt = createdAt;
    }

    public User(UserId id, String name, String email, UserStatus status) {
        super.setId(id);
        this.name = name;
        this.email = email;
        this.status = status;
    }

    public User(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public User(UserId id, String auth0Id, String email) {
        super.setId(id);
        this.auth0Id = auth0Id;
        this.email = email;
    }
}
