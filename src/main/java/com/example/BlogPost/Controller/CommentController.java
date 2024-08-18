package com.example.BlogPost.Controller;

import com.example.BlogPost.Model.Comment;
import com.example.BlogPost.Service.CommentService;
import com.example.BlogPost.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comments")
public class CommentController {
    @Autowired
    CommentService commentService;
    @Autowired
    UserService userService;
   @PostMapping("/post/{post_id}")
    public ResponseEntity<String> post(@RequestBody Comment comment, @PathVariable int post_id){


       Long user_id=userService.findUserIdFromLogin();
           commentService.save(comment,user_id,post_id);
           return ResponseEntity.ok("saved successfully");
    }
    @GetMapping("/getall")
    public List<Comment>getAll(){
       return commentService.findall();
    }
}
