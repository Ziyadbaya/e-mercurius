package com.emercurius.productservice.repositories;

import com.emercurius.commonlibs.dto.product.ProductFilter;
import com.emercurius.productservice.entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductCustomRepository {
    Page<Product> findAllByFilter(ProductFilter filter, Pageable pageable);
}
