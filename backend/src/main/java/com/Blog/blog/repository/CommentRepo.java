package com.Blog.blog.repository;

import com.Blog.blog.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepo extends JpaRepository<Comment,Integer> {
    List<Comment> findByBlogId(Integer blogId);
}
