package com.cataloging.user.service.application.controller;

import com.cataloging.DomainConstants;
import com.cataloging.user.service.domain.service.create.CreateUserCommand;
import com.cataloging.user.service.domain.service.create.CreateUserResponse;
import com.cataloging.user.service.domain.service.delete.DeleteUserCommand;
import com.cataloging.user.service.domain.service.delete.DeleteUserResponse;
import com.cataloging.user.service.domain.service.query.QueryUserCommand;
import com.cataloging.user.service.domain.service.query.QueryUsersCommand;
import com.cataloging.user.service.domain.service.query.QueryUsersResponse;
import com.cataloging.user.service.domain.service.query.QueryUserResponse;
import com.cataloging.user.service.domain.service.update.UpdateUserCommand;
import com.cataloging.user.service.domain.service.update.UpdateUserResponse;
import com.cataloging.user.service.domain.service.UserApplicationServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user")
public class UserController {
    private final UserApplicationServiceImpl service;

    public UserController(UserApplicationServiceImpl service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<QueryUsersResponse> getUsers(@RequestParam(defaultValue = DomainConstants.SIZE_DEFAULT) int size,
                                                       @RequestParam(defaultValue = DomainConstants.PAGE_DEFAULT) int page,
                                                       @RequestParam(defaultValue = DomainConstants.ORDER_STATUS_DEFAULT) String order,
                                                       @RequestParam(defaultValue = "createdAt") String sort) {
        QueryUsersCommand queryUsersCommand = QueryUsersCommand
                .builder()
                .size(size)
                .page(page)
                .order(order)
                .sort(sort)
                .build();

        var response = service.findAll(queryUsersCommand);
        return ResponseEntity.ok(response);

    }

    @GetMapping("/{id}")
    public ResponseEntity<QueryUserResponse> findUserById(@PathVariable String id) {
        QueryUserCommand queryUserCommand = QueryUserCommand
                .builder()
                .id(id)
                .build();
        var response = service.findById(queryUserCommand);
        return ResponseEntity.ok(response);

    }

    @PostMapping
    public ResponseEntity<CreateUserResponse> createUser(@RequestParam String name,
                                                         @RequestParam String email) {
        CreateUserCommand command = CreateUserCommand.builder()
                .name(name)
                .email(email)
                .build();
        var response = service.createUser(command);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UpdateUserResponse> updateUser(@PathVariable String id,
                                                         @RequestParam String name,
                                                         @RequestParam String email) {
        UpdateUserCommand command = UpdateUserCommand.builder()
                .id(id)
                .name(name)
                .email(email)
                .build();
        var response = service.updateUser(command);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DeleteUserResponse> deleteUser(@PathVariable String id) {
        DeleteUserCommand deleteUserCommand = DeleteUserCommand
                .builder()
                .id(id)
                .build();
        var response = service.deleteUser(deleteUserCommand);
        return ResponseEntity.ok(response);
    }
}
