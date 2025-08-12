package com.emercurius.productservice.mapper;

import com.emercurius.commonlibs.dtos.CategoryRequestDTO;
import com.emercurius.commonlibs.dtos.CategoryResponseDTO;
import com.emercurius.productservice.entities.Category;
import org.mapstruct.*;

@Mapper(componentModel = "spring",
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        unmappedTargetPolicy = org.mapstruct.ReportingPolicy.IGNORE)
public abstract class CategoryMapper {

    @Mapping(target = "id", ignore = true)
    public abstract Category toEntity(CategoryRequestDTO requestDTO);

    public abstract CategoryResponseDTO toDTO(Category category);

    @Mapping(target = "id", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract Category partialUpdate(CategoryRequestDTO requestDTO, @MappingTarget Category category);

}
