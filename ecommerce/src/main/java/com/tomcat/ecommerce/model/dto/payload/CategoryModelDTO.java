package com.tomcat.ecommerce.model.dto.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CategoryModelDTO {
    private Long categoryId;
    private String categoryName;
}
