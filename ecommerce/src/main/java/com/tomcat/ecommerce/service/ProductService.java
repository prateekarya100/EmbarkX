package com.tomcat.ecommerce.service;

import com.tomcat.ecommerce.model.Product;
import com.tomcat.ecommerce.payload.ProductResponse;

import java.util.Optional;
import java.util.stream.Stream;

public interface ProductService {
    Optional<Product> addProduct(Long categoryId, Product product);

   ProductResponse findingAllProducts();
}
