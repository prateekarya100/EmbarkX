package com.tomcat.ecommerce.repository;

import com.tomcat.ecommerce.model.Category;
import com.tomcat.ecommerce.model.Product;
import com.tomcat.ecommerce.payload.ProductResponse;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Long> {
    Optional<Category> findByCategoryName(String categoryName);

}
