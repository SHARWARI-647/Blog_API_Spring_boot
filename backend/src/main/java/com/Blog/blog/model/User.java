package com.Blog.blog.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Schema(
        name = "User",
        description = "Represents a registered user of the Blog application."
)
@Table(name="Users")
public class User {

    @Id
    @NotBlank(message = "Username is required")
    @Schema(
            description = "Unique username of the user",
            example = "Sharwari",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    private String userName;

    @Email(message = "Enter a valid email")
    @NotBlank(message = "Email is required")
    @Schema(
            description = "User's email address",
            example = "sharwarirahandale@gmail.com",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    private String email;

    @NotBlank(message = "Password is required")
    @Schema(
            description = "User password",
            example = "StrongPassword@123",
            requiredMode = Schema.RequiredMode.REQUIRED,
            accessMode = Schema.AccessMode.WRITE_ONLY
    )
    private String password;

    @Schema(
            description = "Short biography of the user",
            example = "Java Full Stack Developer and Blogger."
    )
    private String bio;

    @Schema(
            description = "Profile image URL",
            example = "https://your-project.supabase.co/storage/v1/object/public/profile/profile.jpg"
    )
    private String profileImage;

    @Schema(
            description = "Role assigned to the user",
            example = "ROLE_USER",
            accessMode = Schema.AccessMode.READ_ONLY
    )
    private String role;

    @Schema(
            description = "User's phone number",
            example = "9876543210"
    )
    private String phone;
}