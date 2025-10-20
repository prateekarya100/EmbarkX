package com.tomcat.ecommerce.service;

import com.tomcat.ecommerce.exception.ResourceNotFoundException;
import com.tomcat.ecommerce.model.Category;
import com.tomcat.ecommerce.model.Product;
import com.tomcat.ecommerce.payload.ApiResponse;
import com.tomcat.ecommerce.payload.ProductDTO;
import com.tomcat.ecommerce.payload.ProductResponse;
import com.tomcat.ecommerce.repository.CategoryRepository;
import com.tomcat.ecommerce.repository.ProductRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
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

    @Autowired
    private ModelMapper modelMapper;

    // discount calculation
    double discount=0;
    double discountedPrice=0;

    @Override
    public Optional<Product> addProduct(Long categoryId, Product product) {
        Optional<Category> categoryById = Optional.ofNullable(categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("category","categoryId",categoryId)));
        product.setCategory(categoryById.get());

        // Giving 25% discount on price = 100 * 25 / 100
        discount = (product.getPrice() * product.getDiscount()/100);
        product.setDiscount(discount);
        discountedPrice =(product.getPrice() - discount);
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
    public ProductResponse searchProductByCategory(Long categoryId) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("category","categoryId",categoryId));
        List<Product> productCategory = productRepository.findByCategory(category);

        List<ProductDTO> productDTOS = productCategory.stream()
                .map(product -> modelMapper.map(product,ProductDTO.class)).toList();
        ProductResponse productResponse = new ProductResponse();
        productResponse.setContent(productDTOS);
        return productResponse;
    }

    @Override
    public ProductResponse searchProductsByMatchingKeywords(String keyword) {
        List<Product> products = productRepository.findByProductNameLikeIgnoreCase("%"+keyword+"%");
        List<ProductDTO> productDTOS = products.stream()
                .map(product -> modelMapper.map(product,ProductDTO.class))
                .toList();
        return new ProductResponse(productDTOS);
    }

    @Override
    public Optional<Product> updateExistingProductInfo(Long productId, Product product) {
        Product existedProduct = productRepository.findById(productId)
                .orElseThrow(()->new ResourceNotFoundException("product","productId",productId));

        // BeansUtil setting all field properties in one go without setter mapper
        BeanUtils.copyProperties(product,existedProduct,"productId"); // ignoreProperty :: productId
        discount = product.getPrice() * product.getDiscount()/100;
        existedProduct.setSpecialPrice(existedProduct.getPrice() - discount);
        productRepository.save(existedProduct);
        return Optional.of(existedProduct);
    }

    @Override
    @Transactional
    public boolean deleteProductInfoById(Long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("product", "productId", productId));
//        productRepository.deleteById(product.getProductId());
        productRepository.deleteByProductId(productId);
        return true;
    }


}
