package com.be.inventorymaneger.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer user_id;
    @Column(nullable = false, unique = true, length = 45)
    @Email(message = "Please enter a valid e-mail address")
    @NotEmpty(message = "Email can't not be empty")
    private String email;
    @Column(nullable = false, length = 64)
    @NotEmpty(message = "Password can't not be empty")
    private String password;
    @Column(nullable = false, length = 20)
    @NotEmpty(message = "Firstname can't not be empty")
    private String firstname;
    @Column(nullable = false, length = 20)
    @NotEmpty(message = "Lastname can't not be empty")
    private String lastname;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles = new HashSet<>();

}
