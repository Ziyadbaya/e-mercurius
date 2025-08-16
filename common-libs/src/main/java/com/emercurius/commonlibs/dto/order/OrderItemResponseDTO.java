package com.emercurius.commonlibs.dto.order;

public record OrderItemResponseDTO(

        Long id,

        Long totalAmount,

        String productName,

        int quantity,

        Long orderId,

        Long userId,

        String productId
) {
}
