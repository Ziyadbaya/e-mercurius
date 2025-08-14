package com.emercurius.productservice.services;

import com.emercurius.commonlibs.dto.common.PageResponse;
import com.emercurius.commonlibs.dto.product.*;
import com.emercurius.commonlibs.exceptions.EntityNotFoundException;
import com.emercurius.productservice.entities.Product;
import com.emercurius.productservice.entities.ProductReview;
import com.emercurius.productservice.mapper.ProductMapper;
import com.emercurius.productservice.mapper.ProductReviewMapper;
import com.emercurius.productservice.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final ProductReviewMapper productReviewMapper;


    public ProductResponseDTO createProduct(ProductRequestDTO productRequestDTO) {
        Product product = productMapper.toEntity(productRequestDTO);
        Product savedProduct = productRepository.save(product);
        return productMapper.toDTO(savedProduct);
    }

    @Transactional
    public ProductReviewDto addProductReview(String productId, ProductReviewDto productReviewDto) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new EntityNotFoundException("Product not found with id " + productId));
        ProductReview productReview = productReviewMapper.toEntity(productReviewDto);
        product.addReview(productReview);
        productRepository.save(product);
        return productReviewDto;
    }

    public ProductResponseDTO updateProduct(String id, ProductRequestDTO requestDTO) {
        Product existingProduct = productRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Product not found with id " + id));
        Product updatedProduct = productMapper.partialUpdate(requestDTO, existingProduct);
        Product savedProduct = productRepository.save(updatedProduct);
        return productMapper.toDTO(savedProduct);
    }

    public ProductResponseDTO getProductById(String id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Product not found with id " + id));
        return productMapper.toDTO(product);
    }

    public PageResponse<ProductOverviewDto> getAllProducts(ProductFilter filter, Pageable pageable) {
        Page<Product> productPage = productRepository.findAllByFilter(filter, pageable);
        return PageResponse.from(productPage.map(productMapper::toOverviewDTO));
    }

}
