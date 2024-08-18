package com.example.BlogPost.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
@Data
@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String content;
    @Column(name = "auther_id")
    private Long user_id;
    @Column(name = "input_username")
    private String username;
//    @Column(name = "createdAt")
//    private LocalDateTime createdAt = LocalDateTime.now();

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    @JsonBackReference("user-post")
    private User user;

    @OneToMany(mappedBy = "post")
    @JsonManagedReference("post-comment")
    private List<Comment> comments;

}
