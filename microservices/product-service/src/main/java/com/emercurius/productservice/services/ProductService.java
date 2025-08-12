package com.emercurius.productservice.services;

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

    public ProductResponseDTO createProduct(ProductRequestDTO productRequestDTO) {
        Product product = productMapper.toEntity(productRequestDTO);
        Product savedProduct = productRepository.save(product);
        return productMapper.toDTO(savedProduct);
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

}
