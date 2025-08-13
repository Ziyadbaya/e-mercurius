package com.emercurius.commonlibs.dto.errors;


public record ErrorResponse(
        String message,
        int errorCode
) {
}
