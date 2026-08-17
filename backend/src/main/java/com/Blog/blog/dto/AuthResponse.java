package com.Blog.blog.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(
        name = "Authentication Response",
        description = "Response returned after successful user authentication."
)
public class AuthResponse {

    @Schema(
            description = "JWT Access Token used to access secured APIs",
            example = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJTaGFyd2FyaSJ9.xxxxxxxxxxxxxxxxxxxxxxxxx"
    )
    private String accessToken;

    @Schema(
            description = "JWT Refresh Token used to generate a new access token after expiration",
            example = "eyJhbGciOiJIUzI1NiJ9.eyJ0eXAiOiJSZWZyZXNoVG9rZW4ifQ.xxxxxxxxxxxxxxxxxxxxxxxxx"
    )
    private String refreshToken;
}