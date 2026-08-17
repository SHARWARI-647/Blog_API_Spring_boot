package com.Blog.blog.controller;

import com.Blog.blog.model.Blog;
import com.Blog.blog.model.Bookmark;
import com.Blog.blog.service.BookmarkService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/bookmark")
@CrossOrigin("*")
@Tag(
        name = "Bookmark APIs",
        description = "Operations related to saving, fetching and removing blog bookmarks"
)
public class BookmarkController {


    @Autowired
    private BookmarkService service;



    @Operation(
            summary = "Save Blog Bookmark",
            description = "Adds a blog to the logged-in user's bookmark list."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Blog bookmarked successfully"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Blog not found"
            )
    })
    @PostMapping("/{blogId}")
    public Bookmark saveBlog(

            @Parameter(
                    description = "ID of the blog to bookmark",
                    example = "1"
            )
            @PathVariable Integer blogId
    ){

        return service.saveBlog(blogId);
    }





    @Operation(
            summary = "Get Saved Blogs",
            description = "Fetches all blogs bookmarked by the current user."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Bookmarks fetched successfully"
            )
    })
    @GetMapping("/getbookmark")
    public List<Blog> getSavedBlogs(){

        return service.getSavedBlogs();
    }





    @Operation(
            summary = "Remove Blog Bookmark",
            description = "Removes a blog from the user's bookmark list."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Bookmark removed successfully"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Bookmark not found"
            )
    })
    @DeleteMapping("/{blogId}")
    public String removeBookmark(

            @Parameter(
                    description = "ID of the blog to remove from bookmarks",
                    example = "1"
            )
            @PathVariable Integer blogId
    ){

        service.removeBookmark(blogId);

        return "Bookmark Removed";
    }
}