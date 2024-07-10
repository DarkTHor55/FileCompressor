package com.VideoCompressor.Controller;

import com.VideoCompressor.Model.Url;
import com.VideoCompressor.Response.UrlResponse;
import com.VideoCompressor.Service.UrlService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.zip.DataFormatException;

@RestController
@RequestMapping("/api/v1")
public class UrlController {

    @Autowired
    private UrlService urlService;
    @PostMapping("/upload")
    public ResponseEntity<UrlResponse>convert(@RequestParam("file") MultipartFile file) throws IOException {
        Url url=urlService.updaloadFile(file);
        UrlResponse response=new UrlResponse();
        if(url!=null) {
            response.setFileName(file.getName());
            response.setValid(true);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        response.setFileName("Empty");
        response.setValid(false);
        return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
    }
    @GetMapping("/download/{fileName}")
    public ResponseEntity downloadFile(@PathVariable String fileName,@RequestHeader("Content-Type")String contentType) throws IOException, DataFormatException {
        UrlResponse response=new UrlResponse();
        byte[] data=urlService.downloadFile(fileName);
        if (data!=null){
            response.setFileName(fileName);
            response.setValid(true);
            return ResponseEntity.ok().contentType(MediaType.parseMediaType(contentType)).body(data);
        }
        response.setFileName("Empty");
        response.setValid(false);
        return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
    }


}
