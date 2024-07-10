package com.VideoCompressor.Service;
import com.VideoCompressor.Configuration.Compressor;
import com.VideoCompressor.Model.Url;
import com.VideoCompressor.Repository.UrlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.zip.DataFormatException;

@Service
public class UrlService {

    @Autowired
    private UrlRepository urlRepository;
    private  String getAlphaNumericString(){
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                + "0123456789"
                + "abcdefghijklmnopqrstuvxyz";
        StringBuilder sb = new StringBuilder(6);

        for (int i = 0; i < 6; i++) {
            int index
                    = (int)(AlphaNumericString.length()
                    * Math.random());
            sb.append(AlphaNumericString
                    .charAt(index));
        }
        return sb.toString();

    }

    public Url updaloadFile(MultipartFile file) throws IOException {
        Url url = new Url();
        url.setCreatedAt(new Date());
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.YEAR, 10);
        url.setEndedAt(calendar.getTime());
        url.setFileName(file.getOriginalFilename()+getAlphaNumericString());
        url.setFileType(file.getContentType());
        url.setUrl(Compressor.compressfile(file.getBytes()));
        return urlRepository.save(url);
    }
    public byte[] downloadFile(String fileName) throws IOException, DataFormatException {
        Url url = urlRepository.findByFileName(fileName);
        if (url!=null) {
            return Compressor.decompressfile(url.getUrl());
        } else {
            throw new IOException("File not found");
        }
    }

}