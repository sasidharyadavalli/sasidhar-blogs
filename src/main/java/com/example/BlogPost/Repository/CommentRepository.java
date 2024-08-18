package com.example.BlogPost.Repository;

import com.example.BlogPost.Model.Comment;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment,Long> {

    @Modifying
    @Transactional
    @Query(nativeQuery = true,value = "insert  into comment (content,user_id,post_id)  values(?1, ?2,?3) ")
    void save1(String content,long user_id, int postId);

}
