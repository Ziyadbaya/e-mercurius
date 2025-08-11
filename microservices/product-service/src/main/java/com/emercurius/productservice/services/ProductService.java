package com.emercurius.productservice.services;

import com.emercurius.commonlibs.dtos.PaymentResponseDTO;
import com.emercurius.commonlibs.dtos.ProductRequestDTO;
import com.emercurius.commonlibs.dtos.ProductResponseDTO;
import com.emercurius.commonlibs.exceptions.EntityNotFoundException;
import com.emercurius.productservice.entities.Product;
import com.emercurius.productservice.mapper.ProductMapper;
import com.emercurius.productservice.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public ProductResponseDTO createProduct(ProductRequestDTO paymentRequestDto) {
        Product product = productMapper.toEntity(paymentRequestDto);
        Product savedProduct = productRepository.save(product);
        return productMapper.toDTO(savedProduct);
    }

    private ProductResponseDTO getProductById(String id) {
        Product payment = productRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Product not found with id " + id));
        return productMapper.toDTO(payment);
    }

}
