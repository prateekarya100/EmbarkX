package com.tomcat.ecommerce.payload;

import com.tomcat.ecommerce.model.Category;
import lombok.Data;

import java.util.List;

@Data
public class CategoryPaginatedDTO {
    private int currentPage;
    private int pageSize;
    private int totalPages;
    private long totalElements;
    private boolean isLastPage;

    private List<Category> categories;
}
