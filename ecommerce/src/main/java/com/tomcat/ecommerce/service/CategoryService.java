package com.tomcat.ecommerce.service;

import com.tomcat.ecommerce.model.Category;
import com.tomcat.ecommerce.payload.CategoryDTO;
import com.tomcat.ecommerce.payload.CategoryResponseDTO;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    CategoryResponseDTO getAllCategories(int pageNumber, int pageSize,String sortBy, String sortDir);
    Optional<CategoryDTO> addCategory(CategoryDTO categoryDTO);
    Optional<Boolean> deleteCategory(Long categoryId);
    Optional<Boolean> updateCategory(long categoryId, CategoryDTO categoryDTO);

    // batch insertion of categories
    List<Category> addBatchCategories(@Valid List<Category> categories);

    Page<Category> fetchCategoriesWithPagination(int pageNumber, int pageSize, String sortBy, String sortDir);
}
