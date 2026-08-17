package com.Blog.blog.service;

import com.Blog.blog.model.Blog;
import com.Blog.blog.model.Bookmark;
import com.Blog.blog.repository.BlogRepo;
import com.Blog.blog.repository.BookmarkRepo;

import org.hibernate.service.spi.InjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class BookmarkService {

    @Autowired
    private BookmarkRepo repo;

    @Autowired
    BlogRepo blogrepo;
    public Bookmark saveBlog(Integer blogId) {

        String username =
                SecurityContextHolder
                        .getContext()
                        .getAuthentication()
                        .getName();

        Bookmark bookmark = new Bookmark();

        bookmark.setBlogId(blogId);
        bookmark.setUserName(username);

        return repo.save(bookmark);
    }

    public List<Blog> getSavedBlogs() {

        String username =
                SecurityContextHolder
                        .getContext()
                        .getAuthentication()
                        .getName();

        List<Bookmark> bookmarks = repo.findByUserName(username);
        return bookmarks.stream()
                .map(bookmark -> blogrepo.findById(bookmark.getBlogId()).orElse(null))
                .filter(Objects::nonNull)
                .toList();
    }


    public void removeBookmark(Integer blogId){

        String username =
                SecurityContextHolder
                        .getContext()
                        .getAuthentication()
                        .getName();

        Bookmark bookmark =
                repo.findByUserNameAndBlogId(
                                username,
                                blogId)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Bookmark Not Found"));

        repo.delete(bookmark);
    }
}