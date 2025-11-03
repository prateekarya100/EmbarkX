package com.tomcat.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import security.jwt.JwtUtils;
import security.jwt.LoginRequest;
import security.jwt.ResponseRequest;

import java.util.List;
import java.util.Map;

@RestController
public class GreetingsMessageController {

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetails userDetails;

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

    @PostMapping(value = "/signin")
    public ResponseEntity<?> AuthenticateUserRequest(@RequestBody LoginRequest loginRequest){
      try{
          Authentication authentication = authenticationManager.authenticate(
                  new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

          SecurityContextHolder.getContext().setAuthentication(authentication);
          String jwtToken = jwtUtils.generateFromUsername(loginRequest.getUsername());
          UserDetails userDetails = (UserDetails) authentication.getPrincipal();

          List<String> roles = userDetails.getAuthorities().stream()
                  .map(GrantedAuthority::getAuthority)
                  .toList();
          return ResponseEntity.ok(new ResponseRequest(jwtToken,
                  userDetails.getUsername(),
                  roles));
      }catch (AuthenticationCredentialsNotFoundException exception){
          return ResponseEntity
                  .status(HttpStatus.UNAUTHORIZED)
                  .body(
                          Map.of("error", "username or password is incorrect, please check your credentials. and try again."));
      }
    }
}
