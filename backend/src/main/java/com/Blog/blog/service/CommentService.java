package com.Blog.blog.service;

import com.Blog.blog.model.Comment;
import com.Blog.blog.repository.CommentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CommentService {

    @Autowired
    private CommentRepo repo;

    public Comment addComment(
            Integer blogId,
            String commentText){

        String username =
                SecurityContextHolder
                        .getContext()
                        .getAuthentication()
                        .getName();

        Comment comment = new Comment();

        comment.setBlogId(blogId);
        comment.setUserName(username);
        comment.setComment(commentText);
        comment.setCreatedAt(LocalDateTime.now());

        return repo.save(comment);
    }

    public List<Comment> getComments(
            Integer blogId){

        return repo.findByBlogId(blogId);
    }
}