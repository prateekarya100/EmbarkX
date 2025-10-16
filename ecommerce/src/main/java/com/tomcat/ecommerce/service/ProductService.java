package com.tomcat.ecommerce.service;

import com.tomcat.ecommerce.model.Product;

import java.util.Optional;

public interface ProductService {
    Optional<Product> addProduct(Long categoryId, Product product);
}
