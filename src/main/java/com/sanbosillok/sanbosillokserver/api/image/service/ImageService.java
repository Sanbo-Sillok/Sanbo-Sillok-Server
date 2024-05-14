package com.sanbosillok.sanbosillokserver.api.image.service;

import com.sanbosillok.sanbosillokserver.api.image.dto.ImagePathResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ImageService {

    public static final String BASE_PATH = "/home/ubuntu/image/";

    public ImagePathResponse upload(MultipartFile file) {
        String uuid = UUID.randomUUID().toString();
        String fileName = file.getOriginalFilename();
        assert fileName != null;
        String fileExtension = fileName.substring(fileName.lastIndexOf('.'));

        File saveFile = new File(BASE_PATH + uuid + fileExtension);

        try {
            file.transferTo(saveFile);
            return new ImagePathResponse(BASE_PATH + uuid + fileExtension);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public byte[] getImage(String fileName) {
        String path = BASE_PATH + fileName;

        try (InputStream inputStream = new FileInputStream(path)) {
            return inputStream.readAllBytes();
        } catch (IOException e) {
            throw new IllegalArgumentException("해당 파일이 존재하지 않습니다.");
        }
    }
}
