package com.cataloging.user.service.dataaccess.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;

import java.util.Date;

@Builder
@Getter
@Entity
public class UserEntity {
    @Id
    private String id;
    private String auth0Id;
    private String name;
    @CreatedDate
    private Date createdAt;
    private String status;
    private String email;
}
