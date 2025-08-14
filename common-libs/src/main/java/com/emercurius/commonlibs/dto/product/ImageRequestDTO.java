package com.emercurius.commonlibs.dto.product;

import jakarta.validation.constraints.NotBlank;

public record ImageRequestDTO(
        @NotBlank(message = "Image URL is required")
        String url,

        String altText,

        boolean mainImage
) {
}
