package com.tomcat.ecommerce.service;

import com.tomcat.ecommerce.model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    Optional<Product> addProduct(Long categoryId, Product product);

    Optional<List<Product>> findingAllProducts();
}
