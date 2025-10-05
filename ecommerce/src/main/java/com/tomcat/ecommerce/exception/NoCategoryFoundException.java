package com.tomcat.ecommerce.exception;

public class NoCategoryFoundException extends RuntimeException {
    public NoCategoryFoundException(String message) {
        super(message);
    }
}
