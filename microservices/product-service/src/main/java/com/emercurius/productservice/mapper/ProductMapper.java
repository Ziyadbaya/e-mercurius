package com.emercurius.productservice.mapper;

import com.emercurius.commonlibs.dto.product.ProductOverviewDto;
import com.emercurius.commonlibs.dto.product.ProductRequestDTO;
import com.emercurius.commonlibs.dto.product.ProductResponseDTO;
import com.emercurius.commonlibs.exceptions.EntityNotFoundException;
import com.emercurius.productservice.entities.Category;
import com.emercurius.productservice.entities.Product;
import com.emercurius.productservice.repositories.CategoryRepository;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring",
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        unmappedTargetPolicy = org.mapstruct.ReportingPolicy.IGNORE)
public abstract class ProductMapper {

    @Autowired
    private CategoryRepository categoryRepository;


    @Mapping(target = "id", ignore = true)
    @Mapping(target = "category", source = "categoryId", qualifiedByName = "mapCategory")
    public abstract Product toEntity(ProductRequestDTO requestDTO);

    public abstract ProductResponseDTO toDTO(Product product);

    public abstract ProductOverviewDto toOverviewDTO(Product product);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "category", source = "categoryId", qualifiedByName = "mapCategory")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract Product partialUpdate(ProductRequestDTO requestDTO, @MappingTarget Product product);

    @Named("mapCategory")
    protected Category mapCategory(String categoryId) {
        if (categoryId == null) return null;
        return categoryRepository.findById(categoryId)
                .orElseThrow(() -> new EntityNotFoundException("Category not found: " + categoryId));
    }

}
