package com.cataloging.product.service.domain.service.query;

import com.cataloging.product.service.domain.core.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class QueryProductsResponse {
    private List<Product> products;
    private final int page;
    private final int size;
    private final long total;
}
