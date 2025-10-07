package com.tomcat.ecommerce.service;

import com.tomcat.ecommerce.model.Category;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    List<Category> getAllCategories();
    Optional<Category> addCategory(Category category);
    Optional<Boolean> deleteCategory(Long categoryId);
    Optional<Boolean> updateCategory(long categoryId, Category category);

    // batch insertion of categories
    List<Category> addBatchCategories(@Valid List<Category> categories);

    Page<Category> fetchCategoriesWithPagination(int pageNumber, int pageSize);
}
