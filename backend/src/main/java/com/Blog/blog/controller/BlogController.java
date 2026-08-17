package com.Blog.blog.controller;

import com.Blog.blog.dto.BlogStats;
import com.Blog.blog.model.Blog;
import com.Blog.blog.service.BlogService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/blogs")
@CrossOrigin("*")
@Tag(
        name = "Blog APIs",
        description = "Operations related to Blog Management"
)
public class BlogController {

    @Autowired
    private BlogService service;

    @Operation(
            summary = "Get All Blogs",
            description = "Returns all blogs available in the system."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Blogs fetched successfully")
    })
    @GetMapping("/")
    public List<Blog> getAllBlogs() {
        return service.getAllBlogs();
    }

    @Operation(
            summary = "Get Blog By ID",
            description = "Returns a specific blog using its ID."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Blog Found"),
            @ApiResponse(responseCode = "404", description = "Blog Not Found")
    })
    @GetMapping("/{id}")
    public Blog getBlogById(

            @Parameter(
                    description = "Unique Blog ID",
                    example = "1"
            )
            @PathVariable Integer id) {

        return service.getBlogById(id);
    }

    @Operation(
            summary = "Get Blogs By Category",
            description = "Returns all blogs of a specific category."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Blogs Found"),
            @ApiResponse(responseCode = "404", description = "Category Not Found")
    })
    @GetMapping("/category/{category}")
    public List<Blog> getCategoryBlogs(

            @Parameter(
                    description = "Category Name",
                    example = "Programming"
            )
            @PathVariable String category) {

        return service.getCategoryBlogs(category);
    }

    @Operation(
            summary = "Search Blogs",
            description = "Search blogs using title."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Search Completed")
    })
    @GetMapping("/search/{title}")
    public List<Blog> searchBlogs(

            @Parameter(
                    description = "Blog Title",
                    example = "Spring Boot"
            )
            @PathVariable String title) {

        return service.searchBlogs(title);
    }

    @Operation(
            summary = "Update Blog",
            description = "Updates an existing blog."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Blog Updated"),
            @ApiResponse(responseCode = "404", description = "Blog Not Found")
    })
    @PutMapping("/{id}")
    public Blog updateBlog(

            @Parameter(
                    description = "Blog ID",
                    example = "1"
            )
            @PathVariable Integer id,

            @RequestBody Blog blog) {

        return service.updateBlog(id, blog);
    }

    @Operation(
            summary = "Trending Blogs",
            description = "Returns Top 5 blogs based on likes."
    )
    @GetMapping("/trending")
    public List<Blog> trendingBlogs() {
        return service.getTrendingBlogs();
    }

    @Operation(
            summary = "Latest Blogs",
            description = "Returns latest 10 blogs."
    )
    @GetMapping("/latest")
    public List<Blog> latestBlogs() {
        return service.latestBlogs();
    }

    @Operation(
            summary = "My Blogs",
            description = "Returns blogs created by logged-in user."
    )
    @GetMapping("/userblog")
    public List<Blog> getMyBlogs() {
        return service.getMyBlogs();
    }

    @Operation(
            summary = "Like Blog",
            description = "Increases like count of a blog."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Like Added"),
            @ApiResponse(responseCode = "404", description = "Blog Not Found")
    })
    @PutMapping("/like/{id}")
    public Blog likeBlog(

            @Parameter(
                    description = "Blog ID",
                    example = "1"
            )
            @PathVariable Integer id) {

        return service.likeBlog(id);
    }

    @Operation(
            summary = "Delete Blog",
            description = "Deletes a blog by ID."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Blog Deleted"),
            @ApiResponse(responseCode = "404", description = "Blog Not Found")
    })
    @DeleteMapping("/{id}")
    public String deleteBlog(

            @Parameter(
                    description = "Blog ID",
                    example = "1"
            )
            @PathVariable Integer id) {

        service.deleteBlog(id);
        return "Blog Deleted Successfully";
    }

    @Operation(
            summary = "Blog Statistics",
            description = "Returns total blogs, likes and views."
    )
    @GetMapping("/stats")
    public BlogStats getStats() {
        return service.getStats();
    }

    @Operation(
            summary = "Pagination",
            description = "Returns blogs with pagination and sorting."
    )
    @GetMapping("/page")
    public Page<Blog> getBlogs(

            @Parameter(description = "Page Number", example = "0")
            @RequestParam(defaultValue = "0")
            int page,

            @Parameter(description = "Page Size", example = "5")
            @RequestParam(defaultValue = "5")
            int size,

            @Parameter(description = "Sort By", example = "createdAt")
            @RequestParam(defaultValue = "createdAt")
            String sortBy,

            @Parameter(description = "Direction", example = "desc")
            @RequestParam(defaultValue = "desc")
            String direction) {

        return service.getBlogs(page, size, sortBy, direction);
    }

    @Operation(
            summary = "Create Blog",
            description = "Creates a new blog with image upload."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Blog Created Successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid Request"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    @PostMapping("/create")
    public ResponseEntity<Blog> createBlog(

            @Parameter(
                    description = "Blog Category",
                    example = "Programming"
            )
            @RequestParam String category,

            @Parameter(
                    description = "Blog Title",
                    example = "Spring Boot Tutorial"
            )
            @RequestParam String title,

            @Parameter(
                    description = "Blog Description",
                    example = "Complete guide to Spring Boot."
            )
            @RequestParam String description,

            @Parameter(description = "Upload Blog Image")
            @RequestParam MultipartFile imgURL) throws Exception {

        return service.createBlog(category, title, description, imgURL);
    }
}