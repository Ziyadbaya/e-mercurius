package com.emercurius.orderservice.mapper;

import com.emercurius.commonlibs.dtos.OrderItemRequestDTO;
import com.emercurius.commonlibs.dtos.OrderItemResponseDTO;

import com.emercurius.orderservice.entities.OrderItem;
import org.mapstruct.*;

@Mapper(componentModel = "spring",
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class OrderItemMapper {

    public abstract OrderItem toEntity(OrderItemRequestDTO requestDTO);

    public abstract OrderItemResponseDTO toDTO(OrderItem orderItem);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract OrderItem partialUpdate(OrderItemRequestDTO requestDTO, @MappingTarget OrderItem orderItem);

}
