package com.emercurius.orderservice.mapper;

import com.emercurius.commonlibs.dtos.OrderRequestDTO;
import com.emercurius.commonlibs.dtos.OrderResponseDTO;
import com.emercurius.orderservice.entities.Order;
import org.mapstruct.*;

@Mapper(componentModel = "spring",
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class OrderMapper {

    public abstract OrderResponseDTO toDTO(Order order);

    @Mapping(target = "items", ignore = true)
    public abstract Order toEntity(OrderRequestDTO requestDTO);
    
}
