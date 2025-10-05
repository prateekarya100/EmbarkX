package com.tomcat.ecommerce.exception;

public class CategoryNotBlankException extends RuntimeException{

    public CategoryNotBlankException(String message) {
        super(message);
    }

}
