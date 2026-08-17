package com.Blog.blog.controller;

import com.Blog.blog.dto.AuthResponse;
import com.Blog.blog.dto.RefreshRequest;
import com.Blog.blog.dto.RequestLogin;
import com.Blog.blog.model.User;
import com.Blog.blog.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin("*")
@Tag(
        name = "Authentication APIs",
        description = "Operations related to user authentication, registration, token refresh and logout"
)
public class AuthController {


    @Autowired
    private UserService service;



    @Operation(
            summary = "User Login",
            description = "Authenticates user credentials and returns access token and refresh token."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Login successful"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid username or password"
            )
    })
    @PostMapping("/login")
    public AuthResponse login(
            @RequestBody RequestLogin request
    ){

        return service.loginUser(request);
    }


    @Operation(
            summary = "Register New User",
            description = "Creates a new user account in the system."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "User registered successfully"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "User registration failed"
            )
    })
    @PostMapping("/register")
    public User register(@RequestBody User user){
        return service.registerUser(user);
    }

    @Operation(
            summary = "Refresh Access Token",
            description = "Generates a new access token using a valid refresh token."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Token refreshed successfully"
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "Invalid or expired refresh token"
            )
    })
    @PostMapping("/refresh")
    public AuthResponse refreshToken(
            @RequestBody RefreshRequest request
    ){

        return service.refreshToken(request);
    }

    @Operation(
            summary = "Logout User",
            description = "Invalidates the refresh token and logs out the user."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Logout successful"
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "Invalid refresh token"
            )
    })
    @PostMapping("/logout")
    public String logout(
            @RequestBody RefreshRequest request
    ){

        service.logout(request.getRefreshToken());

        return "Logged Out Successfully";
    }
}