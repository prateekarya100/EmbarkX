package com.tomcat.security.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingsMessageController {

    @GetMapping(value = "/greetings")
    public String getGreetingsMessage() {
        return "Hello, welcome to our service!";
    }

    @PreAuthorize("hasRole('USER')") // only role-user can access this method
    @GetMapping(value = "/user")
    public String endpointAccessToUser(){
        return "welcome, user is able to access.";
    }

    // setting access level to methods
    @PreAuthorize("hasRole('ADMIN')") // only role-admin can access this method
    @GetMapping(value = "/admin")
    public String endpointAccessToAdmin(){
        return "welcome, admin is able to access.";
    }
}
