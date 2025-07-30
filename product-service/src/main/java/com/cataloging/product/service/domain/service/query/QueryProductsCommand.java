package com.cataloging.product.service.domain.service.query;

import com.cataloging.product.service.domain.core.entity.Product;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class QueryProductsCommand {
    private List<Product> products;
    private int page;
    private int size;
    private String sort;
    private String order;
    private long total;
}
