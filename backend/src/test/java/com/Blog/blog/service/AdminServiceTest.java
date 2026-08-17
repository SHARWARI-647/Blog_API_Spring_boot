package com.Blog.blog.service;

import com.Blog.blog.dto.AdminDashboardResponse;
import com.Blog.blog.model.Blog;
import com.Blog.blog.repository.BlogRepo;
import com.Blog.blog.repository.CommentRepo;
import com.Blog.blog.repository.UserRepo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)
public class AdminServiceTest {
    @Mock
    private UserRepo userRepo;

    @Mock
    private BlogRepo blogRepo;
    @Mock
    private CommentRepo commentRepo;

    @InjectMocks
    private AdminService adminService;

    @Test
    void testGetDashboard(){
        Blog likedBlog = new Blog();
        likedBlog.setTitle("Spring Boot");
        likedBlog.setLikes(100);

        Blog viewedBlog = new Blog();
        viewedBlog.setTitle("Java");
        viewedBlog.setViews(500);

        when(userRepo.count()).thenReturn(10L);
        when(blogRepo.count()).thenReturn(20L);
        when(commentRepo.count()).thenReturn(30L);
        when(blogRepo.findTopByOrderByLikesDesc()).thenReturn(Optional.of(likedBlog));
        when(blogRepo.findTopByOrderByViewsDesc()).thenReturn(Optional.of(viewedBlog));
        AdminDashboardResponse response = adminService.getDashboard();

        // Assert
        assertNotNull(response);

        assertEquals(10L, response.getTotalUsers());
        assertEquals(20L, response.getTotalBlogs());
        assertEquals(30L, response.getTotalComments());

        assertEquals("Spring Boot",
                response.getMostLikedBlog().getTitle());

        assertEquals("Java",
                response.getMostViewedBlog().getTitle());
        // Verify interactions
        verify(userRepo, times(1)).count();
        verify(blogRepo, times(1)).count();
        verify(commentRepo, times(1)).count();
        verify(blogRepo, times(1)).findTopByOrderByLikesDesc();
        verify(blogRepo, times(1)).findTopByOrderByViewsDesc();


    }
}
