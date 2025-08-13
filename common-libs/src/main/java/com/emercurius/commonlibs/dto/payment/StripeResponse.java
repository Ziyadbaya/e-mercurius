package com.emercurius.commonlibs.dto.payment;

public record StripeResponse(
        String status,
        String message,
        String sessionId,
        String sessionUrl
) {
}
