package com.tomcat.ecommerce.controller;

import com.tomcat.ecommerce.model.Product;
import com.tomcat.ecommerce.payload.ApiResponse;
import com.tomcat.ecommerce.payload.ProductDTO;
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

    @PostMapping(value = "/admin/categories/{categoryId}")
    public ResponseEntity<ApiResponse> addProduct(
                                        @RequestBody Product product,
                                        @PathVariable Long categoryId){
        Optional<Product> productOptional = productService.addProduct(categoryId, product);
        if(productOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new ApiResponse("product added successfully.",HttpStatus.CREATED.toString()));
        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ApiResponse("facing issues while adding product, please contact to the author of this service.",HttpStatus.BAD_REQUEST.toString()));
        }
    }
}
