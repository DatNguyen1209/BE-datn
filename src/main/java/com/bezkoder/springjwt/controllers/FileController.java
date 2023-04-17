package com.bezkoder.springjwt.controllers;

import com.bezkoder.springjwt.models.File;
import com.bezkoder.springjwt.payload.response.UploadFileResponse;
import com.bezkoder.springjwt.service.IUpload;
import com.bezkoder.springjwt.serviceimpl.DBFileStorageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class FileController {

    private static final Logger logger = LoggerFactory.getLogger(FileController.class);
    @Autowired
    private IUpload iUpload;

    @Autowired
    private DBFileStorageService dbFileStorageService;
    @CrossOrigin
    @PostMapping("/uploadFile")
    public UploadFileResponse uploadFile(@RequestParam("file") MultipartFile file) {
        File dbFile = dbFileStorageService.storeFile(file);

        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/api/downloadFile/")
                .path(dbFile.getId())
                .toUriString();

        return new UploadFileResponse(dbFile.getFileName(), fileDownloadUri,
                file.getContentType(), file.getSize());
    }
    @CrossOrigin
    @PostMapping("/uploadMultipleFiless")
    public List<UploadFileResponse> uploadMultipleFiles(@RequestParam("file") MultipartFile[] files) {
        return Arrays.asList(files)
                .stream()
                .map(file -> uploadFile(file))
                .collect(Collectors.toList());
    }
    @CrossOrigin
    @GetMapping("/downloadFile/{fileId}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileId) {
        // Load file from database
        File dbFile = dbFileStorageService.getFile(fileId);

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(dbFile.getFileType()))
//                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + dbFile.getFileName() + "\"")
                .body(new ByteArrayResource(dbFile.getData()));
    }
    @CrossOrigin
    @GetMapping("uploads/{fileName}")
    public ResponseEntity<byte[]> readFile(@PathVariable String fileName){
        try {
            byte[] bytes = iUpload.readContentFile(fileName);
            return  ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(bytes);
        }catch (Exception e){
            return ResponseEntity.noContent().build();
        }
    }
}