package com.app.controller;

import static org.springframework.http.MediaType.IMAGE_GIF_VALUE;
import static org.springframework.http.MediaType.IMAGE_JPEG_VALUE;
import static org.springframework.http.MediaType.IMAGE_PNG_VALUE;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.app.service.ImageHandlingService;

@RestController
public class ImageController {
	
	@Autowired
	private ImageHandlingService imageService;

    @GetMapping(value = "/images/{imageName}", produces = { IMAGE_GIF_VALUE,
    		IMAGE_JPEG_VALUE, IMAGE_PNG_VALUE })
    public ResponseEntity<?> getImage(@PathVariable String imageName) {
        return ResponseEntity.status(HttpStatus.OK).body(imageService.downloadImage(imageName));
    }
}
