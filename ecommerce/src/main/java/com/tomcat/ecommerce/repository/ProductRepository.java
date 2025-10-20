package com.tomcat.ecommerce.repository;

import com.tomcat.ecommerce.model.Category;
import com.tomcat.ecommerce.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
    List<Product> findByCategory(Category category);

    // finding products which matching entered keywords by user.
    List<Product> findByProductNameLikeIgnoreCase(String keywords);
}
