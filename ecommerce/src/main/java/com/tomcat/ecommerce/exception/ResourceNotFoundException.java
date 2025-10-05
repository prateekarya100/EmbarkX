package com.tomcat.ecommerce.exception;


public class ResourceNotFoundException extends RuntimeException {
    private String resourceName;
    private String categoryName;
    private Long categoryId;

    public ResourceNotFoundException(String message) {
        super(message);
    }

    // custom exception for resource not found with field name and field value
    public ResourceNotFoundException(String resourceName, String categoryName, Long categoryId) {
        super(String.format("%s not found with %s : %d", resourceName, categoryName, categoryId));
        this.resourceName = resourceName;
        this.categoryName = categoryName;
        this.categoryId = categoryId;
    }
}
