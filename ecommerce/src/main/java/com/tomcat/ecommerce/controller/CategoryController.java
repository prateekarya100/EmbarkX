package com.tomcat.ecommerce.controller;

import com.tomcat.ecommerce.model.Category;
import com.tomcat.ecommerce.model.category.CategoryResponseDTO;
import com.tomcat.ecommerce.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

//    public CategoryController(CategoryService categoryService) {
//        this.categoryService = categoryService;
//    }

    @GetMapping(value = "/public/categories")
    public List<Category> getAllCategories() {
        return categoryService.getAllCategories();
    }

    @PostMapping(value = "/admin/categories")
    public ResponseEntity<CategoryResponseDTO> addCategory(@RequestBody Category category) {
        Optional<Category> optionalCategory = categoryService.addCategory(category);
        if (optionalCategory.isPresent()){
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(new CategoryResponseDTO("Category added successfully"));
        }else {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new CategoryResponseDTO("Failed to add category,please try again"));
        }
    }


    @DeleteMapping(value = "/admin/delete/categories/{categoryId}")
    public ResponseEntity<CategoryResponseDTO> deleteCategory(@PathVariable long categoryId) {
        Optional<Boolean> isDeleted = categoryService.deleteCategory(categoryId);
        if (isDeleted.isPresent() && isDeleted.get()){
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new CategoryResponseDTO("Category deleted successfully"));
        }else {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new CategoryResponseDTO("Category not found with id: " + categoryId));
        }
    }

    // update category - future enhancement
    @PutMapping(value = "/admin/update/categories/{categoryId}")
    public ResponseEntity<CategoryResponseDTO> updateCategory(@PathVariable long categoryId, @RequestBody Category category) {
        Optional<Boolean> updateCategoryOptional = categoryService.updateCategory(categoryId, category);
        if (updateCategoryOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.ACCEPTED)
                    .body(new CategoryResponseDTO("Category updated successfully"));
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new CategoryResponseDTO("Category not found with id: " + categoryId));
        }
    }

}
