package com.Blog.blog.service;

import com.Blog.blog.dto.AdminDashboardResponse;
import com.Blog.blog.dto.BlogRequest;
import com.Blog.blog.dto.BlogStats;
import com.Blog.blog.model.Blog;
import com.Blog.blog.repository.BlogRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;
@Service
public class BlogService {

    @Autowired
    private BlogRepo repo;
    @Autowired
    SupabaseStorageService storageService;

    public List<Blog> getAllBlogs() {
        return repo.findAll();
    }

    public Blog getBlogById(Integer id) {
        Blog blog =  repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Blog Not Found"));
        blog.setViews(blog.getViews() + 1);

        return repo.save(blog);
    }
    public List<Blog> getMyBlogs(){
        String userName= SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();
        return repo.findByUserName(userName);
    }
    public List<Blog> getTrendingBlogs(){

        return repo.findTop5ByOrderByLikesDesc();
    }
    public List<Blog> latestBlogs(){

        return repo.findTop10ByOrderByCreatedAtDesc();
    }
    public List<Blog> getCategoryBlogs(String category) {
        return repo.getByCategory(category);
    }

    public List<Blog> searchBlogs(String title) {
        return repo.findByTitleContainingIgnoreCase(title);
    }

    public ResponseEntity<Blog> createBlog(
            String category,
            String title,
            String description,
            MultipartFile image) throws Exception {

        String imageUrl = storageService.uploadImage(image);

        String username = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();

        Blog blog = new Blog();
        blog.setUserName(username);
        blog.setCategory(category);
        blog.setTitle(title);
        blog.setDescription(description);
        blog.setImgURL(imageUrl);
        blog.setCreatedAt(LocalDateTime.now());
        blog.setUpdatedAt(LocalDateTime.now());
        blog.setLikes(0);
        blog.setViews(0);

        Blog savedBlog = repo.save(blog);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedBlog);
    }

    public Blog updateBlog(Integer id, Blog updatedBlog) {

        Blog blog = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Blog Not Found"));

        blog.setTitle(updatedBlog.getTitle());
        blog.setCategory(updatedBlog.getCategory());
        blog.setDescription(updatedBlog.getDescription());
        blog.setImgURL(updatedBlog.getImgURL());
        blog.setUserName(updatedBlog.getUserName());
        blog.setUpdatedAt(
                java.time.LocalDateTime.now());
        return repo.save(blog);
    }

    public void deleteBlog(Integer id) {
        repo.deleteById(id);
    }

    public Blog likeBlog(Integer id) {

        Blog blog = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Blog Not Found"));

        blog.setLikes(blog.getLikes() + 1);

        return repo.save(blog);
    }
    public BlogStats getStats(){

        long blogs = repo.count();

        long likes = repo.findAll()
                .stream()
                .mapToLong(Blog::getLikes)
                .sum();

        long views = repo.findAll()
                .stream()
                .mapToLong(Blog::getViews)
                .sum();

        return new BlogStats(
                blogs,
                likes,
                views
        );
    }

    public Page<Blog> getBlogs(
            int page,
            int size,
            String sortBy,
            String direction) {

        Sort sort = direction.equalsIgnoreCase("desc")
                ? Sort.by(sortBy).descending()
                : Sort.by(sortBy).ascending();

        Pageable pageable =
                PageRequest.of(page, size, sort);

        return repo.findAll(pageable);
    }
}