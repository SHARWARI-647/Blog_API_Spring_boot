package com.Blog.blog.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Schema(
        name = "Blog Statistics",
        description = "Represents overall statistics of the blog application."
)
public class BlogStats {

    @Schema(
            description = "Total number of blogs available",
            example = "250"
    )
    private long totalBlogs;

    @Schema(
            description = "Total number of likes received across all blogs",
            example = "5420"
    )
    private long totalLikes;

    @Schema(
            description = "Total number of views received across all blogs",
            example = "18500"
    )
    private long totalViews;
}