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
import org.springframework.security.core.userdetails.UserDetailsService;
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
    private JwtUtils jwtUtils; // what is JwtUtils? JwtUtils is a utility class for generating and validating JWT tokens.

    @Autowired
    private AuthenticationManager authenticationManager; // what is AuthenticationManager? AuthenticationManager is an interface that provides authentication services.

    @Autowired
    private UserDetailsService userDetailsService; // what is UserDetailsService? UserDetailsService is an interface that provides user details for authentication.

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

    // what this method does is that it authenticates the user request and returns a JWT token if the user is authenticated successfully.
    @PostMapping(value = "/signin")
    public ResponseEntity<?> AuthenticateUserRequest(@RequestBody LoginRequest loginRequest){
      try{
          // authenticate the user request
          Authentication authentication = authenticationManager.authenticate(
                  // create a UsernamePasswordAuthenticationToken object with the username and password from the login request
                  new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

          // set the authentication object to the SecurityContextHolder
          SecurityContextHolder.getContext().setAuthentication(authentication);

          String jwtToken = jwtUtils.generateFromUsername(loginRequest.getUsername());

          // get the user details from the authentication object
          UserDetails userDetails = (UserDetails) authentication.getPrincipal();

          // get the roles from the user details object
          List<String> roles = userDetails.getAuthorities().stream()
                  .map(GrantedAuthority::getAuthority)
                  .toList();

          // return the response request object with the JWT token, username, and roles
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
