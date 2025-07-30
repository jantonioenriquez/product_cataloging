package com.cataloging.product.service.application.controller;

import com.cataloging.DomainConstants;
import com.cataloging.product.service.domain.service.create.CreateProductCommand;
import com.cataloging.product.service.domain.service.create.CreateProductResponse;
import com.cataloging.product.service.domain.service.delete.DeleteProductCommand;
import com.cataloging.product.service.domain.service.delete.DeleteProductResponse;
import com.cataloging.product.service.domain.service.ports.input.service.ProductApplicationService;
import com.cataloging.product.service.domain.service.query.QueryProductCommand;
import com.cataloging.product.service.domain.service.query.QueryProductResponse;
import com.cataloging.product.service.domain.service.query.QueryProductsCommand;
import com.cataloging.product.service.domain.service.query.QueryProductsResponse;
import com.cataloging.product.service.domain.service.update.UpdateProductCommand;
import com.cataloging.product.service.domain.service.update.UpdateProductResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public class ProductController {
    private final ProductApplicationService service;

    public ProductController(ProductApplicationService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<QueryProductsResponse> getUsers(@RequestParam(defaultValue = DomainConstants.SIZE_DEFAULT) int size,
                                                          @RequestParam(defaultValue = DomainConstants.PAGE_DEFAULT) int page,
                                                          @RequestParam(defaultValue = DomainConstants.ORDER_STATUS_DEFAULT) String order,
                                                          @RequestParam(defaultValue = "createdAt") String sort) {
        QueryProductsCommand queryProductsCommand = QueryProductsCommand
                .builder()
                .size(size)
                .page(page)
                .order(order)
                .sort(sort)
                .build();

        var response = service.findAll(queryProductsCommand);
        return ResponseEntity.ok(response);

    }

    @GetMapping("/{id}")
    public ResponseEntity<QueryProductResponse> findUserById(@PathVariable String id) {
        QueryProductCommand queryProductCommand = QueryProductCommand
                .builder()
                .id(id)
                .build();
        var response = service.findById(queryProductCommand);
        return ResponseEntity.ok(response);

    }

    @PostMapping
    public ResponseEntity<CreateProductResponse> createUser(@RequestParam String name,
                                                            @RequestParam String email) {
        CreateProductCommand command = CreateProductCommand.builder()
                .name(name)
                .email(email)
                .build();
        var response = service.createUser(command);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UpdateProductResponse> updateUser(@PathVariable String id,
                                                            @RequestParam String name,
                                                            @RequestParam String email) {
        UpdateProductCommand command = UpdateProductCommand.builder()
                .id(id)
                .name(name)
                .email(email)
                .build();
        var response = service.updateUser(command);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DeleteProductResponse> deleteUser(@PathVariable String id) {
        DeleteProductCommand deleteUserCommand = DeleteProductCommand
                .builder()
                .id(id)
                .build();
        var response = service.deleteUser(deleteUserCommand);
        return ResponseEntity.ok(response);
    }

}
