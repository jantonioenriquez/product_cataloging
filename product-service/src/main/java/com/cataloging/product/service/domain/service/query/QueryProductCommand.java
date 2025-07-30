package com.cataloging.product.service.domain.service.query;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class QueryProductCommand {
    private String id;
}
