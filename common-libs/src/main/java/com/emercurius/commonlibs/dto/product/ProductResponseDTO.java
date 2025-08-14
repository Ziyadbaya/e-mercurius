package com.emercurius.commonlibs.dto.product;

import java.math.BigDecimal;
import java.util.List;

public record ProductResponseDTO(
        String id,
        String name,
        String description,
        BigDecimal price,
        Integer stockQuantity,
        CategoryResponseDTO category,
        List<ImageResponseDTO> images,
        List<String> tags,
        boolean active,
        List<ProductReviewDto> reviews
) {
}
