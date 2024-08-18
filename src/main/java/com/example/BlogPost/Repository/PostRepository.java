package com.example.BlogPost.Repository;

import com.example.BlogPost.Model.Post;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post,Long> {
@Query(nativeQuery = true,value = "SELECT post.id,title,content,user_id as auther_id,username as input_username,user_id FROM post join user using(user_id) WHERE title=:title")
   List<Post> findByTitle(@Param("title") String title);

@Query(nativeQuery = true,value ="SELECT post.id,title,content,user_id as auther_id,username as input_username,user_id FROM post join user using(user_id) WHERE user_id=?1" )
List<Post>findByUserId(Long user_id);
@Query(nativeQuery = true,value = "CALL getPostByUserName(:username)")
List<Post> getPostByUserName(@Param("username") String username);


@Query(nativeQuery = true,value = "SELECT post.id,title,content,user_id as auther_id,username as input_username,user_id FROM post join user using(user_id) WHERE post.id=?1")
   Optional<Post> findById1(Long id);


@Query(nativeQuery = true,value ="SELECT post.id,title,content,user_id as auther_id,username as input_username,user_id FROM post join user using(user_id)" )
   List<Post> findAll1();

@Modifying
@Transactional
@Procedure(name = "setUserIdFromAutherID")
   void setUserIdFromAutherID(@Param("userId") Long userId);

@Modifying
@Transactional
@Query(nativeQuery = true,value = "delete from post where user_id=?2 and post.id=?1")
    int deleteById1(Long id,Long user_id);


@Modifying
@Transactional
@Query(nativeQuery = true,value = "delete from post where user_id=:user_id and title=:title")
    int deleteByTitle(String title, Long user_id);

@Modifying
@Transactional
   @Query(nativeQuery = true,value = "delete from post where user_id=:user_id")
    int deleteAll1(Long user_id);
}


