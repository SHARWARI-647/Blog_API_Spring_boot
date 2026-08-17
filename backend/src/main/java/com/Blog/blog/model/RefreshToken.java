package com.Blog.blog.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(
        name = "Refresh Token",
        description = "Represents a JWT refresh token issued to a user."
)
public class RefreshToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(
            description = "Unique Refresh Token ID",
            example = "1",
            accessMode = Schema.AccessMode.READ_ONLY
    )
    private Long id;

    @Schema(
            description = "Refresh token used to generate a new JWT access token",
            example = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJTaGFyd2FyaSJ9.xxxxxxxxxxxxxxxxxxxxxxxxx",
            accessMode = Schema.AccessMode.READ_ONLY
    )
    private String token;

    @Schema(
            description = "Username of the user who owns this refresh token",
            example = "Sharwari",
            accessMode = Schema.AccessMode.READ_ONLY
    )
    private String userName;

    @Schema(
            description = "Expiration date and time of the refresh token",
            example = "2026-07-21T20:30:00",
            accessMode = Schema.AccessMode.READ_ONLY
    )
    private LocalDateTime expiryDate;
}