package com.emercurius.productservice.services;

import com.emercurius.commonlibs.dtos.CategoryResponseDTO;
import com.emercurius.commonlibs.dtos.CategoryRequestDTO;
import com.emercurius.commonlibs.exceptions.EntityNotFoundException;
import com.emercurius.productservice.entities.Category;
import com.emercurius.productservice.mapper.CategoryMapper;
import com.emercurius.productservice.repositories.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    public CategoryResponseDTO getCategoryById(String id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Category not found with id " + id));
        return categoryMapper.toDTO(category);
    }

    public CategoryResponseDTO createCategory(CategoryRequestDTO categoryRequestDto) {
        Category category = categoryMapper.toEntity(categoryRequestDto);
        Category savedCategory = categoryRepository.save(category);
        return categoryMapper.toDTO(savedCategory);
    }

    public CategoryResponseDTO updateCategory(String id, CategoryRequestDTO categoryRequestDto) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Category not found with id " + id));
        Category updatedCategory = categoryMapper.partialUpdate(categoryRequestDto, category);
        categoryRepository.save(updatedCategory);
        return categoryMapper.toDTO(updatedCategory);
    }

}
