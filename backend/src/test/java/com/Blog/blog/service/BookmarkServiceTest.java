package com.Blog.blog.service;

import com.Blog.blog.model.Blog;
import com.Blog.blog.model.Bookmark;
import com.Blog.blog.repository.BlogRepo;
import com.Blog.blog.repository.BookmarkRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;

import java.awt.print.Book;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BookmarkServiceTest {
    @Mock
    private BookmarkRepo bookmarkRepo;

    @Mock
    private BlogRepo blogRepo;
    @InjectMocks
    private BookmarkService bookmarkService;
     @BeforeEach
    void setup(){

    UsernamePasswordAuthenticationToken authentication =
            new UsernamePasswordAuthenticationToken(
                    "Sharwari",
                    null,
                    List.of()
            );

    SecurityContextHolder
            .getContext()
            .setAuthentication(authentication);
}
@Test
void testSaveBlog() {

    String username =
            SecurityContextHolder
                    .getContext()
                    .getAuthentication()
                    .getName();

    Bookmark bookmark = new Bookmark();

    bookmark.setBlogId(1);
    bookmark.setUserName(username);

    when(bookmarkRepo.save(any(Bookmark.class))).thenReturn(bookmark);
    Bookmark result=bookmarkService.saveBlog(1);
    assertNotNull(result);
    assertEquals(1, result.getBlogId());
    assertEquals("Sharwari", result.getUserName());

    verify(bookmarkRepo).save(any(Bookmark.class));
}

    void testGetSavedBlogs() {
        String username = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();

        Bookmark bookmark = new Bookmark();
        bookmark.setBlogId(1);
        bookmark.setUserName(username);

        Blog blog = new Blog();
        blog.setId(1);
        blog.setTitle("Spring Boot");

        when(bookmarkRepo.findByUserName(username)).thenReturn(List.of(bookmark));
        when(blogRepo.findById(1))
                .thenReturn(Optional.of(blog));

        List<Blog> result = bookmarkService.getSavedBlogs();
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Spring Boot", result.get(0).getTitle());

        verify(bookmarkRepo).findByUserName(username);
        verify(blogRepo).findById(1);
    }
    @Test
    void testGetSavedBlogsEmpty() {

        when(bookmarkRepo.findByUserName("Sharwari"))
                .thenReturn(List.of());

        List<Blog> result = bookmarkService.getSavedBlogs();

        assertTrue(result.isEmpty());

        verify(bookmarkRepo).findByUserName("Sharwari");
    }
    @Test
    void testRemoveBookmark(){
        String username =
                SecurityContextHolder
                        .getContext()
                        .getAuthentication()
                        .getName();

        Bookmark bookmark = new Bookmark();
        bookmark.setBlogId(1);
        bookmark.setUserName(username);
        when(bookmarkRepo.findByUserNameAndBlogId(username,1))
                .thenReturn(Optional.of(bookmark));
        doNothing().when(bookmarkRepo)
                .delete(bookmark);

        bookmarkService.removeBookmark(1);

        verify(bookmarkRepo)
                .findByUserNameAndBlogId("Sharwari",1);

        verify(bookmarkRepo).delete(bookmark);
    }

}
