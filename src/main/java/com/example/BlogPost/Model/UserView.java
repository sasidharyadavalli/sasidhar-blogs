package com.example.BlogPost.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "user")
@Data
public class UserView {
    @Id
    @Column(name="user_id")
    private  Long user_id;
      private String username;
}
