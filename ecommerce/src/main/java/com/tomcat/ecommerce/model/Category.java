package com.tomcat.ecommerce.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor @NoArgsConstructor
@ToString
@Table(name = "categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long categoryId;
    
    @NotNull(message = "Category name is mandatory field and cannot be blank")
    @Size(min = 2, max = 30, message = "Category name must be between 2 and 30 characters")
    private String categoryName;
}
