package com.sanbosillok.sanbosillokserver.api.image.service;

import com.sanbosillok.sanbosillokserver.api.image.dto.ImagePathResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ImageService {

    public ImagePathResponse upload(String path, MultipartFile file) {
        String uuid = UUID.randomUUID().toString();
        String fileName = file.getOriginalFilename();
        assert fileName != null;
        String fileExtension = fileName.substring(fileName.lastIndexOf('.'));

        File saveFile = new File(path + uuid + fileExtension);

        try {
            file.transferTo(saveFile);
            return new ImagePathResponse(path + uuid + fileExtension);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
