package com.emercurius.commonlibs.dto.product;

import java.math.BigDecimal;
import java.util.List;

public record ProductOverviewDto(
        String id,
        String name,
        String description,
        BigDecimal price,
        String currency,
        CategoryRequestDTO category,
        List<ImageRequestDTO> images,
        List<String> tags,
        Double averageRating,
        Integer reviewCounter
) {
}
