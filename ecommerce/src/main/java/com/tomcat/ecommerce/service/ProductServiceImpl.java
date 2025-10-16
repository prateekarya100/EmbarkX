package com.tomcat.ecommerce.service;

import com.tomcat.ecommerce.exception.ResourceNotFoundException;
import com.tomcat.ecommerce.model.Category;
import com.tomcat.ecommerce.model.Product;
import com.tomcat.ecommerce.repository.CategoryRepository;
import com.tomcat.ecommerce.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService{

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Optional<Product> addProduct(Long categoryId, Product product) {
        Optional<Category> categoryById = Optional.ofNullable(categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("category","categoryId",categoryId)));
        product.setCategory(categoryById.get());

        // Giving 25% discount on price = 100 * 25 / 100
        product.setDiscount((product.getPrice() * 25/100));
        double discountedPrice =(product.getPrice() - (product.getPrice() * 25/100));
        product.setSpecialPrice(discountedPrice);
        product.setImage(product.getImage());
       return Optional.of(productRepository.save(product));
    }

    @Override
    public Optional<List<Product>> findingAllProducts() {
        return Optional.of(productRepository.findAll());
    }
}
