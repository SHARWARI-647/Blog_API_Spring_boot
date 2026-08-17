package com.Blog.blog.repository;

import com.Blog.blog.model.Blog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface BlogRepo extends JpaRepository<Blog,Integer> {
    List<Blog> getByCategory(String category);

    List<Blog> findByTitleContainingIgnoreCase(String title);

    List<Blog> findByUserName(String userName);
    List<Blog> findTop5ByOrderByLikesDesc();
    Optional<Blog> findTopByOrderByLikesDesc();

    Optional<Blog> findTopByOrderByViewsDesc();
    List<Blog> findTop10ByOrderByCreatedAtDesc();
}
