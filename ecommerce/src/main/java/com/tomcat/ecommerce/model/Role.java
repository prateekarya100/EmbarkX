package com.tomcat.ecommerce.model;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "roles")
@ToString(exclude = {"users"}) // exclude users from toString so that they are not included in the toString representation
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private Long roleId;

    @Enumerated(EnumType.STRING) // Use EnumType.STRING to store the enum value as a string in the database
    @Column(name = "role_name", nullable = false, length = 50)
    private AppRoles roleName;

    @ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<User> users = new ArrayList<>(); // Use List instead of Set to allow multiple users with the same role
}
