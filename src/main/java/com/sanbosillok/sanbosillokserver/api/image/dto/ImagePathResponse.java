package com.sanbosillok.sanbosillokserver.api.image.dto;

import lombok.Data;

@Data
public class ImagePathResponse {
    private String imagePath;

    public ImagePathResponse(String imagePath) {
        this.imagePath = imagePath;
    }
}
