package com.emercurius.productservice.controllers;

import com.emercurius.commonlibs.dtos.CategoryRequestDTO;
import com.emercurius.commonlibs.dtos.CategoryResponseDTO;
import com.emercurius.productservice.services.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/product/category")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping
    public ResponseEntity<CategoryResponseDTO> createCategory(
            @Valid @RequestBody CategoryRequestDTO categoryRequestDto) {
        CategoryResponseDTO response = categoryService.createCategory(categoryRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponseDTO> getCategoryById(@PathVariable String id) {
        CategoryResponseDTO response = categoryService.getCategoryById(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryResponseDTO> updateCategory(@PathVariable(name = "id") String id,
                                                            @RequestBody CategoryRequestDTO categoryRequestDto) {
        CategoryResponseDTO response = categoryService.updateCategory(id,categoryRequestDto);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
    
}
