package com.bezkoder.springjwt.service;

import com.bezkoder.springjwt.models.Images;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface IUpload {
    List<Images> storeFile(MultipartFile[] files) throws IOException;
     byte[] readContentFile(String  fileName);

}
