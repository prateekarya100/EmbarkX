package com.tomcat.security.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingsMessageController {

    @GetMapping(value = "/greetings")
    public String getGreetingsMessage() {
        return "Hello, welcome to our service!";
    }


}
