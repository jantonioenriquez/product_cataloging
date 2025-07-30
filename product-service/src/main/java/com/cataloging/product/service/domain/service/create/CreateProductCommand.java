package com.cataloging.product.service.domain.service.create;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class CreateProductCommand {
    private String name;
    private String email;
}
