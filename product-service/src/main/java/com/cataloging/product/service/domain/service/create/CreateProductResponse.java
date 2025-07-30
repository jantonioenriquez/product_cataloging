package com.cataloging.product.service.domain.service.create;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class CreateProductResponse {
    private String id;

    @NotNull
    private final String message;
}
