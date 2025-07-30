package com.cataloging.product.service.dataaccess.entity;

import jakarta.persistence.Id;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document("users")
public class UserEntity {

    @Id
    private String id;
    private String name;
    private String auth0Id;
    private String email;
    private Date createdAt;
    private String status;

}
