package com.emercurius.commonlibs.dto.product;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

public record ProductReviewDto(
        @NotNull(message = "Rating is required")
        @Min(value = 1, message = "Rating must be at least 1")
        @Max(value = 5, message = "Rating must be at most 5")
        Integer rating,
        @Size(max = 1000, message = "Comment must not exceed 1000 characters")
        String comment,
        @NotNull(message = "User ID is required")
        Long userId,
        LocalDateTime createdDate,
        LocalDateTime lastModifiedDate
) {
}
