package com.tomcat.ecommerce.service;

import com.tomcat.ecommerce.exception.ResourceNotFoundException;
import com.tomcat.ecommerce.model.Product;
import com.tomcat.ecommerce.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Service
public class UploadImageServiceImpl implements UploadImageService{

    @Autowired
    private ProductRepository productRepository;

    @Value("${product.image.upload.dir}")
    private String uploadDir;

    public Product saveImage(Long productId, MultipartFile image) throws IOException {

        // create uploads folder if it doesn’t exist
        Path uploadPath = Paths.get(uploadDir);
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        // fetch product from DB
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("product", "productId", productId));

        //  Create a clean, unique filename
        String originalName = image.getOriginalFilename();
        String extension = "";
        if (originalName != null && originalName.contains(".")) {
            extension = originalName.substring(originalName.lastIndexOf("."));
        }

        String uniqueFileName = UUID.randomUUID().toString() + extension;

        //  Delete existing image if present
        String existingImagePath = product.getImage();
        if (existingImagePath != null && !existingImagePath.isEmpty()) {
            try {
                Path existingPath = Paths.get(existingImagePath);
                Files.deleteIfExists(existingPath);
            } catch (Exception ignored) {
                System.out.println("No existing image found or already deleted.");
            }
        }

        //  Save the new file
        Path filePath = uploadPath.resolve(uniqueFileName);
        Files.copy(image.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

        // Save relative path in DB (not full absolute path)
        product.setImage(uploadDir + "/" + uniqueFileName);
        return product;
    }
}
