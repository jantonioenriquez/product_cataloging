package com.cataloging.product.service.domain.service.update;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class UpdateProductCommand {
    private String id;
    private String name;
    private String email;
    private String status;
}
