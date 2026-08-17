package com.Blog.blog.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Schema(
        name = "Refresh Token Request",
        description = "Request used to generate a new JWT access token using a valid refresh token."
)
public class RefreshRequest {

    @NotBlank(message = "Refresh token is required")
    @Schema(
            description = "Valid JWT refresh token",
            example = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJTaGFyd2FyaSJ9.xxxxxxxxxxxxxxxxxxxxxxxxx",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    private String refreshToken;
}