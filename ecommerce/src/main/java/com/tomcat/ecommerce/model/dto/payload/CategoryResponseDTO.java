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
//    private String pageNumber;
//    private String pageSize;
//    private String totalPages;
//    private String totalElements;
//    private String lastPage;
    private List<CategoryDTO> content;
}
