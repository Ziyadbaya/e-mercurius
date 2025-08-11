package com.emercurius.productservice.mapper;

import com.emercurius.commonlibs.dtos.ProductRequestDTO;
import com.emercurius.commonlibs.dtos.ProductResponseDTO;
import com.emercurius.productservice.entities.Product;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;

@Mapper(componentModel = "spring",
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        unmappedTargetPolicy = org.mapstruct.ReportingPolicy.IGNORE)
public abstract class ProductMapper {

    public abstract Product toEntity(ProductRequestDTO requestDTO);

    public abstract ProductResponseDTO toDTO(Product product);

}
