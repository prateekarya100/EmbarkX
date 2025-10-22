package com.tomcat.ecommerce.payload;

import com.tomcat.ecommerce.model.Product;
import lombok.Data;

import java.util.List;

@Data
public class ProductPaginationDTO {
    private int pageNumber;
    private int pageSize;
    private long totalElements;
    private int totalPages;
    private boolean lastPage;
    private List<Product> contents;
}
