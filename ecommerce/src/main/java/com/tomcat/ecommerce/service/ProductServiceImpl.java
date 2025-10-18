package com.tomcat.ecommerce.service;

import com.tomcat.ecommerce.exception.ResourceNotFoundException;
import com.tomcat.ecommerce.model.Category;
import com.tomcat.ecommerce.model.Product;
import com.tomcat.ecommerce.payload.ProductDTO;
import com.tomcat.ecommerce.payload.ProductResponse;
import com.tomcat.ecommerce.repository.CategoryRepository;
import com.tomcat.ecommerce.repository.ProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.*;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Service
public class ProductServiceImpl implements ProductService{

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ModelMapper modelMapper;

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
    public ProductResponse findingAllProducts() {
        List<ProductDTO> productResponses = productRepository.findAll().stream()
                .map(product -> modelMapper.map(product,ProductDTO.class))
                .toList();
       return new ProductResponse(productResponses);
    }

    @Override
    public ProductResponse getProductsByCategory(Long categoryId) {
        List<Product> products = productRepository.findProductByCategory_Id(categoryId);
        return new ProductResponse(products.stream()
                .map(product -> modelMapper.map(product, ProductDTO.class)).toList());
    }
}
