package com.emercurius.commonlibs.dto.order;

import java.util.List;

public record OrderResponseDTO(

        Long id,

        List<OrderItemResponseDTO> items,

        Long userId

) {
}
