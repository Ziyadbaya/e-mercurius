package com.emercurius.commonlibs.dtos;

import com.emercurius.commonlibs.enumerations.PaymentMethod;
import com.emercurius.commonlibs.enumerations.PaymentStatus;
import org.springframework.format.annotation.DateTimeFormat;

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
