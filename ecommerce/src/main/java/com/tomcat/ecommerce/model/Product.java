package com.tomcat.ecommerce.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "products")
@ToString(exclude = {"user", "category"}) // exclude user and category from toString so that they are not included in the toString representation
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;

    @NotBlank
    @Size(min = 3, max = 100, message = "Product name must be between 3 and 40 characters")
    private String productName;

    private String image;

    @Size(min = 4, max = 200, message = "Description must be between 4 and 200 characters")
    private String description;

    @NotNull( message = "Quantity cannot be null")
    private Integer quantity;

    @NotNull( message = "Price cannot be null")
    private double price; // 100

    @NotNull( message = "Discount cannot be null")
    private double discount; // 25% on price

    private double specialPrice; // 75

    // many products can belong to one category
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    // many products can be owned by one user
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}
