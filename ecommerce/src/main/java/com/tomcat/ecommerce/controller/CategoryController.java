package com.tomcat.ecommerce.controller;

import com.tomcat.ecommerce.exception.ResourceNotFoundException;
import com.tomcat.ecommerce.model.Category;
import com.tomcat.ecommerce.model.dto.CategoryPaginatedDTO;
import com.tomcat.ecommerce.model.dto.Response;
import com.tomcat.ecommerce.model.dto.payload.CategoryDTO;
import com.tomcat.ecommerce.model.dto.payload.CategoryResponseDTO;
import com.tomcat.ecommerce.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

//    public CategoryController(CategoryService categoryService) {
//        this.categoryService = categoryService;
//    }

    @GetMapping(value = "/public/categories")
    public CategoryResponseDTO getAllCategories() {
        return categoryService.getAllCategories();
    }

    @PostMapping(value = "/admin/categories")
    public ResponseEntity<Response> addCategory(@Valid @RequestBody CategoryDTO categoryDTO) {
        Optional<CategoryDTO> optionalCategory = categoryService.addCategory(categoryDTO);
        if (optionalCategory.isPresent()){
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(new Response("Category added successfully"));
        }else {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new Response("Failed to add category,please try again"));
        }
    }


    @DeleteMapping(value = "/admin/delete/categories/{categoryId}")
    public ResponseEntity<Response> deleteCategory(@PathVariable long categoryId) {
        Optional<Boolean> isDeleted = Optional.ofNullable(categoryService.deleteCategory(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("sorry category not found with the id: " + categoryId+", please try again with valid category id.")));
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new Response("category deleted successfully"));
    }

    // update category - future enhancement
    @PutMapping(value = "/admin/update/categories/{categoryId}")
    public ResponseEntity<Response> updateCategory(@PathVariable long categoryId, @Valid @RequestBody CategoryDTO categoryDTO) {
        Optional<Boolean> updateCategoryOptional = Optional.ofNullable(categoryService.updateCategory(categoryId, categoryDTO)
                .orElseThrow(() -> new ResourceNotFoundException("category","categoryId", categoryId))); // custom exception created
        return ResponseEntity.status(HttpStatus.OK)
                    .body(new Response("category updated successfully"));
    }

    // batch category insertion
    @PostMapping(value = "/admin/batch/categories")
    public ResponseEntity<Response> addCategories(@Valid @RequestBody List<Category> categories) {
        List<Category> savedCategories = categoryService.addBatchCategories(categories);
        if (!savedCategories.isEmpty()){
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(new Response("Categories added successfully"));
        }else {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new Response("Failed to add categories,please try again"));
        }
    }

    
    /* PAGINATION, SORTING, FILTERING, SEARCHING
    * -------------------------------------------
    * pageNumber: 0
    * pageSize: 10
    * totalPages: 100
    * totalElements: 1000
    * content: []
    * lastPage: true/false
    * firstPage: true/false
    * numberOfElements: 10
    * empty: true/false
    * sort: {}
    * sortBy: categoryName
    * sortDir: asc/desc
    * filterBy: categoryName
    * filterValue: Electronics
    * searchBy: categoryName
    * */

    // return dto list of categories with api metadata
    // like pageNumber, pageSize, totalPages, totalElements, lastPage, firstPage, numberOfElements, empty
        @GetMapping("/public/categories/pagination")
        public ResponseEntity<?> getCategoriesWithPagination(
                @RequestParam(defaultValue = "0") int pageNumber,
                @RequestParam(defaultValue = "5") int pageSize
        ) {
            Page<Category> page = categoryService.fetchCategoriesWithPagination(pageNumber, pageSize);

            CategoryPaginatedDTO categoryPaginatedDTO = new CategoryPaginatedDTO();
                        categoryPaginatedDTO.setCurrentPage(page.getNumber());
                        categoryPaginatedDTO.setPageSize(page.getSize());
                        categoryPaginatedDTO.setTotalPages(page.getTotalPages());
                        categoryPaginatedDTO.setTotalElements(page.getTotalElements());
                        categoryPaginatedDTO.setLastPage(page.isLast());
                        categoryPaginatedDTO.setCategories(page.getContent());

                        return ResponseEntity
                                .status(HttpStatus.OK)
                                .body(categoryPaginatedDTO);
        }

}
