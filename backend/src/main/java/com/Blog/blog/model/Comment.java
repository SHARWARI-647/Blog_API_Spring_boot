package com.Blog.blog.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Schema(
        name = "Comment",
        description = "Represents a comment posted by a user on a blog."
)
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(
            description = "Unique Comment ID",
            example = "1",
            accessMode = Schema.AccessMode.READ_ONLY
    )
    private Integer id;

    @Schema(
            description = "ID of the blog on which the comment is posted",
            example = "15",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    private Integer blogId;

    @Schema(
            description = "Username of the user who posted the comment",
            example = "Sharwari",
            accessMode = Schema.AccessMode.READ_ONLY
    )
    private String userName;

    @Column(length = 1000)
    @Schema(
            description = "Comment content",
            example = "This is a very informative blog. Thanks for sharing!",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    private String comment;

    @Schema(
            description = "Date and time when the comment was created",
            example = "2026-07-14T20:15:30",
            accessMode = Schema.AccessMode.READ_ONLY
    )
    private LocalDateTime createdAt;
}