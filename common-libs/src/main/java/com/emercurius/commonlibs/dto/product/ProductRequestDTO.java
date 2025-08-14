package com.emercurius.commonlibs.dto.product;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record ProductRequestDTO(
        @NotBlank(message = "Name is required")
        String name,

        String description,

        @NotNull(message = "Price is required")
        @DecimalMin(value = "0.0", inclusive = false, message = "Price must be positive")
        BigDecimal price,

        @NotNull(message = "Stock quantity is required")
        @Min(value = 0, message = "Stock quantity must be zero or positive")
        Integer stockQuantity,

        @NotBlank(message = "Category ID is required")
        String categoryId,

        List<ImageRequestDTO> images,

        List<@NotBlank(message = "Tags cannot contain blank") String> tags,

        boolean active,

        LocalDateTime createdDate,

        LocalDateTime lastModifiedDate
) {
}
