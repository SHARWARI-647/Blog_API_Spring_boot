package com.Blog.blog.controller;

import com.Blog.blog.dto.AdminDashboardResponse;
import com.Blog.blog.model.User;
import com.Blog.blog.repository.UserRepo;
import com.Blog.blog.service.AdminService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
@CrossOrigin("*")
@Tag(
        name = "Admin APIs",
        description = "Operations related to admin dashboard and user management"
)
public class AdminController {


    @Autowired
    private AdminService service;


    @Autowired
    private UserRepo userRepo;


    @Operation(
            summary = "Get Admin Dashboard",
            description = "Returns statistics and information required for admin dashboard."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Dashboard data fetched successfully"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal server error"
            )
    })
    @GetMapping("/dashboard")
    public AdminDashboardResponse dashboard() {

        return service.getDashboard();
    }



    @Operation(
            summary = "Get All Users",
            description = "Fetches the list of all registered users."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Users fetched successfully"
            )
    })
    @GetMapping("/users")
    public List<User> getUsers() {

        return userRepo.findAll();
    }




    @Operation(
            summary = "Promote User To Admin",
            description = "Changes the role of a user from USER to ADMIN."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "User promoted to admin successfully"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "User not found"
            )
    })
    @PutMapping("/role/{username}")
    public User makeAdmin(

            @Parameter(
                    description = "Username of the user to promote as admin",
                    example = "Sharwari"
            )
            @PathVariable String username
    ){

        User user = userRepo.findByUserName(username);


        if(user == null){
            throw new RuntimeException(
                    "User Not Found"
            );
        }


        user.setRole("ADMIN");


        return userRepo.save(user);
    }
}