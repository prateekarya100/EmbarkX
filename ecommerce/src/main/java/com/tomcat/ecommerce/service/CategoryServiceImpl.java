package com.tomcat.ecommerce.service;

import com.tomcat.ecommerce.model.Category;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService{

    List<Category> categories = new ArrayList<>(List.of(
            new Category(1L, "Electronics"),
            new Category(2L, "Books"),
            new Category(3L, "Clothing") ,
            new Category(4L, "Home & Kitchen"),
            new Category(5L, "Sports & Outdoors"),
            new Category(6L, "Health & Personal Care"),
            new Category(7L, "Toys & Games")
    ));

    @Override
    public List<Category> getAllCategories() {
        return categories;
    }

    @Override
    public Optional<Category> addCategory(Category category) {
        category.setCategoryId((long) (categories.size() + 1));
        categories.add(category);
        return Optional.of(category);
    }

    @Override
    public Optional<Boolean> deleteCategory(Long categoryId) {
//        boolean isRemoved = categories.removeIf(category -> category.getCategoryId() == categoryId);
        return categories.stream()
                .filter(category -> category.getCategoryId() == categoryId)
                .findFirst()
                .map(category -> {
                    categories.remove(category);
                    return true;
                });
//        return Optional.of(isRemoved);
    }


}
