package com.emercurius.productservice.mapper;

import com.emercurius.commonlibs.dto.product.ProductReviewDto;
import com.emercurius.productservice.entities.ProductReview;
import org.mapstruct.*;

@Mapper(componentModel = "spring",
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class ProductReviewMapper {

    public abstract ProductReview toEntity(ProductReviewDto requestDTO);

    public abstract ProductReviewDto toDTO(ProductReview productReview);

}
