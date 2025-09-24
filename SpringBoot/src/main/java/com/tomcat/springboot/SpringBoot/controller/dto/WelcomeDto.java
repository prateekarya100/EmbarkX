package com.tomcat.springboot.SpringBoot.controller.dto;


import org.springframework.http.ResponseEntity;

public class WelcomeDto {
    private String message;
    public WelcomeDto(String message) {
        this.message = message;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "WelcomeDto [message=" + message + "]";
    }
}
