package com.emercurius.commonlibs.dtos;

import java.util.List;

public record OrderResponseDTO(

        Long id,

        List<OrderItemResponseDTO> items,

        Long userId

) {
}
