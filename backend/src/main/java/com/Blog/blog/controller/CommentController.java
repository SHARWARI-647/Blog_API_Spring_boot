package com.Blog.blog.controller;

import com.Blog.blog.dto.CommentRequest;
import com.Blog.blog.model.Comment;
import com.Blog.blog.service.CommentService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/comments")
@CrossOrigin("*")
@Tag(
        name = "Comment APIs",
        description = "Operations related to adding and fetching blog comments"
)
public class CommentController {


    @Autowired
    private CommentService service;




    @Operation(
            summary = "Add Comment",
            description = "Adds a new comment to a specific blog post."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Comment added successfully"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Blog not found"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid comment request"
            )
    })
    @PostMapping("/{blogId}")
    public Comment addComment(

            @Parameter(
                    description = "ID of the blog on which comment will be added",
                    example = "1"
            )
            @PathVariable Integer blogId,

            @RequestBody CommentRequest request
    ){

        return service.addComment(
                blogId,
                request.getCommentText()
        );
    }





    @Operation(
            summary = "Get Blog Comments",
            description = "Fetches all comments associated with a specific blog post."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Comments fetched successfully"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Blog not found"
            )
    })
    @GetMapping("/{blogId}")
    public List<Comment> getComments(

            @Parameter(
                    description = "ID of the blog whose comments need to be fetched",
                    example = "1"
            )
            @PathVariable Integer blogId
    ){

        return service.getComments(blogId);
    }
}