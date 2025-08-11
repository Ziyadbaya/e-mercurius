package com.emercurius.commonlibs.dtos;

import jakarta.validation.constraints.NotBlank;

public record ImageRequestDTO(
        @NotBlank(message = "Image URL is required")
        String url,

        String altText,

        boolean mainImage
) {
}
