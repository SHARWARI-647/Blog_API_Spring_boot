package com.Blog.blog.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Schema(
        name = "Comment Request",
        description = "Request body used to add a comment to a blog."
)
public class CommentRequest {

    @NotBlank(message = "Comment cannot be empty")
    @Schema(
            description = "Text content of the comment",
            example = "This blog was really helpful. Thanks for sharing!",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    private String commentText;
}