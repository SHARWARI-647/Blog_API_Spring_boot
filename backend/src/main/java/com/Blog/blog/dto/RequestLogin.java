package com.Blog.blog.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Schema(
        name = "Login Request",
        description = "Request body used for user authentication."
)
public class RequestLogin {

    @NotBlank(message = "Username is required")
    @Schema(
            description = "Registered username of the user",
            example = "Sharwari",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    private String userName;

    @NotBlank(message = "Password is required")
    @Schema(
            description = "Password of the user account",
            example = "StrongPassword@123",
            requiredMode = Schema.RequiredMode.REQUIRED,
            accessMode = Schema.AccessMode.WRITE_ONLY
    )
    private String password;
}