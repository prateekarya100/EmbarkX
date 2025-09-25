package com.tomcat.springboot.SpringBoot.controller;

import com.tomcat.springboot.SpringBoot.controller.dto.WelcomeBankingDTO;
import com.tomcat.springboot.SpringBoot.controller.dto.WelcomeDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Objects;

@RestController
public class HelloMessage {

    @GetMapping(value = {"/welcome","/", "/home"})
    public String sayHello() {
        return "Welcome to Spring Boot Application";
    }

    @PostMapping(value = "/post-name")
    public ResponseEntity<WelcomeDto> postName(@RequestBody String name) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new WelcomeDto("Welcome to Spring Boot Application "+name));
    }

    @PostMapping(value = "/post-name-by-path-variable/{name}")
    public ResponseEntity<WelcomeDto> postNameByPathVariable( @PathVariable("name") String name) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new WelcomeDto("Welcome to Spring Boot Application "+name));
    }

    @GetMapping(value = "/banking")
    public WelcomeBankingDTO banking() {
        return new WelcomeBankingDTO("Welcome to Banking Application");
    }

    @PostMapping(value = "/banking")
    public WelcomeBankingDTO bankingByPathVariable( @RequestBody String customerName) {
        if(Objects.isNull(customerName) || customerName.isEmpty()){
            throw new IllegalArgumentException("Customer name is required");
        }
       return new WelcomeBankingDTO("Thank you for registering into Banking Application "+customerName);
    }


}
