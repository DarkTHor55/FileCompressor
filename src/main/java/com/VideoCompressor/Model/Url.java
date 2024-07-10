package com.VideoCompressor.Model;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
@Data
@Entity
public class Url {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 65555)
    @Lob
    private byte[] url;
    private String fileName;
    private String fileType;
    private Date createdAt;
    private Date endedAt;

}
