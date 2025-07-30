package com.cataloging.product.service.domain.service.query;

import com.cataloging.product.service.domain.core.entity.Product;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class QueryProductResponse {
    private Product product;

    @NotNull
    private final String message;
}
