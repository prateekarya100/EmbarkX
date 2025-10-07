package com.tomcat.ecommerce.model.dto.payload;

import com.tomcat.ecommerce.model.dto.CategoryResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CategoryModelResponseDTO {
    private List<CategoryModelDTO> content;
}
