package com.emercurius.commonlibs.dtos;

public record StripeResponse(
        String status,
        String message,
        String sessionId,
        String sessionUrl
) {
}
