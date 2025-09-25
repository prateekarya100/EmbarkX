package com.tomcat.ecommerce.service.category;

import com.tomcat.ecommerce.models.category.Category;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService{

    List<Category> categoryList = new ArrayList<>(List.of(
            new Category(1L, "Electronics"),
            new Category(2L, "Books"),
            new Category(3L, "Clothing"),
            new Category(4L, "Home & Kitchen")
    ));

    @Override
    public List<Category> getAllCategories() {
        return categoryList;
    }
}
