package com.sanbosillok.sanbosillokserver.api.image.controller;

import com.sanbosillok.sanbosillokserver.api.image.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

@RestController
@RequiredArgsConstructor
public class ImageController {

    private final ImageService imageService;

    @GetMapping("/image/{fileName}")
    public byte[] getImage(@PathVariable String fileName){
        return imageService.getImage(fileName);
    }
}
