package com.emercurius.productservice.controllers;

import com.emercurius.commonlibs.dto.common.PageResponse;
import com.emercurius.commonlibs.dto.product.ProductFilter;
import com.emercurius.commonlibs.dto.product.ProductOverviewDto;
import com.emercurius.commonlibs.dto.product.ProductRequestDTO;
import com.emercurius.commonlibs.dto.product.ProductResponseDTO;
import com.emercurius.productservice.services.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;


    @PostMapping
    public ResponseEntity<ProductResponseDTO> createProduct(@Valid @RequestBody ProductRequestDTO productRequestDTO) {
        var response = productService.createProduct(productRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> updateProduct(@PathVariable String id,
                                                            @RequestBody ProductRequestDTO requestDTO) {
        return ResponseEntity.ok(productService.updateProduct(id, requestDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> getProduct(@PathVariable String id) {
        return ResponseEntity.ok(productService.getProductById(id));
    }

    @GetMapping
    public PageResponse<ProductOverviewDto> getProducts(ProductFilter filter,
                                                        @PageableDefault(size = 20, sort = "name") Pageable pageable) {
        return productService.getAllProducts(filter, pageable);
    }

}
