package com.tomcat.contact_mgnt.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/contacts")
public class ContactController {

    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    @GetMapping
    public String getContacts() {
        return "returning a list of contacts";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public String addContact() {
        return "adding a new contact";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public String deleteContact(@PathVariable("id") Long id) {
        return "deleting contact with id: " + id;
    }

    @GetMapping(value = "/public/info")
    public String publicInfo() {
        return "This is public information about the contact management system.";
    }
}
