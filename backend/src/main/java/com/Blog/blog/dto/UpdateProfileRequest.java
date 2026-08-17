package com.Blog.blog.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import lombok.Data;

@Data
@Schema(
        name = "Update Profile Request",
        description = "Request body used to update user profile information."
)
public class UpdateProfileRequest {

    @Email(message = "Email should be valid")
    @Schema(
            description = "Email address of the user",
            example = "sharwari@gmail.com",
            requiredMode = Schema.RequiredMode.NOT_REQUIRED
    )
    private String email;


    @Schema(
            description = "Short biography or description about the user",
            example = "Java Backend Developer | Spring Boot Enthusiast",
            requiredMode = Schema.RequiredMode.NOT_REQUIRED
    )
    private String bio;


    @Schema(
            description = "URL of the user's profile image",
            example = "https://example.com/profile.jpg",
            requiredMode = Schema.RequiredMode.NOT_REQUIRED
    )
    private String profileImage;


    @Schema(
            description = "Contact phone number of the user",
            example = "+91 9876543210",
            requiredMode = Schema.RequiredMode.NOT_REQUIRED
    )
    private String phone;
}