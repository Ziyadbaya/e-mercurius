package com.emercurius.commonlibs.dto.product;

public record ImageResponseDTO(
        String url,
        String altText,
        boolean mainImage
) {
}
