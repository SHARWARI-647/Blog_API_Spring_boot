package com.Blog.blog.dto;

import com.Blog.blog.model.Blog;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Schema(
        name = "Admin Dashboard Response",
        description = "Contains statistics and insights displayed on the admin dashboard."
)
public class AdminDashboardResponse {

    @Schema(
            description = "Total number of registered users",
            example = "150"
    )
    private long totalUsers;

    @Schema(
            description = "Total number of blogs available",
            example = "320"
    )
    private long totalBlogs;

    @Schema(
            description = "Total number of comments",
            example = "875"
    )
    private long totalComments;

    @Schema(
            description = "Blog with the highest number of likes"
    )
    private Blog mostLikedBlog;

    @Schema(
            description = "Blog with the highest number of views"
    )
    private Blog mostViewedBlog;
}