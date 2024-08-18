package com.example.BlogPost.Service;


import com.example.BlogPost.Model.Comment;
import com.example.BlogPost.Model.User;
import com.example.BlogPost.Model.UserView;
import com.example.BlogPost.Repository.UserRepository;
import com.example.BlogPost.Repository.UserViewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final UserViewRepository userViewRepository;

    public UserService(UserRepository userRepository, UserViewRepository userViewRepository) {
        this.userRepository = userRepository;
        this.userViewRepository = userViewRepository;
    }

    public UserView getUserById(Long id) {
        return userViewRepository.findById1(id).orElse(null);
    }
    public List<UserView> getUsers(){
        return userViewRepository.findAll1();
    }

    public void saveUser(User user) {
        userRepository.save(user);
    }

     //this method is created for post mapping in usercontroller
    public Optional<User> findUserByUserName(String username){
        return userRepository.findUserByUserName(username);
    }

    @Autowired
    private PasswordEncoder passwordEncoder;


    public void encodeExistingPasswords() {
        List<User> users = userRepository.findAll();
        for (User user : users) {
            String encodedPassword = passwordEncoder.encode(user.getPassword());
            user.setPassword(encodedPassword);
            userRepository.save(user);
        }
    }


    public String getUserNameFromLogin(){
        try {

            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            return  authentication.getName();
    }catch (Exception e){
            e.getMessage();
        }
        return "user not found";
    }


    public Long findUserIdFromLogin() {
        String username=getUserNameFromLogin();
        return userRepository.findUserIdByUserName(username);
    }




}