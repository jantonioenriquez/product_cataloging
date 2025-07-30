package com.cataloging.product.service.domain.core.entity;

import com.cataloging.entity.AggregateRoot;
import com.cataloging.valueObject.ProductId;

import java.util.Date;
import java.util.List;

public class Product extends AggregateRoot<ProductId> {
    private Date createdAt;
    private Date updatedAt;
    private List<String> urls;
    private User user;
    private Document document;

    public Product(ProductId id, Date createdAt, Date updatedAt, List<String> urls, User user, Document document) {

    }
}
