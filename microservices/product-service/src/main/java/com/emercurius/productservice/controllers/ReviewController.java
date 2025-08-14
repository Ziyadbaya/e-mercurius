package com.emercurius.productservice.controllers;

import com.emercurius.commonlibs.dto.product.ProductResponseDTO;
import com.emercurius.commonlibs.dto.product.ProductReviewDto;
import com.emercurius.productservice.services.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/product/review")
@RequiredArgsConstructor
public class ReviewController {

    private final ProductService productService;

    @PostMapping("/{id}")
    public ResponseEntity<ProductReviewDto> addReview(@PathVariable(name = "id") String productId,
                                                             @Valid @RequestBody ProductReviewDto productReviewDto) {
        var response = productService.addProductReview(productId, productReviewDto);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}
