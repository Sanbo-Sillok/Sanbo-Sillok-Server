package com.sanbosillok.sanbosillokserver.api.image.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

public class ImageService {

    public void upload(String path, MultipartFile file) {
        String uuid = UUID.randomUUID().toString();
        String fileName = file.getOriginalFilename();
        assert fileName != null;
        String fileExtension = fileName.substring(fileName.lastIndexOf('.'));

        File saveFile = new File(path + uuid + fileExtension);

        try {
            file.transferTo(saveFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
