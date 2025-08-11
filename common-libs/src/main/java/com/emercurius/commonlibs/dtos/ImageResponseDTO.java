package com.emercurius.commonlibs.dtos;

public record ImageResponseDTO(
        String url,
        String altText,
        boolean mainImage
) {
}
