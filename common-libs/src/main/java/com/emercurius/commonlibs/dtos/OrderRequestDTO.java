package com.emercurius.commonlibs.dtos;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.util.List;

public record OrderRequestDTO(

        @NotNull(message = "UserId cannot be null")
        Long userId,

        @NotEmpty(message = "Items cannot be empty")
        List<@Valid OrderItemRequestDTO> items

) {
}
