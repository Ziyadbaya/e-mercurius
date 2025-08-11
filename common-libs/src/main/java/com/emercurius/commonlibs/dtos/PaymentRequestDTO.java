package com.emercurius.commonlibs.dtos;

import com.emercurius.commonlibs.enumerations.PaymentMethod;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;


import java.math.BigDecimal;

public record PaymentRequestDTO(

        @NotNull(message = "Amount cannot be null")
        @Positive(message = "Amount must be positive")
        BigDecimal amount,

        @NotNull(message = "Payment Method cannot be null")
        PaymentMethod paymentMethod,

        @NotNull(message = "Order ID cannot be null")
        Long orderId,

        @NotNull(message = "User ID cannot be null")
        Long userId
) {
}
