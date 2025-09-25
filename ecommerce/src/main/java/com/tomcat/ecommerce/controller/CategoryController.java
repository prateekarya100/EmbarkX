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
        Optional<Category> isAdded = categoryService.addCategory(category);
        CategoryResponseDTO responseDTO = new CategoryResponseDTO();
        if(isAdded.isPresent()){
            System.out.println( "updated categories :: " + getAllCategories());
            responseDTO.setResponse("Category added successfully");
            return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
        } else {
            responseDTO.setResponse("Failed to add category");
            return new ResponseEntity<>(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(value = "/admin/delete/categories/{categoryId}")
    public ResponseEntity<CategoryResponseDTO> deleteCategory(@PathVariable long categoryId) {
        Optional<Boolean> isDeleted = categoryService.deleteCategory(categoryId);
        CategoryResponseDTO responseDTO = new CategoryResponseDTO();
        if(isDeleted.isPresent() && isDeleted.get()){
            System.out.println( "updated categories :: " + getAllCategories());
            responseDTO.setResponse("Category deleted successfully");
            return new ResponseEntity<>(responseDTO, HttpStatus.OK);
        } else {
            responseDTO.setResponse("Failed to delete category");
            return new ResponseEntity<>(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
