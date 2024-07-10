package com.VideoCompressor.Request;

import jakarta.persistence.Column;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;
@Data
public class UrlRequest {
    @Column(length = 65555)
    private MultipartFile video;
}
