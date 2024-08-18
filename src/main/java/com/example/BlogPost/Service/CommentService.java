package com.example.BlogPost.Service;

import com.example.BlogPost.Model.Comment;
import com.example.BlogPost.Repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {
    @Autowired
    CommentRepository commentRepository;
    public void save(Comment comment,Long user_id, int post_id) {
        String content=comment.getContent();


    commentRepository.save1(content,user_id,post_id);
    }

    public List<Comment> findall() {
    return commentRepository.findAll();
    }
}
