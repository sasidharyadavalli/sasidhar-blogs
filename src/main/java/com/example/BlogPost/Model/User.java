package com.example.BlogPost.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;


import java.util.List;
import java.util.Set;
@Entity
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name = "user_id")
    private Long id;

    @Column(nullable = false)
    private String username;

    @Column(name = "password",nullable = false)
    private String password;

    @OneToMany(mappedBy = "user")
    @JsonManagedReference("user-post")
    private List<Post> posts;

    @OneToMany(mappedBy = "user")
    private List<Comment> comments;

    // Getters and Setters
}