package com.bezkoder.springjwt.service;

import com.bezkoder.springjwt.models.Images;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.stream.Stream;

public interface FileDBService {

    Stream<Images> getAllFiles();
}
