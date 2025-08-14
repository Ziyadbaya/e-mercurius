package com.emercurius.commonlibs.dto.payment;

import com.emercurius.commonlibs.enums.PaymentMethod;
import com.emercurius.commonlibs.enums.PaymentStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record PaymentResponseDTO(
        Long id,
        BigDecimal amount,
        PaymentStatus status,
        PaymentMethod paymentMethod,
        Long orderId,
        Long userId,
        LocalDateTime createdDate,
        LocalDateTime lastModifiedDate
) {
}
