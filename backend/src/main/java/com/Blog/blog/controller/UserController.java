package com.Blog.blog.controller;

import com.Blog.blog.dto.UpdateProfileRequest;
import com.Blog.blog.model.User;
import com.Blog.blog.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/user")
@CrossOrigin("*")
@Tag(
        name = "User APIs",
        description = "Operations related to user profile and user management"
)
public class UserController {


    @Autowired
    private UserService service;




    @Operation(
            summary = "Get User By Username",
            description = "Fetches user details using the username."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "User fetched successfully"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "User not found"
            )
    })
    @GetMapping("/{userName}")
    public User getUser(

            @Parameter(
                    description = "Username of the user",
                    example = "Sharwari"
            )
            @PathVariable String userName

    ){

        return service.getUser(userName);
    }





    @Operation(
            summary = "Delete User",
            description = "Deletes a user account using username."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "User deleted successfully"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "User not found"
            )
    })
    @DeleteMapping("/delete/{userName}")
    public void deleteUser(

            @Parameter(
                    description = "Username of the user to delete",
                    example = "Sharwari"
            )
            @PathVariable String userName

    ){

        service.deleteUser(userName);
    }





    @Operation(
            summary = "Get Current User Profile",
            description = "Returns the profile details of the currently authenticated user."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Profile fetched successfully"
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "User not authenticated"
            )
    })
    @GetMapping("/profile")
    public User getProfile(){

        return service.getProfile();
    }





    @Operation(
            summary = "Update User Profile",
            description = "Updates profile information of the currently authenticated user."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Profile updated successfully"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid profile data"
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "User not authenticated"
            )
    })
    @PutMapping("/profile")
    public User updateProfile(

            @RequestBody
            UpdateProfileRequest request

    ){

        return service.updateProfile(request);
    }

}