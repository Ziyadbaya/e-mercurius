package com.emercurius.commonlibs.dtos.errors;


public record ErrorResponse(
        String message,
        int errorCode
) {
}
