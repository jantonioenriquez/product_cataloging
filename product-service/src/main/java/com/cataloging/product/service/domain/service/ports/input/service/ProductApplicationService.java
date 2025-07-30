package com.cataloging.product.service.domain.service.ports.input.service;

import com.cataloging.product.service.domain.service.create.CreateProductCommand;
import com.cataloging.product.service.domain.service.create.CreateProductResponse;
import com.cataloging.product.service.domain.service.delete.DeleteProductCommand;
import com.cataloging.product.service.domain.service.delete.DeleteProductResponse;
import com.cataloging.product.service.domain.service.query.QueryProductCommand;
import com.cataloging.product.service.domain.service.query.QueryProductResponse;
import com.cataloging.product.service.domain.service.query.QueryProductsCommand;
import com.cataloging.product.service.domain.service.query.QueryProductsResponse;
import com.cataloging.product.service.domain.service.update.UpdateProductCommand;
import com.cataloging.product.service.domain.service.update.UpdateProductResponse;

public interface ProductApplicationService {
    QueryProductsResponse findAll(QueryProductsCommand queryProductsCommand);

    QueryProductResponse findById(QueryProductCommand queryProductCommand);

    CreateProductResponse createUser(CreateProductCommand createProductCommand);

    UpdateProductResponse updateUser(UpdateProductCommand updateProductCommand);

    DeleteProductResponse deleteUser(DeleteProductCommand deleteProductCommand);
}
