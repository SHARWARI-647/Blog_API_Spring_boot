package com.Blog.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
    @Service
    public class SupabaseStorageService {

        @Value("${supabase.url}")
        private String supabaseUrl;

        @Value("${supabase.bucket}")
        private String bucket;

        @Value("${supabase.service-key}")
        private String serviceKey;

        @Autowired
        private RestTemplate restTemplate;

        public String uploadImage(MultipartFile file) throws IOException {

            String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();

            String uploadUrl =
                    supabaseUrl +
                            "/storage/v1/object/" +
                            bucket +
                            "/" +
                            fileName;

            HttpHeaders headers = new HttpHeaders();

            headers.set("Authorization", "Bearer " + serviceKey);
            headers.set("apikey", serviceKey);
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            headers.set("x-upsert", "true");

            HttpEntity<byte[]> entity =
                    new HttpEntity<>(file.getBytes(), headers);

            ResponseEntity<String> response =
                    restTemplate.exchange(
                            uploadUrl,
                            HttpMethod.POST,
                            entity,
                            String.class
                    );

            if(response.getStatusCode().is2xxSuccessful()){

                return supabaseUrl +
                        "/storage/v1/object/public/" +
                        bucket +
                        "/" +
                        fileName;
            }

            throw new RuntimeException("Image Upload Failed");
        }

}
