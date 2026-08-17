package com.Blog.blog.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(
        name = "Bookmark",
        description = "Represents a bookmarked blog by a user."
)
public class Bookmark {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(
            description = "Unique Bookmark ID",
            example = "1",
            accessMode = Schema.AccessMode.READ_ONLY
    )
    private Integer id;

    @Schema(
            description = "ID of the bookmarked blog",
            example = "10",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    private Integer blogId;

    @Schema(
            description = "Username of the user who bookmarked the blog",
            example = "Sharwari",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    private String userName;
}