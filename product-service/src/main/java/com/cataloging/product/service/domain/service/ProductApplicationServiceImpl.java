package com.cataloging.product.service.domain.service;

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

public class ProductApplicationServiceImpl implements ProductApplicationService {
    @Override
    public QueryProductsResponse findAll(QueryProductsCommand queryProductsCommand) {
        return null;
    }

    @Override
    public QueryProductResponse findById(QueryProductCommand queryProductCommand) {
        return null;
    }

    @Override
    public CreateProductResponse createUser(CreateProductCommand createProductCommand) {
        return null;
    }

    @Override
    public UpdateProductResponse updateUser(UpdateProductCommand updateProductCommand) {
        return null;
    }

    @Override
    public DeleteProductResponse deleteUser(DeleteProductCommand deleteProductCommand) {
        return null;
    }
}
