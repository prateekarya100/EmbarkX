package com.tomcat.ecommerce.model.dto.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CategoryResponseDTO {
//    pageNumber, pageSize, totalPages, totalElements, lastPage,
    private int pageNumber;
    private int pageSize;
    private int totalPages;
    private int totalElements;
    private boolean lastPage;
    private List<CategoryDTO> content;
}
