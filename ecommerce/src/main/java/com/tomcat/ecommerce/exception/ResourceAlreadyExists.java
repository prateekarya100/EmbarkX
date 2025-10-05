package com.tomcat.ecommerce.exception;

public class ResourceAlreadyExists extends RuntimeException {

    private final String serverResponseAlreadyExists;

    public ResourceAlreadyExists(String message) {
        super(message);
        this.serverResponseAlreadyExists = message;
    }
}
