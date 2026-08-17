package com.Blog.blog.controller;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.nio.file.Path;
import java.nio.file.Paths;


@RestController
@RequestMapping("/files")
@CrossOrigin("*")
@Tag(
        name = "File APIs",
        description = "Operations related to accessing uploaded files"
)
public class FileController {


    @Operation(
            summary = "Get Uploaded File",
            description = "Retrieves an uploaded file from the server storage using file name."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "File retrieved successfully"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "File not found"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal server error"
            )
    })
    @GetMapping("/{fileName}")
    public Resource getFile(

            @Parameter(
                    description = "Name of the uploaded file",
                    example = "profile.png"
            )
            @PathVariable String fileName

    ) throws Exception {


        Path path =
                Paths.get("uploads")
                        .resolve(fileName);


        return new UrlResource(
                path.toUri()
        );
    }
}