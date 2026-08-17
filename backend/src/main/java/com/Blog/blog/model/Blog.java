package com.Blog.blog.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(
        name = "Blog",
        description = "Represents a blog created by a user."
)
public class Blog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(
            description = "Unique Blog ID",
            example = "1",
            accessMode = Schema.AccessMode.READ_ONLY
    )
    private Integer id;

    @Schema(
            description = "Category of the blog",
            example = "Programming",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    private String category;

    @Schema(
            description = "Title of the blog",
            example = "Spring Boot Complete Guide",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    private String title;

    @Column(length = 5000)
    @Schema(
            description = "Detailed description of the blog",
            example = "This blog explains Spring Boot from beginner to advanced level.",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    private String description;

    @Schema(
            description = "URL of the uploaded blog image",
            example = "https://your-project.supabase.co/storage/v1/object/public/blogImage/springboot.png"
    )
    private String imgURL;

    @Schema(
            description = "Total number of likes",
            example = "125",
            accessMode = Schema.AccessMode.READ_ONLY
    )
    private int likes;

    @Schema(
            description = "Total number of views",
            example = "1050",
            accessMode = Schema.AccessMode.READ_ONLY
    )
    private int views;

    @Schema(
            description = "Username of the blog author",
            example = "Sharwari",
            accessMode = Schema.AccessMode.READ_ONLY
    )
    private String userName;

    @Schema(
            description = "Date and time when the blog was created",
            example = "2026-07-14T18:30:45",
            accessMode = Schema.AccessMode.READ_ONLY
    )
    private LocalDateTime createdAt;

    @Schema(
            description = "Date and time when the blog was last updated",
            example = "2026-07-14T19:10:30",
            accessMode = Schema.AccessMode.READ_ONLY
    )
    private LocalDateTime updatedAt;
}