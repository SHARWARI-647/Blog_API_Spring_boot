package com.Blog.blog.repository;

import com.Blog.blog.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User,String> {
    User findByUserName(String userName);
}
