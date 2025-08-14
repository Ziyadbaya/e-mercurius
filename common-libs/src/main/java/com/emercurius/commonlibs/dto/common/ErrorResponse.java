package com.emercurius.commonlibs.dto.common;


public record ErrorResponse(
        String message,
        int errorCode
) {
}
