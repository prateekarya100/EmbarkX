package com.tomcat.ecommerce.models.category;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor @NoArgsConstructor
@ToString
public class Category {
    private Long categoryId;
    private String categoryName;
}
