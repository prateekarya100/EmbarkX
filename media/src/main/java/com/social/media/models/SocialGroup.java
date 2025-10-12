package com.social.media.models;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
public class SocialGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //many to many with social users
    @ManyToMany(mappedBy = "socialGroups")
    private Set<SocialUser> socialUsers = new HashSet<>();
}
