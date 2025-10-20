package com.tomcat.ecommerce.controller;

import com.tomcat.ecommerce.model.Product;
import com.tomcat.ecommerce.payload.ApiResponse;
import com.tomcat.ecommerce.payload.ProductDTO;
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

    // getting all products based on their category id
    @GetMapping(value = "/public/categories/{categoryId}/products")
    public ProductResponse getAllProductsByCategory(@PathVariable Long categoryId){
       return productService.searchProductByCategory(categoryId);
    }

    // getting all products based on search % keyword %
    @GetMapping(value = "/public/products/keyword/{keyword}")
    public ResponseEntity<ProductResponse> getProductsByKeywordsMatching(@PathVariable(required = false) String keyword){
        ProductResponse productResponse = productService.searchProductsByMatchingKeywords(keyword);
        return ResponseEntity.status(HttpStatus.FOUND)
                .body(productResponse);
    }

    // updating product based on product-id given by admin
    @PutMapping(value = "/admin/products/{productId}")
    public ResponseEntity<ApiResponse> updatingProductInfoById(
                                                                @PathVariable Long productId,
                                                                @RequestBody Product product){
        Optional<Product> productOptional = productService.updateExistingProductInfo(productId,product);
        if(productOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.ACCEPTED)
                    .body(new ApiResponse("product information updated successfully.",
                            HttpStatus.ACCEPTED.toString()));
        }else {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE)
                    .body(new ApiResponse("causing issues while updating product information, please contact with author.",
                            HttpStatus.NOT_ACCEPTABLE.toString()));
        }
    }

    // deleting product from db by admin by product-id
    @DeleteMapping(value = "/admin/products/{productId}")
    public ResponseEntity<ApiResponse> deleteProductAndCategoryAssociatedById(@PathVariable Long productId){
        boolean isDeleted = productService.deleteProductInfoById(productId);
        if (isDeleted){
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ApiResponse("product deleted successfully.",HttpStatus.OK.toString()));
        }else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ApiResponse("while deletion facing some issues please contact with your website author."
                            ,HttpStatus.BAD_REQUEST.toString()));
        }
    }
}
