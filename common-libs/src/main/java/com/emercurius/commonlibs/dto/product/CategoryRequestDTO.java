package com.emercurius.commonlibs.dto.product;

import jakarta.validation.constraints.NotBlank;

public record CategoryRequestDTO(
        @NotBlank(message = "Name is required")
        String name,
        String description
) {
}
