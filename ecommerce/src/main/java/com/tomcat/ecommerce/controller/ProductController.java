package com.tomcat.ecommerce.controller;

import com.tomcat.ecommerce.model.Product;
import com.tomcat.ecommerce.payload.ApiResponse;
import com.tomcat.ecommerce.payload.ProductResponse;
import com.tomcat.ecommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api")
public class ProductController {

    @Autowired
    private ProductService productService;

    // adding product into product-catalog based on category id by admin
    @PostMapping(value = "/admin/categories/{categoryId}/product")
    public ResponseEntity<ApiResponse> addProduct(
                                        @RequestBody Product product,
                                        @PathVariable Long categoryId){
        Optional<Product> productOptional = productService.addProduct(categoryId, product);
        if(productOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new ApiResponse("product added successfully.",HttpStatus.CREATED.toString()));
        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ApiResponse("facing issues while adding product, " +
                            "please contact to the author of this service.",HttpStatus.BAD_REQUEST.toString()));
        }
    }

    // getting all products list at once, with their category details
    @GetMapping(value = "/public/products")
    public ResponseEntity<?> getAllProducts() {
        ProductResponse response = productService.findingAllProducts();

        if (response.getContent() == null || response.getContent().isEmpty()) {
            return ResponseEntity.ok(
                    new ApiResponse("No products available, please add some products.", "INFO")
            );
        }

        return ResponseEntity.status(HttpStatus.OK)
                .body(response);
    }

}
