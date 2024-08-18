package com.example.BlogPost.Service;




import com.example.BlogPost.Model.Post;
import com.example.BlogPost.Model.PostUpdateDetails;
import com.example.BlogPost.Model.User;
import com.example.BlogPost.Repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class PostService {

    private final PostRepository postRepository;

    @Autowired
    private UserService userService;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public List<Post> getAllPosts() {
        return postRepository.findAll1();
    }

    public Post getPostById(Long id) {
        return postRepository.findById1(id).orElse(null);
    }

    public List<Post> getPostByTitle(String title){
        return  postRepository.findByTitle(title);
   }



    public void savePost(Post post) {
        postRepository.save(post);
    }

    public int deletePost(Long id,Long user_id) {
      return   postRepository.deleteById1(id,user_id);
    }

public List<Post>findByUserId(Long id){
        return postRepository.findByUserId(id);
}
public  List<Post>getPostByUserName(String username){
        return postRepository.getPostByUserName(username);
}


    public void setUserIdFromAutherID(Long userId) {
        postRepository.setUserIdFromAutherID(userId);
    }

    public int deletePostByTitle(String title, Long user_d) {
        return postRepository.deleteByTitle(title,user_d);
    }

    public int deleteAll1(Long user_id) {
        return postRepository.deleteAll1(user_id);
    }

    public ResponseEntity<String> update(long id, PostUpdateDetails postUpdateDetails) throws Exception {
        String username=userService.getUserNameFromLogin();
         Optional<User> user=userService.findUserByUserName(username);
        Optional<Post> post=postRepository.findById1(id);

        if(user.isPresent()&&post.isPresent()){
            if(postUpdateDetails.getTitle()!=null&&!postUpdateDetails.getTitle().isEmpty()){
                post.get().setTitle(postUpdateDetails.getTitle());
            }
            if(postUpdateDetails.getContent()!=null&&!postUpdateDetails.getContent().isEmpty()){
                post.get().setContent(postUpdateDetails.getContent());
            }
        if( user.get().getId()== post.get().getUser_id()){
            postRepository.save(post.get());
            return ResponseEntity.ok("updated successfully");
        }}else return ResponseEntity.badRequest().body("this post not belongs to you ,check whether post belongs to you or not ");
        return  ResponseEntity.badRequest().body("check post is exist  /  no user found check ur login details");

    }

}
