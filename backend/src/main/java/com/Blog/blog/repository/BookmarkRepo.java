package com.Blog.blog.repository;

import com.Blog.blog.model.Bookmark;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BookmarkRepo
        extends JpaRepository<Bookmark,Integer> {

    List<Bookmark> findByUserName(String userName);

    Optional<Bookmark> findByUserNameAndBlogId(
            String userName,
            Integer blogId
    );
}