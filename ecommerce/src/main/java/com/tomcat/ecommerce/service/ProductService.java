package com.tomcat.ecommerce.service;

import com.tomcat.ecommerce.model.Product;
import com.tomcat.ecommerce.payload.ProductResponse;

import java.util.Optional;

public interface ProductService {
    Optional<Product> addProduct(Long categoryId, Product product);

    ProductResponse findingAllProducts();

    ProductResponse searchProductByCategory(Long categoryId);

    ProductResponse searchProductsByMatchingKeywords(String keyword);

    Optional<Product> updateExistingProductInfo(Long productId, Product product);

    boolean deleteProductInfoById(Long productId);
}
