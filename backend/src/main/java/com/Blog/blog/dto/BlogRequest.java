package com.Blog.blog.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(
        name = "Blog Request",
        description = "Request body used to create or update a blog."
)
public class BlogRequest {

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
}