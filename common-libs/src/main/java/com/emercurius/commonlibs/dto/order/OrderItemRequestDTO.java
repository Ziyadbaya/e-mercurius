package com.emercurius.commonlibs.dto.order;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record OrderItemRequestDTO(

        @NotNull(message = "TotalAmount cannot be null")
        @Positive(message = "TotalAmount must be positive")
        BigDecimal totalAmount,

        @NotNull(message = "ProductName cannot be null")
        String productName,

        @NotNull(message = "Quantity cannot be null")
        @Positive(message = "Quantity must be positive")
        Long quantity,

        @NotNull(message = "ProductId cannot be null")
        String productId
) {
}
