package com.tomcat.ecommerce.model;

import jakarta.persistence.*;

@Entity
@Table(name = "address")
public class Address{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "address_id")
    private Long addressId;
}
