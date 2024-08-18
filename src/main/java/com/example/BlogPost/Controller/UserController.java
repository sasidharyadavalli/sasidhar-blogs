package com.example.BlogPost.Controller;

import com.example.BlogPost.Model.User;
import com.example.BlogPost.Model.UserView;
import com.example.BlogPost.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/getbyid/{id}")
    public UserView viewUser(@PathVariable Long id) {
        return    userService.getUserById(id);
    }
@GetMapping("/getall")
    public List<UserView> findAll(){
        return userService.getUsers();
    }


    @PostMapping("/save")
    public ResponseEntity<String> saveUser(@RequestParam String username,@RequestParam String password){
    // String username=user.getUsername();
        if(userService.findUserByUserName(username).isPresent()){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("username already exist");
        }


        User user=new User();
        String encodedPassword=passwordEncoder.encode(password);
        user.setPassword(encodedPassword);
        user.setUsername(username);

        userService.saveUser(user);
        return ResponseEntity.ok("user saved successfully");
  }
 // @GetMapping("/encode")
  //public void encode(){
    //     userService.encodeExistingPasswords();
 // }

}
