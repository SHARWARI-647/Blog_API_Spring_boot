package com.Blog.blog.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.*;


@RestController
@RequestMapping("/upload")
@CrossOrigin("*")
@Tag(
        name = "Upload APIs",
        description = "Operations related to file uploading"
)
public class UploadController {


    @Value("${file.upload-dir}")
    private String uploadDir;



    @Operation(
            summary = "Upload File",
            description = "Uploads a file to the server storage and returns the generated file name."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "File uploaded successfully"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid file request"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "File upload failed"
            )
    })
    @PostMapping(
            consumes = "multipart/form-data"
    )
    public String uploadFile(

            @Parameter(
                    description = "File to upload",
                    required = true,
                    content = @Content(
                            mediaType = "multipart/form-data",
                            schema = @Schema(
                                    type = "string",
                                    format = "binary"
                            )
                    )
            )
            @RequestParam("file")
            MultipartFile file

    ) {


        try {


            String fileName =
                    System.currentTimeMillis()
                            + "_"
                            + file.getOriginalFilename();



            Path path =
                    Paths.get(
                            uploadDir,
                            fileName
                    );



            Files.createDirectories(
                    path.getParent()
            );



            Files.write(
                    path,
                    file.getBytes()
            );



            return fileName;



        } catch (Exception e) {


            throw new RuntimeException(
                    "File Upload Failed"
            );
        }
    }
}