package com.cataloging.product.service.domain.service.delete;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class DeleteProductCommand {
    private String id;
}
