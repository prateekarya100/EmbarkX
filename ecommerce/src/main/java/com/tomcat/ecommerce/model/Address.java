package com.tomcat.ecommerce.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "addresses")
@ToString(exclude = {"users"}) // exclude users from toString so that they are not included in the toString representation
public class Address{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "address_id")
    private Long addressId;

    @NotBlank(message = "Street cannot be blank")
    @Size(min = 3, max = 100, message = "Street must be between 3 and 100 characters")
    private String street;

    @NotBlank(message = "Building name cannot be blank")
    @Size(min = 3, max = 100, message = "Building name must be between 3 and 100 characters")
    private String buildingName;

    @NotBlank(message = "City cannot be blank")
    @Size(min = 3, max = 100, message = "City must be between 3 and 100 characters")
    private String city;

    @NotBlank(message = "State cannot be blank")
    @Size(min = 2, max = 50, message = "State name must be between 2 and 50 characters")
    private String state;

    @NotBlank(message = "Country cannot be blank")
    @Size(min = 2, max = 50, message = "Country name must be between 2 and 50 characters")
    private String country;

    @NotBlank(message = "Zip code cannot be blank")
    @Size(min = 5, max = 10, message = "Pin code must be between 5 and 10 characters")
    private String pinCode;

    // many addresses can belong to many users
    @ManyToMany(mappedBy = "addresses", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<User> users = new HashSet<>(); // Use Set instead of List to allow multiple users with the same address
}
