package com.cataloging.product.service.domain.service.delete;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class DeleteProductResponse {
    @NotNull
    private final String message;
}
