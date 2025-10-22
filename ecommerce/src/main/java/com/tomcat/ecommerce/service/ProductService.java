package com.tomcat.ecommerce.service;

import com.tomcat.ecommerce.model.Product;
import com.tomcat.ecommerce.payload.ProductDTO;
import com.tomcat.ecommerce.payload.ProductPaginationDTO;
import com.tomcat.ecommerce.payload.ProductResponse;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.Optional;

public interface ProductService {
    Optional<Product> addProduct(Long categoryId, Product product);

    ProductResponse findingAllProducts();

    ProductResponse searchProductByCategory(Long categoryId);

    ProductResponse searchProductsByMatchingKeywords(String keyword);

    Optional<Product> updateExistingProductInfo(Long productId, Product product);

    boolean deleteProductInfoById(Long productId);

    Optional<ProductDTO> updateProductImageByProductId(Long productId, MultipartFile image) throws IOException;

    ProductPaginationDTO getPaginatedProducts(int pageNumber, int pageSize);
}
