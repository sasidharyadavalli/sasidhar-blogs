package com.example.BlogPost.Controller;


import com.example.BlogPost.Model.Post;
import com.example.BlogPost.Model.PostUpdateDetails;
import com.example.BlogPost.Service.PostService;
import com.example.BlogPost.Service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostController {
    private final PostService postService;

    private final UserService userService;

    public PostController(PostService postService, UserService userService) {
        this.postService = postService;
        this.userService = userService;
    }

    @GetMapping("/getall")
    public List<Post> listPosts() {
        return   postService.getAllPosts();
    }

    @GetMapping("/getbyid/{id}")
    public Post viewPost(@PathVariable Long id) {
        return   postService.getPostById(id);
    }

    @GetMapping("/getbytitle/{title}")
    public  List<Post> findByTitle(@PathVariable  String title){
       return postService.getPostByTitle(title);
}

    @GetMapping("/getbyuserid/{id}")
    public List<Post>findByUserId(@PathVariable Long id){
        return postService.findByUserId(id);
}

    @GetMapping("/getbyusername/{username}")
    public List<Post>getPostByUserName(@PathVariable String username){
        return postService.getPostByUserName(username);
    }

    @PostMapping(value = "/post", consumes = "application/json", produces = "application/json")
    public ResponseEntity<String> post(@RequestBody Post post){
    Long user_id=userService.findUserIdFromLogin();
   post.setUser_id(user_id);
        postService.savePost(post);
        postService.setUserIdFromAutherID(user_id);

        return ResponseEntity.ok("post saved successfully");
    }
    @DeleteMapping("/delete/{post_id}")
    public ResponseEntity<String>delete(@PathVariable long  post_id){
        Long user_id=userService.findUserIdFromLogin();
       int count= postService.deletePost(post_id,user_id);
       if( count==0) {
            return ResponseEntity.badRequest().body("bad credentials , check whether post belongs to you, or check post is existed ");
       }
       return ResponseEntity.ok("deleted successfully");

    }

    @DeleteMapping("/deletebytitle/{title}")
    public ResponseEntity<String> deleteByTitle(@PathVariable String title){
            Long user_id=userService.findUserIdFromLogin();
           int count= postService.deletePostByTitle(title,user_id);
           if(count==0){
              return ResponseEntity.badRequest().body("bad credentials , check wether post belongs to you or not , or check wether post exits ");
           }
         return   ResponseEntity.ok("deleted successfully");
    }
    @DeleteMapping("/deleteall")
    public ResponseEntity<String >deleteall(){
        Long user_id=userService.findUserIdFromLogin();
        int count=postService.deleteAll1(user_id);
        if(count==0){
            return  ResponseEntity.badRequest().body("bad credentials, check whether posts exists ,or check login details");
        }
        return ResponseEntity.ok("deleted all posts successfully");
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<String> update(@PathVariable long id,@RequestBody PostUpdateDetails postUpdateDetails) throws Exception {

         return  postService.update(id,postUpdateDetails);

    }
}