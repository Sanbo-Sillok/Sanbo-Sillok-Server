package com.sanbosillok.sanbosillokserver.api.member.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class JoinRequest {

    private String username;

    private String password;

    private MultipartFile studentIdImage;
}
