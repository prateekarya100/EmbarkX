package com.tomcat.ecommerce.service;

import com.tomcat.ecommerce.model.Product;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface UploadImageService {
    Product saveImage(Long productId, MultipartFile image) throws IOException;
}
