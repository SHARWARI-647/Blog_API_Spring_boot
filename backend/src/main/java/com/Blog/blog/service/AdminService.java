package com.Blog.blog.service;

import com.Blog.blog.dto.AdminDashboardResponse;
import com.Blog.blog.model.Blog;
import com.Blog.blog.repository.BlogRepo;
import com.Blog.blog.repository.CommentRepo;
import com.Blog.blog.repository.UserRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private BlogRepo blogRepo;

    @Autowired
    private CommentRepo commentRepo;

    public AdminDashboardResponse getDashboard() {

        long totalUsers = userRepo.count();

        long totalBlogs = blogRepo.count();

        long totalComments = commentRepo.count();

        Blog mostLiked =
                blogRepo.findTopByOrderByLikesDesc()
                        .orElse(null);

        Blog mostViewed =
                blogRepo.findTopByOrderByViewsDesc()
                        .orElse(null);

        return new AdminDashboardResponse(
                totalUsers,
                totalBlogs,
                totalComments,
                mostLiked,
                mostViewed
        );
    }
}