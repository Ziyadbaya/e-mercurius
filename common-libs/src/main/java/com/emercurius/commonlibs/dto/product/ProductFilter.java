package com.emercurius.commonlibs.dto.product;

import java.math.BigDecimal;
import java.util.List;

public record ProductFilter(
        String name,
        String categoryId,
        BigDecimal minPrice,
        BigDecimal maxPrice,
        Boolean active,
        List<String> tags
) {
}
